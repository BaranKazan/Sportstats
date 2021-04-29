import React from "react";
import TeamStatsView from "../layout/TeamStatsView";

export default class TeamStats extends React.Component {
  render() {
    return (
      <div>
        <TeamStatsView
          teamId={this.props.location.state.id}
          teamName={this.props.location.state.name}
          seasonId={this.props.location.state.seasonId}
          teamsInSeason={this.props.location.state.teams}
        />
      </div>
    );
  }
}
