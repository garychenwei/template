# spring boot config
可分为独立动行与tomcat等容器运行。

# 1. 配置maven 
配合[spring io](https://docs.spring.io/platform/docs/Athens-SR6/reference/htmlsingle/) 使用可以减少版本控制
具体步骤如下：
* 1.1 在pom文件中引入spring io，以它做为parent
	
	<parent>
		<groupId>io.spring.platform</groupId>
		<artifactId>platform-bom</artifactId>
		<version>Athens-SR6</version>
		<relativePath />
	</parent>
	
* 1.2 按不同的作用添加不同dependency

	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-web</artifactId>
		<!-- use log4j2 -->
		<exclusions>
			<exclusion>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-starter-logging</artifactId>
			</exclusion>
		</exclusions>
	</dependency>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-log4j2</artifactId>
	</dependency>

* 1.3 添加 [spring boot maven plugin](https://docs.spring.io/spring-boot/docs/1.5.7.RELEASE/maven-plugin/)

	<plugin>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-maven-plugin</artifactId>
		<configuration>
			<mainClass>org.gary.practice.springboot.Application</mainClass>
			<!--main class是主启动的class-->
			<layout>WAR</layout>
			<!--WAR 为打包成WAR，JAR-->
		</configuration>
	</plugin>

# 2. 配置profile 
使用maven的profile
## 2.1 改变值
跟据以变量形式，做为资源文件配置。build时再跟据不同的profile读取不同的properties，替换这些变量。

可参考网上资源[Maven根据Profile读取不同配置环境配置文件](http://blog.csdn.net/xiaojiesu/article/details/51866303)
## 2.2 使用不同文件夹文件
以文件夹形式分开不同环境的资源文件。构建时跟据profile打包不同的资源文件。

可参考网上资源[用maven命令打不同配置](https://my.oschina.net/u/1455908/blog/398237)

# 3. 配置spring boot
## 3.1 创建启动类

	@SpringBootApplication
	public class Application {
		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}
	}
`@SpringBootApplication` 会自使用自动配置。spring boot在代码中做了大量默认配置。
### 3.1.1 这时需要介绍一下`@ConditionalOnMissingBean` 
spring boot的默认配置都使用了它，当没有同名的bean时它就会生效。
例如我们需要有自己的datasource

	@Bean
	public DataSource dataSrouce() {
		......
		return null;
	}
	
## 3.2 添加其它功能
例如要启动spring session保存到redis中。代码配置只需要添加

	@Configuration
	@EnableRedisHttpSession
	public class RedisSessionConfig {
	
	}

## 3.3 更改application.properties 或application.yml
spring boot 默认加载(application.properties/application.yml)。

存放数据库链接与一些必要配置。具体可参考[spring](https://docs.spring.io/spring-boot/docs/1.4.7.RELEASE/reference/htmlsingle/#common-application-properties) 

## 3.4 添加更多控制
spring boot以代码型式代替了web.xml，如有需要进行更多的配置。可以继承`WebMvcConfigurerAdapter`与`SpringBootServletInitializer`

## 3.5 兼容xml与代码配置
使用`ImportResource`引入xml配置

	@SpringBootApplication
	@ImportResource("classpath:cons-injec.xml")
	public class Application {
	
		public static void main(String[] args) {
			SpringApplication.run(Application.class, args);
		}
	
	}
