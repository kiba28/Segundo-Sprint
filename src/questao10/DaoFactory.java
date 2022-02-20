package questao10;

public class DaoFactory {

	public static HumorDao createHumorDao() {
		return new HumorDaoJDBC(DB.getConnection());
	}

}
