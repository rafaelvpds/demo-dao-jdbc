package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Db {
// Metodos estaticos para conectar e desconectar com o banco de dados

	private static Connection conn = null;

	public static Connection getConnection() {
		if (conn == null) {
			System.out.println("Open Connection");

			try {

				// pegando as propriedades do banco de dados
				Properties props = loadProperties();
				String url = props.getProperty("dburl");
				// para obter a conecxao com o banco de dados:
				conn = DriverManager.getConnection(url, props);
			} catch (SQLException e) {

				throw new DbException(e.getMessage());

			}

		}

		return conn;
	}

	public static void closeConnection() {

		// verificando se o meu conn esta instaciada
		if (conn != null) {
			System.out.println("Close Connection");

			try {

				conn.close();
			} catch (SQLException e) {
				throw new DbException(e.getMessage());
			}
		}
	}

	// Metodo para carregar as configurações dentro do arquivo db.properties
	private static Properties loadProperties() {
		// Abrir o arquivo para fazer a leitura //recebe como parametro o nome do
		// arquivo
		try (FileInputStream fs = new FileInputStream("db.properties")) {
			Properties props = new Properties();
			// faz a leitura do arquivo properties apontado pelo InputStreamFS e vai quardar
			// os dados dentro do objeto props
			props.load(fs);

			return props;
		} catch (IOException e) {
			throw new DbException(e.getMessage());
		}
	}

	public static void closeStatement(Statement st) {

		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DbException(e.getMessage());
			}
		}

	}

	public static void closeResult(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				throw new DbException(e.getMessage());
			}
		}
	}
}
