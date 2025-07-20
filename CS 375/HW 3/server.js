// server.js
const express = require("express");
const app = express();
const port = 3000;
const hostname = "localhost";

// all readings here
let devices = {};

// Parse JSON bodies
app.use(express.json());

// POST api
app.post("/api/:deviceid", (req, res) => {
  let deviceId = req.params.deviceid;
  let data = req.body;
  let usage = data["energy-usage"];

  // Check if usage exists and is an integer
  if (typeof usage === "number" && Number.isInteger(usage)) {
    if (!devices[deviceId]) {
      devices[deviceId] = [];
    }
    devices[deviceId].push(usage);
    res.status(200).end();
  } else {
    res.status(400);
    res.set("Content-Type", "text/plain");
    res.send("Invalid Request");
  }
});

// GET the api
app.get("/api/:deviceid", (req, res) => {
  let deviceId = req.params.deviceid;
  if (devices[deviceId]) {
    res.status(200).json({
      "total-energy-usage": devices[deviceId],
    });
  } else {
    res.status(404);
    res.set("Content-Type", "text/plain");
    res.send("Device Not Found");
  }
});

// GET /
app.get("/", (req, res) => {
  let html = "";
  html += "<!DOCTYPE html>";
  html += "<html>";
  html += "<head>";
  html += "<meta charset='utf-8'>";
  html += "<title>Device Energy Usage</title>";
  html += "<style>";
  html += "table { border-collapse: collapse; width: 60%; margin: 40px auto; }";
  html +=
    "th, td { border: 1px solid #333; padding: 8px 12px; text-align: left; }";
  html += "th { background: #f5f5f5; }";
  html += ".red { background: #faa; }";
  html += "</style>";
  html += "</head>";
  html += "<body>";
  html += "<h1 style='text-align:center;'>Device Energy Usage Dashboard</h1>";
  html += "<table>";
  html += "<tr><th>Device ID</th><th>Energy Usage</th></tr>";

  let deviceKeys = Object.keys(devices);

  if (deviceKeys.length === 0) {
    html +=
      "<tr><td colspan='2' style='text-align:center;'>No data reported yet</td></tr>";
  } else {
    for (let i = 0; i < deviceKeys.length; i++) {
      let id = deviceKeys[i];
      let readings = devices[id];
      let sum = 0;
      for (let j = 0; j < readings.length; j++) {
        sum += readings[j];
      }
      let rowClass = "";
      if (sum > 1000) {
        rowClass = "red";
      }
      html += "<tr class='" + rowClass + "'>";
      html += "<td>" + id + "</td>";
      html += "<td>" + readings.join(", ") + "</td>";
      html += "</tr>";
    }
  }

  html += "</table>";
  html += "</body>";
  html += "</html>";

  res.status(200);
  res.set("Content-Type", "text/html");
  res.send(html);
});

app.listen(port, hostname, () => {
  console.log(`http://${hostname}:${port}`);
});
