import React, { useEffect } from 'react'
import { BrowserRouter as Router, Switch, Route, Redirect } from "react-router-dom"
import { Home, DashboardPage, PublisherPage, AuthorPage, BookPage, WarehousePage, CustomerPage, OrderPage } from './pages/'
import { CreatePublisherPage, CreateWarehousePage, CreateAuthorPage, CreateBookPage, CreateOrderPage } from './pages/create'
import { AuthorDetail, PublisherDetail, OrderDetail } from './pages/detail'
import { Base } from './components'
import { getPublishers, getAuthors, getBooks, getCustomers, getOrders, getWarehouses, getBooksStock } from './actions/getDataAction'
import { useDispatch, useSelector } from 'react-redux'
import CreateCustomerPage from './pages/create/CreateCustomerPage'

const App = (props) => {
  const dispatch = useDispatch();

  useEffect(() => {
    const actions = [getPublishers, getAuthors, getBooks, getCustomers, getOrders, getWarehouses, getBooksStock];
    actions.map((action) => {
      return dispatch(action());
    })
  }, [dispatch])

  return (
    <>
      <Router>
        {window.location.pathname === '/' ? <Redirect to={'/dashboard'} /> : null}
        <Switch>
          <Base page={
            <>
              <Route path="/" exact>
                <Home />
              </Route>
              <Route path="/dashboard" exact>
                <DashboardPage />
              </Route>

              <Route
                path="/publishers"
                render={({ match: { url } }) => (
                  <>
                    <Route path={`${url}/`} component={PublisherPage} exact />
                    <Route path={`${url}/add`} component={CreatePublisherPage} exact />
                    <Route path={`${url}/id:id`} component={PublisherDetail} exact />
                  </>
                )} />
              <Route
                path="/authors"
                render={({ match: { url } }) => (
                  <>
                    <Route path={`${url}/`} component={AuthorPage} exact />
                    <Route path={`${url}/add`} component={CreateAuthorPage} exact />
                    <Route path={`${url}/id:id`} component={AuthorDetail} exact />
                  </>
                )} />
              <Route
                path="/books"
                render={({ match: { url } }) => (
                  <>
                    <Route path={`${url}/`} component={BookPage} exact />
                    <Route path={`${url}/add`} component={CreateBookPage} exact />
                  </>
                )} />
              <Route
                path="/customers"
                render={({ match: { url } }) => (
                  <>
                    <Route path={`${url}/`} component={CustomerPage} exact />
                    <Route path={`${url}/add`} component={CreateCustomerPage} exact />
                  </>
                )} />
              <Route
                path="/orders"
                render={({ match: { url } }) => (
                  <>
                    <Route path={`${url}/`} component={OrderPage} exact />
                    <Route path={`${url}/add`} component={CreateOrderPage} exact />
                    <Route path={`${url}/id:id`} component={OrderDetail} exact />
                  </>
                )} />
              <Route
                path="/warehouses"
                render={({ match: { url } }) => (
                  <>
                    <Route path={`${url}/`} component={WarehousePage} exact />
                    <Route path={`${url}/add`} component={CreateWarehousePage} exact />
                  </>
                )} />
            </>
          } />
        </Switch>
      </Router>
    </>
  )
}

export default App
