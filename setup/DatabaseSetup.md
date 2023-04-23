# Database setup
 
Prepare the database for the project. Run these scripts in the following order:  
1. [sample_tables.sql](/setup/scripts/sample_tables.sql)
2. [sample_values.sql](/setup/scripts/sample_values.sql)
  
By default, the configuration uses MySQL. If you are using another database, install the appropriate database driver and change project's ```src/main/java/META_INF/persistence.xml``` to match your database connection settings. 

* __hibernate.dialect__ – Database dialect to generate the appropriate SQL statements. E.g., 
 ***MySQL*** : ```org.hibernate.dialect.MySQLDialect```, 
 **Oracle** : ```org.hibernate.dialect.OracleDialect```,
 **Postgre** : ```org.hibernate.dialect.PostgreSQLDialect```,
 **Microsoft SQL Server** : ```org.hibernate.dialect.SQLServerDialect```
* __hibernate.connection.driver_class__ – Driver class to be used with the connection. E.g., 
 ***MySQL*** : ```com.mysql.jdbc.Driver```, 
 **Oracle** : ```oracle.jdbc.driver.OracleDriver```,
 **Postgre** : ```org.postgresql.Driver```,
 **Microsoft SQL Server** : ```com.microsoft.sqlserver.jdbc.SQLServerDriver```
* __hibernate.connection.url__ – Database connection string. Usually, ```jdbc:<dbType>://<server>:<port>/<dbName>```
* __hibernate.connection.username__ – Database username
* __hibernate.connection.password__ – Database password

The code block below shows the default hibernate properties.

```xml
  <properties>
      <property name="hibernate.show_sql" value="true" />
      <property name="hibernate.hbm2ddl.auto" value="update" />
      <property name="hibernate.dialect" value="org.hibernate.dialect.MySQLDialect" />
      <property name="hibernate.connection.driver_class" value="com.mysql.cj.jdbc.Driver" />
      <property name="hibernate.connection.url" value="jdbc:mysql://localhost:3306/db" />
      <property name="hibernate.connection.username" value="admin" />
      <property name="hibernate.connection.password" value="admin" />      
      <property name="hibernate.connection.provider_class" value="com.zaxxer.hikari.hibernate.HikariConnectionProvider" />
  </properties>
```

----
## What is JPA
**Java Persistence API** specification lets you define which objects should be persisted, and how those objects should be persisted in Java applications. It maps object-oriented domain entities to relational database tables and properties of those entities to the rows in the table.

## JPA and Hibernate
**Hibernate ORM** is one of the most mature JPA implementations, and still a popular option for ORM in Java since JPA's object-relational mapping model was originally based on it. Hibernate enables developers to more easily write applications whose data outlives the application process. As an Object/Relational Mapping framework, Hibernate is concerned with data persistence as it applies to relational databases.

----
## Generating Entities from the Database

### 1. Make sure that the project includes JPA as one of its Project Facets.
  - Right click the project name on the the **Project Explorer** and choose **Properties**.
  - In **Project Facets** check **JPA**, select the appropriate runtime environment and click **Apply**, then **OK**.
  - **JPA Tools** should be added to the context menu of the project name.
### 2. Generate the Custom Entites.
  - Select **JPA Tools** on the context menu.
  - Create a connection profile using the same specifications from ```persistence.xml``` and connect.
  - Choose the tables from which JPA models are to be generated from, and click **Next**.
  - Identify the table associations for the models, and click **Next**.
  - Specify ```ph.com.alliance.jpa.entity``` for the package, and click **Finish**.
  - Java entities with mappings specified from the table associations should be generated in ```ph.com.alliance.jpa.entity``` package.

An example of generated entity ```ShopTest.java``` is shown below.

 ```java
 @Entity
@Table(name="shop_test")
@NamedQuery(name="ShopTest.findAll", query="SELECT s FROM ShopTest s")
public class ShopTest implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int shopId;

	private String address;

	private String shopName;

	//bi-directional many-to-one association to UserShop
	@OneToMany(mappedBy="shopTest")
	private List<UserShop> userShops;

	//bi-directional many-to-many association to UserTest
	@ManyToMany
	@JoinTable(
		name="user_shop"
		, joinColumns={
			@JoinColumn(name="shopId")
			}
		, inverseJoinColumns={
			@JoinColumn(name="userId")
			}
		)
	private List<UserTest> userTests;

	public ShopTest() {
	}

	public int getShopId() {
		return this.shopId;
	}

	public void setShopId(int shopId) {
		this.shopId = shopId;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getShopName() {
		return this.shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public List<UserShop> getUserShops() {
		return this.userShops;
	}

	public void setUserShops(List<UserShop> userShops) {
		this.userShops = userShops;
	}

	public UserShop addUserShop(UserShop userShop) {
		getUserShops().add(userShop);
		userShop.setShopTest(this);

		return userShop;
	}

	public UserShop removeUserShop(UserShop userShop) {
		getUserShops().remove(userShop);
		userShop.setShopTest(null);

		return userShop;
	}

	public List<UserTest> getUserTests() {
		return this.userTests;
	}

	public void setUserTests(List<UserTest> userTests) {
		this.userTests = userTests;
	}

}
```

More information about JPA Entities is found [here](https://javaee.github.io/tutorial/persistence-intro002.html).