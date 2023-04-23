package ph.com.alliance.jpa.functions.usertest.service;

import ph.com.alliance.jpa.common.PagedList;
import ph.com.alliance.jpa.functions.usertest.model.User;

public interface UserTestService{

    void deleteById(Integer id);

    void delete(User user);

    long count();

    void deleteAll();

    void deleteAll(Iterable<? extends User> arg0);

    boolean existsById(Integer arg0);

    Iterable<User> findAll();

    Iterable<User> findAllById(Iterable<Integer> arg0);

    User findById(Integer arg0);

    Iterable<User> saveAll(Iterable<User> arg0);

    User save(User arg0);

    PagedList pagedSearchList(int maxRecords, int pageNum, User userExample);

}
