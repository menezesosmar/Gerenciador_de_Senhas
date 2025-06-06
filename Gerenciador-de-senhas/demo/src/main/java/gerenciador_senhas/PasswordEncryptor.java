package gerenciador_senhas;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class PasswordEncryptor {
    private static final String CHAVE = "1234567890123456";

    public static String encrypt(String senha) {
        try {
            SecretKeySpec chave = new SecretKeySpec(CHAVE.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, chave);
            byte[] encrypted = cipher.doFinal(senha.getBytes());
            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String decrypt(String senhaCriptografada) {
        try {
            SecretKeySpec chave = new SecretKeySpec(CHAVE.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, chave);
            byte[] decrypted = cipher.doFinal(Base64.getDecoder().decode(senhaCriptografada));
            return new String(decrypted);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}


