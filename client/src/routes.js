import React from 'react';
import { Route, Switch } from 'react-router-dom';
import LoginView from './Authorization/LoginView';
import MainView from './Main/MainView';
import RegistrationView from './Authorization/RegistrationView';


const BaseRouter = () => (
   <Switch>
       <Route path="/login" component={LoginView} />
       <Route path="/register" component={RegistrationView} />
       <Route path="/signedin" />
       <Route path="/" component={MainView} />
   </Switch>
);

export default BaseRouter;