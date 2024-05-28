import React, { useState } from "react";

const CampingButtons = ({ onDisplayMarkers }) => {
  const [selectedButton, setSelectedButton] = useState("");

  const handleClick = (category) => {
    setSelectedButton(category);
    onDisplayMarkers(category);
  };

  const getButtonStyle = (category) => {
    // 버튼의 기본 스타일
    return `${baseStyle} ${
      selectedButton === category ? selectedStyle : unselectedStyle
    }`;
  };
  const baseStyle = "p-2 m-1 text-white font-bold rounded";

  // 선택된 버튼의 스타일
  const selectedStyle = "bg-blue-500 hover:bg-blue-700";
  // 선택되지 않은 버튼의 스타일
  const unselectedStyle = "bg-gray-500 hover:bg-gray-700";

  return (
    <div>
      <button
        className={getButtonStyle("coffee")}
        onClick={() => handleClick("coffee")}
      >
        일반야영장
      </button>
      <button
        className={getButtonStyle("store")}
        onClick={() => handleClick("store")}
      >
        카라반
      </button>
      <button
        className={getButtonStyle("carpark")}
        onClick={() => handleClick("carpark")}
      >
        글램핑
      </button>
    </div>
  );
};

export default CampingButtons;
