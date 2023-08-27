in process...


## Api

### Auth

| Method | Url                    | Decription       | Sample Valid Request Body | 
| ------ |------------------------|------------------|---------------------------|
| POST   | /api/auth/register     | Sign up          | [JSON](#signUp)           |
| POST   | /api/auth/authenticate | Log in           | [JSON](#signIn)           |

### Posts

| Method | Url             | Decription     | Sample Valid Request Body | 
|--------|-----------------|----------------|---------------------------|
| GET    | /api/posts/     | Get all posts  |                           |
| GET    | /api/posts/{id} | Get post by id |                           |
| POST   | /api/posts/     | Add new post   | [JSON](#addNewPost)       |
| PUT    | /api/posts/{id} | Update post    | [JSON](#addNewPost)       |



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

##### <a id="addNewPost">Log In -> /api/posts/</a>
```json
{
  "title": "My New Blog Post",
  "body": "This is the content of my new blog post. It's a very interesting topic that I wanted to share with everyone!"
}
```

