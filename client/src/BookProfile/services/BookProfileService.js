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

const getReviewsForBook = (book) => {
    let reviewHeaders = new Headers();
    reviewHeaders.append("Authorization", `Bearer ${commonService.getToken()}`);

    const url = `${API_URL}/reviews?book=${book}`;

    let reviewData = {
        method: 'GET',
        headers: reviewHeaders,
        redirect: 'follow',
        mode: "cors",
    };

    return fetch(url, reviewData)
        .then(response => commonService.checkStatus(response)).then(response => response.text())
        .then(response => JSON.parse(response)).catch(error => {
            return {success: false, status: error.message}
        });
}

const addReviewForBook = (data) => {
    const url = `${API_URL}/reviews`;
    let addReviewHeaders = new Headers();
    addReviewHeaders.append("Authorization", `Bearer ${commonService.getToken()}`);
    addReviewHeaders.append("Content-Type", "application/json");
    let raw = JSON.stringify(data);

    let addReviewData = {
        method: 'POST',
        headers: addReviewHeaders,
        redirect: 'follow',
        mode: "cors",
        body: raw
    };

    return fetch(url, addReviewData)
        .then(response => commonService.checkStatus(response)).then(response => response.text())
        .then(response => JSON.parse(response)).catch(error => {
            return {success: false, status: error.message}
        });
}

export const bookProfileService = { getBookById, getReviewsForBook, addReviewForBook };