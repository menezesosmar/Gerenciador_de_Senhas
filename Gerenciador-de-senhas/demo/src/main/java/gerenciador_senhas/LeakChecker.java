package gerenciador_senhas;

// LeakChecker.java - Adiciona método isLeaked para uso interno
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;

public class LeakChecker {
    public static void checkLeak(String senha) {
        if (isLeaked(senha)) {
            System.out.println("\n⚠️ Essa senha já foi vazada! Troque imediatamente.");
        } else {
            System.out.println("\n✅ Essa senha NÃO está presente em vazamentos conhecidos.");
        }
    }

    public static boolean isLeaked(String senha) {
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
            byte[] hash = sha1.digest(senha.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder();
            for (byte b : hash) {
                sb.append(String.format("%02X", b));
            }
            String hashSenha = sb.toString();
            String prefixo = hashSenha.substring(0, 5);
            String sufixo = hashSenha.substring(5);

            URL url = new URL("https://api.pwnedpasswords.com/range/" + prefixo);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                if (inputLine.startsWith(sufixo)) {
                    return true;
                }
            }
            in.close();
        } catch (Exception e) {
            System.out.println("Erro ao verificar vazamento: " + e.getMessage());
        }
        return false;
    }
}