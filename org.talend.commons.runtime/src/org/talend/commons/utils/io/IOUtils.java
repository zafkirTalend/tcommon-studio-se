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
package org.talend.commons.utils.io;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Adler32;
import java.util.zip.CheckedInputStream;

/**
 * DOC xtab class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ææäº, 29 ä¹æ 2006) nrousseau $
 * 
 */
public class IOUtils {

    public static long computeCRC(InputStream in) {
        long unitCRC = 0;

        BufferedInputStream bufferedInputStream = null;

        try {
            bufferedInputStream = new BufferedInputStream(in);

            // Compute Adler-32 checksum
            CheckedInputStream cis = new CheckedInputStream(bufferedInputStream, new Adler32());
            byte[] tempBuf = new byte[128];
            while (cis.read(tempBuf) >= 0) {
                // do nothing
            }
            unitCRC = cis.getChecksum().getValue();
        } catch (IOException e) {
            return -1;
        } finally {
            try {
                bufferedInputStream.close();
            } catch (Exception e) {
                // ignore me even if i'm null
            }
        }
        return unitCRC;
    }

    /**
     * This function will avoid to re-calculate the CRC even if it was calculated in a different OS before. This will
     * avoid each character of \r
     * 
     * @param in
     * @return
     */
    public static long computeCRConTextFile(InputStream in) {
        long unitCRC = 0;

        BufferedInputStream bufferedInputStream = null;

        try {
            bufferedInputStream = new BufferedInputStream(in);

            ByteArrayOutputStream baos = new ByteArrayOutputStream();

            DataInputStream dis = new DataInputStream(bufferedInputStream);
            int len = 0;
            byte[] buf = new byte[1024];
            while (((len = dis.read(buf))) != -1) {
                baos.write(buf, 0, len);
            }
            dis.close();

            String currentContent = baos.toString();
            currentContent = currentContent.replace("\r", ""); //$NON-NLS-1$//$NON-NLS-2$ 

            ByteArrayInputStream bis = new ByteArrayInputStream(currentContent.getBytes());

            // Compute Adler-32 checksum
            CheckedInputStream cis = new CheckedInputStream(bis, new Adler32());
            byte[] tempBuf = new byte[128];
            while (cis.read(tempBuf) >= 0) {
                // do nothing
            }
            unitCRC = cis.getChecksum().getValue();
        } catch (IOException e) {
            return -1;
        } finally {
            try {
                bufferedInputStream.close();
            } catch (Exception e) {
                // ignore me even if i'm null
            }
        }
        return unitCRC;
    }

}
