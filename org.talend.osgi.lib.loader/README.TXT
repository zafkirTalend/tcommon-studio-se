this implements equinox hooks.
To make it work in the Eclipse IDE you are riquired to have this project folder located in the same foldr as the org.eclipse.osgi bundle.
One ways of doing this is to have this folder in the workspace (not a link)
and import the org.eclipse.osgi plugin into the workspace too using the "Plug-ins" view.
You also need to add the followin system property in your launch configurator :
     -Dosgi.framework.extensions=org.talend.osgi.lib.loader

for more information see : 
http://wiki.eclipse.org/index.php/Adaptor_Hooks
http://eclipsesource.com/blogs/2013/01/23/how-to-track-lifecycle-changes-of-osgi-bundles/
http://www.eclipsecon.org/2008/sub/attachments/Equinox_Framework_How_to_get_Hooked.pdf
http://www.eclemma.org/research/instrumentingosgi/index.html