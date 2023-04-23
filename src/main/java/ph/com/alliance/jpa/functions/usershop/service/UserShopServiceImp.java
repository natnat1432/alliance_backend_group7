package ph.com.alliance.jpa.functions.usershop.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ph.com.alliance.jpa.functions.usershop.dao.UserShopDao;
import ph.com.alliance.jpa.functions.usershop.model.UserShop;


@Service
@Transactional(rollbackFor = Exception.class)
public class UserShopServiceImp implements UserShopService {
    
    @Autowired
    private UserShopDao usershopDao;

    @Override
    public List<UserShop> listUserShops() {
        List<UserShop> usershopList = new ArrayList<UserShop>();
        usershopDao.listUserShops().forEach(u-> usershopList.add(new UserShop(u)));
        return usershopList;
    }

    @Override
    public UserShop getUserShopById(int id) {
        return new UserShop(usershopDao.getUserShopById(id));
    }

    @Override
    public void removeUserShop(int id) {
        usershopDao.removeUserShop(id);
    }

    @Override
    public void addUserShop(int userId, int shopId) {
        usershopDao.addUserShop(userId, shopId);
    }

    @Override
    public UserShop findByUserShop(int userId, int shopId) {
        return new UserShop(usershopDao.findByUserShop(userId, shopId));
    }

    @Override
    public void deleteByUserShop(int userId, int shopId) {
        usershopDao.removeByUserShop(userId, shopId);
    }
}
