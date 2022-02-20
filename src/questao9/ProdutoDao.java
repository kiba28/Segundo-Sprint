package questao9;

import java.util.List;

public interface ProdutoDao {

	void insert(Produto obj);

	void update(Produto obj);

	void deleteById(Integer id);

	Produto findById(Integer id);

	List<Produto> findAll();
	
	List<Produto> findByWord(String word);
}