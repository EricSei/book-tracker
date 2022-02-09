import React, { useEffect, useState } from 'react';
import Navbar from '../components/Navbar';
import { Icon, Table } from 'semantic-ui-react'
import axios from "axios";


import Layout from '../components/Layout'; 
import BookPageId from './BookPageId';



const BookPage = () => {
    let [books, setBooks] = useState([]);
    //call api book list
    useEffect(()=>{
        getBooks();
    },[])

    const getBooks = (e) => {
        // e.preventDefault();
        axios({
          method: "get",
          url: "http://localhost:8080/reactmaven/BookServlet"
        })
          .then(function (response) {
            console.log(response.data);
            setBooks(response.data)
          })
          .catch(function (error) {
            console.log(error);
          });
      };

      const renderBooks = (books) => {
        if (Array.isArray(books) && books.length > 0) {
          return books.map((book) => {
            return (
                <Table.Body>
                <BookPageId
                  bookID={book.bookID}
                  title={book.title}
                  authorName={book.authorName} 
                  description={book.description}
                  publisher={book.publisher}
                //   getNotes={getNotes}
                //   setNotes={setNotes}
                />
               </Table.Body>
            );
          });
        } else {
          return <div> Start displaying books... </div>;
        }
      };
    return(
       <Layout>
        <h2>Book List page</h2>
           
        <Table celled>
            <Table.Header>
            <Table.Row>
               
            </Table.Row>
            </Table.Header>

            {renderBooks(books)}


           
            </Table>
    </Layout>
    )
}

export default BookPage;