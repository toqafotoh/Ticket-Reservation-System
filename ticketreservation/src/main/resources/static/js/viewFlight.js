document.addEventListener("DOMContentLoaded", function() {
    fetch("http://localhost:8081/flights")
        .then(response => response.json())
        .then(data => {
            const tableBody = document.getElementById("flight-table-body");
            tableBody.innerHTML = "";

            data.forEach(flight => {
                const row = document.createElement("tr");
                row.setAttribute("data-flight-id", flight.flightNumber);
                row.innerHTML = `
                    <th scope="row">${flight.flightNumber}</th>
                    <td>${flight.origin}</td>
                    <td>${flight.destination}</td>
                    <td>${flight.flightType}</td>
                    <td>${new Date(flight.flightStartTime).toLocaleDateString()}</td>
                    <td>${new Date(flight.flightStartTime).toLocaleTimeString()}</td>
                    <td>${new Date(flight.flightEndTime).toLocaleTimeString()}</td>
                    <td>${flight.price}</td>
                    <td>${flight.flightClass}</td>
                    <td>${flight.airline}</td>
                    <td>${flight.availableSeats}</td>
                    <td>
                        <button type="button" class="btn btn-outline-info">Edit</button>
                    </td>
                    <td>
                        <button type="button" class="delete-btn btn btn-outline-danger">Delete</button>
                    </td>
                `;
                tableBody.appendChild(row);
            });

            document.querySelectorAll('.delete-btn').forEach(button => {
                button.addEventListener('click', function() {
                    const flightId = this.closest('tr').getAttribute('data-flight-id');
                    fetch(`http://localhost:8081/flights/${flightId}`, {
                        method: 'DELETE'
                    })
                    .then(response => {
                        if (response.ok) {
                            response.text().then(message => {
                                console.log(message);
                                this.closest('tr').remove();
                            });
                        } else {
                            response.text().then(message => {
                                console.error('Failed to delete flight:', message);
                            });
                        }
                    })
                    .catch(error => console.error('Error deleting flight:', error));
                });
            });
        })
        .catch(error => console.error("Error fetching flights:", error));
});
