package project.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import project.domain.Online;

public interface OnlineRepository extends Repository<Online, Long>{

	public Page<Online> findAll(Pageable page);
	
	@Query("select o from Online o where o.id = ?1")
	public Online findOnlineById(Long id);
	
	@Query("select o from Online o where o.user.userID = ?1")
	public Online findOnlineByUserId(Long usr_id);
	
	@Modifying
	@Query("delete from Online o where o.id = ?1")
	public void removeOnlineById(Long id);
	
	@Modifying
	@Query("delete from Online o where o.user.userID = ?1")
	public void removeOnlineByUserId(Long usr_id);
	
	public Online save(Online online);
}
