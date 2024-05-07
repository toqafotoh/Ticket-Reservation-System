

$(document).ready(function() {
     // Parse the query string parameters from the URL
     var urlParams = new URLSearchParams(window.location.search);
     var wantedTickets = urlParams.get('wantedTickets'); // Get the value of the 'flightNo' parameter
     
     // Check if the flight number is present in the URL
    function getFlight() {
        $.ajax({
            url: "http://localhost:9090/flights/result",
            type: "GET",
            dataType: "json",
            success: function(data) {
                if ($.isEmptyObject(data)) {
                    // If no flight data, display a message
                    $('#removedflight').remove(); // Clear the existing content
                    $('.nodataToshow').text("No matching flights to show")
                } else {
                     
                    var $template = $('.flight'); 

                    $('#all').empty(); 

                    $.each(data, function(index, flight) {
                        var $clonedFlight = $template.clone(); // Clone the template
                        
                        $clonedFlight.find('.origin').text(flight.origin);
                        $clonedFlight.find('.destination').text(flight.destination);
                        $clonedFlight.find('.flightStartTime').text(flight.flightStartTime);
                        $clonedFlight.find('.flightEndTime').text(flight.flightEndTime);
                        if (flight.flightType === "ow") {
                            $clonedFlight.find('.flightType').text("One Way");
                        } else if (flight.flightType === "rt") {
                            $clonedFlight.find('.flightType').text("Round Trip");
                        }
                        $clonedFlight.find('.flightClass').text(flight.flightClass);
                        $clonedFlight.find('.availableSeats').text(flight.availableSeats);
                        $clonedFlight.find('.price').text(flight.price);
                        $clonedFlight.find('.airline').text(flight.airline);

                        $clonedFlight.find('#flightChoice').attr('href', 'http://localhost:9090/Ticket/submitBooking.html?flightNumber=' + flight.flightNumber+'&wantedTickets='+wantedTickets);

                       
                        $('#all').append($clonedFlight);
                    });
                }
            },
            error: function() {
                console.log('Error fetching flight details');
            }
        });
    }
    getFlight();
});
