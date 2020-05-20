import React, { Component } from "react";
import bookMock from '../Mocks/books.json';
import reviewsMock from '../Mocks/reviews.json';
import { Card, Comment, List, Layout, Form, Button, Input } from 'antd';
import './BookProfile.css';
import Rater from 'react-rater';
import { withRouter } from "react-router-dom";
import defaultBook from '../pictures/bookSmile.png';
import moment from 'moment';

const { Meta } = Card;
const { TextArea } = Input;
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
            reviews: undefined,
            submitting: false,
            value: '',
            score: 0
        }
    }

    editor = () => (
        <div>
          <div style={{fontSize: 'x-large'}}>
            <Rater total={5} rating={this.state.score} onRate={this.handleRate} interactive={true}/>
          </div>
          <Form.Item>
            <TextArea rows={4} onChange={this.handleChange} value={this.state.value} />
          </Form.Item>
          <Form.Item>
            <Button htmlType="submit" loading={this.state.submitting} onClick={this.handleSubmit} type="primary">
              Add Review
            </Button>
          </Form.Item>
        </div>
      ); 

    handleSubmit = () => {
        if (!this.state.value) {
          return;
        }
    
        this.setState({
          submitting: true,
        });
    
        setTimeout(() => {
            this.setState({
                submitting: false,
                value: '',
                score: 0,
                reviews: [
                {
                    author: 'Username',
                    date: moment().format('YYYY-MM-DD'),
                    review: this.state.value,
                    score: this.state.score
                },
                ...this.state.reviews,
                ],
            });
            }, 1000);
      };

    handleChange = e => {
        this.setState({
          value: e.target.value
        });
      }; 

    handleRate = e => {
        this.setState({
            score: e.rating
        });
      };   

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
                <div style={{fontSize: 'x-large'}}>
                    <Rater total={5} rating={this.state.bookData.score} interactive={false}/>
                    {this.state.bookData.numOfReviews} rated
                </div>
            </Card>
        );
    }

    commentHeader = (item) => {
        return (
            <div>
                <strong style={{paddingRight: '10px'}}>{item.author}</strong>
                <div style={{fontSize: 'large'}}><Rater total={5} rating={item.score} interactive={false}/> {item.score}</div>
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
                pagination={{
                    pageSize: 4,
                  }}
                renderItem={item => (
                <List.Item>
                    <Comment
                    author={this.commentHeader(item)}
                    datetime={item.date}
                    avatar={'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png'}
                    content={item.review}
                    />
                </List.Item>
                )}
            />
        );
    }

    commentInput = () => {
        return(
                <Comment
                content={
                        <this.editor/>
                    }/>
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
                <div className='commentInput'>{this.commentInput()}</div>
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