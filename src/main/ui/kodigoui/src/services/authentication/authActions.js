import { LOGIN_REQUEST, SUCCESS, FAILURE } from "./authTypes";

export const authenticateAdmin = (username, pin) => {
  return (dispatch) => {
    dispatch(loginRequest());
    if (username === "joss" && pin === "test") {
      dispatch(success());
    } else {
      dispatch(failure());
    }
  };
};

export const authenticateplayer = (username, pin) => {
  return (dispatch) => {
    dispatch(loginRequest());
    if (username === "joss" && pin === "2") {
      dispatch(success());
    } else {
      dispatch(failure());
    }
  };
};

const loginRequest = () => {
  return {
    type: LOGIN_REQUEST,
  };
};

const success = () => {
  return {
    type: SUCCESS,
    payload: true,
  };
};

const failure = () => {
  return {
    type: FAILURE,
    payload: false,
  };
};
