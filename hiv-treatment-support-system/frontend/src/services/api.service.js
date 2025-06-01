import axios from './axios.customize'

const loginAPI = (username, password) => {
    const URL_BACKEND = '/api/auth/login'
    const data = {
        username: username,
        password: password,
    }
    return axios.post(URL_BACKEND, data)
}

const registerAPI = (values) => {
    const URL_BACKEND = '/api/auth/register'
    const data = {
        fullName: values.fullname,
        gender: values.gender,
        dateOfBirth: values.dob.format('DD-MM-YYYY'),
        email: values.email,
        phone: values.phone,
        address: values.address,
        username: values.username,
        password: values.password
    }
    return axios.post(URL_BACKEND, data)
}

const bookingAPI = (values) => {
    const URL_BACKEND = '/api/users/booking'
    const data = {
        name: values.name,
        phone: values.phone,
        service: values.type,
        doctor: values.doctor,
        date: values.date.format('DD-MM-YYYY'),
        time: values.time,
    }
    return axios.post(URL_BACKEND, data)
}

const createAccountAPI = (username, password, email, role) => {
    const URL_BACKEND = '/api/create'
    const data = {
        username,
        password,
        email,
        role
    }
    return axios.post(URL_BACKEND, data)
}

const fetchAccountsAPI = () => {
    const URL_BACKEND = '/api/admin/accounts'
    return axios.get(URL_BACKEND)
}

const fetchUsersAPI = () => {
    const URL_BACKEND = '/api/admin/users'
    return axios.get(URL_BACKEND)
}

export {
    loginAPI,
    registerAPI,
    bookingAPI,
    createAccountAPI,
    fetchAccountsAPI,
    fetchUsersAPI
}