package neo4jWriter;

import java.io.File;
import java.io.IOException;
import java.sql.Date;

import org.apache.commons.beanutils.ConvertUtils;
import org.neo4j.graphdb.DynamicLabel;
import org.neo4j.graphdb.GraphDatabaseService;
import org.neo4j.graphdb.Label;
import org.neo4j.graphdb.Node;
import org.neo4j.graphdb.Relationship;
import org.neo4j.graphdb.RelationshipType;
import org.neo4j.graphdb.ResourceIterator;
import org.neo4j.graphdb.Transaction;
import org.neo4j.kernel.impl.util.FileUtils;

import oracleReader.oracleReaderImpl;

public class neo4jWriterImpl implements neo4jWriter {

	private static final String DB_PATH = "http://localhost:7474/db/data/";


	int count=0;
	neo4jConn conn = new neo4jConn();
	GraphDatabaseService graphDb=conn.createdb();
	
	
	Label labelP = DynamicLabel.label("Person");
	Label labelR = DynamicLabel.label("Room");
	
	private static enum RelTypes implements RelationshipType  
    {  
		Valid_Access, Put_Off_Site, No_Reader_Access, Tailgate, APB_Violation, Invalid_Pin, error
    } 
	String VA="Valid_Access";
	public void datain(oracleReaderImpl reader)
	{
		int id;
		int access_key;
		Date date_time;
		String system_id;
		String reader_number;
		String reader_id;
		String reader_name;
		String access_event;
		String adacs_card_number;
		String adacs_card_type;
		String surname;
		String forename;
		String location_name;
		String date1;
		String text1;
		String text2;
		String text3;
		String text4;
		String skyline_code;
		
		
		
		
		Relationship relationship;
		
		
		//System.out.println(reader.getID(499)+" "+reader.getACCESS_KEY(499)+" "+reader.getSystemid(499)+" "+reader.getReader_id(499)+" "+reader.getADACS_card_number(499)+" "+reader.getADACS_card_type(499)+" "+reader.getSkyline(499));
		for(int i=0;i<1000;i++)
		{
		    if(reader.getID(i)!=0)
		    {
		    	adacs_card_number=reader.getADACS_card_number(i);
		    	adacs_card_type=reader.getADACS_card_type(i);
		    	surname=reader.getsurname(i);
		    	forename=reader.getforename(i);
		    	text1=reader.getText1(i);
		    	text2=reader.getText2(i);
		    	text3=reader.getText3(i);
		    	//node.person
		    	
		    	id=reader.getID(i);
		    	access_key=reader.getACCESS_KEY(i);
		    	date_time=reader.getDATE_TIME(i);
		    	access_event=reader.getAccess_event(i);
		    	location_name=reader.getlocation(i);
		    	//relationship
		    	
		    	reader_id=reader.getReader_id(i);
		    	reader_number=reader.getReader_number(i);
		    	reader_name=reader.getReader_name(i);
		    	system_id=reader.getSystemid(i);
		    	skyline_code=reader.getSkyline(i);
		    	//node.room

		    	//System.out.println("id");
		    	Transaction tx = graphDb.beginTx();
		    	try
		    	{
		    		Node[] newPerson=new Node[1000];
		    		Node[] newRoom=new Node[1000];
		    		ResourceIterator<Node> temP=null;
		    		ResourceIterator<Node> temR=null;
		    		Node tempNodeP=null;
		    		Node tempNodeR=null;

		    		temP=graphDb.findNodesByLabelAndProperty(labelP, "adacs_card_number",adacs_card_number).iterator();
		    		temR=graphDb.findNodesByLabelAndProperty(labelR, "reader_id",reader_id).iterator();
		    		//System.out.println(adacs_card_number);
		    		//**********************************************node person***********************
		    		if(temP.hasNext())
		            {
		                tempNodeP=temP.next();
		      
		            }
		    		
		    		if(temR.hasNext())
		    		{
		    			tempNodeR=temR.next();
		    		}
		            
		    		if(tempNodeP==null)
		    		{
		    			System.out.println("create new person");
		    			
		    			newPerson[i]=graphDb.createNode();
		    			newPerson[i].addLabel(labelP);
		    			newPerson[i].setProperty("adacs_card_number", adacs_card_number);
		    			if(adacs_card_type!=null)
		    			newPerson[i].setProperty("adacs_card_type", adacs_card_type);
		    			if(surname!=null)
		    			newPerson[i].setProperty("surname", surname);
		    			if(forename!=null)
		    			newPerson[i].setProperty("forename", forename);
		    			if(text1!=null)
		    			newPerson[i].setProperty("employee_id", text1);
		    			if(text2!=null)
		    			newPerson[i].setProperty("note", text2);
		    			if(text3!=null)
		    			newPerson[i].setProperty("email", text3);
		    		}else{
		    			newPerson[i]=tempNodeP;
		    			//System.out.println("person: "+newPerson[i].getProperty("adacs_card_number"));
		    		}
		    			
		    		//******************************************node room**********************************
		    		if(tempNodeR==null)
		    		{
		    			System.out.println("create new room");
		    			newRoom[i]=graphDb.createNode();
		    			newRoom[i].addLabel(labelR);
		    			if(reader_id!=null)
		    			newRoom[i].setProperty("reader_id", reader_id);
		    			if(reader_number!=null)
		    			newRoom[i].setProperty("reader_number", reader_number);
		    			if(reader_name!=null)
		    			newRoom[i].setProperty("reader_name", reader_name);
		    			if(system_id!=null)
		    			newRoom[i].setProperty("system_id", system_id);
		    			if(skyline_code!=null)
		    			newRoom[i].setProperty("skyline_code", skyline_code);
		    			
		    			
		    		}else{
		    			newRoom[i]=tempNodeR;
		    			//System.out.println("room: "+newRoom[i].getProperty("reader_id"));
		    		}
		    		
		    		//**************************************relationship******************************************
		    		
		    		if(access_event==null)
		    		{
		    			count++;
		    			relationship = newPerson[i].createRelationshipTo(newRoom[i], RelTypes.error);
		    			relationship.setProperty( "ID", id );
		    			relationship.setProperty( "access_key", access_key );
		    			if(date_time!=null)
		    			relationship.setProperty( "date_time", date_time.getTime() );
		    			if(location_name!=null)
		    			relationship.setProperty( "location", location_name );
		    		}
		    		else if(access_event.equalsIgnoreCase("Valid Access"))
		    		{
		    			count++;
		    			relationship = newPerson[i].createRelationshipTo(newRoom[i], RelTypes.Valid_Access);
		    			relationship.setProperty( "ID", id );
		    			relationship.setProperty( "access_key", access_key );
		    			if(date_time!=null)
			    			relationship.setProperty( "date_time", date_time.getTime() );
			    			if(location_name!=null)
			    			relationship.setProperty( "location", location_name );
		    			
		    		}else if(access_event.equalsIgnoreCase("Put Off Site"))
		    		{
		    			count++;
		    			relationship = newPerson[i].createRelationshipTo(newRoom[i], RelTypes.Put_Off_Site);
		    			relationship.setProperty( "ID", id );
		    			relationship.setProperty( "access_key", access_key );
		    			if(date_time!=null)
			    			relationship.setProperty( "date_time", date_time.getTime() );
			    			if(location_name!=null)
			    			relationship.setProperty( "location", location_name );
		    			
		    		}else if(access_event.equalsIgnoreCase("No Reader Access"))
		    		{
		    			count++;
		    			relationship = newPerson[i].createRelationshipTo(newRoom[i], RelTypes.No_Reader_Access);
		    			relationship.setProperty( "ID", id );
		    			relationship.setProperty( "access_key", access_key );
		    			if(date_time!=null)
			    			relationship.setProperty( "date_time", date_time.getTime() );
			    			if(location_name!=null)
			    			relationship.setProperty( "location", location_name );
		    			
		    		}else if(access_event.equalsIgnoreCase("Tailgate"))
		    		{
		    			count++;
		    			relationship = newPerson[i].createRelationshipTo(newRoom[i], RelTypes.Tailgate);
		    			relationship.setProperty( "ID", id );
		    			relationship.setProperty( "access_key", access_key );
		    			if(date_time!=null)
			    			relationship.setProperty( "date_time", date_time.getTime() );
			    			if(location_name!=null)
			    			relationship.setProperty( "location", location_name );
		    			
		    		}
		    		else if(access_event.equalsIgnoreCase("APB Violation"))
		    		{
		    			count++;
		    			relationship = newPerson[i].createRelationshipTo(newRoom[i], RelTypes.APB_Violation);
		    			relationship.setProperty( "ID", id );
		    			relationship.setProperty( "access_key", access_key );
		    			if(date_time!=null)
			    			relationship.setProperty( "date_time", date_time.getTime() );
			    			if(location_name!=null)
			    			relationship.setProperty( "location", location_name );
		    			
		    		}else if(access_event.equalsIgnoreCase("Invalid Pin"))
		    		{
		    			count++;
		    			relationship = newPerson[i].createRelationshipTo(newRoom[i], RelTypes.Invalid_Pin);
		    			relationship.setProperty( "ID", id );
		    			relationship.setProperty( "access_key", access_key );
		    			if(date_time!=null)
			    			relationship.setProperty( "date_time", date_time.getTime() );
			    			if(location_name!=null)
			    			relationship.setProperty( "location", location_name );
		    			
		    		}
		    			

			    		tx.success();
		    		
		            
		    	    
		    	}
		    	finally
		    	{
		    	    tx.finish();
		    	}
		    	
		    }
		    else{
		    	shutdown();
		    	System.out.println("transfer done!");
				//System.exit(0);
				break;
		    }
		    
		}
		System.out.println("added "+count+" relationships");
		//shutdown();
		
	}


	@Override
	public void cleardb() {
		// TODO Auto-generated method stub
		
		try  
        {  
            FileUtils.deleteRecursively( new File( DB_PATH ) );  
        }  
        catch ( IOException e )  
        {  
            throw new RuntimeException( e );  
        } 
	}


	@Override
	public void shutdown() {
		// TODO Auto-generated method stub
		System.out.println( "Shutting down database ..." );  
        // START SNIPPET: shutdownServer  
        graphDb.shutdown();  
        // END SNIPPET: shutdownServer  
        System.out.println("done!");
        //System.exit(0);
        
	}


	@Override
	public GraphDatabaseService getdb() {
		// TODO Auto-generated method stub
		return graphDb;
	}


	
}
