package questao10;

import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);

		System.out.println("Digite uma mensagem: ");
		String humor = scan.nextLine();

		System.out.println(VerificaHumor.verificaHumor(humor));

		scan.close();
	}

}
