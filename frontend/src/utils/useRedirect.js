import { useHistory } from 'react-router'

const useRedirect = () => {
    
    const history = useHistory()

    const redirect = (user) => {
        switch(user.role) {
            case 'ADMIN':
                history.push('/admin')
                break
            case 'EMPLOYEE':
                history.push('/employee')
                break
            case 'CUSTOMER':
                history.push('/customer')
                break
            default : 
                history.push('/')
                break
        }
    }

    return redirect
}

export default useRedirect
