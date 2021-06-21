# Vaadin + Spring-Boot + GORM + Groovy + Maven sample/tutorial

A kind of replacement of the [**Grails**](https://grails.org/documentation.html) stack, but…

- keeping its nice features ([**Groovy**](https://groovy-lang.org/) syntax simplicity, [**GORM**](https://gorm.grails.org/) features) …
- … without its less one(s), namely its old-fashioned UI framework: [Groovy Server Pages (GSP)](https://gsp.grails.org/latest/guide/index.html).
  - Replaced by the (far!) better, more modern, mobile-ready and state-of-the-art one: [**Vaadin }>**](https://vaadin.com/) !



<img src="https://vaadin.com/images/trademark/PNG/VaadinLogo_RGB_500x155.png" height="80"> <img src="https://i.imgur.com/KnlveKn.png" height="80"> <img src="https://gorm.grails.org/images/gorm_logo.svg" height="80"> <img src="https://hibernate.org/images/hibernate_icon_whitebkg.svg" height="80"> <img src="https://upload.wikimedia.org/wikipedia/commons/3/36/Groovy-logo.svg" height="80">



![](https://pages.vaadin.com/hubfs/taskmob-devices5.png)



## What

This repository can be used **both** as:

- a simple and **ready-to-use sample** (using its HEAD commit, or also forking it)
- a **step-by-step tutorial** (1 commit = 1 step)


Both showing how one can **efficiently** build **modern** and mobile-ready **web applications** ([PWA](https://en.wikipedia.org/wiki/Progressive_web_application)) by combining those 2 greatnesses:

- On the UI side:
  **Vaadin**, already enabling to create, only with Java code, such web apps powering [Web Components](https://vaadin.com/components) which relies on or is usable with the very **latest and *state-of-the-art* web standards and technologies** ([Polymer](https://www.polymer-project.org/), [Angular](https://angular.io/), [React](https://reactjs.org/), [Vue](https://vuejs.org/), etc…).
  Which is not really the case of the Grails' GSP (Groovy Server Pages) framework.
- On the server-side:
  Some **bricks coming from Grails stack**:
  - First, the language on which it relies: **Groovy** and its **simplicity**, **syntax** powers, **conciseness** (far less verbose than Java in lot of circumstances), etc.
  - Then, its (Groovy-based) very powerful data persistence layer: **GORM**, which can since years now be used [**outside of the Grails stack**](http://gorm.grails.org/latest/hibernate/manual/#outsideGrails).



## Why

#### « Vaadin on Grails » ? Difficult matching

Few years ago, for some personal software development project(s), I already considered combining those 2 "worlds", and was quite helped by the **[vaadinongrails.com](http://vaadinongrails.com/)** initiative providing guide(s) dedicated to this purpose.
It dealt with *"Vaadin 7 on Grails 2"*, followed by *"Vaadin 8 on Grails 3"*.

Considering the quite fast evolution of Vaadin, those guides became **quickly outdated**. To the point that any link to guide(s) are now broken... Only remains [this guide](https://guides.grails.org/vaadin-grails/guide/index.html) on the Grails website, but not sure it's the same content/author.
There is also [this webinar](https://www.youtube.com/watch?v=qOBBllT1-wQ) still on the Vaadin's website.

Moreover, trying to make those "2 worlds" to match appeared **not straightforward and convenient**, already at that time:
One of the most blocking point was (and still is) the need, in order to be able to work with Vaadin on Grails, to create build tooling like **Gradle plugin or, more complicated, Grails profile, often custom-made** by independent developers but not as a community or directly by Vaadin.
Whereas, on the other side, Vaadin always officially delivers its associated Maven plugin as part of each release of their framework (same lifecycle, then).

#### Then, without Grails, try to keep its advantages

If we discard usage of Grails, which of its parts should be kept?

- Of course, the **Groovy** syntax and all its possibilities.
- The **GORM** framework: an ORM with nice and concise features thanks also to Groovy on which it is based.
- **Spring Boot** integration (Grails is built on top of it since version 3+)



## How

After several attempts, it then appeared that it's possible to easily build webapp which makes coexisting **Vaadin** for the UI side with the advantages of Grails **(Groovy, GORM, Spring Boot)** on the server-side, but without an actual Grails stack.

First because [**Vaadin already provides a great integration with Spring Boot**](https://vaadin.com/spring), as shown by several of its tutorials and skeletons.
Especially, the ones that are downloadable as Maven projects on [start.vaadin.com](https://start.vaadin.com/).

Then, it just remains to integrate **Groovy** and **GORM** to such skeleton project.

Here below is how…

- ### Either by building and launching this ready-to-use web-app…

	Simply by launching those **Maven** commands at root of repository, once cloned locally:

  ```bash
  mvn clean install
  mvn spring-boot:run # with '-pl :myapp-server' option starting from step #6
  ```



- ### …or following those step-by-step instructions

	Also, whichever the step, by launching the same `mvn` commands as previously mentioned.

	With, for each step, its corresponding referred **`[commit]`**.
	
	


1. #### Create sample from [start.vaadin.com](https://start.vaadin.com/) [[`66eb603`](https://github.com/cernier/vaadin-springboot-gorm-groovy-maven-sample/tree/66eb603)]

	By keeping all the default content, and just selecting in *"Settings"*:

	- as Vaadin version : the latest possible, 20 (but following adaptations/steps should be similar with an older version)
	- as Java version : the latest possible, 15 (but following adaptations/steps should be similar with an older version)
	  - NB: Java 16 is currently the latest version available on the [`start.vaadin.com`](https://start.vaadin.com/) sample generator, but as it's still not supported by Groovy tooling used later in this tutorial, this version is here downgraded just to 15.
	- ["Flow (Java only)"](https://vaadin.com/flow) as UI framework
	
	


2. #### Add Groovy version/dependency/plugin and create first `HelloCustomView.groovy`'s additional Vaadin's View [[`9d99d0c`](https://github.com/cernier/vaadin-springboot-gorm-groovy-maven-sample/commit/9d99d0c)]

	- By adding those required [changes in `pom.xml`](https://github.com/cernier/vaadin-springboot-gorm-groovy-maven-sample/commit/9d99d0c386ae37722b000bcf36937362e7dd0f0a#diff-9c5fb3d1b7e3b0f54bc5c4182965c4fe1f9023d449017cece3005d3f90e8e4d8)

	- By copying `HelloCustomView` (to the right `com.example.application.views.custom` package within `src/main/groovy` source folder to be created) from Vaadin skeleton's `HelloWorldView` (`src/main/java` source folder), with customized notification message:

		```groovy
		Notification.show("Hello Custom " + name.getValue())
		```

		- **From Java's lambda expressions to Groovy's closures**:
			Note that in this class, the only other required change is to convert this Java "Lambda expression":

			```java
			sayHello.addClickListener(e -> {
			    ...
			});
			```

			Simply to this (apparently very similar) Groovy's closure:

			```groovy
			sayHello.addClickListener({ e ->
			    ...
			})
			```
			Practically, there are lot of differences between them, but it's always possible to convert a syntax from one to the other.
		
	- By adding in `MainView` link/tab to this newly created `HelloCustomView`:
	
		```groovy
		private Component[] createMenuItems() {
		    return new Tab[]{ ... , createTab("Hello Custom", HelloCustomView.class), ...};
		}
		```




3. #### Add Spring's `@Service` and use it in view [[`d9c0caf`](https://github.com/cernier/vaadin-springboot-gorm-groovy-maven-sample/tree/d9c0caf)]

	- By defining this way an `HelloService` …

		```groovy
		@Service
		class HelloService {

		    String getHello(String name) {
		        "Hello Custom from service ${name}"
		    }
		}
		```

	- … to be used this way in the custom Vaadin view

		```groovy
		@SpringComponent
		@UIScope
		public class HelloCustomView extends HorizontalLayout {
		
		    @Autowired
		    HelloService service
		
		    public HelloCustomView() {
		
		        ...
		
		        sayHello.addClickListener({
		            Notification.show(service.getHello(name.getValue()))
		        })
		    }
		}
		```
		- [`@SpringComponent`](https://vaadin.com/api/vaadin-spring/com/vaadin/flow/spring/annotation/SpringComponent.html) annotation is a Vaadin shortcut to denote a Spring's [`@Component`](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Component.html) without confusing with Vaadin's [`Component`](https://vaadin.com/api/platform/com/vaadin/flow/component/Component.html) class.
		  - Telling Spring to consider this Vaadin view class as one of the beans it manages, enables to simply inject the Spring service `HelloService` defined just above, thanks to the Spring's "dependency injection" mechanism implied by the [`@Autowired` annotation](https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/beans/factory/annotation/Autowired.html).
		- [`@UIScope`](https://vaadin.com/api/vaadin-spring/com/vaadin/flow/spring/annotation/UIScope.html) is a custom [Spring's scope](https://docs.spring.io/spring-framework/docs/current/reference/html/core.html#beans-factory-scopes) provided by Vaadin which specifies the lifecycle of this Vaadin view considered as a Spring bean/component.
		  Unlike [`@VaadinSessionScope`](https://vaadin.com/api/vaadin-spring/com/vaadin/flow/spring/scopes/VaadinSessionScope.html) annotation which would make a view instance's lifecycle linked to a Vaadin user-session's lifecycle, `@UIScope` links it to a Vaadin [`UI`](https://vaadin.com/api/platform/com/vaadin/flow/component/UI.html) instance's lifecycle (basically a tab in the browser).
		  ([more details](https://vaadin.com/docs/flow/spring/tutorial-spring-scopes.html))
	
	


4. #### Add GORM's `@Entity` bean and use it in service up to view [[`f3b4295`](https://github.com/cernier/vaadin-springboot-gorm-groovy-maven-sample/tree/f3b4295)]

	- By adding in [`pom.xml`](https://github.com/cernier/vaadin-springboot-gorm-groovy-maven-sample/commit/f3b4295b389ea33969cec8e9e7cffb6f24e01026#diff-9c5fb3d1b7e3b0f54bc5c4182965c4fe1f9023d449017cece3005d3f90e8e4d8) latest version of [`org.grails:gorm-hibernate5-spring-boot`](https://repo.grails.org/grails/core/org/grails/gorm-hibernate5-spring-boot/) Maven artifact and its required repository.

	- By defining a simple GORM entity:

		```groovy
		import org.grails.datastore.gorm.GormEntity
		import grails.gorm.annotation.Entity
		
		@Entity
		class HelloBean implements Serializable, GormEntity<HelloBean> {
		
		    Integer helloNumber
		    String helloField
		
		    ...
		
		}
		```

	- Even if entity is not persisted, use it in Spring's `@Service` and then Vaadin's view:

		```groovy
		@Service
		class HelloService {
		
		    HelloBean getHelloBean(String name) {
		        new HelloBean(helloNumber: 42, helloField : "Hello Custom from service ${name}")
		    }
		}
		```
		```groovy
		public HelloCustomView() {
		
		    ...
		
		    sayHello.addClickListener({
		        HelloBean helloBean = service.getHelloBean(name.getValue())
		        Notification.show("${helloBean.helloNumber} : ${helloBean.helloField}")
		    })
		
		    ...
		}
		```


5. #### Add datasource's (H2) dependencies, configuration and initialization (`BootStrap` class to persist initial data) [[`383d497`](https://github.com/cernier/vaadin-springboot-gorm-groovy-maven-sample/tree/383d497)]

	- By adding in [`pom.xml`](https://github.com/cernier/vaadin-springboot-gorm-groovy-maven-sample/commit/383d4970ed5c8eec5fe9409b1f47a88cb8a513d2#diff-9c5fb3d1b7e3b0f54bc5c4182965c4fe1f9023d449017cece3005d3f90e8e4d8) persistence support (JPA) for Spring Boot (`spring-boot-starter-data-jpa`) and [H2 database access layer](https://www.h2database.com/html/build.html?highlight=maven&search=maven#maven2) (versions inherited from POM's parent).
	
	- By configuring JPA storage with [default H2 settings](https://github.com/cernier/vaadin-springboot-gorm-groovy-maven-sample/commit/383d4970ed5c8eec5fe9409b1f47a88cb8a513d2#diff-54eeffbae371fcd1398d4ca5e89a1b8118208b7bb2f8ddf55c1aa2f7d98ab136).
	
	- By moving `@SpringBootApplication`-annotated `Application` class from Java sources to Groovy ones (`src/main/java` → `src/main/groovy`)
	- And inside, trigger initialization…
		```groovy
		public class Application ... implements ApplicationRunner {
		
		    @Autowired
		    BootStrap bootStrap
		
		    ...
		
		    @Override
		    public void run(ApplicationArguments args) throws Exception {
		        bootStrap.init()
		    }
		}
		```
	
	- …of some test data persistence
		```groovy
		@Component
		class BootStrap {
		
		    @Transactional
		    public void init() {
		        new HelloBean(helloNumber: 42, helloField: 'short message').save()
		        new HelloBean(helloNumber: 13, helloField: 'loooooooooooooooooooooooong message').save()
		        new HelloBean(helloNumber: -1, helloField: 'another short message').save()
		        new HelloBean(helloNumber: 100, helloField: null).save()
		    }
		}
		```



6. #### Split into `*-model` and `*-server` submodules, due to SpringBoot's DevTools vs GORM incompatibility (classloading issue) [[`fabbe1f`](https://github.com/cernier/vaadin-springboot-gorm-groovy-maven-sample/tree/fabbe1f)]

	Due to this classloader issue (cf. [`grails-data-mapping`#970](https://github.com/grails/grails-data-mapping/issues/970)), when using conjointly GORM and SpringBoot's DevTools (and that can be reproduced by trying to launching app at the previous commit / step of the tutorial), one workaround found so far (any better one is welcome 🙂 ) is to split model classes (persisted by GORM) and rest of server code.
	
	This split between `*-model` and `*-server` submodules/artifacts (with quite important part of move from `./src` to `./server/src`) can be inspected [there](https://github.com/cernier/vaadin-springboot-gorm-groovy-maven-sample/tree/fabbe1f).



7. #### Enhance search service by using GORM's finder and data service features [[`0654f49`](https://github.com/cernier/vaadin-springboot-gorm-groovy-maven-sample/tree/0654f49)]

	- By defining `HelloGormService`:
		- as a Spring Service that can be injected into other ones
		- as a low-level [GORM's *"data service"*](http://gorm.grails.org/latest/hibernate/manual/index.html#dataServices) (hence the double `@Service` annotations)
		- using [GORM's *"data service"* queries](https://gorm.grails.org/latest/hibernate/manual/index.html#_data_service_queries) features like [`@Where` queries](https://gorm.grails.org/latest/hibernate/manual/index.html#_where_queries) in this case, but it could be ["dynamic finder queries"](https://gorm.grails.org/latest/hibernate/manual/index.html#_dynamic_finder_queries), or [JPA-QL ones](https://gorm.grails.org/latest/hibernate/manual/index.html#_jpa_ql_queries), etc…

		```groovy
		import grails.gorm.services.Service
		
		@org.springframework.stereotype.Service
		@Service(HelloBean)
		interface HelloGormService {
		
		    @Where({ helloField ==~ '%' + string + '%' })
		    List<HelloBean> getAllWithHelloFieldContaining(String string)
		}
		```
- By injecting and using this GORM/Spring service into the already existing Spring service `HelloService`:
	
	```groovy
		@Service
		class HelloService {
		
		    @Autowired
		    HelloGormService gormService
		
		    List<HelloBean> customSearchHelloBeans(String searchValue) {
		        try {
		            // if searchValue is a number, use this GORM finder
		            return HelloBean.findAllByHelloFieldIsNullOrHelloNumberGreaterThan(searchValue as Integer)
		        } catch (NumberFormatException e) {
		            // otherwise, use this GORM data service
		            return gormService.getAllWithHelloFieldContaining(searchValue)
		        }
		    }
		}
	```
	
	- Note the usage of the GORM's "dynamic finder queries" if provided argument is a number (and the GORM data service otherwise).
	
- And finally, update the UI's `HelloCustomView`  to trigger Vaadin's notification for each result according to underlying service rules (instead of just the single one of previous service):
	
	```groovy
		sayHello.addClickListener({ e ->
		    service.customSearchHelloBeans(name.getValue()).each { HelloBean helloBean ->
		        Notification.show("${helloBean.helloNumber} : ${helloBean.helloField}")
		    }
		})
	```





And then, from this skeleton, you are ready to continue developing, fully in Groovy (within `src/main/groovy` source folder of project(s) ), your nice app 🙂.
