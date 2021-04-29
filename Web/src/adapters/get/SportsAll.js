import axios from "axios";

/**
 * Retrieves all sports from the API-database
 * @param {Function} callback function to call if the operation is successfull
 */
export default async function SportGetAll(callback) {
  return axios
    .get("/sport/all")
    .then(res => callback(res.data))
    .catch(e => console.log("Could not fetch the data -> " + e));
}
