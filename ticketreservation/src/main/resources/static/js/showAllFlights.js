

$(document).ready(function() {
    
    // Check if the flight number is present in the URL
   function getFlight() {
       $.ajax({
           url: "http://localhost:8080/flights",
           type: "GET",
           dataType: "json",
           success: function(data) {
               if ($.isEmptyObject(data)) {
                   // If no flight data, display a message
                   $('#removedflight').remove(); // Clear the existing content
                   $('.nodataToshow').text("No flights to show")
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

                       $clonedFlight.find('#flightChoice').attr('href', 'http://localhost:8080/Ticket/flightForm.html?flightNumber=' + flight.flightNumber);

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
