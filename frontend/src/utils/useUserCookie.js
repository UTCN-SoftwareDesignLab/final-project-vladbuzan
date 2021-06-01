import { useCookies } from 'react-cookie'
import { useHistory } from 'react-router'

const useUserCookie = () => {
    
    const [cookies, setCookie, removeCookie] = useCookies(["user"]);
    const history = useHistory()

    const saveUserCookie = (user) => {
        removeUserCookie()
        setCookie("user", user, { "path": "/" });
        history.push('/' + user.role.toLowerCase())
    }

    const removeUserCookie = () => {
        if (cookies.user !== undefined) {
            removeCookie("user", { "path": "/" });
        }
    }

    const getUserCookie = () => {
        return cookies.user
    }

    return [getUserCookie, saveUserCookie, removeUserCookie]
}

export default useUserCookie
