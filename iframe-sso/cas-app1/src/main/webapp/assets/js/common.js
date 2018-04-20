/**
 * 获取项目根路径
 * 例如：http://app1.com:8181/fire
 * @returns {string}
 */
function getRootPath() {
    var url = window.document.location.href;
    var pathName = window.document.location.pathname;
    var pos = url.indexOf(pathName);
    var path = url.substring(0, pos);
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
    return (path + projectName);
}

/**
 * 获取请求参数
 * 例如：http://app1.com:8181/fire?username=aaa&password=bbb,
 * 操作：GetQueryString('username') 返回 aaa
 *
 * @param name   param 参数的 key
 * @returns {*}  根据 param 的 key，返回value
 */
function GetQueryString(name) {
    var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return unescape(r[2]);
    return null;
}
