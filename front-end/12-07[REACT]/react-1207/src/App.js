import logo from "./logo.svg";
import "./App.css";
import Component from "./Component";
import axios from "axios";

function App() {
  const obj = {
    name: "김정훈",
    age: "123",
  };
  return (
    <div className="App">
      <button
        onClick={(e) => {
          // fetch("https://jsonplaceholder.typicode.com/users")
          //   .then((res) => {
          //     console.log(res);
          //     res.json();
          //   })
          //   .catch((err) => console.log(err, "err"));
          // let req = new XMLHttpRequest();
          // req.open("GET", "https://jsonplaceholder.typicode.com/users");
          // //post방식일때는 send에 파라미터 대입해야함
          // req.send("");
          // req.addEventListener("load", () => {
          //   let data = JSON.parse(req.responseText);
          //   console.log(data);
          // });
          // req.addEventListener("error", (err) => {
          //   console.log(err, "err");
          // });
          axios
            .get("https://jsonplaceholder.typicode.com/users")
            .then((res) => {
              console.log(res.data);
            })
            .catch((err) => {
              console.log(err, "err");
            })
            .finally(() => {
              console.log("무조건실행");
            });
        }}
      >
        다운로드{" "}
      </button>
    </div>
  );
}

export default App;
