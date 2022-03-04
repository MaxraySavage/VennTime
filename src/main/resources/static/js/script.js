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
                let classes = '';

                classes += isWeekend ? 'weekendDays' : ''
                classes += selectedDates.has(nextDayToAddToCalendar.toISODate()) ? ' selected' : ''

                let htmlStringToAddToCalendar = `<div class="dayOfMonth ${classes}" ${dataDateProperty}>`;

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

        let calendarStartDay = DateTime.now().set({ day: 1});

        renderCalenderMonth(calendarStartDay);

        document.querySelector('.prev').addEventListener('click', () => {
            calendarStartDay = calendarStartDay.minus({ months: 1 })
            renderCalenderMonth(calendarStartDay);
        })


        document.querySelector('.next').addEventListener('click', () => {
            calendarStartDay = calendarStartDay.plus({ months: 1 })
            renderCalenderMonth(calendarStartDay);
        })

    }

    const serverTimeTextSpans = document.querySelectorAll(".server-time-text");
    serverTimeTextSpans.forEach((serverTimeTextSpan)=>{
        const formatOption = serverTimeTextSpan.dataset.format;
        let timeString = serverTimeTextSpan.dataset.serverTime;
        timeString = timeString.split('[')[0];

        let dateObj = DateTime.fromISO(timeString);

        if(!formatOption || formatOption === 'full' ){
            serverTimeTextSpan.innerText = dateObj.toLocaleString({ weekday: 'short', month: 'short', day: '2-digit', hour: '2-digit', minute: '2-digit' });
        } else if (formatOption === 'date') {
            serverTimeTextSpan.innerText = dateObj.toLocaleString()
        } else if (formatOption === 'time') {
             serverTimeTextSpan.innerText = dateObj.toLocaleString(DateTime.TIME_SIMPLE);
        }

    })

    const availableTimeBtns = document.querySelectorAll(".inputTimes");
    availableTimeBtns.forEach((availableTimeBtn) => {
        let checkBox = availableTimeBtn.querySelector(".inputAvailabilityBlock");
        availableTimeBtn.addEventListener("click", function () {
            if (checkBox.checked === true) {
                availableTimeBtn.classList.add("active");
            } else {
                availableTimeBtn.classList.remove("active");
            }
        })
    })

  // Fetch all the forms we want to apply custom Bootstrap validation styles to
  var forms = document.querySelectorAll('.needs-validation')

  // Loop over them and prevent submission
  Array.prototype.slice.call(forms)
    .forEach(function (form) {
      form.addEventListener('submit', function (event) {
        if (!form.checkValidity()) {
          event.preventDefault()
          event.stopPropagation()
        }

        form.classList.add('was-validated')
      }, false)
    })

})