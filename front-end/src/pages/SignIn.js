import axios from 'axios';
import React, { useState } from 'react';
import Layout from '../components/Layout';

const SignIn = () => {

    const [title, setTitle ] = useState("");

     // Declare a new state variable, which we'll call "count"
  const [count, setCount] = useState(0);
  const [ username, setUserName ] = useState("");
  const [password, setPassword] = useState("");

  let data = {
    username: 'Sammy'
  }

  let fetchData = {
    method: 'POST',
    body: JSON.stringify(data),
    headers: new Headers({
      'Content-Type': 'application/json; charset=UTF-8'
    })
  }
    
    const addItem = (e) => {
        e.preventDefault();
        console.log(title);
        //api call 
    }

    const handleSubmitTest = (e) => {
        e.preventDefault();
        fetch("http://localhost:8080/BOOK-TRACKER-TEST/ReactServlet", fetchData)
        .then((response) => response.text())
        .then((text) => {
          console.log("here is the text from servlet: ", text);
        });
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        axios({
            method: "POST",
            url: "http://localhost:8080/BOOK-TRACKER-TEST/ReactServlet",
            headers: new Headers({
                'Content-Type': 'application/json',
                'Access-Control-Allow-Origin': '*'
              })
              ,
              data : { "eric" : "eric"}
        })
        .then( (response) => {
            console.log(response.data);
        })
        .catch(error => {
            console.log(error);
        })
    }



    return (
        <Layout>
       
          {/* <div>
            <p>You clicked {count} times</p>
            <button onClick={() => setCount(count + 1)}>
                Click me
            </button>
          </div> */}

        <form onSubmit={handleSubmit} class="ui form">
            <div >
            <label>Sign In</label>
            <input
                onChange={(e) => setUserName(e.target.value)}
                type="text"
                name="title"

                value={username}
            />
            <input
                onChange={(e) => setPassword(e.target.value)}
                type="password"
                name="passowrd"
                value={password}
            />
            </div>
            <button type="submit">
                submit
            </button>
        </form>
        </Layout>

       
    )
}

export default SignIn;