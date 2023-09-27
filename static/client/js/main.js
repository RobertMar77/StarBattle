
// Import statements are the ways that we gain access to exported names in
// other JS files when using ES6 modules.
import {GameClient} from './game-client.js';

// Call the main function when the DOM is loaded
window.addEventListener('load', main);

/**
 * The entry point for your application.  This will be called once the 
 * HTML DOM is fully loaded.
 */
function main() {
    const gameClient = new GameClient();
}
