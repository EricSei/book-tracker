import React, { useState } from "react";
import { Image, Menu, Sidebar, Responsive } from "semantic-ui-react";
import { Link } from "react-router-dom";

const NavBar = () => {
  
  const [activeItem, setActiveItem] = useState(null);

  const handleItemClick = (e, { name }) => {
    setActiveItem(name);
  };
  return (
    <Menu>
      <Menu.Item fixed="top" color="teal" sticky inverted> Book Tracker App </Menu.Item>
      <Menu.Item
        as={Link}
        to={"/user-dashboard-page"}
        name="User Dashboard"
        active={activeItem === "user-dashboard-page"}
        onClick={handleItemClick}
      />
      <Menu.Item
        as={Link}
        to={"/"}
        name="Landing page"
        active={activeItem === "Landing page"}
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
  );
};

export default NavBar;