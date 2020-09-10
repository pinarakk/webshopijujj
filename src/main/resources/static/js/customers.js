$(function() {

    const $searchByFirstName = $('#searchByFirstName');

    const $searchResult = $('#searchResult');

    $searchByFirstName.on('input', function() {

        const searchTerm = $searchByFirstName.val().trim();

        $searchResult.empty();
        $('#records').show();

        if (searchTerm !== '') {

            $('#records').hide();

            // Call REST method (web service method) on server  ...
            // e.g. http://localhost:8080/getCustomers?firstName=Ha
            fetch('http://localhost:8080/getCustomers?firstName=' + searchTerm)
                .then(
                    function(response) {

                        if (response.status !== 200) {
                            console.log('Looks like there was a problem. Status Code: ' + response.status);
                            return;
                        }

                        response.json().then(function(data) {

                            $.each(data, function(i, item) {

                                // Create new row for every customer found ...
                                let customer = '<div class="row text-left mb-2">';

                                // Customer properties ...
                                customer += '<div class="col-1">' + data[i].id + '</div>';
                                customer += '<div class="col-3">' + data[i].firstName + '</div>';
                                customer += '<div class="col-3">' + data[i].lastName + '</div>';
                                customer += '<div class="col-3">' + data[i].email + '</div>';

                                customer += '<div class="col-2 text-right">';
                                customer += '<a href="/editCustomer/' + data[i].id + '" class="btn btn-outline-primary btn-sm buttonWidthSquare" role="button"><i class="fa fa-edit" aria-hidden="true"></i></a>';
                                customer += '<a href="/deleteCustomer/' + data[i].id + '" class="btn btn-outline-primary btn-sm buttonWidthSquare" role="button"><i class="fa fa-trash" aria-hidden="true"></i></a>';
                                customer += '</div>'

                                // Append row ...
                                $searchResult.append(customer);
                            })

                        });
                    }
                )
                .catch(function(err) {
                    console.log('Fetch Error :-S', err);
                });
        }
    });
});