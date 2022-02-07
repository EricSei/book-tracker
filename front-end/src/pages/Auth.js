import React from 'react';
import SignIn from './SignIn';
import UserDashboardPage from './UserDashboardPage';

const Auth = (props) => {
    const isLoggedIn = props.isLoggedIn;
    if (isLoggedIn) {
      return <UserDashboardPage />;
    }
    return <SignIn />;
  }

  export default Auth;