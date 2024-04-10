export interface Book {
    ISBN: string,
    title: string,
}

export async function fetchBooks(): Promise<Book[] | null> {
    try {
        const result = await fetch("/books")
        const json = await result.json()
        return json;
    } catch (err) {
        console.error(err)
    }
    return null;
}

export async function addBook(book: Book) {
    const json = JSON.stringify(book);
    const result = await fetch("/books", {
        method: "post",
        headers: {
            "Content-Type": "application/json"
        },
        body: json
    })
    if (!result.ok) {
        const text = await result.text()
        throw new Error(text)
    }
    console.log(result)
}