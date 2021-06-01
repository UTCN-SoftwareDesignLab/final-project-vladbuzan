import { Button, Table } from 'semantic-ui-react'

export const carToRow = (car, button) => {
    return (
        <Table.Row
            key={car.id}
        >
            <Table.Cell>
                <Button
                    positive
                    onClick={() => button.onClick(car.id)}
                >
                    {button.text}
                </Button>
            </Table.Cell>
            <Table.Cell>
                {car.manufacturer.name}
            </Table.Cell>
            <Table.Cell>
                {car.model}
            </Table.Cell>
            <Table.Cell>
                {car.description}
            </Table.Cell>
        </Table.Row>
    )
}