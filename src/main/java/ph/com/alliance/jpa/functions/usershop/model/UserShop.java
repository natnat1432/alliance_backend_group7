package ph.com.alliance.jpa.functions.usershop.model;

import java.sql.Timestamp;

import ph.com.alliance.jpa.functions.shoptest.model.Shop;
import ph.com.alliance.jpa.functions.usertest.model.User;

public class UserShop  {

	private int userShopId;

	private Timestamp timestamp;

	private Shop shopTest;

	private User userTest;

	public UserShop() {
	}
	
	public UserShop(ph.com.alliance.jpa.entity.UserShop us) {
        this.setUserShopId(us.getUserShopId());
        this.setShop(new Shop(us.getShopTest()));
        this.setUser(new User(us.getUserTest()));
        this.setTimestamp(us.getTimestamp());
    }
    
    public ph.com.alliance.jpa.entity.UserShop toEntity() {
        ph.com.alliance.jpa.entity.UserShop us = new ph.com.alliance.jpa.entity.UserShop();
        us.setUserShopId(this.getUserShopId());
        us.setShopTest(this.getShop().toEntity());
        us.setUserTest(this.getUser().toEntity());
        us.setTimestamp(this.getTimestamp());
        return us;
    }

	public int getUserShopId() {
		return this.userShopId;
	}

	public void setUserShopId(int userShopId) {
		this.userShopId = userShopId;
	}

	public Timestamp getTimestamp() {
		return this.timestamp;
	}

	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	public Shop getShop() {
		return this.shopTest;
	}

	public void setShop(Shop shopTest) {
		this.shopTest = shopTest;
	}

	public User getUser() {
		return this.userTest;
	}

	public void setUser(User userTest) {
		this.userTest = userTest;
	}

}