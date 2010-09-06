package org.talend.metalanguage.job.parser.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.xtext.parsetree.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import org.eclipse.xtext.conversion.ValueConverterException;
import org.talend.metalanguage.job.services.JobGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalJobParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'addParameters'", "'{'", "'}'", "','", "'addDBMapData'", "'addInputTable'", "'addVarTable'", "'addOutputTable'", "'NAME:'", "'MINIMIZED:'", "'READONLY:'", "'TABLENAME:'", "'JOINTYPE:'", "'ALIAS:'", "'addColumn'", "'TYPE:'", "'NULLABLE:'", "'EXPRESSION:'", "'JOIN:'", "'OPERATOR:'", "'addFilter'", "'addMapperData'", "'addUiProperties'", "'SHELLMAXIMIZED:'", "'SIZESTATE:'", "'EXPRESSIONFILTER:'", "'ACTIVATEEXPRESSIONFILTER:'", "'ACTIVATECONDENSEDTOOL:'", "'MATCHINGMODE:'", "'LOOKUPMODE:'", "'REJECT:'", "'REJECTINNERJOIN:'", "'ISERRORREJECTTABLE:'", "'ISJOINTABLEOF:'", "'addComponent'", "'setComponentDefinition'", "'POSITION:'", "'setSettings'", "'ContextType'", "'CONFIRMATIONNEED:'", "'addContextParameter'", "'VALUE:'", "'COMMENT:'", "'PROMPT:'", "'PROMPTNEEDED:'", "'REPOSITORYCONTEXTID:'", "'addConnection'", "'LINESTYLE:'", "'MERGEORDER:'", "'METANAME:'", "'OUTPUTID:'", "'SOURCE:'", "'TARGET:'", "'addNote'", "'opaque'", "'POSITION'", "'\"'", "'text'", "':'", "'addSchema'", "'LABEL:'", "'KEY:'", "'DEFAULTVALUE:'", "'LENGTH:'", "'ORIGINALDBCOLUMNNAME'", "'PATTREN:'", "'PRECISION:'", "'SOURCETYPE:'", "'-'", "'true'", "'false'"
    };
    public static final int RULE_ID=5;
    public static final int RULE_STRING=4;
    public static final int RULE_ANY_OTHER=10;
    public static final int RULE_INT=6;
    public static final int RULE_WS=9;
    public static final int RULE_SL_COMMENT=8;
    public static final int EOF=-1;
    public static final int RULE_ML_COMMENT=7;

        public InternalJobParser(TokenStream input) {
            super(input);
        }
        

    public String[] getTokenNames() { return tokenNames; }
    public String getGrammarFileName() { return "../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g"; }



     	private JobGrammarAccess grammarAccess;
     	
        public InternalJobParser(TokenStream input, IAstFactory factory, JobGrammarAccess grammarAccess) {
            this(input);
            this.factory = factory;
            registerRules(grammarAccess.getGrammar());
            this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected InputStream getTokenFile() {
        	ClassLoader classLoader = getClass().getClassLoader();
        	return classLoader.getResourceAsStream("org/talend/metalanguage/job/parser/antlr/internal/InternalJob.tokens");
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "ProcessType";	
       	}
       	
       	@Override
       	protected JobGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start entryRuleProcessType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:77:1: entryRuleProcessType returns [EObject current=null] : iv_ruleProcessType= ruleProcessType EOF ;
    public final EObject entryRuleProcessType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleProcessType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:78:2: (iv_ruleProcessType= ruleProcessType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:79:2: iv_ruleProcessType= ruleProcessType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getProcessTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleProcessType_in_entryRuleProcessType75);
            iv_ruleProcessType=ruleProcessType();
            _fsp--;

             current =iv_ruleProcessType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleProcessType85); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleProcessType


    // $ANTLR start ruleProcessType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:86:1: ruleProcessType returns [EObject current=null] : ( () ( 'addParameters' '{' ( (lv_parameters_3_0= ruleParametersType ) ) '}' )? ( (lv_node_5_0= ruleNodeType ) )* ( (lv_connection_6_0= ruleConnectionType ) )* ( (lv_note_7_0= ruleNoteType ) )* ( (lv_context_8_0= ruleContextType ) )* ) ;
    public final EObject ruleProcessType() throws RecognitionException {
        EObject current = null;

        EObject lv_parameters_3_0 = null;

        EObject lv_node_5_0 = null;

        EObject lv_connection_6_0 = null;

        EObject lv_note_7_0 = null;

        EObject lv_context_8_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:91:6: ( ( () ( 'addParameters' '{' ( (lv_parameters_3_0= ruleParametersType ) ) '}' )? ( (lv_node_5_0= ruleNodeType ) )* ( (lv_connection_6_0= ruleConnectionType ) )* ( (lv_note_7_0= ruleNoteType ) )* ( (lv_context_8_0= ruleContextType ) )* ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:92:1: ( () ( 'addParameters' '{' ( (lv_parameters_3_0= ruleParametersType ) ) '}' )? ( (lv_node_5_0= ruleNodeType ) )* ( (lv_connection_6_0= ruleConnectionType ) )* ( (lv_note_7_0= ruleNoteType ) )* ( (lv_context_8_0= ruleContextType ) )* )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:92:1: ( () ( 'addParameters' '{' ( (lv_parameters_3_0= ruleParametersType ) ) '}' )? ( (lv_node_5_0= ruleNodeType ) )* ( (lv_connection_6_0= ruleConnectionType ) )* ( (lv_note_7_0= ruleNoteType ) )* ( (lv_context_8_0= ruleContextType ) )* )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:92:2: () ( 'addParameters' '{' ( (lv_parameters_3_0= ruleParametersType ) ) '}' )? ( (lv_node_5_0= ruleNodeType ) )* ( (lv_connection_6_0= ruleConnectionType ) )* ( (lv_note_7_0= ruleNoteType ) )* ( (lv_context_8_0= ruleContextType ) )*
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:92:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:93:5: 
            {
             
                    temp=factory.create(grammarAccess.getProcessTypeAccess().getProcessTypeAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getProcessTypeAccess().getProcessTypeAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:103:2: ( 'addParameters' '{' ( (lv_parameters_3_0= ruleParametersType ) ) '}' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0==11) ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:103:4: 'addParameters' '{' ( (lv_parameters_3_0= ruleParametersType ) ) '}'
                    {
                    match(input,11,FOLLOW_11_in_ruleProcessType130); 

                            createLeafNode(grammarAccess.getProcessTypeAccess().getAddParametersKeyword_1_0(), null); 
                        
                    match(input,12,FOLLOW_12_in_ruleProcessType140); 

                            createLeafNode(grammarAccess.getProcessTypeAccess().getLeftCurlyBracketKeyword_1_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:111:1: ( (lv_parameters_3_0= ruleParametersType ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:112:1: (lv_parameters_3_0= ruleParametersType )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:112:1: (lv_parameters_3_0= ruleParametersType )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:113:3: lv_parameters_3_0= ruleParametersType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getProcessTypeAccess().getParametersParametersTypeParserRuleCall_1_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleParametersType_in_ruleProcessType161);
                    lv_parameters_3_0=ruleParametersType();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getProcessTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"parameters",
                    	        		lv_parameters_3_0, 
                    	        		"ParametersType", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    match(input,13,FOLLOW_13_in_ruleProcessType171); 

                            createLeafNode(grammarAccess.getProcessTypeAccess().getRightCurlyBracketKeyword_1_3(), null); 
                        

                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:139:3: ( (lv_node_5_0= ruleNodeType ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==45) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:140:1: (lv_node_5_0= ruleNodeType )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:140:1: (lv_node_5_0= ruleNodeType )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:141:3: lv_node_5_0= ruleNodeType
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getProcessTypeAccess().getNodeNodeTypeParserRuleCall_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleNodeType_in_ruleProcessType194);
            	    lv_node_5_0=ruleNodeType();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getProcessTypeRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"node",
            	    	        		lv_node_5_0, 
            	    	        		"NodeType", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:163:3: ( (lv_connection_6_0= ruleConnectionType ) )*
            loop3:
            do {
                int alt3=2;
                int LA3_0 = input.LA(1);

                if ( (LA3_0==57) ) {
                    alt3=1;
                }


                switch (alt3) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:164:1: (lv_connection_6_0= ruleConnectionType )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:164:1: (lv_connection_6_0= ruleConnectionType )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:165:3: lv_connection_6_0= ruleConnectionType
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getProcessTypeAccess().getConnectionConnectionTypeParserRuleCall_3_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleConnectionType_in_ruleProcessType216);
            	    lv_connection_6_0=ruleConnectionType();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getProcessTypeRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"connection",
            	    	        		lv_connection_6_0, 
            	    	        		"ConnectionType", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop3;
                }
            } while (true);

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:187:3: ( (lv_note_7_0= ruleNoteType ) )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( (LA4_0==64) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:188:1: (lv_note_7_0= ruleNoteType )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:188:1: (lv_note_7_0= ruleNoteType )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:189:3: lv_note_7_0= ruleNoteType
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getProcessTypeAccess().getNoteNoteTypeParserRuleCall_4_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleNoteType_in_ruleProcessType238);
            	    lv_note_7_0=ruleNoteType();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getProcessTypeRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"note",
            	    	        		lv_note_7_0, 
            	    	        		"NoteType", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:211:3: ( (lv_context_8_0= ruleContextType ) )*
            loop5:
            do {
                int alt5=2;
                int LA5_0 = input.LA(1);

                if ( (LA5_0==49) ) {
                    alt5=1;
                }


                switch (alt5) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:212:1: (lv_context_8_0= ruleContextType )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:212:1: (lv_context_8_0= ruleContextType )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:213:3: lv_context_8_0= ruleContextType
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getProcessTypeAccess().getContextContextTypeParserRuleCall_5_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleContextType_in_ruleProcessType260);
            	    lv_context_8_0=ruleContextType();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getProcessTypeRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"context",
            	    	        		lv_context_8_0, 
            	    	        		"ContextType", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop5;
                }
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleProcessType


    // $ANTLR start entryRuleParametersType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:243:1: entryRuleParametersType returns [EObject current=null] : iv_ruleParametersType= ruleParametersType EOF ;
    public final EObject entryRuleParametersType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleParametersType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:244:2: (iv_ruleParametersType= ruleParametersType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:245:2: iv_ruleParametersType= ruleParametersType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getParametersTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleParametersType_in_entryRuleParametersType297);
            iv_ruleParametersType=ruleParametersType();
            _fsp--;

             current =iv_ruleParametersType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleParametersType307); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleParametersType


    // $ANTLR start ruleParametersType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:252:1: ruleParametersType returns [EObject current=null] : ( ( (lv_elementParameter_0_0= ruleElementParameterType ) ) ( ',' ( (lv_elementParameter_2_0= ruleElementParameterType ) ) )* ) ;
    public final EObject ruleParametersType() throws RecognitionException {
        EObject current = null;

        EObject lv_elementParameter_0_0 = null;

        EObject lv_elementParameter_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:257:6: ( ( ( (lv_elementParameter_0_0= ruleElementParameterType ) ) ( ',' ( (lv_elementParameter_2_0= ruleElementParameterType ) ) )* ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:258:1: ( ( (lv_elementParameter_0_0= ruleElementParameterType ) ) ( ',' ( (lv_elementParameter_2_0= ruleElementParameterType ) ) )* )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:258:1: ( ( (lv_elementParameter_0_0= ruleElementParameterType ) ) ( ',' ( (lv_elementParameter_2_0= ruleElementParameterType ) ) )* )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:258:2: ( (lv_elementParameter_0_0= ruleElementParameterType ) ) ( ',' ( (lv_elementParameter_2_0= ruleElementParameterType ) ) )*
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:258:2: ( (lv_elementParameter_0_0= ruleElementParameterType ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:259:1: (lv_elementParameter_0_0= ruleElementParameterType )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:259:1: (lv_elementParameter_0_0= ruleElementParameterType )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:260:3: lv_elementParameter_0_0= ruleElementParameterType
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getParametersTypeAccess().getElementParameterElementParameterTypeParserRuleCall_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleElementParameterType_in_ruleParametersType353);
            lv_elementParameter_0_0=ruleElementParameterType();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getParametersTypeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		add(
            	       			current, 
            	       			"elementParameter",
            	        		lv_elementParameter_0_0, 
            	        		"ElementParameterType", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:282:2: ( ',' ( (lv_elementParameter_2_0= ruleElementParameterType ) ) )*
            loop6:
            do {
                int alt6=2;
                int LA6_0 = input.LA(1);

                if ( (LA6_0==14) ) {
                    alt6=1;
                }


                switch (alt6) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:282:4: ',' ( (lv_elementParameter_2_0= ruleElementParameterType ) )
            	    {
            	    match(input,14,FOLLOW_14_in_ruleParametersType364); 

            	            createLeafNode(grammarAccess.getParametersTypeAccess().getCommaKeyword_1_0(), null); 
            	        
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:286:1: ( (lv_elementParameter_2_0= ruleElementParameterType ) )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:287:1: (lv_elementParameter_2_0= ruleElementParameterType )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:287:1: (lv_elementParameter_2_0= ruleElementParameterType )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:288:3: lv_elementParameter_2_0= ruleElementParameterType
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getParametersTypeAccess().getElementParameterElementParameterTypeParserRuleCall_1_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleElementParameterType_in_ruleParametersType385);
            	    lv_elementParameter_2_0=ruleElementParameterType();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getParametersTypeRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"elementParameter",
            	    	        		lv_elementParameter_2_0, 
            	    	        		"ElementParameterType", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop6;
                }
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleParametersType


    // $ANTLR start entryRuleExternalData
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:318:1: entryRuleExternalData returns [EObject current=null] : iv_ruleExternalData= ruleExternalData EOF ;
    public final EObject entryRuleExternalData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleExternalData = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:319:2: (iv_ruleExternalData= ruleExternalData EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:320:2: iv_ruleExternalData= ruleExternalData EOF
            {
             currentNode = createCompositeNode(grammarAccess.getExternalDataRule(), currentNode); 
            pushFollow(FOLLOW_ruleExternalData_in_entryRuleExternalData423);
            iv_ruleExternalData=ruleExternalData();
            _fsp--;

             current =iv_ruleExternalData; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleExternalData433); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleExternalData


    // $ANTLR start ruleExternalData
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:327:1: ruleExternalData returns [EObject current=null] : (this_MapperData_0= ruleMapperData | this_DBMapperData_1= ruleDBMapperData ) ;
    public final EObject ruleExternalData() throws RecognitionException {
        EObject current = null;

        EObject this_MapperData_0 = null;

        EObject this_DBMapperData_1 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:332:6: ( (this_MapperData_0= ruleMapperData | this_DBMapperData_1= ruleDBMapperData ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:333:1: (this_MapperData_0= ruleMapperData | this_DBMapperData_1= ruleDBMapperData )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:333:1: (this_MapperData_0= ruleMapperData | this_DBMapperData_1= ruleDBMapperData )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0==32) ) {
                alt7=1;
            }
            else if ( (LA7_0==15) ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("333:1: (this_MapperData_0= ruleMapperData | this_DBMapperData_1= ruleDBMapperData )", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:334:5: this_MapperData_0= ruleMapperData
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getExternalDataAccess().getMapperDataParserRuleCall_0(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleMapperData_in_ruleExternalData480);
                    this_MapperData_0=ruleMapperData();
                    _fsp--;

                     
                            current = this_MapperData_0; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;
                case 2 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:344:5: this_DBMapperData_1= ruleDBMapperData
                    {
                     
                            currentNode=createCompositeNode(grammarAccess.getExternalDataAccess().getDBMapperDataParserRuleCall_1(), currentNode); 
                        
                    pushFollow(FOLLOW_ruleDBMapperData_in_ruleExternalData507);
                    this_DBMapperData_1=ruleDBMapperData();
                    _fsp--;

                     
                            current = this_DBMapperData_1; 
                            currentNode = currentNode.getParent();
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleExternalData


    // $ANTLR start entryRuleDBMapperData
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:360:1: entryRuleDBMapperData returns [EObject current=null] : iv_ruleDBMapperData= ruleDBMapperData EOF ;
    public final EObject entryRuleDBMapperData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDBMapperData = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:361:2: (iv_ruleDBMapperData= ruleDBMapperData EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:362:2: iv_ruleDBMapperData= ruleDBMapperData EOF
            {
             currentNode = createCompositeNode(grammarAccess.getDBMapperDataRule(), currentNode); 
            pushFollow(FOLLOW_ruleDBMapperData_in_entryRuleDBMapperData542);
            iv_ruleDBMapperData=ruleDBMapperData();
            _fsp--;

             current =iv_ruleDBMapperData; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDBMapperData552); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleDBMapperData


    // $ANTLR start ruleDBMapperData
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:369:1: ruleDBMapperData returns [EObject current=null] : ( () 'addDBMapData' '{' ( 'addInputTable' '{' ( (lv_InputTables_5_0= ruleDBInputTable ) ) '}' )* ( 'addVarTable' '{' ( (lv_VarTables_9_0= ruleDBVarTable ) ) '}' )* ( 'addOutputTable' '{' ( (lv_OutputTables_13_0= ruleDBOutputTable ) ) '}' )* '}' ) ;
    public final EObject ruleDBMapperData() throws RecognitionException {
        EObject current = null;

        EObject lv_InputTables_5_0 = null;

        EObject lv_VarTables_9_0 = null;

        EObject lv_OutputTables_13_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:374:6: ( ( () 'addDBMapData' '{' ( 'addInputTable' '{' ( (lv_InputTables_5_0= ruleDBInputTable ) ) '}' )* ( 'addVarTable' '{' ( (lv_VarTables_9_0= ruleDBVarTable ) ) '}' )* ( 'addOutputTable' '{' ( (lv_OutputTables_13_0= ruleDBOutputTable ) ) '}' )* '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:375:1: ( () 'addDBMapData' '{' ( 'addInputTable' '{' ( (lv_InputTables_5_0= ruleDBInputTable ) ) '}' )* ( 'addVarTable' '{' ( (lv_VarTables_9_0= ruleDBVarTable ) ) '}' )* ( 'addOutputTable' '{' ( (lv_OutputTables_13_0= ruleDBOutputTable ) ) '}' )* '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:375:1: ( () 'addDBMapData' '{' ( 'addInputTable' '{' ( (lv_InputTables_5_0= ruleDBInputTable ) ) '}' )* ( 'addVarTable' '{' ( (lv_VarTables_9_0= ruleDBVarTable ) ) '}' )* ( 'addOutputTable' '{' ( (lv_OutputTables_13_0= ruleDBOutputTable ) ) '}' )* '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:375:2: () 'addDBMapData' '{' ( 'addInputTable' '{' ( (lv_InputTables_5_0= ruleDBInputTable ) ) '}' )* ( 'addVarTable' '{' ( (lv_VarTables_9_0= ruleDBVarTable ) ) '}' )* ( 'addOutputTable' '{' ( (lv_OutputTables_13_0= ruleDBOutputTable ) ) '}' )* '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:375:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:376:5: 
            {
             
                    temp=factory.create(grammarAccess.getDBMapperDataAccess().getDBMapDataAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getDBMapperDataAccess().getDBMapDataAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,15,FOLLOW_15_in_ruleDBMapperData596); 

                    createLeafNode(grammarAccess.getDBMapperDataAccess().getAddDBMapDataKeyword_1(), null); 
                
            match(input,12,FOLLOW_12_in_ruleDBMapperData606); 

                    createLeafNode(grammarAccess.getDBMapperDataAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:394:1: ( 'addInputTable' '{' ( (lv_InputTables_5_0= ruleDBInputTable ) ) '}' )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0==16) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:394:3: 'addInputTable' '{' ( (lv_InputTables_5_0= ruleDBInputTable ) ) '}'
            	    {
            	    match(input,16,FOLLOW_16_in_ruleDBMapperData617); 

            	            createLeafNode(grammarAccess.getDBMapperDataAccess().getAddInputTableKeyword_3_0(), null); 
            	        
            	    match(input,12,FOLLOW_12_in_ruleDBMapperData627); 

            	            createLeafNode(grammarAccess.getDBMapperDataAccess().getLeftCurlyBracketKeyword_3_1(), null); 
            	        
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:402:1: ( (lv_InputTables_5_0= ruleDBInputTable ) )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:403:1: (lv_InputTables_5_0= ruleDBInputTable )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:403:1: (lv_InputTables_5_0= ruleDBInputTable )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:404:3: lv_InputTables_5_0= ruleDBInputTable
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getDBMapperDataAccess().getInputTablesDBInputTableParserRuleCall_3_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleDBInputTable_in_ruleDBMapperData648);
            	    lv_InputTables_5_0=ruleDBInputTable();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getDBMapperDataRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"InputTables",
            	    	        		lv_InputTables_5_0, 
            	    	        		"DBInputTable", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }

            	    match(input,13,FOLLOW_13_in_ruleDBMapperData658); 

            	            createLeafNode(grammarAccess.getDBMapperDataAccess().getRightCurlyBracketKeyword_3_3(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:430:3: ( 'addVarTable' '{' ( (lv_VarTables_9_0= ruleDBVarTable ) ) '}' )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( (LA9_0==17) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:430:5: 'addVarTable' '{' ( (lv_VarTables_9_0= ruleDBVarTable ) ) '}'
            	    {
            	    match(input,17,FOLLOW_17_in_ruleDBMapperData671); 

            	            createLeafNode(grammarAccess.getDBMapperDataAccess().getAddVarTableKeyword_4_0(), null); 
            	        
            	    match(input,12,FOLLOW_12_in_ruleDBMapperData681); 

            	            createLeafNode(grammarAccess.getDBMapperDataAccess().getLeftCurlyBracketKeyword_4_1(), null); 
            	        
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:438:1: ( (lv_VarTables_9_0= ruleDBVarTable ) )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:439:1: (lv_VarTables_9_0= ruleDBVarTable )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:439:1: (lv_VarTables_9_0= ruleDBVarTable )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:440:3: lv_VarTables_9_0= ruleDBVarTable
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getDBMapperDataAccess().getVarTablesDBVarTableParserRuleCall_4_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleDBVarTable_in_ruleDBMapperData702);
            	    lv_VarTables_9_0=ruleDBVarTable();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getDBMapperDataRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"VarTables",
            	    	        		lv_VarTables_9_0, 
            	    	        		"DBVarTable", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }

            	    match(input,13,FOLLOW_13_in_ruleDBMapperData712); 

            	            createLeafNode(grammarAccess.getDBMapperDataAccess().getRightCurlyBracketKeyword_4_3(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:466:3: ( 'addOutputTable' '{' ( (lv_OutputTables_13_0= ruleDBOutputTable ) ) '}' )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==18) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:466:5: 'addOutputTable' '{' ( (lv_OutputTables_13_0= ruleDBOutputTable ) ) '}'
            	    {
            	    match(input,18,FOLLOW_18_in_ruleDBMapperData725); 

            	            createLeafNode(grammarAccess.getDBMapperDataAccess().getAddOutputTableKeyword_5_0(), null); 
            	        
            	    match(input,12,FOLLOW_12_in_ruleDBMapperData735); 

            	            createLeafNode(grammarAccess.getDBMapperDataAccess().getLeftCurlyBracketKeyword_5_1(), null); 
            	        
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:474:1: ( (lv_OutputTables_13_0= ruleDBOutputTable ) )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:475:1: (lv_OutputTables_13_0= ruleDBOutputTable )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:475:1: (lv_OutputTables_13_0= ruleDBOutputTable )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:476:3: lv_OutputTables_13_0= ruleDBOutputTable
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getDBMapperDataAccess().getOutputTablesDBOutputTableParserRuleCall_5_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleDBOutputTable_in_ruleDBMapperData756);
            	    lv_OutputTables_13_0=ruleDBOutputTable();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getDBMapperDataRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"OutputTables",
            	    	        		lv_OutputTables_13_0, 
            	    	        		"DBOutputTable", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }

            	    match(input,13,FOLLOW_13_in_ruleDBMapperData766); 

            	            createLeafNode(grammarAccess.getDBMapperDataAccess().getRightCurlyBracketKeyword_5_3(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop10;
                }
            } while (true);

            match(input,13,FOLLOW_13_in_ruleDBMapperData778); 

                    createLeafNode(grammarAccess.getDBMapperDataAccess().getRightCurlyBracketKeyword_6(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleDBMapperData


    // $ANTLR start entryRuleDBInputTable
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:514:1: entryRuleDBInputTable returns [EObject current=null] : iv_ruleDBInputTable= ruleDBInputTable EOF ;
    public final EObject entryRuleDBInputTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDBInputTable = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:515:2: (iv_ruleDBInputTable= ruleDBInputTable EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:516:2: iv_ruleDBInputTable= ruleDBInputTable EOF
            {
             currentNode = createCompositeNode(grammarAccess.getDBInputTableRule(), currentNode); 
            pushFollow(FOLLOW_ruleDBInputTable_in_entryRuleDBInputTable814);
            iv_ruleDBInputTable=ruleDBInputTable();
            _fsp--;

             current =iv_ruleDBInputTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDBInputTable824); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleDBInputTable


    // $ANTLR start ruleDBInputTable
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:523:1: ruleDBInputTable returns [EObject current=null] : ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( 'TABLENAME:' ( (lv_tableName_8_0= ruleEString ) ) )? ( 'JOINTYPE:' ( (lv_joinType_10_0= ruleEString ) ) )? ( 'ALIAS:' ( (lv_alias_12_0= ruleEString ) ) )? ( (lv_DBMapperTableEntries_13_0= ruleDBMapperTableEntry ) )* ) ;
    public final EObject ruleDBInputTable() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_minimized_4_0 = null;

        AntlrDatatypeRuleToken lv_readonly_6_0 = null;

        AntlrDatatypeRuleToken lv_tableName_8_0 = null;

        AntlrDatatypeRuleToken lv_joinType_10_0 = null;

        AntlrDatatypeRuleToken lv_alias_12_0 = null;

        EObject lv_DBMapperTableEntries_13_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:528:6: ( ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( 'TABLENAME:' ( (lv_tableName_8_0= ruleEString ) ) )? ( 'JOINTYPE:' ( (lv_joinType_10_0= ruleEString ) ) )? ( 'ALIAS:' ( (lv_alias_12_0= ruleEString ) ) )? ( (lv_DBMapperTableEntries_13_0= ruleDBMapperTableEntry ) )* ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:529:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( 'TABLENAME:' ( (lv_tableName_8_0= ruleEString ) ) )? ( 'JOINTYPE:' ( (lv_joinType_10_0= ruleEString ) ) )? ( 'ALIAS:' ( (lv_alias_12_0= ruleEString ) ) )? ( (lv_DBMapperTableEntries_13_0= ruleDBMapperTableEntry ) )* )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:529:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( 'TABLENAME:' ( (lv_tableName_8_0= ruleEString ) ) )? ( 'JOINTYPE:' ( (lv_joinType_10_0= ruleEString ) ) )? ( 'ALIAS:' ( (lv_alias_12_0= ruleEString ) ) )? ( (lv_DBMapperTableEntries_13_0= ruleDBMapperTableEntry ) )* )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:529:2: () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( 'TABLENAME:' ( (lv_tableName_8_0= ruleEString ) ) )? ( 'JOINTYPE:' ( (lv_joinType_10_0= ruleEString ) ) )? ( 'ALIAS:' ( (lv_alias_12_0= ruleEString ) ) )? ( (lv_DBMapperTableEntries_13_0= ruleDBMapperTableEntry ) )*
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:529:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:530:5: 
            {
             
                    temp=factory.create(grammarAccess.getDBInputTableAccess().getInputTableAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getDBInputTableAccess().getInputTableAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,19,FOLLOW_19_in_ruleDBInputTable868); 

                    createLeafNode(grammarAccess.getDBInputTableAccess().getNAMEKeyword_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:544:1: ( (lv_name_2_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:545:1: (lv_name_2_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:545:1: (lv_name_2_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:546:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getDBInputTableAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleDBInputTable889);
            lv_name_2_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getDBInputTableRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_2_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:568:2: ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0==20) ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:568:4: 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) )
                    {
                    match(input,20,FOLLOW_20_in_ruleDBInputTable900); 

                            createLeafNode(grammarAccess.getDBInputTableAccess().getMINIMIZEDKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:572:1: ( (lv_minimized_4_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:573:1: (lv_minimized_4_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:573:1: (lv_minimized_4_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:574:3: lv_minimized_4_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBInputTableAccess().getMinimizedEBooleanParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleDBInputTable921);
                    lv_minimized_4_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDBInputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"minimized",
                    	        		lv_minimized_4_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:596:4: ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==21) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:596:6: 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) )
                    {
                    match(input,21,FOLLOW_21_in_ruleDBInputTable934); 

                            createLeafNode(grammarAccess.getDBInputTableAccess().getREADONLYKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:600:1: ( (lv_readonly_6_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:601:1: (lv_readonly_6_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:601:1: (lv_readonly_6_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:602:3: lv_readonly_6_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBInputTableAccess().getReadonlyEBooleanParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleDBInputTable955);
                    lv_readonly_6_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDBInputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"readonly",
                    	        		lv_readonly_6_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:624:4: ( 'TABLENAME:' ( (lv_tableName_8_0= ruleEString ) ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==22) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:624:6: 'TABLENAME:' ( (lv_tableName_8_0= ruleEString ) )
                    {
                    match(input,22,FOLLOW_22_in_ruleDBInputTable968); 

                            createLeafNode(grammarAccess.getDBInputTableAccess().getTABLENAMEKeyword_5_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:628:1: ( (lv_tableName_8_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:629:1: (lv_tableName_8_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:629:1: (lv_tableName_8_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:630:3: lv_tableName_8_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBInputTableAccess().getTableNameEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleDBInputTable989);
                    lv_tableName_8_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDBInputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"tableName",
                    	        		lv_tableName_8_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:652:4: ( 'JOINTYPE:' ( (lv_joinType_10_0= ruleEString ) ) )?
            int alt14=2;
            int LA14_0 = input.LA(1);

            if ( (LA14_0==23) ) {
                alt14=1;
            }
            switch (alt14) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:652:6: 'JOINTYPE:' ( (lv_joinType_10_0= ruleEString ) )
                    {
                    match(input,23,FOLLOW_23_in_ruleDBInputTable1002); 

                            createLeafNode(grammarAccess.getDBInputTableAccess().getJOINTYPEKeyword_6_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:656:1: ( (lv_joinType_10_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:657:1: (lv_joinType_10_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:657:1: (lv_joinType_10_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:658:3: lv_joinType_10_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBInputTableAccess().getJoinTypeEStringParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleDBInputTable1023);
                    lv_joinType_10_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDBInputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"joinType",
                    	        		lv_joinType_10_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:680:4: ( 'ALIAS:' ( (lv_alias_12_0= ruleEString ) ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==24) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:680:6: 'ALIAS:' ( (lv_alias_12_0= ruleEString ) )
                    {
                    match(input,24,FOLLOW_24_in_ruleDBInputTable1036); 

                            createLeafNode(grammarAccess.getDBInputTableAccess().getALIASKeyword_7_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:684:1: ( (lv_alias_12_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:685:1: (lv_alias_12_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:685:1: (lv_alias_12_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:686:3: lv_alias_12_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBInputTableAccess().getAliasEStringParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleDBInputTable1057);
                    lv_alias_12_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDBInputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"alias",
                    	        		lv_alias_12_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:708:4: ( (lv_DBMapperTableEntries_13_0= ruleDBMapperTableEntry ) )*
            loop16:
            do {
                int alt16=2;
                int LA16_0 = input.LA(1);

                if ( (LA16_0==25) ) {
                    alt16=1;
                }


                switch (alt16) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:709:1: (lv_DBMapperTableEntries_13_0= ruleDBMapperTableEntry )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:709:1: (lv_DBMapperTableEntries_13_0= ruleDBMapperTableEntry )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:710:3: lv_DBMapperTableEntries_13_0= ruleDBMapperTableEntry
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getDBInputTableAccess().getDBMapperTableEntriesDBMapperTableEntryParserRuleCall_8_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleDBMapperTableEntry_in_ruleDBInputTable1080);
            	    lv_DBMapperTableEntries_13_0=ruleDBMapperTableEntry();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getDBInputTableRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"DBMapperTableEntries",
            	    	        		lv_DBMapperTableEntries_13_0, 
            	    	        		"DBMapperTableEntry", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop16;
                }
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleDBInputTable


    // $ANTLR start entryRuleDBVarTable
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:740:1: entryRuleDBVarTable returns [EObject current=null] : iv_ruleDBVarTable= ruleDBVarTable EOF ;
    public final EObject entryRuleDBVarTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDBVarTable = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:741:2: (iv_ruleDBVarTable= ruleDBVarTable EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:742:2: iv_ruleDBVarTable= ruleDBVarTable EOF
            {
             currentNode = createCompositeNode(grammarAccess.getDBVarTableRule(), currentNode); 
            pushFollow(FOLLOW_ruleDBVarTable_in_entryRuleDBVarTable1117);
            iv_ruleDBVarTable=ruleDBVarTable();
            _fsp--;

             current =iv_ruleDBVarTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDBVarTable1127); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleDBVarTable


    // $ANTLR start ruleDBVarTable
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:749:1: ruleDBVarTable returns [EObject current=null] : ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )* ) ;
    public final EObject ruleDBVarTable() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_minimized_4_0 = null;

        AntlrDatatypeRuleToken lv_readonly_6_0 = null;

        EObject lv_DBMapperTableEntries_7_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:754:6: ( ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )* ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:755:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )* )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:755:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )* )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:755:2: () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )*
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:755:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:756:5: 
            {
             
                    temp=factory.create(grammarAccess.getDBVarTableAccess().getVarTableAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getDBVarTableAccess().getVarTableAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,19,FOLLOW_19_in_ruleDBVarTable1171); 

                    createLeafNode(grammarAccess.getDBVarTableAccess().getNAMEKeyword_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:770:1: ( (lv_name_2_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:771:1: (lv_name_2_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:771:1: (lv_name_2_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:772:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getDBVarTableAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleDBVarTable1192);
            lv_name_2_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getDBVarTableRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_2_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:794:2: ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==20) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:794:4: 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) )
                    {
                    match(input,20,FOLLOW_20_in_ruleDBVarTable1203); 

                            createLeafNode(grammarAccess.getDBVarTableAccess().getMINIMIZEDKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:798:1: ( (lv_minimized_4_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:799:1: (lv_minimized_4_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:799:1: (lv_minimized_4_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:800:3: lv_minimized_4_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBVarTableAccess().getMinimizedEBooleanParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleDBVarTable1224);
                    lv_minimized_4_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDBVarTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"minimized",
                    	        		lv_minimized_4_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:822:4: ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==21) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:822:6: 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) )
                    {
                    match(input,21,FOLLOW_21_in_ruleDBVarTable1237); 

                            createLeafNode(grammarAccess.getDBVarTableAccess().getREADONLYKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:826:1: ( (lv_readonly_6_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:827:1: (lv_readonly_6_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:827:1: (lv_readonly_6_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:828:3: lv_readonly_6_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBVarTableAccess().getReadonlyEBooleanParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleDBVarTable1258);
                    lv_readonly_6_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDBVarTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"readonly",
                    	        		lv_readonly_6_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:850:4: ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )*
            loop19:
            do {
                int alt19=2;
                int LA19_0 = input.LA(1);

                if ( (LA19_0==25) ) {
                    alt19=1;
                }


                switch (alt19) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:851:1: (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:851:1: (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:852:3: lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getDBVarTableAccess().getDBMapperTableEntriesDBMapperTableEntryParserRuleCall_5_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleDBMapperTableEntry_in_ruleDBVarTable1281);
            	    lv_DBMapperTableEntries_7_0=ruleDBMapperTableEntry();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getDBVarTableRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"DBMapperTableEntries",
            	    	        		lv_DBMapperTableEntries_7_0, 
            	    	        		"DBMapperTableEntry", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop19;
                }
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleDBVarTable


    // $ANTLR start entryRuleDBOutputTable
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:882:1: entryRuleDBOutputTable returns [EObject current=null] : iv_ruleDBOutputTable= ruleDBOutputTable EOF ;
    public final EObject entryRuleDBOutputTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDBOutputTable = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:883:2: (iv_ruleDBOutputTable= ruleDBOutputTable EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:884:2: iv_ruleDBOutputTable= ruleDBOutputTable EOF
            {
             currentNode = createCompositeNode(grammarAccess.getDBOutputTableRule(), currentNode); 
            pushFollow(FOLLOW_ruleDBOutputTable_in_entryRuleDBOutputTable1318);
            iv_ruleDBOutputTable=ruleDBOutputTable();
            _fsp--;

             current =iv_ruleDBOutputTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDBOutputTable1328); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleDBOutputTable


    // $ANTLR start ruleDBOutputTable
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:891:1: ruleDBOutputTable returns [EObject current=null] : ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )* ( (lv_FilterEntries_8_0= ruleDBFilterEntry ) )* ) ;
    public final EObject ruleDBOutputTable() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_minimized_4_0 = null;

        AntlrDatatypeRuleToken lv_readonly_6_0 = null;

        EObject lv_DBMapperTableEntries_7_0 = null;

        EObject lv_FilterEntries_8_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:896:6: ( ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )* ( (lv_FilterEntries_8_0= ruleDBFilterEntry ) )* ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:897:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )* ( (lv_FilterEntries_8_0= ruleDBFilterEntry ) )* )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:897:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )* ( (lv_FilterEntries_8_0= ruleDBFilterEntry ) )* )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:897:2: () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )* ( (lv_FilterEntries_8_0= ruleDBFilterEntry ) )*
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:897:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:898:5: 
            {
             
                    temp=factory.create(grammarAccess.getDBOutputTableAccess().getOutputTableAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getDBOutputTableAccess().getOutputTableAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,19,FOLLOW_19_in_ruleDBOutputTable1372); 

                    createLeafNode(grammarAccess.getDBOutputTableAccess().getNAMEKeyword_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:912:1: ( (lv_name_2_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:913:1: (lv_name_2_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:913:1: (lv_name_2_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:914:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getDBOutputTableAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleDBOutputTable1393);
            lv_name_2_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getDBOutputTableRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_2_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:936:2: ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==20) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:936:4: 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) )
                    {
                    match(input,20,FOLLOW_20_in_ruleDBOutputTable1404); 

                            createLeafNode(grammarAccess.getDBOutputTableAccess().getMINIMIZEDKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:940:1: ( (lv_minimized_4_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:941:1: (lv_minimized_4_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:941:1: (lv_minimized_4_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:942:3: lv_minimized_4_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBOutputTableAccess().getMinimizedEBooleanParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleDBOutputTable1425);
                    lv_minimized_4_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDBOutputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"minimized",
                    	        		lv_minimized_4_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:964:4: ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==21) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:964:6: 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) )
                    {
                    match(input,21,FOLLOW_21_in_ruleDBOutputTable1438); 

                            createLeafNode(grammarAccess.getDBOutputTableAccess().getREADONLYKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:968:1: ( (lv_readonly_6_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:969:1: (lv_readonly_6_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:969:1: (lv_readonly_6_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:970:3: lv_readonly_6_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBOutputTableAccess().getReadonlyEBooleanParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleDBOutputTable1459);
                    lv_readonly_6_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDBOutputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"readonly",
                    	        		lv_readonly_6_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:992:4: ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )*
            loop22:
            do {
                int alt22=2;
                int LA22_0 = input.LA(1);

                if ( (LA22_0==25) ) {
                    alt22=1;
                }


                switch (alt22) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:993:1: (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:993:1: (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:994:3: lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getDBOutputTableAccess().getDBMapperTableEntriesDBMapperTableEntryParserRuleCall_5_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleDBMapperTableEntry_in_ruleDBOutputTable1482);
            	    lv_DBMapperTableEntries_7_0=ruleDBMapperTableEntry();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getDBOutputTableRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"DBMapperTableEntries",
            	    	        		lv_DBMapperTableEntries_7_0, 
            	    	        		"DBMapperTableEntry", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop22;
                }
            } while (true);

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1016:3: ( (lv_FilterEntries_8_0= ruleDBFilterEntry ) )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==31) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1017:1: (lv_FilterEntries_8_0= ruleDBFilterEntry )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1017:1: (lv_FilterEntries_8_0= ruleDBFilterEntry )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1018:3: lv_FilterEntries_8_0= ruleDBFilterEntry
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getDBOutputTableAccess().getFilterEntriesDBFilterEntryParserRuleCall_6_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleDBFilterEntry_in_ruleDBOutputTable1504);
            	    lv_FilterEntries_8_0=ruleDBFilterEntry();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getDBOutputTableRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"FilterEntries",
            	    	        		lv_FilterEntries_8_0, 
            	    	        		"DBFilterEntry", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop23;
                }
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleDBOutputTable


    // $ANTLR start entryRuleDBMapperTableEntry
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1048:1: entryRuleDBMapperTableEntry returns [EObject current=null] : iv_ruleDBMapperTableEntry= ruleDBMapperTableEntry EOF ;
    public final EObject entryRuleDBMapperTableEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDBMapperTableEntry = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1049:2: (iv_ruleDBMapperTableEntry= ruleDBMapperTableEntry EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1050:2: iv_ruleDBMapperTableEntry= ruleDBMapperTableEntry EOF
            {
             currentNode = createCompositeNode(grammarAccess.getDBMapperTableEntryRule(), currentNode); 
            pushFollow(FOLLOW_ruleDBMapperTableEntry_in_entryRuleDBMapperTableEntry1541);
            iv_ruleDBMapperTableEntry=ruleDBMapperTableEntry();
            _fsp--;

             current =iv_ruleDBMapperTableEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDBMapperTableEntry1551); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleDBMapperTableEntry


    // $ANTLR start ruleDBMapperTableEntry
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1057:1: ruleDBMapperTableEntry returns [EObject current=null] : ( () 'addColumn' '{' 'NAME:' ( (lv_name_4_0= ruleEString ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )? ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )? ( ',' 'JOIN:' ( (lv_join_16_0= ruleEBoolean ) ) )? ( ',' 'OPERATOR:' ( (lv_operator_19_0= ruleEString ) ) )? '}' ) ;
    public final EObject ruleDBMapperTableEntry() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        AntlrDatatypeRuleToken lv_type_7_0 = null;

        AntlrDatatypeRuleToken lv_nullable_10_0 = null;

        AntlrDatatypeRuleToken lv_expression_13_0 = null;

        AntlrDatatypeRuleToken lv_join_16_0 = null;

        AntlrDatatypeRuleToken lv_operator_19_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1062:6: ( ( () 'addColumn' '{' 'NAME:' ( (lv_name_4_0= ruleEString ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )? ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )? ( ',' 'JOIN:' ( (lv_join_16_0= ruleEBoolean ) ) )? ( ',' 'OPERATOR:' ( (lv_operator_19_0= ruleEString ) ) )? '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1063:1: ( () 'addColumn' '{' 'NAME:' ( (lv_name_4_0= ruleEString ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )? ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )? ( ',' 'JOIN:' ( (lv_join_16_0= ruleEBoolean ) ) )? ( ',' 'OPERATOR:' ( (lv_operator_19_0= ruleEString ) ) )? '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1063:1: ( () 'addColumn' '{' 'NAME:' ( (lv_name_4_0= ruleEString ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )? ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )? ( ',' 'JOIN:' ( (lv_join_16_0= ruleEBoolean ) ) )? ( ',' 'OPERATOR:' ( (lv_operator_19_0= ruleEString ) ) )? '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1063:2: () 'addColumn' '{' 'NAME:' ( (lv_name_4_0= ruleEString ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )? ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )? ( ',' 'JOIN:' ( (lv_join_16_0= ruleEBoolean ) ) )? ( ',' 'OPERATOR:' ( (lv_operator_19_0= ruleEString ) ) )? '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1063:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1064:5: 
            {
             
                    temp=factory.create(grammarAccess.getDBMapperTableEntryAccess().getDBMapperTableEntryAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getDBMapperTableEntryAccess().getDBMapperTableEntryAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,25,FOLLOW_25_in_ruleDBMapperTableEntry1595); 

                    createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getAddColumnKeyword_1(), null); 
                
            match(input,12,FOLLOW_12_in_ruleDBMapperTableEntry1605); 

                    createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            match(input,19,FOLLOW_19_in_ruleDBMapperTableEntry1615); 

                    createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getNAMEKeyword_3(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1086:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1087:1: (lv_name_4_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1087:1: (lv_name_4_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1088:3: lv_name_4_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getDBMapperTableEntryAccess().getNameEStringParserRuleCall_4_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleDBMapperTableEntry1636);
            lv_name_4_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getDBMapperTableEntryRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_4_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1110:2: ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==14) ) {
                int LA24_1 = input.LA(2);

                if ( (LA24_1==26) ) {
                    alt24=1;
                }
            }
            switch (alt24) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1110:4: ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleDBMapperTableEntry1647); 

                            createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getCommaKeyword_5_0(), null); 
                        
                    match(input,26,FOLLOW_26_in_ruleDBMapperTableEntry1657); 

                            createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getTYPEKeyword_5_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1118:1: ( (lv_type_7_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1119:1: (lv_type_7_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1119:1: (lv_type_7_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1120:3: lv_type_7_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBMapperTableEntryAccess().getTypeEStringParserRuleCall_5_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleDBMapperTableEntry1678);
                    lv_type_7_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDBMapperTableEntryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"type",
                    	        		lv_type_7_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1142:4: ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==14) ) {
                int LA25_1 = input.LA(2);

                if ( (LA25_1==27) ) {
                    alt25=1;
                }
            }
            switch (alt25) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1142:6: ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleDBMapperTableEntry1691); 

                            createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getCommaKeyword_6_0(), null); 
                        
                    match(input,27,FOLLOW_27_in_ruleDBMapperTableEntry1701); 

                            createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getNULLABLEKeyword_6_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1150:1: ( (lv_nullable_10_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1151:1: (lv_nullable_10_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1151:1: (lv_nullable_10_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1152:3: lv_nullable_10_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBMapperTableEntryAccess().getNullableEBooleanParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleDBMapperTableEntry1722);
                    lv_nullable_10_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDBMapperTableEntryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"nullable",
                    	        		lv_nullable_10_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1174:4: ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==14) ) {
                int LA26_1 = input.LA(2);

                if ( (LA26_1==28) ) {
                    alt26=1;
                }
            }
            switch (alt26) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1174:6: ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleDBMapperTableEntry1735); 

                            createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getCommaKeyword_7_0(), null); 
                        
                    match(input,28,FOLLOW_28_in_ruleDBMapperTableEntry1745); 

                            createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getEXPRESSIONKeyword_7_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1182:1: ( (lv_expression_13_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1183:1: (lv_expression_13_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1183:1: (lv_expression_13_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1184:3: lv_expression_13_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBMapperTableEntryAccess().getExpressionEStringParserRuleCall_7_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleDBMapperTableEntry1766);
                    lv_expression_13_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDBMapperTableEntryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"expression",
                    	        		lv_expression_13_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1206:4: ( ',' 'JOIN:' ( (lv_join_16_0= ruleEBoolean ) ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==14) ) {
                int LA27_1 = input.LA(2);

                if ( (LA27_1==29) ) {
                    alt27=1;
                }
            }
            switch (alt27) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1206:6: ',' 'JOIN:' ( (lv_join_16_0= ruleEBoolean ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleDBMapperTableEntry1779); 

                            createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getCommaKeyword_8_0(), null); 
                        
                    match(input,29,FOLLOW_29_in_ruleDBMapperTableEntry1789); 

                            createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getJOINKeyword_8_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1214:1: ( (lv_join_16_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1215:1: (lv_join_16_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1215:1: (lv_join_16_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1216:3: lv_join_16_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBMapperTableEntryAccess().getJoinEBooleanParserRuleCall_8_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleDBMapperTableEntry1810);
                    lv_join_16_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDBMapperTableEntryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"join",
                    	        		lv_join_16_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1238:4: ( ',' 'OPERATOR:' ( (lv_operator_19_0= ruleEString ) ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==14) ) {
                alt28=1;
            }
            switch (alt28) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1238:6: ',' 'OPERATOR:' ( (lv_operator_19_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleDBMapperTableEntry1823); 

                            createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getCommaKeyword_9_0(), null); 
                        
                    match(input,30,FOLLOW_30_in_ruleDBMapperTableEntry1833); 

                            createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getOPERATORKeyword_9_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1246:1: ( (lv_operator_19_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1247:1: (lv_operator_19_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1247:1: (lv_operator_19_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1248:3: lv_operator_19_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBMapperTableEntryAccess().getOperatorEStringParserRuleCall_9_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleDBMapperTableEntry1854);
                    lv_operator_19_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDBMapperTableEntryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"operator",
                    	        		lv_operator_19_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            match(input,13,FOLLOW_13_in_ruleDBMapperTableEntry1866); 

                    createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getRightCurlyBracketKeyword_10(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleDBMapperTableEntry


    // $ANTLR start entryRuleDBFilterEntry
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1282:1: entryRuleDBFilterEntry returns [EObject current=null] : iv_ruleDBFilterEntry= ruleDBFilterEntry EOF ;
    public final EObject entryRuleDBFilterEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDBFilterEntry = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1283:2: (iv_ruleDBFilterEntry= ruleDBFilterEntry EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1284:2: iv_ruleDBFilterEntry= ruleDBFilterEntry EOF
            {
             currentNode = createCompositeNode(grammarAccess.getDBFilterEntryRule(), currentNode); 
            pushFollow(FOLLOW_ruleDBFilterEntry_in_entryRuleDBFilterEntry1902);
            iv_ruleDBFilterEntry=ruleDBFilterEntry();
            _fsp--;

             current =iv_ruleDBFilterEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDBFilterEntry1912); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleDBFilterEntry


    // $ANTLR start ruleDBFilterEntry
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1291:1: ruleDBFilterEntry returns [EObject current=null] : ( 'addFilter' '{' 'NAME:' ( (lv_name_3_0= ruleEString ) ) ',' 'EXPRESSION:' ( (lv_expression_6_0= ruleEString ) ) '}' ) ;
    public final EObject ruleDBFilterEntry() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_3_0 = null;

        AntlrDatatypeRuleToken lv_expression_6_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1296:6: ( ( 'addFilter' '{' 'NAME:' ( (lv_name_3_0= ruleEString ) ) ',' 'EXPRESSION:' ( (lv_expression_6_0= ruleEString ) ) '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1297:1: ( 'addFilter' '{' 'NAME:' ( (lv_name_3_0= ruleEString ) ) ',' 'EXPRESSION:' ( (lv_expression_6_0= ruleEString ) ) '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1297:1: ( 'addFilter' '{' 'NAME:' ( (lv_name_3_0= ruleEString ) ) ',' 'EXPRESSION:' ( (lv_expression_6_0= ruleEString ) ) '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1297:3: 'addFilter' '{' 'NAME:' ( (lv_name_3_0= ruleEString ) ) ',' 'EXPRESSION:' ( (lv_expression_6_0= ruleEString ) ) '}'
            {
            match(input,31,FOLLOW_31_in_ruleDBFilterEntry1947); 

                    createLeafNode(grammarAccess.getDBFilterEntryAccess().getAddFilterKeyword_0(), null); 
                
            match(input,12,FOLLOW_12_in_ruleDBFilterEntry1957); 

                    createLeafNode(grammarAccess.getDBFilterEntryAccess().getLeftCurlyBracketKeyword_1(), null); 
                
            match(input,19,FOLLOW_19_in_ruleDBFilterEntry1967); 

                    createLeafNode(grammarAccess.getDBFilterEntryAccess().getNAMEKeyword_2(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1309:1: ( (lv_name_3_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1310:1: (lv_name_3_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1310:1: (lv_name_3_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1311:3: lv_name_3_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getDBFilterEntryAccess().getNameEStringParserRuleCall_3_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleDBFilterEntry1988);
            lv_name_3_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getDBFilterEntryRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_3_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,14,FOLLOW_14_in_ruleDBFilterEntry1998); 

                    createLeafNode(grammarAccess.getDBFilterEntryAccess().getCommaKeyword_4(), null); 
                
            match(input,28,FOLLOW_28_in_ruleDBFilterEntry2008); 

                    createLeafNode(grammarAccess.getDBFilterEntryAccess().getEXPRESSIONKeyword_5(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1341:1: ( (lv_expression_6_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1342:1: (lv_expression_6_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1342:1: (lv_expression_6_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1343:3: lv_expression_6_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getDBFilterEntryAccess().getExpressionEStringParserRuleCall_6_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleDBFilterEntry2029);
            lv_expression_6_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getDBFilterEntryRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"expression",
            	        		lv_expression_6_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,13,FOLLOW_13_in_ruleDBFilterEntry2039); 

                    createLeafNode(grammarAccess.getDBFilterEntryAccess().getRightCurlyBracketKeyword_7(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleDBFilterEntry


    // $ANTLR start entryRuleMapperData
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1377:1: entryRuleMapperData returns [EObject current=null] : iv_ruleMapperData= ruleMapperData EOF ;
    public final EObject entryRuleMapperData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMapperData = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1378:2: (iv_ruleMapperData= ruleMapperData EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1379:2: iv_ruleMapperData= ruleMapperData EOF
            {
             currentNode = createCompositeNode(grammarAccess.getMapperDataRule(), currentNode); 
            pushFollow(FOLLOW_ruleMapperData_in_entryRuleMapperData2075);
            iv_ruleMapperData=ruleMapperData();
            _fsp--;

             current =iv_ruleMapperData; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMapperData2085); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleMapperData


    // $ANTLR start ruleMapperData
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1386:1: ruleMapperData returns [EObject current=null] : ( () 'addMapperData' '{' ( 'addUiProperties' '{' ( (lv_uiProperties_5_0= ruleUiProperties ) ) '}' )? ( 'addInputTable' '{' ( (lv_inputTables_9_0= ruleInputTable ) ) '}' )* ( 'addVarTable' '{' ( (lv_varTables_13_0= ruleVarTable ) ) '}' )* ( 'addOutputTable' '{' ( (lv_outputTables_17_0= ruleOutputTable ) ) '}' )* '}' ) ;
    public final EObject ruleMapperData() throws RecognitionException {
        EObject current = null;

        EObject lv_uiProperties_5_0 = null;

        EObject lv_inputTables_9_0 = null;

        EObject lv_varTables_13_0 = null;

        EObject lv_outputTables_17_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1391:6: ( ( () 'addMapperData' '{' ( 'addUiProperties' '{' ( (lv_uiProperties_5_0= ruleUiProperties ) ) '}' )? ( 'addInputTable' '{' ( (lv_inputTables_9_0= ruleInputTable ) ) '}' )* ( 'addVarTable' '{' ( (lv_varTables_13_0= ruleVarTable ) ) '}' )* ( 'addOutputTable' '{' ( (lv_outputTables_17_0= ruleOutputTable ) ) '}' )* '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1392:1: ( () 'addMapperData' '{' ( 'addUiProperties' '{' ( (lv_uiProperties_5_0= ruleUiProperties ) ) '}' )? ( 'addInputTable' '{' ( (lv_inputTables_9_0= ruleInputTable ) ) '}' )* ( 'addVarTable' '{' ( (lv_varTables_13_0= ruleVarTable ) ) '}' )* ( 'addOutputTable' '{' ( (lv_outputTables_17_0= ruleOutputTable ) ) '}' )* '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1392:1: ( () 'addMapperData' '{' ( 'addUiProperties' '{' ( (lv_uiProperties_5_0= ruleUiProperties ) ) '}' )? ( 'addInputTable' '{' ( (lv_inputTables_9_0= ruleInputTable ) ) '}' )* ( 'addVarTable' '{' ( (lv_varTables_13_0= ruleVarTable ) ) '}' )* ( 'addOutputTable' '{' ( (lv_outputTables_17_0= ruleOutputTable ) ) '}' )* '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1392:2: () 'addMapperData' '{' ( 'addUiProperties' '{' ( (lv_uiProperties_5_0= ruleUiProperties ) ) '}' )? ( 'addInputTable' '{' ( (lv_inputTables_9_0= ruleInputTable ) ) '}' )* ( 'addVarTable' '{' ( (lv_varTables_13_0= ruleVarTable ) ) '}' )* ( 'addOutputTable' '{' ( (lv_outputTables_17_0= ruleOutputTable ) ) '}' )* '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1392:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1393:5: 
            {
             
                    temp=factory.create(grammarAccess.getMapperDataAccess().getMapperDataAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getMapperDataAccess().getMapperDataAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,32,FOLLOW_32_in_ruleMapperData2129); 

                    createLeafNode(grammarAccess.getMapperDataAccess().getAddMapperDataKeyword_1(), null); 
                
            match(input,12,FOLLOW_12_in_ruleMapperData2139); 

                    createLeafNode(grammarAccess.getMapperDataAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1411:1: ( 'addUiProperties' '{' ( (lv_uiProperties_5_0= ruleUiProperties ) ) '}' )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==33) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1411:3: 'addUiProperties' '{' ( (lv_uiProperties_5_0= ruleUiProperties ) ) '}'
                    {
                    match(input,33,FOLLOW_33_in_ruleMapperData2150); 

                            createLeafNode(grammarAccess.getMapperDataAccess().getAddUiPropertiesKeyword_3_0(), null); 
                        
                    match(input,12,FOLLOW_12_in_ruleMapperData2160); 

                            createLeafNode(grammarAccess.getMapperDataAccess().getLeftCurlyBracketKeyword_3_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1419:1: ( (lv_uiProperties_5_0= ruleUiProperties ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1420:1: (lv_uiProperties_5_0= ruleUiProperties )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1420:1: (lv_uiProperties_5_0= ruleUiProperties )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1421:3: lv_uiProperties_5_0= ruleUiProperties
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMapperDataAccess().getUiPropertiesUiPropertiesParserRuleCall_3_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleUiProperties_in_ruleMapperData2181);
                    lv_uiProperties_5_0=ruleUiProperties();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getMapperDataRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"uiProperties",
                    	        		lv_uiProperties_5_0, 
                    	        		"UiProperties", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    match(input,13,FOLLOW_13_in_ruleMapperData2191); 

                            createLeafNode(grammarAccess.getMapperDataAccess().getRightCurlyBracketKeyword_3_3(), null); 
                        

                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1447:3: ( 'addInputTable' '{' ( (lv_inputTables_9_0= ruleInputTable ) ) '}' )*
            loop30:
            do {
                int alt30=2;
                int LA30_0 = input.LA(1);

                if ( (LA30_0==16) ) {
                    alt30=1;
                }


                switch (alt30) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1447:5: 'addInputTable' '{' ( (lv_inputTables_9_0= ruleInputTable ) ) '}'
            	    {
            	    match(input,16,FOLLOW_16_in_ruleMapperData2204); 

            	            createLeafNode(grammarAccess.getMapperDataAccess().getAddInputTableKeyword_4_0(), null); 
            	        
            	    match(input,12,FOLLOW_12_in_ruleMapperData2214); 

            	            createLeafNode(grammarAccess.getMapperDataAccess().getLeftCurlyBracketKeyword_4_1(), null); 
            	        
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1455:1: ( (lv_inputTables_9_0= ruleInputTable ) )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1456:1: (lv_inputTables_9_0= ruleInputTable )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1456:1: (lv_inputTables_9_0= ruleInputTable )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1457:3: lv_inputTables_9_0= ruleInputTable
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getMapperDataAccess().getInputTablesInputTableParserRuleCall_4_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleInputTable_in_ruleMapperData2235);
            	    lv_inputTables_9_0=ruleInputTable();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getMapperDataRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"inputTables",
            	    	        		lv_inputTables_9_0, 
            	    	        		"InputTable", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }

            	    match(input,13,FOLLOW_13_in_ruleMapperData2245); 

            	            createLeafNode(grammarAccess.getMapperDataAccess().getRightCurlyBracketKeyword_4_3(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop30;
                }
            } while (true);

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1483:3: ( 'addVarTable' '{' ( (lv_varTables_13_0= ruleVarTable ) ) '}' )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==17) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1483:5: 'addVarTable' '{' ( (lv_varTables_13_0= ruleVarTable ) ) '}'
            	    {
            	    match(input,17,FOLLOW_17_in_ruleMapperData2258); 

            	            createLeafNode(grammarAccess.getMapperDataAccess().getAddVarTableKeyword_5_0(), null); 
            	        
            	    match(input,12,FOLLOW_12_in_ruleMapperData2268); 

            	            createLeafNode(grammarAccess.getMapperDataAccess().getLeftCurlyBracketKeyword_5_1(), null); 
            	        
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1491:1: ( (lv_varTables_13_0= ruleVarTable ) )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1492:1: (lv_varTables_13_0= ruleVarTable )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1492:1: (lv_varTables_13_0= ruleVarTable )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1493:3: lv_varTables_13_0= ruleVarTable
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getMapperDataAccess().getVarTablesVarTableParserRuleCall_5_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleVarTable_in_ruleMapperData2289);
            	    lv_varTables_13_0=ruleVarTable();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getMapperDataRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"varTables",
            	    	        		lv_varTables_13_0, 
            	    	        		"VarTable", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }

            	    match(input,13,FOLLOW_13_in_ruleMapperData2299); 

            	            createLeafNode(grammarAccess.getMapperDataAccess().getRightCurlyBracketKeyword_5_3(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1519:3: ( 'addOutputTable' '{' ( (lv_outputTables_17_0= ruleOutputTable ) ) '}' )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==18) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1519:5: 'addOutputTable' '{' ( (lv_outputTables_17_0= ruleOutputTable ) ) '}'
            	    {
            	    match(input,18,FOLLOW_18_in_ruleMapperData2312); 

            	            createLeafNode(grammarAccess.getMapperDataAccess().getAddOutputTableKeyword_6_0(), null); 
            	        
            	    match(input,12,FOLLOW_12_in_ruleMapperData2322); 

            	            createLeafNode(grammarAccess.getMapperDataAccess().getLeftCurlyBracketKeyword_6_1(), null); 
            	        
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1527:1: ( (lv_outputTables_17_0= ruleOutputTable ) )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1528:1: (lv_outputTables_17_0= ruleOutputTable )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1528:1: (lv_outputTables_17_0= ruleOutputTable )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1529:3: lv_outputTables_17_0= ruleOutputTable
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getMapperDataAccess().getOutputTablesOutputTableParserRuleCall_6_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleOutputTable_in_ruleMapperData2343);
            	    lv_outputTables_17_0=ruleOutputTable();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getMapperDataRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"outputTables",
            	    	        		lv_outputTables_17_0, 
            	    	        		"OutputTable", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }

            	    match(input,13,FOLLOW_13_in_ruleMapperData2353); 

            	            createLeafNode(grammarAccess.getMapperDataAccess().getRightCurlyBracketKeyword_6_3(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

            match(input,13,FOLLOW_13_in_ruleMapperData2365); 

                    createLeafNode(grammarAccess.getMapperDataAccess().getRightCurlyBracketKeyword_7(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleMapperData


    // $ANTLR start entryRuleUiProperties
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1567:1: entryRuleUiProperties returns [EObject current=null] : iv_ruleUiProperties= ruleUiProperties EOF ;
    public final EObject entryRuleUiProperties() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUiProperties = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1568:2: (iv_ruleUiProperties= ruleUiProperties EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1569:2: iv_ruleUiProperties= ruleUiProperties EOF
            {
             currentNode = createCompositeNode(grammarAccess.getUiPropertiesRule(), currentNode); 
            pushFollow(FOLLOW_ruleUiProperties_in_entryRuleUiProperties2401);
            iv_ruleUiProperties=ruleUiProperties();
            _fsp--;

             current =iv_ruleUiProperties; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUiProperties2411); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleUiProperties


    // $ANTLR start ruleUiProperties
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1576:1: ruleUiProperties returns [EObject current=null] : ( () 'SHELLMAXIMIZED:' ( (lv_shellMaximized_2_0= ruleEBoolean ) ) ) ;
    public final EObject ruleUiProperties() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_shellMaximized_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1581:6: ( ( () 'SHELLMAXIMIZED:' ( (lv_shellMaximized_2_0= ruleEBoolean ) ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1582:1: ( () 'SHELLMAXIMIZED:' ( (lv_shellMaximized_2_0= ruleEBoolean ) ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1582:1: ( () 'SHELLMAXIMIZED:' ( (lv_shellMaximized_2_0= ruleEBoolean ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1582:2: () 'SHELLMAXIMIZED:' ( (lv_shellMaximized_2_0= ruleEBoolean ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1582:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1583:5: 
            {
             
                    temp=factory.create(grammarAccess.getUiPropertiesAccess().getUiPropertiesAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getUiPropertiesAccess().getUiPropertiesAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,34,FOLLOW_34_in_ruleUiProperties2455); 

                    createLeafNode(grammarAccess.getUiPropertiesAccess().getSHELLMAXIMIZEDKeyword_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1597:1: ( (lv_shellMaximized_2_0= ruleEBoolean ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1598:1: (lv_shellMaximized_2_0= ruleEBoolean )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1598:1: (lv_shellMaximized_2_0= ruleEBoolean )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1599:3: lv_shellMaximized_2_0= ruleEBoolean
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getUiPropertiesAccess().getShellMaximizedEBooleanParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEBoolean_in_ruleUiProperties2476);
            lv_shellMaximized_2_0=ruleEBoolean();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getUiPropertiesRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"shellMaximized",
            	        		lv_shellMaximized_2_0, 
            	        		"EBoolean", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleUiProperties


    // $ANTLR start entryRuleInputTable
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1629:1: entryRuleInputTable returns [EObject current=null] : iv_ruleInputTable= ruleInputTable EOF ;
    public final EObject entryRuleInputTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInputTable = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1630:2: (iv_ruleInputTable= ruleInputTable EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1631:2: iv_ruleInputTable= ruleInputTable EOF
            {
             currentNode = createCompositeNode(grammarAccess.getInputTableRule(), currentNode); 
            pushFollow(FOLLOW_ruleInputTable_in_entryRuleInputTable2512);
            iv_ruleInputTable=ruleInputTable();
            _fsp--;

             current =iv_ruleInputTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInputTable2522); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleInputTable


    // $ANTLR start ruleInputTable
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1638:1: ruleInputTable returns [EObject current=null] : ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( (lv_sizeState_6_0= ruleEString ) ) )? ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_8_0= ruleEString ) ) )? ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_10_0= ruleEBoolean ) ) )? ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_12_0= ruleEBoolean ) ) )? ( 'MATCHINGMODE:' ( (lv_matchingMode_14_0= ruleEString ) ) )? ( 'LOOKUPMODE:' ( (lv_lookupMode_16_0= ruleEString ) ) )? ( (lv_mapperTableEntries_17_0= ruleMapperTableEntry ) )* ) ;
    public final EObject ruleInputTable() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_minimized_4_0 = null;

        AntlrDatatypeRuleToken lv_sizeState_6_0 = null;

        AntlrDatatypeRuleToken lv_expressionFilter_8_0 = null;

        AntlrDatatypeRuleToken lv_activateExpressionFilter_10_0 = null;

        AntlrDatatypeRuleToken lv_activateCondensedTool_12_0 = null;

        AntlrDatatypeRuleToken lv_matchingMode_14_0 = null;

        AntlrDatatypeRuleToken lv_lookupMode_16_0 = null;

        EObject lv_mapperTableEntries_17_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1643:6: ( ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( (lv_sizeState_6_0= ruleEString ) ) )? ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_8_0= ruleEString ) ) )? ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_10_0= ruleEBoolean ) ) )? ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_12_0= ruleEBoolean ) ) )? ( 'MATCHINGMODE:' ( (lv_matchingMode_14_0= ruleEString ) ) )? ( 'LOOKUPMODE:' ( (lv_lookupMode_16_0= ruleEString ) ) )? ( (lv_mapperTableEntries_17_0= ruleMapperTableEntry ) )* ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1644:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( (lv_sizeState_6_0= ruleEString ) ) )? ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_8_0= ruleEString ) ) )? ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_10_0= ruleEBoolean ) ) )? ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_12_0= ruleEBoolean ) ) )? ( 'MATCHINGMODE:' ( (lv_matchingMode_14_0= ruleEString ) ) )? ( 'LOOKUPMODE:' ( (lv_lookupMode_16_0= ruleEString ) ) )? ( (lv_mapperTableEntries_17_0= ruleMapperTableEntry ) )* )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1644:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( (lv_sizeState_6_0= ruleEString ) ) )? ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_8_0= ruleEString ) ) )? ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_10_0= ruleEBoolean ) ) )? ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_12_0= ruleEBoolean ) ) )? ( 'MATCHINGMODE:' ( (lv_matchingMode_14_0= ruleEString ) ) )? ( 'LOOKUPMODE:' ( (lv_lookupMode_16_0= ruleEString ) ) )? ( (lv_mapperTableEntries_17_0= ruleMapperTableEntry ) )* )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1644:2: () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( (lv_sizeState_6_0= ruleEString ) ) )? ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_8_0= ruleEString ) ) )? ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_10_0= ruleEBoolean ) ) )? ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_12_0= ruleEBoolean ) ) )? ( 'MATCHINGMODE:' ( (lv_matchingMode_14_0= ruleEString ) ) )? ( 'LOOKUPMODE:' ( (lv_lookupMode_16_0= ruleEString ) ) )? ( (lv_mapperTableEntries_17_0= ruleMapperTableEntry ) )*
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1644:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1645:5: 
            {
             
                    temp=factory.create(grammarAccess.getInputTableAccess().getInputTableAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getInputTableAccess().getInputTableAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,19,FOLLOW_19_in_ruleInputTable2566); 

                    createLeafNode(grammarAccess.getInputTableAccess().getNAMEKeyword_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1659:1: ( (lv_name_2_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1660:1: (lv_name_2_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1660:1: (lv_name_2_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1661:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getInputTableAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleInputTable2587);
            lv_name_2_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getInputTableRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_2_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1683:2: ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )?
            int alt33=2;
            int LA33_0 = input.LA(1);

            if ( (LA33_0==20) ) {
                alt33=1;
            }
            switch (alt33) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1683:4: 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) )
                    {
                    match(input,20,FOLLOW_20_in_ruleInputTable2598); 

                            createLeafNode(grammarAccess.getInputTableAccess().getMINIMIZEDKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1687:1: ( (lv_minimized_4_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1688:1: (lv_minimized_4_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1688:1: (lv_minimized_4_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1689:3: lv_minimized_4_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getInputTableAccess().getMinimizedEBooleanParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleInputTable2619);
                    lv_minimized_4_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getInputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"minimized",
                    	        		lv_minimized_4_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1711:4: ( 'SIZESTATE:' ( (lv_sizeState_6_0= ruleEString ) ) )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==35) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1711:6: 'SIZESTATE:' ( (lv_sizeState_6_0= ruleEString ) )
                    {
                    match(input,35,FOLLOW_35_in_ruleInputTable2632); 

                            createLeafNode(grammarAccess.getInputTableAccess().getSIZESTATEKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1715:1: ( (lv_sizeState_6_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1716:1: (lv_sizeState_6_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1716:1: (lv_sizeState_6_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1717:3: lv_sizeState_6_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getInputTableAccess().getSizeStateEStringParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleInputTable2653);
                    lv_sizeState_6_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getInputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"sizeState",
                    	        		lv_sizeState_6_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1739:4: ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_8_0= ruleEString ) ) )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( (LA35_0==36) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1739:6: 'EXPRESSIONFILTER:' ( (lv_expressionFilter_8_0= ruleEString ) )
                    {
                    match(input,36,FOLLOW_36_in_ruleInputTable2666); 

                            createLeafNode(grammarAccess.getInputTableAccess().getEXPRESSIONFILTERKeyword_5_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1743:1: ( (lv_expressionFilter_8_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1744:1: (lv_expressionFilter_8_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1744:1: (lv_expressionFilter_8_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1745:3: lv_expressionFilter_8_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getInputTableAccess().getExpressionFilterEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleInputTable2687);
                    lv_expressionFilter_8_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getInputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"expressionFilter",
                    	        		lv_expressionFilter_8_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1767:4: ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_10_0= ruleEBoolean ) ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==37) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1767:6: 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_10_0= ruleEBoolean ) )
                    {
                    match(input,37,FOLLOW_37_in_ruleInputTable2700); 

                            createLeafNode(grammarAccess.getInputTableAccess().getACTIVATEEXPRESSIONFILTERKeyword_6_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1771:1: ( (lv_activateExpressionFilter_10_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1772:1: (lv_activateExpressionFilter_10_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1772:1: (lv_activateExpressionFilter_10_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1773:3: lv_activateExpressionFilter_10_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getInputTableAccess().getActivateExpressionFilterEBooleanParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleInputTable2721);
                    lv_activateExpressionFilter_10_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getInputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"activateExpressionFilter",
                    	        		lv_activateExpressionFilter_10_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1795:4: ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_12_0= ruleEBoolean ) ) )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==38) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1795:6: 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_12_0= ruleEBoolean ) )
                    {
                    match(input,38,FOLLOW_38_in_ruleInputTable2734); 

                            createLeafNode(grammarAccess.getInputTableAccess().getACTIVATECONDENSEDTOOLKeyword_7_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1799:1: ( (lv_activateCondensedTool_12_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1800:1: (lv_activateCondensedTool_12_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1800:1: (lv_activateCondensedTool_12_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1801:3: lv_activateCondensedTool_12_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getInputTableAccess().getActivateCondensedToolEBooleanParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleInputTable2755);
                    lv_activateCondensedTool_12_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getInputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"activateCondensedTool",
                    	        		lv_activateCondensedTool_12_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1823:4: ( 'MATCHINGMODE:' ( (lv_matchingMode_14_0= ruleEString ) ) )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==39) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1823:6: 'MATCHINGMODE:' ( (lv_matchingMode_14_0= ruleEString ) )
                    {
                    match(input,39,FOLLOW_39_in_ruleInputTable2768); 

                            createLeafNode(grammarAccess.getInputTableAccess().getMATCHINGMODEKeyword_8_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1827:1: ( (lv_matchingMode_14_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1828:1: (lv_matchingMode_14_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1828:1: (lv_matchingMode_14_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1829:3: lv_matchingMode_14_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getInputTableAccess().getMatchingModeEStringParserRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleInputTable2789);
                    lv_matchingMode_14_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getInputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"matchingMode",
                    	        		lv_matchingMode_14_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1851:4: ( 'LOOKUPMODE:' ( (lv_lookupMode_16_0= ruleEString ) ) )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==40) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1851:6: 'LOOKUPMODE:' ( (lv_lookupMode_16_0= ruleEString ) )
                    {
                    match(input,40,FOLLOW_40_in_ruleInputTable2802); 

                            createLeafNode(grammarAccess.getInputTableAccess().getLOOKUPMODEKeyword_9_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1855:1: ( (lv_lookupMode_16_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1856:1: (lv_lookupMode_16_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1856:1: (lv_lookupMode_16_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1857:3: lv_lookupMode_16_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getInputTableAccess().getLookupModeEStringParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleInputTable2823);
                    lv_lookupMode_16_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getInputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"lookupMode",
                    	        		lv_lookupMode_16_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1879:4: ( (lv_mapperTableEntries_17_0= ruleMapperTableEntry ) )*
            loop40:
            do {
                int alt40=2;
                int LA40_0 = input.LA(1);

                if ( (LA40_0==25) ) {
                    alt40=1;
                }


                switch (alt40) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1880:1: (lv_mapperTableEntries_17_0= ruleMapperTableEntry )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1880:1: (lv_mapperTableEntries_17_0= ruleMapperTableEntry )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1881:3: lv_mapperTableEntries_17_0= ruleMapperTableEntry
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getInputTableAccess().getMapperTableEntriesMapperTableEntryParserRuleCall_10_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleMapperTableEntry_in_ruleInputTable2846);
            	    lv_mapperTableEntries_17_0=ruleMapperTableEntry();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getInputTableRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"mapperTableEntries",
            	    	        		lv_mapperTableEntries_17_0, 
            	    	        		"MapperTableEntry", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop40;
                }
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleInputTable


    // $ANTLR start entryRuleVarTable
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1911:1: entryRuleVarTable returns [EObject current=null] : iv_ruleVarTable= ruleVarTable EOF ;
    public final EObject entryRuleVarTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVarTable = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1912:2: (iv_ruleVarTable= ruleVarTable EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1913:2: iv_ruleVarTable= ruleVarTable EOF
            {
             currentNode = createCompositeNode(grammarAccess.getVarTableRule(), currentNode); 
            pushFollow(FOLLOW_ruleVarTable_in_entryRuleVarTable2883);
            iv_ruleVarTable=ruleVarTable();
            _fsp--;

             current =iv_ruleVarTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleVarTable2893); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleVarTable


    // $ANTLR start ruleVarTable
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1920:1: ruleVarTable returns [EObject current=null] : ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( (lv_sizeState_6_0= ruleEString ) ) )? ( (lv_mapperTableEntries_7_0= ruleMapperTableEntry ) )* ) ;
    public final EObject ruleVarTable() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_minimized_4_0 = null;

        AntlrDatatypeRuleToken lv_sizeState_6_0 = null;

        EObject lv_mapperTableEntries_7_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1925:6: ( ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( (lv_sizeState_6_0= ruleEString ) ) )? ( (lv_mapperTableEntries_7_0= ruleMapperTableEntry ) )* ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1926:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( (lv_sizeState_6_0= ruleEString ) ) )? ( (lv_mapperTableEntries_7_0= ruleMapperTableEntry ) )* )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1926:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( (lv_sizeState_6_0= ruleEString ) ) )? ( (lv_mapperTableEntries_7_0= ruleMapperTableEntry ) )* )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1926:2: () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( (lv_sizeState_6_0= ruleEString ) ) )? ( (lv_mapperTableEntries_7_0= ruleMapperTableEntry ) )*
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1926:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1927:5: 
            {
             
                    temp=factory.create(grammarAccess.getVarTableAccess().getVarTableAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getVarTableAccess().getVarTableAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,19,FOLLOW_19_in_ruleVarTable2937); 

                    createLeafNode(grammarAccess.getVarTableAccess().getNAMEKeyword_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1941:1: ( (lv_name_2_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1942:1: (lv_name_2_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1942:1: (lv_name_2_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1943:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getVarTableAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleVarTable2958);
            lv_name_2_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getVarTableRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_2_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1965:2: ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==20) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1965:4: 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) )
                    {
                    match(input,20,FOLLOW_20_in_ruleVarTable2969); 

                            createLeafNode(grammarAccess.getVarTableAccess().getMINIMIZEDKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1969:1: ( (lv_minimized_4_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1970:1: (lv_minimized_4_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1970:1: (lv_minimized_4_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1971:3: lv_minimized_4_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getVarTableAccess().getMinimizedEBooleanParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleVarTable2990);
                    lv_minimized_4_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getVarTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"minimized",
                    	        		lv_minimized_4_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1993:4: ( 'SIZESTATE:' ( (lv_sizeState_6_0= ruleEString ) ) )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==35) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1993:6: 'SIZESTATE:' ( (lv_sizeState_6_0= ruleEString ) )
                    {
                    match(input,35,FOLLOW_35_in_ruleVarTable3003); 

                            createLeafNode(grammarAccess.getVarTableAccess().getSIZESTATEKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1997:1: ( (lv_sizeState_6_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1998:1: (lv_sizeState_6_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1998:1: (lv_sizeState_6_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1999:3: lv_sizeState_6_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getVarTableAccess().getSizeStateEStringParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleVarTable3024);
                    lv_sizeState_6_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getVarTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"sizeState",
                    	        		lv_sizeState_6_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2021:4: ( (lv_mapperTableEntries_7_0= ruleMapperTableEntry ) )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==25) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2022:1: (lv_mapperTableEntries_7_0= ruleMapperTableEntry )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2022:1: (lv_mapperTableEntries_7_0= ruleMapperTableEntry )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2023:3: lv_mapperTableEntries_7_0= ruleMapperTableEntry
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getVarTableAccess().getMapperTableEntriesMapperTableEntryParserRuleCall_5_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleMapperTableEntry_in_ruleVarTable3047);
            	    lv_mapperTableEntries_7_0=ruleMapperTableEntry();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getVarTableRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"mapperTableEntries",
            	    	        		lv_mapperTableEntries_7_0, 
            	    	        		"MapperTableEntry", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop43;
                }
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleVarTable


    // $ANTLR start entryRuleOutputTable
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2053:1: entryRuleOutputTable returns [EObject current=null] : iv_ruleOutputTable= ruleOutputTable EOF ;
    public final EObject entryRuleOutputTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOutputTable = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2054:2: (iv_ruleOutputTable= ruleOutputTable EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2055:2: iv_ruleOutputTable= ruleOutputTable EOF
            {
             currentNode = createCompositeNode(grammarAccess.getOutputTableRule(), currentNode); 
            pushFollow(FOLLOW_ruleOutputTable_in_entryRuleOutputTable3084);
            iv_ruleOutputTable=ruleOutputTable();
            _fsp--;

             current =iv_ruleOutputTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOutputTable3094); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleOutputTable


    // $ANTLR start ruleOutputTable
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2062:1: ruleOutputTable returns [EObject current=null] : ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( (lv_sizeState_6_0= ruleEString ) ) )? ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_8_0= ruleEString ) ) )? ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_10_0= ruleEBoolean ) ) )? ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_12_0= ruleEBoolean ) ) )? ( 'REJECT:' ( (lv_reject_14_0= ruleEBoolean ) ) )? ( 'REJECTINNERJOIN:' ( (lv_rejectInnerJoin_16_0= ruleEBoolean ) ) )? ( 'ISERRORREJECTTABLE:' ( (lv_isErrorRejectTable_18_0= ruleEBoolean ) ) )? ( 'ISJOINTABLEOF:' ( (lv_isJoinTableOf_20_0= ruleEString ) ) )? ( (lv_mapperTableEntries_21_0= ruleMapperTableEntry ) )* ) ;
    public final EObject ruleOutputTable() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_minimized_4_0 = null;

        AntlrDatatypeRuleToken lv_sizeState_6_0 = null;

        AntlrDatatypeRuleToken lv_expressionFilter_8_0 = null;

        AntlrDatatypeRuleToken lv_activateExpressionFilter_10_0 = null;

        AntlrDatatypeRuleToken lv_activateCondensedTool_12_0 = null;

        AntlrDatatypeRuleToken lv_reject_14_0 = null;

        AntlrDatatypeRuleToken lv_rejectInnerJoin_16_0 = null;

        AntlrDatatypeRuleToken lv_isErrorRejectTable_18_0 = null;

        AntlrDatatypeRuleToken lv_isJoinTableOf_20_0 = null;

        EObject lv_mapperTableEntries_21_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2067:6: ( ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( (lv_sizeState_6_0= ruleEString ) ) )? ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_8_0= ruleEString ) ) )? ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_10_0= ruleEBoolean ) ) )? ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_12_0= ruleEBoolean ) ) )? ( 'REJECT:' ( (lv_reject_14_0= ruleEBoolean ) ) )? ( 'REJECTINNERJOIN:' ( (lv_rejectInnerJoin_16_0= ruleEBoolean ) ) )? ( 'ISERRORREJECTTABLE:' ( (lv_isErrorRejectTable_18_0= ruleEBoolean ) ) )? ( 'ISJOINTABLEOF:' ( (lv_isJoinTableOf_20_0= ruleEString ) ) )? ( (lv_mapperTableEntries_21_0= ruleMapperTableEntry ) )* ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2068:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( (lv_sizeState_6_0= ruleEString ) ) )? ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_8_0= ruleEString ) ) )? ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_10_0= ruleEBoolean ) ) )? ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_12_0= ruleEBoolean ) ) )? ( 'REJECT:' ( (lv_reject_14_0= ruleEBoolean ) ) )? ( 'REJECTINNERJOIN:' ( (lv_rejectInnerJoin_16_0= ruleEBoolean ) ) )? ( 'ISERRORREJECTTABLE:' ( (lv_isErrorRejectTable_18_0= ruleEBoolean ) ) )? ( 'ISJOINTABLEOF:' ( (lv_isJoinTableOf_20_0= ruleEString ) ) )? ( (lv_mapperTableEntries_21_0= ruleMapperTableEntry ) )* )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2068:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( (lv_sizeState_6_0= ruleEString ) ) )? ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_8_0= ruleEString ) ) )? ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_10_0= ruleEBoolean ) ) )? ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_12_0= ruleEBoolean ) ) )? ( 'REJECT:' ( (lv_reject_14_0= ruleEBoolean ) ) )? ( 'REJECTINNERJOIN:' ( (lv_rejectInnerJoin_16_0= ruleEBoolean ) ) )? ( 'ISERRORREJECTTABLE:' ( (lv_isErrorRejectTable_18_0= ruleEBoolean ) ) )? ( 'ISJOINTABLEOF:' ( (lv_isJoinTableOf_20_0= ruleEString ) ) )? ( (lv_mapperTableEntries_21_0= ruleMapperTableEntry ) )* )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2068:2: () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( (lv_sizeState_6_0= ruleEString ) ) )? ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_8_0= ruleEString ) ) )? ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_10_0= ruleEBoolean ) ) )? ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_12_0= ruleEBoolean ) ) )? ( 'REJECT:' ( (lv_reject_14_0= ruleEBoolean ) ) )? ( 'REJECTINNERJOIN:' ( (lv_rejectInnerJoin_16_0= ruleEBoolean ) ) )? ( 'ISERRORREJECTTABLE:' ( (lv_isErrorRejectTable_18_0= ruleEBoolean ) ) )? ( 'ISJOINTABLEOF:' ( (lv_isJoinTableOf_20_0= ruleEString ) ) )? ( (lv_mapperTableEntries_21_0= ruleMapperTableEntry ) )*
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2068:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2069:5: 
            {
             
                    temp=factory.create(grammarAccess.getOutputTableAccess().getOutputTableAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getOutputTableAccess().getOutputTableAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,19,FOLLOW_19_in_ruleOutputTable3138); 

                    createLeafNode(grammarAccess.getOutputTableAccess().getNAMEKeyword_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2083:1: ( (lv_name_2_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2084:1: (lv_name_2_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2084:1: (lv_name_2_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2085:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getOutputTableAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleOutputTable3159);
            lv_name_2_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_2_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2107:2: ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==20) ) {
                alt44=1;
            }
            switch (alt44) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2107:4: 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) )
                    {
                    match(input,20,FOLLOW_20_in_ruleOutputTable3170); 

                            createLeafNode(grammarAccess.getOutputTableAccess().getMINIMIZEDKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2111:1: ( (lv_minimized_4_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2112:1: (lv_minimized_4_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2112:1: (lv_minimized_4_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2113:3: lv_minimized_4_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getOutputTableAccess().getMinimizedEBooleanParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleOutputTable3191);
                    lv_minimized_4_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"minimized",
                    	        		lv_minimized_4_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2135:4: ( 'SIZESTATE:' ( (lv_sizeState_6_0= ruleEString ) ) )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==35) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2135:6: 'SIZESTATE:' ( (lv_sizeState_6_0= ruleEString ) )
                    {
                    match(input,35,FOLLOW_35_in_ruleOutputTable3204); 

                            createLeafNode(grammarAccess.getOutputTableAccess().getSIZESTATEKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2139:1: ( (lv_sizeState_6_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2140:1: (lv_sizeState_6_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2140:1: (lv_sizeState_6_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2141:3: lv_sizeState_6_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getOutputTableAccess().getSizeStateEStringParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleOutputTable3225);
                    lv_sizeState_6_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"sizeState",
                    	        		lv_sizeState_6_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2163:4: ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_8_0= ruleEString ) ) )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==36) ) {
                alt46=1;
            }
            switch (alt46) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2163:6: 'EXPRESSIONFILTER:' ( (lv_expressionFilter_8_0= ruleEString ) )
                    {
                    match(input,36,FOLLOW_36_in_ruleOutputTable3238); 

                            createLeafNode(grammarAccess.getOutputTableAccess().getEXPRESSIONFILTERKeyword_5_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2167:1: ( (lv_expressionFilter_8_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2168:1: (lv_expressionFilter_8_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2168:1: (lv_expressionFilter_8_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2169:3: lv_expressionFilter_8_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getOutputTableAccess().getExpressionFilterEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleOutputTable3259);
                    lv_expressionFilter_8_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"expressionFilter",
                    	        		lv_expressionFilter_8_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2191:4: ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_10_0= ruleEBoolean ) ) )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==37) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2191:6: 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_10_0= ruleEBoolean ) )
                    {
                    match(input,37,FOLLOW_37_in_ruleOutputTable3272); 

                            createLeafNode(grammarAccess.getOutputTableAccess().getACTIVATEEXPRESSIONFILTERKeyword_6_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2195:1: ( (lv_activateExpressionFilter_10_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2196:1: (lv_activateExpressionFilter_10_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2196:1: (lv_activateExpressionFilter_10_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2197:3: lv_activateExpressionFilter_10_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getOutputTableAccess().getActivateExpressionFilterEBooleanParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleOutputTable3293);
                    lv_activateExpressionFilter_10_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"activateExpressionFilter",
                    	        		lv_activateExpressionFilter_10_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2219:4: ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_12_0= ruleEBoolean ) ) )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==38) ) {
                alt48=1;
            }
            switch (alt48) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2219:6: 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_12_0= ruleEBoolean ) )
                    {
                    match(input,38,FOLLOW_38_in_ruleOutputTable3306); 

                            createLeafNode(grammarAccess.getOutputTableAccess().getACTIVATECONDENSEDTOOLKeyword_7_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2223:1: ( (lv_activateCondensedTool_12_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2224:1: (lv_activateCondensedTool_12_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2224:1: (lv_activateCondensedTool_12_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2225:3: lv_activateCondensedTool_12_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getOutputTableAccess().getActivateCondensedToolEBooleanParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleOutputTable3327);
                    lv_activateCondensedTool_12_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"activateCondensedTool",
                    	        		lv_activateCondensedTool_12_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2247:4: ( 'REJECT:' ( (lv_reject_14_0= ruleEBoolean ) ) )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==41) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2247:6: 'REJECT:' ( (lv_reject_14_0= ruleEBoolean ) )
                    {
                    match(input,41,FOLLOW_41_in_ruleOutputTable3340); 

                            createLeafNode(grammarAccess.getOutputTableAccess().getREJECTKeyword_8_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2251:1: ( (lv_reject_14_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2252:1: (lv_reject_14_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2252:1: (lv_reject_14_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2253:3: lv_reject_14_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getOutputTableAccess().getRejectEBooleanParserRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleOutputTable3361);
                    lv_reject_14_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"reject",
                    	        		lv_reject_14_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2275:4: ( 'REJECTINNERJOIN:' ( (lv_rejectInnerJoin_16_0= ruleEBoolean ) ) )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==42) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2275:6: 'REJECTINNERJOIN:' ( (lv_rejectInnerJoin_16_0= ruleEBoolean ) )
                    {
                    match(input,42,FOLLOW_42_in_ruleOutputTable3374); 

                            createLeafNode(grammarAccess.getOutputTableAccess().getREJECTINNERJOINKeyword_9_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2279:1: ( (lv_rejectInnerJoin_16_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2280:1: (lv_rejectInnerJoin_16_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2280:1: (lv_rejectInnerJoin_16_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2281:3: lv_rejectInnerJoin_16_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getOutputTableAccess().getRejectInnerJoinEBooleanParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleOutputTable3395);
                    lv_rejectInnerJoin_16_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"rejectInnerJoin",
                    	        		lv_rejectInnerJoin_16_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2303:4: ( 'ISERRORREJECTTABLE:' ( (lv_isErrorRejectTable_18_0= ruleEBoolean ) ) )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==43) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2303:6: 'ISERRORREJECTTABLE:' ( (lv_isErrorRejectTable_18_0= ruleEBoolean ) )
                    {
                    match(input,43,FOLLOW_43_in_ruleOutputTable3408); 

                            createLeafNode(grammarAccess.getOutputTableAccess().getISERRORREJECTTABLEKeyword_10_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2307:1: ( (lv_isErrorRejectTable_18_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2308:1: (lv_isErrorRejectTable_18_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2308:1: (lv_isErrorRejectTable_18_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2309:3: lv_isErrorRejectTable_18_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getOutputTableAccess().getIsErrorRejectTableEBooleanParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleOutputTable3429);
                    lv_isErrorRejectTable_18_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"isErrorRejectTable",
                    	        		lv_isErrorRejectTable_18_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2331:4: ( 'ISJOINTABLEOF:' ( (lv_isJoinTableOf_20_0= ruleEString ) ) )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==44) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2331:6: 'ISJOINTABLEOF:' ( (lv_isJoinTableOf_20_0= ruleEString ) )
                    {
                    match(input,44,FOLLOW_44_in_ruleOutputTable3442); 

                            createLeafNode(grammarAccess.getOutputTableAccess().getISJOINTABLEOFKeyword_11_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2335:1: ( (lv_isJoinTableOf_20_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2336:1: (lv_isJoinTableOf_20_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2336:1: (lv_isJoinTableOf_20_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2337:3: lv_isJoinTableOf_20_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getOutputTableAccess().getIsJoinTableOfEStringParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleOutputTable3463);
                    lv_isJoinTableOf_20_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"isJoinTableOf",
                    	        		lv_isJoinTableOf_20_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2359:4: ( (lv_mapperTableEntries_21_0= ruleMapperTableEntry ) )*
            loop53:
            do {
                int alt53=2;
                int LA53_0 = input.LA(1);

                if ( (LA53_0==25) ) {
                    alt53=1;
                }


                switch (alt53) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2360:1: (lv_mapperTableEntries_21_0= ruleMapperTableEntry )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2360:1: (lv_mapperTableEntries_21_0= ruleMapperTableEntry )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2361:3: lv_mapperTableEntries_21_0= ruleMapperTableEntry
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getOutputTableAccess().getMapperTableEntriesMapperTableEntryParserRuleCall_12_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleMapperTableEntry_in_ruleOutputTable3486);
            	    lv_mapperTableEntries_21_0=ruleMapperTableEntry();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"mapperTableEntries",
            	    	        		lv_mapperTableEntries_21_0, 
            	    	        		"MapperTableEntry", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop53;
                }
            } while (true);


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleOutputTable


    // $ANTLR start entryRuleMapperTableEntry
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2391:1: entryRuleMapperTableEntry returns [EObject current=null] : iv_ruleMapperTableEntry= ruleMapperTableEntry EOF ;
    public final EObject entryRuleMapperTableEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMapperTableEntry = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2392:2: (iv_ruleMapperTableEntry= ruleMapperTableEntry EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2393:2: iv_ruleMapperTableEntry= ruleMapperTableEntry EOF
            {
             currentNode = createCompositeNode(grammarAccess.getMapperTableEntryRule(), currentNode); 
            pushFollow(FOLLOW_ruleMapperTableEntry_in_entryRuleMapperTableEntry3523);
            iv_ruleMapperTableEntry=ruleMapperTableEntry();
            _fsp--;

             current =iv_ruleMapperTableEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMapperTableEntry3533); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleMapperTableEntry


    // $ANTLR start ruleMapperTableEntry
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2400:1: ruleMapperTableEntry returns [EObject current=null] : ( () 'addColumn' '{' 'NAME:' ( (lv_name_4_0= ruleEString ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )? ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )? '}' ) ;
    public final EObject ruleMapperTableEntry() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        AntlrDatatypeRuleToken lv_type_7_0 = null;

        AntlrDatatypeRuleToken lv_nullable_10_0 = null;

        AntlrDatatypeRuleToken lv_expression_13_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2405:6: ( ( () 'addColumn' '{' 'NAME:' ( (lv_name_4_0= ruleEString ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )? ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )? '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2406:1: ( () 'addColumn' '{' 'NAME:' ( (lv_name_4_0= ruleEString ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )? ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )? '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2406:1: ( () 'addColumn' '{' 'NAME:' ( (lv_name_4_0= ruleEString ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )? ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )? '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2406:2: () 'addColumn' '{' 'NAME:' ( (lv_name_4_0= ruleEString ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )? ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )? '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2406:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2407:5: 
            {
             
                    temp=factory.create(grammarAccess.getMapperTableEntryAccess().getMapperTableEntryAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getMapperTableEntryAccess().getMapperTableEntryAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,25,FOLLOW_25_in_ruleMapperTableEntry3577); 

                    createLeafNode(grammarAccess.getMapperTableEntryAccess().getAddColumnKeyword_1(), null); 
                
            match(input,12,FOLLOW_12_in_ruleMapperTableEntry3587); 

                    createLeafNode(grammarAccess.getMapperTableEntryAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            match(input,19,FOLLOW_19_in_ruleMapperTableEntry3597); 

                    createLeafNode(grammarAccess.getMapperTableEntryAccess().getNAMEKeyword_3(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2429:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2430:1: (lv_name_4_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2430:1: (lv_name_4_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2431:3: lv_name_4_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getMapperTableEntryAccess().getNameEStringParserRuleCall_4_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleMapperTableEntry3618);
            lv_name_4_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getMapperTableEntryRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_4_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2453:2: ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==14) ) {
                int LA54_1 = input.LA(2);

                if ( (LA54_1==26) ) {
                    alt54=1;
                }
            }
            switch (alt54) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2453:4: ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleMapperTableEntry3629); 

                            createLeafNode(grammarAccess.getMapperTableEntryAccess().getCommaKeyword_5_0(), null); 
                        
                    match(input,26,FOLLOW_26_in_ruleMapperTableEntry3639); 

                            createLeafNode(grammarAccess.getMapperTableEntryAccess().getTYPEKeyword_5_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2461:1: ( (lv_type_7_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2462:1: (lv_type_7_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2462:1: (lv_type_7_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2463:3: lv_type_7_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMapperTableEntryAccess().getTypeEStringParserRuleCall_5_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleMapperTableEntry3660);
                    lv_type_7_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getMapperTableEntryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"type",
                    	        		lv_type_7_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2485:4: ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==14) ) {
                int LA55_1 = input.LA(2);

                if ( (LA55_1==27) ) {
                    alt55=1;
                }
            }
            switch (alt55) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2485:6: ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleMapperTableEntry3673); 

                            createLeafNode(grammarAccess.getMapperTableEntryAccess().getCommaKeyword_6_0(), null); 
                        
                    match(input,27,FOLLOW_27_in_ruleMapperTableEntry3683); 

                            createLeafNode(grammarAccess.getMapperTableEntryAccess().getNULLABLEKeyword_6_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2493:1: ( (lv_nullable_10_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2494:1: (lv_nullable_10_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2494:1: (lv_nullable_10_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2495:3: lv_nullable_10_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMapperTableEntryAccess().getNullableEBooleanParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleMapperTableEntry3704);
                    lv_nullable_10_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getMapperTableEntryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"nullable",
                    	        		lv_nullable_10_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2517:4: ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==14) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2517:6: ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleMapperTableEntry3717); 

                            createLeafNode(grammarAccess.getMapperTableEntryAccess().getCommaKeyword_7_0(), null); 
                        
                    match(input,28,FOLLOW_28_in_ruleMapperTableEntry3727); 

                            createLeafNode(grammarAccess.getMapperTableEntryAccess().getEXPRESSIONKeyword_7_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2525:1: ( (lv_expression_13_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2526:1: (lv_expression_13_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2526:1: (lv_expression_13_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2527:3: lv_expression_13_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMapperTableEntryAccess().getExpressionEStringParserRuleCall_7_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleMapperTableEntry3748);
                    lv_expression_13_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getMapperTableEntryRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"expression",
                    	        		lv_expression_13_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            match(input,13,FOLLOW_13_in_ruleMapperTableEntry3760); 

                    createLeafNode(grammarAccess.getMapperTableEntryAccess().getRightCurlyBracketKeyword_8(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleMapperTableEntry


    // $ANTLR start entryRuleNodeType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2561:1: entryRuleNodeType returns [EObject current=null] : iv_ruleNodeType= ruleNodeType EOF ;
    public final EObject entryRuleNodeType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNodeType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2562:2: (iv_ruleNodeType= ruleNodeType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2563:2: iv_ruleNodeType= ruleNodeType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNodeTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleNodeType_in_entryRuleNodeType3796);
            iv_ruleNodeType=ruleNodeType();
            _fsp--;

             current =iv_ruleNodeType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNodeType3806); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleNodeType


    // $ANTLR start ruleNodeType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2570:1: ruleNodeType returns [EObject current=null] : ( 'addComponent' '{' 'setComponentDefinition' '{' ( 'TYPE:' ( (lv_componentName_5_0= ruleEString ) ) ',' ) ( 'NAME:' ( (lv_elementParameter_8_0= ruleUniqueNameType ) ) ',' ) ( 'POSITION:' ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) ) ) '}' ( (lv_metadata_15_0= ruleMetadataType ) )* 'setSettings' '{' ( ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) ) ) ( ',' ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) ) )* '}' ( (lv_metadata_22_0= ruleMetadataType ) )* ( (lv_nodeData_23_0= ruleExternalData ) )? '}' ) ;
    public final EObject ruleNodeType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_componentName_5_0 = null;

        EObject lv_elementParameter_8_0 = null;

        AntlrDatatypeRuleToken lv_posX_11_0 = null;

        AntlrDatatypeRuleToken lv_posY_13_0 = null;

        EObject lv_metadata_15_0 = null;

        EObject lv_elementParameter_18_1 = null;

        EObject lv_elementParameter_18_2 = null;

        EObject lv_elementParameter_20_1 = null;

        EObject lv_elementParameter_20_2 = null;

        EObject lv_metadata_22_0 = null;

        EObject lv_nodeData_23_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2575:6: ( ( 'addComponent' '{' 'setComponentDefinition' '{' ( 'TYPE:' ( (lv_componentName_5_0= ruleEString ) ) ',' ) ( 'NAME:' ( (lv_elementParameter_8_0= ruleUniqueNameType ) ) ',' ) ( 'POSITION:' ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) ) ) '}' ( (lv_metadata_15_0= ruleMetadataType ) )* 'setSettings' '{' ( ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) ) ) ( ',' ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) ) )* '}' ( (lv_metadata_22_0= ruleMetadataType ) )* ( (lv_nodeData_23_0= ruleExternalData ) )? '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2576:1: ( 'addComponent' '{' 'setComponentDefinition' '{' ( 'TYPE:' ( (lv_componentName_5_0= ruleEString ) ) ',' ) ( 'NAME:' ( (lv_elementParameter_8_0= ruleUniqueNameType ) ) ',' ) ( 'POSITION:' ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) ) ) '}' ( (lv_metadata_15_0= ruleMetadataType ) )* 'setSettings' '{' ( ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) ) ) ( ',' ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) ) )* '}' ( (lv_metadata_22_0= ruleMetadataType ) )* ( (lv_nodeData_23_0= ruleExternalData ) )? '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2576:1: ( 'addComponent' '{' 'setComponentDefinition' '{' ( 'TYPE:' ( (lv_componentName_5_0= ruleEString ) ) ',' ) ( 'NAME:' ( (lv_elementParameter_8_0= ruleUniqueNameType ) ) ',' ) ( 'POSITION:' ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) ) ) '}' ( (lv_metadata_15_0= ruleMetadataType ) )* 'setSettings' '{' ( ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) ) ) ( ',' ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) ) )* '}' ( (lv_metadata_22_0= ruleMetadataType ) )* ( (lv_nodeData_23_0= ruleExternalData ) )? '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2576:3: 'addComponent' '{' 'setComponentDefinition' '{' ( 'TYPE:' ( (lv_componentName_5_0= ruleEString ) ) ',' ) ( 'NAME:' ( (lv_elementParameter_8_0= ruleUniqueNameType ) ) ',' ) ( 'POSITION:' ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) ) ) '}' ( (lv_metadata_15_0= ruleMetadataType ) )* 'setSettings' '{' ( ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) ) ) ( ',' ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) ) )* '}' ( (lv_metadata_22_0= ruleMetadataType ) )* ( (lv_nodeData_23_0= ruleExternalData ) )? '}'
            {
            match(input,45,FOLLOW_45_in_ruleNodeType3841); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getAddComponentKeyword_0(), null); 
                
            match(input,12,FOLLOW_12_in_ruleNodeType3851); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getLeftCurlyBracketKeyword_1(), null); 
                
            match(input,46,FOLLOW_46_in_ruleNodeType3861); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getSetComponentDefinitionKeyword_2(), null); 
                
            match(input,12,FOLLOW_12_in_ruleNodeType3871); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2592:1: ( 'TYPE:' ( (lv_componentName_5_0= ruleEString ) ) ',' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2592:3: 'TYPE:' ( (lv_componentName_5_0= ruleEString ) ) ','
            {
            match(input,26,FOLLOW_26_in_ruleNodeType3882); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getTYPEKeyword_4_0(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2596:1: ( (lv_componentName_5_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2597:1: (lv_componentName_5_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2597:1: (lv_componentName_5_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2598:3: lv_componentName_5_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getComponentNameEStringParserRuleCall_4_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleNodeType3903);
            lv_componentName_5_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getNodeTypeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"componentName",
            	        		lv_componentName_5_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,14,FOLLOW_14_in_ruleNodeType3913); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getCommaKeyword_4_2(), null); 
                

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2624:2: ( 'NAME:' ( (lv_elementParameter_8_0= ruleUniqueNameType ) ) ',' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2624:4: 'NAME:' ( (lv_elementParameter_8_0= ruleUniqueNameType ) ) ','
            {
            match(input,19,FOLLOW_19_in_ruleNodeType3925); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getNAMEKeyword_5_0(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2628:1: ( (lv_elementParameter_8_0= ruleUniqueNameType ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2629:1: (lv_elementParameter_8_0= ruleUniqueNameType )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2629:1: (lv_elementParameter_8_0= ruleUniqueNameType )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2630:3: lv_elementParameter_8_0= ruleUniqueNameType
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getElementParameterUniqueNameTypeParserRuleCall_5_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleUniqueNameType_in_ruleNodeType3946);
            lv_elementParameter_8_0=ruleUniqueNameType();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getNodeTypeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		add(
            	       			current, 
            	       			"elementParameter",
            	        		lv_elementParameter_8_0, 
            	        		"UniqueNameType", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,14,FOLLOW_14_in_ruleNodeType3956); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getCommaKeyword_5_2(), null); 
                

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2656:2: ( 'POSITION:' ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2656:4: 'POSITION:' ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) )
            {
            match(input,47,FOLLOW_47_in_ruleNodeType3968); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getPOSITIONKeyword_6_0(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2660:1: ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2660:2: ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2660:2: ( (lv_posX_11_0= ruleEInt ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2661:1: (lv_posX_11_0= ruleEInt )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2661:1: (lv_posX_11_0= ruleEInt )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2662:3: lv_posX_11_0= ruleEInt
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getPosXEIntParserRuleCall_6_1_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEInt_in_ruleNodeType3990);
            lv_posX_11_0=ruleEInt();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getNodeTypeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"posX",
            	        		lv_posX_11_0, 
            	        		"EInt", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,14,FOLLOW_14_in_ruleNodeType4000); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getCommaKeyword_6_1_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2688:1: ( (lv_posY_13_0= ruleEInt ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2689:1: (lv_posY_13_0= ruleEInt )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2689:1: (lv_posY_13_0= ruleEInt )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2690:3: lv_posY_13_0= ruleEInt
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getPosYEIntParserRuleCall_6_1_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEInt_in_ruleNodeType4021);
            lv_posY_13_0=ruleEInt();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getNodeTypeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"posY",
            	        		lv_posY_13_0, 
            	        		"EInt", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }


            }

            match(input,13,FOLLOW_13_in_ruleNodeType4033); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getRightCurlyBracketKeyword_7(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2716:1: ( (lv_metadata_15_0= ruleMetadataType ) )*
            loop57:
            do {
                int alt57=2;
                int LA57_0 = input.LA(1);

                if ( (LA57_0==70) ) {
                    alt57=1;
                }


                switch (alt57) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2717:1: (lv_metadata_15_0= ruleMetadataType )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2717:1: (lv_metadata_15_0= ruleMetadataType )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2718:3: lv_metadata_15_0= ruleMetadataType
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getMetadataMetadataTypeParserRuleCall_8_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleMetadataType_in_ruleNodeType4054);
            	    lv_metadata_15_0=ruleMetadataType();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getNodeTypeRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"metadata",
            	    	        		lv_metadata_15_0, 
            	    	        		"MetadataType", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop57;
                }
            } while (true);

            match(input,48,FOLLOW_48_in_ruleNodeType4065); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getSetSettingsKeyword_9(), null); 
                
            match(input,12,FOLLOW_12_in_ruleNodeType4075); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getLeftCurlyBracketKeyword_10(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2748:1: ( ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2749:1: ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2749:1: ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2750:1: (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2750:1: (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType )
            int alt58=2;
            switch ( input.LA(1) ) {
            case RULE_STRING:
                {
                int LA58_1 = input.LA(2);

                if ( (LA58_1==69) ) {
                    alt58=2;
                }
                else if ( (LA58_1==12) ) {
                    alt58=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("2750:1: (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType )", 58, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_ID:
                {
                int LA58_2 = input.LA(2);

                if ( (LA58_2==12) ) {
                    alt58=1;
                }
                else if ( (LA58_2==69) ) {
                    alt58=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("2750:1: (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType )", 58, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_INT:
                {
                int LA58_3 = input.LA(2);

                if ( (LA58_3==12) ) {
                    alt58=1;
                }
                else if ( (LA58_3==69) ) {
                    alt58=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("2750:1: (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType )", 58, 3, input);

                    throw nvae;
                }
                }
                break;
            case 13:
            case 14:
                {
                alt58=1;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("2750:1: (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType )", 58, 0, input);

                throw nvae;
            }

            switch (alt58) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2751:3: lv_elementParameter_18_1= ruleSchemaElementParameterType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getElementParameterSchemaElementParameterTypeParserRuleCall_11_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleSchemaElementParameterType_in_ruleNodeType4098);
                    lv_elementParameter_18_1=ruleSchemaElementParameterType();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getNodeTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"elementParameter",
                    	        		lv_elementParameter_18_1, 
                    	        		"SchemaElementParameterType", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;
                case 2 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2772:8: lv_elementParameter_18_2= ruleElementParameterType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getElementParameterElementParameterTypeParserRuleCall_11_0_1(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleElementParameterType_in_ruleNodeType4117);
                    lv_elementParameter_18_2=ruleElementParameterType();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getNodeTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"elementParameter",
                    	        		lv_elementParameter_18_2, 
                    	        		"ElementParameterType", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }
                    break;

            }


            }


            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2796:2: ( ',' ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) ) )*
            loop60:
            do {
                int alt60=2;
                int LA60_0 = input.LA(1);

                if ( (LA60_0==14) ) {
                    alt60=1;
                }


                switch (alt60) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2796:4: ',' ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) )
            	    {
            	    match(input,14,FOLLOW_14_in_ruleNodeType4131); 

            	            createLeafNode(grammarAccess.getNodeTypeAccess().getCommaKeyword_12_0(), null); 
            	        
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2800:1: ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2801:1: ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2801:1: ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2802:1: (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2802:1: (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType )
            	    int alt59=2;
            	    switch ( input.LA(1) ) {
            	    case RULE_STRING:
            	        {
            	        int LA59_1 = input.LA(2);

            	        if ( (LA59_1==69) ) {
            	            alt59=2;
            	        }
            	        else if ( (LA59_1==12) ) {
            	            alt59=1;
            	        }
            	        else {
            	            NoViableAltException nvae =
            	                new NoViableAltException("2802:1: (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType )", 59, 1, input);

            	            throw nvae;
            	        }
            	        }
            	        break;
            	    case RULE_ID:
            	        {
            	        int LA59_2 = input.LA(2);

            	        if ( (LA59_2==69) ) {
            	            alt59=2;
            	        }
            	        else if ( (LA59_2==12) ) {
            	            alt59=1;
            	        }
            	        else {
            	            NoViableAltException nvae =
            	                new NoViableAltException("2802:1: (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType )", 59, 2, input);

            	            throw nvae;
            	        }
            	        }
            	        break;
            	    case RULE_INT:
            	        {
            	        int LA59_3 = input.LA(2);

            	        if ( (LA59_3==69) ) {
            	            alt59=2;
            	        }
            	        else if ( (LA59_3==12) ) {
            	            alt59=1;
            	        }
            	        else {
            	            NoViableAltException nvae =
            	                new NoViableAltException("2802:1: (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType )", 59, 3, input);

            	            throw nvae;
            	        }
            	        }
            	        break;
            	    case 13:
            	    case 14:
            	        {
            	        alt59=1;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("2802:1: (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType )", 59, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt59) {
            	        case 1 :
            	            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2803:3: lv_elementParameter_20_1= ruleSchemaElementParameterType
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getElementParameterSchemaElementParameterTypeParserRuleCall_12_1_0_0(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_ruleSchemaElementParameterType_in_ruleNodeType4154);
            	            lv_elementParameter_20_1=ruleSchemaElementParameterType();
            	            _fsp--;


            	            	        if (current==null) {
            	            	            current = factory.create(grammarAccess.getNodeTypeRule().getType().getClassifier());
            	            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	            	        }
            	            	        try {
            	            	       		add(
            	            	       			current, 
            	            	       			"elementParameter",
            	            	        		lv_elementParameter_20_1, 
            	            	        		"SchemaElementParameterType", 
            	            	        		currentNode);
            	            	        } catch (ValueConverterException vce) {
            	            				handleValueConverterException(vce);
            	            	        }
            	            	        currentNode = currentNode.getParent();
            	            	    

            	            }
            	            break;
            	        case 2 :
            	            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2824:8: lv_elementParameter_20_2= ruleElementParameterType
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getElementParameterElementParameterTypeParserRuleCall_12_1_0_1(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_ruleElementParameterType_in_ruleNodeType4173);
            	            lv_elementParameter_20_2=ruleElementParameterType();
            	            _fsp--;


            	            	        if (current==null) {
            	            	            current = factory.create(grammarAccess.getNodeTypeRule().getType().getClassifier());
            	            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	            	        }
            	            	        try {
            	            	       		add(
            	            	       			current, 
            	            	       			"elementParameter",
            	            	        		lv_elementParameter_20_2, 
            	            	        		"ElementParameterType", 
            	            	        		currentNode);
            	            	        } catch (ValueConverterException vce) {
            	            				handleValueConverterException(vce);
            	            	        }
            	            	        currentNode = currentNode.getParent();
            	            	    

            	            }
            	            break;

            	    }


            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop60;
                }
            } while (true);

            match(input,13,FOLLOW_13_in_ruleNodeType4188); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getRightCurlyBracketKeyword_13(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2852:1: ( (lv_metadata_22_0= ruleMetadataType ) )*
            loop61:
            do {
                int alt61=2;
                int LA61_0 = input.LA(1);

                if ( (LA61_0==70) ) {
                    alt61=1;
                }


                switch (alt61) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2853:1: (lv_metadata_22_0= ruleMetadataType )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2853:1: (lv_metadata_22_0= ruleMetadataType )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2854:3: lv_metadata_22_0= ruleMetadataType
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getMetadataMetadataTypeParserRuleCall_14_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleMetadataType_in_ruleNodeType4209);
            	    lv_metadata_22_0=ruleMetadataType();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getNodeTypeRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"metadata",
            	    	        		lv_metadata_22_0, 
            	    	        		"MetadataType", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop61;
                }
            } while (true);

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2876:3: ( (lv_nodeData_23_0= ruleExternalData ) )?
            int alt62=2;
            int LA62_0 = input.LA(1);

            if ( (LA62_0==15||LA62_0==32) ) {
                alt62=1;
            }
            switch (alt62) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2877:1: (lv_nodeData_23_0= ruleExternalData )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2877:1: (lv_nodeData_23_0= ruleExternalData )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2878:3: lv_nodeData_23_0= ruleExternalData
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getNodeDataExternalDataParserRuleCall_15_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleExternalData_in_ruleNodeType4231);
                    lv_nodeData_23_0=ruleExternalData();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getNodeTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"nodeData",
                    	        		lv_nodeData_23_0, 
                    	        		"ExternalData", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }
                    break;

            }

            match(input,13,FOLLOW_13_in_ruleNodeType4242); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getRightCurlyBracketKeyword_16(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleNodeType


    // $ANTLR start entryRuleContextType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2912:1: entryRuleContextType returns [EObject current=null] : iv_ruleContextType= ruleContextType EOF ;
    public final EObject entryRuleContextType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContextType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2913:2: (iv_ruleContextType= ruleContextType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2914:2: iv_ruleContextType= ruleContextType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getContextTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleContextType_in_entryRuleContextType4278);
            iv_ruleContextType=ruleContextType();
            _fsp--;

             current =iv_ruleContextType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleContextType4288); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleContextType


    // $ANTLR start ruleContextType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2921:1: ruleContextType returns [EObject current=null] : ( () 'ContextType' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'CONFIRMATIONNEED:' ( (lv_confirmationNeeded_6_0= ruleEBoolean ) ) )? ( ( (lv_contextParameter_7_0= ruleContextParameterType ) ) ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )* )? '}' ) ;
    public final EObject ruleContextType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        AntlrDatatypeRuleToken lv_confirmationNeeded_6_0 = null;

        EObject lv_contextParameter_7_0 = null;

        EObject lv_contextParameter_9_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2926:6: ( ( () 'ContextType' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'CONFIRMATIONNEED:' ( (lv_confirmationNeeded_6_0= ruleEBoolean ) ) )? ( ( (lv_contextParameter_7_0= ruleContextParameterType ) ) ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )* )? '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2927:1: ( () 'ContextType' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'CONFIRMATIONNEED:' ( (lv_confirmationNeeded_6_0= ruleEBoolean ) ) )? ( ( (lv_contextParameter_7_0= ruleContextParameterType ) ) ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )* )? '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2927:1: ( () 'ContextType' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'CONFIRMATIONNEED:' ( (lv_confirmationNeeded_6_0= ruleEBoolean ) ) )? ( ( (lv_contextParameter_7_0= ruleContextParameterType ) ) ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )* )? '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2927:2: () 'ContextType' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'CONFIRMATIONNEED:' ( (lv_confirmationNeeded_6_0= ruleEBoolean ) ) )? ( ( (lv_contextParameter_7_0= ruleContextParameterType ) ) ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )* )? '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2927:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2928:5: 
            {
             
                    temp=factory.create(grammarAccess.getContextTypeAccess().getContextTypeAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getContextTypeAccess().getContextTypeAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,49,FOLLOW_49_in_ruleContextType4332); 

                    createLeafNode(grammarAccess.getContextTypeAccess().getContextTypeKeyword_1(), null); 
                
            match(input,12,FOLLOW_12_in_ruleContextType4342); 

                    createLeafNode(grammarAccess.getContextTypeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2946:1: ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )?
            int alt63=2;
            int LA63_0 = input.LA(1);

            if ( (LA63_0==19) ) {
                alt63=1;
            }
            switch (alt63) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2946:3: 'NAME:' ( (lv_name_4_0= ruleEString ) )
                    {
                    match(input,19,FOLLOW_19_in_ruleContextType4353); 

                            createLeafNode(grammarAccess.getContextTypeAccess().getNAMEKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2950:1: ( (lv_name_4_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2951:1: (lv_name_4_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2951:1: (lv_name_4_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2952:3: lv_name_4_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextTypeAccess().getNameEStringParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleContextType4374);
                    lv_name_4_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getContextTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"name",
                    	        		lv_name_4_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2974:4: ( 'CONFIRMATIONNEED:' ( (lv_confirmationNeeded_6_0= ruleEBoolean ) ) )?
            int alt64=2;
            int LA64_0 = input.LA(1);

            if ( (LA64_0==50) ) {
                alt64=1;
            }
            switch (alt64) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2974:6: 'CONFIRMATIONNEED:' ( (lv_confirmationNeeded_6_0= ruleEBoolean ) )
                    {
                    match(input,50,FOLLOW_50_in_ruleContextType4387); 

                            createLeafNode(grammarAccess.getContextTypeAccess().getCONFIRMATIONNEEDKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2978:1: ( (lv_confirmationNeeded_6_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2979:1: (lv_confirmationNeeded_6_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2979:1: (lv_confirmationNeeded_6_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2980:3: lv_confirmationNeeded_6_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextTypeAccess().getConfirmationNeededEBooleanParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleContextType4408);
                    lv_confirmationNeeded_6_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getContextTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"confirmationNeeded",
                    	        		lv_confirmationNeeded_6_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3002:4: ( ( (lv_contextParameter_7_0= ruleContextParameterType ) ) ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )* )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==51) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3002:5: ( (lv_contextParameter_7_0= ruleContextParameterType ) ) ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )*
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3002:5: ( (lv_contextParameter_7_0= ruleContextParameterType ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3003:1: (lv_contextParameter_7_0= ruleContextParameterType )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3003:1: (lv_contextParameter_7_0= ruleContextParameterType )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3004:3: lv_contextParameter_7_0= ruleContextParameterType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextTypeAccess().getContextParameterContextParameterTypeParserRuleCall_5_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleContextParameterType_in_ruleContextType4432);
                    lv_contextParameter_7_0=ruleContextParameterType();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getContextTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"contextParameter",
                    	        		lv_contextParameter_7_0, 
                    	        		"ContextParameterType", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3026:2: ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )*
                    loop65:
                    do {
                        int alt65=2;
                        int LA65_0 = input.LA(1);

                        if ( (LA65_0==14) ) {
                            alt65=1;
                        }


                        switch (alt65) {
                    	case 1 :
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3026:4: ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) )
                    	    {
                    	    match(input,14,FOLLOW_14_in_ruleContextType4443); 

                    	            createLeafNode(grammarAccess.getContextTypeAccess().getCommaKeyword_5_1_0(), null); 
                    	        
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3030:1: ( (lv_contextParameter_9_0= ruleContextParameterType ) )
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3031:1: (lv_contextParameter_9_0= ruleContextParameterType )
                    	    {
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3031:1: (lv_contextParameter_9_0= ruleContextParameterType )
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3032:3: lv_contextParameter_9_0= ruleContextParameterType
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getContextTypeAccess().getContextParameterContextParameterTypeParserRuleCall_5_1_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleContextParameterType_in_ruleContextType4464);
                    	    lv_contextParameter_9_0=ruleContextParameterType();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getContextTypeRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"contextParameter",
                    	    	        		lv_contextParameter_9_0, 
                    	    	        		"ContextParameterType", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop65;
                        }
                    } while (true);


                    }
                    break;

            }

            match(input,13,FOLLOW_13_in_ruleContextType4478); 

                    createLeafNode(grammarAccess.getContextTypeAccess().getRightCurlyBracketKeyword_6(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleContextType


    // $ANTLR start entryRuleContextParameterType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3066:1: entryRuleContextParameterType returns [EObject current=null] : iv_ruleContextParameterType= ruleContextParameterType EOF ;
    public final EObject entryRuleContextParameterType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContextParameterType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3067:2: (iv_ruleContextParameterType= ruleContextParameterType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3068:2: iv_ruleContextParameterType= ruleContextParameterType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getContextParameterTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleContextParameterType_in_entryRuleContextParameterType4514);
            iv_ruleContextParameterType=ruleContextParameterType();
            _fsp--;

             current =iv_ruleContextParameterType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleContextParameterType4524); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleContextParameterType


    // $ANTLR start ruleContextParameterType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3075:1: ruleContextParameterType returns [EObject current=null] : ( () 'addContextParameter' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? ( 'VALUE:' ( (lv_value_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'PROMPT:' ( (lv_prompt_12_0= ruleEString ) ) )? ( 'PROMPTNEEDED:' ( (lv_promptNeeded_14_0= ruleEBoolean ) ) )? ( 'REPOSITORYCONTEXTID:' ( (lv_repositoryContextId_16_0= ruleEString ) ) )? '}' ) ;
    public final EObject ruleContextParameterType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        AntlrDatatypeRuleToken lv_type_6_0 = null;

        AntlrDatatypeRuleToken lv_value_8_0 = null;

        AntlrDatatypeRuleToken lv_comment_10_0 = null;

        AntlrDatatypeRuleToken lv_prompt_12_0 = null;

        AntlrDatatypeRuleToken lv_promptNeeded_14_0 = null;

        AntlrDatatypeRuleToken lv_repositoryContextId_16_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3080:6: ( ( () 'addContextParameter' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? ( 'VALUE:' ( (lv_value_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'PROMPT:' ( (lv_prompt_12_0= ruleEString ) ) )? ( 'PROMPTNEEDED:' ( (lv_promptNeeded_14_0= ruleEBoolean ) ) )? ( 'REPOSITORYCONTEXTID:' ( (lv_repositoryContextId_16_0= ruleEString ) ) )? '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3081:1: ( () 'addContextParameter' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? ( 'VALUE:' ( (lv_value_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'PROMPT:' ( (lv_prompt_12_0= ruleEString ) ) )? ( 'PROMPTNEEDED:' ( (lv_promptNeeded_14_0= ruleEBoolean ) ) )? ( 'REPOSITORYCONTEXTID:' ( (lv_repositoryContextId_16_0= ruleEString ) ) )? '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3081:1: ( () 'addContextParameter' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? ( 'VALUE:' ( (lv_value_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'PROMPT:' ( (lv_prompt_12_0= ruleEString ) ) )? ( 'PROMPTNEEDED:' ( (lv_promptNeeded_14_0= ruleEBoolean ) ) )? ( 'REPOSITORYCONTEXTID:' ( (lv_repositoryContextId_16_0= ruleEString ) ) )? '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3081:2: () 'addContextParameter' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? ( 'VALUE:' ( (lv_value_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'PROMPT:' ( (lv_prompt_12_0= ruleEString ) ) )? ( 'PROMPTNEEDED:' ( (lv_promptNeeded_14_0= ruleEBoolean ) ) )? ( 'REPOSITORYCONTEXTID:' ( (lv_repositoryContextId_16_0= ruleEString ) ) )? '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3081:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3082:5: 
            {
             
                    temp=factory.create(grammarAccess.getContextParameterTypeAccess().getContextParameterTypeAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getContextParameterTypeAccess().getContextParameterTypeAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,51,FOLLOW_51_in_ruleContextParameterType4568); 

                    createLeafNode(grammarAccess.getContextParameterTypeAccess().getAddContextParameterKeyword_1(), null); 
                
            match(input,12,FOLLOW_12_in_ruleContextParameterType4578); 

                    createLeafNode(grammarAccess.getContextParameterTypeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3100:1: ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==19) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3100:3: 'NAME:' ( (lv_name_4_0= ruleEString ) )
                    {
                    match(input,19,FOLLOW_19_in_ruleContextParameterType4589); 

                            createLeafNode(grammarAccess.getContextParameterTypeAccess().getNAMEKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3104:1: ( (lv_name_4_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3105:1: (lv_name_4_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3105:1: (lv_name_4_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3106:3: lv_name_4_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextParameterTypeAccess().getNameEStringParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleContextParameterType4610);
                    lv_name_4_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getContextParameterTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"name",
                    	        		lv_name_4_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3128:4: ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==26) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3128:6: 'TYPE:' ( (lv_type_6_0= ruleEString ) )
                    {
                    match(input,26,FOLLOW_26_in_ruleContextParameterType4623); 

                            createLeafNode(grammarAccess.getContextParameterTypeAccess().getTYPEKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3132:1: ( (lv_type_6_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3133:1: (lv_type_6_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3133:1: (lv_type_6_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3134:3: lv_type_6_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextParameterTypeAccess().getTypeEStringParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleContextParameterType4644);
                    lv_type_6_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getContextParameterTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"type",
                    	        		lv_type_6_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3156:4: ( 'VALUE:' ( (lv_value_8_0= ruleEString ) ) )?
            int alt69=2;
            int LA69_0 = input.LA(1);

            if ( (LA69_0==52) ) {
                alt69=1;
            }
            switch (alt69) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3156:6: 'VALUE:' ( (lv_value_8_0= ruleEString ) )
                    {
                    match(input,52,FOLLOW_52_in_ruleContextParameterType4657); 

                            createLeafNode(grammarAccess.getContextParameterTypeAccess().getVALUEKeyword_5_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3160:1: ( (lv_value_8_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3161:1: (lv_value_8_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3161:1: (lv_value_8_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3162:3: lv_value_8_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextParameterTypeAccess().getValueEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleContextParameterType4678);
                    lv_value_8_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getContextParameterTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"value",
                    	        		lv_value_8_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3184:4: ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==53) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3184:6: 'COMMENT:' ( (lv_comment_10_0= ruleEString ) )
                    {
                    match(input,53,FOLLOW_53_in_ruleContextParameterType4691); 

                            createLeafNode(grammarAccess.getContextParameterTypeAccess().getCOMMENTKeyword_6_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3188:1: ( (lv_comment_10_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3189:1: (lv_comment_10_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3189:1: (lv_comment_10_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3190:3: lv_comment_10_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextParameterTypeAccess().getCommentEStringParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleContextParameterType4712);
                    lv_comment_10_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getContextParameterTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"comment",
                    	        		lv_comment_10_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3212:4: ( 'PROMPT:' ( (lv_prompt_12_0= ruleEString ) ) )?
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==54) ) {
                alt71=1;
            }
            switch (alt71) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3212:6: 'PROMPT:' ( (lv_prompt_12_0= ruleEString ) )
                    {
                    match(input,54,FOLLOW_54_in_ruleContextParameterType4725); 

                            createLeafNode(grammarAccess.getContextParameterTypeAccess().getPROMPTKeyword_7_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3216:1: ( (lv_prompt_12_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3217:1: (lv_prompt_12_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3217:1: (lv_prompt_12_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3218:3: lv_prompt_12_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextParameterTypeAccess().getPromptEStringParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleContextParameterType4746);
                    lv_prompt_12_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getContextParameterTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"prompt",
                    	        		lv_prompt_12_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3240:4: ( 'PROMPTNEEDED:' ( (lv_promptNeeded_14_0= ruleEBoolean ) ) )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==55) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3240:6: 'PROMPTNEEDED:' ( (lv_promptNeeded_14_0= ruleEBoolean ) )
                    {
                    match(input,55,FOLLOW_55_in_ruleContextParameterType4759); 

                            createLeafNode(grammarAccess.getContextParameterTypeAccess().getPROMPTNEEDEDKeyword_8_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3244:1: ( (lv_promptNeeded_14_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3245:1: (lv_promptNeeded_14_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3245:1: (lv_promptNeeded_14_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3246:3: lv_promptNeeded_14_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextParameterTypeAccess().getPromptNeededEBooleanParserRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleContextParameterType4780);
                    lv_promptNeeded_14_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getContextParameterTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"promptNeeded",
                    	        		lv_promptNeeded_14_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3268:4: ( 'REPOSITORYCONTEXTID:' ( (lv_repositoryContextId_16_0= ruleEString ) ) )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==56) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3268:6: 'REPOSITORYCONTEXTID:' ( (lv_repositoryContextId_16_0= ruleEString ) )
                    {
                    match(input,56,FOLLOW_56_in_ruleContextParameterType4793); 

                            createLeafNode(grammarAccess.getContextParameterTypeAccess().getREPOSITORYCONTEXTIDKeyword_9_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3272:1: ( (lv_repositoryContextId_16_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3273:1: (lv_repositoryContextId_16_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3273:1: (lv_repositoryContextId_16_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3274:3: lv_repositoryContextId_16_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextParameterTypeAccess().getRepositoryContextIdEStringParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleContextParameterType4814);
                    lv_repositoryContextId_16_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getContextParameterTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"repositoryContextId",
                    	        		lv_repositoryContextId_16_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            match(input,13,FOLLOW_13_in_ruleContextParameterType4826); 

                    createLeafNode(grammarAccess.getContextParameterTypeAccess().getRightCurlyBracketKeyword_10(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleContextParameterType


    // $ANTLR start entryRuleUniqueNameType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3308:1: entryRuleUniqueNameType returns [EObject current=null] : iv_ruleUniqueNameType= ruleUniqueNameType EOF ;
    public final EObject entryRuleUniqueNameType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUniqueNameType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3309:2: (iv_ruleUniqueNameType= ruleUniqueNameType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3310:2: iv_ruleUniqueNameType= ruleUniqueNameType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getUniqueNameTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleUniqueNameType_in_entryRuleUniqueNameType4862);
            iv_ruleUniqueNameType=ruleUniqueNameType();
            _fsp--;

             current =iv_ruleUniqueNameType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUniqueNameType4872); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleUniqueNameType


    // $ANTLR start ruleUniqueNameType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3317:1: ruleUniqueNameType returns [EObject current=null] : ( (lv_value_0_0= ruleEString ) ) ;
    public final EObject ruleUniqueNameType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_value_0_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3322:6: ( ( (lv_value_0_0= ruleEString ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3323:1: ( (lv_value_0_0= ruleEString ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3323:1: ( (lv_value_0_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3324:1: (lv_value_0_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3324:1: (lv_value_0_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3325:3: lv_value_0_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getUniqueNameTypeAccess().getValueEStringParserRuleCall_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleUniqueNameType4917);
            lv_value_0_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getUniqueNameTypeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"value",
            	        		lv_value_0_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleUniqueNameType


    // $ANTLR start entryRuleConnectionType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3355:1: entryRuleConnectionType returns [EObject current=null] : iv_ruleConnectionType= ruleConnectionType EOF ;
    public final EObject entryRuleConnectionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConnectionType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3356:2: (iv_ruleConnectionType= ruleConnectionType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3357:2: iv_ruleConnectionType= ruleConnectionType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getConnectionTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleConnectionType_in_entryRuleConnectionType4952);
            iv_ruleConnectionType=ruleConnectionType();
            _fsp--;

             current =iv_ruleConnectionType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleConnectionType4962); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleConnectionType


    // $ANTLR start ruleConnectionType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3364:1: ruleConnectionType returns [EObject current=null] : ( () 'addConnection' '{' ( 'TYPE:' ( (lv_connectorName_4_0= ruleEString ) ) ) ( ',' 'NAME:' ( (lv_label_7_0= ruleEString ) ) ) ( ',' 'LINESTYLE:' ( (lv_lineStyle_10_0= ruleEInt ) ) )? ( ',' 'MERGEORDER:' ( (lv_mergeOrder_13_0= ruleEInt ) ) )? ( ',' 'METANAME:' ( (lv_metaname_16_0= ruleEString ) ) )? ( ',' 'OUTPUTID:' ( (lv_outputId_19_0= ruleEInt ) ) )? ( ',' 'SOURCE:' ( (lv_source_22_0= ruleEString ) ) )? ( ',' 'TARGET:' ( (lv_target_25_0= ruleEString ) ) )? ( ',' ( (lv_elementParameter_27_0= ruleElementParameterType ) ) )? '}' ) ;
    public final EObject ruleConnectionType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_connectorName_4_0 = null;

        AntlrDatatypeRuleToken lv_label_7_0 = null;

        AntlrDatatypeRuleToken lv_lineStyle_10_0 = null;

        AntlrDatatypeRuleToken lv_mergeOrder_13_0 = null;

        AntlrDatatypeRuleToken lv_metaname_16_0 = null;

        AntlrDatatypeRuleToken lv_outputId_19_0 = null;

        AntlrDatatypeRuleToken lv_source_22_0 = null;

        AntlrDatatypeRuleToken lv_target_25_0 = null;

        EObject lv_elementParameter_27_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3369:6: ( ( () 'addConnection' '{' ( 'TYPE:' ( (lv_connectorName_4_0= ruleEString ) ) ) ( ',' 'NAME:' ( (lv_label_7_0= ruleEString ) ) ) ( ',' 'LINESTYLE:' ( (lv_lineStyle_10_0= ruleEInt ) ) )? ( ',' 'MERGEORDER:' ( (lv_mergeOrder_13_0= ruleEInt ) ) )? ( ',' 'METANAME:' ( (lv_metaname_16_0= ruleEString ) ) )? ( ',' 'OUTPUTID:' ( (lv_outputId_19_0= ruleEInt ) ) )? ( ',' 'SOURCE:' ( (lv_source_22_0= ruleEString ) ) )? ( ',' 'TARGET:' ( (lv_target_25_0= ruleEString ) ) )? ( ',' ( (lv_elementParameter_27_0= ruleElementParameterType ) ) )? '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3370:1: ( () 'addConnection' '{' ( 'TYPE:' ( (lv_connectorName_4_0= ruleEString ) ) ) ( ',' 'NAME:' ( (lv_label_7_0= ruleEString ) ) ) ( ',' 'LINESTYLE:' ( (lv_lineStyle_10_0= ruleEInt ) ) )? ( ',' 'MERGEORDER:' ( (lv_mergeOrder_13_0= ruleEInt ) ) )? ( ',' 'METANAME:' ( (lv_metaname_16_0= ruleEString ) ) )? ( ',' 'OUTPUTID:' ( (lv_outputId_19_0= ruleEInt ) ) )? ( ',' 'SOURCE:' ( (lv_source_22_0= ruleEString ) ) )? ( ',' 'TARGET:' ( (lv_target_25_0= ruleEString ) ) )? ( ',' ( (lv_elementParameter_27_0= ruleElementParameterType ) ) )? '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3370:1: ( () 'addConnection' '{' ( 'TYPE:' ( (lv_connectorName_4_0= ruleEString ) ) ) ( ',' 'NAME:' ( (lv_label_7_0= ruleEString ) ) ) ( ',' 'LINESTYLE:' ( (lv_lineStyle_10_0= ruleEInt ) ) )? ( ',' 'MERGEORDER:' ( (lv_mergeOrder_13_0= ruleEInt ) ) )? ( ',' 'METANAME:' ( (lv_metaname_16_0= ruleEString ) ) )? ( ',' 'OUTPUTID:' ( (lv_outputId_19_0= ruleEInt ) ) )? ( ',' 'SOURCE:' ( (lv_source_22_0= ruleEString ) ) )? ( ',' 'TARGET:' ( (lv_target_25_0= ruleEString ) ) )? ( ',' ( (lv_elementParameter_27_0= ruleElementParameterType ) ) )? '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3370:2: () 'addConnection' '{' ( 'TYPE:' ( (lv_connectorName_4_0= ruleEString ) ) ) ( ',' 'NAME:' ( (lv_label_7_0= ruleEString ) ) ) ( ',' 'LINESTYLE:' ( (lv_lineStyle_10_0= ruleEInt ) ) )? ( ',' 'MERGEORDER:' ( (lv_mergeOrder_13_0= ruleEInt ) ) )? ( ',' 'METANAME:' ( (lv_metaname_16_0= ruleEString ) ) )? ( ',' 'OUTPUTID:' ( (lv_outputId_19_0= ruleEInt ) ) )? ( ',' 'SOURCE:' ( (lv_source_22_0= ruleEString ) ) )? ( ',' 'TARGET:' ( (lv_target_25_0= ruleEString ) ) )? ( ',' ( (lv_elementParameter_27_0= ruleElementParameterType ) ) )? '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3370:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3371:5: 
            {
             
                    temp=factory.create(grammarAccess.getConnectionTypeAccess().getConnectionTypeAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getConnectionTypeAccess().getConnectionTypeAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,57,FOLLOW_57_in_ruleConnectionType5006); 

                    createLeafNode(grammarAccess.getConnectionTypeAccess().getAddConnectionKeyword_1(), null); 
                
            match(input,12,FOLLOW_12_in_ruleConnectionType5016); 

                    createLeafNode(grammarAccess.getConnectionTypeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3389:1: ( 'TYPE:' ( (lv_connectorName_4_0= ruleEString ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3389:3: 'TYPE:' ( (lv_connectorName_4_0= ruleEString ) )
            {
            match(input,26,FOLLOW_26_in_ruleConnectionType5027); 

                    createLeafNode(grammarAccess.getConnectionTypeAccess().getTYPEKeyword_3_0(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3393:1: ( (lv_connectorName_4_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3394:1: (lv_connectorName_4_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3394:1: (lv_connectorName_4_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3395:3: lv_connectorName_4_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getConnectorNameEStringParserRuleCall_3_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleConnectionType5048);
            lv_connectorName_4_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getConnectionTypeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"connectorName",
            	        		lv_connectorName_4_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3417:3: ( ',' 'NAME:' ( (lv_label_7_0= ruleEString ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3417:5: ',' 'NAME:' ( (lv_label_7_0= ruleEString ) )
            {
            match(input,14,FOLLOW_14_in_ruleConnectionType5060); 

                    createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_4_0(), null); 
                
            match(input,19,FOLLOW_19_in_ruleConnectionType5070); 

                    createLeafNode(grammarAccess.getConnectionTypeAccess().getNAMEKeyword_4_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3425:1: ( (lv_label_7_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3426:1: (lv_label_7_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3426:1: (lv_label_7_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3427:3: lv_label_7_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getLabelEStringParserRuleCall_4_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleConnectionType5091);
            lv_label_7_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getConnectionTypeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"label",
            	        		lv_label_7_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3449:3: ( ',' 'LINESTYLE:' ( (lv_lineStyle_10_0= ruleEInt ) ) )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==14) ) {
                int LA74_1 = input.LA(2);

                if ( (LA74_1==58) ) {
                    alt74=1;
                }
            }
            switch (alt74) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3449:5: ',' 'LINESTYLE:' ( (lv_lineStyle_10_0= ruleEInt ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleConnectionType5103); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_5_0(), null); 
                        
                    match(input,58,FOLLOW_58_in_ruleConnectionType5113); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getLINESTYLEKeyword_5_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3457:1: ( (lv_lineStyle_10_0= ruleEInt ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3458:1: (lv_lineStyle_10_0= ruleEInt )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3458:1: (lv_lineStyle_10_0= ruleEInt )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3459:3: lv_lineStyle_10_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getLineStyleEIntParserRuleCall_5_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEInt_in_ruleConnectionType5134);
                    lv_lineStyle_10_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getConnectionTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"lineStyle",
                    	        		lv_lineStyle_10_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3481:4: ( ',' 'MERGEORDER:' ( (lv_mergeOrder_13_0= ruleEInt ) ) )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==14) ) {
                int LA75_1 = input.LA(2);

                if ( (LA75_1==59) ) {
                    alt75=1;
                }
            }
            switch (alt75) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3481:6: ',' 'MERGEORDER:' ( (lv_mergeOrder_13_0= ruleEInt ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleConnectionType5147); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_6_0(), null); 
                        
                    match(input,59,FOLLOW_59_in_ruleConnectionType5157); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getMERGEORDERKeyword_6_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3489:1: ( (lv_mergeOrder_13_0= ruleEInt ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3490:1: (lv_mergeOrder_13_0= ruleEInt )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3490:1: (lv_mergeOrder_13_0= ruleEInt )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3491:3: lv_mergeOrder_13_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getMergeOrderEIntParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEInt_in_ruleConnectionType5178);
                    lv_mergeOrder_13_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getConnectionTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"mergeOrder",
                    	        		lv_mergeOrder_13_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3513:4: ( ',' 'METANAME:' ( (lv_metaname_16_0= ruleEString ) ) )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==14) ) {
                int LA76_1 = input.LA(2);

                if ( (LA76_1==60) ) {
                    alt76=1;
                }
            }
            switch (alt76) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3513:6: ',' 'METANAME:' ( (lv_metaname_16_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleConnectionType5191); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_7_0(), null); 
                        
                    match(input,60,FOLLOW_60_in_ruleConnectionType5201); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getMETANAMEKeyword_7_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3521:1: ( (lv_metaname_16_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3522:1: (lv_metaname_16_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3522:1: (lv_metaname_16_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3523:3: lv_metaname_16_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getMetanameEStringParserRuleCall_7_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleConnectionType5222);
                    lv_metaname_16_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getConnectionTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"metaname",
                    	        		lv_metaname_16_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3545:4: ( ',' 'OUTPUTID:' ( (lv_outputId_19_0= ruleEInt ) ) )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==14) ) {
                int LA77_1 = input.LA(2);

                if ( (LA77_1==61) ) {
                    alt77=1;
                }
            }
            switch (alt77) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3545:6: ',' 'OUTPUTID:' ( (lv_outputId_19_0= ruleEInt ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleConnectionType5235); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_8_0(), null); 
                        
                    match(input,61,FOLLOW_61_in_ruleConnectionType5245); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getOUTPUTIDKeyword_8_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3553:1: ( (lv_outputId_19_0= ruleEInt ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3554:1: (lv_outputId_19_0= ruleEInt )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3554:1: (lv_outputId_19_0= ruleEInt )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3555:3: lv_outputId_19_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getOutputIdEIntParserRuleCall_8_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEInt_in_ruleConnectionType5266);
                    lv_outputId_19_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getConnectionTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"outputId",
                    	        		lv_outputId_19_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3577:4: ( ',' 'SOURCE:' ( (lv_source_22_0= ruleEString ) ) )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==14) ) {
                int LA78_1 = input.LA(2);

                if ( (LA78_1==62) ) {
                    alt78=1;
                }
            }
            switch (alt78) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3577:6: ',' 'SOURCE:' ( (lv_source_22_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleConnectionType5279); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_9_0(), null); 
                        
                    match(input,62,FOLLOW_62_in_ruleConnectionType5289); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getSOURCEKeyword_9_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3585:1: ( (lv_source_22_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3586:1: (lv_source_22_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3586:1: (lv_source_22_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3587:3: lv_source_22_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getSourceEStringParserRuleCall_9_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleConnectionType5310);
                    lv_source_22_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getConnectionTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"source",
                    	        		lv_source_22_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3609:4: ( ',' 'TARGET:' ( (lv_target_25_0= ruleEString ) ) )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==14) ) {
                int LA79_1 = input.LA(2);

                if ( (LA79_1==63) ) {
                    alt79=1;
                }
            }
            switch (alt79) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3609:6: ',' 'TARGET:' ( (lv_target_25_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleConnectionType5323); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_10_0(), null); 
                        
                    match(input,63,FOLLOW_63_in_ruleConnectionType5333); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getTARGETKeyword_10_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3617:1: ( (lv_target_25_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3618:1: (lv_target_25_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3618:1: (lv_target_25_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3619:3: lv_target_25_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getTargetEStringParserRuleCall_10_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleConnectionType5354);
                    lv_target_25_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getConnectionTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"target",
                    	        		lv_target_25_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3641:4: ( ',' ( (lv_elementParameter_27_0= ruleElementParameterType ) ) )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==14) ) {
                alt80=1;
            }
            switch (alt80) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3641:6: ',' ( (lv_elementParameter_27_0= ruleElementParameterType ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleConnectionType5367); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_11_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3645:1: ( (lv_elementParameter_27_0= ruleElementParameterType ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3646:1: (lv_elementParameter_27_0= ruleElementParameterType )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3646:1: (lv_elementParameter_27_0= ruleElementParameterType )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3647:3: lv_elementParameter_27_0= ruleElementParameterType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getElementParameterElementParameterTypeParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleElementParameterType_in_ruleConnectionType5388);
                    lv_elementParameter_27_0=ruleElementParameterType();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getConnectionTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"elementParameter",
                    	        		lv_elementParameter_27_0, 
                    	        		"ElementParameterType", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            match(input,13,FOLLOW_13_in_ruleConnectionType5400); 

                    createLeafNode(grammarAccess.getConnectionTypeAccess().getRightCurlyBracketKeyword_12(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleConnectionType


    // $ANTLR start entryRuleNoteType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3681:1: entryRuleNoteType returns [EObject current=null] : iv_ruleNoteType= ruleNoteType EOF ;
    public final EObject entryRuleNoteType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNoteType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3682:2: (iv_ruleNoteType= ruleNoteType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3683:2: iv_ruleNoteType= ruleNoteType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNoteTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleNoteType_in_entryRuleNoteType5436);
            iv_ruleNoteType=ruleNoteType();
            _fsp--;

             current =iv_ruleNoteType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNoteType5446); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleNoteType


    // $ANTLR start ruleNoteType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3690:1: ruleNoteType returns [EObject current=null] : ( 'addNote' '{' ( 'opaque' ( (lv_opaque_3_0= ruleEBoolean ) ) )? ( 'POSITION' '\"' ( (lv_posX_6_0= ruleEInt ) ) ',' ( (lv_posY_8_0= ruleEInt ) ) '\"' )? ( 'text' ( (lv_text_11_0= ruleEString ) ) )? ( (lv_elementParameter_12_0= ruleElementParameterType ) ) ( ',' ( (lv_elementParameter_14_0= ruleElementParameterType ) ) )* '}' ) ;
    public final EObject ruleNoteType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_opaque_3_0 = null;

        AntlrDatatypeRuleToken lv_posX_6_0 = null;

        AntlrDatatypeRuleToken lv_posY_8_0 = null;

        AntlrDatatypeRuleToken lv_text_11_0 = null;

        EObject lv_elementParameter_12_0 = null;

        EObject lv_elementParameter_14_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3695:6: ( ( 'addNote' '{' ( 'opaque' ( (lv_opaque_3_0= ruleEBoolean ) ) )? ( 'POSITION' '\"' ( (lv_posX_6_0= ruleEInt ) ) ',' ( (lv_posY_8_0= ruleEInt ) ) '\"' )? ( 'text' ( (lv_text_11_0= ruleEString ) ) )? ( (lv_elementParameter_12_0= ruleElementParameterType ) ) ( ',' ( (lv_elementParameter_14_0= ruleElementParameterType ) ) )* '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3696:1: ( 'addNote' '{' ( 'opaque' ( (lv_opaque_3_0= ruleEBoolean ) ) )? ( 'POSITION' '\"' ( (lv_posX_6_0= ruleEInt ) ) ',' ( (lv_posY_8_0= ruleEInt ) ) '\"' )? ( 'text' ( (lv_text_11_0= ruleEString ) ) )? ( (lv_elementParameter_12_0= ruleElementParameterType ) ) ( ',' ( (lv_elementParameter_14_0= ruleElementParameterType ) ) )* '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3696:1: ( 'addNote' '{' ( 'opaque' ( (lv_opaque_3_0= ruleEBoolean ) ) )? ( 'POSITION' '\"' ( (lv_posX_6_0= ruleEInt ) ) ',' ( (lv_posY_8_0= ruleEInt ) ) '\"' )? ( 'text' ( (lv_text_11_0= ruleEString ) ) )? ( (lv_elementParameter_12_0= ruleElementParameterType ) ) ( ',' ( (lv_elementParameter_14_0= ruleElementParameterType ) ) )* '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3696:3: 'addNote' '{' ( 'opaque' ( (lv_opaque_3_0= ruleEBoolean ) ) )? ( 'POSITION' '\"' ( (lv_posX_6_0= ruleEInt ) ) ',' ( (lv_posY_8_0= ruleEInt ) ) '\"' )? ( 'text' ( (lv_text_11_0= ruleEString ) ) )? ( (lv_elementParameter_12_0= ruleElementParameterType ) ) ( ',' ( (lv_elementParameter_14_0= ruleElementParameterType ) ) )* '}'
            {
            match(input,64,FOLLOW_64_in_ruleNoteType5481); 

                    createLeafNode(grammarAccess.getNoteTypeAccess().getAddNoteKeyword_0(), null); 
                
            match(input,12,FOLLOW_12_in_ruleNoteType5491); 

                    createLeafNode(grammarAccess.getNoteTypeAccess().getLeftCurlyBracketKeyword_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3704:1: ( 'opaque' ( (lv_opaque_3_0= ruleEBoolean ) ) )?
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==65) ) {
                alt81=1;
            }
            switch (alt81) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3704:3: 'opaque' ( (lv_opaque_3_0= ruleEBoolean ) )
                    {
                    match(input,65,FOLLOW_65_in_ruleNoteType5502); 

                            createLeafNode(grammarAccess.getNoteTypeAccess().getOpaqueKeyword_2_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3708:1: ( (lv_opaque_3_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3709:1: (lv_opaque_3_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3709:1: (lv_opaque_3_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3710:3: lv_opaque_3_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNoteTypeAccess().getOpaqueEBooleanParserRuleCall_2_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleNoteType5523);
                    lv_opaque_3_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getNoteTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"opaque",
                    	        		lv_opaque_3_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3732:4: ( 'POSITION' '\"' ( (lv_posX_6_0= ruleEInt ) ) ',' ( (lv_posY_8_0= ruleEInt ) ) '\"' )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==66) ) {
                alt82=1;
            }
            switch (alt82) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3732:6: 'POSITION' '\"' ( (lv_posX_6_0= ruleEInt ) ) ',' ( (lv_posY_8_0= ruleEInt ) ) '\"'
                    {
                    match(input,66,FOLLOW_66_in_ruleNoteType5536); 

                            createLeafNode(grammarAccess.getNoteTypeAccess().getPOSITIONKeyword_3_0(), null); 
                        
                    match(input,67,FOLLOW_67_in_ruleNoteType5546); 

                            createLeafNode(grammarAccess.getNoteTypeAccess().getQuotationMarkKeyword_3_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3740:1: ( (lv_posX_6_0= ruleEInt ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3741:1: (lv_posX_6_0= ruleEInt )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3741:1: (lv_posX_6_0= ruleEInt )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3742:3: lv_posX_6_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNoteTypeAccess().getPosXEIntParserRuleCall_3_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEInt_in_ruleNoteType5567);
                    lv_posX_6_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getNoteTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"posX",
                    	        		lv_posX_6_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    match(input,14,FOLLOW_14_in_ruleNoteType5577); 

                            createLeafNode(grammarAccess.getNoteTypeAccess().getCommaKeyword_3_3(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3768:1: ( (lv_posY_8_0= ruleEInt ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3769:1: (lv_posY_8_0= ruleEInt )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3769:1: (lv_posY_8_0= ruleEInt )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3770:3: lv_posY_8_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNoteTypeAccess().getPosYEIntParserRuleCall_3_4_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEInt_in_ruleNoteType5598);
                    lv_posY_8_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getNoteTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"posY",
                    	        		lv_posY_8_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    match(input,67,FOLLOW_67_in_ruleNoteType5608); 

                            createLeafNode(grammarAccess.getNoteTypeAccess().getQuotationMarkKeyword_3_5(), null); 
                        

                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3796:3: ( 'text' ( (lv_text_11_0= ruleEString ) ) )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==68) ) {
                alt83=1;
            }
            switch (alt83) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3796:5: 'text' ( (lv_text_11_0= ruleEString ) )
                    {
                    match(input,68,FOLLOW_68_in_ruleNoteType5621); 

                            createLeafNode(grammarAccess.getNoteTypeAccess().getTextKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3800:1: ( (lv_text_11_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3801:1: (lv_text_11_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3801:1: (lv_text_11_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3802:3: lv_text_11_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNoteTypeAccess().getTextEStringParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleNoteType5642);
                    lv_text_11_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getNoteTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"text",
                    	        		lv_text_11_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3824:4: ( (lv_elementParameter_12_0= ruleElementParameterType ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3825:1: (lv_elementParameter_12_0= ruleElementParameterType )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3825:1: (lv_elementParameter_12_0= ruleElementParameterType )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3826:3: lv_elementParameter_12_0= ruleElementParameterType
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNoteTypeAccess().getElementParameterElementParameterTypeParserRuleCall_5_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleElementParameterType_in_ruleNoteType5665);
            lv_elementParameter_12_0=ruleElementParameterType();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getNoteTypeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		add(
            	       			current, 
            	       			"elementParameter",
            	        		lv_elementParameter_12_0, 
            	        		"ElementParameterType", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3848:2: ( ',' ( (lv_elementParameter_14_0= ruleElementParameterType ) ) )*
            loop84:
            do {
                int alt84=2;
                int LA84_0 = input.LA(1);

                if ( (LA84_0==14) ) {
                    alt84=1;
                }


                switch (alt84) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3848:4: ',' ( (lv_elementParameter_14_0= ruleElementParameterType ) )
            	    {
            	    match(input,14,FOLLOW_14_in_ruleNoteType5676); 

            	            createLeafNode(grammarAccess.getNoteTypeAccess().getCommaKeyword_6_0(), null); 
            	        
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3852:1: ( (lv_elementParameter_14_0= ruleElementParameterType ) )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3853:1: (lv_elementParameter_14_0= ruleElementParameterType )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3853:1: (lv_elementParameter_14_0= ruleElementParameterType )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3854:3: lv_elementParameter_14_0= ruleElementParameterType
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getNoteTypeAccess().getElementParameterElementParameterTypeParserRuleCall_6_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleElementParameterType_in_ruleNoteType5697);
            	    lv_elementParameter_14_0=ruleElementParameterType();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getNoteTypeRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"elementParameter",
            	    	        		lv_elementParameter_14_0, 
            	    	        		"ElementParameterType", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }


            	    }
            	    break;

            	default :
            	    break loop84;
                }
            } while (true);

            match(input,13,FOLLOW_13_in_ruleNoteType5709); 

                    createLeafNode(grammarAccess.getNoteTypeAccess().getRightCurlyBracketKeyword_7(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleNoteType


    // $ANTLR start entryRuleElementParameterType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3888:1: entryRuleElementParameterType returns [EObject current=null] : iv_ruleElementParameterType= ruleElementParameterType EOF ;
    public final EObject entryRuleElementParameterType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElementParameterType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3889:2: (iv_ruleElementParameterType= ruleElementParameterType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3890:2: iv_ruleElementParameterType= ruleElementParameterType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getElementParameterTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleElementParameterType_in_entryRuleElementParameterType5745);
            iv_ruleElementParameterType=ruleElementParameterType();
            _fsp--;

             current =iv_ruleElementParameterType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleElementParameterType5755); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleElementParameterType


    // $ANTLR start ruleElementParameterType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3897:1: ruleElementParameterType returns [EObject current=null] : ( () ( ( (lv_name_1_0= ruleEString ) ) ':' ( (lv_value_3_0= ruleEString ) ) ) ) ;
    public final EObject ruleElementParameterType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        AntlrDatatypeRuleToken lv_value_3_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3902:6: ( ( () ( ( (lv_name_1_0= ruleEString ) ) ':' ( (lv_value_3_0= ruleEString ) ) ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3903:1: ( () ( ( (lv_name_1_0= ruleEString ) ) ':' ( (lv_value_3_0= ruleEString ) ) ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3903:1: ( () ( ( (lv_name_1_0= ruleEString ) ) ':' ( (lv_value_3_0= ruleEString ) ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3903:2: () ( ( (lv_name_1_0= ruleEString ) ) ':' ( (lv_value_3_0= ruleEString ) ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3903:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3904:5: 
            {
             
                    temp=factory.create(grammarAccess.getElementParameterTypeAccess().getElementParameterTypeAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getElementParameterTypeAccess().getElementParameterTypeAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3914:2: ( ( (lv_name_1_0= ruleEString ) ) ':' ( (lv_value_3_0= ruleEString ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3914:3: ( (lv_name_1_0= ruleEString ) ) ':' ( (lv_value_3_0= ruleEString ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3914:3: ( (lv_name_1_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3915:1: (lv_name_1_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3915:1: (lv_name_1_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3916:3: lv_name_1_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getElementParameterTypeAccess().getNameEStringParserRuleCall_1_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleElementParameterType5811);
            lv_name_1_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getElementParameterTypeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_1_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }

            match(input,69,FOLLOW_69_in_ruleElementParameterType5821); 

                    createLeafNode(grammarAccess.getElementParameterTypeAccess().getColonKeyword_1_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3942:1: ( (lv_value_3_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3943:1: (lv_value_3_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3943:1: (lv_value_3_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3944:3: lv_value_3_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getElementParameterTypeAccess().getValueEStringParserRuleCall_1_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleElementParameterType5842);
            lv_value_3_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getElementParameterTypeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"value",
            	        		lv_value_3_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleElementParameterType


    // $ANTLR start entryRuleSchemaElementParameterType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3974:1: entryRuleSchemaElementParameterType returns [EObject current=null] : iv_ruleSchemaElementParameterType= ruleSchemaElementParameterType EOF ;
    public final EObject entryRuleSchemaElementParameterType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSchemaElementParameterType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3975:2: (iv_ruleSchemaElementParameterType= ruleSchemaElementParameterType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3976:2: iv_ruleSchemaElementParameterType= ruleSchemaElementParameterType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSchemaElementParameterTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleSchemaElementParameterType_in_entryRuleSchemaElementParameterType5879);
            iv_ruleSchemaElementParameterType=ruleSchemaElementParameterType();
            _fsp--;

             current =iv_ruleSchemaElementParameterType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSchemaElementParameterType5889); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleSchemaElementParameterType


    // $ANTLR start ruleSchemaElementParameterType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3983:1: ruleSchemaElementParameterType returns [EObject current=null] : ( () ( ( (lv_name_1_0= ruleEString ) ) '{' ( (lv_elementValue_3_0= ruleElementValueType ) ) ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )* '}' )? ) ;
    public final EObject ruleSchemaElementParameterType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_elementValue_3_0 = null;

        EObject lv_elementValue_5_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3988:6: ( ( () ( ( (lv_name_1_0= ruleEString ) ) '{' ( (lv_elementValue_3_0= ruleElementValueType ) ) ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )* '}' )? ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3989:1: ( () ( ( (lv_name_1_0= ruleEString ) ) '{' ( (lv_elementValue_3_0= ruleElementValueType ) ) ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )* '}' )? )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3989:1: ( () ( ( (lv_name_1_0= ruleEString ) ) '{' ( (lv_elementValue_3_0= ruleElementValueType ) ) ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )* '}' )? )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3989:2: () ( ( (lv_name_1_0= ruleEString ) ) '{' ( (lv_elementValue_3_0= ruleElementValueType ) ) ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )* '}' )?
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3989:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3990:5: 
            {
             
                    temp=factory.create(grammarAccess.getSchemaElementParameterTypeAccess().getElementParameterTypeAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getSchemaElementParameterTypeAccess().getElementParameterTypeAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4000:2: ( ( (lv_name_1_0= ruleEString ) ) '{' ( (lv_elementValue_3_0= ruleElementValueType ) ) ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )* '}' )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( ((LA86_0>=RULE_STRING && LA86_0<=RULE_INT)) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4000:3: ( (lv_name_1_0= ruleEString ) ) '{' ( (lv_elementValue_3_0= ruleElementValueType ) ) ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )* '}'
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4000:3: ( (lv_name_1_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4001:1: (lv_name_1_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4001:1: (lv_name_1_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4002:3: lv_name_1_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSchemaElementParameterTypeAccess().getNameEStringParserRuleCall_1_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleSchemaElementParameterType5945);
                    lv_name_1_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getSchemaElementParameterTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"name",
                    	        		lv_name_1_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    match(input,12,FOLLOW_12_in_ruleSchemaElementParameterType5955); 

                            createLeafNode(grammarAccess.getSchemaElementParameterTypeAccess().getLeftCurlyBracketKeyword_1_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4028:1: ( (lv_elementValue_3_0= ruleElementValueType ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4029:1: (lv_elementValue_3_0= ruleElementValueType )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4029:1: (lv_elementValue_3_0= ruleElementValueType )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4030:3: lv_elementValue_3_0= ruleElementValueType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSchemaElementParameterTypeAccess().getElementValueElementValueTypeParserRuleCall_1_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleElementValueType_in_ruleSchemaElementParameterType5976);
                    lv_elementValue_3_0=ruleElementValueType();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getSchemaElementParameterTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		add(
                    	       			current, 
                    	       			"elementValue",
                    	        		lv_elementValue_3_0, 
                    	        		"ElementValueType", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }

                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4052:2: ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )*
                    loop85:
                    do {
                        int alt85=2;
                        int LA85_0 = input.LA(1);

                        if ( (LA85_0==14) ) {
                            alt85=1;
                        }


                        switch (alt85) {
                    	case 1 :
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4052:4: ',' ( (lv_elementValue_5_0= ruleElementValueType ) )
                    	    {
                    	    match(input,14,FOLLOW_14_in_ruleSchemaElementParameterType5987); 

                    	            createLeafNode(grammarAccess.getSchemaElementParameterTypeAccess().getCommaKeyword_1_3_0(), null); 
                    	        
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4056:1: ( (lv_elementValue_5_0= ruleElementValueType ) )
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4057:1: (lv_elementValue_5_0= ruleElementValueType )
                    	    {
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4057:1: (lv_elementValue_5_0= ruleElementValueType )
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4058:3: lv_elementValue_5_0= ruleElementValueType
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getSchemaElementParameterTypeAccess().getElementValueElementValueTypeParserRuleCall_1_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleElementValueType_in_ruleSchemaElementParameterType6008);
                    	    lv_elementValue_5_0=ruleElementValueType();
                    	    _fsp--;


                    	    	        if (current==null) {
                    	    	            current = factory.create(grammarAccess.getSchemaElementParameterTypeRule().getType().getClassifier());
                    	    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	    	        }
                    	    	        try {
                    	    	       		add(
                    	    	       			current, 
                    	    	       			"elementValue",
                    	    	        		lv_elementValue_5_0, 
                    	    	        		"ElementValueType", 
                    	    	        		currentNode);
                    	    	        } catch (ValueConverterException vce) {
                    	    				handleValueConverterException(vce);
                    	    	        }
                    	    	        currentNode = currentNode.getParent();
                    	    	    

                    	    }


                    	    }


                    	    }
                    	    break;

                    	default :
                    	    break loop85;
                        }
                    } while (true);

                    match(input,13,FOLLOW_13_in_ruleSchemaElementParameterType6020); 

                            createLeafNode(grammarAccess.getSchemaElementParameterTypeAccess().getRightCurlyBracketKeyword_1_4(), null); 
                        

                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleSchemaElementParameterType


    // $ANTLR start entryRuleElementValueType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4092:1: entryRuleElementValueType returns [EObject current=null] : iv_ruleElementValueType= ruleElementValueType EOF ;
    public final EObject entryRuleElementValueType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElementValueType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4093:2: (iv_ruleElementValueType= ruleElementValueType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4094:2: iv_ruleElementValueType= ruleElementValueType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getElementValueTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleElementValueType_in_entryRuleElementValueType6058);
            iv_ruleElementValueType=ruleElementValueType();
            _fsp--;

             current =iv_ruleElementValueType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleElementValueType6068); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleElementValueType


    // $ANTLR start ruleElementValueType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4101:1: ruleElementValueType returns [EObject current=null] : ( () ( 'NAME:' ( (lv_elementRef_2_0= ruleEString ) ) ) ( 'VALUE:' ( (lv_value_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? ) ;
    public final EObject ruleElementValueType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_elementRef_2_0 = null;

        AntlrDatatypeRuleToken lv_value_4_0 = null;

        AntlrDatatypeRuleToken lv_type_6_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4106:6: ( ( () ( 'NAME:' ( (lv_elementRef_2_0= ruleEString ) ) ) ( 'VALUE:' ( (lv_value_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4107:1: ( () ( 'NAME:' ( (lv_elementRef_2_0= ruleEString ) ) ) ( 'VALUE:' ( (lv_value_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4107:1: ( () ( 'NAME:' ( (lv_elementRef_2_0= ruleEString ) ) ) ( 'VALUE:' ( (lv_value_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4107:2: () ( 'NAME:' ( (lv_elementRef_2_0= ruleEString ) ) ) ( 'VALUE:' ( (lv_value_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )?
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4107:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4108:5: 
            {
             
                    temp=factory.create(grammarAccess.getElementValueTypeAccess().getElementValueTypeAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getElementValueTypeAccess().getElementValueTypeAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4118:2: ( 'NAME:' ( (lv_elementRef_2_0= ruleEString ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4118:4: 'NAME:' ( (lv_elementRef_2_0= ruleEString ) )
            {
            match(input,19,FOLLOW_19_in_ruleElementValueType6113); 

                    createLeafNode(grammarAccess.getElementValueTypeAccess().getNAMEKeyword_1_0(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4122:1: ( (lv_elementRef_2_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4123:1: (lv_elementRef_2_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4123:1: (lv_elementRef_2_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4124:3: lv_elementRef_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getElementValueTypeAccess().getElementRefEStringParserRuleCall_1_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleElementValueType6134);
            lv_elementRef_2_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getElementValueTypeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"elementRef",
            	        		lv_elementRef_2_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4146:3: ( 'VALUE:' ( (lv_value_4_0= ruleEString ) ) )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==52) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4146:5: 'VALUE:' ( (lv_value_4_0= ruleEString ) )
                    {
                    match(input,52,FOLLOW_52_in_ruleElementValueType6146); 

                            createLeafNode(grammarAccess.getElementValueTypeAccess().getVALUEKeyword_2_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4150:1: ( (lv_value_4_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4151:1: (lv_value_4_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4151:1: (lv_value_4_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4152:3: lv_value_4_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getElementValueTypeAccess().getValueEStringParserRuleCall_2_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleElementValueType6167);
                    lv_value_4_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getElementValueTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"value",
                    	        		lv_value_4_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4174:4: ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )?
            int alt88=2;
            int LA88_0 = input.LA(1);

            if ( (LA88_0==26) ) {
                alt88=1;
            }
            switch (alt88) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4174:6: 'TYPE:' ( (lv_type_6_0= ruleEString ) )
                    {
                    match(input,26,FOLLOW_26_in_ruleElementValueType6180); 

                            createLeafNode(grammarAccess.getElementValueTypeAccess().getTYPEKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4178:1: ( (lv_type_6_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4179:1: (lv_type_6_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4179:1: (lv_type_6_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4180:3: lv_type_6_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getElementValueTypeAccess().getTypeEStringParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleElementValueType6201);
                    lv_type_6_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getElementValueTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"type",
                    	        		lv_type_6_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }


            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleElementValueType


    // $ANTLR start entryRuleMetadataType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4210:1: entryRuleMetadataType returns [EObject current=null] : iv_ruleMetadataType= ruleMetadataType EOF ;
    public final EObject entryRuleMetadataType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMetadataType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4211:2: (iv_ruleMetadataType= ruleMetadataType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4212:2: iv_ruleMetadataType= ruleMetadataType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getMetadataTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleMetadataType_in_entryRuleMetadataType6239);
            iv_ruleMetadataType=ruleMetadataType();
            _fsp--;

             current =iv_ruleMetadataType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMetadataType6249); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleMetadataType


    // $ANTLR start ruleMetadataType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4219:1: ruleMetadataType returns [EObject current=null] : ( () 'addSchema' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_connector_6_0= ruleEString ) ) )? ( 'LABEL:' ( (lv_label_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'SOURCE:' ( (lv_source_12_0= ruleEString ) ) )? ( (lv_column_13_0= ruleColumnType ) )* '}' ) ;
    public final EObject ruleMetadataType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        AntlrDatatypeRuleToken lv_connector_6_0 = null;

        AntlrDatatypeRuleToken lv_label_8_0 = null;

        AntlrDatatypeRuleToken lv_comment_10_0 = null;

        AntlrDatatypeRuleToken lv_source_12_0 = null;

        EObject lv_column_13_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4224:6: ( ( () 'addSchema' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_connector_6_0= ruleEString ) ) )? ( 'LABEL:' ( (lv_label_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'SOURCE:' ( (lv_source_12_0= ruleEString ) ) )? ( (lv_column_13_0= ruleColumnType ) )* '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4225:1: ( () 'addSchema' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_connector_6_0= ruleEString ) ) )? ( 'LABEL:' ( (lv_label_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'SOURCE:' ( (lv_source_12_0= ruleEString ) ) )? ( (lv_column_13_0= ruleColumnType ) )* '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4225:1: ( () 'addSchema' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_connector_6_0= ruleEString ) ) )? ( 'LABEL:' ( (lv_label_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'SOURCE:' ( (lv_source_12_0= ruleEString ) ) )? ( (lv_column_13_0= ruleColumnType ) )* '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4225:2: () 'addSchema' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_connector_6_0= ruleEString ) ) )? ( 'LABEL:' ( (lv_label_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'SOURCE:' ( (lv_source_12_0= ruleEString ) ) )? ( (lv_column_13_0= ruleColumnType ) )* '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4225:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4226:5: 
            {
             
                    temp=factory.create(grammarAccess.getMetadataTypeAccess().getMetadataTypeAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getMetadataTypeAccess().getMetadataTypeAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,70,FOLLOW_70_in_ruleMetadataType6293); 

                    createLeafNode(grammarAccess.getMetadataTypeAccess().getAddSchemaKeyword_1(), null); 
                
            match(input,12,FOLLOW_12_in_ruleMetadataType6303); 

                    createLeafNode(grammarAccess.getMetadataTypeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4244:1: ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )?
            int alt89=2;
            int LA89_0 = input.LA(1);

            if ( (LA89_0==19) ) {
                alt89=1;
            }
            switch (alt89) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4244:3: 'NAME:' ( (lv_name_4_0= ruleEString ) )
                    {
                    match(input,19,FOLLOW_19_in_ruleMetadataType6314); 

                            createLeafNode(grammarAccess.getMetadataTypeAccess().getNAMEKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4248:1: ( (lv_name_4_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4249:1: (lv_name_4_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4249:1: (lv_name_4_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4250:3: lv_name_4_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMetadataTypeAccess().getNameEStringParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleMetadataType6335);
                    lv_name_4_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getMetadataTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"name",
                    	        		lv_name_4_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4272:4: ( 'TYPE:' ( (lv_connector_6_0= ruleEString ) ) )?
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( (LA90_0==26) ) {
                alt90=1;
            }
            switch (alt90) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4272:6: 'TYPE:' ( (lv_connector_6_0= ruleEString ) )
                    {
                    match(input,26,FOLLOW_26_in_ruleMetadataType6348); 

                            createLeafNode(grammarAccess.getMetadataTypeAccess().getTYPEKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4276:1: ( (lv_connector_6_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4277:1: (lv_connector_6_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4277:1: (lv_connector_6_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4278:3: lv_connector_6_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMetadataTypeAccess().getConnectorEStringParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleMetadataType6369);
                    lv_connector_6_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getMetadataTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"connector",
                    	        		lv_connector_6_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4300:4: ( 'LABEL:' ( (lv_label_8_0= ruleEString ) ) )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==71) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4300:6: 'LABEL:' ( (lv_label_8_0= ruleEString ) )
                    {
                    match(input,71,FOLLOW_71_in_ruleMetadataType6382); 

                            createLeafNode(grammarAccess.getMetadataTypeAccess().getLABELKeyword_5_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4304:1: ( (lv_label_8_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4305:1: (lv_label_8_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4305:1: (lv_label_8_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4306:3: lv_label_8_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMetadataTypeAccess().getLabelEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleMetadataType6403);
                    lv_label_8_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getMetadataTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"label",
                    	        		lv_label_8_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4328:4: ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==53) ) {
                alt92=1;
            }
            switch (alt92) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4328:6: 'COMMENT:' ( (lv_comment_10_0= ruleEString ) )
                    {
                    match(input,53,FOLLOW_53_in_ruleMetadataType6416); 

                            createLeafNode(grammarAccess.getMetadataTypeAccess().getCOMMENTKeyword_6_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4332:1: ( (lv_comment_10_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4333:1: (lv_comment_10_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4333:1: (lv_comment_10_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4334:3: lv_comment_10_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMetadataTypeAccess().getCommentEStringParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleMetadataType6437);
                    lv_comment_10_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getMetadataTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"comment",
                    	        		lv_comment_10_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4356:4: ( 'SOURCE:' ( (lv_source_12_0= ruleEString ) ) )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==62) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4356:6: 'SOURCE:' ( (lv_source_12_0= ruleEString ) )
                    {
                    match(input,62,FOLLOW_62_in_ruleMetadataType6450); 

                            createLeafNode(grammarAccess.getMetadataTypeAccess().getSOURCEKeyword_7_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4360:1: ( (lv_source_12_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4361:1: (lv_source_12_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4361:1: (lv_source_12_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4362:3: lv_source_12_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMetadataTypeAccess().getSourceEStringParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleMetadataType6471);
                    lv_source_12_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getMetadataTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"source",
                    	        		lv_source_12_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4384:4: ( (lv_column_13_0= ruleColumnType ) )*
            loop94:
            do {
                int alt94=2;
                int LA94_0 = input.LA(1);

                if ( (LA94_0==25) ) {
                    alt94=1;
                }


                switch (alt94) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4385:1: (lv_column_13_0= ruleColumnType )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4385:1: (lv_column_13_0= ruleColumnType )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4386:3: lv_column_13_0= ruleColumnType
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getMetadataTypeAccess().getColumnColumnTypeParserRuleCall_8_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleColumnType_in_ruleMetadataType6494);
            	    lv_column_13_0=ruleColumnType();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getMetadataTypeRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"column",
            	    	        		lv_column_13_0, 
            	    	        		"ColumnType", 
            	    	        		currentNode);
            	    	        } catch (ValueConverterException vce) {
            	    				handleValueConverterException(vce);
            	    	        }
            	    	        currentNode = currentNode.getParent();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop94;
                }
            } while (true);

            match(input,13,FOLLOW_13_in_ruleMetadataType6505); 

                    createLeafNode(grammarAccess.getMetadataTypeAccess().getRightCurlyBracketKeyword_9(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleMetadataType


    // $ANTLR start entryRuleColumnType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4420:1: entryRuleColumnType returns [EObject current=null] : iv_ruleColumnType= ruleColumnType EOF ;
    public final EObject entryRuleColumnType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4421:2: (iv_ruleColumnType= ruleColumnType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4422:2: iv_ruleColumnType= ruleColumnType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getColumnTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleColumnType_in_entryRuleColumnType6541);
            iv_ruleColumnType=ruleColumnType();
            _fsp--;

             current =iv_ruleColumnType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnType6551); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleColumnType


    // $ANTLR start ruleColumnType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4429:1: ruleColumnType returns [EObject current=null] : ( () 'addColumn' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'KEY:' ( (lv_key_10_0= ruleEBoolean ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_13_0= ruleEBoolean ) ) )? ( ',' 'DEFAULTVALUE:' ( (lv_defaultValue_16_0= ruleEString ) ) )? ( ',' 'LENGTH:' ( (lv_length_19_0= ruleEInt ) ) )? ( ',' 'COMMENT:' ( (lv_comment_22_0= ruleEString ) ) )? ( ',' 'ORIGINALDBCOLUMNNAME' ( (lv_originalDbColumnName_25_0= ruleEString ) ) )? ( ',' 'PATTREN:' ( (lv_pattern_28_0= ruleEString ) ) )? ( ',' 'PRECISION:' ( (lv_precision_31_0= ruleEInt ) ) )? ( ',' 'SOURCETYPE:' ( (lv_sourceType_34_0= ruleEString ) ) )? '}' ) ;
    public final EObject ruleColumnType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        AntlrDatatypeRuleToken lv_type_7_0 = null;

        AntlrDatatypeRuleToken lv_key_10_0 = null;

        AntlrDatatypeRuleToken lv_nullable_13_0 = null;

        AntlrDatatypeRuleToken lv_defaultValue_16_0 = null;

        AntlrDatatypeRuleToken lv_length_19_0 = null;

        AntlrDatatypeRuleToken lv_comment_22_0 = null;

        AntlrDatatypeRuleToken lv_originalDbColumnName_25_0 = null;

        AntlrDatatypeRuleToken lv_pattern_28_0 = null;

        AntlrDatatypeRuleToken lv_precision_31_0 = null;

        AntlrDatatypeRuleToken lv_sourceType_34_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4434:6: ( ( () 'addColumn' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'KEY:' ( (lv_key_10_0= ruleEBoolean ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_13_0= ruleEBoolean ) ) )? ( ',' 'DEFAULTVALUE:' ( (lv_defaultValue_16_0= ruleEString ) ) )? ( ',' 'LENGTH:' ( (lv_length_19_0= ruleEInt ) ) )? ( ',' 'COMMENT:' ( (lv_comment_22_0= ruleEString ) ) )? ( ',' 'ORIGINALDBCOLUMNNAME' ( (lv_originalDbColumnName_25_0= ruleEString ) ) )? ( ',' 'PATTREN:' ( (lv_pattern_28_0= ruleEString ) ) )? ( ',' 'PRECISION:' ( (lv_precision_31_0= ruleEInt ) ) )? ( ',' 'SOURCETYPE:' ( (lv_sourceType_34_0= ruleEString ) ) )? '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4435:1: ( () 'addColumn' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'KEY:' ( (lv_key_10_0= ruleEBoolean ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_13_0= ruleEBoolean ) ) )? ( ',' 'DEFAULTVALUE:' ( (lv_defaultValue_16_0= ruleEString ) ) )? ( ',' 'LENGTH:' ( (lv_length_19_0= ruleEInt ) ) )? ( ',' 'COMMENT:' ( (lv_comment_22_0= ruleEString ) ) )? ( ',' 'ORIGINALDBCOLUMNNAME' ( (lv_originalDbColumnName_25_0= ruleEString ) ) )? ( ',' 'PATTREN:' ( (lv_pattern_28_0= ruleEString ) ) )? ( ',' 'PRECISION:' ( (lv_precision_31_0= ruleEInt ) ) )? ( ',' 'SOURCETYPE:' ( (lv_sourceType_34_0= ruleEString ) ) )? '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4435:1: ( () 'addColumn' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'KEY:' ( (lv_key_10_0= ruleEBoolean ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_13_0= ruleEBoolean ) ) )? ( ',' 'DEFAULTVALUE:' ( (lv_defaultValue_16_0= ruleEString ) ) )? ( ',' 'LENGTH:' ( (lv_length_19_0= ruleEInt ) ) )? ( ',' 'COMMENT:' ( (lv_comment_22_0= ruleEString ) ) )? ( ',' 'ORIGINALDBCOLUMNNAME' ( (lv_originalDbColumnName_25_0= ruleEString ) ) )? ( ',' 'PATTREN:' ( (lv_pattern_28_0= ruleEString ) ) )? ( ',' 'PRECISION:' ( (lv_precision_31_0= ruleEInt ) ) )? ( ',' 'SOURCETYPE:' ( (lv_sourceType_34_0= ruleEString ) ) )? '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4435:2: () 'addColumn' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'KEY:' ( (lv_key_10_0= ruleEBoolean ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_13_0= ruleEBoolean ) ) )? ( ',' 'DEFAULTVALUE:' ( (lv_defaultValue_16_0= ruleEString ) ) )? ( ',' 'LENGTH:' ( (lv_length_19_0= ruleEInt ) ) )? ( ',' 'COMMENT:' ( (lv_comment_22_0= ruleEString ) ) )? ( ',' 'ORIGINALDBCOLUMNNAME' ( (lv_originalDbColumnName_25_0= ruleEString ) ) )? ( ',' 'PATTREN:' ( (lv_pattern_28_0= ruleEString ) ) )? ( ',' 'PRECISION:' ( (lv_precision_31_0= ruleEInt ) ) )? ( ',' 'SOURCETYPE:' ( (lv_sourceType_34_0= ruleEString ) ) )? '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4435:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4436:5: 
            {
             
                    temp=factory.create(grammarAccess.getColumnTypeAccess().getColumnTypeAction_0().getType().getClassifier());
                    current = temp; 
                    temp = null;
                    CompositeNode newNode = createCompositeNode(grammarAccess.getColumnTypeAccess().getColumnTypeAction_0(), currentNode.getParent());
                newNode.getChildren().add(currentNode);
                moveLookaheadInfo(currentNode, newNode);
                currentNode = newNode; 
                    associateNodeWithAstElement(currentNode, current); 
                

            }

            match(input,25,FOLLOW_25_in_ruleColumnType6595); 

                    createLeafNode(grammarAccess.getColumnTypeAccess().getAddColumnKeyword_1(), null); 
                
            match(input,12,FOLLOW_12_in_ruleColumnType6605); 

                    createLeafNode(grammarAccess.getColumnTypeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4454:1: ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4454:3: 'NAME:' ( (lv_name_4_0= ruleEString ) )
            {
            match(input,19,FOLLOW_19_in_ruleColumnType6616); 

                    createLeafNode(grammarAccess.getColumnTypeAccess().getNAMEKeyword_3_0(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4458:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4459:1: (lv_name_4_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4459:1: (lv_name_4_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4460:3: lv_name_4_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getNameEStringParserRuleCall_3_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleColumnType6637);
            lv_name_4_0=ruleEString();
            _fsp--;


            	        if (current==null) {
            	            current = factory.create(grammarAccess.getColumnTypeRule().getType().getClassifier());
            	            associateNodeWithAstElement(currentNode.getParent(), current);
            	        }
            	        try {
            	       		set(
            	       			current, 
            	       			"name",
            	        		lv_name_4_0, 
            	        		"EString", 
            	        		currentNode);
            	        } catch (ValueConverterException vce) {
            				handleValueConverterException(vce);
            	        }
            	        currentNode = currentNode.getParent();
            	    

            }


            }


            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4482:3: ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==14) ) {
                int LA95_1 = input.LA(2);

                if ( (LA95_1==26) ) {
                    alt95=1;
                }
            }
            switch (alt95) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4482:5: ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleColumnType6649); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_4_0(), null); 
                        
                    match(input,26,FOLLOW_26_in_ruleColumnType6659); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getTYPEKeyword_4_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4490:1: ( (lv_type_7_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4491:1: (lv_type_7_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4491:1: (lv_type_7_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4492:3: lv_type_7_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getTypeEStringParserRuleCall_4_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleColumnType6680);
                    lv_type_7_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getColumnTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"type",
                    	        		lv_type_7_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4514:4: ( ',' 'KEY:' ( (lv_key_10_0= ruleEBoolean ) ) )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==14) ) {
                int LA96_1 = input.LA(2);

                if ( (LA96_1==72) ) {
                    alt96=1;
                }
            }
            switch (alt96) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4514:6: ',' 'KEY:' ( (lv_key_10_0= ruleEBoolean ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleColumnType6693); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_5_0(), null); 
                        
                    match(input,72,FOLLOW_72_in_ruleColumnType6703); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getKEYKeyword_5_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4522:1: ( (lv_key_10_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4523:1: (lv_key_10_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4523:1: (lv_key_10_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4524:3: lv_key_10_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getKeyEBooleanParserRuleCall_5_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleColumnType6724);
                    lv_key_10_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getColumnTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"key",
                    	        		lv_key_10_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4546:4: ( ',' 'NULLABLE:' ( (lv_nullable_13_0= ruleEBoolean ) ) )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==14) ) {
                int LA97_1 = input.LA(2);

                if ( (LA97_1==27) ) {
                    alt97=1;
                }
            }
            switch (alt97) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4546:6: ',' 'NULLABLE:' ( (lv_nullable_13_0= ruleEBoolean ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleColumnType6737); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_6_0(), null); 
                        
                    match(input,27,FOLLOW_27_in_ruleColumnType6747); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getNULLABLEKeyword_6_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4554:1: ( (lv_nullable_13_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4555:1: (lv_nullable_13_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4555:1: (lv_nullable_13_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4556:3: lv_nullable_13_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getNullableEBooleanParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleColumnType6768);
                    lv_nullable_13_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getColumnTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"nullable",
                    	        		lv_nullable_13_0, 
                    	        		"EBoolean", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4578:4: ( ',' 'DEFAULTVALUE:' ( (lv_defaultValue_16_0= ruleEString ) ) )?
            int alt98=2;
            int LA98_0 = input.LA(1);

            if ( (LA98_0==14) ) {
                int LA98_1 = input.LA(2);

                if ( (LA98_1==73) ) {
                    alt98=1;
                }
            }
            switch (alt98) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4578:6: ',' 'DEFAULTVALUE:' ( (lv_defaultValue_16_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleColumnType6781); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_7_0(), null); 
                        
                    match(input,73,FOLLOW_73_in_ruleColumnType6791); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getDEFAULTVALUEKeyword_7_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4586:1: ( (lv_defaultValue_16_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4587:1: (lv_defaultValue_16_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4587:1: (lv_defaultValue_16_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4588:3: lv_defaultValue_16_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getDefaultValueEStringParserRuleCall_7_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleColumnType6812);
                    lv_defaultValue_16_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getColumnTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"defaultValue",
                    	        		lv_defaultValue_16_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4610:4: ( ',' 'LENGTH:' ( (lv_length_19_0= ruleEInt ) ) )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==14) ) {
                int LA99_1 = input.LA(2);

                if ( (LA99_1==74) ) {
                    alt99=1;
                }
            }
            switch (alt99) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4610:6: ',' 'LENGTH:' ( (lv_length_19_0= ruleEInt ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleColumnType6825); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_8_0(), null); 
                        
                    match(input,74,FOLLOW_74_in_ruleColumnType6835); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getLENGTHKeyword_8_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4618:1: ( (lv_length_19_0= ruleEInt ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4619:1: (lv_length_19_0= ruleEInt )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4619:1: (lv_length_19_0= ruleEInt )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4620:3: lv_length_19_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getLengthEIntParserRuleCall_8_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEInt_in_ruleColumnType6856);
                    lv_length_19_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getColumnTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"length",
                    	        		lv_length_19_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4642:4: ( ',' 'COMMENT:' ( (lv_comment_22_0= ruleEString ) ) )?
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( (LA100_0==14) ) {
                int LA100_1 = input.LA(2);

                if ( (LA100_1==53) ) {
                    alt100=1;
                }
            }
            switch (alt100) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4642:6: ',' 'COMMENT:' ( (lv_comment_22_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleColumnType6869); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_9_0(), null); 
                        
                    match(input,53,FOLLOW_53_in_ruleColumnType6879); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCOMMENTKeyword_9_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4650:1: ( (lv_comment_22_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4651:1: (lv_comment_22_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4651:1: (lv_comment_22_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4652:3: lv_comment_22_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getCommentEStringParserRuleCall_9_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleColumnType6900);
                    lv_comment_22_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getColumnTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"comment",
                    	        		lv_comment_22_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4674:4: ( ',' 'ORIGINALDBCOLUMNNAME' ( (lv_originalDbColumnName_25_0= ruleEString ) ) )?
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==14) ) {
                int LA101_1 = input.LA(2);

                if ( (LA101_1==75) ) {
                    alt101=1;
                }
            }
            switch (alt101) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4674:6: ',' 'ORIGINALDBCOLUMNNAME' ( (lv_originalDbColumnName_25_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleColumnType6913); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_10_0(), null); 
                        
                    match(input,75,FOLLOW_75_in_ruleColumnType6923); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getORIGINALDBCOLUMNNAMEKeyword_10_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4682:1: ( (lv_originalDbColumnName_25_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4683:1: (lv_originalDbColumnName_25_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4683:1: (lv_originalDbColumnName_25_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4684:3: lv_originalDbColumnName_25_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getOriginalDbColumnNameEStringParserRuleCall_10_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleColumnType6944);
                    lv_originalDbColumnName_25_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getColumnTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"originalDbColumnName",
                    	        		lv_originalDbColumnName_25_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4706:4: ( ',' 'PATTREN:' ( (lv_pattern_28_0= ruleEString ) ) )?
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==14) ) {
                int LA102_1 = input.LA(2);

                if ( (LA102_1==76) ) {
                    alt102=1;
                }
            }
            switch (alt102) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4706:6: ',' 'PATTREN:' ( (lv_pattern_28_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleColumnType6957); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_11_0(), null); 
                        
                    match(input,76,FOLLOW_76_in_ruleColumnType6967); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getPATTRENKeyword_11_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4714:1: ( (lv_pattern_28_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4715:1: (lv_pattern_28_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4715:1: (lv_pattern_28_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4716:3: lv_pattern_28_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getPatternEStringParserRuleCall_11_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleColumnType6988);
                    lv_pattern_28_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getColumnTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"pattern",
                    	        		lv_pattern_28_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4738:4: ( ',' 'PRECISION:' ( (lv_precision_31_0= ruleEInt ) ) )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==14) ) {
                int LA103_1 = input.LA(2);

                if ( (LA103_1==77) ) {
                    alt103=1;
                }
            }
            switch (alt103) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4738:6: ',' 'PRECISION:' ( (lv_precision_31_0= ruleEInt ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleColumnType7001); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_12_0(), null); 
                        
                    match(input,77,FOLLOW_77_in_ruleColumnType7011); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getPRECISIONKeyword_12_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4746:1: ( (lv_precision_31_0= ruleEInt ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4747:1: (lv_precision_31_0= ruleEInt )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4747:1: (lv_precision_31_0= ruleEInt )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4748:3: lv_precision_31_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getPrecisionEIntParserRuleCall_12_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEInt_in_ruleColumnType7032);
                    lv_precision_31_0=ruleEInt();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getColumnTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"precision",
                    	        		lv_precision_31_0, 
                    	        		"EInt", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4770:4: ( ',' 'SOURCETYPE:' ( (lv_sourceType_34_0= ruleEString ) ) )?
            int alt104=2;
            int LA104_0 = input.LA(1);

            if ( (LA104_0==14) ) {
                alt104=1;
            }
            switch (alt104) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4770:6: ',' 'SOURCETYPE:' ( (lv_sourceType_34_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleColumnType7045); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_13_0(), null); 
                        
                    match(input,78,FOLLOW_78_in_ruleColumnType7055); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getSOURCETYPEKeyword_13_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4778:1: ( (lv_sourceType_34_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4779:1: (lv_sourceType_34_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4779:1: (lv_sourceType_34_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4780:3: lv_sourceType_34_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getSourceTypeEStringParserRuleCall_13_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleColumnType7076);
                    lv_sourceType_34_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getColumnTypeRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"sourceType",
                    	        		lv_sourceType_34_0, 
                    	        		"EString", 
                    	        		currentNode);
                    	        } catch (ValueConverterException vce) {
                    				handleValueConverterException(vce);
                    	        }
                    	        currentNode = currentNode.getParent();
                    	    

                    }


                    }


                    }
                    break;

            }

            match(input,13,FOLLOW_13_in_ruleColumnType7088); 

                    createLeafNode(grammarAccess.getColumnTypeAccess().getRightCurlyBracketKeyword_14(), null); 
                

            }


            }

             resetLookahead(); 
                	lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleColumnType


    // $ANTLR start entryRuleEString
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4814:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4815:2: (iv_ruleEString= ruleEString EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4816:2: iv_ruleEString= ruleEString EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEStringRule(), currentNode); 
            pushFollow(FOLLOW_ruleEString_in_entryRuleEString7125);
            iv_ruleEString=ruleEString();
            _fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEString7136); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEString


    // $ANTLR start ruleEString
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4823:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID | this_INT_2= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;
        Token this_INT_2=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4828:6: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID | this_INT_2= RULE_INT ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4829:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID | this_INT_2= RULE_INT )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4829:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID | this_INT_2= RULE_INT )
            int alt105=3;
            switch ( input.LA(1) ) {
            case RULE_STRING:
                {
                alt105=1;
                }
                break;
            case RULE_ID:
                {
                alt105=2;
                }
                break;
            case RULE_INT:
                {
                alt105=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("4829:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID | this_INT_2= RULE_INT )", 105, 0, input);

                throw nvae;
            }

            switch (alt105) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4829:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleEString7176); 

                    		current.merge(this_STRING_0);
                        
                     
                        createLeafNode(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4837:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)input.LT(1);
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleEString7202); 

                    		current.merge(this_ID_1);
                        
                     
                        createLeafNode(grammarAccess.getEStringAccess().getIDTerminalRuleCall_1(), null); 
                        

                    }
                    break;
                case 3 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4845:10: this_INT_2= RULE_INT
                    {
                    this_INT_2=(Token)input.LT(1);
                    match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleEString7228); 

                    		current.merge(this_INT_2);
                        
                     
                        createLeafNode(grammarAccess.getEStringAccess().getINTTerminalRuleCall_2(), null); 
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEString


    // $ANTLR start entryRuleEInt
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4860:1: entryRuleEInt returns [String current=null] : iv_ruleEInt= ruleEInt EOF ;
    public final String entryRuleEInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEInt = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4861:2: (iv_ruleEInt= ruleEInt EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4862:2: iv_ruleEInt= ruleEInt EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEIntRule(), currentNode); 
            pushFollow(FOLLOW_ruleEInt_in_entryRuleEInt7274);
            iv_ruleEInt=ruleEInt();
            _fsp--;

             current =iv_ruleEInt.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEInt7285); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEInt


    // $ANTLR start ruleEInt
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4869:1: ruleEInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleEInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4874:6: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4875:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4875:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4875:2: (kw= '-' )? this_INT_1= RULE_INT
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4875:2: (kw= '-' )?
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==79) ) {
                alt106=1;
            }
            switch (alt106) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4876:2: kw= '-'
                    {
                    kw=(Token)input.LT(1);
                    match(input,79,FOLLOW_79_in_ruleEInt7324); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getEIntAccess().getHyphenMinusKeyword_0(), null); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)input.LT(1);
            match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleEInt7341); 

            		current.merge(this_INT_1);
                
             
                createLeafNode(grammarAccess.getEIntAccess().getINTTerminalRuleCall_1(), null); 
                

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEInt


    // $ANTLR start entryRuleEBoolean
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4896:1: entryRuleEBoolean returns [String current=null] : iv_ruleEBoolean= ruleEBoolean EOF ;
    public final String entryRuleEBoolean() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEBoolean = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4897:2: (iv_ruleEBoolean= ruleEBoolean EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4898:2: iv_ruleEBoolean= ruleEBoolean EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEBooleanRule(), currentNode); 
            pushFollow(FOLLOW_ruleEBoolean_in_entryRuleEBoolean7387);
            iv_ruleEBoolean=ruleEBoolean();
            _fsp--;

             current =iv_ruleEBoolean.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEBoolean7398); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end entryRuleEBoolean


    // $ANTLR start ruleEBoolean
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4905:1: ruleEBoolean returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'true' | kw= 'false' ) ;
    public final AntlrDatatypeRuleToken ruleEBoolean() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4910:6: ( (kw= 'true' | kw= 'false' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4911:1: (kw= 'true' | kw= 'false' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4911:1: (kw= 'true' | kw= 'false' )
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==80) ) {
                alt107=1;
            }
            else if ( (LA107_0==81) ) {
                alt107=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("4911:1: (kw= 'true' | kw= 'false' )", 107, 0, input);

                throw nvae;
            }
            switch (alt107) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4912:2: kw= 'true'
                    {
                    kw=(Token)input.LT(1);
                    match(input,80,FOLLOW_80_in_ruleEBoolean7436); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getEBooleanAccess().getTrueKeyword_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4919:2: kw= 'false'
                    {
                    kw=(Token)input.LT(1);
                    match(input,81,FOLLOW_81_in_ruleEBoolean7455); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getEBooleanAccess().getFalseKeyword_1(), null); 
                        

                    }
                    break;

            }


            }

             resetLookahead(); 
            	    lastConsumedNode = currentNode;
                
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end ruleEBoolean


 

    public static final BitSet FOLLOW_ruleProcessType_in_entryRuleProcessType75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleProcessType85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_11_in_ruleProcessType130 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleProcessType140 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleParametersType_in_ruleProcessType161 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleProcessType171 = new BitSet(new long[]{0x0202200000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_ruleNodeType_in_ruleProcessType194 = new BitSet(new long[]{0x0202200000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_ruleConnectionType_in_ruleProcessType216 = new BitSet(new long[]{0x0202000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_ruleNoteType_in_ruleProcessType238 = new BitSet(new long[]{0x0002000000000002L,0x0000000000000001L});
    public static final BitSet FOLLOW_ruleContextType_in_ruleProcessType260 = new BitSet(new long[]{0x0002000000000002L});
    public static final BitSet FOLLOW_ruleParametersType_in_entryRuleParametersType297 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleParametersType307 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementParameterType_in_ruleParametersType353 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_14_in_ruleParametersType364 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleElementParameterType_in_ruleParametersType385 = new BitSet(new long[]{0x0000000000004002L});
    public static final BitSet FOLLOW_ruleExternalData_in_entryRuleExternalData423 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleExternalData433 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMapperData_in_ruleExternalData480 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDBMapperData_in_ruleExternalData507 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDBMapperData_in_entryRuleDBMapperData542 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDBMapperData552 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_15_in_ruleDBMapperData596 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleDBMapperData606 = new BitSet(new long[]{0x0000000000072000L});
    public static final BitSet FOLLOW_16_in_ruleDBMapperData617 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleDBMapperData627 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ruleDBInputTable_in_ruleDBMapperData648 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleDBMapperData658 = new BitSet(new long[]{0x0000000000072000L});
    public static final BitSet FOLLOW_17_in_ruleDBMapperData671 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleDBMapperData681 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ruleDBVarTable_in_ruleDBMapperData702 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleDBMapperData712 = new BitSet(new long[]{0x0000000000062000L});
    public static final BitSet FOLLOW_18_in_ruleDBMapperData725 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleDBMapperData735 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ruleDBOutputTable_in_ruleDBMapperData756 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleDBMapperData766 = new BitSet(new long[]{0x0000000000042000L});
    public static final BitSet FOLLOW_13_in_ruleDBMapperData778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDBInputTable_in_entryRuleDBInputTable814 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDBInputTable824 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleDBInputTable868 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBInputTable889 = new BitSet(new long[]{0x0000000003F00002L});
    public static final BitSet FOLLOW_20_in_ruleDBInputTable900 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleDBInputTable921 = new BitSet(new long[]{0x0000000003E00002L});
    public static final BitSet FOLLOW_21_in_ruleDBInputTable934 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleDBInputTable955 = new BitSet(new long[]{0x0000000003C00002L});
    public static final BitSet FOLLOW_22_in_ruleDBInputTable968 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBInputTable989 = new BitSet(new long[]{0x0000000003800002L});
    public static final BitSet FOLLOW_23_in_ruleDBInputTable1002 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBInputTable1023 = new BitSet(new long[]{0x0000000003000002L});
    public static final BitSet FOLLOW_24_in_ruleDBInputTable1036 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBInputTable1057 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_ruleDBMapperTableEntry_in_ruleDBInputTable1080 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_ruleDBVarTable_in_entryRuleDBVarTable1117 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDBVarTable1127 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleDBVarTable1171 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBVarTable1192 = new BitSet(new long[]{0x0000000002300002L});
    public static final BitSet FOLLOW_20_in_ruleDBVarTable1203 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleDBVarTable1224 = new BitSet(new long[]{0x0000000002200002L});
    public static final BitSet FOLLOW_21_in_ruleDBVarTable1237 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleDBVarTable1258 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_ruleDBMapperTableEntry_in_ruleDBVarTable1281 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_ruleDBOutputTable_in_entryRuleDBOutputTable1318 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDBOutputTable1328 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleDBOutputTable1372 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBOutputTable1393 = new BitSet(new long[]{0x0000000082300002L});
    public static final BitSet FOLLOW_20_in_ruleDBOutputTable1404 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleDBOutputTable1425 = new BitSet(new long[]{0x0000000082200002L});
    public static final BitSet FOLLOW_21_in_ruleDBOutputTable1438 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleDBOutputTable1459 = new BitSet(new long[]{0x0000000082000002L});
    public static final BitSet FOLLOW_ruleDBMapperTableEntry_in_ruleDBOutputTable1482 = new BitSet(new long[]{0x0000000082000002L});
    public static final BitSet FOLLOW_ruleDBFilterEntry_in_ruleDBOutputTable1504 = new BitSet(new long[]{0x0000000080000002L});
    public static final BitSet FOLLOW_ruleDBMapperTableEntry_in_entryRuleDBMapperTableEntry1541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDBMapperTableEntry1551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleDBMapperTableEntry1595 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleDBMapperTableEntry1605 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleDBMapperTableEntry1615 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBMapperTableEntry1636 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleDBMapperTableEntry1647 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleDBMapperTableEntry1657 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBMapperTableEntry1678 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleDBMapperTableEntry1691 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleDBMapperTableEntry1701 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleDBMapperTableEntry1722 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleDBMapperTableEntry1735 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleDBMapperTableEntry1745 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBMapperTableEntry1766 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleDBMapperTableEntry1779 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_29_in_ruleDBMapperTableEntry1789 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleDBMapperTableEntry1810 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleDBMapperTableEntry1823 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_30_in_ruleDBMapperTableEntry1833 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBMapperTableEntry1854 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleDBMapperTableEntry1866 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDBFilterEntry_in_entryRuleDBFilterEntry1902 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDBFilterEntry1912 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_ruleDBFilterEntry1947 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleDBFilterEntry1957 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleDBFilterEntry1967 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBFilterEntry1988 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleDBFilterEntry1998 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleDBFilterEntry2008 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBFilterEntry2029 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleDBFilterEntry2039 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMapperData_in_entryRuleMapperData2075 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMapperData2085 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_32_in_ruleMapperData2129 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMapperData2139 = new BitSet(new long[]{0x0000000200072000L});
    public static final BitSet FOLLOW_33_in_ruleMapperData2150 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMapperData2160 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_ruleUiProperties_in_ruleMapperData2181 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleMapperData2191 = new BitSet(new long[]{0x0000000000072000L});
    public static final BitSet FOLLOW_16_in_ruleMapperData2204 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMapperData2214 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ruleInputTable_in_ruleMapperData2235 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleMapperData2245 = new BitSet(new long[]{0x0000000000072000L});
    public static final BitSet FOLLOW_17_in_ruleMapperData2258 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMapperData2268 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ruleVarTable_in_ruleMapperData2289 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleMapperData2299 = new BitSet(new long[]{0x0000000000062000L});
    public static final BitSet FOLLOW_18_in_ruleMapperData2312 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMapperData2322 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ruleOutputTable_in_ruleMapperData2343 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleMapperData2353 = new BitSet(new long[]{0x0000000000042000L});
    public static final BitSet FOLLOW_13_in_ruleMapperData2365 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUiProperties_in_entryRuleUiProperties2401 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUiProperties2411 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_34_in_ruleUiProperties2455 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleUiProperties2476 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInputTable_in_entryRuleInputTable2512 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInputTable2522 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleInputTable2566 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleInputTable2587 = new BitSet(new long[]{0x000001F802100002L});
    public static final BitSet FOLLOW_20_in_ruleInputTable2598 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleInputTable2619 = new BitSet(new long[]{0x000001F802000002L});
    public static final BitSet FOLLOW_35_in_ruleInputTable2632 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleInputTable2653 = new BitSet(new long[]{0x000001F002000002L});
    public static final BitSet FOLLOW_36_in_ruleInputTable2666 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleInputTable2687 = new BitSet(new long[]{0x000001E002000002L});
    public static final BitSet FOLLOW_37_in_ruleInputTable2700 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleInputTable2721 = new BitSet(new long[]{0x000001C002000002L});
    public static final BitSet FOLLOW_38_in_ruleInputTable2734 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleInputTable2755 = new BitSet(new long[]{0x0000018002000002L});
    public static final BitSet FOLLOW_39_in_ruleInputTable2768 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleInputTable2789 = new BitSet(new long[]{0x0000010002000002L});
    public static final BitSet FOLLOW_40_in_ruleInputTable2802 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleInputTable2823 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_ruleMapperTableEntry_in_ruleInputTable2846 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_ruleVarTable_in_entryRuleVarTable2883 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleVarTable2893 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleVarTable2937 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleVarTable2958 = new BitSet(new long[]{0x0000000802100002L});
    public static final BitSet FOLLOW_20_in_ruleVarTable2969 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleVarTable2990 = new BitSet(new long[]{0x0000000802000002L});
    public static final BitSet FOLLOW_35_in_ruleVarTable3003 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleVarTable3024 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_ruleMapperTableEntry_in_ruleVarTable3047 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_ruleOutputTable_in_entryRuleOutputTable3084 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOutputTable3094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleOutputTable3138 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleOutputTable3159 = new BitSet(new long[]{0x00001E7802100002L});
    public static final BitSet FOLLOW_20_in_ruleOutputTable3170 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleOutputTable3191 = new BitSet(new long[]{0x00001E7802000002L});
    public static final BitSet FOLLOW_35_in_ruleOutputTable3204 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleOutputTable3225 = new BitSet(new long[]{0x00001E7002000002L});
    public static final BitSet FOLLOW_36_in_ruleOutputTable3238 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleOutputTable3259 = new BitSet(new long[]{0x00001E6002000002L});
    public static final BitSet FOLLOW_37_in_ruleOutputTable3272 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleOutputTable3293 = new BitSet(new long[]{0x00001E4002000002L});
    public static final BitSet FOLLOW_38_in_ruleOutputTable3306 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleOutputTable3327 = new BitSet(new long[]{0x00001E0002000002L});
    public static final BitSet FOLLOW_41_in_ruleOutputTable3340 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleOutputTable3361 = new BitSet(new long[]{0x00001C0002000002L});
    public static final BitSet FOLLOW_42_in_ruleOutputTable3374 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleOutputTable3395 = new BitSet(new long[]{0x0000180002000002L});
    public static final BitSet FOLLOW_43_in_ruleOutputTable3408 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleOutputTable3429 = new BitSet(new long[]{0x0000100002000002L});
    public static final BitSet FOLLOW_44_in_ruleOutputTable3442 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleOutputTable3463 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_ruleMapperTableEntry_in_ruleOutputTable3486 = new BitSet(new long[]{0x0000000002000002L});
    public static final BitSet FOLLOW_ruleMapperTableEntry_in_entryRuleMapperTableEntry3523 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMapperTableEntry3533 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleMapperTableEntry3577 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMapperTableEntry3587 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleMapperTableEntry3597 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMapperTableEntry3618 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleMapperTableEntry3629 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleMapperTableEntry3639 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMapperTableEntry3660 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleMapperTableEntry3673 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleMapperTableEntry3683 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleMapperTableEntry3704 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleMapperTableEntry3717 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_28_in_ruleMapperTableEntry3727 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMapperTableEntry3748 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleMapperTableEntry3760 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNodeType_in_entryRuleNodeType3796 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNodeType3806 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_45_in_ruleNodeType3841 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleNodeType3851 = new BitSet(new long[]{0x0000400000000000L});
    public static final BitSet FOLLOW_46_in_ruleNodeType3861 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleNodeType3871 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleNodeType3882 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleNodeType3903 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleNodeType3913 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleNodeType3925 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleUniqueNameType_in_ruleNodeType3946 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleNodeType3956 = new BitSet(new long[]{0x0000800000000000L});
    public static final BitSet FOLLOW_47_in_ruleNodeType3968 = new BitSet(new long[]{0x0000000000000040L,0x0000000000008000L});
    public static final BitSet FOLLOW_ruleEInt_in_ruleNodeType3990 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleNodeType4000 = new BitSet(new long[]{0x0000000000000040L,0x0000000000008000L});
    public static final BitSet FOLLOW_ruleEInt_in_ruleNodeType4021 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleNodeType4033 = new BitSet(new long[]{0x0001000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_ruleMetadataType_in_ruleNodeType4054 = new BitSet(new long[]{0x0001000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_48_in_ruleNodeType4065 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleNodeType4075 = new BitSet(new long[]{0x0000000000006070L});
    public static final BitSet FOLLOW_ruleSchemaElementParameterType_in_ruleNodeType4098 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_ruleElementParameterType_in_ruleNodeType4117 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleNodeType4131 = new BitSet(new long[]{0x0000000000006070L});
    public static final BitSet FOLLOW_ruleSchemaElementParameterType_in_ruleNodeType4154 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_ruleElementParameterType_in_ruleNodeType4173 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_13_in_ruleNodeType4188 = new BitSet(new long[]{0x000000010000A000L,0x0000000000000040L});
    public static final BitSet FOLLOW_ruleMetadataType_in_ruleNodeType4209 = new BitSet(new long[]{0x000000010000A000L,0x0000000000000040L});
    public static final BitSet FOLLOW_ruleExternalData_in_ruleNodeType4231 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleNodeType4242 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleContextType_in_entryRuleContextType4278 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleContextType4288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_49_in_ruleContextType4332 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleContextType4342 = new BitSet(new long[]{0x000C000000082000L});
    public static final BitSet FOLLOW_19_in_ruleContextType4353 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleContextType4374 = new BitSet(new long[]{0x000C000000002000L});
    public static final BitSet FOLLOW_50_in_ruleContextType4387 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleContextType4408 = new BitSet(new long[]{0x0008000000002000L});
    public static final BitSet FOLLOW_ruleContextParameterType_in_ruleContextType4432 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleContextType4443 = new BitSet(new long[]{0x0008000000000000L});
    public static final BitSet FOLLOW_ruleContextParameterType_in_ruleContextType4464 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_13_in_ruleContextType4478 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleContextParameterType_in_entryRuleContextParameterType4514 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleContextParameterType4524 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_51_in_ruleContextParameterType4568 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleContextParameterType4578 = new BitSet(new long[]{0x01F0000004082000L});
    public static final BitSet FOLLOW_19_in_ruleContextParameterType4589 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleContextParameterType4610 = new BitSet(new long[]{0x01F0000004002000L});
    public static final BitSet FOLLOW_26_in_ruleContextParameterType4623 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleContextParameterType4644 = new BitSet(new long[]{0x01F0000000002000L});
    public static final BitSet FOLLOW_52_in_ruleContextParameterType4657 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleContextParameterType4678 = new BitSet(new long[]{0x01E0000000002000L});
    public static final BitSet FOLLOW_53_in_ruleContextParameterType4691 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleContextParameterType4712 = new BitSet(new long[]{0x01C0000000002000L});
    public static final BitSet FOLLOW_54_in_ruleContextParameterType4725 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleContextParameterType4746 = new BitSet(new long[]{0x0180000000002000L});
    public static final BitSet FOLLOW_55_in_ruleContextParameterType4759 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleContextParameterType4780 = new BitSet(new long[]{0x0100000000002000L});
    public static final BitSet FOLLOW_56_in_ruleContextParameterType4793 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleContextParameterType4814 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleContextParameterType4826 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUniqueNameType_in_entryRuleUniqueNameType4862 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUniqueNameType4872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEString_in_ruleUniqueNameType4917 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConnectionType_in_entryRuleConnectionType4952 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConnectionType4962 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_57_in_ruleConnectionType5006 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleConnectionType5016 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleConnectionType5027 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleConnectionType5048 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleConnectionType5060 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleConnectionType5070 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleConnectionType5091 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleConnectionType5103 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_ruleConnectionType5113 = new BitSet(new long[]{0x0000000000000040L,0x0000000000008000L});
    public static final BitSet FOLLOW_ruleEInt_in_ruleConnectionType5134 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleConnectionType5147 = new BitSet(new long[]{0x0800000000000000L});
    public static final BitSet FOLLOW_59_in_ruleConnectionType5157 = new BitSet(new long[]{0x0000000000000040L,0x0000000000008000L});
    public static final BitSet FOLLOW_ruleEInt_in_ruleConnectionType5178 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleConnectionType5191 = new BitSet(new long[]{0x1000000000000000L});
    public static final BitSet FOLLOW_60_in_ruleConnectionType5201 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleConnectionType5222 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleConnectionType5235 = new BitSet(new long[]{0x2000000000000000L});
    public static final BitSet FOLLOW_61_in_ruleConnectionType5245 = new BitSet(new long[]{0x0000000000000040L,0x0000000000008000L});
    public static final BitSet FOLLOW_ruleEInt_in_ruleConnectionType5266 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleConnectionType5279 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_62_in_ruleConnectionType5289 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleConnectionType5310 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleConnectionType5323 = new BitSet(new long[]{0x8000000000000000L});
    public static final BitSet FOLLOW_63_in_ruleConnectionType5333 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleConnectionType5354 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleConnectionType5367 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleElementParameterType_in_ruleConnectionType5388 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleConnectionType5400 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNoteType_in_entryRuleNoteType5436 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNoteType5446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_64_in_ruleNoteType5481 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleNoteType5491 = new BitSet(new long[]{0x0000000000000070L,0x0000000000000016L});
    public static final BitSet FOLLOW_65_in_ruleNoteType5502 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleNoteType5523 = new BitSet(new long[]{0x0000000000000070L,0x0000000000000014L});
    public static final BitSet FOLLOW_66_in_ruleNoteType5536 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_ruleNoteType5546 = new BitSet(new long[]{0x0000000000000040L,0x0000000000008000L});
    public static final BitSet FOLLOW_ruleEInt_in_ruleNoteType5567 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleNoteType5577 = new BitSet(new long[]{0x0000000000000040L,0x0000000000008000L});
    public static final BitSet FOLLOW_ruleEInt_in_ruleNoteType5598 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_ruleNoteType5608 = new BitSet(new long[]{0x0000000000000070L,0x0000000000000010L});
    public static final BitSet FOLLOW_68_in_ruleNoteType5621 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleNoteType5642 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleElementParameterType_in_ruleNoteType5665 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleNoteType5676 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleElementParameterType_in_ruleNoteType5697 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_13_in_ruleNoteType5709 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementParameterType_in_entryRuleElementParameterType5745 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleElementParameterType5755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEString_in_ruleElementParameterType5811 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_ruleElementParameterType5821 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleElementParameterType5842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSchemaElementParameterType_in_entryRuleSchemaElementParameterType5879 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSchemaElementParameterType5889 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEString_in_ruleSchemaElementParameterType5945 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleSchemaElementParameterType5955 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ruleElementValueType_in_ruleSchemaElementParameterType5976 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleSchemaElementParameterType5987 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ruleElementValueType_in_ruleSchemaElementParameterType6008 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_13_in_ruleSchemaElementParameterType6020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementValueType_in_entryRuleElementValueType6058 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleElementValueType6068 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleElementValueType6113 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleElementValueType6134 = new BitSet(new long[]{0x0010000004000002L});
    public static final BitSet FOLLOW_52_in_ruleElementValueType6146 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleElementValueType6167 = new BitSet(new long[]{0x0000000004000002L});
    public static final BitSet FOLLOW_26_in_ruleElementValueType6180 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleElementValueType6201 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMetadataType_in_entryRuleMetadataType6239 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMetadataType6249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_70_in_ruleMetadataType6293 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMetadataType6303 = new BitSet(new long[]{0x4020000006082000L,0x0000000000000080L});
    public static final BitSet FOLLOW_19_in_ruleMetadataType6314 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMetadataType6335 = new BitSet(new long[]{0x4020000006002000L,0x0000000000000080L});
    public static final BitSet FOLLOW_26_in_ruleMetadataType6348 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMetadataType6369 = new BitSet(new long[]{0x4020000002002000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_ruleMetadataType6382 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMetadataType6403 = new BitSet(new long[]{0x4020000002002000L});
    public static final BitSet FOLLOW_53_in_ruleMetadataType6416 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMetadataType6437 = new BitSet(new long[]{0x4000000002002000L});
    public static final BitSet FOLLOW_62_in_ruleMetadataType6450 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMetadataType6471 = new BitSet(new long[]{0x0000000002002000L});
    public static final BitSet FOLLOW_ruleColumnType_in_ruleMetadataType6494 = new BitSet(new long[]{0x0000000002002000L});
    public static final BitSet FOLLOW_13_in_ruleMetadataType6505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnType_in_entryRuleColumnType6541 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnType6551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_ruleColumnType6595 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleColumnType6605 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleColumnType6616 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleColumnType6637 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleColumnType6649 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_26_in_ruleColumnType6659 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleColumnType6680 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleColumnType6693 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_ruleColumnType6703 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleColumnType6724 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleColumnType6737 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_27_in_ruleColumnType6747 = new BitSet(new long[]{0x0000000000000000L,0x0000000000030000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleColumnType6768 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleColumnType6781 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_73_in_ruleColumnType6791 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleColumnType6812 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleColumnType6825 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_ruleColumnType6835 = new BitSet(new long[]{0x0000000000000040L,0x0000000000008000L});
    public static final BitSet FOLLOW_ruleEInt_in_ruleColumnType6856 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleColumnType6869 = new BitSet(new long[]{0x0020000000000000L});
    public static final BitSet FOLLOW_53_in_ruleColumnType6879 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleColumnType6900 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleColumnType6913 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000800L});
    public static final BitSet FOLLOW_75_in_ruleColumnType6923 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleColumnType6944 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleColumnType6957 = new BitSet(new long[]{0x0000000000000000L,0x0000000000001000L});
    public static final BitSet FOLLOW_76_in_ruleColumnType6967 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleColumnType6988 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleColumnType7001 = new BitSet(new long[]{0x0000000000000000L,0x0000000000002000L});
    public static final BitSet FOLLOW_77_in_ruleColumnType7011 = new BitSet(new long[]{0x0000000000000040L,0x0000000000008000L});
    public static final BitSet FOLLOW_ruleEInt_in_ruleColumnType7032 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleColumnType7045 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_78_in_ruleColumnType7055 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleColumnType7076 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleColumnType7088 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEString_in_entryRuleEString7125 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEString7136 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleEString7176 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleEString7202 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleEString7228 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEInt_in_entryRuleEInt7274 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEInt7285 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_79_in_ruleEInt7324 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleEInt7341 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEBoolean_in_entryRuleEBoolean7387 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEBoolean7398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_80_in_ruleEBoolean7436 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_ruleEBoolean7455 = new BitSet(new long[]{0x0000000000000002L});

}