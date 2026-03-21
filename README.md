# My Spring API

A minimal Spring Boot REST API that is ready to:

- run locally
- build into a Docker image
- deploy to Kubernetes

## Endpoints

- `GET /`
- `GET /health`
- `GET /hello/{name}`
- `POST /greet`
- `GET /actuator/health`
- `GET /actuator/health/liveness`
- `GET /actuator/health/readiness`

## Requirements

- Java 17
- Maven 3.9+
- Docker
- kubectl
- a Kubernetes cluster

## Run locally

```bash
mvn clean package
java -jar target/my-spring-api.jar
```

Test it:

```bash
curl http://localhost:8080/
curl http://localhost:8080/health
curl http://localhost:8080/hello/Seyi
curl -X POST http://localhost:8080/greet \
  -H "Content-Type: application/json" \
  -d '{"name":"Seyi"}'
```

## Build Docker image

```bash
docker build -t your-dockerhub-username/my-spring-api:1.0.0 .
```

## Push Docker image

```bash
docker login
docker push your-dockerhub-username/my-spring-api:1.0.0
```

## Deploy to Kubernetes

Update the image in `k8s/deployment.yaml`, then run:

```bash
kubectl apply -f k8s/deployment.yaml
kubectl apply -f k8s/service.yaml
```

Check the deployment:

```bash
kubectl get deployments
kubectl get pods
kubectl get svc
```

Port-forward for testing:

```bash
kubectl port-forward svc/my-spring-api-service 8080:80
```

## Optional ingress

If you already have an ingress controller such as NGINX installed:

```bash
kubectl apply -f k8s/ingress.yaml
```

## Example responses

### GET /

```json
{
  "application": "my-spring-api",
  "message": "API is running",
  "environment": "production"
}
```

### GET /health

```json
{
  "status": "ok"
}
```

### POST /greet

Request:

```json
{
  "name": "Seyi"
}
```

Response:

```json
{
  "message": "Welcome, Seyi!"
}
```
