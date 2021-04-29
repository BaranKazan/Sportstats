import axios from "axios";

/**
 * Retrieves all games by team id from the API-database
 * @param {number} id
 * @param {function} cb function to call if the operation is successfull
 */
export default async function GamesGetByTeam(id, cb) {
  axios
    .get(`/game/team_id/${id}`)
    .then(res => {
      cb(res.data);
    })
    .catch(e => {
      console.log("Failed to retrieve games: " + e);
    });
}
