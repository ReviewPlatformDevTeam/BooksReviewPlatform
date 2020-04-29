const userKey = { key: 'user' };

const checkStatus = (response) => {

    console.log(response);
    if(!response.ok) {
        throw Error(response.status);
    }
    return response;
}

const login = (username, password) => {
    const url = "http://localhost:8080/login";
    const loginBody = {
        username: username,
        password: password
    };

    const fetchData = {
        method: "POST",
        mode: 'cors',
        headers: {
            "Content-type": "application/x-www-form-urlencoded"
        },
        body: JSON.stringify(loginBody)
    };

    return fetch(url, fetchData)
    .then(response => checkStatus(response))
    .then(response => response.json())
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

export const authService = { login, logout, isAuthenticated, authenticateUser };
