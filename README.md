Criando e testando containers Docker
Criar rede docker para sistema hr
docker network create hr-net
Testando perfil dev com Postgresql no Docker
docker pull postgres:12-alpine

docker run -p 5432:5432 --name hr-worker-pg12 --network hr-net -e POSTGRES_PASSWORD=1234567 -e POSTGRES_DB=db_hr_worker postgres:12-alpine

docker run -p 5432:5432 --name hr-user-pg12 --network hr-net -e POSTGRES_PASSWORD=1234567 -e POSTGRES_DB=db_hr_user postgres:12-alpine
hr-config-server
FROM openjdk:11
VOLUME /tmp
EXPOSE 8888
ADD ./target/hr-config-server-0.0.1-SNAPSHOT.jar hr-config-server.jar
ENTRYPOINT ["java","-jar","/hr-config-server.jar"]
mvnw clean package

docker build -t hr-config-server:v1 .

docker run -p 8888:8888 --name hr-config-server --network hr-net -e GITHUB_USER=acenelio -e GITHUB_PASS= hr-config-server:v1
hr-eureka-server
FROM openjdk:11
VOLUME /tmp
EXPOSE 8761
ADD ./target/hr-eureka-server-0.0.1-SNAPSHOT.jar hr-eureka-server.jar
ENTRYPOINT ["java","-jar","/hr-eureka-server.jar"]
mvnw clean package

docker build -t hr-eureka-server:v1 .

docker run -p 8761:8761 --name hr-eureka-server --network hr-net hr-eureka-server:v1
hr-worker
FROM openjdk:11
VOLUME /tmp
ADD ./target/hr-worker-0.0.1-SNAPSHOT.jar hr-worker.jar
ENTRYPOINT ["java","-jar","/hr-worker.jar"]
mvnw clean package -DskipTests

docker build -t hr-worker:v1 .

docker run -P --network hr-net hr-worker:v1
hr-user
FROM openjdk:11
VOLUME /tmp
ADD ./target/hr-user-0.0.1-SNAPSHOT.jar hr-user.jar
ENTRYPOINT ["java","-jar","/hr-user.jar"]
mvnw clean package -DskipTests

docker build -t hr-user:v1 .

docker run -P --network hr-net hr-user:v1
hr-payroll
FROM openjdk:11
VOLUME /tmp
ADD ./target/hr-payroll-0.0.1-SNAPSHOT.jar hr-payroll.jar
ENTRYPOINT ["java","-jar","/hr-payroll.jar"]
mvnw clean package -DskipTests

docker build -t hr-payroll:v1 .

docker run -P --network hr-net hr-payroll:v1
hr-oauth
FROM openjdk:11
VOLUME /tmp
ADD ./target/hr-oauth-0.0.1-SNAPSHOT.jar hr-oauth.jar
ENTRYPOINT ["java","-jar","/hr-oauth.jar"]
mvnw clean package -DskipTests

docker build -t hr-oauth:v1 .

docker run -P --network hr-net hr-oauth:v1
hr-api-gateway-zuul
FROM openjdk:11
VOLUME /tmp
EXPOSE 8765
ADD ./target/hr-api-gateway-zuul-0.0.1-SNAPSHOT.jar hr-api-gateway-zuul.jar
ENTRYPOINT ["java","-jar","/hr-api-gateway-zuul.jar"]
mvnw clean package -DskipTests

docker build -t hr-api-gateway-zuul:v1 .

docker run -p 8765:8765 --name hr-api-gateway-zuul --network hr-net hr-api-gateway-zuul:v1
Alguns comandos Docker
Criar uma rede Docker

docker network create <nome-da-rede>
Baixar imagem do Dockerhub

docker pull <nome-da-imagem:tag>
Ver imagens

docker images
Criar/rodar um container de uma imagem

docker run -p <porta-externa>:<porta-interna> --name <nome-do-container> --network <nome-da-rede> <nome-da-imagem:tag> 
Listar containers

docker ps

docker ps -a
Acompanhar logs do container em execução

docker logs -f <container-id>


Vai no git no microservice-config:

cria as seguintes variavel de ambiente:

1 - application.properties

	oauth.client.name=myappname123
	oauth.client.secret=myappsecret123
	
	jwt.secret=MY-JWT-SECRET
	

2 - hr-user-dev.properties

	spring.jpa.properties.javax.persistence.schema-generation.create-source=metadata
	spring.jpa.properties.javax.persistence.schema-generation.scripts.action=create
	spring.jpa.properties.javax.persistence.schema-generation.scripts.create-target=create.sql
	spring.jpa.properties.hibernate.hbm2ddl.delimiter=;
	
	spring.datasource.url=jdbc:postgresql://localhost:5433/db_hr_user
	spring.datasource.username=postgres
	spring.datasource.password=1234567
	
	spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
	spring.jpa.hibernate.ddl-auto=none
	
	
3 - hr-user-prod.properties

	spring.datasource.url=jdbc:postgresql://hr-user-pg12:5432/db_hr_user
	spring.datasource.username=postgres
	spring.datasource.password=1234567
	
	spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
	spring.jpa.hibernate.ddl-auto=none
	
4 - hr-user-test.properties

	# Database configuration
	spring.datasource.url=jdbc:h2:mem:testdb
	spring.datasource.username=sa
	spring.datasource.password=
	
	spring.h2.console.enabled=true
	spring.h2.console.path=/h2-console
	
5 - hr-worker-dev.properties

	spring.jpa.properties.javax.persistence.schema-generation.create-source=metadata
	spring.jpa.properties.javax.persistence.schema-generation.scripts.action=create
	spring.jpa.properties.javax.persistence.schema-generation.scripts.create-target=create.sql
	spring.jpa.properties.hibernate.hbm2ddl.delimiter=;
	
	spring.datasource.url=jdbc:postgresql://localhost:5432/db_hr_worker
	spring.datasource.username=postgres
	spring.datasource.password=1234567
	
	spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
	spring.jpa.hibernate.ddl-auto=none
	
6 - hr-worker-prod.properties

	spring.datasource.url=jdbc:postgresql://hr-worker-pg12:5432/db_hr_worker
	spring.datasource.username=postgres
	spring.datasource.password=1234567
	
	spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true
	spring.jpa.hibernate.ddl-auto=none
	
7 - hr-worker-test.properties

	# Database configuration
	spring.datasource.url=jdbc:h2:mem:testdb
	spring.datasource.username=sa
	spring.datasource.password=
	
	spring.h2.console.enabled=true
	spring.h2.console.path=/h2-console
