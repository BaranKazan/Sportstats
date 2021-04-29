import GamesGetByRound from "../../adapters/get/GamesByRound";
import React, { Component } from "react";
import GameList from "./reusables/GameList";
import TeamsGetBySeason from "../../adapters/get/TeamsBySeason";
import GoalsGetByGame from "../../adapters/get/GoalsCountByGame";
import { Button } from "reactstrap";
import { Link } from "react-router-dom";

export default class GameView extends Component {
  state = {
    games: [],
    teams: []
  };

  render() {
    return (
      <div>
        <GameList
          games={this.state.games}
          teamsInSeason={this.state.teams}
        ></GameList>
        <div className="lbl" style={{ padding: "6px" }}>
          <Button
            className="btn btn-success"
            tag={Link}
            to={{
              pathname: "/GameAdd",
              state: { id: this.props.id, teamsInRound: this.state.teams }
            }}
          >
            Add Game
          </Button>
        </div>
      </div>
    );
  }

  componentDidMount() {
    GamesGetByRound(this.props.id, games => {
      this.setState({
        games: games
          .map(g => {
            // Changes date format
            g.date = g.date.split("-").join("/");

            // Adds score attributes
            g.homeTeamScore = 0;
            g.awayTeamScore = 0;

            // Adds team name attributes
            // Use helper method to get names
            g.homeTeamName = "N/A";
            g.awayTeamName = "N/A";
            return g;
          })
          .sort((g1, g2) => g2.date.localeCompare(g1.date))
      });

      // Sets each game score
      this.state.games.forEach(g => {
        GoalsGetByGame(
          g.id,
          g.homeTeamId,
          g.awayTeamId,
          (homeGoals, awayGoals) => {
            this.setState(
              this.state.games.map(game => {
                if (game.id === g.id) {
                  game.homeTeamScore = homeGoals;
                  game.awayTeamScore = awayGoals;
                }
                return game;
              })
            );
          }
        );
      });
    });
    TeamsGetBySeason(this.props.seasonId, teams => this.setState({ teams }));
  }
}
