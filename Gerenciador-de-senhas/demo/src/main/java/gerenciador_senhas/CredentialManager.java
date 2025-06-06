package gerenciador_senhas;
// CredentialManager.java - Gerencia credenciais e vazamentos
import java.util.Scanner;
import java.util.ArrayList;

public class CredentialManager {
    private static ArrayList<Credential> credenciais = new ArrayList<>();

    public static void addCredential(Scanner scanner) {
        System.out.print("Nome do serviço: ");
        String servico = scanner.nextLine();
        System.out.print("Usuário/Login: ");
        String usuario = scanner.nextLine();
        System.out.print("Deseja que o sistema gere uma senha segura para você? (s/n): ");
        String resposta = scanner.nextLine();
        String senha;

        if (resposta.equalsIgnoreCase("s")) {
            senha = PasswordGenerator.gerarSenha(12);
            System.out.println("Senha gerada: " + senha);
        } else {
            System.out.print("Senha: ");
            senha = scanner.nextLine();
        }


        if (LeakChecker.isLeaked(senha)) {
            System.out.println("⚠️ Essa senha já foi vazada! Por favor, escolha outra senha.");
            return;
        }

        String senhaCriptografada = PasswordEncryptor.encrypt(senha);
        Credential cred = new Credential(servico, usuario, senhaCriptografada);
        credenciais.add(cred);
        System.out.println("Credencial salva com segurança!");
    }

    public static void listCredentials(Scanner scanner) {
        if (credenciais.isEmpty()) {
            System.out.println("Nenhuma credencial cadastrada.");
            return;
        }
        System.out.println("--- Credenciais Cadastradas ---");
        for (Credential cred : credenciais) {
            System.out.println("Serviço: " + cred.getServico());
            System.out.println("Usuário: " + cred.getUsuario());
            String senha = PasswordEncryptor.decrypt(cred.getSenhaCriptografada());
            System.out.println("Senha: " + senha);
            if (LeakChecker.isLeaked(senha)) {
                System.out.println("⚠️ Senha vazada detectada!");
                System.out.print("Deseja manter esta senha mesmo assim? (s/n): ");
                String resposta = scanner.nextLine();
                if (!resposta.equalsIgnoreCase("s")) {
                    System.out.println("Por favor, cadastre uma nova senha para este serviço posteriormente.");
                } else {
                    System.out.println("Senha mantida pelo usuário.");
                }
            } else {
                System.out.println("Senha segura.");
            }
            System.out.println("--------------------------------");
        }
    }

    public static void checkLeakedCredentials() {
        System.out.println("--- Verificando credenciais vazadas ---");
        boolean encontrou = false;
        for (Credential cred : credenciais) {
            String senha = PasswordEncryptor.decrypt(cred.getSenhaCriptografada());
            if (LeakChecker.isLeaked(senha)) {
                System.out.println("⚠️ Vazamento detectado!");
                System.out.println("Serviço: " + cred.getServico());
                System.out.println("Usuário: " + cred.getUsuario());
                encontrou = true;
            }
        }
        if (!encontrou) {
            System.out.println("Nenhuma credencial vazada detectada.");
        }
    }
}
