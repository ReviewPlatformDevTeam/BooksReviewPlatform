import React, { useState } from 'react';
import './Categories.css';
import * as categories from '../../Mocks/categories.json';

const Categories = (props) => {

  const [showCategories, switchCategories] = useState(false);

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
                <a href="/">{cat}</a>
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