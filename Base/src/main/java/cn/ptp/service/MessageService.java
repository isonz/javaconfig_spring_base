package cn.ptp.service;

import java.util.Optional;

import javax.transaction.Transactional;

import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import cn.ptp.entity.Message;
import cn.ptp.repository.MessageRepository;

@Transactional
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))	
public class MessageService
{
	private final MessageRepository repository;
	
	public Optional<Message> findById(int id)
	{
		Assert.notNull(id, "id must not be null");
		return repository.findById(id);
	}
	
	public Page<Message> findAll(Pageable pageable)
	{
		Assert.notNull(pageable, "Pageable must not be null!");
		return repository.findAll(pageable);
	}
	
	public Message add(Message message, String name, String msg) {

		Assert.notNull(name, "Username must not be null!");
		Assert.notNull(msg, "Password must not be null!");

		repository.findByName(name).ifPresent(mesg -> {
			throw new IllegalArgumentException("User with that name already exists!");
		});
		message.setName(name);
		message.setMsg(msg);
		return repository.save(message);
	}
}
