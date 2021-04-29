import React, { Component } from "react";
import ModalText from "./reusables/ModalText";
import GameAdd from "../../adapters/add/Game";
import { ModalBody, Form, FormGroup, Button, Label, Input } from "reactstrap";

export default class GameAddView extends Component {
  state = {
    spectators: 0,
    awayTeam: "",
    homeTeam: "",
    date: "",
    homeTeamId: 0,
    awayTeamId: 0,
    alertSuccess: false,
    alertError: false,
    alertMsg: "",
    modal: false
  };

  render() {
    return (
      <div>
        <ModalText
          modal={this.state.modal}
          alertSuccess={this.state.alertSuccess}
          alertMsg={this.state.alertMsg}
          alertError={this.state.alertError}
          hideModal={this.hideModal}
          showModal={this.showModal}
          onAlertDismiss={this.onAlertDismiss}
          addSport={this.getTeamId}
          modalBody={
            <ModalBody>
              <p>The following Game is going to be added:</p>
              <p>Date: {this.state.date}</p>
              <p>Spectators: {this.state.spectators}</p>
              <p>
                Teams: {this.state.homeTeam} - {this.state.awayTeam}
              </p>
            </ModalBody>
          }
        />
        <Form onSubmit={this.showModal}>
          <FormGroup>
            <Label>Game Date:</Label>
            <Input
              min="1899-01-01"
              type="date"
              placeholder="I.e. Football or Hockey"
              className="form-control"
              onChange={this.setDate}
              required
            />
          </FormGroup>
          <FormGroup>
            <Label>Select amount of spectators:</Label>
            <Input
              min="100"
              max="80000"
              pattern="\d+"
              type="number"
              className="form-control"
              onChange={this.setSpectators}
              required
            />
          </FormGroup>
          <FormGroup>
            <Label>Home Team:</Label>
            <Input
              defaultValue={"DEFAULT"}
              type="select"
              className="form-control"
              onChange={this.setHomeTeam}
              required
            >
              <option value="DEFAULT" disabled hidden>
                Choose Home Team
              </option>
              {this.props.teams.map(t => {
                return <option>{t.name}</option>;
              })}
            </Input>
          </FormGroup>
          <FormGroup>
            <Label>Away Team:</Label>
            <Input
              defaultValue={"DEFAULT"}
              type="select"
              className="form-control"
              onChange={this.setAwayTeam}
              required
            >
              <option value="DEFAULT" disabled hidden>
                Choose Away Team
              </option>
              {this.props.teams.map(t => {
                return <option>{t.name}</option>;
              })}
            </Input>
          </FormGroup>
          <Button type="submit" className="btn-success">
            Submit
          </Button>
        </Form>
      </div>
    );
  }
  setSpectators = e => {
    this.setState({
      spectators: e.target.value
    });
  };

  setDate = e => {
    this.setState({
      date: e.target.value
    });
  };

  setHomeTeam = e => {
    this.setState({
      homeTeam: e.target.value
    });
  };
  setHomeTeamId = e => {
    this.setState({
      homeTeamId: e.target.value
    });
  };
  setAwayTeamId = e => {
    this.setState({
      awayTeamId: e.target.value
    });
  };

  setAwayTeam = e => {
    this.setState({
      awayTeam: e.target.value
    });
  };

  getTeamId = e => {
    this.props.teams.forEach(t => {
      if (t.name === this.state.awayTeam) {
        this.setState({ awayTeamId: t.id }, () => this.addGame());
      }
      if (t.name === this.state.homeTeam) {
        this.setState({ homeTeamId: t.id });
      }
    });
  };

  onAlertDismiss = () =>
    this.setState({ alertSuccess: false, alertError: false });

  showModal = e => {
    e.preventDefault();
    this.setState({ modal: true });
  };
  hideModal = () => this.setState({ modal: false });

  addGame = () => {
    this.hideModal();
    GameAdd(
      this.state.spectators,
      this.state.date,
      this.props.roundId,
      this.state.homeTeamId,
      this.state.awayTeamId,

      res => {
        this.setState({
          alertSuccess: true,
          alertMsg: "Success! " + res.data.replace("</br>", "")
        });
      },
      e => {
        this.setState({
          alertError: true,
          alertMsg: "Error! Failed to add league: " + e
        });
      }
    );
  };
}
