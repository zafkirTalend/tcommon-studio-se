// ============================================================================
//
// Talend Community Edition
//
// Copyright (C) 2006 Talend - www.talend.com
//
// This library is free software; you can redistribute it and/or
// modify it under the terms of the GNU Lesser General Public
// License as published by the Free Software Foundation; either
// version 2.1 of the License, or (at your option) any later version.
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
package org.talend.commons.utils.data.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import junit.framework.TestCase;

import org.apache.commons.collections.CollectionUtils;
import org.talend.commons.utils.Bean;

/**
 * 
 * @author amaumont <br/> $Id$
 */
public class MultiplePropertiesBeanComparatorTest extends TestCase {
    private final int fiveHundred = 500;
    private final int ten = 10;

    /*
     */
    @SuppressWarnings("unchecked")
    public void test() {

        List<Bean> list = new ArrayList<Bean>();
        List<String> listValidTest = new ArrayList<String>();

        for (int i = 0; i < fiveHundred; i++) {
            for (int j = 0; j < ten; j++) {
                for (int k = 0; k < ten; k++) {
                    Bean bean = new Bean();
                    bean.setString1(String.valueOf(i));
                    bean.setString2(String.valueOf(j));
                    bean.setString3(String.valueOf(k));
                    list.add(bean);
                    listValidTest.add("" + i + j + k);

                    bean = new Bean();
                    bean.setString1(String.valueOf(i));
                    bean.setString2(String.valueOf(k));
                    bean.setString3(String.valueOf(j));
                    list.add(bean);
                    listValidTest.add("" + i + k + j);

                    bean = new Bean();
                    bean.setString1(String.valueOf(k));
                    bean.setString2(String.valueOf(i));
                    bean.setString3(String.valueOf(j));
                    list.add(bean);
                    listValidTest.add("" + k + i + j);

                }
            }
        }
        MultiplePropertiesBeanComparator comparator = new MultiplePropertiesBeanComparator(true, new String[] {
                "string1", "string2", "string3" });

        long time1 = System.currentTimeMillis();
        Collections.sort(list, comparator);
        long time2 = System.currentTimeMillis();
        System.out.println("Time of execution:" + (time2 - time1));

        Collections.sort(listValidTest);

        List<String> listValidTest2 = new ArrayList<String>();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Bean bean = (Bean) iter.next();
            listValidTest2.add(bean.getString1() + bean.getString2() + bean.getString3());
        }

        // for( Iterator iter = list.iterator(); iter.hasNext(); ) {
        // Bean bean = (Bean)iter.next();
        //      
        // System.out.println(bean.getString1()+ " " + bean.getString2() + " " + bean.getString3());
        //      
        // }
        //    
        // int lstSize = listValidTest.size();
        // for( int i = 0; i < lstSize; i++ ) {
        // String string = (String)listValidTest.get( i );
        // String string2 = (String)listValidTest2.get( i );
        //      
        // System.out.println(string +"|||||" + string2);
        // }
        System.out.println(listValidTest.size() + " objects sorted by three properties");
        assertTrue(CollectionUtils.isEqualCollection(listValidTest, listValidTest2));

    }

    @SuppressWarnings("unchecked")
    public void test2() {
        List<Bean> list = new ArrayList<Bean>();
        List<String> listValidTest = new ArrayList<String>();

        for (int i = 0; i < fiveHundred; i++) {
            for (int j = 0; j < ten; j++) {
                for (int k = 0; k < ten; k++) {
                    Bean bean = new Bean();
                    bean.setString1(String.valueOf(i));
                    bean.setString2(String.valueOf(j));
                    bean.setString3(String.valueOf(k));
                    list.add(bean);
                    listValidTest.add("" + i + j + k);

                    bean = new Bean();
                    bean.setString1(String.valueOf(i));
                    bean.setString2(String.valueOf(k));
                    bean.setString3(String.valueOf(j));
                    list.add(bean);
                    listValidTest.add("" + i + k + j);

                    bean = new Bean();
                    bean.setString1(String.valueOf(k));
                    bean.setString2(String.valueOf(i));
                    bean.setString3(String.valueOf(j));
                    list.add(bean);
                    listValidTest.add("" + k + i + j);

                }
            }
        }
        MultiplePropertiesBeanComparator comparator = new MultiplePropertiesBeanComparator(true,
                new String[] { "string1" });

        long time1 = System.currentTimeMillis();
        Collections.sort(list, comparator);
        long time2 = System.currentTimeMillis();
        System.out.println("Time of execution:" + (time2 - time1));

        Collections.sort(listValidTest);

        List<String> listValidTest2 = new ArrayList<String>();
        for (Iterator iter = list.iterator(); iter.hasNext();) {
            Bean bean = (Bean) iter.next();
            listValidTest2.add(bean.getString1() + bean.getString2() + bean.getString3());
        }

        // for( Iterator iter = list.iterator(); iter.hasNext(); ) {
        // Bean bean = (Bean)iter.next();
        //      
        // System.out.println(bean.getString1()+ " " + bean.getString2() + " " + bean.getString3());
        //      
        // }
        //    
        // int lstSize = listValidTest.size();
        // for( int i = 0; i < lstSize; i++ ) {
        // String string = (String)listValidTest.get( i );
        // String string2 = (String)listValidTest2.get( i );
        //      
        // System.out.println(string +"|||||" + string2);
        // }
        System.out.println(listValidTest.size() + " objects sorted with by one property");
        assertTrue(CollectionUtils.isEqualCollection(listValidTest, listValidTest2));

    }

}
