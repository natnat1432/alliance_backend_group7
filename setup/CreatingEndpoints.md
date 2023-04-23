## Creating API Endpoints

*Refer [here](lld/FolderStructure.md) for the folder structure.*

### 1. Generate the JPA Entities. See **Generating Entities and Mappers** from [Database Setup](/setup/DatabaseSetup.md)

### 2. Create a folder for the module under ```src\main\java\ph\com\alliance\jpa\functions```.

### 3. Create the DAO.
- Create a ```dao``` folder. Start with creating a DAO interface, optionally extending one of Spring Data JPA repositories.

> Working with Spring Data JPA repositories need no implementation classes. Spring provides the functionalities for the repository for you. Read more [here](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/).

- Create a concrete class to implement the interface and annotate the entity manager with **@PersistenceContext** and use it to execute changes in the database. Annotate the class with **@Repository** so Spring can recognize it as a DAO component.

```java

- ph/com/alliance/jpa/functions/module1/dao/ISampleDAO.java
public interface ISampleDAO {
	List<Record> doThis(Record example);
}

- ph/com/alliance/jpa/functions/module1/dao/SampleDAO.java
@Repository
public class SampleDAO implements ISampleDAO{

	@PersistenceContext
    private EntityManager emf;
	
	public List<Record> doThis(Record example){
        emf.methodName(example);
    }
}

````

### 4. Create the Service.

- Do the same as the DAO. In a ```service``` folder, create the interface and implement it. 
Autowire the repository and annotate the class with **@Service**. But this time, the methods will include the logic to be operated with the DAO.


```java

- ph/com/alliance/jpa/functions/module1/service/ISampleService.java
public interface SampleService {
	List<Record> doSomething();
}

- ph/com/alliance/jpa/functions/module1/service/SampleService.java
@Service
public class SampleService implements ISampleService{

	@Autowired
    private ISampleDAO dao;
		
	public List<Record> doSomething(){
        RecordExample obj = new RecordExample();        
        List<Record> res = dao.doThis(obj);
		//do something 
    }
}

````

### 5. Create the Controller.
- Create a class in a ```controller``` folder with **@RestController** and controller **@RequestMapping**
annotations. Autowire the service and create the methods to return results to the client. Apply **@RequestMapping** to them and make sure each mapping is unique.
> Use ```ApiResult.java``` class to contain the request results.


```java

- ph/com/alliance/jpa/functions/module1/controller/SampleController.java

@RestController
@RequestMapping("/sample")
public class SampleService implements ISampleService{

	@Autowired
    private ISampleService sampleService;

	@RequestMapping(value = "/doSomething", method = RequestMethod.GET)	
	public ApiResult doSomething(){
    	try{
			return ApiResult.CreateSuccess(sampleService.doSomething());
		}catch(Exception e){
			return ApiResult.CreateError(e.toString());
		}
    }
}

````

### 6. Build. Run. [Test](/setup/SwaggerUI.md).

