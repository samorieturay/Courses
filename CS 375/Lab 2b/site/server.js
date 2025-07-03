let http = require("http");
let fs = require("fs");
let path = require("path");

let hostname = "localhost";
let port = 3000;

function handleRequest(req, res) {
  console.log("Request URL:", req.url);
  console.log("Request headers:", req.headers);
  console.log("Request method:", req.method);
  console.log();

  if (req.method !== "GET") {
    res.statusCode = 405;
    res.setHeader("Content-Type", "text/plain");
    return res.end("Method not allowed");
  }

  // Create path to the requested file inside the 'site' directory
  let filePath = path.join(__dirname, "site", req.url);

  try {
    let stat = fs.statSync(filePath);

    // check for index.html inside it
    if (stat.isDirectory()) {
      filePath = path.join(filePath, "index.html");
      stat = fs.statSync(filePath);
    }

    // should read and then send the file
    let data = fs.readFileSync(filePath, "utf-8");
    res.statusCode = 200;
    res.setHeader("Content-Type", "text/plain");
    res.end(data);
  } catch (error) {
    if (error.code === "ENOENT") {
      res.statusCode = 404;
      res.setHeader("Content-Type", "text/plain");
      res.end("File not found");
    } else {
      console.error(error);
      res.statusCode = 500;
      res.setHeader("Content-Type", "text/plain");
      res.end("Something went wrong");
    }
  }
}

let server = http.createServer(handleRequest);

server.listen(port, hostname, function () {
  console.log(`Server listening on http://${hostname}:${port}`);
});
