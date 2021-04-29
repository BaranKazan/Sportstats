import React, { Component } from "react";
import { Modal, ModalFooter, ModalHeader, Button, Alert } from "reactstrap";

/**
 * This modal can be used to show simple text.
 * For example, it can be used as a confirmation
 * modal to show up before data is added to the
 * data store.
 */
export default class ModalText extends Component {
  render() {
    return (
      <div>
        <Modal isOpen={this.props.modal}>
          <ModalHeader toggle={this.props.hideModal}>Confirmation</ModalHeader>
          {this.props.modalBody}
          <ModalFooter>
            <Button color="primary" onClick={this.props.addSport}>
              Add
            </Button>
          </ModalFooter>
        </Modal>
        <Alert
          color="success"
          isOpen={this.props.alertSuccess}
          fade={true}
          toggle={this.props.onAlertDismiss}
        >
          {this.props.alertMsg}
        </Alert>
        <Alert
          color="danger"
          isOpen={this.props.alertError}
          fade={true}
          toggle={this.props.onAlertDismiss}
        >
          {this.props.alertMsg}
        </Alert>
      </div>
    );
  }
}
