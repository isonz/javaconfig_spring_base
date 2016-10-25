package cn.ptp.repository;

import java.util.Optional;

import cn.ptp.entity.Message;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface MessageRepository extends PagingAndSortingRepository<Message, Long>
{
	Optional<Message> findById(long id);

	//@Query("select c from message c where c.name = ?1")
	//@Query("from message where name=:name")
	//@Query(value = "select u.* from userinfo u where u.name = ?1", nativeQuery = true)
	Optional<Message> findByName(String name);
}
