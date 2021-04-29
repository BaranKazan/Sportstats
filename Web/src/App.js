import React, { useState } from "react";
import "bootstrap/dist/css/bootstrap.min.css";
import "./App.css";
import SideBar from "./components/ui-components/SideBar";
import { BrowserRouter as Router, Route } from "react-router-dom";
import SlideShow from "./components/pages/SlideShow";
import Sports from "./components/pages/Sports";
import GameAdd from "./components/pages/GameAdd";
import SportAdd from "./components/pages/SportAdd";
import Developers from "./components/pages/Developers";
import Leagues from "./components/pages/Leagues";
import Seasons from "./components/pages/Seasons";
import Teams from "./components/pages/Teams";
import TeamStats from "./components/pages/TeamStats";
import RoundGames from "./components/pages/RoundGames";
import Navbar from "./components/ui-components/Navbar";
import { Container } from "reactstrap";
import classNames from "classnames";

/**
 * This is the default component who acts as the root
 * of the react single-page application. In this component
 * all the other components who builds up the application
 * are placed. It contains the sidebar and also a container
 * where all the routing content is placed.
 */
export default () => {
  const [isOpen, setOpen] = useState(false);
  const toggle = () => setOpen(!isOpen);

  return (
    <Router basename={process.env.PUBLIC_URL}>
      <div className="App wrapper">
        <SideBar toggle={toggle} isOpen={isOpen} />
        <Container
          fluid
          className={classNames("content", { "is-open": isOpen })}
        >
          <Navbar toggle={toggle} />

          <Route exact path="/" component={SlideShow}></Route>
          <Route exact path="/Sports" component={Sports}></Route>
          <Route exact path="/Leagues" component={Leagues}></Route>
          <Route exact path="/GameAdd" component={GameAdd}></Route>
          <Route exact path="/Seasons" component={Seasons}></Route>
          <Route exact path="/Teams" component={Teams}></Route>
          <Route exact path="/SportAdd" component={SportAdd}></Route>
          <Route exact path="/TeamStats" component={TeamStats}></Route>
          <Route exact path="/RoundGames" component={RoundGames}></Route>
          <Route exact path="/Developers" component={Developers}></Route>
        </Container>
      </div>
    </Router>
  );
};
