import axios from "axios";

/**
 * Retrieves all goals by game id from the API-database
 * @param {number} id
 * @param {function} cb function to call if the operation is successfull
 */
export default async function GoalsGetByGame(id, cb) {
  axios
    .get(`/goal/game_id/${id}`)
    .then(res => {
      cb(res.data);
    })
    .catch(e => {
      console.log("Failed to retrieve goals: " + e);
    });
}
