package dsaexample;

import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;

public class TestDSA {
    public static void main(String[] args) {
        try {
            DSA dsa = new DSA();

            byte[] bytes = {'w', 'a', 'g', 'n', 'e', 'r'};
            System.out.println("Criando assinatura para " + new String(bytes, StandardCharsets.UTF_8));

            byte[] sign = dsa.createSignature(bytes);
            System.out.println("Assinatura: " + new String(sign, StandardCharsets.US_ASCII));

            System.out.println("Verificando assinatura: " + new String(sign, StandardCharsets.US_ASCII));
            boolean result = dsa.verifySignature(sign);

            System.out.println("A assinatura está " + (result ? "correta" : "errada"));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
