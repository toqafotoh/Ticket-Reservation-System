document.addEventListener("DOMContentLoaded", function () {
    document.getElementById("addflatsFrom").addEventListener("submit", function () {

        var address = document.getElementById("addressInput").value;
        var flatDescription = document.getElementById("descriptionInput").value;
        var countryName = document.getElementById("countryInput").value;
        var capacity = document.getElementById("capacityInput").value;
        var price = document.getElementById("priceInput").value;
        var flatImage = document.getElementById("imageInput").value;

        const flatData = {
            address: address,
            flatDescription: flatDescription,
            countryName: countryName,
            capacity: capacity,
            price: price,
            flatImage: flatImage
        }

        fetch(`http://localhost:9090/api/flats`, {
            method: "POST",
            headers: {
                "Authorization": `Bearer ${sessionStorage.getItem("accessToken")}`,
                "Content-Type": "application/json",
            },
            body: JSON.stringify(flatData)
        })
            .then((response) => {
                if (!response.ok) {
                    alert('Invalid Credentials');
                } else {
                    alert("Flat Added Successfully");
                }
                return response.json();
            })
            .catch((error) => {
                console.error(
                    "There was a problem with the fetch operation:",
                    error
                );
            });
    })
})
