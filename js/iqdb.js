import {handleToggleClick} from "./toggler";
import {InspirationalQuoteHandler} from "./inspirationalQuotehandler";


//const autoadvanceTimer = setInterval(getData, 10000, alreadySeen);



async function handleSubmit(event) {
    console.log("SUBMITTING")
    event.preventDefault();
    const form = event.currentTarget;
    const formData = new FormData(form);
    const plainFormData = Object.fromEntries(formData.entries());
    const formDataJsonString = JSON.stringify(plainFormData);
    console.log("DATA :  " + formDataJsonString)

    const url = "/inspirational-quote/create";
    try {
        const response = await fetch(url, {
            method: 'POST',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json; charset=utf-8'
            },
            body: formDataJsonString,
        });
        if (!response.ok) {
            throw new Error(`Response status: ${response.status}`);
        }
        const status = response.status;


        if (status === 200) {
            console.log("saved");

        }

    } catch (error) {
        console.error(error.message);
    }
}

function fieldTrimmer(elementId) {
    document.getElementById(elementId).addEventListener("focusout", (event) => {
        event.target.value = event.target.value.trim()
    });
}

document.addEventListener("DOMContentLoaded", (event) => {
    fieldTrimmer("quote");
    fieldTrimmer("author");
    const inspirationalQuoteHandler = new InspirationalQuoteHandler();

    document.getElementById("auto-advance-toggle").addEventListener("change", handleToggleClick);
    document.getElementById("next-button").addEventListener("click", inspirationalQuoteHandler.getNextQuote);
    document.getElementById("new-quote-form").addEventListener("submit", handleSubmit);
});
