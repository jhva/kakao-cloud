import React, { Component } from "react";

class ScrollBox extends Component {
  render() {
    const style = {
      border: "1px solid black",
      height: "300px",
      width: "300px",
      overflow: "auto",
      position: "relative",
    };
    const innerStyle = {
      width: "100%",
      height: "650px",
      background: "linear-gradient(white,black)",
    };
    return (
      <>
        <h1>ScrollBox</h1>
        <div style={style} ref={(ref) => (this.box = ref)}>
          <div style={innerStyle} />
        </div>
      </>
    );
  }

  //스크롤박스의 스크롤을 맨 아래로 이동ㅅ기키는 메서드
  scrollToBottom = () => {
    const { scrollHeight, clientHeight } = this.box;
    this.box.scrollTop = scrollHeight - clientHeight;
  };
}

export default ScrollBox;
