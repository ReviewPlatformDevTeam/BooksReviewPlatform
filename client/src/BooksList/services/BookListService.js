import { commonService } from '../../Common/services/CommonService';
const API_URL = process.env.REACT_APP_API_URL;

const getAllBooks = () => {
    const url = `${API_URL}/books`;

    let myHeaders = new Headers();
    myHeaders.append("Authorization", `Bearer ${commonService.getToken()}`);

    let fetchData = {
        method: 'GET',
        mode: "cors",
        headers: myHeaders,
        redirect: 'follow'
    };

    return fetch(url, fetchData)
        .then(response => commonService.checkStatus(response))
        .then(response => response.text())
        .then(response => JSON.parse(response))
        .catch(error => {
            return undefined
        });
}

export const bookListService = { getAllBooks };
