import TeamsGetBySeason from "../../adapters/get/TeamsBySeason";
import React, { Component } from "react";
import CardView from "./reusables/CardView";
import TeamAdd from "../../adapters/add/Team";
import ModalView from "./reusables/ModalInput";
import { Input } from "reactstrap";

export default class TeamView extends Component {
  state = {
    teams: [],
    name: "",
    seasonId: this.props.seasonId,
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
          addType={this.addTeam}
          onAlertDismiss={this.onAlertDismiss}
          header="Add New Team"
          labelForInputOne="Team name:"
          buttonText="Add Team"
          inputTwo={
            <Input
              pattern="[A-Za-z]+"
              type="name"
              placeholder="I.e. Liverpool or Milan"
              className="form-control"
              onChange={this.setName}
              required
            />
          }
        />
            <CardView items={this.state.teams}></CardView>
            
      </div>
    );
  }

  componentDidMount() {
    this.getTeamBySeasonId();
  }

  addTeam = e => {
    e.preventDefault();
    this.hideModal();
    TeamAdd(
      this.state.name,
      this.state.seasonId,
      res => {
        this.setState(
          {
            alertSuccess: true,
            alertMsg: "Success! " + res.data.replace("</br>", "")
          },
          this.getTeamBySeasonId()
        );
      },
      e => {
        this.setState({
          alertError: true,
          alertMsg: "Error! Failed to add team: " + e
        });
      }
    );
  };

  getTeamBySeasonId() {
    TeamsGetBySeason(this.props.seasonId, teams => {
      // Sätt alla lag i state
      // this.setState({ teams });

      // Tilldela attribut
      this.setState({
        teams: teams.map(t => {
          t.title = t.name;

          t.text = "";
          t.path = {
            pathname: "/TeamStats",
            state: {
              id: t.id,
              name: t.name,
              seasonId: this.props.seasonId,
              teams: teams
            }
          };
          return t;
        })
      });
    });
  }

  setName = e => {
    this.setState({
      name: e.target.value
    });
  };

  onAlertDismiss = () =>
    this.setState({ alertSuccess: false, alertError: false });

  showModal = e => {
    e.preventDefault();
    this.setState({ modal: true });
  };

  hideModal = () => this.setState({ modal: false });
}
