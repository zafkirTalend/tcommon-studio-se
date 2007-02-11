package com.quantum.wizards;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import com.quantum.ImageStore;
import com.quantum.Messages;
import com.quantum.model.Bookmark;
import com.quantum.model.BookmarkCollection;
import com.quantum.model.xml.ModelToXMLConverter;
import com.quantum.ui.dialog.ExceptionDisplayDialog;
import com.quantum.util.xml.XMLHelper;

import org.eclipse.jface.viewers.CheckStateChangedEvent;
import org.eclipse.jface.viewers.CheckboxTreeViewer;
import org.eclipse.jface.viewers.ICheckStateListener;
import org.eclipse.jface.viewers.ILabelProvider;
import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITreeContentProvider;
import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;
import org.w3c.dom.Document;

/**
 * @author BC
 */
public class ExportBookmarkWizardPage extends WizardPage {
    
    public class ContentProvider implements ITreeContentProvider {

        public Object[] getChildren(Object parentElement) {
            if (parentElement instanceof BookmarkCollection) {
                return ((BookmarkCollection) parentElement).getBookmarks();
            } else {
                return new Object[0];
            }
        }
        public Object getParent(Object element) {
            if (element instanceof Bookmark) {
                return BookmarkCollection.getInstance();
            } else {
                return null;
            }
        }
        public boolean hasChildren(Object element) {
            if (element instanceof BookmarkCollection) {
                return ((BookmarkCollection) element).getBookmarks().length > 0;
            } else {
                return false;
            }
        }
        public Object[] getElements(Object inputElement) {
            return getChildren(inputElement);
        }
        public void dispose() {
        }
        public void inputChanged(Viewer viewer, Object oldInput, Object newInput) {
        }
    }
    
    public class LabelProvider implements ILabelProvider {

        public Image getImage(Object element) {
            if (element instanceof Bookmark) {
                return ImageStore.getImage(ImageStore.BOOKMARK);
            } else {
                return null;
            }
        }

        public String getText(Object element) {
            
            if (element instanceof Bookmark) {
                return ((Bookmark) element).getName();
            } else {
                return element.toString();
            }
        }

        public void addListener(ILabelProviderListener listener) {
        }

        public void dispose() {
        }

        public boolean isLabelProperty(Object element, String property) {
            return false;
        }

        public void removeListener(ILabelProviderListener listener) {
        }
    }
    
    private CheckboxTreeViewer treeViewer;
    private Text fileNameText;
    
    private boolean sourceIsSelected = false;
    private boolean destinationIsSelected = false;

    /**
     * @param pageName
     */
    protected ExportBookmarkWizardPage() {
        super("page1");
        setTitle(Messages.getString(getClass(), "title"));
    }

    public void createControl(Composite pageContainer) {
        this.sourceIsSelected = false;
        this.destinationIsSelected = false;
        
        Composite composite = new Composite(pageContainer, SWT.NULL);
        composite.setLayout(new GridLayout());
        composite.setLayoutData(
            new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL));

        this.treeViewer = new CheckboxTreeViewer(composite, 
            SWT.CHECK | SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL);
        this.treeViewer.setContentProvider(new ContentProvider());
        this.treeViewer.setLabelProvider(new LabelProvider());
        this.treeViewer.setInput(BookmarkCollection.getInstance());
        selectAll();
        
        this.treeViewer.addCheckStateListener(new ICheckStateListener() {
            public void checkStateChanged(CheckStateChangedEvent event) {
                setSourceIsSelected(
                    ExportBookmarkWizardPage.this.treeViewer.getCheckedElements().length > 0);
            }
        });

        GridData data = new GridData();
        data.horizontalAlignment = GridData.HORIZONTAL_ALIGN_FILL;
        data.verticalAlignment = GridData.VERTICAL_ALIGN_BEGINNING;
        data.grabExcessHorizontalSpace = true;
        data.heightHint = 200;
        data.widthHint = 400;
        this.treeViewer.getControl().setLayoutData(data);

