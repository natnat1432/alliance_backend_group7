package ph.com.alliance.jpa.functions.usertest.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ph.com.alliance.jpa.common.PagedList;
import ph.com.alliance.jpa.entity.UserTest;
import ph.com.alliance.jpa.functions.usertest.dao.UserTestDao;
import ph.com.alliance.jpa.functions.usertest.model.User;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserTestImp implements UserTestService {

    @Autowired
    private UserTestDao userDao;

    @PersistenceContext
    private EntityManager emf;

    @Override
    public void deleteById(Integer id) {
        userDao.deleteById(id);
    }

    @Override
    public void delete(User user) {
        userDao.delete(user.toEntity());
    }

    @Override
    public long count() {
        return userDao.count();
    }

    @Override
    public void deleteAll() {
        userDao.deleteAll();
    }

    @Override
    public void deleteAll(Iterable<? extends User> arg0) {
        List<UserTest> userList = new ArrayList<UserTest>();
        arg0.forEach(u -> userList.add(u.toEntity()));
        userDao.deleteAll(userList);
    }

    @Override
    public boolean existsById(Integer arg0) {
        return userDao.existsById(arg0);
    }

    @Override
    public List<User> findAll() {
        List<User> userList = new ArrayList<User>();
        userDao.findAll().forEach(u -> userList.add(new User(u)));
        return userList;
    }

    @Override
    public Iterable<User> findAllById(Iterable<Integer> arg0) {
        List<User> userList = new ArrayList<User>();
        userDao.findAllById(arg0).forEach(u -> userList.add(new User(u)));
        return userList;
    }

    @Override
    public User findById(Integer arg0) {
        Optional<UserTest> u = userDao.findById(arg0);
        return u.isPresent() ? new User(u.get()) : null;
    }

    @Override
    public User save(User arg0) {
        return new User(userDao.save(arg0.toEntity()));
    }

    @Override
    public Iterable<User> saveAll(Iterable<User> arg0) {
        List<UserTest> userList = new ArrayList<UserTest>();
        arg0.forEach(u -> userList.add(u.toEntity()));
        userDao.saveAll(userList);
        return arg0;
    }
    
    @Override
    public PagedList pagedSearchList(int maxRecords, int pageNum, User userExample) {
        Page<UserTest> page;
        Pageable pageable = PageRequest.of(pageNum - 1, maxRecords); // Page starts with 0
        if (null == userExample) {
            page = userDao.findAll(pageable);
        } else {
            ExampleMatcher matcher = ExampleMatcher.matching()
                    .withStringMatcher(StringMatcher.CONTAINING)
                    .withIgnorePaths("userId"); // ignore userId
            Example<UserTest> example = Example.of(userExample.toEntity(), matcher);
            page = userDao.findAll(example, pageable);
        }
        List<User> userList = page.getContent().stream().map(user -> new User(user)).collect(Collectors.toList());
        PagedList pagedList = new PagedList(page.getTotalPages(), pageNum, maxRecords, userList);
        return pagedList;
    }

}
