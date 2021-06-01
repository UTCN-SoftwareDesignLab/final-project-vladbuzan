import React from 'react'
import { Table } from 'semantic-ui-react'
import { rentalToRow } from '../mapper/rentalToRow'

const RentalTable = ({ rentals, onClick }) => {
    return (
        <Table celled selectable>
            <Table.Header>
                <Table.Row>
                    <Table.HeaderCell> Customer </Table.HeaderCell>
                    <Table.HeaderCell> From </Table.HeaderCell>
                    <Table.HeaderCell> Until </Table.HeaderCell>
                    <Table.HeaderCell> Car </Table.HeaderCell>
                    <Table.HeaderCell> Status </Table.HeaderCell>
                </Table.Row>
            </Table.Header>
            <Table.Body>
                {rentals.map(elem => {
                    return rentalToRow(elem, onClick)
                })}
            </Table.Body>
        </Table>
    )
}

export default RentalTable
