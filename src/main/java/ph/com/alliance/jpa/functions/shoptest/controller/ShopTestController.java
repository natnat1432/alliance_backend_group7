
package ph.com.alliance.jpa.functions.shoptest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ph.com.alliance.jpa.common.ApiResult;
import ph.com.alliance.jpa.functions.shoptest.model.Shop;
import ph.com.alliance.jpa.functions.shoptest.service.ShopTestService;
import ph.com.alliance.jpa.functions.usertest.model.User;

@RestController
@RequestMapping("/shop")
public class ShopTestController {

    @Autowired
    private ShopTestService shopService;
    
    private static final String STR_CONTROLLER_NAME ="ShopTest";

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public ApiResult count() {
        return ApiResult.CreateSuccess(shopService.getShopTestCount(),STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.RETRIEVE);
    }
        
    @RequestMapping(value = "/findById/{id}", method = RequestMethod.GET)
    public ApiResult findById(@PathVariable int id) {
        return ApiResult.CreateSuccess(shopService.getShopTestById(id),STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.RETRIEVE);
    }
    
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public ApiResult findAll() {
        return ApiResult.CreateSuccess(shopService.listShopTests(), STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.RETRIEVE);
    } 
    
    @RequestMapping(value = "/deleteById/{id}", method = RequestMethod.DELETE)
    public ApiResult deleteById(@PathVariable int id) {
        shopService.removeShopTest(id);
        return ApiResult.CreateSuccess(STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.DELETE);
    } 
    
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ApiResult add(Shop arg0) {
        shopService.addShopTest(arg0);
        return ApiResult.CreateSuccess(STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.INSERT);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ApiResult saveAll(Shop arg0) {
        shopService.updateShopTest(arg0);
        return ApiResult.CreateSuccess(STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.UPDATE);
    } 
    
    @RequestMapping(value = "/updateUsers/{id}", method = RequestMethod.POST)
    public ApiResult saveAll(@RequestBody List<User> ulist,@PathVariable int id) {
        shopService.updateShopUsers(ulist, id);
        return ApiResult.CreateSuccess(STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.UPDATE);
    } 

    @RequestMapping(value = "/pagedSearchList/{maxRecords}/{pageNum}", method = RequestMethod.POST)
    public ApiResult pagedSearchList(@PathVariable int maxRecords, @PathVariable int pageNum, @RequestBody(required=false) Shop arg0) {
        return ApiResult.CreateSuccess(shopService.pagedSearchList(maxRecords,pageNum,arg0), STR_CONTROLLER_NAME, ApiResult.MESSAGE_TYPE.UPDATE);
    }

}
