# common-services
- TO use GitHub action to push to ECR: Create Github Action secret: AWS_ACCESS_KEY_ID and AWS_SECRET_ACCESS_KEY. Value from AWS IAM > Users > <user> > Security credentials > access key
- Otherwise, to go ECR > repo > view push commands to push from local
- Create Dockerfile
- 


- Configure spring doc https://springdoc.org/v2/:
  - Gradle config: implementation 'org.springdoc:springdoc-openapi-starter-webmvc-ui:2.0.2'
  - Change default Swagger URL in application.yml
  - Visit Swagger doc at http://localhost:8080/<name in application.yml>
  - Visit API spec at http://localhost:8080/v3/api-docs
  - Json can be viewed at https://editor.swagger.io/



**DEPLOYMENT**
- For simplicity, this tutorial will not deploy the app using argocd but k8s deployment
  - Create k8s/commonservices-deployment.yaml and k8s/commonservices-service.yaml
  - ========== START Clean up ==============================
- #Delete namespace
  - kubectl delete namespace commonservices-app

- #Delete cluster
  - eksctl delete cluster --name commonservices-cluster --region ap-southeast-1

- #Delete RDS
- #Delete MSK
- #Delete MSK client

#========== END Clean up ==============================


#===== Create cluster
- eksctl create cluster --name commonservices-cluster --region ap-southeast-1 --instance-types <instance type>

#===== Create application namespace
- kubectl create namespace commonservices-app


- Create configMap and secrets, udpate with RDS credential For secreit, need to use stringData instead of data so no need to do encoding the value
  - kubectl apply -f ./k8s/env-configmap.yml
  - kubectl apply -f ./k8s/env-secrets.yml
    - Note that we need to set the namespace in configmap/secret yml files



#===== Apply deployment & service manifest, or check the argocd/apps repo
- kubectl apply -f ./k8s/commonservices-deployment.yaml
- kubectl apply -f ./k8s/commonservices-service.yaml
- Check pod status: 
  - kubectl get pod -o wide --namespace commonservices-app
- #Run shell on a pod - to replace with pod ID. Then curl the localhost:8080/<url>
  - kubectl exec -it <pod ID> -n commonservices-app -- /bin/bash
  - kubectl exec -it commonservices-linux-deployment-bb5bbbfbd-97225 -n commonservices-app -- /bin/bash




SETUP DOCKERS:
- Create docker-compose.yml. Note that the hostnames used in network created by docker will be different when running app in docker vs from IDE
- If need to run the app in docker, need to build image, and add to docker-compose.yml
  - docker build -t commonservices:latest .  
- docker-compose up -d


KAFKA - LOCAL
- Ref:
  - https://codenotfound.com/spring-kafka-consumer-producer-example.html
  - https://github.com/code-not-found/spring-kafka

- Update application.yml with Kafka config
- Run command to create topic "notification"

```
docker compose exec broker \
  kafka-topics --create \
    --topic notification \
    --bootstrap-server localhost:9092 \
    --replication-factor 1 \
    --partitions 1
```

- Create config, consumer and receiver classes in kafka package
- Create KafkaController and test




REDIS - LOCAL
- Update application.yml with Redis config. Note that redis hostname will be different when calling from IDE vs docker
- Create RedisConfig.java to read from config
- Create RedisClient to interact with Redis, and create RedisController

ELASTICSEARCH - LOCAL
- Update application.yml with Easticsearch config
- Create ElasticsearchConfig.java to read from config
- Create simple Person.java as DTO
- Create ElasticsearchClientWrapper.java to interact with Elasticsearch, and create ElasticsearchController
