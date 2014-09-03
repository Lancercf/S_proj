package oracleReader;

import java.sql.Date;

public interface oracleReader {

	void datareader(int start,int end);
	
	
	String getADACS_card_number(int i);
	
	String getADACS_card_type(int i);
	
	String getsurname(int i);
	
	String getforename(int i);
	
	String getText1(int i);
	
	String getText2(int i);
	
	String getText3(int i);
	
	
	
	int getID(int i);
	
	int getACCESS_KEY(int i);
	
	Date getDATE_TIME(int i);
	
	String getAccess_event(int i);
	
	String getlocation(int i);
	
	
	
	String getReader_id(int i);
	
	String getReader_number(int i);
	
	String getReader_name(int i);
	
	String getSystemid(int i);
	
	String getSkyline(int i);
}
