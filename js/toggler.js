export function handleToggleClick() {
    if (this.checked) {
        document.getElementById("toggle-indicator-text").innerText = "on";
    } else {
        document.getElementById("toggle-indicator-text").innerText = "off";
    }
}