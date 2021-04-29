import SportGetAll from "../../adapters/get/SportsAll";
import React, { Component } from "react";
import SlideShow from "../pages/SlideShow";
import { Alert } from "reactstrap";
import CardView from "./reusables/CardView";

export default class SportsView extends Component {
  state = {
    sports: []
  };

  render() {
    if (this.state.sports.length === 0) {
      return (
        <div>
          <div className="text-center">
            <Alert color="danger">
              Error! Possible Problems:
              <br/>
              <br />
              CORS is turned Off
              <br />
              Check Internet Connection
              <br />
              There Are No Avaiable Sports
            </Alert>
          </div>
          <SlideShow />
        </div>
      );
    } else
      return (
        <div>
          <CardView
            items={this.state.sports}
            path={{ pathname: "/Leagues", state: { id: 1 } }}
          ></CardView>
        </div>
      );
  }

  update = sports => {
    this.setState({
      sports: sports.map(s => {
        s.title = s.name;
        s.text = [
          <br />,
          "Periods: ",
          s.nrOfPeriods,
          <br />,
          "Time Per Period (minutes): ",
          s.periodTimeInMinutes
        ];
        s.path = { pathname: "/Leagues", state: { id: s.id } };
        return s;
      })
    });
  };

  componentDidMount() {
    SportGetAll(this.update);
  }
}
