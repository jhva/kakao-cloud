import React, { Component } from "react";
import propTypes from "prop-types";

// const MyComponent = (props) => {
//   return <div>{props.name}</div>;
// };

// export default MyComponent;

class MyComponent extends Component {
  render() {
    //prop-types
    //클래스형 컴포넌트에는 props 속성이존재
    const { name } = this.props;
    return <div>{name}</div>;
  }
}

export default MyComponent;
