import axios from "axios";

/**
 * Retrieves the number of goals for home team and away team in a game
 * @param {number} id
 * @param {number} homeId
 * @param {number} awayId
 * @param {Function} cb the function to call if the operation is successfull
 */
export default async function GoalsGetByGame(id, homeId, awayId, cb) {
  axios
    .get(`/goal/game_id/${id}`)
    .then(res => {
      cb(
        res.data.filter(goal => goal.teamId === homeId).length,
        res.data.filter(goal => goal.teamId === awayId).length
      );
    })
    .catch(e => {
      console.log("Failed to retrieve goals: " + e);
    });
}
