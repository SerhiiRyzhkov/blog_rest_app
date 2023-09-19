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

### Stories

| Method | Url                    | Description         | Sample Valid Request Body  | 
|--------|------------------------|---------------------|----------------------------|
| GET    | /api/stories/          | Get all stories     |                            |
| GET    | /api/stories/{id}      | Get stories by id   |                            |
| POST   | /api/stories/          | Add new stories     | [JSON](#addStories)        |
| DELETE | /api/posts/{id}        | Delete stories      |                            |
| POST   | /api/posts/{id}/like   | Like/unlike stories |                            |
| POST   | /api/posts/{id}/repost | Repost stories      |                            |

### Tags

| Method | Url                    | Description         | Sample Valid Request Body  | 
|--------|------------------------|---------------------|----------------------------|
| GET    | /api/tags/{id}/posts   | Get posts by tag    |                            |
| GET    | /api/tags/{id}/stories | Get stories by tag  |                            |

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

##### <a id="addPost">Add new post  -> /api/posts/</a>
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

##### <a id="updatePost">Update post -> /api/posts/{id}</a>
```json
{
  "title": "My Updated Blog Post",
  "body": "This is the content of my updated blog post. It's a very interesting topic that I wanted to share with everyone!"
}
```

##### <a id="addStories">Add new stories -> /api/stories/</a>
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

