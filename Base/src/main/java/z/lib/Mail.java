package z.lib;

import java.io.Serializable;

public class Mail implements Serializable 
{
	private static final long serialVersionUID = 1L;

	public static final String ENCODEING = "UTF-8";  
    private String host; // 服务器地址  
    private String port; // 服务器端口
	private String email; // 发件人的邮箱  
    private String nickname; // 发件人昵称  
    private String username; // 账号  
    private String password; // 密码  
    
    private String receiver; // 收件人的邮箱  
    private String subject; // 主题  
    private String message; // 信息(支持HTML)  
  
    public String getHost() {  
        return host;  
    }  
    public void setHost(String host) {  
        this.host = host;  
    }  
  
    public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	
    public String getEmail() {  
        return email;  
    }  
    public void setEmail(String email) {  
        this.email = email;  
    }  
  
    public String getReceiver() {  
        return receiver;  
    }
    public void setReceiver(String receiver) {  
        this.receiver = receiver;  
    }  
  
    public String getNickname() {  
        return nickname;  
    }  
    public void setNickname(String nickname) {  
        this.nickname = nickname;  
    }  
  
    public String getUsername() {  
        return username;  
    }
    public void setUsername(String username) {  
        this.username = username;  
    }  
  
    public String getPassword() {  
        return password;  
    }
    public void setPassword(String password) {  
        this.password = password;  
    }  
  
    public String getSubject() {  
        return subject;  
    }
    public void setSubject(String subject) {  
        this.subject = subject;  
    }  
  
    public String getMessage() {  
        return message;  
    }
    public void setMessage(String message) {  
        this.message = message;  
    }  
  
}
