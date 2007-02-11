package com.quantum.util.versioning;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IAction;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.resource.ImageRegistry;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.widgets.FontDialog;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.IKeyBindingService;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPartSite;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.actions.ExportResourcesAction;
import org.eclipse.ui.actions.ImportResourcesAction;
import org.eclipse.ui.part.ViewPart;
import org.eclipse.ui.part.WorkbenchPart;

/**
 * This class provides backward compatibility between versions of Eclipse for
 * known differences.  It also provides utilities for testing the versions of the JDK.
 * 
 * @author BC Holmes
 */
public class VersioningHelper {
    
    public static final int ECLIPSE_VERSION_2_0_1 = 2049;
    public static final int ECLIPSE_VERSION_2_1_1 = 2135;
    public static final int ECLIPSE_VERSION_3_0_RC1 = 3054;
    public static final int ECLIPSE_VERSION_3_0_RC3 = 3061;
    
    public static final int JDK_1_3 = 3;
    public static final int JDK_1_4 = 4;
    public static final int JDK_1_5 = 5;
    
    /**
     * Set the font in a FontDialog.  In Eclipse 2.1.1, the 
     * <code>setFontData()</code> method was deprecated and an alternative
     * method, <code>setFontList()</code> was suggested in its place.  
     * 
     * @param fontDialog
     * @param fontData
     */    
    public static void setFont(FontDialog fontDialog, FontData[] fontData) {
        try {
            if (SWT.getVersion() >= ECLIPSE_VERSION_2_1_1) {
                Method method = fontDialog.getClass().getMethod(
                    "setFontList", new Class[] { fontData.getClass()});
                method.invoke(fontDialog, new Object[] {fontData});
            } else if (fontData.length > 0) {
                Method method = fontDialog.getClass().getMethod(
                    "setFontData", new Class[] { FontData.class });
                method.invoke(fontDialog, new Object[] { fontData[0] });
            }
        } catch (NoSuchMethodException e) {
            // should not happen
        } catch (IllegalArgumentException e) {
            // should not happen
        } catch (IllegalAccessException e) {
            // should not happen
        } catch (InvocationTargetException e) {
            // should not happen
        }
    }
    
    /**
     * Determine the JDK version.
     * @return JDK_1_3, JDK_1_4, or JDK_1_5
     */
    public static int getJDKVersion() {
    	if (isClassDefined("java.util.UUID")) {
    		return JDK_1_5;
    	} else if (isClassDefined("java.nio.charset.Charset")) {
    		return JDK_1_4;
    	} else {
    		return JDK_1_3;
    	}
    }

    private static boolean isClassDefined(String className) {
    	try {
    		Class.forName(className);
    		return true;
    	} catch (ClassNotFoundException e) {
    		return false;
    	}
    }
    public static void setPartName(ViewPart viewPart, String partName) {
        try {
            if (SWT.getVersion() >= ECLIPSE_VERSION_3_0_RC1) {
                Method method = WorkbenchPart.class.getDeclaredMethod(
                    "setPartName", new Class[] { String.class });
                method.invoke(viewPart, new Object[] {partName});
            } else {
                Method method = WorkbenchPart.class.getDeclaredMethod(
                    "setTitle", new Class[] { String.class });
                method.invoke(method, new Object[] { partName });
            }
        } catch (NoSuchMethodException e) {
            // should not happen
        } catch (IllegalArgumentException e) {
            // should not happen
        } catch (IllegalAccessException e) {
            // should not happen
        } catch (InvocationTargetException e) {
            // should not happen
        }
    }
    
    public static ExportResourcesAction createExportResourcesAction(IWorkbenchWindow window) {
    	ExportResourcesAction action = null;
    	
    	try {
    		if (isEclipse21OrHigher()) {
    			Constructor constructor = ExportResourcesAction.class.getConstructor(
    				new Class[] { IWorkbenchWindow.class });
    			action = (ExportResourcesAction) constructor.newInstance(
    				new Object[] { window });
    		} else {
    			Constructor constructor = ExportResourcesAction.class.getConstructor(
    				new Class[] { IWorkbench.class });
    			action = (ExportResourcesAction) constructor.newInstance(
    				new Object[] { window.getWorkbench() });
    		}
        } catch (NoSuchMethodException e) {
            // should not happen
        } catch (IllegalArgumentException e) {
            // should not happen
        } catch (IllegalAccessException e) {
            // should not happen
        } catch (InvocationTargetException e) {
            // should not happen
        } catch (InstantiationException e) {
            // should not happen
        }
    	return action;
    }
    
