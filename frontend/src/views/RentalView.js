import React, { useState, useEffect } from 'react'
import { Container, Pagination } from 'semantic-ui-react'
import { findAllRentals, patchRental } from '../api/services/rental'
import RentalTable from '../components/rental/RentalTable'
import useUserCookie from '../utils/useUserCookie'

const RentalView = () => {
    
    const [activePage, setActivePage] = useState(1)
    const [numberOfPages, setNumberOfPages] = useState(1)
    const [rentals, setRentals] = useState([])
    const [getUser] = useUserCookie()
    const [render, setRender] = useState(false)

    const rentalsPerPage = 5

    useEffect(() => {
        findAllRentals(getUser(), activePage - 1, rentalsPerPage)
        .then(response => {
            setRentals(response.data.content)
            setNumberOfPages(response.data.totalPages)
        })
    }, [render])

    const onClick = id => {
        patchRental(getUser(), id)
        .then(response => {
            setRender(!render)
        })
    }

    return (
        <Container>
            <RentalTable
                rentals={rentals}
                onClick={onClick}
            />
            <Pagination
                activePage={activePage}
                onPageChange={(_, { activePage }) => setActivePage(activePage)}
                totalPages={numberOfPages}
            />
        </Container>
    )
}

export default RentalView
