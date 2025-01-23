
    <title>Planify - Backend</title>

    <h1>Planify (Backend)</h1>

    <p><strong>Planify</strong> is an interactive calendar application designed to simplify event planning and sharing among friends. This repository contains the code for the <strong>Spring Boot</strong> backend services of the application.</p>

    <h2>Technologies Used</h2>
    <ul>
        <li><strong>Spring Boot:</strong> For handling business logic, RESTful APIs, and WebSocket communication.</li>
        <li><strong>PostgreSQL:</strong> For data persistence, including user accounts, plans, and relationships.</li>
        <li><strong>WebSockets:</strong> To provide real-time updates for plan approvals, comments, and notifications.</li>
        <li><strong>JWT (JSON Web Tokens)/OAuth2:</strong> For secure user authentication and authorization.</li>
    </ul>

    <h2>Prerequisites</h2>
    <ul>
        <li>Java JDK 17</li>
        <li>Maven</li>
        <li>PostgreSQL (v13 or higher)</li>
    </ul>

    <h2>Features</h2>
    <ul>
        <li><strong>User Management:</strong> Register, log in, and manage accounts securely with JWT.</li>
        <li><strong>Plan Management:</strong> Create, update, delete, and retrieve user plans.</li>
        <li><strong>Friendship Management:</strong> Add, remove, and view friends.</li>
        <li><strong>Real-Time Collaboration:</strong> Enable instant feedback with WebSocket-powered updates.</li>
        <li><strong>Data Persistence:</strong> Store and retrieve information using PostgreSQL.</li>
    </ul>

    <h2>API Endpoints</h2>

    <h3>Authentication</h3>
    <ul>
        <li><code>POST /api/auth/validate-token</code>: Validate an existing JWT token from local storage.</li>
        <li><code>POST /api/auth/login</code>: Authenticate a user and generate a JWT.</li>
    </ul>

    <h3>User</h3>
    <ul>
        <li><code>POST /api/users</code>: Create and persist a new user.</li>
        <li><code>PUT /api/users</code>: Update the profile of the logged-in user.</li>
        <li><code>GET /api/users</code>: Retrieve the UserDTO of the logged-in user.</li>
        <li><code>DELETE /api/users</code>: Remove the logged-in user from the database.</li>
    </ul>

    <h3>Plans</h3>
    <ul>
        <li><code>POST /api/plans</code>: Persist a new Plan.</li>
        <li><code>GET /api/plans/{id}</code>: Retrieve all plans for a user.</li>
        <li><code>PUT /api/plans/{id}</code>: Update a specific plan.</li>
        <li><code>DELETE /api/plans/{id}</code>: Delete a specific plan.</li>
        <li><code>GET /api/{id}/details</code>: Retrieve a Plan along with all nested dependencies (PlanDTO).</li>
    </ul>

    <h3>Friends</h3>
    <ul>
        <li><code>POST /api/friendships</code>: Create a new friendship between users.</li>
        <li><code>GET /api/friendships</code>: Retrieve the user's friends list.</li>
        <li><code>DELETE /api/friendships/{id}</code>: Remove a friendship between two users based on friendship ID.</li>
        <li><code>GET /api/friendships/status/{status}</code>: Retrieve all friendships for a user with a given status. <em>Enum Status:</em> PENDING, APPROVED, REJECTED, OWNER.</li>
    </ul>

    <h3>(Plan) Approvals</h3>
    <ul>
        <li><code>POST /api/approvals</code>: Creates a new approval and associates the approval with a specific plan and user.</li>
        <li><code>DELETE /api/approvals/{id}</code>: Deletes an approval using the Approval ID.</li>
        <li><code>PUT /api/approvals/{id}/{accepted}</code>: Updates the status enum of an approval. <em>Accepted</em> is a boolean path variable. <code>accepted = true</code> will update the status to <strong>ACCEPTED</strong> and <code>false</code> will update to <strong>REJECTED</strong>.</li>
        <li><code>GET /api/approvals/users/status/{status}</code>: Retrieves all Plans associated with the logged-in user filtered by <em>status</em> (PENDING, APPROVED, REJECTED, OWNER). Includes an optional query parameter <code>includeOwner</code> (default: false). If true, it returns all plans where the user is the owner, regardless of status.</li>
    </ul>

    <h3>Comments</h3>
    <ul>
        <li><code>POST /api/comments</code>: Creates a new Comment.</li>
        <li><code>DELETE /api/comments/{id}</code>: Deletes a comment.</li>
        <li><code>GET /plans/{id}</code>: Retrieves all comments for a given Plan.</li>
    </ul>




