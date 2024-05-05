document.addEventListener('DOMContentLoaded', function() {
    fetchEntertainmentData();
});

function fetchEntertainmentData() {
    fetch('http://localhost:8081/entertainment')
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
                    <td><button type="button" class="btn btn-outline-info">Edit</button></td>
                    <td><button type="button" class="btn btn-outline-danger">Delete</button></td>
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
