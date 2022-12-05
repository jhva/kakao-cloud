import React, { useState } from "react";

const FunctionComponent = () => {
  // const [message, setMessage] = useState("");

  const [form, setForm] = useState({
    username: "",
    message: "",
  });

  const { username, message } = form;
  const handleChage = (e) => {
    //form 을 복제해서 e.target.name 에 해당하는 속성만
    // e.target.value로 수정
    // react에서는 state를 수정할때 복제해서 수정해야함
    //하나의 항목으로 만들어진 데이터는 바로 수정하면 되지만
    //여러 항목으로 구성된 객체 나 배열은 복제해서 수정한다.
    const newForm = {
      ...form,
      [e.target.name]: e.target.value,
    };
    setForm(newForm);
  };

  const onClick = (e) => {
    alert(username + ":" + message);
    setForm({
      username: "",
      message: "",
    });
  };
  const onKeyPress = (e) => {
    if (e.key == "Enter") {
      // onClick();
    }
  };
  return (
    <div>
      <h1>함수형 컴포넌트</h1>
      <div>
        <input
          placeholder="이름"
          value={username}
          type="text"
          name="username"
          onChange={handleChage}
          onKeyPress={onKeyPress}
        />
        <input
          placeholder="메세지"
          value={message}
          type="text"
          name="message"
          onChange={(e) => {
            handleChage(e);
          }}
          onKeyPress={onKeyPress}
        />
      </div>
      <button onClick={(e) => onClick(e)}>확인</button>
    </div>
  );
};

export default FunctionComponent;
