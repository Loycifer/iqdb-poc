export class Toggler {

    #inspirationalQuoteHandler;

    constructor(inspirationalQuoteHandler) {
        this.#inspirationalQuoteHandler = inspirationalQuoteHandler;
    }

    handleToggleClick = (event) => {
        if (event.target.checked) {
            this.#inspirationalQuoteHandler.autoAdvanceOn();
        } else {
            this.#inspirationalQuoteHandler.autoAdvanceOff();
        }
    }
}