import React, { useState, useEffect } from 'react'
import { Container, Label, Pagination } from 'semantic-ui-react'
import { findAllCars, findAllCarsByOffice } from '../api/services/car'
import { findOfficeById } from '../api/services/office'
import CarTable from '../components/car/CarTable'
import useUserCookie from '../utils/useUserCookie'

const OfficeView = ({ id, onRentClick }) => {
    const [getUser] = useUserCookie()
    const [office, setOffice] = useState({})
    const [cars, setCars] = useState([])
    const [activePage, setActivePage] = useState(1)
    const [numberOfPages, setNumberOfPages] = useState(1)
    const carsPerPage = 5

    useEffect(() => {
        findOfficeById(getUser(), id)
        .then(response => {
            setOffice(response.data)
        })
        findAllCarsByOffice(getUser(), id, activePage-1, carsPerPage)
        .then(response => {
            setCars(response.data.content)
            setNumberOfPages(response.data.totalPages)
        })
    }, [activePage])

    return (
        <Container>
            {office.name}
            <CarTable
                cars={cars}
                button={{
                    text:'Rent',
                    onClick: (id) => {
                        onRentClick(id)
                    }
                }
                }
            />
            <Pagination
                activePage={activePage}
                onPageChange={(_, { activePage }) => setActivePage(activePage)}
                totalPages={numberOfPages}
            />
        </Container>
    )
}

export default OfficeView
