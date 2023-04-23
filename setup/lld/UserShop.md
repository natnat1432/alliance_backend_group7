# LLD: UserShop


## Package: ```ph.com.alliance.jpa.functions.usershop.dao```

### [ Interface ] : ***UserShopDao.java***   

- ### ```addUserShop``` - *abstract method for inserting a record* 

   **Parameters**:
    - ```int``` userId : UserTest userId of UserShop record to insert
    - ```int``` shopId : ShopTest shopId of UserShop record to insert

- ### ```listUserShops``` - *abstract method for retrieving the list of records* 

    **Return**:
    - ```List<UserShop>``` : list of retrieved UserShop records

- ### ```getUserShopById``` - *abstract method for UserShop record retrieval using the key* 

    **Return**:
    - ```UserShop``` : UserShop record with the specified id

    **Parameters**:
    - **```int``` id** : UserShop UserShopId to retrieve

- ### ```findByUserShop``` - *abstract method for UserShop record retrieval using the userId and shopId* 

    **Return**:
    - ```UserShop``` : UserShop record with the specified userId and shopId

    **Parameters**:
    - ```int``` userId : UserTest userId of UserShop record to retrieve
    - ```int``` shopId : ShopTest shopId of UserShop record to retrieve

- ### ```removeUserShop``` - *abstract method for deleting the UserShop record with the given userShopId * 

    **Parameters**:
    - **```int``` id** :  UserShop UserShopId to delete

- ### ```removeByUserShop``` - *abstract method for deleting the UserShop record with the given userId and shopId* 

   **Parameters**:
    - ```int``` userId : UserTest userId of UserShop record to delete
    - ```int``` shopId : ShopTest shopId of UserShop record to delete

### [ Class ] : ***UserShopDaoImp.java***   


- ### ```removeUserShop``` - *implementation method for UserShopDao abstract method ```removeUserShop```*

    **Flow/Pseudocode**    
    - retrieve UserShop of given id and remove

- ### ```removeByUserShop``` - *implementation method for UserShopDao abstract method ```removeByUserShop```*

    **Flow/Pseudocode**    
    - delete UserShop of given userId and shopId parameters through Native Query
    
- ### ```addUserShop``` - *implementation method for UserShopDao abstract method ```addUserShop```*

    **Flow/Pseudocode**    
    - insert UserShop of given userId and shopId parameters through Native Query

- ### ```listUserShops``` - *implementation method for UserShopDao abstract method ```listUserShops```*

    **Flow/Pseudocode**    
    - get result list of select criteria query from UserShop

- ### ```getUserShopById``` - *implementation method for UserShopDao abstract method ```getUserShopById```*

    **Flow/Pseudocode**    
    - find UserShop of given id 

- ### ```findByUserShop``` - *implementation method for UserShopDao abstract method ```findByUserShop```*

    **Flow/Pseudocode**    
    - get single UserShop result of select criteria query where userId and shopId finds a match

---

## Package: ```ph.com.alliance.jpa.functions.usershop.service```

### [ Interface ] : ***UserShopService.java***   
- ### ```addUserShop``` - *abstract method for inserting a record* 

   **Parameters**:
    - ```int``` userId : UserTest userId of UserShop record to insert
    - ```int``` shopId : ShopTest shopId of UserShop record to insert

- ### ```listUserShops``` - *abstract method for retrieving the list of records* 

    **Return**:
    - ```List<UserShop>``` : list of retrieved UserShop records

- ### ```getUserShopById``` - *abstract method for UserShop record retrieval using the key* 

    **Return**:
    - ```UserShop``` : UserShop record with the specified id

    **Parameters**:
    - **```int``` id** : UserShop UserShopId to retrieve

- ### ```findByUserShop``` - *abstract method for UserShop record retrieval using the userId and shopId* 

    **Return**:
    - ```UserShop``` : UserShop record with the specified userId and shopId

    **Parameters**:
    - ```int``` userId : UserTest userId of UserShop record to retrieve
    - ```int``` shopId : ShopTest shopId of UserShop record to retrieve

