import React, { useEffect } from 'react'
import { FormComp } from '../../components'
import { addAuthor } from '../../actions/addDataAction'
import { getAuthors } from '../../actions/getDataAction'
import { updateAuthor } from '../../actions/updateDataAction'

const addInput = (type, label, name) => {
    return { type, label, name }
}

const formInput = [
    addInput('text', 'Author Firstname', 'firstName'),
    addInput('text', 'Author Lastname', 'lastName'),
    addInput('text', 'Author Address', 'address'),
    addInput('text', 'Author Email', 'email'),
    addInput('text', 'Author Url', 'url'),
]

const CreateAuthorPage = (props) => {

    let submit = addAuthor;

    useEffect(() => {
        if (props.data != undefined) {
            submit = updateAuthor;
        }

        return () => {

        }
    }, [])

    return (
        <>
            <FormComp formInput={formInput} submit={(el) => submit(el)} dispatch={getAuthors} data={props.data} />
        </>
    )
}

export default CreateAuthorPage
