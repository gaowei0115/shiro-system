/**
 *  分页js
 * */
// 定义前端分页插件类
var pageFn = {

};

var default_page = {"page":1, "pageSize":10};

/**
 * 设置插件基本参数属性
 * @param pageSelector
 *          插件标识
 * @param data
 *          后台返回数据
 * @param options
 *          查询条件
 */
pageFn.setPageProperties = function (pageSelector, data, options) {
    // 获取后台传递总条数
    var totals = data.viewData.total;
    // 每页显示条数
    var pageSize = options.pageSize;
    // 总页数
    var totalPages = 0;

    // 计算每页条数
    if (totals != 0) {
        if (totals % pageSize == 0) {
            totalPages = totals / pageSize;
        } else {
            totalPages = Math.ceil(totals / pageSize);
        }
    }

    // 写入共XX页...
    $("#pageleft").text("共" + totalPages + "页/" + totals + "条数据");
    if (totalPages > 1) {
        pageFn.buildPage(pageSelector, $.extend(options, {totalPages: totalPages}));
    } else {
        var options = {
            currentPage: 1, //当前页
            totalPages: 1, //总页数
            numberOfPages: 5, //设置控件显示的页码数
            bootstrapMajorVersion: 3,//如果是bootstrap3版本需要加此标识，并且设置包含分页内容的DOM元素为UL,如果是bootstrap2版本，则DOM包含元素是DIV
            useBootstrapTooltip: false,//是否显示tip提示框
        }
        pageSelector.bootstrapPaginator(options);
    }
}

/**
 * 执行分页
 * @param paginatorSelector
 * @param option
 */
pageFn.buildPage = function (pageSelector, options) {
    var _option = {
        currentPage: options.currentPage, //当前页
        totalPages: options.totalPages, //总页数
        numberOfPages: 5, //设置控件显示的页码数
        bootstrapMajorVersion: 3,//如果是bootstrap3版本需要加此标识，并且设置包含分页内容的DOM元素为UL,如果是bootstrap2版本，则DOM包含元素是DIV
        useBootstrapTooltip: false,//是否显示tip提示框
        itemTexts: function (type, page, current) {//
            switch (type) {
                case "first":
                    return "首页";
                case "prev":
                    return "&lt;";
                case "next":
                    return "&gt;";
                case "last":
                    return "尾页";
                case "page":
                    return page;
            }
        },
        onPageClicked: function (event, originalEvent, type, page) {
            // 调用查询
            query(page, options.pageSize);
        }
    }

    $.extend(_option, options);
    pageSelector.bootstrapPaginator(_option);
}