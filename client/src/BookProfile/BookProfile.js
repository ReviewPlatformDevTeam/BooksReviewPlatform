import React, { Component } from "react";
import { Card, Comment, List, Layout, Form, Button, Input, Spin } from 'antd';
import './BookProfile.css';
import Rater from 'react-rater';
import { withRouter } from "react-router-dom";
import defaultBook from '../pictures/bookSmile.png';
import { bookProfileService } from './services/BookProfileService';


const mockDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                         "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure " +
                         "dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non " +
                         "proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur " +
                         "adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud " +
                         "exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate " +
                         "velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia " +
                         "deserunt mollit anim id est laborum. ";
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
            score: 0,
            loading: true
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
            alert("Review can't be empty")
            return;
        }
    
        this.setState({
          submitting: true,
        });

        setTimeout(() => {
            let user = window.localStorage.getItem('user');
            user = JSON.parse(user);

            let reviewData = {
                user: user.id,
                content: this.state.value,
                score: this.state.score,
                book: this.state.bookId,
            }

            bookProfileService.addReviewForBook(reviewData).then(resp =>
                {
                    this.setState({
                        submitting: false,
                        value: '',
                        score: 0,
                        reviews: [
                            reviewData,
                            ...this.state.reviews,
                        ],
                    });
                }
            );

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
        bookProfileService.getBookById(this.state.bookId).then(resp => 
            {
                let bookData = resp;

                if(bookData.photoUrl === null || bookData.photoUrl.length === 0) {
                    bookData.photoUrl = defaultBook;
                }
                this.setState({bookData: bookData, loading: false});
            }
        );
    }

    loadReviews() {
        bookProfileService.getReviewsForBook(this.state.bookId).then(resp =>
            {
                let reviewData = resp;
                this.setState({reviews: reviewData.reverse(), loading: false});
            }
        );
    }

    createBookCard = () => {
        return (
            <Card
                className='bookCard'
                cover={<img alt="" src={this.state.bookData.photoUrl} />}>
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
                <p>{mockDescription}</p>
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
                    <Rater total={5} rating={parseFloat(this.state.bookData.score)} interactive={false}/>
                    {this.state.bookData.numOfReviews} rating(s)
                </div>
            </Card>
        );
    }

    commentHeader = (item) => {
        return (
            <div>
                <strong style={{paddingRight: '10px'}}>{item.user}</strong>
                <div style={{fontSize: 'large'}}><Rater total={5}
                                                        rating={parseInt(item.score)}
                                                        interactive={false}/> {parseInt(item.score)}</div>
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
                    avatar={'https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png'}
                    content={item.content}
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
            <Spin size="large" spinning={this.state.loading} >
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
            </Spin>
        );
    }
}

export default withRouter(BookProfile);