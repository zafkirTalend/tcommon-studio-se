// $ANTLR : "SQL92Select.g" -> "Sql92Parser.java"$

package com.quantum.sql.grammar;

import antlr.TokenBuffer;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.ANTLRException;
import antlr.LLkParser;
import antlr.Token;
import antlr.TokenStream;
import antlr.RecognitionException;
import antlr.NoViableAltException;
import antlr.MismatchedTokenException;
import antlr.SemanticException;
import antlr.ParserSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.collections.AST;
import java.util.Hashtable;
import antlr.ASTFactory;
import antlr.ASTPair;
import antlr.collections.impl.ASTArray;

public class Sql92Parser extends antlr.LLkParser       implements Sql92ParserTokenTypes
 {

protected Sql92Parser(TokenBuffer tokenBuf, int k) {
  super(tokenBuf,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public Sql92Parser(TokenBuffer tokenBuf) {
  this(tokenBuf,1);
}

protected Sql92Parser(TokenStream lexer, int k) {
  super(lexer,k);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

public Sql92Parser(TokenStream lexer) {
  this(lexer,1);
}

public Sql92Parser(ParserSharedInputState state) {
  super(state,1);
  tokenNames = _tokenNames;
  buildTokenTypeASTClassMap();
  astFactory = new ASTFactory(getTokenTypeToASTClassMap());
}

	public final void statement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST statement_AST = null;
		
		selectStatement();
		astFactory.addASTChild(currentAST, returnAST);
		{
		switch ( LA(1)) {
		case SEMICOLON:
		{
			AST tmp1_AST = null;
			tmp1_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp1_AST);
			match(SEMICOLON);
			break;
		}
		case EOF:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		AST tmp2_AST = null;
		tmp2_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp2_AST);
		match(Token.EOF_TYPE);
		statement_AST = (AST)currentAST.root;
		returnAST = statement_AST;
	}
	
	public final void selectStatement() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST selectStatement_AST = null;
		
		AST tmp3_AST = null;
		tmp3_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp3_AST);
		match(SELECT);
		{
		switch ( LA(1)) {
		case ALL:
		{
			AST tmp4_AST = null;
			tmp4_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp4_AST);
			match(ALL);
			break;
		}
		case DISTINCT:
		{
			AST tmp5_AST = null;
			tmp5_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp5_AST);
			match(DISTINCT);
			break;
		}
		case STAR:
		case LITERAL_COUNT:
		case LPAREN:
		case LITERAL_MAX:
		case LITERAL_MIN:
		case LITERAL_AVG:
		case LITERAL_SUM:
		case NULL:
		case Integer:
		case Real:
		case HexLiteral:
		case Currency:
		case ODBCDateTime:
		case PLUS:
		case MINUS:
		case TILDE:
		case NonQuotedIdentifier:
		case QuotedIdentifier:
		case UnicodeStringLiteral:
		case ASCIIStringLiteral:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		target();
		astFactory.addASTChild(currentAST, returnAST);
		{
		switch ( LA(1)) {
		case FROM:
		{
			fromClause();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case EOF:
		case SEMICOLON:
		case RPAREN:
		case WHERE:
		case ORDER:
		case GROUP:
		case HAVING:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case WHERE:
		{
			where_condition();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case EOF:
		case SEMICOLON:
		case RPAREN:
		case ORDER:
		case GROUP:
		case HAVING:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		if ((_tokenSet_0.member(LA(1)))) {
			group_by_clause();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case HAVING:
			{
				having_condition();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case SEMICOLON:
			case RPAREN:
			case ORDER:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
		}
		else if ((_tokenSet_1.member(LA(1)))) {
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		{
		switch ( LA(1)) {
		case ORDER:
		{
			order_by_clause();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case EOF:
		case SEMICOLON:
		case RPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		selectStatement_AST = (AST)currentAST.root;
		returnAST = selectStatement_AST;
	}
	
	public final void target() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST target_AST = null;
		
		target_item();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop262:
		do {
			if ((LA(1)==COMMA)) {
				AST tmp6_AST = null;
				tmp6_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp6_AST);
				match(COMMA);
				target_item();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop262;
			}
			
		} while (true);
		}
		target_AST = (AST)currentAST.root;
		returnAST = target_AST;
	}
	
	public final void fromClause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST fromClause_AST = null;
		
		AST tmp7_AST = null;
		tmp7_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp7_AST);
		match(FROM);
		tableSource();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop285:
		do {
			if ((LA(1)==COMMA)) {
				AST tmp8_AST = null;
				tmp8_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp8_AST);
				match(COMMA);
				tableSource();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop285;
			}
			
		} while (true);
		}
		fromClause_AST = (AST)currentAST.root;
		returnAST = fromClause_AST;
	}
	
	public final void where_condition() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST where_condition_AST = null;
		AST t_AST = null;
		
		AST tmp9_AST = null;
		tmp9_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp9_AST);
		match(WHERE);
		condition();
		t_AST = (AST)returnAST;
		astFactory.addASTChild(currentAST, returnAST);
		where_condition_AST = (AST)currentAST.root;
		returnAST = where_condition_AST;
	}
	
	public final void group_by_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST group_by_clause_AST = null;
		
		{
		switch ( LA(1)) {
		case GROUP:
		{
			AST tmp10_AST = null;
			tmp10_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp10_AST);
			match(GROUP);
			AST tmp11_AST = null;
			tmp11_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp11_AST);
			match(BY);
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			{
			_loop367:
			do {
				if ((LA(1)==COMMA)) {
					AST tmp12_AST = null;
					tmp12_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp12_AST);
					match(COMMA);
					expression();
					astFactory.addASTChild(currentAST, returnAST);
				}
				else {
					break _loop367;
				}
				
			} while (true);
			}
			break;
		}
		case EOF:
		case SEMICOLON:
		case RPAREN:
		case ORDER:
		case HAVING:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		group_by_clause_AST = (AST)currentAST.root;
		returnAST = group_by_clause_AST;
	}
	
	public final void having_condition() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST having_condition_AST = null;
		
		AST tmp13_AST = null;
		tmp13_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp13_AST);
		match(HAVING);
		condition();
		astFactory.addASTChild(currentAST, returnAST);
		having_condition_AST = (AST)currentAST.root;
		returnAST = having_condition_AST;
	}
	
	public final void order_by_clause() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST order_by_clause_AST = null;
		
		AST tmp14_AST = null;
		tmp14_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp14_AST);
		match(ORDER);
		AST tmp15_AST = null;
		tmp15_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp15_AST);
		match(BY);
		expression();
		astFactory.addASTChild(currentAST, returnAST);
		{
		switch ( LA(1)) {
		case ASC:
		{
			AST tmp16_AST = null;
			tmp16_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp16_AST);
			match(ASC);
			break;
		}
		case DESC:
		{
			AST tmp17_AST = null;
			tmp17_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp17_AST);
			match(DESC);
			break;
		}
		case EOF:
		case SEMICOLON:
		case COMMA:
		case RPAREN:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		_loop344:
		do {
			if ((LA(1)==COMMA)) {
				AST tmp18_AST = null;
				tmp18_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp18_AST);
				match(COMMA);
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case ASC:
				{
					AST tmp19_AST = null;
					tmp19_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp19_AST);
					match(ASC);
					break;
				}
				case DESC:
				{
					AST tmp20_AST = null;
					tmp20_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp20_AST);
					match(DESC);
					break;
				}
				case EOF:
				case SEMICOLON:
				case COMMA:
				case RPAREN:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
			}
			else {
				break _loop344;
			}
			
		} while (true);
		}
		order_by_clause_AST = (AST)currentAST.root;
		returnAST = order_by_clause_AST;
	}
	
	public final void target_item() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST target_item_AST = null;
		
		switch ( LA(1)) {
		case STAR:
		{
			AST tmp21_AST = null;
			tmp21_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp21_AST);
			match(STAR);
			target_item_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_COUNT:
		{
			{
			AST tmp22_AST = null;
			tmp22_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp22_AST);
			match(LITERAL_COUNT);
			}
			AST tmp23_AST = null;
			tmp23_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp23_AST);
			match(LPAREN);
			{
			switch ( LA(1)) {
			case DISTINCT:
			case NonQuotedIdentifier:
			case QuotedIdentifier:
			{
				{
				{
				switch ( LA(1)) {
				case DISTINCT:
				{
					AST tmp24_AST = null;
					tmp24_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp24_AST);
					match(DISTINCT);
					break;
				}
				case NonQuotedIdentifier:
				case QuotedIdentifier:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				column_name();
				astFactory.addASTChild(currentAST, returnAST);
				}
				break;
			}
			case STAR:
			{
				AST tmp25_AST = null;
				tmp25_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp25_AST);
				match(STAR);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			AST tmp26_AST = null;
			tmp26_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp26_AST);
			match(RPAREN);
			{
			switch ( LA(1)) {
			case AS:
			{
				column_alias();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case SEMICOLON:
			case COMMA:
			case RPAREN:
			case FROM:
			case WHERE:
			case ORDER:
			case GROUP:
			case HAVING:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			target_item_AST = (AST)currentAST.root;
			break;
		}
		case LITERAL_MAX:
		case LITERAL_MIN:
		case LITERAL_AVG:
		case LITERAL_SUM:
		{
			{
			switch ( LA(1)) {
			case LITERAL_MAX:
			{
				AST tmp27_AST = null;
				tmp27_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp27_AST);
				match(LITERAL_MAX);
				break;
			}
			case LITERAL_MIN:
			{
				AST tmp28_AST = null;
				tmp28_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp28_AST);
				match(LITERAL_MIN);
				break;
			}
			case LITERAL_AVG:
			{
				AST tmp29_AST = null;
				tmp29_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp29_AST);
				match(LITERAL_AVG);
				break;
			}
			case LITERAL_SUM:
			{
				AST tmp30_AST = null;
				tmp30_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp30_AST);
				match(LITERAL_SUM);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			AST tmp31_AST = null;
			tmp31_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp31_AST);
			match(LPAREN);
			{
			column_name();
			astFactory.addASTChild(currentAST, returnAST);
			}
			AST tmp32_AST = null;
			tmp32_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp32_AST);
			match(RPAREN);
			{
			switch ( LA(1)) {
			case AS:
			{
				column_alias();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case SEMICOLON:
			case COMMA:
			case RPAREN:
			case FROM:
			case WHERE:
			case ORDER:
			case GROUP:
			case HAVING:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			target_item_AST = (AST)currentAST.root;
			break;
		}
		default:
			boolean synPredMatched265 = false;
			if (((LA(1)==NonQuotedIdentifier||LA(1)==QuotedIdentifier))) {
				int _m265 = mark();
				synPredMatched265 = true;
				inputState.guessing++;
				try {
					{
					table_dot_star();
					}
				}
				catch (RecognitionException pe) {
					synPredMatched265 = false;
				}
				rewind(_m265);
inputState.guessing--;
			}
			if ( synPredMatched265 ) {
				table_dot_star();
				astFactory.addASTChild(currentAST, returnAST);
				target_item_AST = (AST)currentAST.root;
			}
			else {
				boolean synPredMatched267 = false;
				if (((LA(1)==NonQuotedIdentifier||LA(1)==QuotedIdentifier))) {
					int _m267 = mark();
					synPredMatched267 = true;
					inputState.guessing++;
					try {
						{
						dbObject();
						}
					}
					catch (RecognitionException pe) {
						synPredMatched267 = false;
					}
					rewind(_m267);
inputState.guessing--;
				}
				if ( synPredMatched267 ) {
					dbObject();
					astFactory.addASTChild(currentAST, returnAST);
					{
					switch ( LA(1)) {
					case AS:
					{
						column_alias();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					case EOF:
					case SEMICOLON:
					case COMMA:
					case RPAREN:
					case FROM:
					case WHERE:
					case ORDER:
					case GROUP:
					case HAVING:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					target_item_AST = (AST)currentAST.root;
				}
				else {
					boolean synPredMatched270 = false;
					if (((_tokenSet_2.member(LA(1))))) {
						int _m270 = mark();
						synPredMatched270 = true;
						inputState.guessing++;
						try {
							{
							expression();
							}
						}
						catch (RecognitionException pe) {
							synPredMatched270 = false;
						}
						rewind(_m270);
inputState.guessing--;
					}
					if ( synPredMatched270 ) {
						expression();
						astFactory.addASTChild(currentAST, returnAST);
						{
						switch ( LA(1)) {
						case AS:
						{
							column_alias();
							astFactory.addASTChild(currentAST, returnAST);
							break;
						}
						case EOF:
						case SEMICOLON:
						case COMMA:
						case RPAREN:
						case FROM:
						case WHERE:
						case ORDER:
						case GROUP:
						case HAVING:
						{
							break;
						}
						default:
						{
							throw new NoViableAltException(LT(1), getFilename());
						}
						}
						}
						target_item_AST = (AST)currentAST.root;
					}
				else {
					throw new NoViableAltException(LT(1), getFilename());
				}
				}}}
				returnAST = target_item_AST;
			}
			
	public final void table_dot_star() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST table_dot_star_AST = null;
		AST t_AST = null;
		
		dbObject();
		t_AST = (AST)returnAST;
		astFactory.addASTChild(currentAST, returnAST);
		AST tmp33_AST = null;
		tmp33_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp33_AST);
		match(DOT_STAR);
		if ( inputState.guessing==0 ) {
			t_AST.setType(TABLE_IDENTIFIER);
		}
		table_dot_star_AST = (AST)currentAST.root;
		returnAST = table_dot_star_AST;
	}
	
	public final void dbObject() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST dbObject_AST = null;
		AST o1_AST = null;
		AST o2_AST = null;
		AST o3_AST = null;
		
		identifier();
		o1_AST = (AST)returnAST;
		astFactory.addASTChild(currentAST, returnAST);
		{
		switch ( LA(1)) {
		case DOT:
		{
			AST tmp34_AST = null;
			tmp34_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp34_AST);
			match(DOT);
			identifier();
			o2_AST = (AST)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case DOT:
			{
				AST tmp35_AST = null;
				tmp35_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp35_AST);
				match(DOT);
				identifier();
				o3_AST = (AST)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case SEMICOLON:
			case COMMA:
			case RPAREN:
			case DOT_STAR:
			case AS:
			case FROM:
			case CROSS:
			case JOIN:
			case INNER:
			case LEFT:
			case RIGHT:
			case FULL:
			case ON:
			case WHERE:
			case ORDER:
			case NonQuotedIdentifier:
			case QuotedIdentifier:
			case GROUP:
			case HAVING:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			break;
		}
		case EOF:
		case SEMICOLON:
		case COMMA:
		case RPAREN:
		case DOT_STAR:
		case AS:
		case FROM:
		case CROSS:
		case JOIN:
		case INNER:
		case LEFT:
		case RIGHT:
		case FULL:
		case ON:
		case WHERE:
		case ORDER:
		case NonQuotedIdentifier:
		case QuotedIdentifier:
		case GROUP:
		case HAVING:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			
					if(o3_AST!=null){
						o3_AST.setType(COLUMN_IDENTIFIER);
						o2_AST.setType(TABLE_IDENTIFIER);
					}else if(o2_AST!=null){
						o2_AST.setType(COLUMN_IDENTIFIER);
						o1_AST.setType(TABLE_IDENTIFIER);
					}else{
						if(o1_AST.getType()!=TABLE_IDENTIFIER)
						{
							o1_AST.setType(COLUMN_IDENTIFIER);
						}
					}
				
		}
		dbObject_AST = (AST)currentAST.root;
		returnAST = dbObject_AST;
	}
	
	public final void column_alias() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST column_alias_AST = null;
		AST i_AST = null;
		AST s_AST = null;
		
		AST tmp36_AST = null;
		tmp36_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp36_AST);
		match(AS);
		{
		switch ( LA(1)) {
		case NonQuotedIdentifier:
		case QuotedIdentifier:
		{
			identifier();
			i_AST = (AST)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				i_AST.setType(COLUMN_IDENTIFIER);
			}
			break;
		}
		case UnicodeStringLiteral:
		case ASCIIStringLiteral:
		{
			stringLiteral();
			s_AST = (AST)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			if ( inputState.guessing==0 ) {
				s_AST.setType(COLUMN_IDENTIFIER);
			}
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		column_alias_AST = (AST)currentAST.root;
		returnAST = column_alias_AST;
	}
	
	public final void expression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST expression_AST = null;
		
		subExpression();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop347:
		do {
			if ((_tokenSet_3.member(LA(1)))) {
				binaryOperator();
				astFactory.addASTChild(currentAST, returnAST);
				subExpression();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop347;
			}
			
		} while (true);
		}
		expression_AST = (AST)currentAST.root;
		returnAST = expression_AST;
	}
	
	public final void column_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST column_name_AST = null;
		
		dbObject();
		astFactory.addASTChild(currentAST, returnAST);
		column_name_AST = (AST)currentAST.root;
		returnAST = column_name_AST;
	}
	
	public final void identifier() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST identifier_AST = null;
		
		switch ( LA(1)) {
		case NonQuotedIdentifier:
		{
			AST tmp37_AST = null;
			tmp37_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp37_AST);
			match(NonQuotedIdentifier);
			identifier_AST = (AST)currentAST.root;
			break;
		}
		case QuotedIdentifier:
		{
			AST tmp38_AST = null;
			tmp38_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp38_AST);
			match(QuotedIdentifier);
			identifier_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = identifier_AST;
	}
	
	public final void stringLiteral() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST stringLiteral_AST = null;
		
		switch ( LA(1)) {
		case UnicodeStringLiteral:
		{
			AST tmp39_AST = null;
			tmp39_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp39_AST);
			match(UnicodeStringLiteral);
			stringLiteral_AST = (AST)currentAST.root;
			break;
		}
		case ASCIIStringLiteral:
		{
			AST tmp40_AST = null;
			tmp40_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp40_AST);
			match(ASCIIStringLiteral);
			stringLiteral_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = stringLiteral_AST;
	}
	
	public final void tableSource() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST tableSource_AST = null;
		
		subTableSource();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop288:
		do {
			if (((LA(1) >= CROSS && LA(1) <= FULL))) {
				joinedTable();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop288;
			}
			
		} while (true);
		}
		tableSource_AST = (AST)currentAST.root;
		returnAST = tableSource_AST;
	}
	
	public final void subTableSource() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST subTableSource_AST = null;
		
		{
		switch ( LA(1)) {
		case LPAREN:
		{
			AST tmp41_AST = null;
			tmp41_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp41_AST);
			match(LPAREN);
			{
			switch ( LA(1)) {
			case LPAREN:
			case Variable:
			case NonQuotedIdentifier:
			case QuotedIdentifier:
			{
				joinedTables();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp42_AST = null;
				tmp42_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp42_AST);
				match(RPAREN);
				break;
			}
			case SELECT:
			{
				selectStatement();
				astFactory.addASTChild(currentAST, returnAST);
				AST tmp43_AST = null;
				tmp43_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp43_AST);
				match(RPAREN);
				table_alias();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			break;
		}
		case NonQuotedIdentifier:
		case QuotedIdentifier:
		{
			table_name();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case AS:
			case NonQuotedIdentifier:
			case QuotedIdentifier:
			{
				table_alias();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case SEMICOLON:
			case COMMA:
			case RPAREN:
			case CROSS:
			case JOIN:
			case INNER:
			case LEFT:
			case RIGHT:
			case FULL:
			case ON:
			case WHERE:
			case ORDER:
			case GROUP:
			case HAVING:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			break;
		}
		case Variable:
		{
			AST tmp44_AST = null;
			tmp44_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp44_AST);
			match(Variable);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		subTableSource_AST = (AST)currentAST.root;
		returnAST = subTableSource_AST;
	}
	
	public final void joinedTable() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST joinedTable_AST = null;
		
		switch ( LA(1)) {
		case CROSS:
		{
			AST tmp45_AST = null;
			tmp45_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp45_AST);
			match(CROSS);
			AST tmp46_AST = null;
			tmp46_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp46_AST);
			match(JOIN);
			subTableSource();
			astFactory.addASTChild(currentAST, returnAST);
			joinedTable_AST = (AST)currentAST.root;
			break;
		}
		case JOIN:
		case INNER:
		case LEFT:
		case RIGHT:
		case FULL:
		{
			{
			switch ( LA(1)) {
			case INNER:
			case LEFT:
			case RIGHT:
			case FULL:
			{
				{
				switch ( LA(1)) {
				case INNER:
				{
					AST tmp47_AST = null;
					tmp47_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp47_AST);
					match(INNER);
					break;
				}
				case LEFT:
				case RIGHT:
				case FULL:
				{
					{
					switch ( LA(1)) {
					case LEFT:
					{
						AST tmp48_AST = null;
						tmp48_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp48_AST);
						match(LEFT);
						break;
					}
					case RIGHT:
					{
						AST tmp49_AST = null;
						tmp49_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp49_AST);
						match(RIGHT);
						break;
					}
					case FULL:
					{
						AST tmp50_AST = null;
						tmp50_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp50_AST);
						match(FULL);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					{
					switch ( LA(1)) {
					case OUTER:
					{
						AST tmp51_AST = null;
						tmp51_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp51_AST);
						match(OUTER);
						break;
					}
					case JOIN:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				break;
			}
			case JOIN:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			AST tmp52_AST = null;
			tmp52_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp52_AST);
			match(JOIN);
			tableSource();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp53_AST = null;
			tmp53_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp53_AST);
			match(ON);
			condition();
			astFactory.addASTChild(currentAST, returnAST);
			joinedTable_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = joinedTable_AST;
	}
	
	public final void joinedTables() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST joinedTables_AST = null;
		
		subTableSource();
		astFactory.addASTChild(currentAST, returnAST);
		{
		int _cnt304=0;
		_loop304:
		do {
			if (((LA(1) >= CROSS && LA(1) <= FULL))) {
				joinedTable();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				if ( _cnt304>=1 ) { break _loop304; } else {throw new NoViableAltException(LT(1), getFilename());}
			}
			
			_cnt304++;
		} while (true);
		}
		joinedTables_AST = (AST)currentAST.root;
		returnAST = joinedTables_AST;
	}
	
	public final void table_alias() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST table_alias_AST = null;
		AST t_AST = null;
		
		{
		switch ( LA(1)) {
		case AS:
		{
			AST tmp54_AST = null;
			tmp54_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp54_AST);
			match(AS);
			break;
		}
		case NonQuotedIdentifier:
		case QuotedIdentifier:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		identifier();
		t_AST = (AST)returnAST;
		astFactory.addASTChild(currentAST, returnAST);
		if ( inputState.guessing==0 ) {
			t_AST.setType(ALIAS_IDENTIFIER);
		}
		table_alias_AST = (AST)currentAST.root;
		returnAST = table_alias_AST;
	}
	
	public final void table_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST table_name_AST = null;
		AST t_AST = null;
		
		dbObject();
		t_AST = (AST)returnAST;
		astFactory.addASTChild(currentAST, returnAST);
		if ( inputState.guessing==0 ) {
			t_AST.setType(TABLE_IDENTIFIER);
		}
		table_name_AST = (AST)currentAST.root;
		returnAST = table_name_AST;
	}
	
	public final void condition() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST condition_AST = null;
		
		subCondition();
		astFactory.addASTChild(currentAST, returnAST);
		{
		_loop320:
		do {
			if ((LA(1)==AND||LA(1)==OR)) {
				{
				switch ( LA(1)) {
				case AND:
				{
					AST tmp55_AST = null;
					tmp55_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp55_AST);
					match(AND);
					break;
				}
				case OR:
				{
					AST tmp56_AST = null;
					tmp56_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp56_AST);
					match(OR);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				subCondition();
				astFactory.addASTChild(currentAST, returnAST);
			}
			else {
				break _loop320;
			}
			
		} while (true);
		}
		condition_AST = (AST)currentAST.root;
		returnAST = condition_AST;
	}
	
	public final void condition_column_name() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST condition_column_name_AST = null;
		
		condition_dbObject();
		astFactory.addASTChild(currentAST, returnAST);
		condition_column_name_AST = (AST)currentAST.root;
		returnAST = condition_column_name_AST;
	}
	
	public final void condition_dbObject() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST condition_dbObject_AST = null;
		AST o1_AST = null;
		AST o2_AST = null;
		AST o3_AST = null;
		
		identifier();
		o1_AST = (AST)returnAST;
		astFactory.addASTChild(currentAST, returnAST);
		{
		switch ( LA(1)) {
		case DOT:
		{
			AST tmp57_AST = null;
			tmp57_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp57_AST);
			match(DOT);
			identifier();
			o2_AST = (AST)returnAST;
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case DOT:
			{
				AST tmp58_AST = null;
				tmp58_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp58_AST);
				match(DOT);
				identifier();
				o3_AST = (AST)returnAST;
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case EOF:
			case SEMICOLON:
			case COMMA:
			case STAR:
			case RPAREN:
			case AS:
			case FROM:
			case CROSS:
			case JOIN:
			case INNER:
			case LEFT:
			case RIGHT:
			case FULL:
			case ON:
			case WHERE:
			case AND:
			case OR:
			case NOT:
			case IS:
			case LIKE:
			case ESCAPE:
			case BETWEEN:
			case IN:
			case ORDER:
			case ASC:
			case DESC:
			case PLUS:
			case MINUS:
			case TILDE:
			case DIVIDE:
			case MOD:
			case AMPERSAND:
			case BITWISEOR:
			case BITWISEXOR:
			case ASSIGNEQUAL:
			case NOTEQUAL1:
			case NOTEQUAL2:
			case LESSTHANOREQUALTO1:
			case LESSTHANOREQUALTO2:
			case LESSTHAN:
			case GREATERTHANOREQUALTO1:
			case GREATERTHANOREQUALTO2:
			case GREATERTHAN:
			case GROUP:
			case HAVING:
			{
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			break;
		}
		case EOF:
		case SEMICOLON:
		case COMMA:
		case STAR:
		case RPAREN:
		case AS:
		case FROM:
		case CROSS:
		case JOIN:
		case INNER:
		case LEFT:
		case RIGHT:
		case FULL:
		case ON:
		case WHERE:
		case AND:
		case OR:
		case NOT:
		case IS:
		case LIKE:
		case ESCAPE:
		case BETWEEN:
		case IN:
		case ORDER:
		case ASC:
		case DESC:
		case PLUS:
		case MINUS:
		case TILDE:
		case DIVIDE:
		case MOD:
		case AMPERSAND:
		case BITWISEOR:
		case BITWISEXOR:
		case ASSIGNEQUAL:
		case NOTEQUAL1:
		case NOTEQUAL2:
		case LESSTHANOREQUALTO1:
		case LESSTHANOREQUALTO2:
		case LESSTHAN:
		case GREATERTHANOREQUALTO1:
		case GREATERTHANOREQUALTO2:
		case GREATERTHAN:
		case GROUP:
		case HAVING:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			
					if(o3_AST!=null){
						o3_AST.setType(CONDITION_COLUMN_IDENTIFIER);
						o2_AST.setType(CONDITION_TABLE_IDENTIFIER);
					}else if(o2_AST!=null){
						o2_AST.setType(CONDITION_COLUMN_IDENTIFIER);
						o1_AST.setType(CONDITION_TABLE_IDENTIFIER);
					}else{
						if(o1_AST.getType()!=CONDITION_TABLE_IDENTIFIER)
						{
							o1_AST.setType(CONDITION_COLUMN_IDENTIFIER);
						}
					}
				
		}
		condition_dbObject_AST = (AST)currentAST.root;
		returnAST = condition_dbObject_AST;
	}
	
	public final void subCondition() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST subCondition_AST = null;
		
		{
		switch ( LA(1)) {
		case NOT:
		{
			AST tmp59_AST = null;
			tmp59_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp59_AST);
			match(NOT);
			break;
		}
		case LPAREN:
		case NULL:
		case EXISTS:
		case Integer:
		case Real:
		case HexLiteral:
		case Currency:
		case ODBCDateTime:
		case PLUS:
		case MINUS:
		case TILDE:
		case NonQuotedIdentifier:
		case QuotedIdentifier:
		case UnicodeStringLiteral:
		case ASCIIStringLiteral:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		boolean synPredMatched325 = false;
		if (((LA(1)==LPAREN))) {
			int _m325 = mark();
			synPredMatched325 = true;
			inputState.guessing++;
			try {
				{
				match(LPAREN);
				condition();
				match(RPAREN);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched325 = false;
			}
			rewind(_m325);
inputState.guessing--;
		}
		if ( synPredMatched325 ) {
			AST tmp60_AST = null;
			tmp60_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp60_AST);
			match(LPAREN);
			condition();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp61_AST = null;
			tmp61_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp61_AST);
			match(RPAREN);
		}
		else if ((_tokenSet_4.member(LA(1)))) {
			predicate();
			astFactory.addASTChild(currentAST, returnAST);
		}
		else {
			throw new NoViableAltException(LT(1), getFilename());
		}
		
		}
		subCondition_AST = (AST)currentAST.root;
		returnAST = subCondition_AST;
	}
	
	public final void predicate() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST predicate_AST = null;
		
		{
		switch ( LA(1)) {
		case LPAREN:
		case NULL:
		case Integer:
		case Real:
		case HexLiteral:
		case Currency:
		case ODBCDateTime:
		case PLUS:
		case MINUS:
		case TILDE:
		case NonQuotedIdentifier:
		case QuotedIdentifier:
		case UnicodeStringLiteral:
		case ASCIIStringLiteral:
		{
			expression();
			astFactory.addASTChild(currentAST, returnAST);
			{
			switch ( LA(1)) {
			case ASSIGNEQUAL:
			case NOTEQUAL1:
			case NOTEQUAL2:
			case LESSTHANOREQUALTO1:
			case LESSTHANOREQUALTO2:
			case LESSTHAN:
			case GREATERTHANOREQUALTO1:
			case GREATERTHANOREQUALTO2:
			case GREATERTHAN:
			{
				comparisonOperator();
				astFactory.addASTChild(currentAST, returnAST);
				{
				switch ( LA(1)) {
				case LPAREN:
				case NULL:
				case Integer:
				case Real:
				case HexLiteral:
				case Currency:
				case ODBCDateTime:
				case PLUS:
				case MINUS:
				case TILDE:
				case NonQuotedIdentifier:
				case QuotedIdentifier:
				case UnicodeStringLiteral:
				case ASCIIStringLiteral:
				{
					expression();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case ALL:
				case SOME:
				case ANY:
				{
					{
					switch ( LA(1)) {
					case ALL:
					{
						AST tmp62_AST = null;
						tmp62_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp62_AST);
						match(ALL);
						break;
					}
					case SOME:
					{
						AST tmp63_AST = null;
						tmp63_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp63_AST);
						match(SOME);
						break;
					}
					case ANY:
					{
						AST tmp64_AST = null;
						tmp64_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp64_AST);
						match(ANY);
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					AST tmp65_AST = null;
					tmp65_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp65_AST);
					match(LPAREN);
					selectStatement();
					astFactory.addASTChild(currentAST, returnAST);
					AST tmp66_AST = null;
					tmp66_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp66_AST);
					match(RPAREN);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				break;
			}
			case IS:
			{
				AST tmp67_AST = null;
				tmp67_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp67_AST);
				match(IS);
				{
				switch ( LA(1)) {
				case NOT:
				{
					AST tmp68_AST = null;
					tmp68_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp68_AST);
					match(NOT);
					break;
				}
				case NULL:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				AST tmp69_AST = null;
				tmp69_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp69_AST);
				match(NULL);
				break;
			}
			case NOT:
			case LIKE:
			case BETWEEN:
			case IN:
			{
				{
				switch ( LA(1)) {
				case NOT:
				{
					AST tmp70_AST = null;
					tmp70_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp70_AST);
					match(NOT);
					break;
				}
				case LIKE:
				case BETWEEN:
				case IN:
				{
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				{
				switch ( LA(1)) {
				case LIKE:
				{
					AST tmp71_AST = null;
					tmp71_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp71_AST);
					match(LIKE);
					expression();
					astFactory.addASTChild(currentAST, returnAST);
					{
					switch ( LA(1)) {
					case ESCAPE:
					{
						AST tmp72_AST = null;
						tmp72_AST = astFactory.create(LT(1));
						astFactory.addASTChild(currentAST, tmp72_AST);
						match(ESCAPE);
						expression();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					case EOF:
					case SEMICOLON:
					case COMMA:
					case RPAREN:
					case CROSS:
					case JOIN:
					case INNER:
					case LEFT:
					case RIGHT:
					case FULL:
					case ON:
					case WHERE:
					case AND:
					case OR:
					case ORDER:
					case GROUP:
					case HAVING:
					{
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					break;
				}
				case BETWEEN:
				{
					AST tmp73_AST = null;
					tmp73_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp73_AST);
					match(BETWEEN);
					expression();
					astFactory.addASTChild(currentAST, returnAST);
					AST tmp74_AST = null;
					tmp74_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp74_AST);
					match(AND);
					expression();
					astFactory.addASTChild(currentAST, returnAST);
					break;
				}
				case IN:
				{
					AST tmp75_AST = null;
					tmp75_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp75_AST);
					match(IN);
					AST tmp76_AST = null;
					tmp76_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp76_AST);
					match(LPAREN);
					{
					switch ( LA(1)) {
					case SELECT:
					{
						selectStatement();
						astFactory.addASTChild(currentAST, returnAST);
						break;
					}
					case LPAREN:
					case NULL:
					case Integer:
					case Real:
					case HexLiteral:
					case Currency:
					case ODBCDateTime:
					case PLUS:
					case MINUS:
					case TILDE:
					case NonQuotedIdentifier:
					case QuotedIdentifier:
					case UnicodeStringLiteral:
					case ASCIIStringLiteral:
					{
						expression();
						astFactory.addASTChild(currentAST, returnAST);
						{
						_loop339:
						do {
							if ((LA(1)==COMMA)) {
								AST tmp77_AST = null;
								tmp77_AST = astFactory.create(LT(1));
								astFactory.addASTChild(currentAST, tmp77_AST);
								match(COMMA);
								expression();
								astFactory.addASTChild(currentAST, returnAST);
							}
							else {
								break _loop339;
							}
							
						} while (true);
						}
						break;
					}
					default:
					{
						throw new NoViableAltException(LT(1), getFilename());
					}
					}
					}
					AST tmp78_AST = null;
					tmp78_AST = astFactory.create(LT(1));
					astFactory.addASTChild(currentAST, tmp78_AST);
					match(RPAREN);
					break;
				}
				default:
				{
					throw new NoViableAltException(LT(1), getFilename());
				}
				}
				}
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			break;
		}
		case EXISTS:
		{
			AST tmp79_AST = null;
			tmp79_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp79_AST);
			match(EXISTS);
			AST tmp80_AST = null;
			tmp80_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp80_AST);
			match(LPAREN);
			selectStatement();
			astFactory.addASTChild(currentAST, returnAST);
			AST tmp81_AST = null;
			tmp81_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp81_AST);
			match(RPAREN);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		predicate_AST = (AST)currentAST.root;
		returnAST = predicate_AST;
	}
	
	public final void comparisonOperator() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST comparisonOperator_AST = null;
		
		switch ( LA(1)) {
		case ASSIGNEQUAL:
		{
			AST tmp82_AST = null;
			tmp82_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp82_AST);
			match(ASSIGNEQUAL);
			comparisonOperator_AST = (AST)currentAST.root;
			break;
		}
		case NOTEQUAL1:
		{
			AST tmp83_AST = null;
			tmp83_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp83_AST);
			match(NOTEQUAL1);
			comparisonOperator_AST = (AST)currentAST.root;
			break;
		}
		case NOTEQUAL2:
		{
			AST tmp84_AST = null;
			tmp84_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp84_AST);
			match(NOTEQUAL2);
			comparisonOperator_AST = (AST)currentAST.root;
			break;
		}
		case LESSTHANOREQUALTO1:
		{
			AST tmp85_AST = null;
			tmp85_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp85_AST);
			match(LESSTHANOREQUALTO1);
			comparisonOperator_AST = (AST)currentAST.root;
			break;
		}
		case LESSTHANOREQUALTO2:
		{
			AST tmp86_AST = null;
			tmp86_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp86_AST);
			match(LESSTHANOREQUALTO2);
			comparisonOperator_AST = (AST)currentAST.root;
			break;
		}
		case LESSTHAN:
		{
			AST tmp87_AST = null;
			tmp87_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp87_AST);
			match(LESSTHAN);
			comparisonOperator_AST = (AST)currentAST.root;
			break;
		}
		case GREATERTHANOREQUALTO1:
		{
			AST tmp88_AST = null;
			tmp88_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp88_AST);
			match(GREATERTHANOREQUALTO1);
			comparisonOperator_AST = (AST)currentAST.root;
			break;
		}
		case GREATERTHANOREQUALTO2:
		{
			AST tmp89_AST = null;
			tmp89_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp89_AST);
			match(GREATERTHANOREQUALTO2);
			comparisonOperator_AST = (AST)currentAST.root;
			break;
		}
		case GREATERTHAN:
		{
			AST tmp90_AST = null;
			tmp90_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp90_AST);
			match(GREATERTHAN);
			comparisonOperator_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = comparisonOperator_AST;
	}
	
	public final void subExpression() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST subExpression_AST = null;
		
		{
		switch ( LA(1)) {
		case PLUS:
		case MINUS:
		case TILDE:
		{
			unaryOperator();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case LPAREN:
		case NULL:
		case Integer:
		case Real:
		case HexLiteral:
		case Currency:
		case ODBCDateTime:
		case NonQuotedIdentifier:
		case QuotedIdentifier:
		case UnicodeStringLiteral:
		case ASCIIStringLiteral:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		{
		switch ( LA(1)) {
		case NULL:
		case Integer:
		case Real:
		case HexLiteral:
		case Currency:
		case ODBCDateTime:
		case UnicodeStringLiteral:
		case ASCIIStringLiteral:
		{
			constant();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		case LPAREN:
		{
			AST tmp91_AST = null;
			tmp91_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp91_AST);
			match(LPAREN);
			{
			switch ( LA(1)) {
			case SELECT:
			{
				selectStatement();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			case LPAREN:
			case NULL:
			case Integer:
			case Real:
			case HexLiteral:
			case Currency:
			case ODBCDateTime:
			case PLUS:
			case MINUS:
			case TILDE:
			case NonQuotedIdentifier:
			case QuotedIdentifier:
			case UnicodeStringLiteral:
			case ASCIIStringLiteral:
			{
				expression();
				astFactory.addASTChild(currentAST, returnAST);
				break;
			}
			default:
			{
				throw new NoViableAltException(LT(1), getFilename());
			}
			}
			}
			AST tmp92_AST = null;
			tmp92_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp92_AST);
			match(RPAREN);
			break;
		}
		case NonQuotedIdentifier:
		case QuotedIdentifier:
		{
			condition_column_name();
			astFactory.addASTChild(currentAST, returnAST);
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		subExpression_AST = (AST)currentAST.root;
		returnAST = subExpression_AST;
	}
	
	public final void binaryOperator() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST binaryOperator_AST = null;
		
		switch ( LA(1)) {
		case STAR:
		case PLUS:
		case MINUS:
		case DIVIDE:
		case MOD:
		{
			arithmeticOperator();
			astFactory.addASTChild(currentAST, returnAST);
			binaryOperator_AST = (AST)currentAST.root;
			break;
		}
		case TILDE:
		case AMPERSAND:
		case BITWISEOR:
		case BITWISEXOR:
		{
			bitwiseOperator();
			astFactory.addASTChild(currentAST, returnAST);
			binaryOperator_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = binaryOperator_AST;
	}
	
	public final void unaryOperator() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST unaryOperator_AST = null;
		
		switch ( LA(1)) {
		case PLUS:
		{
			AST tmp93_AST = null;
			tmp93_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp93_AST);
			match(PLUS);
			unaryOperator_AST = (AST)currentAST.root;
			break;
		}
		case MINUS:
		{
			AST tmp94_AST = null;
			tmp94_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp94_AST);
			match(MINUS);
			unaryOperator_AST = (AST)currentAST.root;
			break;
		}
		case TILDE:
		{
			AST tmp95_AST = null;
			tmp95_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp95_AST);
			match(TILDE);
			unaryOperator_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = unaryOperator_AST;
	}
	
	public final void constant() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST constant_AST = null;
		
		switch ( LA(1)) {
		case Integer:
		{
			AST tmp96_AST = null;
			tmp96_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp96_AST);
			match(Integer);
			constant_AST = (AST)currentAST.root;
			break;
		}
		case Real:
		{
			AST tmp97_AST = null;
			tmp97_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp97_AST);
			match(Real);
			constant_AST = (AST)currentAST.root;
			break;
		}
		case NULL:
		{
			AST tmp98_AST = null;
			tmp98_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp98_AST);
			match(NULL);
			constant_AST = (AST)currentAST.root;
			break;
		}
		case UnicodeStringLiteral:
		case ASCIIStringLiteral:
		{
			stringLiteral();
			astFactory.addASTChild(currentAST, returnAST);
			constant_AST = (AST)currentAST.root;
			break;
		}
		case HexLiteral:
		{
			AST tmp99_AST = null;
			tmp99_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp99_AST);
			match(HexLiteral);
			constant_AST = (AST)currentAST.root;
			break;
		}
		case Currency:
		{
			AST tmp100_AST = null;
			tmp100_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp100_AST);
			match(Currency);
			constant_AST = (AST)currentAST.root;
			break;
		}
		case ODBCDateTime:
		{
			AST tmp101_AST = null;
			tmp101_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp101_AST);
			match(ODBCDateTime);
			constant_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = constant_AST;
	}
	
	public final void arithmeticOperator() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST arithmeticOperator_AST = null;
		
		switch ( LA(1)) {
		case PLUS:
		{
			AST tmp102_AST = null;
			tmp102_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp102_AST);
			match(PLUS);
			arithmeticOperator_AST = (AST)currentAST.root;
			break;
		}
		case MINUS:
		{
			AST tmp103_AST = null;
			tmp103_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp103_AST);
			match(MINUS);
			arithmeticOperator_AST = (AST)currentAST.root;
			break;
		}
		case STAR:
		{
			AST tmp104_AST = null;
			tmp104_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp104_AST);
			match(STAR);
			arithmeticOperator_AST = (AST)currentAST.root;
			break;
		}
		case DIVIDE:
		{
			AST tmp105_AST = null;
			tmp105_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp105_AST);
			match(DIVIDE);
			arithmeticOperator_AST = (AST)currentAST.root;
			break;
		}
		case MOD:
		{
			AST tmp106_AST = null;
			tmp106_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp106_AST);
			match(MOD);
			arithmeticOperator_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = arithmeticOperator_AST;
	}
	
	public final void bitwiseOperator() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST bitwiseOperator_AST = null;
		
		switch ( LA(1)) {
		case AMPERSAND:
		{
			AST tmp107_AST = null;
			tmp107_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp107_AST);
			match(AMPERSAND);
			bitwiseOperator_AST = (AST)currentAST.root;
			break;
		}
		case TILDE:
		{
			AST tmp108_AST = null;
			tmp108_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp108_AST);
			match(TILDE);
			bitwiseOperator_AST = (AST)currentAST.root;
			break;
		}
		case BITWISEOR:
		{
			AST tmp109_AST = null;
			tmp109_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp109_AST);
			match(BITWISEOR);
			bitwiseOperator_AST = (AST)currentAST.root;
			break;
		}
		case BITWISEXOR:
		{
			AST tmp110_AST = null;
			tmp110_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp110_AST);
			match(BITWISEXOR);
			bitwiseOperator_AST = (AST)currentAST.root;
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		returnAST = bitwiseOperator_AST;
	}
	
	public final void unionOperator() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST unionOperator_AST = null;
		
		AST tmp111_AST = null;
		tmp111_AST = astFactory.create(LT(1));
		astFactory.addASTChild(currentAST, tmp111_AST);
		match(UNION);
		{
		switch ( LA(1)) {
		case ALL:
		{
			AST tmp112_AST = null;
			tmp112_AST = astFactory.create(LT(1));
			astFactory.addASTChild(currentAST, tmp112_AST);
			match(ALL);
			break;
		}
		case EOF:
		{
			break;
		}
		default:
		{
			throw new NoViableAltException(LT(1), getFilename());
		}
		}
		}
		unionOperator_AST = (AST)currentAST.root;
		returnAST = unionOperator_AST;
	}
	
	public final void comment() throws RecognitionException, TokenStreamException {
		
		returnAST = null;
		ASTPair currentAST = new ASTPair();
		AST comment_AST = null;
		
		{
		_loop371:
		do {
			switch ( LA(1)) {
			case MultiLineComment:
			{
				AST tmp113_AST = null;
				tmp113_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp113_AST);
				match(MultiLineComment);
				break;
			}
			case SingleLineComment:
			{
				AST tmp114_AST = null;
				tmp114_AST = astFactory.create(LT(1));
				astFactory.addASTChild(currentAST, tmp114_AST);
				match(SingleLineComment);
				break;
			}
			default:
			{
				break _loop371;
			}
			}
		} while (true);
		}
		comment_AST = (AST)currentAST.root;
		returnAST = comment_AST;
	}
	
	
	public static final String[] _tokenNames = {
		"<0>",
		"EOF",
		"<2>",
		"NULL_TREE_LOOKAHEAD",
		"TABLE_IDENTIFIER",
		"COLUMN_IDENTIFIER",
		"COLUMN_IDENTIFIER_ALIAS",
		"ALIAS_IDENTIFIER",
		"CONDITION_COLUMN_IDENTIFIER",
		"CONDITION_TABLE_IDENTIFIER",
		"SEMICOLON",
		"\"select\"",
		"\"all\"",
		"\"distinct\"",
		"COMMA",
		"STAR",
		"\"COUNT\"",
		"LPAREN",
		"RPAREN",
		"\"MAX\"",
		"\"MIN\"",
		"\"AVG\"",
		"\"SUM\"",
		"DOT_STAR",
		"\"as\"",
		"\"from\"",
		"Variable",
		"\"cross\"",
		"\"join\"",
		"\"inner\"",
		"\"left\"",
		"\"right\"",
		"\"full\"",
		"\"outer\"",
		"\"on\"",
		"DOT",
		"\"where\"",
		"\"and\"",
		"\"or\"",
		"\"not\"",
		"\"some\"",
		"\"any\"",
		"\"is\"",
		"\"null\"",
		"\"like\"",
		"\"escape\"",
		"\"between\"",
		"\"in\"",
		"\"exists\"",
		"\"order\"",
		"\"by\"",
		"\"asc\"",
		"\"desc\"",
		"Integer",
		"Real",
		"HexLiteral",
		"Currency",
		"ODBCDateTime",
		"PLUS",
		"MINUS",
		"TILDE",
		"DIVIDE",
		"MOD",
		"AMPERSAND",
		"BITWISEOR",
		"BITWISEXOR",
		"ASSIGNEQUAL",
		"NOTEQUAL1",
		"NOTEQUAL2",
		"LESSTHANOREQUALTO1",
		"LESSTHANOREQUALTO2",
		"LESSTHAN",
		"GREATERTHANOREQUALTO1",
		"GREATERTHANOREQUALTO2",
		"GREATERTHAN",
		"\"union\"",
		"NonQuotedIdentifier",
		"QuotedIdentifier",
		"UnicodeStringLiteral",
		"ASCIIStringLiteral",
		"\"group\"",
		"\"having\"",
		"MultiLineComment",
		"SingleLineComment",
		"\"add\"",
		"\"alter\"",
		"\"authorization\"",
		"\"auto\"",
		"\"backup\"",
		"\"base64\"",
		"\"begin\"",
		"\"binary\"",
		"\"break\"",
		"\"browse\"",
		"\"bulk\"",
		"\"cascade\"",
		"\"case\"",
		"\"cast\"",
		"\"check\"",
		"\"checkpoint\"",
		"\"close\"",
		"\"clustered\"",
		"\"collate\"",
		"\"column\"",
		"\"commit\"",
		"\"compute\"",
		"\"concat\"",
		"\"constraint\"",
		"\"contains\"",
		"\"containstable\"",
		"\"continue\"",
		"\"create\"",
		"\"cube\"",
		"\"current\"",
		"\"current_date\"",
		"\"current_time\"",
		"\"current_timestamp\"",
		"\"current_user\"",
		"\"cursor\"",
		"\"database\"",
		"\"dbcc\"",
		"\"deallocate\"",
		"\"declare\"",
		"\"default\"",
		"\"delete\"",
		"\"deny\"",
		"\"disk\"",
		"\"distributed\"",
		"\"double\"",
		"\"drop\"",
		"\"dump\"",
		"\"elements\"",
		"\"else\"",
		"\"end\"",
		"\"errlvl\"",
		"\"except\"",
		"\"exec\"",
		"\"execute\"",
		"\"exit\"",
		"\"expand\"",
		"\"explicit\"",
		"\"fast\"",
		"\"fastfirstrow\"",
		"\"fetch\"",
		"\"file\"",
		"\"fillfactor\"",
		"\"for\"",
		"\"force\"",
		"\"foreign\"",
		"\"freetext\"",
		"\"freetexttable\"",
		"\"function\"",
		"\"goto\"",
		"\"grant\"",
		"\"hash\"",
		"\"holdlock\"",
		"\"identity\"",
		"\"identity_insert\"",
		"\"identitycol\"",
		"\"if\"",
		"\"index\"",
		"\"insert\"",
		"\"intersect\"",
		"\"into\"",
		"\"keep\"",
		"\"keepfixed\"",
		"\"key\"",
		"\"kill\"",
		"\"lineno\"",
		"\"load\"",
		"\"loop\"",
		"\"maxdop\"",
		"\"merge\"",
		"\"national\"",
		"\"nocheck\"",
		"\"nolock\"",
		"\"nonclustered\"",
		"\"of\"",
		"\"off\"",
		"\"offsets\"",
		"\"open\"",
		"\"opendatasource\"",
		"\"openquery\"",
		"\"openrowset\"",
		"\"openxml\"",
		"\"option\"",
		"\"over\"",
		"\"paglock\"",
		"\"percent\"",
		"\"plan\"",
		"\"precision\"",
		"\"primary\"",
		"\"print\"",
		"\"proc\"",
		"\"procedure\"",
		"\"public\"",
		"\"raiserror\"",
		"\"raw\"",
		"\"read\"",
		"\"readcommited\"",
		"\"readpast\"",
		"\"readtext\"",
		"\"readuncommited\"",
		"\"reconfigure\"",
		"\"references\"",
		"\"remote\"",
		"\"repeatableread\"",
		"\"replication\"",
		"\"restore\"",
		"\"restrict\"",
		"\"return\"",
		"\"revoke\"",
		"\"robust\"",
		"\"rollback\"",
		"\"rollup\"",
		"\"rowcount\"",
		"\"rowguidcol\"",
		"\"rowlock\"",
		"\"rule\"",
		"\"save\"",
		"\"schema\"",
		"\"serializable\"",
		"\"session_user\"",
		"\"set\"",
		"\"setuser\"",
		"\"shutdown\"",
		"\"statistics\"",
		"\"system_user\"",
		"\"table\"",
		"\"tablock\"",
		"\"tablockx\"",
		"\"textsize\"",
		"\"then\"",
		"\"ties\"",
		"\"to\"",
		"\"top\"",
		"\"tran\"",
		"\"transaction\"",
		"\"trigger\"",
		"\"truncate\"",
		"\"tsequal\"",
		"\"unique\"",
		"\"update\"",
		"\"updatetext\"",
		"\"updlock\"",
		"\"use\"",
		"\"user\"",
		"\"values\"",
		"\"varying\"",
		"\"view\"",
		"\"views\"",
		"\"waitfor\"",
		"\"when\"",
		"\"while\"",
		"\"with\"",
		"\"writetext\"",
		"\"xlock\"",
		"\"xml\"",
		"\"xmldata\"",
		"\"@@connections\"",
		"\"@@cpu_busy\"",
		"\"@@cursor_rows\"",
		"\"@@datefirst\"",
		"\"@@dbts\"",
		"\"@@error\"",
		"\"@@fetch_status\"",
		"\"@@identity\"",
		"\"@@idle\"",
		"\"@@io_busy\"",
		"\"@@langid\"",
		"\"@@language\"",
		"\"@@lock_timeout\"",
		"\"@@max_connections\"",
		"\"@@max_precision\"",
		"\"@@nestlevel\"",
		"\"@@options\"",
		"\"@@pack_received\"",
		"\"@@pack_sent\"",
		"\"@@packet_errors\"",
		"\"@@procid\"",
		"\"@@remserver\"",
		"\"@@rowcount\"",
		"\"@@servername\"",
		"\"@@servicename\"",
		"\"@@spid\"",
		"\"@@textsize\"",
		"\"@@timeticks\"",
		"\"@@total_errors\"",
		"\"@@total_read\"",
		"\"@@total_write\"",
		"\"@@trancount\"",
		"\"@@version\"",
		"COLON",
		"Whitespace",
		"Letter",
		"Digit",
		"Exponent",
		"Number"
	};
	
	protected void buildTokenTypeASTClassMap() {
		tokenTypeToASTClassMap=null;
	};
	
	private static final long[] mk_tokenSet_0() {
		long[] data = { 562949953684482L, 196608L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = { 562949953684482L, 0L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = { 2296844606052106240L, 61440L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = { -288230376151678976L, 3L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = { 2297126081028816896L, 61440L, 0L, 0L, 0L};
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	
	}
