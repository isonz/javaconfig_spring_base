package z.lib;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.net.smtp.SMTPClient;
import org.apache.commons.net.smtp.SMTPReply;
import org.json.JSONObject;
import org.xbill.DNS.Lookup;
import org.xbill.DNS.Record;
import org.xbill.DNS.Type;

public class Func 
{
	static private JSONObject _json = null;
	static private Map<String, Object> _map = null;
	
	public static void set_json(JSONObject json) {_json = json;}
	public static void set_map(Map<String, Object> map) {_map = map;}

	//产生n长度的随机码
	static public String getRandomCode(int n)
	{
		String tt = "";
		String[] ss = getAllChar();
		for (int i=0; i < n; i++){
			tt += ss[(int)(Math.random()*62)];
		}
		return tt;
	}
	
	//36个字符
	static public String[] getAllChar(){
		String[] ss = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",	"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z","1","2","3","4","5","6","7","8","9","0"};
		return ss;
	}
	
	//--- 将二进制转换成16进制 
	public static String parseByte2HexStr(byte buf[])
	{  
	    StringBuffer sb = new StringBuffer();  
	    for (int i = 0; i < buf.length; i++) {  
            String hex = Integer.toHexString(buf[i] & 0xFF);  
            if (hex.length() == 1) {  
                    hex = '0' + hex;  
            }  
            sb.append(hex.toUpperCase());  
	    }  
	    return sb.toString();  
	}
	
	//--- 将16进制转换为二进制 
	public static byte[] parseHexStr2Byte(String hexStr)
	{  
        if (hexStr.length() < 1) return null;  
        byte[] result = new byte[hexStr.length()/2];  
        for (int i = 0;i< hexStr.length()/2; i++) {
        	int high = Integer.parseInt(hexStr.substring(i*2, i*2+1), 16);
        	int low = Integer.parseInt(hexStr.substring(i*2+1, i*2+2), 16);
        	result[i] = (byte) (high * 16 + low);  
        }
        return result;  
	}
	
	//------------ 使用方式：JSONObject json; json = json("name", "json"); json = json("message", message);
	static public JSONObject json(String key, Object obj)
	{
		if(null == _map) _map = new HashMap<String, Object>(); 
		_map.put(key, obj);
		_json = new JSONObject(_map);
		return _json;
	}
	//带有status 和 msg 状态的JSON格式 一般用于网络传输
	static public JSONObject toJson(String key, Object obj)
	{
		int status = 0;
		String msg = "SUCCESS";
		return toJson(key, obj, status, msg);
	}
	static public JSONObject toJson(String key, Object obj, int status, String msg)
	{
		if(null == _map){ 
			_map = new HashMap<String, Object>(); 
			_map.put("status", status);
			_map.put("msg", msg);
		}
		return json(key, obj);
	}
	//json形式的字符串 {"name":"ison"}转化为json对象
	static public JSONObject jsonstrToJson(String str)
	{
		JSONObject json = new JSONObject(str);
		return json;
	}
	
	//只取name1=value1&name2=value2的值
	static public String getParamsValue(String params)
	{
		String[] strArr = params.split("&");
		String[] kvArr = null;
		String val = "";
		String vals = "";
		for(int i=0; i<strArr.length; i++){
			try{
				kvArr = strArr[i].split("=");
				try{val = kvArr[1];}catch(Exception e){val = "";}
				vals += val; 
			}catch(Exception e){
				continue;
			}
		}
		return vals;
	}
	
	// json 转化为 name1=value1&name2=value2 的形式
	static public String jsonToParamStr(JSONObject json)
	{
		if(null == json){log("Func.jsonToParamStr(JSONObject json) json is null"); return null;}
		String param = "";
		@SuppressWarnings("unchecked")
		Iterator<String> it = json.keys();
		while (it.hasNext()) {  
            String key = (String)it.next();
            String value;
            try {
            	value = (String)json.getString(key);
			} catch (Exception e) {
				value = "";
			}
            param += "&"+key+"="+value;
        }
		param = param.substring(1);
		return param;
	}
	// name1=value1&name2=value2 的形式转化为 json形式
	static public JSONObject paramstrToJson(String str)
	{
		if(null==str || ""==str){log("Func.paramstrToJson(String str) String is null"); return null;}
		JSONObject json = null;
		_map = null;
		String[] strArr = str.split("&");
		String[] kvArr = null;
		String key = "";
		String val = "";
		for(int i=0; i<strArr.length; i++){
			try{
				kvArr = strArr[i].split("=");
				try{key = kvArr[0];}catch(Exception e){key = ""; continue;}
				try{val = kvArr[1];}catch(Exception e){val = "";}
				json = json(key, val);
			}catch(Exception e){
				continue;
			}
		}
		return json;
	}
	//------------- END json
	
	//-- 创建文件夹和文件
	static public boolean mkdir(String path) throws IOException
	{
		File file = new File(path);
		return file.mkdirs();
	}
	static public File createFile(String path, String name) throws IOException
	{
		mkdir(path);
		String file = path + name;
		File fp = new File(file);
		if(!fp.exists()){
		    try {
		    	fp.createNewFile();
		    } catch (IOException e) {     
		        e.printStackTrace();    
		    }
		}
		return fp;
	}
	//------ 创建文件夹和文件 end
	
