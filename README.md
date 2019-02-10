# User Registration

This micro service handles, as its name implies, user registration.  
It is inspired by http://www.baeldung.com/spring-security-registration.

## Pre-requisites

* [Maven](https://maven.apache.org)

The artifact is built using [Maven](https://maven.apache.org).  

If you want to use the provided [`.mvn` folder](./.mvn) to specify project-specific settings, you need version `3.3.1` or above.

* [Java](https://www.oracle.com/technetwork/java/javase/overview/index.html) 8 or above

## Build


## Run

### Command Line

The service can be started as follows from the `user-registration` directory :

> `java -jar target/user-registration.jar`

This requires Java 8 to be installed.  

The server port is `9000`.  

### Docker

The Docker build process is integrated with the Maven build process thanks to [Spotify's `dockerfile-maven` plugin](https://github.com/spotify/dockerfile-maven).

The `mvn package` command ( or the `mvn docker:build` one ) creates an image named `aowss-api/user-registration` tagged `latest`.

The server can therefore be started using the following command :

> `docker run -p 9001:9000 -t aowss-api/user-registration`

Note that the above command mapped the `9000` port in the container to the `9001` port on the host.

#### Publish

For now, the docker image is shared using Dokcer's [`save`](https://docs.docker.com/engine/reference/commandline/save/) and [`load`](https://docs.docker.com/engine/reference/commandline/load/) commands.

Once the image has been created, run the following command from the current directory :
> `docker save aowss-api/user-registration --output target/user-registration.tar`

Push the image using the following command :
> `scp target/user-registration.tar username@host:path`

On the build server, stop the running container, remove it, remove the image and create a new image from the saved file using the following command, from the `Development` directory :
> `docker load --input user-registration.tar`

## Documentation

The Design Documentation is generated from the [`Design.adoc`](./Design.adoc) file using [Asciidoctor](http://asciidoctor.org/)  with the [Diagram Extension](http://asciidoctor.org/docs/asciidoctor-diagram/) :

> `asciidoctor -r asciidoctor-diagram Design.adoc`
