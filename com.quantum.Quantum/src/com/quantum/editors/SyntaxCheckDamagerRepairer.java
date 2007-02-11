package com.quantum.editors;

import org.eclipse.jface.text.ITypedRegion;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.TextPresentation;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.ITokenScanner;
import org.eclipse.jface.text.rules.Token;

// I needed to subclass this for constructs like INNER JOIN
// 2 Keywords and spaces got ignored so the range length became too short.

public class SyntaxCheckDamagerRepairer extends DefaultDamagerRepairer {

	public SyntaxCheckDamagerRepairer(ITokenScanner scanner) {
		super(scanner);
	}
    
	public void createPresentation(TextPresentation presentation, ITypedRegion region) {
		int lastStart= region.getOffset();
		int length= 0;
		boolean firstToken= true;
		IToken lastToken= Token.UNDEFINED;
		TextAttribute lastAttribute= getTokenTextAttribute(lastToken);

		fScanner.setRange(fDocument, lastStart, region.getLength());

		while (true) 
		{
			IToken token= fScanner.nextToken();
			if (token.isEOF())
				break;
		
			TextAttribute attribute= getTokenTextAttribute(token);
				if (!firstToken)
                {
                    try
                    {
                        addRange(presentation, lastStart, length, lastAttribute);
                    }catch(Exception e){
                        System.out.println("createPresentation (in the loop): " + e.getMessage());
                    }
                }
				firstToken= false;
				lastAttribute= attribute;
				lastStart= fScanner.getTokenOffset();
				length= fScanner.getTokenLength();
				System.out.println(lastStart + " " + length + " " + (lastStart+length) );
		}
        try
        {
            addRange(presentation, lastStart, length, lastAttribute);
        }catch(Exception e){
            System.out.println("createPresentation: " + e.getMessage());
        }
	}

}
