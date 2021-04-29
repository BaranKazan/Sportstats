import axios from "axios";

/**
 * Adds a sport to the API-database
 * @param {string} name
 * @param {number} periods
 * @param {number} minsPeriod
 * @param {Function} callbackSuccess function to call if the operation is successfull
 * @param {Function} callbackFail function to call if the operation fails
 */
export default async function SportAdd(
  name,
  periods,
  minsPeriod,
  callbackSuccess,
  callbackFail
) {
  axios
    .get(
      `/sport/add/name/${name}/number_of_periods/${periods}/minutes_per_period/${minsPeriod}`
    )
    .then(res => {
      callbackSuccess(res);
    })
    .catch(e => {
      callbackFail(e);
    });
}
