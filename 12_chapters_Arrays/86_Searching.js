let results = ["pass", "fail", "pass", "error", "fail"];

console.log(results.indexOf("fail")); //1
console.log(results.indexOf("skip"));  // -1

// lastIndexOf — searches from the end
console.log(results.lastIndexOf("fail"));

// includes — returns boolean
console.log(results.includes("error")); // true
console.log(results.includes("skip"));