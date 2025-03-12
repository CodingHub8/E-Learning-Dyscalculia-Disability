document.addEventListener("DOMContentLoaded", function () {
    const API_URL = "http://localhost:8080/api/users";

    const tableBody = document.querySelector("tbody");
    const addUserForm = document.querySelector("#addUserForm form");

    const sidebar = document.getElementById('sidebar');
            const toggleBtn = document.getElementById('toggleBtn');
            const overlay = document.getElementById('overlay');

            toggleBtn.addEventListener('click', function () {
                sidebar.classList.toggle('open');
                overlay.classList.toggle('show');
                toggleBtn.classList.toggle('hidden');
            });

            overlay.addEventListener('click', function () {
                sidebar.classList.remove('open');
                overlay.classList.remove('show');
                toggleBtn.classList.remove('hidden');
            });
    // Fetch and display users
    async function loadUsers() {

        try {
            const response = await fetch(API_URL);
            const users = await response.json();

            tableBody.innerHTML = ""; // Clear table before adding new data
            users.forEach((user,index) => {
                const row = document.createElement("tr");
                row.innerHTML = `
                        <tr>
                            <td>${index+1}</td>
                            <td>${user.username}</td>
                            <td>${user.email}</td>
                            <td>${user.grade_level}</td>
                            <td>${user.password}</td>

                            <td>
                                <button class="btn btn-warning btn-sm edit-btn" 
                                    data-id="${user.id}" 
                                    data-username="${user.username}" 
                                    data-email="${user.email}" 
                                    data-grade_level="${user.grade_level}"
                                    data-password="${user.password}"

                                    data-bs-toggle="modal" data-bs-target="#editUserModal">Edit</button>
                                <button class="btn btn-danger btn-sm delete-btn" 
                                    data-id="${user.id}" 
                                    data-bs-toggle="modal" data-bs-target="#deleteUserModal">Delete</button>
                            </td>
                        </tr>`;
                        tableBody.appendChild(row);

                });

                attachEditListeners();
                attachDeleteListeners();
            } catch (error) {
                console.error("Error fetching questions:", error);
            }    
        }

    // // Add new user
    // document.querySelector("#addUserModal form").addEventListener("submit", function (event) {
    //     event.preventDefault();
           

    //     const formData = new FormData(this); 
    //     const username =  formData.get("username")
    //     const email =  formData.get("email")
    //     const role = formData.get("role")
    //     fetch(API_URL, {
    //         method: "POST",
    //         headers: { "Content-Type": "application/json" },
    //         body: JSON.stringify({username,email,role})
    //     }).then(() => {
    //         addUserForm.reset();
    //         loadUsers();
    //         bootstrap.Modal.getInstance(document.querySelector("#addUserModal")).hide();
    //     });
    // });

    // Edit user
    function attachEditListeners() {
        document.querySelectorAll(".edit-btn").forEach(button => {
            button.addEventListener("click", function () {
                document.getElementById("editUserId").value = this.dataset.id;
                document.getElementById("editUsername").value = this.dataset.username;
                document.getElementById("editUserEmail").value = this.dataset.email;
                document.getElementById("editUserGrade").value = this.dataset.grade_level;
                document.getElementById("editUserPassword").value = this.dataset.password;

            });
        });

        document.querySelector("#editUserModal form").addEventListener("submit", function (event) {
            event.preventDefault();
            let userId = document.getElementById("editUserId").value;
            let updatedUser = {
                username: document.getElementById("editUsername").value,
                email: document.getElementById("editUserEmail").value,
                grade_level: document.getElementById("editUserGrade").value,
                password: document.getElementById("editUserPassword").value

            };

            fetch(`${API_URL}/${userId}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(updatedUser)
            }).then(() => {
                loadUsers();
                bootstrap.Modal.getInstance(document.querySelector("#editUserModal")).hide();
            });
        });
    }

    // Delete user
    function attachDeleteListeners() {  
        document.querySelectorAll(".delete-btn").forEach(button => {
            button.addEventListener("click", function () {
                let userId = this.dataset.id;
                document.querySelector("#deleteUserModal .btn-danger").addEventListener("click", function () {
                    fetch(`${API_URL}/${userId}`, { method: "DELETE" }).then(() => {
                        loadUsers();
                        bootstrap.Modal.getInstance(document.querySelector("#deleteUserModal")).hide();
                    });
                });
            });
        });
    }

    loadUsers();
});
