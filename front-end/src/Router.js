import React from "react";
import {
  BrowserRouter as Router,
  Switch,
  Route,
  Link
} from "react-router-dom";
import BookPage from "./pages/BookPage"
import LandingPage from "./pages/LandingPage";
import UserDashboardPage from "./pages/UserDashboardPage";
import SignIn from "./pages/SignIn";
import SignUp from "./pages/SignUp";
import SignOut from "./pages/SignOutPage";



const AppRouter = () => {
  return (
    <Router>
      <Switch>
        {/* Public Routes */}
        <Route exact path="/" component={LandingPage} />
        <Route exact path="/signin" component={SignIn} />
        <Route exact path="/signup" component={SignUp} />

        {/* Private Routes */}
        <Route exact path="/user-dashboard-page" component={UserDashboardPage} />
        <Route exact path="/BookPage" component={BookPage} />
        <Route exact path="/signout" component={SignOut} />
       
      </Switch>
    </Router>
  );
}

export default AppRouter;