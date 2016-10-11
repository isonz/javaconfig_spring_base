/*
 * implements Serializable 最重要的两个原因是：
 * 1、将对象的状态保存在存储媒体中以便可以在以后重新创建出完全相同的副本；
 * 2、按值将对象从一个应用程序域发送至另一个应用程序域。
 */
package cn.ptp.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "dept" )
public class Department implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "INT(10) UNSIGNED")
	private Integer id;

	@Column(name = "name",length=20)
	private String name;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="parent_id", columnDefinition="INT(10) UNSIGNED COMMENT '父级ID'")
	private Department parent;

	
	public Department getParent() {
		return parent;
	}

	public void setParent(Department parent) {
		this.parent = parent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}
