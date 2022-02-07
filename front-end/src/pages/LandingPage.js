import React from 'react';
import { useState, useEffect } from 'react';
import {Link} from 'react-router-dom';
import Layout from '../components/Layout';
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
    Visibility,
  } from 'semantic-ui-react'
  import bookBackground from '../assets/books.jpg';

const LandingPage = (pros) => {
  
    // callApi --> setState --> State --> data used By JSX

    //[initialState, setState ]
    const [books, setBooks] = useState(['boo1', 'book2']);

    const [userId, setUseId] = useState(null); //{username, email, }

    //call api
    useEffect(() => {
        // make api call here
        //get books list
      }, []);


    //call books api 
    const getBooks = () => {

        return []
    }

    return (
            <Layout>
                <Segment style={{ padding: '8em 0em' }} vertical>
                <Grid container stackable verticalAlign='middle'>
                    <Grid.Row>
                    <Grid.Column width={8}>
                        <Header as='h3' style={{ fontSize: '2em' }}>
                        Super Awesome Book Tracker 
                        </Header>
                        <p style={{ fontSize: '1.33em' }}>
                            Track You favorite books as you read them ...
                        </p>
                        <Header as='h3' style={{ fontSize: '2em' }}>
                        We Make Bananas That Can Dance
                        </Header>
                        <p style={{ fontSize: '1.33em' }}>
                        Yes that's right, you thought it was the stuff of dreams, but even bananas can be
                        bioengineered.
                        </p>
                    </Grid.Column>
                    <Grid.Column floated='right' width={6}>
                        <Image bordered rounded size='large' src={bookBackground} />
                    </Grid.Column>
                    </Grid.Row>
                    <Grid.Row>
                    <Grid.Column textAlign='center'>
                        <Button as={Link} to="/BookPage" color="teal">
                            Check Them Out
                        </Button>
                        
                    </Grid.Column>
                    </Grid.Row>
                </Grid>
                </Segment>

               

                <Segment style={{ padding: '8em 0em' }} vertical>
                <Container text>
                    <Header as='h3' style={{ fontSize: '2em' }}>
                        About Us
                    </Header>
                    <p style={{ fontSize: '1.33em' }}>
                        We are a team of 6 dedicated JUMP software engineers.

                    </p>
                    <Button as='a' size='large'>
                        Read More
                    </Button>

                </Container>
                </Segment>

               
            </Layout>
        
    );
};
  
export default LandingPage;
 