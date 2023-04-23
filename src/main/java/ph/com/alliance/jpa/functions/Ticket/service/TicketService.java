package ph.com.alliance.jpa.functions.Ticket.service;

import java.util.List;
import java.util.Optional;

import ph.com.alliance.jpa.entity.Ticket;
import ph.com.alliance.jpa.functions.Ticket.model.TicketModel;

public interface TicketService{
	List<Ticket> getAllTickets();
	void createTicket(TicketModel ticketModel);
	List<Ticket> getAllTicket();
	void updateTicket(Integer idticket, TicketModel ticketModel);
	Optional<Ticket> getTicket(Integer idticket);
	void deleteTicket(Integer idticket);
	Object findByStatus(String status);
	
}
