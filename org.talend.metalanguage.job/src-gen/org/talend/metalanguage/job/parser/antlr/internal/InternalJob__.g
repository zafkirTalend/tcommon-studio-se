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
T24 : 'ALIAS:' ;
T25 : 'addColumn' ;
T26 : 'TYPE:' ;
T27 : 'NULLABLE:' ;
T28 : 'EXPRESSION:' ;
T29 : 'JOIN:' ;
T30 : 'OPERATOR:' ;
T31 : 'addFilter' ;
T32 : 'addMapperData' ;
T33 : 'addUiProperties' ;
T34 : 'SHELLMAXIMIZED:' ;
T35 : 'SIZESTATE:' ;
T36 : 'EXPRESSIONFILTER:' ;
T37 : 'ACTIVATEEXPRESSIONFILTER:' ;
T38 : 'ACTIVATECONDENSEDTOOL:' ;
T39 : 'MATCHINGMODE:' ;
T40 : 'LOOKUPMODE:' ;
T41 : 'REJECT:' ;
T42 : 'REJECTINNERJOIN:' ;
T43 : 'ISERRORREJECTTABLE:' ;
T44 : 'ISJOINTABLEOF:' ;
T45 : 'addComponent' ;
T46 : 'setComponentDefinition' ;
T47 : 'POSITION:' ;
T48 : 'setSettings' ;
T49 : 'ContextType' ;
T50 : 'CONFIRMATIONNEED:' ;
T51 : 'addContextParameter' ;
T52 : 'VALUE:' ;
T53 : 'COMMENT:' ;
T54 : 'PROMPT:' ;
T55 : 'PROMPTNEEDED:' ;
T56 : 'REPOSITORYCONTEXTID:' ;
T57 : 'addConnection' ;
T58 : 'LINESTYLE:' ;
T59 : 'MERGEORDER:' ;
T60 : 'METANAME:' ;
T61 : 'OUTPUTID:' ;
T62 : 'SOURCE:' ;
T63 : 'TARGET:' ;
T64 : 'addNote' ;
T65 : 'opaque' ;
T66 : 'POSITION' ;
T67 : '"' ;
T68 : 'text' ;
T69 : ':' ;
T70 : 'addSchema' ;
T71 : 'LABEL:' ;
T72 : 'KEY:' ;
T73 : 'DEFAULTVALUE:' ;
T74 : 'LENGTH:' ;
T75 : 'ORIGINALDBCOLUMNNAME' ;
T76 : 'PATTREN:' ;
T77 : 'PRECISION:' ;
T78 : 'SOURCETYPE:' ;
T79 : '-' ;
T80 : 'true' ;
T81 : 'false' ;

// $ANTLR src "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g" 4931
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g" 4933
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g" 4935
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g" 4937
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g" 4939
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g" 4941
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g" 4943
RULE_ANY_OTHER : .;


