import React from "react";
import ColorContext from "./context/color";

const ColorBox = () => {
  return (
    <ColorContext.Consumer>
      {(value) => (
        <div style={{ width: "64px", background: value.color }}>abc</div>
      )}
    </ColorContext.Consumer>
  );
};

export default ColorBox;
