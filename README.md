# Mock Server

A mock server has been created to allow clients to _see the API in action_.

## Mock Data

### Accounts

A single account has been created.  
Its id is : `751f0d5b-3119-4a75-a3e8-bb14c2e5ab78`.

### Logins / Users

The API uses HTTP Basic authentication.

All the passwords are `password` !

* The account holder is `Aowss`.  

> ctn-authentication : `Aowss-ctn`  
> login : `aowss@rogers.com-login`.

* The shared service's data manager is `Celine`.

> ctn-authentication : `Celine-ctn`  

* The two other lines on this shared service _belong_ to `Moussa` and `Iman`

> ctn-authentication : `Moussa-ctn`  
> ctn-authentication : `Iman-ctn`  

* The account contains a non-shared service that _belongs_ to `Salman`

> ctn-authentication : `Salman-ctn`  

### Settings

* The data is blocked on the line that _belongs_ to `Aowss`  

* The video is throttled on the line that _belongs_ to `Celine`  

* The video is throttled on the line that _belongs_ to `Moussa`  
* An alert is set at 1 GB on the line that _belongs_ to `Moussa`  

### Behaviour

Each time the `Get Usage Summary` ( `GET /customer/accounts/{accountId}/services/{serviceType}/{serviceId}/usage` ) is called, the usage is artificially increased to demonstrate the effect of overage.

The usage can be reset using `DELETE /customer/accounts/{accountId}/services/{serviceType}/{serviceId}/usage`.

## Run

### Command Line

The mock server can be started as follows from the `mock` directory :

> `java -jar target/usage-mock.jar`

This requires Java 8 to be installed.  

The server port is `9000`.  

The entry point URL is [`http://localhost:9000/customer/accounts/751f0d5b-3119-4a75-a3e8-bb14c2e5ab78`](http://localhost:9000/customer/accounts/751f0d5b-3119-4a75-a3e8-bb14c2e5ab78).

### Docker

The Docker build process is integrated with the Maven build process thanks to [Spotify's `dockerfile-maven` plugin](https://github.com/spotify/dockerfile-maven).

The `mvn package` command ( or the `mvn docker:build` one ) creates an image named `rogers-api/usage` tagged `latest`.

The server can therefore be started using the following command :

> `docker run -p 9001:9000 -t rogers-api/usage`

The entry point URL is [`http://localhost:9001/customer/accounts/751f0d5b-3119-4a75-a3e8-bb14c2e5ab78`](http://localhost:9001/customer/accounts/751f0d5b-3119-4a75-a3e8-bb14c2e5ab78).

Note that the above command mapped the `9000` port in the container to the `9001` port on the host.

#### Publish

Since Rogers doesn't have a [DockerHub](https://hub.docker.com/) account, the docker image is shared using Dokcer's [`save`](https://docs.docker.com/engine/reference/commandline/save/) and [`load`](https://docs.docker.com/engine/reference/commandline/load/) commands.

Once the image has been created, run the following command from the current directory :
> `docker save rogers-api/usage --output target/usage-mock.tar`

Push the image using the following command :
> `scp target/usage-mock.tar username@host:path`

In our case, we push it to the build server :
> `scp target/usage-mock.tar venom@10.19.6.158:Development`

On the build server, stop the running container, remove it, remove the image and create a new image from the saved file using the following command, from the `Development` directory :
> `docker load --input usage-mock.tar`

## To Do

* Externalize the mock data so that it can be configured by the client

* Implement the write APIs
