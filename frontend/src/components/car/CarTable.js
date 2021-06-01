import React, { useEffect } from 'react'
import { Table } from 'semantic-ui-react'
import { carToRow } from '../mapper/carToRow'

const CarTable = ({ cars, button }) => {

    useEffect(() => {
    }, [cars])

    return (
        <Table celled selectable>
            <Table.Header>
                <Table.Row>
                    <Table.HeaderCell>Action</Table.HeaderCell>
                    <Table.HeaderCell>Manufacturer</Table.HeaderCell>
                    <Table.HeaderCell>Model</Table.HeaderCell>
                    <Table.HeaderCell>Description</Table.HeaderCell>
                </Table.Row>
            </Table.Header>
            <Table.Body>
                {cars.map((elem) => {
                    return (carToRow(elem, button))
                })}
            </Table.Body>
        </Table>
    )
}

export default CarTable
