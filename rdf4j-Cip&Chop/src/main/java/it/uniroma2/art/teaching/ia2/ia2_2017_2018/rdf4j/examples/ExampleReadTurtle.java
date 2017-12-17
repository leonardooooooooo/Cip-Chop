package it.uniroma2.art.teaching.ia2.ia2_2017_2018.rdf4j.examples;


import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;
import java.io.IOException;
import java.io.InputStream;

/**
 * RDF Tutorial example 08: Reading a Turtle syntax file to create a Model
 *
 * In this example, we show how you can use the Rio Parser/writer toolkit to read files
 *
 * @see
 */
public class ExampleReadTurtle {

	public static void main(String[] args) throws IOException {

		String filename = "example-data-artists.ttl";

		// read the file 'example-data-artists.ttl' as an InputStream.
		InputStream input = ExampleReadTurtle.class.getResourceAsStream("/" + filename);

		// Rio also accepts a java.io.Reader as input for the parser.
		Model model = Rio.parse(input, "", RDFFormat.TURTLE);

		// To check that we have correctly read the file, let's print out the model to the screen again
		for (Statement statement: model) {
			System.out.println(statement);
		}
	}
}

//https://github.com/eclipse/rdf4j-doc/tree/master/examples/src/main/java/org/eclipse/rdf4j/examples/model