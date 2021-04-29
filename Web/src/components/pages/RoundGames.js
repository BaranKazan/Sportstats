import React, { Component } from "react";
import RoundGamesView from "../layout/RoundGamesView";

export default class RoundGames extends Component {
  render() {
    return (
      <div>
        <RoundGamesView seasonId={this.props.location.state.id} />
      </div>
    );
  }
}
