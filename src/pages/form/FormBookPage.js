import React from 'react'
import { FormComp } from '../../components'
import { addFormInput } from '../../mod'
import { addBook } from '../../actions/addDataAction'
import { getBooks, getBookById } from '../../actions/getDataAction'
import { updateBook } from '../../actions/updateDataAction'
import { deleteBook } from '../../actions/deleteDataAction'

const formInput = [
    addFormInput('text', 'Book ISBN', 'isbn'),
    addFormInput('text', 'Book Published On', 'publishedOn', "date format 'yyyy-MM'. example: 2020-07 "),
    addFormInput('text', 'Book Title', 'title'),
    addFormInput('number', 'Book Price', 'price'),
    addFormInput('text', 'Book Publisher ID', 'publisherId'),
    addFormInput('text', 'Book Author ID', 'authorId'),
]

const FormBookPage = (props) => {

    return (
        <>
            <FormComp formInput={formInput} add={addBook} update={updateBook} get={getBookById} delete={deleteBook} dispatch={getBooks} props={props} />
        </>
    )
}

export default FormBookPage
