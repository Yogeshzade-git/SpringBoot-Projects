document.getElementById('emailForm').addEventListener('submit', function (event) {
    event.preventDefault(); // Prevent form from submitting the default way

    const formData = new FormData();
    formData.append('to', document.getElementById('to').value);
    formData.append('subject', document.getElementById('subject').value);
    formData.append('mailBody', document.getElementById('mailBody').value);

    const attachment = document.getElementById('attachment').files[0];
    if (attachment) {
        formData.append('attachment', attachment);
    }

    fetch('http://localhost:8080/api/send-mail', { // Updated to match your controller's mapping
        method: 'POST',
        body: formData
    })
    .then(response => {
        console.log(response); // Log the response to check status
        if (response.ok) {
            return response.text();
        } else {
            throw new Error('Failed to send email. Status: ' + response.status);
        }
    })
    .then(data => {
        document.getElementById('statusMessage').textContent = data; // Use the response message
        document.getElementById('statusMessage').style.color = 'green';
    })
    .catch(error => {
        document.getElementById('statusMessage').textContent = error.message;
        document.getElementById('statusMessage').style.color = 'red';
        console.error('Error:', error);
    });
});
