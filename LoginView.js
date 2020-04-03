import React, { Component } from 'react';
import './LoginView.css';

class LoginView extends Component
{
    constructor(props)
    {
        super(props);

        this.state =
        {
            login: '',
            password: ''
        };

        this.handleLoginChange = this.handleLoginChange.bind(this);
        this.handlePasswordChange = this.handlePasswordChange.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }

    handleSubmit(event)
    {
        event.preventDefault();

        console.log
        ('Login:', this.state.login,
            'Password:', this.state.password
        );
        
        // eslint-disable-next-line no-unused-vars
        let login =
        {
            login: this.state.login,
            password: this.state.password
        };

    };


    handleLoginChange(event)
    {
        console.log('Login', event.target.value);
        this.setState({login: event.target.value})
    }

    handlePasswordChange(event)
    {
        console.log('Password', event.target.value);
        this.setState({password: event.target.value})
    }

    render()
    {
        return(
            <div className={LoginView}>
                <div className='MainLogin'>
                    <div className="LoginContainer">
                        <h1>Login</h1>
                        <form onSubmit={this.handleSubmit}>

                            <input
                                type="text"
                                placeholder="Login"
                                value={this.state.login}
                                onChange={this.handleLoginChange}
                            />

                            <input
                                type="password"
                                placeholder="Password"
                                value={this.state.password}
                                onChange={this.handlePasswordChange}
                            />
                            <button >Login</button>
                        </form>
                    </div>
                </div>
            </div>
        );
    }
}

export default LoginView;

