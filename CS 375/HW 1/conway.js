function stepBoard(board) {
  // If the board is empty or has empty rows, return it as is
  if (board.length === 0 || board[0].length === 0) {
    return board;
  }

  let result = [];

  for (let i = 0; i < board.length; i++) {
    let row = [];
    for (let j = 0; j < board[0].length; j++) {
      let neighbors = countNeighbors(board, i, j);
      let isAlive = board[i][j];

      if (isAlive) {
        if (neighbors === 2 || neighbors === 3) {
          row.push(true);
        } else {
          row.push(false);
        }
      } else {
        if (neighbors === 3) {
          row.push(true);
        } else {
          row.push(false);
        }
      }
    }
    result.push(row);
  }

  return result;
}

function countNeighbors(board, row, col) {
  let count = 0;

  for (let i = -1; i <= 1; i++) {
    for (let j = -1; j <= 1; j++) {
      if (i === 0 && j === 0) {
        continue;
      }

      let newRow = row + i;
      let newCol = col + j;

      if (
        newRow >= 0 &&
        newRow < board.length &&
        newCol >= 0 &&
        newCol < board[0].length
      ) {
        if (board[newRow][newCol]) {
          count++;
        }
      }
    }
  }

  return count;
}
