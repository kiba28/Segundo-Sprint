package questao10;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		HumorDao dao = DaoFactory.createHumorDao();

		System.out.println("Digite uma mensagem: ");
		String humor = scan.nextLine();

		String resultado = VerificaHumor.verificaHumor(humor);

		dao.insert(resultado);
		System.out.println(resultado);

		scan.close();
	}

}
