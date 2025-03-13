document.addEventListener("DOMContentLoaded", function () {
    const API_URL = "/api/questions";

    const tableBody = document.querySelector("tbody");
    const addQuestionForm = document.querySelector("#addQuestionModal form");
    const editQuestionForm = document.querySelector("#editQuestionModal form");
    const deleteConfirmButton = document.querySelector("#deleteQuestionModal .btn-danger");
    
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
    let deleteQuestionId = null;

    const editOptionInputs = document.querySelectorAll(".editOption-input");

    const optionInputs = document.querySelectorAll(".option-input");
    const correctAnswerSelect = document.getElementById("correctAnswer");

    function updateCorrectAnswerDropdown() {

        correctAnswerSelect.innerHTML = '<option value="">Select Correct Answer</option>';

        // Get valid options
        const options = Array.from(optionInputs)
            .map(input => input.value.trim())
            .filter(option => option !== "");
        // Populate dropdown
        options.forEach(option => {
            const newOption = document.createElement("option");
            newOption.value = option;
            newOption.textContent = option;
            correctAnswerSelect.appendChild(newOption);
        });

        // Auto-select first option if only one is available
        if (options.length === 1) {
            correctAnswerSelect.value = options[0];
        }
    }

    optionInputs.forEach(input => input.addEventListener("input", updateCorrectAnswerDropdown));

    //Fetch and display questions
    async function fetchQuestions() {
        try {
            const response = await fetch(API_URL);
            const questions = await response.json();
            tableBody.innerHTML = ""; // Clear table before adding new data

            questions.forEach((question,index) => {

                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${index+1}</td>
                    <td>${question.question}</td>
                    <td>${question.difficulty}</td>
                    <td>${question.options}</td>
                    <td>${question.correctAnswer}</td>

                    <td>
                        <button class="btn btn-warning btn-sm edit-btn" data-id="${question.id}" 
                            data-question="${question.question}" 
                            data-difficulty="${question.difficulty}" 
                            data-options="${question.options}" 
                            data-correctAnswer="${question.correctAnswer}" 
                            data-bs-toggle="modal" 
                            data-bs-target="#editQuestionModal">Edit</button>

                        <button class="btn btn-danger btn-sm delete-btn" data-id="${question.id}" 
                            data-bs-toggle="modal" data-bs-target="#deleteQuestionModal">Delete</button>
                    </td>
                `;
                tableBody.appendChild(row);
            });

            attachEventListeners();
        } catch (error) {
            console.error("Error fetching questions:", error);
        }
    }

    // Attach event listeners for edit and delete buttons
    function attachEventListeners() {
        document.querySelectorAll(".edit-btn").forEach((button) => {
            button.addEventListener("click", function () {
                document.querySelector("#editQuestionId").value = this.dataset.id;
                document.querySelector("#editQuestionText").value = this.dataset.question;
                document.querySelector("#editDifficulty").value = this.dataset.difficulty;
    
                const optionInputs = document.querySelectorAll(".editOption-input");
                const correctAnswerSelect = document.querySelector("#editCorrectAnswer");
    
                // Clear existing dropdown options
                correctAnswerSelect.innerHTML = '<option value="">Select Correct Answer</option>';
                const optionsRaw = this.dataset.options;  // "2,13,64,20"
                const options = optionsRaw.split(",");   // ["2", "13", "64", "20"]
                console.log("Parsed options:", options);
                const correctAnswer = this.dataset.correctanswer;

                optionInputs.forEach((input, index) => {
                    input.value = options[index] ? options[index].trim() : ""; // Keep position
                });

                // Populate correct answer dropdown
                updateEditCorrectAnswerDropdown();
    
                    correctAnswerSelect.value = correctAnswer ;
             
                });
        });
    
        document.querySelectorAll(".delete-btn").forEach((button) => {
            button.addEventListener("click", function () {
                deleteQuestionId = this.dataset.id;
            });
        });
    }
    
    // Function to update the correct answer dropdown dynamically
    function updateEditCorrectAnswerDropdown() {
        const optionInputs = document.querySelectorAll(".editOption-input");
        const correctAnswerSelect = document.querySelector("#editCorrectAnswer");
    
        correctAnswerSelect.innerHTML = '<option value="">Select Correct Answer</option>'; // Reset dropdown
    
    
    // Get valid options from input fields
    const options = Array.from(optionInputs).map(input => input.value.trim()).filter(option => option !== "");

    console.log("Updated Options:", options); // Debugging: See options in console

        // Populate dropdown with available options
        options.forEach(option => {
            const newOption = document.createElement("option");
            newOption.value = option;
            newOption.textContent = option;
            correctAnswerSelect.appendChild(newOption);
        });
    
        // Auto-select first option if only one is available
        if (options.length === 1) {
            correctAnswerSelect.value = options[0];
        }
    }
    

    editOptionInputs.forEach(input => input.addEventListener("input", updateEditCorrectAnswerDropdown));


    // Add new question
    addQuestionForm.addEventListener("submit", async function (e) {
        e.preventDefault();

        const formData = new FormData(this);
        const question = formData.get("question");
        const difficulty = formData.get("difficulty");
        const correctAnswer = formData.get("correctAnswer");
    
        // Get options from input fields (assuming they have class "option-input")
        const options = Array.from(document.querySelectorAll(".option-input"))
            .map(input => input.value)
            .filter(option => option.trim() !== ""); // Remove empty options
    
        try {
            const response = await fetch(API_URL, {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ question, difficulty, options, correctAnswer }),
            });
    
            if (!response.ok) throw new Error("Failed to add question");
    
            addQuestionForm.reset();
            fetchQuestions(); // Refresh the table
            bootstrap.Modal.getInstance(document.querySelector("#addQuestionModal")).hide();
        } catch (error) {
            console.error("Error adding question:", error);
        }
    });

    // Edit question
    editQuestionForm.addEventListener("submit", async function (e) {
        e.preventDefault();

        const id = document.querySelector("#editQuestionId").value;
        const question = document.querySelector("#editQuestionText").value;
        const difficulty = document.querySelector("#editDifficulty").value;
        // const options = document.querySelector("#editOptions").value.split(",").map(opt => opt.trim());
        const options = Array.from(document.querySelectorAll(".editOption-input"))
        .map(input => input.value)
        .filter(option => option.trim() !== ""); // Remove empty options

        const correctAnswer = document.querySelector("#editCorrectAnswer").value;
        console.log(options)
        try {
            const response = await fetch(`${API_URL}/${id}`, {
                method: "PUT",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify({ question, difficulty,options,correctAnswer }),
            });

            if (!response.ok) throw new Error("Failed to update question");

            fetchQuestions();
            bootstrap.Modal.getInstance(document.querySelector("#editQuestionModal")).hide();
        } catch (error) {
            console.error("Error updating question:", error);
        }
    });

    // Delete question
    deleteConfirmButton.addEventListener("click", async function () {
        if (!deleteQuestionId) return;

        try {
            const response = await fetch(`${API_URL}/${deleteQuestionId}`, {
                method: "DELETE",
            });

            if (!response.ok) throw new Error("Failed to delete question");

            fetchQuestions();
            bootstrap.Modal.getInstance(document.querySelector("#deleteQuestionModal")).hide();
        } catch (error) {
            console.error("Error deleting question:", error);
        }
    });

    fetchQuestions();
});
