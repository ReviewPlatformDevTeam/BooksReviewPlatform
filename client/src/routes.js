import React from 'react';
import { Route, Switch } from 'react-router-dom';
import LoginView from './Authorization/LoginView';
import MainView from './Main/MainView';
import RegistrationView from './Authorization/RegistrationView';
import PasswordResetView from './Authorization/PasswordResetView';
import BooksList from './BooksList/BooksList';

const BaseRouter = () => (
   <Switch>
       <Route path="/login" component={LoginView} />
       <Route path="/register" component={RegistrationView} />
       <Route path="/signedin" />
       <Route path="/signedup" />
       <Route path="/resetPassword" component={PasswordResetView} />
       <Route path="/signedout" component={MainView} />
       <Route path="/booksList" component={BooksList} />
       <Route path="/" component={MainView} />

   </Switch>

);

export default BaseRouter;
