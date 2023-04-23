package ph.com.alliance.jpa.functions.shoptest.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import ph.com.alliance.jpa.common.PagedList;
import ph.com.alliance.jpa.entity.ShopTest;
import ph.com.alliance.jpa.functions.shoptest.dao.ShopTestDao;
import ph.com.alliance.jpa.functions.shoptest.model.Shop;

@Repository 
@SuppressWarnings("unchecked")
public class ShopTestDaoImp implements ShopTestDao {

    @PersistenceContext
    private EntityManager emf;

    @Override
    public void addShopTest(ShopTest p) {
        emf.persist(p);
    }

    @Override
    public void updateShopTest(ShopTest p) {
        emf.merge(p);
    }

    @Override
    public void removeShopTest(int id) {
        emf.remove(emf.find(ShopTest.class, id));
    }

    @Override
    public ShopTest getShopTestById(int id) {
        return emf.find(ShopTest.class, id);
    }

    @Override
    public List<ShopTest> listShopTests() {
        CriteriaQuery<ShopTest> cq = emf.getCriteriaBuilder().createQuery(ShopTest.class);
        return emf.createQuery(cq.select(cq.from(ShopTest.class))).getResultList();
    }

    @Override
    public Long getShopTestCount(Shop example) {
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<ShopTest> root = cq.from(ShopTest.class);
        CriteriaQuery<Long> count = cq.select(cb.count(root));
        if (null != example) {
            count = (CriteriaQuery<Long>)withWhere(root, cq, cb, example);
        }
        return emf.createQuery(count).getSingleResult();
    }

    @Override
    public PagedList pageListShopTests(PagedList pageList, Shop example) {
        CriteriaBuilder cb = emf.getCriteriaBuilder();
        CriteriaQuery<ShopTest> cq = cb.createQuery(ShopTest.class);
        Root<ShopTest> root = cq.from(ShopTest.class);
        CriteriaQuery<ShopTest> search = cq.select(root);
        if (null != example) {
            search = (CriteriaQuery<ShopTest>)withWhere(root, cq, cb, example);
        }
        pageList.setResultList(emf.createQuery(search).setMaxResults(pageList.getMaxRecords()).setFirstResult((pageList.getPageNumber() - 1) * pageList.getMaxRecords()).getResultList());        
        return pageList;
    }

    public CriteriaQuery<?> withWhere(Root<ShopTest> root, CriteriaQuery<?> cq, CriteriaBuilder cb, Shop example) {
        List<Predicate> predicates = new ArrayList<>();
        if (0 != example.getShopId()) {
            predicates.add(cb.equal(root.get("shopId"), example.getShopId()));
        }
        if (StringUtils.isNotBlank(example.getShopName())) {
            predicates.add(cb.like(cb.lower(root.get("shopName")), "%" + example.getShopName().toLowerCase() + "%"));
        }
        if (StringUtils.isNotBlank(example.getAddress())) {
            predicates.add(cb.like(cb.lower(root.get("address")), "%" + example.getAddress().toLowerCase() + "%"));
        }
        
        return cq.where(cb.and(predicates.toArray(new Predicate[0])));
    }

}