import React, { useEffect } from 'react'
import { BrowserRouter as Router, Switch, Route, Redirect } from "react-router-dom"
import { Home, DashboardPage, PublisherPage, AuthorPage, BookPage, WarehousePage, CustomerPage, OrderPage } from './pages/'
import { FormPublisherPage, FormWarehousePage, FormAuthorPage, FormBookPage, FormCustomerPage, FormOrderPage } from './pages/form'
import { Base } from './components'
import { getPublishers, getAuthors, getBooks, getCustomers, getOrders, getWarehouses, getBooksStock } from './actions/getDataAction'
import { useDispatch } from 'react-redux'

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
                    <Route path={`${url}/add`} component={FormPublisherPage} exact />
                    <Route path={`${url}/id:id`} component={FormPublisherPage} exact />
                  </>
                )} />
              <Route
                path="/authors"
                render={({ match: { url } }) => (
                  <>
                    <Route path={`${url}/`} component={AuthorPage} exact />
                    <Route path={`${url}/add`} component={FormAuthorPage} exact />
                    <Route path={`${url}/id:id`} component={FormAuthorPage} exact />
                  </>
                )} />
              <Route
                path="/books"
                render={({ match: { url } }) => (
                  <>
                    <Route path={`${url}/`} component={BookPage} exact />
                    <Route path={`${url}/add`} component={FormBookPage} exact />
                    <Route path={`${url}/id:id`} component={FormBookPage} exact />
                  </>
                )} />
              <Route
                path="/customers"
                render={({ match: { url } }) => (
                  <>
                    <Route path={`${url}/`} component={CustomerPage} exact />
                    <Route path={`${url}/add`} component={FormCustomerPage} exact />
                    <Route path={`${url}/id:id`} component={FormCustomerPage} exact />
                  </>
                )} />
              <Route
                path="/orders"
                render={({ match: { url } }) => (
                  <>
                    <Route path={`${url}/`} component={OrderPage} exact />
                    <Route path={`${url}/add`} component={FormOrderPage} exact />
                    <Route path={`${url}/id:id`} component={FormOrderPage} exact />
                  </>
                )} />
              <Route
                path="/warehouses"
                render={({ match: { url } }) => (
                  <>
                    <Route path={`${url}/`} component={WarehousePage} exact />
                    <Route path={`${url}/add`} component={FormWarehousePage} exact />
                    <Route path={`${url}/id:id`} component={FormWarehousePage} exact />
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
