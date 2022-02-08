import React, { useContext } from "react";
import { Route, Redirect } from "react-router-dom";
import UserContext from "../UserContext";


const PrivateRoute = ({ component: Component, ...rest }) =>{
  const {user, SetUser} = useContext(UserContext);
  return (
    <Route
      {...rest}
      render={(props) =>
        user ? (
          <Component {...props} />
        ) : (
          <Redirect
            to={{
              pathname: "/signin",
              state: { from: props.location },
            }}
          />
        )
      }
    ></Route>
  )
} 

export default PrivateRoute;