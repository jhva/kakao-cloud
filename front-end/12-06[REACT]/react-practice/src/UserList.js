import React, { useEffect } from "react";

const UserList = ({ user, onRemove, onToggle }) => {
  //마운트 될때 , state가 변경될 때 모두 호출
  useEffect(() => {
    console.log("화면에 나타남");
    return () => {
      console.log("화면에 사라짐");
      console.log(user);
    };
  }, [user]);
  return (
    <div>
      <b
        style={{ cursor: "pointer", color: user.active ? "tomato" : "red" }}
        onClick={(e) => {
          onToggle(user.id);
        }}
      >
        {user.usernickname}
      </b>
      <span>({user.email})</span>
      <button
        onClick={(e) => {
          onRemove(user.id);
        }}
      >
        삭제
      </button>

      <button
        onClick={(e) => {
          onToggle(user.id);
        }}
      >
        수정
      </button>
    </div>
  );
};

export default UserList;
