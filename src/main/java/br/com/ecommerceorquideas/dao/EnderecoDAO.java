package br.com.ecommerceorquideas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.domain.Endereco;
import br.com.ecommerceorquideas.domain.EntidadeDominio;
import br.com.ecommerceorquideas.util.Conexao;
import br.com.ecommerceorquideas.util.GeraSQL;
import br.com.ecommerceorquideas.warning.Aviso;

public class EnderecoDAO implements IDAO{

	private static final String INSERT = "INSERT INTO enderecos(cidade, estado, pais, bairro, tipo_logradouro, logradouro, numero, complemento, tipo_endereco, cli_id) VALUES(?,?,?,?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE enderecos SET cidade=?, estado=?, pais=?, bairro=?, tipo_logradouro=?, logradouro=?, numero=?, complemento=?, tipo_endereco=? WHERE id=?";
	private static final String DELETE = "DELETE FROM enderecos WHERE id=?";

	private Connection connection;
	private PreparedStatement preparedStatement = null;
	
	private Aviso aviso;

	public EnderecoDAO() {}
	
	public EnderecoDAO(Connection connection) {
		this.connection = connection;
	}

	@Override
	public Object salvar(EntidadeDominio entidade){
		Endereco endereco = (Endereco) entidade;
		boolean commit = false;
		aviso = new Aviso();
		
		try {
			if (connection == null) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
				commit = true;
			}
			preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, endereco.getCidade());
			preparedStatement.setString(2, endereco.getEstado());
			preparedStatement.setString(3, endereco.getPais());
			preparedStatement.setString(4, endereco.getBairro());
			preparedStatement.setString(5, endereco.getTipoLogradouro());
			preparedStatement.setString(6, endereco.getLogradouro());
			preparedStatement.setString(7, endereco.getNumero());
			preparedStatement.setString(8, endereco.getComplemento());
			preparedStatement.setString(9, endereco.getTipoEndereco());
			preparedStatement.setInt(10, endereco.getIdCliente());
			
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {
				endereco.setId(rs.getInt(1));
			}
			preparedStatement.close();
			
			if(commit) {
				connection.commit();
			}
			aviso.addMensagem("Endereço salvo com sucesso");
			
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
		Endereco endereco = (Endereco) entidade;
		boolean commit = false;
		aviso = new Aviso();
		
		try {
			if (connection == null) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
				commit = true;
			}
			preparedStatement = connection.prepareStatement(UPDATE);

			preparedStatement.setString(1, endereco.getCidade());
			preparedStatement.setString(2, endereco.getEstado());
			preparedStatement.setString(3, endereco.getPais());
			preparedStatement.setString(4, endereco.getBairro());
			preparedStatement.setString(5, endereco.getTipoLogradouro());
			preparedStatement.setString(6, endereco.getLogradouro());
			preparedStatement.setString(7, endereco.getNumero());
			preparedStatement.setString(8, endereco.getComplemento());
			preparedStatement.setString(9, endereco.getTipoEndereco());
			preparedStatement.setInt(10, endereco.getId());
			
			preparedStatement.executeUpdate();

			preparedStatement.close();
			if(commit) {
				connection.commit();
			}
			aviso.addMensagem("Endereço alterado com sucesso");
			
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
			aviso.addMensagem("Endereço excluido com sucesso");
			
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
	public Object consultar(HashMap<String, String> map) {
		List<EntidadeDominio> enderecos = new ArrayList<>();

		try {
			if (connection == null) {
				connection = Conexao.getConnection();
			}
			
			String find = GeraSQL.select(map, "enderecos");

			preparedStatement = connection.prepareStatement(find);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				enderecos.add(new Endereco(rs.getInt(1), rs.getString(2),
						rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getString(10), rs.getInt(11)));						
			}

			preparedStatement.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return enderecos;
	}
}
