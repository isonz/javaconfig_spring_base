package cn.ptp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import cn.ptp.entity.Message;

@Repository
public interface MessageRepository extends PagingAndSortingRepository<Message, Long>
{
	Optional<Message> findById(int id);
	
	@Query("select c from message c where c.name = ?1")
	Optional<Message> findByName(String name);
}
