import React from 'react'
import { Table } from 'semantic-ui-react'
import { rentalToSimpleRow } from '../mapper/rentalToSimpleRow'

const RentalSimpleTable = ({ rentals }) => {
    return (
        <Table celled selectable>
            <Table.Header>
                <Table.Row>
                    <Table.HeaderCell> From </Table.HeaderCell>
                    <Table.HeaderCell> Until </Table.HeaderCell>
                </Table.Row>
            </Table.Header>
            <Table.Body>
                {rentals.map(elem => {
                    return rentalToSimpleRow(elem)
                })}
            </Table.Body>
        </Table>
    )
}

export default RentalSimpleTable
