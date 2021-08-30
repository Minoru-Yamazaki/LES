package br.com.ecommerceorquideas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.domain.Cartao;
import br.com.ecommerceorquideas.domain.EntidadeDominio;
import br.com.ecommerceorquideas.util.Conexao;
import br.com.ecommerceorquideas.util.GeraSQL;
import br.com.ecommerceorquideas.warning.Aviso;

public class CartaoDAO implements IDAO {

	private static final String INSERT = "INSERT INTO cartoes(numero, nome_impresso, cvv, bandeira, cli_id) VALUES(?,?,?,?,?)";
	private static final String UPDATE = "UPDATE cartoes SET numero=?, nome_impresso=?, bandeira=?, cvv=? WHERE id=?";
	private static final String DELETE = "DELETE FROM cartoes WHERE id=?";
	
	private Connection connection;
	private PreparedStatement preparedStatement = null;

	private Aviso aviso = new Aviso();
	
	public CartaoDAO() {}
	
	public CartaoDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Object salvar(EntidadeDominio entidade){
		Cartao cartao = (Cartao) entidade;
		boolean commit = false;
		
		try {
			if (connection == null) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
				commit = true;
			}
			preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, cartao.getNumero());
			preparedStatement.setString(2, cartao.getNomeImpresso());
			preparedStatement.setString(3, cartao.getCodigoSeguranca());
			preparedStatement.setString(4, cartao.getBandeira());
			preparedStatement.setInt(5, cartao.getIdCliente());
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {
				cartao.setId(rs.getInt(1));
			}
			
			preparedStatement.close();
			if(commit) {
				connection.commit();
			}
			aviso.addMensagem("Cartão salvo com sucesso");
			
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
				if(commit){
					connection.close();
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return aviso;
	}

	@Override
	public Object alterar(EntidadeDominio entidade){
		Cartao cartao = (Cartao) entidade;
		boolean commit = false;
		
		try {
			if (connection == null) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
				commit = true;
			}
			preparedStatement = connection.prepareStatement(UPDATE);

			preparedStatement.setString(1, cartao.getNumero());
			preparedStatement.setString(2, cartao.getNomeImpresso());
			preparedStatement.setString(3, cartao.getBandeira());
			preparedStatement.setString(4, cartao.getCodigoSeguranca());
			preparedStatement.setInt(5, cartao.getId());
			preparedStatement.executeUpdate();
			
			if(commit) {
				connection.commit();
			}
			aviso.addMensagem("Cartão alterado com sucesso");
			
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
				if(commit){
					connection.close();
				}
				preparedStatement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return aviso;
	}

	@Override
	public Object excluir(Integer id){
		boolean commit = false;
		
		try {
			if (connection == null) {
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
			aviso.addMensagem("Cartão excluido com sucesso");
			
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
				if(commit){
					connection.close();
				}				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return aviso;
	}

	@Override
	public Object consultar(HashMap<String, String> map){
		List<EntidadeDominio> cartoes = new ArrayList<>();
				
		try {
			if (connection == null) {
				connection = Conexao.getConnection();
			}
			String find = GeraSQL.select(map, "cartoes");
			
			preparedStatement = connection.prepareStatement(find);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				cartoes.add(new Cartao(
						rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getInt(5), rs.getString(6) ));
			}

			preparedStatement.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return cartoes;
	}
}
