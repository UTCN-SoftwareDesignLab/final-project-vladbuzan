import { Table } from "semantic-ui-react"

export const rentalToSimpleRow = rental => {
    return(
        <Table.Row key={rental.id}>
            <Table.Cell>
                {rental.rentalStart}
            </Table.Cell>
            <Table.Cell>
                {rental.rentalEnd}
            </Table.Cell>
        </Table.Row>

    )
}