import React, { useCallback } from 'react'
import { Table } from '../components'
import { useSelector } from 'react-redux';
import { useHistory } from 'react-router-dom';

const columns = [
  { id: 'id', label: 'ID', minWidth: 50 },
  { id: 'firstName', label: 'Firstname', minWidth: 100 },
  { id: 'lastName', label: 'Lastname', minWidth: 100 },
  { id: 'email', label: 'Email', minWidth: 100 },
  {
    id: 'address',
    label: 'Address',
    minWidth: 170,
    align: 'right',
  },
  {
    id: 'url',
    label: 'URL',
    minWidth: 170,
    align: 'right',
  }
];

const AuthorPage = (props) => {
  const getAuthors = useSelector(state => state.data.getAuthors);
  const history = useHistory();

  const handleOnClick = useCallback((data) => history.push({
    pathname: history.location.pathname + '/id' + data.id,
    data: data
  }), [history]);

  return (
    <>
      <Table data={getAuthors} columns={columns} rowClick={(data) => handleOnClick(data)} />
    </>
  )
}

export default AuthorPage
