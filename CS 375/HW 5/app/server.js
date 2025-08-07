const pg = require("pg");
const express = require("express");
const app = express();

const port = 3000;
const hostname = "localhost";

const env = require("../env.json");
const Pool = pg.Pool;
const pool = new Pool(env);
pool.connect().then(() => {
  console.log(`Connected to database ${env.database}`);
});

app.use(express.json());
app.use(express.static("public"));

// Add a new book
app.post("/add", async (req, res) => {
  const { title, genre, quality } = req.body;

  // Validation
  if (
    typeof title !== "string" ||
    title.length < 1 ||
    title.length > 15 ||
    !["scifi", "romance", "adventure"].includes(genre) ||
    !["yes", "no"].includes(quality)
  ) {
    return res.status(400).end();
  }

  try {
    await pool.query(
      "INSERT INTO books(title, genre, quality) VALUES($1, $2, $3)",
      [title, genre, quality]
    );
    return res.status(200).end();
  } catch (err) {
    console.error("Error inserting book:", err);
    return res.status(500).end();
  }
});

// Search for books
app.get("/search", async (req, res) => {
  const genre = req.query.genre;
  let queryText;
  let params = [];

  if (!["scifi", "romance", "adventure"].includes(genre)) {
    queryText = "SELECT * FROM books";
  } else {
    queryText = "SELECT * FROM books WHERE genre = $1";
    params = [genre];
  }

  try {
    const result = await pool.query(queryText, params);
    return res.status(200).json({ rows: result.rows });
  } catch (err) {
    console.error("Error querying books:", err);
    return res.status(500).end();
  }
});

app.listen(port, hostname, () => {
  console.log(`Listening at: http://${hostname}:${port}`);
});
