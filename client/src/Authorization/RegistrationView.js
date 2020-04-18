import React, { Component } from 'react';
import './RegistrationView.css';
import { Redirect } from "react-router-dom";
<script src="https://unpkg.com/axios/dist/axios.min.js"></script>
const axios = require('axios');

class RegistrationView extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: '',
            email: '',
            password: '',
            redirect: false
        };
        this.handleUsernameChange = this.handleUsernameChange.bind(this);
        this.handleEmailChange = this.handleEmailChange.bind(this);
        this.handlePasswordChange = this.handlePasswordChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    redirectToMainPage = () => {
        this.setState({redirect: true});
    }

    handleSubmit(event){
        event.preventDefault();
            alert(`Welcome, ${this.state.username}!`);


        let addUser = {
            username: this.state.username,
            email: this.state.email,
            password: this.state.password
        };

        const checkStatus = (response) => {
            if(!response.ok) {
                throw Error(response.status);
            }
            return response;
        };

        axios.post('/users', addUser )
            .then(response => checkStatus(response))
            .then(response => response.json())
            .catch(error => {
                return {success: false, status: error.message}
            });
    };

    handleEmailChange(event) {
        this.setState({email: event.target.value})
    }
    handleUsernameChange(event) {
        this.setState({username: event.target.value})
    }
    handlePasswordChange(event) {
        this.setState({password: event.target.value})
    }


    render() {
        const { username, email, password, redirect } = this.state;

        if(redirect) {
            return (<Redirect to='/signedup' />);
        }

        return (
            <div className="RegistrationView">
            <div className='Fon'>
            <div className="RegContainer">
            <h1>Registration</h1>
            <form onSubmit={this.handleSubmit}>

            <input
        type="text" pattern="[a-zA-Z0-9]+"
        title="USE ONLY LETTERS AND NUMBERS PLEASE"
        placeholder="Login"
        value={this.state.username}
        onChange={this.handleUsernameChange}
        required
        />
        <input
        type="text" pattern="[a-zA-Z0-9]+@[a-zA-Z0-9]+\.[a-zA-Z0-9]+"
        title="ENTER A VALID Email ADDRESS. example@example.com"
        placeholder="E-mail"
        value={this.state.email}
        onChange={this.handleEmailChange}
        required
        />
        <input
        type="password" pattern="(?=.*\d)(?=.*[a-z])(?=.*[A-Z]).{5,}"
        title="Your password must be at least 5 characters as well as contain at least one uppercase, one lowercase, and one number."
        placeholder="Password"
        value={this.state.password}
        onChange={this.handlePasswordChange}
        required
        />
        <input type="submit" value="Get Started!" />
        </form>
        </div>
        </div>
        </div>
    );
    }
}
export default RegistrationView;


