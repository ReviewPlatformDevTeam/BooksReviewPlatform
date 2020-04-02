import React, { Component } from 'react';
import './RegistrationView.css';

const axios = require('axios');

class RegistrationView extends Component {
    constructor(props) {
        super(props);
        this.state = {
            login: '',
            email: '',
            password: ''
        };
        this.handleLoginChange = this.handleLoginChange.bind(this);
        this.handleEmailChange = this.handleEmailChange.bind(this);
        this.handlePasswordChange = this.handlePasswordChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event){
        event.preventDefault();
        console.log('form is submitted. Login:', this.state.login,
            'Email:', this.state.email,
            'Password:', this.state.password);

        let addUser = {                      //let login, email, pass from forms
            login: this.state.login,
            email: this.state.email,
            password: this.state.password
        };
        axios.post('../../../server/RegistrationForm', addUser )    //Send Login, email, pass to server

        axios.get('http://localhost:3000/register')                //Check HTTP status code
            .then(result => console.log('success:', result))
            .catch(error => console.log('error:', error));
    };

    handleEmailChange(event) {
        console.log('email was changed', event.target.value);
        this.setState({email: event.target.value})
    }

    handleLoginChange(event) {
        console.log('Login was changed', event.target.value);
        this.setState({login: event.target.value})
    }

    handlePasswordChange(event) {
        console.log('password was changed', event.target.value);
        this.setState({password: event.target.value})
    }

    render() {
        return (
            <div className={RegistrationView}>
                <div className='Fon'>
                    <div className="RegContainer">
                        <h1>Registration</h1>
                        <form onSubmit={this.handleSubmit}>

                            <input                                   //login form
                                type="text"
                                placeholder="Login"
                                value={this.state.login}
                                onChange={this.handleLoginChange}
                            />
                            <input                                   //email form
                                type="email"
                                placeholder="E-mail"
                                value={this.state.email}
                                onChange={this.handleEmailChange}
                            />
                            <input                                   //Password form
                                type="password"
                                placeholder="Password"
                                value={this.state.password}
                                onChange={this.handlePasswordChange}
                            />
                            <button >Get Started!</button>
                        </form>
                    </div>
                </div>
            </div>
        );
    }
}

export default RegistrationView;


