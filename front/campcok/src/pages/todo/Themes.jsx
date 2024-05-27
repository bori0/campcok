import { Link } from "react-router-dom";
import { ReactComponent as ThemeIcon_1 } from "../../img/beach.svg";
import { ReactComponent as ThemeIcon_2 } from "../../img/island.svg";
import { ReactComponent as ThemeIcon_3 } from "../../img/mountain.svg";
import { ReactComponent as ThemeIcon_4 } from "../../img/forest.svg";
import { ReactComponent as ThemeIcon_5 } from "../../img/valley.svg";
import { ReactComponent as ThemeIcon_6 } from "../../img/river.svg";
import { ReactComponent as ThemeIcon_7 } from "../../img/lake.svg";
import { ReactComponent as ThemeIcon_8 } from "../../img/city.svg";

const Themes = () => {
  return (
    <div id="DetailedSearch" className="grid grid-cols-4 mt-10">
      <Link to="/search/list?theme=beach" className="m-auto">
        <ThemeIcon_1 className="ml-1" />
        <p>해변</p>
      </Link>
      <Link to="/search/list?theme=island" className="m-auto">
        <ThemeIcon_2 className="ml-1" />
        <p>섬</p>
      </Link>
      <Link to="/search/list?theme=mountain" className="m-auto">
        <ThemeIcon_3 className="ml-1" />
        <p>산</p>
      </Link>
      <Link to="/search/list?theme=forest" className="m-auto">
        <ThemeIcon_4 className="ml-1" />
        <p>숲</p>
      </Link>
      <Link to="/search/list?theme=valley" className="m-auto">
        <ThemeIcon_5 className="ml-1" />
        <p>계곡</p>
      </Link>
      <Link to="/search/list?theme=river" className="m-auto">
        <ThemeIcon_6 className="ml-1" />
        <p>강</p>
      </Link>
      <Link to="/search/list?theme=lake" className="m-auto">
        <ThemeIcon_7 className="ml-1" />
        <p>호수</p>
      </Link>
      <Link to="/search/list?theme=downtown" className="m-auto">
        <ThemeIcon_8 className="ml-1" />
        <p>도심</p>
      </Link>
    </div>
  );
};

export default Themes;
