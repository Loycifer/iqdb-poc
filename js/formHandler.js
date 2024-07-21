import {postRequest} from "./postRequest";

export class FormHandler {
    #alertBox;

    constructor(alertBox) {
        this.#alertBox = alertBox;
    }

    handleSubmit = async (event) => {
        event.preventDefault();
        const form = event.currentTarget;
        const formData = new FormData(form);
        const plainFormData = Object.fromEntries(formData.entries());
        const formDataJsonString = JSON.stringify(plainFormData);

        const url = "/inspirational-quote/create";
        try {
            const response = await fetch(url, postRequest(formDataJsonString));
            if (!response.ok) {
                throw new Error(`Response status: ${response.status}`);
            }
            const status = response.status;


            if (status === 200) {
                this.#alertBox.showMessage("Quote successfully saved! It belongs to me now.")
                form.reset();
            } else {
                this.#alertBox.showMessage("Something went wrong.  It's not my fault.")
            }

        } catch (error) {
            console.error(error.message);
        }
    }
}