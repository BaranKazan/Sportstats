import axios from "axios";

/**
 * Adds a season to the API-database
 * @param {number} leagueId
 * @param {number} startYear
 * @param {number} endYear
 * @param {number} numberOfRounds
 * @param {Function} callbackSuccess function to call if the operation is successfull
 * @param {Funtion} callbackFail function to call if the operation fails
 */
export default async function Season(
  leagueId,
  startYear,
  endYear,
  numberOfRounds,
  callbackSuccess,
  callbackFail
) {
  axios
    .get(
      `/season/add/league_id/${leagueId}/number_of_rounds/${numberOfRounds}/start_year/${startYear}/end_year/${endYear}`
    )
    .then(res => {
      callbackSuccess(res);
    })
    .catch(e => {
      callbackFail(e);
    });
}
