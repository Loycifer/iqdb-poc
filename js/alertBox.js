export class AlertBox {
    #alertContainer;
    #alertBox;
    #alertButton;

    constructor(alertContainerId, alertBoxTextId, alertButtonId) {
        this.#alertContainer = document.getElementById(alertContainerId);
        this.#alertBox = document.getElementById(alertBoxTextId);
        this.#alertButton = document.getElementById(alertButtonId)

        this.#alertButton.addEventListener("click", () => {
            this.#alertContainer.classList.add("hidden");
        })
    }

    showMessage(text) {
        this.#alertBox.innerText = text;
        this.#alertContainer.classList.remove("hidden");
    }
}