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
package org.talend.core.model.metadata;

import java.util.ArrayList;
import java.util.List;

/**
 * DOC qwei class global comment. Detailled comment <br/>
 * 
 * $Id: talend.epf 1 2006-09-29 17:06:40 +0000 (ææäº, 29 ä¹æ 2006) nrousseau $
 * 
 */
public class TalendTypePreLenRetriever {

    private MappingType mappingtype;

    private int dbLength;

    private int dbPrecion;

    private String talendTypeNew;

    public TalendTypePreLenRetriever(MappingType mappingType, int dbLength, int dbPrecion) {
        this.mappingtype = mappingType;
        this.dbLength = dbLength;
        this.dbPrecion = dbPrecion;
        this.talendTypeNew = mappingType.getTalendType();
        List<TalendTypePrecisionLength> list = new ArrayList();
        TalendTypePrecisionLength talendTypeFloat = new TalendTypePrecisionLength("id_Float", 32, 0, 16, 0);
        TalendTypePrecisionLength talendTypeInt = new TalendTypePrecisionLength("id_Integer", 16, 0, 0, 0);
        TalendTypePrecisionLength talendTypeLong = new TalendTypePrecisionLength("id_Long", 64, 0, 32, 17);
        TalendTypePrecisionLength talendTypeDouble = new TalendTypePrecisionLength("id_Double", 64, 0, 64, 33);
        list.add(talendTypeFloat);
        list.add(talendTypeInt);
        list.add(talendTypeDouble);
        list.add(talendTypeLong);
        init(list);
    }

    public void init(List<TalendTypePrecisionLength> talendTypeList) {
        if (mappingtype.getTalendType().equals("id_Float") || mappingtype.getTalendType().equals("id_Integer")
                || mappingtype.getTalendType().equals("id_Long") || mappingtype.getTalendType().equals("id_Double")) {
            for (TalendTypePrecisionLength talendtype : talendTypeList) {
                if ((talendtype.getLengthMin() <= dbLength && dbLength <= talendtype.getLengthMax())
                        && (talendtype.getPrecMin() <= dbPrecion && dbPrecion <= talendtype.getPrecMax())) {
                    talendTypeNew = talendtype.getTalendtype();
                    return;
                }
            }
        } else {
            return;
        }

    }

    public String getMappingType() {

        return this.talendTypeNew;
    }
}
