import React from 'react'
import { FormComp } from '../../components'
import { addCustomer } from '../../actions/addDataAction'
import { getCustomers } from '../../actions/getDataAction'

const addInput = (type, label, name) => {
    return { type, label, name }
}

const formInput = [
    addInput('email', 'Customer Email', 'email'),
    addInput('text', 'Customer Name', 'name'),
    addInput('text', 'Customer Phone', 'phone'),
    addInput('text', 'Customer Address', 'address'),
]

const CreateCustomerPage = () => {

    return (
        <>
            <FormComp formInput={formInput} submit={(el) => addCustomer(el)} dispatch={getCustomers}/>
        </>

    )
}

export default CreateCustomerPage
