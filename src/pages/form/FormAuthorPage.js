import React from 'react'
import { FormComp } from '../../components'
import { addFormInput } from '../../mod'
import { addAuthor } from '../../actions/addDataAction'
import { getAuthors, getAuthorById } from '../../actions/getDataAction'
import { updateAuthor } from '../../actions/updateDataAction'
import { deleteAuthor } from '../../actions/deleteDataAction'

const formInput = [
    addFormInput('text', 'Author Firstname', 'firstName'),
    addFormInput('text', 'Author Lastname', 'lastName'),
    addFormInput('text', 'Author Address', 'address'),
    addFormInput('text', 'Author Email', 'email'),
    addFormInput('text', 'Author Url', 'url'),
]

const FormAuthorPage = (props) => {

    return (
        <>
            <FormComp formInput={formInput} add={addAuthor} update={updateAuthor} get={getAuthorById} delete={deleteAuthor} dispatch={getAuthors} props={props}/>
        </>
    )
}

export default FormAuthorPage
