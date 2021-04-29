import axios from "axios";

/**
 * Adds a goal to the API-database
 * @param {number} gameId
 * @param {number} teamId
 * @param {number} period
 * @param {number} time
 * @param {boolean} isPenalty
 * @param {boolean} isOvertime
 * @param {Function} cb function to call if the operation is successfull
 */
export default async function GoalAdd(
  gameId,
  teamId,
  period,
  time,
  isPenalty,
  isOvertime,
  cb
) {
  axios
    .get(
      `/goal/add/is_penalty/${isPenalty}/is_overtime/${isOvertime}/time_elapsed_in_minutes/${time}/current_period/${period}/game_id/${gameId}/team_id/${teamId}`
    )
    .then(res => {
      cb(res.data);
    })
    .catch(e => {
      console.log("Failed to add goal: " + e);
    });
}
