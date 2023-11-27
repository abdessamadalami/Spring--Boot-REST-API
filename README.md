# Spring-REST

Spring framework is a very good choice for creating REST applications. Spring provides specialized annotations that make
RESTful application development easy.

### What is Rest

REST stands for the REpresentational State Transfer. It provides a mechanism for communication between applications. In
the REST architecture, the client and server are implemented independently and they do not depend on one another. REST
is language independent, so the client and server applications can use different programming languages. This gives REST
applications a lot of flexibility.

#### Java - JSON data binding:

* **_POJO_** stands for Plain Old Java Object. It is a term used to describe a Java class that adheres to simple Java
  programming principles without any specific frameworks or restrictions.
* **_JSON_** is a collection of name-value pairs, which the application processes as a string. So, instead of using HTML
  or
  JSP to send data, it is passed as a String and the application can process and render the data accordingly. JSON is
  language independent and can be used with any programming language.

transforming POJOs to JSON enables data interchange, facilitates interoperability between different systems and
technologies, and provides a standardized and lightweight format for communication and storage. It plays a crucial
role in modern application development, particularly in web-based, distributed, and data-driven systems.

#### Jackson Project:

    <dependency>
        <groupId>com.fasterxml.jackson.core</groupId>
        <artifactId>jackson-databind</artifactId>
        <version>2.12.3</version>
    </dependency>

Jackson handles the conversion between JSON and POJOs by making use of the getter and setter methods of a class. To
convert a JSON object to POJO
![](/nfs/homes/ael-oual/Downloads/Spring-REST/imges/Pojo.png)

#### @RestController

This annotation is an extension of @Controller annotation. The @RestController annotation has support for REST requests
and responses and automatically handles the data binding between the Java POJOs and JSON.

#### @GetMapping

we will build a REST service that provides basic CRUD functionality. The client sends an HTTP request to the REST
service. The dispatcher servlet handles the request and if the request has JSON data, the HttpMessageConverter converts
it to Java objects. The request is mapped to a controller which calls service layer methods. The service layer delegates
the call to repository and returns the data as POJO. The MessageConverter converts the data to JSON and it is sent back
to the client. The flow of request is shown below:
![](/nfs/homes/ael-oual/Downloads/Spring-REST/imges/restarc.png)

The @GetMapping annotation maps HTTP GET requests to controller methods. It is a shortcut for:
@RequestMapping(method = RequestMethod.GET)

* @PathVariable: Since there is a path variable in the endpoint, we need to bind it with a method parameter. The
  @PathVariable annotation binds the path variable {id} from the URL to the method parameter id. By default, both the
  names must be the same for the binding to work.
  ![](/nfs/homes/ael-oual/Downloads/Spring-REST/imges/path.png)

#### @PostMapping

* The @PostMapping annotation maps HTTP POST requests to controller methods. It is a shortcut annotation for:

      @PostMapping("/players")
      public Player addPlayer(@RequestBody Player player) {
      }
  In the above code snippet, the @RequestBody annotation binds the JSON from the request to the Player object. It
  converts JSON to POJO without us having to parse the request body. We can directly use the data in the player object
  now.

#### @PutMapping

The HTTP PUT request is used for updates. The REST client will send a PUT request to /players/{playerId} with JSON data
containing the information to be updated. The player’s ID is a path variable.

     @PutMapping("/players/{id}")
    public Player updatePlayer(@PathVariable int id, @RequestBody Player player) {
      return service.updatePlayer(id, player);
    }

the @PathVariable annotation which will extract the path variable id from the incoming request /players/{id} and bind it
with the id method parameter.

#### @PatchMapping

The PUT method updates the whole record. There may be a scenario when only one or two fields needs to be updated. In
that case, sending the whole record does not make sense. The HTTP PATCH method is used for partial updates.

Sometimes we may need to update a single field. For example, once we enter a player in our database, the field that will
most likely change is his titles count. The player entity only has a few fields and PUT can be used for update. But if
the entity is large and contains nested objects, it will have a performance impact to send the whole entity only to
update a single field.

* **Using reflection**:
  Reflection API is used to examine and modify fields, methods, and classes at runtime. It allows access to the private
  fields of a class and can be used to access the fields irrespective of their access modifiers. Spring provides the
  ReflectionUtils class for handling reflection and working with the Reflection API.