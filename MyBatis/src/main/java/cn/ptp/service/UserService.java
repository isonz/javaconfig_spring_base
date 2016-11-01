package cn.ptp.service;

import cn.ptp.Page;
import cn.ptp.entity.Dept;
import cn.ptp.entity.Role;
import cn.ptp.entity.User;
import cn.ptp.mapper.DeptMapper;
import cn.ptp.mapper.RoleMapper;
import cn.ptp.mapper.UserMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor(onConstructor = @__(@Autowired))	
public class UserService
{
	private final UserMapper userMapper;
	private final RoleMapper roleMapper;
	private final DeptMapper deptMapper;

	public User findOne(int id)
	{
		User user = userMapper.selectByPrimaryKey(id);
		List<Role> roles = userMapper.selectUserRole(user.getId());
		user.setRoles(roles);
		user.setDepartment(deptMapper.selectByPrimaryKey(user.getDept_id()));
		return user;
	}

	public List<User> findAll()
	{
		List<User> users = userMapper.findAll();

		return users;
	}

	public List<User> findAllOrderByIdDesc()
	{
		List<User> users = userMapper.findAllOrderByIdDesc();
		List<User> tmp = new ArrayList<User>();
		for(User user:users){
			List<Role> roles = userMapper.selectUserRole(user.getId());
			user.setRoles(roles);
			user.setDepartment(deptMapper.selectByPrimaryKey(user.getDept_id()));
			tmp.add(user);
		}
		return tmp;
	}

	public Page<User> paging(int start, int count)
	{
		if(start < 1) start = 0;
		if(count < 1) count = 1;
		double total = userMapper.pageTotal();
		if(count > total) count = (int)total;
		double pages = Math.ceil(total/count);
		if(start+count > total) start = ((int)pages-1)+count;
		if(start+count <1 ) start = 0;
		List<User> list = userMapper.paging(start, count);
		Page<User> page = new Page<User>(list);
		page.setTotal(total);
		page.setPages(pages);
		return page;
	}

	public User findByUsername(String username)
	{
		Assert.hasText(username, "Name must not be empty!");
		return userMapper.findByUsername(username);
	}

	@Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
	public User save(User user, String roles_id)
	{
		Assert.hasText(user.getUsername(), "Mst must not be empty!");
		User mesg = findByUsername(user.getUsername());
		if(null != mesg && mesg.getId() != user.getId()){
			throw new IllegalArgumentException("Name 重复!");
		}
		user.setValid(true);

		int id;
		try {
			id = user.getId();
			userMapper.updateByPrimaryKeySelective(user);
			//-------- 处理关联表
			try {
				String[] strArray = roles_id.split(",");
				userMapper.deleteUserRoleByUser(id);
				insertUserRoleByArray(id, strArray);
			}catch (NullPointerException e){}
		}catch (NullPointerException e){
			userMapper.insert(user);       //插入的条数
			//-------- 处理关联表
			id = user.getId();
			try {
				String[] strArray = roles_id.split(",");
				insertUserRoleByArray(id, strArray);
			}catch (NullPointerException c){}
		}

		System.out.println(user.getId());      //获取插入的ID
		//userMapper.deleteByPrimaryKey(id); //测试Transactional

		if(id > 0) return user;
		return null;
	}

	public void insertUserRoleByArray(long users_id, String[] strArray){
		for(String str : strArray){
			int role_id = Integer.parseInt(str);
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("users_id", users_id);
			param.put("roles_id", role_id);
			userMapper.insertUserRole(param);
		}
	}

	@Transactional(readOnly = false)
	public boolean delete(int id)
	{
		User mess = userMapper.selectByPrimaryKey(id);
		if(null == mess){
			//throw new IllegalArgumentException("ID: "+id+" is not exists!");
			return false;
		}
		int status = userMapper.deleteByPrimaryKey(id);
		if(status>0) return true;
		return false;
	}

	public Iterable<Dept> findAllDept()
	{
		return deptMapper.findAll();
	}

	public Iterable<Role> findAllRole()
	{
		return roleMapper.findAll();
	}


}
