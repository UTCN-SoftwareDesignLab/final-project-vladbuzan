import authHeader, { BASE_URL, encodeParams, HTTP } from '../http'

export const findAllManufacturers = (user) => {
    return HTTP.get(BASE_URL + '/car/manufacturer', { headers: authHeader(user)})
}

export const saveManufacturer = (user, manufacturer) => {
    return HTTP.post(BASE_URL + '/car/manufacturer', manufacturer, { headers: authHeader(user)})
}

