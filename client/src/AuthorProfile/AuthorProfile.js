import React, { Component } from "react";
import data from '../Mocks/authors.json';
import { Card } from 'antd';
import './AuthorProfile.css';
import { withRouter } from "react-router-dom";
import defaultAuthor from '../pictures/authorDefault.png';

const defaultAuthorId = 1;

export class AuthorProfile extends Component {

    constructor(props) {
        super(props);

        let splitedPath = window.location.pathname.split("/");
        let authorId = defaultAuthorId;
        if(splitedPath.length === 3) {
            authorId = parseInt(splitedPath[2]);
        }

        this.state = {
            authorId: authorId,
            authorData: undefined,
        }
    }


    componentDidMount() {
        this.loadAuthorData();
    }

    loadAuthorData() {
        let authorData = Object.keys(data).filter(item => { return data[item].id === this.state.authorId })[0];
        authorData = data[authorData];

        if(authorData.photo.length === 0) {
            authorData.photo = defaultAuthor;
        }
        this.setState({authorData: authorData})
    }

    createAuthorCard = () => {
        return (
            <Card
                className='authorCard'
                cover={<img alt="" src={this.state.authorData.photo} />}>
                     <p><b> {this.state.authorData.author} </b></p>

            </Card>
        );
    }

    createDescriptionCard = () => {
        return (
            <Card
                className='descriptionCard'
               title='Information'
                bodyStyle={{
                    maxHeight: 300,
                    overflow: "auto"
                }}
            >
                <p>{this.state.authorData.description}</p>
            </Card>
        );
    }

    createDetailsCard = () => {
                const autBooks = this.state.authorData.books.map(item => <div
    onClick={ e => this.props.history.push(`/book/${item.bid}`)}
    className='detailsCard'

        key = {item.bid}
        >
        <li>{item.title}</li>
        </div>)
        return (
            autBooks
        );
    }

    render() {
        return (
            <div className="authorProfile" style={{height: "100vh"}}>
            {
                this.state.authorData ?
                    <div>
                        {this.createAuthorCard()}
                        <div className='authorInfo'>
                            {this.createDescriptionCard()}
                            <div className='booksTitle'>Books</div>
                            <u>{this.createDetailsCard()}</u>

                        </div>
                    </div>
                    : null
            }
            </div>
        );
    }
}

export default withRouter(AuthorProfile);
