import React, { useState, useEffect } from 'react'
import { Button, Container, Form, Input, Menu, Message, Modal, Pagination } from 'semantic-ui-react'
import RentalView from '../views/RentalView'
import GMapsComp from '../components/googleMaps/GMapsComp'
import { saveOffice } from '../api/services/office'
import useUserCookie from '../utils/useUserCookie'
import useMessaging from '../utils/useMessaging'

const EmployeePage = () => {

    const [activeItem, setActiveItem] = useState('Offices')
    const [latitude, setLatitude] = useState(null)
    const [longitude, setLongitude] = useState(null)
    const [showOfficeModal, setShowOfficeModal] = useState(false)
    const [officeName, setOfficeName] = useState('')
    const [getUser] = useUserCookie()
    const [render, setRender] = useState(true)
    const [showMessage, setShowMessage] = useState(false)
    const [messageText, setMessageText] = useState('')
    const [client, subscribe] = useMessaging()

    const onMessageReceived = (message) => {
        let received = JSON.parse(message.body)
        if (received !== undefined) {
            setMessageText(received.message)
            setShowMessage(true)
            setTimeout(() => setShowMessage(false), 3000);
        }
    }

    useEffect(() => {
        subscribe("/receive", onMessageReceived, client)
    }, [])

    const onMapClick = event => {
        setLatitude(event.latLng.lat())
        setLongitude(event.latLng.lng())
        setShowOfficeModal(true)
    }

    useEffect(() => {
    }, [render])

    const onSubmitClick = () => {
        saveOffice(getUser(), {
            name: officeName,
            latitude: latitude,
            longitude: longitude
        }).then(response => {
            alert('Office saved successfully')
            setShowOfficeModal(false)
            setRender(!render)
        })
    }

    return (
        <Container>
            <Menu>
                <Menu.Item
                    name='Rentals'
                    active={activeItem === 'Rentals'}
                    onClick={() => setActiveItem('Rentals')}
                />
                <Menu.Item
                    name='Offices'
                    active={activeItem === 'Offices'}
                    onClick={() => setActiveItem('Offices')}
                />
            </Menu>
            {showMessage ?
                <Message>
                    <Message.Header>New rental!</Message.Header>
                    <p>
                        {messageText}
                    </p>
                </Message>
                :
                undefined
            }
            {activeItem === 'Rentals' ?
                <RentalView />
                :
                <GMapsComp
                    onMarkerClick={() => { }}
                    onMapClick={onMapClick}
                />
            }
            <Modal
                onClose={() => setShowOfficeModal(false)}
                onOpen={() => setShowOfficeModal(false)}
                open={showOfficeModal}
            >
                <Modal.Header>
                    Add office
                </Modal.Header>
                <Modal.Content>
                    <Form>
                        <Form.Field>
                            <Input
                                placeholder='Name'
                                value={officeName}
                                onChange={event => setOfficeName(event.target.value)}
                            />
                        </Form.Field>
                        <Button
                            onClick={onSubmitClick}
                        >
                            Submit
                        </Button>
                    </Form>
                </Modal.Content>
            </Modal>
        </Container>
    )
}

export default EmployeePage
