package detector;



import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import org.neo4j.cypher.javacompat.ExecutionEngine;
import org.neo4j.cypher.javacompat.ExecutionResult;
import org.neo4j.graphdb.GraphDatabaseService;

public class detector {
	public  void detec(GraphDatabaseService db)
	{
		System.out.println("select number of functions to continue");
		System.out.println("1. search only by person");
		System.out.println("2. search only by room");
		System.out.println("3. search by access event");
		System.out.println("4. search by person and room");
		System.out.println("5. frequency analysis");
		
		
		Scanner sc = new Scanner(System.in);
		int select = sc.nextInt();
		  

		if(select==1)
		{
			System.out.println("input adacs card number:");
			Scanner arg = new Scanner(System.in);
			String Pnum=arg.next();
		    ExecutionEngine engine = new ExecutionEngine( db );  
		    ExecutionResult result = engine.execute( "MATCH (Person) where Person.adacs_card_number='"+Pnum+"' RETURN Person.adacs_card_number, Person.adacs_card_type, Person.surname, Person.forename, Person.employee_id, Person.note, Person.email" );  

		    System.out.println(result.toString());  
		    System.out.println("the detail of person with card number: "+Pnum+": ");
		
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
		    
		    System.out.println("continue? y/n");
		    Scanner j = new Scanner(System.in);
			String ju=j.next();
		
			if(ju.equalsIgnoreCase("n"))
			{
				System.exit(0);
			}else{
				
		    System.out.println("the activities of this person:");
		    ExecutionResult result2 = engine.execute("match (Person)-[Access_event]-(Room) where Person.adacs_card_number='"+Pnum+"' return Person.adacs_card_number, type(Access_event), Room.reader_id");
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
		    System.out.println("Cypher query is:");
		    System.out.println("match (Person)-[Access_event]-(Room) where Person.adacs_card_number='"+Pnum+"' return Person.adacs_card_number, type(Access_event), Room.reader_id");
		
			}
		}
		
		if(select==2)
		{
			System.out.println("input room reader id:");
			Scanner arg = new Scanner(System.in);
			String Rid=arg.next();
		    ExecutionEngine engine = new ExecutionEngine( db );  
		    ExecutionResult result = engine.execute( "MATCH (Room) where Room.reader_id='"+Rid+"' RETURN Room.reader_id, Room.reader_number, Room.reader_name, Room.system_id, Room.skyline_code" );  

		    System.out.println(result.toString());  
		    System.out.println("the detail of Room with reader id: "+Rid+": ");
		
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
		
		    System.out.println("continue? y/n");
		    Scanner j = new Scanner(System.in);
			String ju=j.next();
		
			if(ju.equalsIgnoreCase("n"))
			{
				System.exit(0);
			}else{
		    
		    System.out.println("the events of this room:");
		    ExecutionResult result2 = engine.execute("match (Person)-[Access_event]-(Room) where Room.reader_id='"+Rid+"' return Person.adacs_card_number, type(Access_event), Room.reader_id");
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
		    System.out.println("Cypher query is:");
		    System.out.println("match (Person)-[Access_event]-(Room) where Room.reader_id='"+Rid+"' return Person.adacs_card_number, type(Access_event), Room.reader_id");
		
			}
		}
		
		if(select==3)
		{
			System.out.println("all access events:");
			
		    ExecutionEngine engine = new ExecutionEngine( db );  
		    ExecutionResult result = engine.execute( "MATCH (Person)-[Access_event]-(Room) RETURN DISTINCT type(Access_event)" );  

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

		    System.out.println("input the access event you want to search:");
		    Scanner j = new Scanner(System.in);
			String Atype=j.next();
		    
		    
		    System.out.println("the events of this activity:");
		    ExecutionResult result2 = engine.execute("MATCH (Person)-[Access_event]->(Room) WHERE type(Access_event)='"+Atype+"' RETURN  Person.adacs_card_number, type(Access_event), Room.reader_id");
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
		    System.out.println("Cypher query is:");
		    System.out.println("MATCH (Person)-[Access_event]->(Room) WHERE type(Access_event)='"+Atype+"' RETURN  Person.adacs_card_number, type(Access_event), Room.reader_id");
		
			
		}
		
		if(select==4)
		{
			System.out.println("input adacs card number:");
			Scanner arg = new Scanner(System.in);
			String Pnum=arg.next();
			System.out.println("input room reader id:");
			Scanner arg2 = new Scanner(System.in);
			String Rid=arg2.next();
			
		    ExecutionEngine engine = new ExecutionEngine( db );  
		    ExecutionResult result = engine.execute( "MATCH (Person) where Person.adacs_card_number='"+Pnum+"' RETURN Person.adacs_card_number, Person.adacs_card_type, Person.surname, Person.forename, Person.employee_id, Person.note, Person.email" );  

		    System.out.println(result.toString());  
		    System.out.println("the detail of person with card number: "+Pnum+": ");
		
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
		    
		    ExecutionResult result2 = engine.execute( "MATCH (Room) where Room.reader_id='"+Rid+"' RETURN Room.reader_id, Room.reader_number, Room.reader_name, Room.system_id, Room.skyline_code" );  

		    System.out.println(result2.toString());  
		    System.out.println("the detail of Room with reader id: "+Rid+": ");
		
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
		    
		    System.out.println("continue? y/n");
		    Scanner j = new Scanner(System.in);
			String ju=j.next();
		
			if(ju.equalsIgnoreCase("n"))
			{
				System.exit(0);
			}else{
				
		    System.out.println("the activities of this person:");
		    ExecutionResult result3 = engine.execute("match (Person)-[Access_event]-(Room) where Person.adacs_card_number='"+Pnum+"' and Room.reader_id='"+Rid+"' return Person.adacs_card_number, type(Access_event), Room.reader_id");
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
		    System.out.println("match (Person)-[Access_event]-(Room) where Person.adacs_card_number='"+Pnum+"' and Room.reader_id='"+Rid+"' return Person.adacs_card_number, type(Access_event), Room.reader_id");
		
			}
		}
		
		if(select==5)
		{

			
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
		    
		}
		
	}

}
