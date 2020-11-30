import React from 'react'
import { CreatePublisherPage } from '../create'

const PublisherDetailPage = (props) => {
    return (
        <>
            <CreatePublisherPage data={props.location.data}/>
        </>
    )
}

export default PublisherDetailPage
