in process...


## Api

### Auth

| Method | Url                    | Description | Sample Valid Request Body | 
|--------|------------------------|-------------|---------------------------|
| POST   | /api/auth/register     | Sign up     | [JSON](#signUp)           |
| POST   | /api/auth/authenticate | Log in      | [JSON](#signIn)           |

### Posts

| Method | Url                    | Description      | Sample Valid Request Body | 
|--------|------------------------|------------------|---------------------------|
| GET    | /api/posts/            | Get all posts    |                           |
| GET    | /api/posts/{id}        | Get post by id   |                           |
| POST   | /api/posts/            | Add new post     | [JSON](#addPost)          |
| PUT    | /api/posts/{id}        | Update post      | [JSON](#updatePost)       |
| DELETE | /api/posts/{id}        | Delete post      |                           |
| POST   | /api/posts/{id}/like   | Like/unlike post |                           |
| POST   | /api/posts/{id}/repost | Repost post      |                           |

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

##### <a id="signIn">Log In -> /api/auth/authenticate</a>
```json
{
  "email": "had0uken@ukr.net",
  "password": "123123"
}
```

##### <a id="addPost">Log In -> /api/posts/</a>
```json
{
  "title": "My New Blog Post",
  "body": "This is the content of my new blog post. It's a very interesting topic that I wanted to share with everyone!"
}
```

##### <a id="updatePost">Log In -> /api/posts/</a>
```json
{
  "title": "My Updated Blog Post",
  "body": "This is the content of my updated blog post. It's a very interesting topic that I wanted to share with everyone!"
}
```