- ### ```removeUserShop``` - *abstract method for deleting the UserShop record with the given userShopId * 

    **Parameters**:
    - **```int``` id** :  UserShop UserShopId to delete

- ### ```removeByUserShop``` - *abstract method for deleting the UserShop record with the given userId and shopId* 

   **Parameters**:
    - ```int``` userId : UserTest userId of UserShop record to delete
    - ```int``` shopId : ShopTest shopId of UserShop record to delete

### [ Class ] : ***UserShopServiceImp.java***   
- ### ```removeUserShop``` - *implementation method for UserShopService abstract method ```removeUserShop```*
    
    **Flow/Pseudocode**    
    - call **UserShopDao** ```removeUserShop``` method of given id

- ### ```removeByUserShop``` - *implementation method for UserShopService abstract method ```removeByUserShop```*

    **Flow/Pseudocode**    
    - call **UserShopDao** ```removeByUserShop``` method of given userId and shopId

- ### ```getUserShopById``` - *implementation method for UserShopService abstract method ```getUserShopById```*

    **Flow/Pseudocode**    
    - call **UserShopDao** ```getUserShopById``` method with given id to retrieve

- ### ```findByUserShop``` - *implementation method for UserShopService abstract method ```findByUserShop```*

    **Flow/Pseudocode**    
    - return result of **UserShopDao** ```findByUserShop``` method of given userId and shopId
    
- ### ```addUserShop``` - *implementation method for UserShopService abstract method ```addUserShop```*

    **Flow/Pseudocode**    
    - call **UserShopDao** ```addUserShop``` method with given userId and shopId

- ### ```listUserShops``` - *implementation method for UserShopService abstract method ```listUserShops```*

     **Flow/Pseudocode**    
    - return result list of **UserShopDao** ```listUserShops``` method

---

## Package: ```ph.com.alliance.jpa.functions.usershop.controller```

- ### ```findById``` - *retrieve the UserShop record given the id* 

    **Return**:
    - ```ApiResult``` : result of the API method

    **Parameters**:
    - **```int``` id** : UserShopId of the record to be retrieved

     **Flow/Pseudocode**    

    - return the ApiResult of **UserShopService** ```getUserShopById``` method
    
- ### ```findByUserShop``` - *retrieve the UserShop record given the userId and shopId* 

    **Return**:
    - ```ApiResult``` : result of the API method

    **Parameters**:
    - **```int``` userId** : userId of the record to be retrieved
    - **```int``` shopId** : shopId of the record to be retrieved

     **Flow/Pseudocode**    

    - return the ApiResult of **UserShopService** ```findByUserShop``` method

- ### ```add``` - *insert the given UserShop record* 

    **Return**:
    - ```ApiResult``` : result of the API method

    **Parameters**:
    - **```int``` userId** : userId of the record to be inserted
    - **```int``` shopId** : shopId of the record to be inserted

     **Flow/Pseudocode**    

    - return the ApiResult of **UserShopService** ```addUserShop``` method

- ### ```deleteById``` - *delete the given UserShop record with the given id* 

    **Return**:
    - ```ApiResult``` : result of the API method

    **Parameters**:
    - **```int``` id** : UserShopId of the record to be deleted

     **Flow/Pseudocode**    

    - return the ApiResult of **UserShopService** ```deleteById``` method

- ### ```deleteByUserShop``` - *delete the given UserShop record of given userId and shopId* 

    **Return**:
    - ```ApiResult``` : result of the API method

    **Parameters**:
    - **```int``` userId** : userId of the record to be deleted
    - **```int``` shopId** : shopId of the record to be deleted

     **Flow/Pseudocode**    

    - return the ApiResult of **UserShopService** ```deleteByUserShop``` method

- ### ```findAll``` - *retrieve the UserShop list* 

    **Return**:
    - ```ApiResult``` : result of the API method

     **Flow/Pseudocode**    

    - return the ApiResult of **UserShopService** ```listUserShops``` method
