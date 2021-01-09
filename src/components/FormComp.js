import React, { useState, useEffect } from 'react'
import Button from '@material-ui/core/Button';
import SaveIcon from '@material-ui/icons/Save';
import RotateLeftIcon from '@material-ui/icons/RotateLeft';
import DeleteIcon from '@material-ui/icons/Delete';
import { makeStyles } from '@material-ui/core/styles';
import { Input, BackButton } from './elements';
import { capitalizer } from '../mod';
import { Container, Grid, Paper } from '@material-ui/core'
import { useDispatch } from 'react-redux';
import { useHistory } from 'react-router-dom'

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
    const history = useHistory();
    const dispatch = useDispatch();
    const [state, setstate] = useState('');
    const classes = useStyles();

    let match, location;
    if (props.props) {
        location = props.props.location;
        match = props.props.match;
    }

    useEffect(() => {

        if (match.params.id) {
            const setData = async () => {
                if (location.data) {
                    setstate(location.data);
                } else {
                    const res = await props.get(match.params.id);
                    setstate(res.data);
                }
            }
            setData();
        }
    }, [props, match, location])

    const set = (el) => {
        if (el.name !== 'url' && el.name !== 'email' && el.name !== 'phone' && el.name !== 'publishedOn' && el.name !== 'codename') {
            el.value = capitalizer(el.value);
        } else if (el.name === 'codename') {
            el.value = el.value.toUpperCase();
        }
        setstate({ ...state, [el.name]: el.value.trimLeft() });
    }

    const resetData = () => {
        if (props.data) {
            setstate(props.data);
        } else {
            setstate('');
        }
    }

    const deleteData = async () => {
        if (window.confirm('Delete data?')) {
            const res = await props.delete(match.params.id);
            if (res.status === 200) {
                dispatch(props.dispatch());
                alert('Data deleted');
                history.goBack();
            }
        }
    }

    const saveData = async () => {
        let fieldFilled = true;
        props.formInput.forEach(e => {
            if (state[e.name] === '' || state[e.name] === undefined) {
                fieldFilled = false;
            }
        })

        if (fieldFilled === false) return alert('Please fill all fields');

        if (window.confirm('Save data?')) {

            let submit = props.add;
            if (match.params.id) {
                submit = props.update;
            }

            const response = await submit(state);
            console.log(response);

            if (response !== undefined) {
                if (response.status === 200) {
                    dispatch(props.dispatch());
                    alert('Data saved');
                } else if (response.status === 400) {
                    alert('Bad date format')
                }
                else {
                    alert(response.data)
                }
            }
        }
    }

    return (
        <>
            <div style={{ marginBottom: '20px' }}>
                <BackButton />
            </div>
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
                                {match.params.id ?
                                    <Button
                                        variant="contained"
                                        color="primary"
                                        type="button"
                                        className={classes.button}
                                        onClick={deleteData}
                                        startIcon={<DeleteIcon />}
                                    >
                                        Delete
                        </Button>
                                    : null}
                                <Button
                                    variant="contained"
                                    color="primary"
                                    type="button"
                                    className={classes.button}
                                    onClick={resetData}
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
        </>
    )
}

export default FormComp
