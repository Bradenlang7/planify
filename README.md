# Planify (Backend)

Planify is an interactive calendar application designed to simplify event planning and sharing among friends. This repository contains the code for the **Spring Boot** backend services of the application.

---

## Technologies Used

- **Spring Boot:** For handling business logic, RESTful APIs, and WebSocket communication.
- **PostgreSQL:** For data persistence, including user accounts, plans, and relationships.
- **WebSockets:** To provide real-time updates for plan approvals, comments, and notifications.
- **JWT (JSON Web Tokens)/OAuth2:** For secure user authentication and authorization.

---

## Prerequisites

- Java JDK 17
- Maven
- PostgreSQL (v13 or higher)

---

## Features

- **User Management:** Register, log in, and manage accounts securely with JWT.
- **Plan Management:** Create, update, delete, and retrieve user plans.
- **Friendship Management:** Add, remove, and view friends.
- **Real-Time Collaboration:** Enable instant feedback with WebSocket-powered updates.
- **Data Persistence:** Store and retrieve information using PostgreSQL.

---

## API Endpoints

### Authentication
- `POST /api/auth/validate-token`: Validate an existing JWT token from local storage.
- `POST /api/auth/login`: Authenticate a user and generate a JWT.

### User
- `POST /api/users`: Create and persist a new user.
- `PUT /api/users`: Update the profile of the logged-in user.
- `GET /api/users`: Retrieve the UserDTO of the logged-in user.
- `DELETE /api/users`: Remove the logged-in user from the database.

### Plans
- `POST /api/plans`: Persist a new Plan.
- `GET /api/plans/{id}`: Retrieve all plans for a user.
- `PUT /api/plans/{id}`: Update a specific plan.
- `DELETE /api/plans/{id}`: Delete a specific plan.
- `GET /api/{id}/details`: Retrieve a Plan along with all nested dependencies (PlanDTO).

### Friends
- `POST /api/friendships`: Create a new friendship between users.
- `GET /api/friendships`: Retrieve the user's friends list.
- `DELETE /api/friendships/{id}`: Remove a friendship between two users based on friendship ID.
- `GET /api/friendships/status/{status}`: Retrieve all friendships for a user with a given status. *Enum Status:* PENDING, APPROVED, REJECTED, OWNER.

### (Plan) Approvals
- `POST /api/approvals`: Creates a new approval and associates the approval with a specific plan and user.
- `DELETE /api/approvals/{id}`: Deletes an approval using the Approval ID.
- `PUT /api/approvals/{id}/{accepted}`: Updates the status enum of an approval. *Accepted* is a boolean path variable. `accepted = true` will update the status to **ACCEPTED** and `false` will update to **REJECTED**.
- `GET /api/approvals/users/status/{status}`: Retrieves all Plans associated with the logged-in user filtered by *status* (PENDING, APPROVED, REJECTED, OWNER). Includes an optional query parameter `includeOwner` (default: false). If true, it returns all plans where the user is the owner, regardless of status.

### Comments
- `POST /api/comments`: Creates a new Comment.
- `DELETE /api/comments/{id}`: Deletes a comment.
- `GET /plans/{id}`: Retrieves all comments for a given Plan.
