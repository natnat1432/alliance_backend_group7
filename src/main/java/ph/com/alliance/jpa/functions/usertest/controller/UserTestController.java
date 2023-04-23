
package ph.com.alliance.jpa.functions.usertest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ph.com.alliance.jpa.common.ApiResult;
import ph.com.alliance.jpa.functions.usertest.model.User;
import ph.com.alliance.jpa.functions.usertest.service.UserTestService;

@RestController
@RequestMapping("/usertest")
public class UserTestController {

    @Autowired
    private UserTestService userService;
    
    private static final String STR_CONTROLLER_NAME ="UserTest";

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ApiResult count() {
        return ApiResult.CreateSuccess(userService.count(),STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.RETRIEVE);
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
    public ApiResult delete(@RequestBody User arg0) {
        userService.delete(arg0);
        return ApiResult.CreateSuccess(STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.DELETE);
    }
    
    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
    public ApiResult deleteById(@PathVariable int id) {
        userService.deleteById(id);
        return ApiResult.CreateSuccess(STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.DELETE);
    } 
    
    @RequestMapping(value = "/deleteAllWhere", method = RequestMethod.DELETE)
    public ApiResult deleteAllWhere(@RequestBody List<User> arg0) {
        userService.deleteAll(arg0);
        return ApiResult.CreateSuccess(STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.DELETE);
    }
    
    @RequestMapping(value = "/deleteAll", method = RequestMethod.DELETE)
    public ApiResult deleteAll() {
        userService.deleteAll();
        return ApiResult.CreateSuccess(STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.DELETE);
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ApiResult save(User arg0) {
        return ApiResult.CreateSuccess(userService.save(arg0), STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.UPDATE);
    }

    @RequestMapping(value = "/saveAll", method = RequestMethod.POST)
    public ApiResult saveAll(@RequestBody Iterable<User> arg0) {
        return ApiResult.CreateSuccess(userService.saveAll(arg0), STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.UPDATE);
    } 

    @RequestMapping(value = "/pagedSearchList/{maxRecords}/{pageNum}", method = RequestMethod.POST)
    public ApiResult pagedSearchList(@PathVariable int maxRecords, @PathVariable int pageNum, @RequestBody(required=false) User searchModel) {
        return ApiResult.CreateSuccess(userService.pagedSearchList(maxRecords,pageNum,searchModel), STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.UPDATE);
    }

}
