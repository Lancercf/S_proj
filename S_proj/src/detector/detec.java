package detector;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.swing.JTextArea;

import neo4jWriter.neo4jWriterImpl;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;


public class detec {
	
	
	public void person(JTextArea textArea,String Pnum)
	{

		textArea.setText(null);
	    neo4jWriterImpl writer =new neo4jWriterImpl();
		GraphDatabaseService db = writer.getdb();
	    ExecutionEngine engine = new ExecutionEngine( db );  
	    ExecutionResult result = engine.execute( "MATCH (Person) where Person.adacs_card_number='"+Pnum+"' RETURN Person.adacs_card_number as CardNumber, Person.adacs_card_type as CardType, Person.surname as Surname, Person.forename as Forename, Person.employee_id as EmployeeId, Person.note as Note, Person.email as EMail" );  

	    System.out.println();
	    System.out.println(result.toString());  
	    System.out.println("the detail of person with card number: "+Pnum+": ");

	    
	    for(Map<String, Object> row : result) 
	    {  
		    
	        for(Entry<String, Object> column : row.entrySet()) 
	        {
	    	
	        	String rows = "";
	            rows += column.getKey() + ": " + column.getValue() + ";   ";  
	            System.out.println(rows);
	        }  
	     
	        
		    textArea.setCaretPosition(textArea.getText().length());

	         
	    }


	    System.out.println();
	    System.out.println("the activities of this person:");
	    ExecutionResult result2 = engine.execute("match (Person)-[Access_event]-(Room) where Person.adacs_card_number='"+Pnum+"' return Person.adacs_card_number as CardNumber, type(Access_event) as Event, Room.reader_id as RoomId");
	    for(Map<String, Object> row : result2) 
	    {  
		    String rows = "";
	        for(Entry<String, Object> column : row.entrySet()) 
	        {
	    	
	            rows += column.getKey() + ": " + column.getValue() + ";   ";  
	        }  
	     
	        System.out.println(rows);
		    textArea.setCaretPosition(textArea.getText().length());

	        rows = ""; 
	    }
	    System.out.println();
	    System.out.println("Cypher query is:");
	    System.out.println("match (Person)-[Access_event]-(Room) where Person.adacs_card_number='"+Pnum+"' return Person.adacs_card_number as CardNumber, type(Access_event) as Event, Room.reader_id as RoomId");
	    System.out.println("Graphic query is:");
	    System.out.println("match (Person)-[Access_event]-(Room) where Person.adacs_card_number='"+Pnum+"' return Person, Access_event, Room limit 1000");

	    
	    writer.shutdown();
	}
	
	public void room(JTextArea textArea,String Rid)
	{

		textArea.setText(null);
		neo4jWriterImpl writer =new neo4jWriterImpl();
		GraphDatabaseService db = writer.getdb();
	    ExecutionEngine engine = new ExecutionEngine( db );  
	    ExecutionResult result = engine.execute( "MATCH (Room) where Room.reader_id='"+Rid+"' RETURN Room.reader_id as ReaderId, Room.reader_number as ReaderNumber, Room.reader_name as RoomName, Room.system_id as SystemId, Room.skyline_code as SkylineCode" );  

	    System.out.println(result.toString());  
	    System.out.println("the detail of Room with reader id: "+Rid+": ");
	
	    for(Map<String, Object> row : result) 
	    {  
		    
	        for(Entry<String, Object> column : row.entrySet()) 
	        {
	        	String rows = "";
	            rows = column.getKey() + ": " + column.getValue() + ";   ";  
	            System.out.println(rows);
	        }  
	     

	    }

	    System.out.println();
	    System.out.println("the events of this room:");
	    ExecutionResult result2 = engine.execute("match (Person)-[Access_event]-(Room) where Room.reader_id='"+Rid+"' return Person.adacs_card_number as CardNumber, type(Access_event) as Event, Room.reader_id as RoomId");
	    for(Map<String, Object> row : result2) 
	    {  
		    String rows = "";
	        for(Entry<String, Object> column : row.entrySet()) 
	        {
	    	
	            rows += column.getKey() + ": " + column.getValue() + ";   ";  
	        }  
	     
	        System.out.println(rows);
	        rows = ""; 
	    }
	    System.out.println();
	    System.out.println("Cypher query is:");
	    System.out.println("match (Person)-[Access_event]-(Room) where Room.reader_id='"+Rid+"' return Person.adacs_card_number as CardNumber, type(Access_event) as Event, Room.reader_id as RoomId");
	    System.out.println("Graphic query is:");
	    System.out.println("match (Person)-[Access_event]-(Room) where Room.reader_id='"+Rid+"' return Person, Access_event, Room limit 1000");

	    
	    writer.shutdown();
	}
	
