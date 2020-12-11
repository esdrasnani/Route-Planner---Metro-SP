/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package routeserver;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
/**
 *
 * @author Gustavo_Moraes
 */
public class RSAUtil {
    
    private static String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCjAf4FQqky2n1gGVLjweKZub1qtSh6RMAc+BZJhsxGzFIW+V4Fu9SKc57W2af4sejyD57McTpC+XIqgCL7Zrc2XtWA2xp3hTQeHEOLblRVDnoVILbWJZnKYoF1CdGRMPfQFJrpQgbYMpyiZmw6OcTgmevILA1c+Uv/TwliShOO+QIDAQAB";
            
    private static String privateKey = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAKMB/gVCqTLafWAZUuPB4pm5vWq1KHpEwBz4FkmGzEbMUhb5XgW71IpzntbZp/ix6PIPnsxxOkL5ciqAIvtmtzZe1YDbGneFNB4cQ4tuVFUOehUgttYlmcpigXUJ0ZEw99AUmulCBtgynKJmbDo5xOCZ68gsDVz5S/9PCWJKE475AgMBAAECgYATd8JxuGNiumts1blrkBVTDLbNjrM13w4ep5Zp05qD/hTzv3Z2MteQbXyT5kzES+MpPapWelNd+lKjNsh2w+6vc6ImsCKLycsLTtvXMbMZ6w6+bBcKzIEVxf9lGss+A8wJxsYnqWNLg4FdD2uZdrQP4xK99IABPCDXSxWOROTqbQJBAOCoc3zDfOa8ck6vTcxQN5LgRsrPeNexMuHCjAYc1SpGKyXAmUsLjBjw7o6+QbZqg36/w5TRAblubr4I7p9AFUsCQQC5v7viohPTQzSOL/285E2wLCfLooTtfcK9P6zszK62hs/JJL1rGAzZ1ICyAwvVTN51hYlwnBlwI5PGMFLhPrZLAkEAyDhY9NCk+F7q4QWLqJO1c8k+TnIpmNHsLxhcAvafZFW1Dqhf9moMKg5QDheyKQk0uDnf7Tl49/Jb8DG1uWg7kQJAMNtjugrTmB/3NOceoI6UeKBEn2VR/X20uT6EZ4y277+p68hEImUjDDC+4RO4CxS69sJ3Ei2c60gQWHLheema3QJBAM/Er5Rjk1JipJJCjN/uUOtXTIzJoqJHZvZ1qP50+DHtspRjUUbzGLGtybQYXyB0dvdV9RLjT6aDOw/jDD58w8g=";
    
    public static PublicKey getPublicKey(String base64PublicKey){
        PublicKey publicKey = null;
        try{
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(base64PublicKey.getBytes()));
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }
    
    public static PrivateKey getPrivateKey(String base64PrivateKey){
        PrivateKey privateKey = null;
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(base64PrivateKey.getBytes()));
        KeyFactory keyFactory = null;
        try {
            keyFactory = KeyFactory.getInstance("RSA");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        try {
            privateKey = keyFactory.generatePrivate(keySpec);
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return privateKey;
    }
    
    public static byte[] encrypt(String data) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
	Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
	return cipher.doFinal(data.getBytes());
    }
    
    public static String decrypt(byte[] data) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.DECRYPT_MODE, getPrivateKey(privateKey));
        return new String(cipher.doFinal(data));
    }
    
    public static String decrypt(String data) throws IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
        return decrypt(Base64.getDecoder().decode(data.getBytes()));
    }
   
}

