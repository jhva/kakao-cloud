//타입생성 매개변수를 받아서 증가 , 감소 \

const SET_DIFF = "counter/SET_DIFF";
const INCREASE = "counter/INCREASE";
const DECREASE = "counter/DECREASE";

//액션 생성함수 만들기

export const setDIFF = (diff) => ({ type: SET_DIFF, diff });
export const decrease = () => ({ type: DECREASE });
export const increase = () => ({ type: INCREASE });

//초기 상태 선언
const initialize = {
  number: 0,
  diff: 1,
};

//리듀서 선언

export default function counter(state = initialize, action) {
  switch (action.type) {
    case SET_DIFF:
      return { ...state, diff: action.diff };
      break;
    case INCREASE:
      return { ...state, number: state.number + state.diff };
      break;
    case DECREASE:
      return { ...state, number: state.number - state.diff };
      break;
    default:
      return state;
      break;
  }
}
