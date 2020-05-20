import React, { Component } from "react";
import bookMock from '../Mocks/books.json';
import reviewsMock from '../Mocks/reviews.json';
import { Card, Comment, List, Layout } from 'antd';
import './BookProfile.css';
import Rater from 'react-rater';
import { withRouter } from "react-router-dom";
import defaultBook from '../pictures/bookSmile.png';

const { Meta } = Card;
const defaultBookId = 1;

export class BookProfile extends Component {

    constructor(props) {
        super(props);

        let splitedPath = window.location.pathname.split("/");
        let bookId = defaultBookId;
        if(splitedPath.length === 3) {
            bookId = parseInt(splitedPath[2]);
        }

        this.state = {
            bookId: bookId,
            bookData: undefined,
            reviews: undefined
        }
    }

    componentDidMount() {
        this.loadBookData();
        this.loadReviews();
    }

    loadBookData() {
        let bookData = Object.keys(bookMock).filter(item => { return bookMock[item].id === this.state.bookId })[0];
        bookData = bookMock[bookData];

        if(bookData.image.length === 0) {
            bookData.image = defaultBook;
        }
        this.setState({bookData: bookData})
    }

    loadReviews() {
        this.setState({reviews: reviewsMock.reviews});
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

    commentHeader = (item) => {
        return (
            <div>
                <strong style={{paddingRight: '10px'}}>{item.author}</strong>
                {item.date}
                <p style={{fontSize: 'large'}}><Rater total={5} rating={item.score} interactive={false}/> {item.score}</p>
            </div>
        );
    }

    createReviewComponent = () => {
        return(
            <List
                className="commentList"
                header={"Reviews"}
                itemLayout="horizontal"
                dataSource={this.state.reviews}
                renderItem={item => (
                <li>
                    <Comment
                    author={this.commentHeader(item)}
                    avatar={'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png'}
                    content={item.review}
                    />
                </li>
                )}
            />
        );
    }

    render() {
        return (
            <div className="bookProfile" style={{height: "100vh"}}>
                <Layout style={{paddingTop: 50, backgroundColor: 'white'}} className='bookLayout'>
                    {
                        this.state.bookData ?
                            <div>
                                {this.createBookCard()}
                                <div className='bookInfo'>
                                    {this.createDescriptionCard()}
                                    {this.createDetailsCard()}
                                </div>
                            </div>: null
                    }
                </Layout>
                <Layout style={{backgroundColor: 'white'}}>
                    { 
                        this.state.reviews  ? 
                            <div className='commentSection'>
                                {this.createReviewComponent()}
                            </div>: null
                    }
                </Layout>
            </div>
        );
    }
}

export default withRouter(BookProfile);