import React from 'react';
import { Icon, Table, Dropdown, Button } from 'semantic-ui-react'
const UserBookId = (props) =>{
    const { userID, bookID, currStatus ,rating, updateBookStatus } = props;

    const handleOnClick = (updateStatus) =>{
        console.log(updateStatus);
        updateBookStatus( userID, bookID, updateStatus );
    }

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
                
                <Table.Cell >{rating}</Table.Cell>
                <Table.Cell >
                <Button inverted color='red' onClick={() => handleOnClick("p") }>
                    <Icon name="paper plane" />
                    To Read
                </Button>
                <Button inverted color='olive'  onClick={() => handleOnClick("s")}>
                 <Icon name="hourglass two" />
                    Reading
                </Button>
                <Button inverted color='green'  onClick={() => handleOnClick("f")}>
                    <Icon name="chess queen" />
                   Completed
                </Button>
                   
                </Table.Cell>
               
            </Table.Row>
    )
}

export default UserBookId;

// "bookID" : "3",
// "title": "title 2",
// "authorName": " author 2",
// "publisher": "publisher2",
// "description": "description 3"