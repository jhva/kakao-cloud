import React, { useState } from "react";

const CreateUser = ({ usernickname, email, onChange, onCreate }) => {
  return (
    <div>
      <input
        onChange={(e) => {
          onChange(e);
        }}
        value={usernickname}
        name="usernickname"
      />
      <input
        onChange={(e) => {
          onChange(e);
        }}
        value={email}
        name="email"
      />
      <button
        onClick={(e) => {
          onCreate(e);
        }}
      >
        추가
      </button>
    </div>
  );
};

export default CreateUser;
