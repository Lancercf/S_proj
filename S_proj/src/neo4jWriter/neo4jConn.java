package neo4jWriter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.ws.rs.core.MediaType;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;
import org.neo4j.graphdb.factory.GraphDatabaseFactory;
import org.neo4j.kernel.impl.util.FileUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class neo4jConn {
	
	
	GraphDatabaseService graphDb;
	
	 public GraphDatabaseService createdb()
	 {

		 Properties p = new Properties();  
		  
	        InputStream in;
			try {
				in = new FileInputStream(new File("Setting.properties"));
				p.load(in); 
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
	         
	      String DBname=p.getProperty("target_name").trim();
	      
		
		GraphDatabaseService graphDb;
		graphDb = new GraphDatabaseFactory()
		          .newEmbeddedDatabaseBuilder( DBname )  
                  .loadPropertiesFromFile("neo4j.properties" )  
                  .newGraphDatabase();
		//registerShutdownHook( graphDb );
		return graphDb;
		 
	 }
	 
	  

	 
	 
	 private static void registerShutdownHook( final GraphDatabaseService graphDb )  
	    {  
	        // Registers a shutdown hook for the Neo4j instance so that it  
	        // shuts down nicely when the VM exits (even if you "Ctrl-C" the  
	        // running application).  
	        Runtime.getRuntime().addShutdownHook( new Thread()  
	        {  
	            @Override  
	            public void run()  
	            {  
	                graphDb.shutdown();  
	            }  
	        } );  
	    }  
}
