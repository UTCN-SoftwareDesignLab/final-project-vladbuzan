import authHeader, { BASE_URL, encodeParams, HTTP } from '../http'

export const findRentalsByCar = (user, id, page, rentalsPerPage) => {
    let request = `/rental/byCar/${id}?page=${page}&rentalsPerPage=${rentalsPerPage}`
    return HTTP.get(BASE_URL + request, { headers: authHeader(user)})
}

export const findAllRentals = (user, page, rentalsPerPage) => {
    let request = `/rental?page=${page}&rentalsPerPage=${rentalsPerPage}`
    return HTTP.get(BASE_URL + request, { headers: authHeader(user)})
}

export const saveRental = (user, data) => {
    return HTTP.post(BASE_URL + '/rental', data, { headers: authHeader(user)})
}

export const patchRental = (user, id) => {
    return HTTP.patch(BASE_URL + `/rental/${id}`, {}, { headers: authHeader(user)})
}