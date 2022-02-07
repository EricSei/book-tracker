import React from 'react';
import { useState, useEffect } from 'react';
import Layout from '../components/Layout';

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


    //

    return (
            <Layout>
                <div>
                    Landing Page
                </div>

            </Layout>
        
    );
};
  
export default LandingPage;
 