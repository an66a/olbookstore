import axios from 'axios'

const deleteData = async (path) => {
    try {
        const res = await axios.delete('https://olbookstore.herokuapp.com' + path);
        return res;
    } catch (error) {
        return error.response;
    }
}

export const deleteAuthor = async (id) => {
    return await deleteData('/authors/' + id);
}

export const deletePublisher = async (id) => {
    return await deleteData('/publishers/' + id);
}

export const deleteBook = async (id) => {
    return await deleteData('/books/' + id);
}

export const deleteWarehouse = async (id) => {
    return await deleteData('/warehouses/' + id);
}

export const deleteCustomer = async (id) => {
    return await deleteData('/customers/' + id);
}