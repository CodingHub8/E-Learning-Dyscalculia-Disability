const API_BASE_URL = window.location.hostname === "localhost" ? "http://localhost:8080" : "https://yourdomain.com";

// Call the function to update the year when the page loads
document.addEventListener('DOMContentLoaded', () => {
    document.getElementById("currentYear").textContent = new Date().getFullYear().toString();
});

// Toggle sidebar visibility
const sidenav = document.getElementById('Msidenav');
const content = document.getElementById('Mcontent');
const sidenavToggleButton = document.getElementById('Mhamburger-toggle');
const closeBtn = document.getElementById('Mclose-btn');

// Open sidebar when hamburger button is clicked
sidenavToggleButton.addEventListener('click', () => {
  sidenav.classList.add('open'); // Open sidebar
  content.classList.add('open'); // Add class to content to adjust margin
});

// Close sidebar when close button is clicked
closeBtn.addEventListener('click', () => {
  sidenav.classList.remove('open'); // Close sidebar
  content.classList.remove('open'); // Remove class to reset content margin
});

// login page
document.getElementById("loginForm").addEventListener("submit", async function(event) {
    event.preventDefault(); // Prevent default form submission

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    try {
        const response = await fetch(`${API_BASE_URL}/login/auth`, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json', // Explicitly set JSON
                'Accept': 'application/json' // Ensure server returns JSON
            },
            body: JSON.stringify({ username, password }) // Convert to JSON
        });

        if (!response.ok) {
            throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const data = await response.json();

        if (data.token) {
            localStorage.setItem('token', data.token);
            window.location.href = `${API_BASE_URL}/`; // Redirect to home page
        } else {
            alert(data.message);
        }
    } catch (error) {
        console.error('Login failed:', error);
    }
});

// JavaScript for MCQ functionality
document.addEventListener('DOMContentLoaded', function () {
    const options = document.querySelectorAll('.mcq-option');
    const submitBtn = document.getElementById('submit-btn');

    let selectedOption = null;

    // Add click event listeners to options
    options.forEach(option => {
        option.addEventListener('click', function () {
        // Remove selected class from all options
        options.forEach(opt => opt.classList.remove('selected'));

        // Add selected class to the clicked option
        this.classList.add('selected');
        selectedOption = this.getAttribute('data-option');
        });
    });

    // Add click event listener to submit button
    submitBtn.addEventListener('click', function () {
        if (selectedOption) {
            alert(`You selected option ${selectedOption}`);
        } else {
            alert('Please select an option before submitting.');
        }
    });
});

// passing user data to homepage by session
document.addEventListener("DOMContentLoaded", async () => {
    const authButtons = document.getElementById("auth-buttons");
    const userButtons = document.getElementById("user-buttons");
    const usernameText = document.getElementById("username-text");

    try {
        const response = await fetch("/session", {
            headers: { "X-Requested-With": "XMLHttpRequest" } // Tell server this is an AJAX request
        });

        const data = await response.json();

        if (data.username) {
            // Show username and logout button
            authButtons.classList.add("d-none");
            userButtons.classList.remove("d-none");
            usernameText.textContent = data.username; // Update username text
        } else {
            // Show login and signup button
            authButtons.classList.remove("d-none");
            userButtons.classList.add("d-none");
        }
    } catch (error) {
        console.error("Error fetching session:", error);
    }
});

//result page function
// Sample data for demonstration
const totalQuestions = 10;
const correctAnswers = 8;

// Calculate the score
const score = `${correctAnswers}/${totalQuestions}`;

// Display the score
document.getElementById("score").textContent = score;

// Display a fun message and emoji based on the score
const resultMessage = document.getElementById("result-message");
const emoji = document.getElementById("emoji");

if (correctAnswers === totalQuestions) {
    resultMessage.textContent = "Perfect score! You're a math wizard! 🧙‍♂️";
    emoji.textContent = "🏆";
} else if (correctAnswers >= totalQuestions * 0.8) {
    resultMessage.textContent = "Great job! You did amazing!";
    emoji.textContent = "🎉";
} else if (correctAnswers >= totalQuestions * 0.5) {
    resultMessage.textContent = "Not bad! Keep practicing!";
    emoji.textContent = "👍";
} else {
    resultMessage.textContent = "Oops! Better luck next time!";
    emoji.textContent = "😅";
}