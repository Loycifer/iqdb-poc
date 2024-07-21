import {postRequest} from "./postRequest";

export class InspirationalQuoteHandler {
    #alreadySeen = [];
    #autoAdvanceTimer;
    #introTimer;
    #introFinished = false;
    #targetElement;
    #toggleContainer;
    #nextButton

    #introText = [
        "Feeling down?",
        "Need a quote that inspires?",
        "I got you.",
        "I got you, fam.",
        "Here they come...",
    ]

    constructor(quoteContainerId, toggleContainerId, nextButtonId) {
        this.#targetElement = document.getElementById(quoteContainerId);
        this.#toggleContainer = document.getElementById(toggleContainerId);
        this.#nextButton = document.getElementById(nextButtonId);
        this.intro();
    }

    intro() {
        this.displayText(this.#introText.shift())
        this.#introTimer = setInterval(this.printIntroText, 3000);
    }

    printIntroText = () => {
        if (this.#introText.length === 0) {
            this.finishIntro();
            this.getNextQuote();
        }
        this.displayText(this.#introText.shift())
    }

    finishIntro() {
        this.#toggleContainer.classList.remove("hidden")
        this.#introFinished = true;
        clearInterval(this.#introTimer);
    }

    autoAdvanceOn() {
        this.#autoAdvanceTimer = setInterval(this.getNextQuote, 7000);
        this.#nextButton.classList.add("hidden");
    }

    autoAdvanceOff() {
        clearInterval(this.#autoAdvanceTimer);
        this.#nextButton.classList.remove("hidden");
    }


    #resetAnimation() {
        this.#targetElement.classList.remove("fade-in-text");
        this.#targetElement.offsetHeight;
        this.#targetElement.classList.add("fade-in-text");
    }

    #displayQuote(jsonResponse) {
        this.#targetElement.innerText = `"${jsonResponse.quote}" ~ ${jsonResponse.author} `;
    }

    #handleResponse(jsonResponse) {
        this.#resetAnimation();
        this.#displayQuote(jsonResponse);
        this.#alreadySeen.push(jsonResponse.id);
    }

    displayText(text) {
        this.#resetAnimation();
        this.#targetElement.innerText = text;
    }

    getNextQuote = async () => {
        if (!this.#introFinished) {
            this.finishIntro();
        }
        const url = "/inspirational-quote/random";
        const alreadySeen = this.#alreadySeen;
        const alreadySeenJSON = JSON.stringify({"alreadySeen": alreadySeen})
        try {
            const response = await fetch(url, postRequest(alreadySeenJSON));
            if (!response.ok) {
                throw new Error(`Response status: ${response.status}`);
            }
            const status = response.status;

            if (status === 200) {
                const json = await response.json();
                this.#handleResponse(json)
            }

            if (status === 204) {
                this.#alreadySeen.splice(0, this.#alreadySeen.length);
                this.displayText("I'm all out of wisdom.  Add some of your own quotes below!")
            }


        } catch (error) {
            console.error(error.message);
        }
    }
}