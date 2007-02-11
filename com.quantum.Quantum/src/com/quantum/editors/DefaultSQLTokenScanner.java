package com.quantum.editors;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.ITokenScanner;

import com.quantum.model.ISQLSyntaxModel;
import java.util.Map;

public class DefaultSQLTokenScanner implements ITokenScanner {
	
	private ISQLSyntaxModel model = null;
	
    public DefaultSQLTokenScanner(ISQLSyntaxModel model, Map tokens) {
		super();
		this.model = model;
		this.model.setTokens(tokens); // these tokens are not QTokens..? No, they define the coloring
        this.model.read(); // perform the first lexical analysis
	}
    
	public void setRange(IDocument document, int offset, int length) {
		// this is where the calls come in
        // this is called for each region, and that slows things down.
        // and leads to Stack overflows....?
//        System.out.println("setRange: " + offset + "-" + (offset + length));
		model.readUpTo(offset, offset + length);
    }

	public IToken nextToken() {
		return model.nextToken();
	}
	

	public int getTokenOffset() {
		return model.getTokenOffset();
	}

	public int getTokenLength() {
		return model.getTokenLength();
	}
}
