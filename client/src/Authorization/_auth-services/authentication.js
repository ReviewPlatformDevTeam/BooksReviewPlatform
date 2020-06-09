import { commonService } from '../../Common/services/CommonService';
const API_URL = process.env.REACT_APP_API_URL;
const userKey = { key: 'user' };

const login = (username, password) => {
    const url = `${API_URL}/login`;
    const loginBody = {
        username: username,
        password: password
    };

    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");

    let raw = JSON.stringify(loginBody);

    const fetchData = {
        method: 'POST',
        mode: "cors",
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    return fetch(url, fetchData)
        .then(response => commonService.checkStatus(response))
        .then(response => response.text())
        .then(response => JSON.parse(response))
        .catch(error => {
            return {success: false, status: error.message}
        });
};

const logout = () => {
    localStorage.removeItem(userKey.key);
}

const isAuthenticated = () => {
    const userData = window.localStorage.getItem(userKey.key);
    return userData ? JSON.parse(userData) : null;
}

const authenticateUser = (user) => {
    window.localStorage.setItem(userKey.key, JSON.stringify(user));
}

const reset = (email) => {
    const resetBody = {
        email: email
    };

    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Content-Type", "text/plain");

    let raw = JSON.stringify(resetBody);

    const fetchData = {
        method: 'POST',
        mode: "cors",
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    return fetch(`${API_URL}/resetPassword`, fetchData)
        .then(response => commonService.checkStatus(response))
        .then(response => response.text())
        .then(response => JSON.parse(response))
        .catch(error => {
            return {success: false, status: error.message}
        });
};

const register = (addUser) => {
    const url = `${API_URL}/users`;

    let myHeaders = new Headers();
    myHeaders.append("Content-Type", "application/json");
    myHeaders.append("Content-Type", "text/plain");

    let raw = JSON.stringify(addUser);

    let fetchData = {
        method: 'POST',
        mode: "cors",
        headers: myHeaders,
        body: raw,
        redirect: 'follow'
    };

    return fetch(url, fetchData)
        .then(response => commonService.checkStatus(response))
        .then(response => response.text())
        .then(response => JSON.parse(response))
        .catch(error => {
            return {success: false, status: error.message}
        });
}

export const authService = { login, logout, isAuthenticated, authenticateUser, reset, register };
