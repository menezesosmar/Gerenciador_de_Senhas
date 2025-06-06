package gerenciador_senhas;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.warrenstrange.googleauth.GoogleAuthenticator;
import com.warrenstrange.googleauth.GoogleAuthenticatorKey;
import com.warrenstrange.googleauth.GoogleAuthenticatorQRGenerator;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Scanner;

public class Authenticator {

    private static GoogleAuthenticator gAuth = new GoogleAuthenticator();
    private static String secretKey;

    public static void cadastrar2FA(String userEmail) {
        GoogleAuthenticatorKey key = gAuth.createCredentials();
        secretKey = key.getKey();

        String otpAuthURL = GoogleAuthenticatorQRGenerator.getOtpAuthURL("PasswordManagerApp", userEmail, key);

        gerarQRCodeImagem(otpAuthURL);

        System.out.println("\n✅ QR Code gerado com sucesso.");
        System.out.println("Abra a imagem gerada para escanear no Google Authenticator.");
        System.out.println("Ou adicione manualmente este código secreto: " + secretKey);
    }

    public static boolean verificarCodigo2FA() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite o código de 6 dígitos do Google Authenticator: ");
        int code = scanner.nextInt();
        return gAuth.authorize(secretKey, code);
    }

    private static void gerarQRCodeImagem(String texto) {
        int width = 300;
        int height = 300;
        String filePath = "QRCode.png";
        QRCodeWriter qrCodeWriter = new QRCodeWriter();

        try {
            BitMatrix bitMatrix = qrCodeWriter.encode(texto, BarcodeFormat.QR_CODE, width, height);
            Path path = FileSystems.getDefault().getPath(filePath);
            MatrixToImageWriter.writeToPath(bitMatrix, "PNG", path);
        } catch (WriterException | IOException e) {
            System.out.println("Erro ao gerar QR Code: " + e.getMessage());
        }
    }

    public static String getSecretKey() {
        return secretKey;
    }

    public static void setSecretKey(String secret) {
        secretKey = secret;
    }
}
