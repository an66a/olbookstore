import axios from 'axios'

const updateData = async (path, data) => {
    try {
        const response = await axios.put('https://olbookstore.herokuapp.com' + path, data);
        // const response = await axios.put('http://localhost:8899' + path, data);
        return response;
    } catch (error) {
        return error.response;
    }
}

export const updatePublisher = async (data) => {
    return await updateData('/publishers/' + data.id, data);
}

export const updateAuthor = async (data) => {
    return await updateData('/authors/' + data.id, data);
}

export const updateBook = async (data) => {
    return await updateData('/books/' + data.id, data);
}

export const updateWarehouse = async (data) => {
    return await updateData('/warehouses/' + data.id, data);
}

export const updateCustomer = async (data) => {
    return await updateData('/customers/' + data.id, data);
}

