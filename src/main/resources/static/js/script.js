
/*-------- new content below ---------*/
    const days = ["Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun"];
    const months = ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"];


    // date.js library
    const date = new Date();
    const cday = date.getDate();
    const wday = days[date.getDay()];
    const month = months[date.getMonth()];
    const year = date.getFullYear();


    console.log(date);
    console.log(cday);
    console.log("weekday: " + wday);
    console.log("calendar: " + month + "/" + cday + "/" + year);

    document.querySelector(".date h1").innerHTML = month;
    document.querySelector(".date p").innerHTML = date.toDateString();

    const lastDay = new Date(date.getFullYear(), date.getMonth() + 1, 0).getDate();
    console.log("last day of the month: " + lastDay);


    // calendar first day starts at what index ?
    // date.setDate(1);

    // const firstDayIndex = date.getDay();
    // console.log(firstDayIndex);




/*-------- existing content below ---------*/

// add event handler to the target html element
document.addEventListener('DOMContentLoaded', () => {
    const isWeekend = day => {
        // highlight saturday & sunday
        // 6 when its saturday, 0 when its sunday
        return day % 7 === 6 || day % 7 === 0;
    }


    const getDayName = day => {
        // javascript datetime format
        const date = new Date(Date.UTC(2022, 0, day));
        // internationalization of datetime format
        return new Intl.DateTimeFormat("en-US", { weekday: "short" }).format(date);

    }

    // returns element with specified id
    const calendar = document.getElementById("app-calendar");
    for (let day = 1; day <= 31; day++){
        const weekend = isWeekend(day);
        let name = "";
        if (day <= 7){
            const dayName = getDayName(day);
            name = `<div class="name">${dayName}</div>`;    //template literals
        }


        // adding node into DOM tree
        // parses the specified text as HTML and inserts the resulting nodes into the DOM tree at specified position
        calendar.insertAdjacentHTML("beforeend",
        `<div class="day ${weekend ? "weekend" : ""}">
          ${name}${day}</div>`);
    }

    // used to return a static NodeList of elements that match the css selector
    document.querySelectorAll("#app-calendar .day").forEach
    (day => {
        // javascript event handler (event, function-to-run)        ...function to run when event is invoked
        // target.addEventListener("event", function)
        // target: html element
        day.addEventListener("click", event => {
            // classList property returns css class names of an element
            // toggle() method toggle between hide() and show() for the selected elements
            event.currentTarget.classList.toggle("selected");
        })
    })
})