import { GET_PUBLISHERS, GET_AUTHORS, GET_BOOKS, GET_CUSTOMERS, GET_ORDERS, GET_WAREHOUSES, GET_BOOKS_STOCK } from '../actions/getDataAction'

let initialState = {
  getPublishers: null,
  getAuthors: null,
  getBooks: null,
  getCustomers: null,
  getOrders: null,
  getWarehouses: null,
  getBooksStock: null
}

const data = (state = initialState, action) => {
  switch (action.type) {
    case GET_PUBLISHERS:
      return {
        ...state,
        getPublishers: action.payload.data,
      }
    case GET_AUTHORS:
      return {
        ...state,
        getAuthors: action.payload.data,
      }
    case GET_BOOKS:
      return {
        ...state,
        getBooks: action.payload.data,
      }
    case GET_CUSTOMERS:
      return {
        ...state,
        getCustomers: action.payload.data,
      }
    case GET_ORDERS:
      return {
        ...state,
        getOrders: action.payload.data,
      }
    case GET_WAREHOUSES:
      return {
        ...state,
        getWarehouses: action.payload.data,
      }
    case GET_BOOKS_STOCK:
      return {
        ...state,
        getBooksStock: action.payload.data,
      }
    default:
      return state
  }
}
export default data
