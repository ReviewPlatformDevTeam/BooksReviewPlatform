import { commonService } from '../../Common/services/CommonService';

const API_URL = process.env.REACT_APP_API_URL;

const getReviewsForUser = (user) => {
    let reviewHeaders = new Headers();
    reviewHeaders.append("Authorization", `Bearer ${commonService.getToken()}`);

    const url = `${API_URL}/reviews?user=${user}`;

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


export const userProfileService = { getReviewsForUser };