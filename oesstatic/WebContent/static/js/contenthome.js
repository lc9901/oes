$("#goTarget").click(function() {
    var pageCount = $("#pageCount").val();
    var check = /^([1-9][0-9]*){1,3}$/;
    var currentPage = $("#tag_page");
    var orderBy = $("#orderBy");
    var search = $("#search");
    if (check.test(currentPage.val())) {
        var pageSize = $("#page_select");
        if (currentPage.val() > pageCount) {
            location.href = "list?currentPage=" + pageCount + "&pageSize=" + pageSize.val() + "&orderBy=" +orderBy + "&search=" + search.val();
        } else {
            location.href = "list?currentPage=" + currentPage.val() + "&pageSize=" + pageSize.val() + "&orderBy=" +orderBy + "&search=" + search.val();
        }
    } else {
        alert("Please input a number");
    }
});

$("#order").click(function() {
    var root = $("#root").attr("value");
    var currentPage = $("#current_page");
    var pageSize = $("#page_select");
    var search = $("#search");
    var orderBy = "";
    if ($(".li_id").children().eq(0).attr("src") === root + "images/ICN_Increase_10x15.png") {
        orderBy = "DESC";
    } else {
        orderBy = "ASC";
    }
    location.href = "list?currentPage=" + currentPage.val() + "&pageSize=" + pageSize.val() + "&orderBy=" +orderBy + "&search=" + search.val();
});

$("#searchBtu").click(function() {
    var currentPage = $("#current_page");
    var pageSize = $("#page_select");
    var orderBy = $("#orderBy");
    var search = $("#search");
    location.href = "list?currentPage=" + currentPage.val() + "&pageSize=" + pageSize.val() + "&orderBy=" +orderBy.val() + "&search=" + search.val();
});

$("#page_select").change(function() {
    var currentPage = $("#current_page");
    var pageSize = $("#page_select");
    var orderBy = $("#orderBy");
    var search = $("#search");
    location.href = "list?currentPage=" + currentPage.val() + "&pageSize=" + pageSize.val() + "&orderBy=" +orderBy.val() + "&search=" + search.val();
});

$("#questionList").on("click", "img[name='opera_status']", function() {
    var root = $("#root").attr("value");
    var currentItem = $(this);
    if ( currentItem.attr("src") === root + "images/ICN_Unselected_15x15.png") {
        currentItem.attr("src", root + "images/ICN_Selected_15x15.png");
    } else {
        currentItem.attr("src", root + "images/ICN_Unselected_15x15.png");
    }
    var selectAll = true;
    var boxArr = $("img[name = 'opera_status']");
    boxArr.each(function() {
        if ($(this).attr("src") ===  root + "images/ICN_Unselected_15x15.png") {
            selectAll = false;
            return false;
        }
    });
    if (selectAll) {
        $("#check_box").attr("src", root + "images/ICN_Selected_15x15.png");
    } else {
        $("#check_box").attr("src", root + "images/ICN_Unselected_15x15.png");
    }
});

$("#deleteOpera").click(
    function() {
        var done = false;
        var root = $("#root").attr("value");
        var list = $("img[name = 'opera_status']");
        $("img[name='opera_status']").each(
            function() {
                if ($(this).attr("src") === root + "images/ICN_Selected_15x15.png") {
                    done = true;
                    return false;
                }
            }
        );
        if(done) {
            $("#deleteBackground").css("display","block");
            $("#deleteTip").css("display","block");
        } else {
            alert("No items were selected");
        }
    }
);
$("#deleteSubmit").click(function() {
    var root = $("#root").attr("value");
    var list = $("img[name = 'opera_status']");
    var deleteList = "";
    list.each(function() {
        if ($(this).attr("src") === root + "images/ICN_Selected_15x15.png") {
            deleteList += $(this).attr("id") + ",";
        }
    });
    if ( deleteList === "") {
        alert("You need selected some option!");
        return;
    }
    location.href = "delete?deletelist=" + deleteList.substring(0, deleteList.length - 1);
});

function detailDelete(id){
    location.href = "delete?deletelist=" + id;
}

function editPage(id) {
    location.href = id + "?currentId=" + id + "&edit=1";
}

function editQuestion(id) {
    location.href= id + "?currentId=" + id;
}

$("#check_box").click(function (){
    var root = $("#root").attr("value");
    var unSelect = root + "images/ICN_Unselected_15x15.png";
    var select = root + "images/ICN_Selected_15x15.png";
    var src = $("#check_box").attr("src");
    var boxArr = $("img[name='opera_status']");
    if (src === unSelect) {
        $("#check_box").attr("src", select);
        boxArr.each(function() {
            $(this).attr("src", select);
        });
    } else {
         $("#check_box").attr("src", unSelect);
         boxArr.each(function() {
             $(this).attr("src", unSelect);
         });
    }
});

$(".submit_delete_button").click(
        function() {
            $(".submit_delete_background").hide();
            $(".submit_delete").hide();
        }
);

$("#close_button").click(
        function() {
            $(".submit_delete_background").hide();
            $(".submit_delete").hide();
        }
);

$("#search").keydown(function() {
    if ($("#search").val().length > 22) {
        alert("Enter 20 characters at most");
    }
});

$("#deleteSingle").click(function() {
    $("#deleteBackground").css("display","block");
    $("#deleteTip").css("display","block");
});