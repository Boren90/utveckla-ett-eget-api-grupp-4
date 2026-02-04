# Grupp4 API

This project is built as a school project and uses Quarkus, the Supersonic Subatomic Java Framework.
The application lets the user create, update and remove cars. To be able to do this the user must first create a user. After the user is created a API key (UUID) will be generated. This is required in the header to be able to access the car resources.

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.

## Getting Started
- Java 21
- PostgreSQL database

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:

./mvnw quarkus:dev

## How to use the API
1. **Create a User:** Send a POST request to /api/user with a username and password. The response will contain your unique apiKey.
2. **Authorize:** In Postman or Swagger UI, add a Header to your requests:
   - **Key**: X-API-KEY
   - **Value:** [Your-Generated-UUID]
3. **Manage Cars:** Use the /api/cars endpoints to GET, POST, PATCH, or DELETE cars. If the header is missing or the key is invalid, you will receive a 401 Unauthorized response.

## Create User JSON format

```json
{
  "userName": "Bosse",
  "userPassword": "Password"
}
```

## Create Car JSON format
```json
{
  "carBrand": "Volvo",
  "carModel": "740",
  "year": "1992",
  "value": "€2000",
  "img": "http://dummyimage.com/160x100.png/cc0000/ffffff",
  "trivia": "This car could be bought with several different engines"
}
```

## Tech stack

- Jakarta EE
- Java 21(if you use anything older you will have depedency problems)
- Vs code
- Hibernate ORM ([guide](https://quarkus.io/guides/hibernate-orm)): Define your persistent model with Hibernate ORM and Jakarta Persistence
- Narayana JTA - Transaction manager ([guide](https://quarkus.io/guides/transaction)): JTA transaction support
- Hibernate Validator ([guide](https://quarkus.io/guides/validation)): Validate object properties (field, getter) and method parameters for your beans (REST, CDI, Jakarta Persistence)
- RESTEasy Classic JSON-B ([guide](https://quarkus.io/guides/rest-json)): JSON-B serialization support for RESTEasy Classic
- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Document your REST APIs with OpenAPI - comes with Swagger UI
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource)): Connect to the PostgreSQL database via JDBC
