import React from "react";
import { Table } from "reactstrap";

function Developers() {
  return (
    <div>
      <h2 class="lbl">Developers: </h2>
      <br />
      <Table>
        <thead>
          <tr>
            <th>First Name</th>
            <th>Last Name</th>
            <th>E-mail</th>
          </tr>
        </thead>
        <tbody>
          <tr>
            <td>Max</td>
            <td>Jonsson</td>
            <td>maxjonsson9@gmail.com</td>
          </tr>

          <tr>
            <td>Hassan</td>
            <td>Sheikha</td>
            <td>sheikha.hassan.rb@gmail.com</td>
          </tr>

          <tr>
            <td>Baran</td>
            <td>Kazan</td>
            <td>baran.kazan@hotmail.com</td>
          </tr>
        </tbody>
      </Table>
      <br />
      <footer>Students at the University Of Gävle, Sweden</footer>
    </div>
  );
}

export default Developers;
