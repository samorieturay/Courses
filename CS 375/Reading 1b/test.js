// Q1
console.log("hello from test.js");
function evenIdxPositiveSum(myArray) {
  let sum = 0;

  for (i = 0; i < myArray.length; i++) {
    if (i % 2 === 0) {
      if (myArray[i] > 0) {
        sum = sum + myArray[i];
      }
    }
  }
  return sum;
}
console.log(evenIdxPositiveSum([-2, 3, 1, 24, -8, -9, 10]));
//Q2
function filterer(myArray2, func) {
  let temp2 = [];

  for (let i = 0; i < myArray2.length; i++) {
    if (func(myArray2[i])) {
      temp2.push(myArray2[i]);
    }
  }
  return temp2;
}
let isEven = (x) => x % 2 == 0;
console.log(filterer([1, 2, 3, 4, 5, 6], isEven));

//q3
function builder(list1, list2) {
  let dict = {};
  for (let i = 0; i < list1.length; i++) {
    dict[list1[i]] = list2[i]; // assign value using bracket notation
  }
  return dict;
}

let keys1 = ["a", "b", "c"];
let values = [1, 2, 3];
console.log(builder(keys1, values)); // { a: 1, b: 2, c: 3 }
console.log("2" + 2 - "2");
