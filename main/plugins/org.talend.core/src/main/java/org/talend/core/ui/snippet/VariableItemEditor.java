// ============================================================================
//
// Copyright (C) 2006-2011 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.core.ui.snippet;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import org.eclipse.jface.text.BadLocationException;
import org.eclipse.jface.text.Document;
import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.ITextOperationTarget;
import org.eclipse.jface.text.ITextViewer;
import org.eclipse.jface.text.contentassist.ContentAssistant;
import org.eclipse.jface.text.contentassist.ICompletionProposal;
import org.eclipse.jface.text.contentassist.IContentAssistProcessor;
import org.eclipse.jface.text.contentassist.IContentAssistant;
import org.eclipse.jface.text.contentassist.IContextInformation;
import org.eclipse.jface.text.contentassist.IContextInformationValidator;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.jface.viewers.ISelectionChangedListener;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.swt.SWT;
import org.eclipse.swt.accessibility.ACC;
import org.eclipse.swt.accessibility.AccessibleAdapter;
import org.eclipse.swt.accessibility.AccessibleEvent;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.custom.VerifyKeyListener;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.events.VerifyEvent;
import org.eclipse.swt.events.VerifyListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.talend.commons.ui.runtime.exception.ExceptionHandler;
import org.talend.commons.utils.StringUtils;
import org.talend.core.i18n.Messages;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.SnippetItem;
import org.talend.core.model.properties.SnippetVariable;

/**
 * A snippet item editor that can define snippet variables
 */
public class VariableItemEditor implements ISnippetEditor {

    /**
     * DOC bqian VariableItemEditor class global comment. Detailled comment <br/>
     * 
     */
    class CompletionProposal implements ICompletionProposal, Comparable<CompletionProposal> {

        int fReplacementOffset;

        String varName;

        public CompletionProposal(int fReplacementOffset, String varName) {
            this.fReplacementOffset = fReplacementOffset;
            this.varName = varName;
        }

        public void apply(IDocument document) {
            try {
                document.replace(fReplacementOffset, 0, "${" + getDisplayString() + "}"); //$NON-NLS-1$ //$NON-NLS-2$
            } catch (BadLocationException e) {
                ExceptionHandler.process(e);
            }
        }

        public String getAdditionalProposalInfo() {
            return null;
        }

        public IContextInformation getContextInformation() {
            return null;
        }

        public String getDisplayString() {
            String display = varName;
            if (display == null)
                display = ""; //$NON-NLS-1$
            return display;
        }

        public Image getImage() {
            return null;
        }

        public Point getSelection(IDocument document) {
            return new Point(fReplacementOffset + 3 + getDisplayString().length(), 0);
        }

        /*
         * (non-Javadoc)
         * 
         * @see java.lang.Comparable#compareTo(java.lang.Object)
         */
        public int compareTo(CompletionProposal o) {
            return Collator.getInstance().compare(this.getDisplayString(), o.getDisplayString());
        }
    }

    /**
     * DOC bqian VariableItemEditor class global comment. Detailled comment <br/>
     * 
     */
    private class ItemEditorSourceViewerConfiguration extends SourceViewerConfiguration {

        private IContentAssistProcessor fProcessor = null;

        public ItemEditorSourceViewerConfiguration() {
            super();
            fProcessor = new IContentAssistProcessor() {

                char[] autochars = new char[] {};

                public ICompletionProposal[] computeCompletionProposals(ITextViewer viewer, int documentOffset) {
                    java.util.List vars = new ArrayList();
                    final ITextViewer textViewer = viewer;
                    Iterator keys = fTableViewer.getColumnData()[0].keySet().iterator();
                    while (keys.hasNext()) {
                        Object key = keys.next();
                        final String varID = (String) fTableViewer.getColumnData()[0].get(key);
                        if (varID != null) {
                            ICompletionProposal proposal = new CompletionProposal(textViewer.getSelectedRange().x, varID);
                            vars.add(proposal);
                        }
                    }
                    ICompletionProposal[] proposals = new ICompletionProposal[vars.size()];
                    vars.toArray(proposals);
                    return sortProposals(proposals);
                }

                public IContextInformation[] computeContextInformation(ITextViewer viewer, int documentOffset) {
                    return null;
                }

                public char[] getCompletionProposalAutoActivationCharacters() {
                    return autochars;
                }

                public char[] getContextInformationAutoActivationCharacters() {
                    return null;
                }

                public IContextInformationValidator getContextInformationValidator() {
                    return null;
                }

                public String getErrorMessage() {
                    return null;
                }

                public ICompletionProposal[] sortProposals(ICompletionProposal[] proposals) {
                    if (proposals == null || proposals.length == 0)
                        return proposals;
                    Arrays.sort(proposals);
                    // List sortedList = new ArrayList();
                    // for (int i = 0; i < sortedProposals.length; i++)
                    // sortedList.add(sortedProposals[i]);
                    // ICompletionProposal[] results = new ICompletionProposal[sortedProposals.length];
                    // sortedList.toArray(results);
                    return proposals;
                }
            };
        }

