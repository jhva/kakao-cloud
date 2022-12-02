import React, { Component } from './react';
import './App.css';
import MyComponent from './MyComponent';

class App extends Component {
  render() {
    const msg = "클래스형 컴포넌트 다이새끼야";
    return (<div>{msg}
      <MyComponent /></div>)
  }

}

export default App;
