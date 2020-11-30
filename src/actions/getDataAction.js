import axios from 'axios'

export const GET_PUBLISHERS = "GET_PUBLISHERS";
export const GET_AUTHORS = "GET_AUTHORS";
export const GET_BOOKS = "GET_BOOKS";
export const GET_WAREHOUSES = "GET_WAREHOUSES";
export const GET_BOOKS_STOCK = "GET_BOOKS_STOCK";
export const GET_CUSTOMERS = "GET_CUSTOMERS";
export const GET_ORDERS = "GET_ORDERS";

const getDataRest = (path, type) => {
    return (dispatch) => {
        axios.get('http://localhost:8899' + path)
        // axios.get('https://olbookstore.herokuapp.com/' + path)
            .then(res => {
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
        dispatch(getDataRest("/publishers", GET_PUBLISHERS));
    }
}

export const getAuthors = () => {
    return (dispatch) => {
        dispatch(getDataRest("/authors", GET_AUTHORS));
    }
}

export const getBooks = () => {
    return (dispatch) => {
        dispatch(getDataRest("/books", GET_BOOKS));
    }
}

export const getWarehouses = () => {
    return (dispatch) => {
        dispatch(getDataRest("/warehouses", GET_WAREHOUSES));
    }
}

export const getBooksStock = () => {
    return (dispatch) => {
        dispatch(getDataRest("books-stock", GET_BOOKS_STOCK));
    }
}

export const getCustomers = () => {
    return (dispatch) => {
        dispatch(getDataRest("/customers", GET_CUSTOMERS));
    }
}

export const getOrders = () => {
    return (dispatch) => {
        dispatch(getDataRest("/orders", GET_ORDERS));
    }
}