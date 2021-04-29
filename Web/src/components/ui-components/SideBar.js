import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import {
  faFutbol,
  faPlus,
  faAt,
  faBasketballBall
} from "@fortawesome/free-solid-svg-icons";
import { NavItem, NavLink, Nav } from "reactstrap";
import classNames from "classnames";
import { Link } from "react-router-dom";

const SideBar = props => (
  <div className={classNames("sidebar", { "is-open": props.isOpen })}>
    <div className="sidebar-header">
      <span color="info" onClick={props.toggle} style={{ color: "#fff" }}>
        &times;
      </span>

      <h3>
        Sp
        <FontAwesomeIcon icon={faBasketballBall} />
        rtstats
      </h3>
    </div>
    <div className="side-menu">
      <Nav vertical className="list-unstyled pb-3" sticky="true">
        <NavItem>
          <NavLink tag={Link} to={"/Sports"}>
            <FontAwesomeIcon icon={faFutbol} className="mr-2" />
            All Sports
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink tag={Link} to={"/SportAdd"}>
            <FontAwesomeIcon icon={faPlus} className="mr-2" />
            Add Sport
          </NavLink>
        </NavItem>
        <NavItem>
          <NavLink tag={Link} to={"/Developers"}>
            <FontAwesomeIcon icon={faAt} className="mr-2" />
            Developers
          </NavLink>
        </NavItem>
      </Nav>
    </div>
  </div>
);

export default SideBar;
