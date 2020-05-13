import React, { Component } from "react";
import { withRouter } from "react-router-dom";
import { authService } from "../_auth-services/authentication";
import passwordresetImage from "../../pictures/passwordreset.png";

export class PasswordReset extends Component {
    constructor(props) {
        super(props);
        this.state = {
            email: "",
            submitted: false
        };
    }

    resetHandler = async event => {
        event.preventDefault();
        this.setState({ submitted: true });

        if (!this.state.email) return;

        const response = await authService.reset(this.state.email);
        if(response.success) {
            alert('Reset link sent to ' + this.state.email);
            this.props.history.push('/login');
        } else {
            if(response.status === '404') {
                alert('User not found');
            } else {
                alert('Error during reset');
            }
        }

    };

    changeHandler = event => {
        const { name, value } = event.target;
        this.setState({ [name]: value });
    };

    render() {
        const { submitted } = this.state;

        return (
            <div className="login">
                <link
                    href="https://fonts.googleapis.com/css?family=Poppins&display=swap"
                    rel="stylesheet"
                ></link>

                <img src={passwordresetImage} alt="passwordreset icon" />
                <h2>Reset Password</h2>
                <form onSubmit={this.resetHandler}>
                    <div
                        className={
                            "input-wrapper" +
                            (submitted && !this.state.email  ? " error-submit" : "")
                        }
                    >
                        <input
                            type="text"
                            name="email"
                            onChange={this.changeHandler}
                            placeholder="Email"
                        />
                        <p
                            className={
                                "error-message" +
                                (submitted && !this.state.email ? " error-submit" : "")
                            }
                        >
                            This field is mandatory
                        </p>
                    </div>
                    <button type="submit">Send</button>
                </form>
            </div>
        );
    }
}

export default withRouter(PasswordReset);