package br.edu.ifms.dao;

import java.sql.*;

public class Conexao {
	private static final String URL =
            "jdbc:postgresql://localhost:5432/projetobanco2216A";
	
	private static final String USUARIO = "postgres";
    private static final String SENHA = "postgresql";
    
    private Conexao() {  }

    public static Connection obterConexao() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
    
    
}
