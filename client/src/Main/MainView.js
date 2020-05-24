import React, { Component } from 'react';
import QueueAnim from 'rc-queue-anim';
import { OverPack } from 'rc-scroll-anim';
import logo from '../pictures/MainView_logo.png';
import intro from '../pictures/MainView_intro.png';
import contact from '../pictures/MainView_contact.png';
import { DownOutlined, FacebookOutlined, TwitterOutlined, SkypeOutlined, InstagramOutlined } from '@ant-design/icons';


class MainView extends Component{
  state = {
    show: true
  };


  onClick = () => {
    this.setState({
      show: !this.state.show
    });
  }

  headerDiv = () => {
    return (
      <div
          style={{height: "100vh", backgroundColor: "transparent"}}>
            <QueueAnim delay={400} >
              <div key="a">
                <img src={logo} alt='logo' style={{ transform: "translate(-50%, 30%)", position: "absolute" }} />
                <form action="">
                  <input type="text" name="" id=""/>
                </form>
              </div>
            </QueueAnim>
            <DownOutlined style={{ fontSize: "30px", top: "90%", color: "white", position: "absolute" }}/>
      </div>
    );
  }

  introDiv = () => {
    return (
      <OverPack style={{ overflow: 'hidden' }} playScale={0.4} >
      <QueueAnim style={{backgroundColor: "#ffffff"}}>
          <div key="a" style={{padding: "2%", fontWeight: "bold", fontSize: "x-large"}}>
              What is Book Review Platform ?
          </div>
          <div key="b" style={{paddingLeft: "10%", paddingRight: "10%", display: "flex"}}>
            <div style={{float: "left", fontSize: "larger", textAlign: "left", width: "60vw", margin: "5%"}} >
              Book Review Platform was created to let all bookworms explor world of books. Thanks to our platform 
              you can find interesting books, give your opinion about it or read reviews to make sure that book is
              worth to be read.
              <br />
              <br />
              Join to our community and enjoy reading. Many cathegories that you can discover. Thousands of books and even
              more reviews. Do not waste your time for books that are noth worth it. Search for reviews and save your time.
            </div>
            <img alt='intro' src={intro} style={{float: "right", height: "350px"}}/>
          </div>
      </QueueAnim>
    </OverPack>
    );
  }

  socialmediaIcons = () => {
    return ([
      <FacebookOutlined key={1} style={{fontSize: "35px", margin: "10px"}}/>,
      <TwitterOutlined key={2} style={{fontSize: "35px",  margin: "10px"}}/>,
      <SkypeOutlined key={3} style={{fontSize: "35px",  margin: "10px"}}/>,
      <InstagramOutlined key={4} style={{fontSize: "35px",  margin: "10px"}}/>
    ]);
  }

  contactUsDiv = () => {
    return (
      <OverPack style={{ overflow: 'hidden' }} playScale={0.1} >
            <QueueAnim style={{backgroundColor: "#ffffff"}}>
                <div key="a" style={{padding: "2%", fontWeight: "bold", fontSize: "x-large"}}>
                    Contact us
                </div>
                <div key="b" style={{paddingLeft: "10%", paddingRight: "10%", display: "flex"}}>
                  <img alt='contact' src={contact} style={{float: "left", height: "350px"}}/>
                  <div style={{float: "right", fontSize: "larger", textAlign: "left", width: "60vw", margin: "5%"}} >
                    If you have any questions or problems with using our platform please contact us.
                    <br />
                    <br />
                    Phone: XXX-XXX-XXX
                    <br />
                    Email: xxx@email.com
                    <br />
                    <br />
                    <this.socialmediaIcons />
                  </div>
              </div>
            </QueueAnim>
          </OverPack>
    );
  }

  render() {
    return (
      <div className="Main" >
          <this.headerDiv />
          <this.introDiv />
          <this.contactUsDiv />
        <div style={{height: "40vh", backgroundColor: "#ffffff"}} />
      </div>
    );
  }
};

export default MainView;
