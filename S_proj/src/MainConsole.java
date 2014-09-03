import java.util.Scanner;

import org.neo4j.graphdb.GraphDatabaseService;

import detector.detector;
import transation.transation;


public class MainConsole {
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub

		GraphDatabaseService db = null;
		transation tran =new transation();

        
        
		System.out.println("select number of functions to continue");
		System.out.println("1. create new data");
		System.out.println("2. transfer data to Neo4j");
		System.out.println("3. anomaly detection");
		
		
		
		
		Scanner sc = new Scanner(System.in);
		int select = sc.nextInt();
		
		if(select==1)
		{
			dataCreator DC= new dataCreator();
			DC.creator();
		}
		
		if(select==2)
		{
		  int start=1;
		  int end=1000;
		  int flg;
		  
		  
		
		  long starttime=System.currentTimeMillis();
		   while(true)
		    {
		     flg=tran.Odata(start,end);
		//System.out.println(start+" "+end+" "+flg);
		     if(flg==0)
		      {
			   System.out.println("transfer done!");
			   System.exit(0);
		      }
		     tran.Ndata();
		     start=end+1;
		     end=start+999;
		     long time = System.currentTimeMillis() - starttime;
		     System.out.println("running time= "+time+" milliseconds");
		   }
		}
		
		if(select==3)
		{
			db=tran.getDb();
			detector det= new detector();
			det.detec(db);
			
		}

 /***********************************Oracle TEST***********************************
		    oracleConn factory = oracleConn.getConnFactoryInstance(oracleConn.ORACLE_DB);  
		    Connection conn = factory.getConn();  
		    try {  
		        PreparedStatement pst = conn.prepareStatement("select * from ACCESSLOG1JUN2013");  
		        java.sql.ResultSet rs = pst.executeQuery();  
		        while (rs.next()) {  
		            System.out.println(rs.getString(1));  
		        }  
		    } catch (SQLException e) {  
		        // TODO Auto-generated catch block  
		        e.printStackTrace();  
		    }  
***************************************************************************************/
		//dataCreator DC= new dataCreator();
		//System.out.println(DC.readerid());
		//DC.creator();
		
/*********************************Neo4j TEST*****************************************
		neo4jConn conn = new neo4jConn();
		GraphDatabaseService graphDb=conn.createdb();
		Transaction tx = graphDb.beginTx();
		Node firstNode;  
	    Node secondNode;  
	    Node tmp;
	    Relationship relationship; 
	    Relationship rela;
		try  
        {  
            // Updating operations go here  
            // END SNIPPET: transaction  
            // START SNIPPET: addData  
            firstNode = graphDb.createNode();  
            firstNode.setProperty( "message", "Hello, " ); 
            firstNode.setProperty("ID", "000000");
            secondNode = graphDb.createNode();  
            secondNode.setProperty( "message", "World!" );  
  
            relationship = firstNode.createRelationshipTo( secondNode, RelTypes.KNOWS );  
            relationship.setProperty( "message", "brave Neo4j " );  
            
            
            rela = firstNode.createRelationshipTo( secondNode, RelTypes.KNOWS );  
            rela.setProperty( "message", "----------- " ); 
            // END SNIPPET: addData  
  
            // START SNIPPET: readData  
            System.out.print( firstNode.getProperty( "message" ) );  
            System.out.print( relationship.getProperty( "message" ) );  
            System.out.print( secondNode.getProperty( "message" ) );  
            // END SNIPPET: readData  
  
            greeting = ( (String) firstNode.getProperty( "message" ) )  
                    + ( (String) relationship.getProperty( "message" ) )  
                    + ( (String) secondNode.getProperty( "message" ) );  
  
            // START SNIPPET: transaction  
            tx.success();  
        }  
        finally  
        {  
            tx.finish();  
        }  
***************************************************************************************************/
	}

}
