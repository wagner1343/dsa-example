package dsaexample;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.security.*;
import java.sql.SQLException;
import java.util.Scanner;

public class DSA {
    private PrivateKey privateKey;
    private PublicKey publicKey;

    public DSA() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("DSA");

        //Initializing the key pair generator
        keyPairGen.initialize(2048);

        //Generate the pair of keys
        KeyPair pair = keyPairGen.generateKeyPair();
        privateKey = pair.getPrivate();
        publicKey = pair.getPublic();
    }

    byte[] createSignature(byte[] bytes) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {

            //Creating a Signature object
            Signature sign = Signature.getInstance("SHA256withDSA");

            //Initialize the signature
            sign.initSign(privateKey);

            //Adding data to the signature
            sign.update(bytes);

            //Calculating the signature
            byte[] signature = sign.sign();

            return signature;
    }

    boolean verifySignature(byte[] bytes) throws NoSuchAlgorithmException, InvalidKeyException, SignatureException {
        Signature sign = Signature.getInstance("SHA256withDSA");
        sign.initVerify(publicKey);
        sign.update(bytes);

        //Verifying the signature
        return sign.verify(bytes);
    }

}
