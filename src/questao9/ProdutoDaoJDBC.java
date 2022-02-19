package questao9;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDaoJDBC implements ProdutoDao {

	private Connection conn;

	public ProdutoDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Produto obj) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement(
					"INSERT INTO produto (nome, descricao, desconto, data_inicio) values (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			st.setString(1, obj.getNome());
			st.setString(2, obj.getDescricao());
			st.setDouble(3, obj.getDesconto());
			st.setDate(4, new java.sql.Date(obj.getDataInicio().getTime()));

			int rowsAffected = st.executeUpdate();

			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Unexpected error!! No rows affected!");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void update(Produto obj) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("UPDATE produto SET nome = ?, descricao = ?, desconto = ? WHERE id = ?");

			st.setString(1, obj.getNome());
			st.setString(2, obj.getDescricao());
			st.setDouble(3, obj.getDesconto());
			st.setInt(4, obj.getId());

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("DELETE FROM produto WHERE id = ?");

			st.setInt(1, id);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Produto findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM produto WHERE id = ?");
			st.setInt(1, id);
			rs = st.executeQuery();

			if (rs.next()) {
				Produto prod = instantiateProduto(rs);
				return prod;
			}
			return null;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	@Override
	public List<Produto> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;

		try {
			st = conn.prepareStatement("SELECT * FROM produto");
			rs = st.executeQuery();

			List<Produto> list = new ArrayList<Produto>();

			while (rs.next()) {
				Produto prod = instantiateProduto(rs);
				list.add(prod);
			}
			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
	}

	private Produto instantiateProduto(ResultSet rs) throws SQLException {
		return new Produto(rs.getInt("id"), rs.getString("nome"), rs.getString("descricao"), rs.getDouble("desconto"),
				rs.getDate("data_inicio"));
	}

}
