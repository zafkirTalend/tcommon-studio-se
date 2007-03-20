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
package org.talend.core.ui.proposal;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.eclipse.jface.fieldassist.IContentProposal;
import org.eclipse.jface.fieldassist.IContentProposalProvider;

/**
 * DOC amaumont class global comment. Detailled comment <br/>
 * 
 * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40Z nrousseau $
 * 
 */
public class JavaSimpleDateFormatProposalProvider implements IContentProposalProvider {

    public static void main(String[] args) {
        Date date = new Date(-3000, 11, 31, 0, 0, 0);
        SimpleDateFormat sdf = new SimpleDateFormat("'G=' G '\ny=' y  '\nyyyy=' yyyy '\nM=' M  '\nMMM=' MMM  '\nMMMM=' MMMM '\nw=' w '\nW=' W '\nD=' D '\nd=' d '\nF=' F '\nE=' E \'\nEEEE=\' EEEE '\na=' a '\nH=' H '\nk=' k '\nK=' K '\nh=' h  '\nhh=' hh  '\nhhh=' hhh '\nm=' m '\ns=' s '\nS=' S '\nz=' z '\nzzzz=' zzzz '\nzzzzz=' zzzzz  '\nzzzzzz=' zzzzzz '\nZ=' Z ");
        String string = sdf.format(date);
        System.out.println(string);
        
//        G   Era designator  Text    AD
//        y   Year    Year    1996; 96
//        M   Month in year   Month   July; Jul; 07
//        w   Week in year    Number  27
//        W   Week in month   Number  2
//        D   Day in year     Number  189
//        d   Day in month    Number  10
//        F   Day of week in month    Number  2
//        E   Day in week     Text    Tuesday; Tue
//        a   Am/pm marker    Text    PM
//        H   Hour in day (0-23)  Number  0
//        k   Hour in day (1-24)  Number  24
//        K   Hour in am/pm (0-11)    Number  0
//        h   Hour in am/pm (1-12)    Number  12
//        m   Minute in hour  Number  30
//        s   Second in minute    Number  55
//        S   Millisecond     Number  978
//        z   Time zone   General time zone   Pacific Standard Time; PST; GMT-08:00
//        Z   Time zone   RFC 822 time zone   -0800
    }
    
