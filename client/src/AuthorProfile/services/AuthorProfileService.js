import { commonService } from '../../Common/services/CommonService';

const API_URL = process.env.REACT_APP_API_URL;

const getAuthorById = (id) => {
    const url = `${API_URL}/authors/${id}`;

    let singleAuthorHeaders = new Headers();
    singleAuthorHeaders.append(
        "Authorization",
        `Bearer ${commonService.getToken()}`
    );

    let singleAuthorData = {
        method: 'GET',
        headers: singleAuthorHeaders,
        redirect: 'follow',
        mode: "cors",
    };

    return fetch(url, singleAuthorData)
        .then(response => commonService.checkStatus(response)).then(response => response.text())
        .then(response => JSON.parse(response)).catch(error => {
            return {success: false, status: error.message}
        });
}

export const authorProfileService = { getAuthorById };