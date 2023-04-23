package ph.com.alliance.jpa.functions.Ticket.model;

public class TicketModel {
		
	
		
		Integer ticketID;
		Integer userID;
		String description;
		String status;
		String date_issued;
		Integer sales_handler;
		Integer billing_handler;
		Integer collection_handler;
		Integer treasury_handler;
		String date_closed;
		String feedback;
		
		public Integer getTicketID() {
			return ticketID;
		}
		public void setTicketID(Integer ticketID) {
			this.ticketID = ticketID;
		}
		public Integer getUserID() {
			return userID;
		}
		public void setUserID(Integer userID) {
			this.userID = userID;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		public String getStatus() {
			return status;
		}
		public void setStatus(String status) {
			this.status = status;
		}
		public String getDate_issued() {
			return date_issued;
		}
		public void setDate_issued(String date_issued) {
			this.date_issued = date_issued;
		}
		public Integer getSales_handler() {
			return sales_handler;
		}
		public void setSales_handler(Integer sales_handler) {
			this.sales_handler = sales_handler;
		}
		public Integer getBilling_handler() {
			return billing_handler;
		}
		public void setBilling_handler(Integer billing_handler) {
			this.billing_handler = billing_handler;
		}
		public Integer getCollection_handler() {
			return collection_handler;
		}
		public void setCollection_handler(Integer collection_handler) {
			this.collection_handler = collection_handler;
		}
		public Integer getTreasury_handler() {
			return treasury_handler;
		}
		public void setTreasury_handler(Integer treasury_handler) {
			this.treasury_handler = treasury_handler;
		}
		public String getDate_closed() {
			return date_closed;
		}
		public void setDate_closed(String date_closed) {
			this.date_closed = date_closed;
		}
		public String getFeedback() {
			return feedback;
		}
		public void setFeedback(String feedback) {
			this.feedback = feedback;
		}


		
}
