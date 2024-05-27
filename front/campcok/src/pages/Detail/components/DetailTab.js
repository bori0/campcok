import React, { useState } from "react";

import Weather from "./Weather";
import TourInfo from "../../../api/TourMap";
import DetailInfo from "./DetailInfo";

import WeatherApp from "../../../api/WeatherApp";

const TabContent = ({ id, activeTab, children }) => {
  return activeTab === id ? <div>{children}</div> : null;
};

const DetailTab = ({ mapX, mapY, gocamping }) => {
  const [activeTab, setActiveTab] = useState("detail");

  const handleTabClick = (tabId) => {
    setActiveTab(tabId);
  };

  const [selectedTab, setSelectedTab] = useState("regional");
  return (
    <div className="container mx-auto p-4">
      <div className="flex border-b">
        {/* <button
          className={`flex-1 py-2 text-center cursor-pointer ${
            activeTab === "map"
              ? "text-blue-500 border-b-2 border-blue-500"
              : "text-gray-500"
          }`}
          onClick={() => handleTabClick("map")}
        >
          지도
        </button> */}
        <button
          className={`flex-1 py-2 text-center cursor-pointer ${
            activeTab === "detail"
              ? "text-blue-500 border-b-2 border-blue-500"
              : "text-gray-500"
          }`}
          onClick={() => handleTabClick("detail")}
        >
          상세정보
        </button>
        <button
          className={`flex-1 py-2 text-center cursor-pointer ${
            activeTab === "weather"
              ? "text-blue-500 border-b-2 border-blue-500"
              : "text-gray-500"
          }`}
          onClick={() => handleTabClick("weather")}
        >
          날씨
        </button>

        <button
          className={`flex-1 py-2 text-center cursor-pointer ${
            activeTab === "tour"
              ? "text-blue-500 border-b-2 border-blue-500"
              : "text-gray-500"
          }`}
          onClick={() => handleTabClick("tour")}
        >
          관광지
        </button>
      </div>

      {/* <TabContent id="map" activeTab={activeTab}>
        <WeatherApp />
      </TabContent> */}
      <TabContent id="detail" activeTab={activeTab}>
        <DetailInfo gocamping={gocamping} />
      </TabContent>
      <TabContent id="weather" activeTab={activeTab}>
        <div className="flex justify-center  mt-4">
          <button
            className={`px-4 py-2 mx-2 rounded ${
              selectedTab === "national"
                ? "bg-blue-500 text-white"
                : "bg-gray-200 text-gray-800"
            }`}
            onClick={() => setSelectedTab("national")}
          >
            전국 날씨
          </button>
          <button
            className={`px-4 py-2 mx-2 rounded ${
              selectedTab === "regional"
                ? "bg-blue-500 text-white"
                : "bg-gray-200 text-gray-800"
            }`}
            onClick={() => setSelectedTab("regional")}
          >
            지역 날씨
          </button>
        </div>
        {selectedTab === "regional" ? (
          <Weather addr={gocamping.addr1} />
        ) : (
          <WeatherApp />
        )}
      </TabContent>
      <TabContent id="tour" activeTab={activeTab}>
        <TourInfo gocamping={gocamping} />
      </TabContent>
    </div>
  );
};

export default DetailTab;
