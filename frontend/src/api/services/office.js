import authHeader, { BASE_URL, encodeParams, HTTP } from '../http'

export const findAllOffices = (user) => {
    return HTTP.get(BASE_URL + '/office', { headers: authHeader(user)})
}

export const findOfficeById = (user, id) => {
    return HTTP.get(BASE_URL + `/office/${id}`, { headers: authHeader(user)})
}

export const saveOffice = (user, data) => {
    return HTTP.post(BASE_URL + '/office', data, { headers: authHeader(user)})
}