import React, { Component } from 'react';
import PasswordReset from './PasswordReset/PasswordReset';


class PasswordResetView extends Component {
    render() {
        return (
            <div className="Login" style={{height: "100vh"}}>
                <PasswordReset />
            </div>);
    }

}

export default PasswordResetView;
