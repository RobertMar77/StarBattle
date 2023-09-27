import {GameView} from './game-view.js';

/**
 * The GameClient object represents the overall client application.
 */
export class GameClient {

    constructor() {
        // Objects that manage each view. Add other objects to manage controls
        this.gameView = new GameView();
    }

}