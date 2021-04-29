import React from "react";
import GamesGetByTeam from "../../adapters/get/GamesByTeam";
import GoalsGetByGame from "../../adapters/get/GoalsCountByGame";
import GameList from "./reusables/GameList";
import DateChooser from "./reusables/DateChooser";
import GamesLargestLosses from "../../adapters/get/GamesLargestLosses";
import GamesLargestWins from "../../adapters/get/GamesLargestWins";
import StreakPane from "./reusables/StreakPane";
import WinStreakByTeam from "../../adapters/get/WinStreakByTeam";
import TieStreakByTeam from "../../adapters/get/TieStreakByTeam";
import LoseStreakByTeam from "../../adapters/get/LoseStreakByTeam";
import { Button } from "reactstrap";

export default class TeamGamesView extends React.Component {
  constructor(props) {
    super(props);

    this.state = {
      recentGames: [],
      largestWins: [],
      largestLosses: [],
      fromDate: "1900-01-01",
      toDate: "2020-01-01",
      winStreak: 0,
      tieStreak: 0,
      loseStreak: 0
    };
  }

  render() {
    return (
      <div>
        <Button className="btn btn-success" onClick={this.fetchData}>
          Update
        </Button>
        <h1 style={labelStyle}>Stats about {this.props.teamName}</h1>
        <h4 style={labelStyle}>Recent games</h4>
        <GameList
          games={this.state.recentGames}
          teamsInSeason={this.props.teamsInSeason}
        ></GameList>
        <hr style={{ margin: "50px" }}></hr>
        <h2>
          <span style={labelStyle}>Stats filtered between:</span>
          <span style={labelStyle}>{this.state.fromDate}</span>
          <span style={labelStyle}>and</span>
          <span style={labelStyle}>{this.state.toDate}</span>
        </h2>
        <div style={labelSpaceEnd}>
          <DateChooser
            onFilter={(fromDate, toDate) =>
              // setState works async, callback for re-rendering
              this.setState({ fromDate, toDate }, () => this.fetchData())
            }
          />
        </div>
        <div style={labelSpaceEnd}>
          <StreakPane
            style={labelSpaceEnd}
            wins={this.state.winStreak}
            ties={this.state.tieStreak}
            losses={this.state.loseStreak}
          />
        </div>
        <h4 style={labelStyle}>Largest wins</h4>
        <GameList
          games={this.state.largestWins}
          teamsInSeason={this.props.teamsInSeason}
        ></GameList>
        <div style={labelSpaceEnd}></div>
        <h4 style={labelStyle}>Largest losses</h4>
        <GameList
          games={this.state.largestLosses}
          teamsInSeason={this.props.teamsInSeason}
        ></GameList>
        <div style={labelSpaceEnd}></div>
      </div>
    );
  }

  setGameFormat = games =>
    games
      .map(g => {
        g.date = g.date.split("-").join("/");
        g.homeTeamScore = 0;
        g.awayTeamScore = 0;
        return g;
      })
      .sort((g1, g2) => g2.date.localeCompare(g1.date));

  setGoalsForGame = (g, cb) => {
    GoalsGetByGame(g.id, g.homeTeamId, g.awayTeamId, cb);
  };

  setLargestLosses = () => {
    //Retrieves largest loss games for team
    GamesLargestLosses(
      this.props.teamId,
      this.state.fromDate,
      this.state.toDate,
      games => {
        this.setState({
          largestLosses: this.setGameFormat(games)
        });

        // Sets each game score
        this.state.largestLosses.forEach(game => {
          this.setGoalsForGame(game, (homeGoals, awayGoals) => {
            this.setState(
              this.state.largestLosses.map(g => {
                if (game.id === g.id) {
                  g.homeTeamScore = homeGoals;
                  g.awayTeamScore = awayGoals;
                }
                return g;
              })
            );
          });
        });
      }
    );
  };

  setLargestWins = () => {
    //Retrieves largest win games for team
    GamesLargestWins(
      this.props.teamId,
      this.state.fromDate,
      this.state.toDate,
      games => {
        this.setState({
          largestWins: this.setGameFormat(games)
        });

        // Sets each game score
        this.state.largestWins.forEach(game => {
          this.setGoalsForGame(game, (homeGoals, awayGoals) => {
            this.setState(
              this.state.largestWins.map(g => {
                if (game.id === g.id) {
                  g.homeTeamScore = homeGoals;
                  g.awayTeamScore = awayGoals;
                }
                return g;
              })
            );
          });
        });
      }
    );
  };

  setRecentGames = () => {
    //Retrieves all games for team
    GamesGetByTeam(this.props.teamId, games => {
      this.setState({
        recentGames: this.setGameFormat(games)
      });

      // Sets each game score
      this.state.recentGames.forEach(game => {
        this.setGoalsForGame(game, (homeGoals, awayGoals) => {
          this.setState(
            this.state.recentGames.map(g => {
              if (game.id === g.id) {
                g.homeTeamScore = homeGoals;
                g.awayTeamScore = awayGoals;
              }
              return g;
            })
          );
        });
      });
    });
  };

  setStreaks = () => {
    WinStreakByTeam(
      this.props.teamId,
      this.state.fromDate,
      this.state.toDate,
      winStreak => this.setState({ winStreak })
    );
    TieStreakByTeam(
      this.props.teamId,
      this.state.fromDate,
      this.state.toDate,
      tieStreak => this.setState({ tieStreak })
    );
    LoseStreakByTeam(
      this.props.teamId,
      this.state.fromDate,
      this.state.toDate,
      loseStreak => this.setState({ loseStreak })
    );
  };

  fetchData = () => {
    this.setRecentGames();
    this.setLargestLosses();
    this.setLargestWins();
    this.setStreaks();
  };

  componentDidMount() {
    this.fetchData();
  }
}

const labelStyle = {
  display: "flex",
  alignItems: "center",
  justifyContent: "center"
};

const labelSpaceEnd = {
  ...labelStyle,
  margin: "0px 0px 40px 0px"
};
