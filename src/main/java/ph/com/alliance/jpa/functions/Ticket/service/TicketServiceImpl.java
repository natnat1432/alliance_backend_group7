package ph.com.alliance.jpa.functions.Ticket.service;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Optional;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ph.com.alliance.jpa.entity.Ticket;
import ph.com.alliance.jpa.functions.Ticket.model.TicketModel;
import ph.com.alliance.jpa.functions.Ticket.dao.TicketDao;

@Service
public class TicketServiceImpl implements TicketService {

	@Autowired
	TicketDao ticketDao;
	
	@Override
	public java.util.List<Ticket> getAllTicket() {
		// TODO Auto-generated method stub
		java.util.List<Ticket> tickets = ticketDao.findAll();
		
		return tickets;
		
	}
	
	public void createTicket(TicketModel ticketModel) {
		Ticket ticket = new Ticket();
		
		try {
			BeanUtils.copyProperties(ticket,  ticketModel);
			ticketDao.saveAndFlush(ticket);
		}
		catch(IllegalAccessException e) {
			e.printStackTrace();
		}
		catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public void updateTicket(Integer idticket, TicketModel ticketModel) {
		Ticket ticket = new Ticket();
		
		try {
			BeanUtils.copyProperties(ticket,  ticketModel);
			ticket.setTicketID(idticket);
			ticketDao.saveAndFlush(ticket);
		}catch(IllegalAccessException e) {
			e.printStackTrace();
		}
		catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}
	
	public void deleteTicket(Integer idticket) {
		ticketDao.deleteById(idticket);
	}
	
	@Override
	public List<Ticket> getAllTickets() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Ticket> getTicket(Integer idticket) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

	public List<Ticket> findByStatus(String status){
		ticketDao.findByStatus(status);
		
		List<Ticket> ticket = ticketDao.findByStatus(status);
		return ticket;
	}
}
