import React from "react";
import { render } from "react-dom";
import { Provider } from "react-redux";
import store from "./js/store/index";
import App from "./js/components/App";


function getUserBuckets(){
	
	const suc= fetch("http://localhost:8080/JPASamples/webresources/test.todoapp.buckets/1",{method: 'GET'})
      .then(res => res.json())
      .then(
        (result) => {
			return (<tr key='1'>
               <td>11111111111111</td>
               <td>2</td>
               <td>3</td>
            </tr>);					
        },
        (error) => {
        return error;
        }
      )
console.log(suc);
}



function Test() {
  return (
    <Provider store={store}>
    <App />
  </Provider>  
  )
}



export default App;
