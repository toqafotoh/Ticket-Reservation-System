document.addEventListener("DOMContentLoaded", function() {
    fetch("http://localhost:8081/admin/payments")
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById("payment-table-body");
            tableBody.innerHTML = "";

            data.forEach(payment => {
                const row = document.createElement("tr");
                row.style.textAlign = "center";
                row.innerHTML = `
                    <td>${payment.paymentId}</td>
                    <td>${payment.passenger.nationalId}</td>
                    <td>${payment.totalAmount}</td>
                    <td>${new Date(payment.transactionDate).toLocaleDateString()}</td>
                `;
                tableBody.appendChild(row);
            });
        })
        .catch(error => console.error("Error fetching payments:", error));
});
