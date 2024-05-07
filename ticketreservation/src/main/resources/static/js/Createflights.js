const tripTypeSelect = document.getElementById('trip-type');
const returnDateContainer = document.getElementById('return-date-container');
const returnDateInput = document.getElementById('return-date');
const returnStartTimeContainer = document.getElementById('return-start-time-container');
const returnStartTimeInput = document.getElementById('return-start-time');
const returnEndTimeContainer = document.getElementById('return-end-time-container');
const returnEndTimeInput = document.getElementById('return-end-time');

tripTypeSelect.addEventListener('change', function () {
    if (this.value === 'rt') {
        returnDateContainer.style.display = 'block';
        returnStartTimeContainer.style.display = 'block';
        returnEndTimeContainer.style.display = 'block';
    } else {
        returnDateContainer.style.display = 'none';
        returnDateInput.value = '';
        returnStartTimeContainer.style.display = 'none';
        returnStartTimeInput.value = '';
        returnEndTimeContainer.style.display = 'none';
        returnEndTimeInput.value = '';
    }
});

document.addEventListener("DOMContentLoaded", function () {
    const form = document.getElementById('InfAdmin');

    form.addEventListener('submit', function (event) {
        event.preventDefault();
        const flightNumber = document.getElementById('validationCustom01').value;
        const origin = document.getElementById('validationCustom02').value;
        const destination = document.getElementById('validationState').value;
        const date = document.getElementById('dateInput').value;
        const time = document.getElementById('time').value;
        const endTime = document.getElementById('endTime').value;
        const price = document.getElementById('Price').value;
        const availableSeats = document.getElementById('validationNationalId').value;
        const flightClass = document.getElementById('Flight Class').value;
        const airline = document.getElementById('airline').value;
        const flightType = document.getElementById('trip-type').value;
        const returnDate = document.getElementById('return-date').value;
        const returnStartTime = document.getElementById('return-start-time').value;
        const returnEndTime = document.getElementById('return-end-time').value;

        // Check if any date field includes a past date
        const currentDate = new Date();
        if (new Date(date) < currentDate || new Date(returnDate) < currentDate) {
            alert('Date must be in the future');
            return;
        }

        // Check if price is not a number or is negative
        if (isNaN(price) || parseFloat(price) < 0) {
            alert('Price must be a positive number');
            return;
        }

        if (flightType === 'ow') {
            const flightData = {
                "flightNumber": flightNumber,
                "flightType": flightType,
                "flightClass": flightClass,
                "origin": origin,
                "destination": destination,
                "flightStartTime": `${date}T${time}`,
                "flightEndTime": `${date}T${endTime}`,
                "availableSeats": parseInt(availableSeats),
                "airline": airline,
                "price": parseFloat(price),
                "flightImage": "jj"
            };

            fetch("http://localhost:9090/flights", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Origin': 'http://localhost:63342'
                },
                body: JSON.stringify(flightData)
            })
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        throw new Error('Failed to add flight');
                    }
                })
                .then(data => {
                    if (data === true) {
                        alert('Flight added successfully');
                    } else {
                        alert('Flight ID already exists');
                    }
                })
                .catch(error => {
                    console.error('Error adding flight:', error);
                    alert('Failed to add flight');
                });
        } else {
            const flightData = {
                "flightNumber": flightNumber,
                "flightType": flightType,
                "flightClass": flightClass,
                "origin": origin,
                "destination": destination,
                "flightStartTime": `${date}T${time}`,
                "flightEndTime": `${date}T${endTime}`,
                "returnFlightStartTime": `${returnDate}T${returnStartTime}`,
                "returnFlightEndTime": `${returnDate}T${returnEndTime}`,
                "availableSeats": parseInt(availableSeats),
                "airline": airline,
                "price": parseFloat(price),
                "flightImage": "jj"
            };

            fetch("http://localhost:9090/round-trip-flights", {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                    'Origin': 'http://localhost:63342'
                },
                body: JSON.stringify(flightData)
            })
                .then(response => {
                    if (response.ok) {
                        return response.json();
                    } else {
                        throw new Error('Failed to add flight');
                    }
                })
                .then(data => {
                    if (data === true) {
                        alert('Flight added successfully');
                    } else {
                        alert('Flight ID already exists');
                    }
                })
                .catch(error => {
                    console.error('Error adding flight:', error);
                    alert('Failed to add flight');
                });
        }
    });
});
