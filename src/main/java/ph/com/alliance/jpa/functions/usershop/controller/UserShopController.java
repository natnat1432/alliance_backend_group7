
package ph.com.alliance.jpa.functions.usershop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ph.com.alliance.jpa.common.ApiResult;
import ph.com.alliance.jpa.functions.usershop.service.UserShopService;

@RestController
@RequestMapping("/usershop")
public class UserShopController {

    @Autowired
    private UserShopService usershopService;
    
    private static final String STR_CONTROLLER_NAME ="UserShop";

    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public ApiResult findById(@PathVariable int id) {
        return ApiResult.CreateSuccess(usershopService.getUserShopById(id),STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.RETRIEVE);
    }
    
    @RequestMapping(value = "/findByUserShop", method = RequestMethod.GET)
    public ApiResult findByUserShop(int userId, int shopId) {
        return ApiResult.CreateSuccess(usershopService.findByUserShop(userId, shopId),STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.RETRIEVE);
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ApiResult findAll() {
        return ApiResult.CreateSuccess(usershopService.listUserShops(), STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.RETRIEVE);
    } 
    
    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
    public ApiResult deleteById(@PathVariable int id) {
        usershopService.removeUserShop(id);
        return ApiResult.CreateSuccess(STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.DELETE);
    } 
    
    @RequestMapping(value = "/deleteByUserShop", method = RequestMethod.DELETE)
    public ApiResult deleteByUserShop(int userId, int shopId) {
        usershopService.deleteByUserShop(userId, shopId);
        return ApiResult.CreateSuccess(STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.DELETE);
    }
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResult add(int userId, int shopId) {
        usershopService.addUserShop(userId, shopId);
        return ApiResult.CreateSuccess(STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.INSERT);
    }
}
