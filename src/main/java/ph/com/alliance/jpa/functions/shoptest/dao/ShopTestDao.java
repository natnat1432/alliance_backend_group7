package ph.com.alliance.jpa.functions.shoptest.dao;

import java.util.List;

import ph.com.alliance.jpa.common.PagedList;
import ph.com.alliance.jpa.entity.ShopTest;
import ph.com.alliance.jpa.functions.shoptest.model.Shop;

public interface ShopTestDao{
    public Long getShopTestCount(Shop example);
    public void addShopTest(ShopTest p);
    public void updateShopTest(ShopTest p);
    public List<ShopTest> listShopTests();
    public ShopTest getShopTestById(int id);
    public void removeShopTest(int id);
    PagedList pageListShopTests(PagedList pagedList, Shop example); 
}
