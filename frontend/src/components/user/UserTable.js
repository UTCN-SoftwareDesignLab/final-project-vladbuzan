import React, { useEffect } from 'react'
import { Table } from 'semantic-ui-react'
import { userToRow } from '../mapper/userToRow'

const UserTable = ({ users, button }) => {

    useEffect(() => {
    }, [users])

    return (
        <Table celled selectable>
            <Table.Header>
                <Table.Row>
                    <Table.HeaderCell />
                    <Table.HeaderCell>Username</Table.HeaderCell>
                    <Table.HeaderCell>Email</Table.HeaderCell>
                    <Table.HeaderCell>Firstname</Table.HeaderCell>
                    <Table.HeaderCell>Lastname</Table.HeaderCell>
                    <Table.HeaderCell>Role</Table.HeaderCell>
                </Table.Row>
            </Table.Header>

            <Table.Body>
                {users.map((elem) => {
                    return (userToRow(elem, button))
                })}
            </Table.Body>
        </Table>
    )
}

export default UserTable