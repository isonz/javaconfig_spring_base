package z.lib;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Encrypt
{
	static private final String KEY = "key123@ison";
	
	//-------------- 编码后的base 64 code 加密和解密
	public static String base64Encode(String str){
		byte[] bytes;
		try {
			bytes = str.getBytes("utf-8");
		} catch (UnsupportedEncodingException e) {
			bytes = str.getBytes();
		}
		return Base64.encodeBase64String(bytes);  
	}
	// 解码
	public static String base64Decode(String base64Code){
		String decode = "";
		byte[] bytes;
		try {
			bytes = Base64.decodeBase64(base64Code.getBytes("utf-8"));
			decode = new String(bytes, "utf-8");
		} catch (UnsupportedEncodingException e) {
			bytes = Base64.decodeBase64(base64Code.getBytes());
			decode = new String(bytes);
		}
		return decode;
	}
	//-------------- end 编码后的base 64 code 加密和解密
	
	//-------------- md5
	static public String md5(String input) 
	{
        byte[] source;
        try {
            source = input.getBytes("utf-8");
        } catch (UnsupportedEncodingException e) {
            source = input.getBytes();
        }
        String result = null;
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(source);
            byte temp[] = md.digest();
            char str[] = new char[16 * 2];
            int k = 0;
            for (int i = 0; i < 16; i++) {
                byte byte0 = temp[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            result = new String(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
		
	//------------- 加密,用了2层MD5加密和增加了一道密钥key验证
	static public String encode(String tex){
		return encode(tex, KEY);
	}
	static public String encode(String tex, String key)
	{
		if(""==key || null==key) key = KEY;
		tex = enAES(urlEncode(tex), key);		
		String key_b = Func.getRandomCode(6);	//随机字符串长度为6
		String rand_key = key_b + key;
		rand_key = md5(md5(rand_key)+key_b);
		String key_p = rand_key.substring(0, 10);
		String reslutstr = "";
		int texlen = tex.length();
		char[] texArr = tex.toCharArray();
		char[] randKeyArr = rand_key.toCharArray();
		for(int i=0; i < texlen; i++) reslutstr += (char)(texArr[i] ^ randKeyArr[i%32]);
		reslutstr = key_b + base64Encode(key_p+reslutstr);
		reslutstr = md5(reslutstr).substring(0, 8) + reslutstr;
		reslutstr = urlEncode(reslutstr);
		return reslutstr;
	}
	//----- 解密
	static public String decode(String tex){
		return decode(tex, KEY);
	}
	static public String decode(String tex, String key)
	{
		if(""==key || null==key) key = KEY;
		if(tex.length() < 24) {Func.log("Func.decode tex.length() < 24"); return null;}
		tex = urlDecode(tex);
		String verity_str = tex.substring(0, 8);
		tex = tex.substring(8);
		if(!verity_str.equals(md5(tex).substring(0, 8))){Func.log("Func.decode !verity_str.equals(md5(tex).substring(0, 8))"); return null;}	//完整性验证失败
		
		String key_b = tex.substring(0, 6);
		String rand_key = key_b + key;
		rand_key = md5(md5(rand_key) + key_b);
		String key_p = rand_key.substring(0, 10);
		
		String texs = base64Decode(tex.substring(6));
		String kp = texs.substring(0, 10);
		if(!key_p.equals(kp)){Func.log("Func.decode !key_p.equals(kp)"); return null;}
		
		tex = texs.substring(10);
		String reslutstr = "";
		int texlen = tex.length();
		char[] texArr = tex.toCharArray();
		char[] randKeyArr = rand_key.toCharArray();
		for(int i=0; i < texlen; i++) reslutstr += (char)(texArr[i] ^ randKeyArr[i%32]);
		reslutstr = deAES(reslutstr, key);
		reslutstr = urlDecode(reslutstr);
		return reslutstr;
	}
	//-------------- end encode decode
	
	//------ 全部加密都是 AES/CBC/ZeroPadding 128位模式  
	public static String enAES(String data,String key) {
		if(""==key || null==key) key = KEY;
	    try {
	    	key = md5(key);
	    	String ivkey = key = key.substring(0, 16);
	        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
	        int blockSize = cipher.getBlockSize();
	        SecretKeySpec keyspec = new SecretKeySpec(fullZore(key,blockSize), "AES");
	        IvParameterSpec ivspec = new IvParameterSpec(fullZore(ivkey,blockSize));
	        cipher.init(Cipher.ENCRYPT_MODE, keyspec, ivspec);
	        byte[] encrypted = cipher.doFinal(fullZore(data,blockSize));
	        return Func.parseByte2HexStr(encrypted).trim().toLowerCase();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "";
	    }
	}
	public static String deAES(String data,String key) {
		if(""==key || null==key) key = KEY;
	    try {
	    	key = md5(key);
	    	String ivkey = key = key.substring(0, 16);
	        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
	        int blockSize = cipher.getBlockSize();
	        SecretKeySpec keyspec = new SecretKeySpec(fullZore(key,blockSize), "AES");
	        IvParameterSpec ivspec = new IvParameterSpec(fullZore(ivkey,blockSize));
	        cipher.init(Cipher.DECRYPT_MODE, keyspec, ivspec);
	        byte[] decrypted = cipher.doFinal(Func.parseHexStr2Byte(data));
	        return new String(decrypted).trim();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return "";
	    }
	}
	public static byte[] fullZore(String data,int blockSize){
	    byte[] dataBytes = data.getBytes();
	    int plaintextLength = dataBytes.length;
	    if (plaintextLength % blockSize != 0) {
	        plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
	    }
	    byte[] plaintext = new byte[plaintextLength];
	    System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);
	    return plaintext;
	}
	//------------ end 加密解密
	
	//-------------- Url encode
	static public String urlEncode(String url)
	{
		try {
			url = URLEncoder.encode(url, "utf-8");
		} catch (UnsupportedEncodingException e) {}
		return url;
	}
	//-- Url decode
	static public String urlDecode(String url)
	{
		try {
			url = URLDecoder.decode(url, "utf-8");
		} catch (UnsupportedEncodingException e) {}
		return url;
	}
	//-------------- Url encode end
	
	
	public static void main(String args[])
	{ 
		String encode = encode("name1=value1&name2=value2", "key123@ison");
		System.out.println(encode);
		String decode = decode(encode, "key123@ison");
		System.out.println(decode);
	}
}
