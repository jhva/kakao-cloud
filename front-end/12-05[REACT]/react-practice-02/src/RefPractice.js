import React from "react";
import "./Validation.css";
// const RefPractice = () => {
//   return (
//     <div>
//       <h1>Ref</h1>
//     </div>
//   );
// };

class RefPractice extends React.Component {
  //Ref (다른 DOM 객체를 참조할 수 있는 속성) 생성
  input = React.createRef();

  state = {
    password: "",
    clicked: false,
    validated: false,
  };
  handleButtonClick = () => {
    this.setState({
      clicked: true,
      validated: this.state.password === "0000",
    });

    //input 에 참조하는 객체에 focus를 설정
    this.input.focus();
  };

  handleChange = (e) => {
    //handleChage는 input의 입력값을 할려할때
    //자신의 name과 동일한 state를 입력한 값으로 변경
    this.setState({
      [e.target.name]: e.target.value,
    });
  };
  render() {
    return (
      <div>
        <h1>Ref</h1>

        <input
          ref={(ref) => {
            this.input = ref;
          }}
          // ref={this.input}
          onChange={this.handleChange}
          name="password"
          type="password"
          value={this.state.password}
          className={
            this.state.clicked
              ? this.state.validated
                ? "success"
                : "failure"
              : ""
          }
        />
        <button onClick={this.handleButtonClick}>검증하기</button>
      </div>
    );
  }
}
export default RefPractice;
