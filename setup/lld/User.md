# LLD: User


## Package: ```ph.com.alliance.jpa.functions.usertest.dao```

### [ Interface ] : ***UserDao.java*** extends ***CrudRepository***  

---

## Package: ```ph.com.alliance.jpa.Functions.User.service```

### [ Interface ] : ***UserService.java***   
- ### ```deleteById``` - *abstract method for deleting the User record with the given key* 

    **Parameters**:
    - **```Integer``` id** : User userId to delete

- ### ```delete``` - *abstract method for deleting the given User record* 

    **Parameters**:
    - **```User```** :  User to delete

- ### ```deleteAll``` - *abstract method for deleting all User records* 

- ### ```deleteAll```(overload) - *abstract method for deleting all User records in given parameter* 
    
    **Parameters**:
    - **```Iterable<User>```** :  Users to delete

- ### ```count``` - *abstract method for retrieving total number User records* 

    **Return**:
    - **```long``` count** :  number of User records

- ### ```existsById``` - *abstract method for checking if User record of given key exists* 

    **Return**:
    - **```boolean```** :  ```true``` if record exists, else  ```false```

    **Parameters**:
    - **```Integer``` id** :  Id of User record to check

- ### ```findAll``` - *abstract method for retrieving the list of all User records* 

    **Return**:
    - **```Iterable<User>```** :  list of all User records

- ### ```findAllById``` - *abstract method for returning the list of User records of given a list of userIds to retrieve* 

    **Return**:
    - **```Iterable<User>```** :  list of retrieved User records

    **Parameters**:
    - **```Iterable<Integer>```** :  userIds of User records to retrieve

- ### ```save``` - *abstract method for insert/update the given User record* 

    **Parameters**:
    - **```User```** :  User to insert/update

- ### ```saveAll``` - *abstract method for insert/update User records in given parameter* 
    
    **Parameters**:
    - **```Iterable<User>```** :  Users to insert/update


### [ Class ] : ***UserService.java***   

- ### ```deleteById``` - *implementation method for UserTestService abstract method ```deleteById```* 

    **Flow/Pseudocode**: 
    -  call **UserTestDao** ```deleteById``` method with given id to delete

- ### ```delete``` - *implementation method for UserTestService abstract method ```delete```* 

    **Flow/Pseudocode**:
    - call **UserTestDao** ```delete``` method with given User to delete

- ### ```deleteAll``` - *implementation method for UserTestService abstract method ```deleteAll```* 

    **Flow/Pseudocode**:
    - call **UserTestDao** ```deleteAll``` method

- ### ```deleteAll```(overload) - *implementation method for UserTestService abstract method ```deleteAll```* 
    
    **Flow/Pseudocode**:
    - call **UserTestDao** ```deleteAll``` method with give list of UserTest objects to delete

- ### ```count``` - *implementation method for UserTestService abstract method ```count``* 

    **Flow/Pseudocode**:
    - return result of **UserTestDao** ```count``` method 

- ### ```existsById``` - *implementation method for UserTestService abstract method ```existsById``* 

    **Flow/Pseudocode**:
    -  return result of **UserTestDao** ```existsById``` method given the userId

- ### ```findAll``` - *implementation method for UserTestService abstract method ```findAll``* 

    **Flow/Pseudocode**:
    - return result list of **UserTestDao** ```findAll``` method

- ### ```findAllById``` - *implementation method for UserTestService abstract method ```findAllById``* 

    **Flow/Pseudocode**:
    - return result list of **UserTestDao** ```findAllById``` method from the given list of userIds

- ### ```save``` - *implementation method for UserTestService abstract method ```save``* 

    **Flow/Pseudocode**:
    - call **UserTestDao** ```save``` method given the record to insert/update

- ### ```saveAll``` - *implementation method for UserTestService abstract method ```saveAll``* 
    
    **Flow/Pseudocode**:
    - call **UserTestDao** ```saveAll``` method given the list of records to insert/update

---

## Package: ```ph.com.alliance.jpa.Functions.User.controller```

- ### ```deleteById``` - *delete User record with the given key* 

    **Return**:
    - ```ApiResult``` : result of the API method

    **Parameters**:
    - **```Integer``` id** : User userId to delete
    
    **Flow/Pseudocode**    
    - return the ApiResult of **UserTestService** ```deleteById``` method

- ### ```delete``` - *delete the given User record* 

    **Return**:
    - ```ApiResult``` : result of the API method    

    **Parameters**:
    - **```User```** :  User to delete

    **Flow/Pseudocode**    
    - return the ApiResult of **UserTestService** ```delete``` method

- ### ```deleteAll``` - *delete all User records* 

    **Return**:
    - ```ApiResult``` : result of the API method

    **Flow/Pseudocode**    
    - return the ApiResult of **UserTestService** ```deleteAll``` method

- ### ```deleteAll```(overload) - *deletie all User records in given parameter* 

    **Return**:
    - ```ApiResult``` : result of the API method
    
    **Parameters**:
    - **```Iterable<User>```** :  Users to delete

    **Flow/Pseudocode**    
    - return the ApiResult of **UserTestService** ```deleteAll``` method

- ### ```count``` - *retrieve total number User records* 

    **Return**:
    - ```ApiResult``` : result of the API method

    **Flow/Pseudocode**    
    - return the ApiResult of **UserTestService** ```count``` method

- ### ```existsById``` - *check if User record of given key exists* 

    **Return**:
    - ```ApiResult``` : result of the API method

    **Parameters**:
    - **```Integer``` id** :  Id of User record to check

    **Flow/Pseudocode**    
    - return the ApiResult of **UserTestService** ```existsById``` method

- ### ```findAll``` - *retrieve the list of all User records* 

    **Return**:
    - ```ApiResult``` : result of the API method

    **Flow/Pseudocode**    
    - return the ApiResult of **UserTestService** ```findAll``` method

- ### ```findAllById``` - *return the list of User records of given a list of userIds to retrieve* 

    **Return**:
    - ```ApiResult``` : result of the API method

    **Parameters**:
    - **```Iterable<Integer>```** :  userIds of User records to retrieve

    **Flow/Pseudocode**    
    - return the ApiResult of **UserTestService** ```findAllById``` method

- ### ```save``` - *insert/update the given User record* 

    **Return**:
    - ```ApiResult``` : result of the API method

    **Parameters**:
    - **```User```** :  User to insert/update

    **Flow/Pseudocode**    
    - return the ApiResult of **UserTestService** ```save``` method

- ### ```saveAll``` - insert/update User records in given parameter* 

    **Return**:
    - ```ApiResult``` : result of the API method
    
    **Parameters**:
    - **```Iterable<User>```** :  Users to insert/update

    **Flow/Pseudocode**    
    - return the ApiResult of **UserTestService** ```saveAll``` method

