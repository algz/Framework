package algz.platform.core.license.licenseManager;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.util.Base64;

public class Signaturer {
	public static byte[] sign(byte[] priKeyText, String plainText) {      try {    
	     PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(priKeyText));    
	     KeyFactory keyf = KeyFactory.getInstance("RSA");    
	     PrivateKey prikey = keyf.generatePrivate(priPKCS8);    
	     // 用私钥对信息生成数字签名    
	      Signature signet = java.security.Signature.getInstance("MD5withRSA");    
	     signet.initSign(prikey);    
	     signet.update(plainText.getBytes());    
	     byte[] signed = Base64.getEncoder().encode(signet.sign());    
	     return signed;    
	    } catch (java.lang.Exception e) {    
	     System.out.println("签名失败");    
	     e.printStackTrace();    
	    }    
	    return null;    
	   }    
}
