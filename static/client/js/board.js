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
        ctx.moveTo( 3 * cellSize, cellSize );
        ctx.lineTo( 3 * cellSize, 4 * cellSize );
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
        const width = this.canvas.width;
        const height = this.canvas.height;
        const cellSize = width / 10.0;  // Assume width = height

        const cx = x * cellSize;
        const cy = y * cellSize;

        ctx.drawImage(this.starImage, cx, cy);
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
    }

}