    // Timer Logic
    let timeRemaining = 1800; // 30 minutes in seconds
    const timerElement = document.getElementById('quiz-timer');

    function updateTimer() {
    const minutes = Math.floor(timeRemaining / 60);
    const seconds = timeRemaining % 60;
    timerElement.textContent = `⏰  Time Remaining: ${minutes}:${seconds.toString().padStart(2, '0')}`;

    if (timeRemaining <= 0) {
        clearInterval(timerInterval);
        submitQuiz();
    } else {
        timeRemaining--;
    }
    }

    // Start the timer
    const timerInterval = setInterval(updateTimer, 1000);

    // Function to submit the quiz
    function submitQuiz() {
      // Send the remaining time to the backend
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
          alert('Quiz submitted successfully!');
          window.location.href = '/result'; // Redirect to results page
        })
        .catch(error => {
          console.error('Error submitting quiz:', error);
        });
    }

    // Add event listener for the "Next" button
    document.querySelector('.quiz-navigation button:last-child').addEventListener('click', () => {
      // Logic to move to the next question
      // For now, submit the quiz when the last question is reached
      submitQuiz();
    });