package questao10;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class HumorDaoJDBC implements HumorDao {

	Connection conn = null;

	public HumorDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(String humor) {

		PreparedStatement st = null;

		try {
			st = conn.prepareStatement("INSERT INTO humor (humor) values (?)");

			st.setString(1, humor);

			st.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

}
