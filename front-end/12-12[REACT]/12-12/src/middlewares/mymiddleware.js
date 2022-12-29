const mymiddleware = (store) => (next) => (action) => {
  //동작로깅
  console.log(action, "action middlewares");

  //다음 미들웨어나리듀서에게 전달

  const result = next(action);

  console.log(result, "result middlewares");
  console.log(store.getState());
  return result;
};

export default mymiddleware;
