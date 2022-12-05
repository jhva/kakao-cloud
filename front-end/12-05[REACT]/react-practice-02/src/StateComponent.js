import React, { Component, useState } from "react";

// class StateComponent extends Component {
//   constructor(props) {
//     //상위 클래스 의 생성자 호출
//     super(props);
//     this.state = {
//       number: 0,
//     };
//   }
//   render() {
//     return (
//       <div>
//         {this.props.name}
//         <div>{this.state.number}</div>
//         <button
//           onClick={(e) => {
//             this.setState({ number: this.state¡.number + 1 }, () => {
//               console.log("값 변경");
//               console.log(this.state);
//             });
//           }}
//         >
//           숫ㅅ자올리기
//         </button>
//       </div>
//     );
//   }
// }

const StateComponent = () => {
  const [msg, setMsg] = useState("");
  const [color, setColor] = useState("red");
  const onClickMsg = (e) => {
    setMsg("야이시발아");
  };

  const onClickColorChange = (e) => {
    setColor("brown");
  };
  return (
    <div style={{ color }}>
      {msg}
      <div>
        <button onClick={onClickMsg}>클릭메세지</button>
        <button onClick={onClickColorChange}>색상바꾸기</button>
      </div>
    </div>
  );
};

export default StateComponent;
