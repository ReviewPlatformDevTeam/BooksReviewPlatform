import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import * as serviceWorker from './serviceWorker';

// ReactDOM.render(<App />, document.getElementById('root'));

import Button from 'antd/lib/button';
import QueueAnim from 'rc-queue-anim';
import { OverPack } from 'rc-scroll-anim';

class Test extends React.Component{
  state = {
    show: true
  };
  onClick = () => {
    this.setState({
      show: !this.state.show
    });
  }
  render() {
    return (
      <div className="queue-demo">
          <div
          style={{height: "1200px", backgroundColor: "#001529"}}
          ></div>
          <OverPack style={{ overflow: 'hidden' }} playScale={0.3} >
        <QueueAnim className="demo-content">
          {this.state.show ? [
            <div className="demo-thead" key="a">
              <ul>
                <li>ABOUT US</li>
                <li>ABOUT US</li>
                <li>ABOUT US</li>
              </ul>
            </div>,
            <div className="demo-tbody" key="b">
              <ul>
                <li>Content</li>
                <li>Content</li>
                <li>Content</li>
              </ul>
            </div>,
            <div className="demo-thead" key="c">
            <ul>
              <li>ABOUT US</li>
              <li>ABOUT US</li>
              <li>ABOUT US</li>
            </ul>
          </div>,
          <div className="demo-tbody" key="d">
            <ul>
              <li>Content</li>
              <li>Content</li>
              <li>Content</li>
            </ul>
          </div>
          ] : null}
        </QueueAnim>
        </OverPack>
        <div
          style={{height: "1200px", backgroundColor: "#001529"}}
          ></div>
      </div>
    );
  }
};

ReactDOM.render(<Test />, document.getElementById('root'));

// If you want your app to work offline and load faster, you can change
// unregister() to register() below. Note this comes with some pitfalls.
// Learn more about service workers: https://bit.ly/CRA-PWA
serviceWorker.unregister();
