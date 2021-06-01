import React, { useState, useEffect } from 'react'
import {
    GoogleMap,
    useLoadScript,
    Marker,
    InfoWindow
} from '@react-google-maps/api'
import { Container } from 'semantic-ui-react'
import useUserCookie from '../../utils/useUserCookie'
import { findAllOffices } from '../../api/services/office'

const GMapsComp = ({onMapClick, onMarkerClick}) => {

    const [render, setRender] = useState(true)
    const [getUser] = useUserCookie()
    const [offices, setOffices] = useState([])

    useEffect(() => {
        findAllOffices(getUser())
        .then(response => {
            setOffices(response.data)
        })
    }, [render])

    const apiKey = 'AIzaSyC4_M7MphxbivjQGF_UZv4CeCTBglZh2fk'
    const { isLoaded, loadError } = useLoadScript({
        googleMapsApiKey: apiKey
    })

    const mapContainerStyle = {
        width: '100vw',
        height: '100vh'
    }

    const center = {
        lat: 46.771210,
        lng: 23.623634
    }

    if (loadError) return "Error loading maps"
    if (!isLoaded) return "Loading maps"

    return (
        <Container>
            <GoogleMap
                mapContainerStyle={mapContainerStyle}
                zoom={8}
                center={center}
                onClick={onMapClick}
            >
            {offices.map(elem => (
                <Marker
                    key={elem.id}
                    position={{
                        lat: elem.latitude,
                        lng: elem.longitude
                    }}
                    onClick={() => onMarkerClick(elem.id)}
                />
            ))}
            </GoogleMap>
        </Container>
    )
}

export default GMapsComp
