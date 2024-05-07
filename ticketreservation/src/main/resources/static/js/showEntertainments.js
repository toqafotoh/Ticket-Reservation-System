$(document).ready(function() {
    function getEntertainments() {
        $.ajax({
            url: "http://localhost:8080/entertainment",
            type: "GET",
            dataType: "json",
            success: function(data) {
                var $template = $('.col-md-3'); // Cache the template element
                
                $.each(data, function(index, entertainment) {
                    var $clonedTicket = $template.clone(); // Clone the template
                    // Update the content of the cloned ticket with data from the 'ticket' object
                    $clonedTicket.find('.entertainmentTourId').text(entertainment.entertainmentTourId);
                    $clonedTicket.find('.description').text(entertainment.description);
                    $clonedTicket.find('.time').text(entertainment.time);
                    $clonedTicket.find('.price').text(entertainment.price);
                    $clonedTicket.find('.destination').text(entertainment.destination);
                    
                    console.log(data);

                    // Append the cloned ticket to the DOM
                    $('#row').append($clonedTicket);
                });
                $('#removeMain').remove();
            },
            error: function() {
                console.log('Error fetching entertaiments details');
            }
        });
    }
    getEntertainments();
});



$(document).ready(function() {
    $(document).on('submit', '.book-flight', function(event) {
        event.preventDefault(); 
        var entertainmentId = $(this).find('.entertainmentTourId').text();
        var amount = $(this).find('.amount').text();
        var wantedTickets = $(this).find('.ticketNumber').val();
        var totalamount = amount*wantedTickets ;
        console.log(entertainmentId);
        console.log(wantedTickets);
        window.location.href = "http://localhost:9090/Entertainment/entertainmentPayment.html?entertainmentTourId=" + entertainmentId +'totalamount'+ totalamount+ '&wantedTickets=' + wantedTickets;
    });
});