        /*
         * @see SourceViewerConfiguration#getContentAssistant(ISourceViewer)
         */
        public IContentAssistant getContentAssistant(ISourceViewer sourceViewer) {
            ContentAssistant assistant = new ContentAssistant();
            assistant.setContentAssistProcessor(fProcessor, IDocument.DEFAULT_CONTENT_TYPE);

            assistant.enableAutoActivation(false);
            assistant.setAutoActivationDelay(500);
            assistant.setProposalPopupOrientation(IContentAssistant.PROPOSAL_OVERLAY);
            assistant.setContextInformationPopupOrientation(IContentAssistant.CONTEXT_INFO_ABOVE);

            // Color background= something;
            // assistant.setContextInformationPopupBackground(background);
            // assistant.setContextSelectorBackground(background);
            // assistant.setProposalSelectorBackground(background);

            return assistant;
        }
    }

    protected StyledText content = null;

    protected SnippetItem fItem = null;

    protected StringPropertyTableViewer fTableViewer = null;

    private Button addButton, removeButton, insertVariableButton;

    private SourceViewer sourceViewer;

    private boolean readonly = false;

    public VariableItemEditor() {
        super();
    }

    public VariableItemEditor(boolean readonly) {
        super();
        this.readonly = readonly;
    }

    public Control createContents(Composite parent) {
        // create the contents of the variable editing dialog

        Composite variableComposite = new Composite(parent, SWT.NONE);
        variableComposite.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        ((GridData) variableComposite.getLayoutData()).widthHint = parent.getDisplay().getClientArea().width / 3;
        GridLayout sublayout = new GridLayout();
        sublayout.numColumns = 2;
        sublayout.marginWidth = 0;
        sublayout.makeColumnsEqualWidth = false;
        variableComposite.setLayout(sublayout);

        Label nameLabel = new Label(variableComposite, SWT.NONE);
        nameLabel.setText(Messages.getString("VariableItemEditor.variable")); //$NON-NLS-1$
        GridData doubleData = new GridData(GridData.FILL_HORIZONTAL);
        nameLabel.setLayoutData(doubleData);

        Label throwAway = new Label(variableComposite, SWT.NONE);
        throwAway.setLayoutData(new GridData());

        // saved and made final here to update the template text area below
        final String nameProperty = Messages.getString("VariableItemEditor.name"); //$NON-NLS-1$
        fTableViewer = new StringPropertyTableViewer();
        fTableViewer.setColumnNames(new String[] { nameProperty, "Description", "Default Value" }); //$NON-NLS-1$ //$NON-NLS-2$
        fTableViewer.createContents(variableComposite);

        GridData data = new GridData(GridData.FILL_HORIZONTAL | GridData.GRAB_HORIZONTAL);
        data.heightHint = fTableViewer.getTable().getItemHeight() * 6;
        fTableViewer.getControl().setLayoutData(data);

        // For now, each column gets its data from a separate hashtable. In
        // each,
        // the key is the ID of the variable with the values being the name,
        // description, and default value.
        List<SnippetVariable> variables = (List<SnippetVariable>) fItem.getVariables();
        for (SnippetVariable var : variables) {
            fTableViewer.getColumnData()[0].put(var.getId(), var.getName());
            if (var.getDescription() != null)
                fTableViewer.getColumnData()[1].put(var.getId(), var.getDescription());
            else
                fTableViewer.getColumnData()[1].put(var.getId(), ""); //$NON-NLS-1$
            if (var.getValue() != null)
                fTableViewer.getColumnData()[2].put(var.getId(), var.getValue());
            else
                fTableViewer.getColumnData()[2].put(var.getId(), ""); //$NON-NLS-1$
        }

        fTableViewer.refresh();

        Composite variableButtons = new Composite(variableComposite, SWT.NULL);
        variableButtons.setLayoutData(new GridData(GridData.VERTICAL_ALIGN_BEGINNING | GridData.HORIZONTAL_ALIGN_END));
        variableButtons.setLayout(new GridLayout());

        // Add the New button with a listener to add a new variable without
        // user
        // input.
        // TODO: for usability, throw up a dialog in the middle
        addButton = new Button(variableButtons, SWT.PUSH);
        addButton.setText(Messages.getString("VariableItemEditor.new")); //$NON-NLS-1$
        addButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));
        addButton.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
                widgetSelected(e);
            }

            public void widgetSelected(SelectionEvent e) {
                int i = 1;
                String newId = "name_" + i++; //$NON-NLS-1$
                while (fTableViewer.getColumnData()[0].keySet().contains(newId)) {
                    newId = "name_" + i++; //$NON-NLS-1$
                }
                fTableViewer.getColumnData()[0].put(newId, newId);
                fTableViewer.getColumnData()[1].put(newId, ""); //$NON-NLS-1$
                fTableViewer.getColumnData()[2].put(newId, ""); //$NON-NLS-1$
                fTableViewer.refresh();
            }
        });
        // add the Remove button with a listener to enable it only when a
        // cell is selected and in focus
        removeButton = new Button(variableButtons, SWT.PUSH);
        removeButton.setText(Messages.getString("VariableItemEditor.remove")); //$NON-NLS-1$
        removeButton.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        nameLabel = new Label(parent, SWT.NONE);
        nameLabel.setText(Messages.getString("VariableItemEditor.twmplatePattern")); //$NON-NLS-1$
        nameLabel.setLayoutData(new GridData(GridData.FILL_HORIZONTAL));

        // create a source viewer for to edit the text (makes it easier to
        // hook into content assist)
        sourceViewer = new SourceViewer(parent, null, SWT.BORDER | SWT.V_SCROLL | SWT.H_SCROLL);
        sourceViewer.setEditable(true);

        // Update EOLs bug 80231
        String systemEOL = System.getProperty("line.separator"); //$NON-NLS-1$
        String text = StringUtils.replace(fItem.getContent(), "\r\n", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
        text = StringUtils.replace(text, "\r", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
        if (!"\n".equals(systemEOL) && systemEOL != null) { //$NON-NLS-1$
            text = StringUtils.replace(text, "\n", systemEOL); //$NON-NLS-1$
        }
        sourceViewer.setDocument(new Document(text));

        final SourceViewerConfiguration sourceViewerConfiguration = new ItemEditorSourceViewerConfiguration();
        sourceViewer.configure(sourceViewerConfiguration);
        content = sourceViewer.getTextWidget();

        // add a listener to the Remove button to remove the variable
        // corresponding to the selected row
        removeButton.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
                widgetSelected(e);
            }

            public void widgetSelected(SelectionEvent e) {
                Object id = fTableViewer.getSelection();
                if (id == null)
                    return;
                // remove from template as well
                Object varKey = fTableViewer.getColumnData()[0].get(id);
                String template = sourceViewer.getDocument().get();
                sourceViewer.getDocument().set(StringUtils.replace(template, "${" + varKey + "}", "")); //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$
                fTableViewer.getColumnData()[0].remove(id);
                fTableViewer.getColumnData()[1].remove(id);
                fTableViewer.getColumnData()[2].remove(id);

                // #222898, clear selection so that SWT errors are avoided
                fTableViewer.getTable().setSelection(new int[0]);

                fTableViewer.refresh();
                removeButton.setEnabled(false);
            }
        });

        GridData contentData = new GridData(GridData.FILL_BOTH);
        contentData.heightHint = parent.getDisplay().getClientArea().height / 5;
        content.setLayoutData(contentData);
        content.setFont(org.eclipse.jface.resource.JFaceResources.getTextFont());
        content.setHorizontalPixel(2);
        // add a verify key listener to trigger the source viewer's undo
        // action
        content.addVerifyKeyListener(new VerifyKeyListener() {

            public void verifyKey(VerifyEvent event) {
                if (!event.doit)
                    return;
                boolean doContentAssistOperation = (event.stateMask == SWT.CTRL && event.character == ' ');
                if (doContentAssistOperation) {
                    sourceViewer.doOperation(ISourceViewer.CONTENTASSIST_PROPOSALS);
                    event.doit = false;
                }
                // CTRL-Z (derived from the JDT template editor's way of
                // assigning the event.character)
                else if (event.stateMask == SWT.CTRL && (event.character == ('z' - 'a' + 1))) {
                    sourceViewer.doOperation(ITextOperationTarget.UNDO);
                    event.doit = false;
                }
            }
        });
        // add a verify listener to trigger the source viewer's content assist
        // and undo actions
        // TODO: determine if this is duplicated effort
        content.addVerifyListener(new VerifyListener() {

            public void verifyText(VerifyEvent event) {
                if (!event.doit || !((event.stateMask & SWT.CTRL) == 1))
                    return;
                boolean doContentAssistOperation = (event.stateMask == SWT.CTRL && event.character == ' ');
                // prevent inserting a space character when content assist is
                // invoked
                if (doContentAssistOperation) {
                    event.doit = false;
                }
                // CTRL-Z
                else if (event.stateMask == SWT.CTRL && event.character == ('z' - 'a' + 1)) {
                    event.doit = false;
                }
            }
        });

        // specifically associate the template pattern label w/ the content
        // styled text so screen reader reads it
        setAccessible(content, "Template Pattern:"); //$NON-NLS-1$

        // add a value change listener to the fTableViewer so that changes to
        // the name property of a variable
        // will rewrite the template text to use the new name immediately
        // TODO: verify with the user that this is OK with them
        // fTableViewer.addValueChangedListener(new ValueChangedListener() {
        //
        // public void valueChanged(String key, String property, String oldValue, String newValue) {
        // if (property.equals(nameProperty)) {
        // String newText = StringUtils.replace(content.getText(), "${" + oldValue + "}", "${" + newValue + "}");
        // //$NON-NLS-1$ //$NON-NLS-2$ //$NON-NLS-3$ //$NON-NLS-4$
        // content.setText(newText);
        // }
        // }
        // });

        /*
         * Add the "Insert Variable Placeholder" button to invoke content assist like the JDT Template editor.
         * 
         * I'm not sure I like this idea as it's mostly a crutch for not making content assist obviously available in
         * the source viewer.
         */
        insertVariableButton = new Button(parent, SWT.PUSH);
        insertVariableButton.setText(Messages.getString("VariableItemEditor.insertVariable")); //$NON-NLS-1$
        insertVariableButton.setLayoutData(new GridData(GridData.HORIZONTAL_ALIGN_BEGINNING | GridData.VERTICAL_ALIGN_BEGINNING));
        insertVariableButton.setEnabled(fTableViewer.getTable().getItemCount() > 0);
        insertVariableButton.addSelectionListener(new SelectionListener() {

            public void widgetDefaultSelected(SelectionEvent e) {
                widgetSelected(e);
            }

            public void widgetSelected(SelectionEvent e) {
                // JDT template editor invokes Content Assist on a
                // configured Java Source Viewer. Not that simple here.
                sourceViewer.getTextWidget().setFocus();
                sourceViewer.doOperation(ISourceViewer.CONTENTASSIST_PROPOSALS);
            }
        });

        fTableViewer.addSelectionChangedListener(new ISelectionChangedListener() {

            public void selectionChanged(SelectionChangedEvent event) {
                insertVariableButton.setEnabled(fTableViewer.getTable().getItemCount() > 0);
                removeButton.setEnabled(fTableViewer.getControl().isFocusControl() && event.getSelection() != null
                        && (!event.getSelection().isEmpty()));
            }
        });

        addButton.addSelectionListener(new SelectionAdapter() {

            public void widgetSelected(SelectionEvent e) {
                insertVariableButton.setEnabled(true);
            }
        });
        removeButton.addSelectionListener(new SelectionAdapter() {

            public void widgetDefaultSelected(SelectionEvent e) {
                insertVariableButton.setEnabled(fTableViewer.getTable().getItemCount() > 0);
            }

            public void widgetSelected(SelectionEvent e) {
                insertVariableButton.setEnabled(fTableViewer.getTable().getItemCount() > 0);
            }
        });
        // set the initial enabled state
        checkStates();
        removeButton.setEnabled(false);
        return parent;
    }

    public SnippetItem getItem() {
        return fItem;
    }

    /**
     * Specifically set the reporting name of a control for accessibility
     */
    private void setAccessible(Control control, String name) {
        if (control == null)
            return;
        final String n = name;
        control.getAccessible().addAccessibleListener(new AccessibleAdapter() {

            public void getName(AccessibleEvent e) {
                if (e.childID == ACC.CHILDID_SELF)
                    e.result = n;
            }
        });
    }

    public void setItem(SnippetItem item) {
        fItem = item;
    }

    public void updateItem() {
        String text = content.getText();
        // Update related to bug 80231
        text = StringUtils.replace(text, "\r\n", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
        text = StringUtils.replace(text, "\r", "\n"); //$NON-NLS-1$ //$NON-NLS-2$
        fItem.setContent(text);

        fItem.getVariables().clear();

        Iterator ids = fTableViewer.getColumnData()[0].keySet().iterator();
        while (ids.hasNext()) {
            Object key = ids.next();
            SnippetVariable var = PropertiesFactory.eINSTANCE.createSnippetVariable();
            var.setId((String) key);
            var.setName((String) fTableViewer.getColumnData()[0].get(key));
            var.setDescription((String) fTableViewer.getColumnData()[1].get(key));
            var.setValue((String) fTableViewer.getColumnData()[2].get(key));
            fItem.getVariables().add(var);
        }
    }

    private void checkStates() {
        fTableViewer.getTable().setEnabled(!readonly);
        addButton.setEnabled(!readonly);
        removeButton.setEnabled(!readonly);
        insertVariableButton.setEnabled(!readonly);
        sourceViewer.setEditable(!readonly);
    }
}
