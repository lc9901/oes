function creatQuestion() {
    var isSubmit = true;
    var questionDescription = document.getElementById("questionDescription");
    var answer = document.getElementById("answer");
    var opt_detail_a = document.getElementById("opt_detail_a");
    var opt_detail_b = document.getElementById("opt_detail_b");
    var opt_detail_c = document.getElementById("opt_detail_c");
    var opt_detail_d = document.getElementById("opt_detail_d");

    if (!questionDescription.value.trim()) {
        isSubmit = false;
        document.getElementById("tip_massage_detail_1").style.visibility = "visible";
    } else {
        document.getElementById("tip_massage_detail_1").style.visibility = "hidden";
    }

    if (!opt_detail_a.value.trim() || !opt_detail_b.value.trim() || !opt_detail_c.value.trim() || !opt_detail_d.value.trim()) {
        isSubmit = false;
        document.getElementById("tip_massage_detail_2").style.visibility = "visible";
    } else {
        document.getElementById("tip_massage_detail_2").style.visibility = "hidden";
    }

    if (answer.value === "4") {
        isSubmit = false;
        document.getElementById("tip_massage_detail_3").style.visibility = "visible";
    } else {
        document.getElementById("tip_massage_detail_3").style.visibility = "hidden";
    }

    if (isSubmit) {
        var newQuestion = document.getElementById("submitQuestion");
        newQuestion.submit();
    }
}

function clickRadio(count, root) {
    var elementById = document.getElementById("opt_radio_a");
    elementById.setAttribute("src", root + "images/BTN_Radio_Unselected_16x16.png");
    elementById = document.getElementById("opt_radio_b");
    elementById.setAttribute("src", root + "images/BTN_Radio_Unselected_16x16.png");
    elementById = document.getElementById("opt_radio_c");
    elementById.setAttribute("src", root + "images/BTN_Radio_Unselected_16x16.png");
    elementById = document.getElementById("opt_radio_d");
    elementById.setAttribute("src", root + "images/BTN_Radio_Unselected_16x16.png");

    elementById = document.getElementById("opt_radio_" + count);
    elementById.setAttribute("src", root + "images/BTN_Radio_Selected_16x16.png");
    elementById = document.getElementById("answer");
    elementById.setAttribute("value", count);
}

function goBack() {
    location.href = "list";
}

function onkeydown() {
    if(event.keyCode == 13) {
        document.getElementById("Create").click();
        return false;
    }
}

function goBackDetail(id){
    location.href = id +"?currentId=" + id;
}

function saveQuestion() {
    var isSubmit = true;
    var questionDescription = document.getElementById("questionDescription");
    var answer = document.getElementById("answer");
    var opt_detail_a = document.getElementById("opt_detail_a");
    var opt_detail_b = document.getElementById("opt_detail_b");
    var opt_detail_c = document.getElementById("opt_detail_c");
    var opt_detail_d = document.getElementById("opt_detail_d");

    if (!questionDescription.value.trim()) {
        isSubmit = false;
        document.getElementById("tip_massage_detail_1").style.visibility = "visible";
    } else {
        document.getElementById("tip_massage_detail_1").style.visibility = "hidden";
    }

    if (!opt_detail_a.value.trim() || !opt_detail_b.value.trim() || !opt_detail_c.value.trim() || !opt_detail_d.value.trim()) {
        isSubmit = false;
        document.getElementById("tip_massage_detail_2").style.visibility = "visible";
    } else {
        document.getElementById("tip_massage_detail_2").style.visibility = "hidden";
    }

    if (answer.value === "4") {
        isSubmit = false;
        document.getElementById("tip_massage_detail_3").style.visibility = "visible";
    } else {
        document.getElementById("tip_massage_detail_3").style.visibility = "hidden";
    }

    if (isSubmit) {
        var editQuestion = document.getElementById("submitQuestion");
        editQuestion.submit();
    }
}