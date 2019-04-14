# nl_msa_database

*microservice*  to manage db subscriptions

## How to build

> You need [Gradle](https://gradle.org/) in order to build the project (or use included gradle script)

1. Clone
2. Execute `gradle build` or `./gradlew build`

## How to create and run Docker image

1. Execute `gradle docker` or `./gradlew docker` to build the image
2. Check the new image using `docker images`
3. Execute `docker run -d --publish {PUBLIC_PORT}:8080 -e "SPRING_PROFILES_ACTIVE={ENVIRONMENT}" --name nl_msa_database com.jene.newsletter.microservices/nl_msa_database` to run the container