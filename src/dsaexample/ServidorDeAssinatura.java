package dsaexample;

import java.security.*;

public class ServidorDeAssinatura {
    private String algoritmoDeGeraçãoDeChaves;
    private String algoritmoDeAssinatura;
    private PrivateKey chavePrivada;
    private PublicKey chavePublica;

    public ServidorDeAssinatura(String algoritmoDeGeraçãoDeChaves, String algoritmoDeAssinatura, int tamanhoDaChave) throws NoSuchAlgorithmException {
        this.algoritmoDeGeraçãoDeChaves = algoritmoDeGeraçãoDeChaves;
        this.algoritmoDeAssinatura = algoritmoDeAssinatura;

        KeyPairGenerator geradorDeChaves = KeyPairGenerator.getInstance(this.algoritmoDeGeraçãoDeChaves);
        //Initializing the key pair generator
        geradorDeChaves.initialize(tamanhoDaChave);

        //Generate the pair of keys
        KeyPair parDeChaves = geradorDeChaves.generateKeyPair();
        chavePrivada = parDeChaves.getPrivate();
        chavePublica = parDeChaves.getPublic();
    }

    byte[] criarAssinatura(byte[] bytes) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

            //Creating a Signature object
            Signature sign = Signature.getInstance(algoritmoDeAssinatura);

            //Initialize the signature

        sign.initSign(chavePrivada);
        sign.update(bytes);
            //Calculating the signature
            return sign.sign();
    }

    boolean verificarAssinatura(byte[] dados, byte[] asasinatura) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature sign = Signature.getInstance(algoritmoDeAssinatura);
        sign.initVerify(chavePublica);
        sign.update(dados);

        //Verifying the signature
        return sign.verify(asasinatura);
    }

}
