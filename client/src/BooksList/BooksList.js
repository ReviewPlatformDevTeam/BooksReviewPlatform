import React, { Component } from 'react';
import { Link } from 'react-router-dom';
//import data from './books.json';
import './BooksList.css';
import Rater from 'react-rater';
import 'react-rater/lib/react-rater.css';

const axios = require('axios');

class BooksList extends Component {

    axios.get('/books')
        .then(response => (this.books = response))
        .then(response => books.json())
        .then(data => {
         this.setState({
                      books: data,
                          })
}
let books = {
    id: this.setState.title,
    image: this.state.image,
    title: this.setState.title,
    author: this.state.author,
    releaseDate: this.state.image,
    score: this.state.image,
    numOfReviews: this.state.image,
    description: this.state.description,
};

    render(){

        const carNode = Object.keys(data).map(item =>
            <span key={data[item].id}>
            <div className="BookContainer">

                <div className="imageContainer">
                 <img src={data[item].image}/>
                 </div>

                <div className="titleContainer">
                 <p><b><i>"{data[item].title}"</i></b></p>
                 <p><b><u>{data[item].author}</u></b> </p>
                 <p> {data[item].releaseDate}</p>
                 <p><Rater total={5} rating={data[item].score} interactive={false}/>  {data[item].numOfReviews} rated</p>
                 <div className="descrContainer">
                     <div className="descr">{data[item].description.substr(0, 560)} </div>
                     <div className="showMore">Show More</div>
                 </div>
                 </div>
            </div>
            </span>)


        return (
            <div className="container-fluid content-main">
        {carNode}
            </div>
    );
    }
}
export default BooksList;