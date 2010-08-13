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
T17 : 'addComponent' ;
T18 : 'setComponentDefinition' ;
T19 : 'setSettings' ;
T20 : 'TYPE:' ;
T21 : 'NAME:' ;
T22 : 'POSITION:' ;
T23 : 'ContextType' ;
T24 : 'CONFIRMATIONNEED:' ;
T25 : 'addContextParameter' ;
T26 : 'VALUE:' ;
T27 : 'COMMENT:' ;
T28 : 'PROMPT:' ;
T29 : 'PROMPTNEEDED:' ;
T30 : 'REPOSITORYCONTEXTID:' ;
T31 : 'addConnection' ;
T32 : 'LINESTYLE:' ;
T33 : 'MERGEORDER:' ;
T34 : 'METANAME:' ;
T35 : 'OUTPUTID:' ;
T36 : 'SOURCE:' ;
T37 : 'TARGET:' ;
T38 : 'addNote' ;
T39 : 'opaque' ;
T40 : 'POSITION' ;
T41 : '"' ;
T42 : 'text' ;
T43 : ':' ;
T44 : 'addSchema' ;
T45 : 'LABEL' ;
T46 : 'addColumn' ;
T47 : 'KEY:' ;
T48 : 'NULLABLE:' ;
T49 : 'DEFAULTVALUE:' ;
T50 : 'LENGTH:' ;
T51 : 'originalDbColumnName' ;
T52 : 'PATTREN:' ;
T53 : 'PRECISION:' ;
T54 : 'SOURCETYPE:' ;
T55 : '-' ;

// $ANTLR src "../org.talend.metalanguage.job.ui/src-gen/org/talend/metalanguage/job/ui/contentassist/antlr/internal/InternalJob.g" 9032
RULE_ID : '^'? ('a'..'z'|'A'..'Z'|'_') ('a'..'z'|'A'..'Z'|'_'|'0'..'9')*;

// $ANTLR src "../org.talend.metalanguage.job.ui/src-gen/org/talend/metalanguage/job/ui/contentassist/antlr/internal/InternalJob.g" 9034
RULE_INT : ('0'..'9')+;

// $ANTLR src "../org.talend.metalanguage.job.ui/src-gen/org/talend/metalanguage/job/ui/contentassist/antlr/internal/InternalJob.g" 9036
RULE_STRING : ('"' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'"')))* '"'|'\'' ('\\' ('b'|'t'|'n'|'f'|'r'|'"'|'\''|'\\')|~(('\\'|'\'')))* '\'');

// $ANTLR src "../org.talend.metalanguage.job.ui/src-gen/org/talend/metalanguage/job/ui/contentassist/antlr/internal/InternalJob.g" 9038
RULE_ML_COMMENT : '/*' ( options {greedy=false;} : . )*'*/';

// $ANTLR src "../org.talend.metalanguage.job.ui/src-gen/org/talend/metalanguage/job/ui/contentassist/antlr/internal/InternalJob.g" 9040
RULE_SL_COMMENT : '//' ~(('\n'|'\r'))* ('\r'? '\n')?;

// $ANTLR src "../org.talend.metalanguage.job.ui/src-gen/org/talend/metalanguage/job/ui/contentassist/antlr/internal/InternalJob.g" 9042
RULE_WS : (' '|'\t'|'\r'|'\n')+;

// $ANTLR src "../org.talend.metalanguage.job.ui/src-gen/org/talend/metalanguage/job/ui/contentassist/antlr/internal/InternalJob.g" 9044
RULE_ANY_OTHER : .;


