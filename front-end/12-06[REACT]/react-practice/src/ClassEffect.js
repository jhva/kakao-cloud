import React from "react";

class ClassEffect extends React.Component {
  constructor(props) {
    super(props);
    console.log("생성자 - 가장 먼저 호출되는 메서드");

    this.state = { count: 0 };
  }

  componentDidMount() {
    console.log("마운트 된 후 호출되는 메서드");
    document.title = `you clicked ${this.state.count}time`;
  }
  // 컴포넌트가 update된 후 호출되는 메서드
  componentDidUpdate() {
    console.log("업데이트 된 후 호출되는 메서드");
    document.title = `you clicked ${this.state.count}time`;
  }

  render() {
    return (
      <div>
        <p>youclicked {this.state.count}</p>
        <button onClick={(e) => this.setState({ count: this.state.count + 1 })}>
          버튼
        </button>
      </div>
    );
  }
}

export default ClassEffect;
