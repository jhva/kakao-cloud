import React, { Component } from "react";

class Iteration extends Component {
  state = {
    names: ["javascript", "고양이"],
    name: "",
  };
  //input에 입력하면 name state의 값을 변경하는 이벤트 처리함수
  handleChange = (e) => {
    this.setState({
      name: e.target.value,
    });
  };
  // name의 값을 names에 추가하는 함수
  //push 대신에 concat을 쓴 이유는 원본은 변경하기때문에
  handleInsert = (e) => {
    this.setState({
      names: this.state.names.concat(this.state.name),
    });
  };
  //데이터 삭제함수
  //index 매개변수로 받아서 삭제

  handleRemove = (index) => {
    const { names } = this.state;
    // this.setState({
    //   names: [names.slice(0, index), names.slice(index + 1, names.length)],
    // });
    this.setState({
      names: names.filter((item, e) => e !== index),
    });
  };

  render() {
    const nameList = this.state.names.map((data, index) => (
      <li
        onDoubleClick={(e) => {
          this.handleRemove(index);
        }}
        key={index}
      >
        {data}
        <button
          onClick={(e) => {
            this.handleRemove(index);
          }}
        >
          삭제
        </button>
      </li>
    ));
    return (
      <div>
        {/* {this.state.missing.value} */}
        <input onChange={this.handleChange} value={this.state.name} />
        <button onClick={this.handleInsert}>추가</button>
        <ul>{nameList}</ul>;
      </div>
    );
  }
}

export default Iteration;
