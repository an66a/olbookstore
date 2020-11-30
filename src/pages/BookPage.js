import React from 'react'
import { Table } from '../components'
import { useSelector } from 'react-redux'

const columns = [
  { id: 'id', label: 'ID', minWidth: 50 },
  { id: 'isbn', label: 'ISBN', minWidth: 100 },
  {
    id: 'publishedOn',
    label: 'Date Published',
    minWidth: 170,
    align: 'right',
  },
  {
    id: 'title',
    label: 'Title',
    minWidth: 170,
    align: 'right',
  },
  {
    id: 'price',
    label: 'Price',
    minWidth: 170,
    align: 'right',
  },
];

const BookPage = () => {
  const getBooks = useSelector(state => state.data.getBooks);
  return (
    <>
        <Table data={getBooks} columns={columns} />

    </>
  )
}

export default BookPage
