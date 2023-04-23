package ph.com.alliance.jpa.functions.user.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.query.Param;

import ph.com.alliance.jpa.entity.UserEntity;
import ph.com.alliance.jpa.functions.user.model.UserModel;
import ph.com.alliance.jpa.common.PagedList;


public interface UserService {

	List<UserEntity> getAllUser();
	List<UserEntity> getAllUsers();
	void createUser(UserModel userModel);
	void updateUser(Integer userID, UserModel userModel);
	Optional<UserEntity> getUser(Integer userID);
	void deleteUser(Integer userID);
	
	Object findByUserType(String user_type);
	Object getUserInfo(int userID);
	
//	Object searchUser(String searchQuery);
	

    void deleteById(Integer id);

    void delete(UserModel user);

    long count(String filter);

    void deleteAll();

    void deleteAll(Iterable<? extends UserModel> arg0);

    boolean existsById(Integer arg0);

    Iterable<UserModel> findAll();

    Iterable<UserModel> findAllById(Iterable<Integer> arg0);

    UserModel findById(Integer arg0);

    Iterable<UserModel> saveAll(Iterable<UserModel> arg0);

    UserModel save(UserModel arg0);

//    PagedList pagedSearchList(int maxRecords, int pageNum, UserModel userExample);
    PagedList pagedSearchList(int maxRecords, int pageNum, String userTypeFilter, UserModel userExample, String sort);
    int getMaxUserID();
    
    Object searchUserPaginated(String searchQuery, int offset, int maxRecords, String sort, String filter);
    
    int countSearchUser(String searchQuery, String filter);
	
}
