import React from 'react'
import { ButtonLink } from './'
import ArrowBackIcon from '@material-ui/icons/ArrowBack';

const BackButton = () => {
    const pathname = (window.location.pathname).split('/');
    const path = '/' + pathname[pathname.length - 2];
    const newTo = {
        pathname: path,
    }
    return (
        <ButtonLink name='Back' linkTo={newTo} icon={<ArrowBackIcon />} />
    )
}

export default BackButton
