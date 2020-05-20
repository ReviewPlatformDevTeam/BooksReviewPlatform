import React, { Component } from "react";
import data from '../Mocks/user.json';
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
        let userData = data;
        if(userData.image.length === 0) {
            userData.image = defaultPicture;
        }
        this.setState({userData: userData})
    }

    createUserCard = () => {
        return (
            <Card
                className='userCard'
                cover={<img alt="" src={this.state.userData.image} />}>
                    <b>Username: </b>{this.state.userData.username}
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