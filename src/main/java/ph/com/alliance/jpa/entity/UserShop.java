package ph.com.alliance.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the user_shop database table.
 * 
 */
@Entity
@Table(name="user_shop")
@NamedQuery(name="UserShop.findAll", query="SELECT u FROM UserShop u")
public class UserShop implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int userShopId;

	private Timestamp timestamp;

	//bi-directional many-to-one association to ShopTest
	@ManyToOne
	@JoinColumn(name="shopId")
	private ShopTest shopTest;

	//bi-directional many-to-one association to UserTest
	@ManyToOne
	@JoinColumn(name="userId")
	private UserTest userTest;

	public UserShop() {
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

	public ShopTest getShopTest() {
		return this.shopTest;
	}

	public void setShopTest(ShopTest shopTest) {
		this.shopTest = shopTest;
	}

	public UserTest getUserTest() {
		return this.userTest;
	}

	public void setUserTest(UserTest userTest) {
		this.userTest = userTest;
	}

}