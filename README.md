# Springboot + JWT + Postgresql

This app uses spring boot, JWT and postgresql. In the root you'll find a postman collection that will help you with testing the app.

## Install instructions
1.- First download source code

```
git clone https://github.com/marvega/marvega-springboot-jwt.git
```

2.- Compile the app. Just skip the tests as there are no db yet and the test will fail
```
./mvnw clean install -DskipTests
```

3.- Run docker container

```
docker-compose up
```

4.- Use postman collection to test.

## Postman
Import the following postman collection

```
Tenpo.postman_collection.json
```

Requests
> Signup: Used for signing up in the app
>
> Signin: Used for login in the app
>
> Multiplicar: Multiply 2 numbers
>
> LogEntry: To see the entire endpoints log

For further request details, please refer to the postman collection.
