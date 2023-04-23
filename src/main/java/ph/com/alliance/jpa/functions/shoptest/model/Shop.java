package ph.com.alliance.jpa.functions.shoptest.model;

import java.util.ArrayList;
import java.util.List;

import ph.com.alliance.jpa.entity.ShopTest;
import ph.com.alliance.jpa.functions.usertest.model.User;
public class Shop  {

	private int shopId;

	private String address;

	private String shopName;
	
	List<User> users;
	
	/**
     * @return the users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * @param users the users to set
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

    public Shop() {
	}
	
	public Shop(ShopTest s) {
	    List<User> users = new ArrayList<User>();
	    this.setShopId(s.getShopId());
	    this.setShopName(s.getShopName());
	    this.setAddress(s.getAddress());
	    s.getUserTests().forEach(u->users.add(new User(u)));
	    this.setUsers(users);
	    
    }	

    public ShopTest toEntity() {
	    ShopTest s = new ShopTest();
        s.setShopId(this.getShopId());
        s.setShopName(this.getShopName());
        s.setAddress(this.getAddress());
        return s;
    }

	public int getShopId() {
		return this.shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

}