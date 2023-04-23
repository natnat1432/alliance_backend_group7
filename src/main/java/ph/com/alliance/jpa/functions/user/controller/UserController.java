package ph.com.alliance.jpa.functions.user.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import ph.com.alliance.jpa.common.ApiResult;
import ph.com.alliance.jpa.functions.user.model.UserInfo;
import ph.com.alliance.jpa.functions.user.model.UserModel;
import ph.com.alliance.jpa.functions.user.service.UserService;
import ph.com.alliance.jpa.functions.user.dao.UserDao;

@RestController
@RequestMapping("/user")

public class UserController {
	
	@Autowired
	private UserService userService;
	
	private static final String STR_CONTROLLER_NAME ="User";
	
	@GetMapping("/getAll")
	public Object getAllUser()	{
		return ApiResult.CreateSuccess(userService.getAllUser());
	}
	
	@PostMapping("/create")
	public ApiResult createUser(UserModel userModel)	{
		userService.createUser(userModel);
		return ApiResult.CreateSuccess("User successfully saved!");
	}
	
	@PutMapping("/update/{userID}")
	public ApiResult updateUser(@PathVariable Integer userID, UserModel userModel) {
		userService.updateUser(userID, userModel);
		return ApiResult.CreateSuccess("Successfully updated user!");
	}
	@DeleteMapping("/delete/{userID}")
	public ApiResult deleteUser(@PathVariable Integer userID) {
		userService.deleteUser(userID);
		return ApiResult.CreateSuccess("Successfully deleted user!");
	}
	
	@GetMapping("/usertype/{user_type}")
	public ApiResult findByUserType(@PathVariable String user_type) {
		userService.findByUserType(user_type);
		return ApiResult.CreateSuccess(userService.findByUserType(user_type),"Retrieved Successfully");
	}
	
	@GetMapping("/image/{userID}")
	public ResponseEntity<byte[]> getUserProfileImage(@PathVariable String userID) {
	    try {
	        // Build the path to the user's profile image
	        String path = "D:\\serverFiles\\users\\" + userID + "\\profile_image.jpg";
	        
	        // Read the image file into a byte array
	        byte[] imageBytes = Files.readAllBytes(Paths.get(path));
	        
	        // Create a response entity with the image byte array and content type
	        HttpHeaders headers = new HttpHeaders();
	        headers.setContentType(MediaType.IMAGE_JPEG);
	        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(imageBytes, headers, HttpStatus.OK);
	        return response;
	    } catch (IOException e) {
	        // If the file cannot be read, return a 404 error
	        return new ResponseEntity<byte[]>(HttpStatus.NOT_FOUND);
	    }
	}

	
	@GetMapping("/userinfo/{userID}")
	public ApiResult getUserInfo(@PathVariable("userID") int userID) {
	    
	    userService.getUserInfo(userID);
	    return ApiResult.CreateSuccess(userService.getUserInfo(userID), "Retrieved Successfully");
	}
	
	@GetMapping("/countID")
	public ApiResult getMaxUserID() {
	    
	    userService.getMaxUserID();
	    return ApiResult.CreateSuccess(userService.getMaxUserID(), "Retrieved Successfully");
	}


	@GetMapping("/")
	
	
//	  @RequestMapping(value = "/count", method = RequestMethod.GET)
//	    public ApiResult count() {
//	        return ApiResult.CreateSuccess(userService.count(),STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.RETRIEVE);
//	    }
	
	@RequestMapping(value = "/count/{filter}", method = RequestMethod.GET)
	public ApiResult count(@PathVariable(required = false) String filter) {
	    long count = userService.count(filter);
	    return ApiResult.CreateSuccess(count, STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.RETRIEVE);
	}
	
	    @RequestMapping(value = "/existsById/{id}", method = RequestMethod.GET)
	    public ApiResult existsById(@PathVariable int id) {
	        return ApiResult.CreateSuccess(userService.existsById(id), STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.RETRIEVE);
	    }
	    
	    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
	    public ApiResult findById(@PathVariable int id) {
	        return ApiResult.CreateSuccess(userService.findById(id),STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.RETRIEVE);
	    }
	    
	    @RequestMapping(value = "/findAllById", method = RequestMethod.GET)
	    public ApiResult findAllById(@RequestBody Iterable<Integer> arg0) {
	        return ApiResult.CreateSuccess(userService.findAllById(arg0), STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.RETRIEVE);
	    }
	            
	    @RequestMapping(value = "/findAll", method = RequestMethod.GET)
	    public ApiResult findAll() {
	        return ApiResult.CreateSuccess(userService.findAll(), STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.RETRIEVE);
	    } 
	    
	    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	    public ApiResult delete(@RequestBody UserModel arg0) {
	        userService.delete(arg0);
	        return ApiResult.CreateSuccess(STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.DELETE);
	    }
	    
	    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
	    public ApiResult deleteById(@PathVariable int id) {
	        userService.deleteById(id);
	        return ApiResult.CreateSuccess(STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.DELETE);
	    } 
	    
	    @RequestMapping(value = "/deleteAllWhere", method = RequestMethod.DELETE)
	    public ApiResult deleteAllWhere(@RequestBody List<UserModel> arg0) {
	        userService.deleteAll(arg0);
	        return ApiResult.CreateSuccess(STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.DELETE);
	    }
	    
