import React from 'react'
import { FormComp } from '../../components'
import { addWarehouse } from '../../actions/addDataAction'
import { getWarehouses } from '../../actions/getDataAction'

const addInput = (type, label, name) => {
    return { type, label, name }
}

const formInput = [
    addInput('text', 'Warehouse Address', 'address'),
    addInput('text', 'Publisher Phone', 'phone'),
]

const CreateWarehousePage = () => {

    return (
        <>
            <FormComp formInput={formInput} submit={(el) => addWarehouse(el)} dispatch={getWarehouses}/>
        </>

    )
}

export default CreateWarehousePage
