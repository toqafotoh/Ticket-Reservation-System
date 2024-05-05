document.addEventListener("DOMContentLoaded", function() {
    fetch("http://localhost:8081/feedbacks")
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById("feedback-table");
            tableBody.innerHTML = "";

          data.forEach(feedback => {
            const row = document.createElement("tr");
             row.style.textAlign = "center";
            row.innerHTML = `
              <td>${feedback.feedbackId}</td>
              <td>${feedback.passenger.name}</td>
              <td>${new Date(feedback.feedbackDate).toLocaleDateString()}</td>
              <td><a href="./ViewFeedBack.html?id=${feedback.feedbackId}" class="btn btn-outline-info">See</a></td>
            `;
            tableBody.appendChild(row);
          });
        })
        .catch(error => console.error("Error fetching feedbacks:", error));
    });