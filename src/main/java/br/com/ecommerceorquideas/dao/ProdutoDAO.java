package br.com.ecommerceorquideas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.domain.EntidadeDominio;
import br.com.ecommerceorquideas.domain.Produto;
import br.com.ecommerceorquideas.util.Conexao;
import br.com.ecommerceorquideas.util.GeraSQL;
import br.com.ecommerceorquideas.warning.Aviso;

public class ProdutoDAO implements IDAO{

	private static final String INSERT = "INSERT INTO produtos(pro_id, preco, nome, descricao, quantidade, sub_total, com_id) VALUES(?,?,?,?,?,?,?)";
	private static final String UPDATE = "UPDATE produtos SET pro_id=?, preco=?, nome=?, descricao=?, quantidade=?, sub_total=? WHERE id=?";
	private static final String DELETE = "DELETE FROM produtos WHERE id=?";
	
	private Connection connection;
	private PreparedStatement preparedStatement = null;

	private Aviso aviso;

	public ProdutoDAO() {
	}

	public ProdutoDAO(Connection connection) {
		this.connection = connection;
	}
	
	@Override
	public Object salvar(EntidadeDominio entidade) throws SQLException {
		Produto produto = (Produto) entidade;
		aviso = new Aviso();
		boolean commit = false;
		
		try {
			if (connection == null || connection.isClosed()) {
				connection = Conexao.getConnection();
				connection.setAutoCommit(false);
				commit = true;
			}

			preparedStatement = connection.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

			preparedStatement.setInt(1, produto.getIdProduto());
			preparedStatement.setDouble(2, produto.getPreco());
			preparedStatement.setString(3, produto.getNome());
			preparedStatement.setString(4, produto.getDescricao());
			preparedStatement.setInt(5, produto.getQuantidade());
			preparedStatement.setDouble(6, produto.getPreco() * produto.getQuantidade());
			preparedStatement.setInt(7, produto.getIdCompra());
						
			preparedStatement.executeUpdate();

			ResultSet rs = preparedStatement.getGeneratedKeys();

			if (rs.next()) {
				produto.setId(rs.getInt(1));
			}

			preparedStatement.close();
			
			if (commit) {
				connection.commit();
			}			
			aviso.addMensagem("Produto salvo com sucesso");

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
				if (commit) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return aviso;
	}

	@Override
	public Object alterar(EntidadeDominio entidade) {
		Produto produto = (Produto) entidade;
		aviso = new Aviso();

		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);

			preparedStatement = connection.prepareStatement(UPDATE);

			preparedStatement.setInt(1, produto.getIdProduto());
			preparedStatement.setDouble(2, produto.getPreco());
			preparedStatement.setString(3, produto.getNome());
			preparedStatement.setString(4, produto.getDescricao());
			preparedStatement.setInt(5, produto.getQuantidade());
			preparedStatement.setDouble(6, produto.getPreco() * produto.getQuantidade());
			preparedStatement.setInt(7, produto.getId());
			
			preparedStatement.executeUpdate();

			preparedStatement.close();
			connection.commit();

			aviso.addMensagem("Pedido alterado com sucesso");

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
			if (commit) {
				connection.commit();
			}
			aviso.addMensagem("Produto excluido com sucesso");

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
				if (commit) {
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
		List<EntidadeDominio> produtos = new ArrayList<>();

		try {
			if (connection == null || connection.isClosed()) {
				connection = Conexao.getConnection();
			}
			String find = GeraSQL.select(map, "produtos");

			preparedStatement = connection.prepareStatement(find);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				produtos.add(new Produto(rs.getInt(1), rs.getInt(2), rs.getDouble(3),
						rs.getString(4), rs.getString(5), rs.getInt(6), rs.getDouble(7), rs.getInt(8)));
			}

			preparedStatement.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return produtos;
	}

}
