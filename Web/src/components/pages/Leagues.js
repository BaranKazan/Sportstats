import React, { Component } from "react";
import LeagueView from "../layout/LeagueView";

export default class Leagues extends Component {
  render() {
    return (
      <div>
        <LeagueView sportId={this.props.location.state.id} /> 
      </div>
    );
  }
}
