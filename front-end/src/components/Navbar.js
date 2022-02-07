import React, { useContext, useState } from "react";
import { Image, Menu, Sidebar, Responsive,Segment, Input, Icon } from "semantic-ui-react";
import { Link } from "react-router-dom";
import UserContext from "../UserContext";

const NavBar = () => {
  const {user, setUser} = useContext(UserContext);
  const [activeItem, setActiveItem] = useState(null);
  // const [user, setUser] = useState("eric");

  const handleItemClick = (e, { name }) => {
    setActiveItem(name);
  };

  const handleSignOut = (e, {name}) =>{
    setActiveItem(name);
    setUser(null);
  }

  if(!user){
    
    return(
    <Segment
    inverted
    textAlign='center'
    style={{ minHeight: 50, padding: '1em 0em' }}
    vertical
  >
    <Menu inverted >
      <Menu.Item
        
        as={Link} 
        to={"/"}
        active={activeItem === "Book Tracker App"}
        fixed="top" color="teal" sticky inverted> Book Tracker App </Menu.Item>
      

      <Menu.Menu position='right'>
      <Menu.Item
            as={Link}
            to={"/signup"}
            name='signup'
            active={activeItem === 'signup'}
            onClick={handleItemClick}
          />
          <Menu.Item
            as={Link}
            to={"/signin"}
            name='signin'
            active={activeItem === 'signin'}
            onClick={handleItemClick}
          />
      </Menu.Menu>
      
    </Menu>
    </Segment>)
  }

  return (
    <Segment
    inverted
    textAlign='center'
    style={{ minHeight: 50, padding: '1em 0em' }}
    vertical
  >
    <Menu inverted >
      <Menu.Item
        
        as={Link} 
        to={"/"}
        active={activeItem === "Book Tracker App"}
        fixed="top" color="teal" sticky inverted> Book Tracker App </Menu.Item>
      <Menu.Item
        as={Link}
        to={"/user-dashboard-page"}
        name="User Dashboard"
        active={activeItem === "user-dashboard-page"}
        onClick={handleItemClick}
      />
      <Menu.Item
        as={Link}
        to={"/BookPage"}
        name="BookPage"
        active={activeItem === "BookPage"}
        onClick={handleItemClick}
      />

        <Menu.Menu position='right'>
          <Icon.Group size='huge'>
              {/* <Icon size='small' name='circle outline' /> */}
              <Icon name='user' />
            </Icon.Group>
          <Menu.Item
            as={Link}
            to={"/signout"}
            name='signout'
            active={activeItem === 'signout'}
            onClick={handleSignOut}
          />
        </Menu.Menu>
    
      
    </Menu>
    </Segment>
  );

};

export default NavBar;