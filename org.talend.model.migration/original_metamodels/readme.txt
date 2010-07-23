These files are orignal metamodel files copied from thery respective plugin and version.
400 means talend version 4.0x
410 means talend version 4.10

Most of them are transformed to the folder /org.talend.model.migration/migration_metamodel/ to work around some ATL problems with some types ATL is not compatible with.
you may find the transfomration scripts to create these files in /org.talend.model.migration/transformation/development/

Due to some ATL limitation I could not solve, such as registering multiple metamodels before an ATL transformation using Generic VM, I had 
to merge org.talend.cwm.ext.ecore with cwm.ecore this is why you have
org.talend.cwm.ext400andCWM.ecore

You'll also notice CWM.ecore is stored as cwm400.ecore whereas it should never change, it also had to be transformed because of these 
incompatible types.
This is also why there is a metadata410referingLocalCwm400.ecore, which is the original metadata410.ecore but updated to reference cwm400.ecore in the same directory.
The procedure to create this file is to open it as text file and
search for this string
../../org.talend.cwm.mip/model/CWM.ecore
and replace it with this string
CWM400.ecore

that's it.
S.Gandon