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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.eclipse.core.runtime.IPath;
import org.eclipse.core.runtime.Path;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.Resource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.talend.commons.emf.CwmResource;
import org.talend.core.model.properties.ProcessItem;
import org.talend.core.model.properties.PropertiesFactory;
import org.talend.core.model.properties.Property;
import org.talend.core.model.repository.IRepositoryViewObject;
import org.talend.core.repository.constants.FileConstants;
import org.talend.repository.items.importexport.handlers.model.ItemRecord;
import org.talend.repository.items.importexport.manager.ResourcesManager;

import static org.mockito.Mockito.*;

/**
 * DOC ggu class global comment. Detailled comment
 */
@SuppressWarnings("nls")
public class ImportBasicHandlerTest {

    private IPath projPath;

    private IPath processPropPath1, processItemPath1, processItemPath2, processItemPath3, connPropPath, connItemPath;

    private Set<IPath> initPathes;

    @Before
    public void initialize() {
        projPath = new Path("TEST/" + FileConstants.LOCAL_PROJECT_FILENAME);

        processPropPath1 = new Path("TEST/process/test1_0.1.properties");
        processItemPath1 = new Path("TEST/process/test1_0.1.item");
        processItemPath2 = new Path("TEST/process/abc/xyz/test2_0.1.properties");
        processItemPath3 = new Path("TEST/process/abc/xyz/test2_0.1.item");
        connPropPath = new Path("TEST/metadata/connections/abc/xyz/test3_0.1.properties");
        connItemPath = new Path("TEST/metadata/connections/abc/xyz/test3_0.1.item");

        initPathes = new HashSet<IPath>();
        initPathes.add(projPath);
        initPathes.add(processPropPath1);
        initPathes.add(processItemPath1);
        initPathes.add(processItemPath2);
        initPathes.add(processItemPath3);
        initPathes.add(connPropPath);
        initPathes.add(connItemPath);
    }

    @Test
    public void testValid4NoBasePath() throws Exception {
        // process
        ImportBasicHandler basicHandler = new ImportBasicHandler();

        ResourcesManager resManager = mock(ResourcesManager.class);
        Set<IPath> pathes = new HashSet<IPath>(initPathes);
        when(resManager.getPaths()).thenReturn(pathes);

        Assert.assertFalse(basicHandler.valid(resManager, processPropPath1));
        Assert.assertFalse(basicHandler.valid(resManager, processItemPath1));

        Assert.assertFalse(basicHandler.valid(resManager, processItemPath2));
        Assert.assertFalse(basicHandler.valid(resManager, processItemPath3));

        Assert.assertFalse(basicHandler.valid(resManager, connPropPath));
        Assert.assertFalse(basicHandler.valid(resManager, connItemPath));

        Assert.assertFalse(basicHandler.valid(resManager, projPath));
    }

    @Test
    public void testValid4Process() throws Exception {
        // process
        ImportBasicHandler basicHandler = new ImportBasicHandler();
        Map<String, String> data = new HashMap<String, String>();
        data.put(IImportConstants.PARAM_PATH, "process,context");
        basicHandler.setInitializationData(null, null, data);

        ResourcesManager resManager = mock(ResourcesManager.class);
        Set<IPath> pathes = new HashSet<IPath>(initPathes);
        when(resManager.getPaths()).thenReturn(pathes);

        Assert.assertTrue(basicHandler.valid(resManager, processPropPath1));
        Assert.assertFalse(basicHandler.valid(resManager, processItemPath1));

        Assert.assertTrue(basicHandler.valid(resManager, processItemPath2));
        Assert.assertFalse(basicHandler.valid(resManager, processItemPath3));

        Assert.assertFalse(basicHandler.valid(resManager, connPropPath));
        Assert.assertFalse(basicHandler.valid(resManager, connItemPath));

        Assert.assertFalse(basicHandler.valid(resManager, projPath));
    }

