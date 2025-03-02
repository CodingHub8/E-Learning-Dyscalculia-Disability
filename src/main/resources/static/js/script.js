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
            window.location.href = response.url; // Redirect to home page
        } else {
            console.error(data.message);
        }
    } catch (error) {
        console.error('Login failed:', error);
    }
});
