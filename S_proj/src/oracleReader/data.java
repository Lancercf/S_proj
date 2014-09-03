package oracleReader;

import java.sql.Date;

class data{
	private int id=0;
	private int access_key=0;
	private Date date_time=null;
	private String system_id=null;
	private String reader_number=null;
	private String reader_id=null;
	private String reader_name=null;
	private String access_event=null;
	private String adacs_card_number=null;
	private String adacs_card_type=null;
	private String surname=null;
	private String forename=null;
	private String location_name=null;
	private String date1=null;
	private String text1=null;
	private String text2=null;
	private String text3=null;
	private String text4=null;
	private String skyline_code=null;
	
	public data(){}
	public data(
			int id,
			int access_key,
			Date date_time,
			String system_id,
			String reader_number,
			String reader_id,
			String reader_name,
			String access_event,
			String adacs_card_number,
			String adacs_card_type,
			String surname,
			String forename,
			String location_name,
			String date1,
			String text1,
			String text2,
			String text3,
			String text4,
			String skyline_code )
	{
		this.id=id;
		this.access_key=access_key;
		this.date_time=date_time;
		this.system_id=system_id;
		this.reader_name=reader_name;
		this.reader_id=reader_id;
		this.reader_number=reader_number;
		this.access_event=access_event;
		this.adacs_card_number=adacs_card_number;
		this.adacs_card_type=adacs_card_type;
		this.surname=surname;
		this.forename=forename;
		this.location_name=location_name;
		this.date1=date1;
		this.text1=text1;
		this.text2=text2;
		this.text3=text3;
		this.text4=text4;
		this.skyline_code=skyline_code;
		
	}	
	public void setdata(int id,
			int access_key,
			Date date_time,
			String system_id,
			String reader_number,
			String reader_id,
			String reader_name,
			String access_event,
			String adacs_card_number,
			String adacs_card_type,
			String surname,
			String forename,
			String location_name,
			String date1,
			String text1,
			String text2,
			String text3,
			String text4,
			String skyline_code )
	{
		this.id=id;
		this.access_key=access_key;
		this.date_time=date_time;
		this.system_id=system_id;
		this.reader_name=reader_name;
		this.reader_id=reader_id;
		this.reader_number=reader_number;
		this.access_event=access_event;
		this.adacs_card_number=adacs_card_number;
		this.adacs_card_type=adacs_card_type;
		this.surname=surname;
		this.forename=forename;
		this.location_name=location_name;
		this.date1=date1;
		this.text1=text1;
		this.text2=text2;
		this.text3=text3;
		this.text4=text4;
		this.skyline_code=skyline_code;
		
	}
	
	public String getcardnum()
	{
		return this.adacs_card_number;
	}
	
	public String getcardtype()
	{
		return this.adacs_card_type;
	}
	
	public String getsurname()
	{
		return this.surname;
	}
	
	public String getforename()
	{
		return this.forename;
	}
	
	public String gettext1()
	{
		return this.text1;
	}
	
	public String gettext2()
	{
		return this.text2;
	}
	
	public String gettext3()
	{
		return this.text3;
	}
	
	public int getid()
	{
		return this.id;
		
	}
	
	public int getacckey()
	{
		return this.access_key;
	}
	
	public Date getdatetime()
	{
		return this.date_time;
	}
	
	public String getaccevent()
	{
		return this.access_event;
	}
	
	public String getlocation()
	{
		return this.location_name;
	}
	
	public String getreaderid()
	{
		return this.reader_id;
	}
	
	public String getreadernum()
	{
		return this.reader_number;
	}
	
	public String getreadername()
	{
		return this.reader_name;
	}
	
	public String getsysid()
	{
		return this.system_id;
	}
	
	public String getskycode()
	{
		return this.skyline_code;
	}

	
	
	
}
