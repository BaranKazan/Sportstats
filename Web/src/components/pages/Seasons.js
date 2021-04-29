import React from "react";
import SeasonView from "../layout/SeasonView";

export default class Seasons extends React.Component {
  render() {
    return (
      <div>
        <SeasonView leagueId={this.props.location.state.id} />
      </div>
    );
  }
}
