# spring boot template
利用spring boot 与spring io 实现 后台接口
实现以下功能：
* json 接口
* 保存session 到 redis

## 1. spring mvc 搭建
只需要引入`spring-boot-starter-web` 即可

## 2. 整合mybatis、tkmybatis、pagehelper
可参考[MyBatis-Spring-Boot](https://github.com/abel533/MyBatis-Spring-Boot)

### 2.1 添加spring boot stater

	<dependency>
			<groupId>org.mybatis.spring.boot</groupId>
			<artifactId>mybatis-spring-boot-starter</artifactId>
			<version>${mybatis.springboot.version}</version>
		</dependency>
		<dependency>
		    <groupId>tk.mybatis</groupId>
		    <artifactId>mapper-spring-boot-starter</artifactId>
		    <version>${mybatis.mapper.starter.version}</version>
		    <exclusions>
		    		<exclusion>
		    		<dependency>
					<groupId>org.mybatis.spring.boot</groupId>
					<artifactId>mybatis-spring-boot-starter</artifactId>
				</dependency>
		    		</exclusion>
		    </exclusions>
		</dependency>
		<dependency>
		    <groupId>com.github.pagehelper</groupId>
		    <artifactId>pagehelper-spring-boot-starter</artifactId>
		    <version>${mybatis.pagehleper.starter.version}</version>
		</dependency>
		<dependency>
            <groupId>javax.persistence</groupId>
            <artifactId>persistence-api</artifactId>
            <version>1.0</version>
        </dependency>

### 2.2 添加mybatis配置
在application.propertis中添加

	mybatis.type-aliases-package=org.gary.practice.springboot.model
	mybatis.mapper-locations=classpath:mapper/*.xml
	
	#mapper
	#mappers \u591A\u4E2A\u63A5\u53E3\u65F6\u9017\u53F7\u9694\u5F00
	mapper.mappers=org.gary.practice.springboot.util.Mapper
	mapper.not-empty=false
	mapper.identity=MYSQL
	
	#pagehelper
	pagehelper.helperDialect=mysql
	pagehelper.offsetAsPageNum=true
	pagehelper.supportMethodsArguments=true
	pagehelper.pageSizeZero=true
	pagehelper.autoRuntimeDialect=true
	pagehelper.rowBoundsWithCount=true

### 2.3 代码中添加注解

	@Configuration
	@MapperScan(basePackages= {"org.gary.practice.springboot.mapper"})
	public class MybatisConfig {
	
	}

## 3. 整合session与redis

### 3.1 添加spring boot starter

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-redis</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.session</groupId>
			<artifactId>spring-session-data-redis</artifactId>
		</dependency>

### 3.2 添加代码注解

	@Configuration
	@EnableRedisHttpSession
	public class RedisSessionConfig {
	
	}

### 3.3 添加redis链接与session的配置
在application.properties中添加 

	#session
	spring.session.store-type:redis
	spring.session.redis.namespace=SPRING_SESSION
	spring.session.redis.flush-mode=on_save
	
	#redis
	spring.redis.host=localhost
	spring.redis.port=6379

# 4. 数据库与连接池
当只用一个数据源时，只需在application.properties中添加配置

	spring.datasource.url=jdbc:mysql://127.0.0.1:3306/sample?useUnicode=true&amp;characterEncoding=utf8&amp;autoReconnect=true&amp;rewriteBatchedStatements=TRUE
	spring.datasource.driver-class-name=com.mysql.jdbc.Driver
	spring.datasource.username=eagletsoft
	spring.datasource.password=eagletsoft
	spring.datasource.platform=mysql
	# pool
	spring.datasource.hikari.minimun-idle=2
	spring.datasrouce.hikari.maximum-pool-size=10


