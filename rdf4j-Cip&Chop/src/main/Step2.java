package b;

import java.io.IOException;
import java.io.InputStream;

import org.eclipse.rdf4j.model.Model;
import org.eclipse.rdf4j.model.Statement;
import org.eclipse.rdf4j.model.vocabulary.FOAF;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.RepositoryResult;
import org.eclipse.rdf4j.repository.sail.SailRepository;
import org.eclipse.rdf4j.rio.RDFFormat;
import org.eclipse.rdf4j.rio.RDFParseException;
import org.eclipse.rdf4j.rio.Rio;
import org.eclipse.rdf4j.rio.UnsupportedRDFormatException;
import org.eclipse.rdf4j.sail.memory.MemoryStore;

public class Step2 {

	public static void main(String[] args) throws RDFParseException, UnsupportedRDFormatException, IOException {

		//prende file in input e fa query SPARQL
		
		// First load our RDF file as a Model.
		String filename = "prova.ttl";
		InputStream input = Step2.class.getResourceAsStream("/" + filename);
		Model model = Rio.parse(input, "", RDFFormat.TURTLE);

		// Create a new Repository. Here, we choose a database implementation
		// that simply stores everything in main memory.
		Repository db = new SailRepository(new MemoryStore());
		db.initialize();

		// Open a connection to the database
		try (RepositoryConnection conn = db.getConnection()) {
			// add the model
			conn.add(model);

			try (RepositoryResult<Statement> result = conn.getStatements(null, null, null);) {
				while (result.hasNext()) {
					Statement st = result.next();
					//System.out.println("db contains: " + st);
				}
			}
		}
		finally {
			db.shutDown();
		}
	
	
	//-------------------------------QUERY---------------------------------//
	db.initialize();

	// Open a connection to the database
	try (RepositoryConnection conn = db.getConnection()) {
	    String filename1 = "prova.ttl";
	    try (InputStream input1 =
	           Step2.class.getResourceAsStream("/" + filename1)) {
		// aggiunge RDF data dall' inputstream al database
		conn.add(input1, "", RDFFormat.TURTLE );
	    }

	    //  simple SPARQL SELECT-query
	  
	    String queryString = "PREFIX ex: <http://example.org/> \n";
	    queryString += "PREFIX foaf: <" + FOAF.NAMESPACE + "> \n";
	    queryString += "SELECT ?s ?n \n";
	    queryString += "WHERE { \n";
	    queryString += "    ?s a ex:Student; \n";
	    queryString += "       foaf:firstName ?n .";
	    queryString += "}";
	    TupleQuery query = conn.prepareTupleQuery(queryString);
	    
	    /*
	     *  String queryString = "PREFIX ex: <http://example.org/> \n";
	    queryString += "PREFIX foaf: <" + FOAF.NAMESPACE + "> \n";
	    queryString += "SELECT ?s ?n .";
	    queryString += "}";
	    TupleQuery query = conn.prepareTupleQuery(queryString);
	    
	     * 
	     * 
	     * 
	     * 
	     * 
	     * */

	    try (TupleQueryResult result = query.evaluate()) {
		while (result.hasNext()) {
		    BindingSet solution = result.next();
		    // for ?s and ?n
		    System.out.println("?s = " + solution.getValue("s"));
		    System.out.println("?n = " + solution.getValue("n"));
		}
	    }
	}
	finally {
	    db.shutDown();
	}


	// Rio also accepts a java.io.Reader as input for the parser.
	
}
}
