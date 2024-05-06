
var searchData ;

$(document).ready(function() {
    // Attach a single submit event handler to the document
    $(document).on('submit', '.search-form', function(event) {
        event.preventDefault();

        // Extract form data for the current form
        var formData = {
            origin: $('#origin').val(),
            destination: $('#destination').val(),
            flightStartDate: $('#date-in').val(),
            flightEndDate: $('#date-out').val(),
            flightType: $('#flight_type').val(),
            flightClass: $('#class_type').val(),
            avaliableSeats: $('#numberOfSeats').val(),
        };
        searchData = formData ;
        console.log("Form data:", formData);
        console.log("Form data:", searchData);

        $.ajax({
            url: url,
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