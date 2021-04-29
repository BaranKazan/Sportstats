import axios from "axios";

/**
 * Retrieves all seasons by league id from the API-database
 * @param {number} id
 * @param {function} cb function to call if the operation is successfull
 */
export default async function SeasonByLeague(id, cb) {
  axios
    .get(`/season/league_id/${id}`)
    .then(res => {
      cb(res.data);
    })
    .catch(e => {
      console.log("Failed to add season: " + id);
    });
}
