let express = require("express");
let app = express();
app.use(express.static("public")); // serves files
let hostname = "localhost";
let port = 3000;
app.listen(port, hostname, () => {
  console.log(`http://${hostname}:${port}`);
});
