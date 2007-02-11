package com.quantum.sql.grammar;

import antlr.CommonAST;
import antlr.Token;

public class SqlAST extends CommonAST {
	int line;
	int column;
	
	static final long serialVersionUID = 1;
	
	public SqlAST() {
		super();
	}

	public SqlAST(Token tok) {
		super(tok);
	}
	public void initialize(final Token token)
	{
        super.initialize(token);
        line = token.getLine();
        column = token.getColumn();
	}
    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

}
