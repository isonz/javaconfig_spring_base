package cn.ptp.service;

import java.util.Optional;

import javax.transaction.Transactional;

import cn.ptp.entity.Message;
import cn.ptp.repository.MessageRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))	
public class MessageService
{
	private final MessageRepository repository;

	public Message findOne(long id)
	{
		Assert.notNull(id, "id must not be null");
		return repository.findOne(id);
	}
	
	public Page<Message> findAll(Pageable pageable)
	{
		Assert.notNull(pageable, "Pageable must not be null!");
		return repository.findAll(pageable);
	}

	public Iterable<Message> findAll()
	{
		return repository.findAll();
	}
	
	public Message save(Message message){

		Assert.notNull(message.getName(), "Username must not be null!");
		Assert.notNull(message.getMsg(), "Password must not be null!");

		repository.findByName(message.getName()).ifPresent(mesg -> {
			System.out.println(mesg.getName());
			throw new IllegalArgumentException("Name 重复!");
		});
		return message;
		//return repository.save(message);
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

}
