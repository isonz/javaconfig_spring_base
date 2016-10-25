package cn.ptp.repository;

import cn.ptp.entity.Department;
import cn.ptp.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface UserRepository extends PagingAndSortingRepository<User, Long>
{
	Optional<User> findById(long id);

	Optional<User> findByUsername(String name);

	@Query(value = "select d.* from user u, dept d where u.dept_id=d.id AND u.id= ?1", nativeQuery = true)
	Department findUserDeptName(long id);

	Department findDepartmentById(long id);
}
