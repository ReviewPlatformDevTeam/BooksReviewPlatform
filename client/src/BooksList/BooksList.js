import React, { Component } from 'react';
import './BooksList.css';
import Rater from 'react-rater';
import 'react-rater/lib/react-rater.css';
import { withRouter } from "react-router-dom";
import { bookListService } from './services/BookListService';
import { Spin } from 'antd';

// TODO - delete after field implemented on backend
const mockDescription = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. " +
                         "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure " +
                         "dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non " +
                         "proident, sunt in culpa qui officia deserunt mollit anim id est laborum. Lorem ipsum dolor sit amet, consectetur " +
                         "adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud " +
                         "exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate " +
                         "velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia " +
                         "deserunt mollit anim id est laborum. ";

class BooksList extends Component {

    constructor (props) {
        super(props);
        this.state = {
            bookListData: undefined,
            loading: true
        };
    }

    componentDidMount() {
        bookListService.getAllBooks().then(resp => this.setState({bookListData: resp, loading: false}));
    }

    createList = () => {
        const carNode = this.state.bookListData.map(bookListItem =>
            <span key={bookListItem.id} onClick={ e => this.props.history.push(`/book/${bookListItem.id}`) } >
            <div className="BookContainer">

                <div className="imageContainer">
                      <img src={bookListItem.photoUrl} alt=""/>
                 </div>

                <div className="titleContainer">
                 <p><b><i>"{bookListItem.title}"</i></b></p>
                 <p><b><u>{bookListItem.author}</u></b> </p>
                 <p> {bookListItem.releaseDate}</p>
                 <div><Rater total={5} rating={parseFloat(bookListItem.score)} interactive={false}/>  {bookListItem.numOfReviews} rating(s)</div>
                 <div className="descrContainer">
                     <div className="descr">{mockDescription} </div>
                     <div className="showMore">Show More</div>
                 </div>
                 </div>
            </div>
            </span>)

        return carNode;
    }
   
    render(){

        return (
            <Spin size="large" style={{height: '100vh'}} spinning={this.state.loading} >
                <div className="container-fluid content-main">
                    { this.state.bookListData ? this.createList(): null }
                </div>
            </Spin>
            );
    }
}
export default withRouter(BooksList);
