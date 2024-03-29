package com.starlight.core.utils;

import javafx.util.Pair;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.Key;
import java.security.MessageDigest;
import java.util.Base64;

/** 加密功能 **/
public final class EncryptUtils {
    private static final String SALT = "2_~!@=?=#$++%^&*:(121)";
    private static final AesAlgorithm AES = new AesAlgorithm(SALT);
    private static final DesAlgorithm DES = new DesAlgorithm(SALT);
    private static final char[] HEX_DIGITS = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    private static MessageDigest SHA1;
    private static MessageDigest MD5;
    static {
        try {
            SHA1 = MessageDigest.getInstance("sha1");
            MD5 = MessageDigest.getInstance("MD5");
        } catch (Exception e){
            throw new RuntimeException("no md5 algorithm error...", e);
        }
    }

    /** SHA1 混淆加密 */
    public static String cryptogram(String val) {
        if(StringUtils.isBlank(val)){
            return val;
        }
        synchronized (SHA1) {
            return toHex(SHA1.digest(BytesUtils.utf8Bytes(val + SALT)));
        }
    }

    /** SHA1 加密 **/
    public static String sha1(String val){
        if(StringUtils.isBlank(val)){
            return val;
        }
        synchronized (SHA1) {
            return toHex(SHA1.digest(BytesUtils.utf8Bytes(val)));
        }
    }

    /** MD5 加密 **/
    public static String md5(String val) {
        if(StringUtils.isBlank(val)){
            return val;
        }
        return md5(BytesUtils.utf8Bytes(val));
    }
    /** MD5 加密 **/
    public static String md5(byte[] bytes) {
        if(null == bytes || bytes.length < 1){
            return StringUtils.EMPTY;
        }
        synchronized(MD5) {
            return toHex(MD5.digest(bytes));
        }
    }

    /** MD5 加密 **/
    public static String md5(File file) {
        FileInputStream fis = null;
        DigestInputStream dis = null;
        try {
            fis = new FileInputStream(file);
            synchronized (MD5) {
                dis = new DigestInputStream(fis, MD5);
                byte[] buffer = new byte[256 * 1024];
                while (dis.read(buffer) > 0) ;
                return toHex(dis.getMessageDigest().digest());
            }
        } catch (Exception e) {
            throw new RuntimeException("File " + file.getName() + " encrypt MD5 error... ", e);
        } finally {
            BytesUtils.close(dis);
            BytesUtils.close(fis);
        }
    }

    /** AES加密字符串 **/
    public static String encryptAES(String strIn){
        try {
            return toHex(AES.encrypt(BytesUtils.utf8Bytes(strIn)));
        } catch (Exception e){
            throw new RuntimeException("AES algorithm encrypt error.....", e);
        }
    }

    /** AES解密字符串 **/
    public static String decryptAES(String strIn) {
        try {
            return new String(AES.decrypt(hex2Bytes(strIn)));
        } catch (Exception e){
            throw new RuntimeException("AES algorithm decrypt error.....", e);
        }
    }

    /** 3DES加密字符串 **/
    public static String encrypt3DES(String strIn){
        try {
            return toHex(DES.encrypt(strIn.getBytes()));
        } catch (Exception e){
            throw new RuntimeException("3DES algorithm encrypt error.....", e);
        }
    }

    /** 3DES解密字符串 **/
    public static String decrypt3DES(String strIn) {
        try {
            return new String(DES.decrypt(hex2Bytes(strIn)));
        } catch (Exception e){
            throw new RuntimeException("3DES algorithm decrypt error.....", e);
        }
    }

    /** Base64 Encode **/
    public static String encode64(String val){
        if(StringUtils.isBlank(val)){
            return val;
        }
        return BytesUtils.string(Base64.getEncoder().encode(BytesUtils.utf8Bytes(val)));
    }

    public static String encode64(byte [] bytes){
        if(null == bytes){
            return StringUtils.EMPTY;
        }
        return BytesUtils.string(Base64.getEncoder().encode(bytes));
    }

    /** Base64 decode byte[] **/
    public static byte[] decode64Bytes(String val){
        val = StringUtils.defaultString(val).replaceAll("\\s+", "");
        if(StringUtils.isBlank(val)){
            return new byte[0];
        }
        try {
            return Base64.getDecoder().decode(BytesUtils.utf8Bytes(val));
        }catch (Exception e){
            throw new RuntimeException("decode base64 error...", e);
        }
    }
    /** Base64 decode string**/
    public static String decode64String(String val){
        return BytesUtils.string(decode64Bytes(val));
    }
    /** Base64 decode InputStream **/
    public static InputStream decode64Stream(String val){
        return new ByteArrayInputStream(decode64Bytes(val));
    }

