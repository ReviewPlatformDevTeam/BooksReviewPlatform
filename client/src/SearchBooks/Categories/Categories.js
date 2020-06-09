import React, { useState, useEffect } from 'react';
import './Categories.css';
import * as categories from '../../Mocks/categories.json';

const Categories = (props) => {

  const [showCategories, switchCategories] = useState(false);

  const returnCategories = () => {
    return (
      <div className="categories-display">
        <div className="categories-container">
          {categories.categories.map((cat, idx) => {
            return (
              <div key={idx}>
                <a href="#">{cat}</a>
              </div>
            );
          })}
        </div>
        <button onClick={() => switchCategories(false)}>
          Close
                </button>
      </div>
    )
  }

  return (
    <div className="categories">
      <button onClick={() => switchCategories(true)} >
        Categories
            </button>
      <div className={`categories-display${showCategories ? "" : " hidden"}`}>
        <div className="categories-container">
          {categories.categories.map((cat, idx) => {
            return (
              <div className="category-link" key={idx}>
                <a href="#">{cat}</a>
              </div>
            );
          })}
          <button id="categories-button" onClick={() => switchCategories(false)}>
            Close
          </button>
        </div>
      </div>
    </div>
  );
}

export default Categories;