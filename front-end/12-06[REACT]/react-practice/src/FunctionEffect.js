import React, { useState, useEffect } from "react";

const FunctionEffect = () => {
  const [count, setCount] = useState(0);

  useEffect(() => {
    console.log("a마운트 와 업데이트가 끝나면 호출");
    document.title = `you clcicked ${count}times`;
  }, [count]);
  return (
    <div>
      <p>youclicked {count}</p>
      <button onClick={(e) => setCount(count + 1)}>버튼</button>
    </div>
  );
};

export default FunctionEffect;