	public void AcE(JTextArea textArea,String Atype)
	{
		textArea.setText(null);
		neo4jWriterImpl writer =new neo4jWriterImpl();
		GraphDatabaseService db = writer.getdb();
		System.out.println();
		System.out.println("all access events:");
	    ExecutionEngine engine = new ExecutionEngine( db );  
	    ExecutionResult result = engine.execute( "MATCH (Person)-[Access_event]-(Room) RETURN DISTINCT type(Access_event) as AccessEvent" );  

	    System.out.println(result.toString()); 
	    for(Map<String, Object> row : result) 
	    {  
		    String rows = "";
	        for(Entry<String, Object> column : row.entrySet()) 
	        {
	    	
	            rows += column.getKey() + ": " + column.getValue() + ";   ";  
	        }  
	     
	        System.out.println(rows);
	        rows = ""; 
	    }
	    
	    System.out.println();
	    System.out.println("the events of this activity:");
	    ExecutionResult result2 = engine.execute("MATCH (Person)-[Access_event]->(Room) WHERE type(Access_event)='"+Atype+"' RETURN  Person.adacs_card_number as CardNumber, type(Access_event), Room.reader_id as RoomId");
	    for(Map<String, Object> row : result2) 
	    {  
		    String rows = "";
	        for(Entry<String, Object> column : row.entrySet()) 
	        {
	    	
	            rows += column.getKey() + ": " + column.getValue() + ";   ";  
	        }  
	     
	        System.out.println(rows);
	        rows = ""; 
	    }
	    System.out.println();
	    System.out.println("Cypher query is:");
	    System.out.println("MATCH (Person)-[Access_event]->(Room) WHERE type(Access_event)='"+Atype+"' RETURN  Person.adacs_card_number as CardNumber, type(Access_event), Room.reader_id as RoomId");
	    System.out.println("Graphic query is:");
	    System.out.println("MATCH (Person)-[Access_event]->(Room) WHERE type(Access_event)='"+Atype+"' RETURN  Person, Access_event, Room limit 1000");


	    
	    writer.shutdown();
	}
	
