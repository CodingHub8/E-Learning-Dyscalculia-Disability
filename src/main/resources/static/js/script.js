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
document.getElementById("loginForm").addEventListener("submit", function(event) {
    event.preventDefault(); // Prevent default form submission

    const username = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    fetch('http://localhost:8080/login/auth', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' }, // Send as JSON
        body: JSON.stringify({ username: username, password: password }) // Convert to JSON
    })
        .then(response => response.json())
        .then(data => {
            if (data.token) {
                localStorage.setItem('token', data.token);
                window.location.href = "index.html"; // Redirect to home page
            } else {
                alert(data.message);
            }
        })
        .catch(error => console.error('Error:', error));
});
