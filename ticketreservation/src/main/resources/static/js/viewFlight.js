document.addEventListener('DOMContentLoaded', function() {
    fetchFlightData();
});

function fetchFlightData() {
    fetch('http://localhost:9090/flights')
    .then(response => response.json())
    .then(data => {
        const tableBody = document.getElementById('flight-table-body');
        tableBody.innerHTML = '';
        data.forEach(flight => {
            const row = `
                <tr>
                    <td>${flight.flightNumber}</td>
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
                    <td><button type="button" class="btn btn-outline-info" onclick="editFlight('${flight.flightNumber}')">Edit</button></td>
                    <td><button type="button" class="btn btn-outline-danger" onclick="deleteFlight('${flight.flightNumber}')">Delete</button></td>
                </tr>
            `;
            tableBody.innerHTML += row;
        });
        // Apply text-align: center; to all table cells
        const tableCells = document.querySelectorAll('#flight-table-body td');
        tableCells.forEach(cell => {
            cell.style.textAlign = "center";
        });
    })
    .catch(error => {
        console.error('Error fetching flight data:', error);
    });
}

function editFlight(flightNumber) {
    fetch(`http://localhost:9090/flights/${flightNumber}`)
    .then(response => response.json())
    .then(data => {
        document.getElementById('validationCustom01').value = data.flightNumber;
        document.getElementById('validationCustom02').value = data.origin;
        document.getElementById('validationState').value = data.destination;
        document.getElementById('dateInput').value = data.flightStartTime.substring(0, 10);
        document.getElementById('time').value = data.flightStartTime.substring(11, 16);
        document.getElementById('Price').value = data.price;
        document.getElementById('validationNationalId').value = data.availableSeats;
        document.getElementById('Flight Class').value = data.flightClass;
        document.getElementById('airline').value = data.airline;

        // Show the edit form
        document.querySelector('.card').style.display = 'block';
    })
    .catch(error => {
        console.error('Error fetching flight data:', error);
    });
}

function deleteFlight(flightNumber) {
    fetch(`http://localhost:9090/flights/${flightNumber}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            fetchFlightData();
        } else {
            console.error('Failed to delete flight');
        }
    })
    .catch(error => {
        console.error('Error deleting flight:', error);
    });
}

document.getElementById('editForm').addEventListener('submit', function(event) {
    event.preventDefault();
    submitEditForm(event);
});
function submitEditForm(event) {
    event.preventDefault();
    const flightNumber = document.getElementById('validationCustom01').value;
    const price = document.getElementById('Price').value;
    const destination = document.getElementById('validationState').value;
    const flightStartTime = document.getElementById('dateInput').value + "T" + document.getElementById('time').value + ":00";
    const availableSeats = document.getElementById('validationNationalId').value;
    const origin = document.getElementById('validationCustom02').value;
    const flightClass = document.getElementById('Flight Class').value;
    const airline = document.getElementById('airline').value;

    fetch(`http://localhost:9090/flights/${flightNumber}`)
        .then(response => response.json())
        .then(data => {
            if (flightNumber !== data.flightNumber) {
                alert('Cannot change the flight number. Please reset the field to the previous value.');
                return;
            }
            if (isNaN(price) || price < 0) {
                alert('Price must be a positive number.');
                return;
            }

            const currentDate = new Date();
            const selectedDate = new Date(flightStartTime);
            if (selectedDate < currentDate) {
                alert('Flight date must be in the future.');
                return;
            }

            const updatedFlight = {
                flightNumber: flightNumber,
                price: price,
                destination: destination,
                origin: origin,
                flightStartTime: flightStartTime,
                availableSeats: availableSeats,
                flightClass: flightClass,
                airline: airline
            };

            fetch(`http://localhost:9090/flights/${flightNumber}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedFlight)
            })
                .then(response => {
                    if (response.ok) {
                        alert('Flight updated successfully.');
                        fetchFlightData();
                        window.location.reload();
                    } else {
                        console.error('Failed to update flight');
                    }
                })
                .catch(error => {
                    console.error('Error updating flight:', error);
                });
        })
        .catch(error => {
            console.error('Error fetching flight data:', error);
        });
}
