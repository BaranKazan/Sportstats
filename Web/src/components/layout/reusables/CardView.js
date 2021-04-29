import React, { Component } from "react";
import { Card, Button, CardText, CardHeader } from "reactstrap";
import PropTypes from "prop-types";
import { Link } from "react-router-dom";

/**
 * The component used to list different items
 * to make them appear in cards with simular
 * stylings on several different sub pages.
 */
export default class CardView extends Component {
  render() {
    return (
      <div className="container">
        <div className="row">
          {this.props.items.map(item => (
            <div className="col-lg-4">
              <div className="p-1">
                <Card body>
                  <CardHeader>{item.title}</CardHeader>
                  <CardText>{item.text}</CardText>
                  <Button tag={Link} to={item.path} className="btn-info">
                    Choose
                  </Button>
                </Card>
              </div>
            </div>
          ))}
        </div>
      </div>
    );
  }
}

CardView.propTypes = {
  // OBS! Items must be objects containing "title", "text" and "path" properties
  items: PropTypes.array.isRequired
};
