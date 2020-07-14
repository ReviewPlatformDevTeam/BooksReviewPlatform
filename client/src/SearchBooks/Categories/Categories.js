import React, { Component } from 'react';
import './Categories.css';
import { categoriesService } from '../services/CategoriesService';

class Categories extends Component {

    constructor(props) {
        super(props);
        this.state = {
            categories: undefined
        }
    }

    componentDidMount() {
        categoriesService.getAllCategories().then(categories => {
            this.setState({categories: categories})
        })
    }

    render() {
        return (
            <div className="categories">
                <div className="categories-display">
                    <div className="categories-container">
                        {
                            this.state.categories ?
                            this.state.categories.map(cat => {
                                return (
                                    <div className="category-link" key={cat.id}>
                                        <a href={`/category/${cat.name}`}>{cat.name}</a>
                                    </div>
                                );
                            }): null
                        }
                    </div>
                </div>
            </div>
        );
    }
}

export default Categories;