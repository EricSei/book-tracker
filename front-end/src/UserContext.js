import React, { useEffect, useState } from 'react';

const UserContext = React.createContext();

export const UserProvider = ({ children }) => {
    const [user, setUser] = useState({
      userID: 1,
      username: "pasang",
      password: "password"
    });
  
    return (
      <UserContext.Provider value={{ user, setUser}}>
        {children}
      </UserContext.Provider>
    );
  };
  
  export default UserContext;