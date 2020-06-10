import React, { Component } from "react";
import { Card } from 'antd';
import './AuthorProfile.css';
import { withRouter } from "react-router-dom";
import defaultAuthor from '../pictures/authorDefault.png';
import {authorProfileService} from "./services/AuthorProfileService";
import {bookListService} from "../BooksList/services/BookListService";

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

        authorProfileService.getAuthorById(this.state.authorId).then(resp =>
            {
                let authorData = resp;
                if(authorData.imageUrl === null || authorData.imageUrl.length === 0) {
                    authorData.imageUrl = defaultAuthor;
                }

                bookListService.getAllBooks().then(books => {
                    authorData.books = books.filter(book => this.state.authorId === book.author);
                    this.setState({authorData: authorData, loading: false});
                });
            }
        );

    }

    createAuthorCard = () => {
        return (
            <Card
                className='authorCard'
                cover={<img alt="" src={this.state.authorData.imageUrl} />}>
                     <p><b> {this.state.authorData.name} </b></p>

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
    onClick={ e => this.props.history.push(`/book/${item.id}`)}
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
