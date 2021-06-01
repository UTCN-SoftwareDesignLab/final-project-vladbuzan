import React, { useState, useEffect } from 'react'
import { Button, Form, Grid, GridColumn, Input, Label } from 'semantic-ui-react'
import SemanticDatepicker from 'react-semantic-ui-datepickers';
import useUserCookie from '../../utils/useUserCookie'
import { saveRental } from '../../api/services/rental';
import useMessaging from '../../utils/useMessaging'

const RentalForm = ({ carId }) => {

    const [startHour, setStartHour] = useState('')
    const [startDate, setStartDate] = useState(new Date())
    const [endHour, setEndHour] = useState('')
    const [endDate, setEndDate] = useState(new Date())
    const dateFormat = require('dateformat');
    const [getUser] = useUserCookie()
    const [client, , sendMessage] = useMessaging()

    const format = 'dd.mm.yyyy'

    const onSubmitClick = () => {
        let data = {
            userId: getUser().id,
            carId: carId,
            rentalStart: dateFormat(startDate, format) + ' ' + startHour,
            rentalEnd: dateFormat(endDate, format) + ' ' + endHour
        }
        saveRental(getUser(), data)
        .then(response => {
            alert('saved successfully')
            sendMessage('/app/send', {
                message: "New rental !!"
            }, client)
        })
        .catch(reason => alert(reason.response.data))
    }
    return (
        <Form>
            <Grid divided='vertically'>
                <Grid.Row columns={2}>
                    <Grid.Column>
                        <Form.Field>
                            <Label>
                                Start Date
                            </Label>
                            <Input
                                placeholder='HH:mm'
                                value={startHour}
                                onChange={event => setStartHour(event.target.value)}
                            />
                        </Form.Field>
                        <Form.Field>
                            <SemanticDatepicker
                                onChange={(_, data) => setStartDate(data.value)}
                                time={true}
                            />
                        </Form.Field>
                    </Grid.Column>
                    <Grid.Column>
                        <Form.Field>
                            <Label>
                                End Date
                            </Label>
                            <Input
                                placeholder='HH:mm'
                                value={endHour}
                                onChange={event => setEndHour(event.target.value)}
                            />
                        </Form.Field>
                        <Form.Field>
                            <SemanticDatepicker
                                onChange={(_, data) => setEndDate(data.value)}
                                time={true}
                            />
                        </Form.Field>
                    </Grid.Column>
                </Grid.Row>
            </Grid>
            <Button
                onClick={onSubmitClick}
            >
                Rent
            </Button>
        </Form>
    )
}

export default RentalForm
