let browsers = ["Chrome", "Firefox", "Safari"];
console.log(browsers);

let scores = new Array(3);  // creates [empty x 3]
scores[0] = "1"; 
console.log(scores);

let scores2 = new Array(1, 2, 3);
console.log(scores2);

let numbers = new Array(100, 200, 300, 400);
console.log(numbers);


let test = Array.of(10, 20, 30, 40, 50);
console.log(test.length);
console.log(test);

// Array.from()
let chars = Array.from("hello");
console.log(chars);
// ["h", "e", "l", "l", "o"]