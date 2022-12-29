import "./App.css";
import ErrorBoundary from "./ErrorBoundary";
import Iteration from "./Iteration";
import React, { Component, useState, useRef, useMemo } from "react";
import InputStream from "./InputStream";
import ClassEffect from "./ClassEffect";
import FunctionEffect from "./FunctionEffect";
import UserList from "./UserList";
import User from "./User";
import CreateUser from "./CreateUser";

class ClassCate extends Component {
  //생성자를 만들지 않고 이렇게 state를 초기화해도 된다
  state = {
    count: 0,
  };

  constructor(props) {
    super(props);
    this.state = {
      count: 0,
    };
  }
  render() {
    return (
      <div>
        클릭을 {this.state.count}q번 수행
        <button
          onClick={(e) => {
            this.setState({
              count: this.state.count + 1,
            });
          }}
        >
          버튼
        </button>
      </div>
    );
  }
}

const FunctionState = () => {
  const [count, setCount] = useState(0);
  return (
    <div>
      클릭을 {count}q번 수행
      <button
        onClick={(e) => {
          setCount(count + 1);
        }}
      >
        버튼
      </button>
    </div>
  );
};

function App() {
  const [users, setUsers] = useState([
    {
      id: 1,
      usernickname: "김정훈",
      email: "junghun8158@naer.com",
      active: false,
    },
    {
      id: 2,
      usernickname: "김흥국",
      email: "kimheoungkook@naer.com",
      active: false,
    },
    { id: 3, usernickname: "김나나", email: "kimnana@naer.com", active: true },
  ]);

  const nextId = useRef(4);
  //변수 생성

  const [inputs, setInputs] = useState({
    email: "",
    usernickname: "",
  });
  const { email, usernickname } = inputs;

  const onCreate = (e) => {
    const user = {
      id: nextId,
      usernickname,
      email,
    };
    setUsers([...users, user]);

    setInputs({
      email: "",
      usernickname: "",
    });
    nextId.current += 1;
  };

  const onChange = (e) => {
    setInputs({
      ...inputs,
      [e.target.name]: e.target.value,
    });
  };
  const onRemove = (id) => {
    //ㅕuser state 에서 id가 id인 데이터 삭제
    // id가 일치하지 않는 데이터만 삭제
    //실제로는 id가 일치하지 않는 데이터만 가지고 배열을 만들어서 수정한다.
    setUsers(users.filter((user) => user.id !== id));
  };

  const onToggle = (id) => {
    setUsers(
      users.map((user) =>
        user.id === id ? { ...user, active: !user.active } : user
      )
    );
  };

  const countActiveUser = (user) => {
    console.log("사용자 개수세기 ");
    return user.filter((user) => user.active).length;
  };
  const count = useMemo(() => {
    countActiveUser(users);
  }, [users]);

  return (
    <div>
      <ErrorBoundary>
        <Iteration />
      </ErrorBoundary>
      {/* <FunctionState /> */}
      {/* <ClassCate /> */}
      <InputStream />
      {/* <ClassEffect /> */}
      {/* <FunctionEffect /> */}
      <User onToggle={onToggle} users={users} onRemove={onRemove} />
      <CreateUser
        email={email}
        usernickname={usernickname}
        onCreate={onCreate}
        onChange={onChange}
      />
      <p>활성화된유저 {count}</p>
    </div>
  );
}

export default App;
