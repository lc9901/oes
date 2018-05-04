String.prototype.trim = function() {
    return this.replace(/(^\s*)|(\s*$)/g, "");
}

String.prototype.ltrim = function() {
    return this.replace(/(^\s*)/g,"");
}

String.prototype.rtrim = function() {
    return this.replace(/(^\s*$)/g,"")
}

$(document).ready(function(){
    $("#logout").click(
            function () {
                location.href = "/oes/page/login/logout";
            }
    )
});