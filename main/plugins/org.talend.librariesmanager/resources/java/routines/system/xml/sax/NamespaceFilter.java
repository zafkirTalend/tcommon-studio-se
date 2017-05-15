package routines.system.xml.sax;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.XMLFilterImpl;

/**
 *
 */
public class NamespaceFilter extends XMLFilterImpl {

    private Matcher matcher;

    private Stack<Context> nsStack = new Stack<Context>();

    public NamespaceFilter() {
    }

    public NamespaceFilter(ContentHandler contentHandler, Matcher matcher) {
        setContentHandler(contentHandler);
        setMatcher(matcher);
    }

    public void setMatcher(Matcher matcher) {
        this.matcher = matcher;
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        if (matchNamespace(uri)) {
            nsStack.peek().setMapping(prefix, uri);
        } else {
            super.startPrefixMapping(prefix, uri);
        }
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
        if (nsStack.isEmpty()) {
            return;
        }
        String uri = nsStack.peek().getUri(prefix);
        if (uri != null && matchNamespace(uri)) {
            return;
        } else {
            super.endPrefixMapping(prefix);
        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        nsStack.push(new Context());
        if (matchNamespace(uri)) {
            super.startElement("", localName, localName, atts);
        } else {
            super.startElement(uri, localName, qName, atts);
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (matchNamespace(uri)) {
            super.endElement("", localName, localName);
        } else {
            super.endElement(uri, localName, qName);
        }
        nsStack.pop();
    }

    private boolean matchNamespace(String uri) {
        if (uri.length() != 0 && matcher != null && matcher.matches(uri)) {
            return true;
        }
        return false;
    }

    static class Context {
        private Map<String, String> prefixToUriMap;

        public void setMapping(String prefix, String uri) {
            if (prefixToUriMap == null) {
                prefixToUriMap = new HashMap<String, String>();
            }
            prefixToUriMap.put(uri, prefix);
        }

        public String getUri(String prefix) {
            return prefixToUriMap != null ? prefixToUriMap.get(prefix) : null;
        }
    }

    public interface Matcher {

        boolean matches(String uri);
    }

}
