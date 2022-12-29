import React, { Component } from "react";

class EventPractice extends Component {
  state = {
    name: "",
    message: "",
  };
  handleClick = (e) => {
    alert(this.state.message + "" + this.state.name);
    this.setState({
      name: "",
      message: "",
    });
  };
  handleChange = (e) => {
    //이벤트가 발생한 객체는 target
    //e.target.name은 이벤트가 발생한 객체의 name
    this.setState({
      [e.target.name]: e.target.value,
    });
  };
  haddleKeypress = (e) => {
    if (e.key === "Enter") {
      this.handleClick();
    } else {
      alert("아니에요");
    }
  };
  render() {
    return (
      <div>
        <h1>이벤트 연습</h1>
        <input
          value={this.state.message}
          type="text"
          name="message"
          placeholder="메세지 입력하세요"
          onChange={this.handleChange}
          onKeyPress={this.haddleKeypress}
        />
        <input
          value={this.state.name}
          type="text"
          name="name"
          placeholder="이름을 입력하세요"
          onChange={this.handleChange}
        />
        <button onClick={this.handleClick}>
          스테이트 에 저장된 name을 다이얼로그에 출력
        </button>
        <p></p>
      </div>
    );
  }
}

export default EventPractice;
