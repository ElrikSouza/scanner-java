import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static void printTokens(ArrayList<Token> tokens) {
		for (Token token : tokens) {
			System.out.println(token);
		}
	}

	private static void testString(String string) {
		System.out.println("Input atual: " + string);

		try {
			CompScanner compScanner = new CompScanner();
			ArrayList<Token> tokens = compScanner.getTokens(string);
			printTokens(tokens);
		} catch (InvalidCharacterException ice) {
			System.out.println("Scanner rejeitou o input");
		} catch (Exception unknown) {
			System.out.println("Um erro desconhecido ocorreu");
		}

		System.out.println("---------------------");
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		while (scanner.hasNextLine()) {
			testString(scanner.nextLine());
		}

		scanner.close();
	}
}
