package cn.ptp.entity;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
//import javax.persistence.Table;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.AllArgsConstructor;
import lombok.AccessLevel;

import z.lib.DateUtil;

@Entity
@RequiredArgsConstructor							//仅针对final系列强制初始化  
@AllArgsConstructor(access = AccessLevel.PACKAGE)	//构造包含所有参数的构造器,可指定access级别  
@EqualsAndHashCode(of = "id")
@Table(name="message")
public class Message
{
	private @GeneratedValue @Id Long id;
	@Getter @Setter private String name;
	@Getter @Setter private String msg;
	@Getter @Setter private int create_at = DateUtil.getTimeStemp();
	@Getter @Setter private String update_at = DateUtil.getTime();
	@Getter @Setter private Date date = DateUtil.getDate(new Date());
	@Getter @Setter private float days=0;
	@Getter @Setter private String ip;

}
