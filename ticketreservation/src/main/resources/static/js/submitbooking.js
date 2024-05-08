console.log(l);

function changeColor(){
    var elements1 = document.getElementsByClassName('from');
    var elements2 = document.getElementsByClassName('from-b');
    var elements3 = document.getElementsByClassName('inputt');

    changeBackgroundColor(elements1);
    changeBackgroundColor(elements2);
    changeBackgroundColor(elements3);


    var elements4 = document.getElementsByClassName('transform-color');

    // Loop through each element and change its background color
    for (var i = 0; i < elements4.length; i++) {
        // Check current background color
        if (elements4[i].style.backgroundColor === 'white') {
            // If background color is black, change it to white
            elements4[i].style.backgroundColor = 'black';
        } else {
            // If background color is white, change it to black
            elements4[i].style.backgroundColor = 'white';
        }
    }
}
// Function to change background color of elements with class 'change-bg'
function changeBackgroundColor(element) {

    // Loop through each element and change its text color to white
    // Loop through each element and change its background color
    for (var i = 0; i < element.length; i++) {
        // Check current background color
        if (element[i].style.color === 'black') {
            // If background color is black, change it to white
            element[i].style.color = 'white';
        } else {
            // If background color is white, change it to black
            element[i].style.color = 'black';
        }
    }
}


var tickets ;
var flightnum ;
var origin1 ;
var dest1;


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
                url: "http://localhost:9090/flights/" + flightNumber,
                type: "GET",
                dataType: "json",
                success: function(data) {
                    // Update the HTML content with the retrieved flight details
                    $('.flight_type').text(data.flightType);
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
                    console.log(tickets);
                    console.log(data);

                    if(tickets > 1)
						  {

                            for (var i = 0; i < tickets-1 ; i++) {
                            // Clone the booking form HTML structure
                            var bookingForm = $('.col-md-7').first().clone();

                            // Append the cloned booking form to the container
                            $('#repeat').append(bookingForm);

                            if ((i + 1) % 2 === 0 && (i + 1) !== tickets) 
                                $('#repeat').append("<br />");
                        
                            }
					       }
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


var buttonEnabled =0 ;

// $(document).ready(function() {
//     // Attach a single submit event handler to the document
//     $(document).on('submit', '.ticket-form', function(event) {
//         event.preventDefault();

//         var url = "http://localhost:9090/ticket/add";

//         // Extract form data for the current form
//         var formData = {
//             firstName: $(this).find('.FN').val(),
//             lastName: $(this).find('.LN').val(),
//             nationalID: $(this).find('.nid').val(),
//             flightNumber: flightnum,
//             origin: origin1,
//             dest: dest1
//         };
//         console.log("Form data:", formData);

//         // Make AJAX request to create a new ticket
//         $.ajax({
//             url: url,
//             type: "POST",
//             contentType: "application/json",
//             data: JSON.stringify(formData),
//             success: function(data) {
//                 console.log("Ticket created successfully:", data);
//                 buttonEnabled+=1 ;
//                 console.log(buttonEnabled);
//                 console.log(tickets);
//                 if(buttonEnabled == tickets)
//                 {
//                     var $continueCheckoutButton = $('button.continue-checkout');
//                     $continueCheckoutButton.prop('disabled', false);
//                 }
//                 // You can handle success if needed
//             },
//             error: function(xhr, status, error) {
//                 console.log("Error creating ticket for form:", formData);
//                 console.error("Error:", error);
//             }
//         });
//     });
// });


$(document).ready(function() {
    // Attach a single submit event handler to the document
    $(document).on('submit', '.ticket-form', function(event) {
        event.preventDefault();

        var url = "http://localhost:9090/ticket/add";

        // Extract form data for the current form
        var formData = {
            firstName: $(this).find('.FN').val(),
            lastName: $(this).find('.LN').val(),
            nationalID: $(this).find('.nid').val(),
            flightNumber: flightnum,
            origin: origin1,
            dest: dest1
        };
        console.log("Form data:", formData);

        // Make AJAX request to create a new ticket
        $.ajax({
            url: url,
            type: "POST",
            headers: {
                "Authorization": "Bearer " + sessionStorage.getItem("accessToken"),
                "Content-Type": "application/json"
            },
            data: JSON.stringify(formData),
            success: function(data) {
                console.log("Ticket created successfully:", data);
                buttonEnabled+=1 ;
                console.log(buttonEnabled);
                console.log(tickets);
                if(buttonEnabled == tickets)
                {
                    var $continueCheckoutButton = $('button.continue-checkout');
                    $continueCheckoutButton.prop('disabled', false);
                }
                // You can handle success if needed
            },
            error: function(xhr, status, error) {
                console.log("Error creating ticket for form:", formData);
                console.error("Error:", error);
            }
        });
    });
});




