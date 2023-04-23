package ph.com.alliance.jpa.functions.user.service;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
//import java.net.URLDecoder;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;


import ph.com.alliance.jpa.functions.email.model.UserCreationEmailModel;
import ph.com.alliance.jpa.functions.email.service.EmailService;




import java.util.ArrayList;

import java.util.stream.Collectors;



import ph.com.alliance.jpa.common.PagedList;
import ph.com.alliance.jpa.entity.UserEntity;

import ph.com.alliance.jpa.functions.user.model.UserModel;
import ph.com.alliance.jpa.functions.user.model.UserInfo;
import ph.com.alliance.jpa.functions.user.dao.UserDao;
//import ph.com.alliance.jpa.functions.file.service.FileServiceImp;

import java.io.File;
import java.io.IOException;
//import java.io.UnsupportedEncodingException;

//import org.apache.commons.io.FileUtils;
import org.springframework.core.env.Environment;
//import org.springframework.stereotype.Component;

import java.util.Collections;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
    @PersistenceContext
    private EntityManager emf;
    
    @Autowired
    private Environment env;
    
    @Autowired
    EmailService emailService;

	@Override
	public java.util.List<UserEntity> getAllUser() {
		java.util.List<UserEntity> users = userDao.findAll();
		return users;
	}

	public void createUser(UserModel userModel) {
	    UserEntity user = new UserEntity();
	    user.setUserID(userModel.getUserID()); // set the ID field

	    boolean existingUser = userDao.existsById(userModel.getUserID());
	    if (existingUser == true) {
	        throw new IllegalArgumentException("User ID already exists");
	    }
	    try {
	        // Decode all string fields of userModel to UTF-8 encoding
	        UserModel decodedUserModel = new UserModel();
	        BeanUtils.copyProperties(decodedUserModel, userModel);
	        Field[] fields = decodedUserModel.getClass().getDeclaredFields();
	        for (Field field : fields) {
	            if (field.getType() == String.class) {
	                field.setAccessible(true);
	                String fieldValue = (String) field.get(decodedUserModel);
	                if (fieldValue != null) {
	                    String decodedFieldValue = new String(fieldValue.getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
	                    field.set(decodedUserModel, decodedFieldValue);
	                }
	            }
	        }

	        BeanUtils.copyProperties(user, decodedUserModel);

	        // Create the user directory if it doesn't exist
	        String userDirectory = env.getProperty("files.path") + "/users/" + user.getUserID();
	        File directory = new File(userDirectory);
	        if (!directory.exists()) {
	            directory.mkdirs();
	        }

	        // Copy the profile image to the user directory
	        String imagePath = env.getProperty("files.path") + "/images/profile_image.png";
	        File sourceFile = new File(imagePath);
	        File targetFile = new File(userDirectory + "/profile_image.jpg");
	        Files.copy(sourceFile.toPath(), targetFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

	        userDao.saveAndFlush(user);
	        UserCreationEmailModel mailModel = new UserCreationEmailModel();
	        mailModel.setSignature(env.getProperty("mail.from"));
	        mailModel.setName(decodedUserModel.getFirstname());
	        mailModel.setEmail_to(decodedUserModel.getEmail());
	        mailModel.setEmail_credential(decodedUserModel.getEmail());
	        mailModel.setPassword_credential(decodedUserModel.getPassword());
	        emailService.sendUserCreationMail(mailModel);
	    } catch (IllegalAccessException | InvocationTargetException e) {
	        e.printStackTrace();
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
	}

	public void updateUser(Integer userID, UserModel userModel) {
		UserEntity user = new UserEntity();
		try {
			BeanUtils.copyProperties(user, userModel);
			user.setUserID(userID);
			userDao.saveAndFlush(user);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

	public void deleteUser(Integer userID) {
		userDao.deleteById(userID);

	}

	@Override
	  public List<UserEntity> getAllUsers(){ return null; }
	
	@Override
	public Optional<UserEntity> getUser(Integer userID) {
		return Optional.empty();
	}

	public List<UserEntity> findByUserType(String user_type) {
		userDao.findByUserType(user_type);
		List<UserEntity> user = userDao.findByUserType(user_type);
		return user;
	}
	
	@Override
	public List<UserInfo>  getUserInfo(int userID) {
		userDao.getUserInfo(userID);
		 List<Object[]>  rows  = userDao.getUserInfo(userID);
		 List<UserInfo> users  = new ArrayList<>();
		 

		    for (Object[] row : rows) {
		    	UserInfo user = new UserInfo();
		        user.setUserID((int) row[0]);
		        user.setEmail((String) row[1]);
		        user.setFirstname((String) row[2]);
		        user.setLastname((String) row[3]);
		        user.setcontact_number((String) row[4]);
		        user.setuser_type((String) row[5]);
		        
		        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        String createdAtString = sdf.format(row[6]);
		        
		        user.setCreatedAt(createdAtString);
		        users.add(user);
		    }

		    return users;
	}

	
	   @Override
	    public void deleteById(Integer id) {
	        userDao.deleteById(id);
	    }

	    @Override
	    public void delete(UserModel user) {
	        userDao.delete(user.toEntity());
	    }

//	    @Override
//	    public long count() {
//	        return userDao.count();
//	    }
	    
	    @Override
	    public long count(String filter) {
	        if (filter == null || filter.equalsIgnoreCase("all")) {
	            return userDao.count();
	        } else {
	            ExampleMatcher matcher = ExampleMatcher.matching()
	                    .withStringMatcher(StringMatcher.CONTAINING)
	                    .withIgnorePaths("userId"); // ignore userId

	            UserEntity userExample = new UserEntity();
	            userExample.setuser_type(filter);

	            Example<UserEntity> example = Example.of(userExample, matcher);
	            return userDao.count(example);
	        }
	    }

	    @Override
	    public void deleteAll() {
	        userDao.deleteAll();
	    }

	    @Override
	    public void deleteAll(Iterable<? extends UserModel> arg0) {
	        List<UserEntity> userList = new ArrayList<UserEntity>();
	        arg0.forEach(u -> userList.add(u.toEntity()));
	        userDao.deleteAll(userList);
	    }

	    @Override
	    public boolean existsById(Integer arg0) {
	        return userDao.existsById(arg0);
	    }

	    @Override
	    public List<UserModel> findAll() {
	        List<UserModel> userList = new ArrayList<UserModel>();
	        userDao.findAll().forEach(u -> userList.add(new UserModel(u)));
	        return userList;
	    }

	    @Override
	    public Iterable<UserModel> findAllById(Iterable<Integer> arg0) {
	        List<UserModel> userList = new ArrayList<UserModel>();
	        userDao.findAllById(arg0).forEach(u -> userList.add(new UserModel(u)));
	        return userList;
	    }

	    @Override
	    public UserModel findById(Integer arg0) {
	        Optional<UserEntity> u = userDao.findById(arg0);
	        return u.isPresent() ? new UserModel(u.get()) : null;
	    }

	    @Override
	    public UserModel save(UserModel arg0) {
	        return new UserModel(userDao.save(arg0.toEntity()));
	    }

	    @Override
	    public Iterable<UserModel> saveAll(Iterable<UserModel> arg0) {
	        List<UserEntity> userList = new ArrayList<UserEntity>();
	        arg0.forEach(u -> userList.add(u.toEntity()));
	        userDao.saveAll(userList);
	        return arg0;
	    }
	    
//	    @Override
//	    public PagedList pagedSearchList(int maxRecords, int pageNum, UserModel userExample) {
//	        Page<UserEntity> page;
//	        Pageable pageable = PageRequest.of(pageNum - 1, maxRecords); // Page starts with 0
//	        if (null == userExample) {
//	            page = userDao.findAll(pageable);
//	        } else {
//	            ExampleMatcher matcher = ExampleMatcher.matching()
//	                    .withStringMatcher(StringMatcher.CONTAINING)
//	                    .withIgnorePaths("userId"); // ignore userId
//	            Example<UserEntity> example = Example.of(userExample.toEntity(), matcher);
//	            page = userDao.findAll(example, pageable);
//	        }
//	        List<UserModel> userList = page.getContent().stream().map(user -> new UserModel(user)).collect(Collectors.toList());
//	        PagedList pagedList = new PagedList(page.getTotalPages(), pageNum, maxRecords, userList);
//	        return pagedList;
//	    }

	    @Override
	    public PagedList pagedSearchList(int maxRecords, int pageNum, String userTypeFilter, UserModel userExample, String sort) {
	        Page<UserEntity> page;
	        Pageable pageable;
	        
	        if (sort != null && sort.equalsIgnoreCase("desc")) {
	            pageable = PageRequest.of(pageNum - 1, maxRecords, Sort.by("userID").descending());
	        } else {
	            pageable = PageRequest.of(pageNum - 1, maxRecords, Sort.by("userID").ascending());
	        }
	        
	        if (userTypeFilter == null || userTypeFilter.equalsIgnoreCase("all")) {
	            page = userDao.findAll(pageable);
	        } else {
	            ExampleMatcher matcher = ExampleMatcher.matching()
	                    .withStringMatcher(StringMatcher.CONTAINING)
	                    .withIgnorePaths("userId"); // ignore userId
	              
	            userExample.setuser_type(userTypeFilter);

	            Example<UserEntity> example = Example.of(userExample.toEntity(), matcher);
	            page = userDao.findAll(example, pageable);
	        }
	        
	        List<UserModel> userList = page.getContent().stream().map(user -> new UserModel(user)).collect(Collectors.toList());
	        PagedList pagedList = new PagedList(page.getTotalPages(), pageNum, maxRecords, userList);
	        return pagedList;
	    }
	    
	   
		@Override
		public int getMaxUserID() {
		
			userDao.getMaxUserID();
			int user = userDao.getMaxUserID();
			return user;
		}
		
		
//		@Override
//		public List<UserInfo>  searchUser(String searchQuery) {
//			
//		    try {
//		        searchQuery = URLDecoder.decode(searchQuery, "UTF-8");
//		    } catch (UnsupportedEncodingException e) {
//		        // Handle the exception
//		    	e.printStackTrace();
//		    }
//		    
//			userDao.searchUser(searchQuery);
//			 List<Object[]>  rows  = userDao.searchUser(searchQuery);
//			 List<UserInfo> users  = new ArrayList<>();
//			 
//
//			    for (Object[] row : rows) {
//			    	UserInfo user = new UserInfo();
//			        user.setUserID((int) row[0]);
//			        user.setEmail((String) row[1]);
//			        user.setFirstname((String) row[2]);
//			        user.setLastname((String) row[3]);
//			        user.setContactNumber((String) row[4]);
//			        user.setUserType((String) row[5]);
//			        
//			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//			        String createdAtString = sdf.format(row[6]);
//			        
//			        user.setCreatedAt(createdAtString);
//			        users.add(user);
//			    }
//
//			    return users;
//		}

		
		public Object searchUserPaginated(String searchQuery, int offset, int maxRecords, String sort, String filter) {
			
			
			userDao.searchUser(searchQuery);
		    List<Object[]> results = userDao.searchUser(searchQuery);
		    
		  
		    if(sort.equalsIgnoreCase("desc")) {
		    	Collections.reverse(results);
		    }
		    
		    List<UserInfo> paginatedResults = new ArrayList<>();
		    int totalRecords = results.size();
		    int end = Math.min(offset + maxRecords, totalRecords);
		    
		    if(filter == null || filter.equalsIgnoreCase("all"))
		    {
		  	  

			    for (int i = offset; i < end; i++) {
			    	
			        Object[] row = results.get(i);
			        UserInfo user = new UserInfo();
			        user.setUserID((Integer) row[0]);
			        user.setEmail((String) row[1]);
			        user.setFirstname((String) row[2]);
			        user.setLastname((String) row[3]);
			        user.setcontact_number((String) row[4]);
			        user.setuser_type((String) row[5]);
			        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			        String createdAtString = sdf.format(row[6]);
			        user.setCreatedAt(createdAtString);
			        paginatedResults.add(user);
			  
			    }
			   
		    	
		    }
		    else {
		  	  

			    for (int i = offset; i < end; i++) {
			        Object[] row = results.get(i);
			        if(filter.equals((String)row[5]))
			        {
			        	  UserInfo user = new UserInfo();
					        user.setUserID((Integer) row[0]);
					        user.setEmail((String) row[1]);
					        user.setFirstname((String) row[2]);
					        user.setLastname((String) row[3]);
					        user.setcontact_number((String) row[4]);
					        user.setuser_type((String) row[5]);
					        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					        String createdAtString = sdf.format(row[6]);
					        
					        user.setCreatedAt(createdAtString);
					        paginatedResults.add(user);
					      
			        }
			      
			    }
			    
			 
			
		    }
		    
		
	

		    return paginatedResults;
		}
		
		
		
		@Override
		public int countSearchUser(String searchQuery, String filter) {
		
		
			
			if(filter.equalsIgnoreCase("all")) {
				userDao.countSearchUserAll(searchQuery);
				int user = userDao.countSearchUserAll(searchQuery);
				return user;
			}
			else {
				userDao.countSearchUserFilter(searchQuery,filter);
				int user = userDao.countSearchUserFilter(searchQuery,filter);
				return user;
				
			}
		}
		

	

	    
	
	



}