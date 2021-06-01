import React, { useState } from 'react'
import { Container, Menu } from 'semantic-ui-react'
import AdminCarsView from '../views/AdminCarsView'
import AdminUsersView from '../views/AdminUsersView'

const AdminPage = () => {

    const [activeItem, setActiveItem] = useState('Users')


    return(
        <Container>
            <Menu>
                <Menu.Item
                    name='Users'
                    active={activeItem === 'Users'}
                    onClick={() => setActiveItem('Users')}
                >
                </Menu.Item>
                <Menu.Item
                    name='Cars'
                    active={activeItem === 'Cars'}
                    onClick={() => setActiveItem('Cars')}
                >
                </Menu.Item>
            </Menu>
            {activeItem === 'Users' ? 
                <AdminUsersView/>
                :
                <AdminCarsView/>
            }
        </Container>
    )
}

export default AdminPage