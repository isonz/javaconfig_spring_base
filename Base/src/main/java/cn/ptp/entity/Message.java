package cn.ptp.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

import z.lib.DateUtil;

@Entity
@Getter
@Table(name="message")
public class Message implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "INT(10) UNSIGNED")
	private int id;
	
	@Column(name = "name", length=20)
	private String name;
	
	@Column(name = "msg", columnDefinition="LONGTEXT")
	private String msg;
	
	@Column(name = "create_at", columnDefinition="INT(10) UNSIGNED COMMENT '时间戳'")
	private int create_at = DateUtil.getTimeStemp();
	
	//@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "update_at", columnDefinition="TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
	private String update_at = DateUtil.getTime();
	
	@Column(name = "date", columnDefinition="DATE")
	private Date date = DateUtil.getDate(new Date());
	
	@Column(name = "days", columnDefinition="float(10,2)")
	private float days=0;
	
	@Column(name = "ip", length=15)
	private String ip;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name.length() > 20) name = name.substring(0,20);
		this.name = name;
	}
	
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	@Column(updatable=false)
	public int getCreate_at() {
		return create_at;
	}
	public String getCreate_at(String format){
		return DateUtil.getTime(create_at, format);
	}
	public void setCreate_at(int create_at) {
		if(0==create_at) create_at = DateUtil.getTimeStemp();
		this.create_at = create_at;
	}
	
	public String getUpdate_at() {
		return update_at;
	}
	public void setUpdate_at(String update_at) {
		this.update_at = update_at;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public double getDays() {
		return days;
	}
	public void setDays(float d) {
		this.days = d;
	}

}
