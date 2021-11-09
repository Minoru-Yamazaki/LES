package br.com.ecommerceorquideas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import br.com.ecommerceorquideas.model.Analise;
import br.com.ecommerceorquideas.model.Compra;
import br.com.ecommerceorquideas.model.Produto;
import br.com.ecommerceorquideas.util.Conexao;
import br.com.ecommerceorquideas.util.GeraSQL;

public class CompraAnaliseDAO {
	private Connection connection;
	private PreparedStatement preparedStatement = null;

	public CompraAnaliseDAO() {
	}
	
	public List<Compra> consultar(Analise analise) {
		List<Compra> compras = new ArrayList<Compra>();
		Compra compra;
		
		try {
			connection = Conexao.getConnection();
			connection.setAutoCommit(false);
			
			String select = GeraSQL.analiseSelect(analise);
			
			preparedStatement = connection.prepareStatement(select);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				
				compra = new Compra(rs.getInt(1), rs.getString(3), rs.getDate(4), rs.getDouble(5),
						rs.getDouble(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13),
						rs.getString(14), rs.getString(15), rs.getString(16), rs.getInt(17));
				
				HashMap<String, String> map = new HashMap<>();
				map.put("com_id", compra.getId().toString());
				ProdutoDAO dao = new ProdutoDAO(connection);
				compra.setProdutos((List<Produto>) dao.consultar(map));
								
				compras.add(compra);
			}

			preparedStatement.close();

		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return compras;
	}
}
