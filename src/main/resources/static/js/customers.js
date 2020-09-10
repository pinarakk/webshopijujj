$(function() {

    const $searchByFirstName = $('#searchByFirstName');

    const $searchResult = $('#searchResult');

    $searchByFirstName.on('input', function() {

        const searchTerm = $searchByFirstName.val().trim();

        $searchResult.empty();

        if (searchTerm !== '') {

            fetch('http://localhost:8080/getCustomers?firstName=' + searchTerm)
                .then(
                    function(response) {

                        if (response.status !== 200) {
                            console.log('Looks like there was a problem. Status Code: ' + response.status);
                            return;
                        }

                        response.json().then(function(data) {

                            $.each(data, function(i, item) {

                                let customer = '<div class="row text-left mb-2">';

                                customer += '<div class="col-1">' + data[i].id + '</div>';
                                customer += '<div class="col-3">' + data[i].firstName + '</div>';
                                customer += '<div class="col-3">' + data[i].lastName + '</div>';
                                customer += '<div class="col-3">' + data[i].email + '</div>';

                                // <a th:href="'/editCustomer/' + ${customer.getId()}" class="btn btn-outline-primary btn-sm buttonWidthSquare" role="button"><i class="fa fa-edit" aria-hidden="true"></i></a>
                                // <a th:href="'/deleteCustomer/' + ${customer.getId()}" class="btn btn-outline-primary btn-sm buttonWidthSquare" role="button"><i class="fa fa-trash" aria-hidden="true"></i></a>

                                customer += '<div class="col-2 text-right">';
                                customer += '<a href="/editCustomer/' + data[i].id + '" class="btn btn-outline-primary btn-sm buttonWidthSquare" role="button"><i class="fa fa-edit" aria-hidden="true"></i></a>';
                                customer += '<a href="/deleteCustomer/' + data[i].id + '" class="btn btn-outline-primary btn-sm buttonWidthSquare" role="button"><i class="fa fa-trash" aria-hidden="true"></i></a>';
                                customer += '</div>'

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