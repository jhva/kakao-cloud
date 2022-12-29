import React, { useCallback } from "react";
import ToDoListItem from "./ToDoListItem";
import "./ToDoList.scss";
import { List } from "react-virtualized";

const ToDoList = ({ todos, onRemove, onToggle }) => {
  //하나의 항목을 렌더링하기 위한 함수생성

  const rowRender = useCallback(
    ({ index, key, style }) => {
      const todo = todos[index];
      return (
        <ToDoListItem
          todo={todo}
          key={key}
          onRemove={onRemove}
          onToggle={onToggle}
          style={style}
        />
      );
    },
    [onRemove, onToggle, todos]
  );

  return (
    <List
      className="ToDoList"
      width={512}
      height={513}
      rowCount={todos.length}
      rowHeight={57} //항목의 높이
      rowRenderer={rowRender} //행을 만들어주는 함수
      list={todos} //데이터
      style={{ outline: "none" }}
    />
  );
};

export default ToDoList;
