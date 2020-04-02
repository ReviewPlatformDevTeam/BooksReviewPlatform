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

    return fetch(url, fetchData).then(response =>
        response.json()
    );
};

const logout = () => {
    localStorage.removeItem('user');
}

const authenticateUser = () => { // returns object {name, token} if user is authenticated, returns null if not authenticated
    const userData = window.localStorage.getItem('user');
    return userData !== null ? JSON.parse(userData) : null;
}

// const authenticateUser = (user) => {
//     window.localStorage.setItem('user', JSON.stringify(user)); // user is kept in localStorage under 'user' key
// }

export const authService = { login, logout, authenticateUser };
