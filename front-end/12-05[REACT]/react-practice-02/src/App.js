import "./App.css";
import EventPractice from "./EventPractice";
import FunctionComponent from "./FunctionComponent";
import MyComponent from "./MyComponent";
import RefPractice from "./RefPractice";
import ScrollBox from "./ScrollBox";
import StateComponent from "./StateComponent";

function App() {
  return (
    <>
      <MyComponent name="adam" />
      <StateComponent />
      <EventPractice />
      <FunctionComponent />
      <RefPractice />
      <ScrollBox
        ref={(ref) => {
          this.scrollBox = ref;
        }}
      />
      <button
        onClick={(e) => {
          this.scrollBox.scrollToBottom();
        }}
      >
        맨 아래로
      </button>
    </>
  );
}

export default App;
