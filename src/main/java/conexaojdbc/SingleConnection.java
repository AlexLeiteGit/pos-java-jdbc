package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/*Nesta classe utilizamos o padrão Singleton*/
public class SingleConnection {
	
	/*Neste caso, iniciamos criando as 4 variáveis estáticas (pois não serão modadas na aplicação)
	 * necessárias para a aplicação.
	 * Também devemos criar uma conexão do java.sql para efetuarmos a conexão com o Banco de Dados*/
	private static String banco = "jdbc:postgresql://localhost:5433/posjava";
	private static String passwaord = "admin";
	private static String user = "postgres";
	private static Connection connection = null;
	
	/*Método para chamar a conexão de onde estiver*/
	static {
		conectar();
	}
	
	
	/*Construtor padrão para chamarmos a conexão*/
	public SingleConnection() {
		conectar();
	}

	/*Médoto para realizar a conexão com o Bando de Dados*/
	private static void conectar() {
		
		/*Verificação da conexão. A conexão é uma só. O que são várias que podem ser fechadas e abertas são as seções*/
		try {
			
			if(connection == null) {
				/*Driver do pstgress*/
				Class.forName("org.postgresql.Driver");
				/*Passando a conexão para o Drive*/
				connection = DriverManager.getConnection(banco, user, passwaord);
				/*Para o banco não salvar automaticamente.*/
				connection.setAutoCommit(false);
				
				/*Print do console apenas para verificar se conecta ou não*/
				System.out.println("Conectou com Sucesso!!!!!!");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/*Método para retornar a conexão. Iqual a o getter*/
	public static Connection getConnection() {
		return connection;
	}

}