    public static ImportResourcesAction createImportResourcesAction(IWorkbenchWindow window) {
    	ImportResourcesAction action = null;
    	
    	try {
    		if (isEclipse21OrHigher()) {
    			Constructor constructor = ImportResourcesAction.class.getConstructor(
    				new Class[] { IWorkbenchWindow.class });
    			action = (ImportResourcesAction) constructor.newInstance(
    				new Object[] { window });
    		} else {
    			Constructor constructor = ImportResourcesAction.class.getConstructor(
    				new Class[] { IWorkbench.class });
    			action = (ImportResourcesAction) constructor.newInstance(
    				new Object[] { window.getWorkbench() });
    		}
        } catch (NoSuchMethodException e) {
            // should not happen
        } catch (IllegalArgumentException e) {
            // should not happen
        } catch (IllegalAccessException e) {
            // should not happen
        } catch (InvocationTargetException e) {
            // should not happen
        } catch (InstantiationException e) {
            // should not happen
        }
    	return action;
    }

    public static void registerActionToKeyBindingService(
    	IWorkbenchPartSite site, String[] scopes, IAction action) {
    		
    	try {
    		if (isEclipse21OrHigher()) {
                Method method = IWorkbenchPartSite.class.getMethod(
                    "getKeyBindingService", new Class[0]);
                IKeyBindingService service = (IKeyBindingService) method.invoke(site, null);
                
                method = IKeyBindingService.class.getMethod(
                	"setScopes", new Class[] { String[].class });
                method.invoke(service, new Object[] { scopes});
                
		        service.registerAction(action);
    		}
        } catch (NoSuchMethodException e) {
            // should not happen
        } catch (IllegalArgumentException e) {
            // should not happen
        } catch (IllegalAccessException e) {
            // should not happen
        } catch (InvocationTargetException e) {
            // should not happen
        }
    }
    
    public static void main(String[] args) {
    	System.out.println(SWT.getVersion());
    }

	/**
	 * @return
	 */
	public static boolean isEclipse30() {
		return SWT.getVersion() >= 3000;
	}

	/**
	 * @return
	 */
	public static boolean isEclipse21OrHigher() {
		return SWT.getVersion() >= 2100;
	}
	/**
	 * Method getDescriptor.
	 * @param registry
	 * @param imageName
	 * @return ImageDescriptor
	 */
	public static ImageDescriptor getDescriptor(
		ImageRegistry registry,
		String imageName) {
		ImageDescriptor descriptor = null;
        try {
            if (isEclipse21OrHigher()) {
                Method method = ImageRegistry.class.getMethod(
                    "getDescriptor", new Class[] { String.class });
                descriptor = (ImageDescriptor) method.invoke(registry, new Object[] {imageName});
            }
        } catch (NoSuchMethodException e) {
            // should not happen
        } catch (IllegalArgumentException e) {
            // should not happen
        } catch (IllegalAccessException e) {
            // should not happen
        } catch (InvocationTargetException e) {
            // should not happen
        }
        return descriptor;
	}

	/**
	 * @param file
	 */
	public static void openEditor(Shell shell, final IFile file) {
		if (isEclipse30()) {
            final IWorkbenchPage page =
            	PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
            final Class ideClass;
			try {
				ideClass = Class.forName("org.eclipse.ui.ide.IDE");
				final Method method = ideClass.getMethod("openEditor", 
	            		new Class[] { IWorkbenchPage.class, IFile.class});
		        shell.getDisplay().asyncExec(new Runnable() {
				    public void run() {
				    	try {
							method.invoke(null, new Object[] {page, file});
						} catch (IllegalArgumentException e) {
						} catch (IllegalAccessException e) {
						} catch (InvocationTargetException e) {
						}
				    }
		        });
			} catch (ClassNotFoundException e) {
			} catch (SecurityException e) {
			} catch (NoSuchMethodException e) {
			}
		}
	}
}
