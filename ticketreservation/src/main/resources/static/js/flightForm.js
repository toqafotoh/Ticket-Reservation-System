$(document).ready(function() { //done
    // Function to fetch flight details from the backend API
    function fetchFlightDetails() {
        // Parse the query string parameters from the URL
        var urlParams = new URLSearchParams(window.location.search);
        var flightNumber = urlParams.get('flightNumber');
        var wantedTickets = urlParams.get('wantedTickets'); // Get the value of the 'flightNo' parameter
         
        
        // Check if the flight number is present in the URL
        if (flightNumber) {
            $.ajax({
                url: "http://localhost:8080/flights/" + flightNumber,
                type: "GET",
                dataType: "json",
                success: function(data) {
                    // Update the HTML content with the retrieved flight details
                    if(data.flightType == "ow")
                        {
                            $('.flight_type').text("One Way");
                        }
                    else if(data.flightType == "rt")
                       {
                        $('.flight_type').text("RoundTrip");
                       }    
                    
                    $('.origin').text(data.origin);
                    $('.dest').text(data.destination);
                    $('.departing').text(data.flightStartTime);
                    $('.returning').text(data.flightEndTime);
                    $('.no_of_tickets').text(data.availableSeats);
                    $('.flight_class').text(data.flightClass);
                    $('.flight_code').text(data.flightNumber);
                    //tickets = data.availableSeats;
                    tickets = wantedTickets;
                    flightnum = data.flightNumber;
                    origin1 = data.origin;
                    dest1 = data.destination;
                    //console.log(tickets);
                    console.log(data);

                },
                error: function() {
                    console.log('Error fetching flight details');
                }
            });
        } else {
            console.log('Flight number not found in URL');
        }
    }

    // Call the fetchFlightDetails function to retrieve flight details based on the URL parameter
    fetchFlightDetails();
    
});

$(document).ready(function() {
    $('#book-flight').submit(function(event) {
        event.preventDefault(); 
        var flightNumber = $('.flight_code').text();
        var wantedTickets = $('.tickets').val();
        console.log(flightNumber);
        console.log(wantedTickets);
        window.location.href = "http://localhost:8080/Ticket/submitBooking.html?flightNumber=" + flightNumber+'&wantedTickets='+wantedTickets;

    });
});
