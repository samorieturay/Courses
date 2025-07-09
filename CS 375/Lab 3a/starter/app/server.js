let express = require("express");

// express returns a function, we can call it to create a server object
let app = express();

let port = 3000;
let hostname = "localhost";

let visited = new Set();
app.use(express.json());

// middleware just for debugging
app.use("*", (req, res, next) => {
  console.log({ url: req.url, body: req.body });
  // makes sure next matching request handler is called
  // necessary b/c this doesn't send a response
  next();
});

app.post("/flargle", (req, res) => {
  console.log("You sent the body:", req.body);
  let hasFlarge = req.body.hasOwnProperty("flargle");
  res.json({hasFlarge});
});

app.get("/visitors/:visitorName", (req, res) => {
  let visitorName = req.params.visitorName;
  let hasVisited = visited.has(visitorName);
  console.log(req.params, visitorName);
  res.json({hasVisited});
});
app.post("/visitors/:visitorName", (req, res) => {
  let visitorName = req.params.visitorName;
  visited.add(visitorName);
  console.log(req.params, visitorName);
  res.json();
});

app.get("/all-visitors", (req, res) => {
  let visitorList = Array.from(visited)
    .map(name => `<li>${name}</li>`)
    .join("\n");

  let html = `
    <!DOCTYPE html>
    <html>
    <head>
      <title>All Visitors</title>
    </head>
    <body>
      <h1>Visitor List</h1>
      <ul>
        ${visitorList}
      </ul>
    </body>
    </html>
  `;

  res.send(html);
});

app.listen(port, hostname, () => {
  console.log(`Listening at: http://${hostname}:${port}`);
});