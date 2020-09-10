$(function() {

    const $searchByFirstName = $('#searchByFirstName');

    const $searchResult = $('#searchResult');

    // Method called on every change in search field, even paste!
    $searchByFirstName.on('input', function() {

        // Get search term from input field (and remove eventually leading and/or trailing whitespace)
        const searchTerm = $searchByFirstName.val().trim();

        // Empty dynamic search results div
        $searchResult.empty();

        if (searchTerm !== '') {

            // Hide 'all' customers ...
            $('#records').hide();

            // Call REST method (web service method) on server  ...
            // e.g. http://localhost:8080/getCustomers?firstName=Ha
            fetch('http://localhost:8080/getCustomers?firstName=' + searchTerm)
                .then(
                    function(response) {

                        // TODO: Needs to be changed! Don't hide problems! Update DOM (HTML) with error!
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

                    // TODO: Needs to be changed! Don't hide problems! Update DOM (HTML) with error!
                    console.log('Fetch Error :-S', err);
                });
        }
        else {

            // Show all customers (rendered from server / Thymeleaf) ...
            $('#records').show();
        }
    });
});