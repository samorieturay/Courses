const express = require("express");
const axios = require("axios");
const myKeys = require("./key.json");

const app = express();
const port = 3000;
const apiKey = myKeys.key;

app.use(express.static("public")); // optional static folder

app.get("/data", (req, res) => {
  const zip = req.query.zip || "19104"; // default to Drexel ZIP
  const url = `https://api.openweathermap.org/data/2.5/weather?zip=${zip}&appid=${apiKey}`;

  axios
    .get(url)
    .then((response) => {
      res.json(response.data);
    })
    .catch((err) => {
      console.error("Error fetching weather:", err.message);
      res.status(500).json({ error: "Failed to fetch weather" });
    });
});

app.listen(port, () => {
  console.log(`Server running on http://localhost:${port}`);
});
