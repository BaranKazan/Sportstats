import axios from "axios";

/**
 * Retrieves longest lose streak for a team filtered between two dates from the API-database
 * @param {number} id
 * @param {string} from the date where filtering should start
 * @param {string} to the date where filtering should end
 * @param {Function} cb function to call if the operation is successfull
 */
export default async function WinStreakByTeam(id, from, to, cb) {
  axios
    .get(
      `/game/longest_losing_streak/team_id/${id}/start_date/${from}/end_date/${to}`
    )
    .then(res => {
      cb(res.data.length);
    })
    .catch(e => {
      console.log("Failed to retrieve losing streak for id: " + id);
    });
}
