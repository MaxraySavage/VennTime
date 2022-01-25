//import { isWeekend, getDayName } from "./date-helper.js";
document.addEventListener('DOMContentLoaded', () => {
    const isWeekend = day => {
        // highlight saturday & sunday
        // 6 when its saturday, 0 when its sunday
        return day % 7 === 6 || day % 7 === 0;
    }


    const getDayName = day => {
        const date = new Date(Date.UTC(2022, 0, day));
        return new Intl.DateTimeFormat("en-US", { weekday: "short" }).format(date);

    }

    const calendar = document.getElementById("app-calendar");

    for (let day = 1; day <= 31; day++){

        const weekend = isWeekend(day);

        let name = "";
        if (day <= 7){
            const dayName = getDayName(day);
            name = `<div class="name">${dayName}</div>`;
        }


        // 31 days divs
        // adding conditional class 'weekend'
        // conditional: if weekend = true, return string 'weekend'
        // after colon: if not, return empty string aka no class
        calendar.insertAdjacentHTML("beforeend",
        `<div class="day ${weekend ? "weekend" : ""}">
          ${name}${day}</div>`);
    }

    document.querySelectorAll("#app-calendar .day").forEach
    (day => {
        day.addEventListener("click", event => {
            event.currentTarget.classList.toggle("selected");
        })
    })
})
