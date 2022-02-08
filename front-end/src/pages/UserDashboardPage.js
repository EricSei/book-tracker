import React, { useContext, useEffect, useState } from 'react';
import Navbar from '../components/Navbar';
import { Icon, Table } from 'semantic-ui-react'
import axios from "axios";


import Layout from '../components/Layout'; 
import BookPageId from './BookPageId';
import UserBookId from './UserBookId';
import UserContext from '../UserContext';

// staus : p, s, f
// rating: 0 -10
// p: planning to study,
// s: started
// f: finished
//need call userBook to update it

//get input for status
//userID, bookID, currentPage="dummy" currStatus, rating=3 to backend

const BookDashboardPage = () => {
    let [userbooks, setUserBooks] = useState([]);
    const {user, setUser} = useContext(UserContext);
    const {userID} = user;
    //call api book list
    useEffect(()=>{
        getUserBooks();
    },[])

    const getUserBooks = (e) => {
        // e.preventDefault();
        axios({
          method: "get",
          url: "http://localhost:3000/userBooks"
        })
          .then(function (response) {
            console.log(response.data);
            setUserBooks(response.data)
          })
          .catch(function (error) {
            console.log(error);
          });
      };

      const updateBookStatus = (userID, bookID, currStatus) =>{
        axios({
          method: "post",
          url: "http://localhost:3000/userBooks/1",
          data: {
            bookID : bookID,
            userID : userID,
            currStatus : currStatus,
            currentPage: 100,
            rating : 5
          }
        })
          .then(function (response) {
            console.log(response.data);
          })
          .catch(function (error) {
            console.log(error);
          });
      }

      const renderBooks = (books) => {
        if (Array.isArray(books) && books.length > 0) {
          return books.map((book) => {
            return (
             
                <UserBookId
                  userID ={book.userID}
                  bookID={book.bookID}
                  currStatus={book.currStatus}
                  rating={book.rating}
                  updateBookStatus={updateBookStatus}
                //   getNotes={getNotes}
                //   setNotes={setNotes}
                />
            
            );
          });
        } else {
          return <div> Start displaying books... </div>;
        }
      };
    return(
       <Layout>
        <h2 inverted> Your Favorite Reading Books...</h2>
        <Table celled>
          <Table.Header>
                <Table.Row>
                  <Table.HeaderCell>User Id</Table.HeaderCell>
                  <Table.HeaderCell>Book Id</Table.HeaderCell>
                  <Table.HeaderCell>Reading status</Table.HeaderCell>
                  <Table.HeaderCell>book rating</Table.HeaderCell>
                  <Table.HeaderCell>Update Progress</Table.HeaderCell>
                </Table.Row>
              </Table.Header>
              <Table.Body>
              {renderBooks(userbooks)}
              </Table.Body>
            </Table>
    </Layout>
    )
}

export default BookDashboardPage;