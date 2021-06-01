import React, { useState, useEffect } from 'react'
import { Container, Pagination } from 'semantic-ui-react'
import { findRentalsByCar } from '../api/services/rental'
import RentalForm from '../components/rental/RentalForm'
import RentalSimpleTable from '../components/rental/RentalSimpleTable'
import useUserCookie from '../utils/useUserCookie'
const RentalSimpleView = ({ carId }) => {
    
    const [rentals, setRentals] = useState([])
    const [activePage, setActivePage] = useState(1)
    const [numberOfPages, setNumberOfPages] = useState(1)
    const rentalsPerPage = 3
    const [getUser] = useUserCookie()

    useEffect(() => {
        findRentalsByCar(getUser(), carId, activePage - 1, rentalsPerPage)
        .then(response => {
            setRentals(response.data.content)
            setNumberOfPages(response.data.totalPages)
        })
    }, [activePage])

    return (
        <Container>
            <RentalSimpleTable
                rentals={rentals}
            />
            <Pagination
                activePage={activePage}
                onPageChange={(_, { activePage }) => setActivePage(activePage)}
                totalPages={numberOfPages}
            />
            <RentalForm
                carId={carId}
            />
            
        </Container>
    )
}

export default RentalSimpleView
