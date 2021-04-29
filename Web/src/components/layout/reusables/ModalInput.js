import React, { Component } from "react";
import {
  Modal,
  ModalBody,
  ModalFooter,
  Form,
  FormGroup,
  ModalHeader,
  Button,
  Label,
  Alert
} from "reactstrap";

/**
 * The component used to show a modal with input
 * fields. This can be used to show a modal that
 * for example can be used to add different types
 * of data to the database.
 */
export default class ModalInput extends Component {
  render() {
    return (
      <div>
        <Modal isOpen={this.props.modal}>
          <ModalHeader toggle={this.props.hideModal}>
            {this.props.header}
          </ModalHeader>

          <Form onSubmit={this.props.addType}>
            <ModalBody>
              <FormGroup>
                <Label>{this.props.labelForInputOne}</Label>
                {this.props.inputOne}
              </FormGroup>
              <FormGroup>
                <Label>{this.props.labelForInputTwo}</Label>
                {this.props.inputTwo}
              </FormGroup>
              <FormGroup>
                <Label>{this.props.labelForInputThree}</Label>
                {this.props.inputThree}
              </FormGroup>
            </ModalBody>
            <ModalFooter>
              <Button color="primary" type="submit">
                Add
              </Button>
            </ModalFooter>
          </Form>
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
          toggle={this.onAlertDismiss}
        >
          {this.props.alertMsg}
        </Alert>
        <Button
          onClick={this.props.showModal}
          className="btn-success"
          style={{ marginBottom: "20px" }}
        >
          {this.props.buttonText}
        </Button>
      </div>
    );
  }
}
