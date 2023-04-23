# LLD: Shop


## Package: ```ph.com.alliance.jpa.functions.shoptest.dao```

### [ Interface ] : ***ShopTestDao.java***   
- ### ```getShopTestCount``` - *abstract method for retrieving the total number of ShopTest records* 

    **Return**:
    - ```Long``` :  number of ShopTest records

- ### ```addShopTest``` - *abstract method for inserting a record* 

   **Parameters**:
    - ```ShopTest``` : ShopTest record to insert

- ### ```updateShopTest``` - *abstract method for updating a record* 

   **Parameters**:
    - ```ShopTest``` : ShopTest record to update

- ### ```listShopTests``` - *abstract method for retrieving the list of records* 

    **Return**:
    - ```List<ShopTest>``` : list of retrieved ShopTest records

- ### ```getShopTestById``` - *abstract method for ShopTest record retrieval using the key* 

    **Return**:
    - ```ShopTest``` : ShopTest record with the specified id

    **Parameters**:
    - **```int``` id** : ShopTest ShopId to retrieve

- ### ```removeShopTest``` - *abstract method for deleting the ShopTest record with the given id * 

    **Parameters**:
    - **```int``` id** :  ShopTest ShopId to delete

### [ Class ] : ***ShopDaoImp.java***   
- ### ```getShopTestCount``` - *implementation method for ShopDao abstract method ```getShopTestCount```*
    
    **Flow/Pseudocode**    
    - get single number result of select count criteria query from ShopTest

- ### ```removeShopTest``` - *implementation method for ShopDao abstract method ```removeShopTest```*

    **Flow/Pseudocode**    
    - retrieve ShopTest of given id and remove
    
- ### ```addShopTest``` - *implementation method for ShopDao abstract method ```addShopTest```*

    **Flow/Pseudocode**    
    - persist object into entity manager

- ### ```updateShopTest``` - *implementation method for ShopDao abstract method ```updateShopTest```*

    **Flow/Pseudocode**    
    - merge updated object into entity manager

- ### ```listShopTests``` - *implementation method for ShopDao abstract method ```listShopTests```*

    **Flow/Pseudocode**    
    - get result list of select criteria query from ShopTest

---

## Package: ```ph.com.alliance.jpa.functions.shoptest.service```

### [ Interface ] : ***ShopService.java***   
- ### ```getShopTestCount``` - *abstract method for retrieving the total number of ShopTest records* 

    **Return**:
    - ```Long``` :  number of ShopTest records

- ### ```addShopTest``` - *abstract method for inserting a record* 

   **Parameters**:
    - ```Shop``` : Shop record to insert

- ### ```updateShopTest``` - *abstract method for updating a record* 

   **Parameters**:
    - ```Shop``` : Shop record to update

- ### ```listShopTests``` - *abstract method for retrieving the list of records* 

    **Return**:
    - ```List<Shop>``` : list of retrieved Shop records

- ### ```getShopTestById``` - *abstract method for ShopTest record retrieval using the key* 

    **Return**:
    - ```Shop``` : Shop record with the specified id

    **Parameters**:
    - **```int``` id** : ShopTest ShopId to retrieve

- ### ```removeShopTest``` - *abstract method for deleting the ShopTest record with the given id * 

    **Parameters**:
    - **```int``` id** :  ShopTest ShopId to delete

### [ Class ] : ***ShopServiceImp.java***   
- ### ```getShopTestCount``` - *implementation method for ShopService abstract method ```getShopTestCount```*
    
    **Flow/Pseudocode**    
    - return result of **ShopDao** ```getShopTestCount``` method

- ### ```removeShopTest``` - *implementation method for ShopService abstract method ```removeShopTest```*

    **Flow/Pseudocode**    
    - return result of **ShopDao** ```removeShopTest``` method of given id

- ### ```getShopTestById``` - *implementation method for ShopService abstract method ```getShopTestById```*

    **Flow/Pseudocode**    
    - call **ShopDao** ```getShopTestById``` method with given id to retrieve
    
- ### ```addShopTest``` - *implementation method for ShopService abstract method ```addShopTest```*

    **Flow/Pseudocode**    
    - call **ShopDao** ```addShopTest``` method with given record to insert

- ### ```updateShopTest``` - *implementation method for ShopService abstract method ```updateShopTest```*

    **Flow/Pseudocode**    
    - call **ShopDao** ```updateShopTest``` method with given record to update

- ### ```listShopTests``` - *implementation method for ShopService abstract method ```listShopTests```*

     **Flow/Pseudocode**    
    - return result list of **ShopDao** ```listShopTests``` method

---

## Package: ```ph.com.alliance.jpa.Functions.shoptest.controller```

- ### ```findById``` - *retrieve the Shop record given the id* 

    **Return**:
    - ```ApiResult``` : result of the API method

    **Parameters**:
    - **```int``` id** : ShopId of the record to be retrieved

     **Flow/Pseudocode**    

    - return the ApiResult of **ShopService** ```getShopTestById``` method

- ### ```add``` - *insert the given Shop record* 

    **Return**:
    - ```ApiResult``` : result of the API method

    **Parameters**:
    - **```Shop``` Shop** : Shop record to be inserted

     **Flow/Pseudocode**    

    - return the ApiResult of **ShopService** ```addShopTest``` method

- ### ```saveAll``` - *update the given Shop record* 

    **Return**:
    - ```ApiResult``` : result of the API method

    **Parameters**:
    - **```Shop``` Shop** : Shop record to be updated

     **Flow/Pseudocode**    

    - return the ApiResult of **ShopService** ```updateShopTest``` method

- ### ```deleteById``` - *delete the given Shop record with the given id* 

    **Return**:
    - ```ApiResult``` : result of the API method

    **Parameters**:
    - **```int``` id** : ShopId of the record to be deleted

     **Flow/Pseudocode**    

    - return the ApiResult of **ShopService** ```deleteById``` method

- ### ```findAll``` - *retrieve the Shop list* 

    **Return**:
    - ```ApiResult``` : result of the API method

     **Flow/Pseudocode**    

    - return the ApiResult of **ShopService** ```listShopTests``` method

- ### ```count``` - *retrieving the total number of Shop records* 

    **Return**:
    - ```ApiResult``` : result of the API method

     **Flow/Pseudocode**    

    - return the ApiResult of **ShopService** ```getShopTestCount``` method
