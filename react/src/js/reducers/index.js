

const initialState = {
  buckets: [],
  selectedBucket: 1,
};

function rootReducer(state = initialState, action) {
  switch (action.type) {
    case "DATA_LOADED":
      return Object.assign({}, state, {
        buckets: action.payload
      });
    case "CHANGE_SELECTED_BUCKET":
      return Object.assign({}, state, {
        selectedBucket: action.payload.payload
      });
    default:
      break;
  }
  return state;

}

export default rootReducer;