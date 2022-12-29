import { useCallback, useRef, useState } from "react";
import immer from "immer";
import ColorBox from "./ColorBox";

function App() {
  const nextId = useRef();
  const [form, setForm] = useState({ name: "", username: "" });
  const [data, setData] = useState({ array: "", uselessValue: null });

  const onChange = useCallback((e) => {
    // setForm(immer((draft) => (draft[e.target.name] = e.target.value)));
    // setForm({
    //   ...form,
    //   [e.target.name]: e.target.value,
    // });
  }, []);

  //입력받은 데이터를 등록하는 함수
  //form 에서 submit 이벤트가 발생할 때 호출

  const onSubmit = useCallback(
    (e) => {
      e.preventDefault();

      const info = {
        id: nextId.current,
        name: form.name,
        username: form.username,
      };
      // setData(
      //   immer((draft) => {
      //     draft.array.push(info);
      //   })
      // );
      // setData({ ...data, array: data.array.concat(info) });
      setForm({ name: "", username: "" });
      nextId.current += 1;
    },
    [data, form.name, form.username]
  );

  const onRemove = useCallback(
    (id) => {
      // setData({
      //   ...data,
      //   array: data.array.filter((info) => info.id !== id),
      // });
    },
    [data]
  );

  return (
    <div>
      <ColorBox />
      {/* <form onSubmit={onSubmit}>
        <input
          name="username"
          placeholder="아이디를 입력하세요"
          value={form.username}
          onChange={(e) => {
            onChange(e);
          }}
        />
        <input
          name="name"
          placeholder="dlfmadmf 입력하세요"
          value={form.name}
          onChange={(e) => {
            onChange(e);
          }}
        />
        <button type="submit">등록</button>
      </form>
      <div>
        <ul></ul>
      </div> */}
    </div>
  );
}

export default App;
