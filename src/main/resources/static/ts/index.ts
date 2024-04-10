import {addBook, Book, fetchBooks} from "./Book.js"

function createBookRow(book: Book) {
    const row = document.createElement("tr");

    const isbnRow = row.insertCell()
    isbnRow.textContent = book.ISBN
    const titleRow = row.insertCell()
    titleRow.textContent = book.title

    return row;
}

function clearTable() {
    const table: HTMLTableElement | null = document.querySelector("table#table");
    if (!table) return;

    let tBody = table.tBodies.item(0);
    if (tBody == null) return;

    while (tBody.rows.length > 0) {
        tBody.deleteRow(-1);
    }
}

function renderTable(books: Book[]) {
    const table: HTMLTableElement | null = document.querySelector("table#table");
    if (!table) return;

    let tBody = table.tBodies.item(0);
    if (tBody == null) {
        tBody = table.createTBody()
    }

    for (const book of books) {
        const row = createBookRow(book)
        tBody.appendChild(row)
    }
}

function updateTable() {
    clearTable()
    fetchBooks().then(v => {
        if (!v) return;
        renderTable(v)
    })
}

window.addEventListener("DOMContentLoaded", () => {
    updateTable()

    const form: HTMLFormElement | null = document.querySelector("form#form")

    form?.addEventListener("submit", (ev) => {
        ev.preventDefault()
        const formData = new FormData(form);
        const data = Object.fromEntries(formData) as unknown as Book
        addBook(data).then(() => {
            updateTable()
        }).catch((err) => {
        })
    })
})