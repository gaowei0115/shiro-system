/**
 * 获取项目路径
 */
function getRootPath() {
    // http://localhost:8080/exam-web/course/toExamType
    var strFullPath = window.document.location.href;
    // 验证fullpath中是否包含exam-web
    var pj = "exam-web";
    if (strFullPath.indexOf(pj) == -1) {
        alert("部署项目名需以exam-web命名");
        return;
    }
    // /course/toExamType
    var strPath = window.document.location.pathname;
    var pos = strFullPath.indexOf(strPath);
    var prePath = strFullPath.substring(0, pos);
    var postPath = strPath.substring(0, strPath.substr(1).indexOf('/') + 1);
    // alert(prePath + postPath);
    return (prePath + postPath);
}