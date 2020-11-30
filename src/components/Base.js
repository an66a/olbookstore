import React, { useState } from 'react';
import clsx from 'clsx';
import { makeStyles, useTheme } from '@material-ui/core/styles';
import Drawer from '@material-ui/core/Drawer';
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import List from '@material-ui/core/List';
import CssBaseline from '@material-ui/core/CssBaseline';
import Typography from '@material-ui/core/Typography';
import Divider from '@material-ui/core/Divider';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import ChevronLeftIcon from '@material-ui/icons/ChevronLeft';
import ChevronRightIcon from '@material-ui/icons/ChevronRight';
import ListItem from '@material-ui/core/ListItem';
import ListItemIcon from '@material-ui/core/ListItemIcon';
import ListItemText from '@material-ui/core/ListItemText';
import DashboardIcon from '@material-ui/icons/Dashboard';
import GroupIcon from '@material-ui/icons/Group';
import ShoppingBasketIcon from '@material-ui/icons/ShoppingBasket';
import LibraryBooksIcon from '@material-ui/icons/LibraryBooks';
import LocalLibraryIcon from '@material-ui/icons/LocalLibrary';
import BusinessIcon from '@material-ui/icons/Business';
import HomeIcon from '@material-ui/icons/Home';
import AddBoxIcon from '@material-ui/icons/AddBox';
import { Link } from 'react-router-dom';
import { BackButton, ButtonLink } from './elements'

const drawerWidth = 240;

