import React from 'react';
import './SearchBooks.css';
import Bestsellers from './Bestsellers/Bestsellers';
import Categories from './Categories/Categories';

const SearchBooks = (props) => {
    return (
        <form action="" className="search-books-form" onSubmit={(e) => e.preventDefault()}>
            <input type="text" name="" id="" />
            <Bestsellers />
            <Categories />
        </form>
    );
}

export default SearchBooks;