package br.com.fiap.porto.model.repository;

import java.sql.Connection;
import java.sql.SQLException;



public class Repository {
	
	

	protected static Connection connection;

	/**
	 * Construtor padrão
	 */
	public Repository() {
		super();
		getConnection();
	}

	/**
	 * Consegue uma conexão com o banco de dados
	 * 
	 * @see ConnectionFactory#getInstance()
	 * 
	 * @return
	 */
	public static Connection getConnection() {
		try {
			connection = ConnectionFactory.getInstance().getConnection();
			return connection;
		} catch (Exception e) {
			System.out.println("Erro nos parâmetros da conexão com o banco de dados :" + e.getMessage());
		}
		return null;
	}

	/**
	 * Fecha a conexão com o banco de dados
	 */
	public static void closeConnection() {

		try {
			// se a conexão não estiver fechada, feche-a
			if (!connection.isClosed()) {
				connection.close();
			}
		} catch (SQLException ex) {
			System.out.println("Erro ao encerrar conex�o:" + " \n" + ex.getMessage());
		}
	}

}