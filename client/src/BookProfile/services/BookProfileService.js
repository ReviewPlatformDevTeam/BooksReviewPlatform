import { commonService } from '../../Common/services/CommonService';

const API_URL = process.env.REACT_APP_API_URL;

const getBookById = (id) => {
    const url = `${API_URL}/books/${id}`;

    let singleBookHeaders = new Headers();
    singleBookHeaders.append(
                            "Authorization", 
                            `Bearer ${commonService.getToken()}`
                            );

    let singleBookData = {
        method: 'GET',
        headers: singleBookHeaders,
        redirect: 'follow',
        mode: "cors",
    };

    return fetch(url, singleBookData)
        .then(response => commonService.checkStatus(response)).then(response => response.text())
        .then(response => JSON.parse(response)).catch(error => {
            return {success: false, status: error.message}
        });
}

export const bookProfileService = { getBookById };