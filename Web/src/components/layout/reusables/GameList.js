import React from "react";
import {
  ListGroup,
  ListGroupItem,
  Row,
  Col,
  Table,
  Modal,
  ModalHeader,
  ModalBody,
  ModalFooter,
  Label,
  FormGroup,
  Input
} from "reactstrap";
import PropTypes from "prop-types";
import GoalsByGame from "../../../adapters/get/GoalsByGame";
import ModalView from "./ModalInput";
import GoalAdd from "../../../adapters/add/Goal";

/**
 * The component used to list games with simular
 * styling on different pages of the application.
 * Provided a list of games this component will show
 * up as a list with functionality to show detailed
 * game information and add goals to a game.
 */
export default class GameList extends React.Component {
  state = {
    modalTitle: "Hello",
    homeTeamId: -1,
    awayTeamId: -1,
    selTeamId: "",
    selGameId: "",
    homeTeamName: "",
    awayTeamName: "",
    currentGame: {},
    modalOpen: false,
    spectators: 0,
    date: "2000-01-01",
    goals: [],
    modalInputOpen: false,
    alertIsSuccess: false,
    alertIsFail: false,
    period: "",
    time: "",
    penChecked: false,
    otChecked: false
  };

  currentHomeScore = 0;
  currentAwayScore = 0;

  incHomeScore = () => this.currentHomeScore++;
  incAwayScore = () => this.currentAwayScore++;
  resetScores = () => {
    this.currentHomeScore = 0;
    this.currentAwayScore = 0;
  };

  modalClose = () => this.setState({ modalOpen: false });

  getTeamNameFromId = id => {
    let team = this.props.teamsInSeason.find(t => {
      return t.id === id;
    });

    if (!team) {
      return "N/A";
    } else {
      return team.name;
    }
  };

  addGoal = () => {
    let gameId = this.state.selGameId;
    let teamId = this.state.selTeamId;
    let period = this.state.period;
    let time = this.state.time;
    let isPenalty = this.state.penChecked;
    let isOvertime = this.state.otChecked;

    GoalAdd(gameId, teamId, period, time, isPenalty, isOvertime, res => {
      this.setState({ modalInputOpen: false, modalOpen: false }, () =>
        this.showModal(this.state.currentGame)
      );
    });
  };

  componentDidUpdate() {
    this.resetScores();
  }

  showModal = game => {
    if (this.state.modalOpen) {
      this.setState({ modalOpen: false });
    } else {
      GoalsByGame(game.id, res => {
        this.setState({
          modalTitle:
            this.getTeamNameFromId(game.homeTeamId) +
            " - " +
            this.getTeamNameFromId(game.awayTeamId),
          homeTeamId: game.homeTeamId,
          awayTeamId: game.awayTeamId,
          currentGame: game,
          selGameId: game.id,
          date: game.date,
          spectators: game.spectators,
          goals: res.sort((g1, g2) => g1.elapsedTime - g2.elapsedTime),
          modalOpen: true
        });
      });
    }
  };

