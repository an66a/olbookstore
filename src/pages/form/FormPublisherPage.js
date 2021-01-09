import React from 'react'
import { FormComp } from '../../components'
import { addFormInput } from '../../mod'
import { addPublisher } from '../../actions/addDataAction'
import { getPublishers, getPublisherById } from '../../actions/getDataAction'
import { updatePublisher } from '../../actions/updateDataAction'
import { deletePublisher } from '../../actions/deleteDataAction'

const formInput = [
    addFormInput('text', 'Publisher Name', 'name'),
    addFormInput('text', 'Publisher Address', 'address'),
    addFormInput('text', 'Publisher Phone', 'phone'),
    addFormInput('text', 'Publisher Url', 'url'),
]

const FormPublisherPage = (props) => {
  
    return (
        <>
            <FormComp formInput={formInput} add={addPublisher} update={updatePublisher} get={getPublisherById} delete={deletePublisher} dispatch={getPublishers} props={props} />
        </>
    )
}

export default FormPublisherPage
