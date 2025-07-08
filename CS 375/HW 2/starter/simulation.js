// TODO your DOM code goes here

// once you've replaced conway.js with your solution to the previous assignment
// this will print [[false, true, false], [false, true, false]]
const ROWS = 25;
const COLS = 25;
let board = [];
let boardElements = [];
let interval = null;

const container = document.getElementById("board-container");
const table = document.createElement("table");
container.appendChild(table);

// Initialize checkerboard pattern
function initCheckerboard() {
  board = [];
  for (let i = 0; i < ROWS; i++) {
    const row = [];
    for (let j = 0; j < COLS; j++) {
      row.push((i + j) % 2 === 0);
    }
    board.push(row);
  }
}

// Create the board and cache the cell elements
function renderBoard() {
  table.innerHTML = ""; // clear any previous rows
  boardElements = [];

  for (let i = 0; i < ROWS; i++) {
    const tr = document.createElement("tr");
    const rowElems = [];

    for (let j = 0; j < COLS; j++) {
      const td = document.createElement("td");
      td.className = board[i][j] ? "alive" : "dead";
      tr.appendChild(td);
      rowElems.push(td);
    }

    table.appendChild(tr);
    boardElements.push(rowElems);
  }
}

// Efficiently update only the cell classes
function updateBoardVisual() {
  for (let i = 0; i < ROWS; i++) {
    for (let j = 0; j < COLS; j++) {
      boardElements[i][j].className = board[i][j] ? "alive" : "dead";
    }
  }
}

// Advance one generation using stepBoard()
function step() {
  board = stepBoard(board);
  updateBoardVisual();
}

// Generate a fully random board
function randomizeBoard() {
  board = [];
  for (let i = 0; i < ROWS; i++) {
    const row = [];
    for (let j = 0; j < COLS; j++) {
      row.push(Math.random() < 0.5);
    }
    board.push(row);
  }
  updateBoardVisual();
}

// Stop the running simulation
function stopSimulation() {
  if (interval !== null) {
    clearInterval(interval);
    interval = null;
  }
}

// Button handlers
document.getElementById("step").onclick = step;

document.getElementById("reset").onclick = () => {
  stopSimulation();
  initCheckerboard();
  updateBoardVisual();
};

document.getElementById("random").onclick = () => {
  stopSimulation();
  randomizeBoard();
};

document.getElementById("go").onclick = () => {
  if (interval === null) {
    interval = setInterval(step, 100);
  }
};

document.getElementById("pause").onclick = () => {
  stopSimulation();
};

// Initialize and render board on load
initCheckerboard();
renderBoard();
