package ph.com.alliance.jpa.functions.usershop.service;

import java.util.List;

import ph.com.alliance.jpa.functions.usershop.model.UserShop;

public interface UserShopService{
    public List<UserShop> listUserShops();
    public UserShop getUserShopById(int id);
    public void removeUserShop(int id);
    public void addUserShop(int userId, int shopId);
    public UserShop findByUserShop(int userId, int shopId);
    public void deleteByUserShop(int userId, int shopId);
}
