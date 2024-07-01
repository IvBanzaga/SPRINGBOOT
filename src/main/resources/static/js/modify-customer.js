const URL_SERVER = 'http://localhost:8081/';

async function loadCustomer() {
    if (isNew()) {

        return;
    }

    let auxSplit = window.location.href.split('id=');
    let id = getCustomerId();
    let customer = await getCustomerById(id);

    document.getElementById("txtFirstname").value = customer.firstname;
    document.getElementById("txtLastname").value = customer.lastname;
    document.getElementById("txtEmail").value = customer.email;
    document.getElementById("txtPhone").value = customer.phone
    document.getElementById("txtAddress").value = customer.address;

}

function getCustomerId() {
    let auxSplit = window.location.href.split('id=');
    let id = auxSplit[1];
    return id;
}

function isNew() {
    let hasIdInurl = window.location.href.includes('id=');
    return !hasIdInurl;
}

async function getCustomerById(id) {

    let url = URL_SERVER + 'customer/' + id;
    let response = await fetch(url);
    let json = await response.json();

    return json;
}




async function clickCreate() {
    let firstname = document.getElementById("txtFirstname").value;
    let lastname = document.getElementById("txtLastname").value;
    let email = document.getElementById("txtEmail").value;
    let phone = document.getElementById("txtPhone").value;
    let address = document.getElementById("txtAddress").value;

    let customer = {
        "firstname": firstname,
        "lastname": lastname,
        "email": email,
        "phone": phone,
        "address": address
    };


    await save(customer);
}

async function save(customer) {
    let url = URL_SERVER + "customer";
    let methodType = isNew() ? 'POST' : 'PUT';
    if (!isNew()) {
        url += '/' + getCustomerId();
    }
    let config = {
        "method": methodType,
        "body": JSON.stringify(customer),
        "headers": { "Content-Type": "application/json" }
    };

    try {
        let response = await fetch(url, config);
        if (!response.ok) {
            throw new Error('La respuesta de la red no fue exitosa');
        }
        // Modificar el mensaje de alerta basado en si es nuevo o no
        if (isNew()) {
            alert('Cliente creado correctamente');
        } else {
            alert('Cliente modificado correctamente');
        }
        window.location.href = 'customers.html';
    } catch (error) {
        console.error('Hubo un problema con la operación fetch:', error);
    }
}

loadCustomer();