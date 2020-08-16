package jum.utility;

import org.apache.commons.net.util.Base64;

import javax.crypto.Cipher;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

public class RSAEncryptUtils {

    private static Map<Integer, String> keyMap = new HashMap<Integer, String>();  //用于封装随机产生的公钥与私钥
    static {
        keyMap.put(0,"MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCYJcNxlp7AT5yYpFKm5GaRvq74aviwwVnkb5iXWg8AE8x09sgRAVIBrsQ7Ws3Vp9b6ErMZJCtrJN5Hqg7tCNZycr9gqT21aU8k6jS04ISFQltThUtdQYfF/n632qX36sRpxi6NSzv+wsFt5TCRDxcudCnz/ggsr+KAGQ5sijaeFQIDAQAB");
        keyMap.put(1,"MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJglw3GWnsBPnJikUqbkZpG+rvhq+LDBWeRvmJdaDwATzHT2yBEBUgGuxDtazdWn1voSsxkkK2sk3keqDu0I1nJyv2CpPbVpTyTqNLTghIVCW1OFS11Bh8X+frfapffqxGnGLo1LO/7CwW3lMJEPFy50KfP+CCyv4oAZDmyKNp4VAgMBAAECgYB8a9VqnHvyVKiBA1A7/ugKI9++cuwwz82NS92DWDiQHaDDvdRaY7QGgD8lMtrE8F6TXptr4fJHynykKmPOX73NzGhsvdH1GqjST/e8WMzA9EIV8cIzIwAtrs1TVM0mNQqp9HG+Ya5/OzuJNjOnmIpKQHgDNJ8H4lwysBC7Pp7HwQJBAPKoBH7A4OU7EZtVgWPV4+XZbbE8yRq9vR+s7Q3IEzgP778LYZ+th7+z2S4qqJxcxKO4DrKrAHBM5a41J/N3HC0CQQCgg5zUxaGpJUa60vPIdCfbiGSe33x2kAYRC8VErpv1UOhG/j48JrO5eEUn/xGcyEpe5J0FRSwqUX4ehUJp7vKJAkEAiH/fV3YkjI53zwuAZ6qwMmHnBKaO8g39z021Y+VCemzsjmlWlz5EeKAw8Xlps9uzee1Vx0fOTV3UbfTU7m34WQJBAJH33hEoQGEfgvi2ifdKmkxr9oT726W+R6YMvDI/T5iBIRoebt4om9wYYVuD+7JF+kvdPmXih81cGoMK0GChaXECQAu6X+9PpNG0IfwDaG6KfxgtG64hJYmLmGDa5OHQU5YvLo2sE13kmRjPP7vxFCn+SBqpdBMJbphgrN50ykr1tSw=");
    }
    public static void main(String[] args) throws Exception {
//        生成公钥和私钥
//        genKeyPair();
//        加密字符串
        String message = "t0test20190612161801";
        System.out.println("随机生成的公钥为:" + keyMap.get(0));
        System.out.println("随机生成的私钥为:" + keyMap.get(1));
        String messageEn = encrypt(message,keyMap.get(0));

        String encryptStr = "14uBFNGIK42Z9RCZM685ODPxImj5WEfN5trwoClbwvovNrkrc6fkD3SkMOd405muVrBsfqSDFnmBs3VoBKfRhOwXhAzzm1x1J4LDBaqUwdhzZgiJZSgAlvHGm2z2HGGxjMuz9s+Iux0latIf8aUch3uMgls9neRBWpQEZ/lI5y8=";

        System.out.println(message + "\t加密后的字符串为:" + messageEn);
        String messageDe = decrypt(encryptStr,keyMap.get(1));
        System.out.println("还原后的字符串为:" + messageDe);
    }

    /**
     * 随机生成密钥对
     * @throws NoSuchAlgorithmException
     */
    public static void genKeyPair() throws NoSuchAlgorithmException {
        // KeyPairGenerator类用于生成公钥和私钥对，基于RSA算法生成对象
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance("RSA");
        // 初始化密钥对生成器，密钥大小为96-1024位
        keyPairGen.initialize(1024,new SecureRandom());
        // 生成一个密钥对，保存在keyPair中
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();   // 得到私钥
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();  // 得到公钥
        String publicKeyString = new String(Base64.encodeBase64(publicKey.getEncoded()));
        // 得到私钥字符串
        String privateKeyString = new String(Base64.encodeBase64((privateKey.getEncoded())));
        // 将公钥和私钥保存到Map
        keyMap.put(0,publicKeyString);  //0表示公钥
        keyMap.put(1,privateKeyString);  //1表示私钥
    }
    /**
     * RSA公钥加密
     *
     * @param str
     *            加密字符串
     * @param publicKey
     *            公钥
     * @return 密文
     * @throws Exception
     *             加密过程中的异常信息
     */
    public static String encrypt( String str, String publicKey ) throws Exception{
        //base64编码的公钥
        byte[] decoded = Base64.decodeBase64(publicKey);
        RSAPublicKey pubKey = (RSAPublicKey) KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(decoded));
        //RSA加密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        String outStr = Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
        return outStr;
    }

    /**
     * RSA私钥解密
     *
     * @param str
     *            加密字符串
     * @param privateKey
     *            私钥
     * @return 铭文
     * @throws Exception
     *             解密过程中的异常信息
     */
    public static String decrypt(String str, String privateKey) throws Exception{
        //64位解码加密后的字符串
        byte[] inputByte = Base64.decodeBase64(str.getBytes("UTF-8"));
        //base64编码的私钥
        byte[] decoded = Base64.decodeBase64(privateKey);
        RSAPrivateKey priKey = (RSAPrivateKey) KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(decoded));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        String outStr = new String(cipher.doFinal(inputByte));
        return outStr;
    }
}
