package b;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFHandlerException;
import org.eclipse.rdf4j.rio.RDFParseException;
import org.eclipse.rdf4j.rio.RDFParser;
import org.eclipse.rdf4j.rio.RDFWriter;
import org.eclipse.rdf4j.rio.Rio;

public class Step1 {

	public static void main(String[] args) throws IOException {
		// parsing URL in file example-output.ttl

		// open our input document
		java.net.URL documentUrl = new URL("https://www.w3.org/2006/vcard/ns#Home");
		InputStream inputStream = documentUrl.openStream();

		RDFFormat format = Rio.getParserFormatForFileName(documentUrl.toString()).orElse(RDFFormat.RDFXML);

		RDFParser rdfParser = Rio.createParser(format);

		RDFWriter rdfWriter = Rio.createWriter(RDFFormat.TURTLE,
				new FileOutputStream("src/main/resources/example-output.ttl"));

		// link our parser to our writer...
		rdfParser.setRDFHandler(rdfWriter);
		// ...and start the conversion!
		try {
			rdfParser.parse(inputStream, documentUrl.toString());
		} catch (IOException e) {
			// handle IO problems (e.g. the file could not be read)
		} catch (RDFParseException e) {
			// handle unrecoverable parse error
		} catch (RDFHandlerException e) {
			// handle a problem encountered by the RDFHandler
		} finally {
			inputStream.close();
		}

		// Rio also accepts a java.io.Reader as input for the parser.
		Model model = Rio.parse(inputStream, "", RDFFormat.TURTLE);

		// To check that we have correctly read the file, let's print out the model to
		// the screen again
		for (Statement statement : model) {
			System.out.println(statement);

		}
	}

}
