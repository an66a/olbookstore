import React from 'react'
import { Table } from '../components'
import { useSelector } from 'react-redux';
import { ButtonLink } from '../components/elements'

const columns = [
  { id: 'id', label: 'ID', minWidth: 50 },
  { id: 'customerId', label: 'Customer ID', minWidth: 100 },
  {
    id: 'bookId',
    label: 'Book ID',
    minWidth: 170,
    align: 'right',
  },
  {
    id: 'quantity',
    label: 'Quantity',
    minWidth: 170,
    align: 'right',
  },
];

const OrderPage = () => {
    const getOrders = useSelector(state => state.data.getOrders);
    console.log(getOrders);
    return (
        <Table data={getOrders} columns={columns} />
    )
}

export default OrderPage
