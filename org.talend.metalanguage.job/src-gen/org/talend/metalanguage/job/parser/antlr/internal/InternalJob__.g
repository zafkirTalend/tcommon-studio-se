lexer grammar InternalJob;
@header {
package org.talend.metalanguage.job.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;
}

T11 : 'addParameters' ;
T12 : '{' ;
T13 : '}' ;
T14 : ',' ;
T15 : 'addDBMapData' ;
T16 : 'addInputTable' ;
T17 : 'addVarTable' ;
T18 : 'addOutputTable' ;
T19 : 'NAME:' ;
T20 : 'MINIMIZED:' ;
T21 : 'READONLY:' ;
T22 : 'TABLENAME:' ;
T23 : 'JOINTYPE:' ;
T24 : 'NO_JOIN' ;
T25 : 'INNER_JOIN' ;
T26 : 'LEFT_OUTER_JOIN' ;
T27 : 'RIGHT_OUTER_JOIN' ;
T28 : 'FULL_OUTER_JOIN' ;
T29 : 'CROSS_JOIN' ;
T30 : 'LEFT_OUTER_JOIN_ORACLE' ;
T31 : 'RIGHT_OUTER_JOIN_ORACLE' ;
T32 : 'ALIAS:' ;
T33 : 'addColumn' ;
T34 : 'TYPE:' ;
T35 : 'NULLABLE:' ;
T36 : 'EXPRESSION:' ;
T37 : 'JOIN:' ;
T38 : 'OPERATOR:' ;
T39 : 'addFilter' ;
T40 : 'addMapperData' ;
T41 : 'addUiProperties' ;
T42 : 'SHELLMAXIMIZED:' ;
T43 : 'SIZESTATE:' ;
T44 : 'MINIMIZED' ;
T45 : 'INTERMEDIATE' ;
T46 : 'MAXIMIZED' ;
T47 : 'EXPRESSIONFILTER:' ;
T48 : 'ACTIVATEEXPRESSIONFILTER:' ;
T49 : 'ACTIVATECONDENSEDTOOL:' ;
T50 : 'MATCHINGMODE:' ;
T51 : 'LOOKUPMODE:' ;
T52 : 'REJECT:' ;
T53 : 'REJECTINNERJOIN:' ;
T54 : 'ISERRORREJECTTABLE:' ;
T55 : 'ISJOINTABLEOF:' ;
T56 : 'addComponent' ;
T57 : 'setComponentDefinition' ;
T58 : 'POSITION:' ;
T59 : 'setSettings' ;
T60 : 'ContextType' ;
T61 : 'CONFIRMATIONNEED:' ;
T62 : 'addContextParameter' ;
T63 : 'VALUE:' ;
T64 : 'COMMENT:' ;
T65 : 'PROMPT:' ;
T66 : 'PROMPTNEEDED:' ;
T67 : 'REPOSITORYCONTEXTID:' ;
T68 : 'addConnection' ;
T69 : 'LINESTYLE:' ;
T70 : 'MERGEORDER:' ;
T71 : 'METANAME:' ;
T72 : 'OUTPUTID:' ;
T73 : 'SOURCE:' ;
T74 : 'TARGET:' ;
T75 : 'addNote' ;
T76 : 'opaque' ;
T77 : 'POSITION' ;
T78 : '"' ;
T79 : 'text' ;
T80 : ':' ;
T81 : 'addSchema' ;
T82 : 'LABEL:' ;
T83 : 'KEY:' ;
T84 : 'DEFAULTVALUE:' ;
T85 : 'LENGTH:' ;
T86 : 'ORIGINALDBCOLUMNNAME' ;
T87 : 'PATTREN:' ;
T88 : 'PRECISION:' ;
T89 : 'SOURCETYPE:' ;
T90 : '-' ;
T91 : 'true' ;
T92 : 'false' ;

// $ANTLR src "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g" 5205
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g" 5207
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g" 5209
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g" 5211
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g" 5213
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g" 5215
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g" 5217
RULE_ANY_OTHER : .;


