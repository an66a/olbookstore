import React, { useCallback } from 'react'
import { Table } from '../components'
import { useSelector } from 'react-redux'
import { useHistory } from 'react-router-dom'

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

  const history = useHistory();

  const handleOnClick = useCallback((data) => history.push({
    pathname: history.location.pathname + '/id' + data.id,
    data: data
  }), [history]);

  return (
    <>
      <Table data={getCustomers} columns={columns} rowClick={(el) => handleOnClick(el)} />
    </>
  )
}

export default CustomerPage
