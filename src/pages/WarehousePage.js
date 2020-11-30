import React from 'react'
import { Table } from '../components'
import { useSelector } from 'react-redux'
import { ButtonLink } from '../components/elements'

const columns = [
  { id: 'id', label: 'ID', minWidth: 50 },
  { id: 'address', label: 'Warehouse Address', minWidth: 100 },
  {
    id: 'phone',
    label: 'Phone',
    minWidth: 170,
    align: 'right',
  }
];

const WarehousePage = () => {
  const getWarehouses = useSelector(state => state.data.getWarehouses);
  return (
    <>
      <Table data={getWarehouses} columns={columns} />
    </>
  )
}

export default WarehousePage
