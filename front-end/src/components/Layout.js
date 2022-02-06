import React from "react";
import NavBar from "./Navbar";


const Layout = ({ children }) => {
  return (
    <>
     <NavBar />
     {/* pages */}
      {children}
    </>
  );
};

export default Layout;