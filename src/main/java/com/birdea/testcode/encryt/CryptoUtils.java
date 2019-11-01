package com.birdea.testcode.encryt;

import com.birdea.testcode.util.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;

public class CryptoUtils {
    private static String TAG = "CryptoUtils";
    private String ALGORITHM = "AES";
    private String TRANSFORMATION = "AES/CBC/PKCS5Padding";
    private String mAESKey = null;
    private String mRSAPublicKey = null;
    private String mUploadKey = null;

    // Get Public key from VA server
    private String getKeyOnly(String key) {
        String retKey = key;
        retKey = retKey.replace("-----BEGIN PUBLIC KEY-----", "")
                .replace("-----END PUBLIC KEY-----", "")
                .replace("\n", "");
        return retKey;
    }

    public void setPublicKey(String key) {
        mRSAPublicKey = key;
    }

    private String aesRawKey() {
        if (mAESKey == null) {
            KeyGenerator generator = null;
            SecureRandom random = null;

            try {
                generator = KeyGenerator.getInstance("AES");
                random = SecureRandom.getInstance("SHA1PRNG");
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            generator.init(128, random);
            SecretKey k = generator.generateKey();
            byte[] byteKey = k.getEncoded();

            mAESKey = Base64.encodeToString(byteKey, Base64.DEFAULT);
            return mAESKey;
        }
        return mAESKey;
    }

    public String getKey() {
        if (mUploadKey != null) return mUploadKey;

        if (mAESKey == null) {
            mAESKey = aesRawKey();
        }

        mRSAPublicKey = getKeyOnly(mRSAPublicKey);
        byte[] decoded = Base64.decode(mRSAPublicKey, Base64.DEFAULT);

        KeyFactory kf = null;
        try {
            kf = KeyFactory.getInstance("RSA");
            X509EncodedKeySpec spec = new X509EncodedKeySpec(decoded);
            PublicKey pubKey = kf.generatePublic(spec);
            Cipher cipherRSA = Cipher.getInstance("RSA/ECB/NoPadding");
            cipherRSA.init(Cipher.ENCRYPT_MODE, pubKey);

            byte[] tmp = cipherRSA.doFinal(Base64.decode(mAESKey, Base64.DEFAULT));
            mUploadKey = Base64.encodeToString(tmp, Base64.DEFAULT);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        }
        return mUploadKey;
    }

    private byte[] doCrypt(int cipherMode, String key, byte[] inputByte) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        SecretKeySpec secretKey = new SecretKeySpec(Base64.decode(key, Base64.DEFAULT), ALGORITHM);
        String iv = "0000000000000000";
        IvParameterSpec ivSpec = new IvParameterSpec(iv.getBytes());
        Cipher cipher = Cipher.getInstance(TRANSFORMATION);
        cipher.init(cipherMode, secretKey, ivSpec);

        return cipher.doFinal(inputByte);
    }

    private File doCrypt(int cipherMode, File src, File dst) throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        byte[] srcBytes = readFile(src);
        byte[] encBytes = doCrypt(cipherMode, aesRawKey(), srcBytes);
        writeFile(encBytes, dst);
        return dst;
    }

    private byte[] readFile(File src) {
        byte[] srcBytes = new byte[(int) src.length()];
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src));
            bis.read(srcBytes, 0, srcBytes.length);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return srcBytes;
    }

    private void writeFile(byte[] bytes, File dstFile) {
        try {
            BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(dstFile));
            bos.write(bytes);
            bos.flush();
            bos.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public byte[] encrypt(String key, byte[] inputByte) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        return doCrypt(Cipher.ENCRYPT_MODE, key, inputByte);
    }

    public byte[] encrypt(byte[] inputByte) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        return doCrypt(Cipher.ENCRYPT_MODE, aesRawKey(), inputByte);
    }

    public byte[] decrypt(byte[] inputByte) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        return doCrypt(Cipher.DECRYPT_MODE, aesRawKey(), inputByte);
    }

    public File encrypt(File src, File dst) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        return doCrypt(Cipher.ENCRYPT_MODE, src, dst);
    }

    public File decrypt(File src, File dst) throws NoSuchPaddingException, InvalidKeyException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException {
        return doCrypt(Cipher.DECRYPT_MODE, src, dst);
    }
}