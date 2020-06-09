import React from 'react';
import './SearchBooks.css';
import { SearchOutlined } from '@ant-design/icons';
import Bestsellers from './Bestsellers/Bestsellers';
import Categories from './Categories/Categories';

const SearchBooks = (props) => {
    return (
        <form action="" className="search-books-form" onSubmit={(e) => e.preventDefault()}>
            <div className="search-bar">
                <input type="text" name="" id="" />
                <button className="search" type="submit">
                    <SearchOutlined />
                </button>
            </div>
            <Bestsellers />
            <Categories />
        </form>
    );
}

export default SearchBooks;