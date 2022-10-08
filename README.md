
# Spring Boot Login and Registration example using MySQL databases, password encryption and email verification

This application was made while learning Spring Boot backend development.

Application uses MySQL, BCrypt password encryption and Spring Boot email sender.

If any mistakes were made please send a Pull Request.
## API Reference

#### Get by Id

```http
  GET /user/{id}
```

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `id` | `int` | **Required**. Your id |

#### Register a new User

```http
  POST /registration
```

| Parameter  | Type     | Description                        |
| :--------  | :------- | :--------------------------------- |
| `name`     | `string` | **Required**. name of the User     |
| `email`    | `string` | **Required**. email of the User    |
| `password` | `string` | **Required**. password of the User |


#### Additional APIs

```http
  GET /registration/verify
```

This API will be used when you will receive the verification email to activate the new User.





## Authors

- [@confusedGustas](https://github.com/confusedGustas)

