package br.com.ecommerceorquideas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.codec.binary.Base64;

import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.LoginAdmin;
import br.com.ecommerceorquideas.util.Conexao;
import br.com.ecommerceorquideas.util.GeraSQL;
import br.com.ecommerceorquideas.warning.Aviso;

public class LoginAdminDAO implements IDAO{

	private static final String UPDATE = "UPDATE adm_senhas SET email=?, senha=? WHERE adm_id=?";
	private static final String INSERT = "INSERT INTO adm_senhas(email, senha, adm_id) VALUES(?,?,?)";
	private static final String DELETE = "DELETE FROM adm_senhas WHERE adm_id=?";
	
	private Connection connection;
	private PreparedStatement preparedStatement = null;

	private Aviso aviso;
	
	public LoginAdminDAO() {}
	
	public LoginAdminDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Object salvar(EntidadeDominio entidade) throws SQLException{
		LoginAdmin login = (LoginAdmin) entidade;
		
		try {
			String senhaCodificada = new Base64().encodeToString(login.getSenha().getBytes());
			preparedStatement = connection.prepareStatement(INSERT);
						
			preparedStatement.setString(1, login.getEmail());
			preparedStatement.setString(2, senhaCodificada);
			preparedStatement.setInt(3, login.getId());		
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			
			
		} catch (SQLException e) {
			throw e;
		} 
		return null;
	}

	@Override
	public Object alterar(EntidadeDominio entidade){
		LoginAdmin login = (LoginAdmin) entidade;
		aviso = new Aviso();
		
		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);
			
			String senhaCodificada = new Base64().encodeToString(login.getSenha().getBytes());
			preparedStatement = connection.prepareStatement(UPDATE);
			
			preparedStatement.setString(1, login.getEmail());
			preparedStatement.setString(2, senhaCodificada);
			preparedStatement.setInt(3, login.getId());		
			
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			connection.commit();
			
			aviso.addMensagem("Alterado com sucesso");
			
		} catch (SQLException | ClassNotFoundException e) {
			aviso.addMensagem("Desculpe, ocorreu um erro ao alterar as informações");
			try {
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				connection.close();
								
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return aviso;
	}

	@Override
	public Object excluir(Integer id){
		boolean commit = false;
		aviso = new Aviso();
		
		try {
			if (connection == null || connection.isClosed()) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
				commit = true;
			}
			preparedStatement = connection.prepareStatement(DELETE);
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
			
			preparedStatement.close();
			if(commit) {
				connection.commit();
			}
		} catch (SQLException | ClassNotFoundException e) {
			try {
				connection.rollback();
			} catch (SQLException e2) {
				e2.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			try {
				if(commit){
					connection.close();
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}		
		return null;
	}

	@Override
	public Object consultar(HashMap<String, String> map){
		String find = GeraSQL.select(map, "adm_senhas");
		List<EntidadeDominio> login = new ArrayList<EntidadeDominio>();
		
		try {
			if (connection == null || connection.isClosed()) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false); 
			}
			preparedStatement = connection.prepareStatement(find);
			
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				login.add(new LoginAdmin(rs.getInt(3), rs.getString(1)));
			}

			preparedStatement.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return login;
	}
}
