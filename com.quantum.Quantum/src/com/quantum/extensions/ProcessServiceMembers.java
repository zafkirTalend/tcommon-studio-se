
/*
 * Created on 16-feb-2004
 *
 */
package com.quantum.extensions;
import java.util.Vector;

import com.quantum.view.bookmark.BookmarkView;
import com.quantum.view.tableview.TableView;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtension;
import org.eclipse.core.runtime.IExtensionPoint;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.eclipse.ui.IViewPart;
import org.eclipse.ui.WorkbenchException;
import org.w3c.dom.Document;

/**
 * Extension processing logic for the <code>functions</code> extension-point.
 * Extract specific information about each function. Create callback
 * function object when required.
 *
 */
public class ProcessServiceMembers {

	/**
	 * The fully-qualified name of the functions extension-point for this plug-in.
	 */
	private static final String EXTENSION_POINT_METADATA =
		"com.quantum.Quantum.metadata";
	private static final String EXTENSION_POINT_DATA =
		"com.quantum.Quantum.data";

	/**
	 * Name of the XML attribute designating a metadata actor label.
	 */
	private static final String FUNCTION_NAME_ATTRIBUTE = "label";

	/**
	 * Name of the XML attribute designating the fully-qualified name 
	 * of the implementation class of a function.
	 */
	private static final String CLASS_ATTRIBUTE = "class";

	/**
	 * Perform initial extension processing for the members of the
	 * <code>functions</code> extension-point. Make calls to the user interface
	 * module to add the functions of an extension to the UI functions grid.
	 * For each function, a virtual proxy callback object is created and handed
	 * to the user interface module. The proxy class is a nested top-level 
	 * class and is therefore known at compile time. The actual (real) callback
	 * objects configured into extensions are instantiated and initialized in 
	 * a lazy fashion by the proxy callback objects.
	 * 
	 * @param grid The UI functions grid exposing the functions configured
	 * 		into <code>functions</code> extensions.
	 * 
	 */
	public static void process(IViewPart view, Vector extensionActions) throws WorkbenchException {
		
		extensionActions.clear();
		
        IExtensionRegistry registry = Platform.getExtensionRegistry();
		String extPointId; 
		// We have two different extension points, we choose the needed one based
		// on the view that calls this function. 
		// Our two extension points have the same attributes, so the only change is the name
		// If the implementation differed more, we should probably make two different functions
		if (view instanceof BookmarkView)
			extPointId = EXTENSION_POINT_METADATA;
		else if (view instanceof TableView)
			extPointId = EXTENSION_POINT_DATA;
		else
			return;
		
		IExtensionPoint extensionPoint =
			registry.getExtensionPoint(extPointId);
		if (extensionPoint == null) {
			throw new WorkbenchException(
				"unable to resolve extension-point: " + extPointId);
		}
		IConfigurationElement[] members =
			extensionPoint.getConfigurationElements();

		// For each service:
		for (int m = 0; m < members.length; m++) {
			IConfigurationElement member = members[m];
			// Get the label of the extender plugin and the ID of the extension.
			IExtension extension = member.getDeclaringExtension();
			String pluginLabel =
				extension.getDeclaringPluginDescriptor().getLabel();
			if (pluginLabel == null) {
				pluginLabel = "[unnamed plugin]";
			}
			// Get the name of the operation implemented by the service.
			// The operation name is a service attribute in the extension's XML specification.
			String functionName = member.getAttribute(FUNCTION_NAME_ATTRIBUTE);
			if (functionName == null) {
				functionName = "[unnamed function]";
			}
			String label = pluginLabel + "/" + functionName;
			// Add a row to support this operation to the UI grid.
			IQuantumExtension proxy = null;
			if (view instanceof BookmarkView)
				proxy = (IMetadataExtension) new MetadataFunctionProxy(member);
			else if (view instanceof TableView)
				proxy = (IDataExtension) new DataFunctionProxy(member);
			//grid.addFunction(proxy, functionName, label);
			extensionActions.add(new ExtensionAction(view, label, label, proxy));
		}
	}

	/**
	 * Virtual proxy class for function invokation.
	 * Members of this class support lazy processing by fronting the real 
	 * implementations of arithmetic functions configured into extensions.
	 */
	private static class MetadataFunctionProxy implements IMetadataExtension {
		// The "real" implementation of the function.
		private IMetadataExtension delegate = null;
		// The configured state of the extension element representing this arithmetic function.
		private IConfigurationElement element;
		// Whether this function has been invoked before. 
		// Used for lazy evaluation of the service.
		private boolean invoked = false;