  render() {
    return (
      <div>
        <Modal isOpen={this.state.modalOpen} toggle={this.showModal}>
          <ModalHeader
            toggle={this.modalClose}
            cssModule={{ "modal-title": "w-100 text-center" }}
          >
            {this.state.modalTitle}
          </ModalHeader>

          <ModalBody>
            <div className="lbl">
              <span>Date: {this.state.date}</span>
              <br></br>
              <span>Spectators: {this.state.spectators}</span>
            </div>
          </ModalBody>
          <ModalBody>
            <Table>
              <thead>
                <tr>
                  <th>Time</th>
                  <th>Score</th>
                  <th>Extra</th>
                </tr>
              </thead>
              <tbody>
                {this.state.goals.map(goal => {
                  if (goal.teamId === this.state.homeTeamId) {
                    this.incHomeScore();
                  }
                  if (goal.teamId === this.state.awayTeamId) {
                    this.incAwayScore();
                  }

                  let extra = "-";

                  if (goal.penalty && goal.overtime) {
                    extra = "P OT";
                  } else if (goal.penalty) {
                    extra = "P";
                  } else if (goal.overtime) {
                    extra = "OT";
                  }

                  return (
                    <tr>
                      <td>{goal.elapsedTime}'</td>
                      <td>
                        {this.currentHomeScore} - {this.currentAwayScore}
                      </td>
                      <td>{extra}</td>
                    </tr>
                  );
                })}
              </tbody>
            </Table>
          </ModalBody>

          <ModalFooter>
            <ModalView
              modal={this.state.modalInputOpen}
              alertSuccess={this.state.alertIsSuccess}
              alertMsg={" SOme message "}
              alertError={this.state.alertIsFail}
              hideModal={() => this.setState({ modalInputOpen: false })}
              showModal={() => this.setState({ modalInputOpen: true })}
              addType={this.addGoal}
              onAlertDismiss={() =>
                this.setState({ alertIsSuccess: false, alertIsFail: false })
              }
              header="Add Goal"
              labelForInputOne="Time"
              inputOne={
                <Input
                  min="1"
                  max="150"
                  type="number"
                  className="form-control"
                  onChange={e => this.setState({ time: e.target.value })}
                  value={this.state.penVal}
                  required
                />
              }
              labelForInputTwo="Period"
              inputTwo={
                <Input
                  min="1"
                  max="6"
                  type="number"
                  className="form-control"
                  onChange={e => this.setState({ period: e.target.value })}
                  checked={this.state.otVal}
                  required
                />
              }
              labelForInputThree="Extras"
              inputThree={
                <div>
                  <FormGroup>
                    <Label for="teamChooser">Who scored?</Label>
                    <Input
                      defaultValue={"DEFAULT"}
                      type="select"
                      name="select"
                      id="teamChooser"
                      onChange={e =>
                        this.setState({ selTeamId: e.nativeEvent.target.value })
                      }
                    >
                      <option disabled hidden value="DEFAULT">
                        Choose
                      </option>
                      <option value={this.state.homeTeamId}>
                        {this.getTeamNameFromId(this.state.homeTeamId)}
                      </option>
                      <option value={this.state.awayTeamId}>
                        {this.getTeamNameFromId(this.state.awayTeamId)}
                      </option>
                    </Input>
                  </FormGroup>
                  <FormGroup check>
                    <Label check>
                      <Input
                        type="checkbox"
                        checked={this.state.penChecked}
                        onChange={() =>
                          this.setState({ penChecked: !this.state.penChecked })
                        }
                      />{" "}
                      Penalty
                    </Label>
                  </FormGroup>
                  <FormGroup check>
                    <Label check>
                      <Input
                        type="checkbox"
                        checked={this.state.otChecked}
                        onChange={() =>
                          this.setState({ otChecked: !this.state.otChecked })
                        }
                      />{" "}
                      Overtime
                    </Label>
                  </FormGroup>
                </div>
              }
              buttonText="Add Goal"
            />
          </ModalFooter>
        </Modal>
        <ListGroup>
          {this.props.games.map(game => {
            return (
              <ListGroupItem
                onClick={() => this.showModal(game)}
                className="col-sm-6"
                style={{ placeSelf: "center" }}
              >
                <Row className="justify-content-md-center">
                  <Col xs={1} md={1} lg={1}>
                    <Row>{game.homeTeamScore}</Row>
                    <Row>{game.awayTeamScore}</Row>
                  </Col>
                  <Col xs={7} md={5} lg={7}>
                    <Row>{this.getTeamNameFromId(game.homeTeamId)}</Row>
                    <Row>{this.getTeamNameFromId(game.awayTeamId)}</Row>
                  </Col>
                  <Col
                    xs={1}
                    md={1}
                    lg={1}
                    style={{ display: "flex", alignItems: "center" }}
                  >
                    {game.date}
                  </Col>
                </Row>
              </ListGroupItem>
            );
          })}
        </ListGroup>
      </div>
    );
  }
}

GameList.propTypes = {
  // Every game needs attributes homeTeamScore and awayTeamScore
  games: PropTypes.array.isRequired,
  teamsInSeason: PropTypes.array.isRequired
};
