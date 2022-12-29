//액션타입 정의
const INCREASE = "counter/INCREASE";
const DECREASE = "counter/DECREASE";

//액션 생성 함수 정의
export const increase = () => ({ type: INCREASE });
export const decrease = () => ({ type: DECREASE });
//초기 상태를 정의

const initialize = {
  number: 0,
};

const counter = (state = initialize, action) => {
  switch (action.type) {
    case INCREASE:
      return { number: state.number + 1 };
    case DECREASE:
      return { number: state.number - 1 };
      break;
    default:
      return state;
      break;
  }
};

export default counter;

//리듀서 함수를 생성
