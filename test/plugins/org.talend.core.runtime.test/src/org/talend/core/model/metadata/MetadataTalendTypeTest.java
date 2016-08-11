package org.talend.core.model.metadata;

import static org.junit.Assert.*;

import java.net.URL;
import org.junit.Test;
import org.talend.commons.exception.SystemException;

public class MetadataTalendTypeTest {

    @Test
    public void testGetSystemForderURLOfMappingsFile() throws SystemException {
        URL url = MetadataTalendType.getSystemForderURLOfMappingsFile();
        assertTrue(url.getFile().endsWith("/org.talend.core.runtime/mappings/"));
    }

    @Test
    public void testGetProjectForderURLOfMappingsFile() throws SystemException {
        URL url = MetadataTalendType.getProjectForderURLOfMappingsFile();
        assertTrue(url.getFile().endsWith("/.settings/mappings/"));
    }

}
