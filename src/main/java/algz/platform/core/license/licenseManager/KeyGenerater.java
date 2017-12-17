package algz.platform.core.license.licenseManager;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.util.Base64;


/**
 * 
 java中使用公钥加密私钥解密原理实现license控制
现在很多J2EE应用都采用一个license文件来授权系统的使用，特别是在系统购买的早期，会提供有限制的license文件对系统进行限制，比如试用版有譬如IP、日期、最大用户数量的限制等。而license控制的方法又有很多，目前比较流行，只要设计的好就很难破解的方法就是采用一对密匙（私匙加密公匙解密）来生成License文件中的Sinature签名内容，再通过Base64或Hex来进行编码。比如原BEA公司现在是Oracle公司的WebLogic就采用的是这种方法来设置License文件。   
 这里只进行一个比较简单的实现：   
 
 一共三个类：   
 A.KeyGenerater类生成公钥私钥对   
 B.Signaturer类使用私钥进行签名   
 C.SignProvider类用公钥验证   
  
公钥和私钥使用Base64加密Base64这个类很多地方都可以查到。   
  KeyGenerater类：   
 * 
 * @author algz
 *
 */
public class KeyGenerater {
	private byte[] priKey;
	private byte[] pubKey;

	public void generater() {
		try {
			KeyPairGenerator keygen = KeyPairGenerator.getInstance("RSA");
			SecureRandom secrand = new SecureRandom();
			secrand.setSeed("www.algz.com".getBytes()); // 初始化随机产生器
			keygen.initialize(1024, secrand);
			KeyPair keys = keygen.genKeyPair();
			PublicKey pubkey = keys.getPublic();
			PrivateKey prikey = keys.getPrivate();
			pubKey = Base64.getEncoder().encode(pubkey.getEncoded());
			priKey = Base64.getEncoder().encode(prikey.getEncoded());
			System.out.println("pubKey = " + new String(pubKey));
			System.out.println("priKey = " + new String(priKey));
		} catch (java.lang.Exception e) {
			System.out.println("生成密钥对失败");
			e.printStackTrace();
		}
	}

	public byte[] getPriKey() {
		return priKey;
	}

	public byte[] getPubKey() {
		return pubKey;
	}
}
