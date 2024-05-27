import { useEffect, useState } from "react";
import { getList2 } from "../../../api/openWeather";

const Weather = ({ addr }) => {
  const [serverData, setServerData] = useState([]); //30일
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const [selectedCity, setSelectedCity] = useState(""); // 디폴트 값은 빈 문자열로 설정
  const [inputLocation, setInputLocation] = useState("");
  const [convertedLocation, setConvertedLocation] = useState("");
  const handleLocationChange = (event) => {
    setInputLocation(event.target.value);
  };

  useEffect(() => {
    // addr prop을 사용하여 변환된 도시 설정
    convertLocationFromAddr(addr);
  }, [addr]);

  const convertLocationFromAddr = (addr) => {
    const prefix = addr.substring(0, 2); // 주소의 맨 앞 두 글자 추출
    let city = "";
    switch (prefix) {
      case "강원":
        city = "춘천";
        break;
      case "경기":
        city = "인천";
        break;
      case "경남":
        city = "부산";
        break;
      case "경북":
        city = "안동";
        break;
      case "충북":
        city = "청주";
        break;
      case "전남":
        city = "광주";
        break;
      case "전북":
        city = "전주";
        break;
      case "충남":
        city = "대전";
        break;
      case "경상":
        city = "안동";
        break;
      case "전라":
        city = "광주";
        break;
      case "충청":
        city = "대전";
        break;
      default:
        city = prefix; // 알 수 없는 지역
    }
    setSelectedCity(city); // 변환된 도시명을 setSelectedCity를 통해 설정
  };
  useEffect(() => {
    convertLocationFromAddr(addr);
  }, [addr]);
  useEffect(() => {
    const storedData = JSON.parse(localStorage.getItem("serverData"));
    if (storedData) {
      setServerData(storedData);
      setLoading(false);
    } else {
      fetchData(); // 저장된 데이터가 없으면 서버에서 데이터 가져오기
    }
  }, []);

  const fetchData = () => {
    setLoading(true);
    getList2()
      .then((data) => {
        console.log("Received data: ", data);
        setServerData(data);
        localStorage.setItem("serverData", JSON.stringify(data)); // 데이터를 로컬 스토리지에 저장
        const secondCity = getSecondCity(data); // 두 번째 도시 가져오기
        setSelectedCity(secondCity); // 두 번째 도시를 기본값으로 설정
      })
      .catch((err) => {
        console.error("Error fetching data: ", err);
        setError(err);
      })
      .finally(() => {
        setLoading(false);
      });
  };

  const getSecondCity = (data) => {
    const cityNames = Array.from(new Set(data.map((city) => city.urlKRName)));
    if (cityNames.length >= 2) {
      return cityNames[1]; // 두 번째 도시 반환
    } else {
      return ""; // 데이터가 없거나 도시가 하나밖에 없을 경우 빈 문자열 반환
    }
  };

  //에러 시 표현
  if (loading) return <div>Loading...</div>;
  if (error) return <div>Error: {error.message}</div>;

  // 선택한 도시에 해당하는 데이터만 필터링
  const filteredData = serverData.filter(
    (data) => data.urlKRName === selectedCity
  );

  return (
    <div className="  mt-10 mr-2 ml-2 p-4 overflow-x-auto">
      {filteredData.length > 0 ? (
        <div className="flex flex-nowrap gap-4">
          {filteredData.map((openWeather) => (
            <div
              key={openWeather.tno}
              className="border border-gray-200 p-4 rounded-lg min-w-max"
            >
              <div className="mt-2">
                📅 {openWeather.date} {getWeatherIcon(openWeather.weatherIcon)}
              </div>
              <div></div>
              <div>Day Temp: {Math.round(openWeather.dayTemp)}°C</div>
              <div>
                🌝 {Math.round(openWeather.maxTemp)}°C 🌛{" "}
                {Math.round(openWeather.minTemp)}°C
              </div>
              <div>
                💧 {Math.round(openWeather.humidity)}% 💨{" "}
                {Math.round(openWeather.windSpeed)}m/s
              </div>
            </div>
          ))}
        </div>
      ) : (
        <div>No data available</div>
      )}
    </div>
  );
};

const getWeatherIcon = (weatherIcon) => {
  switch (weatherIcon) {
    case "01d":
      return "☀️"; // 맑음 (주간)
    case "01n":
      return "🌙"; // 맑음 (야간)
    case "02d":
    case "02n":
      return "⛅"; // 구름 조금 (주간, 야간)
    case "03d":
    case "03n":
      return "🌥️"; // 구름 (주간, 야간)
    case "04d":
    case "04n":
      return "☁️"; // 흐림 (주간, 야간)
    case "09d":
    case "09n":
      return "🌧️"; // 비 (주간, 야간)
    case "10d":
    case "10n":
      return "🌧️"; // 비 (주간, 야간)
    case "11d":
    case "11n":
      return "⛈️"; // 천둥번개 (주간, 야간)
    case "13d":
    case "13n":
      return "❄️"; // 눈 (주간, 야간)
    case "50d":
    case "50n":
      return "🌫️"; // 안개 (주간, 야간)
    default:
      return ""; // 기타
  }
};

export default Weather;
