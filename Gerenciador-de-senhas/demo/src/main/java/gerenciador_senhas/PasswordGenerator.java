package gerenciador_senhas;

import java.security.SecureRandom;

public class PasswordGenerator {
    private static final String LETRAS_MAIUSCULAS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LETRAS_MINUSCULAS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITOS = "0123456789";
    private static final String ESPECIAIS = "!@#$%^&*()-_+=<>?";
    private static final String TODOS_CARACTERES = LETRAS_MAIUSCULAS + LETRAS_MINUSCULAS + DIGITOS + ESPECIAIS;

    private static final SecureRandom random = new SecureRandom();

    public static String gerarSenha(int tamanho) {
        if (tamanho < 8) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 8 caracteres.");
        }

        StringBuilder senha = new StringBuilder(tamanho);

        // Garantir pelo menos um de cada tipo
        senha.append(pegarAleatorio(LETRAS_MAIUSCULAS));
        senha.append(pegarAleatorio(LETRAS_MINUSCULAS));
        senha.append(pegarAleatorio(DIGITOS));
        senha.append(pegarAleatorio(ESPECIAIS));

        // Preencher o restante com caracteres variados
        for (int i = 4; i < tamanho; i++) {
            senha.append(pegarAleatorio(TODOS_CARACTERES));
        }

        return embaralhar(senha.toString());
    }

    private static String pegarAleatorio(String caracteres) {
        int index = random.nextInt(caracteres.length());
        return String.valueOf(caracteres.charAt(index));
    }

    private static String embaralhar(String input) {
        char[] a = input.toCharArray();
        for (int i = 0; i < a.length; i++) {
            int j = random.nextInt(a.length);
            char temp = a[i];
            a[i] = a[j];
            a[j] = temp;
        }
        return new String(a);
    }
}
