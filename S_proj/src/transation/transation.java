package transation;

import org.neo4j.graphdb.GraphDatabaseService;

import oracleReader.oracleReaderImpl;
import neo4jWriter.neo4jWriterImpl;

public class transation {
	
	
	oracleReaderImpl reader = new oracleReaderImpl();
	neo4jWriterImpl writer =new neo4jWriterImpl();

	
	public int Odata(int start, int end)
	{
		
		
		reader.datareader(start, end);
		//System.out.println(reader.getID(0)+" "+reader.getACCESS_KEY(499)+" "+reader.getSystemid(499)+" "+reader.getReader_id(499)+" "+reader.getADACS_card_number(499)+" "+reader.getADACS_card_type(499)+" "+reader.getSkyline(499));
		//System.out.println(reader.getID(0));
		
		return reader.getID(0);
	}
	
	public GraphDatabaseService getDb()
	{
		GraphDatabaseService db=writer.getdb();
		return db;
		
	}
	
	
	
	public void Ndata()
	{
		//t.createDb(reader);
		//t.shutDown();
		//writer.cleardb();
		writer.datain(reader);
		
	}

}
