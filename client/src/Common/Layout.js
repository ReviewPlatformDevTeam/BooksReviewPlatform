import React from 'react';
import { Layout, Menu } from 'antd';
import { Link, withRouter } from 'react-router-dom';
import logo from '../pictures/Common_logo.png';
import { HomeOutlined, UserOutlined, TeamOutlined, ReadOutlined } from '@ant-design/icons';
import { authService } from "../Authorization/_auth-services/authentication";


const { Header, Content } = Layout;

class CustomLayout extends React.Component{

    constructor (props) {
        super(props);
        this.menuType = undefined;
        this.iconPath = '/';
    }

    componentDidMount() {
        this.setMenu();
    }

    setMenu() {
        let authorizedMenuPaths = ['/booksList', '/profile', '/home'];
        let type = authorizedMenuPaths.includes(window.location.pathname)
            || window.location.pathname.startsWith("/book")
            || window.location.pathname.startsWith("/author")
            || window.location.pathname.startsWith("/category")
            ? "authorized": "non-authorized";

        if (type === "authorized") this.iconPath = '/home';
        else this.iconPath = '/';

        this.menuType = type;
    }

    nonAuthorizedMenu = () => {
        return(
            <Menu
                theme="light"
                mode="horizontal"
                style={{lineHeight: '64px'}} >
                <Menu.Item
                    key="register"
                    style={{float: 'right'}}>
                    <Link to="/register">Sign up</Link>
                </Menu.Item>
                <Menu.Item
                    key="login"
                    style={{float: 'right'}}>
                    <Link to="/login">Login</Link>
                </Menu.Item>

            </Menu>
        );
    }

    authorizedMenu = () => {
        return(
            <Menu
                theme="light"
                mode="horizontal"
                style={{lineHeight: '64px', width: '100%'}} >
                <Menu.Item
                    key="home"
                    style={{float: 'center'}}>
                    <Link to="/home"><HomeOutlined />Home</Link>
                </Menu.Item>
                <Menu.Item
                    key="profile"
                    style={{float: 'center', marginLeft: '2%'}}>
                    <Link to="/profile"><UserOutlined />Profile</Link>
                </Menu.Item>
                <Menu.Item
                    key="signout"
                    onClick={() => {authService.logout()}}
                    style={{float: 'right'}}>
                    <Link to="/signedout">Sign out</Link>
                </Menu.Item>
                <Menu.Item
                    key="booksList"
                    style={{float: 'center', marginLeft: '2%'}}>
                    <Link to="/booksList">< ReadOutlined />Books</Link>
                </Menu.Item>
                <Menu.Item
                    key="authorsList"
                    style={{float: 'center', marginLeft: '2%'}}>
                    <Link to="/authorsList">< TeamOutlined />Authors</Link>
                </Menu.Item>
            </Menu>
        );
    }

    renderMenu = () => {
        if ( this.menuType === "authorized" ) return <this.authorizedMenu/>;
        else return <this.nonAuthorizedMenu />;
    }

    render(){
        this.setMenu();
        return (
        <Layout className="layout">
            <Header style={{ position: 'fixed', zIndex: 1, width: '100%', background: '#ffffff' }}>
                <div style={{color: "black", float: "left", width: "350px"}}>
                    <img src={logo} alt='logo'/>
                    <Link style={{color: "black", fontSize: '20px'}} to={this.iconPath}>  Book Review Platform </Link>
                </div>
                <this.renderMenu />
            </Header>
            <Content style={{ padding: '0 0px', backgroundColor: 'white', paddingTop: '64px', height: "100%" }}>
                <div>
                {this.props.children}
                </div>
            </Content>
        </Layout>
    );
    }
}

export default withRouter(CustomLayout);