    /*
     * (non-Javadoc)
     * 
     * @see org.eclipse.jface.fieldassist.IContentProposalProvider#getProposals(java.lang.String, int)
     */
    public IContentProposal[] getProposals(String contents, int position) {

        IContentProposal[] cp = new IContentProposal[] { 
                new DateFormatContentProposal("\"dd-MM-yyyy\" : common format", "dd-MM-yyyy", "Date\n Examples : \n  01-01-2007\n  31-12-2007"), 
                new DateFormatContentProposal("\"MM-dd-yyyy\" : common format", "MM-dd-yyyy", "Date\n Examples : \n  01-01-2007\n  12-31-2007"), 
                new DateFormatContentProposal("\"dd/MM/yyyy\" : common format", "dd/MM/yyyy", "Date\n Examples : \n  01/01/2007\n  31/12/2007"), 
                new DateFormatContentProposal("\"MM/dd/yyyy\" : common format", "MM/dd/yyyy", "Date\n Examples : \n  01/01/2007\n  12/31/2007"), 
                new DateFormatContentProposal("\"HH:mm:ss\" : common format", "HH:mm:ss", "Date\n Examples : \n  00:00:00 \n  23:59:59"), 
                new DateFormatContentProposal("\"yy\" : Year (00-99)", "yy", "Year \nExamples : \n  98\n  07"), 
                new DateFormatContentProposal("\"yyyy\" : Year", "yyyy", "Year \nExamples : \n  1998\n  2007"), 
                new DateFormatContentProposal("\"MM\" : Month in year (01-12)", "MM", "Month in year \nExamples : \n  01 .. 12"), 
                new DateFormatContentProposal("\"MMM\" : Month in year (Text)", "MMM", "Month in year \nExamples : \n  Dec"), 
                new DateFormatContentProposal("\"MMMM\" : Month in year (Text)", "MMMM", "Month in year \nExamples : \n  December"), 
                new DateFormatContentProposal("\"DDD\" : Day in year (001-366)", "DDD", "Day in year \nExamples : 001 .. 366"), 
                new DateFormatContentProposal("\"dd\" : Day in month (01-31)", "dd", "Day in month \nExamples : 01 .. 31 "), 
                new DateFormatContentProposal("\"F\" : Week in month (1-5)", "F", "Week in month \nExamples : 1 .. 5"), 
                new DateFormatContentProposal("\"EEE\" : Day in week (Text)", "EEE", "Day in week (Text)\nExample :  Tue"), 
                new DateFormatContentProposal("\"EEEE\" : Day in week (Text)", "EEEE", "Day in week (Text)\nExample : Tuesday"), 
                new DateFormatContentProposal("\"HH\" : Hour in day (00-23) ", "HH", "Hour in day \nExamples : 00 .. 23"), 
                new DateFormatContentProposal("\"kk\" : Hour in day (01-24) ", "kk", "Hour in day \nExamples : 01 .. 24"), 
                new DateFormatContentProposal("\"KK\" : Hour in AM/PM (00-11) ", "KK", "Hour in AM/PM \nExamples : 00 .. 11"), 
                new DateFormatContentProposal("\"hh\" : Hour in AM/PM (01-12) ", "hh", "Hour in day \nExamples : 01 .. 12"), 
                new DateFormatContentProposal("\"mm\" : Minute in hour (00-59) ", "mm", "Minute in hour \nExamples : 00 .. 59"), 
                new DateFormatContentProposal("\"ss\" : Second in minute (00-59) ", "ss", "Second in minute \nExamples : 00 .. 59"), 
                new DateFormatContentProposal("\"SSS\" : Millisecond (000-999) ", "SSS", "Millisecond \nExamples : 000 .. 999"), 
                new DateFormatContentProposal("\"aa\" : AM/PM marker (Text)", "aa", "AM/PM marker (Text)\nExamples : AM PM"), 
                new DateFormatContentProposal("\"GG\" : Era designator (Text)", "GG", "Year \nExamples : \n  AD\n  BC"), 
                new DateFormatContentProposal("\"zzz\" : Time zone", "zzz", "General time zone\nExamples : \n  CET \n  PST \n ..."), 
                new DateFormatContentProposal("\"zzzz\" : Time zone", "zzzz", "General time zone\nExamples : \n  Pacific Standard Time \n  ..."), 
                new DateFormatContentProposal("\"Z\" : Time zone", "Z", "RFC 822 time zone\nExamples : \n  -0800 \n  ..."), 
                new DateFormatContentProposal(" ' : Start/end free text ", "'", "Start/end free text\n Examples : MM-dd-yyyy ; 23:59:59"), 
                new DateFormatContentProposal(" '' : Display a single quote ", "'", "Two single quotes display only one single quote"), 
                
        };

        return cp;
    }

    /**
     * 
     * DOC amaumont JavaSimpleDateFormatProposalProvider class global comment. Detailled comment
     * <br/>
     *
     * $Id: talend-code-templates.xml 1 2006-09-29 17:06:40Z nrousseau $
     *
     */
    class DateFormatContentProposal implements IContentProposal {

        String content;

        String description;

        String label;

        /**
         * DOC amaumont DateFormatProposal constructor comment.
         * 
         * @param content
         * @param description
         * @param label
         */
        public DateFormatContentProposal(String label, String content, String description) {
            super();
            this.content = content;
            this.description = description;
            this.label = label;
        }

        public String getContent() {
            return content;
        }

        public int getCursorPosition() {
            return content.length();
        }

        public String getDescription() {
            return description;
        }

        public String getLabel() {
            return label;
        }

    }

}
