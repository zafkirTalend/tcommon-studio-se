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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'addParameters'", "'{'", "'}'", "','", "'addDBMapData'", "'addInputTable'", "'addVarTable'", "'addOutputTable'", "'NAME:'", "'MINIMIZED:'", "'READONLY:'", "'TABLENAME:'", "'JOINTYPE:'", "'NO_JOIN'", "'INNER_JOIN'", "'LEFT_OUTER_JOIN'", "'RIGHT_OUTER_JOIN'", "'FULL_OUTER_JOIN'", "'CROSS_JOIN'", "'LEFT_OUTER_JOIN_ORACLE'", "'RIGHT_OUTER_JOIN_ORACLE'", "'ALIAS:'", "'addColumn'", "'TYPE:'", "'NULLABLE:'", "'EXPRESSION:'", "'JOIN:'", "'OPERATOR:'", "'addFilter'", "'addMapperData'", "'addUiProperties'", "'SHELLMAXIMIZED:'", "'SIZESTATE:'", "'MINIMIZED'", "'INTERMEDIATE'", "'MAXIMIZED'", "'EXPRESSIONFILTER:'", "'ACTIVATEEXPRESSIONFILTER:'", "'ACTIVATECONDENSEDTOOL:'", "'MATCHINGMODE:'", "'LOOKUPMODE:'", "'REJECT:'", "'REJECTINNERJOIN:'", "'ISERRORREJECTTABLE:'", "'ISJOINTABLEOF:'", "'addComponent'", "'setComponentDefinition'", "'POSITION:'", "'setSettings'", "'ContextType'", "'CONFIRMATIONNEED:'", "'addContextParameter'", "'VALUE:'", "'COMMENT:'", "'PROMPT:'", "'PROMPTNEEDED:'", "'REPOSITORYCONTEXTID:'", "'addConnection'", "'LINESTYLE:'", "'MERGEORDER:'", "'METANAME:'", "'OUTPUTID:'", "'SOURCE:'", "'TARGET:'", "'addNote'", "'opaque'", "'POSITION'", "'\"'", "'text'", "':'", "'addSchema'", "'LABEL:'", "'KEY:'", "'DEFAULTVALUE:'", "'LENGTH:'", "'ORIGINALDBCOLUMNNAME'", "'PATTREN:'", "'PRECISION:'", "'SOURCETYPE:'", "'-'", "'true'", "'false'"
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

                if ( (LA2_0==56) ) {
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

                if ( (LA3_0==68) ) {
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

                if ( (LA4_0==75) ) {
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

                if ( (LA5_0==60) ) {
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

            if ( (LA7_0==40) ) {
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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:523:1: ruleDBInputTable returns [EObject current=null] : ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( 'TABLENAME:' ( (lv_tableName_8_0= ruleEString ) ) )? ( 'JOINTYPE:' ( ( (lv_joinType_10_0= 'NO_JOIN' ) ) | ( (lv_joinType_11_0= 'INNER_JOIN' ) ) | ( (lv_joinType_12_0= 'LEFT_OUTER_JOIN' ) ) | ( (lv_joinType_13_0= 'RIGHT_OUTER_JOIN' ) ) | ( (lv_joinType_14_0= 'FULL_OUTER_JOIN' ) ) | ( (lv_joinType_15_0= 'CROSS_JOIN' ) ) | ( (lv_joinType_16_0= 'LEFT_OUTER_JOIN_ORACLE' ) ) | ( (lv_joinType_17_0= 'RIGHT_OUTER_JOIN_ORACLE' ) ) ) )? ( 'ALIAS:' ( (lv_alias_19_0= ruleEString ) ) )? ( (lv_DBMapperTableEntries_20_0= ruleDBMapperTableEntry ) )* ) ;
    public final EObject ruleDBInputTable() throws RecognitionException {
        EObject current = null;

        Token lv_joinType_10_0=null;
        Token lv_joinType_11_0=null;
        Token lv_joinType_12_0=null;
        Token lv_joinType_13_0=null;
        Token lv_joinType_14_0=null;
        Token lv_joinType_15_0=null;
        Token lv_joinType_16_0=null;
        Token lv_joinType_17_0=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_minimized_4_0 = null;

        AntlrDatatypeRuleToken lv_readonly_6_0 = null;

        AntlrDatatypeRuleToken lv_tableName_8_0 = null;

        AntlrDatatypeRuleToken lv_alias_19_0 = null;

        EObject lv_DBMapperTableEntries_20_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:528:6: ( ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( 'TABLENAME:' ( (lv_tableName_8_0= ruleEString ) ) )? ( 'JOINTYPE:' ( ( (lv_joinType_10_0= 'NO_JOIN' ) ) | ( (lv_joinType_11_0= 'INNER_JOIN' ) ) | ( (lv_joinType_12_0= 'LEFT_OUTER_JOIN' ) ) | ( (lv_joinType_13_0= 'RIGHT_OUTER_JOIN' ) ) | ( (lv_joinType_14_0= 'FULL_OUTER_JOIN' ) ) | ( (lv_joinType_15_0= 'CROSS_JOIN' ) ) | ( (lv_joinType_16_0= 'LEFT_OUTER_JOIN_ORACLE' ) ) | ( (lv_joinType_17_0= 'RIGHT_OUTER_JOIN_ORACLE' ) ) ) )? ( 'ALIAS:' ( (lv_alias_19_0= ruleEString ) ) )? ( (lv_DBMapperTableEntries_20_0= ruleDBMapperTableEntry ) )* ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:529:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( 'TABLENAME:' ( (lv_tableName_8_0= ruleEString ) ) )? ( 'JOINTYPE:' ( ( (lv_joinType_10_0= 'NO_JOIN' ) ) | ( (lv_joinType_11_0= 'INNER_JOIN' ) ) | ( (lv_joinType_12_0= 'LEFT_OUTER_JOIN' ) ) | ( (lv_joinType_13_0= 'RIGHT_OUTER_JOIN' ) ) | ( (lv_joinType_14_0= 'FULL_OUTER_JOIN' ) ) | ( (lv_joinType_15_0= 'CROSS_JOIN' ) ) | ( (lv_joinType_16_0= 'LEFT_OUTER_JOIN_ORACLE' ) ) | ( (lv_joinType_17_0= 'RIGHT_OUTER_JOIN_ORACLE' ) ) ) )? ( 'ALIAS:' ( (lv_alias_19_0= ruleEString ) ) )? ( (lv_DBMapperTableEntries_20_0= ruleDBMapperTableEntry ) )* )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:529:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( 'TABLENAME:' ( (lv_tableName_8_0= ruleEString ) ) )? ( 'JOINTYPE:' ( ( (lv_joinType_10_0= 'NO_JOIN' ) ) | ( (lv_joinType_11_0= 'INNER_JOIN' ) ) | ( (lv_joinType_12_0= 'LEFT_OUTER_JOIN' ) ) | ( (lv_joinType_13_0= 'RIGHT_OUTER_JOIN' ) ) | ( (lv_joinType_14_0= 'FULL_OUTER_JOIN' ) ) | ( (lv_joinType_15_0= 'CROSS_JOIN' ) ) | ( (lv_joinType_16_0= 'LEFT_OUTER_JOIN_ORACLE' ) ) | ( (lv_joinType_17_0= 'RIGHT_OUTER_JOIN_ORACLE' ) ) ) )? ( 'ALIAS:' ( (lv_alias_19_0= ruleEString ) ) )? ( (lv_DBMapperTableEntries_20_0= ruleDBMapperTableEntry ) )* )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:529:2: () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( 'TABLENAME:' ( (lv_tableName_8_0= ruleEString ) ) )? ( 'JOINTYPE:' ( ( (lv_joinType_10_0= 'NO_JOIN' ) ) | ( (lv_joinType_11_0= 'INNER_JOIN' ) ) | ( (lv_joinType_12_0= 'LEFT_OUTER_JOIN' ) ) | ( (lv_joinType_13_0= 'RIGHT_OUTER_JOIN' ) ) | ( (lv_joinType_14_0= 'FULL_OUTER_JOIN' ) ) | ( (lv_joinType_15_0= 'CROSS_JOIN' ) ) | ( (lv_joinType_16_0= 'LEFT_OUTER_JOIN_ORACLE' ) ) | ( (lv_joinType_17_0= 'RIGHT_OUTER_JOIN_ORACLE' ) ) ) )? ( 'ALIAS:' ( (lv_alias_19_0= ruleEString ) ) )? ( (lv_DBMapperTableEntries_20_0= ruleDBMapperTableEntry ) )*
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:652:4: ( 'JOINTYPE:' ( ( (lv_joinType_10_0= 'NO_JOIN' ) ) | ( (lv_joinType_11_0= 'INNER_JOIN' ) ) | ( (lv_joinType_12_0= 'LEFT_OUTER_JOIN' ) ) | ( (lv_joinType_13_0= 'RIGHT_OUTER_JOIN' ) ) | ( (lv_joinType_14_0= 'FULL_OUTER_JOIN' ) ) | ( (lv_joinType_15_0= 'CROSS_JOIN' ) ) | ( (lv_joinType_16_0= 'LEFT_OUTER_JOIN_ORACLE' ) ) | ( (lv_joinType_17_0= 'RIGHT_OUTER_JOIN_ORACLE' ) ) ) )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==23) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:652:6: 'JOINTYPE:' ( ( (lv_joinType_10_0= 'NO_JOIN' ) ) | ( (lv_joinType_11_0= 'INNER_JOIN' ) ) | ( (lv_joinType_12_0= 'LEFT_OUTER_JOIN' ) ) | ( (lv_joinType_13_0= 'RIGHT_OUTER_JOIN' ) ) | ( (lv_joinType_14_0= 'FULL_OUTER_JOIN' ) ) | ( (lv_joinType_15_0= 'CROSS_JOIN' ) ) | ( (lv_joinType_16_0= 'LEFT_OUTER_JOIN_ORACLE' ) ) | ( (lv_joinType_17_0= 'RIGHT_OUTER_JOIN_ORACLE' ) ) )
                    {
                    match(input,23,FOLLOW_23_in_ruleDBInputTable1002); 

                            createLeafNode(grammarAccess.getDBInputTableAccess().getJOINTYPEKeyword_6_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:656:1: ( ( (lv_joinType_10_0= 'NO_JOIN' ) ) | ( (lv_joinType_11_0= 'INNER_JOIN' ) ) | ( (lv_joinType_12_0= 'LEFT_OUTER_JOIN' ) ) | ( (lv_joinType_13_0= 'RIGHT_OUTER_JOIN' ) ) | ( (lv_joinType_14_0= 'FULL_OUTER_JOIN' ) ) | ( (lv_joinType_15_0= 'CROSS_JOIN' ) ) | ( (lv_joinType_16_0= 'LEFT_OUTER_JOIN_ORACLE' ) ) | ( (lv_joinType_17_0= 'RIGHT_OUTER_JOIN_ORACLE' ) ) )
                    int alt14=8;
                    switch ( input.LA(1) ) {
                    case 24:
                        {
                        alt14=1;
                        }
                        break;
                    case 25:
                        {
                        alt14=2;
                        }
                        break;
                    case 26:
                        {
                        alt14=3;
                        }
                        break;
                    case 27:
                        {
                        alt14=4;
                        }
                        break;
                    case 28:
                        {
                        alt14=5;
                        }
                        break;
                    case 29:
                        {
                        alt14=6;
                        }
                        break;
                    case 30:
                        {
                        alt14=7;
                        }
                        break;
                    case 31:
                        {
                        alt14=8;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("656:1: ( ( (lv_joinType_10_0= 'NO_JOIN' ) ) | ( (lv_joinType_11_0= 'INNER_JOIN' ) ) | ( (lv_joinType_12_0= 'LEFT_OUTER_JOIN' ) ) | ( (lv_joinType_13_0= 'RIGHT_OUTER_JOIN' ) ) | ( (lv_joinType_14_0= 'FULL_OUTER_JOIN' ) ) | ( (lv_joinType_15_0= 'CROSS_JOIN' ) ) | ( (lv_joinType_16_0= 'LEFT_OUTER_JOIN_ORACLE' ) ) | ( (lv_joinType_17_0= 'RIGHT_OUTER_JOIN_ORACLE' ) ) )", 14, 0, input);

                        throw nvae;
                    }

                    switch (alt14) {
                        case 1 :
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:656:2: ( (lv_joinType_10_0= 'NO_JOIN' ) )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:656:2: ( (lv_joinType_10_0= 'NO_JOIN' ) )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:657:1: (lv_joinType_10_0= 'NO_JOIN' )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:657:1: (lv_joinType_10_0= 'NO_JOIN' )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:658:3: lv_joinType_10_0= 'NO_JOIN'
                            {
                            lv_joinType_10_0=(Token)input.LT(1);
                            match(input,24,FOLLOW_24_in_ruleDBInputTable1021); 

                                    createLeafNode(grammarAccess.getDBInputTableAccess().getJoinTypeNO_JOINKeyword_6_1_0_0(), "joinType"); 
                                

                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getDBInputTableRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode, current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "joinType", lv_joinType_10_0, "NO_JOIN", lastConsumedNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	    

                            }


                            }


                            }
                            break;
                        case 2 :
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:678:6: ( (lv_joinType_11_0= 'INNER_JOIN' ) )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:678:6: ( (lv_joinType_11_0= 'INNER_JOIN' ) )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:679:1: (lv_joinType_11_0= 'INNER_JOIN' )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:679:1: (lv_joinType_11_0= 'INNER_JOIN' )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:680:3: lv_joinType_11_0= 'INNER_JOIN'
                            {
                            lv_joinType_11_0=(Token)input.LT(1);
                            match(input,25,FOLLOW_25_in_ruleDBInputTable1058); 

                                    createLeafNode(grammarAccess.getDBInputTableAccess().getJoinTypeINNER_JOINKeyword_6_1_1_0(), "joinType"); 
                                

                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getDBInputTableRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode, current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "joinType", lv_joinType_11_0, "INNER_JOIN", lastConsumedNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	    

                            }


                            }


                            }
                            break;
                        case 3 :
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:700:6: ( (lv_joinType_12_0= 'LEFT_OUTER_JOIN' ) )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:700:6: ( (lv_joinType_12_0= 'LEFT_OUTER_JOIN' ) )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:701:1: (lv_joinType_12_0= 'LEFT_OUTER_JOIN' )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:701:1: (lv_joinType_12_0= 'LEFT_OUTER_JOIN' )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:702:3: lv_joinType_12_0= 'LEFT_OUTER_JOIN'
                            {
                            lv_joinType_12_0=(Token)input.LT(1);
                            match(input,26,FOLLOW_26_in_ruleDBInputTable1095); 

                                    createLeafNode(grammarAccess.getDBInputTableAccess().getJoinTypeLEFT_OUTER_JOINKeyword_6_1_2_0(), "joinType"); 
                                

                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getDBInputTableRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode, current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "joinType", lv_joinType_12_0, "LEFT_OUTER_JOIN", lastConsumedNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	    

                            }


                            }


                            }
                            break;
                        case 4 :
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:722:6: ( (lv_joinType_13_0= 'RIGHT_OUTER_JOIN' ) )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:722:6: ( (lv_joinType_13_0= 'RIGHT_OUTER_JOIN' ) )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:723:1: (lv_joinType_13_0= 'RIGHT_OUTER_JOIN' )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:723:1: (lv_joinType_13_0= 'RIGHT_OUTER_JOIN' )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:724:3: lv_joinType_13_0= 'RIGHT_OUTER_JOIN'
                            {
                            lv_joinType_13_0=(Token)input.LT(1);
                            match(input,27,FOLLOW_27_in_ruleDBInputTable1132); 

                                    createLeafNode(grammarAccess.getDBInputTableAccess().getJoinTypeRIGHT_OUTER_JOINKeyword_6_1_3_0(), "joinType"); 
                                

                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getDBInputTableRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode, current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "joinType", lv_joinType_13_0, "RIGHT_OUTER_JOIN", lastConsumedNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	    

                            }


                            }


                            }
                            break;
                        case 5 :
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:744:6: ( (lv_joinType_14_0= 'FULL_OUTER_JOIN' ) )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:744:6: ( (lv_joinType_14_0= 'FULL_OUTER_JOIN' ) )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:745:1: (lv_joinType_14_0= 'FULL_OUTER_JOIN' )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:745:1: (lv_joinType_14_0= 'FULL_OUTER_JOIN' )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:746:3: lv_joinType_14_0= 'FULL_OUTER_JOIN'
                            {
                            lv_joinType_14_0=(Token)input.LT(1);
                            match(input,28,FOLLOW_28_in_ruleDBInputTable1169); 

                                    createLeafNode(grammarAccess.getDBInputTableAccess().getJoinTypeFULL_OUTER_JOINKeyword_6_1_4_0(), "joinType"); 
                                

                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getDBInputTableRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode, current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "joinType", lv_joinType_14_0, "FULL_OUTER_JOIN", lastConsumedNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	    

                            }


                            }


                            }
                            break;
                        case 6 :
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:766:6: ( (lv_joinType_15_0= 'CROSS_JOIN' ) )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:766:6: ( (lv_joinType_15_0= 'CROSS_JOIN' ) )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:767:1: (lv_joinType_15_0= 'CROSS_JOIN' )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:767:1: (lv_joinType_15_0= 'CROSS_JOIN' )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:768:3: lv_joinType_15_0= 'CROSS_JOIN'
                            {
                            lv_joinType_15_0=(Token)input.LT(1);
                            match(input,29,FOLLOW_29_in_ruleDBInputTable1206); 

                                    createLeafNode(grammarAccess.getDBInputTableAccess().getJoinTypeCROSS_JOINKeyword_6_1_5_0(), "joinType"); 
                                

                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getDBInputTableRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode, current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "joinType", lv_joinType_15_0, "CROSS_JOIN", lastConsumedNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	    

                            }


                            }


                            }
                            break;
                        case 7 :
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:788:6: ( (lv_joinType_16_0= 'LEFT_OUTER_JOIN_ORACLE' ) )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:788:6: ( (lv_joinType_16_0= 'LEFT_OUTER_JOIN_ORACLE' ) )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:789:1: (lv_joinType_16_0= 'LEFT_OUTER_JOIN_ORACLE' )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:789:1: (lv_joinType_16_0= 'LEFT_OUTER_JOIN_ORACLE' )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:790:3: lv_joinType_16_0= 'LEFT_OUTER_JOIN_ORACLE'
                            {
                            lv_joinType_16_0=(Token)input.LT(1);
                            match(input,30,FOLLOW_30_in_ruleDBInputTable1243); 

                                    createLeafNode(grammarAccess.getDBInputTableAccess().getJoinTypeLEFT_OUTER_JOIN_ORACLEKeyword_6_1_6_0(), "joinType"); 
                                

                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getDBInputTableRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode, current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "joinType", lv_joinType_16_0, "LEFT_OUTER_JOIN_ORACLE", lastConsumedNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	    

                            }


                            }


                            }
                            break;
                        case 8 :
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:810:6: ( (lv_joinType_17_0= 'RIGHT_OUTER_JOIN_ORACLE' ) )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:810:6: ( (lv_joinType_17_0= 'RIGHT_OUTER_JOIN_ORACLE' ) )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:811:1: (lv_joinType_17_0= 'RIGHT_OUTER_JOIN_ORACLE' )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:811:1: (lv_joinType_17_0= 'RIGHT_OUTER_JOIN_ORACLE' )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:812:3: lv_joinType_17_0= 'RIGHT_OUTER_JOIN_ORACLE'
                            {
                            lv_joinType_17_0=(Token)input.LT(1);
                            match(input,31,FOLLOW_31_in_ruleDBInputTable1280); 

                                    createLeafNode(grammarAccess.getDBInputTableAccess().getJoinTypeRIGHT_OUTER_JOIN_ORACLEKeyword_6_1_7_0(), "joinType"); 
                                

                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getDBInputTableRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode, current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "joinType", lv_joinType_17_0, "RIGHT_OUTER_JOIN_ORACLE", lastConsumedNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	    

                            }


                            }


                            }
                            break;

                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:831:5: ( 'ALIAS:' ( (lv_alias_19_0= ruleEString ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==32) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:831:7: 'ALIAS:' ( (lv_alias_19_0= ruleEString ) )
                    {
                    match(input,32,FOLLOW_32_in_ruleDBInputTable1307); 

                            createLeafNode(grammarAccess.getDBInputTableAccess().getALIASKeyword_7_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:835:1: ( (lv_alias_19_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:836:1: (lv_alias_19_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:836:1: (lv_alias_19_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:837:3: lv_alias_19_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBInputTableAccess().getAliasEStringParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleDBInputTable1328);
                    lv_alias_19_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getDBInputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"alias",
                    	        		lv_alias_19_0, 
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:859:4: ( (lv_DBMapperTableEntries_20_0= ruleDBMapperTableEntry ) )*
            loop17:
            do {
                int alt17=2;
                int LA17_0 = input.LA(1);

                if ( (LA17_0==33) ) {
                    alt17=1;
                }


                switch (alt17) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:860:1: (lv_DBMapperTableEntries_20_0= ruleDBMapperTableEntry )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:860:1: (lv_DBMapperTableEntries_20_0= ruleDBMapperTableEntry )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:861:3: lv_DBMapperTableEntries_20_0= ruleDBMapperTableEntry
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getDBInputTableAccess().getDBMapperTableEntriesDBMapperTableEntryParserRuleCall_8_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleDBMapperTableEntry_in_ruleDBInputTable1351);
            	    lv_DBMapperTableEntries_20_0=ruleDBMapperTableEntry();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getDBInputTableRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"DBMapperTableEntries",
            	    	        		lv_DBMapperTableEntries_20_0, 
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
            	    break loop17;
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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:891:1: entryRuleDBVarTable returns [EObject current=null] : iv_ruleDBVarTable= ruleDBVarTable EOF ;
    public final EObject entryRuleDBVarTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDBVarTable = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:892:2: (iv_ruleDBVarTable= ruleDBVarTable EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:893:2: iv_ruleDBVarTable= ruleDBVarTable EOF
            {
             currentNode = createCompositeNode(grammarAccess.getDBVarTableRule(), currentNode); 
            pushFollow(FOLLOW_ruleDBVarTable_in_entryRuleDBVarTable1388);
            iv_ruleDBVarTable=ruleDBVarTable();
            _fsp--;

             current =iv_ruleDBVarTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDBVarTable1398); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:900:1: ruleDBVarTable returns [EObject current=null] : ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )* ) ;
    public final EObject ruleDBVarTable() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_minimized_4_0 = null;

        AntlrDatatypeRuleToken lv_readonly_6_0 = null;

        EObject lv_DBMapperTableEntries_7_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:905:6: ( ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )* ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:906:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )* )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:906:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )* )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:906:2: () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )*
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:906:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:907:5: 
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

            match(input,19,FOLLOW_19_in_ruleDBVarTable1442); 

                    createLeafNode(grammarAccess.getDBVarTableAccess().getNAMEKeyword_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:921:1: ( (lv_name_2_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:922:1: (lv_name_2_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:922:1: (lv_name_2_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:923:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getDBVarTableAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleDBVarTable1463);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:945:2: ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==20) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:945:4: 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) )
                    {
                    match(input,20,FOLLOW_20_in_ruleDBVarTable1474); 

                            createLeafNode(grammarAccess.getDBVarTableAccess().getMINIMIZEDKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:949:1: ( (lv_minimized_4_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:950:1: (lv_minimized_4_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:950:1: (lv_minimized_4_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:951:3: lv_minimized_4_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBVarTableAccess().getMinimizedEBooleanParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleDBVarTable1495);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:973:4: ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==21) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:973:6: 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) )
                    {
                    match(input,21,FOLLOW_21_in_ruleDBVarTable1508); 

                            createLeafNode(grammarAccess.getDBVarTableAccess().getREADONLYKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:977:1: ( (lv_readonly_6_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:978:1: (lv_readonly_6_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:978:1: (lv_readonly_6_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:979:3: lv_readonly_6_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBVarTableAccess().getReadonlyEBooleanParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleDBVarTable1529);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1001:4: ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )*
            loop20:
            do {
                int alt20=2;
                int LA20_0 = input.LA(1);

                if ( (LA20_0==33) ) {
                    alt20=1;
                }


                switch (alt20) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1002:1: (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1002:1: (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1003:3: lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getDBVarTableAccess().getDBMapperTableEntriesDBMapperTableEntryParserRuleCall_5_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleDBMapperTableEntry_in_ruleDBVarTable1552);
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
            	    break loop20;
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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1033:1: entryRuleDBOutputTable returns [EObject current=null] : iv_ruleDBOutputTable= ruleDBOutputTable EOF ;
    public final EObject entryRuleDBOutputTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDBOutputTable = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1034:2: (iv_ruleDBOutputTable= ruleDBOutputTable EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1035:2: iv_ruleDBOutputTable= ruleDBOutputTable EOF
            {
             currentNode = createCompositeNode(grammarAccess.getDBOutputTableRule(), currentNode); 
            pushFollow(FOLLOW_ruleDBOutputTable_in_entryRuleDBOutputTable1589);
            iv_ruleDBOutputTable=ruleDBOutputTable();
            _fsp--;

             current =iv_ruleDBOutputTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDBOutputTable1599); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1042:1: ruleDBOutputTable returns [EObject current=null] : ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )* ( (lv_FilterEntries_8_0= ruleDBFilterEntry ) )* ) ;
    public final EObject ruleDBOutputTable() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_minimized_4_0 = null;

        AntlrDatatypeRuleToken lv_readonly_6_0 = null;

        EObject lv_DBMapperTableEntries_7_0 = null;

        EObject lv_FilterEntries_8_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1047:6: ( ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )* ( (lv_FilterEntries_8_0= ruleDBFilterEntry ) )* ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1048:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )* ( (lv_FilterEntries_8_0= ruleDBFilterEntry ) )* )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1048:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )* ( (lv_FilterEntries_8_0= ruleDBFilterEntry ) )* )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1048:2: () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )? ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )* ( (lv_FilterEntries_8_0= ruleDBFilterEntry ) )*
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1048:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1049:5: 
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

            match(input,19,FOLLOW_19_in_ruleDBOutputTable1643); 

                    createLeafNode(grammarAccess.getDBOutputTableAccess().getNAMEKeyword_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1063:1: ( (lv_name_2_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1064:1: (lv_name_2_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1064:1: (lv_name_2_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1065:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getDBOutputTableAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleDBOutputTable1664);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1087:2: ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==20) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1087:4: 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) )
                    {
                    match(input,20,FOLLOW_20_in_ruleDBOutputTable1675); 

                            createLeafNode(grammarAccess.getDBOutputTableAccess().getMINIMIZEDKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1091:1: ( (lv_minimized_4_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1092:1: (lv_minimized_4_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1092:1: (lv_minimized_4_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1093:3: lv_minimized_4_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBOutputTableAccess().getMinimizedEBooleanParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleDBOutputTable1696);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1115:4: ( 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==21) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1115:6: 'READONLY:' ( (lv_readonly_6_0= ruleEBoolean ) )
                    {
                    match(input,21,FOLLOW_21_in_ruleDBOutputTable1709); 

                            createLeafNode(grammarAccess.getDBOutputTableAccess().getREADONLYKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1119:1: ( (lv_readonly_6_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1120:1: (lv_readonly_6_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1120:1: (lv_readonly_6_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1121:3: lv_readonly_6_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBOutputTableAccess().getReadonlyEBooleanParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleDBOutputTable1730);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1143:4: ( (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry ) )*
            loop23:
            do {
                int alt23=2;
                int LA23_0 = input.LA(1);

                if ( (LA23_0==33) ) {
                    alt23=1;
                }


                switch (alt23) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1144:1: (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1144:1: (lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1145:3: lv_DBMapperTableEntries_7_0= ruleDBMapperTableEntry
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getDBOutputTableAccess().getDBMapperTableEntriesDBMapperTableEntryParserRuleCall_5_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleDBMapperTableEntry_in_ruleDBOutputTable1753);
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
            	    break loop23;
                }
            } while (true);

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1167:3: ( (lv_FilterEntries_8_0= ruleDBFilterEntry ) )*
            loop24:
            do {
                int alt24=2;
                int LA24_0 = input.LA(1);

                if ( (LA24_0==39) ) {
                    alt24=1;
                }


                switch (alt24) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1168:1: (lv_FilterEntries_8_0= ruleDBFilterEntry )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1168:1: (lv_FilterEntries_8_0= ruleDBFilterEntry )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1169:3: lv_FilterEntries_8_0= ruleDBFilterEntry
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getDBOutputTableAccess().getFilterEntriesDBFilterEntryParserRuleCall_6_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleDBFilterEntry_in_ruleDBOutputTable1775);
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
            	    break loop24;
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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1199:1: entryRuleDBMapperTableEntry returns [EObject current=null] : iv_ruleDBMapperTableEntry= ruleDBMapperTableEntry EOF ;
    public final EObject entryRuleDBMapperTableEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDBMapperTableEntry = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1200:2: (iv_ruleDBMapperTableEntry= ruleDBMapperTableEntry EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1201:2: iv_ruleDBMapperTableEntry= ruleDBMapperTableEntry EOF
            {
             currentNode = createCompositeNode(grammarAccess.getDBMapperTableEntryRule(), currentNode); 
            pushFollow(FOLLOW_ruleDBMapperTableEntry_in_entryRuleDBMapperTableEntry1812);
            iv_ruleDBMapperTableEntry=ruleDBMapperTableEntry();
            _fsp--;

             current =iv_ruleDBMapperTableEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDBMapperTableEntry1822); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1208:1: ruleDBMapperTableEntry returns [EObject current=null] : ( () 'addColumn' '{' 'NAME:' ( (lv_name_4_0= ruleEString ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )? ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )? ( ',' 'JOIN:' ( (lv_join_16_0= ruleEBoolean ) ) )? ( ',' 'OPERATOR:' ( (lv_operator_19_0= ruleEString ) ) )? '}' ) ;
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
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1213:6: ( ( () 'addColumn' '{' 'NAME:' ( (lv_name_4_0= ruleEString ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )? ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )? ( ',' 'JOIN:' ( (lv_join_16_0= ruleEBoolean ) ) )? ( ',' 'OPERATOR:' ( (lv_operator_19_0= ruleEString ) ) )? '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1214:1: ( () 'addColumn' '{' 'NAME:' ( (lv_name_4_0= ruleEString ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )? ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )? ( ',' 'JOIN:' ( (lv_join_16_0= ruleEBoolean ) ) )? ( ',' 'OPERATOR:' ( (lv_operator_19_0= ruleEString ) ) )? '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1214:1: ( () 'addColumn' '{' 'NAME:' ( (lv_name_4_0= ruleEString ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )? ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )? ( ',' 'JOIN:' ( (lv_join_16_0= ruleEBoolean ) ) )? ( ',' 'OPERATOR:' ( (lv_operator_19_0= ruleEString ) ) )? '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1214:2: () 'addColumn' '{' 'NAME:' ( (lv_name_4_0= ruleEString ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )? ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )? ( ',' 'JOIN:' ( (lv_join_16_0= ruleEBoolean ) ) )? ( ',' 'OPERATOR:' ( (lv_operator_19_0= ruleEString ) ) )? '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1214:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1215:5: 
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

            match(input,33,FOLLOW_33_in_ruleDBMapperTableEntry1866); 

                    createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getAddColumnKeyword_1(), null); 
                
            match(input,12,FOLLOW_12_in_ruleDBMapperTableEntry1876); 

                    createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            match(input,19,FOLLOW_19_in_ruleDBMapperTableEntry1886); 

                    createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getNAMEKeyword_3(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1237:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1238:1: (lv_name_4_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1238:1: (lv_name_4_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1239:3: lv_name_4_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getDBMapperTableEntryAccess().getNameEStringParserRuleCall_4_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleDBMapperTableEntry1907);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1261:2: ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==14) ) {
                int LA25_1 = input.LA(2);

                if ( (LA25_1==34) ) {
                    alt25=1;
                }
            }
            switch (alt25) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1261:4: ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleDBMapperTableEntry1918); 

                            createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getCommaKeyword_5_0(), null); 
                        
                    match(input,34,FOLLOW_34_in_ruleDBMapperTableEntry1928); 

                            createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getTYPEKeyword_5_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1269:1: ( (lv_type_7_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1270:1: (lv_type_7_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1270:1: (lv_type_7_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1271:3: lv_type_7_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBMapperTableEntryAccess().getTypeEStringParserRuleCall_5_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleDBMapperTableEntry1949);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1293:4: ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==14) ) {
                int LA26_1 = input.LA(2);

                if ( (LA26_1==35) ) {
                    alt26=1;
                }
            }
            switch (alt26) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1293:6: ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleDBMapperTableEntry1962); 

                            createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getCommaKeyword_6_0(), null); 
                        
                    match(input,35,FOLLOW_35_in_ruleDBMapperTableEntry1972); 

                            createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getNULLABLEKeyword_6_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1301:1: ( (lv_nullable_10_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1302:1: (lv_nullable_10_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1302:1: (lv_nullable_10_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1303:3: lv_nullable_10_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBMapperTableEntryAccess().getNullableEBooleanParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleDBMapperTableEntry1993);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1325:4: ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==14) ) {
                int LA27_1 = input.LA(2);

                if ( (LA27_1==36) ) {
                    alt27=1;
                }
            }
            switch (alt27) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1325:6: ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleDBMapperTableEntry2006); 

                            createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getCommaKeyword_7_0(), null); 
                        
                    match(input,36,FOLLOW_36_in_ruleDBMapperTableEntry2016); 

                            createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getEXPRESSIONKeyword_7_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1333:1: ( (lv_expression_13_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1334:1: (lv_expression_13_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1334:1: (lv_expression_13_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1335:3: lv_expression_13_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBMapperTableEntryAccess().getExpressionEStringParserRuleCall_7_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleDBMapperTableEntry2037);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1357:4: ( ',' 'JOIN:' ( (lv_join_16_0= ruleEBoolean ) ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==14) ) {
                int LA28_1 = input.LA(2);

                if ( (LA28_1==37) ) {
                    alt28=1;
                }
            }
            switch (alt28) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1357:6: ',' 'JOIN:' ( (lv_join_16_0= ruleEBoolean ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleDBMapperTableEntry2050); 

                            createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getCommaKeyword_8_0(), null); 
                        
                    match(input,37,FOLLOW_37_in_ruleDBMapperTableEntry2060); 

                            createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getJOINKeyword_8_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1365:1: ( (lv_join_16_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1366:1: (lv_join_16_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1366:1: (lv_join_16_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1367:3: lv_join_16_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBMapperTableEntryAccess().getJoinEBooleanParserRuleCall_8_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleDBMapperTableEntry2081);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1389:4: ( ',' 'OPERATOR:' ( (lv_operator_19_0= ruleEString ) ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==14) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1389:6: ',' 'OPERATOR:' ( (lv_operator_19_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleDBMapperTableEntry2094); 

                            createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getCommaKeyword_9_0(), null); 
                        
                    match(input,38,FOLLOW_38_in_ruleDBMapperTableEntry2104); 

                            createLeafNode(grammarAccess.getDBMapperTableEntryAccess().getOPERATORKeyword_9_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1397:1: ( (lv_operator_19_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1398:1: (lv_operator_19_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1398:1: (lv_operator_19_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1399:3: lv_operator_19_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getDBMapperTableEntryAccess().getOperatorEStringParserRuleCall_9_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleDBMapperTableEntry2125);
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

            match(input,13,FOLLOW_13_in_ruleDBMapperTableEntry2137); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1433:1: entryRuleDBFilterEntry returns [EObject current=null] : iv_ruleDBFilterEntry= ruleDBFilterEntry EOF ;
    public final EObject entryRuleDBFilterEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleDBFilterEntry = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1434:2: (iv_ruleDBFilterEntry= ruleDBFilterEntry EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1435:2: iv_ruleDBFilterEntry= ruleDBFilterEntry EOF
            {
             currentNode = createCompositeNode(grammarAccess.getDBFilterEntryRule(), currentNode); 
            pushFollow(FOLLOW_ruleDBFilterEntry_in_entryRuleDBFilterEntry2173);
            iv_ruleDBFilterEntry=ruleDBFilterEntry();
            _fsp--;

             current =iv_ruleDBFilterEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleDBFilterEntry2183); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1442:1: ruleDBFilterEntry returns [EObject current=null] : ( 'addFilter' '{' 'NAME:' ( (lv_name_3_0= ruleEString ) ) ',' 'EXPRESSION:' ( (lv_expression_6_0= ruleEString ) ) '}' ) ;
    public final EObject ruleDBFilterEntry() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_3_0 = null;

        AntlrDatatypeRuleToken lv_expression_6_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1447:6: ( ( 'addFilter' '{' 'NAME:' ( (lv_name_3_0= ruleEString ) ) ',' 'EXPRESSION:' ( (lv_expression_6_0= ruleEString ) ) '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1448:1: ( 'addFilter' '{' 'NAME:' ( (lv_name_3_0= ruleEString ) ) ',' 'EXPRESSION:' ( (lv_expression_6_0= ruleEString ) ) '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1448:1: ( 'addFilter' '{' 'NAME:' ( (lv_name_3_0= ruleEString ) ) ',' 'EXPRESSION:' ( (lv_expression_6_0= ruleEString ) ) '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1448:3: 'addFilter' '{' 'NAME:' ( (lv_name_3_0= ruleEString ) ) ',' 'EXPRESSION:' ( (lv_expression_6_0= ruleEString ) ) '}'
            {
            match(input,39,FOLLOW_39_in_ruleDBFilterEntry2218); 

                    createLeafNode(grammarAccess.getDBFilterEntryAccess().getAddFilterKeyword_0(), null); 
                
            match(input,12,FOLLOW_12_in_ruleDBFilterEntry2228); 

                    createLeafNode(grammarAccess.getDBFilterEntryAccess().getLeftCurlyBracketKeyword_1(), null); 
                
            match(input,19,FOLLOW_19_in_ruleDBFilterEntry2238); 

                    createLeafNode(grammarAccess.getDBFilterEntryAccess().getNAMEKeyword_2(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1460:1: ( (lv_name_3_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1461:1: (lv_name_3_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1461:1: (lv_name_3_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1462:3: lv_name_3_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getDBFilterEntryAccess().getNameEStringParserRuleCall_3_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleDBFilterEntry2259);
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

            match(input,14,FOLLOW_14_in_ruleDBFilterEntry2269); 

                    createLeafNode(grammarAccess.getDBFilterEntryAccess().getCommaKeyword_4(), null); 
                
            match(input,36,FOLLOW_36_in_ruleDBFilterEntry2279); 

                    createLeafNode(grammarAccess.getDBFilterEntryAccess().getEXPRESSIONKeyword_5(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1492:1: ( (lv_expression_6_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1493:1: (lv_expression_6_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1493:1: (lv_expression_6_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1494:3: lv_expression_6_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getDBFilterEntryAccess().getExpressionEStringParserRuleCall_6_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleDBFilterEntry2300);
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

            match(input,13,FOLLOW_13_in_ruleDBFilterEntry2310); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1528:1: entryRuleMapperData returns [EObject current=null] : iv_ruleMapperData= ruleMapperData EOF ;
    public final EObject entryRuleMapperData() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMapperData = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1529:2: (iv_ruleMapperData= ruleMapperData EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1530:2: iv_ruleMapperData= ruleMapperData EOF
            {
             currentNode = createCompositeNode(grammarAccess.getMapperDataRule(), currentNode); 
            pushFollow(FOLLOW_ruleMapperData_in_entryRuleMapperData2346);
            iv_ruleMapperData=ruleMapperData();
            _fsp--;

             current =iv_ruleMapperData; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMapperData2356); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1537:1: ruleMapperData returns [EObject current=null] : ( () 'addMapperData' '{' ( 'addUiProperties' '{' ( (lv_uiProperties_5_0= ruleUiProperties ) ) '}' )? ( 'addInputTable' '{' ( (lv_inputTables_9_0= ruleInputTable ) ) '}' )* ( 'addVarTable' '{' ( (lv_varTables_13_0= ruleVarTable ) ) '}' )* ( 'addOutputTable' '{' ( (lv_outputTables_17_0= ruleOutputTable ) ) '}' )* '}' ) ;
    public final EObject ruleMapperData() throws RecognitionException {
        EObject current = null;

        EObject lv_uiProperties_5_0 = null;

        EObject lv_inputTables_9_0 = null;

        EObject lv_varTables_13_0 = null;

        EObject lv_outputTables_17_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1542:6: ( ( () 'addMapperData' '{' ( 'addUiProperties' '{' ( (lv_uiProperties_5_0= ruleUiProperties ) ) '}' )? ( 'addInputTable' '{' ( (lv_inputTables_9_0= ruleInputTable ) ) '}' )* ( 'addVarTable' '{' ( (lv_varTables_13_0= ruleVarTable ) ) '}' )* ( 'addOutputTable' '{' ( (lv_outputTables_17_0= ruleOutputTable ) ) '}' )* '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1543:1: ( () 'addMapperData' '{' ( 'addUiProperties' '{' ( (lv_uiProperties_5_0= ruleUiProperties ) ) '}' )? ( 'addInputTable' '{' ( (lv_inputTables_9_0= ruleInputTable ) ) '}' )* ( 'addVarTable' '{' ( (lv_varTables_13_0= ruleVarTable ) ) '}' )* ( 'addOutputTable' '{' ( (lv_outputTables_17_0= ruleOutputTable ) ) '}' )* '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1543:1: ( () 'addMapperData' '{' ( 'addUiProperties' '{' ( (lv_uiProperties_5_0= ruleUiProperties ) ) '}' )? ( 'addInputTable' '{' ( (lv_inputTables_9_0= ruleInputTable ) ) '}' )* ( 'addVarTable' '{' ( (lv_varTables_13_0= ruleVarTable ) ) '}' )* ( 'addOutputTable' '{' ( (lv_outputTables_17_0= ruleOutputTable ) ) '}' )* '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1543:2: () 'addMapperData' '{' ( 'addUiProperties' '{' ( (lv_uiProperties_5_0= ruleUiProperties ) ) '}' )? ( 'addInputTable' '{' ( (lv_inputTables_9_0= ruleInputTable ) ) '}' )* ( 'addVarTable' '{' ( (lv_varTables_13_0= ruleVarTable ) ) '}' )* ( 'addOutputTable' '{' ( (lv_outputTables_17_0= ruleOutputTable ) ) '}' )* '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1543:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1544:5: 
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

            match(input,40,FOLLOW_40_in_ruleMapperData2400); 

                    createLeafNode(grammarAccess.getMapperDataAccess().getAddMapperDataKeyword_1(), null); 
                
            match(input,12,FOLLOW_12_in_ruleMapperData2410); 

                    createLeafNode(grammarAccess.getMapperDataAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1562:1: ( 'addUiProperties' '{' ( (lv_uiProperties_5_0= ruleUiProperties ) ) '}' )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==41) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1562:3: 'addUiProperties' '{' ( (lv_uiProperties_5_0= ruleUiProperties ) ) '}'
                    {
                    match(input,41,FOLLOW_41_in_ruleMapperData2421); 

                            createLeafNode(grammarAccess.getMapperDataAccess().getAddUiPropertiesKeyword_3_0(), null); 
                        
                    match(input,12,FOLLOW_12_in_ruleMapperData2431); 

                            createLeafNode(grammarAccess.getMapperDataAccess().getLeftCurlyBracketKeyword_3_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1570:1: ( (lv_uiProperties_5_0= ruleUiProperties ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1571:1: (lv_uiProperties_5_0= ruleUiProperties )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1571:1: (lv_uiProperties_5_0= ruleUiProperties )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1572:3: lv_uiProperties_5_0= ruleUiProperties
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMapperDataAccess().getUiPropertiesUiPropertiesParserRuleCall_3_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleUiProperties_in_ruleMapperData2452);
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

                    match(input,13,FOLLOW_13_in_ruleMapperData2462); 

                            createLeafNode(grammarAccess.getMapperDataAccess().getRightCurlyBracketKeyword_3_3(), null); 
                        

                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1598:3: ( 'addInputTable' '{' ( (lv_inputTables_9_0= ruleInputTable ) ) '}' )*
            loop31:
            do {
                int alt31=2;
                int LA31_0 = input.LA(1);

                if ( (LA31_0==16) ) {
                    alt31=1;
                }


                switch (alt31) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1598:5: 'addInputTable' '{' ( (lv_inputTables_9_0= ruleInputTable ) ) '}'
            	    {
            	    match(input,16,FOLLOW_16_in_ruleMapperData2475); 

            	            createLeafNode(grammarAccess.getMapperDataAccess().getAddInputTableKeyword_4_0(), null); 
            	        
            	    match(input,12,FOLLOW_12_in_ruleMapperData2485); 

            	            createLeafNode(grammarAccess.getMapperDataAccess().getLeftCurlyBracketKeyword_4_1(), null); 
            	        
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1606:1: ( (lv_inputTables_9_0= ruleInputTable ) )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1607:1: (lv_inputTables_9_0= ruleInputTable )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1607:1: (lv_inputTables_9_0= ruleInputTable )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1608:3: lv_inputTables_9_0= ruleInputTable
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getMapperDataAccess().getInputTablesInputTableParserRuleCall_4_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleInputTable_in_ruleMapperData2506);
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

            	    match(input,13,FOLLOW_13_in_ruleMapperData2516); 

            	            createLeafNode(grammarAccess.getMapperDataAccess().getRightCurlyBracketKeyword_4_3(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop31;
                }
            } while (true);

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1634:3: ( 'addVarTable' '{' ( (lv_varTables_13_0= ruleVarTable ) ) '}' )*
            loop32:
            do {
                int alt32=2;
                int LA32_0 = input.LA(1);

                if ( (LA32_0==17) ) {
                    alt32=1;
                }


                switch (alt32) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1634:5: 'addVarTable' '{' ( (lv_varTables_13_0= ruleVarTable ) ) '}'
            	    {
            	    match(input,17,FOLLOW_17_in_ruleMapperData2529); 

            	            createLeafNode(grammarAccess.getMapperDataAccess().getAddVarTableKeyword_5_0(), null); 
            	        
            	    match(input,12,FOLLOW_12_in_ruleMapperData2539); 

            	            createLeafNode(grammarAccess.getMapperDataAccess().getLeftCurlyBracketKeyword_5_1(), null); 
            	        
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1642:1: ( (lv_varTables_13_0= ruleVarTable ) )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1643:1: (lv_varTables_13_0= ruleVarTable )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1643:1: (lv_varTables_13_0= ruleVarTable )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1644:3: lv_varTables_13_0= ruleVarTable
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getMapperDataAccess().getVarTablesVarTableParserRuleCall_5_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleVarTable_in_ruleMapperData2560);
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

            	    match(input,13,FOLLOW_13_in_ruleMapperData2570); 

            	            createLeafNode(grammarAccess.getMapperDataAccess().getRightCurlyBracketKeyword_5_3(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop32;
                }
            } while (true);

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1670:3: ( 'addOutputTable' '{' ( (lv_outputTables_17_0= ruleOutputTable ) ) '}' )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==18) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1670:5: 'addOutputTable' '{' ( (lv_outputTables_17_0= ruleOutputTable ) ) '}'
            	    {
            	    match(input,18,FOLLOW_18_in_ruleMapperData2583); 

            	            createLeafNode(grammarAccess.getMapperDataAccess().getAddOutputTableKeyword_6_0(), null); 
            	        
            	    match(input,12,FOLLOW_12_in_ruleMapperData2593); 

            	            createLeafNode(grammarAccess.getMapperDataAccess().getLeftCurlyBracketKeyword_6_1(), null); 
            	        
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1678:1: ( (lv_outputTables_17_0= ruleOutputTable ) )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1679:1: (lv_outputTables_17_0= ruleOutputTable )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1679:1: (lv_outputTables_17_0= ruleOutputTable )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1680:3: lv_outputTables_17_0= ruleOutputTable
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getMapperDataAccess().getOutputTablesOutputTableParserRuleCall_6_2_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleOutputTable_in_ruleMapperData2614);
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

            	    match(input,13,FOLLOW_13_in_ruleMapperData2624); 

            	            createLeafNode(grammarAccess.getMapperDataAccess().getRightCurlyBracketKeyword_6_3(), null); 
            	        

            	    }
            	    break;

            	default :
            	    break loop33;
                }
            } while (true);

            match(input,13,FOLLOW_13_in_ruleMapperData2636); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1718:1: entryRuleUiProperties returns [EObject current=null] : iv_ruleUiProperties= ruleUiProperties EOF ;
    public final EObject entryRuleUiProperties() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUiProperties = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1719:2: (iv_ruleUiProperties= ruleUiProperties EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1720:2: iv_ruleUiProperties= ruleUiProperties EOF
            {
             currentNode = createCompositeNode(grammarAccess.getUiPropertiesRule(), currentNode); 
            pushFollow(FOLLOW_ruleUiProperties_in_entryRuleUiProperties2672);
            iv_ruleUiProperties=ruleUiProperties();
            _fsp--;

             current =iv_ruleUiProperties; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUiProperties2682); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1727:1: ruleUiProperties returns [EObject current=null] : ( () 'SHELLMAXIMIZED:' ( (lv_shellMaximized_2_0= ruleEBoolean ) ) ) ;
    public final EObject ruleUiProperties() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_shellMaximized_2_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1732:6: ( ( () 'SHELLMAXIMIZED:' ( (lv_shellMaximized_2_0= ruleEBoolean ) ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1733:1: ( () 'SHELLMAXIMIZED:' ( (lv_shellMaximized_2_0= ruleEBoolean ) ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1733:1: ( () 'SHELLMAXIMIZED:' ( (lv_shellMaximized_2_0= ruleEBoolean ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1733:2: () 'SHELLMAXIMIZED:' ( (lv_shellMaximized_2_0= ruleEBoolean ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1733:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1734:5: 
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

            match(input,42,FOLLOW_42_in_ruleUiProperties2726); 

                    createLeafNode(grammarAccess.getUiPropertiesAccess().getSHELLMAXIMIZEDKeyword_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1748:1: ( (lv_shellMaximized_2_0= ruleEBoolean ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1749:1: (lv_shellMaximized_2_0= ruleEBoolean )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1749:1: (lv_shellMaximized_2_0= ruleEBoolean )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1750:3: lv_shellMaximized_2_0= ruleEBoolean
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getUiPropertiesAccess().getShellMaximizedEBooleanParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEBoolean_in_ruleUiProperties2747);
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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1780:1: entryRuleInputTable returns [EObject current=null] : iv_ruleInputTable= ruleInputTable EOF ;
    public final EObject entryRuleInputTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleInputTable = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1781:2: (iv_ruleInputTable= ruleInputTable EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1782:2: iv_ruleInputTable= ruleInputTable EOF
            {
             currentNode = createCompositeNode(grammarAccess.getInputTableRule(), currentNode); 
            pushFollow(FOLLOW_ruleInputTable_in_entryRuleInputTable2783);
            iv_ruleInputTable=ruleInputTable();
            _fsp--;

             current =iv_ruleInputTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleInputTable2793); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1789:1: ruleInputTable returns [EObject current=null] : ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) ) )? ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_10_0= ruleEString ) ) )? ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_12_0= ruleEBoolean ) ) )? ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_14_0= ruleEBoolean ) ) )? ( 'MATCHINGMODE:' ( (lv_matchingMode_16_0= ruleEString ) ) )? ( 'LOOKUPMODE:' ( (lv_lookupMode_18_0= ruleEString ) ) )? ( (lv_mapperTableEntries_19_0= ruleMapperTableEntry ) )* ) ;
    public final EObject ruleInputTable() throws RecognitionException {
        EObject current = null;

        Token lv_sizeState_6_0=null;
        Token lv_sizeState_7_0=null;
        Token lv_sizeState_8_0=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_minimized_4_0 = null;

        AntlrDatatypeRuleToken lv_expressionFilter_10_0 = null;

        AntlrDatatypeRuleToken lv_activateExpressionFilter_12_0 = null;

        AntlrDatatypeRuleToken lv_activateCondensedTool_14_0 = null;

        AntlrDatatypeRuleToken lv_matchingMode_16_0 = null;

        AntlrDatatypeRuleToken lv_lookupMode_18_0 = null;

        EObject lv_mapperTableEntries_19_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1794:6: ( ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) ) )? ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_10_0= ruleEString ) ) )? ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_12_0= ruleEBoolean ) ) )? ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_14_0= ruleEBoolean ) ) )? ( 'MATCHINGMODE:' ( (lv_matchingMode_16_0= ruleEString ) ) )? ( 'LOOKUPMODE:' ( (lv_lookupMode_18_0= ruleEString ) ) )? ( (lv_mapperTableEntries_19_0= ruleMapperTableEntry ) )* ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1795:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) ) )? ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_10_0= ruleEString ) ) )? ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_12_0= ruleEBoolean ) ) )? ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_14_0= ruleEBoolean ) ) )? ( 'MATCHINGMODE:' ( (lv_matchingMode_16_0= ruleEString ) ) )? ( 'LOOKUPMODE:' ( (lv_lookupMode_18_0= ruleEString ) ) )? ( (lv_mapperTableEntries_19_0= ruleMapperTableEntry ) )* )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1795:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) ) )? ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_10_0= ruleEString ) ) )? ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_12_0= ruleEBoolean ) ) )? ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_14_0= ruleEBoolean ) ) )? ( 'MATCHINGMODE:' ( (lv_matchingMode_16_0= ruleEString ) ) )? ( 'LOOKUPMODE:' ( (lv_lookupMode_18_0= ruleEString ) ) )? ( (lv_mapperTableEntries_19_0= ruleMapperTableEntry ) )* )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1795:2: () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) ) )? ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_10_0= ruleEString ) ) )? ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_12_0= ruleEBoolean ) ) )? ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_14_0= ruleEBoolean ) ) )? ( 'MATCHINGMODE:' ( (lv_matchingMode_16_0= ruleEString ) ) )? ( 'LOOKUPMODE:' ( (lv_lookupMode_18_0= ruleEString ) ) )? ( (lv_mapperTableEntries_19_0= ruleMapperTableEntry ) )*
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1795:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1796:5: 
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

            match(input,19,FOLLOW_19_in_ruleInputTable2837); 

                    createLeafNode(grammarAccess.getInputTableAccess().getNAMEKeyword_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1810:1: ( (lv_name_2_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1811:1: (lv_name_2_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1811:1: (lv_name_2_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1812:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getInputTableAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleInputTable2858);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1834:2: ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )?
            int alt34=2;
            int LA34_0 = input.LA(1);

            if ( (LA34_0==20) ) {
                alt34=1;
            }
            switch (alt34) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1834:4: 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) )
                    {
                    match(input,20,FOLLOW_20_in_ruleInputTable2869); 

                            createLeafNode(grammarAccess.getInputTableAccess().getMINIMIZEDKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1838:1: ( (lv_minimized_4_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1839:1: (lv_minimized_4_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1839:1: (lv_minimized_4_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1840:3: lv_minimized_4_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getInputTableAccess().getMinimizedEBooleanParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleInputTable2890);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1862:4: ( 'SIZESTATE:' ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==43) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1862:6: 'SIZESTATE:' ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) )
                    {
                    match(input,43,FOLLOW_43_in_ruleInputTable2903); 

                            createLeafNode(grammarAccess.getInputTableAccess().getSIZESTATEKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1866:1: ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) )
                    int alt35=3;
                    switch ( input.LA(1) ) {
                    case 44:
                        {
                        alt35=1;
                        }
                        break;
                    case 45:
                        {
                        alt35=2;
                        }
                        break;
                    case 46:
                        {
                        alt35=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("1866:1: ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) )", 35, 0, input);

                        throw nvae;
                    }

                    switch (alt35) {
                        case 1 :
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1866:2: ( (lv_sizeState_6_0= 'MINIMIZED' ) )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1866:2: ( (lv_sizeState_6_0= 'MINIMIZED' ) )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1867:1: (lv_sizeState_6_0= 'MINIMIZED' )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1867:1: (lv_sizeState_6_0= 'MINIMIZED' )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1868:3: lv_sizeState_6_0= 'MINIMIZED'
                            {
                            lv_sizeState_6_0=(Token)input.LT(1);
                            match(input,44,FOLLOW_44_in_ruleInputTable2922); 

                                    createLeafNode(grammarAccess.getInputTableAccess().getSizeStateMINIMIZEDKeyword_4_1_0_0(), "sizeState"); 
                                

                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getInputTableRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode, current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "sizeState", lv_sizeState_6_0, "MINIMIZED", lastConsumedNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	    

                            }


                            }


                            }
                            break;
                        case 2 :
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1888:6: ( (lv_sizeState_7_0= 'INTERMEDIATE' ) )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1888:6: ( (lv_sizeState_7_0= 'INTERMEDIATE' ) )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1889:1: (lv_sizeState_7_0= 'INTERMEDIATE' )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1889:1: (lv_sizeState_7_0= 'INTERMEDIATE' )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1890:3: lv_sizeState_7_0= 'INTERMEDIATE'
                            {
                            lv_sizeState_7_0=(Token)input.LT(1);
                            match(input,45,FOLLOW_45_in_ruleInputTable2959); 

                                    createLeafNode(grammarAccess.getInputTableAccess().getSizeStateINTERMEDIATEKeyword_4_1_1_0(), "sizeState"); 
                                

                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getInputTableRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode, current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "sizeState", lv_sizeState_7_0, "INTERMEDIATE", lastConsumedNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	    

                            }


                            }


                            }
                            break;
                        case 3 :
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1910:6: ( (lv_sizeState_8_0= 'MAXIMIZED' ) )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1910:6: ( (lv_sizeState_8_0= 'MAXIMIZED' ) )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1911:1: (lv_sizeState_8_0= 'MAXIMIZED' )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1911:1: (lv_sizeState_8_0= 'MAXIMIZED' )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1912:3: lv_sizeState_8_0= 'MAXIMIZED'
                            {
                            lv_sizeState_8_0=(Token)input.LT(1);
                            match(input,46,FOLLOW_46_in_ruleInputTable2996); 

                                    createLeafNode(grammarAccess.getInputTableAccess().getSizeStateMAXIMIZEDKeyword_4_1_2_0(), "sizeState"); 
                                

                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getInputTableRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode, current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "sizeState", lv_sizeState_8_0, "MAXIMIZED", lastConsumedNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	    

                            }


                            }


                            }
                            break;

                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1931:5: ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_10_0= ruleEString ) ) )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==47) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1931:7: 'EXPRESSIONFILTER:' ( (lv_expressionFilter_10_0= ruleEString ) )
                    {
                    match(input,47,FOLLOW_47_in_ruleInputTable3023); 

                            createLeafNode(grammarAccess.getInputTableAccess().getEXPRESSIONFILTERKeyword_5_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1935:1: ( (lv_expressionFilter_10_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1936:1: (lv_expressionFilter_10_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1936:1: (lv_expressionFilter_10_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1937:3: lv_expressionFilter_10_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getInputTableAccess().getExpressionFilterEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleInputTable3044);
                    lv_expressionFilter_10_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getInputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"expressionFilter",
                    	        		lv_expressionFilter_10_0, 
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1959:4: ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_12_0= ruleEBoolean ) ) )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==48) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1959:6: 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_12_0= ruleEBoolean ) )
                    {
                    match(input,48,FOLLOW_48_in_ruleInputTable3057); 

                            createLeafNode(grammarAccess.getInputTableAccess().getACTIVATEEXPRESSIONFILTERKeyword_6_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1963:1: ( (lv_activateExpressionFilter_12_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1964:1: (lv_activateExpressionFilter_12_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1964:1: (lv_activateExpressionFilter_12_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1965:3: lv_activateExpressionFilter_12_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getInputTableAccess().getActivateExpressionFilterEBooleanParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleInputTable3078);
                    lv_activateExpressionFilter_12_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getInputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"activateExpressionFilter",
                    	        		lv_activateExpressionFilter_12_0, 
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1987:4: ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_14_0= ruleEBoolean ) ) )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==49) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1987:6: 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_14_0= ruleEBoolean ) )
                    {
                    match(input,49,FOLLOW_49_in_ruleInputTable3091); 

                            createLeafNode(grammarAccess.getInputTableAccess().getACTIVATECONDENSEDTOOLKeyword_7_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1991:1: ( (lv_activateCondensedTool_14_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1992:1: (lv_activateCondensedTool_14_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1992:1: (lv_activateCondensedTool_14_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1993:3: lv_activateCondensedTool_14_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getInputTableAccess().getActivateCondensedToolEBooleanParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleInputTable3112);
                    lv_activateCondensedTool_14_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getInputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"activateCondensedTool",
                    	        		lv_activateCondensedTool_14_0, 
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2015:4: ( 'MATCHINGMODE:' ( (lv_matchingMode_16_0= ruleEString ) ) )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==50) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2015:6: 'MATCHINGMODE:' ( (lv_matchingMode_16_0= ruleEString ) )
                    {
                    match(input,50,FOLLOW_50_in_ruleInputTable3125); 

                            createLeafNode(grammarAccess.getInputTableAccess().getMATCHINGMODEKeyword_8_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2019:1: ( (lv_matchingMode_16_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2020:1: (lv_matchingMode_16_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2020:1: (lv_matchingMode_16_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2021:3: lv_matchingMode_16_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getInputTableAccess().getMatchingModeEStringParserRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleInputTable3146);
                    lv_matchingMode_16_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getInputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"matchingMode",
                    	        		lv_matchingMode_16_0, 
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2043:4: ( 'LOOKUPMODE:' ( (lv_lookupMode_18_0= ruleEString ) ) )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==51) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2043:6: 'LOOKUPMODE:' ( (lv_lookupMode_18_0= ruleEString ) )
                    {
                    match(input,51,FOLLOW_51_in_ruleInputTable3159); 

                            createLeafNode(grammarAccess.getInputTableAccess().getLOOKUPMODEKeyword_9_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2047:1: ( (lv_lookupMode_18_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2048:1: (lv_lookupMode_18_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2048:1: (lv_lookupMode_18_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2049:3: lv_lookupMode_18_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getInputTableAccess().getLookupModeEStringParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleInputTable3180);
                    lv_lookupMode_18_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getInputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"lookupMode",
                    	        		lv_lookupMode_18_0, 
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2071:4: ( (lv_mapperTableEntries_19_0= ruleMapperTableEntry ) )*
            loop42:
            do {
                int alt42=2;
                int LA42_0 = input.LA(1);

                if ( (LA42_0==33) ) {
                    alt42=1;
                }


                switch (alt42) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2072:1: (lv_mapperTableEntries_19_0= ruleMapperTableEntry )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2072:1: (lv_mapperTableEntries_19_0= ruleMapperTableEntry )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2073:3: lv_mapperTableEntries_19_0= ruleMapperTableEntry
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getInputTableAccess().getMapperTableEntriesMapperTableEntryParserRuleCall_10_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleMapperTableEntry_in_ruleInputTable3203);
            	    lv_mapperTableEntries_19_0=ruleMapperTableEntry();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getInputTableRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"mapperTableEntries",
            	    	        		lv_mapperTableEntries_19_0, 
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
            	    break loop42;
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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2103:1: entryRuleVarTable returns [EObject current=null] : iv_ruleVarTable= ruleVarTable EOF ;
    public final EObject entryRuleVarTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleVarTable = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2104:2: (iv_ruleVarTable= ruleVarTable EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2105:2: iv_ruleVarTable= ruleVarTable EOF
            {
             currentNode = createCompositeNode(grammarAccess.getVarTableRule(), currentNode); 
            pushFollow(FOLLOW_ruleVarTable_in_entryRuleVarTable3240);
            iv_ruleVarTable=ruleVarTable();
            _fsp--;

             current =iv_ruleVarTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleVarTable3250); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2112:1: ruleVarTable returns [EObject current=null] : ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) ) )? ( (lv_mapperTableEntries_9_0= ruleMapperTableEntry ) )* ) ;
    public final EObject ruleVarTable() throws RecognitionException {
        EObject current = null;

        Token lv_sizeState_6_0=null;
        Token lv_sizeState_7_0=null;
        Token lv_sizeState_8_0=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_minimized_4_0 = null;

        EObject lv_mapperTableEntries_9_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2117:6: ( ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) ) )? ( (lv_mapperTableEntries_9_0= ruleMapperTableEntry ) )* ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2118:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) ) )? ( (lv_mapperTableEntries_9_0= ruleMapperTableEntry ) )* )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2118:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) ) )? ( (lv_mapperTableEntries_9_0= ruleMapperTableEntry ) )* )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2118:2: () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) ) )? ( (lv_mapperTableEntries_9_0= ruleMapperTableEntry ) )*
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2118:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2119:5: 
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

            match(input,19,FOLLOW_19_in_ruleVarTable3294); 

                    createLeafNode(grammarAccess.getVarTableAccess().getNAMEKeyword_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2133:1: ( (lv_name_2_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2134:1: (lv_name_2_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2134:1: (lv_name_2_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2135:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getVarTableAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleVarTable3315);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2157:2: ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )?
            int alt43=2;
            int LA43_0 = input.LA(1);

            if ( (LA43_0==20) ) {
                alt43=1;
            }
            switch (alt43) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2157:4: 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) )
                    {
                    match(input,20,FOLLOW_20_in_ruleVarTable3326); 

                            createLeafNode(grammarAccess.getVarTableAccess().getMINIMIZEDKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2161:1: ( (lv_minimized_4_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2162:1: (lv_minimized_4_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2162:1: (lv_minimized_4_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2163:3: lv_minimized_4_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getVarTableAccess().getMinimizedEBooleanParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleVarTable3347);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2185:4: ( 'SIZESTATE:' ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) ) )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==43) ) {
                alt45=1;
            }
            switch (alt45) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2185:6: 'SIZESTATE:' ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) )
                    {
                    match(input,43,FOLLOW_43_in_ruleVarTable3360); 

                            createLeafNode(grammarAccess.getVarTableAccess().getSIZESTATEKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2189:1: ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) )
                    int alt44=3;
                    switch ( input.LA(1) ) {
                    case 44:
                        {
                        alt44=1;
                        }
                        break;
                    case 45:
                        {
                        alt44=2;
                        }
                        break;
                    case 46:
                        {
                        alt44=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("2189:1: ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) )", 44, 0, input);

                        throw nvae;
                    }

                    switch (alt44) {
                        case 1 :
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2189:2: ( (lv_sizeState_6_0= 'MINIMIZED' ) )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2189:2: ( (lv_sizeState_6_0= 'MINIMIZED' ) )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2190:1: (lv_sizeState_6_0= 'MINIMIZED' )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2190:1: (lv_sizeState_6_0= 'MINIMIZED' )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2191:3: lv_sizeState_6_0= 'MINIMIZED'
                            {
                            lv_sizeState_6_0=(Token)input.LT(1);
                            match(input,44,FOLLOW_44_in_ruleVarTable3379); 

                                    createLeafNode(grammarAccess.getVarTableAccess().getSizeStateMINIMIZEDKeyword_4_1_0_0(), "sizeState"); 
                                

                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getVarTableRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode, current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "sizeState", lv_sizeState_6_0, "MINIMIZED", lastConsumedNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	    

                            }


                            }


                            }
                            break;
                        case 2 :
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2211:6: ( (lv_sizeState_7_0= 'INTERMEDIATE' ) )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2211:6: ( (lv_sizeState_7_0= 'INTERMEDIATE' ) )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2212:1: (lv_sizeState_7_0= 'INTERMEDIATE' )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2212:1: (lv_sizeState_7_0= 'INTERMEDIATE' )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2213:3: lv_sizeState_7_0= 'INTERMEDIATE'
                            {
                            lv_sizeState_7_0=(Token)input.LT(1);
                            match(input,45,FOLLOW_45_in_ruleVarTable3416); 

                                    createLeafNode(grammarAccess.getVarTableAccess().getSizeStateINTERMEDIATEKeyword_4_1_1_0(), "sizeState"); 
                                

                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getVarTableRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode, current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "sizeState", lv_sizeState_7_0, "INTERMEDIATE", lastConsumedNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	    

                            }


                            }


                            }
                            break;
                        case 3 :
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2233:6: ( (lv_sizeState_8_0= 'MAXIMIZED' ) )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2233:6: ( (lv_sizeState_8_0= 'MAXIMIZED' ) )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2234:1: (lv_sizeState_8_0= 'MAXIMIZED' )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2234:1: (lv_sizeState_8_0= 'MAXIMIZED' )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2235:3: lv_sizeState_8_0= 'MAXIMIZED'
                            {
                            lv_sizeState_8_0=(Token)input.LT(1);
                            match(input,46,FOLLOW_46_in_ruleVarTable3453); 

                                    createLeafNode(grammarAccess.getVarTableAccess().getSizeStateMAXIMIZEDKeyword_4_1_2_0(), "sizeState"); 
                                

                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getVarTableRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode, current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "sizeState", lv_sizeState_8_0, "MAXIMIZED", lastConsumedNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	    

                            }


                            }


                            }
                            break;

                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2254:5: ( (lv_mapperTableEntries_9_0= ruleMapperTableEntry ) )*
            loop46:
            do {
                int alt46=2;
                int LA46_0 = input.LA(1);

                if ( (LA46_0==33) ) {
                    alt46=1;
                }


                switch (alt46) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2255:1: (lv_mapperTableEntries_9_0= ruleMapperTableEntry )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2255:1: (lv_mapperTableEntries_9_0= ruleMapperTableEntry )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2256:3: lv_mapperTableEntries_9_0= ruleMapperTableEntry
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getVarTableAccess().getMapperTableEntriesMapperTableEntryParserRuleCall_5_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleMapperTableEntry_in_ruleVarTable3490);
            	    lv_mapperTableEntries_9_0=ruleMapperTableEntry();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getVarTableRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"mapperTableEntries",
            	    	        		lv_mapperTableEntries_9_0, 
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
            	    break loop46;
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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2286:1: entryRuleOutputTable returns [EObject current=null] : iv_ruleOutputTable= ruleOutputTable EOF ;
    public final EObject entryRuleOutputTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleOutputTable = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2287:2: (iv_ruleOutputTable= ruleOutputTable EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2288:2: iv_ruleOutputTable= ruleOutputTable EOF
            {
             currentNode = createCompositeNode(grammarAccess.getOutputTableRule(), currentNode); 
            pushFollow(FOLLOW_ruleOutputTable_in_entryRuleOutputTable3527);
            iv_ruleOutputTable=ruleOutputTable();
            _fsp--;

             current =iv_ruleOutputTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleOutputTable3537); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2295:1: ruleOutputTable returns [EObject current=null] : ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) ) )? ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_10_0= ruleEString ) ) )? ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_12_0= ruleEBoolean ) ) )? ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_14_0= ruleEBoolean ) ) )? ( 'REJECT:' ( (lv_reject_16_0= ruleEBoolean ) ) )? ( 'REJECTINNERJOIN:' ( (lv_rejectInnerJoin_18_0= ruleEBoolean ) ) )? ( 'ISERRORREJECTTABLE:' ( (lv_isErrorRejectTable_20_0= ruleEBoolean ) ) )? ( 'ISJOINTABLEOF:' ( (lv_isJoinTableOf_22_0= ruleEString ) ) )? ( (lv_mapperTableEntries_23_0= ruleMapperTableEntry ) )* ) ;
    public final EObject ruleOutputTable() throws RecognitionException {
        EObject current = null;

        Token lv_sizeState_6_0=null;
        Token lv_sizeState_7_0=null;
        Token lv_sizeState_8_0=null;
        AntlrDatatypeRuleToken lv_name_2_0 = null;

        AntlrDatatypeRuleToken lv_minimized_4_0 = null;

        AntlrDatatypeRuleToken lv_expressionFilter_10_0 = null;

        AntlrDatatypeRuleToken lv_activateExpressionFilter_12_0 = null;

        AntlrDatatypeRuleToken lv_activateCondensedTool_14_0 = null;

        AntlrDatatypeRuleToken lv_reject_16_0 = null;

        AntlrDatatypeRuleToken lv_rejectInnerJoin_18_0 = null;

        AntlrDatatypeRuleToken lv_isErrorRejectTable_20_0 = null;

        AntlrDatatypeRuleToken lv_isJoinTableOf_22_0 = null;

        EObject lv_mapperTableEntries_23_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2300:6: ( ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) ) )? ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_10_0= ruleEString ) ) )? ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_12_0= ruleEBoolean ) ) )? ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_14_0= ruleEBoolean ) ) )? ( 'REJECT:' ( (lv_reject_16_0= ruleEBoolean ) ) )? ( 'REJECTINNERJOIN:' ( (lv_rejectInnerJoin_18_0= ruleEBoolean ) ) )? ( 'ISERRORREJECTTABLE:' ( (lv_isErrorRejectTable_20_0= ruleEBoolean ) ) )? ( 'ISJOINTABLEOF:' ( (lv_isJoinTableOf_22_0= ruleEString ) ) )? ( (lv_mapperTableEntries_23_0= ruleMapperTableEntry ) )* ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2301:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) ) )? ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_10_0= ruleEString ) ) )? ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_12_0= ruleEBoolean ) ) )? ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_14_0= ruleEBoolean ) ) )? ( 'REJECT:' ( (lv_reject_16_0= ruleEBoolean ) ) )? ( 'REJECTINNERJOIN:' ( (lv_rejectInnerJoin_18_0= ruleEBoolean ) ) )? ( 'ISERRORREJECTTABLE:' ( (lv_isErrorRejectTable_20_0= ruleEBoolean ) ) )? ( 'ISJOINTABLEOF:' ( (lv_isJoinTableOf_22_0= ruleEString ) ) )? ( (lv_mapperTableEntries_23_0= ruleMapperTableEntry ) )* )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2301:1: ( () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) ) )? ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_10_0= ruleEString ) ) )? ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_12_0= ruleEBoolean ) ) )? ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_14_0= ruleEBoolean ) ) )? ( 'REJECT:' ( (lv_reject_16_0= ruleEBoolean ) ) )? ( 'REJECTINNERJOIN:' ( (lv_rejectInnerJoin_18_0= ruleEBoolean ) ) )? ( 'ISERRORREJECTTABLE:' ( (lv_isErrorRejectTable_20_0= ruleEBoolean ) ) )? ( 'ISJOINTABLEOF:' ( (lv_isJoinTableOf_22_0= ruleEString ) ) )? ( (lv_mapperTableEntries_23_0= ruleMapperTableEntry ) )* )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2301:2: () 'NAME:' ( (lv_name_2_0= ruleEString ) ) ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )? ( 'SIZESTATE:' ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) ) )? ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_10_0= ruleEString ) ) )? ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_12_0= ruleEBoolean ) ) )? ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_14_0= ruleEBoolean ) ) )? ( 'REJECT:' ( (lv_reject_16_0= ruleEBoolean ) ) )? ( 'REJECTINNERJOIN:' ( (lv_rejectInnerJoin_18_0= ruleEBoolean ) ) )? ( 'ISERRORREJECTTABLE:' ( (lv_isErrorRejectTable_20_0= ruleEBoolean ) ) )? ( 'ISJOINTABLEOF:' ( (lv_isJoinTableOf_22_0= ruleEString ) ) )? ( (lv_mapperTableEntries_23_0= ruleMapperTableEntry ) )*
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2301:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2302:5: 
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

            match(input,19,FOLLOW_19_in_ruleOutputTable3581); 

                    createLeafNode(grammarAccess.getOutputTableAccess().getNAMEKeyword_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2316:1: ( (lv_name_2_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2317:1: (lv_name_2_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2317:1: (lv_name_2_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2318:3: lv_name_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getOutputTableAccess().getNameEStringParserRuleCall_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleOutputTable3602);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2340:2: ( 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) ) )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==20) ) {
                alt47=1;
            }
            switch (alt47) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2340:4: 'MINIMIZED:' ( (lv_minimized_4_0= ruleEBoolean ) )
                    {
                    match(input,20,FOLLOW_20_in_ruleOutputTable3613); 

                            createLeafNode(grammarAccess.getOutputTableAccess().getMINIMIZEDKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2344:1: ( (lv_minimized_4_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2345:1: (lv_minimized_4_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2345:1: (lv_minimized_4_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2346:3: lv_minimized_4_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getOutputTableAccess().getMinimizedEBooleanParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleOutputTable3634);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2368:4: ( 'SIZESTATE:' ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) ) )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==43) ) {
                alt49=1;
            }
            switch (alt49) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2368:6: 'SIZESTATE:' ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) )
                    {
                    match(input,43,FOLLOW_43_in_ruleOutputTable3647); 

                            createLeafNode(grammarAccess.getOutputTableAccess().getSIZESTATEKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2372:1: ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) )
                    int alt48=3;
                    switch ( input.LA(1) ) {
                    case 44:
                        {
                        alt48=1;
                        }
                        break;
                    case 45:
                        {
                        alt48=2;
                        }
                        break;
                    case 46:
                        {
                        alt48=3;
                        }
                        break;
                    default:
                        NoViableAltException nvae =
                            new NoViableAltException("2372:1: ( ( (lv_sizeState_6_0= 'MINIMIZED' ) ) | ( (lv_sizeState_7_0= 'INTERMEDIATE' ) ) | ( (lv_sizeState_8_0= 'MAXIMIZED' ) ) )", 48, 0, input);

                        throw nvae;
                    }

                    switch (alt48) {
                        case 1 :
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2372:2: ( (lv_sizeState_6_0= 'MINIMIZED' ) )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2372:2: ( (lv_sizeState_6_0= 'MINIMIZED' ) )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2373:1: (lv_sizeState_6_0= 'MINIMIZED' )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2373:1: (lv_sizeState_6_0= 'MINIMIZED' )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2374:3: lv_sizeState_6_0= 'MINIMIZED'
                            {
                            lv_sizeState_6_0=(Token)input.LT(1);
                            match(input,44,FOLLOW_44_in_ruleOutputTable3666); 

                                    createLeafNode(grammarAccess.getOutputTableAccess().getSizeStateMINIMIZEDKeyword_4_1_0_0(), "sizeState"); 
                                

                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode, current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "sizeState", lv_sizeState_6_0, "MINIMIZED", lastConsumedNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	    

                            }


                            }


                            }
                            break;
                        case 2 :
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2394:6: ( (lv_sizeState_7_0= 'INTERMEDIATE' ) )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2394:6: ( (lv_sizeState_7_0= 'INTERMEDIATE' ) )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2395:1: (lv_sizeState_7_0= 'INTERMEDIATE' )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2395:1: (lv_sizeState_7_0= 'INTERMEDIATE' )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2396:3: lv_sizeState_7_0= 'INTERMEDIATE'
                            {
                            lv_sizeState_7_0=(Token)input.LT(1);
                            match(input,45,FOLLOW_45_in_ruleOutputTable3703); 

                                    createLeafNode(grammarAccess.getOutputTableAccess().getSizeStateINTERMEDIATEKeyword_4_1_1_0(), "sizeState"); 
                                

                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode, current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "sizeState", lv_sizeState_7_0, "INTERMEDIATE", lastConsumedNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	    

                            }


                            }


                            }
                            break;
                        case 3 :
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2416:6: ( (lv_sizeState_8_0= 'MAXIMIZED' ) )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2416:6: ( (lv_sizeState_8_0= 'MAXIMIZED' ) )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2417:1: (lv_sizeState_8_0= 'MAXIMIZED' )
                            {
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2417:1: (lv_sizeState_8_0= 'MAXIMIZED' )
                            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2418:3: lv_sizeState_8_0= 'MAXIMIZED'
                            {
                            lv_sizeState_8_0=(Token)input.LT(1);
                            match(input,46,FOLLOW_46_in_ruleOutputTable3740); 

                                    createLeafNode(grammarAccess.getOutputTableAccess().getSizeStateMAXIMIZEDKeyword_4_1_2_0(), "sizeState"); 
                                

                            	        if (current==null) {
                            	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
                            	            associateNodeWithAstElement(currentNode, current);
                            	        }
                            	        
                            	        try {
                            	       		set(current, "sizeState", lv_sizeState_8_0, "MAXIMIZED", lastConsumedNode);
                            	        } catch (ValueConverterException vce) {
                            				handleValueConverterException(vce);
                            	        }
                            	    

                            }


                            }


                            }
                            break;

                    }


                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2437:5: ( 'EXPRESSIONFILTER:' ( (lv_expressionFilter_10_0= ruleEString ) ) )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==47) ) {
                alt50=1;
            }
            switch (alt50) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2437:7: 'EXPRESSIONFILTER:' ( (lv_expressionFilter_10_0= ruleEString ) )
                    {
                    match(input,47,FOLLOW_47_in_ruleOutputTable3767); 

                            createLeafNode(grammarAccess.getOutputTableAccess().getEXPRESSIONFILTERKeyword_5_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2441:1: ( (lv_expressionFilter_10_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2442:1: (lv_expressionFilter_10_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2442:1: (lv_expressionFilter_10_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2443:3: lv_expressionFilter_10_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getOutputTableAccess().getExpressionFilterEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleOutputTable3788);
                    lv_expressionFilter_10_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"expressionFilter",
                    	        		lv_expressionFilter_10_0, 
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2465:4: ( 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_12_0= ruleEBoolean ) ) )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==48) ) {
                alt51=1;
            }
            switch (alt51) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2465:6: 'ACTIVATEEXPRESSIONFILTER:' ( (lv_activateExpressionFilter_12_0= ruleEBoolean ) )
                    {
                    match(input,48,FOLLOW_48_in_ruleOutputTable3801); 

                            createLeafNode(grammarAccess.getOutputTableAccess().getACTIVATEEXPRESSIONFILTERKeyword_6_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2469:1: ( (lv_activateExpressionFilter_12_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2470:1: (lv_activateExpressionFilter_12_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2470:1: (lv_activateExpressionFilter_12_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2471:3: lv_activateExpressionFilter_12_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getOutputTableAccess().getActivateExpressionFilterEBooleanParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleOutputTable3822);
                    lv_activateExpressionFilter_12_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"activateExpressionFilter",
                    	        		lv_activateExpressionFilter_12_0, 
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2493:4: ( 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_14_0= ruleEBoolean ) ) )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==49) ) {
                alt52=1;
            }
            switch (alt52) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2493:6: 'ACTIVATECONDENSEDTOOL:' ( (lv_activateCondensedTool_14_0= ruleEBoolean ) )
                    {
                    match(input,49,FOLLOW_49_in_ruleOutputTable3835); 

                            createLeafNode(grammarAccess.getOutputTableAccess().getACTIVATECONDENSEDTOOLKeyword_7_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2497:1: ( (lv_activateCondensedTool_14_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2498:1: (lv_activateCondensedTool_14_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2498:1: (lv_activateCondensedTool_14_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2499:3: lv_activateCondensedTool_14_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getOutputTableAccess().getActivateCondensedToolEBooleanParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleOutputTable3856);
                    lv_activateCondensedTool_14_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"activateCondensedTool",
                    	        		lv_activateCondensedTool_14_0, 
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2521:4: ( 'REJECT:' ( (lv_reject_16_0= ruleEBoolean ) ) )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==52) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2521:6: 'REJECT:' ( (lv_reject_16_0= ruleEBoolean ) )
                    {
                    match(input,52,FOLLOW_52_in_ruleOutputTable3869); 

                            createLeafNode(grammarAccess.getOutputTableAccess().getREJECTKeyword_8_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2525:1: ( (lv_reject_16_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2526:1: (lv_reject_16_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2526:1: (lv_reject_16_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2527:3: lv_reject_16_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getOutputTableAccess().getRejectEBooleanParserRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleOutputTable3890);
                    lv_reject_16_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"reject",
                    	        		lv_reject_16_0, 
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2549:4: ( 'REJECTINNERJOIN:' ( (lv_rejectInnerJoin_18_0= ruleEBoolean ) ) )?
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==53) ) {
                alt54=1;
            }
            switch (alt54) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2549:6: 'REJECTINNERJOIN:' ( (lv_rejectInnerJoin_18_0= ruleEBoolean ) )
                    {
                    match(input,53,FOLLOW_53_in_ruleOutputTable3903); 

                            createLeafNode(grammarAccess.getOutputTableAccess().getREJECTINNERJOINKeyword_9_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2553:1: ( (lv_rejectInnerJoin_18_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2554:1: (lv_rejectInnerJoin_18_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2554:1: (lv_rejectInnerJoin_18_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2555:3: lv_rejectInnerJoin_18_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getOutputTableAccess().getRejectInnerJoinEBooleanParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleOutputTable3924);
                    lv_rejectInnerJoin_18_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"rejectInnerJoin",
                    	        		lv_rejectInnerJoin_18_0, 
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2577:4: ( 'ISERRORREJECTTABLE:' ( (lv_isErrorRejectTable_20_0= ruleEBoolean ) ) )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==54) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2577:6: 'ISERRORREJECTTABLE:' ( (lv_isErrorRejectTable_20_0= ruleEBoolean ) )
                    {
                    match(input,54,FOLLOW_54_in_ruleOutputTable3937); 

                            createLeafNode(grammarAccess.getOutputTableAccess().getISERRORREJECTTABLEKeyword_10_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2581:1: ( (lv_isErrorRejectTable_20_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2582:1: (lv_isErrorRejectTable_20_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2582:1: (lv_isErrorRejectTable_20_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2583:3: lv_isErrorRejectTable_20_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getOutputTableAccess().getIsErrorRejectTableEBooleanParserRuleCall_10_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleOutputTable3958);
                    lv_isErrorRejectTable_20_0=ruleEBoolean();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"isErrorRejectTable",
                    	        		lv_isErrorRejectTable_20_0, 
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2605:4: ( 'ISJOINTABLEOF:' ( (lv_isJoinTableOf_22_0= ruleEString ) ) )?
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==55) ) {
                alt56=1;
            }
            switch (alt56) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2605:6: 'ISJOINTABLEOF:' ( (lv_isJoinTableOf_22_0= ruleEString ) )
                    {
                    match(input,55,FOLLOW_55_in_ruleOutputTable3971); 

                            createLeafNode(grammarAccess.getOutputTableAccess().getISJOINTABLEOFKeyword_11_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2609:1: ( (lv_isJoinTableOf_22_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2610:1: (lv_isJoinTableOf_22_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2610:1: (lv_isJoinTableOf_22_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2611:3: lv_isJoinTableOf_22_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getOutputTableAccess().getIsJoinTableOfEStringParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleOutputTable3992);
                    lv_isJoinTableOf_22_0=ruleEString();
                    _fsp--;


                    	        if (current==null) {
                    	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
                    	            associateNodeWithAstElement(currentNode.getParent(), current);
                    	        }
                    	        try {
                    	       		set(
                    	       			current, 
                    	       			"isJoinTableOf",
                    	        		lv_isJoinTableOf_22_0, 
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2633:4: ( (lv_mapperTableEntries_23_0= ruleMapperTableEntry ) )*
            loop57:
            do {
                int alt57=2;
                int LA57_0 = input.LA(1);

                if ( (LA57_0==33) ) {
                    alt57=1;
                }


                switch (alt57) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2634:1: (lv_mapperTableEntries_23_0= ruleMapperTableEntry )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2634:1: (lv_mapperTableEntries_23_0= ruleMapperTableEntry )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2635:3: lv_mapperTableEntries_23_0= ruleMapperTableEntry
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getOutputTableAccess().getMapperTableEntriesMapperTableEntryParserRuleCall_12_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleMapperTableEntry_in_ruleOutputTable4015);
            	    lv_mapperTableEntries_23_0=ruleMapperTableEntry();
            	    _fsp--;


            	    	        if (current==null) {
            	    	            current = factory.create(grammarAccess.getOutputTableRule().getType().getClassifier());
            	    	            associateNodeWithAstElement(currentNode.getParent(), current);
            	    	        }
            	    	        try {
            	    	       		add(
            	    	       			current, 
            	    	       			"mapperTableEntries",
            	    	        		lv_mapperTableEntries_23_0, 
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
            	    break loop57;
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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2665:1: entryRuleMapperTableEntry returns [EObject current=null] : iv_ruleMapperTableEntry= ruleMapperTableEntry EOF ;
    public final EObject entryRuleMapperTableEntry() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMapperTableEntry = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2666:2: (iv_ruleMapperTableEntry= ruleMapperTableEntry EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2667:2: iv_ruleMapperTableEntry= ruleMapperTableEntry EOF
            {
             currentNode = createCompositeNode(grammarAccess.getMapperTableEntryRule(), currentNode); 
            pushFollow(FOLLOW_ruleMapperTableEntry_in_entryRuleMapperTableEntry4052);
            iv_ruleMapperTableEntry=ruleMapperTableEntry();
            _fsp--;

             current =iv_ruleMapperTableEntry; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMapperTableEntry4062); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2674:1: ruleMapperTableEntry returns [EObject current=null] : ( () 'addColumn' '{' 'NAME:' ( (lv_name_4_0= ruleEString ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )? ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )? '}' ) ;
    public final EObject ruleMapperTableEntry() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        AntlrDatatypeRuleToken lv_type_7_0 = null;

        AntlrDatatypeRuleToken lv_nullable_10_0 = null;

        AntlrDatatypeRuleToken lv_expression_13_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2679:6: ( ( () 'addColumn' '{' 'NAME:' ( (lv_name_4_0= ruleEString ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )? ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )? '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2680:1: ( () 'addColumn' '{' 'NAME:' ( (lv_name_4_0= ruleEString ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )? ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )? '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2680:1: ( () 'addColumn' '{' 'NAME:' ( (lv_name_4_0= ruleEString ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )? ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )? '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2680:2: () 'addColumn' '{' 'NAME:' ( (lv_name_4_0= ruleEString ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )? ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )? '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2680:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2681:5: 
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

            match(input,33,FOLLOW_33_in_ruleMapperTableEntry4106); 

                    createLeafNode(grammarAccess.getMapperTableEntryAccess().getAddColumnKeyword_1(), null); 
                
            match(input,12,FOLLOW_12_in_ruleMapperTableEntry4116); 

                    createLeafNode(grammarAccess.getMapperTableEntryAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            match(input,19,FOLLOW_19_in_ruleMapperTableEntry4126); 

                    createLeafNode(grammarAccess.getMapperTableEntryAccess().getNAMEKeyword_3(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2703:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2704:1: (lv_name_4_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2704:1: (lv_name_4_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2705:3: lv_name_4_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getMapperTableEntryAccess().getNameEStringParserRuleCall_4_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleMapperTableEntry4147);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2727:2: ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )?
            int alt58=2;
            int LA58_0 = input.LA(1);

            if ( (LA58_0==14) ) {
                int LA58_1 = input.LA(2);

                if ( (LA58_1==34) ) {
                    alt58=1;
                }
            }
            switch (alt58) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2727:4: ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleMapperTableEntry4158); 

                            createLeafNode(grammarAccess.getMapperTableEntryAccess().getCommaKeyword_5_0(), null); 
                        
                    match(input,34,FOLLOW_34_in_ruleMapperTableEntry4168); 

                            createLeafNode(grammarAccess.getMapperTableEntryAccess().getTYPEKeyword_5_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2735:1: ( (lv_type_7_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2736:1: (lv_type_7_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2736:1: (lv_type_7_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2737:3: lv_type_7_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMapperTableEntryAccess().getTypeEStringParserRuleCall_5_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleMapperTableEntry4189);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2759:4: ( ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) ) )?
            int alt59=2;
            int LA59_0 = input.LA(1);

            if ( (LA59_0==14) ) {
                int LA59_1 = input.LA(2);

                if ( (LA59_1==35) ) {
                    alt59=1;
                }
            }
            switch (alt59) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2759:6: ',' 'NULLABLE:' ( (lv_nullable_10_0= ruleEBoolean ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleMapperTableEntry4202); 

                            createLeafNode(grammarAccess.getMapperTableEntryAccess().getCommaKeyword_6_0(), null); 
                        
                    match(input,35,FOLLOW_35_in_ruleMapperTableEntry4212); 

                            createLeafNode(grammarAccess.getMapperTableEntryAccess().getNULLABLEKeyword_6_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2767:1: ( (lv_nullable_10_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2768:1: (lv_nullable_10_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2768:1: (lv_nullable_10_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2769:3: lv_nullable_10_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMapperTableEntryAccess().getNullableEBooleanParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleMapperTableEntry4233);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2791:4: ( ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) ) )?
            int alt60=2;
            int LA60_0 = input.LA(1);

            if ( (LA60_0==14) ) {
                alt60=1;
            }
            switch (alt60) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2791:6: ',' 'EXPRESSION:' ( (lv_expression_13_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleMapperTableEntry4246); 

                            createLeafNode(grammarAccess.getMapperTableEntryAccess().getCommaKeyword_7_0(), null); 
                        
                    match(input,36,FOLLOW_36_in_ruleMapperTableEntry4256); 

                            createLeafNode(grammarAccess.getMapperTableEntryAccess().getEXPRESSIONKeyword_7_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2799:1: ( (lv_expression_13_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2800:1: (lv_expression_13_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2800:1: (lv_expression_13_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2801:3: lv_expression_13_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMapperTableEntryAccess().getExpressionEStringParserRuleCall_7_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleMapperTableEntry4277);
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

            match(input,13,FOLLOW_13_in_ruleMapperTableEntry4289); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2835:1: entryRuleNodeType returns [EObject current=null] : iv_ruleNodeType= ruleNodeType EOF ;
    public final EObject entryRuleNodeType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNodeType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2836:2: (iv_ruleNodeType= ruleNodeType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2837:2: iv_ruleNodeType= ruleNodeType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNodeTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleNodeType_in_entryRuleNodeType4325);
            iv_ruleNodeType=ruleNodeType();
            _fsp--;

             current =iv_ruleNodeType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNodeType4335); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2844:1: ruleNodeType returns [EObject current=null] : ( 'addComponent' '{' 'setComponentDefinition' '{' ( 'TYPE:' ( (lv_componentName_5_0= ruleEString ) ) ',' ) ( 'NAME:' ( (lv_elementParameter_8_0= ruleUniqueNameType ) ) ',' ) ( 'POSITION:' ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) ) ) '}' ( (lv_metadata_15_0= ruleMetadataType ) )* 'setSettings' '{' ( ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) ) ) ( ',' ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) ) )* '}' ( (lv_metadata_22_0= ruleMetadataType ) )* ( (lv_nodeData_23_0= ruleExternalData ) )? '}' ) ;
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
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2849:6: ( ( 'addComponent' '{' 'setComponentDefinition' '{' ( 'TYPE:' ( (lv_componentName_5_0= ruleEString ) ) ',' ) ( 'NAME:' ( (lv_elementParameter_8_0= ruleUniqueNameType ) ) ',' ) ( 'POSITION:' ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) ) ) '}' ( (lv_metadata_15_0= ruleMetadataType ) )* 'setSettings' '{' ( ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) ) ) ( ',' ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) ) )* '}' ( (lv_metadata_22_0= ruleMetadataType ) )* ( (lv_nodeData_23_0= ruleExternalData ) )? '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2850:1: ( 'addComponent' '{' 'setComponentDefinition' '{' ( 'TYPE:' ( (lv_componentName_5_0= ruleEString ) ) ',' ) ( 'NAME:' ( (lv_elementParameter_8_0= ruleUniqueNameType ) ) ',' ) ( 'POSITION:' ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) ) ) '}' ( (lv_metadata_15_0= ruleMetadataType ) )* 'setSettings' '{' ( ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) ) ) ( ',' ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) ) )* '}' ( (lv_metadata_22_0= ruleMetadataType ) )* ( (lv_nodeData_23_0= ruleExternalData ) )? '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2850:1: ( 'addComponent' '{' 'setComponentDefinition' '{' ( 'TYPE:' ( (lv_componentName_5_0= ruleEString ) ) ',' ) ( 'NAME:' ( (lv_elementParameter_8_0= ruleUniqueNameType ) ) ',' ) ( 'POSITION:' ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) ) ) '}' ( (lv_metadata_15_0= ruleMetadataType ) )* 'setSettings' '{' ( ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) ) ) ( ',' ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) ) )* '}' ( (lv_metadata_22_0= ruleMetadataType ) )* ( (lv_nodeData_23_0= ruleExternalData ) )? '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2850:3: 'addComponent' '{' 'setComponentDefinition' '{' ( 'TYPE:' ( (lv_componentName_5_0= ruleEString ) ) ',' ) ( 'NAME:' ( (lv_elementParameter_8_0= ruleUniqueNameType ) ) ',' ) ( 'POSITION:' ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) ) ) '}' ( (lv_metadata_15_0= ruleMetadataType ) )* 'setSettings' '{' ( ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) ) ) ( ',' ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) ) )* '}' ( (lv_metadata_22_0= ruleMetadataType ) )* ( (lv_nodeData_23_0= ruleExternalData ) )? '}'
            {
            match(input,56,FOLLOW_56_in_ruleNodeType4370); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getAddComponentKeyword_0(), null); 
                
            match(input,12,FOLLOW_12_in_ruleNodeType4380); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getLeftCurlyBracketKeyword_1(), null); 
                
            match(input,57,FOLLOW_57_in_ruleNodeType4390); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getSetComponentDefinitionKeyword_2(), null); 
                
            match(input,12,FOLLOW_12_in_ruleNodeType4400); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2866:1: ( 'TYPE:' ( (lv_componentName_5_0= ruleEString ) ) ',' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2866:3: 'TYPE:' ( (lv_componentName_5_0= ruleEString ) ) ','
            {
            match(input,34,FOLLOW_34_in_ruleNodeType4411); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getTYPEKeyword_4_0(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2870:1: ( (lv_componentName_5_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2871:1: (lv_componentName_5_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2871:1: (lv_componentName_5_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2872:3: lv_componentName_5_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getComponentNameEStringParserRuleCall_4_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleNodeType4432);
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

            match(input,14,FOLLOW_14_in_ruleNodeType4442); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getCommaKeyword_4_2(), null); 
                

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2898:2: ( 'NAME:' ( (lv_elementParameter_8_0= ruleUniqueNameType ) ) ',' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2898:4: 'NAME:' ( (lv_elementParameter_8_0= ruleUniqueNameType ) ) ','
            {
            match(input,19,FOLLOW_19_in_ruleNodeType4454); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getNAMEKeyword_5_0(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2902:1: ( (lv_elementParameter_8_0= ruleUniqueNameType ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2903:1: (lv_elementParameter_8_0= ruleUniqueNameType )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2903:1: (lv_elementParameter_8_0= ruleUniqueNameType )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2904:3: lv_elementParameter_8_0= ruleUniqueNameType
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getElementParameterUniqueNameTypeParserRuleCall_5_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleUniqueNameType_in_ruleNodeType4475);
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

            match(input,14,FOLLOW_14_in_ruleNodeType4485); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getCommaKeyword_5_2(), null); 
                

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2930:2: ( 'POSITION:' ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2930:4: 'POSITION:' ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) )
            {
            match(input,58,FOLLOW_58_in_ruleNodeType4497); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getPOSITIONKeyword_6_0(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2934:1: ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2934:2: ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2934:2: ( (lv_posX_11_0= ruleEInt ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2935:1: (lv_posX_11_0= ruleEInt )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2935:1: (lv_posX_11_0= ruleEInt )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2936:3: lv_posX_11_0= ruleEInt
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getPosXEIntParserRuleCall_6_1_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEInt_in_ruleNodeType4519);
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

            match(input,14,FOLLOW_14_in_ruleNodeType4529); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getCommaKeyword_6_1_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2962:1: ( (lv_posY_13_0= ruleEInt ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2963:1: (lv_posY_13_0= ruleEInt )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2963:1: (lv_posY_13_0= ruleEInt )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2964:3: lv_posY_13_0= ruleEInt
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getPosYEIntParserRuleCall_6_1_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEInt_in_ruleNodeType4550);
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

            match(input,13,FOLLOW_13_in_ruleNodeType4562); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getRightCurlyBracketKeyword_7(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2990:1: ( (lv_metadata_15_0= ruleMetadataType ) )*
            loop61:
            do {
                int alt61=2;
                int LA61_0 = input.LA(1);

                if ( (LA61_0==81) ) {
                    alt61=1;
                }


                switch (alt61) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2991:1: (lv_metadata_15_0= ruleMetadataType )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2991:1: (lv_metadata_15_0= ruleMetadataType )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2992:3: lv_metadata_15_0= ruleMetadataType
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getMetadataMetadataTypeParserRuleCall_8_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleMetadataType_in_ruleNodeType4583);
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
            	    break loop61;
                }
            } while (true);

            match(input,59,FOLLOW_59_in_ruleNodeType4594); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getSetSettingsKeyword_9(), null); 
                
            match(input,12,FOLLOW_12_in_ruleNodeType4604); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getLeftCurlyBracketKeyword_10(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3022:1: ( ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3023:1: ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3023:1: ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3024:1: (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3024:1: (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType )
            int alt62=2;
            switch ( input.LA(1) ) {
            case RULE_STRING:
                {
                int LA62_1 = input.LA(2);

                if ( (LA62_1==12) ) {
                    alt62=1;
                }
                else if ( (LA62_1==80) ) {
                    alt62=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("3024:1: (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType )", 62, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_ID:
                {
                int LA62_2 = input.LA(2);

                if ( (LA62_2==12) ) {
                    alt62=1;
                }
                else if ( (LA62_2==80) ) {
                    alt62=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("3024:1: (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType )", 62, 2, input);

                    throw nvae;
                }
                }
                break;
            case RULE_INT:
                {
                int LA62_3 = input.LA(2);

                if ( (LA62_3==12) ) {
                    alt62=1;
                }
                else if ( (LA62_3==80) ) {
                    alt62=2;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("3024:1: (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType )", 62, 3, input);

                    throw nvae;
                }
                }
                break;
            case 13:
            case 14:
                {
                alt62=1;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("3024:1: (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType )", 62, 0, input);

                throw nvae;
            }

            switch (alt62) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3025:3: lv_elementParameter_18_1= ruleSchemaElementParameterType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getElementParameterSchemaElementParameterTypeParserRuleCall_11_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleSchemaElementParameterType_in_ruleNodeType4627);
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
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3046:8: lv_elementParameter_18_2= ruleElementParameterType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getElementParameterElementParameterTypeParserRuleCall_11_0_1(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleElementParameterType_in_ruleNodeType4646);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3070:2: ( ',' ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) ) )*
            loop64:
            do {
                int alt64=2;
                int LA64_0 = input.LA(1);

                if ( (LA64_0==14) ) {
                    alt64=1;
                }


                switch (alt64) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3070:4: ',' ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) )
            	    {
            	    match(input,14,FOLLOW_14_in_ruleNodeType4660); 

            	            createLeafNode(grammarAccess.getNodeTypeAccess().getCommaKeyword_12_0(), null); 
            	        
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3074:1: ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3075:1: ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3075:1: ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3076:1: (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3076:1: (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType )
            	    int alt63=2;
            	    switch ( input.LA(1) ) {
            	    case RULE_STRING:
            	        {
            	        int LA63_1 = input.LA(2);

            	        if ( (LA63_1==80) ) {
            	            alt63=2;
            	        }
            	        else if ( (LA63_1==12) ) {
            	            alt63=1;
            	        }
            	        else {
            	            NoViableAltException nvae =
            	                new NoViableAltException("3076:1: (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType )", 63, 1, input);

            	            throw nvae;
            	        }
            	        }
            	        break;
            	    case RULE_ID:
            	        {
            	        int LA63_2 = input.LA(2);

            	        if ( (LA63_2==80) ) {
            	            alt63=2;
            	        }
            	        else if ( (LA63_2==12) ) {
            	            alt63=1;
            	        }
            	        else {
            	            NoViableAltException nvae =
            	                new NoViableAltException("3076:1: (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType )", 63, 2, input);

            	            throw nvae;
            	        }
            	        }
            	        break;
            	    case RULE_INT:
            	        {
            	        int LA63_3 = input.LA(2);

            	        if ( (LA63_3==12) ) {
            	            alt63=1;
            	        }
            	        else if ( (LA63_3==80) ) {
            	            alt63=2;
            	        }
            	        else {
            	            NoViableAltException nvae =
            	                new NoViableAltException("3076:1: (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType )", 63, 3, input);

            	            throw nvae;
            	        }
            	        }
            	        break;
            	    case 13:
            	    case 14:
            	        {
            	        alt63=1;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("3076:1: (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType )", 63, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt63) {
            	        case 1 :
            	            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3077:3: lv_elementParameter_20_1= ruleSchemaElementParameterType
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getElementParameterSchemaElementParameterTypeParserRuleCall_12_1_0_0(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_ruleSchemaElementParameterType_in_ruleNodeType4683);
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
            	            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3098:8: lv_elementParameter_20_2= ruleElementParameterType
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getElementParameterElementParameterTypeParserRuleCall_12_1_0_1(), currentNode); 
            	            	    
            	            pushFollow(FOLLOW_ruleElementParameterType_in_ruleNodeType4702);
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
            	    break loop64;
                }
            } while (true);

            match(input,13,FOLLOW_13_in_ruleNodeType4717); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getRightCurlyBracketKeyword_13(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3126:1: ( (lv_metadata_22_0= ruleMetadataType ) )*
            loop65:
            do {
                int alt65=2;
                int LA65_0 = input.LA(1);

                if ( (LA65_0==81) ) {
                    alt65=1;
                }


                switch (alt65) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3127:1: (lv_metadata_22_0= ruleMetadataType )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3127:1: (lv_metadata_22_0= ruleMetadataType )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3128:3: lv_metadata_22_0= ruleMetadataType
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getMetadataMetadataTypeParserRuleCall_14_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleMetadataType_in_ruleNodeType4738);
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
            	    break loop65;
                }
            } while (true);

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3150:3: ( (lv_nodeData_23_0= ruleExternalData ) )?
            int alt66=2;
            int LA66_0 = input.LA(1);

            if ( (LA66_0==15||LA66_0==40) ) {
                alt66=1;
            }
            switch (alt66) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3151:1: (lv_nodeData_23_0= ruleExternalData )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3151:1: (lv_nodeData_23_0= ruleExternalData )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3152:3: lv_nodeData_23_0= ruleExternalData
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getNodeDataExternalDataParserRuleCall_15_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleExternalData_in_ruleNodeType4760);
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

            match(input,13,FOLLOW_13_in_ruleNodeType4771); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3186:1: entryRuleContextType returns [EObject current=null] : iv_ruleContextType= ruleContextType EOF ;
    public final EObject entryRuleContextType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContextType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3187:2: (iv_ruleContextType= ruleContextType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3188:2: iv_ruleContextType= ruleContextType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getContextTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleContextType_in_entryRuleContextType4807);
            iv_ruleContextType=ruleContextType();
            _fsp--;

             current =iv_ruleContextType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleContextType4817); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3195:1: ruleContextType returns [EObject current=null] : ( () 'ContextType' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'CONFIRMATIONNEED:' ( (lv_confirmationNeeded_6_0= ruleEBoolean ) ) )? ( ( (lv_contextParameter_7_0= ruleContextParameterType ) ) ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )* )? '}' ) ;
    public final EObject ruleContextType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        AntlrDatatypeRuleToken lv_confirmationNeeded_6_0 = null;

        EObject lv_contextParameter_7_0 = null;

        EObject lv_contextParameter_9_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3200:6: ( ( () 'ContextType' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'CONFIRMATIONNEED:' ( (lv_confirmationNeeded_6_0= ruleEBoolean ) ) )? ( ( (lv_contextParameter_7_0= ruleContextParameterType ) ) ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )* )? '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3201:1: ( () 'ContextType' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'CONFIRMATIONNEED:' ( (lv_confirmationNeeded_6_0= ruleEBoolean ) ) )? ( ( (lv_contextParameter_7_0= ruleContextParameterType ) ) ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )* )? '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3201:1: ( () 'ContextType' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'CONFIRMATIONNEED:' ( (lv_confirmationNeeded_6_0= ruleEBoolean ) ) )? ( ( (lv_contextParameter_7_0= ruleContextParameterType ) ) ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )* )? '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3201:2: () 'ContextType' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'CONFIRMATIONNEED:' ( (lv_confirmationNeeded_6_0= ruleEBoolean ) ) )? ( ( (lv_contextParameter_7_0= ruleContextParameterType ) ) ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )* )? '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3201:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3202:5: 
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

            match(input,60,FOLLOW_60_in_ruleContextType4861); 

                    createLeafNode(grammarAccess.getContextTypeAccess().getContextTypeKeyword_1(), null); 
                
            match(input,12,FOLLOW_12_in_ruleContextType4871); 

                    createLeafNode(grammarAccess.getContextTypeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3220:1: ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )?
            int alt67=2;
            int LA67_0 = input.LA(1);

            if ( (LA67_0==19) ) {
                alt67=1;
            }
            switch (alt67) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3220:3: 'NAME:' ( (lv_name_4_0= ruleEString ) )
                    {
                    match(input,19,FOLLOW_19_in_ruleContextType4882); 

                            createLeafNode(grammarAccess.getContextTypeAccess().getNAMEKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3224:1: ( (lv_name_4_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3225:1: (lv_name_4_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3225:1: (lv_name_4_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3226:3: lv_name_4_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextTypeAccess().getNameEStringParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleContextType4903);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3248:4: ( 'CONFIRMATIONNEED:' ( (lv_confirmationNeeded_6_0= ruleEBoolean ) ) )?
            int alt68=2;
            int LA68_0 = input.LA(1);

            if ( (LA68_0==61) ) {
                alt68=1;
            }
            switch (alt68) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3248:6: 'CONFIRMATIONNEED:' ( (lv_confirmationNeeded_6_0= ruleEBoolean ) )
                    {
                    match(input,61,FOLLOW_61_in_ruleContextType4916); 

                            createLeafNode(grammarAccess.getContextTypeAccess().getCONFIRMATIONNEEDKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3252:1: ( (lv_confirmationNeeded_6_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3253:1: (lv_confirmationNeeded_6_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3253:1: (lv_confirmationNeeded_6_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3254:3: lv_confirmationNeeded_6_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextTypeAccess().getConfirmationNeededEBooleanParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleContextType4937);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3276:4: ( ( (lv_contextParameter_7_0= ruleContextParameterType ) ) ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )* )?
            int alt70=2;
            int LA70_0 = input.LA(1);

            if ( (LA70_0==62) ) {
                alt70=1;
            }
            switch (alt70) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3276:5: ( (lv_contextParameter_7_0= ruleContextParameterType ) ) ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )*
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3276:5: ( (lv_contextParameter_7_0= ruleContextParameterType ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3277:1: (lv_contextParameter_7_0= ruleContextParameterType )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3277:1: (lv_contextParameter_7_0= ruleContextParameterType )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3278:3: lv_contextParameter_7_0= ruleContextParameterType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextTypeAccess().getContextParameterContextParameterTypeParserRuleCall_5_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleContextParameterType_in_ruleContextType4961);
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

                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3300:2: ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )*
                    loop69:
                    do {
                        int alt69=2;
                        int LA69_0 = input.LA(1);

                        if ( (LA69_0==14) ) {
                            alt69=1;
                        }


                        switch (alt69) {
                    	case 1 :
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3300:4: ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) )
                    	    {
                    	    match(input,14,FOLLOW_14_in_ruleContextType4972); 

                    	            createLeafNode(grammarAccess.getContextTypeAccess().getCommaKeyword_5_1_0(), null); 
                    	        
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3304:1: ( (lv_contextParameter_9_0= ruleContextParameterType ) )
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3305:1: (lv_contextParameter_9_0= ruleContextParameterType )
                    	    {
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3305:1: (lv_contextParameter_9_0= ruleContextParameterType )
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3306:3: lv_contextParameter_9_0= ruleContextParameterType
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getContextTypeAccess().getContextParameterContextParameterTypeParserRuleCall_5_1_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleContextParameterType_in_ruleContextType4993);
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
                    	    break loop69;
                        }
                    } while (true);


                    }
                    break;

            }

            match(input,13,FOLLOW_13_in_ruleContextType5007); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3340:1: entryRuleContextParameterType returns [EObject current=null] : iv_ruleContextParameterType= ruleContextParameterType EOF ;
    public final EObject entryRuleContextParameterType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContextParameterType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3341:2: (iv_ruleContextParameterType= ruleContextParameterType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3342:2: iv_ruleContextParameterType= ruleContextParameterType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getContextParameterTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleContextParameterType_in_entryRuleContextParameterType5043);
            iv_ruleContextParameterType=ruleContextParameterType();
            _fsp--;

             current =iv_ruleContextParameterType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleContextParameterType5053); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3349:1: ruleContextParameterType returns [EObject current=null] : ( () 'addContextParameter' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? ( 'VALUE:' ( (lv_value_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'PROMPT:' ( (lv_prompt_12_0= ruleEString ) ) )? ( 'PROMPTNEEDED:' ( (lv_promptNeeded_14_0= ruleEBoolean ) ) )? ( 'REPOSITORYCONTEXTID:' ( (lv_repositoryContextId_16_0= ruleEString ) ) )? '}' ) ;
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
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3354:6: ( ( () 'addContextParameter' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? ( 'VALUE:' ( (lv_value_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'PROMPT:' ( (lv_prompt_12_0= ruleEString ) ) )? ( 'PROMPTNEEDED:' ( (lv_promptNeeded_14_0= ruleEBoolean ) ) )? ( 'REPOSITORYCONTEXTID:' ( (lv_repositoryContextId_16_0= ruleEString ) ) )? '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3355:1: ( () 'addContextParameter' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? ( 'VALUE:' ( (lv_value_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'PROMPT:' ( (lv_prompt_12_0= ruleEString ) ) )? ( 'PROMPTNEEDED:' ( (lv_promptNeeded_14_0= ruleEBoolean ) ) )? ( 'REPOSITORYCONTEXTID:' ( (lv_repositoryContextId_16_0= ruleEString ) ) )? '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3355:1: ( () 'addContextParameter' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? ( 'VALUE:' ( (lv_value_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'PROMPT:' ( (lv_prompt_12_0= ruleEString ) ) )? ( 'PROMPTNEEDED:' ( (lv_promptNeeded_14_0= ruleEBoolean ) ) )? ( 'REPOSITORYCONTEXTID:' ( (lv_repositoryContextId_16_0= ruleEString ) ) )? '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3355:2: () 'addContextParameter' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? ( 'VALUE:' ( (lv_value_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'PROMPT:' ( (lv_prompt_12_0= ruleEString ) ) )? ( 'PROMPTNEEDED:' ( (lv_promptNeeded_14_0= ruleEBoolean ) ) )? ( 'REPOSITORYCONTEXTID:' ( (lv_repositoryContextId_16_0= ruleEString ) ) )? '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3355:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3356:5: 
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

            match(input,62,FOLLOW_62_in_ruleContextParameterType5097); 

                    createLeafNode(grammarAccess.getContextParameterTypeAccess().getAddContextParameterKeyword_1(), null); 
                
            match(input,12,FOLLOW_12_in_ruleContextParameterType5107); 

                    createLeafNode(grammarAccess.getContextParameterTypeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3374:1: ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )?
            int alt71=2;
            int LA71_0 = input.LA(1);

            if ( (LA71_0==19) ) {
                alt71=1;
            }
            switch (alt71) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3374:3: 'NAME:' ( (lv_name_4_0= ruleEString ) )
                    {
                    match(input,19,FOLLOW_19_in_ruleContextParameterType5118); 

                            createLeafNode(grammarAccess.getContextParameterTypeAccess().getNAMEKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3378:1: ( (lv_name_4_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3379:1: (lv_name_4_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3379:1: (lv_name_4_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3380:3: lv_name_4_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextParameterTypeAccess().getNameEStringParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleContextParameterType5139);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3402:4: ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )?
            int alt72=2;
            int LA72_0 = input.LA(1);

            if ( (LA72_0==34) ) {
                alt72=1;
            }
            switch (alt72) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3402:6: 'TYPE:' ( (lv_type_6_0= ruleEString ) )
                    {
                    match(input,34,FOLLOW_34_in_ruleContextParameterType5152); 

                            createLeafNode(grammarAccess.getContextParameterTypeAccess().getTYPEKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3406:1: ( (lv_type_6_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3407:1: (lv_type_6_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3407:1: (lv_type_6_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3408:3: lv_type_6_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextParameterTypeAccess().getTypeEStringParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleContextParameterType5173);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3430:4: ( 'VALUE:' ( (lv_value_8_0= ruleEString ) ) )?
            int alt73=2;
            int LA73_0 = input.LA(1);

            if ( (LA73_0==63) ) {
                alt73=1;
            }
            switch (alt73) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3430:6: 'VALUE:' ( (lv_value_8_0= ruleEString ) )
                    {
                    match(input,63,FOLLOW_63_in_ruleContextParameterType5186); 

                            createLeafNode(grammarAccess.getContextParameterTypeAccess().getVALUEKeyword_5_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3434:1: ( (lv_value_8_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3435:1: (lv_value_8_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3435:1: (lv_value_8_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3436:3: lv_value_8_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextParameterTypeAccess().getValueEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleContextParameterType5207);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3458:4: ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )?
            int alt74=2;
            int LA74_0 = input.LA(1);

            if ( (LA74_0==64) ) {
                alt74=1;
            }
            switch (alt74) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3458:6: 'COMMENT:' ( (lv_comment_10_0= ruleEString ) )
                    {
                    match(input,64,FOLLOW_64_in_ruleContextParameterType5220); 

                            createLeafNode(grammarAccess.getContextParameterTypeAccess().getCOMMENTKeyword_6_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3462:1: ( (lv_comment_10_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3463:1: (lv_comment_10_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3463:1: (lv_comment_10_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3464:3: lv_comment_10_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextParameterTypeAccess().getCommentEStringParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleContextParameterType5241);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3486:4: ( 'PROMPT:' ( (lv_prompt_12_0= ruleEString ) ) )?
            int alt75=2;
            int LA75_0 = input.LA(1);

            if ( (LA75_0==65) ) {
                alt75=1;
            }
            switch (alt75) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3486:6: 'PROMPT:' ( (lv_prompt_12_0= ruleEString ) )
                    {
                    match(input,65,FOLLOW_65_in_ruleContextParameterType5254); 

                            createLeafNode(grammarAccess.getContextParameterTypeAccess().getPROMPTKeyword_7_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3490:1: ( (lv_prompt_12_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3491:1: (lv_prompt_12_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3491:1: (lv_prompt_12_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3492:3: lv_prompt_12_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextParameterTypeAccess().getPromptEStringParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleContextParameterType5275);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3514:4: ( 'PROMPTNEEDED:' ( (lv_promptNeeded_14_0= ruleEBoolean ) ) )?
            int alt76=2;
            int LA76_0 = input.LA(1);

            if ( (LA76_0==66) ) {
                alt76=1;
            }
            switch (alt76) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3514:6: 'PROMPTNEEDED:' ( (lv_promptNeeded_14_0= ruleEBoolean ) )
                    {
                    match(input,66,FOLLOW_66_in_ruleContextParameterType5288); 

                            createLeafNode(grammarAccess.getContextParameterTypeAccess().getPROMPTNEEDEDKeyword_8_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3518:1: ( (lv_promptNeeded_14_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3519:1: (lv_promptNeeded_14_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3519:1: (lv_promptNeeded_14_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3520:3: lv_promptNeeded_14_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextParameterTypeAccess().getPromptNeededEBooleanParserRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleContextParameterType5309);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3542:4: ( 'REPOSITORYCONTEXTID:' ( (lv_repositoryContextId_16_0= ruleEString ) ) )?
            int alt77=2;
            int LA77_0 = input.LA(1);

            if ( (LA77_0==67) ) {
                alt77=1;
            }
            switch (alt77) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3542:6: 'REPOSITORYCONTEXTID:' ( (lv_repositoryContextId_16_0= ruleEString ) )
                    {
                    match(input,67,FOLLOW_67_in_ruleContextParameterType5322); 

                            createLeafNode(grammarAccess.getContextParameterTypeAccess().getREPOSITORYCONTEXTIDKeyword_9_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3546:1: ( (lv_repositoryContextId_16_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3547:1: (lv_repositoryContextId_16_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3547:1: (lv_repositoryContextId_16_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3548:3: lv_repositoryContextId_16_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextParameterTypeAccess().getRepositoryContextIdEStringParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleContextParameterType5343);
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

            match(input,13,FOLLOW_13_in_ruleContextParameterType5355); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3582:1: entryRuleUniqueNameType returns [EObject current=null] : iv_ruleUniqueNameType= ruleUniqueNameType EOF ;
    public final EObject entryRuleUniqueNameType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUniqueNameType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3583:2: (iv_ruleUniqueNameType= ruleUniqueNameType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3584:2: iv_ruleUniqueNameType= ruleUniqueNameType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getUniqueNameTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleUniqueNameType_in_entryRuleUniqueNameType5391);
            iv_ruleUniqueNameType=ruleUniqueNameType();
            _fsp--;

             current =iv_ruleUniqueNameType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleUniqueNameType5401); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3591:1: ruleUniqueNameType returns [EObject current=null] : ( (lv_value_0_0= ruleEString ) ) ;
    public final EObject ruleUniqueNameType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_value_0_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3596:6: ( ( (lv_value_0_0= ruleEString ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3597:1: ( (lv_value_0_0= ruleEString ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3597:1: ( (lv_value_0_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3598:1: (lv_value_0_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3598:1: (lv_value_0_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3599:3: lv_value_0_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getUniqueNameTypeAccess().getValueEStringParserRuleCall_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleUniqueNameType5446);
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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3629:1: entryRuleConnectionType returns [EObject current=null] : iv_ruleConnectionType= ruleConnectionType EOF ;
    public final EObject entryRuleConnectionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConnectionType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3630:2: (iv_ruleConnectionType= ruleConnectionType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3631:2: iv_ruleConnectionType= ruleConnectionType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getConnectionTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleConnectionType_in_entryRuleConnectionType5481);
            iv_ruleConnectionType=ruleConnectionType();
            _fsp--;

             current =iv_ruleConnectionType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleConnectionType5491); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3638:1: ruleConnectionType returns [EObject current=null] : ( () 'addConnection' '{' ( 'TYPE:' ( (lv_connectorName_4_0= ruleEString ) ) ) ( ',' 'NAME:' ( (lv_label_7_0= ruleEString ) ) ) ( ',' 'LINESTYLE:' ( (lv_lineStyle_10_0= ruleEInt ) ) )? ( ',' 'MERGEORDER:' ( (lv_mergeOrder_13_0= ruleEInt ) ) )? ( ',' 'METANAME:' ( (lv_metaname_16_0= ruleEString ) ) )? ( ',' 'OUTPUTID:' ( (lv_outputId_19_0= ruleEInt ) ) )? ( ',' 'SOURCE:' ( (lv_source_22_0= ruleEString ) ) )? ( ',' 'TARGET:' ( (lv_target_25_0= ruleEString ) ) )? ( ',' ( (lv_elementParameter_27_0= ruleElementParameterType ) ) )? '}' ) ;
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
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3643:6: ( ( () 'addConnection' '{' ( 'TYPE:' ( (lv_connectorName_4_0= ruleEString ) ) ) ( ',' 'NAME:' ( (lv_label_7_0= ruleEString ) ) ) ( ',' 'LINESTYLE:' ( (lv_lineStyle_10_0= ruleEInt ) ) )? ( ',' 'MERGEORDER:' ( (lv_mergeOrder_13_0= ruleEInt ) ) )? ( ',' 'METANAME:' ( (lv_metaname_16_0= ruleEString ) ) )? ( ',' 'OUTPUTID:' ( (lv_outputId_19_0= ruleEInt ) ) )? ( ',' 'SOURCE:' ( (lv_source_22_0= ruleEString ) ) )? ( ',' 'TARGET:' ( (lv_target_25_0= ruleEString ) ) )? ( ',' ( (lv_elementParameter_27_0= ruleElementParameterType ) ) )? '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3644:1: ( () 'addConnection' '{' ( 'TYPE:' ( (lv_connectorName_4_0= ruleEString ) ) ) ( ',' 'NAME:' ( (lv_label_7_0= ruleEString ) ) ) ( ',' 'LINESTYLE:' ( (lv_lineStyle_10_0= ruleEInt ) ) )? ( ',' 'MERGEORDER:' ( (lv_mergeOrder_13_0= ruleEInt ) ) )? ( ',' 'METANAME:' ( (lv_metaname_16_0= ruleEString ) ) )? ( ',' 'OUTPUTID:' ( (lv_outputId_19_0= ruleEInt ) ) )? ( ',' 'SOURCE:' ( (lv_source_22_0= ruleEString ) ) )? ( ',' 'TARGET:' ( (lv_target_25_0= ruleEString ) ) )? ( ',' ( (lv_elementParameter_27_0= ruleElementParameterType ) ) )? '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3644:1: ( () 'addConnection' '{' ( 'TYPE:' ( (lv_connectorName_4_0= ruleEString ) ) ) ( ',' 'NAME:' ( (lv_label_7_0= ruleEString ) ) ) ( ',' 'LINESTYLE:' ( (lv_lineStyle_10_0= ruleEInt ) ) )? ( ',' 'MERGEORDER:' ( (lv_mergeOrder_13_0= ruleEInt ) ) )? ( ',' 'METANAME:' ( (lv_metaname_16_0= ruleEString ) ) )? ( ',' 'OUTPUTID:' ( (lv_outputId_19_0= ruleEInt ) ) )? ( ',' 'SOURCE:' ( (lv_source_22_0= ruleEString ) ) )? ( ',' 'TARGET:' ( (lv_target_25_0= ruleEString ) ) )? ( ',' ( (lv_elementParameter_27_0= ruleElementParameterType ) ) )? '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3644:2: () 'addConnection' '{' ( 'TYPE:' ( (lv_connectorName_4_0= ruleEString ) ) ) ( ',' 'NAME:' ( (lv_label_7_0= ruleEString ) ) ) ( ',' 'LINESTYLE:' ( (lv_lineStyle_10_0= ruleEInt ) ) )? ( ',' 'MERGEORDER:' ( (lv_mergeOrder_13_0= ruleEInt ) ) )? ( ',' 'METANAME:' ( (lv_metaname_16_0= ruleEString ) ) )? ( ',' 'OUTPUTID:' ( (lv_outputId_19_0= ruleEInt ) ) )? ( ',' 'SOURCE:' ( (lv_source_22_0= ruleEString ) ) )? ( ',' 'TARGET:' ( (lv_target_25_0= ruleEString ) ) )? ( ',' ( (lv_elementParameter_27_0= ruleElementParameterType ) ) )? '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3644:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3645:5: 
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

            match(input,68,FOLLOW_68_in_ruleConnectionType5535); 

                    createLeafNode(grammarAccess.getConnectionTypeAccess().getAddConnectionKeyword_1(), null); 
                
            match(input,12,FOLLOW_12_in_ruleConnectionType5545); 

                    createLeafNode(grammarAccess.getConnectionTypeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3663:1: ( 'TYPE:' ( (lv_connectorName_4_0= ruleEString ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3663:3: 'TYPE:' ( (lv_connectorName_4_0= ruleEString ) )
            {
            match(input,34,FOLLOW_34_in_ruleConnectionType5556); 

                    createLeafNode(grammarAccess.getConnectionTypeAccess().getTYPEKeyword_3_0(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3667:1: ( (lv_connectorName_4_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3668:1: (lv_connectorName_4_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3668:1: (lv_connectorName_4_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3669:3: lv_connectorName_4_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getConnectorNameEStringParserRuleCall_3_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleConnectionType5577);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3691:3: ( ',' 'NAME:' ( (lv_label_7_0= ruleEString ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3691:5: ',' 'NAME:' ( (lv_label_7_0= ruleEString ) )
            {
            match(input,14,FOLLOW_14_in_ruleConnectionType5589); 

                    createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_4_0(), null); 
                
            match(input,19,FOLLOW_19_in_ruleConnectionType5599); 

                    createLeafNode(grammarAccess.getConnectionTypeAccess().getNAMEKeyword_4_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3699:1: ( (lv_label_7_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3700:1: (lv_label_7_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3700:1: (lv_label_7_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3701:3: lv_label_7_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getLabelEStringParserRuleCall_4_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleConnectionType5620);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3723:3: ( ',' 'LINESTYLE:' ( (lv_lineStyle_10_0= ruleEInt ) ) )?
            int alt78=2;
            int LA78_0 = input.LA(1);

            if ( (LA78_0==14) ) {
                int LA78_1 = input.LA(2);

                if ( (LA78_1==69) ) {
                    alt78=1;
                }
            }
            switch (alt78) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3723:5: ',' 'LINESTYLE:' ( (lv_lineStyle_10_0= ruleEInt ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleConnectionType5632); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_5_0(), null); 
                        
                    match(input,69,FOLLOW_69_in_ruleConnectionType5642); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getLINESTYLEKeyword_5_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3731:1: ( (lv_lineStyle_10_0= ruleEInt ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3732:1: (lv_lineStyle_10_0= ruleEInt )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3732:1: (lv_lineStyle_10_0= ruleEInt )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3733:3: lv_lineStyle_10_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getLineStyleEIntParserRuleCall_5_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEInt_in_ruleConnectionType5663);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3755:4: ( ',' 'MERGEORDER:' ( (lv_mergeOrder_13_0= ruleEInt ) ) )?
            int alt79=2;
            int LA79_0 = input.LA(1);

            if ( (LA79_0==14) ) {
                int LA79_1 = input.LA(2);

                if ( (LA79_1==70) ) {
                    alt79=1;
                }
            }
            switch (alt79) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3755:6: ',' 'MERGEORDER:' ( (lv_mergeOrder_13_0= ruleEInt ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleConnectionType5676); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_6_0(), null); 
                        
                    match(input,70,FOLLOW_70_in_ruleConnectionType5686); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getMERGEORDERKeyword_6_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3763:1: ( (lv_mergeOrder_13_0= ruleEInt ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3764:1: (lv_mergeOrder_13_0= ruleEInt )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3764:1: (lv_mergeOrder_13_0= ruleEInt )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3765:3: lv_mergeOrder_13_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getMergeOrderEIntParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEInt_in_ruleConnectionType5707);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3787:4: ( ',' 'METANAME:' ( (lv_metaname_16_0= ruleEString ) ) )?
            int alt80=2;
            int LA80_0 = input.LA(1);

            if ( (LA80_0==14) ) {
                int LA80_1 = input.LA(2);

                if ( (LA80_1==71) ) {
                    alt80=1;
                }
            }
            switch (alt80) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3787:6: ',' 'METANAME:' ( (lv_metaname_16_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleConnectionType5720); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_7_0(), null); 
                        
                    match(input,71,FOLLOW_71_in_ruleConnectionType5730); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getMETANAMEKeyword_7_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3795:1: ( (lv_metaname_16_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3796:1: (lv_metaname_16_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3796:1: (lv_metaname_16_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3797:3: lv_metaname_16_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getMetanameEStringParserRuleCall_7_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleConnectionType5751);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3819:4: ( ',' 'OUTPUTID:' ( (lv_outputId_19_0= ruleEInt ) ) )?
            int alt81=2;
            int LA81_0 = input.LA(1);

            if ( (LA81_0==14) ) {
                int LA81_1 = input.LA(2);

                if ( (LA81_1==72) ) {
                    alt81=1;
                }
            }
            switch (alt81) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3819:6: ',' 'OUTPUTID:' ( (lv_outputId_19_0= ruleEInt ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleConnectionType5764); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_8_0(), null); 
                        
                    match(input,72,FOLLOW_72_in_ruleConnectionType5774); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getOUTPUTIDKeyword_8_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3827:1: ( (lv_outputId_19_0= ruleEInt ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3828:1: (lv_outputId_19_0= ruleEInt )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3828:1: (lv_outputId_19_0= ruleEInt )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3829:3: lv_outputId_19_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getOutputIdEIntParserRuleCall_8_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEInt_in_ruleConnectionType5795);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3851:4: ( ',' 'SOURCE:' ( (lv_source_22_0= ruleEString ) ) )?
            int alt82=2;
            int LA82_0 = input.LA(1);

            if ( (LA82_0==14) ) {
                int LA82_1 = input.LA(2);

                if ( (LA82_1==73) ) {
                    alt82=1;
                }
            }
            switch (alt82) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3851:6: ',' 'SOURCE:' ( (lv_source_22_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleConnectionType5808); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_9_0(), null); 
                        
                    match(input,73,FOLLOW_73_in_ruleConnectionType5818); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getSOURCEKeyword_9_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3859:1: ( (lv_source_22_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3860:1: (lv_source_22_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3860:1: (lv_source_22_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3861:3: lv_source_22_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getSourceEStringParserRuleCall_9_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleConnectionType5839);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3883:4: ( ',' 'TARGET:' ( (lv_target_25_0= ruleEString ) ) )?
            int alt83=2;
            int LA83_0 = input.LA(1);

            if ( (LA83_0==14) ) {
                int LA83_1 = input.LA(2);

                if ( (LA83_1==74) ) {
                    alt83=1;
                }
            }
            switch (alt83) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3883:6: ',' 'TARGET:' ( (lv_target_25_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleConnectionType5852); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_10_0(), null); 
                        
                    match(input,74,FOLLOW_74_in_ruleConnectionType5862); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getTARGETKeyword_10_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3891:1: ( (lv_target_25_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3892:1: (lv_target_25_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3892:1: (lv_target_25_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3893:3: lv_target_25_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getTargetEStringParserRuleCall_10_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleConnectionType5883);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3915:4: ( ',' ( (lv_elementParameter_27_0= ruleElementParameterType ) ) )?
            int alt84=2;
            int LA84_0 = input.LA(1);

            if ( (LA84_0==14) ) {
                alt84=1;
            }
            switch (alt84) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3915:6: ',' ( (lv_elementParameter_27_0= ruleElementParameterType ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleConnectionType5896); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_11_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3919:1: ( (lv_elementParameter_27_0= ruleElementParameterType ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3920:1: (lv_elementParameter_27_0= ruleElementParameterType )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3920:1: (lv_elementParameter_27_0= ruleElementParameterType )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3921:3: lv_elementParameter_27_0= ruleElementParameterType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getElementParameterElementParameterTypeParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleElementParameterType_in_ruleConnectionType5917);
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

            match(input,13,FOLLOW_13_in_ruleConnectionType5929); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3955:1: entryRuleNoteType returns [EObject current=null] : iv_ruleNoteType= ruleNoteType EOF ;
    public final EObject entryRuleNoteType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNoteType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3956:2: (iv_ruleNoteType= ruleNoteType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3957:2: iv_ruleNoteType= ruleNoteType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNoteTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleNoteType_in_entryRuleNoteType5965);
            iv_ruleNoteType=ruleNoteType();
            _fsp--;

             current =iv_ruleNoteType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleNoteType5975); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3964:1: ruleNoteType returns [EObject current=null] : ( 'addNote' '{' ( 'opaque' ( (lv_opaque_3_0= ruleEBoolean ) ) )? ( 'POSITION' '\"' ( (lv_posX_6_0= ruleEInt ) ) ',' ( (lv_posY_8_0= ruleEInt ) ) '\"' )? ( 'text' ( (lv_text_11_0= ruleEString ) ) )? ( (lv_elementParameter_12_0= ruleElementParameterType ) ) ( ',' ( (lv_elementParameter_14_0= ruleElementParameterType ) ) )* '}' ) ;
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
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3969:6: ( ( 'addNote' '{' ( 'opaque' ( (lv_opaque_3_0= ruleEBoolean ) ) )? ( 'POSITION' '\"' ( (lv_posX_6_0= ruleEInt ) ) ',' ( (lv_posY_8_0= ruleEInt ) ) '\"' )? ( 'text' ( (lv_text_11_0= ruleEString ) ) )? ( (lv_elementParameter_12_0= ruleElementParameterType ) ) ( ',' ( (lv_elementParameter_14_0= ruleElementParameterType ) ) )* '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3970:1: ( 'addNote' '{' ( 'opaque' ( (lv_opaque_3_0= ruleEBoolean ) ) )? ( 'POSITION' '\"' ( (lv_posX_6_0= ruleEInt ) ) ',' ( (lv_posY_8_0= ruleEInt ) ) '\"' )? ( 'text' ( (lv_text_11_0= ruleEString ) ) )? ( (lv_elementParameter_12_0= ruleElementParameterType ) ) ( ',' ( (lv_elementParameter_14_0= ruleElementParameterType ) ) )* '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3970:1: ( 'addNote' '{' ( 'opaque' ( (lv_opaque_3_0= ruleEBoolean ) ) )? ( 'POSITION' '\"' ( (lv_posX_6_0= ruleEInt ) ) ',' ( (lv_posY_8_0= ruleEInt ) ) '\"' )? ( 'text' ( (lv_text_11_0= ruleEString ) ) )? ( (lv_elementParameter_12_0= ruleElementParameterType ) ) ( ',' ( (lv_elementParameter_14_0= ruleElementParameterType ) ) )* '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3970:3: 'addNote' '{' ( 'opaque' ( (lv_opaque_3_0= ruleEBoolean ) ) )? ( 'POSITION' '\"' ( (lv_posX_6_0= ruleEInt ) ) ',' ( (lv_posY_8_0= ruleEInt ) ) '\"' )? ( 'text' ( (lv_text_11_0= ruleEString ) ) )? ( (lv_elementParameter_12_0= ruleElementParameterType ) ) ( ',' ( (lv_elementParameter_14_0= ruleElementParameterType ) ) )* '}'
            {
            match(input,75,FOLLOW_75_in_ruleNoteType6010); 

                    createLeafNode(grammarAccess.getNoteTypeAccess().getAddNoteKeyword_0(), null); 
                
            match(input,12,FOLLOW_12_in_ruleNoteType6020); 

                    createLeafNode(grammarAccess.getNoteTypeAccess().getLeftCurlyBracketKeyword_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3978:1: ( 'opaque' ( (lv_opaque_3_0= ruleEBoolean ) ) )?
            int alt85=2;
            int LA85_0 = input.LA(1);

            if ( (LA85_0==76) ) {
                alt85=1;
            }
            switch (alt85) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3978:3: 'opaque' ( (lv_opaque_3_0= ruleEBoolean ) )
                    {
                    match(input,76,FOLLOW_76_in_ruleNoteType6031); 

                            createLeafNode(grammarAccess.getNoteTypeAccess().getOpaqueKeyword_2_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3982:1: ( (lv_opaque_3_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3983:1: (lv_opaque_3_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3983:1: (lv_opaque_3_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:3984:3: lv_opaque_3_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNoteTypeAccess().getOpaqueEBooleanParserRuleCall_2_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleNoteType6052);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4006:4: ( 'POSITION' '\"' ( (lv_posX_6_0= ruleEInt ) ) ',' ( (lv_posY_8_0= ruleEInt ) ) '\"' )?
            int alt86=2;
            int LA86_0 = input.LA(1);

            if ( (LA86_0==77) ) {
                alt86=1;
            }
            switch (alt86) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4006:6: 'POSITION' '\"' ( (lv_posX_6_0= ruleEInt ) ) ',' ( (lv_posY_8_0= ruleEInt ) ) '\"'
                    {
                    match(input,77,FOLLOW_77_in_ruleNoteType6065); 

                            createLeafNode(grammarAccess.getNoteTypeAccess().getPOSITIONKeyword_3_0(), null); 
                        
                    match(input,78,FOLLOW_78_in_ruleNoteType6075); 

                            createLeafNode(grammarAccess.getNoteTypeAccess().getQuotationMarkKeyword_3_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4014:1: ( (lv_posX_6_0= ruleEInt ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4015:1: (lv_posX_6_0= ruleEInt )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4015:1: (lv_posX_6_0= ruleEInt )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4016:3: lv_posX_6_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNoteTypeAccess().getPosXEIntParserRuleCall_3_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEInt_in_ruleNoteType6096);
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

                    match(input,14,FOLLOW_14_in_ruleNoteType6106); 

                            createLeafNode(grammarAccess.getNoteTypeAccess().getCommaKeyword_3_3(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4042:1: ( (lv_posY_8_0= ruleEInt ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4043:1: (lv_posY_8_0= ruleEInt )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4043:1: (lv_posY_8_0= ruleEInt )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4044:3: lv_posY_8_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNoteTypeAccess().getPosYEIntParserRuleCall_3_4_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEInt_in_ruleNoteType6127);
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

                    match(input,78,FOLLOW_78_in_ruleNoteType6137); 

                            createLeafNode(grammarAccess.getNoteTypeAccess().getQuotationMarkKeyword_3_5(), null); 
                        

                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4070:3: ( 'text' ( (lv_text_11_0= ruleEString ) ) )?
            int alt87=2;
            int LA87_0 = input.LA(1);

            if ( (LA87_0==79) ) {
                alt87=1;
            }
            switch (alt87) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4070:5: 'text' ( (lv_text_11_0= ruleEString ) )
                    {
                    match(input,79,FOLLOW_79_in_ruleNoteType6150); 

                            createLeafNode(grammarAccess.getNoteTypeAccess().getTextKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4074:1: ( (lv_text_11_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4075:1: (lv_text_11_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4075:1: (lv_text_11_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4076:3: lv_text_11_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNoteTypeAccess().getTextEStringParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleNoteType6171);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4098:4: ( (lv_elementParameter_12_0= ruleElementParameterType ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4099:1: (lv_elementParameter_12_0= ruleElementParameterType )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4099:1: (lv_elementParameter_12_0= ruleElementParameterType )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4100:3: lv_elementParameter_12_0= ruleElementParameterType
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNoteTypeAccess().getElementParameterElementParameterTypeParserRuleCall_5_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleElementParameterType_in_ruleNoteType6194);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4122:2: ( ',' ( (lv_elementParameter_14_0= ruleElementParameterType ) ) )*
            loop88:
            do {
                int alt88=2;
                int LA88_0 = input.LA(1);

                if ( (LA88_0==14) ) {
                    alt88=1;
                }


                switch (alt88) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4122:4: ',' ( (lv_elementParameter_14_0= ruleElementParameterType ) )
            	    {
            	    match(input,14,FOLLOW_14_in_ruleNoteType6205); 

            	            createLeafNode(grammarAccess.getNoteTypeAccess().getCommaKeyword_6_0(), null); 
            	        
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4126:1: ( (lv_elementParameter_14_0= ruleElementParameterType ) )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4127:1: (lv_elementParameter_14_0= ruleElementParameterType )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4127:1: (lv_elementParameter_14_0= ruleElementParameterType )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4128:3: lv_elementParameter_14_0= ruleElementParameterType
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getNoteTypeAccess().getElementParameterElementParameterTypeParserRuleCall_6_1_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleElementParameterType_in_ruleNoteType6226);
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
            	    break loop88;
                }
            } while (true);

            match(input,13,FOLLOW_13_in_ruleNoteType6238); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4162:1: entryRuleElementParameterType returns [EObject current=null] : iv_ruleElementParameterType= ruleElementParameterType EOF ;
    public final EObject entryRuleElementParameterType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElementParameterType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4163:2: (iv_ruleElementParameterType= ruleElementParameterType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4164:2: iv_ruleElementParameterType= ruleElementParameterType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getElementParameterTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleElementParameterType_in_entryRuleElementParameterType6274);
            iv_ruleElementParameterType=ruleElementParameterType();
            _fsp--;

             current =iv_ruleElementParameterType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleElementParameterType6284); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4171:1: ruleElementParameterType returns [EObject current=null] : ( () ( ( (lv_name_1_0= ruleEString ) ) ':' ( (lv_value_3_0= ruleEString ) ) ) ) ;
    public final EObject ruleElementParameterType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        AntlrDatatypeRuleToken lv_value_3_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4176:6: ( ( () ( ( (lv_name_1_0= ruleEString ) ) ':' ( (lv_value_3_0= ruleEString ) ) ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4177:1: ( () ( ( (lv_name_1_0= ruleEString ) ) ':' ( (lv_value_3_0= ruleEString ) ) ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4177:1: ( () ( ( (lv_name_1_0= ruleEString ) ) ':' ( (lv_value_3_0= ruleEString ) ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4177:2: () ( ( (lv_name_1_0= ruleEString ) ) ':' ( (lv_value_3_0= ruleEString ) ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4177:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4178:5: 
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4188:2: ( ( (lv_name_1_0= ruleEString ) ) ':' ( (lv_value_3_0= ruleEString ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4188:3: ( (lv_name_1_0= ruleEString ) ) ':' ( (lv_value_3_0= ruleEString ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4188:3: ( (lv_name_1_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4189:1: (lv_name_1_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4189:1: (lv_name_1_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4190:3: lv_name_1_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getElementParameterTypeAccess().getNameEStringParserRuleCall_1_0_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleElementParameterType6340);
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

            match(input,80,FOLLOW_80_in_ruleElementParameterType6350); 

                    createLeafNode(grammarAccess.getElementParameterTypeAccess().getColonKeyword_1_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4216:1: ( (lv_value_3_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4217:1: (lv_value_3_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4217:1: (lv_value_3_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4218:3: lv_value_3_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getElementParameterTypeAccess().getValueEStringParserRuleCall_1_2_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleElementParameterType6371);
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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4248:1: entryRuleSchemaElementParameterType returns [EObject current=null] : iv_ruleSchemaElementParameterType= ruleSchemaElementParameterType EOF ;
    public final EObject entryRuleSchemaElementParameterType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSchemaElementParameterType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4249:2: (iv_ruleSchemaElementParameterType= ruleSchemaElementParameterType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4250:2: iv_ruleSchemaElementParameterType= ruleSchemaElementParameterType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSchemaElementParameterTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleSchemaElementParameterType_in_entryRuleSchemaElementParameterType6408);
            iv_ruleSchemaElementParameterType=ruleSchemaElementParameterType();
            _fsp--;

             current =iv_ruleSchemaElementParameterType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleSchemaElementParameterType6418); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4257:1: ruleSchemaElementParameterType returns [EObject current=null] : ( () ( ( (lv_name_1_0= ruleEString ) ) '{' ( (lv_elementValue_3_0= ruleElementValueType ) ) ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )* '}' )? ) ;
    public final EObject ruleSchemaElementParameterType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_elementValue_3_0 = null;

        EObject lv_elementValue_5_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4262:6: ( ( () ( ( (lv_name_1_0= ruleEString ) ) '{' ( (lv_elementValue_3_0= ruleElementValueType ) ) ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )* '}' )? ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4263:1: ( () ( ( (lv_name_1_0= ruleEString ) ) '{' ( (lv_elementValue_3_0= ruleElementValueType ) ) ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )* '}' )? )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4263:1: ( () ( ( (lv_name_1_0= ruleEString ) ) '{' ( (lv_elementValue_3_0= ruleElementValueType ) ) ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )* '}' )? )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4263:2: () ( ( (lv_name_1_0= ruleEString ) ) '{' ( (lv_elementValue_3_0= ruleElementValueType ) ) ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )* '}' )?
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4263:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4264:5: 
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4274:2: ( ( (lv_name_1_0= ruleEString ) ) '{' ( (lv_elementValue_3_0= ruleElementValueType ) ) ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )* '}' )?
            int alt90=2;
            int LA90_0 = input.LA(1);

            if ( ((LA90_0>=RULE_STRING && LA90_0<=RULE_INT)) ) {
                alt90=1;
            }
            switch (alt90) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4274:3: ( (lv_name_1_0= ruleEString ) ) '{' ( (lv_elementValue_3_0= ruleElementValueType ) ) ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )* '}'
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4274:3: ( (lv_name_1_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4275:1: (lv_name_1_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4275:1: (lv_name_1_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4276:3: lv_name_1_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSchemaElementParameterTypeAccess().getNameEStringParserRuleCall_1_0_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleSchemaElementParameterType6474);
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

                    match(input,12,FOLLOW_12_in_ruleSchemaElementParameterType6484); 

                            createLeafNode(grammarAccess.getSchemaElementParameterTypeAccess().getLeftCurlyBracketKeyword_1_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4302:1: ( (lv_elementValue_3_0= ruleElementValueType ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4303:1: (lv_elementValue_3_0= ruleElementValueType )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4303:1: (lv_elementValue_3_0= ruleElementValueType )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4304:3: lv_elementValue_3_0= ruleElementValueType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSchemaElementParameterTypeAccess().getElementValueElementValueTypeParserRuleCall_1_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleElementValueType_in_ruleSchemaElementParameterType6505);
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

                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4326:2: ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )*
                    loop89:
                    do {
                        int alt89=2;
                        int LA89_0 = input.LA(1);

                        if ( (LA89_0==14) ) {
                            alt89=1;
                        }


                        switch (alt89) {
                    	case 1 :
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4326:4: ',' ( (lv_elementValue_5_0= ruleElementValueType ) )
                    	    {
                    	    match(input,14,FOLLOW_14_in_ruleSchemaElementParameterType6516); 

                    	            createLeafNode(grammarAccess.getSchemaElementParameterTypeAccess().getCommaKeyword_1_3_0(), null); 
                    	        
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4330:1: ( (lv_elementValue_5_0= ruleElementValueType ) )
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4331:1: (lv_elementValue_5_0= ruleElementValueType )
                    	    {
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4331:1: (lv_elementValue_5_0= ruleElementValueType )
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4332:3: lv_elementValue_5_0= ruleElementValueType
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getSchemaElementParameterTypeAccess().getElementValueElementValueTypeParserRuleCall_1_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FOLLOW_ruleElementValueType_in_ruleSchemaElementParameterType6537);
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
                    	    break loop89;
                        }
                    } while (true);

                    match(input,13,FOLLOW_13_in_ruleSchemaElementParameterType6549); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4366:1: entryRuleElementValueType returns [EObject current=null] : iv_ruleElementValueType= ruleElementValueType EOF ;
    public final EObject entryRuleElementValueType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElementValueType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4367:2: (iv_ruleElementValueType= ruleElementValueType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4368:2: iv_ruleElementValueType= ruleElementValueType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getElementValueTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleElementValueType_in_entryRuleElementValueType6587);
            iv_ruleElementValueType=ruleElementValueType();
            _fsp--;

             current =iv_ruleElementValueType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleElementValueType6597); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4375:1: ruleElementValueType returns [EObject current=null] : ( () ( 'NAME:' ( (lv_elementRef_2_0= ruleEString ) ) ) ( 'VALUE:' ( (lv_value_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? ) ;
    public final EObject ruleElementValueType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_elementRef_2_0 = null;

        AntlrDatatypeRuleToken lv_value_4_0 = null;

        AntlrDatatypeRuleToken lv_type_6_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4380:6: ( ( () ( 'NAME:' ( (lv_elementRef_2_0= ruleEString ) ) ) ( 'VALUE:' ( (lv_value_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4381:1: ( () ( 'NAME:' ( (lv_elementRef_2_0= ruleEString ) ) ) ( 'VALUE:' ( (lv_value_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4381:1: ( () ( 'NAME:' ( (lv_elementRef_2_0= ruleEString ) ) ) ( 'VALUE:' ( (lv_value_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4381:2: () ( 'NAME:' ( (lv_elementRef_2_0= ruleEString ) ) ) ( 'VALUE:' ( (lv_value_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )?
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4381:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4382:5: 
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4392:2: ( 'NAME:' ( (lv_elementRef_2_0= ruleEString ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4392:4: 'NAME:' ( (lv_elementRef_2_0= ruleEString ) )
            {
            match(input,19,FOLLOW_19_in_ruleElementValueType6642); 

                    createLeafNode(grammarAccess.getElementValueTypeAccess().getNAMEKeyword_1_0(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4396:1: ( (lv_elementRef_2_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4397:1: (lv_elementRef_2_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4397:1: (lv_elementRef_2_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4398:3: lv_elementRef_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getElementValueTypeAccess().getElementRefEStringParserRuleCall_1_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleElementValueType6663);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4420:3: ( 'VALUE:' ( (lv_value_4_0= ruleEString ) ) )?
            int alt91=2;
            int LA91_0 = input.LA(1);

            if ( (LA91_0==63) ) {
                alt91=1;
            }
            switch (alt91) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4420:5: 'VALUE:' ( (lv_value_4_0= ruleEString ) )
                    {
                    match(input,63,FOLLOW_63_in_ruleElementValueType6675); 

                            createLeafNode(grammarAccess.getElementValueTypeAccess().getVALUEKeyword_2_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4424:1: ( (lv_value_4_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4425:1: (lv_value_4_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4425:1: (lv_value_4_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4426:3: lv_value_4_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getElementValueTypeAccess().getValueEStringParserRuleCall_2_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleElementValueType6696);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4448:4: ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )?
            int alt92=2;
            int LA92_0 = input.LA(1);

            if ( (LA92_0==34) ) {
                alt92=1;
            }
            switch (alt92) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4448:6: 'TYPE:' ( (lv_type_6_0= ruleEString ) )
                    {
                    match(input,34,FOLLOW_34_in_ruleElementValueType6709); 

                            createLeafNode(grammarAccess.getElementValueTypeAccess().getTYPEKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4452:1: ( (lv_type_6_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4453:1: (lv_type_6_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4453:1: (lv_type_6_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4454:3: lv_type_6_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getElementValueTypeAccess().getTypeEStringParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleElementValueType6730);
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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4484:1: entryRuleMetadataType returns [EObject current=null] : iv_ruleMetadataType= ruleMetadataType EOF ;
    public final EObject entryRuleMetadataType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMetadataType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4485:2: (iv_ruleMetadataType= ruleMetadataType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4486:2: iv_ruleMetadataType= ruleMetadataType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getMetadataTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleMetadataType_in_entryRuleMetadataType6768);
            iv_ruleMetadataType=ruleMetadataType();
            _fsp--;

             current =iv_ruleMetadataType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMetadataType6778); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4493:1: ruleMetadataType returns [EObject current=null] : ( () 'addSchema' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_connector_6_0= ruleEString ) ) )? ( 'LABEL:' ( (lv_label_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'SOURCE:' ( (lv_source_12_0= ruleEString ) ) )? ( (lv_column_13_0= ruleColumnType ) )* '}' ) ;
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
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4498:6: ( ( () 'addSchema' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_connector_6_0= ruleEString ) ) )? ( 'LABEL:' ( (lv_label_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'SOURCE:' ( (lv_source_12_0= ruleEString ) ) )? ( (lv_column_13_0= ruleColumnType ) )* '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4499:1: ( () 'addSchema' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_connector_6_0= ruleEString ) ) )? ( 'LABEL:' ( (lv_label_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'SOURCE:' ( (lv_source_12_0= ruleEString ) ) )? ( (lv_column_13_0= ruleColumnType ) )* '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4499:1: ( () 'addSchema' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_connector_6_0= ruleEString ) ) )? ( 'LABEL:' ( (lv_label_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'SOURCE:' ( (lv_source_12_0= ruleEString ) ) )? ( (lv_column_13_0= ruleColumnType ) )* '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4499:2: () 'addSchema' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_connector_6_0= ruleEString ) ) )? ( 'LABEL:' ( (lv_label_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'SOURCE:' ( (lv_source_12_0= ruleEString ) ) )? ( (lv_column_13_0= ruleColumnType ) )* '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4499:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4500:5: 
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

            match(input,81,FOLLOW_81_in_ruleMetadataType6822); 

                    createLeafNode(grammarAccess.getMetadataTypeAccess().getAddSchemaKeyword_1(), null); 
                
            match(input,12,FOLLOW_12_in_ruleMetadataType6832); 

                    createLeafNode(grammarAccess.getMetadataTypeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4518:1: ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )?
            int alt93=2;
            int LA93_0 = input.LA(1);

            if ( (LA93_0==19) ) {
                alt93=1;
            }
            switch (alt93) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4518:3: 'NAME:' ( (lv_name_4_0= ruleEString ) )
                    {
                    match(input,19,FOLLOW_19_in_ruleMetadataType6843); 

                            createLeafNode(grammarAccess.getMetadataTypeAccess().getNAMEKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4522:1: ( (lv_name_4_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4523:1: (lv_name_4_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4523:1: (lv_name_4_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4524:3: lv_name_4_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMetadataTypeAccess().getNameEStringParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleMetadataType6864);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4546:4: ( 'TYPE:' ( (lv_connector_6_0= ruleEString ) ) )?
            int alt94=2;
            int LA94_0 = input.LA(1);

            if ( (LA94_0==34) ) {
                alt94=1;
            }
            switch (alt94) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4546:6: 'TYPE:' ( (lv_connector_6_0= ruleEString ) )
                    {
                    match(input,34,FOLLOW_34_in_ruleMetadataType6877); 

                            createLeafNode(grammarAccess.getMetadataTypeAccess().getTYPEKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4550:1: ( (lv_connector_6_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4551:1: (lv_connector_6_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4551:1: (lv_connector_6_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4552:3: lv_connector_6_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMetadataTypeAccess().getConnectorEStringParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleMetadataType6898);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4574:4: ( 'LABEL:' ( (lv_label_8_0= ruleEString ) ) )?
            int alt95=2;
            int LA95_0 = input.LA(1);

            if ( (LA95_0==82) ) {
                alt95=1;
            }
            switch (alt95) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4574:6: 'LABEL:' ( (lv_label_8_0= ruleEString ) )
                    {
                    match(input,82,FOLLOW_82_in_ruleMetadataType6911); 

                            createLeafNode(grammarAccess.getMetadataTypeAccess().getLABELKeyword_5_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4578:1: ( (lv_label_8_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4579:1: (lv_label_8_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4579:1: (lv_label_8_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4580:3: lv_label_8_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMetadataTypeAccess().getLabelEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleMetadataType6932);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4602:4: ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )?
            int alt96=2;
            int LA96_0 = input.LA(1);

            if ( (LA96_0==64) ) {
                alt96=1;
            }
            switch (alt96) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4602:6: 'COMMENT:' ( (lv_comment_10_0= ruleEString ) )
                    {
                    match(input,64,FOLLOW_64_in_ruleMetadataType6945); 

                            createLeafNode(grammarAccess.getMetadataTypeAccess().getCOMMENTKeyword_6_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4606:1: ( (lv_comment_10_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4607:1: (lv_comment_10_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4607:1: (lv_comment_10_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4608:3: lv_comment_10_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMetadataTypeAccess().getCommentEStringParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleMetadataType6966);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4630:4: ( 'SOURCE:' ( (lv_source_12_0= ruleEString ) ) )?
            int alt97=2;
            int LA97_0 = input.LA(1);

            if ( (LA97_0==73) ) {
                alt97=1;
            }
            switch (alt97) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4630:6: 'SOURCE:' ( (lv_source_12_0= ruleEString ) )
                    {
                    match(input,73,FOLLOW_73_in_ruleMetadataType6979); 

                            createLeafNode(grammarAccess.getMetadataTypeAccess().getSOURCEKeyword_7_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4634:1: ( (lv_source_12_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4635:1: (lv_source_12_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4635:1: (lv_source_12_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4636:3: lv_source_12_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMetadataTypeAccess().getSourceEStringParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleMetadataType7000);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4658:4: ( (lv_column_13_0= ruleColumnType ) )*
            loop98:
            do {
                int alt98=2;
                int LA98_0 = input.LA(1);

                if ( (LA98_0==33) ) {
                    alt98=1;
                }


                switch (alt98) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4659:1: (lv_column_13_0= ruleColumnType )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4659:1: (lv_column_13_0= ruleColumnType )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4660:3: lv_column_13_0= ruleColumnType
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getMetadataTypeAccess().getColumnColumnTypeParserRuleCall_8_0(), currentNode); 
            	    	    
            	    pushFollow(FOLLOW_ruleColumnType_in_ruleMetadataType7023);
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
            	    break loop98;
                }
            } while (true);

            match(input,13,FOLLOW_13_in_ruleMetadataType7034); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4694:1: entryRuleColumnType returns [EObject current=null] : iv_ruleColumnType= ruleColumnType EOF ;
    public final EObject entryRuleColumnType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4695:2: (iv_ruleColumnType= ruleColumnType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4696:2: iv_ruleColumnType= ruleColumnType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getColumnTypeRule(), currentNode); 
            pushFollow(FOLLOW_ruleColumnType_in_entryRuleColumnType7070);
            iv_ruleColumnType=ruleColumnType();
            _fsp--;

             current =iv_ruleColumnType; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleColumnType7080); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4703:1: ruleColumnType returns [EObject current=null] : ( () 'addColumn' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'KEY:' ( (lv_key_10_0= ruleEBoolean ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_13_0= ruleEBoolean ) ) )? ( ',' 'DEFAULTVALUE:' ( (lv_defaultValue_16_0= ruleEString ) ) )? ( ',' 'LENGTH:' ( (lv_length_19_0= ruleEInt ) ) )? ( ',' 'COMMENT:' ( (lv_comment_22_0= ruleEString ) ) )? ( ',' 'ORIGINALDBCOLUMNNAME' ( (lv_originalDbColumnName_25_0= ruleEString ) ) )? ( ',' 'PATTREN:' ( (lv_pattern_28_0= ruleEString ) ) )? ( ',' 'PRECISION:' ( (lv_precision_31_0= ruleEInt ) ) )? ( ',' 'SOURCETYPE:' ( (lv_sourceType_34_0= ruleEString ) ) )? '}' ) ;
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
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4708:6: ( ( () 'addColumn' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'KEY:' ( (lv_key_10_0= ruleEBoolean ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_13_0= ruleEBoolean ) ) )? ( ',' 'DEFAULTVALUE:' ( (lv_defaultValue_16_0= ruleEString ) ) )? ( ',' 'LENGTH:' ( (lv_length_19_0= ruleEInt ) ) )? ( ',' 'COMMENT:' ( (lv_comment_22_0= ruleEString ) ) )? ( ',' 'ORIGINALDBCOLUMNNAME' ( (lv_originalDbColumnName_25_0= ruleEString ) ) )? ( ',' 'PATTREN:' ( (lv_pattern_28_0= ruleEString ) ) )? ( ',' 'PRECISION:' ( (lv_precision_31_0= ruleEInt ) ) )? ( ',' 'SOURCETYPE:' ( (lv_sourceType_34_0= ruleEString ) ) )? '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4709:1: ( () 'addColumn' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'KEY:' ( (lv_key_10_0= ruleEBoolean ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_13_0= ruleEBoolean ) ) )? ( ',' 'DEFAULTVALUE:' ( (lv_defaultValue_16_0= ruleEString ) ) )? ( ',' 'LENGTH:' ( (lv_length_19_0= ruleEInt ) ) )? ( ',' 'COMMENT:' ( (lv_comment_22_0= ruleEString ) ) )? ( ',' 'ORIGINALDBCOLUMNNAME' ( (lv_originalDbColumnName_25_0= ruleEString ) ) )? ( ',' 'PATTREN:' ( (lv_pattern_28_0= ruleEString ) ) )? ( ',' 'PRECISION:' ( (lv_precision_31_0= ruleEInt ) ) )? ( ',' 'SOURCETYPE:' ( (lv_sourceType_34_0= ruleEString ) ) )? '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4709:1: ( () 'addColumn' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'KEY:' ( (lv_key_10_0= ruleEBoolean ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_13_0= ruleEBoolean ) ) )? ( ',' 'DEFAULTVALUE:' ( (lv_defaultValue_16_0= ruleEString ) ) )? ( ',' 'LENGTH:' ( (lv_length_19_0= ruleEInt ) ) )? ( ',' 'COMMENT:' ( (lv_comment_22_0= ruleEString ) ) )? ( ',' 'ORIGINALDBCOLUMNNAME' ( (lv_originalDbColumnName_25_0= ruleEString ) ) )? ( ',' 'PATTREN:' ( (lv_pattern_28_0= ruleEString ) ) )? ( ',' 'PRECISION:' ( (lv_precision_31_0= ruleEInt ) ) )? ( ',' 'SOURCETYPE:' ( (lv_sourceType_34_0= ruleEString ) ) )? '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4709:2: () 'addColumn' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'KEY:' ( (lv_key_10_0= ruleEBoolean ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_13_0= ruleEBoolean ) ) )? ( ',' 'DEFAULTVALUE:' ( (lv_defaultValue_16_0= ruleEString ) ) )? ( ',' 'LENGTH:' ( (lv_length_19_0= ruleEInt ) ) )? ( ',' 'COMMENT:' ( (lv_comment_22_0= ruleEString ) ) )? ( ',' 'ORIGINALDBCOLUMNNAME' ( (lv_originalDbColumnName_25_0= ruleEString ) ) )? ( ',' 'PATTREN:' ( (lv_pattern_28_0= ruleEString ) ) )? ( ',' 'PRECISION:' ( (lv_precision_31_0= ruleEInt ) ) )? ( ',' 'SOURCETYPE:' ( (lv_sourceType_34_0= ruleEString ) ) )? '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4709:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4710:5: 
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

            match(input,33,FOLLOW_33_in_ruleColumnType7124); 

                    createLeafNode(grammarAccess.getColumnTypeAccess().getAddColumnKeyword_1(), null); 
                
            match(input,12,FOLLOW_12_in_ruleColumnType7134); 

                    createLeafNode(grammarAccess.getColumnTypeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4728:1: ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4728:3: 'NAME:' ( (lv_name_4_0= ruleEString ) )
            {
            match(input,19,FOLLOW_19_in_ruleColumnType7145); 

                    createLeafNode(grammarAccess.getColumnTypeAccess().getNAMEKeyword_3_0(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4732:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4733:1: (lv_name_4_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4733:1: (lv_name_4_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4734:3: lv_name_4_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getNameEStringParserRuleCall_3_1_0(), currentNode); 
            	    
            pushFollow(FOLLOW_ruleEString_in_ruleColumnType7166);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4756:3: ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )?
            int alt99=2;
            int LA99_0 = input.LA(1);

            if ( (LA99_0==14) ) {
                int LA99_1 = input.LA(2);

                if ( (LA99_1==34) ) {
                    alt99=1;
                }
            }
            switch (alt99) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4756:5: ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleColumnType7178); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_4_0(), null); 
                        
                    match(input,34,FOLLOW_34_in_ruleColumnType7188); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getTYPEKeyword_4_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4764:1: ( (lv_type_7_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4765:1: (lv_type_7_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4765:1: (lv_type_7_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4766:3: lv_type_7_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getTypeEStringParserRuleCall_4_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleColumnType7209);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4788:4: ( ',' 'KEY:' ( (lv_key_10_0= ruleEBoolean ) ) )?
            int alt100=2;
            int LA100_0 = input.LA(1);

            if ( (LA100_0==14) ) {
                int LA100_1 = input.LA(2);

                if ( (LA100_1==83) ) {
                    alt100=1;
                }
            }
            switch (alt100) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4788:6: ',' 'KEY:' ( (lv_key_10_0= ruleEBoolean ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleColumnType7222); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_5_0(), null); 
                        
                    match(input,83,FOLLOW_83_in_ruleColumnType7232); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getKEYKeyword_5_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4796:1: ( (lv_key_10_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4797:1: (lv_key_10_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4797:1: (lv_key_10_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4798:3: lv_key_10_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getKeyEBooleanParserRuleCall_5_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleColumnType7253);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4820:4: ( ',' 'NULLABLE:' ( (lv_nullable_13_0= ruleEBoolean ) ) )?
            int alt101=2;
            int LA101_0 = input.LA(1);

            if ( (LA101_0==14) ) {
                int LA101_1 = input.LA(2);

                if ( (LA101_1==35) ) {
                    alt101=1;
                }
            }
            switch (alt101) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4820:6: ',' 'NULLABLE:' ( (lv_nullable_13_0= ruleEBoolean ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleColumnType7266); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_6_0(), null); 
                        
                    match(input,35,FOLLOW_35_in_ruleColumnType7276); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getNULLABLEKeyword_6_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4828:1: ( (lv_nullable_13_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4829:1: (lv_nullable_13_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4829:1: (lv_nullable_13_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4830:3: lv_nullable_13_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getNullableEBooleanParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEBoolean_in_ruleColumnType7297);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4852:4: ( ',' 'DEFAULTVALUE:' ( (lv_defaultValue_16_0= ruleEString ) ) )?
            int alt102=2;
            int LA102_0 = input.LA(1);

            if ( (LA102_0==14) ) {
                int LA102_1 = input.LA(2);

                if ( (LA102_1==84) ) {
                    alt102=1;
                }
            }
            switch (alt102) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4852:6: ',' 'DEFAULTVALUE:' ( (lv_defaultValue_16_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleColumnType7310); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_7_0(), null); 
                        
                    match(input,84,FOLLOW_84_in_ruleColumnType7320); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getDEFAULTVALUEKeyword_7_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4860:1: ( (lv_defaultValue_16_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4861:1: (lv_defaultValue_16_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4861:1: (lv_defaultValue_16_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4862:3: lv_defaultValue_16_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getDefaultValueEStringParserRuleCall_7_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleColumnType7341);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4884:4: ( ',' 'LENGTH:' ( (lv_length_19_0= ruleEInt ) ) )?
            int alt103=2;
            int LA103_0 = input.LA(1);

            if ( (LA103_0==14) ) {
                int LA103_1 = input.LA(2);

                if ( (LA103_1==85) ) {
                    alt103=1;
                }
            }
            switch (alt103) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4884:6: ',' 'LENGTH:' ( (lv_length_19_0= ruleEInt ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleColumnType7354); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_8_0(), null); 
                        
                    match(input,85,FOLLOW_85_in_ruleColumnType7364); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getLENGTHKeyword_8_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4892:1: ( (lv_length_19_0= ruleEInt ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4893:1: (lv_length_19_0= ruleEInt )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4893:1: (lv_length_19_0= ruleEInt )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4894:3: lv_length_19_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getLengthEIntParserRuleCall_8_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEInt_in_ruleColumnType7385);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4916:4: ( ',' 'COMMENT:' ( (lv_comment_22_0= ruleEString ) ) )?
            int alt104=2;
            int LA104_0 = input.LA(1);

            if ( (LA104_0==14) ) {
                int LA104_1 = input.LA(2);

                if ( (LA104_1==64) ) {
                    alt104=1;
                }
            }
            switch (alt104) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4916:6: ',' 'COMMENT:' ( (lv_comment_22_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleColumnType7398); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_9_0(), null); 
                        
                    match(input,64,FOLLOW_64_in_ruleColumnType7408); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCOMMENTKeyword_9_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4924:1: ( (lv_comment_22_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4925:1: (lv_comment_22_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4925:1: (lv_comment_22_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4926:3: lv_comment_22_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getCommentEStringParserRuleCall_9_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleColumnType7429);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4948:4: ( ',' 'ORIGINALDBCOLUMNNAME' ( (lv_originalDbColumnName_25_0= ruleEString ) ) )?
            int alt105=2;
            int LA105_0 = input.LA(1);

            if ( (LA105_0==14) ) {
                int LA105_1 = input.LA(2);

                if ( (LA105_1==86) ) {
                    alt105=1;
                }
            }
            switch (alt105) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4948:6: ',' 'ORIGINALDBCOLUMNNAME' ( (lv_originalDbColumnName_25_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleColumnType7442); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_10_0(), null); 
                        
                    match(input,86,FOLLOW_86_in_ruleColumnType7452); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getORIGINALDBCOLUMNNAMEKeyword_10_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4956:1: ( (lv_originalDbColumnName_25_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4957:1: (lv_originalDbColumnName_25_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4957:1: (lv_originalDbColumnName_25_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4958:3: lv_originalDbColumnName_25_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getOriginalDbColumnNameEStringParserRuleCall_10_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleColumnType7473);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4980:4: ( ',' 'PATTREN:' ( (lv_pattern_28_0= ruleEString ) ) )?
            int alt106=2;
            int LA106_0 = input.LA(1);

            if ( (LA106_0==14) ) {
                int LA106_1 = input.LA(2);

                if ( (LA106_1==87) ) {
                    alt106=1;
                }
            }
            switch (alt106) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4980:6: ',' 'PATTREN:' ( (lv_pattern_28_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleColumnType7486); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_11_0(), null); 
                        
                    match(input,87,FOLLOW_87_in_ruleColumnType7496); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getPATTRENKeyword_11_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4988:1: ( (lv_pattern_28_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4989:1: (lv_pattern_28_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4989:1: (lv_pattern_28_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:4990:3: lv_pattern_28_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getPatternEStringParserRuleCall_11_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleColumnType7517);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5012:4: ( ',' 'PRECISION:' ( (lv_precision_31_0= ruleEInt ) ) )?
            int alt107=2;
            int LA107_0 = input.LA(1);

            if ( (LA107_0==14) ) {
                int LA107_1 = input.LA(2);

                if ( (LA107_1==88) ) {
                    alt107=1;
                }
            }
            switch (alt107) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5012:6: ',' 'PRECISION:' ( (lv_precision_31_0= ruleEInt ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleColumnType7530); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_12_0(), null); 
                        
                    match(input,88,FOLLOW_88_in_ruleColumnType7540); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getPRECISIONKeyword_12_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5020:1: ( (lv_precision_31_0= ruleEInt ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5021:1: (lv_precision_31_0= ruleEInt )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5021:1: (lv_precision_31_0= ruleEInt )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5022:3: lv_precision_31_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getPrecisionEIntParserRuleCall_12_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEInt_in_ruleColumnType7561);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5044:4: ( ',' 'SOURCETYPE:' ( (lv_sourceType_34_0= ruleEString ) ) )?
            int alt108=2;
            int LA108_0 = input.LA(1);

            if ( (LA108_0==14) ) {
                alt108=1;
            }
            switch (alt108) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5044:6: ',' 'SOURCETYPE:' ( (lv_sourceType_34_0= ruleEString ) )
                    {
                    match(input,14,FOLLOW_14_in_ruleColumnType7574); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_13_0(), null); 
                        
                    match(input,89,FOLLOW_89_in_ruleColumnType7584); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getSOURCETYPEKeyword_13_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5052:1: ( (lv_sourceType_34_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5053:1: (lv_sourceType_34_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5053:1: (lv_sourceType_34_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5054:3: lv_sourceType_34_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getSourceTypeEStringParserRuleCall_13_2_0(), currentNode); 
                    	    
                    pushFollow(FOLLOW_ruleEString_in_ruleColumnType7605);
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

            match(input,13,FOLLOW_13_in_ruleColumnType7617); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5088:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5089:2: (iv_ruleEString= ruleEString EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5090:2: iv_ruleEString= ruleEString EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEStringRule(), currentNode); 
            pushFollow(FOLLOW_ruleEString_in_entryRuleEString7654);
            iv_ruleEString=ruleEString();
            _fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEString7665); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5097:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID | this_INT_2= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;
        Token this_INT_2=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5102:6: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID | this_INT_2= RULE_INT ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5103:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID | this_INT_2= RULE_INT )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5103:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID | this_INT_2= RULE_INT )
            int alt109=3;
            switch ( input.LA(1) ) {
            case RULE_STRING:
                {
                alt109=1;
                }
                break;
            case RULE_ID:
                {
                alt109=2;
                }
                break;
            case RULE_INT:
                {
                alt109=3;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("5103:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID | this_INT_2= RULE_INT )", 109, 0, input);

                throw nvae;
            }

            switch (alt109) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5103:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FOLLOW_RULE_STRING_in_ruleEString7705); 

                    		current.merge(this_STRING_0);
                        
                     
                        createLeafNode(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5111:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)input.LT(1);
                    match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleEString7731); 

                    		current.merge(this_ID_1);
                        
                     
                        createLeafNode(grammarAccess.getEStringAccess().getIDTerminalRuleCall_1(), null); 
                        

                    }
                    break;
                case 3 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5119:10: this_INT_2= RULE_INT
                    {
                    this_INT_2=(Token)input.LT(1);
                    match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleEString7757); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5134:1: entryRuleEInt returns [String current=null] : iv_ruleEInt= ruleEInt EOF ;
    public final String entryRuleEInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEInt = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5135:2: (iv_ruleEInt= ruleEInt EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5136:2: iv_ruleEInt= ruleEInt EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEIntRule(), currentNode); 
            pushFollow(FOLLOW_ruleEInt_in_entryRuleEInt7803);
            iv_ruleEInt=ruleEInt();
            _fsp--;

             current =iv_ruleEInt.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEInt7814); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5143:1: ruleEInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleEInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5148:6: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5149:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5149:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5149:2: (kw= '-' )? this_INT_1= RULE_INT
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5149:2: (kw= '-' )?
            int alt110=2;
            int LA110_0 = input.LA(1);

            if ( (LA110_0==90) ) {
                alt110=1;
            }
            switch (alt110) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5150:2: kw= '-'
                    {
                    kw=(Token)input.LT(1);
                    match(input,90,FOLLOW_90_in_ruleEInt7853); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getEIntAccess().getHyphenMinusKeyword_0(), null); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)input.LT(1);
            match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleEInt7870); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5170:1: entryRuleEBoolean returns [String current=null] : iv_ruleEBoolean= ruleEBoolean EOF ;
    public final String entryRuleEBoolean() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEBoolean = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5171:2: (iv_ruleEBoolean= ruleEBoolean EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5172:2: iv_ruleEBoolean= ruleEBoolean EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEBooleanRule(), currentNode); 
            pushFollow(FOLLOW_ruleEBoolean_in_entryRuleEBoolean7916);
            iv_ruleEBoolean=ruleEBoolean();
            _fsp--;

             current =iv_ruleEBoolean.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleEBoolean7927); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5179:1: ruleEBoolean returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'true' | kw= 'false' ) ;
    public final AntlrDatatypeRuleToken ruleEBoolean() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5184:6: ( (kw= 'true' | kw= 'false' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5185:1: (kw= 'true' | kw= 'false' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5185:1: (kw= 'true' | kw= 'false' )
            int alt111=2;
            int LA111_0 = input.LA(1);

            if ( (LA111_0==91) ) {
                alt111=1;
            }
            else if ( (LA111_0==92) ) {
                alt111=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("5185:1: (kw= 'true' | kw= 'false' )", 111, 0, input);

                throw nvae;
            }
            switch (alt111) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5186:2: kw= 'true'
                    {
                    kw=(Token)input.LT(1);
                    match(input,91,FOLLOW_91_in_ruleEBoolean7965); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getEBooleanAccess().getTrueKeyword_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:5193:2: kw= 'false'
                    {
                    kw=(Token)input.LT(1);
                    match(input,92,FOLLOW_92_in_ruleEBoolean7984); 

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
    public static final BitSet FOLLOW_13_in_ruleProcessType171 = new BitSet(new long[]{0x1100000000000002L,0x0000000000000810L});
    public static final BitSet FOLLOW_ruleNodeType_in_ruleProcessType194 = new BitSet(new long[]{0x1100000000000002L,0x0000000000000810L});
    public static final BitSet FOLLOW_ruleConnectionType_in_ruleProcessType216 = new BitSet(new long[]{0x1000000000000002L,0x0000000000000810L});
    public static final BitSet FOLLOW_ruleNoteType_in_ruleProcessType238 = new BitSet(new long[]{0x1000000000000002L,0x0000000000000800L});
    public static final BitSet FOLLOW_ruleContextType_in_ruleProcessType260 = new BitSet(new long[]{0x1000000000000002L});
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
    public static final BitSet FOLLOW_ruleEString_in_ruleDBInputTable889 = new BitSet(new long[]{0x0000000300F00002L});
    public static final BitSet FOLLOW_20_in_ruleDBInputTable900 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleDBInputTable921 = new BitSet(new long[]{0x0000000300E00002L});
    public static final BitSet FOLLOW_21_in_ruleDBInputTable934 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleDBInputTable955 = new BitSet(new long[]{0x0000000300C00002L});
    public static final BitSet FOLLOW_22_in_ruleDBInputTable968 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBInputTable989 = new BitSet(new long[]{0x0000000300800002L});
    public static final BitSet FOLLOW_23_in_ruleDBInputTable1002 = new BitSet(new long[]{0x00000000FF000000L});
    public static final BitSet FOLLOW_24_in_ruleDBInputTable1021 = new BitSet(new long[]{0x0000000300000002L});
    public static final BitSet FOLLOW_25_in_ruleDBInputTable1058 = new BitSet(new long[]{0x0000000300000002L});
    public static final BitSet FOLLOW_26_in_ruleDBInputTable1095 = new BitSet(new long[]{0x0000000300000002L});
    public static final BitSet FOLLOW_27_in_ruleDBInputTable1132 = new BitSet(new long[]{0x0000000300000002L});
    public static final BitSet FOLLOW_28_in_ruleDBInputTable1169 = new BitSet(new long[]{0x0000000300000002L});
    public static final BitSet FOLLOW_29_in_ruleDBInputTable1206 = new BitSet(new long[]{0x0000000300000002L});
    public static final BitSet FOLLOW_30_in_ruleDBInputTable1243 = new BitSet(new long[]{0x0000000300000002L});
    public static final BitSet FOLLOW_31_in_ruleDBInputTable1280 = new BitSet(new long[]{0x0000000300000002L});
    public static final BitSet FOLLOW_32_in_ruleDBInputTable1307 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBInputTable1328 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_ruleDBMapperTableEntry_in_ruleDBInputTable1351 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_ruleDBVarTable_in_entryRuleDBVarTable1388 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDBVarTable1398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleDBVarTable1442 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBVarTable1463 = new BitSet(new long[]{0x0000000200300002L});
    public static final BitSet FOLLOW_20_in_ruleDBVarTable1474 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleDBVarTable1495 = new BitSet(new long[]{0x0000000200200002L});
    public static final BitSet FOLLOW_21_in_ruleDBVarTable1508 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleDBVarTable1529 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_ruleDBMapperTableEntry_in_ruleDBVarTable1552 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_ruleDBOutputTable_in_entryRuleDBOutputTable1589 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDBOutputTable1599 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleDBOutputTable1643 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBOutputTable1664 = new BitSet(new long[]{0x0000008200300002L});
    public static final BitSet FOLLOW_20_in_ruleDBOutputTable1675 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleDBOutputTable1696 = new BitSet(new long[]{0x0000008200200002L});
    public static final BitSet FOLLOW_21_in_ruleDBOutputTable1709 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleDBOutputTable1730 = new BitSet(new long[]{0x0000008200000002L});
    public static final BitSet FOLLOW_ruleDBMapperTableEntry_in_ruleDBOutputTable1753 = new BitSet(new long[]{0x0000008200000002L});
    public static final BitSet FOLLOW_ruleDBFilterEntry_in_ruleDBOutputTable1775 = new BitSet(new long[]{0x0000008000000002L});
    public static final BitSet FOLLOW_ruleDBMapperTableEntry_in_entryRuleDBMapperTableEntry1812 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDBMapperTableEntry1822 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleDBMapperTableEntry1866 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleDBMapperTableEntry1876 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleDBMapperTableEntry1886 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBMapperTableEntry1907 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleDBMapperTableEntry1918 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleDBMapperTableEntry1928 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBMapperTableEntry1949 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleDBMapperTableEntry1962 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_35_in_ruleDBMapperTableEntry1972 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleDBMapperTableEntry1993 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleDBMapperTableEntry2006 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_36_in_ruleDBMapperTableEntry2016 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBMapperTableEntry2037 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleDBMapperTableEntry2050 = new BitSet(new long[]{0x0000002000000000L});
    public static final BitSet FOLLOW_37_in_ruleDBMapperTableEntry2060 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleDBMapperTableEntry2081 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleDBMapperTableEntry2094 = new BitSet(new long[]{0x0000004000000000L});
    public static final BitSet FOLLOW_38_in_ruleDBMapperTableEntry2104 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBMapperTableEntry2125 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleDBMapperTableEntry2137 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleDBFilterEntry_in_entryRuleDBFilterEntry2173 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleDBFilterEntry2183 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_39_in_ruleDBFilterEntry2218 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleDBFilterEntry2228 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleDBFilterEntry2238 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBFilterEntry2259 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleDBFilterEntry2269 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_36_in_ruleDBFilterEntry2279 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleDBFilterEntry2300 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleDBFilterEntry2310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMapperData_in_entryRuleMapperData2346 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMapperData2356 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_40_in_ruleMapperData2400 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMapperData2410 = new BitSet(new long[]{0x0000020000072000L});
    public static final BitSet FOLLOW_41_in_ruleMapperData2421 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMapperData2431 = new BitSet(new long[]{0x0000040000000000L});
    public static final BitSet FOLLOW_ruleUiProperties_in_ruleMapperData2452 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleMapperData2462 = new BitSet(new long[]{0x0000000000072000L});
    public static final BitSet FOLLOW_16_in_ruleMapperData2475 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMapperData2485 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ruleInputTable_in_ruleMapperData2506 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleMapperData2516 = new BitSet(new long[]{0x0000000000072000L});
    public static final BitSet FOLLOW_17_in_ruleMapperData2529 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMapperData2539 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ruleVarTable_in_ruleMapperData2560 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleMapperData2570 = new BitSet(new long[]{0x0000000000062000L});
    public static final BitSet FOLLOW_18_in_ruleMapperData2583 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMapperData2593 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ruleOutputTable_in_ruleMapperData2614 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleMapperData2624 = new BitSet(new long[]{0x0000000000042000L});
    public static final BitSet FOLLOW_13_in_ruleMapperData2636 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUiProperties_in_entryRuleUiProperties2672 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUiProperties2682 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_42_in_ruleUiProperties2726 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleUiProperties2747 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleInputTable_in_entryRuleInputTable2783 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleInputTable2793 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleInputTable2837 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleInputTable2858 = new BitSet(new long[]{0x000F880200100002L});
    public static final BitSet FOLLOW_20_in_ruleInputTable2869 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleInputTable2890 = new BitSet(new long[]{0x000F880200000002L});
    public static final BitSet FOLLOW_43_in_ruleInputTable2903 = new BitSet(new long[]{0x0000700000000000L});
    public static final BitSet FOLLOW_44_in_ruleInputTable2922 = new BitSet(new long[]{0x000F800200000002L});
    public static final BitSet FOLLOW_45_in_ruleInputTable2959 = new BitSet(new long[]{0x000F800200000002L});
    public static final BitSet FOLLOW_46_in_ruleInputTable2996 = new BitSet(new long[]{0x000F800200000002L});
    public static final BitSet FOLLOW_47_in_ruleInputTable3023 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleInputTable3044 = new BitSet(new long[]{0x000F000200000002L});
    public static final BitSet FOLLOW_48_in_ruleInputTable3057 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleInputTable3078 = new BitSet(new long[]{0x000E000200000002L});
    public static final BitSet FOLLOW_49_in_ruleInputTable3091 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleInputTable3112 = new BitSet(new long[]{0x000C000200000002L});
    public static final BitSet FOLLOW_50_in_ruleInputTable3125 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleInputTable3146 = new BitSet(new long[]{0x0008000200000002L});
    public static final BitSet FOLLOW_51_in_ruleInputTable3159 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleInputTable3180 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_ruleMapperTableEntry_in_ruleInputTable3203 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_ruleVarTable_in_entryRuleVarTable3240 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleVarTable3250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleVarTable3294 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleVarTable3315 = new BitSet(new long[]{0x0000080200100002L});
    public static final BitSet FOLLOW_20_in_ruleVarTable3326 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleVarTable3347 = new BitSet(new long[]{0x0000080200000002L});
    public static final BitSet FOLLOW_43_in_ruleVarTable3360 = new BitSet(new long[]{0x0000700000000000L});
    public static final BitSet FOLLOW_44_in_ruleVarTable3379 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_45_in_ruleVarTable3416 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_46_in_ruleVarTable3453 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_ruleMapperTableEntry_in_ruleVarTable3490 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_ruleOutputTable_in_entryRuleOutputTable3527 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleOutputTable3537 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleOutputTable3581 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleOutputTable3602 = new BitSet(new long[]{0x00F3880200100002L});
    public static final BitSet FOLLOW_20_in_ruleOutputTable3613 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleOutputTable3634 = new BitSet(new long[]{0x00F3880200000002L});
    public static final BitSet FOLLOW_43_in_ruleOutputTable3647 = new BitSet(new long[]{0x0000700000000000L});
    public static final BitSet FOLLOW_44_in_ruleOutputTable3666 = new BitSet(new long[]{0x00F3800200000002L});
    public static final BitSet FOLLOW_45_in_ruleOutputTable3703 = new BitSet(new long[]{0x00F3800200000002L});
    public static final BitSet FOLLOW_46_in_ruleOutputTable3740 = new BitSet(new long[]{0x00F3800200000002L});
    public static final BitSet FOLLOW_47_in_ruleOutputTable3767 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleOutputTable3788 = new BitSet(new long[]{0x00F3000200000002L});
    public static final BitSet FOLLOW_48_in_ruleOutputTable3801 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleOutputTable3822 = new BitSet(new long[]{0x00F2000200000002L});
    public static final BitSet FOLLOW_49_in_ruleOutputTable3835 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleOutputTable3856 = new BitSet(new long[]{0x00F0000200000002L});
    public static final BitSet FOLLOW_52_in_ruleOutputTable3869 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleOutputTable3890 = new BitSet(new long[]{0x00E0000200000002L});
    public static final BitSet FOLLOW_53_in_ruleOutputTable3903 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleOutputTable3924 = new BitSet(new long[]{0x00C0000200000002L});
    public static final BitSet FOLLOW_54_in_ruleOutputTable3937 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleOutputTable3958 = new BitSet(new long[]{0x0080000200000002L});
    public static final BitSet FOLLOW_55_in_ruleOutputTable3971 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleOutputTable3992 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_ruleMapperTableEntry_in_ruleOutputTable4015 = new BitSet(new long[]{0x0000000200000002L});
    public static final BitSet FOLLOW_ruleMapperTableEntry_in_entryRuleMapperTableEntry4052 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMapperTableEntry4062 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleMapperTableEntry4106 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMapperTableEntry4116 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleMapperTableEntry4126 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMapperTableEntry4147 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleMapperTableEntry4158 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleMapperTableEntry4168 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMapperTableEntry4189 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleMapperTableEntry4202 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_35_in_ruleMapperTableEntry4212 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleMapperTableEntry4233 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleMapperTableEntry4246 = new BitSet(new long[]{0x0000001000000000L});
    public static final BitSet FOLLOW_36_in_ruleMapperTableEntry4256 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMapperTableEntry4277 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleMapperTableEntry4289 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNodeType_in_entryRuleNodeType4325 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNodeType4335 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_56_in_ruleNodeType4370 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleNodeType4380 = new BitSet(new long[]{0x0200000000000000L});
    public static final BitSet FOLLOW_57_in_ruleNodeType4390 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleNodeType4400 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleNodeType4411 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleNodeType4432 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleNodeType4442 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleNodeType4454 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleUniqueNameType_in_ruleNodeType4475 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleNodeType4485 = new BitSet(new long[]{0x0400000000000000L});
    public static final BitSet FOLLOW_58_in_ruleNodeType4497 = new BitSet(new long[]{0x0000000000000040L,0x0000000004000000L});
    public static final BitSet FOLLOW_ruleEInt_in_ruleNodeType4519 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleNodeType4529 = new BitSet(new long[]{0x0000000000000040L,0x0000000004000000L});
    public static final BitSet FOLLOW_ruleEInt_in_ruleNodeType4550 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleNodeType4562 = new BitSet(new long[]{0x0800000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_ruleMetadataType_in_ruleNodeType4583 = new BitSet(new long[]{0x0800000000000000L,0x0000000000020000L});
    public static final BitSet FOLLOW_59_in_ruleNodeType4594 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleNodeType4604 = new BitSet(new long[]{0x0000000000006070L});
    public static final BitSet FOLLOW_ruleSchemaElementParameterType_in_ruleNodeType4627 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_ruleElementParameterType_in_ruleNodeType4646 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleNodeType4660 = new BitSet(new long[]{0x0000000000006070L});
    public static final BitSet FOLLOW_ruleSchemaElementParameterType_in_ruleNodeType4683 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_ruleElementParameterType_in_ruleNodeType4702 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_13_in_ruleNodeType4717 = new BitSet(new long[]{0x000001000000A000L,0x0000000000020000L});
    public static final BitSet FOLLOW_ruleMetadataType_in_ruleNodeType4738 = new BitSet(new long[]{0x000001000000A000L,0x0000000000020000L});
    public static final BitSet FOLLOW_ruleExternalData_in_ruleNodeType4760 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleNodeType4771 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleContextType_in_entryRuleContextType4807 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleContextType4817 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_60_in_ruleContextType4861 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleContextType4871 = new BitSet(new long[]{0x6000000000082000L});
    public static final BitSet FOLLOW_19_in_ruleContextType4882 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleContextType4903 = new BitSet(new long[]{0x6000000000002000L});
    public static final BitSet FOLLOW_61_in_ruleContextType4916 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleContextType4937 = new BitSet(new long[]{0x4000000000002000L});
    public static final BitSet FOLLOW_ruleContextParameterType_in_ruleContextType4961 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleContextType4972 = new BitSet(new long[]{0x4000000000000000L});
    public static final BitSet FOLLOW_ruleContextParameterType_in_ruleContextType4993 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_13_in_ruleContextType5007 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleContextParameterType_in_entryRuleContextParameterType5043 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleContextParameterType5053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_62_in_ruleContextParameterType5097 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleContextParameterType5107 = new BitSet(new long[]{0x8000000400082000L,0x000000000000000FL});
    public static final BitSet FOLLOW_19_in_ruleContextParameterType5118 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleContextParameterType5139 = new BitSet(new long[]{0x8000000400002000L,0x000000000000000FL});
    public static final BitSet FOLLOW_34_in_ruleContextParameterType5152 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleContextParameterType5173 = new BitSet(new long[]{0x8000000000002000L,0x000000000000000FL});
    public static final BitSet FOLLOW_63_in_ruleContextParameterType5186 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleContextParameterType5207 = new BitSet(new long[]{0x0000000000002000L,0x000000000000000FL});
    public static final BitSet FOLLOW_64_in_ruleContextParameterType5220 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleContextParameterType5241 = new BitSet(new long[]{0x0000000000002000L,0x000000000000000EL});
    public static final BitSet FOLLOW_65_in_ruleContextParameterType5254 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleContextParameterType5275 = new BitSet(new long[]{0x0000000000002000L,0x000000000000000CL});
    public static final BitSet FOLLOW_66_in_ruleContextParameterType5288 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleContextParameterType5309 = new BitSet(new long[]{0x0000000000002000L,0x0000000000000008L});
    public static final BitSet FOLLOW_67_in_ruleContextParameterType5322 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleContextParameterType5343 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleContextParameterType5355 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleUniqueNameType_in_entryRuleUniqueNameType5391 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleUniqueNameType5401 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEString_in_ruleUniqueNameType5446 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleConnectionType_in_entryRuleConnectionType5481 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleConnectionType5491 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_68_in_ruleConnectionType5535 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleConnectionType5545 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleConnectionType5556 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleConnectionType5577 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleConnectionType5589 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleConnectionType5599 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleConnectionType5620 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleConnectionType5632 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000020L});
    public static final BitSet FOLLOW_69_in_ruleConnectionType5642 = new BitSet(new long[]{0x0000000000000040L,0x0000000004000000L});
    public static final BitSet FOLLOW_ruleEInt_in_ruleConnectionType5663 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleConnectionType5676 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000040L});
    public static final BitSet FOLLOW_70_in_ruleConnectionType5686 = new BitSet(new long[]{0x0000000000000040L,0x0000000004000000L});
    public static final BitSet FOLLOW_ruleEInt_in_ruleConnectionType5707 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleConnectionType5720 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000080L});
    public static final BitSet FOLLOW_71_in_ruleConnectionType5730 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleConnectionType5751 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleConnectionType5764 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000100L});
    public static final BitSet FOLLOW_72_in_ruleConnectionType5774 = new BitSet(new long[]{0x0000000000000040L,0x0000000004000000L});
    public static final BitSet FOLLOW_ruleEInt_in_ruleConnectionType5795 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleConnectionType5808 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000200L});
    public static final BitSet FOLLOW_73_in_ruleConnectionType5818 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleConnectionType5839 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleConnectionType5852 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000400L});
    public static final BitSet FOLLOW_74_in_ruleConnectionType5862 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleConnectionType5883 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleConnectionType5896 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleElementParameterType_in_ruleConnectionType5917 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleConnectionType5929 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleNoteType_in_entryRuleNoteType5965 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleNoteType5975 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_75_in_ruleNoteType6010 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleNoteType6020 = new BitSet(new long[]{0x0000000000000070L,0x000000000000B000L});
    public static final BitSet FOLLOW_76_in_ruleNoteType6031 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleNoteType6052 = new BitSet(new long[]{0x0000000000000070L,0x000000000000A000L});
    public static final BitSet FOLLOW_77_in_ruleNoteType6065 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_78_in_ruleNoteType6075 = new BitSet(new long[]{0x0000000000000040L,0x0000000004000000L});
    public static final BitSet FOLLOW_ruleEInt_in_ruleNoteType6096 = new BitSet(new long[]{0x0000000000004000L});
    public static final BitSet FOLLOW_14_in_ruleNoteType6106 = new BitSet(new long[]{0x0000000000000040L,0x0000000004000000L});
    public static final BitSet FOLLOW_ruleEInt_in_ruleNoteType6127 = new BitSet(new long[]{0x0000000000000000L,0x0000000000004000L});
    public static final BitSet FOLLOW_78_in_ruleNoteType6137 = new BitSet(new long[]{0x0000000000000070L,0x0000000000008000L});
    public static final BitSet FOLLOW_79_in_ruleNoteType6150 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleNoteType6171 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleElementParameterType_in_ruleNoteType6194 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleNoteType6205 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleElementParameterType_in_ruleNoteType6226 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_13_in_ruleNoteType6238 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementParameterType_in_entryRuleElementParameterType6274 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleElementParameterType6284 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEString_in_ruleElementParameterType6340 = new BitSet(new long[]{0x0000000000000000L,0x0000000000010000L});
    public static final BitSet FOLLOW_80_in_ruleElementParameterType6350 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleElementParameterType6371 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleSchemaElementParameterType_in_entryRuleSchemaElementParameterType6408 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleSchemaElementParameterType6418 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEString_in_ruleSchemaElementParameterType6474 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleSchemaElementParameterType6484 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ruleElementValueType_in_ruleSchemaElementParameterType6505 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleSchemaElementParameterType6516 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_ruleElementValueType_in_ruleSchemaElementParameterType6537 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_13_in_ruleSchemaElementParameterType6549 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleElementValueType_in_entryRuleElementValueType6587 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleElementValueType6597 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_ruleElementValueType6642 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleElementValueType6663 = new BitSet(new long[]{0x8000000400000002L});
    public static final BitSet FOLLOW_63_in_ruleElementValueType6675 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleElementValueType6696 = new BitSet(new long[]{0x0000000400000002L});
    public static final BitSet FOLLOW_34_in_ruleElementValueType6709 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleElementValueType6730 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMetadataType_in_entryRuleMetadataType6768 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMetadataType6778 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_81_in_ruleMetadataType6822 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleMetadataType6832 = new BitSet(new long[]{0x0000000600082000L,0x0000000000040201L});
    public static final BitSet FOLLOW_19_in_ruleMetadataType6843 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMetadataType6864 = new BitSet(new long[]{0x0000000600002000L,0x0000000000040201L});
    public static final BitSet FOLLOW_34_in_ruleMetadataType6877 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMetadataType6898 = new BitSet(new long[]{0x0000000200002000L,0x0000000000040201L});
    public static final BitSet FOLLOW_82_in_ruleMetadataType6911 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMetadataType6932 = new BitSet(new long[]{0x0000000200002000L,0x0000000000000201L});
    public static final BitSet FOLLOW_64_in_ruleMetadataType6945 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMetadataType6966 = new BitSet(new long[]{0x0000000200002000L,0x0000000000000200L});
    public static final BitSet FOLLOW_73_in_ruleMetadataType6979 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleMetadataType7000 = new BitSet(new long[]{0x0000000200002000L});
    public static final BitSet FOLLOW_ruleColumnType_in_ruleMetadataType7023 = new BitSet(new long[]{0x0000000200002000L});
    public static final BitSet FOLLOW_13_in_ruleMetadataType7034 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleColumnType_in_entryRuleColumnType7070 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleColumnType7080 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_33_in_ruleColumnType7124 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_12_in_ruleColumnType7134 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleColumnType7145 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleColumnType7166 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleColumnType7178 = new BitSet(new long[]{0x0000000400000000L});
    public static final BitSet FOLLOW_34_in_ruleColumnType7188 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleColumnType7209 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleColumnType7222 = new BitSet(new long[]{0x0000000000000000L,0x0000000000080000L});
    public static final BitSet FOLLOW_83_in_ruleColumnType7232 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleColumnType7253 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleColumnType7266 = new BitSet(new long[]{0x0000000800000000L});
    public static final BitSet FOLLOW_35_in_ruleColumnType7276 = new BitSet(new long[]{0x0000000000000000L,0x0000000018000000L});
    public static final BitSet FOLLOW_ruleEBoolean_in_ruleColumnType7297 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleColumnType7310 = new BitSet(new long[]{0x0000000000000000L,0x0000000000100000L});
    public static final BitSet FOLLOW_84_in_ruleColumnType7320 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleColumnType7341 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleColumnType7354 = new BitSet(new long[]{0x0000000000000000L,0x0000000000200000L});
    public static final BitSet FOLLOW_85_in_ruleColumnType7364 = new BitSet(new long[]{0x0000000000000040L,0x0000000004000000L});
    public static final BitSet FOLLOW_ruleEInt_in_ruleColumnType7385 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleColumnType7398 = new BitSet(new long[]{0x0000000000000000L,0x0000000000000001L});
    public static final BitSet FOLLOW_64_in_ruleColumnType7408 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleColumnType7429 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleColumnType7442 = new BitSet(new long[]{0x0000000000000000L,0x0000000000400000L});
    public static final BitSet FOLLOW_86_in_ruleColumnType7452 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleColumnType7473 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleColumnType7486 = new BitSet(new long[]{0x0000000000000000L,0x0000000000800000L});
    public static final BitSet FOLLOW_87_in_ruleColumnType7496 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleColumnType7517 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleColumnType7530 = new BitSet(new long[]{0x0000000000000000L,0x0000000001000000L});
    public static final BitSet FOLLOW_88_in_ruleColumnType7540 = new BitSet(new long[]{0x0000000000000040L,0x0000000004000000L});
    public static final BitSet FOLLOW_ruleEInt_in_ruleColumnType7561 = new BitSet(new long[]{0x0000000000006000L});
    public static final BitSet FOLLOW_14_in_ruleColumnType7574 = new BitSet(new long[]{0x0000000000000000L,0x0000000002000000L});
    public static final BitSet FOLLOW_89_in_ruleColumnType7584 = new BitSet(new long[]{0x0000000000000070L});
    public static final BitSet FOLLOW_ruleEString_in_ruleColumnType7605 = new BitSet(new long[]{0x0000000000002000L});
    public static final BitSet FOLLOW_13_in_ruleColumnType7617 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEString_in_entryRuleEString7654 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEString7665 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_STRING_in_ruleEString7705 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleEString7731 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleEString7757 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEInt_in_entryRuleEInt7803 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEInt7814 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_90_in_ruleEInt7853 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleEInt7870 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleEBoolean_in_entryRuleEBoolean7916 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleEBoolean7927 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_91_in_ruleEBoolean7965 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_92_in_ruleEBoolean7984 = new BitSet(new long[]{0x0000000000000002L});

}