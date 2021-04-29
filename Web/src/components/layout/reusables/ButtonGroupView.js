import React, { Component } from "react";
import { ButtonGroup, Button } from "reactstrap";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

/**
 * This component is used to list diffferent contents
 * where two different routings are possible. It defines
 * a center label and two side buttons that redirects
 * to a different sub page.
 */
export default class ButtonGroupView extends Component {
  render() {
    return (
      <div style={{ textAlign: "center" }}>
        {this.props.items.map(item => (
          <div>
            <ButtonGroup size="sm" className="mt-3">
              <Button
                style={{ display: "flex", alignItems: "center" }}
                tag={Link}
                to={item.pathRight}
                className="btn-info"
              >
                {this.props.titleRight}
              </Button>
              <Button
                disabled
                style={{ display: "flex", alignItems: "center" }}
              >
                {item.text}
              </Button>
              <Button
                style={{ display: "flex", alignItems: "center" }}
                tag={Link}
                to={item.pathLeft}
                className="btn-info"
              >
                {this.props.titleLeft}
              </Button>
            </ButtonGroup>
            <br />
          </div>
        ))}
      </div>
    );
  }
}

ButtonGroupView.propTypes = {
  // OBS! Items must be objects containing "text" and "pathLeft" and "pathRight" properties
  items: PropTypes.array.isRequired
};
