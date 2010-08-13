package org.talend.metalanguage.job.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalJobLexer extends Lexer {
    public static final int RULE_ID=5;
    public static final int RULE_ANY_OTHER=10;
    public static final int T29=29;
    public static final int T28=28;
    public static final int T27=27;
    public static final int T26=26;
    public static final int T25=25;
    public static final int EOF=-1;
    public static final int T24=24;
    public static final int T23=23;
    public static final int T22=22;
    public static final int T21=21;
    public static final int T20=20;
    public static final int RULE_INT=6;
    public static final int T38=38;
    public static final int T37=37;
    public static final int T39=39;
    public static final int T34=34;
    public static final int T33=33;
    public static final int T36=36;
    public static final int T35=35;
    public static final int T30=30;
    public static final int T32=32;
    public static final int T31=31;
    public static final int T49=49;
    public static final int T48=48;
    public static final int T43=43;
    public static final int Tokens=56;
    public static final int RULE_SL_COMMENT=8;
    public static final int T42=42;
    public static final int T41=41;
    public static final int T40=40;
    public static final int T47=47;
    public static final int T46=46;
    public static final int T45=45;
    public static final int RULE_ML_COMMENT=7;
    public static final int T44=44;
    public static final int RULE_STRING=4;
    public static final int T50=50;
    public static final int T11=11;
    public static final int T12=12;
    public static final int T13=13;
    public static final int T14=14;
    public static final int T52=52;
    public static final int T15=15;
    public static final int RULE_WS=9;
    public static final int T51=51;
    public static final int T16=16;
    public static final int T54=54;
    public static final int T17=17;
    public static final int T53=53;
    public static final int T18=18;
    public static final int T19=19;
    public static final int T55=55;
    public InternalJobLexer() {;} 
    public InternalJobLexer(CharStream input) {
        super(input);
    }
    public String getGrammarFileName() { return "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g"; }

    // $ANTLR start T11
    public final void mT11() throws RecognitionException {
        try {
            int _type = T11;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:10:5: ( 'addParameters' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:10:7: 'addParameters'
            {
            match("addParameters"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T11

    // $ANTLR start T12
    public final void mT12() throws RecognitionException {
        try {
            int _type = T12;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:11:5: ( '{' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:11:7: '{'
            {
            match('{'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T12

    // $ANTLR start T13
    public final void mT13() throws RecognitionException {
        try {
            int _type = T13;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:12:5: ( '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:12:7: '}'
            {
            match('}'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T13

    // $ANTLR start T14
    public final void mT14() throws RecognitionException {
        try {
            int _type = T14;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:13:5: ( ',' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:13:7: ','
            {
            match(','); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T14

    // $ANTLR start T15
    public final void mT15() throws RecognitionException {
        try {
            int _type = T15;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:14:5: ( 'addComponent' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:14:7: 'addComponent'
            {
            match("addComponent"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T15

    // $ANTLR start T16
    public final void mT16() throws RecognitionException {
        try {
            int _type = T16;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:15:5: ( 'setComponentDefinition' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:15:7: 'setComponentDefinition'
            {
            match("setComponentDefinition"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T16

    // $ANTLR start T17
    public final void mT17() throws RecognitionException {
        try {
            int _type = T17;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:16:5: ( 'TYPE:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:16:7: 'TYPE:'
            {
            match("TYPE:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T17

    // $ANTLR start T18
    public final void mT18() throws RecognitionException {
        try {
            int _type = T18;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:17:5: ( 'NAME:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:17:7: 'NAME:'
            {
            match("NAME:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T18

    // $ANTLR start T19
    public final void mT19() throws RecognitionException {
        try {
            int _type = T19;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:18:5: ( 'POSITION:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:18:7: 'POSITION:'
            {
            match("POSITION:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T19

    // $ANTLR start T20
    public final void mT20() throws RecognitionException {
        try {
            int _type = T20;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:19:5: ( 'setSettings' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:19:7: 'setSettings'
            {
            match("setSettings"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T20

    // $ANTLR start T21
    public final void mT21() throws RecognitionException {
        try {
            int _type = T21;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:20:5: ( 'ContextType' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:20:7: 'ContextType'
            {
            match("ContextType"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T21

    // $ANTLR start T22
    public final void mT22() throws RecognitionException {
        try {
            int _type = T22;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:21:5: ( 'CONFIRMATIONNEED:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:21:7: 'CONFIRMATIONNEED:'
            {
            match("CONFIRMATIONNEED:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T22

    // $ANTLR start T23
    public final void mT23() throws RecognitionException {
        try {
            int _type = T23;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:22:5: ( 'addContextParameter' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:22:7: 'addContextParameter'
            {
            match("addContextParameter"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T23

    // $ANTLR start T24
    public final void mT24() throws RecognitionException {
        try {
            int _type = T24;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:23:5: ( 'VALUE:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:23:7: 'VALUE:'
            {
            match("VALUE:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T24

    // $ANTLR start T25
    public final void mT25() throws RecognitionException {
        try {
            int _type = T25;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:24:5: ( 'COMMENT:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:24:7: 'COMMENT:'
            {
            match("COMMENT:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T25

    // $ANTLR start T26
    public final void mT26() throws RecognitionException {
        try {
            int _type = T26;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:25:5: ( 'PROMPT:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:25:7: 'PROMPT:'
            {
            match("PROMPT:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T26

    // $ANTLR start T27
    public final void mT27() throws RecognitionException {
        try {
            int _type = T27;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:26:5: ( 'PROMPTNEEDED:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:26:7: 'PROMPTNEEDED:'
            {
            match("PROMPTNEEDED:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T27

    // $ANTLR start T28
    public final void mT28() throws RecognitionException {
        try {
            int _type = T28;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:27:5: ( 'REPOSITORYCONTEXTID:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:27:7: 'REPOSITORYCONTEXTID:'
            {
            match("REPOSITORYCONTEXTID:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T28

    // $ANTLR start T29
    public final void mT29() throws RecognitionException {
        try {
            int _type = T29;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:28:5: ( 'addConnection' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:28:7: 'addConnection'
            {
            match("addConnection"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T29

    // $ANTLR start T30
    public final void mT30() throws RecognitionException {
        try {
            int _type = T30;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:29:5: ( 'LINESTYLE:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:29:7: 'LINESTYLE:'
            {
            match("LINESTYLE:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T30

    // $ANTLR start T31
    public final void mT31() throws RecognitionException {
        try {
            int _type = T31;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:30:5: ( 'MERGEORDER:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:30:7: 'MERGEORDER:'
            {
            match("MERGEORDER:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T31

    // $ANTLR start T32
    public final void mT32() throws RecognitionException {
        try {
            int _type = T32;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:31:5: ( 'METANAME:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:31:7: 'METANAME:'
            {
            match("METANAME:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T32

    // $ANTLR start T33
    public final void mT33() throws RecognitionException {
        try {
            int _type = T33;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:32:5: ( 'OUTPUTID:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:32:7: 'OUTPUTID:'
            {
            match("OUTPUTID:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T33

    // $ANTLR start T34
    public final void mT34() throws RecognitionException {
        try {
            int _type = T34;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:33:5: ( 'SOURCE:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:33:7: 'SOURCE:'
            {
            match("SOURCE:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T34

    // $ANTLR start T35
    public final void mT35() throws RecognitionException {
        try {
            int _type = T35;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:34:5: ( 'TARGET:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:34:7: 'TARGET:'
            {
            match("TARGET:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T35

    // $ANTLR start T36
    public final void mT36() throws RecognitionException {
        try {
            int _type = T36;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:35:5: ( 'addNote' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:35:7: 'addNote'
            {
            match("addNote"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T36

    // $ANTLR start T37
    public final void mT37() throws RecognitionException {
        try {
            int _type = T37;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:36:5: ( 'opaque' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:36:7: 'opaque'
            {
            match("opaque"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T37

    // $ANTLR start T38
    public final void mT38() throws RecognitionException {
        try {
            int _type = T38;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:37:5: ( 'POSITION' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:37:7: 'POSITION'
            {
            match("POSITION"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T38

    // $ANTLR start T39
    public final void mT39() throws RecognitionException {
        try {
            int _type = T39;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:38:5: ( '\"' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:38:7: '\"'
            {
            match('\"'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T39

    // $ANTLR start T40
    public final void mT40() throws RecognitionException {
        try {
            int _type = T40;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:39:5: ( 'text' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:39:7: 'text'
            {
            match("text"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T40

    // $ANTLR start T41
    public final void mT41() throws RecognitionException {
        try {
            int _type = T41;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:40:5: ( ':' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:40:7: ':'
            {
            match(':'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T41

    // $ANTLR start T42
    public final void mT42() throws RecognitionException {
        try {
            int _type = T42;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:41:5: ( 'addSchema' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:41:7: 'addSchema'
            {
            match("addSchema"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T42

    // $ANTLR start T43
    public final void mT43() throws RecognitionException {
        try {
            int _type = T43;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:42:5: ( 'LABEL' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:42:7: 'LABEL'
            {
            match("LABEL"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T43

    // $ANTLR start T44
    public final void mT44() throws RecognitionException {
        try {
            int _type = T44;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:43:5: ( 'addColumn' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:43:7: 'addColumn'
            {
            match("addColumn"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T44

    // $ANTLR start T45
    public final void mT45() throws RecognitionException {
        try {
            int _type = T45;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:44:5: ( 'KEY:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:44:7: 'KEY:'
            {
            match("KEY:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T45

    // $ANTLR start T46
    public final void mT46() throws RecognitionException {
        try {
            int _type = T46;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:45:5: ( 'NULLABLE:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:45:7: 'NULLABLE:'
            {
            match("NULLABLE:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T46

    // $ANTLR start T47
    public final void mT47() throws RecognitionException {
        try {
            int _type = T47;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:46:5: ( 'DEFAULTVALUE:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:46:7: 'DEFAULTVALUE:'
            {
            match("DEFAULTVALUE:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T47

    // $ANTLR start T48
    public final void mT48() throws RecognitionException {
        try {
            int _type = T48;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:47:5: ( 'LENGTH:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:47:7: 'LENGTH:'
            {
            match("LENGTH:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T48

    // $ANTLR start T49
    public final void mT49() throws RecognitionException {
        try {
            int _type = T49;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:48:5: ( 'originalDbColumnName' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:48:7: 'originalDbColumnName'
            {
            match("originalDbColumnName"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T49

    // $ANTLR start T50
    public final void mT50() throws RecognitionException {
        try {
            int _type = T50;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:49:5: ( 'PATTREN:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:49:7: 'PATTREN:'
            {
            match("PATTREN:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T50

    // $ANTLR start T51
    public final void mT51() throws RecognitionException {
        try {
            int _type = T51;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:50:5: ( 'PRECISION:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:50:7: 'PRECISION:'
            {
            match("PRECISION:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T51

    // $ANTLR start T52
    public final void mT52() throws RecognitionException {
        try {
            int _type = T52;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:51:5: ( 'SOURCETYPE:' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:51:7: 'SOURCETYPE:'
            {
            match("SOURCETYPE:"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T52

    // $ANTLR start T53
    public final void mT53() throws RecognitionException {
        try {
            int _type = T53;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:52:5: ( '-' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:52:7: '-'
            {
            match('-'); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T53

    // $ANTLR start T54
    public final void mT54() throws RecognitionException {
        try {
            int _type = T54;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:53:5: ( 'true' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:53:7: 'true'
            {
            match("true"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T54

    // $ANTLR start T55
    public final void mT55() throws RecognitionException {
        try {
            int _type = T55;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:54:5: ( 'false' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:54:7: 'false'
            {
            match("false"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end T55

    // $ANTLR start RULE_ID
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2658:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2658:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2658:11: ( '^' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='^') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2658:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse =
                    new MismatchedSetException(null,input);
                recover(mse);    throw mse;
            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2658:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')||(LA2_0>='A' && LA2_0<='Z')||LA2_0=='_'||(LA2_0>='a' && LA2_0<='z')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ID

    // $ANTLR start RULE_INT
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2660:10: ( ( '0' .. '9' )+ )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2660:12: ( '0' .. '9' )+
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2660:12: ( '0' .. '9' )+
            int cnt3=0;
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( ((LA3_0>='0' && LA3_0<='9')) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2660:13: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt3 >= 1 ) break loop3;
                        EarlyExitException eee =
                            new EarlyExitException(3, input);
                        throw eee;
                }
                cnt3++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_INT

    // $ANTLR start RULE_STRING
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2662:13: ( ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2662:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2662:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt6=2;
            int LA6_0 = input.LA(1);

            if ( (LA6_0=='\"') ) {
                alt6=1;
            }
            else if ( (LA6_0=='\'') ) {
                alt6=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("2662:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )", 6, 0, input);

                throw nvae;
            }
            switch (alt6) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2662:16: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2662:20: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop4:
                    do {
                        int alt4=3;
                        int LA4_0 = input.LA(1);

                        if ( (LA4_0=='\\') ) {
                            alt4=1;
                        }
                        else if ( ((LA4_0>='\u0000' && LA4_0<='!')||(LA4_0>='#' && LA4_0<='[')||(LA4_0>=']' && LA4_0<='\uFFFE')) ) {
                            alt4=2;
                        }


                        switch (alt4) {
                    	case 1 :
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2662:21: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2662:62: ~ ( ( '\\\\' | '\"' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop4;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2662:82: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2662:87: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop5:
                    do {
                        int alt5=3;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0=='\\') ) {
                            alt5=1;
                        }
                        else if ( ((LA5_0>='\u0000' && LA5_0<='&')||(LA5_0>='(' && LA5_0<='[')||(LA5_0>=']' && LA5_0<='\uFFFE')) ) {
                            alt5=2;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2662:88: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||input.LA(1)=='t' ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2662:129: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFE') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse =
                    	            new MismatchedSetException(null,input);
                    	        recover(mse);    throw mse;
                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_STRING

    // $ANTLR start RULE_ML_COMMENT
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2664:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2664:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2664:24: ( options {greedy=false; } : . )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0=='*') ) {
                    int LA7_1 = input.LA(2);

                    if ( (LA7_1=='/') ) {
                        alt7=2;
                    }
                    else if ( ((LA7_1>='\u0000' && LA7_1<='.')||(LA7_1>='0' && LA7_1<='\uFFFE')) ) {
                        alt7=1;
                    }


                }
                else if ( ((LA7_0>='\u0000' && LA7_0<=')')||(LA7_0>='+' && LA7_0<='\uFFFE')) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2664:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop7;
                }
            } while (true);

            match("*/"); 


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ML_COMMENT

    // $ANTLR start RULE_SL_COMMENT
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2666:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2666:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2666:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( ((LA8_0>='\u0000' && LA8_0<='\t')||(LA8_0>='\u000B' && LA8_0<='\f')||(LA8_0>='\u000E' && LA8_0<='\uFFFE')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2666:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFE') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2666:40: ( ( '\\r' )? '\\n' )?
            int alt10=2;
            int LA10_0 = input.LA(1);

            if ( (LA10_0=='\n'||LA10_0=='\r') ) {
                alt10=1;
            }
            switch (alt10) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2666:41: ( '\\r' )? '\\n'
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2666:41: ( '\\r' )?
                    int alt9=2;
                    int LA9_0 = input.LA(1);

                    if ( (LA9_0=='\r') ) {
                        alt9=1;
                    }
                    switch (alt9) {
                        case 1 :
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2666:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_SL_COMMENT

    // $ANTLR start RULE_WS
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2668:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2668:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2668:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt11=0;
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( ((LA11_0>='\t' && LA11_0<='\n')||LA11_0=='\r'||LA11_0==' ') ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse =
            	            new MismatchedSetException(null,input);
            	        recover(mse);    throw mse;
            	    }


            	    }
            	    break;

            	default :
            	    if ( cnt11 >= 1 ) break loop11;
                        EarlyExitException eee =
                            new EarlyExitException(11, input);
                        throw eee;
                }
                cnt11++;
            } while (true);


            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_WS

    // $ANTLR start RULE_ANY_OTHER
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2670:16: ( . )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2670:18: .
            {
            matchAny(); 

            }

            this.type = _type;
        }
        finally {
        }
    }
    // $ANTLR end RULE_ANY_OTHER

    public void mTokens() throws RecognitionException {
        // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:8: ( T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | T40 | T41 | T42 | T43 | T44 | T45 | T46 | T47 | T48 | T49 | T50 | T51 | T52 | T53 | T54 | T55 | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt12=52;
        int LA12_0 = input.LA(1);

        if ( (LA12_0=='a') ) {
            alt12 = mTokensHelper001();
        }
        else if ( (LA12_0=='{') ) {
            alt12 = mTokensHelper002();
        }
        else if ( (LA12_0=='}') ) {
            alt12 = mTokensHelper003();
        }
        else if ( (LA12_0==',') ) {
            alt12 = mTokensHelper004();
        }
        else if ( (LA12_0=='s') ) {
            alt12 = mTokensHelper005();
        }
        else if ( (LA12_0=='T') ) {
            alt12 = mTokensHelper006();
        }
        else if ( (LA12_0=='N') ) {
            alt12 = mTokensHelper007();
        }
        else if ( (LA12_0=='P') ) {
            alt12 = mTokensHelper008();
        }
        else if ( (LA12_0=='C') ) {
            alt12 = mTokensHelper009();
        }
        else if ( (LA12_0=='V') ) {
            alt12 = mTokensHelper010();
        }
        else if ( (LA12_0=='R') ) {
            alt12 = mTokensHelper011();
        }
        else if ( (LA12_0=='L') ) {
            alt12 = mTokensHelper012();
        }
        else if ( (LA12_0=='M') ) {
            alt12 = mTokensHelper013();
        }
        else if ( (LA12_0=='O') ) {
            alt12 = mTokensHelper014();
        }
        else if ( (LA12_0=='S') ) {
            alt12 = mTokensHelper015();
        }
        else if ( (LA12_0=='o') ) {
            alt12 = mTokensHelper016();
        }
        else if ( (LA12_0=='\"') ) {
            alt12 = mTokensHelper017();
        }
        else if ( (LA12_0=='t') ) {
            alt12 = mTokensHelper018();
        }
        else if ( (LA12_0==':') ) {
            alt12 = mTokensHelper019();
        }
        else if ( (LA12_0=='K') ) {
            alt12 = mTokensHelper020();
        }
        else if ( (LA12_0=='D') ) {
            alt12 = mTokensHelper021();
        }
        else if ( (LA12_0=='-') ) {
            alt12 = mTokensHelper022();
        }
        else if ( (LA12_0=='f') ) {
            alt12 = mTokensHelper023();
        }
        else if ( (LA12_0=='^') ) {
            alt12 = mTokensHelper024();
        }
        else if ( ((LA12_0>='A' && LA12_0<='B')||(LA12_0>='E' && LA12_0<='J')||LA12_0=='Q'||LA12_0=='U'||(LA12_0>='W' && LA12_0<='Z')||LA12_0=='_'||(LA12_0>='b' && LA12_0<='e')||(LA12_0>='g' && LA12_0<='n')||(LA12_0>='p' && LA12_0<='r')||(LA12_0>='u' && LA12_0<='z')) ) {
            alt12 = mTokensHelper025();
        }
        else if ( ((LA12_0>='0' && LA12_0<='9')) ) {
            alt12 = mTokensHelper026();
        }
        else if ( (LA12_0=='\'') ) {
            alt12 = mTokensHelper027();
        }
        else if ( (LA12_0=='/') ) {
            alt12 = mTokensHelper028();
        }
        else if ( ((LA12_0>='\t' && LA12_0<='\n')||LA12_0=='\r'||LA12_0==' ') ) {
            alt12 = mTokensHelper029();
        }
        else if ( ((LA12_0>='\u0000' && LA12_0<='\b')||(LA12_0>='\u000B' && LA12_0<='\f')||(LA12_0>='\u000E' && LA12_0<='\u001F')||LA12_0=='!'||(LA12_0>='#' && LA12_0<='&')||(LA12_0>='(' && LA12_0<='+')||LA12_0=='.'||(LA12_0>=';' && LA12_0<='@')||(LA12_0>='[' && LA12_0<=']')||LA12_0=='`'||LA12_0=='|'||(LA12_0>='~' && LA12_0<='\uFFFE')) ) {
            alt12 = mTokensHelper030();
        }
        else {
            alt12 = mTokensHelper031();
        }
        switch (alt12) {
            case 1 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:10: T11
                {
                mT11(); 

                }
                break;
            case 2 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:14: T12
                {
                mT12(); 

                }
                break;
            case 3 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:18: T13
                {
                mT13(); 

                }
                break;
            case 4 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:22: T14
                {
                mT14(); 

                }
                break;
            case 5 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:26: T15
                {
                mT15(); 

                }
                break;
            case 6 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:30: T16
                {
                mT16(); 

                }
                break;
            case 7 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:34: T17
                {
                mT17(); 

                }
                break;
            case 8 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:38: T18
                {
                mT18(); 

                }
                break;
            case 9 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:42: T19
                {
                mT19(); 

                }
                break;
            case 10 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:46: T20
                {
                mT20(); 

                }
                break;
            case 11 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:50: T21
                {
                mT21(); 

                }
                break;
            case 12 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:54: T22
                {
                mT22(); 

                }
                break;
            case 13 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:58: T23
                {
                mT23(); 

                }
                break;
            case 14 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:62: T24
                {
                mT24(); 

                }
                break;
            case 15 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:66: T25
                {
                mT25(); 

                }
                break;
            case 16 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:70: T26
                {
                mT26(); 

                }
                break;
            case 17 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:74: T27
                {
                mT27(); 

                }
                break;
            case 18 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:78: T28
                {
                mT28(); 

                }
                break;
            case 19 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:82: T29
                {
                mT29(); 

                }
                break;
            case 20 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:86: T30
                {
                mT30(); 

                }
                break;
            case 21 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:90: T31
                {
                mT31(); 

                }
                break;
            case 22 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:94: T32
                {
                mT32(); 

                }
                break;
            case 23 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:98: T33
                {
                mT33(); 

                }
                break;
            case 24 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:102: T34
                {
                mT34(); 

                }
                break;
            case 25 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:106: T35
                {
                mT35(); 

                }
                break;
            case 26 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:110: T36
                {
                mT36(); 

                }
                break;
            case 27 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:114: T37
                {
                mT37(); 

                }
                break;
            case 28 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:118: T38
                {
                mT38(); 

                }
                break;
            case 29 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:122: T39
                {
                mT39(); 

                }
                break;
            case 30 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:126: T40
                {
                mT40(); 

                }
                break;
            case 31 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:130: T41
                {
                mT41(); 

                }
                break;
            case 32 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:134: T42
                {
                mT42(); 

                }
                break;
            case 33 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:138: T43
                {
                mT43(); 

                }
                break;
            case 34 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:142: T44
                {
                mT44(); 

                }
                break;
            case 35 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:146: T45
                {
                mT45(); 

                }
                break;
            case 36 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:150: T46
                {
                mT46(); 

                }
                break;
            case 37 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:154: T47
                {
                mT47(); 

                }
                break;
            case 38 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:158: T48
                {
                mT48(); 

                }
                break;
            case 39 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:162: T49
                {
                mT49(); 

                }
                break;
            case 40 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:166: T50
                {
                mT50(); 

                }
                break;
            case 41 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:170: T51
                {
                mT51(); 

                }
                break;
            case 42 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:174: T52
                {
                mT52(); 

                }
                break;
            case 43 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:178: T53
                {
                mT53(); 

                }
                break;
            case 44 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:182: T54
                {
                mT54(); 

                }
                break;
            case 45 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:186: T55
                {
                mT55(); 

                }
                break;
            case 46 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:190: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 47 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:198: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 48 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:207: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 49 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:219: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 50 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:235: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 51 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:251: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 52 :
                // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1:259: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }
    private int mTokensHelper001() throws RecognitionException {
        int LA12_1 = input.LA(2);

        if ( (LA12_1=='d') ) {
            int LA12_31 = input.LA(3);

            if ( (LA12_31=='d') ) {
                switch ( input.LA(4) ) {
                case 'C':
                    {
                    int LA12_98 = input.LA(5);

                    if ( (LA12_98=='o') ) {
                        switch ( input.LA(6) ) {
                        case 'n':
                            {
                            switch ( input.LA(7) ) {
                            case 't':
                                {
                                int LA12_193 = input.LA(8);

                                if ( (LA12_193=='e') ) {
                                    int LA12_223 = input.LA(9);

                                    if ( (LA12_223=='x') ) {
                                        int LA12_248 = input.LA(10);

                                        if ( (LA12_248=='t') ) {
                                            int LA12_271 = input.LA(11);

                                            if ( (LA12_271=='P') ) {
                                                int LA12_289 = input.LA(12);

                                                if ( (LA12_289=='a') ) {
                                                    int LA12_303 = input.LA(13);

                                                    if ( (LA12_303=='r') ) {
                                                        int LA12_315 = input.LA(14);

                                                        if ( (LA12_315=='a') ) {
                                                            int LA12_325 = input.LA(15);

                                                            if ( (LA12_325=='m') ) {
                                                                int LA12_332 = input.LA(16);

                                                                if ( (LA12_332=='e') ) {
                                                                    int LA12_337 = input.LA(17);

                                                                    if ( (LA12_337=='t') ) {
                                                                        int LA12_342 = input.LA(18);

                                                                        if ( (LA12_342=='e') ) {
                                                                            int LA12_347 = input.LA(19);

                                                                            if ( (LA12_347=='r') ) {
                                                                                int LA12_351 = input.LA(20);

                                                                                if ( ((LA12_351>='0' && LA12_351<='9')||(LA12_351>='A' && LA12_351<='Z')||LA12_351=='_'||(LA12_351>='a' && LA12_351<='z')) ) {
                                                                                    return 46;
                                                                                }
                                                                                else {
                                                                                    return 13;}
                                                                            }
                                                                            else {
                                                                                return 46;}
                                                                        }
                                                                        else {
                                                                            return 46;}
                                                                    }
                                                                    else {
                                                                        return 46;}
                                                                }
                                                                else {
                                                                    return 46;}
                                                            }
                                                            else {
                                                                return 46;}
                                                        }
                                                        else {
                                                            return 46;}
                                                    }
                                                    else {
                                                        return 46;}
                                                }
                                                else {
                                                    return 46;}
                                            }
                                            else {
                                                return 46;}
                                        }
                                        else {
                                            return 46;}
                                    }
                                    else {
                                        return 46;}
                                }
                                else {
                                    return 46;}
                                }
                            case 'n':
                                {
                                int LA12_194 = input.LA(8);

                                if ( (LA12_194=='e') ) {
                                    int LA12_224 = input.LA(9);

                                    if ( (LA12_224=='c') ) {
                                        int LA12_249 = input.LA(10);

                                        if ( (LA12_249=='t') ) {
                                            int LA12_272 = input.LA(11);

                                            if ( (LA12_272=='i') ) {
                                                int LA12_290 = input.LA(12);

                                                if ( (LA12_290=='o') ) {
                                                    int LA12_304 = input.LA(13);

                                                    if ( (LA12_304=='n') ) {
                                                        int LA12_316 = input.LA(14);

                                                        if ( ((LA12_316>='0' && LA12_316<='9')||(LA12_316>='A' && LA12_316<='Z')||LA12_316=='_'||(LA12_316>='a' && LA12_316<='z')) ) {
                                                            return 46;
                                                        }
                                                        else {
                                                            return 19;}
                                                    }
                                                    else {
                                                        return 46;}
                                                }
                                                else {
                                                    return 46;}
                                            }
                                            else {
                                                return 46;}
                                        }
                                        else {
                                            return 46;}
                                    }
                                    else {
                                        return 46;}
                                }
                                else {
                                    return 46;}
                                }
                            default:
                                return 46;}

                            }
                        case 'm':
                            {
                            int LA12_164 = input.LA(7);

                            if ( (LA12_164=='p') ) {
                                int LA12_195 = input.LA(8);

                                if ( (LA12_195=='o') ) {
                                    int LA12_225 = input.LA(9);

                                    if ( (LA12_225=='n') ) {
                                        int LA12_250 = input.LA(10);

                                        if ( (LA12_250=='e') ) {
                                            int LA12_273 = input.LA(11);

                                            if ( (LA12_273=='n') ) {
                                                int LA12_291 = input.LA(12);

                                                if ( (LA12_291=='t') ) {
                                                    int LA12_305 = input.LA(13);

                                                    if ( ((LA12_305>='0' && LA12_305<='9')||(LA12_305>='A' && LA12_305<='Z')||LA12_305=='_'||(LA12_305>='a' && LA12_305<='z')) ) {
                                                        return 46;
                                                    }
                                                    else {
                                                        return 5;}
                                                }
                                                else {
                                                    return 46;}
                                            }
                                            else {
                                                return 46;}
                                        }
                                        else {
                                            return 46;}
                                    }
                                    else {
                                        return 46;}
                                }
                                else {
                                    return 46;}
                            }
                            else {
                                return 46;}
                            }
                        case 'l':
                            {
                            int LA12_165 = input.LA(7);

                            if ( (LA12_165=='u') ) {
                                int LA12_196 = input.LA(8);

                                if ( (LA12_196=='m') ) {
                                    int LA12_226 = input.LA(9);

                                    if ( (LA12_226=='n') ) {
                                        int LA12_251 = input.LA(10);

                                        if ( ((LA12_251>='0' && LA12_251<='9')||(LA12_251>='A' && LA12_251<='Z')||LA12_251=='_'||(LA12_251>='a' && LA12_251<='z')) ) {
                                            return 46;
                                        }
                                        else {
                                            return 34;}
                                    }
                                    else {
                                        return 46;}
                                }
                                else {
                                    return 46;}
                            }
                            else {
                                return 46;}
                            }
                        default:
                            return 46;}

                    }
                    else {
                        return 46;}
                    }
                case 'P':
                    {
                    int LA12_99 = input.LA(5);

                    if ( (LA12_99=='a') ) {
                        int LA12_132 = input.LA(6);

                        if ( (LA12_132=='r') ) {
                            int LA12_166 = input.LA(7);

                            if ( (LA12_166=='a') ) {
                                int LA12_197 = input.LA(8);

                                if ( (LA12_197=='m') ) {
                                    int LA12_227 = input.LA(9);

                                    if ( (LA12_227=='e') ) {
                                        int LA12_252 = input.LA(10);

                                        if ( (LA12_252=='t') ) {
                                            int LA12_275 = input.LA(11);

                                            if ( (LA12_275=='e') ) {
                                                int LA12_292 = input.LA(12);

                                                if ( (LA12_292=='r') ) {
                                                    int LA12_306 = input.LA(13);

                                                    if ( (LA12_306=='s') ) {
                                                        int LA12_318 = input.LA(14);

                                                        if ( ((LA12_318>='0' && LA12_318<='9')||(LA12_318>='A' && LA12_318<='Z')||LA12_318=='_'||(LA12_318>='a' && LA12_318<='z')) ) {
                                                            return 46;
                                                        }
                                                        else {
                                                            return 1;}
                                                    }
                                                    else {
                                                        return 46;}
                                                }
                                                else {
                                                    return 46;}
                                            }
                                            else {
                                                return 46;}
                                        }
                                        else {
                                            return 46;}
                                    }
                                    else {
                                        return 46;}
                                }
                                else {
                                    return 46;}
                            }
                            else {
                                return 46;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                    }
                case 'S':
                    {
                    int LA12_100 = input.LA(5);

                    if ( (LA12_100=='c') ) {
                        int LA12_133 = input.LA(6);

                        if ( (LA12_133=='h') ) {
                            int LA12_167 = input.LA(7);

                            if ( (LA12_167=='e') ) {
                                int LA12_198 = input.LA(8);

                                if ( (LA12_198=='m') ) {
                                    int LA12_228 = input.LA(9);

                                    if ( (LA12_228=='a') ) {
                                        int LA12_253 = input.LA(10);

                                        if ( ((LA12_253>='0' && LA12_253<='9')||(LA12_253>='A' && LA12_253<='Z')||LA12_253=='_'||(LA12_253>='a' && LA12_253<='z')) ) {
                                            return 46;
                                        }
                                        else {
                                            return 32;}
                                    }
                                    else {
                                        return 46;}
                                }
                                else {
                                    return 46;}
                            }
                            else {
                                return 46;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                    }
                case 'N':
                    {
                    int LA12_101 = input.LA(5);

                    if ( (LA12_101=='o') ) {
                        int LA12_134 = input.LA(6);

                        if ( (LA12_134=='t') ) {
                            int LA12_168 = input.LA(7);

                            if ( (LA12_168=='e') ) {
                                int LA12_199 = input.LA(8);

                                if ( ((LA12_199>='0' && LA12_199<='9')||(LA12_199>='A' && LA12_199<='Z')||LA12_199=='_'||(LA12_199>='a' && LA12_199<='z')) ) {
                                    return 46;
                                }
                                else {
                                    return 26;}
                            }
                            else {
                                return 46;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                    }
                default:
                    return 46;}

            }
            else {
                return 46;}
        }
        else {
            return 46;}
    }

    private int mTokensHelper002() throws RecognitionException {
        return 2;
    }

    private int mTokensHelper003() throws RecognitionException {
        return 3;
    }

    private int mTokensHelper004() throws RecognitionException {
        return 4;
    }

    private int mTokensHelper005() throws RecognitionException {
        int LA12_5 = input.LA(2);

        if ( (LA12_5=='e') ) {
            int LA12_36 = input.LA(3);

            if ( (LA12_36=='t') ) {
                switch ( input.LA(4) ) {
                case 'S':
                    {
                    int LA12_102 = input.LA(5);

                    if ( (LA12_102=='e') ) {
                        int LA12_135 = input.LA(6);

                        if ( (LA12_135=='t') ) {
                            int LA12_169 = input.LA(7);

                            if ( (LA12_169=='t') ) {
                                int LA12_200 = input.LA(8);

                                if ( (LA12_200=='i') ) {
                                    int LA12_230 = input.LA(9);

                                    if ( (LA12_230=='n') ) {
                                        int LA12_254 = input.LA(10);

                                        if ( (LA12_254=='g') ) {
                                            int LA12_277 = input.LA(11);

                                            if ( (LA12_277=='s') ) {
                                                int LA12_293 = input.LA(12);

                                                if ( ((LA12_293>='0' && LA12_293<='9')||(LA12_293>='A' && LA12_293<='Z')||LA12_293=='_'||(LA12_293>='a' && LA12_293<='z')) ) {
                                                    return 46;
                                                }
                                                else {
                                                    return 10;}
                                            }
                                            else {
                                                return 46;}
                                        }
                                        else {
                                            return 46;}
                                    }
                                    else {
                                        return 46;}
                                }
                                else {
                                    return 46;}
                            }
                            else {
                                return 46;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                    }
                case 'C':
                    {
                    int LA12_103 = input.LA(5);

                    if ( (LA12_103=='o') ) {
                        int LA12_136 = input.LA(6);

                        if ( (LA12_136=='m') ) {
                            int LA12_170 = input.LA(7);

                            if ( (LA12_170=='p') ) {
                                int LA12_201 = input.LA(8);

                                if ( (LA12_201=='o') ) {
                                    int LA12_231 = input.LA(9);

                                    if ( (LA12_231=='n') ) {
                                        int LA12_255 = input.LA(10);

                                        if ( (LA12_255=='e') ) {
                                            int LA12_278 = input.LA(11);

                                            if ( (LA12_278=='n') ) {
                                                int LA12_294 = input.LA(12);

                                                if ( (LA12_294=='t') ) {
                                                    int LA12_308 = input.LA(13);

                                                    if ( (LA12_308=='D') ) {
                                                        int LA12_319 = input.LA(14);

                                                        if ( (LA12_319=='e') ) {
                                                            int LA12_328 = input.LA(15);

                                                            if ( (LA12_328=='f') ) {
                                                                int LA12_333 = input.LA(16);

                                                                if ( (LA12_333=='i') ) {
                                                                    int LA12_338 = input.LA(17);

                                                                    if ( (LA12_338=='n') ) {
                                                                        int LA12_343 = input.LA(18);

                                                                        if ( (LA12_343=='i') ) {
                                                                            int LA12_348 = input.LA(19);

                                                                            if ( (LA12_348=='t') ) {
                                                                                int LA12_352 = input.LA(20);

                                                                                if ( (LA12_352=='i') ) {
                                                                                    int LA12_356 = input.LA(21);

                                                                                    if ( (LA12_356=='o') ) {
                                                                                        int LA12_359 = input.LA(22);

                                                                                        if ( (LA12_359=='n') ) {
                                                                                            int LA12_361 = input.LA(23);

                                                                                            if ( ((LA12_361>='0' && LA12_361<='9')||(LA12_361>='A' && LA12_361<='Z')||LA12_361=='_'||(LA12_361>='a' && LA12_361<='z')) ) {
                                                                                                return 46;
                                                                                            }
                                                                                            else {
                                                                                                return 6;}
                                                                                        }
                                                                                        else {
                                                                                            return 46;}
                                                                                    }
                                                                                    else {
                                                                                        return 46;}
                                                                                }
                                                                                else {
                                                                                    return 46;}
                                                                            }
                                                                            else {
                                                                                return 46;}
                                                                        }
                                                                        else {
                                                                            return 46;}
                                                                    }
                                                                    else {
                                                                        return 46;}
                                                                }
                                                                else {
                                                                    return 46;}
                                                            }
                                                            else {
                                                                return 46;}
                                                        }
                                                        else {
                                                            return 46;}
                                                    }
                                                    else {
                                                        return 46;}
                                                }
                                                else {
                                                    return 46;}
                                            }
                                            else {
                                                return 46;}
                                        }
                                        else {
                                            return 46;}
                                    }
                                    else {
                                        return 46;}
                                }
                                else {
                                    return 46;}
                            }
                            else {
                                return 46;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                    }
                default:
                    return 46;}

            }
            else {
                return 46;}
        }
        else {
            return 46;}
    }

    private int mTokensHelper006() throws RecognitionException {
        switch ( input.LA(2) ) {
        case 'A':
            {
            int LA12_37 = input.LA(3);

            if ( (LA12_37=='R') ) {
                int LA12_71 = input.LA(4);

                if ( (LA12_71=='G') ) {
                    int LA12_104 = input.LA(5);

                    if ( (LA12_104=='E') ) {
                        int LA12_137 = input.LA(6);

                        if ( (LA12_137=='T') ) {
                            int LA12_171 = input.LA(7);

                            if ( (LA12_171==':') ) {
                                return 25;
                            }
                            else {
                                return 46;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
            }
            else {
                return 46;}
            }
        case 'Y':
            {
            int LA12_38 = input.LA(3);

            if ( (LA12_38=='P') ) {
                int LA12_72 = input.LA(4);

                if ( (LA12_72=='E') ) {
                    int LA12_105 = input.LA(5);

                    if ( (LA12_105==':') ) {
                        return 7;
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
            }
            else {
                return 46;}
            }
        default:
            return 46;}

    }

    private int mTokensHelper007() throws RecognitionException {
        switch ( input.LA(2) ) {
        case 'U':
            {
            int LA12_39 = input.LA(3);

            if ( (LA12_39=='L') ) {
                int LA12_73 = input.LA(4);

                if ( (LA12_73=='L') ) {
                    int LA12_106 = input.LA(5);

                    if ( (LA12_106=='A') ) {
                        int LA12_139 = input.LA(6);

                        if ( (LA12_139=='B') ) {
                            int LA12_172 = input.LA(7);

                            if ( (LA12_172=='L') ) {
                                int LA12_203 = input.LA(8);

                                if ( (LA12_203=='E') ) {
                                    int LA12_232 = input.LA(9);

                                    if ( (LA12_232==':') ) {
                                        return 36;
                                    }
                                    else {
                                        return 46;}
                                }
                                else {
                                    return 46;}
                            }
                            else {
                                return 46;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
            }
            else {
                return 46;}
            }
        case 'A':
            {
            int LA12_40 = input.LA(3);

            if ( (LA12_40=='M') ) {
                int LA12_74 = input.LA(4);

                if ( (LA12_74=='E') ) {
                    int LA12_107 = input.LA(5);

                    if ( (LA12_107==':') ) {
                        return 8;
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
            }
            else {
                return 46;}
            }
        default:
            return 46;}

    }

    private int mTokensHelper008() throws RecognitionException {
        switch ( input.LA(2) ) {
        case 'R':
            {
            switch ( input.LA(3) ) {
            case 'O':
                {
                int LA12_75 = input.LA(4);

                if ( (LA12_75=='M') ) {
                    int LA12_108 = input.LA(5);

                    if ( (LA12_108=='P') ) {
                        int LA12_141 = input.LA(6);

                        if ( (LA12_141=='T') ) {
                            switch ( input.LA(7) ) {
                            case 'N':
                                {
                                int LA12_204 = input.LA(8);

                                if ( (LA12_204=='E') ) {
                                    int LA12_233 = input.LA(9);

                                    if ( (LA12_233=='E') ) {
                                        int LA12_257 = input.LA(10);

                                        if ( (LA12_257=='D') ) {
                                            int LA12_279 = input.LA(11);

                                            if ( (LA12_279=='E') ) {
                                                int LA12_295 = input.LA(12);

                                                if ( (LA12_295=='D') ) {
                                                    int LA12_309 = input.LA(13);

                                                    if ( (LA12_309==':') ) {
                                                        return 17;
                                                    }
                                                    else {
                                                        return 46;}
                                                }
                                                else {
                                                    return 46;}
                                            }
                                            else {
                                                return 46;}
                                        }
                                        else {
                                            return 46;}
                                    }
                                    else {
                                        return 46;}
                                }
                                else {
                                    return 46;}
                                }
                            case ':':
                                {
                                return 16;
                                }
                            default:
                                return 46;}

                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
                }
            case 'E':
                {
                int LA12_76 = input.LA(4);

                if ( (LA12_76=='C') ) {
                    int LA12_109 = input.LA(5);

                    if ( (LA12_109=='I') ) {
                        int LA12_142 = input.LA(6);

                        if ( (LA12_142=='S') ) {
                            int LA12_174 = input.LA(7);

                            if ( (LA12_174=='I') ) {
                                int LA12_206 = input.LA(8);

                                if ( (LA12_206=='O') ) {
                                    int LA12_234 = input.LA(9);

                                    if ( (LA12_234=='N') ) {
                                        int LA12_258 = input.LA(10);

                                        if ( (LA12_258==':') ) {
                                            return 41;
                                        }
                                        else {
                                            return 46;}
                                    }
                                    else {
                                        return 46;}
                                }
                                else {
                                    return 46;}
                            }
                            else {
                                return 46;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
                }
            default:
                return 46;}

            }
        case 'O':
            {
            int LA12_42 = input.LA(3);

            if ( (LA12_42=='S') ) {
                int LA12_77 = input.LA(4);

                if ( (LA12_77=='I') ) {
                    int LA12_110 = input.LA(5);

                    if ( (LA12_110=='T') ) {
                        int LA12_143 = input.LA(6);

                        if ( (LA12_143=='I') ) {
                            int LA12_175 = input.LA(7);

                            if ( (LA12_175=='O') ) {
                                int LA12_207 = input.LA(8);

                                if ( (LA12_207=='N') ) {
                                    switch ( input.LA(9) ) {
                                    case ':':
                                        {
                                        return 9;
                                        }
                                    case '0':
                                    case '1':
                                    case '2':
                                    case '3':
                                    case '4':
                                    case '5':
                                    case '6':
                                    case '7':
                                    case '8':
                                    case '9':
                                    case 'A':
                                    case 'B':
                                    case 'C':
                                    case 'D':
                                    case 'E':
                                    case 'F':
                                    case 'G':
                                    case 'H':
                                    case 'I':
                                    case 'J':
                                    case 'K':
                                    case 'L':
                                    case 'M':
                                    case 'N':
                                    case 'O':
                                    case 'P':
                                    case 'Q':
                                    case 'R':
                                    case 'S':
                                    case 'T':
                                    case 'U':
                                    case 'V':
                                    case 'W':
                                    case 'X':
                                    case 'Y':
                                    case 'Z':
                                    case '_':
                                    case 'a':
                                    case 'b':
                                    case 'c':
                                    case 'd':
                                    case 'e':
                                    case 'f':
                                    case 'g':
                                    case 'h':
                                    case 'i':
                                    case 'j':
                                    case 'k':
                                    case 'l':
                                    case 'm':
                                    case 'n':
                                    case 'o':
                                    case 'p':
                                    case 'q':
                                    case 'r':
                                    case 's':
                                    case 't':
                                    case 'u':
                                    case 'v':
                                    case 'w':
                                    case 'x':
                                    case 'y':
                                    case 'z':
                                        {
                                        return 46;
                                        }
                                    default:
                                        return 28;}

                                }
                                else {
                                    return 46;}
                            }
                            else {
                                return 46;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
            }
            else {
                return 46;}
            }
        case 'A':
            {
            int LA12_43 = input.LA(3);

            if ( (LA12_43=='T') ) {
                int LA12_78 = input.LA(4);

                if ( (LA12_78=='T') ) {
                    int LA12_111 = input.LA(5);

                    if ( (LA12_111=='R') ) {
                        int LA12_144 = input.LA(6);

                        if ( (LA12_144=='E') ) {
                            int LA12_176 = input.LA(7);

                            if ( (LA12_176=='N') ) {
                                int LA12_208 = input.LA(8);

                                if ( (LA12_208==':') ) {
                                    return 40;
                                }
                                else {
                                    return 46;}
                            }
                            else {
                                return 46;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
            }
            else {
                return 46;}
            }
        default:
            return 46;}

    }

    private int mTokensHelper009() throws RecognitionException {
        switch ( input.LA(2) ) {
        case 'O':
            {
            switch ( input.LA(3) ) {
            case 'N':
                {
                int LA12_79 = input.LA(4);

                if ( (LA12_79=='F') ) {
                    int LA12_112 = input.LA(5);

                    if ( (LA12_112=='I') ) {
                        int LA12_145 = input.LA(6);

                        if ( (LA12_145=='R') ) {
                            int LA12_177 = input.LA(7);

                            if ( (LA12_177=='M') ) {
                                int LA12_209 = input.LA(8);

                                if ( (LA12_209=='A') ) {
                                    int LA12_237 = input.LA(9);

                                    if ( (LA12_237=='T') ) {
                                        int LA12_261 = input.LA(10);

                                        if ( (LA12_261=='I') ) {
                                            int LA12_281 = input.LA(11);

                                            if ( (LA12_281=='O') ) {
                                                int LA12_296 = input.LA(12);

                                                if ( (LA12_296=='N') ) {
                                                    int LA12_310 = input.LA(13);

                                                    if ( (LA12_310=='N') ) {
                                                        int LA12_321 = input.LA(14);

                                                        if ( (LA12_321=='E') ) {
                                                            int LA12_329 = input.LA(15);

                                                            if ( (LA12_329=='E') ) {
                                                                int LA12_334 = input.LA(16);

                                                                if ( (LA12_334=='D') ) {
                                                                    int LA12_339 = input.LA(17);

                                                                    if ( (LA12_339==':') ) {
                                                                        return 12;
                                                                    }
                                                                    else {
                                                                        return 46;}
                                                                }
                                                                else {
                                                                    return 46;}
                                                            }
                                                            else {
                                                                return 46;}
                                                        }
                                                        else {
                                                            return 46;}
                                                    }
                                                    else {
                                                        return 46;}
                                                }
                                                else {
                                                    return 46;}
                                            }
                                            else {
                                                return 46;}
                                        }
                                        else {
                                            return 46;}
                                    }
                                    else {
                                        return 46;}
                                }
                                else {
                                    return 46;}
                            }
                            else {
                                return 46;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
                }
            case 'M':
                {
                int LA12_80 = input.LA(4);

                if ( (LA12_80=='M') ) {
                    int LA12_113 = input.LA(5);

                    if ( (LA12_113=='E') ) {
                        int LA12_146 = input.LA(6);

                        if ( (LA12_146=='N') ) {
                            int LA12_178 = input.LA(7);

                            if ( (LA12_178=='T') ) {
                                int LA12_210 = input.LA(8);

                                if ( (LA12_210==':') ) {
                                    return 15;
                                }
                                else {
                                    return 46;}
                            }
                            else {
                                return 46;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
                }
            default:
                return 46;}

            }
        case 'o':
            {
            int LA12_45 = input.LA(3);

            if ( (LA12_45=='n') ) {
                int LA12_81 = input.LA(4);

                if ( (LA12_81=='t') ) {
                    int LA12_114 = input.LA(5);

                    if ( (LA12_114=='e') ) {
                        int LA12_147 = input.LA(6);

                        if ( (LA12_147=='x') ) {
                            int LA12_179 = input.LA(7);

                            if ( (LA12_179=='t') ) {
                                int LA12_211 = input.LA(8);

                                if ( (LA12_211=='T') ) {
                                    int LA12_239 = input.LA(9);

                                    if ( (LA12_239=='y') ) {
                                        int LA12_262 = input.LA(10);

                                        if ( (LA12_262=='p') ) {
                                            int LA12_282 = input.LA(11);

                                            if ( (LA12_282=='e') ) {
                                                int LA12_297 = input.LA(12);

                                                if ( ((LA12_297>='0' && LA12_297<='9')||(LA12_297>='A' && LA12_297<='Z')||LA12_297=='_'||(LA12_297>='a' && LA12_297<='z')) ) {
                                                    return 46;
                                                }
                                                else {
                                                    return 11;}
                                            }
                                            else {
                                                return 46;}
                                        }
                                        else {
                                            return 46;}
                                    }
                                    else {
                                        return 46;}
                                }
                                else {
                                    return 46;}
                            }
                            else {
                                return 46;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
            }
            else {
                return 46;}
            }
        default:
            return 46;}

    }

    private int mTokensHelper010() throws RecognitionException {
        int LA12_10 = input.LA(2);

        if ( (LA12_10=='A') ) {
            int LA12_46 = input.LA(3);

            if ( (LA12_46=='L') ) {
                int LA12_82 = input.LA(4);

                if ( (LA12_82=='U') ) {
                    int LA12_115 = input.LA(5);

                    if ( (LA12_115=='E') ) {
                        int LA12_148 = input.LA(6);

                        if ( (LA12_148==':') ) {
                            return 14;
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
            }
            else {
                return 46;}
        }
        else {
            return 46;}
    }

    private int mTokensHelper011() throws RecognitionException {
        int LA12_11 = input.LA(2);

        if ( (LA12_11=='E') ) {
            int LA12_47 = input.LA(3);

            if ( (LA12_47=='P') ) {
                int LA12_83 = input.LA(4);

                if ( (LA12_83=='O') ) {
                    int LA12_116 = input.LA(5);

                    if ( (LA12_116=='S') ) {
                        int LA12_149 = input.LA(6);

                        if ( (LA12_149=='I') ) {
                            int LA12_181 = input.LA(7);

                            if ( (LA12_181=='T') ) {
                                int LA12_212 = input.LA(8);

                                if ( (LA12_212=='O') ) {
                                    int LA12_240 = input.LA(9);

                                    if ( (LA12_240=='R') ) {
                                        int LA12_263 = input.LA(10);

                                        if ( (LA12_263=='Y') ) {
                                            int LA12_283 = input.LA(11);

                                            if ( (LA12_283=='C') ) {
                                                int LA12_298 = input.LA(12);

                                                if ( (LA12_298=='O') ) {
                                                    int LA12_312 = input.LA(13);

                                                    if ( (LA12_312=='N') ) {
                                                        int LA12_322 = input.LA(14);

                                                        if ( (LA12_322=='T') ) {
                                                            int LA12_330 = input.LA(15);

                                                            if ( (LA12_330=='E') ) {
                                                                int LA12_335 = input.LA(16);

                                                                if ( (LA12_335=='X') ) {
                                                                    int LA12_340 = input.LA(17);

                                                                    if ( (LA12_340=='T') ) {
                                                                        int LA12_345 = input.LA(18);

                                                                        if ( (LA12_345=='I') ) {
                                                                            int LA12_349 = input.LA(19);

                                                                            if ( (LA12_349=='D') ) {
                                                                                int LA12_353 = input.LA(20);

                                                                                if ( (LA12_353==':') ) {
                                                                                    return 18;
                                                                                }
                                                                                else {
                                                                                    return 46;}
                                                                            }
                                                                            else {
                                                                                return 46;}
                                                                        }
                                                                        else {
                                                                            return 46;}
                                                                    }
                                                                    else {
                                                                        return 46;}
                                                                }
                                                                else {
                                                                    return 46;}
                                                            }
                                                            else {
                                                                return 46;}
                                                        }
                                                        else {
                                                            return 46;}
                                                    }
                                                    else {
                                                        return 46;}
                                                }
                                                else {
                                                    return 46;}
                                            }
                                            else {
                                                return 46;}
                                        }
                                        else {
                                            return 46;}
                                    }
                                    else {
                                        return 46;}
                                }
                                else {
                                    return 46;}
                            }
                            else {
                                return 46;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
            }
            else {
                return 46;}
        }
        else {
            return 46;}
    }

    private int mTokensHelper012() throws RecognitionException {
        switch ( input.LA(2) ) {
        case 'I':
            {
            int LA12_48 = input.LA(3);

            if ( (LA12_48=='N') ) {
                int LA12_84 = input.LA(4);

                if ( (LA12_84=='E') ) {
                    int LA12_117 = input.LA(5);

                    if ( (LA12_117=='S') ) {
                        int LA12_150 = input.LA(6);

                        if ( (LA12_150=='T') ) {
                            int LA12_182 = input.LA(7);

                            if ( (LA12_182=='Y') ) {
                                int LA12_213 = input.LA(8);

                                if ( (LA12_213=='L') ) {
                                    int LA12_241 = input.LA(9);

                                    if ( (LA12_241=='E') ) {
                                        int LA12_264 = input.LA(10);

                                        if ( (LA12_264==':') ) {
                                            return 20;
                                        }
                                        else {
                                            return 46;}
                                    }
                                    else {
                                        return 46;}
                                }
                                else {
                                    return 46;}
                            }
                            else {
                                return 46;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
            }
            else {
                return 46;}
            }
        case 'A':
            {
            int LA12_49 = input.LA(3);

            if ( (LA12_49=='B') ) {
                int LA12_85 = input.LA(4);

                if ( (LA12_85=='E') ) {
                    int LA12_118 = input.LA(5);

                    if ( (LA12_118=='L') ) {
                        int LA12_151 = input.LA(6);

                        if ( ((LA12_151>='0' && LA12_151<='9')||(LA12_151>='A' && LA12_151<='Z')||LA12_151=='_'||(LA12_151>='a' && LA12_151<='z')) ) {
                            return 46;
                        }
                        else {
                            return 33;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
            }
            else {
                return 46;}
            }
        case 'E':
            {
            int LA12_50 = input.LA(3);

            if ( (LA12_50=='N') ) {
                int LA12_86 = input.LA(4);

                if ( (LA12_86=='G') ) {
                    int LA12_119 = input.LA(5);

                    if ( (LA12_119=='T') ) {
                        int LA12_152 = input.LA(6);

                        if ( (LA12_152=='H') ) {
                            int LA12_184 = input.LA(7);

                            if ( (LA12_184==':') ) {
                                return 38;
                            }
                            else {
                                return 46;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
            }
            else {
                return 46;}
            }
        default:
            return 46;}

    }

    private int mTokensHelper013() throws RecognitionException {
        int LA12_13 = input.LA(2);

        if ( (LA12_13=='E') ) {
            switch ( input.LA(3) ) {
            case 'T':
                {
                int LA12_87 = input.LA(4);

                if ( (LA12_87=='A') ) {
                    int LA12_120 = input.LA(5);

                    if ( (LA12_120=='N') ) {
                        int LA12_153 = input.LA(6);

                        if ( (LA12_153=='A') ) {
                            int LA12_185 = input.LA(7);

                            if ( (LA12_185=='M') ) {
                                int LA12_215 = input.LA(8);

                                if ( (LA12_215=='E') ) {
                                    int LA12_242 = input.LA(9);

                                    if ( (LA12_242==':') ) {
                                        return 22;
                                    }
                                    else {
                                        return 46;}
                                }
                                else {
                                    return 46;}
                            }
                            else {
                                return 46;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
                }
            case 'R':
                {
                int LA12_88 = input.LA(4);

                if ( (LA12_88=='G') ) {
                    int LA12_121 = input.LA(5);

                    if ( (LA12_121=='E') ) {
                        int LA12_154 = input.LA(6);

                        if ( (LA12_154=='O') ) {
                            int LA12_186 = input.LA(7);

                            if ( (LA12_186=='R') ) {
                                int LA12_216 = input.LA(8);

                                if ( (LA12_216=='D') ) {
                                    int LA12_243 = input.LA(9);

                                    if ( (LA12_243=='E') ) {
                                        int LA12_266 = input.LA(10);

                                        if ( (LA12_266=='R') ) {
                                            int LA12_285 = input.LA(11);

                                            if ( (LA12_285==':') ) {
                                                return 21;
                                            }
                                            else {
                                                return 46;}
                                        }
                                        else {
                                            return 46;}
                                    }
                                    else {
                                        return 46;}
                                }
                                else {
                                    return 46;}
                            }
                            else {
                                return 46;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
                }
            default:
                return 46;}

        }
        else {
            return 46;}
    }

    private int mTokensHelper014() throws RecognitionException {
        int LA12_14 = input.LA(2);

        if ( (LA12_14=='U') ) {
            int LA12_52 = input.LA(3);

            if ( (LA12_52=='T') ) {
                int LA12_89 = input.LA(4);

                if ( (LA12_89=='P') ) {
                    int LA12_122 = input.LA(5);

                    if ( (LA12_122=='U') ) {
                        int LA12_155 = input.LA(6);

                        if ( (LA12_155=='T') ) {
                            int LA12_187 = input.LA(7);

                            if ( (LA12_187=='I') ) {
                                int LA12_217 = input.LA(8);

                                if ( (LA12_217=='D') ) {
                                    int LA12_244 = input.LA(9);

                                    if ( (LA12_244==':') ) {
                                        return 23;
                                    }
                                    else {
                                        return 46;}
                                }
                                else {
                                    return 46;}
                            }
                            else {
                                return 46;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
            }
            else {
                return 46;}
        }
        else {
            return 46;}
    }

    private int mTokensHelper015() throws RecognitionException {
        int LA12_15 = input.LA(2);

        if ( (LA12_15=='O') ) {
            int LA12_53 = input.LA(3);

            if ( (LA12_53=='U') ) {
                int LA12_90 = input.LA(4);

                if ( (LA12_90=='R') ) {
                    int LA12_123 = input.LA(5);

                    if ( (LA12_123=='C') ) {
                        int LA12_156 = input.LA(6);

                        if ( (LA12_156=='E') ) {
                            switch ( input.LA(7) ) {
                            case ':':
                                {
                                return 24;
                                }
                            case 'T':
                                {
                                int LA12_219 = input.LA(8);

                                if ( (LA12_219=='Y') ) {
                                    int LA12_245 = input.LA(9);

                                    if ( (LA12_245=='P') ) {
                                        int LA12_268 = input.LA(10);

                                        if ( (LA12_268=='E') ) {
                                            int LA12_286 = input.LA(11);

                                            if ( (LA12_286==':') ) {
                                                return 42;
                                            }
                                            else {
                                                return 46;}
                                        }
                                        else {
                                            return 46;}
                                    }
                                    else {
                                        return 46;}
                                }
                                else {
                                    return 46;}
                                }
                            default:
                                return 46;}

                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
            }
            else {
                return 46;}
        }
        else {
            return 46;}
    }

    private int mTokensHelper016() throws RecognitionException {
        switch ( input.LA(2) ) {
        case 'p':
            {
            int LA12_54 = input.LA(3);

            if ( (LA12_54=='a') ) {
                int LA12_91 = input.LA(4);

                if ( (LA12_91=='q') ) {
                    int LA12_124 = input.LA(5);

                    if ( (LA12_124=='u') ) {
                        int LA12_157 = input.LA(6);

                        if ( (LA12_157=='e') ) {
                            int LA12_189 = input.LA(7);

                            if ( ((LA12_189>='0' && LA12_189<='9')||(LA12_189>='A' && LA12_189<='Z')||LA12_189=='_'||(LA12_189>='a' && LA12_189<='z')) ) {
                                return 46;
                            }
                            else {
                                return 27;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
            }
            else {
                return 46;}
            }
        case 'r':
            {
            int LA12_55 = input.LA(3);

            if ( (LA12_55=='i') ) {
                int LA12_92 = input.LA(4);

                if ( (LA12_92=='g') ) {
                    int LA12_125 = input.LA(5);

                    if ( (LA12_125=='i') ) {
                        int LA12_158 = input.LA(6);

                        if ( (LA12_158=='n') ) {
                            int LA12_190 = input.LA(7);

                            if ( (LA12_190=='a') ) {
                                int LA12_221 = input.LA(8);

                                if ( (LA12_221=='l') ) {
                                    int LA12_246 = input.LA(9);

                                    if ( (LA12_246=='D') ) {
                                        int LA12_269 = input.LA(10);

                                        if ( (LA12_269=='b') ) {
                                            int LA12_287 = input.LA(11);

                                            if ( (LA12_287=='C') ) {
                                                int LA12_301 = input.LA(12);

                                                if ( (LA12_301=='o') ) {
                                                    int LA12_313 = input.LA(13);

                                                    if ( (LA12_313=='l') ) {
                                                        int LA12_323 = input.LA(14);

                                                        if ( (LA12_323=='u') ) {
                                                            int LA12_331 = input.LA(15);

                                                            if ( (LA12_331=='m') ) {
                                                                int LA12_336 = input.LA(16);

                                                                if ( (LA12_336=='n') ) {
                                                                    int LA12_341 = input.LA(17);

                                                                    if ( (LA12_341=='N') ) {
                                                                        int LA12_346 = input.LA(18);

                                                                        if ( (LA12_346=='a') ) {
                                                                            int LA12_350 = input.LA(19);

                                                                            if ( (LA12_350=='m') ) {
                                                                                int LA12_354 = input.LA(20);

                                                                                if ( (LA12_354=='e') ) {
                                                                                    int LA12_358 = input.LA(21);

                                                                                    if ( ((LA12_358>='0' && LA12_358<='9')||(LA12_358>='A' && LA12_358<='Z')||LA12_358=='_'||(LA12_358>='a' && LA12_358<='z')) ) {
                                                                                        return 46;
                                                                                    }
                                                                                    else {
                                                                                        return 39;}
                                                                                }
                                                                                else {
                                                                                    return 46;}
                                                                            }
                                                                            else {
                                                                                return 46;}
                                                                        }
                                                                        else {
                                                                            return 46;}
                                                                    }
                                                                    else {
                                                                        return 46;}
                                                                }
                                                                else {
                                                                    return 46;}
                                                            }
                                                            else {
                                                                return 46;}
                                                        }
                                                        else {
                                                            return 46;}
                                                    }
                                                    else {
                                                        return 46;}
                                                }
                                                else {
                                                    return 46;}
                                            }
                                            else {
                                                return 46;}
                                        }
                                        else {
                                            return 46;}
                                    }
                                    else {
                                        return 46;}
                                }
                                else {
                                    return 46;}
                            }
                            else {
                                return 46;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
            }
            else {
                return 46;}
            }
        default:
            return 46;}

    }

    private int mTokensHelper017() throws RecognitionException {
        int LA12_17 = input.LA(2);

        if ( ((LA12_17>='\u0000' && LA12_17<='\uFFFE')) ) {
            return 48;
        }
        else {
            return 29;}
    }

    private int mTokensHelper018() throws RecognitionException {
        switch ( input.LA(2) ) {
        case 'e':
            {
            int LA12_58 = input.LA(3);

            if ( (LA12_58=='x') ) {
                int LA12_93 = input.LA(4);

                if ( (LA12_93=='t') ) {
                    int LA12_126 = input.LA(5);

                    if ( ((LA12_126>='0' && LA12_126<='9')||(LA12_126>='A' && LA12_126<='Z')||LA12_126=='_'||(LA12_126>='a' && LA12_126<='z')) ) {
                        return 46;
                    }
                    else {
                        return 30;}
                }
                else {
                    return 46;}
            }
            else {
                return 46;}
            }
        case 'r':
            {
            int LA12_59 = input.LA(3);

            if ( (LA12_59=='u') ) {
                int LA12_94 = input.LA(4);

                if ( (LA12_94=='e') ) {
                    int LA12_127 = input.LA(5);

                    if ( ((LA12_127>='0' && LA12_127<='9')||(LA12_127>='A' && LA12_127<='Z')||LA12_127=='_'||(LA12_127>='a' && LA12_127<='z')) ) {
                        return 46;
                    }
                    else {
                        return 44;}
                }
                else {
                    return 46;}
            }
            else {
                return 46;}
            }
        default:
            return 46;}

    }

    private int mTokensHelper019() throws RecognitionException {
        return 31;
    }

    private int mTokensHelper020() throws RecognitionException {
        int LA12_20 = input.LA(2);

        if ( (LA12_20=='E') ) {
            int LA12_61 = input.LA(3);

            if ( (LA12_61=='Y') ) {
                int LA12_95 = input.LA(4);

                if ( (LA12_95==':') ) {
                    return 35;
                }
                else {
                    return 46;}
            }
            else {
                return 46;}
        }
        else {
            return 46;}
    }

    private int mTokensHelper021() throws RecognitionException {
        int LA12_21 = input.LA(2);

        if ( (LA12_21=='E') ) {
            int LA12_62 = input.LA(3);

            if ( (LA12_62=='F') ) {
                int LA12_96 = input.LA(4);

                if ( (LA12_96=='A') ) {
                    int LA12_129 = input.LA(5);

                    if ( (LA12_129=='U') ) {
                        int LA12_161 = input.LA(6);

                        if ( (LA12_161=='L') ) {
                            int LA12_191 = input.LA(7);

                            if ( (LA12_191=='T') ) {
                                int LA12_222 = input.LA(8);

                                if ( (LA12_222=='V') ) {
                                    int LA12_247 = input.LA(9);

                                    if ( (LA12_247=='A') ) {
                                        int LA12_270 = input.LA(10);

                                        if ( (LA12_270=='L') ) {
                                            int LA12_288 = input.LA(11);

                                            if ( (LA12_288=='U') ) {
                                                int LA12_302 = input.LA(12);

                                                if ( (LA12_302=='E') ) {
                                                    int LA12_314 = input.LA(13);

                                                    if ( (LA12_314==':') ) {
                                                        return 37;
                                                    }
                                                    else {
                                                        return 46;}
                                                }
                                                else {
                                                    return 46;}
                                            }
                                            else {
                                                return 46;}
                                        }
                                        else {
                                            return 46;}
                                    }
                                    else {
                                        return 46;}
                                }
                                else {
                                    return 46;}
                            }
                            else {
                                return 46;}
                        }
                        else {
                            return 46;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
            }
            else {
                return 46;}
        }
        else {
            return 46;}
    }

    private int mTokensHelper022() throws RecognitionException {
        return 43;
    }

    private int mTokensHelper023() throws RecognitionException {
        int LA12_23 = input.LA(2);

        if ( (LA12_23=='a') ) {
            int LA12_64 = input.LA(3);

            if ( (LA12_64=='l') ) {
                int LA12_97 = input.LA(4);

                if ( (LA12_97=='s') ) {
                    int LA12_130 = input.LA(5);

                    if ( (LA12_130=='e') ) {
                        int LA12_162 = input.LA(6);

                        if ( ((LA12_162>='0' && LA12_162<='9')||(LA12_162>='A' && LA12_162<='Z')||LA12_162=='_'||(LA12_162>='a' && LA12_162<='z')) ) {
                            return 46;
                        }
                        else {
                            return 45;}
                    }
                    else {
                        return 46;}
                }
                else {
                    return 46;}
            }
            else {
                return 46;}
        }
        else {
            return 46;}
    }

    private int mTokensHelper024() throws RecognitionException {
        int LA12_24 = input.LA(2);

        if ( ((LA12_24>='A' && LA12_24<='Z')||LA12_24=='_'||(LA12_24>='a' && LA12_24<='z')) ) {
            return 46;
        }
        else {
            return 52;}
    }

    private int mTokensHelper025() throws RecognitionException {
        return 46;
    }

    private int mTokensHelper026() throws RecognitionException {
        return 47;
    }

    private int mTokensHelper027() throws RecognitionException {
        int LA12_27 = input.LA(2);

        if ( ((LA12_27>='\u0000' && LA12_27<='\uFFFE')) ) {
            return 48;
        }
        else {
            return 52;}
    }

    private int mTokensHelper028() throws RecognitionException {
        switch ( input.LA(2) ) {
        case '*':
            {
            return 49;
            }
        case '/':
            {
            return 50;
            }
        default:
            return 52;}

    }

    private int mTokensHelper029() throws RecognitionException {
        return 51;
    }

    private int mTokensHelper030() throws RecognitionException {
        return 52;
    }

    private int mTokensHelper031() throws RecognitionException {
        NoViableAltException nvae =
            new NoViableAltException("1:1: Tokens : ( T11 | T12 | T13 | T14 | T15 | T16 | T17 | T18 | T19 | T20 | T21 | T22 | T23 | T24 | T25 | T26 | T27 | T28 | T29 | T30 | T31 | T32 | T33 | T34 | T35 | T36 | T37 | T38 | T39 | T40 | T41 | T42 | T43 | T44 | T45 | T46 | T47 | T48 | T49 | T50 | T51 | T52 | T53 | T54 | T55 | RULE_ID | RULE_INT | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );", 12, 0, input);

        throw nvae;
    }



 

}