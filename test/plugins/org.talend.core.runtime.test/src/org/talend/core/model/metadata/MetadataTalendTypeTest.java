package org.talend.core.model.metadata;

import static org.junit.Assert.*;

import java.net.URL;
import org.junit.Test;
import org.talend.commons.exception.SystemException;
import org.talend.repository.ProjectManager;

public class MetadataTalendTypeTest {

    @Test
    public void testGetProjectForderURLOfMappingsFile() throws SystemException {
        URL url = MetadataTalendType.getProjectForderURLOfMappingsFile();
        String projectLabel = ProjectManager.getInstance().getCurrentProject().getTechnicalLabel();
        assertTrue(url.getFile().endsWith(projectLabel + "/.settings/mappings/"));
    }

}
