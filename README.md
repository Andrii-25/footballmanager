# Spring Boot MySQL Football manager app

## How to install

### Using Git

1.  Clone the project from github. Change "myproject" to your project name.

```bash
https://github.com/Andrii-25/footballmanager.git ./myproject
```

### Using manual download ZIP

1.  Download repository
2.  Uncompress to your desired directory

### Go to the `resources/application.properties` file, and add the following content like it:
 ```
spring.datasource.url=your_db_url
spring.datasource.username=db_username
spring.datasource.password=db_password
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
 ```
 

## Steps to run
1. Build the project using `mvn clean install`
2. Run using `java -jar target/footballmanager-0.0.1-SNAPSHOT.jar`
3. The web application is accessible via localhost:8080

## Alternatively, you can run the app without packaging it using:
`mvn spring-boot:run`

## Routes

### GET Routes

- /api/teams - to get all teams.
- /api/teams/:id - to get one team.
- /api/players - to get all players.
- /api/players/:id - to get one player.
- /api/players/team/:teamId - to get all players that belong to some specific team by team id.

### POST Routes

- /api/teams - to create new team.
- /api/players/new/:teamName - to create a new player and add him to a certain team.
- /api/players/transfer?playerId=[playerId]&team=[teamName] - to transfer the player to another team.

### PUT Routes

- /api/teams/:id - to update specific team.
- /api/players/:id - to update specific player.

### DELETE Routes

- /api/teams/:id - to delete specific team.
- /api/players/:id - to delete specific player.
