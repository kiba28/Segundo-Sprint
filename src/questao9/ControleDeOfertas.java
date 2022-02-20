package questao9;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ControleDeOfertas {

	private ProdutoDao produtoDao;

	public ControleDeOfertas() {
		this.produtoDao = DaoFactory.createProdutoDao();
	}

	public void options() {

		Scanner scan = new Scanner(System.in);
		int i = -1;

		while (i != 0) {
			System.out.println("========== SYSTEM ==========");
			listOptions();
			i = scan.nextInt();

			switch (i) {
			case 1: {
				System.out.println("Digite os dados da oferta que deseja criar: ");
				Produto prod = new Produto();
				prod = preencheProduto(prod);
				produtoDao.insert(prod);
				System.out.println("Produto criado, ID: " + prod.getId());
				insertTresProdutos();

				break;
			}

			case 2: {
				System.out.println("Digite o ID da oferta que deseja alterar: ");
				int id = scan.nextInt();
				Produto prod = produtoDao.findById(id);

				if (prod == null) {
					System.out.println("Não existe oferta com este ID. Inserinto nova oferta, digite os dados: ");
					prod = new Produto();
					prod = preencheProduto(prod);
					produtoDao.insert(prod);
					insertTresProdutos();
				} else {
					System.out.println("Digite os dados para alterar: ");
					prod = preencheProduto(prod);
					produtoDao.update(prod);
				}

				break;
			}

			case 3: {
				System.out.println("Digite o ID da oferta que deseja deletar: ");
				int id = scan.nextInt();
				Produto prod = produtoDao.findById(id);

				if (prod == null) {
					System.out.println("Não existe oferta com esse ID.");
				} else {
					produtoDao.deleteById(id);
				}

				break;
			}

			case 4: {
				List<Produto> listProd = produtoDao.findAll();
				for (Produto produto : listProd) {
					System.out.println(produto.toString());
				}

				break;
			}

			case 5: {
				System.out.println("Digite a paravra que deseja verificar: ");
				scan.nextLine();
				String palavra = scan.nextLine();

				List<Produto> listProd = produtoDao.findByWord(palavra);

				if (listProd.size() > 0) {
					for (Produto produto : listProd) {
						System.out.println(produto.toString());
					}
				} else {
					System.out.println("Nenhuma oferta com esta palavra.");
				}

				break;
			}

			case 0: {
				i = 0;
				break;
			}

			default:
				System.out.println("Opção invalidada, tente novamente.");
			}
		}
		scan.close();
	}

	private void listOptions() {
		System.out.println("Digite a opção desejada: ");
		System.out.println("1 - para INSERIR uma nova oferta");
		System.out.println("2 - para ATUALIZAR uma nova oferta");
		System.out.println("3 - para DELETAR uma nova oferta");
		System.out.println("4 - para listar todas as ofertas cadastradas");
		System.out.println("5 - para listar ofertas que contém uma palavra");
		System.out.println("0 - para SAIR");
	}

	private Produto preencheProduto(Produto prod) {
		Scanner scan = new Scanner(System.in);
		System.out.println("Nome: ");
		prod.setNome(scan.nextLine());
		System.out.println("Descrição: ");
		prod.setDescricao(scan.nextLine());
		System.out.println("Desconto em porcentagem: ");
		prod.setDesconto(scan.nextDouble() / 100);
		prod.setDataInicio(new Date());
		return prod;
	}

	private void insertTresProdutos() {
		List<Produto> listProd = new ArrayList<Produto>();
		Random random = new Random();

		listProd = produtoDao.findAll();
		for (int i = 1; i <= 3; i++) {
			int aleatorio = random.nextInt(listProd.size() - 1);
			produtoDao.insert(listProd.get(aleatorio));
			System.out.println("Produto criado, ID: " + listProd.get(aleatorio).getId());

		}
	}

}