		/**
		 * Construct a virtual proxy to stand in place of an extension function.
		 * The constructor simply keeps track of an arithmetic function's configured state
		 * without at this time instantiating the implementation of the function.
		 * 
		 * @param element The configuration of this arithmetic function.
		 */
		public MetadataFunctionProxy(IConfigurationElement element) {
			this.element = element;
		}

		/**
		 * Compute the function value. 
		 * The proxy computation first instantiates the implementation
		 * of the function, if this is the first time the function is called,
		 * and then delegates to that implementation. The instantiation and
		 * initialization of the delegate implementation uses the standard 
		 * callback object instantiation and initialization methods of Eclipse.
		 * 
		 * @see com.bolour.sample.eclipse.service.ui.IFunction#compute(long)
		 */
		public final void run(Document doc) {
			try {
				getDelegate().run(doc);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		/**
		 * Instantiate and initialize the implementation of the function if
		 * this is the first time the proxy has been called.
		 * 
		 * @return The implementation delegate (same as the instance variable <code>delegate</code>.
		 * @throws Exception If the callback object is misconfigured.
		 */
		private final IMetadataExtension getDelegate() throws Exception {
			if (invoked) {
				return delegate;
			}
			invoked = true;
			try {
				Object callback =
					element.createExecutableExtension(CLASS_ATTRIBUTE);
				if (!(callback instanceof IMetadataExtension)) {
					String message =
						"callback class '"
							+ callback.getClass().getName()
							+ "' is not an IFunction";
					System.err.println(message);
					throw new ClassCastException(message);
				}
				delegate = (IMetadataExtension) callback;
			} catch (CoreException ex) {
				System.err.println(ex.getMessage());
				ex.printStackTrace();
				throw ex;
			} catch (Error err) {
				System.err.println(err.getMessage());
				err.printStackTrace();
				throw err;
			}
			return delegate;
		}
	}
	/**
	 * Virtual proxy class for function invokation.
	 * Members of this class support lazy processing by fronting the real 
	 * implementations of arithmetic functions configured into extensions.
	 */
	private static class DataFunctionProxy implements IDataExtension {
		// The "real" implementation of the function.
		private IDataExtension delegate = null;
		// The configured state of the extension element representing this arithmetic function.
		private IConfigurationElement element;
		// Whether this function has been invoked before. 
		// Used for lazy evaluation of the service.
		private boolean invoked = false;

		/**
		 * Construct a virtual proxy to stand in place of an extension function.
		 * The constructor simply keeps track of an arithmetic function's configured state
		 * without at this time instantiating the implementation of the function.
		 * 
		 * @param element The configuration of this arithmetic function.
		 */
		public DataFunctionProxy(IConfigurationElement element) {
			this.element = element;
		}

		/**
		 * Compute the function value. 
		 * The proxy computation first instantiates the implementation
		 * of the function, if this is the first time the function is called,
		 * and then delegates to that implementation. The instantiation and
		 * initialization of the delegate implementation uses the standard 
		 * callback object instantiation and initialization methods of Eclipse.
		 * 
		 * @see com.bolour.sample.eclipse.service.ui.IFunction#compute(long)
		 */
		public final void run(Document doc) {
			try {
				getDelegate().run(doc);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		/**
		 * Instantiate and initialize the implementation of the function if
		 * this is the first time the proxy has been called.
		 * 
		 * @return The implementation delegate (same as the instance variable <code>delegate</code>.
		 * @throws Exception If the callback object is misconfigured.
		 */
		private final IDataExtension getDelegate() throws Exception {
			if (invoked) {
				return delegate;
			}
			invoked = true;
			try {
				Object callback =
					element.createExecutableExtension(CLASS_ATTRIBUTE);
				if (!(callback instanceof IDataExtension)) {
					String message =
						"callback class '"
							+ callback.getClass().getName()
							+ "' is not an IFunction";
					System.err.println(message);
					throw new ClassCastException(message);
				}
				delegate = (IDataExtension) callback;
			} catch (CoreException ex) {
				System.err.println(ex.getMessage());
				ex.printStackTrace();
				throw ex;
			} catch (Error err) {
				System.err.println(err.getMessage());
				err.printStackTrace();
				throw err;
			}
			return delegate;
		}
	}

}

