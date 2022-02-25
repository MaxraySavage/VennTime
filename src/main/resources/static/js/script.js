// Use luxon instead of DateTime
const DateTime = luxon.DateTime;

// fire event when entire document is loaded
document.addEventListener('DOMContentLoaded', () => {

    const calendar = document.getElementById("app-calendar");
    if(calendar){
        const calendarMonthDisplay = document.getElementById("calendar-month-display");

        const selectedDates = new Set();

        const targetInput = document.getElementById(calendar.dataset.target);

        const renderCalenderMonth = (firstDayOfMonth) => {
            calendarMonthDisplay.innerText = `${firstDayOfMonth.monthLong} ${firstDayOfMonth.year}`
            calendar.innerHTML = '';

            // Get starting day of the week
            // 1 is Monday and 7 is Sunday
            const startingWeekday = calendarStartDay.weekday

            // number of days we have to go to get to a monday
            // for example if startingWeekday is 3 for wednesday
            // 1 - 3 is negative 2, so we have to go back two days to be at monday
            let dayOffset = 1 - startingWeekday

            // add ending days from preceding month to calendar
            while(dayOffset < 0) {
                const nextDayToAddToCalendar = firstDayOfMonth.plus({ days: dayOffset })

                const nameOfWeekdayHTML = `<div class="nameOfWeek">${nextDayToAddToCalendar.weekdayShort}</div>`;

                calendar.insertAdjacentHTML("beforeend",
                        `<div class="prevDays cola">${nameOfWeekdayHTML}${nextDayToAddToCalendar.day}</div>`);

                dayOffset += 1;
            }

            for(let i = 1; i <= firstDayOfMonth.daysInMonth; i += 1){
                const nextDayToAddToCalendar = firstDayOfMonth.set({ day: i });
                const isWeekend = nextDayToAddToCalendar.weekday > 5;
                const inFirstCalendarRow = nextDayToAddToCalendar.startOf('week') <= firstDayOfMonth;

                const dataDateProperty = `data-date="${nextDayToAddToCalendar.toISODate()}"`

                let htmlStringToAddToCalendar = `<div class="dayOfMonth cola ${isWeekend ? 'weekendDays' : ''}" ${dataDateProperty}>`;

                if(inFirstCalendarRow) {
                    htmlStringToAddToCalendar += `<div class="nameOfWeek">${nextDayToAddToCalendar.weekdayShort}</div>`;
                }

                htmlStringToAddToCalendar += `${nextDayToAddToCalendar.day}</div>`;

                if(nextDayToAddToCalendar.weekday == 7) {
                    //htmlStringToAddToCalendar += '<div class="w-100"></div>'
                }

                calendar.insertAdjacentHTML("beforeend",htmlStringToAddToCalendar);
            }

            // fill out the rest of last week
            const lastDayOfMonth = firstDayOfMonth.endOf('month')

            for(let i = 1; i <= 7 - lastDayOfMonth.weekday; i++){
                const nextDayToAddToCalendar = lastDayOfMonth.plus({ days: i })

                calendar.insertAdjacentHTML("beforeend",
                        `<div class="nextDays cola">${nextDayToAddToCalendar.day}</div>`);

            }

            document.querySelectorAll("#app-calendar .dayOfMonth").forEach(day => {
                day.addEventListener("click", event => {
                    // classList property returns css class names of an element
                    // toggle() method toggle between hide() and show() for the selected elements
                    event.currentTarget.classList.toggle("selected");
                    const dateString = day.dataset.date;
                    if(selectedDates.has(dateString)) {
                        selectedDates.delete(dateString)
                    } else {
                        selectedDates.add(dateString);
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
          // attendeeAvailabilityGraphRow.innerText = attendeeList.length;

//
//for(let i=0; i < attendeeList.length; i++) {
//
//           return attendeeList[i] }




//   const name = attendeeList[i].toUpperCase.split('');
//   const firstInitial = name.substring(0,1);


 attendeeList.forEach(function(attendee) {
    let firstInitial = attendee.trim().charAt(0) ;


attendeeAvailabilityGraphRow.innerHTML += `<div class="progress-bar" style="width: 15%;" role="progressbar"  aria-valuenow="15" aria-valuemin="0" aria-valuemax="100">
                  ${firstInitial} </div>`;
                        })
});

})