export function postRequest(body) {
    return {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json; charset=utf-8',
        },
        body: body,
    }
}