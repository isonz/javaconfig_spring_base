/*
 * implements Serializable 最重要的两个原因是：
 * 1、将对象的状态保存在存储媒体中以便可以在以后重新创建出完全相同的副本；
 * 2、按值将对象从一个应用程序域发送至另一个应用程序域。
 * 3、用transient关键字标记的成员变量不参与序列化过程。如：transient private String abc;	
 */

package cn.ptp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "user" )
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "INT(10) UNSIGNED")
	private Integer id;
	
	@Column(name = "openid", columnDefinition="varchar(30) COMMENT '微信OPENID'")
	private String openid;
	
	@Column(name = "userid", columnDefinition="varchar(20) COMMENT '微信企业号里的userid'")
	private String userid;
	
	@Column(name = "username",length=20)
	private String username;
	
	@ManyToMany
	private List<Role> roles = new ArrayList<Role>();
	
	@ManyToOne(fetch=FetchType.EAGER)  //如果是EAGER，那么表示取出这条数据时，它关联的数据也同时取出放入内存中,如果是LAZY，那么取出这条数据时，它关联的数据并不取出来，在同一个session中，什么时候要用，就什么时候取(再次访问数据库)。但是，在session外，就不能再取了。用EAGER时，因为在内存里，所以在session外也可以取。一般只在一边设Eager，JPA接口默认为一对多为Lazy，多对一为Eager，但是Hibernate反向工程生成Entity时，多对一为Lazy，需要手动改为Eager。而两边都设Eager，那么代码中取一条记录时，会发2次SQL。
	@JoinColumn(name="dept_id", columnDefinition="INT(10) UNSIGNED COMMENT '部门ID，外键'")
	private Department department;
	
	@Column(name = "valid", columnDefinition="bit COMMENT '用户是否有效T,F'")
	private boolean valid=true;


	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public boolean isValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}

	@Override
	public int hashCode() {
		
		return id;
	}

	@Override
	public boolean equals(Object o) {
		if ( this == o ) {
            return true;
        }
        if ( o == null || getClass() != o.getClass() ) {
            return false;
        }
        User u = (User)o;
		return u.getId().equals(this.getId());
	}
	
	

}
