document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("addflatsFrom").addEventListener("submit", function () {
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
                    data.forEach(item => {
                        const tableBody = document.querySelector("#flatOwnerTable tbody");
                        console.log(item);
                        const row = document.createElement('tr');
                        row.innerHTML = `
                                        <td>${item.address}</td>
                                        <td>${item.flatDescription}</td>
                                        <td>${item.countryName}</td>
                                        <td>${item.capacity}</td>
                                        <td>${item.price}</td>
                                        <td>${item.flatImage}</td>
                                        `;
                        tableBody.appendChild(row);
    
                    });
                }
            })
            .catch((error) => {
                console.error(
                    "There was a problem with the fetch operation:",
                    error
                );
            });
    })
})
