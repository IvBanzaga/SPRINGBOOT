function init() {

    renderCustomers();
}

async function getCustomers() {

    let url = URL_SERVER + 'customer';
    let response = await fetch(url);
    let json = await response.json();

    return json;
}

async function renderCustomers() {

    let customers = await getCustomers();
    let html = '';
    for (let i = 0; i < customers.length; i++) {
        html += getHtmlRowCustomer(customers[i]);
    }

    let tbody = document.getElementById('tbody-customers');
    tbody.innerHTML = html;

}

async function onClickEdit(id) {

    window.location.href = 'modify-customer.html?id=' + id;
}

async function onClickRemove(id) {
    let response = confirm('¿Estás seguro de eliminar el cliente?');
    if (!response) {
        return;
    }
    let url = URL_SERVER + 'customer/' + id;
    let config = {
        method: 'DELETE'
    };
    await fetch(url, config);
    renderCustomers();
}

function getHtmlRowCustomer(customer) {
    return ` <tr>
                        <td>${customer.id}</td>
                        <td>${customer.firstname}</td>
                        <td>${customer.lastname}</td>
                        <td>${customer.email}</td>
                        <td>${customer.phone}</td>
                        <td>${customer.address}</td>
                        <td>
                          <a href="#" onClick="onClickEdit(${customer.id})" class="btn btn-primary btn-circle btn-sm">
                            <i class="fas fa-edit"></i>
                          </a>
                          <a href="#" onClick="onClickRemove(${customer.id})" class="btn btn-danger btn-circle btn-sm">
                            <i class="fas fa-trash"></i>
                          </a>
                        </td>
                      </tr> `;
}

init();