document.addEventListener("DOMContentLoaded", function () {
    if (sessionStorage.getItem("role") == "PASSENGER") {
        document.getElementById("flatOwnerSection").style.display = "none";
    } else {
        var ok = false;
        fetch(`http://localhost:9090/api/profile/flats`, {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${sessionStorage.getItem("accessToken")}`,
                "Content-Type": "application/json",
            },
        })
            .then((response) => {
                if (!response.ok) {
                    alert('Invalid Credentials');
                } else {
                    ok = true;
                }
                return response.json();
            })
            .then((data) => {
                console.log(data);
                if (ok) {
                    const tableBody = document.querySelector("#flatOwnerTable tbody");
                    tableBody.innerHTML = '';

                    data.forEach(item => {
                        console.log(item);
                        const row = document.createElement('tr');
                        row.innerHTML = `<tr flat-id="${item.flatId}">
                                    <td>${item.flatId}</td>
                                    <td>${item.address}</td>
                                    <td>${item.flatDescription}</td>
                                    <td>${item.countryName}</td>
                                    <td>${item.capacity}</td>
                                    <td>${item.price}</td>
                                    <td>${item.flatImage}</td>
                                    <td>
                                        <button type="button" class="delete-btn btn btn-outline-danger">Delete</button>
                                    </td>
                                    `;
                        tableBody.appendChild(row);

                        row.querySelector('.delete-btn').addEventListener('click', function () {
                                const flatId = item.flatId;
                                fetch(`http://localhost:9090/api/flats/${flatId}`, {
                                    method: 'DELETE',
                                    headers: {
                                        "Authorization": `Bearer ${sessionStorage.getItem("accessToken")}`,
                                        "Content-Type": "application/json",
                                    },
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
                }
            })
            .catch((error) => {
                console.error(
                    "There was a problem with the fetch operation:",
                    error
                );
            });
    }
    var ok = false;
    fetch(`http://localhost:9090/api/userData`, {
        method: "POST",
        headers: {
            "Authorization": `Bearer ${sessionStorage.getItem("accessToken")}`,
            "Content-Type": "application/json",
        },
    })
        .then((response) => {
            if (!response.ok) {
                alert('Invalid Credentials');
            } else {
                ok = true;
            }
            return response.json();
        })
        .then((data) => {
            console.log(data);
            if (ok) {
                document.getElementById("name").innerHTML = data.name;
                document.getElementById("email").innerHTML = data.email;
                document.getElementById("nationalId").innerHTML = data.nationalId;
                document.getElementById("nationality").innerHTML = data.nationality;
                document.getElementById("gender").innerHTML = data.gender;
                document.getElementById("age").innerHTML = data.age;
                document.getElementById("loyaltyPoints").innerHTML = data.loyaltyPoints;
            }
        })
        .catch((error) => {
            console.error(
                "There was a problem with the fetch operation:",
                error
            );
        });

    document.getElementById("updateUser").addEventListener("submit", function () {
        var name = document.getElementById("updateName").value;
        var email = document.getElementById("updateEmail").value;
        var nationality = document.getElementById("updateNationality").value;
        var age = new Date(document.getElementById("updateAge").value);
        var password = document.getElementById("updatePassword").value;

        age = calculateAge(age);

        const updateData = {
            name: name,
            email: email,
            nationality: nationality,
            age: age,
            password: password
        }

        updateUser(updateData);
    })
});

function calculateAge(birthday) {
    var ageDifMs = Date.now() - birthday.getTime();
    var ageDate = new Date(ageDifMs);
    return Math.abs(ageDate.getUTCFullYear() - 1970);
}

function updateUser(params) {
    let ok = false;
    fetch(`http://localhost:9090/accounts/update`, {
        method: "PUT",
        headers: {
            "Authorization": `Bearer ${sessionStorage.getItem("accessToken")}`,
            "Content-Type": "application/json",
        },
        body: JSON.stringify(params),
    })
        .then((response) => {
            if (!response.ok) {
                alert('Invalid Credentials');
            } else {
                ok = true;
            }
            return response.json();
        })
        .then((data) => {
            console.log(data);
            if (ok) {
                alert('Update Successful');
            }
        })
        .catch((error) => {
            console.error(
                "There was a problem with the fetch operation:",
                error
            );
        });
}
