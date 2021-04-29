import SportGetAll from "../../adapters/get/SportsAll";
import React, { Component } from "react";
import ModalText from "./reusables/ModalText";
import SportAdd from "../../adapters/add/Sport";
import { ModalBody, Form, FormGroup, Button, Label, Input } from "reactstrap";

export default class SportAddView extends Component {
  state = {
    sports: [],
    name: "",
    periods: 0,
    minsPeriod: 0,
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
          setName={this.setName}
          setMinutes={this.setMinutes}
          setPeriods={this.setPeriods}
          name={this.state.name}
          periods={this.state.periods}
          minsPeriod={this.state.minsPeriod}
          addSport={this.addSport}
          modalBody={
            <ModalBody>
              <p>The following sport is going to be added:</p>
              <p>Name: {this.state.name}</p>
              <p>Number of periods: {this.state.periods}</p>
              <p>Minutes per period: {this.state.minsPeriod}</p>
            </ModalBody>
          }
        />
        <Form onSubmit={this.showModal}>
          <FormGroup>
            <Label>Sport name:</Label>
            <Input
              pattern="[A-Za-z]+"
              type="name"
              placeholder="I.e. Football or Hockey"
              className="form-control"
              onChange={this.setName}
              required
            />
          </FormGroup>
          <FormGroup>
            <Label>Select number of periods:</Label>
            <Input
              type="select"
              className="form-control"
              onChange={this.setPeriods}
              required
            >
              <option>1</option>
              <option>2</option>
              <option>3</option>
              <option>4</option>
              <option>5</option>
              <option>6</option>
            </Input>
          </FormGroup>
          <FormGroup>
            <Label>Period time in minutes:</Label>
            <Input
              min="1"
              max="120"
              type="number"
              placeholder="I.e. 45"
              className="form-control"
              onChange={this.setMinutes}
              required
            />
          </FormGroup>
          <Button type="submit" className="btn-success">
            Submit
          </Button>
        </Form>
      </div>
    );
  }

  update = sports => {
    this.setState({
      sports: sports.map(s => {
        s.title = s.name;
        s.text = [
          <br />,
          "Periods: ",
          s.nrOfPeriods,
          <br />,
          "Time Per Period (minutes): ",
          s.periodTimeInMinutes
        ];
        s.path = { pathname: "/Leagues", state: { id: s.id } };
        return s;
      })
    });
  };

  componentDidMount() {
    SportGetAll(this.update);
  }

  setName = e => {
    this.setState({
      name: e.target.value
    });
  };

  setPeriods = e => {
    this.setState({
      periods: e.target.value
    });
  };

  setMinutes = e => {
    this.setState({
      minsPeriod: e.target.value
    });
  };

  onAlertDismiss = () =>
    this.setState({ alertSuccess: false, alertError: false });

  showModal = e => {
    e.preventDefault();
    this.setState({ modal: true });
  };
  hideModal = () => this.setState({ modal: false });

  addSport = () => {
    this.hideModal();
    SportAdd(
      this.state.name,
      this.state.periods,
      this.state.minsPeriod,
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
