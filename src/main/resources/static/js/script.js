// Use luxon instead of DateTime
const DateTime = luxon.DateTime;

// fire event when entire document is loaded
document.addEventListener('DOMContentLoaded', () => {

    const calendar = document.getElementById("app-calendar");
    if(calendar){

        const selectedDates = new Set();

        const isWeekend = day => {
            // 6 when its saturday, 0 when its sunday
            return day % 7 === 6 || day % 7 === 0;
        }

        // return day names of the week on calendar
        const getDayName = day => {
            const date = new Date(Date.UTC(2022, 1, day));
            // internationalization of datetime format
            return new Intl.DateTimeFormat("en-US", { weekday: "short" }).format(date);
        }

        // add day names of the week just on the top first week on calendar

        const targetInput = document.getElementById(calendar.dataset.target);

        for (let day = 1; day <= 31; day++){
            const weekend = isWeekend(day);
            let name = "";
            if (day <= 7){
                const dayName = getDayName(day);
                name = `<div class="name">${dayName}</div>`;
            }

            calendar.insertAdjacentHTML("beforeend",
            `<div class="day ${weekend ? "weekend" : ""}" data-date="2022-02-${day.toString().padStart(2,'0')}">
              ${name}${day}</div>`);
        }

        // toggle select or unselect day(s) on calendar
        document.querySelectorAll("#app-calendar .day").forEach
        (day => {
            day.addEventListener("click", event => {
                // classList property returns css class names of an element
                // toggle() method toggle between hide() and show() for the selected elements
                event.currentTarget.classList.toggle("selected");
                const dateString = day.dataset.date;
                if(selectedDates.has(dateString)) {
                    console.log(`${dateString} has already been selected`);
                    selectedDates.delete(dateString)
                } else {
                    selectedDates.add(dateString);
                    console.log(`${dateString} added to selected dates`);
                }
                targetInput.value = Array.from(selectedDates).join(',');
            })
        })
    }

    const serverTimeTextSpans = document.querySelectorAll(".server-time-text");
    serverTimeTextSpans.forEach((serverTimeTextSpan)=>{
        const formatOption = serverTimeTextSpan.dataset.format;
        let timeString = serverTimeTextSpan.dataset.serverTime;
        timeString = timeString.split(' ').join('T');

        let dateObj = DateTime.fromISO(timeString);

        if(!formatOption || formatOption === 'full' ){
            serverTimeTextSpan.innerText = dateObj.toLocaleString({ weekday: 'short', month: 'short', day: '2-digit', hour: '2-digit', minute: '2-digit' });
        } else if (formatOption === 'date') {
            serverTimeTextSpan.innerText = dateObj.toLocaleString()
        } else if (formatOption === 'time') {
             serverTimeTextSpan.innerText = dateObj.toLocaleString(DateTime.TIME_SIMPLE);
        }

    })

//    button function for time slots in viewEvent

    let timeSlotBtns = document.getElementById("timeChunkBtn");

        (timeSlotBtns).click(function(){
        (timeSlotBtns).addClass("active");
    })


    const attendeeAvailabilityGraphRows = document.querySelectorAll(".attendeeAvailabilityGraphRow");
    attendeeAvailabilityGraphRows.forEach((attendeeAvailabilityGraphRow)=>{
            const attendeeList = attendeeAvailabilityGraphRow.dataset.attendees.split(",");
            attendeeAvailabilityGraphRow.innerText = attendeeList.length;


             for(let i=0; i < attendeeList.length; i++) {
                attendeeAvailabilityGraphRow.innerHTML += '<div class="attendeeAvailabilityGraphRow col-md">'
                 + attendeeList[i] + '</div>';
                    }

})

});