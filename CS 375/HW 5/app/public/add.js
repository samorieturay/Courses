document.getElementById("submit").addEventListener("click", () => {
  const title = document.getElementById("title").value;
  const genre = document.getElementById("genre").value;
  const qualityInput = document.querySelector('input[name="quality"]:checked');
  const quality = qualityInput ? qualityInput.value : "";

  fetch("/add", {
    method: "POST",
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify({ title, genre, quality })
  })
    .then(response => {
      const msg = document.getElementById("message");
      if (response.status === 200) {
        msg.textContent = "Success";
      } else {
        msg.textContent = "Bad request";
      }
    })
    .catch(err => {
      document.getElementById("message").textContent = "Bad request";
      console.error("Fetch error:", err);
    });
});
