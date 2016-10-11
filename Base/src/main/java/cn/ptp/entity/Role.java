package cn.ptp.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table( name = "role" )
public class Role implements Serializable{
	private static final long serialVersionUID = 1L;
	/**
	 * 部门经理code
	 */
	public final static String DEPT_MANAGER_CODE = "01";
	/**
	 * 总经理code
	 */
	public final static String GENERALMANAGER_CODE = "02";
	/**
	 * 董事长code
	 */
	public final static String CHAIRMAN_CODE = "03";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "INT(10) UNSIGNED")
	private Integer id;
	
	@Column(name = "code", columnDefinition="varchar(20) COMMENT '角色代码'")
	private String code;
	
	@Column(name = "name",length=20)
	private String name;
	
	@ManyToMany(mappedBy = "roles")
	private List<User> users = new ArrayList<User>();

	
	public List<User> getUsers() {
		return users;
	}

	public void setUsers(List<User> users) {
		this.users = users;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
        Role u = (Role)o;
		return u.getId().equals(this.getId());
	}
}
