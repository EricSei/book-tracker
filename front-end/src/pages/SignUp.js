import axios from "axios";
import React, { useState } from "react";
import {
  Button,
  Form,
  Grid,
  Header,
  Image,
  Message,
  Segment,
  Icon
} from "semantic-ui-react";
import Layout from "../components/Layout";
import { Link } from "react-router-dom";
import profile from "../assets/profile.jpg"

const SignUp = () => {
  const [username, setUsername] = useState("");
  const [password, setPassword] = useState("");

  const login = (e) => {
    e.preventDefault();
    axios({
      method: "post",
      url: "http://localhost:8080/reactmaven/ReactServlet",
      data: {
        username: username,
        password: password,
      },
    })
      .then(function (response) {
        console.log(response.data);
      })
      .catch(function (error) {
        console.log(error);
      });
  };

  return (
    <Layout>
      <Grid
        textAlign="center"
        style={{ height: "100vh" }}
        verticalAlign="middle"
      >
        <Grid.Column style={{ maxWidth: 450 }}>
          <Header as="h2" color="teal" textAlign="center">
            
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
                Sign Up
              </Button>
            </Segment>
          </Form>
          <Message>
            Already Created ? 
            <Link to="/signin" > Sign In </Link>
          </Message>
        </Grid.Column>
      </Grid>

      <Link />
    </Layout>
  );
};

export default SignUp;
