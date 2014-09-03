import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Random;

import oracleReader.oracleConn;


public class dataCreator {
	
	void creator(){

		int id=554851725;
		int key=10130677;
	oracleConn factory = oracleConn.getConnFactoryInstance(oracleConn.ORACLE_DB);  
    Connection conn = factory.getConn();
    /*
    String sql1="CREATE TABLE SYSTEM.ACCESSLOG1JUN2013" 
    		+"(ID NUMBER(38,2), "
	+"ACCESS_KEY NUMBER(38,2)," 
	+"DATE_TIME DATE," 
	+"SYSTEM_ID VARCHAR2(8 CHAR)," 
	+"READER_NUMBER VARCHAR2(32 CHAR)," 
	+"READER_ID VARCHAR2(6 CHAR), "
	+"READER_NAME VARCHAR2(32 CHAR)," 
	+"ACCESS_EVENT VARCHAR2(32 CHAR)," 
	+"ADACS_CARD_NUMBER VARCHAR2(8 CHAR)," 
	+"ADACS_CARD_TYPE CHAR(1 CHAR), "
	+"SURNAME VARCHAR2(40 CHAR), "
	+"FORENAME VARCHAR2(16 CHAR), "
	+"LOCATION_NAME VARCHAR2(32 CHAR)," 
	+"DATE1 DATE, "
	+"TEXT1 VARCHAR2(32 CHAR)," 
	+"TEXT2 VARCHAR2(32 CHAR), "
	+"TEXT3 VARCHAR2(32 CHAR), "
	+"TEXT4 VARCHAR2(32 CHAR), "
	+"SKYLINE_CODE VARCHAR2(8 CHAR)"
    +")";
    */
    String sql="insert into ACCESSLOG1JUN2013 (ID,"
    		+ "ACCESS_KEY,"
    		+ "DATE_TIME,"
    		+ "SYSTEM_ID,"
    		+ "READER_NUMBER,"
    		+ "READER_ID,"
    		+ "READER_NAME,"
    		+ "ACCESS_EVENT,"
    		+ "ADACS_CARD_NUMBER,"
    		+ "ADACS_CARD_TYPE,"
    		+ "SURNAME,"
    		+ "FORENAME,"
    		+ "LOCATION_NAME,"
    		+ "DATE1,"
    		+ "TEXT1,"
    		+ "TEXT2,"
    		+ "TEXT3,"
    		+ "TEXT4,"
    		+ "SKYLINE_CODE)"
    		+" values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
  
    try {  
    	conn.setAutoCommit(false);
        PreparedStatement pst = conn.prepareStatement(sql);  
        for(int i=0;i<10000;i++){
        	pst.setInt(1, id);
        	pst.setInt(2, key);
        	pst.setString(3, "");
        	pst.setString(4,"IP0150A2");
        	pst.setString(5,"");
        	pst.setString(6, String.valueOf(readerid()));
        	pst.setString(7, "");
        	pst.setString(8, "");
        	pst.setString(9, String.valueOf(cardnum()));
        	pst.setString(10, "E");
        	pst.setString(11, "");
        	pst.setString(12, "");
        	pst.setString(13, "");
        	pst.setString(14, "");
        	pst.setString(15, "");
        	pst.setString(16, "");
        	pst.setString(17, "");
        	pst.setString(18, "");
        	pst.setString(19, "IP0150A2");
        	pst.addBatch();
        	id++;
        	key++;
        }
        pst.executeBatch(); 
        conn.commit(); 
        pst.close();  
        conn.close();  
        System.out.println("done!");
    } catch (SQLException e) {  
        // TODO Auto-generated catch block  
        e.printStackTrace();  
    }  

	}
	
	int readerid(){
		int r=0;
		Random rand = new Random();
		int i = rand.nextInt(); 
		i = rand.nextInt(3); 
		if(i==0) r=28;
		else if(i==1) r=27;
		else if(i==2) r=19;
		return r;
	}
	
	int cardnum()
	{
		int r=0;
		Random rand = new Random();
		int i = rand.nextInt(); 
		i = rand.nextInt(3); 
		if(i==0) r=257729;
		else if(i==1) r=259250;
		else if(i==2) r=325678;
		return r;
		
	}
}
