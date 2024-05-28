import React, { useState } from "react";
import "../App.css";
import data from "../data";
import { Tabs, Tab, Nav } from "react-bootstrap";
import DetailDesc from "./Detail/components/DetailDesc";
import BannerSlider from "./Detail/components/BannerSlider";
import DetailTab from "./Detail/components/DetailTab";
import BasicLayout from "../layouts/BasicLayout";

const DetailPage = () => {
  let [campInfo, setCampInfo] = useState(data[0]);
  let [key, setKey] = useState("home");
  let [tab, setTab] = useState(0);

  return (
    <BasicLayout>
      <div className="detailContainer">
        <BannerSlider />
        <DetailDesc />
        <DetailTab />
      </div>
    </BasicLayout>
  );
};

export default DetailPage;