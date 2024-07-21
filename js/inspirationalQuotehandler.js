import {postRequest} from "./postRequest";

export class InspirationalQuoteHandler {
    #alreadySeen = [];
    #autoAdvanceTimer;
    #introTimer;
    #targetElement;

    #introText = [
        "Feeling down?",
        "Need a quote that inspires?",
        "I got you.",
        "I got you, fam.",
        "Here they come...",
    ]

    constructor() {
        this.#targetElement = document.getElementById("quoteContainer");
        this.intro();
    }

    intro() {
        this.displayText(this.#introText.shift())
        this.#introTimer = setInterval(this.printIntroText, 3000);
    }

    printIntroText = () => {
        if (this.#introText.length === 0) {
            clearInterval(this.#introTimer)
            this.getNextQuote();
        }
        this.displayText(this.#introText.shift())
    }

    #resetAnimation() {
        this.#targetElement.classList.remove("fade-in-text");
        this.#targetElement.offsetHeight;
        this.#targetElement.classList.add("fade-in-text");
    }

    #displayQuote(jsonResponse) {
        this.#targetElement.innerText = `"${jsonResponse.quote}" ~${jsonResponse.author} `;
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

        } catch (error) {
            console.error(error.message);
        }
    }
}