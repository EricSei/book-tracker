import React from "react";
import NavBar from "./Navbar";
import {
  Button,
  Container,
  Divider,
  Grid,
  Header,
  Icon,
  Image,
  List,
  Menu,
  Segment,
  Sidebar,
  Visibility
} from 'semantic-ui-react'
import bookBackground from '../assets/books.jpg';


const Layout = ({ children }) => {
  return (
    <>
     <NavBar />
        {/* pages */}
      {children}
      <div style={{ margin: '10em' }}></div>
        <Segment inverted  style={{ padding: '5em 0em' }}>
                <Container>
                    <Grid divided inverted stackable>
                    <Grid.Row>
                        <Grid.Column width={3}>
                        <Header inverted as='h4' content='About' />
                        <List link inverted>
                            <List.Item as='a'>Sitemap</List.Item>
                            <List.Item as='a'>Contact Us</List.Item>
                           
                        </List>
                        </Grid.Column>
                        <Grid.Column width={3}>
                        <Header inverted as='h4' content='Services' />
                        <List link inverted>
                            <List.Item as='a'>Favorite Books</List.Item>
                            <List.Item as='a'>FAQ</List.Item>
                        
                        </List>
                        </Grid.Column>
                        <Grid.Column width={7}>
                        <Header as='h4' inverted>
                            Book Tracker
                        </Header>
                        <p>
                            Start reading your favorite books with Awesome Book Tracker 
                        </p>
                        </Grid.Column>
                    </Grid.Row>
                    </Grid>
                </Container>
                </Segment>
    </>
  );
};

export default Layout;