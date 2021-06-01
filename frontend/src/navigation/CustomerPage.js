import React, { useState } from 'react'
import { Container, Label, Modal } from 'semantic-ui-react'
import GMapsComp from '../components/googleMaps/GMapsComp'
import OfficeView from '../views/OfficeView'
import RentalSimpleView from '../views/RentalSimpleView'
import useMessaging from '../utils/useMessaging'

const CustomerPage = () => {

    const [selectedOffice, setSelectedOffice] = useState(0)
    const [showOfficeModal, setShowOfficeModal] = useState(false)
    const [showRentModal, setShowRentModal] = useState(false)
    const [selectedCar, setSelectedCar] = useState(0)
    const [client, , sendMessage] = useMessaging()
    
    const onMarkerClick = (id) => {
        setSelectedOffice(id)
        setShowOfficeModal(true)
    }

    const onRentClick = (id) => {
        setShowRentModal(true)
        setShowOfficeModal(false)
        setSelectedCar(id)
    }

    return (
        <Container>
            <GMapsComp
                onMarkerClick={onMarkerClick}
            />
            <Modal
                onClose={() => setShowOfficeModal(false)}
                onOpen={() => setShowOfficeModal(false)}
                open={showOfficeModal}
            >
                <Modal.Header>
                    Office
                </Modal.Header>
                <Modal.Content>
                    <OfficeView
                        id={selectedOffice}
                        onRentClick={onRentClick}
                    />
                </Modal.Content>
            </Modal>
            <Modal
                onClose={() => setShowRentModal(false)}
                onOpen={() => setShowRentModal(false)}
                open={showRentModal}
            >
                <Modal.Header>
                    Rent
                </Modal.Header>
                <Modal.Content>
                    Current rentals
                    <RentalSimpleView
                        carId={selectedCar}
                    />
                </Modal.Content>

            </Modal>
        </Container>
    )
}

export default CustomerPage
