// Timer Logic
let timeRemaining = 300; // 5 minutes in seconds
const timerElement = document.getElementById('quiz-timer');

function updateTimer() {
    const minutes = Math.floor(timeRemaining / 60);
    const seconds = timeRemaining % 60;
    timerElement.textContent = `⏰ Time Remaining: ${minutes}:${seconds.toString().padStart(2, '0')}`;
<<<<<<< HEAD
=======

>>>>>>> 57133f82322e04600a9af32f366f5bc5e4e15efd

    if (timeRemaining <= 0) {
        clearInterval(timerInterval);
    } else {
        timeRemaining--;
    }
<<<<<<< HEAD
}

=======


>>>>>>> 57133f82322e04600a9af32f366f5bc5e4e15efd
// Start the timer
const timerInterval = setInterval(updateTimer, 1000);
