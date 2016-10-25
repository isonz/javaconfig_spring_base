package cn.ptp.service;

import cn.ptp.entity.User;
import cn.ptp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.transaction.Transactional;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))	
public class UserService
{
	private final UserRepository repository;

	public User findOne(long id)
	{
		Assert.notNull(id, "id must not be null");
		return repository.findOne(id);
	}

	public int count()
	{
		return (int)repository.count();
	}

	public Page<User> paged(Pageable pageable)
	{
		Assert.notNull(pageable, "Pageable must not be null!");
		Page<User> page = repository.findAll(pageable);
		//for (Message p : page){	System.out.println(p.getName());}
		return page;
	}

	public Iterable<User> findAll()
	{
		return repository.findAll();
	}

	public User save(User user)
    {
		repository.findByUsername(user.getUsername()).ifPresent(tuser -> {
			if(!tuser.equals(user)){
				throw new IllegalArgumentException("Username 重复!");
			}
		});
		User tmp;
		try {
			tmp = findOne(user.getId());	    //防止没更新的字段变空
			tmp.setOpenid(user.getOpenid());
			tmp.setUsername(user.getUsername());
			tmp.setUserid(user.getUserid());
		}catch (NullPointerException e){
			tmp = user;
		}
		return repository.save(tmp);
	}

	public boolean delete(long id)
	{
		if(!repository.findById(id).isPresent()){
			//throw new IllegalArgumentException("ID: "+id+" is not exists!");
			return false;
		}
		repository.delete(id);
		if(!repository.findById(id).isPresent()) return true;
		return false;
	}

	public User sql(User user)
	{
		return user;
	}

}
