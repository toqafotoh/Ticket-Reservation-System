document.addEventListener('DOMContentLoaded', function() {
    fetchEntertainmentData();
});

function fetchEntertainmentData() {
    fetch('http://localhost:9090/entertainment')
    .then(response => response.json())
    .then(data => {
        const tableBody = document.getElementById('entertainmentTableBody');
        tableBody.innerHTML = '';
        data.forEach(entertainment => {
            const row = `
                <tr>
                    <td>${entertainment.entertainmentTourId}</td>
                    <td>${new Date(entertainment.time).toLocaleDateString()}</td>
                    <td>${new Date(entertainment.time).toLocaleTimeString()}</td>
                    <td>${entertainment.price}</td>
                    <td>${entertainment.destination}</td>
                    <td><button type="button" class="btn btn-outline-info" onclick="editEntertainment(${entertainment.entertainmentTourId})">Edit</button></td>
                    <td><button type="button" class="btn btn-outline-danger" onclick="deleteEntertainment(${entertainment.entertainmentTourId})">Delete</button></td>
                </tr>
            `;
            tableBody.innerHTML += row;
        });
        // Apply text-align: center; to all table cells
        const tableCells = document.querySelectorAll('#entertainmentTableBody td');
        tableCells.forEach(cell => {
            cell.style.textAlign = "center";
        });
    })
    .catch(error => {
        console.error('Error fetching entertainment data:', error);
    });
}

function editEntertainment(id) {
    fetch(`http://localhost:9090/entertainment/${id}`)
    .then(response => response.json())
    .then(data => {
        document.getElementById('validationCustom01').value = data.entertainmentTourId;
        document.getElementById('validationCustom02').value = data.destination;
        //document.getElementById('validationCustom03').value = new Date(data.time).toLocaleTimeString();
        document.getElementById('validationCustom04').value = data.price;
        //document.getElementById('dateInput').value = new Date(data.time).toLocaleDateString();
        document.getElementById('validationAge').value = data.description;

        // Show the edit form
        document.querySelector('.card').style.display = 'block';
    })
    .catch(error => {
        console.error('Error fetching entertainment data:', error);
    });
}

function deleteEntertainment(id) {
    fetch(`http://localhost:9090/entertainment/${id}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            fetchEntertainmentData();
        } else {
            console.error('Failed to delete entertainment');
        }
    })
    .catch(error => {
        console.error('Error deleting entertainment:', error);
    });
}

document.getElementById('editForm').addEventListener('submit', function(event) {
    event.preventDefault();
    submitEditForm(event);
});

function submitEditForm(event) {
    event.preventDefault();
    const id = document.getElementById('validationCustom01').value;
    const price = document.getElementById('validationCustom04').value;
    const destination = document.getElementById('validationCustom02').value;
    const description = document.getElementById('validationAge').value;
    fetch(`http://localhost:9090/entertainment/${id}`)
        .then(response => response.json())
        .then(data => {
            if (parseInt(id) !== data.entertainmentTourId) {
                alert('Cannot change the ID. Please reset the field to the previous value.');
                return;
            }
            if (isNaN(price)) {
                alert('Price must be a number.');
                return;
            }

            if (price < 0) {
                alert('Price must not be negative.');
                return;
            }

            const updatedEntertainment = {
                entertainmentTourId: id,
                price: price,
                destination: destination,
                description: description
            };

            fetch(`http://localhost:9090/entertainment/${id}`, {
                method: 'PUT',
                headers: {
                    'Content-Type': 'application/json'
                },
                body: JSON.stringify(updatedEntertainment)
            })
                .then(response => response.json())
                .then(data => {
                    alert('Entertainment updated successfully.');
                    fetchEntertainmentData();
                     window.location.reload();
                })
                .catch(error => {
                    console.error('Error updating entertainment:', error);
                });
        })
        .catch(error => {
            console.error('Error fetching entertainment data:', error);
        });
}

