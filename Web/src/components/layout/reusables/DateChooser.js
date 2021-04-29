import React from "react";
import {
  Button,
  UncontrolledPopover,
  PopoverHeader,
  PopoverBody,
  Row,
  Col
} from "reactstrap";
import PropTypes from "prop-types";

/**
 * The component used to add a date chooser with
 * ready-to-use functionality pre build within it.
 * On the screen it appears as a button that when
 * clicked shows a popover where dates can be selected.
 */
export default class DateChooser extends React.Component {
  state = {
    popoverOpen: false,
    from: "",
    to: ""
  };

  setFromDate = e => {
    this.setState({ from: e.target.value });
  };

  setToDate = e => {
    this.setState({ to: e.target.value });
  };

  handleClick = () => {
    this.toggle();
    this.props.onFilter(this.state.from, this.state.to);
  };

  toggle = () => this.setState({ popoverOpen: !this.state.popoverOpen });

  render() {
    return (
      <div>
        <Button id="UncontrolledPopover" type="button">
          Filter on dates
        </Button>
        <UncontrolledPopover
          style={{ padding: "10px" }}
          placement="bottom"
          target="UncontrolledPopover"
          isOpen={this.state.popoverOpen}
          toggle={this.toggle}
        >
          <PopoverHeader>Input dates</PopoverHeader>
          <PopoverBody>
            <Row>
              <Col>
                <Row>
                  <label>From:</label>
                </Row>
                <Row>
                  <label>To:</label>
                </Row>
              </Col>
              <Col>
                <Row>
                  <input
                    type="date"
                    value={this.state.from}
                    onChange={this.setFromDate.bind(this)}
                    style={inputStyle}
                  ></input>
                </Row>
                <Row>
                  <input
                    type="date"
                    value={this.state.to}
                    onChange={this.setToDate.bind(this)}
                    style={inputStyle}
                  ></input>
                </Row>
              </Col>
            </Row>
            <div style={buttonStyle}>
              <Button onClick={this.handleClick}>Filter</Button>
            </div>
          </PopoverBody>
        </UncontrolledPopover>
      </div>
    );
  }
}

const buttonStyle = {
  display: "flex",
  alignItems: "flex-end",
  justifyContent: "flex-end"
};

const inputStyle = {
  margin: "0px 0px 5px 10px"
};

DateChooser.propTypes = {
  onFilter: PropTypes.func.isRequired
};
