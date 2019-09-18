/**
 * 函数对象
 */
var Validate = function () {

    /**
     * 初始化 jquery validation
     */
    var handlerInitValidate = function () {
        $("#inputForm").validate({
            errorElement: 'span',
            errorClass: 'help-block',

            errorPlacement: function (error, element) {
                element.parent().parent().attr("class", "form-group has-error");
                error.insertAfter(element);
            }
        });
    };

    /**
     * 增加自定义验证规则
     */
    var handlerInitCustomValidate = function () {
        $.validator.addMethod("mobile", function(value, element) {
            var length = value.length;
            var mobile =  /^1(3|4|5|7|8)\d{9}$/;
            return this.optional(element) || (length == 11 && mobile.test(value));
        }, "手机号码格式错误");
    };

    return {
        /**
         * 初始化
         */
        init: function () {
            handlerInitCustomValidate();
            handlerInitValidate();
        }
    }

}();

$(document).ready(function () {
    Validate.init();
});