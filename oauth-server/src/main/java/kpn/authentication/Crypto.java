package kpn.authentication;

import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public class Crypto {

    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int KEY_SIZE = 32;
    private static final int IV_SIZE = 16;

    private final SecretKey secretKey;

    public Crypto() {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("AES");
            int keySizeBits = KEY_SIZE * 8;
            generator.init(keySizeBits, RANDOM);
            this.secretKey = generator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] encrypt(byte[] clearText) {
        byte[] ivBytes = new byte[IV_SIZE];
        RANDOM.nextBytes(ivBytes);
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));
            byte[] cipherBytes = cipher.doFinal(clearText);
            return concat(ivBytes, cipherBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] decrypt(byte[] cipherText) {
        byte[][] byteArrays = split(cipherText);
        byte[] ivBytes = byteArrays[0];
        byte[] cipherBytes = byteArrays[1];
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(ivBytes));
            return cipher.doFinal(cipherBytes);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static byte[] concat(byte[] ivBytes, byte[] cipherBytes) {
        byte[] concatenatedBytes = new byte[ivBytes.length + cipherBytes.length];
        System.arraycopy(ivBytes, 0, concatenatedBytes, 0, ivBytes.length);
        System.arraycopy(cipherBytes, 0, concatenatedBytes, ivBytes.length, cipherBytes.length);
        return concatenatedBytes;
    }

    private static byte[][] split(byte[] concatenatedBytes) {
        byte[] ivBytes = new byte[IV_SIZE];
        byte[] cipherBytes = new byte[concatenatedBytes.length - IV_SIZE];
        System.arraycopy(concatenatedBytes, 0, ivBytes, 0, IV_SIZE);
        System.arraycopy(concatenatedBytes, IV_SIZE, cipherBytes, 0, concatenatedBytes.length - IV_SIZE);
        return new byte[][]{ivBytes, cipherBytes};
    }

}
