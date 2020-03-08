
export function middleware({ dispatch, getState }) {
  return function (next) {
    return function (action) {
      switch (action.type) {
        case "ADD_BUCKET":
          dispatch(addbucketToDB(action.payload.title));
          break;
        case "ADD_TODO":
          dispatch(addToDoToDB(action.payload.title, getState().selectedBucket));
          break;
        case "DELETE_BUCKET":
          dispatch(deletebucketFromDB(action.payload));
          break;
        case "DELETE_TODO":
          dispatch(deleteTodoFromDB(action.payload));
          break;
        case "SELECTED_BUCKET":
          dispatch({ type: "CHANGE_SELECTED_BUCKET", payload: action });
          break;
        case "UPDATE_STATUS":
          (action.payload2 === 0) ? action.payload2 = 1 : action.payload2 = 0;
          dispatch(updateTodoStatusToDB(action.payload1, action.payload2));
          break;
        default:
          break;
      };
      return next(action);
    };
  }
}
export function addbucketToDB(buckettitle) {
  return function (dispatch) {
    var addbucketurl = "http://localhost:8080/WebApplication2/webresources/todo.buckets/addbuckets/1/" + buckettitle;
    return fetch(addbucketurl, { method: 'GET', })
      .then(response => response.json())
      .then(json => {
        dispatch(refreshDataFromDB());
      });
  };
}

export function addToDoToDB(todotitle, bucketid) {
  return function (dispatch) {
    var addtodourl = "http://localhost:8080/WebApplication2/webresources/todo.todos/addTodo/" + bucketid + "/" + todotitle;
    return fetch(addtodourl, { method: 'GET', })
      .then(response => response.json())
      .then(json => {
        dispatch(refreshDataFromDB());
      });
  };
}

export function deletebucketFromDB(bucketid) {
  return function (dispatch) {
    var deletebucketurl = "http://localhost:8080/WebApplication2/webresources/todo.buckets/deleteBuckets/" + bucketid;
    return fetch(deletebucketurl, { method: 'DELETE', })
      .then(response => response.json())
      .then(json => {
        dispatch(refreshDataFromDB());
      });
  };
}

export function deleteTodoFromDB(todoid) {
  return function (dispatch) {
    var deletetodourl = "http://localhost:8080/WebApplication2/webresources/todo.todos/deleteTodo/" + todoid;
    return fetch(deletetodourl, { method: 'DELETE', })
      .then(response => response.json())
      .then(json => {
        dispatch(refreshDataFromDB());
      });
  };
}

export function updateTodoStatusToDB(todoid, status) {
  return function (dispatch) {
    var addtodourl = "http://localhost:8080/WebApplication2/webresources/todo.todos/updateTodo/" + todoid + "/" + status;
    return fetch(addtodourl, { method: 'PUT', })
      .then(response => response.json())
      .then(json => {
        dispatch(refreshDataFromDB());
      });
  };
}

export function refreshDataFromDB() {
  return function (dispatch) {
    return fetch("http://localhost:8080/WebApplication2/webresources/todo.users/1", { method: 'GET', })
      .then(response => response.json())
      .then(json => {
        dispatch({ type: "DATA_LOADED", payload: json });
      });
  };
}