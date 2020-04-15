const userKey = { key: 'user' };

const checkStatus = (response) => {
    if(!response.ok) {
        throw Error(response.status);
    }
    return response;
}

const login = (username, password) => {
    const url = "/login";
    const fetchData = {
        method: "POST",
        body: {
            username: username,
            password: password
        },
        headers: new Headers(),
        mode: "cors"
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
