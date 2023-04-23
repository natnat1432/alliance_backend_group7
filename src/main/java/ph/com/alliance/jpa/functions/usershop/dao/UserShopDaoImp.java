package ph.com.alliance.jpa.functions.usershop.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import ph.com.alliance.jpa.entity.ShopTest;
import ph.com.alliance.jpa.entity.UserShop;
import ph.com.alliance.jpa.entity.UserTest;

@Repository
public class UserShopDaoImp implements UserShopDao {
    
    @PersistenceContext
    private EntityManager emf;
    
    @Override
    public void removeUserShop(int id) {
        emf.remove(emf.find(UserShop.class, id));
    }
    
    @Override
    public UserShop getUserShopById(int id) {
        return emf.find(UserShop.class, id);
    }
        
    @Override
    public void addUserShop(int userId, int shopId) {
        UserShop us = new UserShop();
        us.setUserTest(emf.find(UserTest.class, userId));
        us.setShopTest(emf.find(ShopTest.class, shopId));
        emf.persist(us);
    }    
    
    @Override
    public void removeByUserShop(int userId, int shopId) {
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        CriteriaDelete<UserShop> delete = cb.createCriteriaDelete(UserShop.class);
        Root<UserShop> root = delete.from(UserShop.class);
        Predicate equalUser = cb.equal(root.get("userTest").get("userId"), userId);
        Predicate equalShop = cb.equal(root.get("shopTest").get("shopId"), shopId);
        delete.where(cb.and(equalUser, equalShop));
        emf.createQuery(delete).executeUpdate();
    }
    
    @Override
    public List<UserShop> listUserShops() {
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        CriteriaQuery<UserShop> cq = cb.createQuery(UserShop.class);
        Root<UserShop> rootEntry = cq.from(UserShop.class);
        CriteriaQuery<UserShop> all = cq.select(rootEntry);
        return emf.createQuery(all).getResultList();
    }   

    @Override
    public UserShop findByUserShop(int userId, int shopId) {
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        CriteriaQuery<UserShop> cq = cb.createQuery(UserShop.class);
        Root<UserShop> root = cq.from(UserShop.class);
        Predicate equalUser = cb.equal(root.get("userTest").get("userId"), userId);
        Predicate equalShop = cb.equal(root.get("shopTest").get("shopId"), shopId);
        CriteriaQuery<UserShop> all = cq.select(root).where(cb.and(equalUser, equalShop));
        return emf.createQuery(all).getSingleResult();
    }
   
}