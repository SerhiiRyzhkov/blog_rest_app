in process...


## Api

### Auth

| Method | Url                    | Decription       | Sample Valid Request Body | 
| ------ |------------------------|------------------|---------------------------|
| POST   | /api/auth/register     | Sign up          | [JSON](#signUp)           |
| POST   | /api/auth/authenticate | Log in           | [JSON](#signIn)           |

### Posts

| Method | Url                    | Decription    | Sample Valid Request Body | 
|--------|------------------------|---------------|---------------------------|
| GET    | /api/posts/getAllPosts | Get all posts |                           |
|        |                        |               |                           |




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
