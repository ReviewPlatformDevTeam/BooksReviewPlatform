import React, { Component } from 'react';
import data from './books.json';
import './BooksList.css';
import Rater from 'react-rater';
import 'react-rater/lib/react-rater.css';


class BooksList extends Component {

   
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
                     <div className="descr">{data[item].description} </div>
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
