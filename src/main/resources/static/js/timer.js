// Timer Logic
let timeRemaining = 300; // 5 minutes in seconds
const timerElement = document.getElementById('quiz-timer');

function updateTimer() {
    const minutes = Math.floor(timeRemaining / 60);
    const seconds = timeRemaining % 60;
    timerElement.textContent = `⏰ Time Remaining: ${minutes}:${seconds.toString().padStart(2, '0')}`;

    if (timeRemaining <= 0) {
        clearInterval(timerInterval);
    } else {
        timeRemaining--;
    }
}

// Start the timer
const timerInterval = setInterval(updateTimer, 1000);

// Function to submit the quiz
function submitQuiz() {
    fetch('/submit-quiz', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            timeRemaining: timeRemaining,
        }),
    })
        .then(response => response.json())
        .then(data => {
            window.location.href = '/result'; // Redirect to results page
        })
        .catch(error => {
            console.error('Error submitting quiz:', error);
        });
}
