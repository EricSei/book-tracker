import logo from './logo.svg';
import SignIn from './pages/SignIn';
import Navbar from './components/Navbar';
import React from 'react';
import AppRouter from "./Router";
import { UserProvider } from './UserContext';


const App = () => {
  return (
    <UserProvider>
       <AppRouter />
    </UserProvider>
  
  );
}

export default App;