	    @RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
	    public ApiResult deleteAll() {
	        userService.deleteAll();
	        return ApiResult.CreateSuccess(STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.DELETE);
	    }

	    @RequestMapping(value = "/save", method = RequestMethod.POST)
	    public ApiResult save(UserModel arg0) {
	        return ApiResult.CreateSuccess(userService.save(arg0), STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.UPDATE);
	    }

	    @RequestMapping(value = "/saveAll", method = RequestMethod.POST)
	    public ApiResult saveAll(@RequestBody Iterable<UserModel> arg0) {
	        return ApiResult.CreateSuccess(userService.saveAll(arg0), STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.UPDATE);
	    } 

//	    @RequestMapping(value = "/pagedSearchList/{maxRecords}/{pageNum}", method = RequestMethod.POST)
//	    public ApiResult pagedSearchList(@PathVariable int maxRecords, @PathVariable int pageNum, @RequestBody(required=false) UserModel searchModel) {
//	        return ApiResult.CreateSuccess(userService.pagedSearchList(maxRecords,pageNum,searchModel), STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.UPDATE);
//	    }
	    
	    @RequestMapping(value = "/pagedSearchList/{maxRecords}/{pageNum}/{userTypeFilter}/{sort}", method = RequestMethod.POST)
	    public ApiResult pagedSearchList(@PathVariable int maxRecords, @PathVariable int pageNum, @PathVariable 
	    		String userTypeFilter,@RequestBody(required=false) UserModel searchModel,@PathVariable String sort) {
	        return ApiResult.CreateSuccess(userService.pagedSearchList(maxRecords,pageNum,userTypeFilter,searchModel,sort), STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.UPDATE);
	    }
	    
//		@GetMapping("/searchUser/{searchQuery}", produces = "application/json; charset=UTF-8")
//		public ApiResult searchUser(@PathVariable("searchQuery") String searchQuery) {
//		    
//		    userService.searchUser(searchQuery);
//		    return ApiResult.CreateSuccess(userService.searchUser(searchQuery), "Retrieved Successfully");
//		}
	    
//	    
//	    @PostMapping(value = "/searchUser", produces = "application/json; charset=UTF-8")
//	    public ApiResult searchUser(@RequestBody Map<String, String> requestBody) {
//	        String searchQueryEncoded = requestBody.get("searchQuery");
//	        String searchQuery = "";
//	        try {
//	            searchQuery = URLDecoder.decode(searchQueryEncoded, "UTF-8");
//	        } catch (UnsupportedEncodingException e) {
//	            // Handle the exception
//	        }
//	        //need to count all data,
//	        //need to compute total page count with the input page count
//	        //
//	        userService.searchUser(searchQuery);
//	        return ApiResult.CreateSuccess(userService.searchUser(searchQuery), "Retrieved Successfully");
//	    }
	    
	    
	    @PostMapping(value = "/searchUser", produces = "application/json; charset=UTF-8")
	    public ApiResult searchUser(@RequestBody Map<String, String> requestBody) {
	        String searchQueryEncoded = requestBody.get("searchQuery");
	        String searchQuery = "";
	        try {
	            searchQuery = URLDecoder.decode(searchQueryEncoded, "UTF-8");
	        } catch (UnsupportedEncodingException e) {
	            // Handle the exception
	        }
	        int pageNumber = Integer.parseInt(requestBody.get("pageNumber"));
	        int maxRecords = Integer.parseInt(requestBody.get("maxRecords"));
	        String sort = requestBody.get("sort").toString().toUpperCase();
	        String filter = requestBody.get("filter").toString();
	        int offset = (pageNumber - 1) * maxRecords;
	        Object paginatedResults = userService.searchUserPaginated(searchQuery, offset, maxRecords, sort, filter);
	        return ApiResult.CreateSuccess(paginatedResults, "Retrieved Successfully");
	    }
	    
	    
	
		@PostMapping(value = "/countSearchUser", produces = "application/json; charset=UTF-8")
		public ApiResult countSearchUser(@RequestBody Map<String,String> requestBody) {
	        String searchQueryEncoded = requestBody.get("searchQuery");
	        String searchQuery = "";
	     
	
	        try {
	            searchQuery = URLDecoder.decode(searchQueryEncoded, "UTF-8");
	        } catch (UnsupportedEncodingException e) {
	            // Handle the exception
	        }
	        String filter = requestBody.get("filter").toString();
	        

	        int countSearchUser = userService.countSearchUser(searchQuery, filter);

	        return ApiResult.CreateSuccess(countSearchUser, "Retrieved Successfully");
		}
		
//	    @PostMapping(value = "/countMaxUserSearchResult", produces = "application/json; charset=UTF-8")
//	    public ApiResult getMaxUserSearchResult(@RequestBody Map<String, String> requestBody) {
//	        String searchQueryEncoded = requestBody.get("searchQuery");
//	        String searchQuery = "";
//	        try {
//	            searchQuery = URLDecoder.decode(searchQueryEncoded, "UTF-8");
//	        } catch (UnsupportedEncodingException e) {
//	            // Handle the exception
//	        }
//	        //need to count all data,
//	        //need to compute total page count with the input page count
//	        //
//	        userService.searchUser(searchQuery);
//	        return ApiResult.CreateSuccess(userService.searchUser(searchQuery), "Retrieved Successfully");
//	    }
	    

}
