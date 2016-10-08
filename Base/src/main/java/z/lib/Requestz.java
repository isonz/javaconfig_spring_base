package z.lib;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;

@Controller
public class Requestz
{
	static public String getIp(HttpServletRequest request) 
	{   
	     String ipAddress = null;    
	     ipAddress = request.getHeader("x-forwarded-for");   
	     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
	      ipAddress = request.getHeader("Proxy-Client-IP");   
	     }   
	     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
	         ipAddress = request.getHeader("WL-Proxy-Client-IP");   
	     }   
	     if(ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {   
	    	 ipAddress = request.getRemoteAddr();
	    	 if(ipAddress.equals("127.0.0.1")){   
	    		 //根据网卡取本机配置的IP   
	    		 InetAddress inet=null;   
	    		 try {   
	    			 inet = InetAddress.getLocalHost();   
	    		 } catch (UnknownHostException e) {   
	    			 e.printStackTrace();   
	    		 }   
	    		 ipAddress= inet.getHostAddress();   
	    	 }   
	            
	     }
	     //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割   
	     if(ipAddress!=null && ipAddress.length() > 15){ //"***.***.***.***".length() = 15   
	         if(ipAddress.indexOf(",")>0){   
	             ipAddress = ipAddress.substring(0,ipAddress.indexOf(","));   
	         }   
	     }   
	     return ipAddress;    
	}
	
	//获取当前完整的带参数的URL
	static public String getCurrentURL(HttpServletRequest request)
	{
		String query = request.getQueryString();
		int port = request.getServerPort();
		String servername = request.getServerName();
		String scheme = request.getScheme();
		if((80 != port && "http" != scheme) || (443 != port && "https" != scheme)) servername = servername + ":" + port;
		
		if(null != query){
			return  scheme+"://"+ servername + request.getRequestURI()+"?"+query;
		}else{
			return  scheme+"://"+ servername + request.getRequestURI();
		}
	}
	
	//-------判断是否在微信中打开页面
	static public boolean isInWeixin(HttpServletRequest request)
	{
		String ua = request.getHeader("user-agent").toLowerCase();  
		if (ua.indexOf("micromessenger") > 0) return true;  
		return false;
	}

	
		
}
