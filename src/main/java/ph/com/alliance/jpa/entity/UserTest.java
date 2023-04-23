package ph.com.alliance.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user_test database table.
 * 
 */
@Entity
@Table(name="user_test")
@NamedQuery(name="UserTest.findAll", query="SELECT u FROM UserTest u")
public class UserTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int userId;

	private String name;

	private String password;

	//bi-directional many-to-one association to UserShop
	@OneToMany(mappedBy="userTest")
	private List<UserShop> userShops;

	//bi-directional many-to-many association to ShopTest
	@ManyToMany(mappedBy="userTests")
	private List<ShopTest> shopTests;

	public UserTest() {
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<UserShop> getUserShops() {
		return this.userShops;
	}

	public void setUserShops(List<UserShop> userShops) {
		this.userShops = userShops;
	}

	public UserShop addUserShop(UserShop userShop) {
		getUserShops().add(userShop);
		userShop.setUserTest(this);

		return userShop;
	}

	public UserShop removeUserShop(UserShop userShop) {
		getUserShops().remove(userShop);
		userShop.setUserTest(null);

		return userShop;
	}

	public List<ShopTest> getShopTests() {
		return this.shopTests;
	}

	public void setShopTests(List<ShopTest> shopTests) {
		this.shopTests = shopTests;
	}

}