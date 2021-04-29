import React from "react";
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome";
import { faAlignLeft } from "@fortawesome/free-solid-svg-icons";
import { Navbar, Button, NavbarBrand } from "reactstrap";

export default props => {
  function titleToggle() {
    var x = document.getElementById("navbarTitle");
    x.style.display = "block";
    props.toggle();
  }

  return (
    <Navbar
      color="light"
      light
      className="navbar shadow-sm p-3 mb-5 bg-white rounded"
      expand="md"
      sticky={"top"}
    >
      <Button color="success" onClick={titleToggle}>
        <FontAwesomeIcon icon={faAlignLeft} />
      </Button>
      <NavbarBrand id="navbarTitle" style={{ padding: "0rem 0rem 0rem .5rem" }}>
        Sportstats{" "}
      </NavbarBrand>
    </Navbar>
  );
};
