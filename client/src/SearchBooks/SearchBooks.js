import React from 'react';
import './SearchBooks.css';
import { SearchOutlined } from '@ant-design/icons';
import Bestsellers from './Bestsellers/Bestsellers';
import Categories from './Categories/Categories';

const SearchBooks = (props) => {
    return (
        <div style={{paddingLeft: '5%'}}>
            <form action="" className="search-books-form" onSubmit={(e) => e.preventDefault()}>
                <div className="search-bar" style={{marginLeft: '30%', marginTop: '20px'}} >
                    <input style={{width: '50vw'}} type="text" name="" id="" />
                    <button className="search" type="submit">
                        <SearchOutlined />
                    </button>
                </div>
                <div style={{marginLeft: '30%', display: 'flex'}}>
                    <Bestsellers />
                    <Categories />
                </div>
            </form>
        </div>
    );
}

export default SearchBooks;