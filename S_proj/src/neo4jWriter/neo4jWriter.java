package neo4jWriter;

import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Transaction;

import oracleReader.oracleReaderImpl;



public interface neo4jWriter {
	
	void datain(oracleReaderImpl reader);
	
	GraphDatabaseService getdb();
	
	 void shutdown();
	
	void cleardb();

	

	
}
