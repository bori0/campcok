import React, { useEffect, useState } from "react";
import { useDispatch, useSelector } from "react-redux";
import {
  fetchWeatherData,
  setSelectedCity,
  setSelectedDate,
} from "../../pages/weatherSlice";
import TestLogo from "../../img/logo.png";
import LocationIcon from "../../img/loca.png";
import { Link } from "react-router-dom";
import sunnydown from "../../img/sunnydown.svg";
import foggydown from "../../img/foggydown.svg";
import cloudydown from "../../img/cloudydown.svg";
import rainydown from "../../img/rainydown.svg";
import snowydown from "../../img/snowydown.svg";

const ReadOpenWeatherComponentOneDay = () => {
  const dispatch = useDispatch();
  const { data, selectedCity, selectedDate, loading, error } = useSelector(
    (state) => state.weather
  );
  const [showLocationButtons, setShowLocationButtons] = useState(false);

  const ICONS = {
    "01d": sunnydown,
    "01n": sunnydown,
    "02d": cloudydown,
    "02n": cloudydown,
    "03d": cloudydown,
    "03n": cloudydown,
    "04d": cloudydown,
    "04n": cloudydown,
    "09d": rainydown,
    "09n": rainydown,
    "10d": rainydown,
    "10n": rainydown,
    "11d": rainydown,
    "11n": rainydown,
    "13d": snowydown,
    "13n": snowydown,
    "50d": foggydown,
    "50n": foggydown,
  };

  useEffect(() => {
    dispatch(fetchWeatherData()).then((response) => {
      console.log("API 응답 데이터:", response.payload);
    });
  }, [dispatch]);

  const handleCityClick = (cityName) => {
    dispatch(setSelectedCity(cityName));
  };

  const toggleLocationButtonContainer = () => {
    setShowLocationButtons(!showLocationButtons);
  };

  const handleDateChange = (e) => {
    dispatch(setSelectedDate(e.target.value));
  };

  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error}</div>;

  const cityNames = Array.from(new Set(data.map((city) => city.urlKRName)));
  const filteredData = data.filter(
    (item) => item.date === selectedDate && item.urlKRName === selectedCity
  );

  return (
    // 전체 박스 (헤더 위에 고정)
    <div className="sticky top-0 z-50">
      <div className="bg-[#12372A] w-full h-full m-0 p-0">
        <div className="flex justify-between items-center">
          <div className="space-x-4 mb-3 mt-1">
            <Link to={`/`}>
              <img src={TestLogo} className="h-19 bg-[#12372A]" alt="Logo" />
            </Link>
          </div>

          {/* 지역선택 부분 */}
          <div className="space-x-4 my-5">
            <img
              src={LocationIcon}
              className="h-8 cursor-pointer"
              alt="Location Icon"
              onClick={toggleLocationButtonContainer}
            />
          </div>

          {/* 날씨 부분 */}
          {filteredData.length > 0 ? (
            <div className="grid grid-cols-1 bg-slate-100 ">
              {filteredData.map((openWeather) => (
                <div key={openWeather.tno}>
                  <div className="grid grid-cols-2 ">
                    {/* 달력 */}
                    <input
                      type="date"
                      id="date"
                      value={selectedDate}
                      onChange={handleDateChange}
                      className="m-0 text-18pt text-[#FFFFFF] bg-[#12372A] cursor-pointer 
                                border-transparent hover:border-white hover:rounded-lg"
                    />

                    {/* 달력 아이콘 부분  */}
                    <style>
                      {`
                      input[type="date"]::-webkit-calendar-picker-indicator {
                        filter: invert(1);
                      }
                    `}
                    </style>

                    {/* 지역 */}
                    <div className="text-16pt font-bold text-[#FFFFFF] bg-[#12372A] flex justify-center items-center">
                      {openWeather.urlKRName}
                      <img
                        src={ICONS[openWeather.weatherIcon]}
                        alt={openWeather.description}
                        className="ml-2 w-6 h-6 white-filter"
                      />
                    </div>
                  </div>

                  {/* 날씨 정보*/}
                  <div className="grid grid-cols-1 bg-slate-100 ">
                    <h3 className="text-10px text-[#FFFFFF] bg-[#12372A] flex justify-between pr-0">
                      🌛{Math.round(openWeather.minTemp)}℃ 🌝
                      {Math.round(openWeather.maxTemp)}°C 💧
                      {Math.round(openWeather.humidity)}% 💨
                      {Math.round(openWeather.windSpeed)}㎧{/* °C m/s */}
                    </h3>
                  </div>
                </div>
              ))}
            </div>
          ) : (
            <div>No data available</div>
          )}
        </div>

        {showLocationButtons && (
          <div className="bg-white border-2">
            {cityNames.map((cityName) => (
              <button
                key={cityName}
                onClick={() => handleCityClick(cityName)}
                className={`py-2 px-4 bg-[#12372A] text-[#E8DFCA] rounded hover:bg-blue-600 focus:outline-none focus:bg-blue-600 m-2 ${
                  cityName === selectedCity ? "bg-blue-600" : ""
                }`}
              >
                {cityName}
              </button>
            ))}
          </div>
        )}
      </div>
    </div>
  );
};

export default ReadOpenWeatherComponentOneDay;
