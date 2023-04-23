package ph.com.alliance.jpa.functions.Ticket.dao;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


import ph.com.alliance.jpa.entity.Ticket;



@Repository
public interface TicketDao extends JpaRepository<Ticket, Integer> {
		
	@Query (value="select * from jumpstartuc.ticket where status = :status", nativeQuery=true)
	List <Ticket> findByStatus(@Param (value = "status") String status);
}
