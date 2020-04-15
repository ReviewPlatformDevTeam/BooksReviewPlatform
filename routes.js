import React from 'react';
import {Route} from 'react-router-dom';
import LoginView from './Authorization/LoginView';
import MainView from './Main/MainView';
import RegistrationView from './Authorization/RegistrationView';


const BaseRouter = () => (
   <div>
       <Route path="/main" component={MainView} />
       <Route path="/login" component={LoginView} />
       <Route path="/register" component={RegistrationView} />
       <Route path="/signedin" />
       <Route path="/signedup" />
   </div>
);

export default BaseRouter;