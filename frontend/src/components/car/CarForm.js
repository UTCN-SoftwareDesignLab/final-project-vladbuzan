import React, { useState, useEffect } from 'react'
import { Container, Form, Label, Input, Dropdown, Button } from 'semantic-ui-react'
import { findAllManufacturers } from '../../api/services/manufacturer'
import { findAllOffices } from '../../api/services/office'
import useUserCookie from '../../utils/useUserCookie'

const CarForm = ({ onSubmitClick }) => {

    const [description, setDescription] = useState('')
    const [model, setModel] = useState('')
    const [manufacturers, setManufacturers] = useState([])
    const [offices, setOffices] = useState([])
    const [getUser] = useUserCookie()
    const [selectedManufacturer, setSelectedManufacturer] = useState(0)
    const [selectedOffice, setSelectedOffice] = useState(0)

    useEffect(() => {
        findAllManufacturers(getUser())
        .then(response => {
            setManufacturers(response.data
                .map(elem => {
                    return {
                        key: elem.id,
                        text: elem.name,
                        value: elem.id
                    }
                }))
        }).catch(reason => alert(reason))
        findAllOffices(getUser())
        .then(response => {
            setOffices(response.data
                .map(elem => {
                    return {
                        key: elem.id,
                        text: elem.name,
                        value: elem.id
                    }
                }))
        }).catch(reason => alert(reason))
    }, [])


    return (
        <Container>
            <Form>
                <Form.Field>
                    <Label>
                        Description
                    </Label>
                    <Input
                        palceholder='Description'
                        value={description}
                        onChange={e => setDescription(e.target.value)}                    
                    />
                </Form.Field>
                <Form.Field>
                    <Label>
                        Model
                    </Label>
                    <Input
                        placeholder='Model'
                        value={model}
                        onChange={e => setModel(e.target.value)}
                    />
                </Form.Field>
                <Form.Field>
                    <Label>
                        Manufacturer
                    </Label>
                    <Dropdown
                        placeholder='Manufacturer'
                        search selection options = {manufacturers}
                        onChange={(_, value) => 
                            setSelectedManufacturer(value.value)}
                    />
                </Form.Field>
                <Form.Field>
                    <Label>
                        Office
                    </Label>
                    <Dropdown
                        palceholder='Office'
                        search selection options = {offices}
                        onChange={(_, value) => 
                            setSelectedOffice(value.value)}
                    />
                </Form.Field>
                <Button
                    onClick={() => onSubmitClick({
                        description : description,
                        manufacturerId : selectedManufacturer,
                        officeId : selectedOffice,
                        model : model
                    })}
                >
                    Submit
                </Button>

            </Form>

        </Container>
    )
}

export default CarForm
