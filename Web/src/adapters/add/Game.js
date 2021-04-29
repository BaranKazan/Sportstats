import axios from "axios";

/**
 * Adds a game to the API-database
 * @param {number} spectators
 * @param {string} date
 * @param {number} roundId
 * @param {number} homeTeamId
 * @param {number} awayTeamId
 * @param {Function} callbackSuccess function to call if the operation is successfull
 * @param {Function} callbackFail function to call if the operation fails
 */
export default async function GameAdd(
  spectators,
  date,
  roundId,
  homeTeamId,
  awayTeamId,
  callbackSuccess,
  callbackFail
) {
  axios
    .get(
      `/game/add/date/${date}/round_id/${roundId}/home_team_id/${homeTeamId}/away_team_id/${awayTeamId}/arena_id/1/spectators/${spectators}`
    )
    .then(res => {
      callbackSuccess(res);
    })
    .catch(e => {
      callbackFail(e);
    });
}
