# Dockerizing the EventBidding Spring Boot App

This document explains how to build and run the application using Docker.

Prerequisites
- Docker Engine installed
- (Optional) Docker Compose

Build image

```powershell
# From repository root
docker build -t eventbidding:latest .
```

Run container

```powershell
# Provide MongoDB connection string as env var
docker run -d --name eventbidding_app -p 8080:8080 \
  -e SPRING_DATA_MONGODB_URI="mongodb+srv://..." \
  -e SPRING_DATA_MONGODB_DATABASE=eventbidding \
  eventbidding:latest
```

Using docker-compose

```powershell
# Copy .env or set variables in environment
docker compose up --build -d
```

Notes
- The Dockerfile is multi-stage: it uses Maven to build the JAR then copies it into a slim JRE image.
- For local development you can start a local MongoDB container and point the app to it via `SPRING_DATA_MONGODB_URI`.
- If you want to pass JVM options, set `JAVA_OPTS` env var when running the container.

