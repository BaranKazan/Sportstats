import RoundGetBySeason from "../../adapters/get/RoundsBySeason";
import React, { Component } from "react";
import GameView from "./GameView";
import { Input } from "reactstrap";
import RoundGamesAdd from "../../adapters/add/RoundGames";
import ModalInput from "../layout/reusables/ModalInput";

export default class RoundGamesView extends Component {
  state = {
    rounds: [],
    names: [],
    seasonId: this.props.seasonId,
    roundNumber: 0,
    alertSuccess: false,
    alertError: false,
    alertMsg: "",
    modal: false
  };

  render() {
    return (
      <div>
        {/*Modal*/}
        <ModalInput
          modal={this.state.modal}
          alertSuccess={this.state.alertSuccess}
          alertMsg={this.state.alertMsg}
          alertError={this.state.alertError}
          hideModal={this.hideModal}
          showModal={this.showModal}
          addType={this.addRoundGames}
          onAlertDismiss={this.onAlertDismiss}
          header="Add New Round"
          labelForInputOne="Round Number"
          buttonText="Add Round"
          inputTwo={
            <Input
              type="number"
              min="1"
              placeholder="I.e. 2"
              className="form-control"
              onChange={this.setRoundNumber}
              required
            />
          }
        />
        {this.state.rounds.map(round => (
          <div>
            <h6 className="lbl">Round Number: {round.roundNumber}</h6>
            <GameView id={round.id} seasonId={this.props.seasonId} /> <br></br>
          </div>
        ))}
      </div>
    );
  }
  componentDidMount() {
    this.getRoundBySeasonId();
  }

  getRoundBySeasonId() {
    RoundGetBySeason(this.props.seasonId, rounds => this.setState({ rounds }));
  }

  setRoundNumber = e => {
    this.setState({
      roundNumber: e.target.value
    });
  };

  onAlertDismiss = () =>
    this.setState({ alertSuccess: false, alertError: false });

  showModal = e => {
    e.preventDefault();
    this.setState({ modal: true });
  };

  hideModal = () => this.setState({ modal: false });

  addRoundGames = e => {
    e.preventDefault();
    this.hideModal();
    RoundGamesAdd(
      this.state.seasonId,
      this.state.roundNumber,
      res => {
        this.setState(
          {
            alertSuccess: true,
            alertMsg: "Success! " + res.data.replace("</br>", "")
          },
          this.getRoundBySeasonId()
        );
      },
      e => {
        this.setState({
          alertError: true,
          alertMsg: "Error! Failed to add round: " + e
        });
      }
    );
  };
}
