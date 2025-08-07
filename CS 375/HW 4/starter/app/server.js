// server.js
const express = require('express');
const axios = require('axios');
const path = require('path');
const apiFile = require('../env.json'); // Adjust path if needed

const app = express();
const PORT = 3000;

// Load API config
const apiKey = apiFile["api_key"];
const baseUrl = apiFile["api_url"];

// Serve static files from public
app.use(express.static(path.join(__dirname, 'public')));

// GET /
app.get('/', (req, res) => {
    res.sendFile(path.join(__dirname, 'public', 'index.html'));
});

// GET /forecast
app.get('/forecast', async (req, res) => {
    const zip = req.query.zip;
    try {
        // Build the API request
        let url = `${baseUrl}?appid=${apiKey}`;
        if (zip) url += `&zip=${zip}`;
        // Only pass appid and zip as params

        const response = await axios.get(url);

        // Only send the needed weather data
        res.status(200).json(response.data);
    } catch (error) {
        // If error, return API's error message and status code
        if (error.response && error.response.data && error.response.data.message) {
            res.status(error.response.status).json({ error: error.response.data.message });
        } else {
            res.status(500).json({ error: "Unknown error" });
        }
    }
});

// Start server
app.listen(PORT, () => {
    console.log(`Server running on http://localhost:${PORT}`);
});
