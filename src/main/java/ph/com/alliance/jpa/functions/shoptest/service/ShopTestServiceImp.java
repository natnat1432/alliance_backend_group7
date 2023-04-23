package ph.com.alliance.jpa.functions.shoptest.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ph.com.alliance.jpa.common.PagedList;
import ph.com.alliance.jpa.entity.ShopTest;
import ph.com.alliance.jpa.entity.UserTest;
import ph.com.alliance.jpa.functions.shoptest.dao.ShopTestDao;
import ph.com.alliance.jpa.functions.shoptest.model.Shop;
import ph.com.alliance.jpa.functions.usertest.model.User;


@Service
@Transactional(rollbackFor = Exception.class)
public class ShopTestServiceImp implements ShopTestService {
    
    @Autowired
    private ShopTestDao shopDao;

    @Override
    public void addShopTest(Shop p) {
        shopDao.addShopTest(p.toEntity());
    }

    @Override
    public void updateShopTest(Shop p) {
        shopDao.updateShopTest(p.toEntity());
    }

    @Override
    public List<Shop> listShopTests() {
        return shopDao.listShopTests().stream().map(s -> new Shop(s)).collect(Collectors.toList());
    }

    @Override
    public Shop getShopTestById(int id) {
        return new Shop(shopDao.getShopTestById(id));
    }

    @Override
    public void removeShopTest(int id) {
        shopDao.removeShopTest(id);
    }

    @Override
    public Long getShopTestCount() {
        return shopDao.getShopTestCount(null);
    }
    
    @Override
    public void updateShopUsers(List<User> ulist, int id) {        
        ShopTest shop = shopDao.getShopTestById(id);
        List<UserTest> ul = ulist.stream().map(u->u.toEntity()).collect(Collectors.toList());
        shop.setUserTests(ul);
        shopDao.updateShopTest(shop);
    }
    
    @Override
    public PagedList pagedSearchList(int maxRecords, int pageNum, Shop shopModel) {
        PagedList pageList = new PagedList();
        pageList.setMaxRecords(maxRecords);
        pageList.setPageNumber(pageNum);
        pageList.setPageCount((shopDao.getShopTestCount(shopModel) - 1) /maxRecords + 1);
        pageList.setResultList(shopDao.pageListShopTests(pageList, shopModel).getResultList().stream().map(shop->new Shop((ShopTest) shop)).collect(Collectors.toList()));
        return pageList;
    }

}
