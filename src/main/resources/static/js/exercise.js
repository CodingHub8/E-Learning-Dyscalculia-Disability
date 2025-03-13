    document.addEventListener("DOMContentLoaded", function () {

    const fetchButton = document.getElementById("fetch-questions");
    const questionContainer = document.getElementById("question-container");

    let studentId = null;

    // Fetch session data on page load
    fetch('/session')
      .then(response => response.json())
      .then(data => {
        if (data.studentId) {
          studentId = data.studentId; // Store the student ID
        } else {
          alert("Not authenticated. Please log in.");
          window.location.href = "/login"; // Redirect if not authenticated
        }
      })
      .catch(error => console.error('Error fetching session data:', error));

    fetchButton.addEventListener("click", () => {
        const difficulty = document.getElementById("difficulty").value;

        fetch(`/exercise/questions?difficulty=${difficulty}`)
            .then(response => response.json())
            .then(questions => {
                questionContainer.innerHTML = ""; 

                if (questions.length === 0) {
                    questionContainer.innerHTML = "<p>No questions available.</p>";
                    return;
                }

                questions.forEach(question => {
                    const questionDiv = document.createElement("div");
                    questionDiv.classList.add("mb-4");

                    questionDiv.innerHTML = `
                        <p><strong>${question.question}</strong></p>
                        <div>
                            ${question.options.map(option => `
                                <button class="btn btn-outline-primary mcq-option" data-question-id="${question.id}" data-answer="${option}">
                                    ${option}
                                </button>
                            `).join("")}
                        </div>
                    `;

                    questionContainer.appendChild(questionDiv);
                });

                document.querySelectorAll(".mcq-option").forEach(button => {
                    button.addEventListener("click", function () {
                        document.querySelectorAll(`.mcq-option[data-question-id="${this.dataset.questionId}"]`).forEach(btn => {
                            btn.classList.remove("active");
                        });
                        this.classList.add("active");
                    });
                });

                const submitBtn = document.createElement("button");
                submitBtn.id = "submit-btn";
                submitBtn.classList.add("btn", "btn-success", "mt-3");
                submitBtn.textContent = "Submit Answers";
                questionContainer.appendChild(submitBtn);

                submitBtn.addEventListener("click", () => {
                    let attempts = [];

                    document.querySelectorAll(".mcq-option.active").forEach(selected => {
                        attempts.push({
                            studentId,  // test je
                            questionId: selected.dataset.questionId,
                            selectedAnswer: selected.dataset.answer
                        });
                    });

                    if (attempts.length === 0) {
                        alert("Please select at least one answer.");
                        return;
                    }

                    attempts.forEach(attempt => {
                        fetch("/exercise/submitAttempt", {
                            method: "POST",
                            headers: { "Content-Type": "application/x-www-form-urlencoded" },
                            body: `studentId=${attempt.studentId}&questionId=${attempt.questionId}&selectedAnswer=${attempt.selectedAnswer}`
                        })
                        .then(response => response.json())
                        .then(data => {
                            console.log("Attempt stored:", data);
                        });
                    });

                    alert("Answers submitted successfully!");
                });
            });
    });
});