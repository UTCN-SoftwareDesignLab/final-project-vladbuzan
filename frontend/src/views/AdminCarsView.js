import React, { useState, useEffect } from 'react'
import { Button, Container, Form, Input, Modal, Pagination } from 'semantic-ui-react'
import { deleteCar, findAllCars, saveCar, updateCar } from '../api/services/car'
import { saveManufacturer } from '../api/services/manufacturer'
import CarForm from '../components/car/CarForm'
import CarTable from '../components/car/CarTable'
import useUserCookie from '../utils/useUserCookie'
const AdminCarsView = () => {

    const [busy, setBusy] = useState(true)
    const [cars, setCars] = useState([])
    const [carId, setCarId] = useState(0)
    const [showModifyForm, setShowModifyForm] = useState(false)
    const [showAddForm, setShowAddForm] = useState(false)
    const [shoeManufacturerForm, setShoeManufacturerForm] = useState(false)
    const [render, setRender] = useState(true)
    const [manufacturer, setManufacturer] = useState('')
    const [activePage, setActivePage] = useState(1)
    const [numberOfPages, setNumberOfPages] = useState(1)
    const [getUser] = useUserCookie()
    const carsPerPage = 5

    const onAddManufacturerClick = () => {
        saveManufacturer(getUser(), {
            name: manufacturer
        }).then(response => {
            alert('Manufacturer saved successfully')
            setShoeManufacturerForm(false)
            setRender(!render)
        })
    }

    useEffect(() => {
        setBusy(true)
        findAllCars(getUser(), activePage - 1, carsPerPage)
            .then(response => {
                setNumberOfPages(response.data.totalPages)
                setCars(response.data.content)
            })
            .catch(reason => alert(reason))
        setBusy(false)
    }, [busy, render, activePage])

    const onUpdateClick = (data, id) => {
        updateCar(getUser(), data, id)
            .then(response => {
                alert('Car updated successfully')
                setShowModifyForm(false)
                setRender(!render)
            })
            .catch(reason => alert(reason))
    }

    const onRemoveClick = () => {
        deleteCar(getUser(), carId)
            .then(response => {
                alert('Car removed successfully')
                setShowModifyForm(false)
                setRender(!render)
            })
            .catch(reason => alert(reason))
    }

    const onAddClick = e => {
        saveCar(getUser(), e)
        .then(response => {
            alert('Car saved successfully')
            setShowAddForm(false)
            setRender(!render)
        })
        .catch(reason => alert(reason))
    }

    return (
        <Container>
            <CarTable
                cars={cars}
                button={{
                    text: 'Modify',
                    onClick: (id) => {
                        setCarId(id)
                        setShowModifyForm(true)
                    }
                }}
            />
            <Button
                onClick={() => setShowAddForm(true)}
            >
                Add Car
            </Button>
            <Button
                onClick={() => setShoeManufacturerForm(true)}
            >
                Add Manufacturer
            </Button>
            <Pagination
                activePage={activePage}
                onPageChange={(_, { activePage }) => setActivePage(activePage)}
                totalPages={numberOfPages}
            />
            <Modal
                onClose={() => setShowModifyForm(false)}
                onOpen={() => setShowModifyForm(true)}
                open={showModifyForm}
            >
                <Modal.Header>
                    Modify / Delete car
                </Modal.Header>
                <Modal.Content>
                    <CarForm
                        onSubmitClick={(e) => onUpdateClick(e, carId)}
                    />
                    <Button
                        onClick={onRemoveClick}
                    >
                        Remove car
                    </Button>
                </Modal.Content>
            </Modal>
            <Modal
                onClose={() => setShowAddForm(false)}
                onOpen={() => setShowAddForm(true)}
                open={showAddForm}
            >
                <Modal.Header>
                    Add car
                </Modal.Header>
                <Modal.Content>
                    <CarForm
                        onSubmitClick={(e) => onAddClick(e)}
                    />
                </Modal.Content>
            </Modal>
            <Modal
                onClose={() => setShoeManufacturerForm(false)}
                onOpen={() => setShoeManufacturerForm(true)}
                open={shoeManufacturerForm}
            >
                <Modal.Header>
                    Add Manufacturer
                </Modal.Header>
                <Modal.Content>
                    <Form>
                        <Form.Field>
                            <Input
                                placeholder='Manufacturer'
                                value={manufacturer}
                                onChange={event => setManufacturer(event.target.value)}
                            />
                        </Form.Field>
                        <Button
                            onClick={onAddManufacturerClick}
                        >
                            Add
                        </Button>
                    </Form>
                </Modal.Content>
            </Modal>
        </Container>
    )
}

export default AdminCarsView