	//检查是否手机号
	static public boolean checkmobile(String mobiles)
	{
		String regExp = "^[1]([3][0-9]{1}|59|71|70|58|88|89)[0-9]{8}$";
		Pattern p = Pattern.compile(regExp);  
		Matcher m = p.matcher(mobiles);  
		return m.find();//boolean   
	}
	
	//------ 检查是否邮箱地址以及地址是否有效
	public static boolean checkEmail(String email) {
		boolean mx = false;
		return checkEmail(email, mx);
	}
	public static boolean checkEmail(String email, boolean mx) 
	{
        if (!email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+")) return false;
        if(!mx) return true;
        
        String host = "";
        String hostName = email.split("@")[1];
        Record[] result = null;
        SMTPClient client = new SMTPClient();
        try {
            Lookup lookup = new Lookup(hostName, Type.MX);	// 查找MX记录
            lookup.run();
            if (lookup.getResult() != Lookup.SUCCESSFUL) {
                return false;
            } else {
                result = lookup.getAnswers();
            }
            for (int i = 0; i < result.length; i++) {
                host = result[i].getAdditionalName().toString();
                client.connect(host);
                if (!SMTPReply.isPositiveCompletion(client.getReplyCode())) {
                    client.disconnect(); 	// 连接到邮箱服务器
                    continue;
                } else {break;}
            }
            //以下2项自己填写快速的，有效的邮箱
            client.login("163.com");
            client.setSender("zxskigg@163.com");
            client.addRecipient(email);
            if (250 == client.getReplyCode()) return true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                client.disconnect();
            } catch (IOException e) {}
        }
        return false;
    }
	
	//按字典正序排序一个字符串,也对中文排序
	static public String dictSortStr(String str)
	{
		//Comparator<Object> cmp = Collator.getInstance(java.util.Locale.CHINA);
		//Arrays.sort(strArr, cmp); 	//字符串才用的上，可以按中文拼音排序
		char[] strArr = str.toCharArray();
		Arrays.sort(strArr);
		str = "";
		for(int i=0; i<strArr.length; i++){
			str += strArr[i];
		}
		return str;
	}
	
	//----- log
	static public void log(String msg) 
    {
		String name = "debug";
		String project = "default";
		log(msg, name, project);
    }
	static public void log(String msg, String name) 
    {
		String project = "default";
		log(msg, name, project);
    }
	static public void log(String msg, String name, String project) 
    {
		if(null == msg || "" == msg) return;
		msg = DateUtil.getTime()+":\t"+ msg + "\t\n";
		if(null == project || "" == project) project = getProjectName();
		if(null == name || "" == name) name = "debug";
		try{
			String logname = DateUtil.getDate()+"_"+name+".log";
			String logpath = "/tmp/javalog/"+project+"/";
			String os = os();
			if(os.startsWith("win")) logpath = "D:\\javalog\\"+project+"\\";
			File fp = createFile(logpath, logname);			
			FileWriter fw = new FileWriter(fp, true);
			fw.write(msg);
			fw.close();
		}catch(Exception e){
			e.printStackTrace();
		}
    }
	//---- log end
	
	//-- 获取操作系统名称
	static public String os()
	{
		Properties prop = System.getProperties();
		String os = prop.getProperty("os.name");
		os = os.toLowerCase();
		return os;
	}
	
	//获取JAVA工程名称，但在servlet环境下不能获取
	static public String getProjectName()
	{
		String project = System.getProperty("user.dir");
		String separator = System.getProperty("file.separator");
		int pos = project.lastIndexOf(separator);
		project = project.substring(pos+1);
		return project;
	}
	
	//读取文件创建时间戳
	static public int getFileLastModified(String filePath)
	{  
		File file = new File(filePath);
        int time = (int)(file.lastModified()/1000);	//最后修改过时间
        return time;
    }  
	
	//----- 读取文件夹文件
	//读取当前文件夹 Use:String [] fileName = getFileName("F:\\");for(String name:fileName){name;}
	public static String [] getFileName(String path)
    {
        File file = new File(path);
        String [] fileName = file.list();
        return fileName;
    }
	//读取当前文件及其子文件的文件名  Use: ArrayList<String> listFileName = new ArrayList<String>(); getAllFileName("F:\\",listFileName);for(String name:listFileName){name;}
    public static void getAllFileName(String path, ArrayList<String> fileName)
    {
        File file = new File(path);
        File [] files = file.listFiles();
        String [] names = file.list();
        if(names != null){
        	fileName.addAll(Arrays.asList(names));
        	for(File a:files){
        		if(a.isDirectory()){
        			getAllFileName(a.getAbsolutePath(), fileName);
        		}
        	}
        }
    }
	//----- 读取文件夹文件 end
    
	public static void main(String args[])
	{ 
		//
	}
	
	
}
