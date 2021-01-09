import React, { useCallback } from 'react'
import { Table } from '../components'
import { useSelector } from 'react-redux'
import { useHistory } from 'react-router-dom'

const columns = [
  { id: 'id', label: 'ID', minWidth: 50 },
  { id: 'name', label: 'Publisher Name', minWidth: 100 },
  {
    id: 'address',
    label: 'Address',
    minWidth: 170,
    align: 'right',
  },
  {
    id: 'phone',
    label: 'Phone',
    minWidth: 170,
    align: 'right',

  },
  {
    id: 'url',
    label: 'URL',
    minWidth: 170,
    align: 'right',
  },
];

const PublisherPage = () => {
  const getPublishers = useSelector(state => state.data.getPublishers);
  const history = useHistory();

  const handleOnClick = useCallback((data) => history.push({
    pathname: history.location.pathname + '/id' + data.id,
    data: data
  }), [history]);
  
  return (
    <>
        <Table data={getPublishers} columns={columns} rowClick={(el) => handleOnClick(el)} />
    </>
  );
}

export default PublisherPage;
