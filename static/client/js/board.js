/**
 * This class manages the graphical view of the game board.  Clicks on
 * the game board will cause the cell location to print to the browser console (see
 * mouseClickOnBoard().
 */
export class Board {

    constructor() {
        this.canvas = document.getElementById('game-canvas');

        // Load star image
        this.starImage = new Image();
        this.starImage.onload = () => this.draw();
        this.starImage.src = "img/star_gold.png";

        this.layout = [
            [1, 1, 1, 1, 1, 1, 1, 1, 2, 2],
            [1, 1, 1, 1, 1, 1, 5, 1, 2, 2],
            [3, 4, 4, 4, 1, 5, 5, 5, 2, 2],
            [3, 3, 3, 3, 6, 7, 7, 5, 2, 2],
            [3, 3, 3, 6, 6, 6, 7, 7, 2, 2],
            [3, 3, 8, 6, 6, 6, 7, 7, 2, 2],
            [3, 3, 8, 8, 8, 7, 7, 7, 9, 2],
            [3, 3, 3, 3, 3, 3, 7, 7, 9, 2],
            [3, 10, 10, 10, 10, 10, 10, 10, 9, 9],
            [10, 10, 10, 10, 10, 10, 10, 10, 10, 9]
        ];

        this.stars = [
            [false, false, false, false, false, false, false, false, false, false],
            [false, false, false, false, false, false, false, false, false, false],
            [false, false, false, false, false, false, false, false, false, false],
            [false, false, false, false, false, false, false, false, false, false],
            [false, false, false, false, false, false, false, false, false, false],
            [false, false, false, false, false, false, false, false, false, false],
            [false, false, false, false, false, false, false, false, false, false],
            [false, false, false, false, false, false, false, false, false, false],
            [false, false, false, false, false, false, false, false, false, false],
            [false, false, false, false, false, false, false, false, false, false]
        ];

        // Add an event listener to the canvas to be triggered on mouse click.  When a
        // click occurs, it will call the function mouseClickOnBoard.
        //
        // Important note: when using **instance methods** as event listeners, you should wrap with
        // a lambda function as shown below.
        this.canvas.addEventListener('click', (e) => this.mouseClickOnBoard(e) );
    }

    draw() {
        const ctx = this.canvas.getContext('2d');

        // Draw the Board's background and grid
        this.drawBoard(ctx);

        this.drawLayout(ctx);
        // Draw some pieces
        this.drawExamplePieces(ctx);
    }

    /**
     * Draws the game board.  For details on how to draw with a CanvasRenderingContext2D,
     * see this:  https://developer.mozilla.org/en-US/docs/Web/API/CanvasRenderingContext2D
     */
    drawBoard(ctx) {
        const width = this.canvas.width;
        const height = this.canvas.height;
        const cellSize = width / 10.0;  // Assume width = height

        // Clear the canvas
        ctx.clearRect(0, 0, width, height);

        // Set the style for the grid lines
        ctx.strokeStyle = "black";
        ctx.lineWidth = 1.5;

        // Draw the grid lines
        ctx.beginPath();
        // Vertical lines
        for( let i = 1; i < 10; i++ ) {
            const x = i * cellSize;
            ctx.moveTo(x, 0);
            ctx.lineTo(x, height);
        }
        // Horizontal lines
        for( let i = 1; i < 10; i++ ) {
            const y = i * cellSize;
            ctx.moveTo(0, y);
            ctx.lineTo(width, y);
        }
        ctx.closePath();
        ctx.stroke();

        // Draw a thicker edge
        ctx.lineWidth = 4.5;
        ctx.beginPath();
        ctx.moveTo( 0 * cellSize, 0 * cellSize );
        ctx.lineTo( 10 * cellSize, 0 * cellSize );
        ctx.lineTo( 10 * cellSize, 10 * cellSize );
        ctx.lineTo( 0 * cellSize, 10 * cellSize );
        ctx.lineTo( 0 * cellSize, 0 * cellSize );
        ctx.stroke();
        ctx.closePath();


    }

    drawLayout(ctx) {
        const width = this.canvas.width;
        const height = this.canvas.height;
        const cellSize = width / 10.0;  // Assume width = height
        ctx.beginPath();
        for (let i = 0; i < 10; i++) {
            for (let j = 0; j < 10; j++) {
                const curr = this.layout[j][i];

                if (i > 0 && curr != this.layout[j][i-1]) { // Draw line to the left
                    ctx.moveTo( i * cellSize, j * cellSize );
                    ctx.lineTo( i * cellSize, (j+1) * cellSize );
                }

                if (i < 9 && curr != this.layout[j][i+1]) { // Draw line to the right
                    ctx.moveTo( (i+1) * cellSize, j * cellSize );
                    ctx.lineTo( (i+1) * cellSize, (j+1) * cellSize );
                }

                if (j > 0 && curr != this.layout[j-1][i]) { // Draw line above
                    ctx.moveTo( i * cellSize, j * cellSize );
                    ctx.lineTo( (i+1) * cellSize, j * cellSize );
                }

                if (j < 9 && curr != this.layout[j+1][i]) { // Draw line below
                    ctx.moveTo( i * cellSize, (j+1) * cellSize );
                    ctx.lineTo( (i+1) * cellSize, (j+1) * cellSize );
                }
            }
        }
        ctx.closePath();
        ctx.stroke();
    }

    drawExamplePieces(ctx) {
        // Draw a few pieces as examples
        this.drawPiece(ctx, 0, 0 );
        this.drawPiece(ctx, 5, 8 );
        this.drawPiece(ctx, 3, 2 );
        this.drawPiece(ctx, 9, 1 );
    }

    /**
     * Draw a single piece on the board.
     *
     * @param ctx the 2D graphics context
     * @param {*} x the x cell number (zero based)
     * @param {*} y the y cell number (zero based)
     */
    drawPiece(ctx, x, y) {
        if (!this.canDrawStar(x, y)) {
            return;
        }
        const width = this.canvas.width;
        const height = this.canvas.height;
        const cellSize = width / 10.0;  // Assume width = height

        const cx = x * cellSize;
        const cy = y * cellSize;

        ctx.drawImage(this.starImage, cx, cy);

        this.stars[y][x] = true;
    }

    canDrawStar(x, y) {
        const curr = this.layout[y][x];
        let numStarsInSection = 0;
        for (let i = 0; i < 10; i++) {
            for (let j = 0; j < 10; j++) {
                if (this.layout[j][i] == curr) {
                    if (this.stars[j][i] == true) {
                        numStarsInSection++;
                        if (numStarsInSection > 1) {
                            console.log("Too many stars!");
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * Called when a mouse click occurs on the game board.  Currently, just
     * prints the cell corresponding to the click to the Javascript console.
     *
     * @param {MouseEvent} e the mouse event
     */
    mouseClickOnBoard(e) {
        const width = this.canvas.width;
        const height = this.canvas.height;
        const cellSize = width / 10.0;  // Assume width = height

        let mouseX = e.offsetX;
        let mouseY = e.offsetY;

        // Clamp mouse coordinates to valid range
        mouseX = Math.max( Math.min(mouseX, width - 1), 0);
        mouseY = Math.max( Math.min(mouseY, height - 1), 0);

        // Find and print the cell number for the click
        const cellX = Math.floor( mouseX / cellSize );
        const cellY = Math.floor( mouseY / cellSize );

        console.log(`Click on cell: (${cellX}, ${cellY})`);

        this.drawPiece(this.canvas.getContext('2d'), cellX, cellY);
    }

}