$(function() {

    // Get access to customer form.
    const $customerForm = $('#customerForm');

    // Get access to submit button.
    const $submitButton = $('#submitButton');

    // Serialize the customer form i.e. make a snapshot of all fields.
    const customerFormOriginalData = $customerForm.serialize();

    console.log(customerFormOriginalData);

    $customerForm.keyup(function() {

        if ($customerForm.serialize() !== customerFormOriginalData) {
            $submitButton.prop('disabled', false);
        }
        else {
            $submitButton.prop('disabled', true);
        }
    });
});