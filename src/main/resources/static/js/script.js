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

    fetch('http://localhost:8080/api/auth/login', {
        method: 'POST',
        headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
        body: new URLSearchParams({ username: username, password: password }) // Send user input
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
