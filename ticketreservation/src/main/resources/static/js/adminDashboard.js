document.addEventListener('DOMContentLoaded', function() {
    fetchDashboardData();
    fetchUsersData();
});

function fetchDashboardData() {
    fetch('http://localhost:8081/flights/count')
    .then(response => response.json())
    .then(data => {
        document.getElementById('flights').textContent = data;
    });

    fetch('http://localhost:8081/payments/total')
    .then(response => response.json())
    .then(data => {
        document.getElementById('earning').textContent = '$' + data.toFixed(2);
    });

    fetch('http://localhost:8081/feedback/count')
    .then(response => response.json())
    .then(data => {
        document.getElementById('feedback').textContent = data;
    });
}

function fetchUsersData() {
    fetch('http://localhost:8081/users')
    .then(response => response.json())
    .then(data => {
        const tableBody = document.getElementById('users');
        tableBody.innerHTML = '';
        data.forEach(user => {
            const row = `<tr data-user-id="${user.nationalId}">
                            <td>${user.nationalId}</td>
                            <td>${user.nationality}</td>
                            <td>${user.name}</td>
                            <td>${user.age}</td>
                            <td>${user.gender}</td>
                            <td>${user.email}</td>
                            <td>${user.role}</td>
                            <td>
                                <button type="button" class="delete-btn btn btn-outline-danger" style="border-radius: 25px">Delete</button>
                            </td>
                        </tr>`;
            tableBody.innerHTML += row;
        });

        document.querySelectorAll('.delete-btn').forEach(button => {
            button.addEventListener('click', function() {
                const userId = this.closest('tr').getAttribute('data-user-id');
                fetch(`http://localhost:8081/users/delete/${userId}`, {
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
                            console.error('Failed to delete user:', message);
                        });
                    }
                })
                .catch(error => console.error('Error deleting user:', error));
            });
        });
    })
    .catch(error => {
        console.error('Error fetching users data:', error);
    });
}
