package ph.com.alliance.jpa.functions.user.dao;




import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.query.QueryByExampleExecutor;



import ph.com.alliance.jpa.entity.UserEntity;



public interface UserDao extends CrudRepository<UserEntity, Integer>, PagingAndSortingRepository<UserEntity, Integer>, 
								QueryByExampleExecutor <UserEntity>,JpaRepository<UserEntity, Integer>{
	

	@Query (value="select * from jumpstartuc.user where user_type = :user_type", nativeQuery=true)
	List <UserEntity> findByUserType(@Param (value = "user_type") String user_type);
	
	
	@Query(value = "SELECT userID,email,firstname, lastname,contact_number,user_type,created_at FROM jumpstartuc.user WHERE userID = :userID", nativeQuery = true)
	List<Object[]> getUserInfo(@Param("userID") int userID);
	
	@Query(value = "SELECT userID, email, firstname, lastname, contact_number, user_type, "
	        + "created_at FROM jumpstartuc.user WHERE userID LIKE %:searchQuery% OR firstname LIKE %:searchQuery% OR lastname LIKE %:searchQuery% "
	        + "OR contact_number LIKE %:searchQuery% OR email LIKE %:searchQuery% ", nativeQuery = true)
	List<Object[]> searchUser(@Param("searchQuery") String searchQuery);
	
	
	@Query(value = "SELECT MAX(userID) FROM jumpstartuc.user", nativeQuery = true)
	int getMaxUserID();
	
	
	
	@Query(value = "SELECT COUNT(*) FROM jumpstartuc.user WHERE userID LIKE %:searchQuery% OR firstname LIKE %:searchQuery% OR lastname LIKE %:searchQuery% "
	        + "OR contact_number LIKE %:searchQuery% OR email LIKE %:searchQuery% ORDER BY userID ASC", nativeQuery = true)
	int countSearchUserAll(@Param("searchQuery") String searchQuery);
	
	
	
	@Query(value = "SELECT COUNT(*) FROM jumpstartuc.user WHERE userID LIKE %:searchQuery% OR firstname LIKE %:searchQuery% OR lastname LIKE %:searchQuery% "
	        + "OR contact_number LIKE %:searchQuery% OR email LIKE %:searchQuery% AND user_type = :filter", nativeQuery = true)
	int countSearchUserFilter(@Param("searchQuery") String searchQuery, @Param("filter") String filter);
	
	

}
