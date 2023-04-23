package ph.com.alliance.jpa.functions.usertest.dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import ph.com.alliance.jpa.entity.UserTest;

public interface UserTestDao extends CrudRepository<UserTest, Integer>, 
                                     PagingAndSortingRepository<UserTest, Integer>,
                                     QueryByExampleExecutor <UserTest>{
}
