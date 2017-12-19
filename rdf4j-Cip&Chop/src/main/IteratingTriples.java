package b;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.eclipse.rdf4j.rio.RDFParseException;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.rio.helpers.StatementCollector;

public class IteratingTriples {

	public static void main(String[] args) throws IOException {

		java.net.URL documentUrl = new URL("https://www.w3.org/2006/vcard/ns#Home");
		InputStream inputStream = documentUrl.openStream();

		
		RDFFormat format = Rio.getParserFormatForFileName(documentUrl.toString()).orElse(RDFFormat.RDFXML);
		RDFParser rdfParser = Rio.createParser(format);
		
	//	org.openrdf.model.Graph myGraph = new org.openrdf.model.impl.GraphImpl();
	//	StatementCollector collector = new StatementCollector(myGraph);
	//	rdfParser.setRDFHandler(collector);
		
		try {
			   rdfParser.parse(inputStream, documentUrl.toString());
			}
			catch (IOException e) {
			  // handle IO problems (e.g. the file could not be read)
			}
			catch (RDFParseException e) {
			  // handle unrecoverable parse error
			}
			catch (RDFHandlerException e) {
			  // handle a problem encountered by the RDFHandler
			}
	}

}
