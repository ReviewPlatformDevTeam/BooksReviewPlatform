const userKey = { key: 'user' };

const checkStatus = (response) => {
    if(!response.ok) {
        throw Error(response.status);
    }
    return response;
}

const login = (username, password) => {
    const url = "/login";
    const loginBody = {
        username: username,
        password: password
    };
    
    const fetchData = {
        method: "POST",
        mode: 'cors',
        headers: {
            "Content-type": "application/json"
        },
<<<<<<< HEAD
        body: JSON.stringify(loginBody)
=======
        headers: {
            "Content-type": "application/json"
        }
>>>>>>> ebb46cfd4a785b12aa48e3257d4ebb17fa9c705d
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
