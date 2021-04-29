import axios from "axios";

/**
 * Adds a team to the API-database
 * @param {string} name
 * @param {number} seasonId
 * @param {Function} callbackSuccess function to call if the operation is successfull
 * @param {Function} callbackFail function to call if the operation fails
 */
export default async function TeamAdd(name, seasonId, callbackSuccess, callbackFail) {
  axios
    .get(`/team/add/name/${name}/season_id/${seasonId}`)
    .then(res => {
      callbackSuccess(res);
    })
    .catch(e => {
      callbackFail(e);
    });
}
