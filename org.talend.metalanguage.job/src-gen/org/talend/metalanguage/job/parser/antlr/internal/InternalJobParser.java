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
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_STRING", "RULE_ID", "RULE_INT", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'addParameters'", "'{'", "'}'", "','", "'addComponent'", "'setComponentDefinition'", "'TYPE:'", "'NAME:'", "'POSITION:'", "'setSettings'", "'ContextType'", "'CONFIRMATIONNEED:'", "'addContextParameter'", "'VALUE:'", "'COMMENT:'", "'PROMPT:'", "'PROMPTNEEDED:'", "'REPOSITORYCONTEXTID:'", "'addConnection'", "'LINESTYLE:'", "'MERGEORDER:'", "'METANAME:'", "'OUTPUTID:'", "'SOURCE:'", "'TARGET:'", "'addNote'", "'opaque'", "'POSITION'", "'\"'", "'text'", "':'", "'addSchema'", "'LABEL'", "'addColumn'", "'KEY:'", "'NULLABLE:'", "'DEFAULTVALUE:'", "'LENGTH:'", "'originalDbColumnName'", "'PATTREN:'", "'PRECISION:'", "'SOURCETYPE:'", "'-'", "'true'", "'false'"
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
            pushFollow(FollowSets000.FOLLOW_ruleProcessType_in_entryRuleProcessType75);
            iv_ruleProcessType=ruleProcessType();
            _fsp--;

             current =iv_ruleProcessType; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleProcessType85); 

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
                    match(input,11,FollowSets000.FOLLOW_11_in_ruleProcessType130); 

                            createLeafNode(grammarAccess.getProcessTypeAccess().getAddParametersKeyword_1_0(), null); 
                        
                    match(input,12,FollowSets000.FOLLOW_12_in_ruleProcessType140); 

                            createLeafNode(grammarAccess.getProcessTypeAccess().getLeftCurlyBracketKeyword_1_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:111:1: ( (lv_parameters_3_0= ruleParametersType ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:112:1: (lv_parameters_3_0= ruleParametersType )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:112:1: (lv_parameters_3_0= ruleParametersType )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:113:3: lv_parameters_3_0= ruleParametersType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getProcessTypeAccess().getParametersParametersTypeParserRuleCall_1_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleParametersType_in_ruleProcessType161);
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

                    match(input,13,FollowSets000.FOLLOW_13_in_ruleProcessType171); 

                            createLeafNode(grammarAccess.getProcessTypeAccess().getRightCurlyBracketKeyword_1_3(), null); 
                        

                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:139:3: ( (lv_node_5_0= ruleNodeType ) )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==15) ) {
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
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleNodeType_in_ruleProcessType194);
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

                if ( (LA3_0==29) ) {
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
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleConnectionType_in_ruleProcessType216);
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

                if ( (LA4_0==36) ) {
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
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleNoteType_in_ruleProcessType238);
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

                if ( (LA5_0==21) ) {
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
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleContextType_in_ruleProcessType260);
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
            pushFollow(FollowSets000.FOLLOW_ruleParametersType_in_entryRuleParametersType297);
            iv_ruleParametersType=ruleParametersType();
            _fsp--;

             current =iv_ruleParametersType; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleParametersType307); 

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
            	    
            pushFollow(FollowSets000.FOLLOW_ruleElementParameterType_in_ruleParametersType353);
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
            	    match(input,14,FollowSets000.FOLLOW_14_in_ruleParametersType364); 

            	            createLeafNode(grammarAccess.getParametersTypeAccess().getCommaKeyword_1_0(), null); 
            	        
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:286:1: ( (lv_elementParameter_2_0= ruleElementParameterType ) )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:287:1: (lv_elementParameter_2_0= ruleElementParameterType )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:287:1: (lv_elementParameter_2_0= ruleElementParameterType )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:288:3: lv_elementParameter_2_0= ruleElementParameterType
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getParametersTypeAccess().getElementParameterElementParameterTypeParserRuleCall_1_1_0(), currentNode); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleElementParameterType_in_ruleParametersType385);
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


    // $ANTLR start entryRuleNodeType
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:320:1: entryRuleNodeType returns [EObject current=null] : iv_ruleNodeType= ruleNodeType EOF ;
    public final EObject entryRuleNodeType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNodeType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:321:2: (iv_ruleNodeType= ruleNodeType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:322:2: iv_ruleNodeType= ruleNodeType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNodeTypeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleNodeType_in_entryRuleNodeType425);
            iv_ruleNodeType=ruleNodeType();
            _fsp--;

             current =iv_ruleNodeType; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleNodeType435); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:329:1: ruleNodeType returns [EObject current=null] : ( 'addComponent' '{' 'setComponentDefinition' '{' ( 'TYPE:' ( (lv_componentName_5_0= ruleEString ) ) ',' ) ( 'NAME:' ( (lv_elementParameter_8_0= ruleUniqueNameType ) ) ',' ) ( 'POSITION:' ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) ) ) '}' ( (lv_metadata_15_0= ruleMetadataType ) )* 'setSettings' '{' ( ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) ) ) ( ',' ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) ) )* '}' ( (lv_metadata_22_0= ruleMetadataType ) )* '}' ) ;
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


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:334:6: ( ( 'addComponent' '{' 'setComponentDefinition' '{' ( 'TYPE:' ( (lv_componentName_5_0= ruleEString ) ) ',' ) ( 'NAME:' ( (lv_elementParameter_8_0= ruleUniqueNameType ) ) ',' ) ( 'POSITION:' ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) ) ) '}' ( (lv_metadata_15_0= ruleMetadataType ) )* 'setSettings' '{' ( ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) ) ) ( ',' ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) ) )* '}' ( (lv_metadata_22_0= ruleMetadataType ) )* '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:335:1: ( 'addComponent' '{' 'setComponentDefinition' '{' ( 'TYPE:' ( (lv_componentName_5_0= ruleEString ) ) ',' ) ( 'NAME:' ( (lv_elementParameter_8_0= ruleUniqueNameType ) ) ',' ) ( 'POSITION:' ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) ) ) '}' ( (lv_metadata_15_0= ruleMetadataType ) )* 'setSettings' '{' ( ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) ) ) ( ',' ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) ) )* '}' ( (lv_metadata_22_0= ruleMetadataType ) )* '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:335:1: ( 'addComponent' '{' 'setComponentDefinition' '{' ( 'TYPE:' ( (lv_componentName_5_0= ruleEString ) ) ',' ) ( 'NAME:' ( (lv_elementParameter_8_0= ruleUniqueNameType ) ) ',' ) ( 'POSITION:' ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) ) ) '}' ( (lv_metadata_15_0= ruleMetadataType ) )* 'setSettings' '{' ( ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) ) ) ( ',' ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) ) )* '}' ( (lv_metadata_22_0= ruleMetadataType ) )* '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:335:3: 'addComponent' '{' 'setComponentDefinition' '{' ( 'TYPE:' ( (lv_componentName_5_0= ruleEString ) ) ',' ) ( 'NAME:' ( (lv_elementParameter_8_0= ruleUniqueNameType ) ) ',' ) ( 'POSITION:' ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) ) ) '}' ( (lv_metadata_15_0= ruleMetadataType ) )* 'setSettings' '{' ( ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) ) ) ( ',' ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) ) )* '}' ( (lv_metadata_22_0= ruleMetadataType ) )* '}'
            {
            match(input,15,FollowSets000.FOLLOW_15_in_ruleNodeType470); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getAddComponentKeyword_0(), null); 
                
            match(input,12,FollowSets000.FOLLOW_12_in_ruleNodeType480); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getLeftCurlyBracketKeyword_1(), null); 
                
            match(input,16,FollowSets000.FOLLOW_16_in_ruleNodeType490); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getSetComponentDefinitionKeyword_2(), null); 
                
            match(input,12,FollowSets000.FOLLOW_12_in_ruleNodeType500); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getLeftCurlyBracketKeyword_3(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:351:1: ( 'TYPE:' ( (lv_componentName_5_0= ruleEString ) ) ',' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:351:3: 'TYPE:' ( (lv_componentName_5_0= ruleEString ) ) ','
            {
            match(input,17,FollowSets000.FOLLOW_17_in_ruleNodeType511); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getTYPEKeyword_4_0(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:355:1: ( (lv_componentName_5_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:356:1: (lv_componentName_5_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:356:1: (lv_componentName_5_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:357:3: lv_componentName_5_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getComponentNameEStringParserRuleCall_4_1_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleNodeType532);
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

            match(input,14,FollowSets000.FOLLOW_14_in_ruleNodeType542); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getCommaKeyword_4_2(), null); 
                

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:383:2: ( 'NAME:' ( (lv_elementParameter_8_0= ruleUniqueNameType ) ) ',' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:383:4: 'NAME:' ( (lv_elementParameter_8_0= ruleUniqueNameType ) ) ','
            {
            match(input,18,FollowSets000.FOLLOW_18_in_ruleNodeType554); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getNAMEKeyword_5_0(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:387:1: ( (lv_elementParameter_8_0= ruleUniqueNameType ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:388:1: (lv_elementParameter_8_0= ruleUniqueNameType )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:388:1: (lv_elementParameter_8_0= ruleUniqueNameType )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:389:3: lv_elementParameter_8_0= ruleUniqueNameType
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getElementParameterUniqueNameTypeParserRuleCall_5_1_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleUniqueNameType_in_ruleNodeType575);
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

            match(input,14,FollowSets000.FOLLOW_14_in_ruleNodeType585); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getCommaKeyword_5_2(), null); 
                

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:415:2: ( 'POSITION:' ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:415:4: 'POSITION:' ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) )
            {
            match(input,19,FollowSets000.FOLLOW_19_in_ruleNodeType597); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getPOSITIONKeyword_6_0(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:419:1: ( ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:419:2: ( (lv_posX_11_0= ruleEInt ) ) ',' ( (lv_posY_13_0= ruleEInt ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:419:2: ( (lv_posX_11_0= ruleEInt ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:420:1: (lv_posX_11_0= ruleEInt )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:420:1: (lv_posX_11_0= ruleEInt )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:421:3: lv_posX_11_0= ruleEInt
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getPosXEIntParserRuleCall_6_1_0_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleNodeType619);
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

            match(input,14,FollowSets000.FOLLOW_14_in_ruleNodeType629); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getCommaKeyword_6_1_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:447:1: ( (lv_posY_13_0= ruleEInt ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:448:1: (lv_posY_13_0= ruleEInt )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:448:1: (lv_posY_13_0= ruleEInt )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:449:3: lv_posY_13_0= ruleEInt
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getPosYEIntParserRuleCall_6_1_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleNodeType650);
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

            match(input,13,FollowSets000.FOLLOW_13_in_ruleNodeType662); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getRightCurlyBracketKeyword_7(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:475:1: ( (lv_metadata_15_0= ruleMetadataType ) )*
            loop7:
            do {
                int alt7=2;
                int LA7_0 = input.LA(1);

                if ( (LA7_0==42) ) {
                    alt7=1;
                }


                switch (alt7) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:476:1: (lv_metadata_15_0= ruleMetadataType )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:476:1: (lv_metadata_15_0= ruleMetadataType )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:477:3: lv_metadata_15_0= ruleMetadataType
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getMetadataMetadataTypeParserRuleCall_8_0(), currentNode); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleMetadataType_in_ruleNodeType683);
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
            	    break loop7;
                }
            } while (true);

            match(input,20,FollowSets000.FOLLOW_20_in_ruleNodeType694); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getSetSettingsKeyword_9(), null); 
                
            match(input,12,FollowSets000.FOLLOW_12_in_ruleNodeType704); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getLeftCurlyBracketKeyword_10(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:507:1: ( ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:508:1: ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:508:1: ( (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:509:1: (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:509:1: (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType )
            int alt8=2;
            switch ( input.LA(1) ) {
            case RULE_STRING:
                {
                int LA8_1 = input.LA(2);

                if ( (LA8_1==41) ) {
                    alt8=2;
                }
                else if ( (LA8_1==12) ) {
                    alt8=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("509:1: (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType )", 8, 1, input);

                    throw nvae;
                }
                }
                break;
            case RULE_ID:
                {
                int LA8_2 = input.LA(2);

                if ( (LA8_2==41) ) {
                    alt8=2;
                }
                else if ( (LA8_2==12) ) {
                    alt8=1;
                }
                else {
                    NoViableAltException nvae =
                        new NoViableAltException("509:1: (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType )", 8, 2, input);

                    throw nvae;
                }
                }
                break;
            case 13:
            case 14:
                {
                alt8=1;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("509:1: (lv_elementParameter_18_1= ruleSchemaElementParameterType | lv_elementParameter_18_2= ruleElementParameterType )", 8, 0, input);

                throw nvae;
            }

            switch (alt8) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:510:3: lv_elementParameter_18_1= ruleSchemaElementParameterType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getElementParameterSchemaElementParameterTypeParserRuleCall_11_0_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleSchemaElementParameterType_in_ruleNodeType727);
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
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:531:8: lv_elementParameter_18_2= ruleElementParameterType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getElementParameterElementParameterTypeParserRuleCall_11_0_1(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleElementParameterType_in_ruleNodeType746);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:555:2: ( ',' ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) ) )*
            loop10:
            do {
                int alt10=2;
                int LA10_0 = input.LA(1);

                if ( (LA10_0==14) ) {
                    alt10=1;
                }


                switch (alt10) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:555:4: ',' ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) )
            	    {
            	    match(input,14,FollowSets000.FOLLOW_14_in_ruleNodeType760); 

            	            createLeafNode(grammarAccess.getNodeTypeAccess().getCommaKeyword_12_0(), null); 
            	        
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:559:1: ( ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) ) )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:560:1: ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:560:1: ( (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType ) )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:561:1: (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:561:1: (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType )
            	    int alt9=2;
            	    switch ( input.LA(1) ) {
            	    case RULE_STRING:
            	        {
            	        int LA9_1 = input.LA(2);

            	        if ( (LA9_1==12) ) {
            	            alt9=1;
            	        }
            	        else if ( (LA9_1==41) ) {
            	            alt9=2;
            	        }
            	        else {
            	            NoViableAltException nvae =
            	                new NoViableAltException("561:1: (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType )", 9, 1, input);

            	            throw nvae;
            	        }
            	        }
            	        break;
            	    case RULE_ID:
            	        {
            	        int LA9_2 = input.LA(2);

            	        if ( (LA9_2==41) ) {
            	            alt9=2;
            	        }
            	        else if ( (LA9_2==12) ) {
            	            alt9=1;
            	        }
            	        else {
            	            NoViableAltException nvae =
            	                new NoViableAltException("561:1: (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType )", 9, 2, input);

            	            throw nvae;
            	        }
            	        }
            	        break;
            	    case 13:
            	    case 14:
            	        {
            	        alt9=1;
            	        }
            	        break;
            	    default:
            	        NoViableAltException nvae =
            	            new NoViableAltException("561:1: (lv_elementParameter_20_1= ruleSchemaElementParameterType | lv_elementParameter_20_2= ruleElementParameterType )", 9, 0, input);

            	        throw nvae;
            	    }

            	    switch (alt9) {
            	        case 1 :
            	            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:562:3: lv_elementParameter_20_1= ruleSchemaElementParameterType
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getElementParameterSchemaElementParameterTypeParserRuleCall_12_1_0_0(), currentNode); 
            	            	    
            	            pushFollow(FollowSets000.FOLLOW_ruleSchemaElementParameterType_in_ruleNodeType783);
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
            	            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:583:8: lv_elementParameter_20_2= ruleElementParameterType
            	            {
            	             
            	            	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getElementParameterElementParameterTypeParserRuleCall_12_1_0_1(), currentNode); 
            	            	    
            	            pushFollow(FollowSets000.FOLLOW_ruleElementParameterType_in_ruleNodeType802);
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
            	    break loop10;
                }
            } while (true);

            match(input,13,FollowSets000.FOLLOW_13_in_ruleNodeType817); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getRightCurlyBracketKeyword_13(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:611:1: ( (lv_metadata_22_0= ruleMetadataType ) )*
            loop11:
            do {
                int alt11=2;
                int LA11_0 = input.LA(1);

                if ( (LA11_0==42) ) {
                    alt11=1;
                }


                switch (alt11) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:612:1: (lv_metadata_22_0= ruleMetadataType )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:612:1: (lv_metadata_22_0= ruleMetadataType )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:613:3: lv_metadata_22_0= ruleMetadataType
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getNodeTypeAccess().getMetadataMetadataTypeParserRuleCall_14_0(), currentNode); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleMetadataType_in_ruleNodeType838);
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
            	    break loop11;
                }
            } while (true);

            match(input,13,FollowSets000.FOLLOW_13_in_ruleNodeType849); 

                    createLeafNode(grammarAccess.getNodeTypeAccess().getRightCurlyBracketKeyword_15(), null); 
                

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:647:1: entryRuleContextType returns [EObject current=null] : iv_ruleContextType= ruleContextType EOF ;
    public final EObject entryRuleContextType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContextType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:648:2: (iv_ruleContextType= ruleContextType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:649:2: iv_ruleContextType= ruleContextType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getContextTypeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleContextType_in_entryRuleContextType885);
            iv_ruleContextType=ruleContextType();
            _fsp--;

             current =iv_ruleContextType; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleContextType895); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:656:1: ruleContextType returns [EObject current=null] : ( () 'ContextType' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'CONFIRMATIONNEED:' ( (lv_confirmationNeeded_6_0= ruleEBoolean ) ) )? ( ( (lv_contextParameter_7_0= ruleContextParameterType ) ) ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )* )? '}' ) ;
    public final EObject ruleContextType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_4_0 = null;

        AntlrDatatypeRuleToken lv_confirmationNeeded_6_0 = null;

        EObject lv_contextParameter_7_0 = null;

        EObject lv_contextParameter_9_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:661:6: ( ( () 'ContextType' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'CONFIRMATIONNEED:' ( (lv_confirmationNeeded_6_0= ruleEBoolean ) ) )? ( ( (lv_contextParameter_7_0= ruleContextParameterType ) ) ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )* )? '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:662:1: ( () 'ContextType' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'CONFIRMATIONNEED:' ( (lv_confirmationNeeded_6_0= ruleEBoolean ) ) )? ( ( (lv_contextParameter_7_0= ruleContextParameterType ) ) ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )* )? '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:662:1: ( () 'ContextType' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'CONFIRMATIONNEED:' ( (lv_confirmationNeeded_6_0= ruleEBoolean ) ) )? ( ( (lv_contextParameter_7_0= ruleContextParameterType ) ) ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )* )? '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:662:2: () 'ContextType' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'CONFIRMATIONNEED:' ( (lv_confirmationNeeded_6_0= ruleEBoolean ) ) )? ( ( (lv_contextParameter_7_0= ruleContextParameterType ) ) ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )* )? '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:662:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:663:5: 
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

            match(input,21,FollowSets000.FOLLOW_21_in_ruleContextType939); 

                    createLeafNode(grammarAccess.getContextTypeAccess().getContextTypeKeyword_1(), null); 
                
            match(input,12,FollowSets000.FOLLOW_12_in_ruleContextType949); 

                    createLeafNode(grammarAccess.getContextTypeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:681:1: ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )?
            int alt12=2;
            int LA12_0 = input.LA(1);

            if ( (LA12_0==18) ) {
                alt12=1;
            }
            switch (alt12) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:681:3: 'NAME:' ( (lv_name_4_0= ruleEString ) )
                    {
                    match(input,18,FollowSets000.FOLLOW_18_in_ruleContextType960); 

                            createLeafNode(grammarAccess.getContextTypeAccess().getNAMEKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:685:1: ( (lv_name_4_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:686:1: (lv_name_4_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:686:1: (lv_name_4_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:687:3: lv_name_4_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextTypeAccess().getNameEStringParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleContextType981);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:709:4: ( 'CONFIRMATIONNEED:' ( (lv_confirmationNeeded_6_0= ruleEBoolean ) ) )?
            int alt13=2;
            int LA13_0 = input.LA(1);

            if ( (LA13_0==22) ) {
                alt13=1;
            }
            switch (alt13) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:709:6: 'CONFIRMATIONNEED:' ( (lv_confirmationNeeded_6_0= ruleEBoolean ) )
                    {
                    match(input,22,FollowSets000.FOLLOW_22_in_ruleContextType994); 

                            createLeafNode(grammarAccess.getContextTypeAccess().getCONFIRMATIONNEEDKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:713:1: ( (lv_confirmationNeeded_6_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:714:1: (lv_confirmationNeeded_6_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:714:1: (lv_confirmationNeeded_6_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:715:3: lv_confirmationNeeded_6_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextTypeAccess().getConfirmationNeededEBooleanParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleContextType1015);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:737:4: ( ( (lv_contextParameter_7_0= ruleContextParameterType ) ) ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )* )?
            int alt15=2;
            int LA15_0 = input.LA(1);

            if ( (LA15_0==23) ) {
                alt15=1;
            }
            switch (alt15) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:737:5: ( (lv_contextParameter_7_0= ruleContextParameterType ) ) ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )*
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:737:5: ( (lv_contextParameter_7_0= ruleContextParameterType ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:738:1: (lv_contextParameter_7_0= ruleContextParameterType )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:738:1: (lv_contextParameter_7_0= ruleContextParameterType )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:739:3: lv_contextParameter_7_0= ruleContextParameterType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextTypeAccess().getContextParameterContextParameterTypeParserRuleCall_5_0_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleContextParameterType_in_ruleContextType1039);
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

                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:761:2: ( ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) ) )*
                    loop14:
                    do {
                        int alt14=2;
                        int LA14_0 = input.LA(1);

                        if ( (LA14_0==14) ) {
                            alt14=1;
                        }


                        switch (alt14) {
                    	case 1 :
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:761:4: ',' ( (lv_contextParameter_9_0= ruleContextParameterType ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleContextType1050); 

                    	            createLeafNode(grammarAccess.getContextTypeAccess().getCommaKeyword_5_1_0(), null); 
                    	        
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:765:1: ( (lv_contextParameter_9_0= ruleContextParameterType ) )
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:766:1: (lv_contextParameter_9_0= ruleContextParameterType )
                    	    {
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:766:1: (lv_contextParameter_9_0= ruleContextParameterType )
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:767:3: lv_contextParameter_9_0= ruleContextParameterType
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getContextTypeAccess().getContextParameterContextParameterTypeParserRuleCall_5_1_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleContextParameterType_in_ruleContextType1071);
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
                    	    break loop14;
                        }
                    } while (true);


                    }
                    break;

            }

            match(input,13,FollowSets000.FOLLOW_13_in_ruleContextType1085); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:801:1: entryRuleContextParameterType returns [EObject current=null] : iv_ruleContextParameterType= ruleContextParameterType EOF ;
    public final EObject entryRuleContextParameterType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleContextParameterType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:802:2: (iv_ruleContextParameterType= ruleContextParameterType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:803:2: iv_ruleContextParameterType= ruleContextParameterType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getContextParameterTypeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleContextParameterType_in_entryRuleContextParameterType1121);
            iv_ruleContextParameterType=ruleContextParameterType();
            _fsp--;

             current =iv_ruleContextParameterType; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleContextParameterType1131); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:810:1: ruleContextParameterType returns [EObject current=null] : ( () 'addContextParameter' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? ( 'VALUE:' ( (lv_value_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'PROMPT:' ( (lv_prompt_12_0= ruleEString ) ) )? ( 'PROMPTNEEDED:' ( (lv_promptNeeded_14_0= ruleEBoolean ) ) )? ( 'REPOSITORYCONTEXTID:' ( (lv_repositoryContextId_16_0= ruleEString ) ) )? '}' ) ;
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
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:815:6: ( ( () 'addContextParameter' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? ( 'VALUE:' ( (lv_value_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'PROMPT:' ( (lv_prompt_12_0= ruleEString ) ) )? ( 'PROMPTNEEDED:' ( (lv_promptNeeded_14_0= ruleEBoolean ) ) )? ( 'REPOSITORYCONTEXTID:' ( (lv_repositoryContextId_16_0= ruleEString ) ) )? '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:816:1: ( () 'addContextParameter' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? ( 'VALUE:' ( (lv_value_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'PROMPT:' ( (lv_prompt_12_0= ruleEString ) ) )? ( 'PROMPTNEEDED:' ( (lv_promptNeeded_14_0= ruleEBoolean ) ) )? ( 'REPOSITORYCONTEXTID:' ( (lv_repositoryContextId_16_0= ruleEString ) ) )? '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:816:1: ( () 'addContextParameter' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? ( 'VALUE:' ( (lv_value_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'PROMPT:' ( (lv_prompt_12_0= ruleEString ) ) )? ( 'PROMPTNEEDED:' ( (lv_promptNeeded_14_0= ruleEBoolean ) ) )? ( 'REPOSITORYCONTEXTID:' ( (lv_repositoryContextId_16_0= ruleEString ) ) )? '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:816:2: () 'addContextParameter' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? ( 'VALUE:' ( (lv_value_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'PROMPT:' ( (lv_prompt_12_0= ruleEString ) ) )? ( 'PROMPTNEEDED:' ( (lv_promptNeeded_14_0= ruleEBoolean ) ) )? ( 'REPOSITORYCONTEXTID:' ( (lv_repositoryContextId_16_0= ruleEString ) ) )? '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:816:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:817:5: 
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

            match(input,23,FollowSets000.FOLLOW_23_in_ruleContextParameterType1175); 

                    createLeafNode(grammarAccess.getContextParameterTypeAccess().getAddContextParameterKeyword_1(), null); 
                
            match(input,12,FollowSets000.FOLLOW_12_in_ruleContextParameterType1185); 

                    createLeafNode(grammarAccess.getContextParameterTypeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:835:1: ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )?
            int alt16=2;
            int LA16_0 = input.LA(1);

            if ( (LA16_0==18) ) {
                alt16=1;
            }
            switch (alt16) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:835:3: 'NAME:' ( (lv_name_4_0= ruleEString ) )
                    {
                    match(input,18,FollowSets000.FOLLOW_18_in_ruleContextParameterType1196); 

                            createLeafNode(grammarAccess.getContextParameterTypeAccess().getNAMEKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:839:1: ( (lv_name_4_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:840:1: (lv_name_4_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:840:1: (lv_name_4_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:841:3: lv_name_4_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextParameterTypeAccess().getNameEStringParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleContextParameterType1217);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:863:4: ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )?
            int alt17=2;
            int LA17_0 = input.LA(1);

            if ( (LA17_0==17) ) {
                alt17=1;
            }
            switch (alt17) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:863:6: 'TYPE:' ( (lv_type_6_0= ruleEString ) )
                    {
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleContextParameterType1230); 

                            createLeafNode(grammarAccess.getContextParameterTypeAccess().getTYPEKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:867:1: ( (lv_type_6_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:868:1: (lv_type_6_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:868:1: (lv_type_6_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:869:3: lv_type_6_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextParameterTypeAccess().getTypeEStringParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleContextParameterType1251);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:891:4: ( 'VALUE:' ( (lv_value_8_0= ruleEString ) ) )?
            int alt18=2;
            int LA18_0 = input.LA(1);

            if ( (LA18_0==24) ) {
                alt18=1;
            }
            switch (alt18) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:891:6: 'VALUE:' ( (lv_value_8_0= ruleEString ) )
                    {
                    match(input,24,FollowSets000.FOLLOW_24_in_ruleContextParameterType1264); 

                            createLeafNode(grammarAccess.getContextParameterTypeAccess().getVALUEKeyword_5_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:895:1: ( (lv_value_8_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:896:1: (lv_value_8_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:896:1: (lv_value_8_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:897:3: lv_value_8_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextParameterTypeAccess().getValueEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleContextParameterType1285);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:919:4: ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )?
            int alt19=2;
            int LA19_0 = input.LA(1);

            if ( (LA19_0==25) ) {
                alt19=1;
            }
            switch (alt19) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:919:6: 'COMMENT:' ( (lv_comment_10_0= ruleEString ) )
                    {
                    match(input,25,FollowSets000.FOLLOW_25_in_ruleContextParameterType1298); 

                            createLeafNode(grammarAccess.getContextParameterTypeAccess().getCOMMENTKeyword_6_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:923:1: ( (lv_comment_10_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:924:1: (lv_comment_10_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:924:1: (lv_comment_10_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:925:3: lv_comment_10_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextParameterTypeAccess().getCommentEStringParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleContextParameterType1319);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:947:4: ( 'PROMPT:' ( (lv_prompt_12_0= ruleEString ) ) )?
            int alt20=2;
            int LA20_0 = input.LA(1);

            if ( (LA20_0==26) ) {
                alt20=1;
            }
            switch (alt20) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:947:6: 'PROMPT:' ( (lv_prompt_12_0= ruleEString ) )
                    {
                    match(input,26,FollowSets000.FOLLOW_26_in_ruleContextParameterType1332); 

                            createLeafNode(grammarAccess.getContextParameterTypeAccess().getPROMPTKeyword_7_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:951:1: ( (lv_prompt_12_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:952:1: (lv_prompt_12_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:952:1: (lv_prompt_12_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:953:3: lv_prompt_12_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextParameterTypeAccess().getPromptEStringParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleContextParameterType1353);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:975:4: ( 'PROMPTNEEDED:' ( (lv_promptNeeded_14_0= ruleEBoolean ) ) )?
            int alt21=2;
            int LA21_0 = input.LA(1);

            if ( (LA21_0==27) ) {
                alt21=1;
            }
            switch (alt21) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:975:6: 'PROMPTNEEDED:' ( (lv_promptNeeded_14_0= ruleEBoolean ) )
                    {
                    match(input,27,FollowSets000.FOLLOW_27_in_ruleContextParameterType1366); 

                            createLeafNode(grammarAccess.getContextParameterTypeAccess().getPROMPTNEEDEDKeyword_8_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:979:1: ( (lv_promptNeeded_14_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:980:1: (lv_promptNeeded_14_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:980:1: (lv_promptNeeded_14_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:981:3: lv_promptNeeded_14_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextParameterTypeAccess().getPromptNeededEBooleanParserRuleCall_8_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleContextParameterType1387);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1003:4: ( 'REPOSITORYCONTEXTID:' ( (lv_repositoryContextId_16_0= ruleEString ) ) )?
            int alt22=2;
            int LA22_0 = input.LA(1);

            if ( (LA22_0==28) ) {
                alt22=1;
            }
            switch (alt22) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1003:6: 'REPOSITORYCONTEXTID:' ( (lv_repositoryContextId_16_0= ruleEString ) )
                    {
                    match(input,28,FollowSets000.FOLLOW_28_in_ruleContextParameterType1400); 

                            createLeafNode(grammarAccess.getContextParameterTypeAccess().getREPOSITORYCONTEXTIDKeyword_9_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1007:1: ( (lv_repositoryContextId_16_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1008:1: (lv_repositoryContextId_16_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1008:1: (lv_repositoryContextId_16_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1009:3: lv_repositoryContextId_16_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getContextParameterTypeAccess().getRepositoryContextIdEStringParserRuleCall_9_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleContextParameterType1421);
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

            match(input,13,FollowSets000.FOLLOW_13_in_ruleContextParameterType1433); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1043:1: entryRuleUniqueNameType returns [EObject current=null] : iv_ruleUniqueNameType= ruleUniqueNameType EOF ;
    public final EObject entryRuleUniqueNameType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleUniqueNameType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1044:2: (iv_ruleUniqueNameType= ruleUniqueNameType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1045:2: iv_ruleUniqueNameType= ruleUniqueNameType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getUniqueNameTypeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleUniqueNameType_in_entryRuleUniqueNameType1469);
            iv_ruleUniqueNameType=ruleUniqueNameType();
            _fsp--;

             current =iv_ruleUniqueNameType; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleUniqueNameType1479); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1052:1: ruleUniqueNameType returns [EObject current=null] : ( (lv_value_0_0= ruleEString ) ) ;
    public final EObject ruleUniqueNameType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_value_0_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1057:6: ( ( (lv_value_0_0= ruleEString ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1058:1: ( (lv_value_0_0= ruleEString ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1058:1: ( (lv_value_0_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1059:1: (lv_value_0_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1059:1: (lv_value_0_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1060:3: lv_value_0_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getUniqueNameTypeAccess().getValueEStringParserRuleCall_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleUniqueNameType1524);
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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1090:1: entryRuleConnectionType returns [EObject current=null] : iv_ruleConnectionType= ruleConnectionType EOF ;
    public final EObject entryRuleConnectionType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleConnectionType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1091:2: (iv_ruleConnectionType= ruleConnectionType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1092:2: iv_ruleConnectionType= ruleConnectionType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getConnectionTypeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleConnectionType_in_entryRuleConnectionType1559);
            iv_ruleConnectionType=ruleConnectionType();
            _fsp--;

             current =iv_ruleConnectionType; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleConnectionType1569); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1099:1: ruleConnectionType returns [EObject current=null] : ( () 'addConnection' '{' ( 'TYPE:' ( (lv_connectorName_4_0= ruleEString ) ) ) ( ',' 'NAME:' ( (lv_label_7_0= ruleEString ) ) ) ( ',' 'LINESTYLE:' ( (lv_lineStyle_10_0= ruleEInt ) ) )? ( ',' 'MERGEORDER:' ( (lv_mergeOrder_13_0= ruleEInt ) ) )? ( ',' 'METANAME:' ( (lv_metaname_16_0= ruleEString ) ) )? ( ',' 'OUTPUTID:' ( (lv_outputId_19_0= ruleEInt ) ) )? ( ',' 'SOURCE:' ( (lv_source_22_0= ruleEString ) ) )? ( ',' 'TARGET:' ( (lv_target_25_0= ruleEString ) ) )? ( ',' ( (lv_elementParameter_27_0= ruleElementParameterType ) ) )? '}' ) ;
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
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1104:6: ( ( () 'addConnection' '{' ( 'TYPE:' ( (lv_connectorName_4_0= ruleEString ) ) ) ( ',' 'NAME:' ( (lv_label_7_0= ruleEString ) ) ) ( ',' 'LINESTYLE:' ( (lv_lineStyle_10_0= ruleEInt ) ) )? ( ',' 'MERGEORDER:' ( (lv_mergeOrder_13_0= ruleEInt ) ) )? ( ',' 'METANAME:' ( (lv_metaname_16_0= ruleEString ) ) )? ( ',' 'OUTPUTID:' ( (lv_outputId_19_0= ruleEInt ) ) )? ( ',' 'SOURCE:' ( (lv_source_22_0= ruleEString ) ) )? ( ',' 'TARGET:' ( (lv_target_25_0= ruleEString ) ) )? ( ',' ( (lv_elementParameter_27_0= ruleElementParameterType ) ) )? '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1105:1: ( () 'addConnection' '{' ( 'TYPE:' ( (lv_connectorName_4_0= ruleEString ) ) ) ( ',' 'NAME:' ( (lv_label_7_0= ruleEString ) ) ) ( ',' 'LINESTYLE:' ( (lv_lineStyle_10_0= ruleEInt ) ) )? ( ',' 'MERGEORDER:' ( (lv_mergeOrder_13_0= ruleEInt ) ) )? ( ',' 'METANAME:' ( (lv_metaname_16_0= ruleEString ) ) )? ( ',' 'OUTPUTID:' ( (lv_outputId_19_0= ruleEInt ) ) )? ( ',' 'SOURCE:' ( (lv_source_22_0= ruleEString ) ) )? ( ',' 'TARGET:' ( (lv_target_25_0= ruleEString ) ) )? ( ',' ( (lv_elementParameter_27_0= ruleElementParameterType ) ) )? '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1105:1: ( () 'addConnection' '{' ( 'TYPE:' ( (lv_connectorName_4_0= ruleEString ) ) ) ( ',' 'NAME:' ( (lv_label_7_0= ruleEString ) ) ) ( ',' 'LINESTYLE:' ( (lv_lineStyle_10_0= ruleEInt ) ) )? ( ',' 'MERGEORDER:' ( (lv_mergeOrder_13_0= ruleEInt ) ) )? ( ',' 'METANAME:' ( (lv_metaname_16_0= ruleEString ) ) )? ( ',' 'OUTPUTID:' ( (lv_outputId_19_0= ruleEInt ) ) )? ( ',' 'SOURCE:' ( (lv_source_22_0= ruleEString ) ) )? ( ',' 'TARGET:' ( (lv_target_25_0= ruleEString ) ) )? ( ',' ( (lv_elementParameter_27_0= ruleElementParameterType ) ) )? '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1105:2: () 'addConnection' '{' ( 'TYPE:' ( (lv_connectorName_4_0= ruleEString ) ) ) ( ',' 'NAME:' ( (lv_label_7_0= ruleEString ) ) ) ( ',' 'LINESTYLE:' ( (lv_lineStyle_10_0= ruleEInt ) ) )? ( ',' 'MERGEORDER:' ( (lv_mergeOrder_13_0= ruleEInt ) ) )? ( ',' 'METANAME:' ( (lv_metaname_16_0= ruleEString ) ) )? ( ',' 'OUTPUTID:' ( (lv_outputId_19_0= ruleEInt ) ) )? ( ',' 'SOURCE:' ( (lv_source_22_0= ruleEString ) ) )? ( ',' 'TARGET:' ( (lv_target_25_0= ruleEString ) ) )? ( ',' ( (lv_elementParameter_27_0= ruleElementParameterType ) ) )? '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1105:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1106:5: 
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

            match(input,29,FollowSets000.FOLLOW_29_in_ruleConnectionType1613); 

                    createLeafNode(grammarAccess.getConnectionTypeAccess().getAddConnectionKeyword_1(), null); 
                
            match(input,12,FollowSets000.FOLLOW_12_in_ruleConnectionType1623); 

                    createLeafNode(grammarAccess.getConnectionTypeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1124:1: ( 'TYPE:' ( (lv_connectorName_4_0= ruleEString ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1124:3: 'TYPE:' ( (lv_connectorName_4_0= ruleEString ) )
            {
            match(input,17,FollowSets000.FOLLOW_17_in_ruleConnectionType1634); 

                    createLeafNode(grammarAccess.getConnectionTypeAccess().getTYPEKeyword_3_0(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1128:1: ( (lv_connectorName_4_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1129:1: (lv_connectorName_4_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1129:1: (lv_connectorName_4_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1130:3: lv_connectorName_4_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getConnectorNameEStringParserRuleCall_3_1_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleConnectionType1655);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1152:3: ( ',' 'NAME:' ( (lv_label_7_0= ruleEString ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1152:5: ',' 'NAME:' ( (lv_label_7_0= ruleEString ) )
            {
            match(input,14,FollowSets000.FOLLOW_14_in_ruleConnectionType1667); 

                    createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_4_0(), null); 
                
            match(input,18,FollowSets000.FOLLOW_18_in_ruleConnectionType1677); 

                    createLeafNode(grammarAccess.getConnectionTypeAccess().getNAMEKeyword_4_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1160:1: ( (lv_label_7_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1161:1: (lv_label_7_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1161:1: (lv_label_7_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1162:3: lv_label_7_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getLabelEStringParserRuleCall_4_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleConnectionType1698);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1184:3: ( ',' 'LINESTYLE:' ( (lv_lineStyle_10_0= ruleEInt ) ) )?
            int alt23=2;
            int LA23_0 = input.LA(1);

            if ( (LA23_0==14) ) {
                int LA23_1 = input.LA(2);

                if ( (LA23_1==30) ) {
                    alt23=1;
                }
            }
            switch (alt23) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1184:5: ',' 'LINESTYLE:' ( (lv_lineStyle_10_0= ruleEInt ) )
                    {
                    match(input,14,FollowSets000.FOLLOW_14_in_ruleConnectionType1710); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_5_0(), null); 
                        
                    match(input,30,FollowSets000.FOLLOW_30_in_ruleConnectionType1720); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getLINESTYLEKeyword_5_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1192:1: ( (lv_lineStyle_10_0= ruleEInt ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1193:1: (lv_lineStyle_10_0= ruleEInt )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1193:1: (lv_lineStyle_10_0= ruleEInt )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1194:3: lv_lineStyle_10_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getLineStyleEIntParserRuleCall_5_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleConnectionType1741);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1216:4: ( ',' 'MERGEORDER:' ( (lv_mergeOrder_13_0= ruleEInt ) ) )?
            int alt24=2;
            int LA24_0 = input.LA(1);

            if ( (LA24_0==14) ) {
                int LA24_1 = input.LA(2);

                if ( (LA24_1==31) ) {
                    alt24=1;
                }
            }
            switch (alt24) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1216:6: ',' 'MERGEORDER:' ( (lv_mergeOrder_13_0= ruleEInt ) )
                    {
                    match(input,14,FollowSets000.FOLLOW_14_in_ruleConnectionType1754); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_6_0(), null); 
                        
                    match(input,31,FollowSets000.FOLLOW_31_in_ruleConnectionType1764); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getMERGEORDERKeyword_6_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1224:1: ( (lv_mergeOrder_13_0= ruleEInt ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1225:1: (lv_mergeOrder_13_0= ruleEInt )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1225:1: (lv_mergeOrder_13_0= ruleEInt )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1226:3: lv_mergeOrder_13_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getMergeOrderEIntParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleConnectionType1785);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1248:4: ( ',' 'METANAME:' ( (lv_metaname_16_0= ruleEString ) ) )?
            int alt25=2;
            int LA25_0 = input.LA(1);

            if ( (LA25_0==14) ) {
                int LA25_1 = input.LA(2);

                if ( (LA25_1==32) ) {
                    alt25=1;
                }
            }
            switch (alt25) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1248:6: ',' 'METANAME:' ( (lv_metaname_16_0= ruleEString ) )
                    {
                    match(input,14,FollowSets000.FOLLOW_14_in_ruleConnectionType1798); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_7_0(), null); 
                        
                    match(input,32,FollowSets000.FOLLOW_32_in_ruleConnectionType1808); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getMETANAMEKeyword_7_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1256:1: ( (lv_metaname_16_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1257:1: (lv_metaname_16_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1257:1: (lv_metaname_16_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1258:3: lv_metaname_16_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getMetanameEStringParserRuleCall_7_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleConnectionType1829);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1280:4: ( ',' 'OUTPUTID:' ( (lv_outputId_19_0= ruleEInt ) ) )?
            int alt26=2;
            int LA26_0 = input.LA(1);

            if ( (LA26_0==14) ) {
                int LA26_1 = input.LA(2);

                if ( (LA26_1==33) ) {
                    alt26=1;
                }
            }
            switch (alt26) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1280:6: ',' 'OUTPUTID:' ( (lv_outputId_19_0= ruleEInt ) )
                    {
                    match(input,14,FollowSets000.FOLLOW_14_in_ruleConnectionType1842); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_8_0(), null); 
                        
                    match(input,33,FollowSets000.FOLLOW_33_in_ruleConnectionType1852); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getOUTPUTIDKeyword_8_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1288:1: ( (lv_outputId_19_0= ruleEInt ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1289:1: (lv_outputId_19_0= ruleEInt )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1289:1: (lv_outputId_19_0= ruleEInt )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1290:3: lv_outputId_19_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getOutputIdEIntParserRuleCall_8_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleConnectionType1873);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1312:4: ( ',' 'SOURCE:' ( (lv_source_22_0= ruleEString ) ) )?
            int alt27=2;
            int LA27_0 = input.LA(1);

            if ( (LA27_0==14) ) {
                int LA27_1 = input.LA(2);

                if ( (LA27_1==34) ) {
                    alt27=1;
                }
            }
            switch (alt27) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1312:6: ',' 'SOURCE:' ( (lv_source_22_0= ruleEString ) )
                    {
                    match(input,14,FollowSets000.FOLLOW_14_in_ruleConnectionType1886); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_9_0(), null); 
                        
                    match(input,34,FollowSets000.FOLLOW_34_in_ruleConnectionType1896); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getSOURCEKeyword_9_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1320:1: ( (lv_source_22_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1321:1: (lv_source_22_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1321:1: (lv_source_22_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1322:3: lv_source_22_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getSourceEStringParserRuleCall_9_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleConnectionType1917);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1344:4: ( ',' 'TARGET:' ( (lv_target_25_0= ruleEString ) ) )?
            int alt28=2;
            int LA28_0 = input.LA(1);

            if ( (LA28_0==14) ) {
                int LA28_1 = input.LA(2);

                if ( (LA28_1==35) ) {
                    alt28=1;
                }
            }
            switch (alt28) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1344:6: ',' 'TARGET:' ( (lv_target_25_0= ruleEString ) )
                    {
                    match(input,14,FollowSets000.FOLLOW_14_in_ruleConnectionType1930); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_10_0(), null); 
                        
                    match(input,35,FollowSets000.FOLLOW_35_in_ruleConnectionType1940); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getTARGETKeyword_10_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1352:1: ( (lv_target_25_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1353:1: (lv_target_25_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1353:1: (lv_target_25_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1354:3: lv_target_25_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getTargetEStringParserRuleCall_10_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleConnectionType1961);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1376:4: ( ',' ( (lv_elementParameter_27_0= ruleElementParameterType ) ) )?
            int alt29=2;
            int LA29_0 = input.LA(1);

            if ( (LA29_0==14) ) {
                alt29=1;
            }
            switch (alt29) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1376:6: ',' ( (lv_elementParameter_27_0= ruleElementParameterType ) )
                    {
                    match(input,14,FollowSets000.FOLLOW_14_in_ruleConnectionType1974); 

                            createLeafNode(grammarAccess.getConnectionTypeAccess().getCommaKeyword_11_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1380:1: ( (lv_elementParameter_27_0= ruleElementParameterType ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1381:1: (lv_elementParameter_27_0= ruleElementParameterType )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1381:1: (lv_elementParameter_27_0= ruleElementParameterType )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1382:3: lv_elementParameter_27_0= ruleElementParameterType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getConnectionTypeAccess().getElementParameterElementParameterTypeParserRuleCall_11_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleElementParameterType_in_ruleConnectionType1995);
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

            match(input,13,FollowSets000.FOLLOW_13_in_ruleConnectionType2007); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1416:1: entryRuleNoteType returns [EObject current=null] : iv_ruleNoteType= ruleNoteType EOF ;
    public final EObject entryRuleNoteType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleNoteType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1417:2: (iv_ruleNoteType= ruleNoteType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1418:2: iv_ruleNoteType= ruleNoteType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getNoteTypeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleNoteType_in_entryRuleNoteType2043);
            iv_ruleNoteType=ruleNoteType();
            _fsp--;

             current =iv_ruleNoteType; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleNoteType2053); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1425:1: ruleNoteType returns [EObject current=null] : ( 'addNote' '{' ( 'opaque' ( (lv_opaque_3_0= ruleEBoolean ) ) )? ( 'POSITION' '\"' ( (lv_posX_6_0= ruleEInt ) ) ',' ( (lv_posY_8_0= ruleEInt ) ) '\"' )? ( 'text' ( (lv_text_11_0= ruleEString ) ) )? ( (lv_elementParameter_12_0= ruleElementParameterType ) ) ( ',' ( (lv_elementParameter_14_0= ruleElementParameterType ) ) )* '}' ) ;
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
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1430:6: ( ( 'addNote' '{' ( 'opaque' ( (lv_opaque_3_0= ruleEBoolean ) ) )? ( 'POSITION' '\"' ( (lv_posX_6_0= ruleEInt ) ) ',' ( (lv_posY_8_0= ruleEInt ) ) '\"' )? ( 'text' ( (lv_text_11_0= ruleEString ) ) )? ( (lv_elementParameter_12_0= ruleElementParameterType ) ) ( ',' ( (lv_elementParameter_14_0= ruleElementParameterType ) ) )* '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1431:1: ( 'addNote' '{' ( 'opaque' ( (lv_opaque_3_0= ruleEBoolean ) ) )? ( 'POSITION' '\"' ( (lv_posX_6_0= ruleEInt ) ) ',' ( (lv_posY_8_0= ruleEInt ) ) '\"' )? ( 'text' ( (lv_text_11_0= ruleEString ) ) )? ( (lv_elementParameter_12_0= ruleElementParameterType ) ) ( ',' ( (lv_elementParameter_14_0= ruleElementParameterType ) ) )* '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1431:1: ( 'addNote' '{' ( 'opaque' ( (lv_opaque_3_0= ruleEBoolean ) ) )? ( 'POSITION' '\"' ( (lv_posX_6_0= ruleEInt ) ) ',' ( (lv_posY_8_0= ruleEInt ) ) '\"' )? ( 'text' ( (lv_text_11_0= ruleEString ) ) )? ( (lv_elementParameter_12_0= ruleElementParameterType ) ) ( ',' ( (lv_elementParameter_14_0= ruleElementParameterType ) ) )* '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1431:3: 'addNote' '{' ( 'opaque' ( (lv_opaque_3_0= ruleEBoolean ) ) )? ( 'POSITION' '\"' ( (lv_posX_6_0= ruleEInt ) ) ',' ( (lv_posY_8_0= ruleEInt ) ) '\"' )? ( 'text' ( (lv_text_11_0= ruleEString ) ) )? ( (lv_elementParameter_12_0= ruleElementParameterType ) ) ( ',' ( (lv_elementParameter_14_0= ruleElementParameterType ) ) )* '}'
            {
            match(input,36,FollowSets000.FOLLOW_36_in_ruleNoteType2088); 

                    createLeafNode(grammarAccess.getNoteTypeAccess().getAddNoteKeyword_0(), null); 
                
            match(input,12,FollowSets000.FOLLOW_12_in_ruleNoteType2098); 

                    createLeafNode(grammarAccess.getNoteTypeAccess().getLeftCurlyBracketKeyword_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1439:1: ( 'opaque' ( (lv_opaque_3_0= ruleEBoolean ) ) )?
            int alt30=2;
            int LA30_0 = input.LA(1);

            if ( (LA30_0==37) ) {
                alt30=1;
            }
            switch (alt30) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1439:3: 'opaque' ( (lv_opaque_3_0= ruleEBoolean ) )
                    {
                    match(input,37,FollowSets000.FOLLOW_37_in_ruleNoteType2109); 

                            createLeafNode(grammarAccess.getNoteTypeAccess().getOpaqueKeyword_2_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1443:1: ( (lv_opaque_3_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1444:1: (lv_opaque_3_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1444:1: (lv_opaque_3_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1445:3: lv_opaque_3_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNoteTypeAccess().getOpaqueEBooleanParserRuleCall_2_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleNoteType2130);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1467:4: ( 'POSITION' '\"' ( (lv_posX_6_0= ruleEInt ) ) ',' ( (lv_posY_8_0= ruleEInt ) ) '\"' )?
            int alt31=2;
            int LA31_0 = input.LA(1);

            if ( (LA31_0==38) ) {
                alt31=1;
            }
            switch (alt31) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1467:6: 'POSITION' '\"' ( (lv_posX_6_0= ruleEInt ) ) ',' ( (lv_posY_8_0= ruleEInt ) ) '\"'
                    {
                    match(input,38,FollowSets000.FOLLOW_38_in_ruleNoteType2143); 

                            createLeafNode(grammarAccess.getNoteTypeAccess().getPOSITIONKeyword_3_0(), null); 
                        
                    match(input,39,FollowSets000.FOLLOW_39_in_ruleNoteType2153); 

                            createLeafNode(grammarAccess.getNoteTypeAccess().getQuotationMarkKeyword_3_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1475:1: ( (lv_posX_6_0= ruleEInt ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1476:1: (lv_posX_6_0= ruleEInt )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1476:1: (lv_posX_6_0= ruleEInt )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1477:3: lv_posX_6_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNoteTypeAccess().getPosXEIntParserRuleCall_3_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleNoteType2174);
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

                    match(input,14,FollowSets000.FOLLOW_14_in_ruleNoteType2184); 

                            createLeafNode(grammarAccess.getNoteTypeAccess().getCommaKeyword_3_3(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1503:1: ( (lv_posY_8_0= ruleEInt ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1504:1: (lv_posY_8_0= ruleEInt )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1504:1: (lv_posY_8_0= ruleEInt )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1505:3: lv_posY_8_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNoteTypeAccess().getPosYEIntParserRuleCall_3_4_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleNoteType2205);
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

                    match(input,39,FollowSets000.FOLLOW_39_in_ruleNoteType2215); 

                            createLeafNode(grammarAccess.getNoteTypeAccess().getQuotationMarkKeyword_3_5(), null); 
                        

                    }
                    break;

            }

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1531:3: ( 'text' ( (lv_text_11_0= ruleEString ) ) )?
            int alt32=2;
            int LA32_0 = input.LA(1);

            if ( (LA32_0==40) ) {
                alt32=1;
            }
            switch (alt32) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1531:5: 'text' ( (lv_text_11_0= ruleEString ) )
                    {
                    match(input,40,FollowSets000.FOLLOW_40_in_ruleNoteType2228); 

                            createLeafNode(grammarAccess.getNoteTypeAccess().getTextKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1535:1: ( (lv_text_11_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1536:1: (lv_text_11_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1536:1: (lv_text_11_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1537:3: lv_text_11_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getNoteTypeAccess().getTextEStringParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleNoteType2249);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1559:4: ( (lv_elementParameter_12_0= ruleElementParameterType ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1560:1: (lv_elementParameter_12_0= ruleElementParameterType )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1560:1: (lv_elementParameter_12_0= ruleElementParameterType )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1561:3: lv_elementParameter_12_0= ruleElementParameterType
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getNoteTypeAccess().getElementParameterElementParameterTypeParserRuleCall_5_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleElementParameterType_in_ruleNoteType2272);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1583:2: ( ',' ( (lv_elementParameter_14_0= ruleElementParameterType ) ) )*
            loop33:
            do {
                int alt33=2;
                int LA33_0 = input.LA(1);

                if ( (LA33_0==14) ) {
                    alt33=1;
                }


                switch (alt33) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1583:4: ',' ( (lv_elementParameter_14_0= ruleElementParameterType ) )
            	    {
            	    match(input,14,FollowSets000.FOLLOW_14_in_ruleNoteType2283); 

            	            createLeafNode(grammarAccess.getNoteTypeAccess().getCommaKeyword_6_0(), null); 
            	        
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1587:1: ( (lv_elementParameter_14_0= ruleElementParameterType ) )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1588:1: (lv_elementParameter_14_0= ruleElementParameterType )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1588:1: (lv_elementParameter_14_0= ruleElementParameterType )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1589:3: lv_elementParameter_14_0= ruleElementParameterType
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getNoteTypeAccess().getElementParameterElementParameterTypeParserRuleCall_6_1_0(), currentNode); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleElementParameterType_in_ruleNoteType2304);
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
            	    break loop33;
                }
            } while (true);

            match(input,13,FollowSets000.FOLLOW_13_in_ruleNoteType2316); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1623:1: entryRuleElementParameterType returns [EObject current=null] : iv_ruleElementParameterType= ruleElementParameterType EOF ;
    public final EObject entryRuleElementParameterType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElementParameterType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1624:2: (iv_ruleElementParameterType= ruleElementParameterType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1625:2: iv_ruleElementParameterType= ruleElementParameterType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getElementParameterTypeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleElementParameterType_in_entryRuleElementParameterType2352);
            iv_ruleElementParameterType=ruleElementParameterType();
            _fsp--;

             current =iv_ruleElementParameterType; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleElementParameterType2362); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1632:1: ruleElementParameterType returns [EObject current=null] : ( () ( ( (lv_name_1_0= ruleEString ) ) ':' ( (lv_value_3_0= ruleEString ) ) ) ) ;
    public final EObject ruleElementParameterType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        AntlrDatatypeRuleToken lv_value_3_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1637:6: ( ( () ( ( (lv_name_1_0= ruleEString ) ) ':' ( (lv_value_3_0= ruleEString ) ) ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1638:1: ( () ( ( (lv_name_1_0= ruleEString ) ) ':' ( (lv_value_3_0= ruleEString ) ) ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1638:1: ( () ( ( (lv_name_1_0= ruleEString ) ) ':' ( (lv_value_3_0= ruleEString ) ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1638:2: () ( ( (lv_name_1_0= ruleEString ) ) ':' ( (lv_value_3_0= ruleEString ) ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1638:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1639:5: 
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1649:2: ( ( (lv_name_1_0= ruleEString ) ) ':' ( (lv_value_3_0= ruleEString ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1649:3: ( (lv_name_1_0= ruleEString ) ) ':' ( (lv_value_3_0= ruleEString ) )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1649:3: ( (lv_name_1_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1650:1: (lv_name_1_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1650:1: (lv_name_1_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1651:3: lv_name_1_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getElementParameterTypeAccess().getNameEStringParserRuleCall_1_0_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleElementParameterType2418);
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

            match(input,41,FollowSets000.FOLLOW_41_in_ruleElementParameterType2428); 

                    createLeafNode(grammarAccess.getElementParameterTypeAccess().getColonKeyword_1_1(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1677:1: ( (lv_value_3_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1678:1: (lv_value_3_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1678:1: (lv_value_3_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1679:3: lv_value_3_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getElementParameterTypeAccess().getValueEStringParserRuleCall_1_2_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleElementParameterType2449);
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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1709:1: entryRuleSchemaElementParameterType returns [EObject current=null] : iv_ruleSchemaElementParameterType= ruleSchemaElementParameterType EOF ;
    public final EObject entryRuleSchemaElementParameterType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleSchemaElementParameterType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1710:2: (iv_ruleSchemaElementParameterType= ruleSchemaElementParameterType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1711:2: iv_ruleSchemaElementParameterType= ruleSchemaElementParameterType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getSchemaElementParameterTypeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleSchemaElementParameterType_in_entryRuleSchemaElementParameterType2486);
            iv_ruleSchemaElementParameterType=ruleSchemaElementParameterType();
            _fsp--;

             current =iv_ruleSchemaElementParameterType; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleSchemaElementParameterType2496); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1718:1: ruleSchemaElementParameterType returns [EObject current=null] : ( () ( ( (lv_name_1_0= ruleEString ) ) '{' ( (lv_elementValue_3_0= ruleElementValueType ) ) ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )* '}' )? ) ;
    public final EObject ruleSchemaElementParameterType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_1_0 = null;

        EObject lv_elementValue_3_0 = null;

        EObject lv_elementValue_5_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1723:6: ( ( () ( ( (lv_name_1_0= ruleEString ) ) '{' ( (lv_elementValue_3_0= ruleElementValueType ) ) ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )* '}' )? ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1724:1: ( () ( ( (lv_name_1_0= ruleEString ) ) '{' ( (lv_elementValue_3_0= ruleElementValueType ) ) ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )* '}' )? )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1724:1: ( () ( ( (lv_name_1_0= ruleEString ) ) '{' ( (lv_elementValue_3_0= ruleElementValueType ) ) ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )* '}' )? )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1724:2: () ( ( (lv_name_1_0= ruleEString ) ) '{' ( (lv_elementValue_3_0= ruleElementValueType ) ) ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )* '}' )?
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1724:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1725:5: 
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1735:2: ( ( (lv_name_1_0= ruleEString ) ) '{' ( (lv_elementValue_3_0= ruleElementValueType ) ) ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )* '}' )?
            int alt35=2;
            int LA35_0 = input.LA(1);

            if ( ((LA35_0>=RULE_STRING && LA35_0<=RULE_ID)) ) {
                alt35=1;
            }
            switch (alt35) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1735:3: ( (lv_name_1_0= ruleEString ) ) '{' ( (lv_elementValue_3_0= ruleElementValueType ) ) ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )* '}'
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1735:3: ( (lv_name_1_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1736:1: (lv_name_1_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1736:1: (lv_name_1_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1737:3: lv_name_1_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSchemaElementParameterTypeAccess().getNameEStringParserRuleCall_1_0_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleSchemaElementParameterType2552);
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

                    match(input,12,FollowSets000.FOLLOW_12_in_ruleSchemaElementParameterType2562); 

                            createLeafNode(grammarAccess.getSchemaElementParameterTypeAccess().getLeftCurlyBracketKeyword_1_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1763:1: ( (lv_elementValue_3_0= ruleElementValueType ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1764:1: (lv_elementValue_3_0= ruleElementValueType )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1764:1: (lv_elementValue_3_0= ruleElementValueType )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1765:3: lv_elementValue_3_0= ruleElementValueType
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getSchemaElementParameterTypeAccess().getElementValueElementValueTypeParserRuleCall_1_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleElementValueType_in_ruleSchemaElementParameterType2583);
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

                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1787:2: ( ',' ( (lv_elementValue_5_0= ruleElementValueType ) ) )*
                    loop34:
                    do {
                        int alt34=2;
                        int LA34_0 = input.LA(1);

                        if ( (LA34_0==14) ) {
                            alt34=1;
                        }


                        switch (alt34) {
                    	case 1 :
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1787:4: ',' ( (lv_elementValue_5_0= ruleElementValueType ) )
                    	    {
                    	    match(input,14,FollowSets000.FOLLOW_14_in_ruleSchemaElementParameterType2594); 

                    	            createLeafNode(grammarAccess.getSchemaElementParameterTypeAccess().getCommaKeyword_1_3_0(), null); 
                    	        
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1791:1: ( (lv_elementValue_5_0= ruleElementValueType ) )
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1792:1: (lv_elementValue_5_0= ruleElementValueType )
                    	    {
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1792:1: (lv_elementValue_5_0= ruleElementValueType )
                    	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1793:3: lv_elementValue_5_0= ruleElementValueType
                    	    {
                    	     
                    	    	        currentNode=createCompositeNode(grammarAccess.getSchemaElementParameterTypeAccess().getElementValueElementValueTypeParserRuleCall_1_3_1_0(), currentNode); 
                    	    	    
                    	    pushFollow(FollowSets000.FOLLOW_ruleElementValueType_in_ruleSchemaElementParameterType2615);
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
                    	    break loop34;
                        }
                    } while (true);

                    match(input,13,FollowSets000.FOLLOW_13_in_ruleSchemaElementParameterType2627); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1827:1: entryRuleElementValueType returns [EObject current=null] : iv_ruleElementValueType= ruleElementValueType EOF ;
    public final EObject entryRuleElementValueType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleElementValueType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1828:2: (iv_ruleElementValueType= ruleElementValueType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1829:2: iv_ruleElementValueType= ruleElementValueType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getElementValueTypeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleElementValueType_in_entryRuleElementValueType2665);
            iv_ruleElementValueType=ruleElementValueType();
            _fsp--;

             current =iv_ruleElementValueType; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleElementValueType2675); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1836:1: ruleElementValueType returns [EObject current=null] : ( () ( 'NAME:' ( (lv_elementRef_2_0= ruleEString ) ) ) ( 'VALUE:' ( (lv_value_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? ) ;
    public final EObject ruleElementValueType() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_elementRef_2_0 = null;

        AntlrDatatypeRuleToken lv_value_4_0 = null;

        AntlrDatatypeRuleToken lv_type_6_0 = null;


         EObject temp=null; setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1841:6: ( ( () ( 'NAME:' ( (lv_elementRef_2_0= ruleEString ) ) ) ( 'VALUE:' ( (lv_value_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1842:1: ( () ( 'NAME:' ( (lv_elementRef_2_0= ruleEString ) ) ) ( 'VALUE:' ( (lv_value_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1842:1: ( () ( 'NAME:' ( (lv_elementRef_2_0= ruleEString ) ) ) ( 'VALUE:' ( (lv_value_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )? )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1842:2: () ( 'NAME:' ( (lv_elementRef_2_0= ruleEString ) ) ) ( 'VALUE:' ( (lv_value_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )?
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1842:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1843:5: 
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1853:2: ( 'NAME:' ( (lv_elementRef_2_0= ruleEString ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1853:4: 'NAME:' ( (lv_elementRef_2_0= ruleEString ) )
            {
            match(input,18,FollowSets000.FOLLOW_18_in_ruleElementValueType2720); 

                    createLeafNode(grammarAccess.getElementValueTypeAccess().getNAMEKeyword_1_0(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1857:1: ( (lv_elementRef_2_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1858:1: (lv_elementRef_2_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1858:1: (lv_elementRef_2_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1859:3: lv_elementRef_2_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getElementValueTypeAccess().getElementRefEStringParserRuleCall_1_1_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleElementValueType2741);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1881:3: ( 'VALUE:' ( (lv_value_4_0= ruleEString ) ) )?
            int alt36=2;
            int LA36_0 = input.LA(1);

            if ( (LA36_0==24) ) {
                alt36=1;
            }
            switch (alt36) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1881:5: 'VALUE:' ( (lv_value_4_0= ruleEString ) )
                    {
                    match(input,24,FollowSets000.FOLLOW_24_in_ruleElementValueType2753); 

                            createLeafNode(grammarAccess.getElementValueTypeAccess().getVALUEKeyword_2_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1885:1: ( (lv_value_4_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1886:1: (lv_value_4_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1886:1: (lv_value_4_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1887:3: lv_value_4_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getElementValueTypeAccess().getValueEStringParserRuleCall_2_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleElementValueType2774);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1909:4: ( 'TYPE:' ( (lv_type_6_0= ruleEString ) ) )?
            int alt37=2;
            int LA37_0 = input.LA(1);

            if ( (LA37_0==17) ) {
                alt37=1;
            }
            switch (alt37) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1909:6: 'TYPE:' ( (lv_type_6_0= ruleEString ) )
                    {
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleElementValueType2787); 

                            createLeafNode(grammarAccess.getElementValueTypeAccess().getTYPEKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1913:1: ( (lv_type_6_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1914:1: (lv_type_6_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1914:1: (lv_type_6_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1915:3: lv_type_6_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getElementValueTypeAccess().getTypeEStringParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleElementValueType2808);
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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1945:1: entryRuleMetadataType returns [EObject current=null] : iv_ruleMetadataType= ruleMetadataType EOF ;
    public final EObject entryRuleMetadataType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMetadataType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1946:2: (iv_ruleMetadataType= ruleMetadataType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1947:2: iv_ruleMetadataType= ruleMetadataType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getMetadataTypeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleMetadataType_in_entryRuleMetadataType2846);
            iv_ruleMetadataType=ruleMetadataType();
            _fsp--;

             current =iv_ruleMetadataType; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleMetadataType2856); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1954:1: ruleMetadataType returns [EObject current=null] : ( () 'addSchema' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_connector_6_0= ruleEString ) ) )? ( 'LABEL' ( (lv_label_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'SOURCE:' ( (lv_source_12_0= ruleEString ) ) )? ( (lv_column_13_0= ruleColumnType ) )* '}' ) ;
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
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1959:6: ( ( () 'addSchema' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_connector_6_0= ruleEString ) ) )? ( 'LABEL' ( (lv_label_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'SOURCE:' ( (lv_source_12_0= ruleEString ) ) )? ( (lv_column_13_0= ruleColumnType ) )* '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1960:1: ( () 'addSchema' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_connector_6_0= ruleEString ) ) )? ( 'LABEL' ( (lv_label_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'SOURCE:' ( (lv_source_12_0= ruleEString ) ) )? ( (lv_column_13_0= ruleColumnType ) )* '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1960:1: ( () 'addSchema' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_connector_6_0= ruleEString ) ) )? ( 'LABEL' ( (lv_label_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'SOURCE:' ( (lv_source_12_0= ruleEString ) ) )? ( (lv_column_13_0= ruleColumnType ) )* '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1960:2: () 'addSchema' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )? ( 'TYPE:' ( (lv_connector_6_0= ruleEString ) ) )? ( 'LABEL' ( (lv_label_8_0= ruleEString ) ) )? ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )? ( 'SOURCE:' ( (lv_source_12_0= ruleEString ) ) )? ( (lv_column_13_0= ruleColumnType ) )* '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1960:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1961:5: 
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

            match(input,42,FollowSets000.FOLLOW_42_in_ruleMetadataType2900); 

                    createLeafNode(grammarAccess.getMetadataTypeAccess().getAddSchemaKeyword_1(), null); 
                
            match(input,12,FollowSets000.FOLLOW_12_in_ruleMetadataType2910); 

                    createLeafNode(grammarAccess.getMetadataTypeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1979:1: ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )?
            int alt38=2;
            int LA38_0 = input.LA(1);

            if ( (LA38_0==18) ) {
                alt38=1;
            }
            switch (alt38) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1979:3: 'NAME:' ( (lv_name_4_0= ruleEString ) )
                    {
                    match(input,18,FollowSets000.FOLLOW_18_in_ruleMetadataType2921); 

                            createLeafNode(grammarAccess.getMetadataTypeAccess().getNAMEKeyword_3_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1983:1: ( (lv_name_4_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1984:1: (lv_name_4_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1984:1: (lv_name_4_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:1985:3: lv_name_4_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMetadataTypeAccess().getNameEStringParserRuleCall_3_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleMetadataType2942);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2007:4: ( 'TYPE:' ( (lv_connector_6_0= ruleEString ) ) )?
            int alt39=2;
            int LA39_0 = input.LA(1);

            if ( (LA39_0==17) ) {
                alt39=1;
            }
            switch (alt39) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2007:6: 'TYPE:' ( (lv_connector_6_0= ruleEString ) )
                    {
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleMetadataType2955); 

                            createLeafNode(grammarAccess.getMetadataTypeAccess().getTYPEKeyword_4_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2011:1: ( (lv_connector_6_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2012:1: (lv_connector_6_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2012:1: (lv_connector_6_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2013:3: lv_connector_6_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMetadataTypeAccess().getConnectorEStringParserRuleCall_4_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleMetadataType2976);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2035:4: ( 'LABEL' ( (lv_label_8_0= ruleEString ) ) )?
            int alt40=2;
            int LA40_0 = input.LA(1);

            if ( (LA40_0==43) ) {
                alt40=1;
            }
            switch (alt40) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2035:6: 'LABEL' ( (lv_label_8_0= ruleEString ) )
                    {
                    match(input,43,FollowSets000.FOLLOW_43_in_ruleMetadataType2989); 

                            createLeafNode(grammarAccess.getMetadataTypeAccess().getLABELKeyword_5_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2039:1: ( (lv_label_8_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2040:1: (lv_label_8_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2040:1: (lv_label_8_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2041:3: lv_label_8_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMetadataTypeAccess().getLabelEStringParserRuleCall_5_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleMetadataType3010);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2063:4: ( 'COMMENT:' ( (lv_comment_10_0= ruleEString ) ) )?
            int alt41=2;
            int LA41_0 = input.LA(1);

            if ( (LA41_0==25) ) {
                alt41=1;
            }
            switch (alt41) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2063:6: 'COMMENT:' ( (lv_comment_10_0= ruleEString ) )
                    {
                    match(input,25,FollowSets000.FOLLOW_25_in_ruleMetadataType3023); 

                            createLeafNode(grammarAccess.getMetadataTypeAccess().getCOMMENTKeyword_6_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2067:1: ( (lv_comment_10_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2068:1: (lv_comment_10_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2068:1: (lv_comment_10_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2069:3: lv_comment_10_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMetadataTypeAccess().getCommentEStringParserRuleCall_6_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleMetadataType3044);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2091:4: ( 'SOURCE:' ( (lv_source_12_0= ruleEString ) ) )?
            int alt42=2;
            int LA42_0 = input.LA(1);

            if ( (LA42_0==34) ) {
                alt42=1;
            }
            switch (alt42) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2091:6: 'SOURCE:' ( (lv_source_12_0= ruleEString ) )
                    {
                    match(input,34,FollowSets000.FOLLOW_34_in_ruleMetadataType3057); 

                            createLeafNode(grammarAccess.getMetadataTypeAccess().getSOURCEKeyword_7_0(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2095:1: ( (lv_source_12_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2096:1: (lv_source_12_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2096:1: (lv_source_12_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2097:3: lv_source_12_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getMetadataTypeAccess().getSourceEStringParserRuleCall_7_1_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleMetadataType3078);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2119:4: ( (lv_column_13_0= ruleColumnType ) )*
            loop43:
            do {
                int alt43=2;
                int LA43_0 = input.LA(1);

                if ( (LA43_0==44) ) {
                    alt43=1;
                }


                switch (alt43) {
            	case 1 :
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2120:1: (lv_column_13_0= ruleColumnType )
            	    {
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2120:1: (lv_column_13_0= ruleColumnType )
            	    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2121:3: lv_column_13_0= ruleColumnType
            	    {
            	     
            	    	        currentNode=createCompositeNode(grammarAccess.getMetadataTypeAccess().getColumnColumnTypeParserRuleCall_8_0(), currentNode); 
            	    	    
            	    pushFollow(FollowSets000.FOLLOW_ruleColumnType_in_ruleMetadataType3101);
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
            	    break loop43;
                }
            } while (true);

            match(input,13,FollowSets000.FOLLOW_13_in_ruleMetadataType3112); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2155:1: entryRuleColumnType returns [EObject current=null] : iv_ruleColumnType= ruleColumnType EOF ;
    public final EObject entryRuleColumnType() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleColumnType = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2156:2: (iv_ruleColumnType= ruleColumnType EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2157:2: iv_ruleColumnType= ruleColumnType EOF
            {
             currentNode = createCompositeNode(grammarAccess.getColumnTypeRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleColumnType_in_entryRuleColumnType3148);
            iv_ruleColumnType=ruleColumnType();
            _fsp--;

             current =iv_ruleColumnType; 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleColumnType3158); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2164:1: ruleColumnType returns [EObject current=null] : ( () 'addColumn' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'KEY:' ( (lv_key_10_0= ruleEBoolean ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_13_0= ruleEBoolean ) ) )? ( ',' 'DEFAULTVALUE:' ( (lv_defaultValue_16_0= ruleEString ) ) )? ( ',' 'LENGTH:' ( (lv_length_19_0= ruleEInt ) ) )? ( ',' 'COMMENT:' ( (lv_comment_22_0= ruleEString ) ) )? ( ',' 'originalDbColumnName' ( (lv_originalDbColumnName_25_0= ruleEString ) ) )? ( ',' 'PATTREN:' ( (lv_pattern_28_0= ruleEString ) ) )? ( ',' 'PRECISION:' ( (lv_precision_31_0= ruleEInt ) ) )? ( ',' 'SOURCETYPE:' ( (lv_sourceType_34_0= ruleEString ) ) )? '}' ) ;
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
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2169:6: ( ( () 'addColumn' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'KEY:' ( (lv_key_10_0= ruleEBoolean ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_13_0= ruleEBoolean ) ) )? ( ',' 'DEFAULTVALUE:' ( (lv_defaultValue_16_0= ruleEString ) ) )? ( ',' 'LENGTH:' ( (lv_length_19_0= ruleEInt ) ) )? ( ',' 'COMMENT:' ( (lv_comment_22_0= ruleEString ) ) )? ( ',' 'originalDbColumnName' ( (lv_originalDbColumnName_25_0= ruleEString ) ) )? ( ',' 'PATTREN:' ( (lv_pattern_28_0= ruleEString ) ) )? ( ',' 'PRECISION:' ( (lv_precision_31_0= ruleEInt ) ) )? ( ',' 'SOURCETYPE:' ( (lv_sourceType_34_0= ruleEString ) ) )? '}' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2170:1: ( () 'addColumn' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'KEY:' ( (lv_key_10_0= ruleEBoolean ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_13_0= ruleEBoolean ) ) )? ( ',' 'DEFAULTVALUE:' ( (lv_defaultValue_16_0= ruleEString ) ) )? ( ',' 'LENGTH:' ( (lv_length_19_0= ruleEInt ) ) )? ( ',' 'COMMENT:' ( (lv_comment_22_0= ruleEString ) ) )? ( ',' 'originalDbColumnName' ( (lv_originalDbColumnName_25_0= ruleEString ) ) )? ( ',' 'PATTREN:' ( (lv_pattern_28_0= ruleEString ) ) )? ( ',' 'PRECISION:' ( (lv_precision_31_0= ruleEInt ) ) )? ( ',' 'SOURCETYPE:' ( (lv_sourceType_34_0= ruleEString ) ) )? '}' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2170:1: ( () 'addColumn' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'KEY:' ( (lv_key_10_0= ruleEBoolean ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_13_0= ruleEBoolean ) ) )? ( ',' 'DEFAULTVALUE:' ( (lv_defaultValue_16_0= ruleEString ) ) )? ( ',' 'LENGTH:' ( (lv_length_19_0= ruleEInt ) ) )? ( ',' 'COMMENT:' ( (lv_comment_22_0= ruleEString ) ) )? ( ',' 'originalDbColumnName' ( (lv_originalDbColumnName_25_0= ruleEString ) ) )? ( ',' 'PATTREN:' ( (lv_pattern_28_0= ruleEString ) ) )? ( ',' 'PRECISION:' ( (lv_precision_31_0= ruleEInt ) ) )? ( ',' 'SOURCETYPE:' ( (lv_sourceType_34_0= ruleEString ) ) )? '}' )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2170:2: () 'addColumn' '{' ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) ) ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )? ( ',' 'KEY:' ( (lv_key_10_0= ruleEBoolean ) ) )? ( ',' 'NULLABLE:' ( (lv_nullable_13_0= ruleEBoolean ) ) )? ( ',' 'DEFAULTVALUE:' ( (lv_defaultValue_16_0= ruleEString ) ) )? ( ',' 'LENGTH:' ( (lv_length_19_0= ruleEInt ) ) )? ( ',' 'COMMENT:' ( (lv_comment_22_0= ruleEString ) ) )? ( ',' 'originalDbColumnName' ( (lv_originalDbColumnName_25_0= ruleEString ) ) )? ( ',' 'PATTREN:' ( (lv_pattern_28_0= ruleEString ) ) )? ( ',' 'PRECISION:' ( (lv_precision_31_0= ruleEInt ) ) )? ( ',' 'SOURCETYPE:' ( (lv_sourceType_34_0= ruleEString ) ) )? '}'
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2170:2: ()
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2171:5: 
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

            match(input,44,FollowSets000.FOLLOW_44_in_ruleColumnType3202); 

                    createLeafNode(grammarAccess.getColumnTypeAccess().getAddColumnKeyword_1(), null); 
                
            match(input,12,FollowSets000.FOLLOW_12_in_ruleColumnType3212); 

                    createLeafNode(grammarAccess.getColumnTypeAccess().getLeftCurlyBracketKeyword_2(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2189:1: ( 'NAME:' ( (lv_name_4_0= ruleEString ) ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2189:3: 'NAME:' ( (lv_name_4_0= ruleEString ) )
            {
            match(input,18,FollowSets000.FOLLOW_18_in_ruleColumnType3223); 

                    createLeafNode(grammarAccess.getColumnTypeAccess().getNAMEKeyword_3_0(), null); 
                
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2193:1: ( (lv_name_4_0= ruleEString ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2194:1: (lv_name_4_0= ruleEString )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2194:1: (lv_name_4_0= ruleEString )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2195:3: lv_name_4_0= ruleEString
            {
             
            	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getNameEStringParserRuleCall_3_1_0(), currentNode); 
            	    
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleColumnType3244);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2217:3: ( ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) ) )?
            int alt44=2;
            int LA44_0 = input.LA(1);

            if ( (LA44_0==14) ) {
                int LA44_1 = input.LA(2);

                if ( (LA44_1==17) ) {
                    alt44=1;
                }
            }
            switch (alt44) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2217:5: ',' 'TYPE:' ( (lv_type_7_0= ruleEString ) )
                    {
                    match(input,14,FollowSets000.FOLLOW_14_in_ruleColumnType3256); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_4_0(), null); 
                        
                    match(input,17,FollowSets000.FOLLOW_17_in_ruleColumnType3266); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getTYPEKeyword_4_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2225:1: ( (lv_type_7_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2226:1: (lv_type_7_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2226:1: (lv_type_7_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2227:3: lv_type_7_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getTypeEStringParserRuleCall_4_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleColumnType3287);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2249:4: ( ',' 'KEY:' ( (lv_key_10_0= ruleEBoolean ) ) )?
            int alt45=2;
            int LA45_0 = input.LA(1);

            if ( (LA45_0==14) ) {
                int LA45_1 = input.LA(2);

                if ( (LA45_1==45) ) {
                    alt45=1;
                }
            }
            switch (alt45) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2249:6: ',' 'KEY:' ( (lv_key_10_0= ruleEBoolean ) )
                    {
                    match(input,14,FollowSets000.FOLLOW_14_in_ruleColumnType3300); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_5_0(), null); 
                        
                    match(input,45,FollowSets000.FOLLOW_45_in_ruleColumnType3310); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getKEYKeyword_5_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2257:1: ( (lv_key_10_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2258:1: (lv_key_10_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2258:1: (lv_key_10_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2259:3: lv_key_10_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getKeyEBooleanParserRuleCall_5_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleColumnType3331);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2281:4: ( ',' 'NULLABLE:' ( (lv_nullable_13_0= ruleEBoolean ) ) )?
            int alt46=2;
            int LA46_0 = input.LA(1);

            if ( (LA46_0==14) ) {
                int LA46_1 = input.LA(2);

                if ( (LA46_1==46) ) {
                    alt46=1;
                }
            }
            switch (alt46) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2281:6: ',' 'NULLABLE:' ( (lv_nullable_13_0= ruleEBoolean ) )
                    {
                    match(input,14,FollowSets000.FOLLOW_14_in_ruleColumnType3344); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_6_0(), null); 
                        
                    match(input,46,FollowSets000.FOLLOW_46_in_ruleColumnType3354); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getNULLABLEKeyword_6_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2289:1: ( (lv_nullable_13_0= ruleEBoolean ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2290:1: (lv_nullable_13_0= ruleEBoolean )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2290:1: (lv_nullable_13_0= ruleEBoolean )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2291:3: lv_nullable_13_0= ruleEBoolean
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getNullableEBooleanParserRuleCall_6_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_ruleColumnType3375);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2313:4: ( ',' 'DEFAULTVALUE:' ( (lv_defaultValue_16_0= ruleEString ) ) )?
            int alt47=2;
            int LA47_0 = input.LA(1);

            if ( (LA47_0==14) ) {
                int LA47_1 = input.LA(2);

                if ( (LA47_1==47) ) {
                    alt47=1;
                }
            }
            switch (alt47) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2313:6: ',' 'DEFAULTVALUE:' ( (lv_defaultValue_16_0= ruleEString ) )
                    {
                    match(input,14,FollowSets000.FOLLOW_14_in_ruleColumnType3388); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_7_0(), null); 
                        
                    match(input,47,FollowSets000.FOLLOW_47_in_ruleColumnType3398); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getDEFAULTVALUEKeyword_7_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2321:1: ( (lv_defaultValue_16_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2322:1: (lv_defaultValue_16_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2322:1: (lv_defaultValue_16_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2323:3: lv_defaultValue_16_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getDefaultValueEStringParserRuleCall_7_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleColumnType3419);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2345:4: ( ',' 'LENGTH:' ( (lv_length_19_0= ruleEInt ) ) )?
            int alt48=2;
            int LA48_0 = input.LA(1);

            if ( (LA48_0==14) ) {
                int LA48_1 = input.LA(2);

                if ( (LA48_1==48) ) {
                    alt48=1;
                }
            }
            switch (alt48) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2345:6: ',' 'LENGTH:' ( (lv_length_19_0= ruleEInt ) )
                    {
                    match(input,14,FollowSets000.FOLLOW_14_in_ruleColumnType3432); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_8_0(), null); 
                        
                    match(input,48,FollowSets000.FOLLOW_48_in_ruleColumnType3442); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getLENGTHKeyword_8_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2353:1: ( (lv_length_19_0= ruleEInt ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2354:1: (lv_length_19_0= ruleEInt )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2354:1: (lv_length_19_0= ruleEInt )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2355:3: lv_length_19_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getLengthEIntParserRuleCall_8_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleColumnType3463);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2377:4: ( ',' 'COMMENT:' ( (lv_comment_22_0= ruleEString ) ) )?
            int alt49=2;
            int LA49_0 = input.LA(1);

            if ( (LA49_0==14) ) {
                int LA49_1 = input.LA(2);

                if ( (LA49_1==25) ) {
                    alt49=1;
                }
            }
            switch (alt49) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2377:6: ',' 'COMMENT:' ( (lv_comment_22_0= ruleEString ) )
                    {
                    match(input,14,FollowSets000.FOLLOW_14_in_ruleColumnType3476); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_9_0(), null); 
                        
                    match(input,25,FollowSets000.FOLLOW_25_in_ruleColumnType3486); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCOMMENTKeyword_9_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2385:1: ( (lv_comment_22_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2386:1: (lv_comment_22_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2386:1: (lv_comment_22_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2387:3: lv_comment_22_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getCommentEStringParserRuleCall_9_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleColumnType3507);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2409:4: ( ',' 'originalDbColumnName' ( (lv_originalDbColumnName_25_0= ruleEString ) ) )?
            int alt50=2;
            int LA50_0 = input.LA(1);

            if ( (LA50_0==14) ) {
                int LA50_1 = input.LA(2);

                if ( (LA50_1==49) ) {
                    alt50=1;
                }
            }
            switch (alt50) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2409:6: ',' 'originalDbColumnName' ( (lv_originalDbColumnName_25_0= ruleEString ) )
                    {
                    match(input,14,FollowSets000.FOLLOW_14_in_ruleColumnType3520); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_10_0(), null); 
                        
                    match(input,49,FollowSets000.FOLLOW_49_in_ruleColumnType3530); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getOriginalDbColumnNameKeyword_10_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2417:1: ( (lv_originalDbColumnName_25_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2418:1: (lv_originalDbColumnName_25_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2418:1: (lv_originalDbColumnName_25_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2419:3: lv_originalDbColumnName_25_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getOriginalDbColumnNameEStringParserRuleCall_10_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleColumnType3551);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2441:4: ( ',' 'PATTREN:' ( (lv_pattern_28_0= ruleEString ) ) )?
            int alt51=2;
            int LA51_0 = input.LA(1);

            if ( (LA51_0==14) ) {
                int LA51_1 = input.LA(2);

                if ( (LA51_1==50) ) {
                    alt51=1;
                }
            }
            switch (alt51) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2441:6: ',' 'PATTREN:' ( (lv_pattern_28_0= ruleEString ) )
                    {
                    match(input,14,FollowSets000.FOLLOW_14_in_ruleColumnType3564); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_11_0(), null); 
                        
                    match(input,50,FollowSets000.FOLLOW_50_in_ruleColumnType3574); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getPATTRENKeyword_11_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2449:1: ( (lv_pattern_28_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2450:1: (lv_pattern_28_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2450:1: (lv_pattern_28_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2451:3: lv_pattern_28_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getPatternEStringParserRuleCall_11_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleColumnType3595);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2473:4: ( ',' 'PRECISION:' ( (lv_precision_31_0= ruleEInt ) ) )?
            int alt52=2;
            int LA52_0 = input.LA(1);

            if ( (LA52_0==14) ) {
                int LA52_1 = input.LA(2);

                if ( (LA52_1==51) ) {
                    alt52=1;
                }
            }
            switch (alt52) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2473:6: ',' 'PRECISION:' ( (lv_precision_31_0= ruleEInt ) )
                    {
                    match(input,14,FollowSets000.FOLLOW_14_in_ruleColumnType3608); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_12_0(), null); 
                        
                    match(input,51,FollowSets000.FOLLOW_51_in_ruleColumnType3618); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getPRECISIONKeyword_12_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2481:1: ( (lv_precision_31_0= ruleEInt ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2482:1: (lv_precision_31_0= ruleEInt )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2482:1: (lv_precision_31_0= ruleEInt )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2483:3: lv_precision_31_0= ruleEInt
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getPrecisionEIntParserRuleCall_12_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEInt_in_ruleColumnType3639);
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

            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2505:4: ( ',' 'SOURCETYPE:' ( (lv_sourceType_34_0= ruleEString ) ) )?
            int alt53=2;
            int LA53_0 = input.LA(1);

            if ( (LA53_0==14) ) {
                alt53=1;
            }
            switch (alt53) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2505:6: ',' 'SOURCETYPE:' ( (lv_sourceType_34_0= ruleEString ) )
                    {
                    match(input,14,FollowSets000.FOLLOW_14_in_ruleColumnType3652); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getCommaKeyword_13_0(), null); 
                        
                    match(input,52,FollowSets000.FOLLOW_52_in_ruleColumnType3662); 

                            createLeafNode(grammarAccess.getColumnTypeAccess().getSOURCETYPEKeyword_13_1(), null); 
                        
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2513:1: ( (lv_sourceType_34_0= ruleEString ) )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2514:1: (lv_sourceType_34_0= ruleEString )
                    {
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2514:1: (lv_sourceType_34_0= ruleEString )
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2515:3: lv_sourceType_34_0= ruleEString
                    {
                     
                    	        currentNode=createCompositeNode(grammarAccess.getColumnTypeAccess().getSourceTypeEStringParserRuleCall_13_2_0(), currentNode); 
                    	    
                    pushFollow(FollowSets000.FOLLOW_ruleEString_in_ruleColumnType3683);
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

            match(input,13,FollowSets000.FOLLOW_13_in_ruleColumnType3695); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2549:1: entryRuleEString returns [String current=null] : iv_ruleEString= ruleEString EOF ;
    public final String entryRuleEString() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEString = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2550:2: (iv_ruleEString= ruleEString EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2551:2: iv_ruleEString= ruleEString EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEStringRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEString_in_entryRuleEString3732);
            iv_ruleEString=ruleEString();
            _fsp--;

             current =iv_ruleEString.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEString3743); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2558:1: ruleEString returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) ;
    public final AntlrDatatypeRuleToken ruleEString() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_STRING_0=null;
        Token this_ID_1=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2563:6: ( (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2564:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2564:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )
            int alt54=2;
            int LA54_0 = input.LA(1);

            if ( (LA54_0==RULE_STRING) ) {
                alt54=1;
            }
            else if ( (LA54_0==RULE_ID) ) {
                alt54=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("2564:1: (this_STRING_0= RULE_STRING | this_ID_1= RULE_ID )", 54, 0, input);

                throw nvae;
            }
            switch (alt54) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2564:6: this_STRING_0= RULE_STRING
                    {
                    this_STRING_0=(Token)input.LT(1);
                    match(input,RULE_STRING,FollowSets000.FOLLOW_RULE_STRING_in_ruleEString3783); 

                    		current.merge(this_STRING_0);
                        
                     
                        createLeafNode(grammarAccess.getEStringAccess().getSTRINGTerminalRuleCall_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2572:10: this_ID_1= RULE_ID
                    {
                    this_ID_1=(Token)input.LT(1);
                    match(input,RULE_ID,FollowSets000.FOLLOW_RULE_ID_in_ruleEString3809); 

                    		current.merge(this_ID_1);
                        
                     
                        createLeafNode(grammarAccess.getEStringAccess().getIDTerminalRuleCall_1(), null); 
                        

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2587:1: entryRuleEInt returns [String current=null] : iv_ruleEInt= ruleEInt EOF ;
    public final String entryRuleEInt() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEInt = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2588:2: (iv_ruleEInt= ruleEInt EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2589:2: iv_ruleEInt= ruleEInt EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEIntRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEInt_in_entryRuleEInt3855);
            iv_ruleEInt=ruleEInt();
            _fsp--;

             current =iv_ruleEInt.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEInt3866); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2596:1: ruleEInt returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : ( (kw= '-' )? this_INT_1= RULE_INT ) ;
    public final AntlrDatatypeRuleToken ruleEInt() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;
        Token this_INT_1=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2601:6: ( ( (kw= '-' )? this_INT_1= RULE_INT ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2602:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2602:1: ( (kw= '-' )? this_INT_1= RULE_INT )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2602:2: (kw= '-' )? this_INT_1= RULE_INT
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2602:2: (kw= '-' )?
            int alt55=2;
            int LA55_0 = input.LA(1);

            if ( (LA55_0==53) ) {
                alt55=1;
            }
            switch (alt55) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2603:2: kw= '-'
                    {
                    kw=(Token)input.LT(1);
                    match(input,53,FollowSets000.FOLLOW_53_in_ruleEInt3905); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getEIntAccess().getHyphenMinusKeyword_0(), null); 
                        

                    }
                    break;

            }

            this_INT_1=(Token)input.LT(1);
            match(input,RULE_INT,FollowSets000.FOLLOW_RULE_INT_in_ruleEInt3922); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2623:1: entryRuleEBoolean returns [String current=null] : iv_ruleEBoolean= ruleEBoolean EOF ;
    public final String entryRuleEBoolean() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleEBoolean = null;


        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2624:2: (iv_ruleEBoolean= ruleEBoolean EOF )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2625:2: iv_ruleEBoolean= ruleEBoolean EOF
            {
             currentNode = createCompositeNode(grammarAccess.getEBooleanRule(), currentNode); 
            pushFollow(FollowSets000.FOLLOW_ruleEBoolean_in_entryRuleEBoolean3968);
            iv_ruleEBoolean=ruleEBoolean();
            _fsp--;

             current =iv_ruleEBoolean.getText(); 
            match(input,EOF,FollowSets000.FOLLOW_EOF_in_entryRuleEBoolean3979); 

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
    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2632:1: ruleEBoolean returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'true' | kw= 'false' ) ;
    public final AntlrDatatypeRuleToken ruleEBoolean() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         setCurrentLookahead(); resetLookahead(); 
            
        try {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2637:6: ( (kw= 'true' | kw= 'false' ) )
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2638:1: (kw= 'true' | kw= 'false' )
            {
            // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2638:1: (kw= 'true' | kw= 'false' )
            int alt56=2;
            int LA56_0 = input.LA(1);

            if ( (LA56_0==54) ) {
                alt56=1;
            }
            else if ( (LA56_0==55) ) {
                alt56=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("2638:1: (kw= 'true' | kw= 'false' )", 56, 0, input);

                throw nvae;
            }
            switch (alt56) {
                case 1 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2639:2: kw= 'true'
                    {
                    kw=(Token)input.LT(1);
                    match(input,54,FollowSets000.FOLLOW_54_in_ruleEBoolean4017); 

                            current.merge(kw);
                            createLeafNode(grammarAccess.getEBooleanAccess().getTrueKeyword_0(), null); 
                        

                    }
                    break;
                case 2 :
                    // ../org.talend.metalanguage.job/src-gen/org/talend/metalanguage/job/parser/antlr/internal/InternalJob.g:2646:2: kw= 'false'
                    {
                    kw=(Token)input.LT(1);
                    match(input,55,FollowSets000.FOLLOW_55_in_ruleEBoolean4036); 

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


 

    
    private static class FollowSets000 {
        public static final BitSet FOLLOW_ruleProcessType_in_entryRuleProcessType75 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleProcessType85 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_11_in_ruleProcessType130 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleProcessType140 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleParametersType_in_ruleProcessType161 = new BitSet(new long[]{0x0000000000002000L});
        public static final BitSet FOLLOW_13_in_ruleProcessType171 = new BitSet(new long[]{0x0000001020208002L});
        public static final BitSet FOLLOW_ruleNodeType_in_ruleProcessType194 = new BitSet(new long[]{0x0000001020208002L});
        public static final BitSet FOLLOW_ruleConnectionType_in_ruleProcessType216 = new BitSet(new long[]{0x0000001020200002L});
        public static final BitSet FOLLOW_ruleNoteType_in_ruleProcessType238 = new BitSet(new long[]{0x0000001000200002L});
        public static final BitSet FOLLOW_ruleContextType_in_ruleProcessType260 = new BitSet(new long[]{0x0000000000200002L});
        public static final BitSet FOLLOW_ruleParametersType_in_entryRuleParametersType297 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleParametersType307 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleElementParameterType_in_ruleParametersType353 = new BitSet(new long[]{0x0000000000004002L});
        public static final BitSet FOLLOW_14_in_ruleParametersType364 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleElementParameterType_in_ruleParametersType385 = new BitSet(new long[]{0x0000000000004002L});
        public static final BitSet FOLLOW_ruleNodeType_in_entryRuleNodeType425 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleNodeType435 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_15_in_ruleNodeType470 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleNodeType480 = new BitSet(new long[]{0x0000000000010000L});
        public static final BitSet FOLLOW_16_in_ruleNodeType490 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleNodeType500 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleNodeType511 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleNodeType532 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleNodeType542 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleNodeType554 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleUniqueNameType_in_ruleNodeType575 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleNodeType585 = new BitSet(new long[]{0x0000000000080000L});
        public static final BitSet FOLLOW_19_in_ruleNodeType597 = new BitSet(new long[]{0x0020000000000040L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleNodeType619 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleNodeType629 = new BitSet(new long[]{0x0020000000000040L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleNodeType650 = new BitSet(new long[]{0x0000000000002000L});
        public static final BitSet FOLLOW_13_in_ruleNodeType662 = new BitSet(new long[]{0x0000040000100000L});
        public static final BitSet FOLLOW_ruleMetadataType_in_ruleNodeType683 = new BitSet(new long[]{0x0000040000100000L});
        public static final BitSet FOLLOW_20_in_ruleNodeType694 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleNodeType704 = new BitSet(new long[]{0x0000000000006030L});
        public static final BitSet FOLLOW_ruleSchemaElementParameterType_in_ruleNodeType727 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_ruleElementParameterType_in_ruleNodeType746 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleNodeType760 = new BitSet(new long[]{0x0000000000006030L});
        public static final BitSet FOLLOW_ruleSchemaElementParameterType_in_ruleNodeType783 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_ruleElementParameterType_in_ruleNodeType802 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleNodeType817 = new BitSet(new long[]{0x0000040000002000L});
        public static final BitSet FOLLOW_ruleMetadataType_in_ruleNodeType838 = new BitSet(new long[]{0x0000040000002000L});
        public static final BitSet FOLLOW_13_in_ruleNodeType849 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleContextType_in_entryRuleContextType885 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleContextType895 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_21_in_ruleContextType939 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleContextType949 = new BitSet(new long[]{0x0000000000C42000L});
        public static final BitSet FOLLOW_18_in_ruleContextType960 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleContextType981 = new BitSet(new long[]{0x0000000000C02000L});
        public static final BitSet FOLLOW_22_in_ruleContextType994 = new BitSet(new long[]{0x00C0000000000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleContextType1015 = new BitSet(new long[]{0x0000000000802000L});
        public static final BitSet FOLLOW_ruleContextParameterType_in_ruleContextType1039 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleContextType1050 = new BitSet(new long[]{0x0000000000800000L});
        public static final BitSet FOLLOW_ruleContextParameterType_in_ruleContextType1071 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleContextType1085 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleContextParameterType_in_entryRuleContextParameterType1121 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleContextParameterType1131 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_23_in_ruleContextParameterType1175 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleContextParameterType1185 = new BitSet(new long[]{0x000000001F062000L});
        public static final BitSet FOLLOW_18_in_ruleContextParameterType1196 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleContextParameterType1217 = new BitSet(new long[]{0x000000001F022000L});
        public static final BitSet FOLLOW_17_in_ruleContextParameterType1230 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleContextParameterType1251 = new BitSet(new long[]{0x000000001F002000L});
        public static final BitSet FOLLOW_24_in_ruleContextParameterType1264 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleContextParameterType1285 = new BitSet(new long[]{0x000000001E002000L});
        public static final BitSet FOLLOW_25_in_ruleContextParameterType1298 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleContextParameterType1319 = new BitSet(new long[]{0x000000001C002000L});
        public static final BitSet FOLLOW_26_in_ruleContextParameterType1332 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleContextParameterType1353 = new BitSet(new long[]{0x0000000018002000L});
        public static final BitSet FOLLOW_27_in_ruleContextParameterType1366 = new BitSet(new long[]{0x00C0000000000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleContextParameterType1387 = new BitSet(new long[]{0x0000000010002000L});
        public static final BitSet FOLLOW_28_in_ruleContextParameterType1400 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleContextParameterType1421 = new BitSet(new long[]{0x0000000000002000L});
        public static final BitSet FOLLOW_13_in_ruleContextParameterType1433 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleUniqueNameType_in_entryRuleUniqueNameType1469 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleUniqueNameType1479 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_ruleUniqueNameType1524 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleConnectionType_in_entryRuleConnectionType1559 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleConnectionType1569 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_29_in_ruleConnectionType1613 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleConnectionType1623 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleConnectionType1634 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleConnectionType1655 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleConnectionType1667 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleConnectionType1677 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleConnectionType1698 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleConnectionType1710 = new BitSet(new long[]{0x0000000040000000L});
        public static final BitSet FOLLOW_30_in_ruleConnectionType1720 = new BitSet(new long[]{0x0020000000000040L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleConnectionType1741 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleConnectionType1754 = new BitSet(new long[]{0x0000000080000000L});
        public static final BitSet FOLLOW_31_in_ruleConnectionType1764 = new BitSet(new long[]{0x0020000000000040L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleConnectionType1785 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleConnectionType1798 = new BitSet(new long[]{0x0000000100000000L});
        public static final BitSet FOLLOW_32_in_ruleConnectionType1808 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleConnectionType1829 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleConnectionType1842 = new BitSet(new long[]{0x0000000200000000L});
        public static final BitSet FOLLOW_33_in_ruleConnectionType1852 = new BitSet(new long[]{0x0020000000000040L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleConnectionType1873 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleConnectionType1886 = new BitSet(new long[]{0x0000000400000000L});
        public static final BitSet FOLLOW_34_in_ruleConnectionType1896 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleConnectionType1917 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleConnectionType1930 = new BitSet(new long[]{0x0000000800000000L});
        public static final BitSet FOLLOW_35_in_ruleConnectionType1940 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleConnectionType1961 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleConnectionType1974 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleElementParameterType_in_ruleConnectionType1995 = new BitSet(new long[]{0x0000000000002000L});
        public static final BitSet FOLLOW_13_in_ruleConnectionType2007 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleNoteType_in_entryRuleNoteType2043 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleNoteType2053 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_36_in_ruleNoteType2088 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleNoteType2098 = new BitSet(new long[]{0x0000016000000030L});
        public static final BitSet FOLLOW_37_in_ruleNoteType2109 = new BitSet(new long[]{0x00C0000000000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleNoteType2130 = new BitSet(new long[]{0x0000014000000030L});
        public static final BitSet FOLLOW_38_in_ruleNoteType2143 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39_in_ruleNoteType2153 = new BitSet(new long[]{0x0020000000000040L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleNoteType2174 = new BitSet(new long[]{0x0000000000004000L});
        public static final BitSet FOLLOW_14_in_ruleNoteType2184 = new BitSet(new long[]{0x0020000000000040L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleNoteType2205 = new BitSet(new long[]{0x0000008000000000L});
        public static final BitSet FOLLOW_39_in_ruleNoteType2215 = new BitSet(new long[]{0x0000010000000030L});
        public static final BitSet FOLLOW_40_in_ruleNoteType2228 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleNoteType2249 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleElementParameterType_in_ruleNoteType2272 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleNoteType2283 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleElementParameterType_in_ruleNoteType2304 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleNoteType2316 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleElementParameterType_in_entryRuleElementParameterType2352 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleElementParameterType2362 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_ruleElementParameterType2418 = new BitSet(new long[]{0x0000020000000000L});
        public static final BitSet FOLLOW_41_in_ruleElementParameterType2428 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleElementParameterType2449 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleSchemaElementParameterType_in_entryRuleSchemaElementParameterType2486 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleSchemaElementParameterType2496 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_ruleSchemaElementParameterType2552 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleSchemaElementParameterType2562 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_ruleElementValueType_in_ruleSchemaElementParameterType2583 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleSchemaElementParameterType2594 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_ruleElementValueType_in_ruleSchemaElementParameterType2615 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_13_in_ruleSchemaElementParameterType2627 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleElementValueType_in_entryRuleElementValueType2665 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleElementValueType2675 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_18_in_ruleElementValueType2720 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleElementValueType2741 = new BitSet(new long[]{0x0000000001020002L});
        public static final BitSet FOLLOW_24_in_ruleElementValueType2753 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleElementValueType2774 = new BitSet(new long[]{0x0000000000020002L});
        public static final BitSet FOLLOW_17_in_ruleElementValueType2787 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleElementValueType2808 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleMetadataType_in_entryRuleMetadataType2846 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleMetadataType2856 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_42_in_ruleMetadataType2900 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleMetadataType2910 = new BitSet(new long[]{0x0000180402062000L});
        public static final BitSet FOLLOW_18_in_ruleMetadataType2921 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleMetadataType2942 = new BitSet(new long[]{0x0000180402022000L});
        public static final BitSet FOLLOW_17_in_ruleMetadataType2955 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleMetadataType2976 = new BitSet(new long[]{0x0000180402002000L});
        public static final BitSet FOLLOW_43_in_ruleMetadataType2989 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleMetadataType3010 = new BitSet(new long[]{0x0000100402002000L});
        public static final BitSet FOLLOW_25_in_ruleMetadataType3023 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleMetadataType3044 = new BitSet(new long[]{0x0000100400002000L});
        public static final BitSet FOLLOW_34_in_ruleMetadataType3057 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleMetadataType3078 = new BitSet(new long[]{0x0000100000002000L});
        public static final BitSet FOLLOW_ruleColumnType_in_ruleMetadataType3101 = new BitSet(new long[]{0x0000100000002000L});
        public static final BitSet FOLLOW_13_in_ruleMetadataType3112 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleColumnType_in_entryRuleColumnType3148 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleColumnType3158 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_44_in_ruleColumnType3202 = new BitSet(new long[]{0x0000000000001000L});
        public static final BitSet FOLLOW_12_in_ruleColumnType3212 = new BitSet(new long[]{0x0000000000040000L});
        public static final BitSet FOLLOW_18_in_ruleColumnType3223 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleColumnType3244 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleColumnType3256 = new BitSet(new long[]{0x0000000000020000L});
        public static final BitSet FOLLOW_17_in_ruleColumnType3266 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleColumnType3287 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleColumnType3300 = new BitSet(new long[]{0x0000200000000000L});
        public static final BitSet FOLLOW_45_in_ruleColumnType3310 = new BitSet(new long[]{0x00C0000000000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleColumnType3331 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleColumnType3344 = new BitSet(new long[]{0x0000400000000000L});
        public static final BitSet FOLLOW_46_in_ruleColumnType3354 = new BitSet(new long[]{0x00C0000000000000L});
        public static final BitSet FOLLOW_ruleEBoolean_in_ruleColumnType3375 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleColumnType3388 = new BitSet(new long[]{0x0000800000000000L});
        public static final BitSet FOLLOW_47_in_ruleColumnType3398 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleColumnType3419 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleColumnType3432 = new BitSet(new long[]{0x0001000000000000L});
        public static final BitSet FOLLOW_48_in_ruleColumnType3442 = new BitSet(new long[]{0x0020000000000040L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleColumnType3463 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleColumnType3476 = new BitSet(new long[]{0x0000000002000000L});
        public static final BitSet FOLLOW_25_in_ruleColumnType3486 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleColumnType3507 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleColumnType3520 = new BitSet(new long[]{0x0002000000000000L});
        public static final BitSet FOLLOW_49_in_ruleColumnType3530 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleColumnType3551 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleColumnType3564 = new BitSet(new long[]{0x0004000000000000L});
        public static final BitSet FOLLOW_50_in_ruleColumnType3574 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleColumnType3595 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleColumnType3608 = new BitSet(new long[]{0x0008000000000000L});
        public static final BitSet FOLLOW_51_in_ruleColumnType3618 = new BitSet(new long[]{0x0020000000000040L});
        public static final BitSet FOLLOW_ruleEInt_in_ruleColumnType3639 = new BitSet(new long[]{0x0000000000006000L});
        public static final BitSet FOLLOW_14_in_ruleColumnType3652 = new BitSet(new long[]{0x0010000000000000L});
        public static final BitSet FOLLOW_52_in_ruleColumnType3662 = new BitSet(new long[]{0x0000000000000030L});
        public static final BitSet FOLLOW_ruleEString_in_ruleColumnType3683 = new BitSet(new long[]{0x0000000000002000L});
        public static final BitSet FOLLOW_13_in_ruleColumnType3695 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEString_in_entryRuleEString3732 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEString3743 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_STRING_in_ruleEString3783 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_RULE_ID_in_ruleEString3809 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEInt_in_entryRuleEInt3855 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEInt3866 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_53_in_ruleEInt3905 = new BitSet(new long[]{0x0000000000000040L});
        public static final BitSet FOLLOW_RULE_INT_in_ruleEInt3922 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_ruleEBoolean_in_entryRuleEBoolean3968 = new BitSet(new long[]{0x0000000000000000L});
        public static final BitSet FOLLOW_EOF_in_entryRuleEBoolean3979 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_54_in_ruleEBoolean4017 = new BitSet(new long[]{0x0000000000000002L});
        public static final BitSet FOLLOW_55_in_ruleEBoolean4036 = new BitSet(new long[]{0x0000000000000002L});
    }


}