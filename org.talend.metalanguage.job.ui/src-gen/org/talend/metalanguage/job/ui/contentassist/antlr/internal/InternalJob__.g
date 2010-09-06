lexer grammar InternalJob;
@header {
package org.talend.metalanguage.job.ui.contentassist.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.Lexer;
}

T11 : 'true' ;
T12 : 'false' ;
T13 : 'addParameters' ;
T14 : '{' ;
T15 : '}' ;
T16 : ',' ;
T17 : 'addDBMapData' ;
T18 : 'addInputTable' ;
T19 : 'addVarTable' ;
T20 : 'addOutputTable' ;
T21 : 'NAME:' ;
T22 : 'MINIMIZED:' ;
T23 : 'READONLY:' ;
T24 : 'TABLENAME:' ;
T25 : 'JOINTYPE:' ;
T26 : 'ALIAS:' ;
T27 : 'addColumn' ;
T28 : 'TYPE:' ;
T29 : 'NULLABLE:' ;
T30 : 'EXPRESSION:' ;
T31 : 'JOIN:' ;
T32 : 'OPERATOR:' ;
T33 : 'addFilter' ;
T34 : 'addMapperData' ;
T35 : 'addUiProperties' ;
T36 : 'SHELLMAXIMIZED:' ;
T37 : 'SIZESTATE:' ;
T38 : 'EXPRESSIONFILTER:' ;
T39 : 'ACTIVATEEXPRESSIONFILTER:' ;
T40 : 'ACTIVATECONDENSEDTOOL:' ;
T41 : 'MATCHINGMODE:' ;
T42 : 'LOOKUPMODE:' ;
T43 : 'REJECT:' ;
T44 : 'REJECTINNERJOIN:' ;
T45 : 'ISERRORREJECTTABLE:' ;
T46 : 'ISJOINTABLEOF:' ;
T47 : 'addComponent' ;
T48 : 'setComponentDefinition' ;
T49 : 'setSettings' ;
T50 : 'POSITION:' ;
T51 : 'ContextType' ;
T52 : 'CONFIRMATIONNEED:' ;
T53 : 'addContextParameter' ;
T54 : 'VALUE:' ;
T55 : 'COMMENT:' ;
T56 : 'PROMPT:' ;
T57 : 'PROMPTNEEDED:' ;
T58 : 'REPOSITORYCONTEXTID:' ;
T59 : 'addConnection' ;
T60 : 'LINESTYLE:' ;
T61 : 'MERGEORDER:' ;
T62 : 'METANAME:' ;
T63 : 'OUTPUTID:' ;
T64 : 'SOURCE:' ;
T65 : 'TARGET:' ;
T66 : 'addNote' ;
T67 : 'opaque' ;
T68 : 'POSITION' ;
T69 : '"' ;
T70 : 'text' ;
T71 : ':' ;
T72 : 'addSchema' ;
T73 : 'LABEL:' ;
T74 : 'KEY:' ;
T75 : 'DEFAULTVALUE:' ;
T76 : 'LENGTH:' ;
T77 : 'ORIGINALDBCOLUMNNAME' ;
T78 : 'PATTREN:' ;
T79 : 'PRECISION:' ;
T80 : 'SOURCETYPE:' ;
T81 : '-' ;

// $ANTLR src "../org.talend.metalanguage.job.ui/src-gen/org/talend/metalanguage/job/ui/contentassist/antlr/internal/InternalJob.g" 16840
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.talend.metalanguage.job.ui/src-gen/org/talend/metalanguage/job/ui/contentassist/antlr/internal/InternalJob.g" 16842
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.talend.metalanguage.job.ui/src-gen/org/talend/metalanguage/job/ui/contentassist/antlr/internal/InternalJob.g" 16844
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../org.talend.metalanguage.job.ui/src-gen/org/talend/metalanguage/job/ui/contentassist/antlr/internal/InternalJob.g" 16846
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.talend.metalanguage.job.ui/src-gen/org/talend/metalanguage/job/ui/contentassist/antlr/internal/InternalJob.g" 16848
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.talend.metalanguage.job.ui/src-gen/org/talend/metalanguage/job/ui/contentassist/antlr/internal/InternalJob.g" 16850
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.talend.metalanguage.job.ui/src-gen/org/talend/metalanguage/job/ui/contentassist/antlr/internal/InternalJob.g" 16852
RULE_ANY_OTHER : .;


