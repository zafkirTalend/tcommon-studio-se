// $ANTLR : "SQL92SelectParser.g" -> "Sql92SelectLexer.java"$

package com.quantum.sql.grammar;

import java.io.InputStream;
import antlr.TokenStreamException;
import antlr.TokenStreamIOException;
import antlr.TokenStreamRecognitionException;
import antlr.CharStreamException;
import antlr.CharStreamIOException;
import antlr.ANTLRException;
import java.io.Reader;
import java.util.Hashtable;
import antlr.CharScanner;
import antlr.InputBuffer;
import antlr.ByteBuffer;
import antlr.CharBuffer;
import antlr.Token;
import antlr.CommonToken;
import antlr.RecognitionException;
import antlr.NoViableAltForCharException;
import antlr.MismatchedCharException;
import antlr.TokenStream;
import antlr.ANTLRHashString;
import antlr.LexerSharedInputState;
import antlr.collections.impl.BitSet;
import antlr.SemanticException;

public class Sql92SelectLexer extends antlr.CharScanner implements Sql92SelectParserTokenTypes, TokenStream
 {
public Sql92SelectLexer(InputStream in) {
	this(new ByteBuffer(in));
}
public Sql92SelectLexer(Reader in) {
	this(new CharBuffer(in));
}
public Sql92SelectLexer(InputBuffer ib) {
	this(new LexerSharedInputState(ib));
}
public Sql92SelectLexer(LexerSharedInputState state) {
	super(state);
	caseSensitiveLiterals = false;
	setCaseSensitive(false);
	literals = new Hashtable();
	literals.put(new ANTLRHashString("@@nestlevel", this), new Integer(272));
	literals.put(new ANTLRHashString("full", this), new Integer(32));
	literals.put(new ANTLRHashString("browse", this), new Integer(91));
	literals.put(new ANTLRHashString("cube", this), new Integer(110));
	literals.put(new ANTLRHashString("update", this), new Integer(240));
	literals.put(new ANTLRHashString("@@version", this), new Integer(289));
	literals.put(new ANTLRHashString("use", this), new Integer(243));
	literals.put(new ANTLRHashString("robust", this), new Integer(210));
	literals.put(new ANTLRHashString("escape", this), new Integer(45));
	literals.put(new ANTLRHashString("with", this), new Integer(252));
	literals.put(new ANTLRHashString("replication", this), new Integer(205));
	literals.put(new ANTLRHashString("precision", this), new Integer(188));
	literals.put(new ANTLRHashString("@@total_errors", this), new Integer(285));
	literals.put(new ANTLRHashString("over", this), new Integer(184));
	literals.put(new ANTLRHashString("@@connections", this), new Integer(257));
	literals.put(new ANTLRHashString("hash", this), new Integer(152));
	literals.put(new ANTLRHashString("having", this), new Integer(81));
	literals.put(new ANTLRHashString("current", this), new Integer(111));
	literals.put(new ANTLRHashString("nonclustered", this), new Integer(174));
	literals.put(new ANTLRHashString("@@error", this), new Integer(262));
	literals.put(new ANTLRHashString("@@langid", this), new Integer(267));
	literals.put(new ANTLRHashString("current_time", this), new Integer(113));
	literals.put(new ANTLRHashString("contains", this), new Integer(106));
	literals.put(new ANTLRHashString("serializable", this), new Integer(219));
	literals.put(new ANTLRHashString("print", this), new Integer(190));
	literals.put(new ANTLRHashString("errlvl", this), new Integer(132));
	literals.put(new ANTLRHashString("maxdop", this), new Integer(169));
	literals.put(new ANTLRHashString("repeatableread", this), new Integer(204));
	literals.put(new ANTLRHashString("remote", this), new Integer(203));
	literals.put(new ANTLRHashString("break", this), new Integer(90));
	literals.put(new ANTLRHashString("grant", this), new Integer(151));
	literals.put(new ANTLRHashString("current_user", this), new Integer(115));
	literals.put(new ANTLRHashString("varying", this), new Integer(246));
	literals.put(new ANTLRHashString("cursor", this), new Integer(116));
	literals.put(new ANTLRHashString("references", this), new Integer(202));
	literals.put(new ANTLRHashString("keepfixed", this), new Integer(163));
	literals.put(new ANTLRHashString("continue", this), new Integer(108));
	literals.put(new ANTLRHashString("outer", this), new Integer(33));
	literals.put(new ANTLRHashString("tablock", this), new Integer(227));
	literals.put(new ANTLRHashString("for", this), new Integer(144));
	literals.put(new ANTLRHashString("check", this), new Integer(96));
	literals.put(new ANTLRHashString("else", this), new Integer(130));
	literals.put(new ANTLRHashString("current_date", this), new Integer(112));
	literals.put(new ANTLRHashString("is", this), new Integer(42));
	literals.put(new ANTLRHashString("@@packet_errors", this), new Integer(276));
	literals.put(new ANTLRHashString("readuncommited", this), new Integer(200));
	literals.put(new ANTLRHashString("insert", this), new Integer(159));
	literals.put(new ANTLRHashString("cascade", this), new Integer(93));
	literals.put(new ANTLRHashString("of", this), new Integer(175));
	literals.put(new ANTLRHashString("and", this), new Integer(36));
	literals.put(new ANTLRHashString("concat", this), new Integer(104));
	literals.put(new ANTLRHashString("user", this), new Integer(244));
	literals.put(new ANTLRHashString("begin", this), new Integer(88));
	literals.put(new ANTLRHashString("some", this), new Integer(40));
	literals.put(new ANTLRHashString("as", this), new Integer(24));
	literals.put(new ANTLRHashString("database", this), new Integer(117));
	literals.put(new ANTLRHashString("restore", this), new Integer(206));
	literals.put(new ANTLRHashString("key", this), new Integer(164));
	literals.put(new ANTLRHashString("current_timestamp", this), new Integer(114));
	literals.put(new ANTLRHashString("desc", this), new Integer(52));
	literals.put(new ANTLRHashString("@@max_precision", this), new Integer(271));
	literals.put(new ANTLRHashString("SUM", this), new Integer(22));
	literals.put(new ANTLRHashString("auto", this), new Integer(85));
	literals.put(new ANTLRHashString("procedure", this), new Integer(192));
	literals.put(new ANTLRHashString("exists", this), new Integer(48));
	literals.put(new ANTLRHashString("@@max_connections", this), new Integer(270));
	literals.put(new ANTLRHashString("like", this), new Integer(44));
	literals.put(new ANTLRHashString("@@trancount", this), new Integer(288));
	literals.put(new ANTLRHashString("deallocate", this), new Integer(119));
	literals.put(new ANTLRHashString("merge", this), new Integer(170));
	literals.put(new ANTLRHashString("MIN", this), new Integer(20));
	literals.put(new ANTLRHashString("left", this), new Integer(30));
	literals.put(new ANTLRHashString("foreign", this), new Integer(146));
	literals.put(new ANTLRHashString("explicit", this), new Integer(138));
	literals.put(new ANTLRHashString("shutdown", this), new Integer(223));
	literals.put(new ANTLRHashString("@@timeticks", this), new Integer(284));
	literals.put(new ANTLRHashString("offsets", this), new Integer(177));
	literals.put(new ANTLRHashString("xmldata", this), new Integer(256));
	literals.put(new ANTLRHashString("identitycol", this), new Integer(156));
	literals.put(new ANTLRHashString("unique", this), new Integer(239));
	literals.put(new ANTLRHashString("updlock", this), new Integer(242));
	literals.put(new ANTLRHashString("tran", this), new Integer(234));
	literals.put(new ANTLRHashString("@@pack_sent", this), new Integer(275));
	literals.put(new ANTLRHashString("@@options", this), new Integer(273));
	literals.put(new ANTLRHashString("open", this), new Integer(178));
	literals.put(new ANTLRHashString("between", this), new Integer(46));
	literals.put(new ANTLRHashString("function", this), new Integer(149));
	literals.put(new ANTLRHashString("identity_insert", this), new Integer(155));
	literals.put(new ANTLRHashString("paglock", this), new Integer(185));
	literals.put(new ANTLRHashString("national", this), new Integer(171));
	literals.put(new ANTLRHashString("opendatasource", this), new Integer(179));
	literals.put(new ANTLRHashString("binary", this), new Integer(89));
	literals.put(new ANTLRHashString("intersect", this), new Integer(160));
	literals.put(new ANTLRHashString("view", this), new Integer(247));
	literals.put(new ANTLRHashString("declare", this), new Integer(120));
	literals.put(new ANTLRHashString("bulk", this), new Integer(92));
	literals.put(new ANTLRHashString("any", this), new Integer(41));
	literals.put(new ANTLRHashString("AVG", this), new Integer(21));
	literals.put(new ANTLRHashString("@@servicename", this), new Integer(281));
	literals.put(new ANTLRHashString("not", this), new Integer(39));
	literals.put(new ANTLRHashString("off", this), new Integer(176));
	literals.put(new ANTLRHashString("updatetext", this), new Integer(241));
	literals.put(new ANTLRHashString("@@datefirst", this), new Integer(260));
	literals.put(new ANTLRHashString("all", this), new Integer(12));
	literals.put(new ANTLRHashString("@@remserver", this), new Integer(278));
	literals.put(new ANTLRHashString("in", this), new Integer(47));
	literals.put(new ANTLRHashString("fillfactor", this), new Integer(143));
	literals.put(new ANTLRHashString("except", this), new Integer(133));
	literals.put(new ANTLRHashString("file", this), new Integer(142));
	literals.put(new ANTLRHashString("column", this), new Integer(101));
	literals.put(new ANTLRHashString("compute", this), new Integer(103));
	literals.put(new ANTLRHashString("raw", this), new Integer(195));
	literals.put(new ANTLRHashString("backup", this), new Integer(86));
	literals.put(new ANTLRHashString("execute", this), new Integer(135));
	literals.put(new ANTLRHashString("system_user", this), new Integer(225));
	literals.put(new ANTLRHashString("readpast", this), new Integer(198));
	literals.put(new ANTLRHashString("reconfigure", this), new Integer(201));
	literals.put(new ANTLRHashString("transaction", this), new Integer(235));
	literals.put(new ANTLRHashString("fetch", this), new Integer(141));
	literals.put(new ANTLRHashString("proc", this), new Integer(191));
	literals.put(new ANTLRHashString("openrowset", this), new Integer(181));
	literals.put(new ANTLRHashString("@@lock_timeout", this), new Integer(269));
	literals.put(new ANTLRHashString("restrict", this), new Integer(207));
	literals.put(new ANTLRHashString("delete", this), new Integer(122));
	literals.put(new ANTLRHashString("when", this), new Integer(250));
	literals.put(new ANTLRHashString("null", this), new Integer(43));
	literals.put(new ANTLRHashString("join", this), new Integer(28));
	literals.put(new ANTLRHashString("top", this), new Integer(233));
	literals.put(new ANTLRHashString("rule", this), new Integer(216));
	literals.put(new ANTLRHashString("clustered", this), new Integer(99));
	literals.put(new ANTLRHashString("xlock", this), new Integer(254));
	literals.put(new ANTLRHashString("public", this), new Integer(193));
	literals.put(new ANTLRHashString("freetext", this), new Integer(147));
	literals.put(new ANTLRHashString("@@total_read", this), new Integer(286));
	literals.put(new ANTLRHashString("disk", this), new Integer(124));
	literals.put(new ANTLRHashString("truncate", this), new Integer(237));
	literals.put(new ANTLRHashString("distinct", this), new Integer(13));
	literals.put(new ANTLRHashString("readtext", this), new Integer(199));
	literals.put(new ANTLRHashString("lineno", this), new Integer(166));
	literals.put(new ANTLRHashString("readcommited", this), new Integer(197));
	literals.put(new ANTLRHashString("inner", this), new Integer(29));
	literals.put(new ANTLRHashString("@@idle", this), new Integer(265));
	literals.put(new ANTLRHashString("to", this), new Integer(232));
	literals.put(new ANTLRHashString("into", this), new Integer(161));
	literals.put(new ANTLRHashString("case", this), new Integer(94));
	literals.put(new ANTLRHashString("@@spid", this), new Integer(282));
	literals.put(new ANTLRHashString("force", this), new Integer(145));
	literals.put(new ANTLRHashString("deny", this), new Integer(123));
	literals.put(new ANTLRHashString("primary", this), new Integer(189));
	literals.put(new ANTLRHashString("rollup", this), new Integer(212));
	literals.put(new ANTLRHashString("plan", this), new Integer(187));
	literals.put(new ANTLRHashString("textsize", this), new Integer(229));
	literals.put(new ANTLRHashString("holdlock", this), new Integer(153));
	literals.put(new ANTLRHashString("elements", this), new Integer(129));
	literals.put(new ANTLRHashString("percent", this), new Integer(186));
	literals.put(new ANTLRHashString("containstable", this), new Integer(107));
	literals.put(new ANTLRHashString("@@cpu_busy", this), new Integer(258));
	literals.put(new ANTLRHashString("openquery", this), new Integer(180));
	literals.put(new ANTLRHashString("freetexttable", this), new Integer(148));
	literals.put(new ANTLRHashString("@@language", this), new Integer(268));
	literals.put(new ANTLRHashString("checkpoint", this), new Integer(97));
	literals.put(new ANTLRHashString("values", this), new Integer(245));
	literals.put(new ANTLRHashString("ties", this), new Integer(231));
	literals.put(new ANTLRHashString("statistics", this), new Integer(224));
	literals.put(new ANTLRHashString("collate", this), new Integer(100));
	literals.put(new ANTLRHashString("@@textsize", this), new Integer(283));
	literals.put(new ANTLRHashString("right", this), new Integer(31));
	literals.put(new ANTLRHashString("union", this), new Integer(75));
	literals.put(new ANTLRHashString("then", this), new Integer(230));
	literals.put(new ANTLRHashString("close", this), new Integer(98));
	literals.put(new ANTLRHashString("set", this), new Integer(221));
	literals.put(new ANTLRHashString("schema", this), new Integer(218));
	literals.put(new ANTLRHashString("save", this), new Integer(217));
	literals.put(new ANTLRHashString("or", this), new Integer(37));
	literals.put(new ANTLRHashString("cross", this), new Integer(27));
	literals.put(new ANTLRHashString("if", this), new Integer(157));
	literals.put(new ANTLRHashString("exec", this), new Integer(134));
	literals.put(new ANTLRHashString("by", this), new Integer(50));
	literals.put(new ANTLRHashString("index", this), new Integer(158));
	literals.put(new ANTLRHashString("add", this), new Integer(82));
	literals.put(new ANTLRHashString("base64", this), new Integer(87));
	literals.put(new ANTLRHashString("table", this), new Integer(226));
	literals.put(new ANTLRHashString("loop", this), new Integer(168));
	literals.put(new ANTLRHashString("distributed", this), new Integer(125));
	literals.put(new ANTLRHashString("return", this), new Integer(208));
	literals.put(new ANTLRHashString("keep", this), new Integer(162));
	literals.put(new ANTLRHashString("@@io_busy", this), new Integer(266));
	literals.put(new ANTLRHashString("group", this), new Integer(80));
	literals.put(new ANTLRHashString("from", this), new Integer(25));
	literals.put(new ANTLRHashString("dump", this), new Integer(128));
	literals.put(new ANTLRHashString("fast", this), new Integer(139));
	literals.put(new ANTLRHashString("rollback", this), new Integer(211));
	literals.put(new ANTLRHashString("read", this), new Integer(196));
	literals.put(new ANTLRHashString("setuser", this), new Integer(222));
	literals.put(new ANTLRHashString("load", this), new Integer(167));
	literals.put(new ANTLRHashString("default", this), new Integer(121));
	literals.put(new ANTLRHashString("drop", this), new Integer(127));
	literals.put(new ANTLRHashString("kill", this), new Integer(165));
	literals.put(new ANTLRHashString("writetext", this), new Integer(253));
	literals.put(new ANTLRHashString("alter", this), new Integer(83));
	literals.put(new ANTLRHashString("where", this), new Integer(38));
	literals.put(new ANTLRHashString("xml", this), new Integer(255));
	literals.put(new ANTLRHashString("dbcc", this), new Integer(118));
	literals.put(new ANTLRHashString("revoke", this), new Integer(209));
	literals.put(new ANTLRHashString("identity", this), new Integer(154));
	literals.put(new ANTLRHashString("@@pack_received", this), new Integer(274));
	literals.put(new ANTLRHashString("constraint", this), new Integer(105));
	literals.put(new ANTLRHashString("order", this), new Integer(49));
	literals.put(new ANTLRHashString("end", this), new Integer(131));
	literals.put(new ANTLRHashString("commit", this), new Integer(102));
	literals.put(new ANTLRHashString("@@servername", this), new Integer(280));
	literals.put(new ANTLRHashString("@@identity", this), new Integer(264));
	literals.put(new ANTLRHashString("tablockx", this), new Integer(228));
	literals.put(new ANTLRHashString("raiserror", this), new Integer(194));
	literals.put(new ANTLRHashString("COUNT", this), new Integer(16));
	literals.put(new ANTLRHashString("authorization", this), new Integer(84));
	literals.put(new ANTLRHashString("nolock", this), new Integer(173));
	literals.put(new ANTLRHashString("goto", this), new Integer(150));
	literals.put(new ANTLRHashString("on", this), new Integer(34));
	literals.put(new ANTLRHashString("create", this), new Integer(109));
	literals.put(new ANTLRHashString("MAX", this), new Integer(19));
	literals.put(new ANTLRHashString("select", this), new Integer(11));
	literals.put(new ANTLRHashString("rowlock", this), new Integer(215));
	literals.put(new ANTLRHashString("session_user", this), new Integer(220));
	literals.put(new ANTLRHashString("rowcount", this), new Integer(213));
	literals.put(new ANTLRHashString("while", this), new Integer(251));
	literals.put(new ANTLRHashString("tsequal", this), new Integer(238));
	literals.put(new ANTLRHashString("option", this), new Integer(183));
	literals.put(new ANTLRHashString("@@fetch_status", this), new Integer(263));
	literals.put(new ANTLRHashString("@@total_write", this), new Integer(287));
	literals.put(new ANTLRHashString("@@cursor_rows", this), new Integer(259));
	literals.put(new ANTLRHashString("cast", this), new Integer(95));
	literals.put(new ANTLRHashString("waitfor", this), new Integer(249));
	literals.put(new ANTLRHashString("asc", this), new Integer(51));
	literals.put(new ANTLRHashString("@@rowcount", this), new Integer(279));
	literals.put(new ANTLRHashString("double", this), new Integer(126));
	literals.put(new ANTLRHashString("rowguidcol", this), new Integer(214));
	literals.put(new ANTLRHashString("@@procid", this), new Integer(277));
	literals.put(new ANTLRHashString("nocheck", this), new Integer(172));
	literals.put(new ANTLRHashString("trigger", this), new Integer(236));
	literals.put(new ANTLRHashString("openxml", this), new Integer(182));
	literals.put(new ANTLRHashString("@@dbts", this), new Integer(261));
	literals.put(new ANTLRHashString("fastfirstrow", this), new Integer(140));
	literals.put(new ANTLRHashString("exit", this), new Integer(136));
	literals.put(new ANTLRHashString("expand", this), new Integer(137));
	literals.put(new ANTLRHashString("views", this), new Integer(248));
}

public Token nextToken() throws TokenStreamException {
	Token theRetToken=null;
tryAgain:
	for (;;) {
		Token _token = null;
		int _ttype = Token.INVALID_TYPE;
		resetText();
		try {   // for char stream error handling
			try {   // for lexical error handling
				switch ( LA(1)) {
				case ':':
				{
					mCOLON(true);
					theRetToken=_returnToken;
					break;
				}
				case ',':
				{
					mCOMMA(true);
					theRetToken=_returnToken;
					break;
				}
				case ';':
				{
					mSEMICOLON(true);
					theRetToken=_returnToken;
					break;
				}
				case '(':
				{
					mLPAREN(true);
					theRetToken=_returnToken;
					break;
				}
				case ')':
				{
					mRPAREN(true);
					theRetToken=_returnToken;
					break;
				}
				case '=':
				{
					mASSIGNEQUAL(true);
					theRetToken=_returnToken;
					break;
				}
				case '+':
				{
					mPLUS(true);
					theRetToken=_returnToken;
					break;
				}
				case '*':
				{
					mSTAR(true);
					theRetToken=_returnToken;
					break;
				}
				case '%':
				{
					mMOD(true);
					theRetToken=_returnToken;
					break;
				}
				case '&':
				{
					mAMPERSAND(true);
					theRetToken=_returnToken;
					break;
				}
				case '~':
				{
					mTILDE(true);
					theRetToken=_returnToken;
					break;
				}
				case '|':
				{
					mBITWISEOR(true);
					theRetToken=_returnToken;
					break;
				}
				case '^':
				{
					mBITWISEXOR(true);
					theRetToken=_returnToken;
					break;
				}
				case '\t':  case '\n':  case '\r':  case ' ':
				{
					mWhitespace(true);
					theRetToken=_returnToken;
					break;
				}
				case '{':
				{
					mODBCDateTime(true);
					theRetToken=_returnToken;
					break;
				}
				case '"':  case '[':
				{
					mQuotedIdentifier(true);
					theRetToken=_returnToken;
					break;
				}
				case '@':
				{
					mVariable(true);
					theRetToken=_returnToken;
					break;
				}
				case '\'':
				{
					mASCIIStringLiteral(true);
					theRetToken=_returnToken;
					break;
				}
				default:
					if ((LA(1)=='<') && (LA(2)=='>')) {
						mNOTEQUAL1(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='!') && (LA(2)=='=')) {
						mNOTEQUAL2(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='<') && (LA(2)=='=')) {
						mLESSTHANOREQUALTO1(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='!') && (LA(2)=='>')) {
						mLESSTHANOREQUALTO2(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='>') && (LA(2)=='=')) {
						mGREATERTHANOREQUALTO1(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='!') && (LA(2)=='<')) {
						mGREATERTHANOREQUALTO2(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='.') && (LA(2)=='*')) {
						mDOT_STAR(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='-') && (LA(2)=='-')) {
						mSingleLineComment(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='/') && (LA(2)=='*')) {
						mMultiLineComment(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='n') && (LA(2)=='\'')) {
						mUnicodeStringLiteral(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='<') && (true)) {
						mLESSTHAN(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='>') && (true)) {
						mGREATERTHAN(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='/') && (true)) {
						mDIVIDE(true);
						theRetToken=_returnToken;
					}
					else if ((LA(1)=='-') && (true)) {
						mMINUS(true);
						theRetToken=_returnToken;
					}
					else if ((_tokenSet_0.member(LA(1))) && (true)) {
						mNumber(true);
						theRetToken=_returnToken;
					}
					else if ((_tokenSet_1.member(LA(1))) && (true)) {
						mNonQuotedIdentifier(true);
						theRetToken=_returnToken;
					}
				else {
					if (LA(1)==EOF_CHAR) {uponEOF(); _returnToken = makeToken(Token.EOF_TYPE);}
				else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				}
				if ( _returnToken==null ) continue tryAgain; // found SKIP token
				_ttype = _returnToken.getType();
				_returnToken.setType(_ttype);
				return _returnToken;
			}
			catch (RecognitionException e) {
				throw new TokenStreamRecognitionException(e);
			}
		}
		catch (CharStreamException cse) {
			if ( cse instanceof CharStreamIOException ) {
				throw new TokenStreamIOException(((CharStreamIOException)cse).io);
			}
			else {
				throw new TokenStreamException(cse.getMessage());
			}
		}
	}
}

	protected final void mDOT(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DOT;
		int _saveIndex;
		
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mCOLON(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = COLON;
		int _saveIndex;
		
		match(':');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mCOMMA(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = COMMA;
		int _saveIndex;
		
		match(',');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSEMICOLON(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SEMICOLON;
		int _saveIndex;
		
		match(';');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mLPAREN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LPAREN;
		int _saveIndex;
		
		match('(');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mRPAREN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = RPAREN;
		int _saveIndex;
		
		match(')');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mASSIGNEQUAL(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ASSIGNEQUAL;
		int _saveIndex;
		
		match('=');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mNOTEQUAL1(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = NOTEQUAL1;
		int _saveIndex;
		
		match("<>");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mNOTEQUAL2(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = NOTEQUAL2;
		int _saveIndex;
		
		match("!=");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mLESSTHANOREQUALTO1(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LESSTHANOREQUALTO1;
		int _saveIndex;
		
		match("<=");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mLESSTHANOREQUALTO2(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LESSTHANOREQUALTO2;
		int _saveIndex;
		
		match("!>");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mLESSTHAN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = LESSTHAN;
		int _saveIndex;
		
		match("<");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mGREATERTHANOREQUALTO1(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = GREATERTHANOREQUALTO1;
		int _saveIndex;
		
		match(">=");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mGREATERTHANOREQUALTO2(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = GREATERTHANOREQUALTO2;
		int _saveIndex;
		
		match("!<");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mGREATERTHAN(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = GREATERTHAN;
		int _saveIndex;
		
		match(">");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDIVIDE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DIVIDE;
		int _saveIndex;
		
		match('/');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mPLUS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = PLUS;
		int _saveIndex;
		
		match('+');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mMINUS(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = MINUS;
		int _saveIndex;
		
		match('-');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSTAR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = STAR;
		int _saveIndex;
		
		match('*');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mMOD(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = MOD;
		int _saveIndex;
		
		match('%');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mAMPERSAND(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = AMPERSAND;
		int _saveIndex;
		
		match('&');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mTILDE(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = TILDE;
		int _saveIndex;
		
		match('~');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mBITWISEOR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = BITWISEOR;
		int _saveIndex;
		
		match('|');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mBITWISEXOR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = BITWISEXOR;
		int _saveIndex;
		
		match('^');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mDOT_STAR(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = DOT_STAR;
		int _saveIndex;
		
		match(".*");
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mWhitespace(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = Whitespace;
		int _saveIndex;
		
		{
		switch ( LA(1)) {
		case ' ':
		{
			match(' ');
			break;
		}
		case '\t':
		{
			match('\t');
			break;
		}
		case '\n':
		{
			match('\n');
			break;
		}
		case '\r':
		{
			match('\r');
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		if ( inputState.guessing==0 ) {
			_ttype = Token.SKIP;
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mSingleLineComment(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = SingleLineComment;
		int _saveIndex;
		
		match("--");
		{
		_loop152:
		do {
			if ((_tokenSet_2.member(LA(1)))) {
				{
				match(_tokenSet_2);
				}
			}
			else {
				break _loop152;
			}
			
		} while (true);
		}
		if ( inputState.guessing==0 ) {
			_ttype = Token.SKIP;
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mMultiLineComment(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = MultiLineComment;
		int _saveIndex;
		
		match("/*");
		{
		_loop155:
		do {
			if ((_tokenSet_3.member(LA(1)))) {
				matchNot('*');
			}
			else {
				break _loop155;
			}
			
		} while (true);
		}
		match('*');
		{
		_loop161:
		do {
			if ((LA(1)=='*')) {
				match('*');
			}
			else if ((_tokenSet_4.member(LA(1)))) {
				{
				{
				match(_tokenSet_4);
				}
				{
				_loop160:
				do {
					if ((_tokenSet_3.member(LA(1)))) {
						matchNot('*');
					}
					else {
						break _loop160;
					}
					
				} while (true);
				}
				match('*');
				}
			}
			else {
				break _loop161;
			}
			
		} while (true);
		}
		match('/');
		if ( inputState.guessing==0 ) {
			_ttype = Token.SKIP;
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mLetter(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = Letter;
		int _saveIndex;
		
		switch ( LA(1)) {
		case 'a':  case 'b':  case 'c':  case 'd':
		case 'e':  case 'f':  case 'g':  case 'h':
		case 'i':  case 'j':  case 'k':  case 'l':
		case 'm':  case 'n':  case 'o':  case 'p':
		case 'q':  case 'r':  case 's':  case 't':
		case 'u':  case 'v':  case 'w':  case 'x':
		case 'y':  case 'z':
		{
			matchRange('a','z');
			break;
		}
		case '_':
		{
			match('_');
			break;
		}
		case '#':
		{
			match('#');
			break;
		}
		case '@':
		{
			match('@');
			break;
		}
		default:
			if (((LA(1) >= '\u0080' && LA(1) <= '\ufffe'))) {
				matchRange('\u0080','\ufffe');
			}
		else {
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mDigit(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = Digit;
		int _saveIndex;
		
		matchRange('0','9');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mInteger(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = Integer;
		int _saveIndex;
		
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mReal(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = Real;
		int _saveIndex;
		
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mExponent(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = Exponent;
		int _saveIndex;
		
		match('e');
		{
		switch ( LA(1)) {
		case '+':
		{
			match('+');
			break;
		}
		case '-':
		{
			match('-');
			break;
		}
		case '0':  case '1':  case '2':  case '3':
		case '4':  case '5':  case '6':  case '7':
		case '8':  case '9':
		{
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		{
		int _cnt169=0;
		_loop169:
		do {
			if (((LA(1) >= '0' && LA(1) <= '9'))) {
				mDigit(false);
			}
			else {
				if ( _cnt169>=1 ) { break _loop169; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
			}
			
			_cnt169++;
		} while (true);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mNumber(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = Number;
		int _saveIndex;
		
		boolean synPredMatched175 = false;
		if ((((LA(1) >= '0' && LA(1) <= '9')) && (_tokenSet_5.member(LA(2))))) {
			int _m175 = mark();
			synPredMatched175 = true;
			inputState.guessing++;
			try {
				{
				{
				int _cnt173=0;
				_loop173:
				do {
					if (((LA(1) >= '0' && LA(1) <= '9'))) {
						mDigit(false);
					}
					else {
						if ( _cnt173>=1 ) { break _loop173; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
					}
					
					_cnt173++;
				} while (true);
				}
				{
				switch ( LA(1)) {
				case '.':
				{
					match('.');
					break;
				}
				case 'e':
				{
					match('e');
					break;
				}
				default:
				{
					throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
				}
				}
				}
				}
			}
			catch (RecognitionException pe) {
				synPredMatched175 = false;
			}
			rewind(_m175);
inputState.guessing--;
		}
		if ( synPredMatched175 ) {
			{
			int _cnt177=0;
			_loop177:
			do {
				if (((LA(1) >= '0' && LA(1) <= '9'))) {
					mDigit(false);
				}
				else {
					if ( _cnt177>=1 ) { break _loop177; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				
				_cnt177++;
			} while (true);
			}
			{
			switch ( LA(1)) {
			case '.':
			{
				match('.');
				{
				_loop180:
				do {
					if (((LA(1) >= '0' && LA(1) <= '9'))) {
						mDigit(false);
					}
					else {
						break _loop180;
					}
					
				} while (true);
				}
				{
				if ((LA(1)=='e')) {
					mExponent(false);
				}
				else {
				}
				
				}
				break;
			}
			case 'e':
			{
				mExponent(false);
				break;
			}
			default:
			{
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			}
			}
			if ( inputState.guessing==0 ) {
				_ttype = Real;
			}
		}
		else if ((LA(1)=='0') && (LA(2)=='x')) {
			match("0x");
			{
			_loop189:
			do {
				switch ( LA(1)) {
				case 'a':  case 'b':  case 'c':  case 'd':
				case 'e':  case 'f':
				{
					matchRange('a','f');
					break;
				}
				case '0':  case '1':  case '2':  case '3':
				case '4':  case '5':  case '6':  case '7':
				case '8':  case '9':
				{
					mDigit(false);
					break;
				}
				default:
				{
					break _loop189;
				}
				}
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				_ttype = HexLiteral;
			}
		}
		else if ((LA(1)=='.')) {
			match('.');
			if ( inputState.guessing==0 ) {
				_ttype = DOT;
			}
			{
			if (((LA(1) >= '0' && LA(1) <= '9'))) {
				{
				int _cnt184=0;
				_loop184:
				do {
					if (((LA(1) >= '0' && LA(1) <= '9'))) {
						mDigit(false);
					}
					else {
						if ( _cnt184>=1 ) { break _loop184; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
					}
					
					_cnt184++;
				} while (true);
				}
				{
				if ((LA(1)=='e')) {
					mExponent(false);
				}
				else {
				}
				
				}
				if ( inputState.guessing==0 ) {
					_ttype = Real;
				}
			}
			else {
			}
			
			}
		}
		else if (((LA(1) >= '0' && LA(1) <= '9')) && (true)) {
			{
			int _cnt187=0;
			_loop187:
			do {
				if (((LA(1) >= '0' && LA(1) <= '9'))) {
					mDigit(false);
				}
				else {
					if ( _cnt187>=1 ) { break _loop187; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				
				_cnt187++;
			} while (true);
			}
			if ( inputState.guessing==0 ) {
				_ttype = Integer;
			}
		}
		else {
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mCurrency(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = Currency;
		int _saveIndex;
		
		{
		switch ( LA(1)) {
		case '$':
		{
			match('$');
			break;
		}
		case '\u00a3':  case '\u00a4':  case '\u00a5':
		{
			matchRange('\u00a3','\u00a5');
			break;
		}
		case '\u09f2':  case '\u09f3':
		{
			matchRange('\u09f2','\u09f3');
			break;
		}
		case '\u0e3f':
		{
			match('\u0e3f');
			break;
		}
		case '\u20a0':  case '\u20a1':  case '\u20a2':  case '\u20a3':
		case '\u20a4':
		{
			matchRange('\u20a0','\u20a4');
			break;
		}
		case '\u20a6':  case '\u20a7':  case '\u20a8':  case '\u20a9':
		case '\u20aa':  case '\u20ab':
		{
			matchRange('\u20a6','\u20ab');
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		{
		switch ( LA(1)) {
		case '0':  case '1':  case '2':  case '3':
		case '4':  case '5':  case '6':  case '7':
		case '8':  case '9':
		{
			{
			int _cnt194=0;
			_loop194:
			do {
				if (((LA(1) >= '0' && LA(1) <= '9'))) {
					mDigit(false);
				}
				else {
					if ( _cnt194>=1 ) { break _loop194; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				
				_cnt194++;
			} while (true);
			}
			{
			if ((LA(1)=='.')) {
				match('.');
				{
				_loop197:
				do {
					if (((LA(1) >= '0' && LA(1) <= '9'))) {
						mDigit(false);
					}
					else {
						break _loop197;
					}
					
				} while (true);
				}
			}
			else {
			}
			
			}
			break;
		}
		case '.':
		{
			match('.');
			{
			int _cnt199=0;
			_loop199:
			do {
				if (((LA(1) >= '0' && LA(1) <= '9'))) {
					mDigit(false);
				}
				else {
					if ( _cnt199>=1 ) { break _loop199; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
				}
				
				_cnt199++;
			} while (true);
			}
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mODBCDateTime(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ODBCDateTime;
		int _saveIndex;
		
		match('{');
		{
		switch ( LA(1)) {
		case '\t':  case '\n':  case '\r':  case ' ':
		{
			mWhitespace(false);
			break;
		}
		case 'd':  case 't':
		{
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		{
		if ((LA(1)=='t') && (LA(2)=='s')) {
			match("ts");
		}
		else if ((LA(1)=='t') && (_tokenSet_6.member(LA(2)))) {
			match('t');
		}
		else if ((LA(1)=='d')) {
			match('d');
		}
		else {
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		
		}
		{
		switch ( LA(1)) {
		case '\t':  case '\n':  case '\r':  case ' ':
		{
			mWhitespace(false);
			break;
		}
		case '\'':  case 'n':
		{
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		{
		switch ( LA(1)) {
		case 'n':
		{
			match('n');
			break;
		}
		case '\'':
		{
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		match('\'');
		{
		_loop206:
		do {
			if ((_tokenSet_7.member(LA(1)))) {
				matchNot('\'');
			}
			else {
				break _loop206;
			}
			
		} while (true);
		}
		match('\'');
		{
		_loop210:
		do {
			if ((LA(1)=='\'')) {
				match('\'');
				{
				_loop209:
				do {
					if ((_tokenSet_7.member(LA(1)))) {
						matchNot('\'');
					}
					else {
						break _loop209;
					}
					
				} while (true);
				}
				match('\'');
			}
			else {
				break _loop210;
			}
			
		} while (true);
		}
		{
		switch ( LA(1)) {
		case '\t':  case '\n':  case '\r':  case ' ':
		{
			mWhitespace(false);
			break;
		}
		case '}':
		{
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		match('}');
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mNonQuotedIdentifier(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = NonQuotedIdentifier;
		int _saveIndex;
		
		boolean synPredMatched214 = false;
		if (((_tokenSet_8.member(LA(1))) && (_tokenSet_0.member(LA(2))))) {
			int _m214 = mark();
			synPredMatched214 = true;
			inputState.guessing++;
			try {
				{
				mCurrency(false);
				}
			}
			catch (RecognitionException pe) {
				synPredMatched214 = false;
			}
			rewind(_m214);
inputState.guessing--;
		}
		if ( synPredMatched214 ) {
			mCurrency(false);
			if ( inputState.guessing==0 ) {
				_ttype = Currency;
			}
		}
		else if ((_tokenSet_9.member(LA(1))) && (true)) {
			{
			switch ( LA(1)) {
			case 'a':  case 'b':  case 'c':  case 'd':
			case 'e':  case 'f':  case 'g':  case 'h':
			case 'i':  case 'j':  case 'k':  case 'l':
			case 'm':  case 'n':  case 'o':  case 'p':
			case 'q':  case 'r':  case 's':  case 't':
			case 'u':  case 'v':  case 'w':  case 'x':
			case 'y':  case 'z':
			{
				matchRange('a','z');
				break;
			}
			case '_':
			{
				match('_');
				break;
			}
			case '#':
			{
				match('#');
				break;
			}
			default:
				if (((LA(1) >= '\u0080' && LA(1) <= '\ufffe'))) {
					matchRange('\u0080','\ufffe');
				}
			else {
				throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
			}
			}
			}
			{
			_loop217:
			do {
				if ((_tokenSet_10.member(LA(1)))) {
					mLetter(false);
				}
				else if (((LA(1) >= '0' && LA(1) <= '9'))) {
					mDigit(false);
				}
				else {
					break _loop217;
				}
				
			} while (true);
			}
		}
		else {
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		
		_ttype = testLiteralsTable(_ttype);
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mQuotedIdentifier(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = QuotedIdentifier;
		int _saveIndex;
		
		{
		switch ( LA(1)) {
		case '[':
		{
			match('[');
			{
			_loop221:
			do {
				if ((_tokenSet_11.member(LA(1)))) {
					matchNot(']');
				}
				else {
					break _loop221;
				}
				
			} while (true);
			}
			match(']');
			{
			_loop225:
			do {
				if ((LA(1)==']')) {
					match(']');
					{
					_loop224:
					do {
						if ((_tokenSet_11.member(LA(1)))) {
							matchNot(']');
						}
						else {
							break _loop224;
						}
						
					} while (true);
					}
					match(']');
				}
				else {
					break _loop225;
				}
				
			} while (true);
			}
			break;
		}
		case '"':
		{
			match('"');
			{
			_loop227:
			do {
				if ((_tokenSet_12.member(LA(1)))) {
					matchNot('"');
				}
				else {
					break _loop227;
				}
				
			} while (true);
			}
			match('"');
			{
			_loop231:
			do {
				if ((LA(1)=='"')) {
					match('"');
					{
					_loop230:
					do {
						if ((_tokenSet_12.member(LA(1)))) {
							matchNot('"');
						}
						else {
							break _loop230;
						}
						
					} while (true);
					}
					match('"');
				}
				else {
					break _loop231;
				}
				
			} while (true);
			}
			break;
		}
		default:
		{
			throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());
		}
		}
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mVariable(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = Variable;
		int _saveIndex;
		
		match('@');
		{
		int _cnt234=0;
		_loop234:
		do {
			if ((_tokenSet_10.member(LA(1)))) {
				mLetter(false);
			}
			else if (((LA(1) >= '0' && LA(1) <= '9'))) {
				mDigit(false);
			}
			else {
				if ( _cnt234>=1 ) { break _loop234; } else {throw new NoViableAltForCharException((char)LA(1), getFilename(), getLine(), getColumn());}
			}
			
			_cnt234++;
		} while (true);
		}
		_ttype = testLiteralsTable(_ttype);
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mASCIIStringLiteral(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = ASCIIStringLiteral;
		int _saveIndex;
		
		match('\'');
		{
		_loop237:
		do {
			if ((_tokenSet_7.member(LA(1)))) {
				matchNot('\'');
			}
			else {
				break _loop237;
			}
			
		} while (true);
		}
		match('\'');
		{
		_loop241:
		do {
			if ((LA(1)=='\'')) {
				match('\'');
				{
				_loop240:
				do {
					if ((_tokenSet_7.member(LA(1)))) {
						matchNot('\'');
					}
					else {
						break _loop240;
					}
					
				} while (true);
				}
				match('\'');
			}
			else {
				break _loop241;
			}
			
		} while (true);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	public final void mUnicodeStringLiteral(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = UnicodeStringLiteral;
		int _saveIndex;
		
		match('n');
		match('\'');
		{
		_loop244:
		do {
			if ((_tokenSet_7.member(LA(1)))) {
				matchNot('\'');
			}
			else {
				break _loop244;
			}
			
		} while (true);
		}
		match('\'');
		{
		_loop248:
		do {
			if ((LA(1)=='\'')) {
				match('\'');
				{
				_loop247:
				do {
					if ((_tokenSet_7.member(LA(1)))) {
						matchNot('\'');
					}
					else {
						break _loop247;
					}
					
				} while (true);
				}
				match('\'');
			}
			else {
				break _loop248;
			}
			
		} while (true);
		}
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	protected final void mHexLiteral(boolean _createToken) throws RecognitionException, CharStreamException, TokenStreamException {
		int _ttype; Token _token=null; int _begin=text.length();
		_ttype = HexLiteral;
		int _saveIndex;
		
		if ( _createToken && _token==null && _ttype!=Token.SKIP ) {
			_token = makeToken(_ttype);
			_token.setText(new String(text.getBuffer(), _begin, text.length()-_begin));
		}
		_returnToken = _token;
	}
	
	
	private static final long[] mk_tokenSet_0() {
		long[] data = new long[1025];
		data[0]=288019269919178752L;
		return data;
	}
	public static final BitSet _tokenSet_0 = new BitSet(mk_tokenSet_0());
	private static final long[] mk_tokenSet_1() {
		long[] data = new long[3072];
		data[0]=103079215104L;
		data[1]=576460745860972544L;
		for (int i = 2; i<=1022; i++) { data[i]=-1L; }
		data[1023]=9223372036854775807L;
		return data;
	}
	public static final BitSet _tokenSet_1 = new BitSet(mk_tokenSet_1());
	private static final long[] mk_tokenSet_2() {
		long[] data = new long[2048];
		data[0]=-9217L;
		for (int i = 1; i<=1022; i++) { data[i]=-1L; }
		data[1023]=9223372036854775807L;
		return data;
	}
	public static final BitSet _tokenSet_2 = new BitSet(mk_tokenSet_2());
	private static final long[] mk_tokenSet_3() {
		long[] data = new long[2048];
		data[0]=-4398046511105L;
		for (int i = 1; i<=1022; i++) { data[i]=-1L; }
		data[1023]=9223372036854775807L;
		return data;
	}
	public static final BitSet _tokenSet_3 = new BitSet(mk_tokenSet_3());
	private static final long[] mk_tokenSet_4() {
		long[] data = new long[2048];
		data[0]=-145135534866433L;
		for (int i = 1; i<=1022; i++) { data[i]=-1L; }
		data[1023]=9223372036854775807L;
		return data;
	}
	public static final BitSet _tokenSet_4 = new BitSet(mk_tokenSet_4());
	private static final long[] mk_tokenSet_5() {
		long[] data = new long[1025];
		data[0]=288019269919178752L;
		data[1]=137438953472L;
		return data;
	}
	public static final BitSet _tokenSet_5 = new BitSet(mk_tokenSet_5());
	private static final long[] mk_tokenSet_6() {
		long[] data = new long[1025];
		data[0]=554050790912L;
		data[1]=70368744177664L;
		return data;
	}
	public static final BitSet _tokenSet_6 = new BitSet(mk_tokenSet_6());
	private static final long[] mk_tokenSet_7() {
		long[] data = new long[2048];
		data[0]=-549755813889L;
		for (int i = 1; i<=1022; i++) { data[i]=-1L; }
		data[1023]=9223372036854775807L;
		return data;
	}
	public static final BitSet _tokenSet_7 = new BitSet(mk_tokenSet_7());
	private static final long[] mk_tokenSet_8() {
		long[] data = new long[1025];
		data[0]=68719476736L;
		data[2]=240518168576L;
		data[39]=3377699720527872L;
		data[56]=-9223372036854775808L;
		data[130]=17450452123648L;
		return data;
	}
	public static final BitSet _tokenSet_8 = new BitSet(mk_tokenSet_8());
	private static final long[] mk_tokenSet_9() {
		long[] data = new long[3072];
		data[0]=34359738368L;
		data[1]=576460745860972544L;
		for (int i = 2; i<=1022; i++) { data[i]=-1L; }
		data[1023]=9223372036854775807L;
		return data;
	}
	public static final BitSet _tokenSet_9 = new BitSet(mk_tokenSet_9());
	private static final long[] mk_tokenSet_10() {
		long[] data = new long[3072];
		data[0]=34359738368L;
		data[1]=576460745860972545L;
		for (int i = 2; i<=1022; i++) { data[i]=-1L; }
		data[1023]=9223372036854775807L;
		return data;
	}
	public static final BitSet _tokenSet_10 = new BitSet(mk_tokenSet_10());
	private static final long[] mk_tokenSet_11() {
		long[] data = new long[2048];
		data[0]=-1L;
		data[1]=-536870913L;
		for (int i = 2; i<=1022; i++) { data[i]=-1L; }
		data[1023]=9223372036854775807L;
		return data;
	}
	public static final BitSet _tokenSet_11 = new BitSet(mk_tokenSet_11());
	private static final long[] mk_tokenSet_12() {
		long[] data = new long[2048];
		data[0]=-17179869185L;
		for (int i = 1; i<=1022; i++) { data[i]=-1L; }
		data[1023]=9223372036854775807L;
		return data;
	}
	public static final BitSet _tokenSet_12 = new BitSet(mk_tokenSet_12());
	
	}
