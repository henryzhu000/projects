package com.example;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.Products;


/**
 * Servlet implementation class PetsServlet
 */
public class PetsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	database d = new database();
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PetsServlet() {
        super();
        d.startup();
        // TODO Auto-generated constructor stub
    }

    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String returned=request.getParameter("name");
		System.out.println("returned2:"+returned);
		// TODO Auto-generated method stub
        try {
               SessionFactory factory = HibernateUtil.getSessionFactory();
               
               Session session = factory.openSession();
               // using HQL
               List<Products> list = session.createQuery("from Products", Products.class).list();
               
               session.close();
               
                PrintWriter out = response.getWriter();
                out.println("<html><body>");
                out.println("<b>Product Listing</b><br>");
                for(Products p: list) {
                        out.println("ID: " + String.valueOf(p.getID()) + ", Name: " + p.getName() +
                                        ", Price: " + String.valueOf(p.getPrice()) + ", Color: " + p.getColor().toString() + "<br>");
                }
                
            out.println("</body></html>");
            
            
        } catch (Exception ex) {
                throw ex;
        }

		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		


		// TODO Auto-generated method stub
	
		try {
		
		SessionFactory factory = HibernateUtil.getSessionFactory();
        
        Session session = factory.openSession();
        session.beginTransaction();
        Products p = new Products();
        p.setColor(request.getParameter("color")+"");
        p.setName(request.getParameter("name")+"");
        BigDecimal price = new BigDecimal(request.getParameter("price")+"");
        p.setPrice(price);
     //   p.setID(1);
        
    
        session.save(p);
        session.getTransaction().commit();
    //    factory.getSessionFactory().close();
   //     session.
        session.close();
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<b>Adding Pet</b> " + request.getParameter("name") + "<br>");
        out.println("<a href='index.jsp'>Return to Main</a><br>");
        out.println("</body></html>");
        
	  }catch(Exception e) {
		  System.out.println(e);
		  PrintWriter out = response.getWriter();
	        out.println("<html><body>");
	        out.println("<b>error check your format and try again</b>");
	        out.println("<a href='index.jsp'>Return to Main</a><br>");
	        out.println("</body></html>");  
	  }
       
        
        
        
        //TODO: Take all parameters from post, and use hibernate to insert new pet.
        // Then you need to print out some confirmation as to the the success/failure.
        // You also need to validate your input and not allow missing / NULL data.

		//doGet(request, response);
	}
}