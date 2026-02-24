# Social Network API

## Description

Spring Boot REST application for managing social network posts.

The project is created as part of laboratory work and follows a layered architecture with Checkstyle validation.

## Features (Lab 1)

* REST API for `Post` entity
* GET by ID (using `@PathVariable`)
* GET by author (using `@RequestParam`)
* DTO + Mapper between entity and API response
* Layered architecture: Controller → Service → Repository
* In‑memory storage with sample data loader
* Code style validation with Checkstyle

## How to run

```bash
./mvnw spring-boot:run
```

The application will start on `http://localhost:8080`.

## API Endpoints

| Method | URL                          | Description                         |
|--------|------------------------------|-------------------------------------|
| GET    | `/api/posts/{id}`            | Retrieve a post by its ID           |
| GET    | `/api/posts`                 | Get all posts (no `author` param)   |
| GET    | `/api/posts?author={name}`   | Get posts written by a specific author |

### Example requests

```bash
# Get post with ID 1
curl http://localhost:8080/api/posts/1

# Get all posts by author "john"
curl "http://localhost:8080/api/posts?author=john"

# Get all posts (no filter)
curl http://localhost:8080/api/posts
```

## Future work

* Add `POST`, `PUT`, `DELETE` endpoints
* Persistent database (PostgreSQL / MySQL)
* Relationships (comments, likes)
* User authentication
* Pagination and sorting
* Caching
* Containerization with Docker
