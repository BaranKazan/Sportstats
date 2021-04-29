import axios from "axios";

/**
 * Retrieves all rounds by season id from the API-database
 * @param {number} id
 * @param {function} cb function to call if the operation is successfull
 */
export default async function RoundGetBySeason(id, cb) {
  axios
    .get(`/round/season_id/${id}`)
    .then(res => {
      cb(res.data);
    })
    .catch(e => {
      console.log("Failed to add season: " + id);
    });
}
