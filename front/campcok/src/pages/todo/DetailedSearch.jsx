import React, { useState } from "react";
import { ReactComponent as ThemeIcon_1 } from "../../img/beach.svg";
import { ReactComponent as ThemeIcon_2 } from "../../img/island.svg";
import { ReactComponent as ThemeIcon_3 } from "../../img/mountain.svg";
import { ReactComponent as ThemeIcon_4 } from "../../img/forest.svg";
import { ReactComponent as ThemeIcon_5 } from "../../img/valley.svg";
import { ReactComponent as ThemeIcon_6 } from "../../img/river.svg";
import { ReactComponent as ThemeIcon_7 } from "../../img/lake.svg";
import { ReactComponent as ThemeIcon_8 } from "../../img/city.svg";
import DetailModal from "./DetailModal";

const DetailedSearch = ({
  fetchBeachData,
  fetchIslandData,
  fetchMountainData,
  fetchForestData,
  fetchValleyData,
  fetchRiverData,
  fetchLakeData,
  fetchDowntownData,
}) => {
  return (
    <>
      <div id="DetailedSearch" className="grid grid-cols-4">
        <a href="#" onClick={fetchBeachData} className="m-auto ">
          <ThemeIcon_1 className="ml-1" />
          <p>해변</p>
        </a>
        <a href="#" onClick={fetchIslandData} className="m-auto">
          <ThemeIcon_2 className="ml-1" />
          <p>섬</p>
        </a>
        <a href="#" onClick={fetchMountainData} className="m-auto">
          <ThemeIcon_3 className="ml-1" />
          <p>산</p>
        </a>
        <a href="#" onClick={fetchForestData} className="m-auto">
          <ThemeIcon_4 className="ml-1" />
          <p>숲</p>
        </a>
        <a href="#" onClick={fetchValleyData} className="m-auto">
          <ThemeIcon_5 className="ml-1" />
          <p>계곡</p>
        </a>
        <a href="#" onClick={fetchRiverData} className="m-auto">
          <ThemeIcon_6 className="ml-1" />
          <p>강</p>
        </a>
        <a href="#" onClick={fetchLakeData} className="m-auto">
          <ThemeIcon_7 className="ml-1" />
          <p>호수</p>
        </a>
        <a href="#" onClick={fetchDowntownData} className="m-auto">
          <ThemeIcon_8 className="ml-1" />
          <p>도심</p>
        </a>
      </div>
    </>
  );
};

export default DetailedSearch;
