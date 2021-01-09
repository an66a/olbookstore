export const capitalizer = (str) => {
    let splitStr = str.toLowerCase().split(' ');
    for (let i = 0; i < splitStr.length; i++) {
        splitStr[i] = splitStr[i].charAt(0).toUpperCase() + splitStr[i].substring(1);
    }
    return splitStr.join(' ');
}

export const random = (arr) => {
    let result = Math.floor(Math.random() * arr.length);
    return arr[result];
}

export const addFormInput = (type, label, name) => {
    return { type, label, name }
}