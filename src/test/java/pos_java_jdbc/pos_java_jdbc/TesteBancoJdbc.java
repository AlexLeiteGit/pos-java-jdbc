package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

import org.junit.Test;

import dao.UserPosDao;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class TesteBancoJdbc {

//	@Test
//	public void iniciarBanco() {
//		
//		/*Se instancia o objeto DAO para se realizar o salvamento*/
//		UserPosDao userPosDao = new UserPosDao();
//		
//		/*Se instância o objeto userposjava para se pegar os parâmetros que estarão nele*/
//		Userposjava userposjava = new Userposjava();
//		
//		userposjava.setNome("Maria Auxiliadora");
//		userposjava.setEmail("maria@gmail.com");
//		
//		/*salva os parâmetros que está fixos no objeto userposjava*/
//		userPosDao.salvar(userposjava);
//	}
//	
//	@Test
//	public void listarBanco() {
//		
//		/*Se instancia o objeto dao para realizar o select*/
//		UserPosDao dao = new UserPosDao();
//		
//		try {
//			
//			List<Userposjava> list = dao.listar();
//			
//			for (Userposjava user : list) {
//				System.out.println(user);
//				System.out.println("------------------------------------------------");
//			}
//			
//			for (Userposjava user : list) {
//				System.out.println(user.getNome());
//				System.out.println("------------------------------------------------");
//			}
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		}		
//
//	}
//	
//	@Test
//	public void buscarBanco() throws Exception {
//		
//		/*Instancia o objeto DAO para a realização dos comandos*/
//		UserPosDao dao = new UserPosDao();
//		
//		/*chama o método buscar*/
//		Userposjava userposjava = dao.buscar(4L);
//		
//		/*Imprme na tela ou faz outra coisa com o resultado da consulta*/
//		System.out.println(userposjava);
//	}
//	
//	@Test
//	public void atualizarBanco() {
//		
//		try {
//			
//			UserPosDao dao = new UserPosDao();
//			
//			Userposjava objetoBanco = dao.buscar(2L);
//			
//			objetoBanco.setNome("Nome mudado com o método atualizar!");
//			
//			dao.atualizar(objetoBanco);
//			
//		} catch (Exception e) {
//
//			e.printStackTrace();
//		}
//		
//
//	}
	
//	@Test
//	public void deletarBanco() {
//		
//		try {
//			
//			UserPosDao dao = new UserPosDao();
//			dao.deletarBanco(7L);
//			
//		} catch (Exception e) {
//
//			e.printStackTrace();
//
//		}
//		
//	}
	
//	@Test
//	public void testeInsertTelefone() {
//		
//		UserPosDao dao = new UserPosDao();
//		
//		Telefone tel = new Telefone();
//		
//		tel.setNumero("98833-2754");
//		tel.setTipo("cwelular");
//		tel.setUsuario(11L);
//		
//		dao.salvarTelefone(tel);
//	}

//	@Test
//	public void testeCarregaFoneUser() {
//		
//		UserPosDao dao = new UserPosDao();
//		
//		List<BeanUserFone> beanUserFones = dao.listaUserFone(10L);
//		
//		for (BeanUserFone beanUserFone : beanUserFones) {
//			System.out.println(beanUserFone);
//		}
//		
//	}
	
	@Test
	public void testeDeleUserFone() {
		
		UserPosDao dao = new UserPosDao();
		dao.deletarDados(11L);
		
	}
	
	
}