const useStyles = makeStyles((theme) => ({
    root: {
        display: 'flex',
    },
    appBar: {
        zIndex: theme.zIndex.drawer + 1,
        transition: theme.transitions.create(['width', 'margin'], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
    },
    appBarShift: {
        marginLeft: drawerWidth,
        width: `calc(100% - ${drawerWidth}px)`,
        transition: theme.transitions.create(['width', 'margin'], {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.enteringScreen,
        }),
    },
    menuButton: {
        marginRight: 36,
    },
    hide: {
        display: 'none',
    },
    drawer: {
        width: drawerWidth,
        flexShrink: 0,
        whiteSpace: 'nowrap',
    },
    drawerOpen: {
        width: drawerWidth,
        transition: theme.transitions.create('width', {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.enteringScreen,
        }),
    },
    drawerClose: {
        transition: theme.transitions.create('width', {
            easing: theme.transitions.easing.sharp,
            duration: theme.transitions.duration.leavingScreen,
        }),
        overflowX: 'hidden',
        width: theme.spacing(7) + 1,
        [theme.breakpoints.up('sm')]: {
            width: theme.spacing(9) + 1,
        },
    },
    toolbar: {
        display: 'flex',
        alignItems: 'center',
        justifyContent: 'flex-end',
        padding: theme.spacing(0, 1),
        // necessary for content to be below app bar
        ...theme.mixins.toolbar,
    },
    account: {
        position: 'fixed',
        right: '20px',
        alignItems: 'center',
        display: 'flex'
    },
    content: {
        flexGrow: 1,
        padding: theme.spacing(3),
    },
    marginBottom: {
        marginBottom: '20px'
    }
}));

export default function Base(props) {
    const classes = useStyles();
    const theme = useTheme();
    const [open, setOpen] = useState(false);
    // const [isAccOpen, setOpenAcc] = useState(false);

    const handleDrawerOpen = () => {
        setOpen(true);
    };

    const handleDrawerClose = () => {
        setOpen(false);
    };
    // const handleMenu = () => {
    //     setOpenAcc(!isAccOpen)
    // };

    // const handleClose = () => {
    //     setOpenAcc(false);
    // };

    const arrIcon1 = [<DashboardIcon />, <BusinessIcon />, <LocalLibraryIcon />, <LibraryBooksIcon />];

    return (
        <div className={classes.root}>
            <CssBaseline />
            <AppBar
                position="fixed"
                className={clsx(classes.appBar, {
                    [classes.appBarShift]: open,
                })}
            >
                <Toolbar>

                    <IconButton
                        color="inherit"
                        aria-label="open drawer"
                        onClick={handleDrawerOpen}
                        edge="start"
                        className={clsx(classes.menuButton, {
                            [classes.hide]: open,
                        })}
                    >
                        <MenuIcon />
                    </IconButton>

                    <Typography variant="h6" noWrap>
                        {window.location.pathname.slice(1).toUpperCase()}
                    </Typography>

                    <div className={classes.account}>
                        <Typography variant="h6" noWrap style={{ marginRight: 5 }}>
                            olbookstore
                        </Typography>
                        {/* <IconButton
                            aria-label="account of current user"
                            aria-controls="menu-appbar"
                            aria-haspopup="true"
                            onClick={handleMenu}
                            color="inherit"
                        >
                            <AccountCircle />
                        </IconButton>
                        <Menu
                            id="menu-appbar"
                            // anchorEl={anchorEl}
                            anchorOrigin={{
                                vertical: 'top',
                                horizontal: 'right',
                            }}
                            keepMounted
                            transformOrigin={{
                                vertical: 'top',
                                horizontal: 'right',
                            }}
                            open={isAccOpen}
                            onClose={handleClose}
                        >
                            <MenuItem onClick={handleClose}>Profile</MenuItem>
                            <MenuItem onClick={handleClose}>My account</MenuItem>
                        </Menu> */}
                    </div>
                </Toolbar>
            </AppBar>
            <Drawer
                variant="permanent"
                className={clsx(classes.drawer, {
                    [classes.drawerOpen]: open,
                    [classes.drawerClose]: !open,
                })}
                classes={{
                    paper: clsx({
                        [classes.drawerOpen]: open,
                        [classes.drawerClose]: !open,
                    }),
                }}
            >
                <div className={classes.toolbar}>
                    <IconButton onClick={handleDrawerClose}>
                        {theme.direction === 'rtl' ? <ChevronRightIcon /> : <ChevronLeftIcon />}
                    </IconButton>
                </div>
                <Divider />
                <List>
                    {['Dashboard', 'Publishers', 'Authors', 'Books'].map((text, index) => (
                        <Link to={'/' + text.toLowerCase().replace(/\s/g, '')} key={index} style={{ textDecoration: 'none', color: "black" }} >
                            <ListItem button key={text}>
                                <ListItemIcon >{arrIcon1[index]}</ListItemIcon>
                                <ListItemText primary={text} />
                            </ListItem>
                        </Link>
                    ))}
                </List>
                <Divider />

                <List>
                    {['Warehouses', 'Book Stocks'].map((text, index) => (
                        <Link to={'/' + text.toLowerCase().replace(/\s/g, '')} key={index} style={{ textDecoration: 'none', color: "black" }} >
                            <ListItem button key={text}>
                                <ListItemIcon>{index % 2 === 0 ? <HomeIcon /> : <LibraryBooksIcon />}</ListItemIcon>
                                <ListItemText primary={text} />
                            </ListItem>
                        </Link>
                    ))}
                </List>

                <Divider />

                <List>
                    {['Customers', 'Orders'].map((text, index) => (
                        <Link to={'/' + text.toLowerCase()} key={index} style={{ textDecoration: 'none', color: "black" }} >
                            <ListItem button key={text}>
                                <ListItemIcon>{index % 2 === 0 ? <GroupIcon /> : <ShoppingBasketIcon />}</ListItemIcon>
                                <ListItemText primary={text} />
                            </ListItem>
                        </Link>
                    ))}
                </List>

            </Drawer>
            <main className={classes.content}>
                <div className={classes.toolbar} />
                {((props.location.pathname).slice(1)) !== 'dashboard' ?
                    <>
                        {((props.location.pathname).split('/'))[2] === 'add' ?
                            <div className={classes.marginBottom}>
                                <BackButton />
                            </div>
                            : null}

                        {((props.location.pathname).split('/')).length === 2 ?
                            <div className={classes.marginBottom}>
                                <div>
                                    <ButtonLink name={'ADD ' + ((((props.location.pathname).split('/'))[1]).toUpperCase().slice(0, -1))} linkTo={(props.location.pathname) + '/add'} icon={<AddBoxIcon />} />
                                </div>
                            </div>
                            : null}
                    </>
                    : null}
                {props.page}
            </main>
        </div>
    );
}
