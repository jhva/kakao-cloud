import "./App.css";
import ToDoInsert from "./components/ToDoInsert";
import ToDoList from "./components/ToDoList";
import ToDoListItem from "./components/ToDoListItem";
import ToDoTemplate from "./components/ToDoTemplate";
import React, { useState, useRef, useCallback, useReducer } from "react";

const createBulkTodos = () => {
  const arr = [];
  for (let i = 0; i <= 1000; i++) {
    arr.push({ id: i, text: `할일${i}`, checked: false });
  }
  return arr;
};

const todoReducer = (state, action) => {
  //분기
  switch (action.type) {
    case "INSERT":
      return state.concat(action.todo);
    case "REMOVE":
      return state.filter((todo) => todo.id !== action.id);
    case "TOGGLE":
      return state.map((todo) =>
        todo.id === action.id ? { ...todo, checked: !todo.checked } : todo
      );
    default:
      return state;
      break;
  }
};

function App() {
  const [todos, dispatch] = useReducer(todoReducer, undefined, createBulkTodos);
  // const [todos, setTodos] = useState(createBulkTodos);

  //idㅡㄹ ㅡㄹ 위한 변수 생성
  const nextId = useRef(2001);

  //삽입을 처리하기 위한 함수

  const onInsert = useCallback((text) => {
    const todo = { id: nextId, text, checked: false };

    // setTodos((prev) => {
    //   [...prev, todo];
    // });
    // setTodos((prev) => prev.concat(todo));
    dispatch({ type: "INSERT", todo });
    // setTodos(todos.concat(todo));
    nextId.current += 1;
  }, []);

  const onRemove = useCallback((id) => {
    // setTodos((todos) => todos.filter((todo) => todo.id !== id));
    // setTodos((prev) => {
    // });

    dispatch({ type: "REMOVE", id });
  }, []);

  const onToggle = useCallback((id) => {
    dispatch({ type: "TOGGLE", id });
    // setTodos(
    //   todos.map((todo) =>
    //     todo.id === id ? { ...todo, checked: !todo.checked } : todo
    //   )
    // );
  }, []);

  return (
    <>
      <ToDoTemplate>
        <ToDoInsert onInsert={onInsert} />
        {/* <ToDoListItem /> */}
        <ToDoList onToggle={onToggle} onRemove={onRemove} todos={todos} />
      </ToDoTemplate>
    </>
  );
}

// let a = 100;
// let b = a;
// let obj = { num: 100 };
// let copyojb = obj;
// // console.log(a == b);
// console.log(obj === copyojb);
// // copyojb.num = 10;
// // console.log(copyojb.num);
// // let copydata = Object.assign({}, obj);
// // copydata.num = 300;

// obj = {
//   num: 100,
//   ar: ["a", "b"],
// };
// let copydata = Object.assign({}, obj);
// // copydata = { ...obj };
// // console.log(copydata);
// // copydata.ar[0] = "오잉";
// // console.log(copydata);

// // let copydata = { ...obj };
// // copydata.num = 300;
// // console.log(copydata);
// // console.log(obj);
// // console.log(obj.num);

// // console.log(a === b);

// //깊은복사 (deep copy) 재귀적으로 복제 하는 것
// //데이터만 복제한다싶으면 깊은복제
// //JSON문자열로 변환후 JSON파싱을 수행

// let deepCopy = JSON.parse(JSON.stringify(obj));
// copydata.ar[0] = "오잉";

// console.log(deepCopy);
// console.log(obj);

export default App;
