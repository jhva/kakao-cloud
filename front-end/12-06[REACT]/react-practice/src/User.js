import React, { useEffect } from "react";
import UserList from "./UserList";
const User = ({ users, onRemove, onToggle }) => {
  return (
    <div>
      <h1>User</h1>
      <h2>UseList</h2>

      {users.map((user) => (
        <UserList
          onToggle={onToggle}
          key={user.id}
          user={user}
          onRemove={onRemove}
        />
      ))}
    </div>
  );
};

export default User;
