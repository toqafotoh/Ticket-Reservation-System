$(document).ready(function() {
    
    $(document).on('submit', '#payment-form', function(event) {
        event.preventDefault();
        var formData = {

            destination: $('.destination').val(),
        };
        console.log("Form data:", formData);

        $.ajax({
            url: "http://localhost:9090/flights/search",
            type: "POST",
            contentType: "application/json",
            data: JSON.stringify(formData),
            success: function(data) {
                console.log(" created successfully:", data);
        },
            error: function(xhr, status, error) {
                console.log("Error creating ticket for form:", formData);
                console.error("Error:", error);
            }
        });

    });
});

$(document).ready(function() {
    $('#search-flight').click(function(event) {
        event.preventDefault(); 

        var wantedTickets = $('.numberOfSeats').val();
        $('#search-flight').submit();
        window.location.href = "http://localhost:9090/Passenger/resultSearchflights.html?wantedTickets=" + wantedTickets;

    });
});



  
  

