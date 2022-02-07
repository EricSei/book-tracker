import React from 'react';
import { Icon, Table } from 'semantic-ui-react'
const BookPageId = (props) =>{
    const { bookID, title ,authorName, publisher, description  } = props;
    return(
     
           <Table.Row>
                <Table.Cell>{bookID}</Table.Cell>
                <Table.Cell>{title}</Table.Cell>
                <Table.Cell negative>{authorName}</Table.Cell>
                <Table.Cell>{publisher}</Table.Cell>
                <Table.Cell>{description}</Table.Cell>
                <Table.Cell>
                    <Icon size="big"  name='plus' />
                </Table.Cell>
            </Table.Row>
    )
}

export default BookPageId;

// "bookID" : "3",
// "title": "title 2",
// "authorName": " author 2",
// "publisher": "publisher2",
// "description": "description 3"