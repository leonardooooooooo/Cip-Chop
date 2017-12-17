package it.uniroma2.art.teaching.ia2.ia2_2017_2018.rdf4j.examples;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import org.eclipse.rdf4j.model.BNode;
import org.eclipse.rdf4j.model.IRI;
import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Resource;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.Value;
import org.eclipse.rdf4j.model.ValueFactory;
import org.eclipse.rdf4j.model.impl.SimpleValueFactory;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.Rio;

public class PrintTriples {

	private static ValueFactory fact;

	public static void main(String[] args) throws IOException {

		String filename = "prova.ttl";

		InputStream input = PrintTriples.class.getResourceAsStream("/" + filename);
		Model model = Rio.parse(input, "", RDFFormat.TURTLE);

		ValueFactory vf = SimpleValueFactory.getInstance();
		
		IRI leonardo = vf.createIRI("http://example.org/Leonardo");
		IRI dario = vf.createIRI("http://example.org/Dario");


		Model aboutLeonardo = model.filter(leonardo, null, null);
		Model aboutDario = model.filter(dario, null, null);

		for (Statement st: aboutLeonardo) {
			IRI subject = (IRI)st.getSubject();
			IRI predicate = st.getPredicate();


			Value object = st.getObject();


			System.out.print(subject.getLocalName() + " " + predicate.getLocalName() + " ");
			if (object instanceof Literal) {
		
				System.out.println("\"" + ((Literal)object).getLabel() + "\"");
			}
			else if (object instanceof  IRI) {
				System.out.println(((IRI)object).getLocalName());
			}
			else {
				System.out.println(object);
			}
		
				System.out.println("-------------------------------------------");fact = SimpleValueFactory.getInstance();

	
				
		}
		
		for (Statement st: aboutDario) {
			IRI subject = (IRI)st.getSubject();
			IRI predicate = st.getPredicate();


			Value object = st.getObject();


			System.out.print(subject.getLocalName() + " " + predicate.getLocalName() + " ");
			if (object instanceof Literal) {
		
				System.out.println("\"" + ((Literal)object).getLabel() + "\"");
			}
			else if (object instanceof  IRI) {
				System.out.println(((IRI)object).getLocalName());
			}
			else {
				System.out.println(object);
			}
		
				System.out.println("-------------------------------------------");fact = SimpleValueFactory.getInstance();

	
				
		}
	}		
			
		

	
}