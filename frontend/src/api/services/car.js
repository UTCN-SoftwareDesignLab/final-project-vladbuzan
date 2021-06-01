import authHeader, { BASE_URL, encodeParams, HTTP } from '../http'

export const findAllCars = (user, page, carsPerPage) => {
    let request = `/car?page=${page}&carsPerPage=${carsPerPage}`
    return HTTP.get(BASE_URL + request, { headers: authHeader(user)})
}

export const findAllCarsByOffice = (user, id, page, carsPerPage) => {
    console.log(id)
    let request = `/car/${id}?page=${page}&carsPerPage=${carsPerPage}`
    return HTTP.get(BASE_URL + request, { headers: authHeader(user)})
}
export const saveCar = (user, data) => {
    return HTTP.post(BASE_URL + '/car', data, { headers: authHeader(user)})
}

export const updateCar = (user, data, id) => {
    return HTTP.put(BASE_URL + `/car/${id}`, data, { headers: authHeader(user)})
}

export const deleteCar = (user, id) => {
    return HTTP.delete(BASE_URL + `/car/${id}`, { headers: authHeader(user)})
}