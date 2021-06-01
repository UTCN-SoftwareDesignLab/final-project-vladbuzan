import React from 'react';
import { CookiesProvider } from 'react-cookie';
import ReactDOM from 'react-dom';
import { BrowserRouter, Route } from 'react-router-dom';
import AdminPage from './navigation/AdminPage';
import LandingPage from './navigation/Landing';
import 'semantic-ui-css/semantic.min.css'
import CustomerPage from './navigation/CustomerPage';
import EmployeePage from './navigation/EmployeePage';

ReactDOM.render(
  <BrowserRouter>
    <CookiesProvider>
      <Route exact path='/'>
        <LandingPage />
      </Route>
      <Route exact path='/admin'>
        <AdminPage />
      </Route>
      <Route exact path='/customer'>
        <CustomerPage/>
      </Route>
      <Route exact path='/employee'>
        <EmployeePage/>
      </Route>
    </CookiesProvider>
  </BrowserRouter>,
  document.getElementById('root')
);

