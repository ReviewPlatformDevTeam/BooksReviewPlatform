import React, { Component } from 'react';
import Login from './Login/Login';


class LoginView extends Component {
  render() {
  return (
    <div className="Login" style={{height: "100vh"}}>
        <Login />
    </div>);
  }

}

export default LoginView;
