document.addEventListener("DOMContentLoaded", function () {
    const API_URL = "/api/quizzes"; // API endpoint updated for quizzes

    const tableBody = document.querySelector("tbody");
    const addQuizForm = document.querySelector("#addQuizModal form"); // Modal form for adding a quiz
    const editQuizForm = document.querySelector("#editQuizModal form"); // Modal form for editing a quiz
    const deleteConfirmButton = document.querySelector("#deleteQuizModal .btn-danger"); // Confirm button for deletion
    
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

    let deleteQuizId = null;
    const editOptionInputs = document.querySelectorAll(".editOption-input");

    const optionInputs = document.querySelectorAll(".option-input");
    const correctAnswerSelect = document.getElementById("correctAnswer");

    function updateCorrectAnswerDropdown() {
        correctAnswerSelect.innerHTML = '<option value="">Select Correct Answer</option>';

        const options = Array.from(optionInputs)
            .map(input => input.value.trim())
            .filter(option => option !== "");

        options.forEach(option => {
            const newOption = document.createElement("option");
            newOption.value = option;
            newOption.textContent = option;
            correctAnswerSelect.appendChild(newOption);
        });

        if (options.length === 1) {
            correctAnswerSelect.value = options[0];
        }
    }

    optionInputs.forEach(input => input.addEventListener("input", updateCorrectAnswerDropdown));

    // Fetch and display quizzes
    async function fetchQuizzes() {
        try {
            const response = await fetch(API_URL);
            const quizzes = await response.json();
            tableBody.innerHTML = ""; 

            quizzes.forEach((quiz, index) => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${index + 1}</td>
                    <td>${quiz.topic}</td>
                    <td>${quiz.question}</td>
                    <td>${quiz.answers}</td>
                    <td>${quiz.correct_answer}</td>

                    <td>
                        <button class="btn btn-warning btn-sm edit-btn" data-id="${quiz.id}" 
                            data-topic="${quiz.topic}" 
                            data-question="${quiz.question}" 
                            data-answers="${quiz.answers}" 
                            data-correct_answer="${quiz.correct_answer}" 
                            data-bs-toggle="modal" 
                            data-bs-target="#editQuizModal">Edit</button>

                        <button class="btn btn-danger btn-sm delete-btn" data-id="${quiz.id}" 
                            data-bs-toggle="modal" data-bs-target="#deleteQuizModal">Delete</button>
                    </td>
                `;
                tableBody.appendChild(row);
            });

            attachEventListeners();
        } catch (error) {
            console.error("Error fetching quizzes:", error);
        }
    }

    // Attach event listeners for edit and delete buttons
    function attachEventListeners() {
        document.querySelectorAll(".edit-btn").forEach((button) => {
            button.addEventListener("click", function () {
                document.querySelector("#editQuizId").value = this.dataset.id;
                document.querySelector("#editTopic").value = this.dataset.topic;
                document.querySelector("#editQuestion").value = this.dataset.question;

                const optionInputs = document.querySelectorAll(".editOption-input");
                const correctAnswerSelect = document.querySelector("#editCorrectAnswer");

                correctAnswerSelect.innerHTML = '<option value="">Select Correct Answer</option>';
                const optionsRaw = this.dataset.answers;
                const options = optionsRaw.split(",");
                const correctAnswer = this.dataset.correct_answer;

                optionInputs.forEach((input, index) => {
                    input.value = options[index] ? options[index].trim() : "";
                });

                updateEditCorrectAnswerDropdown();

                correctAnswerSelect.value = correctAnswer;
            });
        });

        document.querySelectorAll(".delete-btn").forEach((button) => {
            button.addEventListener("click", function () {
                deleteQuizId = this.dataset.id;
            });
        });
    }

    // Function to update the correct answer dropdown dynamically
    function updateEditCorrectAnswerDropdown() {
        const optionInputs = document.querySelectorAll(".editOption-input");
        const correctAnswerSelect = document.querySelector("#editCorrectAnswer");

        correctAnswerSelect.innerHTML = '<option value="">Select Correct Answer</option>'; 

        const options = Array.from(optionInputs).map(input => input.value.trim()).filter(option => option !== "");

        options.forEach(option => {
            const newOption = document.createElement("option");
            newOption.value = option;
            newOption.textContent = option;
            correctAnswerSelect.appendChild(newOption);
        });

        if (options.length === 1) {
            correctAnswerSelect.value = options[0];
        }
    }

    editOptionInputs.forEach(input => input.addEventListener("input", updateEditCorrectAnswerDropdown));

    // Add new quiz
    addQuizForm.addEventListener("submit", async function (e) {
        e.preventDefault();

        const formData = new FormData(this);
        const topic = formData.get("topic");
        const question = formData.get("question");
        const difficulty = formData.get("difficulty");
        const correct_answer = formData.get("correctAnswer");

        const options = Array.from(document.querySelectorAll(".option-input"))
            .map(input => input.value)
            .filter(option => option.trim() !== "");

        try {
            const response = await fetch(API_URL, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ topic, question, difficulty, answers:options, correct_answer }),
            });

            if (!response.ok) throw new Error("Failed to add quiz");

            addQuizForm.reset();
            fetchQuizzes();
            bootstrap.Modal.getInstance(document.querySelector("#addQuizModal")).hide();
        } catch (error) {
            console.error("Error adding quiz:", error);
        }
    });

    // Edit quiz
    editQuizForm.addEventListener("submit", async function (e) {
        e.preventDefault();

        const id = document.querySelector("#editQuizId").value;
        const topic = document.querySelector("#editTopic").value;
        const question = document.querySelector("#editQuestion").value;
        const options = Array.from(document.querySelectorAll(".editOption-input"))
            .map(input => input.value)
            .filter(option => option.trim() !== "");

        const correct_answer = document.querySelector("#editCorrectAnswer").value;

        try {
            const response = await fetch(`${API_URL}/${id}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ topic, question, answers: options, correct_answer }),
            });

            if (!response.ok) throw new Error("Failed to update quiz");

            fetchQuizzes();
            bootstrap.Modal.getInstance(document.querySelector("#editQuizModal")).hide();
        } catch (error) {
            console.error("Error updating quiz:", error);
        }
    });

    // Delete quiz
    deleteConfirmButton.addEventListener("click", async function () {
        if (!deleteQuizId) return;

        try {
            const response = await fetch(`${API_URL}/${deleteQuizId}`, {
                method: "DELETE",
            });

            if (!response.ok) throw new Error("Failed to delete quiz");

            fetchQuizzes();
            bootstrap.Modal.getInstance(document.querySelector("#deleteQuizModal")).hide();
        } catch (error) {
            console.error("Error deleting quiz:", error);
        }
    });

    fetchQuizzes();
});
