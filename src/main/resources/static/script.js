// function checkOverdue() {
//     let dueDateString = document.getElementsByClassName("dueDateTask");
//     let today = new Date();
//     for (let i = 0; i < dueDateString.length; i++) {
//         const dueDate = new Date(dueDateString[i].innerHTML)
//         if (dueDate < today) {
//             dueDateString[i].style.color = "red";
//         }
//     }
// }
// checkOverdue();
//
// function categoryAdded() {
//     let newCategory = document.getElementById("categoryName").innerHTML;
//     alert("Dodano nową kategorię")
// }

function checkCategories() {
    let inputGroupSelect01 = document.getElementById("inputGroupSelect01");
    let newCategory = document.getElementById("newCategory");
    // let endTime = document.getElementsByClassName("taskEndTime");
    // let doneButton = document.getElementsByName("taskDoneButton");
    // let startButton = document.getElementsByName("taskStartButton");
    // let endButton = document.getElementsByName("taskEndButton");

    // for (let i = 0; i < startTime.length; i++) {
    //     if (startTime[i].value !== '') {
    //         endButton[i].disabled = false;
    //         startButton[i].disabled = true;
    //         doneButton[i].disabled = true;
    //     }
    // }
}
checkCategories();
