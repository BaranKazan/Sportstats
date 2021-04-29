import React from "react";
import GameAddView from "../layout/GameAddView";

export default class Games extends React.Component {
  render() {
    return (
      <div>
        <GameAddView
          roundId={this.props.location.state.id}
          teams={this.props.location.state.teamsInRound}
        />
      </div>
    );
  }
}
