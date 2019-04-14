# msas_newsletter

## Newsletter subscriptions

I can use Springboot for this purpouse. Another interesting enterprise architecture could be Lagom framework, could be an attractive alternative with perhaps better performance than SpringCloud ecosystem.

## Components 

### Microservices

Springboot reactive microservices with Kafka 

Due of scale and isolation requirements, each microservice will be associated to one specific task with its own logic, so there´ll be three microservices.

* Public subscription microservice: Will receive incoming newsletter web requests and store them in a kafka topic.
  * Read subscriptions from controller api public subscriptions
  * Store data to kafka topic: "nl_subscriptions"
  * @data: uuid, email, firstName*, gender*, dateOfBith, flagOfconsent, newsletterId 

* Private reactive subscription microservice database: Will read from previous topic and store data in a database (postgresql). Also send event to a new kafka topic.
  * Read subscriptions from kafka topic "nl_subscriptions" 
  * Store data to kafka topic: "nl_database"
  * @data: uuid, email, firstName*, gender*, dateOfBith, flagOfconsent, newsletterId, subscriptionId 

* Private reactive subscription microservice email: Will read from previous topic and send emails to confirm newsletter registration
  * Read subscriptions from kafka topic "nl_database" 
  * Send emails to confirm newsletter registration
  * @data: uuid, email, firstName*, gender*, dateOfBith, flagOfconsent, newsletterId, subscriptionId

### Kafka

Due of the requirements of simple logic with no processing, analysis, aggregations, windowing...And the scalability of this use case that perhaps for a newsletter registration is less than one million events per second, I won´t choose Kafka Streams to process the events. 

### Postgresql

May I use others, but surely there´ll be requirements for searching 'email' and 'newsletterid' and other fields perhaps. It could scale good with not a huge volume.


## Security:

Microservices will be deployed in Kubernetes so it will manage the security by network policies for public and private msas with control over ingress TCP traffic and port filtering. 


## Deploy

* Deployed as PODS on Kubernetes
* Charts for kubernetes

## CD/CI:

* Git
* Jenkins jobs
* Nexus
* Ansible
* Helm Charts
* Kubernetes


## Dependencies

* To take into account the kafka cluster version for msas dependencies
* The database version for msas dependencies

