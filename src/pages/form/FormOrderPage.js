import React from 'react'
import { FormComp } from '../../components'
import { addOrder } from '../../actions/addDataAction'
import { getOrders } from '../../actions/getDataAction'

const addInput = (type, label, name) => {
    return { type, label, name }
}

const formInput = [
    addInput('number', 'Customer ID', 'customerId'),
    addInput('number', 'Book ID', 'bookId'),
    addInput('number', 'Book Quantity', 'quantity'),
]

const FormOrderPage = () => {

    return (
        <>
            <FormComp formInput={formInput} submit={(el) => addOrder(el)} dispatch={getOrders}/>
        </>
    )
}

export default FormOrderPage
