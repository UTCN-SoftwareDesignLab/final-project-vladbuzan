import { Button, Label, Table } from "semantic-ui-react"

export const rentalToRow = (rental, onClick) => {
    return(
        <Table.Row key={rental.id}>
            <Table.Cell>
                {rental.customer.firstname + ' ' + rental.customer.lastname}
            </Table.Cell>
            <Table.Cell>
                {rental.rentalStart}
            </Table.Cell>
            <Table.Cell>
                {rental.rentalEnd}
            </Table.Cell>
            <Table.Cell>
                {rental.car.manufacturer.name + rental.car.model}
            </Table.Cell>
            <Table.Cell>
                <Label>
                    {rental.status}
                </Label>
            </Table.Cell>
            <Table.Cell>
                <Button
                    onClick={() => onClick(rental.id)}
                >
                    Change status
                </Button>
            </Table.Cell>
        </Table.Row>
    )
}