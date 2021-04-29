import axios from "axios";

/**
 * Retrieves all leagues by sport id from the API-database
 * @param {number} id
 * @param {function} cb function to call if the operation is successfull
 */
export default async function LeagueGetBySport(id, cb) {
  axios
    .get(`/league/sport_id/${id}`)
    .then(res => {
      cb(res.data);
    })
    .catch(e => {
      console.log("Failed to add league: " + id);
    });
}
