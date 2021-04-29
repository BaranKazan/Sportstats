import React, { Component } from "react";
import { TabContent, TabPane, Nav, NavItem, NavLink } from "reactstrap";
import classnames from "classnames";

/**
 * This component is used to show streaks for
 * a team. It includes longest win streak, tie
 * streak and lose streak. It uses the numbers
 * sent in as values to show.
 */
export default class StreakPane extends Component {
  state = {
    activeTab: "1"
  };

  toggleTab = id => {
    this.setState({ activeTab: id });
  };

  render() {
    return (
      <div>
        <div style={this.props.style}>
          <Nav tabs>
            <NavItem>
              <NavLink
                className={classnames({ active: this.state.activeTab === "1" })}
                onClick={() => {
                  this.toggleTab("1");
                }}
              >
                Longest winning streak
              </NavLink>
            </NavItem>
            <NavItem>
              <NavLink
                className={classnames({ active: this.state.activeTab === "2" })}
                onClick={() => {
                  this.toggleTab("2");
                }}
              >
                Longest tie streak
              </NavLink>
            </NavItem>
            <NavItem>
              <NavLink
                className={classnames({ active: this.state.activeTab === "3" })}
                onClick={() => {
                  this.toggleTab("3");
                }}
              >
                Longest losing streak
              </NavLink>
            </NavItem>
          </Nav>
        </div>
        <div style={this.props.style}>
          <TabContent activeTab={this.state.activeTab}>
            <TabPane tabId="1">
              <h4 style={this.props.style}>Longest winning streak:</h4>
              <h1 style={this.props.style}>{this.props.wins}</h1>
            </TabPane>
            <TabPane tabId="2">
              <h4 style={this.props.style}>Longest tie streak:</h4>
              <h1 style={this.props.style}>{this.props.ties}</h1>
            </TabPane>
            <TabPane tabId="3">
              <h4 style={this.props.style}>Longest losing streak:</h4>
              <h1 style={this.props.style}>{this.props.losses}</h1>
            </TabPane>
          </TabContent>
        </div>
      </div>
    );
  }
}
