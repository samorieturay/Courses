//Q1
function sumDiagonals(arr, row, col) {
    let sum = 0;

    if (row > 0 && col > 0)
        sum += arr[row - 1][col - 1]; // top-left

    if (row > 0 && col < arr[0].length - 1)
        sum += arr[row - 1][col + 1]; // top-right

    if (row < arr.length - 1 && col > 0)
        sum += arr[row + 1][col - 1]; // bottom-left

    if (row < arr.length - 1 && col < arr[0].length - 1)
        sum += arr[row + 1][col + 1]; // bottom-right

    return sum;
}

arr = [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12], [13, 14, 15, 16]];
console.log(arr[1][2]);
console.log(sumDiagonals(arr, 0, 0));
//Q2
function inc(arr) {
    let temp = [];
    for (let i = 0; i < arr.length; i++) {
        temp.push(arr[i] + 1);
    }
    return temp;
}

function copyInc(arr) {
    let temp = [];
    for (let i = 0; i < arr.length; i++) {
        temp.push(inc(arr[i]));
    }
    return temp;
}

console.log(copyInc([[1, 2, 3], [4, 5, 6], [7, 8, 9]]));
