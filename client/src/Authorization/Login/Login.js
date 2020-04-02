import React, { Component } from "react";
import { Redirect } from "react-router-dom";
import { authService } from "../_auth-services/authentication";
import "./Login.css";
import bookImage from "../../pictures/books.png";

class Login extends Component {
    constructor(props) {
        super(props);
        this.state = {
            username: "",
            password: "",
            submitted: false, 
            redirect: false
        };
    }
    
    redirectToMainPage = () => {
        this.setState({redirect: true});
    }

    componentDidMount() {
        if(authService.isAuthenticated()) {
            this.redirectToMainPage();
        }
    }

    loginHandler = async event => {
        const { username, password } = this.state;

        event.preventDefault();
        this.setState({ submitted: true });

        if (!(username && password)) return;

        const response = await authService.login(username, password);
        if(response.success) {
            const user = {
                name: username,
                token: response.token
            }
            authService.authenticateUser(user);
            this.redirectToMainPage();
        } else {
            if(response.status === '404') {
                alert('Page not found');
            } else {
                alert('Wrong credentials');
            }
        }
        
    };

    changeHandler = event => {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    };

    render() {
        const { username, password, submitted, redirect } = this.state;

        if(redirect) {
            return (<Redirect to='/main' />);
        }

        return (
            <div className="login">
                <link
                    href="https://fonts.googleapis.com/css?family=Poppins&display=swap"
                    rel="stylesheet"
                ></link>

                <img src={bookImage} alt="books icon" />
                <h2>Sign in</h2>
                <form onSubmit={this.loginHandler}>
                    <div
                        className={
                            "input-wrapper" +
                            (submitted && !username ? " error-submit" : "") 
                        }
                    >
                        <input
                            type="text"
                            name="username"
                            onChange={this.changeHandler}
                            placeholder="Login"
                        />
                        <p
                            className={
                                "error-message" +
                                (submitted && !username ? " error-submit" : "")
                            }
                        >
                            This field is mandatory
                        </p>
                    </div>
                    <div
                        className={
                            "input-wrapper" +
                            (submitted && !password ? " error-submit" : "") 
                        }
                    >
                        <input
                            type="password"
                            name="password"
                            onChange={this.changeHandler}
                            placeholder="Password"
                        />
                        <p
                            className={
                                "error-message" +
                                (submitted && !password ? " error-submit" : "")
                            }
                        >
                            This field is mandatory
                        </p>
                    </div>

                    <button type="submit">Sign In</button>
                </form>
                <a href="#">Forgot password?</a>
            </div>
        );
    }
}

export default Login;
