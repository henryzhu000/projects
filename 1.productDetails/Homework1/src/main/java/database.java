import java.sql.ResultSet;
import java.sql.Statement;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Statement;
import java.util.Properties;

public class database {

	public void startup() {
		try {
		  DBConnection conn = new DBConnection("jdbc:mysql://localhost:3306", "root", "password");
	         Statement stmt = conn.getConnection().createStatement();
	         stmt.executeUpdate("drop database ecommerce89329893");
	         stmt.executeUpdate("create database ecommerce89329893");
	         stmt.executeUpdate("use ecommerce89329893");
	         stmt.executeUpdate("CREATE TABLE eproduct (ID bigint primary key auto_increment, name varchar(100), price decimal(10,2), date_added timestamp default now())");
	         
	            stmt.execute("INSERT INTO eproduct(name, price) values ( \"HP Laptop ABC\",1000);");
	            stmt.execute("INSERT INTO eproduct(name, price) values ( \"Acer Laptop\",14000);");
	            stmt.execute("INSERT INTO eproduct(name, price) values ( \"Lenovo Laptop ABC�\",1000);");
	}catch(Exception e) {}}
	
	public String search(String input) {
		      String returned="";
		try {
			
	 
		  DBConnection conn = new DBConnection("jdbc:mysql://localhost:3306", "root", "password");
	         Statement stmt = conn.getConnection().createStatement();
	         stmt.executeUpdate("use ecommerce89329893");
	         String sql = "SELECT * from eproduct where id="+input;
	      ResultSet rs = stmt.executeQuery(sql);
	
	         while(rs.next()){
	            String first = rs.getString("name");
	            String price = rs.getString("price");
	            String date=rs.getString("date_added");
	           returned+="name:"+first+", price:"+price+", date:"+date+"\n";
	         }
	         rs.close();
	         
	}catch(Exception e) {}
		return returned;
	}


	
}
