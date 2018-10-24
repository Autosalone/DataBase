package it.corso.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class DAO{

	private static DataSource ds = null;
	private static Connection con = null;
	private static DAO dao = null;
	
	private DAO(){
		Context initCtx = null;
		Context envCtx = null;
		try{
			// Look up our data source
			// Allocate and use a connection from the pool
			initCtx = new InitialContext();
			envCtx = (Context) initCtx.lookup("java:comp/env");
			ds = (DataSource)envCtx.lookup("jdbc/Esakila");

		}
		catch(NamingException e){
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnetion() {
		if(ds == null) {
			dao = new DAO();
		}
		try {
			con = ds.getConnection();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
}
