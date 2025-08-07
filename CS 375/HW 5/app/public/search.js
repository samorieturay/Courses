document.getElementById("submit").addEventListener("click", () => {
  const genre = document.getElementById("genre").value;
  const messageDiv = document.getElementById("message");
  const tbody = document.getElementById("books");

  // clear previous results
  while (tbody.firstChild) {
    tbody.removeChild(tbody.firstChild);
  }
  messageDiv.textContent = "";

  fetch(`/search?genre=${encodeURIComponent(genre)}`)
    .then(res => res.json())
    .then(data => {
      const rows = data.rows;
      if (rows.length === 0) {
        messageDiv.textContent = "No books found";
      } else {
        rows.forEach(book => {
          const tr = document.createElement("tr");

          const tdTitle = document.createElement("td");
          tdTitle.textContent = book.title;
          tr.appendChild(tdTitle);

          const tdGenre = document.createElement("td");
          tdGenre.textContent = book.genre;
          tr.appendChild(tdGenre);

          const tdQuality = document.createElement("td");
          tdQuality.textContent = book.quality ? "Yes" : "No";
          tr.appendChild(tdQuality);

          tbody.appendChild(tr);
        });
      }
    })
    .catch(err => {
      messageDiv.textContent = "No books found";
      console.error("Fetch error:", err);
    });
});
