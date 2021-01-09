import React from 'react'
import { FormComp } from '../../components'
import { addFormInput } from '../../mod'
import { addCustomer } from '../../actions/addDataAction'
import { getCustomers, getCustomerById } from '../../actions/getDataAction'
import { updateCustomer } from '../../actions/updateDataAction'
import { deleteCustomer } from '../../actions/deleteDataAction'

const formInput = [
    addFormInput('email', 'Customer Email', 'email'),
    addFormInput('text', 'Customer Name', 'name'),
    addFormInput('text', 'Customer Phone', 'phone'),
    addFormInput('text', 'Customer Address', 'address'),
]

const FormCustomerPage = (props) => {

    return (
        <>
            <FormComp formInput={formInput} add={addCustomer} update={updateCustomer} get={getCustomerById} delete={deleteCustomer} dispatch={getCustomers} props={props} />
        </>
    )
}

export default FormCustomerPage
