import React, { useEffect, useState } from "react";
import axios from "axios";
import { getList, getList2 } from "../../../api/CamcokSearchApi";
import { Link } from "react-router-dom";
import BannerImg_1 from "../../../img/댕댕이.png";
import BannerImg_2 from "../../../img/온라인예약.png";

const DogLike = () => {
  const [serverDog, setServerDog] = useState([]); // 서버에서 가져온 데이터
  const [displayData, setDisplayData] = useState([
    {
      tno: 2,
      facltNm: "파크킹",
      firstImageUrl:
        "https://gocamping.or.kr/upload/camp/100000/thumb/thumb_720_8961mOX5pkL8PIG296E1RU3S.jpg",
    },
    {
      tno: 5,
      facltNm: "우니메이카 태안점",
      firstImageUrl:
        "https://gocamping.or.kr/upload/camp/100003/thumb/thumb_720_6517S9GUDxExcoVxf6fWGCxC.jpg",
    },
    {
      tno: 6,
      facltNm: "뇌운계곡글램핑",
      firstImageUrl:
        "https://gocamping.or.kr/upload/camp/100004/thumb/thumb_720_0924x1f5rjPF1V1zMXPvW0Rh.jpg",
    },
  ]); // 초기 로딩 상태 데이터
  const [displayData1, setDisplayData1] = useState([
    {
      tno: 1246,
      facltNm: "가평사계절캠핑장",
      firstImageUrl:
        "https://gocamping.or.kr/upload/camp/102/thumb/thumb_720_2180uMBOFgzbjXUHEvvLuoXe.jpg",
    },
    {
      tno: 1252,
      facltNm: "만산동 국민여가캠핑장",
      firstImageUrl:
        "https://gocamping.or.kr/upload/camp/1029/thumb/thumb_720_127089C6nimsgCUyS7fg8l7e.jpg",
    },
    {
      tno: 1257,
      facltNm: "맑은개울 캠핑장",
      firstImageUrl:
        "https://gocamping.or.kr/upload/camp/1034/thumb/thumb_720_1522UZ1qNFX1G9tM2MVPJ14O.jpg",
    },
  ]); // 초기 로딩 상태 데이터
  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(true);

  useEffect(() => {
    //localStorage.removeItem("serverDog");
    const storedData = JSON.parse(localStorage.getItem("serverDog"));
    console.log("저장된 ", storedData);
    if (Array.isArray(storedData)) {
      setServerDog(storedData);
      setLoading(false);
    } else {
      fetchData();
    }
  }, []);

  const fetchData = () => {
    setLoading(true);
    getList(1, 200)
      .then((response) => {
        console.log("Received data: ", response);
        // 여기서 data를 response.dtoList로 변경하여 배열을 직접 사용합니다.
        if (response && Array.isArray(response.dtoList)) {
          setServerDog(response.dtoList);
          localStorage.setItem("serverDog", JSON.stringify(response.dtoList));
        } else {
          console.error("Data is not an array or invalid response:", response);
        }
      })
      .catch((err) => {
        console.error("Error fetching data: ", err);
        setError(err);
      })
      .finally(() => {
        setLoading(false);
      });
  };
  // useEffect(() => {
  //   const fetchData = async () => {
  //     try {
  //       const response = await getList(1, 200);
  //       console.log("Response:", response);
  //       if (response && response.dtoList && Array.isArray(response.dtoList)) {
  //         setServerDog(response.dtoList);
  //       } else {
  //         console.error("Data is not an array or invalid response:", response);
  //         setServerDog([]);
  //       }
  //     } catch (e) {
  //       setError(e);
  //     }
  //   };
  //   fetchData();
  // }, []);
  const dog = "가능";
  const online = "온라인실시간예약";
  const filterDogData = serverDog.filter((data) => data.animalCmgCl === dog);
  const filterOnlineData = serverDog.filter((data) => data.resveCl === online);
  useEffect(() => {
    if (filterDogData.length > 0) {
      const interval = setInterval(() => {
        // 먼저 데이터를 섞습니다.
        const shuffledData = [...filterDogData].sort(() => 0.5 - Math.random());
        const shuffledData1 = [...filterOnlineData].sort(
          () => 0.5 - Math.random()
        );
        // firstImageUrl이 존재하는 항목만 필터링합니다.
        const filteredData = shuffledData.filter((item) => item.firstImageUrl);
        const filteredData1 = shuffledData1.filter(
          (item) => item.firstImageUrl
        );
        // 필터링된 데이터의 상위 3개 항목만 displayData로 설정합니다.
        setDisplayData(filteredData.slice(0, 3));
        setDisplayData1(filteredData1.slice(0, 3));
      }, 3000);

      return () => clearInterval(interval);
    }
  }, [serverDog]);

  if (error) return <div>Error: {error.message}</div>;

  return (
    <>
      <div className="container mx-auto p-4">
        <div className="flex justify-center items-center">
          <img
            src={BannerImg_1}
            className="h-40 w-80 mt-10 mb-5 bg-[#FFFFFF]"
            alt="Banner 4"
          />
        </div>
        <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
          {displayData.map((campground) => (
            <div
              key={campground.tno}
              className="bg-green-50  p-4 rounded shadow-md flex flex-col"
            >
              <h6 className="text-xl font-semibold h-16 overflow-hidden ">
                {campground.facltNm}
              </h6>
              <Link to={`/read/${campground.tno}`} className="w-full mt-auto">
                <img
                  src={campground.firstImageUrl}
                  className="w-full h-48 object-cover"
                  alt={campground.facltNm}
                />
              </Link>
            </div>
          ))}
        </div>
      </div>
      <div className="container mx-auto p-4">
        <div className="flex justify-center items-center">
          <img
            src={BannerImg_2}
            className="h-40 w-80 mt-10 mb-5 bg-[#FFFFFF]"
            alt="Banner 4"
          />
        </div>

        <div className="grid grid-cols-1 md:grid-cols-3 gap-4">
          {displayData1.map((campground) => (
            <div
              key={campground.tno}
              className="bg-green-50 p-4 rounded shadow-md flex flex-col"
            >
              <h6 className="text-xl font-semibold h-16 overflow-hidden ">
                {campground.facltNm}
              </h6>
              <Link to={`/read/${campground.tno}`} className="w-full mt-auto">
                <img
                  src={campground.firstImageUrl}
                  className="w-full h-48 object-cover"
                  alt={campground.facltNm}
                />
              </Link>
            </div>
          ))}
        </div>
      </div>
    </>
  );
};

export default DogLike;
