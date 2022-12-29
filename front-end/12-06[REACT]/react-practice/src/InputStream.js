import React, { useRef, useState } from "react";

const InputStream = () => {
  //2개의 속성을 가진 state생성
  const [inputs, setInputs] = useState({
    name: "",
    nickname: "",
  });

  //state를 편리하게 사용하기 위해서 비구조화할당
  const { name, nickname } = inputs;

  //react에서 다른 컴포넌트 나 DOM 을 참조할 수 있는 변수를 생성
  const nameInput = useRef();
  const onChange = (e) => {
    setInputs({
      [e.target.value]: e.target.value,
    });
  };
  const onReset = (e) => {
    setInputs({
      name: "",
      nickname: "",
    });
    nameInput.current.focus();
    //이름 입력란으로 포커스를 옮길때
  };
  return (
    <div>
      <input
        onChange={(e) => {
          onChange(e);
        }}
        ref={nameInput}
        name="name"
        value={name}
      />
      <input
        onChange={(e) => {
          onChange(e);
        }}
        name=" nickname"
        value={nickname}
      />
      <button
        onClick={(e) => {
          onReset(e);
        }}
      >
        초기화
      </button>
    </div>
  );
};
export default InputStream;
