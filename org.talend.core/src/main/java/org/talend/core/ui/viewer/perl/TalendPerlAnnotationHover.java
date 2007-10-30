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
package org.talend.core.ui.viewer.perl;

import java.util.List;

import org.eclipse.core.resources.IMarker;
import org.eclipse.core.resources.IResource;
import org.eclipse.jface.text.source.IAnnotationHover;
import org.eclipse.jface.text.source.ISourceViewer;
import org.epic.perleditor.editors.util.MarkerUtil;

/**
 * Modificatition of the class from epic to be able to use only in a viewer. <br>
 */
public class TalendPerlAnnotationHover implements IAnnotationHover {

    static final int MAX_INFO_LENGTH = 80;

    private TalendPerlSourceViewer viewer;

    public TalendPerlAnnotationHover(TalendPerlSourceViewer viewer) {
        super();
        this.viewer = viewer;
    }

    /**
     * @see org.eclipse.jface.text.source.IAnnotationHover#getHoverInfo(org.eclipse.jface.text.source.ISourceViewer,
     * int)
     */

    public String getHoverInfo(ISourceViewer viewer, int line) {
        String info = null;

        IResource resource = this.viewer.getFile();

        List markers = MarkerUtil.getMarkersForLine(resource, line + 1);
        if (markers != null) {
            info = "";
            for (int i = 0; i < markers.size(); i++) {
                IMarker marker = (IMarker) markers.get(i);
                String message = marker.getAttribute(IMarker.MESSAGE, (String) null);
                if (message != null && message.trim().length() > 0) {

                    if (message.length() > MAX_INFO_LENGTH) {
                        message = splitMessage(message);
                    }
                    info += message;

                    if (i != markers.size() - 1) {
                        info += "\n";
                    }
                }
            }
        }
        return info;
    }

    private String splitMessage(String message) {
        String result = "";

        if (message.length() <= MAX_INFO_LENGTH) {
            return message;
        }

        String tmpStr = new String(message);

        while (tmpStr.length() > MAX_INFO_LENGTH) {

            int spacepos = tmpStr.indexOf(" ", MAX_INFO_LENGTH);

            if (spacepos != -1) {
                result += tmpStr.substring(0, spacepos) + "\n";
                tmpStr = tmpStr.substring(spacepos);
            } else {
                result += tmpStr.substring(0, MAX_INFO_LENGTH) + "\n";
                tmpStr = tmpStr.substring(MAX_INFO_LENGTH);
            }

        }

        result += tmpStr;

        return result;
    }

}