    @Test
    public void testValid4Context() throws Exception {
        // for context

        ImportBasicHandler basicHandler = new ImportBasicHandler();

        Map<String, String> data = new HashMap<String, String>();
        data.put(IImportConstants.PARAM_PATH, "context");
        basicHandler.setInitializationData(null, null, data);

        ResourcesManager resManager = mock(ResourcesManager.class);
        Set<IPath> pathes = new HashSet<IPath>(initPathes);
        when(resManager.getPaths()).thenReturn(pathes);

        Assert.assertFalse(basicHandler.valid(resManager, processPropPath1));
        Assert.assertFalse(basicHandler.valid(resManager, processItemPath1));
        Assert.assertFalse(basicHandler.valid(resManager, processItemPath2));
        Assert.assertFalse(basicHandler.valid(resManager, processItemPath3));
        Assert.assertFalse(basicHandler.valid(resManager, connPropPath));
        Assert.assertFalse(basicHandler.valid(resManager, connItemPath));

        IPath contextPropPath = new Path("TEST/context/test_0.1.properties");
        IPath contextItemPath = new Path("TEST/context/test_0.1.item");
        pathes.add(contextPropPath);
        pathes.add(contextItemPath);
        Assert.assertTrue(basicHandler.valid(resManager, contextPropPath));
        Assert.assertFalse(basicHandler.valid(resManager, contextItemPath));
    }

    @Test
    public void testValid4Connection() throws Exception {
        // DB connections
        ImportBasicHandler basicHandler = new ImportBasicHandler();

        Map<String, String> data = new HashMap<String, String>();
        data.put(IImportConstants.PARAM_PATH, "context,metadata/connections");
        basicHandler.setInitializationData(null, null, data);

        ResourcesManager resManager = mock(ResourcesManager.class);
        Set<IPath> pathes = new HashSet<IPath>(initPathes);
        when(resManager.getPaths()).thenReturn(pathes);

        Assert.assertFalse(basicHandler.valid(resManager, processPropPath1));
        Assert.assertFalse(basicHandler.valid(resManager, processItemPath1));

        Assert.assertFalse(basicHandler.valid(resManager, processItemPath2));
        Assert.assertFalse(basicHandler.valid(resManager, processItemPath3));

        Assert.assertTrue(basicHandler.valid(resManager, connPropPath));
        Assert.assertFalse(basicHandler.valid(resManager, connItemPath));
    }

    @Test
    public void testValid4Unknown() throws Exception {
        ImportBasicHandler basicHandler = new ImportBasicHandler();

        Map<String, String> data = new HashMap<String, String>();
        data.put(IImportConstants.PARAM_PATH, "XXX");
        basicHandler.setInitializationData(null, null, data);

        ResourcesManager resManager = mock(ResourcesManager.class);
        Set<IPath> pathes = new HashSet<IPath>(initPathes);
        when(resManager.getPaths()).thenReturn(pathes);

        Assert.assertFalse(basicHandler.valid(resManager, processPropPath1));
        Assert.assertFalse(basicHandler.valid(resManager, processItemPath1));
        Assert.assertFalse(basicHandler.valid(resManager, processItemPath2));
        Assert.assertFalse(basicHandler.valid(resManager, processItemPath3));
        Assert.assertFalse(basicHandler.valid(resManager, connPropPath));
        Assert.assertFalse(basicHandler.valid(resManager, connItemPath));
    }

    @Test
    public void testCheckAndSetProject4NoProject() {
        ImportBasicHandler basicHandler = new ImportBasicHandler();

        ItemRecord itemRecord = new ItemRecord(processPropPath1);
        Property property = mock(Property.class);
        itemRecord.setProperty(property);

        ProcessItem item = PropertiesFactory.eINSTANCE.createProcessItem();
        when(property.getItem()).thenReturn(item);

        ResourcesManager resManager = mock(ResourcesManager.class);
        basicHandler.checkAndSetProject(resManager, itemRecord);

        Assert.assertFalse(itemRecord.getErrors().isEmpty());
        Assert.assertFalse(itemRecord.isValid());
        Assert.assertTrue(itemRecord.getErrors().size() == 1);
    }

    @Test
    public void testIsSame() {
        ItemRecord itemRecord1 = mock(ItemRecord.class);
        Property property1 = mock(Property.class);
        when(itemRecord1.getProperty()).thenReturn(property1);
        when(property1.getId()).thenReturn("123456789");
        when(property1.getLabel()).thenReturn("test");

        ItemRecord itemRecord2 = mock(ItemRecord.class);
        Property property2 = mock(Property.class);
        when(itemRecord2.getProperty()).thenReturn(property2);
        when(property2.getId()).thenReturn("123456789");
        when(property2.getLabel()).thenReturn("test");

        ImportBasicHandler basicHandler = new ImportBasicHandler();
        Assert.assertTrue(basicHandler.isSame(itemRecord1, itemRecord2));
    }

