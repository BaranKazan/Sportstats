import axios from "axios";

/**
 * Retrieves games with largest wins by team id filtered between two dates from the API-database
 * @param {number} id
 * @param {string} from the date where filtering should start
 * @param {string} to the date where filtering should end
 * @param {Function} cb function to call if the operation is successfull
 */
export default async function GamesLargestWins(id, from, to, cb) {
  axios
    .get(`/game/largest_win/team_id/${id}/start_date/${from}/end_date/${to}`)
    .then(res => {
      cb(res.data);
    })
    .catch(e => {
      console.log("Failed to retrieve games: " + e);
    });
}
