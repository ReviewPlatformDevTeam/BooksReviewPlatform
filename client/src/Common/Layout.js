import React from 'react';
import { Layout, Menu } from 'antd';
import { Link, withRouter } from 'react-router-dom';
import logo from '../pictures/Common_logo.png';


const { Header, Content } = Layout;

class CustomLayout extends React.Component{
    
    componentDidMount() {
        this.props.history.push('/main');
    }

    render(){
        return (
        <Layout className="layout">
            <Header style={{ position: 'fixed', zIndex: 1, width: '100%', background: '#ffffff' }}>
            <div style={{color: "black", float: "left", width: "200px"}}>
                <img src={logo} alt='logo'/>
                <Link style={{color: "black"}} to="/main">  Book Review Platform </Link>
            </div>
            <Menu
                theme="light"
                mode="horizontal"
                style={{lineHeight: '64px',}} >
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
            </Header>
            <Content style={{ padding: '0 0px', background: 'none', paddingTop: '64px', height: "100%" }}>
                <div>
                {this.props.children}
                </div>
            </Content>
        </Layout>
    );
    }
}

export default withRouter(CustomLayout);
