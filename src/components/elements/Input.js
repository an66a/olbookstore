import React from 'react'
// import { Form } from 'react-bootstrap';
import TextField from '@material-ui/core/TextField';

const Input = (props) => {
  return (
    <TextField
      fullWidth={props.fullWidth}
      label={props.label}
      type={props.type}
      name={props.name}
      value={props.value}
      helperText={props.helperText}
      variant="outlined"
      onChange={(el) => props.set(el.target)}
    />
  )
}

export default Input
