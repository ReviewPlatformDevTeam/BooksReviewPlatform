import React, { Component } from 'react';
import { BrowserRouter as Router } from 'react-router-dom';
import './App.css';
import BaseRouter from './routes';
import Layout from './Common/Layout.js';
import 'antd/dist/antd.css';
import MainView from './Main/MainView';


class App extends Component {
  render() {

  return (
    <div className="App" style={{height: "100%"}}>
      <Router>
        <Layout {...this.props}>
          <BaseRouter />
        </Layout>
      </Router>
    </div>);
  }

}

export default App;
