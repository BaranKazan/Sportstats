import axios from "axios";

/**
 * Adds a round to the API-database
 * @param {number} seasonId
 * @param {number} roundNumber
 * @param {Function} callbackSuccess function to call if the operation is successfull
 * @param {Function} callbackFail function to call if the operation fails
 */
export default async function RoundGamesAdd(
  seasonId,
  roundNumber,
  callbackSuccess,
  callbackFail
) {
  axios
    .get(`/round/add/season_id/${seasonId}/round_number/${roundNumber}`)
    .then(res => {
      callbackSuccess(res);
    })
    .catch(e => {
      callbackFail(e);
    });
}
