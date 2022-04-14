import java.util.ArrayList;

/*
  CompScanner

  Possui apenas um método público que lê uma string e retorna uma lista de tokens.

  Caso haja algum erro, utiliza a exception InvalidCharacterException para indicar erros
*/
public class CompScanner {

  private boolean isDelimiter(char character) {
    return character == ' ' || character == '\n' || character == '\t';
  }

  private boolean isLetterOrLegalSpecialChar(char character) {
    return (character >= 'a' && character <= 'z') || (character >= 'A' && character <= 'Z') || character == '_'
        || character == '$';
  }

  private boolean isDigit(char character) {
    return character >= '0' && character <= '9';
  }

  // Adiciona um token do tipo id na lista de tokens e devolve onde parou
  private int readNextIdToken(int start, char[] characters, ArrayList<Token> tokens) throws InvalidCharacterException {
    StringBuilder builder = new StringBuilder();

    int index = start;

    while (index < characters.length && !isDelimiter(characters[index])) {

      if (isDigit(characters[index]) || isLetterOrLegalSpecialChar(characters[index])) {
        builder.append(characters[index]);
      } else {
        throw new InvalidCharacterException("Caracter invalido durante a leitura de um identificador");
      }

      index++;
    }

    tokens.add(new Token("id", builder.toString()));
    return index;
  }

  // Adiciona um token do tipo int/float na lista de tokens e devolve onde parou
  private int readNextNumberToken(int start, char[] characters, ArrayList<Token> tokens)
      throws InvalidCharacterException {
    StringBuilder builder = new StringBuilder();

    int index = start;
    boolean hasOneDot = false;

    while (index < characters.length && !isDelimiter(characters[index])) {
      if (hasOneDot && characters[index] == '.') {
        throw new InvalidCharacterException("Ponto flutuante so pode ter um ponto separador");
      } else if (characters[index] == '.') {
        builder.append('.');
        hasOneDot = true;
      } else if (isDigit(characters[index])) {
        builder.append(characters[index]);
      } else {
        throw new InvalidCharacterException("Caracter inválido durante a leitura de um número");
      }

      index++;
    }

    if (builder.charAt(builder.length() - 1) == '.') {
      throw new InvalidCharacterException("O scanner nao aceito `.` ao final de floats");
    }

    String tokenType = hasOneDot ? "float" : "int";
    tokens.add(new Token(tokenType, builder.toString()));

    return index;
  }

  public ArrayList<Token> getTokens(String source) throws InvalidCharacterException {
    ArrayList<Token> tokens = new ArrayList<Token>();
    int length = source.length();
    int current_index = 0;
    char[] characters = source.toCharArray();

    while (current_index < length) {
      if (isLetterOrLegalSpecialChar(characters[current_index])) {
        current_index = readNextIdToken(current_index, characters, tokens) + 1;
      } else if (isDigit(characters[current_index])) {
        current_index = readNextNumberToken(current_index, characters, tokens) + 1;
      } else if (isDelimiter(characters[current_index])) {
        current_index++;
      } else {
        throw new InvalidCharacterException("Caracter inválido");
      }
    }

    return tokens;
  }
}
