$(function() {

    const $searchByFirstName = $('#searchByFirstName');

    const $searchResult = $('#searchResult');

    $searchByFirstName.on('input', function() {

        console.log('Search changed ...')

        const searchTerm = $searchByFirstName.val().trim();

        $searchResult.empty();

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

                            customer += '<div class="col-3">' + data[i].firstName + '</div>';

                            customer += '</div>'

                            $searchResult.append(customer);
                        })

                    });
                }
            )
            .catch(function(err) {
                console.log('Fetch Error :-S', err);
            });
    });
});