        Composite buttons = new Composite(composite, SWT.NULL);
        buttons.setLayout(new GridLayout(2, false));
        buttons.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING));
        
        Button selectAll = new Button(buttons, SWT.NONE);
        selectAll.setText(Messages.getString(getClass(), "selectAll"));
        selectAll.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                selectAll();
            }
        });

        Button deselectAll = new Button(buttons, SWT.NONE);
        deselectAll.setText(Messages.getString(getClass(), "deselectAll"));
        deselectAll.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                deselectAll();
            }
        });
        
        createDestinationArea(composite);

        setControl(composite);
    }

    private void createDestinationArea(Composite composite) {
        Composite fileArea = new Composite(composite, SWT.NULL);
        fileArea.setLayout(new GridLayout(3, false));
        fileArea.setLayoutData(
            new GridData(GridData.HORIZONTAL_ALIGN_FILL | GridData.VERTICAL_ALIGN_FILL));
        Label label = new Label(fileArea, SWT.NONE);
        label.setText("File name:");
        
        this.fileNameText = new Text(fileArea, SWT.BORDER);
        GridData data = new GridData();
        data.horizontalAlignment = GridData.HORIZONTAL_ALIGN_FILL;
        data.widthHint = 300;
        this.fileNameText.setLayoutData(data);
        this.fileNameText.addModifyListener(new ModifyListener() {
            public void modifyText(ModifyEvent event) {
                String text = ((Text) event.getSource()).getText();
                setDestinationIsSelected(text != null && text.trim().length() > 0);
            }
        });
        
        Button button = new Button(fileArea, SWT.NONE);
        button.setText("Browse");
        button.addSelectionListener(new SelectionAdapter() {
            public void widgetSelected(SelectionEvent event) {
                promptForFile();
            }
        });
    }
    
    private void setSourceIsSelected(boolean selected) {
        this.sourceIsSelected = selected;
        setPageComplete(selected & this.destinationIsSelected);
    }
    
    private void setDestinationIsSelected(boolean selected) {
        this.destinationIsSelected = selected;
        setPageComplete(selected & this.sourceIsSelected);
    }
    
    protected void promptForFile() {
    
        FileDialog dialog = new FileDialog(getShell(), SWT.SAVE);
        dialog.setFilterExtensions(new String[] { "xml" });
        dialog.setFilterNames(new String[] { "XML Files (*.xml)"});
        String filename = dialog.open();
        if (filename != null) {
            this.fileNameText.setText(filename);
        }
    }
    protected void deselectAll() {
        this.treeViewer.setCheckedElements(new Object[0]);
        setSourceIsSelected(false);
    }

    protected void selectAll() {
        Bookmark[] bookmarks = BookmarkCollection.getInstance().getBookmarks();
        this.treeViewer.setCheckedElements(bookmarks);
        setSourceIsSelected(bookmarks.length > 0);
    }
    
    public boolean finish() {
        
        String fileName = this.fileNameText.getText();
        File file = new File(fileName);
        if (file.exists()) {
            // prompt for overwrite
        } else if (!file.getParentFile().exists()) {
            // do what?
        }
        
        try {
            Object[] bookmarks = this.treeViewer.getCheckedElements();
            Document document = XMLHelper.createEmptyDocument();
            ModelToXMLConverter.getInstance().createRoot(document);
                
            for (int i = 0, length = (bookmarks == null) ? 0 : bookmarks.length;
                i < length;
                i++) {
                ModelToXMLConverter.getInstance().convert(
                    document.getDocumentElement(), (Bookmark) bookmarks[i]);
            }

            FileWriter writer = new FileWriter(file);
            try {
                XMLHelper.write(writer, document);
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            ExceptionDisplayDialog.openError(getShell(), 
                Messages.getString(getClass(), "error.IOException.title"), 
                Messages.getString(getClass(), "error.IOException.message", 
                    new Object[] { fileName }), e);
        } catch (ParserConfigurationException e) {
            ExceptionDisplayDialog.openError(getShell(), 
                Messages.getString(getClass(), "error.IOException.title"), 
                Messages.getString(getClass(), "error.IOException.message", 
                    new Object[] { fileName }), e);
        }
        
        return true;
    }
}
