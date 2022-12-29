// import React from "react";

// const TodoItem = ({ todo, onToggle, onRemove }) => {
//   return (
//     <div>
//       <input
//         type="checkbox"
//         onClick={() => {
//           onToggle(todo.id);
//         }}
//         checked={todo.done}
//         readOnly={true}
//       />
//       <span style={{ textDecoration: todo.done ? "line-through" : "none" }}>
//         {todo.text}
//       </span>
//       <button
//         onClick={() => {
//           onRemove(todo.id);
//         }}
//       >
//         삭제
//       </button>
//     </div>
//   );
// };

// const ToDos = ({
//   input,
//   todo,
//   onChangeInput,
//   onInsert,
//   onToggle,
//   onRemove,
// }) => {
//   const onSubmit = (e) => {
//     e.preventDefault();
//     onInsert(input);
//     onChangeInput("");
//   };
//   const onChange = (e) => {
//     onChangeInput(e.target.value);
//   };
//   return (
//     <div>
//       <form onSubmit={onSubmit}>
//         <input
//           value={input}
//           onChange={(e) => {
//             onChange(e);
//           }}
//         />
//         <button type="submit">등록</button>
//       </form>
//       {todo?.map((todo) => (
//         <TodoItem
//           todo={todo}
//           key={todo.id}
//           onToggle={onToggle}
//           onRemove={onRemove}
//         />
//       ))}
//     </div>
//   );
// };

// export default ToDos;
import React from "react";

const ToDos = ({
  input,
  todos,
  onChangeInput,
  onInsert,
  onToggle,
  onRemove,
}) => {
  const onSubmit = (e) => {
    e.preventDefault();
    onInsert(input);
    onChangeInput("");
  };
  const onChange = (e) => onChangeInput(e.target.value);
  return (
    <div>
      <form onSubmit={onSubmit}>
        {" "}
        {/* 중괄호는 자바스크립트 문법 사용할때 사용 */}
        <input value={input} onChange={onChange} />
        <button type="submit">등록</button>
      </form>
      <div>
        {todos.map((todo) => (
          <ToDoItem
            todo={todo}
            key={todo.id}
            onToggle={onToggle}
            onRemove={onRemove}
          />
        ))}
      </div>
    </div>
  );
};

export const ToDoItem = ({ todo, onToggle, onRemove }) => {
  return (
    <div>
      <input
        type="checkbox"
        onClick={() => onToggle(todo.id)}
        checked={todo.done}
        readOnly={true}
      />
      <span style={{ textDecoration: todo.done ? "line-through" : "none" }}>
        {todo.text}
      </span>
      <button onClick={() => onRemove(todo.id)}>삭제</button>
    </div>
  );
};

//여러개의 ToDoItem을 출력할 컴포넌트

export default ToDos;
