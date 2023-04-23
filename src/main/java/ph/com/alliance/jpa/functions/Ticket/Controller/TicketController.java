package ph.com.alliance.jpa.functions.Ticket.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ph.com.alliance.jpa.common.ApiResult;
import ph.com.alliance.jpa.functions.Ticket.model.TicketModel;
import ph.com.alliance.jpa.functions.Ticket.service.TicketService;

@RestController
@RequestMapping("/ticket")

public class TicketController {

	@Autowired
	private TicketService ticketService;

	@GetMapping("/getAll")
	public Object getAllTicket() {

		return ApiResult.CreateSuccess(ticketService.getAllTicket());
	}
	
	@GetMapping("/test")
	public ApiResult getTest() {
		return ApiResult.CreateSuccess("Okay Gwapo ko Wilson");
	}
	
	@PostMapping("/create")
	public ApiResult createTicket(TicketModel ticketModel) {
		ticketService.createTicket(ticketModel);
		return ApiResult.CreateSuccess("Successfully saved!");
	}
	
	@PutMapping("/update/{idticket}")
	public ApiResult updateTicket(@PathVariable Integer idticket, TicketModel ticketModel) {
		ticketService.updateTicket(idticket, ticketModel);
		return ApiResult.CreateSuccess("Successfully updated!");
	}
	
	@DeleteMapping("/delete/{idticket}")
	public ApiResult deleteTicket(@PathVariable Integer idticket) {
		ticketService.deleteTicket(idticket);
		return ApiResult.CreateSuccess("Successfully deleted!");
	}
	 
	@GetMapping("/status/{status}")
	public ApiResult findByStatus(@PathVariable String status) {
		ticketService.findByStatus(status);
		return ApiResult.CreateSuccess(ticketService.findByStatus(status), "Retrieved Successfully");
	}
}
