import React, { Component } from 'react';
import data from '../Mocks/authors.json';
import './AuthorsList.css';
import { withRouter } from "react-router-dom";


class AuthorsList extends Component {

    render(){

        const carNode = Object.keys(data).map(item =>
            <span key={data[item].id} onClick={ e => window.setTimeout(() => {
             this.props.history.push(`/author/${data[item].id}`)}, 500) } >

            <div className="authorContainer">
                <div className="photoContainer">
                      <img src={data[item].photo} alt=""/>
                 </div>
                <p><b>{data[item].author}</b></p>
            </div>
            </span>)


        return (
            <div className="authorListContainer">
        {carNode}
            </div>
    );
    }
}
export default withRouter(AuthorsList);
