package com.example;
import java.sql.ResultSet;
import java.sql.Statement;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Statement;
import java.util.Properties;

public class database {

	public void startupDelete() {try {//System.out.println("deleting");
		  DBConnection conn = new DBConnection("jdbc:mysql://localhost:3306", "root", "password");
	         Statement stmt = conn.getConnection().createStatement();
	         stmt.executeUpdate("drop database ecommerce89329893");
	         stmt.close();
	         conn.closeConnection();
	}catch(Exception e) {}
	}
	
	public void startupCreateDatabase() {try {// System.out.println("create database");
		  DBConnection conn = new DBConnection("jdbc:mysql://localhost:3306", "root", "password");
	         Statement stmt = conn.getConnection().createStatement();
	         stmt.executeUpdate("create database ecommerce89329893");
	         stmt.close();
	         conn.closeConnection();
	}catch(Exception e) {}
	}
	
	public void startupCreateTable() {try {//System.out.println("create table");
		  DBConnection conn = new DBConnection("jdbc:mysql://localhost:3306", "root", "password");
	         Statement stmt = conn.getConnection().createStatement();
     stmt.executeUpdate("use ecommerce89329893");
	         
	         stmt.executeUpdate("CREATE TABLE products (\n"
	         		+ "id int(11) not null AUTO_INCREMENT,\n"
	         		+ "name VARCHAR(256),\n"
	         		+ "color VARCHAR(256),\n"
	         		+ "price DECIMAL(19,2),\n"
	         		+ "PRIMARY KEY (`id`)\n"
	         		+ ");");
	   //      System.out.println("inserting into tables");
	//         stmt.executeUpdate("INSERT INTO products (name,color,price) VALUES (\"Bird\", \"Chartreuse\", 200.00);");
	//            stmt.execute("INSERT INTO products (name,color,price) VALUES (\"Hamster\", \"Brown\", 30.00);");
	//            stmt.execute("INSERT INTO products (name,color,price) VALUES (\"Cayman\", \"Neon Pink\", 500.00)");
	//            stmt.execute("INSERT INTO products (name,color,price) VALUES (\"Tarantula\", \"Red\", 100.00)");
	     //  System.out.println("done inserting into tables");
	     
	            stmt.close();
	         conn.closeConnection();
	}catch(Exception e) {}
	}
	
	public void startup() {

startupDelete();
startupCreateDatabase();
startupCreateTable();
	}
	


	
}
