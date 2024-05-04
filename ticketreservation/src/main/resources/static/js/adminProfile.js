document.addEventListener('DOMContentLoaded', function() {
        const urlParams = new URLSearchParams(window.location.search);
        const nationalId = urlParams.get('nationalId');
        if (nationalId) {
        fetch(`http://localhost:8081/admin/accounts/${nationalId}`)
        .then(response => response.json())
        .then(account => {
        document.getElementById('nationalId').value = account.nationalId;
        document.getElementById('name').value = account.name;
        document.getElementById('nationality').value = account.nationality;
        document.getElementById('email').value = account.email;
        document.getElementById('age').value = account.age;
        document.getElementById('gender').value = account.gender;
        })
        .catch(error => {
        console.error('Error fetching account data:', error);
        });
        }
        });

        function updateAccount(event) {
    event.preventDefault();
    const urlParams = new URLSearchParams(window.location.search);
    const originalNationalId = urlParams.get('nationalId');
    const nationalId = document.getElementById('nationalId').value;
    const name = document.getElementById('name').value;
    const nationality = document.getElementById('nationality').value;
    const email = document.getElementById('email').value;
    const age = document.getElementById('age').value;
    const gender = document.getElementById('gender').value;

    if (originalNationalId !== nationalId) {
        alert('You cannot change your National ID');
         document.getElementById('nationalId').value = originalNationalId;
        return;
    }

    fetch(`http://localhost:8081/admin/accounts/${originalNationalId}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            nationalId: nationalId,
            name: name,
            nationality: nationality,
            email: email,
            age: age,
            gender: gender
        })
    })
    .then(response => response.json())
    .then(updatedAccount => {
        alert('Your account had been updated');
    })
    .catch(error => {
    alert('Error updating account');
    });
}
