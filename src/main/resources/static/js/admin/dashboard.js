document.addEventListener("DOMContentLoaded", function () {

      // Sample data for quiz performance chart
      const ctx = document.getElementById('quizChart').getContext('2d');
      new Chart(ctx, {
          type: 'bar',
          data: {
              labels: ['Easy', 'Medium', 'Hard'],
              datasets: [{
                  label: 'Questions Answered',
                  data: [50, 45, 25],
                  backgroundColor: ['#007bff', '#ffc107', '#dc3545']
              }]
          },
          options: {
              responsive: true,
              scales: {
                  y: { beginAtZero: true }
              }
          }
      });
     
            const sidebar = document.getElementById('sidebar');
            const toggleBtn = document.getElementById('toggleBtn');
            const overlay = document.getElementById('overlay');

            toggleBtn.addEventListener('click', function () {
                sidebar.classList.toggle('open');
                overlay.classList.toggle('show');
                
                // Hide toggle button when sidebar opens
                if (sidebar.classList.contains('open')) {
                    toggleBtn.classList.add('hidden');
                } else {
                    toggleBtn.classList.remove('hidden');
                }
            });

            // Close sidebar when clicking on overlay
            overlay.addEventListener('click', function () {
                sidebar.classList.remove('open');
                overlay.classList.remove('show');
                
                // Show toggle button again when sidebar closes
                toggleBtn.classList.remove('hidden');
            });


                fetch("http://localhost:8080/api/dashboard")
        .then(response => response.json())
        .then(data => {
            document.getElementById("total-questions").textContent = data.totalQuestions;
            document.getElementById("total-users").textContent = data.totalUsers;
            document.getElementById("quizzes-attempted").textContent = data.quizzesAttempted;
            document.getElementById("avg-score").textContent = data.avgScore + "%";
        })
        .catch(error => console.error("Error fetching dashboard data:", error));
});

