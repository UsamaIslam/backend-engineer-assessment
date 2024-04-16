# Getting Started

**IMPORTANT: Do not send pull requests to this repository. This is a template repository and is not used for grading. Any pull requests will be closed and ignored.**

## Introduction

If you are reading this, you are probably have received this project as a coding challenge. Please read the instructions
carefully and follow the steps below to get started.

## Setup

### Pre-requisities

To run the application you would require:

- [Java](https://www.azul.com/downloads/#zulu)
- [Temporal](https://docs.temporal.io/cli#install)
- [Docker](https://docs.docker.com/get-docker/)
- [Stripe API Keys](https://stripe.com/docs/keys)

### On macOS:

First, you need to install Java 21 or later. You can download it from [Azul](https://www.azul.com/downloads/#zulu) or
use [SDKMAN](https://sdkman.io/).

```sh
brew install --cask zulu21
```

You can install Temporal using Homebrew

```sh
brew install temporal
```

or visit [Temporal Installation](https://docs.temporal.io/cli#install) for more information.

You can install Docker using Homebrew

```sh
brew install docker
```

or visit [Docker Installation](https://docs.docker.com/get-docker/) for more information.

### Other platforms

Please check the official documentation for the installation of Java, Temporal, and Docker for your platform.

### Stripe API Keys

Sign up for a Stripe account and get your API keys from the [Stripe Dashboard](https://dashboard.stripe.com/apikeys).
Then in `application.properties` file add the following line with your secret key.

```properties
stripe.api-key=sk_test_51J3j
```

## Run

You are required to first start the temporal server using the following command

```sh
temporal server start-dev
```

and then run the application using the following command or using your IDE.

```sh
./gradlew bootRun
```

### Other commands

#### Lint
To run lint checks, use the following command

```sh
./gradlew sonarlintMain
```

#### Code Formatting
To format the code, use the following command

```sh
./gradlew spotlessApply
```

## Guides

The following guides illustrate how to use some features concretely:

- [Accessing Data with JPA](https://spring.io/guides/gs/accessing-data-jpa/)
- [Temporal Quick Start](https://docs.temporal.io/docs/quick-start)
- [Temporal Java SDK Quick Guide](https://docs.temporal.io/dev-guide/java)
- [Stripe Quick Start](https://stripe.com/docs/quickstart)
- [Stripe Java SDK](https://stripe.com/docs/api/java)

### Docker Compose support

Here's list of the services used in this Docker Compose file along with their purposes:

1. **CockroachDB**:
    - Purpose: A distributed SQL database used by Temporal Workflow Service.

2. **Temporal Workflow Service**:
    - Purpose: Runs the Temporal Workflow Service, orchestrating distributed workflows.

3. **Temporal Admin Tools**:
    - Purpose: Provides administrative tools for managing Temporal, such as CLI commands.

4. **Temporal UI**:
    - Purpose: Web-based user interface for interacting with Temporal and monitoring workflows.

5. **PostgreSQL**:
    - Purpose: A relational database used by the application (Midas App) running alongside Temporal.

6. **Midas App**:
    - Purpose: An application that depends on both PostgreSQL and Temporal services. It likely interacts with Temporal workflows and uses PostgreSQL for persistent data storage.

These services work together to provide a complete environment for developing and running applications that utilize Temporal Workflow Service along with supporting databases.

Please review the tags of the used images and set them to the same as you're running in production.

To run Docker Compose with the provided configuration file, navigate to the directory where your `docker-compose.yml` file is located and use the following command:

```bash
docker-compose up
```

This command will start all the services defined in the `docker-compose.yml` file in the foreground. If you want to run the services in detached mode (in the background), you can use the `-d` flag:

```bash
docker-compose up -d
```
`.env` file contains the environment variables necessary for docker-compose file


## Running Tests in a Spring Boot Project with Gradle

### 1. Writing Tests
- Write your tests using JUnit or TestNG. Place your test classes in the `src/test/java` directory.

### 2. Gradle Configuration
- Ensure that your `build.gradle` file includes the necessary dependencies for testing. Typically, Spring Boot projects come with the required dependencies for testing included by default.
  
  ```groovy
  dependencies {
      testImplementation 'org.springframework.boot:spring-boot-starter-test'
  }
  ```

### 3. Run Tests
- To run your tests using Gradle, execute the following command in your terminal:

  ```bash
  ./gradlew test
  ```

  This command will execute all tests in your project.

### 4. Run Specific Tests
- You can also run specific tests by providing the class name to Gradle:

  ```bash
  ./gradlew test --tests com.midas.app.activities.AccountActivityImplTest
  ```

### 5. Generate Test Reports
- By default, Gradle generates test reports in HTML format. After running your tests, you can find the reports in the `build/reports/tests/test` directory.

### 6. Configure Test Tasks
- Customize the test task in your `build.gradle` file. For example, to change the test output directory:

  ```groovy
  test {
      testLogging {
          outputs.html.enabled = true
          outputs.html.destination = file("$buildDir/reports/tests")
      }
  }
  ```

  This configuration changes the test output directory to `build/reports/tests`.

### 7. Recordings
- 1. https://drive.google.com/file/d/1R_9VG90g6cIW81mdOYTmKPsLSBRdkcPP/view?usp=drive_link
- 2. https://drive.google.com/file/d/1njqnbuEOy6mw0Wciwcqc9z9DfOTofBW1/view?usp=drive_link
