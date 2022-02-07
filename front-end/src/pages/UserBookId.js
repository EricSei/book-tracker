import React from 'react';
import { Icon, Table } from 'semantic-ui-react'
const UserBookId = (props) =>{
    const { userID, bookID, currStatus ,rating } = props;
    return(
     
           <Table.Row>
                <Table.Cell>{bookID}</Table.Cell>
                <Table.Cell>{userID}</Table.Cell>
                {
                    currStatus === "p" &&
                    <Table.Cell negative> Planning to read </Table.Cell>
                }
                 {
                    currStatus === "s" &&
                    <Table.Cell negative> Started reading </Table.Cell>
                }
                 {
                    currStatus === "f" &&
                    <Table.Cell negative> Completed </Table.Cell>
                }
                
                <Table.Cell>{rating}</Table.Cell>
               
            </Table.Row>
    )
}

export default UserBookId;

// "bookID" : "3",
// "title": "title 2",
// "authorName": " author 2",
// "publisher": "publisher2",
// "description": "description 3"