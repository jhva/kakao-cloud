import React from "react";
import { connect } from "react-redux";
import Counter from "../components/Counter";

import { increase, decrease } from "../redux/count";

const CounterContainer = ({ number, increase, decrease }) => {
  return (
    <Counter number={number} onIncrease={increase} onDecrease={decrease} />
  );
};

const mapStateProps = (state) => ({
  number: state.counter.number,
});

const mapDispatchProps = (dispatch) => ({
  increase: () => {
    dispatch(increase());
  },
  decrease: () => {
    dispatch(decrease());
  },
});

export default connect(mapStateProps, mapDispatchProps)(CounterContainer);
