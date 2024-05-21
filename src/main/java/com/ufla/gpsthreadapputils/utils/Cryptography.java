package com.ufla.gpsthreadapputils.utils;
import android.util.Base64;
import android.util.Log;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class Cryptography {

    // Chave estática usada para a criptografia/descriptografia.
    // Importante: usar chaves estáticas em código é inseguro para produção!
    private static final String KEY = "0123456789abcdef";

    /**
     * Criptografa uma string usando AES/ECB/PKCS5Padding.
     * @param data A string que será criptografada.
     * @return String A string criptografada codificada em Base64 ou null em caso de erro.
     */
    public static String encrypt(String data) {
        try {
            // Configura a chave e o algoritmo de criptografia
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            // Realiza a criptografia e codifica o resultado em Base64
            byte[] encryptedBytes = cipher.doFinal(data.getBytes());
            return Base64.encodeToString(encryptedBytes, Base64.DEFAULT);
        } catch (Exception e) {
            Log.e("Cryptography", "Encryption error: " + e.getMessage());
            return null;
        }
    }

    /**
     * Descriptografa uma string criptografada e codificada em Base64 usando AES/ECB/PKCS5Padding.
     * @param encryptedData A string criptografada em Base64.
     * @return String A string descriptografada ou null em caso de erro.
     */
    public static String decrypt(String encryptedData) {
        try {
            // Configura a chave e o algoritmo de descriptografia
            SecretKeySpec keySpec = new SecretKeySpec(KEY.getBytes(), "AES");
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            // Decodifica a string de Base64 e realiza a descriptografia
            byte[] decodedBytes = Base64.decode(encryptedData, Base64.DEFAULT);
            byte[] decryptedBytes = cipher.doFinal(decodedBytes);
            return new String(decryptedBytes);
        } catch (Exception e) {
            Log.e("Cryptography", "Decryption error: " + e.getMessage());
            return null;
        }
    }
}
