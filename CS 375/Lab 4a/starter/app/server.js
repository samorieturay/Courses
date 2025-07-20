const express = require("express");
const axios = require("axios");
const app = express();
app.use(express.static("public"));

const apiFile = require("../key.json");
const apiKey = apiFile["key"];
const port = 3000;
const hostname = "localhost";
const baseUrl = "https://api.openweathermap.org/data/2.5/weather";

app.get("/feels-like", (req, res) => {
  const zip = req.query.zip || 19104;
  const url = `${baseUrl}?zip=${zip}&appid=${apiKey}`;
  axios.get(url).then((response) => {
    const feelsLikeKelvin = response.data.main.feels_like;
    const feelsLikeF = Math.round((feelsLikeKelvin - 273.15) * 9 / 5 + 32);
    res.json({
      "zip": zip,
      "feels-like-fahrenheit": feelsLikeF
    });
  });
});

app.listen(port, hostname, () => {
  console.log(`http://${hostname}:${port}`);
});
