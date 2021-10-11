package br.com.ecommerceorquideas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.model.Administrador;
import br.com.ecommerceorquideas.model.EntidadeDominio;
import br.com.ecommerceorquideas.model.LoginAdmin;
import br.com.ecommerceorquideas.util.Conexao;
import br.com.ecommerceorquideas.util.GeraSQL;
import br.com.ecommerceorquideas.warning.Aviso;

public class AdministradorDAO implements IDAO{
	
	private static final String INSERT = "INSERT INTO administradores(cpf, nome, sexo, data_nascimento, telefone, tipo_telefone, permissao) VALUES(?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE administradores SET cpf=?, nome=?, sexo=?, data_nascimento=?, telefone=?, tipo_telefone=?, permissao=? WHERE id=?";
	private static final String DELETE = "DELETE FROM administradores WHERE id=?";

	private Connection connection;
	private PreparedStatement preparedStatement = null;
	
	private Aviso aviso;

	@Override
	public Object salvar(EntidadeDominio entidade){
		Administrador administrador = (Administrador) entidade;
		aviso = new Aviso();
		
		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, administrador.getCpf());
			preparedStatement.setString(2, administrador.getNome());
			preparedStatement.setString(3, administrador.getSexo());
			Timestamp time = new Timestamp(administrador.getNascimento().getTime());
			preparedStatement.setTimestamp(4, time);
			preparedStatement.setString(5, administrador.getTelefone());
			preparedStatement.setString(6, administrador.getTipoTelefone());
			preparedStatement.setInt(7, administrador.getPermissao());
			
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				administrador.setId(rs.getInt(1));
				administrador.getLogin().setId(rs.getInt(1));
			}

			LoginAdminDAO loginDAO = new LoginAdminDAO(connection);
			loginDAO.salvar(administrador.getLogin()); 

			preparedStatement.close();
			connection.commit();
			aviso.addMensagem("Salvo com sucesso");

		} catch (SQLException | ClassNotFoundException e) {
			aviso.addMensagem("Desculpe, ocorreu um erro ao salvar as informações");
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
	public Object alterar(EntidadeDominio entidade) {
		Administrador administrador = (Administrador) entidade;
		aviso = new Aviso();
		
		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(UPDATE);

			preparedStatement.setString(1, administrador.getCpf());
			preparedStatement.setString(2, administrador.getNome());
			preparedStatement.setString(3, administrador.getSexo());
			Timestamp time = new Timestamp(administrador.getNascimento().getTime());
			preparedStatement.setTimestamp(4, time);
			preparedStatement.setString(5, administrador.getTelefone());
			preparedStatement.setString(6, administrador.getTipoTelefone());
			preparedStatement.setInt(7, administrador.getPermissao());
			preparedStatement.setInt(8, administrador.getId());

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
	public Object excluir(Integer id) {
		aviso = new Aviso();
		
		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			exluirDados(connection, id);

			preparedStatement = connection.prepareStatement(DELETE);

			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.commit();
			aviso.addMensagem("Excluido com sucesso");

		} catch (SQLException | ClassNotFoundException e) {
			aviso.addMensagem("Desculpe, ocorreu um erro ao excluir as informações");
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
	public Object consultar(HashMap<String, String> map) {
		List<Object> administradores = new ArrayList<>();
		Administrador administrador;
		IDAO dao;

		try {
			connection = Conexao.getConnection();

			String find = GeraSQL.select(map, "administradores");
			preparedStatement = connection.prepareStatement(find);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				administrador = new Administrador(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7), rs.getInt(8));

				map = new HashMap<>();
				map.put("adm_id", administrador.getId().toString());
				
				dao = new LoginAdminDAO(connection);
				List<LoginAdmin> logins = (List<LoginAdmin>) dao.consultar(map);
				administrador.setLogin(logins.get(0));
				
				administradores.add(administrador);
			}

			preparedStatement.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return administradores;
	}

	private void exluirDados(Connection connection, Integer id) throws SQLException {
		
		try {
			IDAO dao = new LoginAdminDAO(connection);
			dao.excluir(id);
			
		} catch (Exception e) {
			throw e;
		}
		
	}

}
