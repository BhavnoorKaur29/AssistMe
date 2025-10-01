// ========================
// UTILITY FUNCTIONS
// ========================
const API_URL = "http://localhost:8090/api";
const token = localStorage.getItem("token");

// Helper to set Authorization header
function getAuthHeader() {
    return { "Content-Type": "application/json", "Authorization": token };
}

// ========================
// USER LOGIN & REGISTER
// ========================
async function loginUser() {
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const res = await fetch(`${API_URL}/users/login`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password }),
    });
    const data = await res.json();

    if (res.status === 200) {
        localStorage.setItem("token", data.token);
        window.location.href = "dashboard.html";
    } else {
        alert(data);
    }
}

async function registerUser() {
    const name = document.getElementById("name").value;
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;

    const res = await fetch(`${API_URL}/users/register`, {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ name, email, password }),
    });
    const data = await res.json();

    if (res.status === 200) {
        alert("Registered successfully. Login now!");
        window.location.href = "login.html";
    } else {
        alert(data);
    }
}

// ========================
// PRODUCT FUNCTIONS
// ========================
async function getProducts() {
    const res = await fetch(`${API_URL}/products`, {
        headers: getAuthHeader(),
    });
    const products = await res.json();

    let container = document.getElementById("product-list");
    container.innerHTML = "";
    products.forEach((p) => {
        container.innerHTML += `
            <div class="product">
                <h3>${p.name}</h3>
                <p>Price: ₹${p.price}</p>
                <button onclick="placeOrder(${p.id}, 1)">Order</button>
            </div>
        `;
    });
}

// ========================
// ORDER FUNCTIONS
// ========================
async function placeOrder(productId, quantity) {
    const res = await fetch(`${API_URL}/orders`, {
        method: "POST",
        headers: getAuthHeader(),
        body: JSON.stringify({ product: { id: productId }, quantity }),
    });
    const data = await res.json();
    if (res.id) {
        alert("Order placed successfully!");
        window.location.href = "payments.html?orderId=" + data.id;
    } else {
        alert("Order failed");
    }
}

async function getOrders() {
    const res = await fetch(`${API_URL}/orders`, {
        headers: getAuthHeader(),
    });
    const orders = await res.json();
    let container = document.getElementById("order-list");
    container.innerHTML = "";
    orders.forEach((o) => {
        container.innerHTML += `
            <div class="order">
                <h3>Order #${o.id}</h3>
                <p>Product: ${o.product.name}</p>
                <p>Quantity: ${o.quantity}</p>
                <p>Total: ₹${o.total}</p>
            </div>
        `;
    });
}

// ========================
// PAYMENT FUNCTIONS
// ========================
async function makePayment(orderId) {
    const res = await fetch(`${API_URL}/payments`, {
        method: "POST",
        headers: getAuthHeader(),
        body: JSON.stringify({ order: { id: orderId }, amount: 100, status: "COMPLETED" }),
    });
    const data = await res.json();
    if (data.id) {
        alert("Payment successful!");
        window.location.href = "thankyou.html";
    } else {
        alert("Payment failed!");
    }
}

async function getPayments() {
    const res = await fetch(`${API_URL}/payments`, {
        headers: getAuthHeader(),
    });
    const payments = await res.json();
    let container = document.getElementById("payment-list");
    container.innerHTML = "";
    payments.forEach((p) => {
        container.innerHTML += `
            <div class="payment">
                <h3>Payment #${p.id}</h3>
                <p>Order ID: ${p.order.id}</p>
                <p>Amount: ₹${p.amount}</p>
                <p>Status: ${p.status}</p>
            </div>
        `;
    });
}

// ========================
// LOGOUT
// ========================
function logout() {
    localStorage.removeItem("token");
    window.location.href = "login.html";
}