	public void P_R(JTextArea textArea,String Pnum,String Rid)
	{
		textArea.setText(null);
		neo4jWriterImpl writer =new neo4jWriterImpl();
		GraphDatabaseService db = writer.getdb();
		System.out.println();
	    ExecutionEngine engine = new ExecutionEngine( db );  
	    ExecutionResult result = engine.execute( "MATCH (Person) where Person.adacs_card_number='"+Pnum+"' RETURN Person.adacs_card_number as CardNumber, Person.adacs_card_type as CardType, Person.surname as Surname, Person.forename as Forename, Person.employee_id as EmployeeId, Person.note as Note, Person.email as EMail" );  

	    System.out.println(result.toString());  
	    System.out.println("the detail of person with card number: "+Pnum+": ");
	
	    for(Map<String, Object> row : result) 
	    {  
		    
	        for(Entry<String, Object> column : row.entrySet()) 
	        {
	    	    String rows = "";
	            rows += column.getKey() + ": " + column.getValue() + ";   ";  
	            System.out.println(rows);
	        }  
	     
	        
	    }
	    
	    ExecutionResult result2 = engine.execute( "MATCH (Room) where Room.reader_id='"+Rid+"' RETURN Room.reader_id as ReaderId, Room.reader_number as ReaderNumber, Room.reader_name as RoomName, Room.system_id as SystemId, Room.skyline_code as SkylineCode" );  

	    System.out.println();
	    System.out.println(result2.toString());  
	    System.out.println("the detail of Room with reader id: "+Rid+": ");
	
	    for(Map<String, Object> row : result2) 
	    {  
		    
	        for(Entry<String, Object> column : row.entrySet()) 
	        {
	    	    String rows = "";
	            rows += column.getKey() + ": " + column.getValue() + ";   ";  
	            System.out.println(rows);
	        }  
	     
	        
	    }
	    

		System.out.println();
	    System.out.println("the activities of this person:");
	    ExecutionResult result3 = engine.execute("match (Person)-[Access_event]-(Room) where Person.adacs_card_number='"+Pnum+"' and Room.reader_id='"+Rid+"' return Person.adacs_card_number as CardNumber, type(Access_event) as Event, Room.reader_id as RoomId");
	    for(Map<String, Object> row : result3) 
	    {  
		    String rows = "";
	        for(Entry<String, Object> column : row.entrySet()) 
	        {
	    	
	            rows += column.getKey() + ": " + column.getValue() + ";   ";  
	        }  
	     
	        System.out.println(rows);
	        rows = ""; 
	    }
	    System.out.println("Cypher query is:");
	    System.out.println("match (Person)-[Access_event]-(Room) where Person.adacs_card_number='"+Pnum+"' and Room.reader_id='"+Rid+"' return Person.adacs_card_number as CardNumber, type(Access_event) as Event, Room.reader_id as RoomId");
	    System.out.println("Graphic query is:");
	    System.out.println("match (Person)-[Access_event]-(Room) where Person.adacs_card_number='"+Pnum+"' and Room.reader_id='"+Rid+"' return Person, Access_event, Room limit 1000");
	    
	    writer.shutdown();
	}
	
	public void Frq(JTextArea textArea) throws ParseException
	{
		textArea.setText(null);
		neo4jWriterImpl writer =new neo4jWriterImpl();
		GraphDatabaseService db = writer.getdb();
		 ExecutionEngine engine = new ExecutionEngine( db );  
		    ExecutionResult result = engine.execute( "MATCH (Person)-[Access_event]->(Room) RETURN count(*)" );  

		    float amont=0;
		    for(Map<String, Object> row : result) 
		    {  
		    	
			    String count = "";
			   
		        for(Entry<String, Object> column : row.entrySet()) 
		        {
		        	
		            count +=column.getValue();  

		        }  
		     
		        amont=Integer.parseInt(count);

		        //System.out.println("amont/:"+amont);
		        
		         
		    }

		    
		    ExecutionResult result2 = engine.execute("match (a)-[r]->(b) return b.reader_id as TO,r.location as FROM,r.date_time as TIME,count(r.date_time) as COUNT");
		    for(Map<String, Object> row : result2) 
		    {  
		    	String rows = "";
			    String num="";
			    String time="";
			    Date ttt;
			    long tt;
		        for(Entry<String, Object> column : row.entrySet()) 
		        {
		        	String value="";
		        	value+=column.getValue();
		        	if(column.getKey().equalsIgnoreCase("TIME"))
		        	{
		        		String check="";
		        		check+=column.getValue();
		        		if(!check.equalsIgnoreCase("null"))
		        		{

		        			time+=column.getValue();
		        		tt=Long.parseLong(time);
		        		tt=tt*1000;
		        		ttt= new Date(tt);

		        		
		        		SimpleDateFormat sdf= new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		        		time=sdf.format(ttt);
		        		
		        		value=time;

		        		

		        		}
		        			
		        	}
		    	if(column.getKey().equalsIgnoreCase("COUNT"))
		    	{
		    		num+=column.getValue();
		    		
		    	}
		    	    
		            rows = column.getKey() + ": " + value + ";   ";  
		            System.out.print(rows);
		            rows="";
		        }  
		     
		        float frq=0;
		        frq=Integer.parseInt(num)/amont;

		        System.out.println("   frequency: "+String.valueOf(frq));

		    }
		    writer.shutdown();
	}


}
