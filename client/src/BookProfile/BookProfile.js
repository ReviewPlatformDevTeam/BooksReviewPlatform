import React, { Component } from "react";
import data from '../Mocks/books.json';
import { Card } from 'antd';
import './BookProfile.css';
import Rater from 'react-rater';
import { withRouter } from "react-router-dom";

const { Meta } = Card;

export class BookProfile extends Component {

    constructor(props) {
        super(props);

        let splitedPath = window.location.pathname.split("/");
        let bookId = 1;
        if(splitedPath.length == 3) bookId = parseInt(splitedPath[2]);

        this.state = {
            bookId: bookId,
            bookData: undefined,
        }
    }

    componentDidMount() {
        this.loadBookData();
    }

    loadBookData() {
        let bookData = Object.keys(data).filter(item => { return data[item].id === this.state.bookId });
        this.setState({bookData: data[bookData[0]]})
    }

    createBookCard = () => {
        return (
            <Card
                className='bookCard'
                cover={<img alt="" src={this.state.bookData.image} />}>
                <Meta title={this.state.bookData.title}
                      description={
                          <i onClick={e => this.props.history.push('/author')} className='authorName'>
                              {this.state.bookData.author}
                          </i>}/>
            </Card>
        );
    }

    createDescriptionCard = () => {
        return (
            <Card
                className='descriptionCard'
                title='Description'
                bodyStyle={{
                    maxHeight: 300,
                    overflow: "auto"
                }}
            >
                <p>{this.state.bookData.description}</p>
            </Card>
        );
    }

    createDetailsCard = () => {
        return (
            <Card
                className='detailsCard'
                title='Details'
                bodyStyle={{
                    maxHeight: 300,
                    overflow: "auto"
                }}
            >
                <strong>Title: </strong>{this.state.bookData.title}<br />
                <strong>Author: </strong>{this.state.bookData.author}<br />
                <strong>Release date: </strong>{this.state.bookData.releaseDate}<br />
                <br />
                <p style={{fontSize: 'x-large'}}>
                    <Rater total={5} rating={this.state.bookData.score} interactive={false}/>
                    {this.state.bookData.numOfReviews} rated
                </p>
            </Card>
        );
    }

    render() {
        return (
            <div className="bookProfile" style={{height: "100vh"}}>
            {
                this.state.bookData ?
                    <div>
                        {this.createBookCard()}
                        <div className='bookInfo'>
                            {this.createDescriptionCard()}
                            {this.createDetailsCard()}
                        </div>
                    </div>
                    : null
            }
            </div>
        );
    }
}

export default withRouter(BookProfile);