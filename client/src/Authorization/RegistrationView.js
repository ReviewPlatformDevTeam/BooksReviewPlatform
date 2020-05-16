import React, { Component } from 'react';
import Registration from './Registration/Registration';


class RegistrationView extends Component {
    render() {
        return (
            <div className="Registration" style={{height: "100vh"}}>
                <Registration />
            </div>);
    }

}

export default RegistrationView;
