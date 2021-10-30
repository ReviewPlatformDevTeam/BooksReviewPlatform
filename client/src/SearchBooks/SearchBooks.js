import React from 'react';
import './SearchBooks.css';
import Categories from './Categories/Categories';

const SearchBooks = (props) => {
    return (
        <div style={{paddingLeft: '5%'}}>
            <Categories />
        </div>
    );
}

export default SearchBooks;