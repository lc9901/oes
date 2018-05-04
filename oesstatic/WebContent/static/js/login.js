$("#loginbtn").click(function() {
    var userName = $("#userName").val();
    var password = $("#password").val();
    var go = $("#go");
    var queryString = $("#queryString")
    var isPass = true;

    if (!userName) {
        isPass = false;
        $("#errorUserName").css("visibility", "visible");
    } else {
        $("#errorUserName").css("visibility", "hidden");
    }

    if (!password) {
        isPass = false;
        $("#errorPassword").css("visibility", "visible");
    } else {
        $("#errorPassword").css("visibility", "hidden");

    }
    var user= {
               userName : userName,
               password : password
               };
    if(isPass) {
        $.ajax({
            url:"login",
            type:"POST",
            datatype:"JSON",
            contentType:'application/json;charset=UTF-8',
            data:JSON.stringify(user),
            success:function(data){
                if (data.status === "SUCCESS") {
                    if (go.val() == "null"){
                        location.href = data.data;
                    }else{
                        var targetPage = $("#root").val() + "/" + go.val();
                        if (!(queryString.val() == "null")) {
                            targetPage = targetPage + "?" + queryString.val().substr(1, queryString.val().length);
                        }
                        location.href = targetPage;
                    }
                } else {
                    $("#tipUser").text(data.data);
                    $("#tipUser").css("display", "inline");
                }
            }
        });
    }
});