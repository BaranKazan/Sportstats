import axios from "axios";

/**
 * Retrieves all games by round id from the API-database
 * @param {number} id
 * @param {function} cb function to call if the operation is successfull
 */
export default async function GamesByRound(id, cb) {
  axios
    .get(`/game/round_id/${id}`)
    .then(res => {
      cb(res.data);
    })
    .catch(e => {
      console.log("Failed to add season: " + id);
    });
}
