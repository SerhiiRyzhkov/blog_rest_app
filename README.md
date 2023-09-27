# BLOG REST APPLICATION

This project is a Java-based Spring REST API application designed to mimic the functionality of a social media platform like Instagram. It utilizes various technologies including Spring Framework, PostgreSQL for data storage, JWT for authentication, and JPA for data persistence. The primary goal is to provide a backend for a social media platform.

## Table of Contents

- [Technologies](#technologies)
- [Setup](#setup)
- [API](#api)



## Technologies <a id="technologies"></a>
- **Spring Framework**: Utilizing Spring Boot for building RESTful APIs. Spring provides a powerful and flexible framework for creating Java-based web applications.
- **PostgreSQL**: PostgreSQL is used for robust and scalable data storage. It's a popular open-source relational database.
- **JWT (JSON Web Tokens)**: JWT is used for secure user authentication. It's a standard for securing web applications and APIs.
- **JPA (Java Persistence API)**: JPA is used for object-relational mapping, simplifying database interaction in Java applications.
- **Spring Security**: Spring Security is used for managing security features, including user authentication and authorization.
- **Docker**: Docker is used for containerization and deployment of the application. It allows for easy packaging and distribution of applications and their dependencies.

## Setup <a id="setup"></a>

Follow these steps to set up and run the application:

1. **Clone the application repository**:
   ```bash
   git clone https://github.com/SerhiiRyzhkov/blog_rest_app.git
   
2. **Create a PostgreSQL database**:
   ```sql
   create database blog_app
3. **Configure properties file**:
   Open src/main/resources/application.yml and configure it according to your database
4. Run the app using Maven
   ```bash
   mvn spring-boot:run

The app will start running at http://localhost:8080


## Api <a id="api"> </a>

### Auth

| Method | Url                           | Description                   | Sample Valid Request Body  | 
|--------|-------------------------------|-------------------------------|----------------------------|
| POST   | /api/auth/register            | Sign up                       | [JSON](#signUp)            |
| POST   | /api/auth/authenticate        | Log in                        | [JSON](#signIn)            |

### Posts

| Method | Url                           | Description                   | Sample Valid Request Body  | 
|--------|-------------------------------|-------------------------------|----------------------------|
| GET    | /api/posts/                   | Get all posts                 |                            |
| GET    | /api/posts/{id}               | Get a post by id              |                            |
| POST   | /api/posts/                   | Add a new post                | [JSON](#addPost)           |
| PUT    | /api/posts/{id}               | Update a post                 | [JSON](#updatePost)        |
| DELETE | /api/posts/{id}               | Delete a post                 |                            |
| POST   | /api/posts/{id}/like          | Like/unlike a post            |                            |
| POST   | /api/posts/{id}/repost        | Repost a post                 |                            |

### Stories

| Method | Url                           | Description                   | Sample Valid Request Body  | 
|--------|-------------------------------|-------------------------------|----------------------------|
| GET    | /api/stories/                 | Get all stories               |                            |
| GET    | /api/stories/{id}             | Get a stories by id           |                            |
| POST   | /api/stories/                 | Add a new stories             | [JSON](#addStories)        |
| DELETE | /api/posts/{id}               | Delete stories                |                            |
| POST   | /api/posts/{id}/like          | Like/unlike a stories         |                            |
| POST   | /api/posts/{id}/repost        | Repost a stories              |                            |

### Tags

| Method | Url                           | Description                   | Sample Valid Request Body  | 
|--------|-------------------------------|-------------------------------|----------------------------|
| GET    | /api/tags/{id}/posts          | Get posts by tag              |                            |
| GET    | /api/tags/{id}/stories        | Get stories by tag            |                            |

### Comments

| Method | Url                           | Description                   | Sample Valid Request Body   | 
|--------|-------------------------------|-------------------------------|-----------------------------|
| GET    | /api/comments/{id}/post       | Get comments by post Id       |                             |
| GET    | /api/comments/{id}            | Get a comment by comment Id   |                             |
| POST   | /api/comments/{id}/post       | Add a new comment to post     | [JSON](#addComment)         |
| PUT    | /api/comments/{id}            | Update a comment by Id        | [JSON](#updateComment)      |
| DELETE | /api/comments/{id}            | Delete a comment by Id        |                             |

### Users

| Method | Url                              | Description                       | Sample Valid Request Body | 
|--------|----------------------------------|-----------------------------------|---------------------------|
| GET    | /api/users/{username}/posts      | Get posts published by user       |                           |
| GET    | /api/users/{username}/stories    | Get stories published by user     |                           |
| POST   | /api/users/                      | Add a new user (Only admins)      | [JSON](#addUser)          |
| PUT    | /api/users/{username}	           | Update user (Only admins)         | [JSON](#updateUser)       |
| DELETE | /api/users/{username}            | Delete a user (Only admins)       |                           |
| PUT    | /api/users/{username}/admin	     | Assign as admin (Only admins)     |                           |
| PUT    | /api/users/{username}/moderator	 | Assign as moderator (Only admins) |                           |
| PUT    | /api/users/{username}/user	      | Assign as user (Only admins)      |                           |

##  JSON Request Bodies examples

##### <a id="signUp">Sign Up -> /api/auth/register</a>
```json
{
  "firstname": "Serhii",
  "lastname": "Ryzhkov",
  "email": "had0uken@ukr.net",
  "password": "123123"
}
```

##### <a id="signIn">Sign In -> /api/auth/authenticate</a>
```json
{
  "email": "had0uken@ukr.net",
  "password": "123123"
}
```

##### <a id="addPost">Add a new post  -> /api/posts/</a>
```json
{
  "title": "New Post Title",
  "body": "This is the body of the new post",
  "mediafiles": [
    {
      "URL": "https://google.com/photo1.jpg",
      "fileName": "photo1.jpg",
      "size": 2048.0,
      "mediatype": "JPEG"
    },
    {
      "URL": "https://google.com/photo2.jpg",
      "fileName": "photo2.jpg",
      "size": 1024.0,
      "mediatype": "JPEG"
    },
    {
      "URL": "https://google.com/photo3.png",
      "fileName": "photo3.png",
      "size": 512.0,
      "mediatype": "PNG"
    }
  ]
}
```

##### <a id="updatePost">Update a post -> /api/posts/{id}</a>
```json
{
  "title": "My Updated Blog Post",
  "body": "This is the content of my updated blog post. It's a very interesting topic that I wanted to share with everyone!"
}
```

##### <a id="addStories">Add a new stories -> /api/stories/</a>
```json
{
  "mediafile":
  {
    "URL": "https://google.com/photo1.jpg",
    "fileName": "photo1.jpg",
    "size": 2048.0,
    "mediatype": "JPEG"
  }
}
```

##### <a id="addComment">Add a new comment -> /api/comments/{id}/post</a>
```json
{
  "text": "Awesome! I like this post!!!"
}
```

##### <a id="updateComment">Add new comment -> /api/comments/{id}</a>
```json
{
  "text": "Hi, my post was updated"
}
```

##### <a id="addUser">Add a new user -> /api/users/</a>
```json
{
  "firstname": "Serhii",
  "lastname": "Ryzhkov",
  "email": "had0uken2@ukr.net",
  "password": "999000"
}
```

##### <a id="updateUser">Update a user -> /api/users/{username}</a>
```json
{
  "password": "newPassword123QWERTY",
  "role": "MODERATOR"
}
```

