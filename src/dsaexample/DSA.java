package dsaexample;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Arrays;

public class DSA {
    public static void main(String[] args) {
        if(args.length < 1 || args.length > 2)
            System.out.println("Como usar: java dsa assinar <dados>, ou java dsa verificar <dados> <assinatura>");

        if(args[0].equals("assinar"))
            assinar(args[1]);

        else
            verificar(args[1], args[2]);

    }


    public static void assinar(String dados){
        try{
            ServidorDeAssinatura servidorDeAssinatura = new ServidorDeAssinatura(
                    "DSA", "SHA256withDSA", 2048
            );
            System.out.println(Arrays.toString(servidorDeAssinatura.criarAssinatura(dados.getBytes())));
            System.out.println(servidorDeAssinatura.chavePublica);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }

    }

    public static void verificar(String dados, String assinatura){
        try{
            ServidorDeAssinatura servidorDeAssinatura = new ServidorDeAssinatura(
                    "DSA", "SHA256withDSA", 2048
            );
            System.out.println(servidorDeAssinatura.verificarAssinatura(dados.getBytes(), assinatura.getBytes()));

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
    }
}
