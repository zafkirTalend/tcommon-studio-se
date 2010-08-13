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
T15 : 'addComponent' ;
T16 : 'setComponentDefinition' ;
T17 : 'TYPE:' ;
T18 : 'NAME:' ;
T19 : 'POSITION:' ;
T20 : 'setSettings' ;
T21 : 'ContextType' ;
T22 : 'CONFIRMATIONNEED:' ;
T23 : 'addContextParameter' ;
T24 : 'VALUE:' ;
T25 : 'COMMENT:' ;
T26 : 'PROMPT:' ;
T27 : 'PROMPTNEEDED:' ;
T28 : 'REPOSITORYCONTEXTID:' ;
T29 : 'addConnection' ;
T30 : 'LINESTYLE:' ;
T31 : 'MERGEORDER:' ;
T32 : 'METANAME:' ;
T33 : 'OUTPUTID:' ;
T34 : 'SOURCE:' ;
T35 : 'TARGET:' ;
T36 : 'addNote' ;
T37 : 'opaque' ;
T38 : 'POSITION' ;
T39 : '"' ;
T40 : 'text' ;
T41 : ':' ;
T42 : 'addSchema' ;
T43 : 'LABEL' ;
T44 : 'addColumn' ;
T45 : 'KEY:' ;
T46 : 'NULLABLE:' ;
T47 : 'DEFAULTVALUE:' ;
T48 : 'LENGTH:' ;
T49 : 'originalDbColumnName' ;
T50 : 'PATTREN:' ;
T51 : 'PRECISION:' ;
T52 : 'SOURCETYPE:' ;
T53 : '-' ;
T54 : 'true' ;
T55 : 'false' ;

// $ANTLR src "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g" 2658
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g" 2660
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g" 2662
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g" 2664
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g" 2666
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g" 2668
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g" 2670
RULE_ANY_OTHER : .;


