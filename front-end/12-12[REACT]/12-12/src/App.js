import logo from "./logo.svg";
import "./App.css";
import Todos from "./components/Todos";
import Counter from "./components/Counter";
import CounterContainer from "./containers/CounterCombine";
import ToDoContainer from "./containers/ToDoContainer";

function App() {
  return (
    <div>
      <div>
        카운터 컴포넌트
        <CounterContainer />
      </div>

      <div>
        Todos
        <ToDoContainer />
      </div>
    </div>
  );
}

export default App;
