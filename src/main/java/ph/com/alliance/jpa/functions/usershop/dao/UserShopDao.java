package ph.com.alliance.jpa.functions.usershop.dao;

import java.util.List;

import ph.com.alliance.jpa.entity.UserShop;

public interface UserShopDao{
    public List<UserShop> listUserShops();
    public UserShop getUserShopById(int id);
    public void removeUserShop(int id);
    public void addUserShop(int userId, int shopId);
    public UserShop findByUserShop(int userId, int shopId);
    void removeByUserShop(int userId, int shopId); 
}
