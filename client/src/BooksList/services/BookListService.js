const API_URL = process.env.REACT_APP_API_URL;

const checkStatus = (response) => {
    if(!response.ok) {
        throw Error(response.status);
    }
    return response;
}

const getToken = () => {
    let token = window.localStorage.getItem('user');
    token = JSON.parse(token);
    token = token.token;

    return token;
}


const getAllBooks = () => {
    const url = `${API_URL}/books`;

    let myHeaders = new Headers();
    myHeaders.append("Authorization", `Bearer ${getToken()}`);

    let fetchData = {
        method: 'GET',
        mode: "cors",
        headers: myHeaders,
        redirect: 'follow'
    };

    return fetch(url, fetchData)
        .then(response => checkStatus(response))
        .then(response => response.text())
        .then(response => JSON.parse(response))
        .catch(error => {
            return {success: false, status: error.message}
        });
}

export const bookListService = { getAllBooks };
