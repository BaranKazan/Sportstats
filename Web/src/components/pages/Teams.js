import React from "react";
import TeamView from "../layout/TeamView";

export default class Teams extends React.Component {
  render() {
    return (
      <div>
        <TeamView seasonId={this.props.location.state.id} />
      </div>
    );
  }
}
