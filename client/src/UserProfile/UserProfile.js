import React, { Component } from "react";
import {Card, Comment, List} from 'antd';
import './UserProfile.css';
import { withRouter } from "react-router-dom";
import defaultPicture from '../pictures/userDefault.png';
import {userProfileService} from "./services/UserProfileService";
import Rater from "react-rater";
import {bookProfileService} from "../BookProfile/services/BookProfileService";
import defaultBook from "../pictures/bookSmile.png";


export class UserProfile extends Component {

    constructor(props) {
        super(props);

        this.state = {
            userData: undefined,
        }
    }

    componentDidMount() {
        this.loadUserData();
    }

    loadUserData() {
        let user = window.localStorage.getItem('user');
        user = JSON.parse(user);

        if(user.image === undefined || user.image.length === 0) {
            user.image = defaultPicture;
        }

        this.setState({userData: user}, () => {
            this.loadReviews();
        })
    }

    loadReviews() {
        userProfileService.getReviewsForUser(this.state.userData.id).then(resp =>
            {
                let reviewData = resp;
                reviewData = Promise.all(reviewData.map(async review => {
                    let book = await bookProfileService.getBookById(review.book);

                    if(book.photoUrl === null || book.photoUrl.length === 0) {
                        book.photoUrl = defaultBook;
                    }

                    review.book = book
                    return review
                }));

                reviewData.then(result => {
                    this.setState({reviews: result.reverse(), loading: false});
                });
            }
        );
    }

    commentHeader = (item) => {
        return (
            <div>
                <strong style={{paddingRight: '10px'}}>Book title: </strong>{item.book.title}
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
                header={"User reviews"}
                itemLayout="horizontal"
                dataSource={this.state.reviews}
                pagination={{
                    pageSize: 4,
                }}
                locale={{ emptyText: 'No reviews for user' }}
                renderItem={item => (
                    <List.Item>
                        <Comment
                            author={this.commentHeader(item)}
                            style={{width: '100%'}}
                            content={
                                <div>
                                    <img alt="" style={{height: "100px", float: "right"}} src={item.book.photoUrl} />
                                    <p>
                                        <b>Review: </b>
                                        {item.content}
                                    </p>
                                </div>
                            }
                        />
                    </List.Item>
                )}
            />
        );
    }

    createUserCard = () => {
        return (
            <Card
                className='userCard'
                cover={<img alt="" src={this.state.userData.image} />}>
                    <b>Username: </b>{this.state.userData.name}
                    <br />
                    <b>Email: </b>{this.state.userData.email}
            </Card>
        );
    }

    render() {
        return (
            <div className="userProfile" style={{height: "100vh"}}>
                {
                    this.state.userData ?
                        <div>
                            {this.createUserCard()}
                        </div>
                        : null
                }
                <div>
                    {
                        this.state.reviews  ?
                            <div className='commentSection'>
                                {this.createReviewComponent()}
                            </div>: null
                    }
                </div>
            </div>
        );
    }
}

export default withRouter(UserProfile);
