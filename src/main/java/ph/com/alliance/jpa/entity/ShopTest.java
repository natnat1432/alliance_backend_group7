package ph.com.alliance.jpa.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the shop_test database table.
 * 
 */
@Entity
@Table(name="shop_test")
@NamedQuery(name="ShopTest.findAll", query="SELECT s FROM ShopTest s")
public class ShopTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int shopId;

	private String address;

	private String shopName;

	//bi-directional many-to-one association to UserShop
	@OneToMany(mappedBy="shopTest")
	private List<UserShop> userShops;

	//bi-directional many-to-many association to UserTest
	@ManyToMany
	@JoinTable(
		name="user_shop"
		, joinColumns={
			@JoinColumn(name="shopId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="userId")
			}
		)
	private List<UserTest> userTests;

	public ShopTest() {
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

	public List<UserShop> getUserShops() {
		return this.userShops;
	}

	public void setUserShops(List<UserShop> userShops) {
		this.userShops = userShops;
	}

	public UserShop addUserShop(UserShop userShop) {
		getUserShops().add(userShop);
		userShop.setShopTest(this);

		return userShop;
	}

	public UserShop removeUserShop(UserShop userShop) {
		getUserShops().remove(userShop);
		userShop.setShopTest(null);

		return userShop;
	}

	public List<UserTest> getUserTests() {
		return this.userTests;
	}

	public void setUserTests(List<UserTest> userTests) {
		this.userTests = userTests;
	}

}