package questao10;

public class VerificaHumor {

	public static String verificaHumor(String humor) {
		humor += " ";
		int divertido = 0;
		int chateado = 0;

		if (humor.contains(":-)")) {
			String[] lista = humor.split(":-\\)");
			divertido = lista.length - 1;
		}
		if (humor.contains(":-(")) {
			String[] lista2 = humor.split(":-\\(");
			chateado = lista2.length - 1;
		}

		if (divertido > 0 || chateado > 0) {
			if (divertido > chateado) {
				return "Divertido";
			} else if (divertido == chateado) {
				return "Neutro";
			} else {
				return "Chateado";
			}
		} else {
			return "Neutro";
		}
	}

}
