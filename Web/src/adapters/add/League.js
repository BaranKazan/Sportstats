import axios from "axios";

/**
 * Adds a league to the API-database
 * @param {string} name
 * @param {number} sportId
 * @param {Function} callbackSuccess function to call if the operation is successfull
 * @param {Function} callbackFail function to call if the operation fails
 */
export default async function LeagueAdd(
  name,
  sportId,
  callbackSuccess,
  callbackFail
) {
  axios
    .get(`/league/add/name/${name}/sport_id/${sportId}`)
    .then(res => {
      callbackSuccess(res);
    })
    .catch(e => {
      callbackFail(e);
    });
}
