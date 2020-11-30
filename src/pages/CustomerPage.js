import React from 'react'
import { Table } from '../components'
import { useSelector } from 'react-redux';
import { ButtonLink } from '../components/elements'

const columns = [
  { id: 'id', label: 'ID', minWidth: 50 },
  { id: 'email', label: 'Customer Email', minWidth: 100 },
  {
    id: 'name',
    label: 'Customer Name',
    minWidth: 170,
    align: 'right',
  },
  {
    id: 'phone',
    label: 'Customer Phone',
    minWidth: 170,
    align: 'right',
  },
  {
    id: 'address',
    label: 'Customer Address',
    minWidth: 170,
    align: 'right',
  },
];

const CustomerPage = () => {
    const getCustomers = useSelector(state => state.data.getCustomers);
    return (
      <>
    
        <Table data={getCustomers} columns={columns} />
        </>
    )
}

export default CustomerPage
