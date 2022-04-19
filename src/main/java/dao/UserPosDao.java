package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class UserPosDao {

	/* Estabelece a conexão */
	private Connection connection;

	/* Construtor que injeta para dentro do objeto a conexão */
	public UserPosDao() {
		connection = SingleConnection.getConnection();
	}

	/* Criando ométodo INSERT */
	public void salvar(Userposjava userposjava) {

		try {
			/* criando a sintaxe do inseret, igual faríamos no Query Tools */
			String sql = "insert into userposjava (nome, email) values (?,?)";

			/*
			 * PreparedStatement é a classe que se utiliza para manipular estas instruções
			 * SQL
			 */
			PreparedStatement insert = connection.prepareStatement(sql);

			/*
			 * inserindo o parâmetro ID. A inserção está sendo direta, mas é possível
			 * futuramente fazermos um get de outro objeto
			 */

			insert.setString(1, userposjava.getNome());
			insert.setString(2, userposjava.getEmail());

			/* executa o SQL */
			insert.execute();

			/* Salva no Banco de Dados */
			connection.commit();

		} catch (Exception e) {
			try {

				/* reverte a operação caso ocorra algum problema */
				connection.rollback();

			} catch (Exception e2) {

				e.printStackTrace();

			}

		}

	}
	
	public void salvarTelefone(Telefone telefone) {
		
		try {
			
			String sql = "INSERT INTO telefoneuser(numero, tipo, usuariopessoa) VALUES (?, ?, ?)";
			
			PreparedStatement insertTelefone = connection.prepareStatement(sql);
			
			insertTelefone.setString(1, telefone.getNumero());
			insertTelefone.setString(2, telefone.getTipo());
			insertTelefone.setLong(3, telefone.getUsuario());
			
			insertTelefone.execute();
			
			connection.commit();
			
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			
			e.printStackTrace();
		}
	}

	/*
	 * Criando o comando SELECT na classe DAO Neste caso retornará TODOS os objetos
	 * do BD. Neste caso o parâmetro será a lista de todos os objetos a serem
	 * listados.
	 */
	public List<Userposjava> listar() throws Exception {

		/* cria-se a lista que será retornada */
		List<Userposjava> lista = new ArrayList<Userposjava>();

		/* Comando SQL a ser realizado pelo statement */
		String sql = "select * from userposjava";

		/* cria-se o statement para a realização do comendo SQL */
		PreparedStatement select = connection.prepareStatement(sql);

		/* Objeto de retorno do resultado, que neste caso é a consulta */
		ResultSet resultado = select.executeQuery();

		/* Percorre o banco para colocar na lista */
		while (resultado.next()) {
			/*
			 * Enquanto houver resltado, preciso instanciar um objeto userposjava pegar os
			 * dados (id, nome, email) para ao final colocasr na lista
			 */
			Userposjava userposjava = new Userposjava();

			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));

			lista.add(userposjava);
		}

		return lista;

	}

	/*
	 * Neste caso o SELECT retornará apenas um objeto do banco de dados. Neste caso
	 * o parâmetro será o objeto a ser buscado
	 */
	public Userposjava buscar(Long id) throws Exception {

		/* cria-se o objeto a ser buscado */
		Userposjava user = new Userposjava();

		/* Comando SQL a ser realizado pelo statement */
		String sql = "select * from userposjava where id = " + id;

		/* cria-se o statement para a realização do comendo SQL */
		PreparedStatement select = connection.prepareStatement(sql);

		/* Objeto de retorno do resultado, que neste caso é a consulta */
		ResultSet resultado = select.executeQuery();

		/* Percorre o banco para achar o id indicado na colsuta ou não retorna nada */
		while (resultado.next()) {
			/*
			 * Ou vai ter um objeto ou não terá nenhum. Assim não precisa isntancias,
			 * bastará passar os dados da busca
			 */

			user.setId(resultado.getLong("id"));
			user.setNome(resultado.getString("nome"));
			user.setEmail(resultado.getString("email"));
		}

		return user;

	}
	
	public List<BeanUserFone> listaUserFone(Long idUser){
		
		List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();
		
		String sql = " select nome, numero, email from telefoneuser as fone ";
		sql += " inner join userposjava as usertel ";
		sql += " on fone.usuariopessoa = usertel.id ";
		sql += " where usertel.id = " + idUser;
		
		try {
			
			PreparedStatement innerJoin = connection.prepareStatement(sql);
			ResultSet resultado = innerJoin.executeQuery();
			
			while(resultado.next()) {
				
				BeanUserFone userFone = new BeanUserFone();
				userFone.setEmail(resultado.getString("email"));
				userFone.setNome(resultado.getString("nome"));
				userFone.setNumero(resultado.getString("numero"));
				
				beanUserFones.add(userFone);
				
			}
		
		} catch (Exception e) {
			
			e.printStackTrace();
			
		}	
		
		return beanUserFones;
	}

	/* criando o UPDATE neste caso o parâmetro será o objeto a ser atualizado */
	public void atualizar(Userposjava userposjava) {

		try {

			String sql = "update userposjava set nome = ? where id = " + userposjava.getId();

			PreparedStatement update = connection.prepareStatement(sql);

			update.setString(1, userposjava.getNome());

			update.execute();

			connection.commit();

		} catch (SQLException e) {
			
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}

			e.printStackTrace();

		}

	}
	
	public void deletarBanco(long id) {
		try {
			
			String sql = "delete from userposjava where id = " + id;
			
			PreparedStatement delete = connection.prepareStatement(sql);
			
			delete.execute();
			
			connection.commit();
			
		}catch (Exception e) {
			
			try {
				
				connection.rollback();
			
			} catch (SQLException e1) {
				
				e1.printStackTrace();
			
			}
			
			e.printStackTrace();
			
		}
	}
	
	public void deletarDados(Long idUser) {
		
		try {
			
			String sqlFone = "delete from telefoneuser where usuariopessoa = " + idUser;
			String sqlUser = "delete from userposjava where id = " + idUser;
			
			PreparedStatement statement = connection.prepareStatement(sqlFone);
			statement.executeUpdate();
			connection.commit();
			
			statement = connection.prepareStatement(sqlUser);
			statement.executeUpdate();
			connection.commit();
			
		} catch (Exception e) {

			try {
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			
			e.printStackTrace();
		}
		
		System.out.println("Dado Deletado com Sucesso!!");
	}

}
