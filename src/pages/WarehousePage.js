import React, { useCallback } from 'react'
import { Table } from '../components'
import { useSelector } from 'react-redux'
import { useHistory } from 'react-router-dom'

const columns = [
  { id: 'id', label: 'ID', minWidth: 50 },
  { id: 'codename', label: 'Codename', minWidth: 50 },
  { id: 'address', label: 'Warehouse Address', minWidth: 100 },
  {
    id: 'phone',
    label: 'Phone',
    minWidth: 100,
    align: 'right',
  }
];

const WarehousePage = () => {
  const getWarehouses = useSelector(state => state.data.getWarehouses);
  const history = useHistory();

  const handleOnClick = useCallback((data) => history.push({
    pathname: history.location.pathname + '/id' + data.id,
    data: data
  }), [history]);

  return (
    <>
      <Table data={getWarehouses} columns={columns} rowClick={(el) => handleOnClick(el)} />
    </>
  )
}

export default WarehousePage
