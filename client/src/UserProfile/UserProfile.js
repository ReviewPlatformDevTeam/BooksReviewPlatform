import React, { Component } from "react";
import { Card } from 'antd';
import './UserProfile.css';
import { withRouter } from "react-router-dom";
import defaultPicture from '../pictures/userDefault.png';


export class UserProfile extends Component {

    constructor(props) {
        super(props);

        this.state = {
            userData: undefined,
        }
    }

    componentDidMount() {
        this.loadUserData();
    }

    loadUserData() {
        let user = window.localStorage.getItem('user');
        user = JSON.parse(user);

        if(user.image === undefined || user.image.length === 0) {
            user.image = defaultPicture;
        }

        this.setState({userData: user})
    }

    createUserCard = () => {
        return (
            <Card
                className='userCard'
                cover={<img alt="" src={this.state.userData.image} />}>
                    <b>Username: </b>{this.state.userData.name}
                    <br />
                    <b>Email: </b>{this.state.userData.email}
            </Card>
        );
    }

    render() {
        return (
            <div className="userProfile" style={{height: "100vh"}}>
            {
                this.state.userData ?
                    <div>
                        {this.createUserCard()}
                    </div>
                    : null
            }
            </div>
        );
    }
}

export default withRouter(UserProfile);
