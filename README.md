<div align="center">
  <img src="src/main/webapp/img/logo.png" alt="CA3Library logo" width="110" />

  <h1>CA3Library</h1>
  <p><strong>A full-stack library management application for discovering, borrowing, and managing books.</strong></p>
  <p>Built with Java, JSP, Jakarta Servlets, JDBC, and MySQL.</p>
</div>

## Overview

CA3Library gives readers a single place to browse a catalogue, find books by title or genre, manage active loans, return books, and settle overdue fees. The application follows a command-based web architecture: a central servlet routes requests to focused command classes, while DAO interfaces keep persistence logic separate from the JSP user interface.

The project was developed collaboratively by **Mercy**, **Jeremiah**, and **Carlson** as a practical exercise in full-stack Java development, relational data modelling, authentication, and automated testing.

## Features

- Register and sign in with BCrypt-hashed passwords.
- Browse the full book catalogue.
- Search for books by title.
- Filter books by genre.
- Borrow available books with tracked due dates.
- View current and historical loans from a member profile.
- Return borrowed books.
- Calculate and pay overdue fees.
- View the ten most-borrowed books in a moving “trending” display.
- Update account and contact information.
- Persist users, books, genres, loans, and book–genre relationships in MySQL.

## Tech stack

| Layer | Technology |
| --- | --- |
| Backend | Java 11, Jakarta Servlet API 5 |
| Frontend | JSP, HTML, CSS, JavaScript, Bootstrap |
| Data | MySQL/MariaDB, JDBC |
| Security | BCrypt |
| Build | Maven, Maven Wrapper, WAR packaging |
| Testing | JUnit 5 |

## Architecture

```text
Browser / JSP views
        │
        ▼
Controller servlet
        │
        ▼
Command classes
        │
        ▼
DAO interfaces + implementations
        │
        ▼
MySQL / MariaDB
```

The central `Controller` selects a command from the request's `action` value. Commands coordinate a single user workflow—such as login, borrowing, returning, or profile updates—and call the appropriate DAO classes for persistence.

## Project structure

```text
.
├── pom.xml
├── mvnw / mvnw.cmd
└── src
    ├── main
    │   ├── java
    │   │   ├── business      # Book, user, genre, and loan domain models
    │   │   ├── commands      # Application use cases and request workflows
    │   │   ├── controller    # Central Jakarta Servlet controller
    │   │   ├── daos          # JDBC data-access interfaces and implementations
    │   │   └── sql           # Development and test database scripts
    │   └── webapp            # JSP views, styles, JavaScript, and images
    └── test
        └── java/tests        # DAO-focused JUnit test suite
```

## Getting started

### Prerequisites

- JDK 11 or newer
- MySQL or MariaDB on `localhost:3306`
- Apache Tomcat 10.x, or another Jakarta Servlet 5-compatible container
- Maven, or the included Maven Wrapper

Tomcat 10 is recommended because the application uses the `jakarta.servlet` namespace.

### 1. Clone the repository

```bash
git clone https://github.com/MercySoh/CA3Library.git
cd CA3Library
```

### 2. Create the database

Import the included development SQL script:

```bash
mysql -u root < src/main/java/sql/library.sql
```

The application currently expects:

```text
host: localhost
port: 3306
database: ca3library
username: root
password: empty
```

If your local credentials differ, update `src/main/java/daos/Dao.java`.

### 3. Build the application

macOS/Linux:

```bash
./mvnw clean package
```

Windows PowerShell:

```powershell
.\mvnw.cmd clean package
```

Maven produces the deployable WAR at:

```text
target/CA3Library-1.0-SNAPSHOT.war
```

### 4. Deploy with Tomcat

Copy the WAR into Tomcat's `webapps` directory, start Tomcat, and open:

```text
http://localhost:8080/CA3Library-1.0-SNAPSHOT/
```

If the WAR is renamed, use the new filename (without `.war`) as the URL context path.

## Demo account

The development database contains a seeded reader account:

```text
username: jerry
password: rippleMMW1$
```

The SQL scripts store only the BCrypt password hash; the plaintext password above is provided for local demonstration.

## Running tests

Create the test database first:

```bash
mysql -u root < src/main/java/sql/librarytest.sql
```

Then run the JUnit suite:

```bash
./mvnw test
```

On Windows:

```powershell
.\mvnw.cmd test
```

The DAO tests use the `ca3librarytest` database and cover users, books, genres, book–genre relationships, and loan workflows.

## Team contributions

| Contributor | Primary areas |
| --- | --- |
| Mercy | Users, authentication, BCrypt password security |
| Jeremiah | Books, genres, catalogue filtering |
| Carlson | Loans, book–genre relationships, trending books |

## Configuration notes

- Database connection defaults are defined in `src/main/java/daos/Dao.java`.
- Development and test schemas are intentionally separate.
- Overdue fees are calculated from the number of days past the due date.
- Book popularity is derived from borrowing frequency and displayed as a top-ten list.
- The application is packaged as a WAR and requires a compatible servlet container.

## Troubleshooting

### Database connection fails

Confirm MySQL/MariaDB is running, the `ca3library` database exists, and the credentials in `Dao.java` match your environment.

### The application returns 404

Check that Tomcat deployed the WAR successfully and that the URL context matches the deployed WAR filename.

### Servlet classes fail to load

Use Tomcat 10 or another Jakarta-compatible container. Tomcat 9 uses the older `javax.servlet` namespace.

### Tests fail before assertions run

Import `librarytest.sql`, verify the `ca3librarytest` database exists, and confirm the local database credentials.

## Contributors

- Mercy — [@MercySoh](https://github.com/MercySoh)
- Carlson — [@Carlson29](https://github.com/Carlson29)

## License

No license file is currently included. Add one before redistributing or reusing the project outside its intended academic context.
