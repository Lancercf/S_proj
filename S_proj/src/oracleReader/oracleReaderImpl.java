package oracleReader;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.omg.CORBA.DATA_CONVERSION;

import oracle.sql.DATE;





public class oracleReaderImpl implements oracleReader {

	data[] data_all=new data[1000];
	@Override
	public void datareader(int start,int end) {
		// TODO Auto-generated method stub
		oracleConn factory = oracleConn.getConnFactoryInstance(oracleConn.ORACLE_DB);  
	    Connection conn = factory.getConn();  
	    
		for(int j=0;j<data_all.length;j++)
			data_all[j]=new data();
		int i=0;
	    try {  
	        PreparedStatement pst = conn.prepareStatement("SELECT * FROM (SELECT A.*, ROWNUM RN FROM (SELECT * FROM ACCESSLOG1JUN2013) A WHERE ROWNUM <= "+end+") WHERE RN >= "+start);  
	        java.sql.ResultSet rs = pst.executeQuery();  
	        while (rs.next() ) {  
	        	data_all[i].setdata(rs.getInt(1),rs.getInt(2),rs.getDate(3),rs.getString(4),rs.getString(5),rs.getString(6),rs.getString(7),rs.getString(8),rs.getString(9),rs.getString(10),rs.getString(11),rs.getString(12),rs.getString(13),rs.getString(14),rs.getString(15),rs.getString(16),rs.getString(17),rs.getString(18),rs.getString(19));
				i++;  
	        }  
	        //System.out.println(data_all[501].getid());
	        rs.close();
	        pst.close();
	        conn.close();
	    } catch (SQLException e) {  
	        // TODO Auto-generated catch block  
	        e.printStackTrace();  
	    }  
	}

	@Override
	public String getADACS_card_number(int i) {
		// TODO Auto-generated method stub
		
		
		return data_all[i].getcardnum();
	}

	@Override
	public String getADACS_card_type(int i) {
		// TODO Auto-generated method stub
		return data_all[i].getcardtype();
	}

	@Override
	public String getsurname(int i) {
		// TODO Auto-generated method stub
		return data_all[i].getsurname();
	}

	@Override
	public String getforename(int i) {
		// TODO Auto-generated method stub
		return data_all[i].getforename();
	}

	@Override
	public String getText1(int i) {
		// TODO Auto-generated method stub
		return data_all[i].gettext1();
	}

	@Override
	public String getText2(int i) {
		// TODO Auto-generated method stub
		return data_all[i].gettext2();
	}

	@Override
	public String getText3(int i) {
		// TODO Auto-generated method stub
		return data_all[i].gettext3();
	}

	@Override
	public int getID(int i) {
		// TODO Auto-generated method stub
		return data_all[i].getid();
	}

	@Override
	public int getACCESS_KEY(int i) {
		// TODO Auto-generated method stub
		return data_all[i].getacckey();
	}

	@Override
	public Date getDATE_TIME(int i) {
		// TODO Auto-generated method stub
		return data_all[i].getdatetime();
	}

	@Override
	public String getAccess_event(int i) {
		// TODO Auto-generated method stub
		return data_all[i].getaccevent();
	}

	@Override
	public String getlocation(int i) {
		// TODO Auto-generated method stub
		return data_all[i].getlocation();
	}

	@Override
	public String getReader_id(int i) {
		// TODO Auto-generated method stub
		return data_all[i].getreaderid();
	}

	@Override
	public String getReader_number(int i) {
		// TODO Auto-generated method stub
		return data_all[i].getreadernum();
	}

	@Override
	public String getReader_name(int i) {
		// TODO Auto-generated method stub
		return data_all[i].getreadername();
	}

	@Override
	public String getSystemid(int i) {
		// TODO Auto-generated method stub
		return data_all[i].getsysid();
	}

	@Override
	public String getSkyline(int i) {
		// TODO Auto-generated method stub
		return data_all[i].getskycode();
	}

	
	

}
