import React, { useState, useCallback, useRef } from "react";
import { MdAdd } from "react-icons/md";

import "./ToDoInsert.scss";

const ToDoInsert = ({ onInsert }) => {
  const [value, setValue] = useState("");
  //입력내용 변경될때

  const onChange = useCallback((e) => {
    setValue(e.target.value);
  }, []);

  //form submit event발생할때 호출되는 함수
  //form 안에서 submit버튼을 눌러도 submit이벤트가 발생하지만
  //form 안에서 enter를 눌러도 submit이벤트는 발생한다.

  const onSubmit = useCallback(
    (e) => {
      onInsert(value);
      setValue("");
      e.preventDefault();
    },
    [onInsert, value]
  );
  return (
    <form onSubmit={onSubmit} className="ToDoInsert">
      <input
        value={value}
        onChange={(e) => {
          onChange(e);
        }}
        type="text"
        placeholder="할 일을 입력하세요"
      />
      <button type="submit">
        <MdAdd />
      </button>
    </form>
  );
};

export default ToDoInsert;
