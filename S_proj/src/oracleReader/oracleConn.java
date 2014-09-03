package oracleReader;
  

import java.io.File;  
import java.io.FileInputStream;  
import java.io.IOException;  
import java.io.InputStream;  
import java.sql.Connection;  
import java.sql.DriverManager;  
import java.sql.PreparedStatement;  
import java.sql.ResultSet;  
import java.sql.SQLException;  
import java.util.Properties;  
import java.util.UUID;  
  
public class oracleConn {  

    private String driver;  

    private String url;  

    private String username;  

    private String password;  
      
    private String db;  

    public final static String ORACLE_DB = "oracle";  
     

    private static oracleConn connFactory = null;  
      
    public static synchronized oracleConn getConnFactoryInstance(String db){  
        if(connFactory == null){  
            connFactory = new oracleConn(db);  
        }  
        return connFactory;  
    }  
      
    private oracleConn(String db){  
        try {  
            initOrclParams(db);  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }  
      

    private void initOrclParams(String db) throws IOException{  
        Properties properties = new Properties();  
  
        InputStream in = new FileInputStream(new File("database.properties"));  
        properties.load(in);  
      
        if(db.equals(ORACLE_DB))  
        {  
            getParams(properties,db);  
        }  
 
    }  
      
 
    private void getParams(Properties p,String db){  

        driver = p.getProperty(db + ".driver").trim();  
        url = p.getProperty(db + ".url").trim();  
        username = p.getProperty(db + ".user").trim();  
        password = p.getProperty(db + ".password").trim();  
    }  
      
  
    public Connection getConn()  
    {  
        Connection conn = null;  
          
        try   
        {  
            Class.forName(driver);  
            conn = DriverManager.getConnection(url, username, password);  
  
            conn.setAutoCommit(false);  
        }   
        catch (ClassNotFoundException e)   
        {  
            e.printStackTrace();  
        }   
        catch (SQLException e)   
        {  
            e.printStackTrace();  
        }   
          
        return conn;  
    }  
  

    public static void close(Connection conn, PreparedStatement pst, ResultSet rs) {  
        try {  
            if (rs != null) {  
                rs.close();  
            }  
            if (pst != null) {  
                pst.close();  
            }  
            if (conn != null) {  
                conn.close();  
            }  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
      
  
    public String getUUID() {  
        return UUID.randomUUID().toString();  
    }  
      
    public void setPassword(String password) {  
        this.password = password;  
    }  
      
    public String getDriver() {  
        return driver;  
    }  
  
    public void setDriver(String driver) {  
        this.driver = driver;  
    }  
  
    public String getUrl() {  
        return url;  
    }  
  
    public void setUrl(String url) {  
        this.url = url;  
    }  
  
    public String getUsername() {  
        return username;  
    }  
  
    public void setUsername(String username) {  
        this.username = username;  
    }  
  
    public String getPassword() {  
        return password;  
    }  
  
    public String getDb() {  
        return db;  
    }  
  
    public void setDb(String db) {  
        this.db = db;  
    }  
  
} 


