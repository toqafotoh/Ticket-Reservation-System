document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('InfAdmin').addEventListener('submit', function(event) {
        event.preventDefault(); // Prevent default form submission

        const form = event.target;
        const entertainment = {
            description: form.querySelector('#validationCustom02').value,
            datetime: form.querySelector('#dateInput').value + 'T' + form.querySelector('#time').value,
            price: form.querySelector('#price').value,
            destination: form.querySelector('#destination').value
        };

        fetch('http://localhost:8081/admin/entertainment', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(entertainment)
        })
        .then(response => response.json())
        .then(result => {
            if (result) {
                alert('Entertainment added successfully');
                form.reset(); // Reset the form after successful submission
            } else {
                alert('Failed to add entertainment');
            }
        })
        .catch(error => {
            console.error('Error adding entertainment:', error);
            alert('Failed to add entertainment');
        });
    });
});