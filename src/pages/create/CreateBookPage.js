import React from 'react'
import { FormComp } from '../../components'
import { addBook } from '../../actions/addDataAction'
import { getBooks } from '../../actions/getDataAction'

const addInput = (type, label, name, helperText) => {
    return { type, label, name, helperText }
}

const formInput = [
    addInput('text', 'Book ISBN', 'isbn'),
    addInput('text', 'Book Published On', 'publishedOn',"date format 'yyyy-MM'. example: 2020-07 "),
    addInput('text', 'Book Title', 'title'),
    addInput('number', 'Book Price', 'price'),
    addInput('text', 'Book Publisher ID', 'publisherId'),
    addInput('text', 'Book Author ID', 'authorId'),
]

const CreateBookPage = () => {

    return (
        <>
            <FormComp formInput={formInput} submit={(el) => addBook(el)} dispatch={getBooks} />
        </>
    )
}

export default CreateBookPage
