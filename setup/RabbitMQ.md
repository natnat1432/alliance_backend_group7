## RabbitMQ

**RabbitMQ** is a message broker that acts as a middleman where queues are defined for different services to publish and consume messages.

### **Definition of terms**

- **Producer** – Message sender
- **Exchange** –  Responsible for routing the messages to the queues
- **Binding** –  Link between the exchange and the queue
- **Routing key** - Message attribute that the exchange looks for to route the message to its queue
- **Queue** –  Messages buffer
- **Message** - Information sent through the queue
- **Consumer** –  Receives the message through the queue

>By default, this code base contains a single ***queue*** named ```queue``` bound to the ***exchange*** named ```spring-rabbit``` bound through the ***routing key*** ```spring.rabbit.#```.

### **Configuring RabbitMQ to the code**

### 1. Install [**Erlang/OTP**](http://www.erlang.org/downloads) and [**RabbitMQ server**](https://www.rabbitmq.com/download.html) first.

Given that we have the following values in our ```app.properties```:
```properties
#RabbitMQ Settings
rabbit.Q1 = queue
rabbit.hostname = localhost
rabbit.topicExchange = spring-rabbit-topic
rabbit.topicRoutingKey = spring.rabbit.#
```

### 2. Setup the **ConnectionFactory**, **AmqpAdmin**, and the **RabbitTemplate**. These are needed to open a connection to the message broker.

```java
@Configuration
public class RabbitConfig {

    @Autowired
    private Environment env;
    
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(env.getProperty("rabbit.hostname"));
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }
}

```

### 3. Name your exchange. 
There are four type of exchanges:
- **Direct Exchange** - Routes messages with routing key that matches exactly what was        declared with the binding queue
- **Topic Exchange** - Routes messages with routing key that matches the pattern declared with the binding queue. Patterns may contain an asterisk (“*”) to match a word in a specific position of the routing key, or a hash (“#”) to match zero or more words.
- **Fanout Exchange** - Routes messages to all bound queues regardless of the routing key
- **Headers Exchange** - Instead of a routing key, it routes messages based upon a matching of message headers to the expected headers specified by the binding queue. 

An example of a Topic Exchange:

```java
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(env.getProperty("rabbit.topicExchange"));
    }
```

### 4. Declare your queue(s).
```java
    @Bean
    public Queue queue1() {
       return new Queue(env.getProperty("rabbit.Q1"));
    }
```

### 5. Bind the queue(s) to the exchange(s).
```java
    @Bean
    Binding topicBinding1(Queue queue1, TopicExchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange).with(env.getProperty("rabbit.topicRoutingKey"));
    }
```

Using the previous settings, publishing and receiving messages in the same function should be possible. Just make sure that the message object to be sent is serializable.
``` java
@Component
public class RabbitComponentSample {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Value("${rabbit.topicExchange}")
    private String exchange;    
 
    @Value("${rabbit.Q1}")
    private String queueName1;    

    public void sendThroughRabbit(MailModel input) {      
        try {
            rabbitTemplate.convertAndSend(exchange, "spring.rabbit.mail", input);
            MailModel fromRabbit = (MailModel) rabbitTemplate.receive(queueName1);
        } catch (Exception e) {
            e.printStackTrace();
        }       
    }
    
}
```

### 6. In ```RabbitConfig.java```, add the **RabbitListenerContainerFactory** and annotate the configuration class with ```@EnableRabbit``` to allow queue listeners. This will permit consuming the messages in another service.

The complete sample configuration class now looks like this:
``` java
@Configuration
@EnableRabbit
public class RabbitConfig {

    @Autowired
    private Environment env;
    
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory(env.getProperty("rabbit.hostname"));
        return connectionFactory;
    }

    @Bean
    public AmqpAdmin amqpAdmin() {
        return new RabbitAdmin(connectionFactory());
    }

    @Bean
    public RabbitTemplate rabbitTemplate() {
        return new RabbitTemplate(connectionFactory());
    }
    
    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(env.getProperty("rabbit.topicExchange"));
    }

    @Bean
    public Queue queue1() {
       return new Queue(env.getProperty("rabbit.Q1"));
    }
 
    @Bean
    Binding topicBinding1(Queue queue1, TopicExchange exchange) {
        return BindingBuilder.bind(queue1).to(exchange).with(env.getProperty("rabbit.topicRoutingKey"));
    }
    
    @Bean
    public RabbitListenerContainerFactory<?> rabbitListenerContainerFactory(){
     SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
     factory.setConnectionFactory(connectionFactory());
     return factory;
    }
    
}
```

### 7. Now we could annotate the listener method with ```@RabbitListener(queues = "${rabbit.Q1}")``` to automatically listen to our newly configured message queue. In other words, the method will execute every time a new message is published to the queue.

>More about **RabbitMQ** exchanges, binding, and queues here:
     https://spring.io/projects/spring-amqp#learn
     https://www.rabbitmq.com/tutorials/tutorial-one-spring-amqp.html
     https://www.baeldung.com/rabbitmq-spring-amqp


