# SoulSprinkle

SoulSprinkle is a platform aimed at enhancing personal development through positive actions in daily routines. Users can create tasks, assign them to others, and receive notifications when tasks are completed. User settings are stored to provide a seamless cross-platform experience.

## Installation

### Prerequisites

- **Java 17+**
- **Maven 3.8+**
- **MySQL or PostgreSQL** (or any supported database)

### Steps

1. **Clone the Project**:
    ```bash
    git clone https://github.com/ecemoz/soulsprinkle.git
    cd soulsprinkle
    ```

2. **Set up the Database**:
   Start your database and configure the connection in `application.properties`.

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/soulsprinkle
   spring.datasource.username=db_username
   spring.datasource.password=db_password
   spring.jpa.hibernate.ddl-auto=update
   ```

3. **Install Dependencies**:
    ```bash
    mvn install
    ```

4. **Run the Application**:
    ```bash
    mvn spring-boot:run
    ```

The application will be accessible at `http://localhost:8080` once it is running successfully.

## Usage

SoulSprinkle provides APIs to add tasks, record positive actions, send notifications, and manage user settings. Here are a few example usages:

- **Add a Task**: Create a new task via POST request to `/api/tasks`.
- **Positive Actions**: Record a positive action when a task is completed.
- **Notifications**: Send notifications when a task is assigned or completed.

## API Documentation

### User Endpoints

| HTTP Method | Endpoint                | Description                              |
|-------------|-------------------------|------------------------------------------|
| GET         | `/api/users/{id}`       | Retrieve a user by ID                    |
| POST        | `/api/users`            | Create a new user                        |
| PUT         | `/api/users/{id}`       | Update an existing user                  |
| DELETE      | `/api/users/{id}`       | Delete a user by ID                      |

### Task Endpoints

| HTTP Method | Endpoint                         | Description                                       |
|-------------|----------------------------------|---------------------------------------------------|
| POST        | `/api/tasks`                     | Create a new task                                 |
| GET         | `/api/tasks/creator/{creatorId}` | Retrieve tasks created by a specific user         |
| PUT         | `/api/tasks/{id}`                | Update an existing task                           |
| DELETE      | `/api/tasks/{id}`                | Delete a task by ID                               |

### Positive Actions Endpoints

| HTTP Method | Endpoint                         | Description                                       |
|-------------|----------------------------------|---------------------------------------------------|
| GET         | `/api/positive-actions/{taskId}` | Retrieve positive actions for a specific task     |

### Notifications Endpoints

| HTTP Method | Endpoint                         | Description                                       |
|-------------|----------------------------------|---------------------------------------------------|
| GET         | `/api/notifications/user/{userId}` | Retrieve all notifications for a user             |
| GET         | `/api/notifications/task/{taskId}` | Retrieve notifications for a specific task        |

### Profile Settings Endpoints

| HTTP Method | Endpoint                                  | Description                                          |
|-------------|------------------------------------------|------------------------------------------------------|
| GET         | `/api/profile-settings/user/{userId}`     | Retrieve profile settings for a specific user        |
| PUT         | `/api/profile-settings/{id}`             | Update profile settings                              |
