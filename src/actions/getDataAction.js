import axios from 'axios'

export const GET_PUBLISHERS = "GET_PUBLISHERS";
export const GET_AUTHORS = "GET_AUTHORS";
export const GET_BOOKS = "GET_BOOKS";
export const GET_WAREHOUSES = "GET_WAREHOUSES";
export const GET_BOOKS_STOCK = "GET_BOOKS_STOCK";
export const GET_CUSTOMERS = "GET_CUSTOMERS";
export const GET_ORDERS = "GET_ORDERS";

const getData = (path, type) => {
    return (dispatch) => {
        axios.get('https://olbookstore.herokuapp.com' + path)
            // axios.get('http://localhost:8899' + path)
            .then(res => {
                res.data.sort((function (a, b) {
                    return a.id - b.id;
                }))
                dispatch({
                    type: type,
                    payload: {
                        data: res.data,
                    },
                })
            })
            .catch(err => console.log(err))
    }
}

export const getPublishers = () => {
    return (dispatch) => {
        dispatch(getData("/publishers", GET_PUBLISHERS));
    }
}

export const getAuthors = () => {
    return (dispatch) => {
        dispatch(getData("/authors", GET_AUTHORS));
    }
}

export const getBooks = () => {
    return (dispatch) => {
        dispatch(getData("/books", GET_BOOKS));
    }
}

export const getWarehouses = () => {
    return (dispatch) => {
        dispatch(getData("/warehouses", GET_WAREHOUSES));
    }
}

export const getBooksStock = () => {
    return (dispatch) => {
        dispatch(getData("/wh-books", GET_BOOKS_STOCK));
    }
}

export const getCustomers = () => {
    return (dispatch) => {
        dispatch(getData("/customers", GET_CUSTOMERS));
    }
}

export const getOrders = () => {
    return (dispatch) => {
        dispatch(getData("/transactions", GET_ORDERS));
    }
}

// GET DATA BY ID
const getDataById = async (path) => {
    try {
        const res = await axios.get('https://olbookstore.herokuapp.com' + path);
        return res;
    } catch (error) {
        return error.response
    }
}

export const getAuthorById = async (id) => {
    return await getDataById("/authors/" + id);
}

export const getPublisherById = async (id) => {
    return await getDataById("/publishers/" + id);
}

export const getBookById = async (id) => {
    return await getDataById("/books/" + id);
}

export const getWarehouseById = async (id) => {
    return await getDataById("/warehouses/" + id);
}

export const getCustomerById = async (id) => {
    return await getDataById("/customers/" + id);
}