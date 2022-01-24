


const isWeekend = day => {
    // highlight saturday & sunday
    // 6 when its saturday, 0 when its sunday
    return day % 7 === 6 || day % 7 === 0; 
}


const getDayName = day => {
    const date = new Date(Date.UTC(2022, 0, day)); 
    return new Intl.DateTimeFormat("en-US", { weekday: "short" }).format(date); 

}


export {isWeekend, getDayName}