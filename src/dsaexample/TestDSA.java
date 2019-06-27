package dsaexample;

import java.nio.charset.StandardCharsets;

public class TestDSA {
    public static void main(String[] args) {
        try {
            ServidorDeAssinatura servidorDeAssinatura = new ServidorDeAssinatura(
                    "DSA", "SHA256withDSA", 2048
            );

            String original = "wagner";
            System.out.println("Criando assinatura para " + original);

            byte[] assinatura = servidorDeAssinatura.criarAssinatura(original.getBytes());
            System.out.println("Assinatura: \n" + new String(assinatura, StandardCharsets.US_ASCII));
            System.out.println();

            System.out.println("Verificando assinatura: \n"
                    + new String(assinatura, StandardCharsets.US_ASCII)
                    + "\n para: " + original);
            System.out.println();

            boolean resultado = servidorDeAssinatura.verificarAssinatura(original.getBytes(), assinatura);
            System.out.println("A assinatura está " + (resultado ? "correta" : "errada"));
            System.out.println();

            System.out.println("Alterando dados orinais, concatenando ' modificado'");
            String editado = original + " modificado";
            System.out.println();

            System.out.println("Tentando verificar os dados alterados com a assinatura do original");
            System.out.println("Verificando assinatura:\n"
                    + new String(assinatura, StandardCharsets.US_ASCII)
                    + "\n para: " + editado);
            System.out.println();

            boolean resultado2 = servidorDeAssinatura.verificarAssinatura(editado.getBytes(), assinatura);
            System.out.println("A assinatura está: " + (resultado2 ? "correta" : "errada"));

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}
