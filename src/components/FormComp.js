import React, { useState, useEffect } from 'react'
import Button from '@material-ui/core/Button';
import SaveIcon from '@material-ui/icons/Save';
import RotateLeftIcon from '@material-ui/icons/RotateLeft';
import { makeStyles } from '@material-ui/core/styles';
import { Input } from './elements'
import { capitalizer } from '../mod'
import { Container, Grid, Paper } from '@material-ui/core'
import { useDispatch } from 'react-redux';

const useStyles = makeStyles((theme) => ({
    root: {
        '& .MuiTextField-root': {
            margin: theme.spacing(1),
        },
    },
    button: {
        margin: theme.spacing(1),
    }
}));

const FormComp = (props) => {
    const dispatch = useDispatch()
    const [state, setstate] = useState('');
    const classes = useStyles();

    useEffect(() => {
        if(props.data){
            setstate(props.data);
        }
        return () => {
         setstate('')
        }
    }, [])
 
    const set = (el) => {
        if (el.name !== 'url' && el.name !== 'email' && el.name !== 'phone' && el.name !== 'publishedOn') {
            el.value = capitalizer(el.value);
        }
        setstate({ ...state, [el.name]: el.value.trimLeft() });
    }

    const saveData = async () => {
        let fieldFilled = true;
        props.formInput.forEach(e => {
            if (state[e.name] === '' || state[e.name] === undefined) {
                fieldFilled = false;
            }
        })
        if (fieldFilled === false) return alert('please fill all fields');

        const response = await props.submit(state);
        console.log(response);
        if (response !== undefined) {
            if (response.status === 200) {
                dispatch(props.dispatch());
                alert('data succes saved');
                setstate('');
            } else if (response.status === 400) {
                alert('bad date format')
            }
            else {
                alert(response.data)
            }
        }
    }
    // console.log(state);
    return (
        <Paper>
            <Container style={{ paddingTop: 10, paddingBottom: 10 }}>
                <form className={classes.root} noValidate autoComplete="off" >
                    {props.formInput.map((input, i) => (
                        <div key={i}>
                            <Input fullWidth type={input.type} helperText={input.helperText} label={input.label} name={input.name} value={state[input.name] || ''} set={(el) => set(el)} />
                        </div>
                    ))}
                    <div>
                        <Grid
                            container
                            direction="row"
                            justify="flex-end"
                            alignItems="center"
                        >
                            <Button
                                variant="contained"
                                color="primary"
                                type="button"
                                className={classes.button}
                                onClick={() => setstate('')}
                                startIcon={<RotateLeftIcon />}
                            >
                                Reset
                        </Button>


                            <Button
                                variant="contained"
                                color="primary"
                                type="button"
                                className={classes.button}
                                onClick={saveData}
                                startIcon={<SaveIcon />}
                            >
                                Save
                        </Button>
                        </Grid>
                    </div>
                </form>
            </Container>
        </Paper>
    )
}

export default FormComp
