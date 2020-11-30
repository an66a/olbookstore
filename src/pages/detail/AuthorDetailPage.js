import React from 'react'
import { CreateAuthorPage } from '../create'

const AuthorDetailPage = (props) => {
   
    return (
        <>
        <CreateAuthorPage data={props.location.data} />
        </>
    )
}

export default AuthorDetailPage
