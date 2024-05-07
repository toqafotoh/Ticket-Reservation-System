$(document).ready(function() {
    // Function to fetch all flight details from the backend API
    function fetchAllFlights() {
        $.ajax({                       //TO TEST
            url: "http://localhost:9090/flights", // Endpoint to fetch all flights
            type: "GET",
            dataType: "json",
            success: function(data) {
                // Loop through the list of flights
                data.forEach(function(flight, index) {
                    // Create a new <div> element for each flight
                    var flightDiv = $('<div>').attr('id', 'flight_' + index).addClass('flight');
                    
                    // Append flight details to the <div> element
                    flightDiv.append('<p>Flight number: ' + flight.flightNumber + '</p>');    
                    flightDiv.append('<p>Flight Type: ' + flight.flightType + '</p>');
                    flightDiv.append('<p>Origin: ' + flight.origin + '</p>');
                    flightDiv.append('<p>Destination: ' + flight.dest + '</p>');
                    flightDiv.append('<p>Departure Date: ' + flight.flightStartTime + '</p>');
                    flightDiv.append('<p>Return Date: ' + flight.flightEndTime + '</p>');
                    flightDiv.append('<p>No. of Tickets: ' + flight.availableSeats + '</p>');
                    flightDiv.append('<p>Flight Class: ' + flight.flightClass + '</p>');
                    flightDiv.append('<p>Flight Price: ' + flight.price + '</p>');
                    flightDiv.append('<br />');

                    var viewTicketLink = $('<a>').attr('href', 'submitBooking.html?flightNumber=' + flight.flightNumber).text('View Flight');
                    // viewTicketLink.click(function() {
                    //     var selectedFlightNo = flight.flightNo;
                    //     window.location.href = 'submitBooking.html?flightNo=' + selectedFlightNo;
                    
                    // });
                    flightDiv.append(viewTicketLink);
                    // Append the <div> element to the HTML body or any other container
                    $('#flights').append(flightDiv);

                    // Add click event listener to the flight div
                    
                });
            },
            error: function() {
                console.log('Error fetching flight details');
            }
        });
    }

    // Call the fetchAllFlights function to retrieve all flights
    fetchAllFlights();
});


var l = 10 ;