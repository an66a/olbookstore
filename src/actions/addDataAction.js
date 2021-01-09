import axios from 'axios'

const addData = async (path, data) => {
    try {
        const response = await axios.post('https://olbookstore.herokuapp.com' + path, data);
        // const response = await axios.post('http://localhost:8899' + path, data);
        return response;
    } catch (error) {
        return error.response;
    }
}

export const addPublisher = async (data) => {
    return await addData('/publishers', data);
}

export const addAuthor = async (data) => {
    return await addData('/authors', data);
}

export const addBook = async (data) => {
    return await addData('/books', data);
}

export const addWarehouse = async (data) => {
    return await addData('/warehouses', data);
}

export const addBookStock = async (data) => {
    return await addData('/warehouses/' + data.warehouseId, data);
}

export const addCustomer = async (data) => {
    return await addData('/customers', data);
}

export const addOrder = async (data) => {
    console.log(data);
    const customerId = data.customerId;
    const bookId = data.bookId;
    const quantity = data.quantity;
    const transactionItems = [{ bookId, quantity }]
    const newData = { customerId, transactionItems };
    // console.log(newData);
    return await addData('/transactions', newData);
}


