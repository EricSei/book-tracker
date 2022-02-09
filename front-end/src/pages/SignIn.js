import axios from "axios";
import React, { useContext, useState } from "react";
import {
  Button,
  Form,
  Grid,
  Header,
  Image,
  Message,
  Segment,
} from "semantic-ui-react";
import Layout from "../components/Layout";
import { Link, useHistory } from "react-router-dom";
import UserContext from "../UserContext";

const SignIn = () => {
  const{user, setUser} = useContext(UserContext);
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");
  const history = useHistory();

  const login = (e) => {
    e.preventDefault();
    //comment out line 25 once we get data from real database
    // history.push("/user-dashboard-page")
    axios({
      method: "post",
      url: `http://localhost:8080/reactmaven/ReactServlet`,
      data: {
        username: username,
        password: password,
      },
    })
      .then(function (response) {
         setUser(response.data);
        history.push("/user-dashboard-page")
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  return  (
    <Layout>
      <Grid
        textAlign="center"
        style={{ height: "100vh" }}
        verticalAlign="middle"
      >
        <Grid.Column style={{ maxWidth: 450 }}>
          <Header as="h2" color="teal" textAlign="center">
            <Image src="/logo.png" /> Log-in to your account
          </Header>
          <Form size="large">
            <Segment stacked>
              <Form.Input
                fluid
                icon="user"
                iconPosition="left"
                placeholder="Username"
                onChange={(e) => {
                  setUsername(e.target.value);
                }}
              />
              <Form.Input
                fluid
                icon="lock"
                iconPosition="left"
                placeholder="Password"
                type="password"
                onChange={(e) => {
                  setPassword(e.target.value);
                }}
              />

              <Button color="teal" fluid size="large" onClick={login}>
                Login
              </Button>
            </Segment>
          </Form>
          <Message>
            New to us?   <Link to="/signup" > Sign Up</Link>
          </Message>
        </Grid.Column>
      </Grid>

      <Link />
    </Layout>
  );
};

export default SignIn;
