import React, { Component } from 'react';
import './AuthorsList.css';
import { withRouter } from "react-router-dom";
import { authorsListService } from './services/AuthorsListService';
import {Spin} from "antd";


class AuthorsList extends Component {

    constructor (props) {
        super(props);
        this.state = {
            authorsListData: undefined,
            loading: true
        };
    }

    componentDidMount() {
        authorsListService.getAllAuthors().then(resp => this.setState({authorsListData: resp, loading: false}));
    }

    renderAuthorsList = () => {
        const authorsNode = this.state.authorsListData.map(item =>
            <span key={item.id} onClick={ e => window.setTimeout(() => {
                this.props.history.push(`/author/${item.id}`)}, 500) } >

            <div className="authorContainer">
                <div className="photoContainer">
                      <img src={item.imageUrl} alt=""/>
                 </div>
                <p><b>{item.name}</b></p>
            </div>
            </span>)

        return authorsNode;
    }

    render(){
        return (
            <Spin size="large" style={{height: '100vh'}} spinning={this.state.loading} >
                <div className="authorListContainer">
                    { this.state.authorsListData ? this.renderAuthorsList(): null }
                </div>
            </Spin>
    );
    }
}
export default withRouter(AuthorsList);
