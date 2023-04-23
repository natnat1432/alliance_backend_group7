package ph.com.alliance.jpa.functions.shoptest.service;

import java.util.List;

import ph.com.alliance.jpa.common.PagedList;
import ph.com.alliance.jpa.functions.shoptest.model.Shop;
import ph.com.alliance.jpa.functions.usertest.model.User;

public interface ShopTestService{
    public Long getShopTestCount();
    public void addShopTest(Shop p);
    public void updateShopTest(Shop p);
    public List<Shop> listShopTests();
    public Shop getShopTestById(int id);
    public void removeShopTest(int id);
    void updateShopUsers(List<User> ulist, int id);
    PagedList pagedSearchList(int maxRecords, int pageNum, Shop shop);

}
