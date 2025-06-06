package gerenciador_senhas;

import java.util.Scanner;

public class Main {
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Gerenciador Seguro de Senhas ---");

        // Simula login do usuário
        System.out.print("Email do usuário: ");
        String email = scanner.nextLine();

        // Configura o 2FA para o usuário
        Authenticator.cadastrar2FA(email);

        // Solicita código do Google Authenticator
        boolean validado = Authenticator.verificarCodigo2FA();
        if (!validado) {
            System.out.println("❌ Código inválido. Acesso negado.");
            return;
        }

        System.out.println("✅ Acesso autorizado com 2FA!");

        while (true) {
            System.out.println("\n1. Cadastrar nova credencial");
            System.out.println("2. Listar credenciais cadastradas");
            System.out.println("3. Gerar senha segura");
            System.out.println("4. Verificar vazamento de senha");
            System.out.println("5. Listar credenciais vazadas");
            System.out.println("6. Sair");
            System.out.print("Escolha: ");
            int escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar buffer

            switch (escolha) {
                case 1:
                    CredentialManager.addCredential(scanner);
                    break;
                case 2:
                    CredentialManager.listCredentials(scanner);
                    break;
                case 3:
                    System.out.println("Senha gerada: " + PasswordGenerator.gerarSenha(12));
                    break;
                case 4:
                    System.out.print("Digite a senha para verificação: ");
                    String senha = scanner.nextLine();
                    LeakChecker.checkLeak(senha);
                    break;
                case 5:
                    CredentialManager.checkLeakedCredentials();
                    break;
                case 6:
                    System.out.println("Saindo...");
                    return;
                default:
                    System.out.println("Opção inválida.");
            }
        }
    }
}
