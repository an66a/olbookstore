import React from 'react'
import Button from '@material-ui/core/Button';
import { Link } from 'react-router-dom';

const ButtonLink = (props) => {
    return (
        <Link to={props.linkTo} style={{ textDecoration: 'none', color: "black" }} >
        <Button variant="contained" color="primary"  startIcon={props.icon} >
            {props.name}
        </Button>
        </Link>
    )
}

export default ButtonLink
