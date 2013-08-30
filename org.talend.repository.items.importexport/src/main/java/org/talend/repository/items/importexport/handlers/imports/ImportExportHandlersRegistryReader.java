// ============================================================================
//
// Copyright (C) 2006-2013 Talend Inc. - www.talend.com
//
// This source code is available under agreement available at
// %InstallDIR%\features\org.talend.rcp.branding.%PRODUCTNAME%\%PRODUCTNAME%license.txt
//
// You should have received a copy of the agreement
// along with this program; if not, write to Talend SA
// 9 rue Pages 92150 Suresnes, France
//
// ============================================================================
package org.talend.repository.items.importexport.handlers.imports;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;
import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.SafeRunner;
import org.talend.core.utils.RegistryReader;
import org.talend.repository.items.importexport.handlers.model.EPriority;
import org.talend.repository.items.importexport.handlers.model.ImportRegistry;

/**
 * DOC ggu class global comment. Detailled comment
 */
public class ImportExportHandlersRegistryReader extends RegistryReader {

    private static Logger log = Logger.getLogger(ImportExportHandlersRegistryReader.class);

    private List<ImportRegistry> imortRegisties = new ArrayList<ImportRegistry>();

    public ImportExportHandlersRegistryReader() {
        super("org.talend.repository.items.importexport", "handler"); //$NON-NLS-1$ //$NON-NLS-2$
    }

    public void init() {
        readRegistry();
        Collections.sort(this.imortRegisties, new Comparator<ImportRegistry>() {

            @Override
            public int compare(ImportRegistry arg0, ImportRegistry arg1) {
                return arg1.getPriority().compareTo(arg0.getPriority());
            }
        });
    }

    public AbstractImportExecutableHandler[] getImportHandlers() {
        List<AbstractImportExecutableHandler> handers = new ArrayList<AbstractImportExecutableHandler>();
        for (ImportRegistry ir : this.imortRegisties) {
            handers.add(ir.getHandler());
        }
        return handers.toArray(new AbstractImportExecutableHandler[0]);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.talend.core.utils.RegistryReader#readElement(org.eclipse.core.runtime.IConfigurationElement)
     */
    @Override
    protected boolean readElement(final IConfigurationElement element) {
        if ("importProvider".equals(element.getName())) { //$NON-NLS-1$
            SafeRunner.run(new RegistryReader.RegistrySafeRunnable() {

                @Override
                public void run() throws Exception {
                    try {
                        String id = element.getAttribute("id"); //$NON-NLS-1$
                        String name = element.getAttribute("name"); //$NON-NLS-1$
                        String description = element.getAttribute("description"); //$NON-NLS-1$
                        String priorityString = element.getAttribute("priority"); //$NON-NLS-1$

                        EPriority priority = (priorityString != null && priorityString.length() > 0) ? EPriority
                                .valueOf(priorityString.toUpperCase()) : EPriority.NORMAL;
                        AbstractImportExecutableHandler handler = (AbstractImportExecutableHandler) element
                                .createExecutableExtension("handler"); //$NON-NLS-1$
                        if (handler == null) {
                            log.error("Can't create handlder for " + name); //$NON-NLS-1$
                            return;
                        }

                        ImportRegistry importRegistry = new ImportRegistry(element.getContributor().getName(), id);
                        importRegistry.setName(name);
                        importRegistry.setDescription(description);
                        importRegistry.setHandler(handler);
                        importRegistry.setPriority(priority);

                        imortRegisties.add(importRegistry);
                    } catch (Exception e) {
                        log.error(e.getMessage(), e);
                    }

                }

            });
            return true;
        }// else return false
        return false;
    }

}
