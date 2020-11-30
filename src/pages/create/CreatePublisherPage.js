import React, { useEffect } from 'react'
import { FormComp } from '../../components'
import { addPublisher } from '../../actions/addDataAction'
import { getPublishers } from '../../actions/getDataAction'
import { updatePublisher } from '../../actions/updateDataAction'

const addInput = (type, label, name) => {
    return { type, label, name }
}

const formInput = [
    addInput('text', 'Publisher Name', 'name'),
    addInput('text', 'Publisher Address', 'address'),
    addInput('text', 'Publisher Phone', 'phone'),
    addInput('text', 'Publisher Url', 'url'),
]

const CreatePublisherPage = (props) => {

    let submit = addPublisher;

    useEffect(() => {
        if (props.data != undefined) {
            submit = updatePublisher;
        }

        return () => {

        }
    }, [])
    return (
        <>
            <FormComp formInput={formInput} submit={(el) => submit(el)} dispatch={getPublishers} data={props.data} />
        </>
    )
}

export default CreatePublisherPage
