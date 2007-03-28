// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006-2007 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
//
// ============================================================================

package org.talend.core.model.properties.helper;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.talend.core.model.properties.ByteArray;
import org.talend.core.model.properties.PropertiesFactory;

/**
 * DOC mhelleboid class global comment. Detailled comment <br/>
 * 
 * $Id$
 * 
 */
public class ByteArrayResource extends ResourceImpl {

    public ByteArrayResource(URI uri) {
        super(uri);
    }

    protected void doLoad(InputStream inputStream, Map options) throws IOException {
        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
        byte[] content = new byte[bufferedInputStream.available()];
        bufferedInputStream.read(content);
        bufferedInputStream.close();
        ByteArray byteArray = PropertiesFactory.eINSTANCE.createByteArray();
        byteArray.setInnerContent(content);
        getContents().add(byteArray);
    }

    protected void doSave(OutputStream outputStream, Map options) throws IOException {
        ByteArray byteArray = (ByteArray) getContents().get(0);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
        bufferedOutputStream.write(byteArray.getInnerContent());
        bufferedOutputStream.flush();
    }
    
    protected void doUnload() {
        super.doUnload();
    }
}
