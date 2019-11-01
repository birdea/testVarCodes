package com.birdea.testcode.encryt;

import com.birdea.testcode.util.Base64;
import com.birdea.testcode.util.L;

import java.io.File;

public class TestCryptoUtils {

    private final String TAG = "TestEncryptCode";

    private final String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAuxqUjkLk04AJjSswWGzXZp76fVUYI0wbAPsWD2Es5S2uvcpMnhFOS//HdOZaDHgPrPsqgE9zUuiaOE1aw1e1OX64QWLlnQRlBJKEDAG2aFWxrdmq19LDjVx/1XAJxhilPCnW0g0PdOT9CZz9bxmnHSjYeU3EMWsB9gVWYCn6hO31Fu4BaB6s9i8vSWDiqF3BDuAjY39Ir4V6b6YY0txqGEWhkLD+Aj2XY1wpSxL7giANoRWrTcN/3taC3YVNoyNAZ/sS9B0q2+wAm3FBvXV63tYZvmQSk4UEhwPoRe9UfWb1rqKCQPPns5elY73Lb+fU5Fy3B7wPAgw+WKjNYJePvQIDAQAB";

    private final String rawString = "Hello, I'm Here!";
    private byte[] encBytes, decBytes;
    private String encString, decString;
    private String aesKey;
    private CryptoUtils cryptoUtils = new CryptoUtils();

    public TestCryptoUtils() {
        cryptoUtils.setPublicKey(PUBLIC_KEY);
    }

    public void encryptBytes() {
        try {
            encBytes = cryptoUtils.encrypt(rawString.getBytes());
            aesKey = cryptoUtils.getKey();
            String encString = new String(encBytes);
            L.msg(TAG, String.format("enc_1 pre(%s) > post(%s)", rawString, encString));

            String target = encString;
            byte[] targetBytes = target.getBytes("UTF-8");

            // Base64 인코딩 ///////////////////////////////////////////////////
            // Encoder#encode(byte[] src) :: 바이트배열로 반환
            byte[] encodedBytes = Base64.encode(targetBytes, Base64.DEFAULT);
            System.out.println(new String(encodedBytes));

            // Encoder#encodeToString(byte[] src) :: 문자열로 반환
            String encodedString = Base64.encodeToString(targetBytes, Base64.DEFAULT);
            System.out.println(encodedString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void decryptBytes() {
        try {
            decBytes = cryptoUtils.decrypt(encBytes);
            String encString = new String(encBytes);
            String decString = new String(decBytes);
            L.msg(TAG, String.format("dec_1 pre(%s) > post(%s)", encString, decString));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    File dst = null;

    /*public void encryptFile() {
        File src = new File (FaceBusiness.getPictureFilePath() + File.separator + "JPEG_1566958324347.jpg");
        dst = new File (src.getPath()+".enc");
        L.msg(TAG, "encryptFile src:"+src.getPath());
        try {
            dst = cryptoUtils.encrypt(src, dst);
            L.msg(TAG, "encryptFile dst:"+dst.getPath());
            L.msg(TAG, "encryptFile dst.length:"+dst.length());
        } catch (Exception e) {
            e.printStackTrace();
            dst = null;
        }
    }

    public void decryptFile() {
        if (dst == null) {
            L.msg(TAG, "decryptFile dst is null");
            return;
        }
        String dstPath = dst.getPath();
        String decPath = dstPath.substring(0, dstPath.lastIndexOf("."));
        File decrypt = new File (decPath+".2");
        try {
            decrypt =  cryptoUtils.decrypt(dst, decrypt);
            L.msg(TAG, "decryptFile decrypt:"+decrypt.getPath());
            L.msg(TAG, "decryptFile decrypt.length:"+decrypt.length());
        } catch (Exception e) {
            e.printStackTrace();
            dst = null;
        }
    }*/
}
