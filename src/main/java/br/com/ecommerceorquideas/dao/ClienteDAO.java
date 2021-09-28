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

import br.com.ecommerceorquideas.domain.Cartao;
import br.com.ecommerceorquideas.domain.Cliente;
import br.com.ecommerceorquideas.domain.Cupom;
import br.com.ecommerceorquideas.domain.Endereco;
import br.com.ecommerceorquideas.domain.EntidadeDominio;
import br.com.ecommerceorquideas.domain.Login;
import br.com.ecommerceorquideas.domain.Compra;
import br.com.ecommerceorquideas.util.Conexao;
import br.com.ecommerceorquideas.util.GeraSQL;
import br.com.ecommerceorquideas.warning.Aviso;

public class ClienteDAO implements IDAO {

	private static final String INSERT = "INSERT INTO clientes(cpf, nome, sexo, data_nascimento, telefone, tipo_telefone) VALUES(?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE clientes SET cpf=?, nome=?, sexo=?, data_nascimento=?, telefone=?, tipo_telefone=? WHERE id=?";
	private static final String DELETE = "DELETE FROM clientes WHERE id=?";

	private Connection connection;
	private PreparedStatement preparedStatement = null;
	
	private Aviso aviso;

	@Override
	public Object salvar(EntidadeDominio entidade){
		Cliente cliente = (Cliente) entidade;
		aviso = new Aviso();
		
		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setString(1, cliente.getCpf());
			preparedStatement.setString(2, cliente.getNome());
			preparedStatement.setString(3, cliente.getSexo());
			Timestamp time = new Timestamp(cliente.getNascimento().getTime());
			preparedStatement.setTimestamp(4, time);
			preparedStatement.setString(5, cliente.getTelefone());
			preparedStatement.setString(6, cliente.getTipoTelefone());

			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();
			if (rs.next()) {
				cliente.setId(rs.getInt(1));
				cliente.getLogin().setId(rs.getInt(1));
			}

			LoginDAO loginDAO = new LoginDAO(connection);
			loginDAO.salvar(cliente.getLogin()); 

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
		Cliente cliente = (Cliente) entidade;
		aviso = new Aviso();
		
		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(UPDATE);

			preparedStatement.setString(1, cliente.getCpf());
			preparedStatement.setString(2, cliente.getNome());
			preparedStatement.setString(3, cliente.getSexo());
			Timestamp time = new Timestamp(cliente.getNascimento().getTime());
			preparedStatement.setTimestamp(4, time);
			preparedStatement.setString(5, cliente.getTelefone());
			preparedStatement.setString(6, cliente.getTipoTelefone());
			preparedStatement.setInt(7, cliente.getId());

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
		List<Object> clientes = new ArrayList<>();
		Cliente cliente;
		IDAO dao;

		try {
			connection = Conexao.getConnection();

			String find = GeraSQL.select(map, "clientes");
			preparedStatement = connection.prepareStatement(find);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				cliente = new Cliente(rs.getInt(1), rs.getString(2), rs.getString(3),
						rs.getString(4), rs.getDate(5), rs.getString(6), rs.getString(7));

				map = new HashMap<>();
				map.put("cli_id", cliente.getId().toString());
				dao = new CartaoDAO(connection);
				cliente.setCartoes((List<Cartao>) dao.consultar(map));

				dao = new EnderecoDAO(connection);
				cliente.setEnderecos((List<Endereco>) dao.consultar(map));

				dao = new LoginDAO(connection);
				List<Login> logins = (List<Login>) dao.consultar(map);
				cliente.setLogin(logins.get(0));
				
				dao = new CupomDAO(connection);
				cliente.setCupons((List<Cupom>) dao.consultar(map));
				
				dao = new CompraDAO(connection);
				cliente.setPedidos((List<Compra>) dao.consultar(map));
				
				clientes.add(cliente);
			}

			preparedStatement.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return clientes;
	}

	private void exluirDados(Connection connection, Integer id) throws SQLException {
		
		try {
			IDAO dao = new LoginDAO(connection);
			dao.excluir(id);

			HashMap<String, String> map = new HashMap<>();
			map.put("cli_id", id.toString());
			dao = new EnderecoDAO(connection);
			List<Endereco> enderecos = (List<Endereco>) dao.consultar(map);
			for (EntidadeDominio endereco : enderecos) {
				dao.excluir(endereco.getId());
			}

			dao = new CartaoDAO(connection);
			List<Cartao> cartoes = (List<Cartao>) dao.consultar(map);
			for (EntidadeDominio cartao : cartoes) {
				dao.excluir(cartao.getId());
			}
		} catch (Exception e) {
			throw e;
		}
		
	}
}
