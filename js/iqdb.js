import {Toggler} from "./toggler";
import {InspirationalQuoteHandler} from "./inspirationalQuotehandler";
import {AlertBox} from "./alertBox";
import {FormHandler} from "./formHandler";

function formFieldTrimmer(elementId) {
    document.getElementById(elementId).addEventListener("focusout", (event) => {
        event.target.value = event.target.value.trim()
    });
}

document.addEventListener("DOMContentLoaded", (event) => {
    formFieldTrimmer("quote");
    formFieldTrimmer("author");

    const alertBox = new AlertBox("alert-display", "alert-box-text", "alert-button");
    const inspirationalQuoteHandler = new InspirationalQuoteHandler("quote-container", "toggle-container", "next-button");
    const toggler = new Toggler(inspirationalQuoteHandler);
    const formHandler = new FormHandler(alertBox);

    document.getElementById("auto-advance-toggle").addEventListener("change", toggler.handleToggleClick);
    document.getElementById("next-button").addEventListener("click", inspirationalQuoteHandler.getNextQuote);
    document.getElementById("new-quote-form").addEventListener("submit", formHandler.handleSubmit);
});
