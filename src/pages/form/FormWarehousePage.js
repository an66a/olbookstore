import React, { useEffect, useState } from 'react'
import { FormComp } from '../../components'
import { addFormInput } from '../../mod'
import { addWarehouse } from '../../actions/addDataAction'
import { getWarehouses, getWarehouseById } from '../../actions/getDataAction'
import { updateWarehouse } from '../../actions/updateDataAction'
import { deleteWarehouse } from '../../actions/deleteDataAction'

const formInput = [
    addFormInput('text', 'Warehouse Codename', 'codename'),
    addFormInput('text', 'Warehouse Address', 'address'),
    addFormInput('text', 'Warehouse Phone', 'phone'),
]

const FormWarehousePage = (props) => {

    return (
        <>
            <FormComp formInput={formInput} add={addWarehouse} update={updateWarehouse} get={getWarehouseById} delete={deleteWarehouse} dispatch={getWarehouses} props={props} />
        </>
    )
}

export default FormWarehousePage
