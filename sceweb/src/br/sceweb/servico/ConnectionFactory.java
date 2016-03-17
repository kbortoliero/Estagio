package br.sceweb.servico;

import java.sql.DriverManager;

import org.apache.log4j.Logger;

public class ConnectionFactory {
	Logger logger = Logger.getLogger(ConnectionFactory.class);
	
	public java.sql.Connection getConnection(){
		String url = "jdbc:mysql://localhost/sceweb";
		try{
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection(url, "root", "");
		}catch (Exception e){
			logger.info("SQLException na classe ConnectionFactory. Causa: " + e.getMessage());
			throw new RuntimeException(e);
		}
	}
}
