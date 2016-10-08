package z.lib;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.HtmlEmail;
import org.json.JSONObject;

public class Sendz 
{
	static private  Mail mail;
	static private final String SEPARATOR = "-010-";
	
	/**
     * 向指定URL发送GET方法的请求
     * @param url 发送请求的URL
     * @param param 请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return URL 所代表远程资源的响应结果
     */
    public static String get(String url, String param) 
    {
        String result = "";
        BufferedReader in = null;
        try {
            String urlNameString = url + "?" + param;
            URL realUrl = new URL(urlNameString);
            URLConnection connection = realUrl.openConnection();
            connection.setRequestProperty("accept", "*/*");
            connection.setRequestProperty("connection", "Keep-Alive");
            connection.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            connection.connect();
            
            /* // 遍历所有的响应头字段
            Map<String, List<String>> map = connection.getHeaderFields();
            for (String key : map.keySet()) {
                System.out.println(key + "--->" + map.get(key));
            }*/
            
            in = new BufferedReader(new InputStreamReader(connection.getInputStream()));	// 定义 BufferedReader输入流来读取URL的响应
            String line;
            while ((line = in.readLine()) != null) { result += line; }
        } catch (Exception e) {
            System.out.println("发送GET请求出现异常！" + e);
            e.printStackTrace();
        }finally {
            try {
                if (in != null) { in.close(); }
            }catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return result;
    }

    /**
     * 向指定 URL 发送POST方法的请求
     * @param url发送请求的 URL
     * @param param请求参数，请求参数应该是 name1=value1&name2=value2 的形式。
     * @return 所代表远程资源的响应结果
     */
    public static String post(String url, String param) 
    {
        PrintWriter out = null;
        BufferedReader in = null;
        String result = "";
        try {
            URL realUrl = new URL(url);
            URLConnection conn = realUrl.openConnection();
            conn.setRequestProperty("accept", "*/*");
            conn.setRequestProperty("connection", "Keep-Alive");
            conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1;SV1)");
            conn.setDoOutput(true);		// 发送POST请求必须设置如下两行
            conn.setDoInput(true);
            out = new PrintWriter(conn.getOutputStream());	// 获取URLConnection对象对应的输出流
            out.print(param);	// 发送请求参数
            out.flush();		// flush输出流的缓冲
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));	// 定义BufferedReader输入流来读取URL的响应
            String line;
            while ((line = in.readLine()) != null) { result += line;}
        } catch (Exception e) {
            System.out.println("发送 POST 请求出现异常！"+e);
            e.printStackTrace();
        }finally{
            try{
                if(out!=null){ out.close(); }
                if(in!=null){ in.close(); }
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
        return result;
    }
    
    //---------------- 发送邮件 eg:Sendz.sendEmail("zxskigg@163.com", "测试", "还是测试")
	static public boolean sendEmail(String toemail, String title, String body)
	{
        HtmlEmail email = new HtmlEmail();  // 发送email
        try {
            email.setHostName(mail.getHost());  				// 这里是SMTP发送服务器的名字：163的如下："smtp.163.com"  
            email.setCharset(Mail.ENCODEING);  					// 字符编码集的设置  				// 收件人的邮箱   
            email.setFrom(mail.getEmail(), mail.getNickname());  	// 发送人的邮箱  
            email.setAuthentication(mail.getUsername(), mail.getPassword());	// 如果需要认证信息的话，设置认证：用户名-密码。分别为发件人在邮件服务器上的注册名称和密码    
            
            email.addTo(toemail);  
            email.setSubject(title);  				// 要发送的邮件主题  
            email.setMsg(body);  		// 要发送的信息，由于使用了HtmlEmail，可以在邮件内容中使用HTML标签  
            email.send();  
            return true;  
        } catch (EmailException e) {  
            e.printStackTrace();
            return false;  
        }
	}
	public Mail getMail() {	return mail;}
	public void setMail(Mail amail) {mail = amail;}
	//----------------- 发送邮件 END
	
	//----------------- Secret Send : encode_data, ext_data 都是name1=value1&name2=value2 的形式, ext_data是其他明文信息
	static public JSONObject sPost(String url, String token, String encode_data)
	{
		String ext_data = null;
		String encodekey = null;
		return sPost(url, token, encode_data, ext_data, encodekey);
	}
	static public JSONObject sPost(String url, String token, String encode_data, String ext_data, String encodekey)
	{
		if(encode_data.length() > 10000){ Func.log("encode_data large than 10K"); return null; }
		JSONObject json = null;

		String sign = setEncode(token, encodekey, encode_data);
		String md = sign;
		String postStr = "sign="+sign;
		if(null!=ext_data || ""!=ext_data){
			ext_data = Func.getParamsValue(ext_data);
			ext_data = Encrypt.urlEncode(ext_data);
			md = Func.dictSortStr(sign + ext_data);
			postStr += "&"+ext_data;
		}
		md = Encrypt.md5(md);
		postStr += "&md="+md;
		String pcontent = post(url, postStr);  //接收的是name1=value1&name2=value2格式的加密密文
		json = sGet(pcontent, token, encodekey);
		return json;
	}
	//通过 URL获取加密参数时，encode_data 参数内容长度最好不要超过 900；
	static public JSONObject sPost4Url(String url, String token, String encode_data)
	{
		String ext_data = null;
		String encodekey = null;
		return sPost4Url(url, token, encode_data, ext_data, encodekey);
	}
	static public JSONObject sPost4Url(String url, String token, String encode_data, String ext_data, String encodekey)
	{
		if(encode_data.length() > 900){ Func.log("encode_data large than 900"); return null; }
		return sPost(url, token, encode_data, ext_data, encodekey);
	}
	//获取加密信息
	static public JSONObject sGet(String sign, String token, String decode_key)
	{
		int timeout = 60;
		String md = null;
		boolean ismd = false;
		String req = null;
		return sGet(sign, token, decode_key, timeout, ismd, md, req);
	}
	//获取加密信息: decode_key为解密密钥，timeout为超时时间（秒）, req:name1=value1&name2=value2 的形式,是其他的参数, 一般有req时ismd才为true
	static public JSONObject sGet(String sign, String token, String decode_key, int timeout, boolean ismd, String md, String req)
	{
		if(null==sign || ""==sign){ Func.log("sign:"+sign); return null; }
		JSONObject json = null;
		if(timeout < 10) timeout = 10;
		if(ismd){
			if(null==md || ""==md){ Func.log("md:"+md); return null;}
			req = Func.getParamsValue(req);
			req = Encrypt.urlEncode(req);
			req = Func.dictSortStr(sign + req);
			String smd = Encrypt.md5(req);
			if(!smd.equals(md)){ Func.log("md:"+md + ", smd:"+smd); return null;}
		}
		
		//String decode = Encrypt.decode(sign, decode_key);		//使用强解密
		String decode = Encrypt.deAES(sign, decode_key);
		if(null == decode || ""==decode){ Func.log("decode:"+decode); return null;}
		
		String[] deArry = decode.split(SEPARATOR);		//deArry[0]为6位随机码
		try{
			String dToken = deArry[1];
			if(!token.equals(dToken)){ Func.log("dToken:"+dToken + ", token:"+token); return null;}
		}catch(Exception e){}
		
		int dTime = 0;
		int time = DateUtil.getTimeStemp();
		try{
			dTime = Integer.parseInt(deArry[2]);
			if(time - dTime > timeout){Func.log("time:"+time + ", dTime:"+dTime + ", timeout:"+timeout + ", time-dTime>timeout"); return null;}
		}catch(Exception e){}
		
		try {
			String data = deArry[3];			//name1=value1&name2=value2 的形式
			json = Func.paramstrToJson(data);
		} catch (Exception e) {
			Func.log("Sendz.sGet encode data is null, may decode key wrong."); return null;
		}
		return json;
	}
	//产生加密密文
	static public String setEncode(String token, String encode_key, String encode_data)
	{
		String sign = "";
		if(null==token || ""==token){Func.log("token:"+token); return null;}
		int time = DateUtil.getTimeStemp();
		
		//sign = token + SEPARATOR + time + SEPARATOR + encode_data;	//使用强加密，密文会增加4倍
		//sign = Encrypt.encode(sign, encode_key);
		sign = Func.getRandomCode(6)+ SEPARATOR + token + SEPARATOR + time + SEPARATOR + encode_data;
		sign = Encrypt.enAES(sign, encode_key);
		return sign;
	}
	//----------------- Secret Send End

    public static void main(String[] args) 
    {
    	//
    }

	
}
