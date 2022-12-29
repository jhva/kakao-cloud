// import React from "react";
// import { connect } from "react-redux";
// import ToDos from "../components/Todos";

// import { changeInput, insert, toggle } from "../redux/todo";

// const TodoContainer = ({ todos, input, changeInput, toggle }) => {
//   return (
//     <ToDos
//       todos={todos}
//       input={input}
//       onChangeInput={changeInput}
//       onInsert={insert}
//       //   onRemove={remove}
//       onToggle={toggle}
//     />
//   );
// };

// export default connect(
//   ({ todos }) => ({
//     input: todos.input,
//     todos: todos.todos,
//   }),
//   { changeInput, insert, toggle }
// )(TodoContainer);

import React from "react";
import { connect } from "react-redux";
import { changeInput, insert, toggle, remove } from "../redux/todo";
import ToDos from "../components/Todos";

const ToDoContainer = ({
  input,
  todos,
  changeInput,
  insert,
  toggle,
  remove,
}) => {
  return (
    <ToDos
      input={input}
      todos={todos}
      onChangeIput={changeInput}
      onInsert={insert}
      onToggle={toggle}
      onRemove={remove}
    />
  );
};

export default connect(
  ({ todos }) => ({
    input: todos.input,
    todos: todos.todos,
  }),
  { changeInput, insert, toggle, remove }
)(ToDoContainer);