    /** 字符串转为 16 进制字符串 **/
    public static String toHex(String src) {
        if(StringUtils.isBlank(src)){
            return src;
        }
        return toHex(BytesUtils.utf8Bytes(src));
    }
    /** 字节数组转 16 进制字符串 **/
    public static String toHex(byte[] bytes) {
        char[] rs = new char[bytes.length * 2];
        for (int i = 0; i < rs.length; i = i + 2) {
            byte b = bytes[i / 2];
            rs[i] = HEX_DIGITS[(b >>> 0x4) & 0xf];
            rs[i + 1] = HEX_DIGITS[b & 0xf];
        }
        return new String(rs);
    }

    /** 16 进制字符串反解 **/
    public static String hex2String(String src) {
        if(StringUtils.isBlank(src)){
            return src;
        }
        return BytesUtils.string(hex2Bytes(src));
    }

    /** 16 进制字符串字节数组 **/
    public static byte[] hex2Bytes(String src){
        byte[] res = new byte[ src.length() / 2];
        char[] chs = src.toCharArray();
        for(int i = 0, c = 0; i < chs.length; i += 2, c++){
            res[c] = (byte) (Integer.parseInt(new String(chs,i,2), 16));
        }
        return res;
    }

    private static byte[] bytes(byte[] bytes, Cipher cipher, int times) throws IllegalBlockSizeException, BadPaddingException {
        byte[] b = bytes;
        for (int i = 0; i < times; i++) {
            b = cipher.doFinal(b);
        }
        return b;
    }
    private static Key createKey(String secret, int bits, String spec){
        byte[] bs = new byte[bits];
        byte[] bytes = BytesUtils.utf8Bytes(secret);
        for (int i = 0; i < bytes.length && i < bs.length; i++) {
            bs[i] = bytes[i];
        }
        return new SecretKeySpec(bs, spec);
    }
    private static Pair<Cipher, Cipher> initialize(String secret, String cipher, int bits, String spec) throws Exception {
        Key key = createKey(secret, bits, spec);
        Cipher encrypt = Cipher.getInstance(cipher);
        encrypt.init(Cipher.ENCRYPT_MODE, key);
        Cipher decrypt = Cipher.getInstance(cipher);
        decrypt.init(Cipher.DECRYPT_MODE, key);
        return new Pair<>(encrypt, decrypt);
    }

    private static final class AesAlgorithm {
        private static final int AES_TIMES = 2, BITS = 16;
        private static final String CIPHER = "AES/ECB/PKCS5Padding", SPEC = "AES";
        private final Cipher encryptCipher, decryptCipher;
        //指定密钥构造方法
        private AesAlgorithm(String secret) {
            try {
                //from gitee CodingMonkey
                Pair<Cipher, Cipher> pair = initialize(secret, CIPHER, BITS, SPEC);
                this.encryptCipher = pair.getKey(); this.decryptCipher = pair.getValue();
            } catch (Exception e){
                throw new RuntimeException("new AES algorithm error.....", e);
            }
        }

        //加密字节数组
        private byte[] encrypt(byte[] bytes) throws BadPaddingException, IllegalBlockSizeException {
            return bytes(bytes, encryptCipher, AES_TIMES);
        }
        //解密字节数组
        private byte[] decrypt(byte[] bytes) throws BadPaddingException, IllegalBlockSizeException {
            return bytes(bytes, decryptCipher, AES_TIMES);
        }
    }

    private static final class DesAlgorithm {
        private static final String CIPHER = "DES";
        private static final int DES_TIMES = 2, BITS = 8;
        private final Cipher encryptCipher, decryptCipher;
        //指定密钥构造方法
        private DesAlgorithm(String secret) {
            try {
                Pair<Cipher, Cipher> pair = initialize(secret, CIPHER, BITS, CIPHER);
                this.encryptCipher = pair.getKey(); this.decryptCipher = pair.getValue();
            } catch (Exception e){
                throw new RuntimeException("new DES algorithm error.....", e);
            }
        }
        //加密字节数组
        private byte[] encrypt(byte[] bytes) throws BadPaddingException, IllegalBlockSizeException {
            return bytes(bytes, encryptCipher, DES_TIMES);
        }
        //解密字节数组
        private byte[] decrypt(byte[] bytes) throws BadPaddingException, IllegalBlockSizeException {
            return bytes(bytes, decryptCipher, DES_TIMES);
        }
    }


}