    @Test
    public void testIsSame4DiffId() {
        ItemRecord itemRecord1 = mock(ItemRecord.class);
        Property property1 = mock(Property.class);
        when(itemRecord1.getProperty()).thenReturn(property1);
        when(property1.getId()).thenReturn("987654321");
        when(property1.getVersion()).thenReturn("0.1");

        ItemRecord itemRecord2 = mock(ItemRecord.class);
        Property property2 = mock(Property.class);
        when(itemRecord2.getProperty()).thenReturn(property2);
        when(property2.getId()).thenReturn("123456789");
        when(property2.getVersion()).thenReturn("0.1");

        ImportBasicHandler basicHandler = new ImportBasicHandler();
        Assert.assertFalse(basicHandler.isSame(itemRecord1, itemRecord2));
    }

    @Test
    public void testIsSame4DiffVersion() {
        ItemRecord itemRecord1 = mock(ItemRecord.class);
        Property property1 = mock(Property.class);
        when(itemRecord1.getProperty()).thenReturn(property1);
        when(property1.getId()).thenReturn("123456789");
        when(property1.getVersion()).thenReturn("0.1");

        ItemRecord itemRecord2 = mock(ItemRecord.class);
        Property property2 = mock(Property.class);
        when(itemRecord2.getProperty()).thenReturn(property2);
        when(property2.getId()).thenReturn("123456789");
        when(property2.getVersion()).thenReturn("0.2");

        ImportBasicHandler basicHandler = new ImportBasicHandler();
        Assert.assertFalse(basicHandler.isSame(itemRecord1, itemRecord2));
    }

    @Test
    public void testIsSameName() {
        ItemRecord itemRecord1 = mock(ItemRecord.class);
        Property property1 = mock(Property.class);
        when(itemRecord1.getProperty()).thenReturn(property1);
        when(property1.getLabel()).thenReturn("test");
        // when(property1.getId()).thenReturn("123456789");

        IRepositoryViewObject repViewObj = mock(IRepositoryViewObject.class);
        when(repViewObj.getLabel()).thenReturn("test");
        // when(repViewObj.getId()).thenReturn("123456789");

        ImportBasicHandler basicHandler = new ImportBasicHandler();
        // now only check the label
        Assert.assertTrue(basicHandler.isSameName(itemRecord1, repViewObj));
    }

    @Test
    public void testIsSameName4DiffLabel() {
        ItemRecord itemRecord1 = mock(ItemRecord.class);
        Property property1 = mock(Property.class);
        when(itemRecord1.getProperty()).thenReturn(property1);
        when(property1.getLabel()).thenReturn("test1");
        // when(property1.getId()).thenReturn("123456789");

        IRepositoryViewObject repViewObj = mock(IRepositoryViewObject.class);
        when(repViewObj.getLabel()).thenReturn("test2");
        // when(repViewObj.getId()).thenReturn("123456789");

        ImportBasicHandler basicHandler = new ImportBasicHandler();
        // only when same label with diff id. don't care the same id with diff label.
        Assert.assertFalse(basicHandler.isSameName(itemRecord1, repViewObj));
    }

    // @Test
    // public void testIsSameName4DiffId() {
    // ItemRecord itemRecord1 = mock(ItemRecord.class);
    // Property property1 = mock(Property.class);
    // when(itemRecord1.getProperty()).thenReturn(property1);
    // when(property1.getLabel()).thenReturn("test");
    // when(property1.getId()).thenReturn("987654321");
    //
    // IRepositoryViewObject repViewObj = mock(IRepositoryViewObject.class);
    // when(repViewObj.getLabel()).thenReturn("test");
    // when(repViewObj.getId()).thenReturn("123456789");
    //
    // ImportBasicHandler basicHandler = new ImportBasicHandler();
    // // diff id, don't care same label.
    // Assert.assertTrue(basicHandler.isSameName(itemRecord1, repViewObj));
    // }

    @Test
    public void testCreateItemResource() {
        URI uri = URI.createURI(processPropPath1.toPortableString());
        ImportBasicHandler basicHandler = new ImportBasicHandler();
        Resource resource = basicHandler.createItemResource(uri);

        Assert.assertNotNull(resource);
        Assert.assertEquals(CwmResource.class, resource.getClass());

    }
}
