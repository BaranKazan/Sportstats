import LeagueGetBySport from "../../adapters/get/LeaguesBySport";
import React, { Component } from "react";
import SeasonView from "./SeasonView";
import { Card, Row, Col, CardHeader, Input } from "reactstrap";
import ModalView from "./reusables/ModalInput";
import LeagueAdd from "../../adapters/add/League";

export default class LeagueView extends Component {
  state = {
    leagues: [],
    name: "",
    sportId: this.props.sportId,
    alertSuccess: false,
    alertError: false,
    alertMsg: "",
    modal: false
  };

  render() {
    return (
      <div>
        <ModalView
          modal={this.state.modal}
          alertSuccess={this.state.alertSuccess}
          alertMsg={this.state.alertMsg}
          alertError={this.state.alertError}
          hideModal={this.hideModal}
          showModal={this.showModal}
          addType={this.addLeague}
          onAlertDismiss={this.onAlertDismiss}
          header="Add New League"
          labelForInputOne="League Name:"
          buttonText="Add League"
          inputTwo={
            <Input
              pattern="[A-Za-z]+"
              type="name"
              placeholder="I.e. Serie A, Premier League"
              className="form-control"
              onChange={this.setName}
              required
            />
          }
        />
        <Row>
          {this.state.leagues.map(league => (
            <div style={{ marginBottom: "15px" }}>
              <Col sm="auto">
                <Card body>
                  <CardHeader>{league.leagueName}</CardHeader>
                  <SeasonView
                    leagueId={league.id}
                    forceUpdate={this.forceUpdate}
                  />
                </Card>
              </Col>
            </div>
          ))}
        </Row>
      </div>
    );
  }
  componentDidMount() {
    this.getLeagueBySportId();
  }

  getLeagueBySportId() {
    LeagueGetBySport(this.props.sportId, leagues => this.setState({ leagues }));
  }

  setName = e => {
    this.setState({
      name: e.target.value
    });
  };

  onAlertDismiss = () =>
    this.setState({ alertSuccess: false, alertError: false });

  showModal = e => {
    // Behövs preventDefault??
    e.preventDefault();
    this.setState({ modal: true });
  };

  hideModal = () => this.setState({ modal: false });

  addLeague = e => {
    e.preventDefault();
    this.hideModal();
    LeagueAdd(
      this.state.name,
      this.state.sportId,
      res => {
        this.setState(
          {
            alertSuccess: true,
            alertMsg: "Success! " + res.data.replace("</br>", "")
          },
          this.getLeagueBySportId()
        );
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
