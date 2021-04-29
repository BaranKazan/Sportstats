import SeasonGetByLeague from "../../adapters/get/SeasonsByLeague";
import React, { Component } from "react";
import ButtonGroupView from "./reusables/ButtonGroupView";
import SeasonAdd from "../../adapters/add/Season";
import ModalInput from "./reusables/ModalInput";
import { Input } from "reactstrap";

export default class SeasonView extends Component {
  state = {
    seasons: [],
    numberOfRounds: 0,
    leagueId: this.props.leagueId,
    startYear: "",
    endYear: "",
    alertSuccess: false,
    alertError: false,
    alertMsg: "",
    modal: false
  };

  render() {
    return (
      <div>
        <ButtonGroupView
          items={this.state.seasons}
          titleLeft="Matches"
          titleRight="Teams"
          path={{ pathname: "/RoundGames", state: { id: 1 } }}
        ></ButtonGroupView>
        <div className="text-center p-4">
          {/*<SeasonAdd leagueId={this.props.leagueId} /*forceUpdate={this.props.forceUpdate}/>*/}
          <ModalInput
            modal={this.state.modal}
            alertSuccess={this.state.alertSuccess}
            alertMsg={this.state.alertMsg}
            alertError={this.state.alertError}
            hideModal={this.hideModal}
            showModal={this.showModal}
            addType={this.addSeason}
            onAlertDismiss={this.onAlertDismiss}
            header="Add New Season"
            labelForInputOne="Start Year:"
            labelForInputTwo="End Year:"
            labelForInputThree="Number of Rounds:"
            buttonText="Add Season"
            inputOne={
              <Input
                type="number"
                min="2018"
                placeholder="I.e. 2018"
                className="form-control"
                onChange={this.setStart}
                required
              />
            }
            inputTwo={
              <Input
                type="number"
                min="2019"
                placeholder="I.e. 2019"
                className="form-control"
                onChange={this.setEnd}
                pattern="\d+"
                required
              />
            }
            inputThree={
              <Input
                type="number"
                min="10"
                placeholder="I.e. 32"
                className="form-control"
                onChange={this.setNumberOfRounds}
                required
              />
            }
          />
        </div>
      </div>
    );
  }
  update = seasons => {
    this.setState({
      seasons: seasons.map(s => {
        s.text = ["Season: ", s.seasonStartYear, "/", s.seasonEndYear];
        s.pathLeft = { pathname: "/RoundGames", state: { id: s.id } };
        s.pathRight = { pathname: "/Teams", state: { id: s.id } };
        return s;
      })
    });
  };

  componentDidMount() {
    this.getSeasonByLeagueId();
  }

  getSeasonByLeagueId() {
    SeasonGetByLeague(this.props.leagueId, this.update);
  }
  setStart = e => {
    this.setState({
      startYear: e.target.value
    });
  };

  setEnd = e => {
    this.setState({
      endYear: e.target.value
    });
  };

  setNumberOfRounds = e => {
    this.setState({
      numberOfRounds: e.target.value
    });
  };

  onAlertDismiss = () =>
    this.setState({ alertSuccess: false, alertError: false });

  showModal = e => {
    e.preventDefault();
    this.setState({ modal: true });
  };

  hideModal = () => this.setState({ modal: false });

  addSeason = e => {
    e.preventDefault();
    this.hideModal();
    SeasonAdd(
      this.state.leagueId,
      this.state.startYear,
      this.state.endYear,
      this.state.numberOfRounds,
      res => {
        this.setState(
          {
            alertSuccess: true,
            alertMsg: "Success! " + res.data.replace("</br>", "")
          },
          this.getSeasonByLeagueId()
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
