import React, { useCallback } from "react";
import {
  MdCheckBox,
  MdCheckBoxOutlineBlank,
  MdRemoveCircleOutline,
} from "react-icons/md";
import cn from "classnames";
import "./ToDoListItem.scss";

const ToDoListItem = ({ todo, onRemove, onToggle, style }) => {
  const { text, checked, id } = todo;

  const onDelete = useCallback(
    (e) => {
      onRemove(id);
    },
    [todo, onRemove]
  );

  return (
    <div className="ToDoListItem" style={style}>
      <div
        onClick={(e) => {
          onToggle(id);
        }}
        className={cn("checkbox", { checked })}
      >
        {checked ? <MdCheckBox /> : <MdCheckBoxOutlineBlank />}
        <div className="text">{text}</div>
      </div>
      <div className="remove">
        <MdRemoveCircleOutline
          onClick={(e) => {
            onDelete(e);
          }}
        />
      </div>
    </div>
  );
};

export default React.memo(ToDoListItem);
