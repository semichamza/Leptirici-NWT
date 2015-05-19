/*
 * Jquery Multiselect插件 中文叫列表多选插件
 * 使用例子:
 * $('table').multiSelect({
 *  actcls: 'active',
 *  selector: 'tbody tr',
 *  callback: false
 * });
 */
(function ($) {
    $.fn.multiSelect = function (options) {
        $.fn.multiSelect.init($(this), options);
    };

    $.extend($.fn.multiSelect, {
        defaults: {
            actcls: 'active',
            selector: 'tbody tr',
            except: ['tbody'],
            statics: ['.static'],
            replaceClass:'replaceClass',
            replaceWith:'replaceWith',
            callback: false
        },
        first: null,
        last: null,
        init: function (scope, options) {
            this.scope = scope;
            this.options = $.extend({}, this.defaults, options);
            this.initEvent();
        },
        checkStatics: function (dom) {
            for (var i in this.options.statics) {
                if (dom.is(this.options.statics[i])) {
                    return true;
                }
            }
        },
        initEvent: function () {
            var self = this,
                scope = self.scope,
                options = self.options,
                callback = options.callback,
                actcls = options.actcls,
                replaceClass=options.replaceClass,
                replaceWith=options.replaceWith;

            scope.on('click.mSelect', options.selector, function (e) {
                if (!e.shiftKey && self.checkStatics($(this))) {
                    return;
                }

                if ($(this).hasClass(actcls)) {
                    $(this).removeClass(actcls);
                }
                else if ($(this).hasClass(replaceWith)) {
                    $(this).removeClass(replaceWith);
                    $(this).addClass(replaceClass);
                }
                else {
                   // if(!$(this).hasClass(replaceWith))
                        $(this).addClass(actcls);

                    if ($(this).hasClass(replaceClass)) {
                        $(this).removeClass(actcls);
                        $(this).removeClass(replaceClass);
                        $(this).addClass(replaceWith);
                    }
                }

                if (e.shiftKey && self.last) {
                    if (!self.first) {
                        self.first = self.last;
                    }
                    var start = self.first.index();
                    var end = $(this).index();
                    if (start > end) {
                        var temp = start;
                        start = end;
                        end = temp;
                    }
                    $(options.selector, scope).removeClass(actcls).slice(start, end + 1).each(function () {
                        if (!self.checkStatics($(this))) {
                            //if(!$(this).hasClass(replaceWith))
                                $(this).addClass(actcls);
                            if ($(this).hasClass(replaceClass)) {
                                $(this).removeClass(actcls);
                                $(this).removeClass(replaceClass);
                                $(this).addClass(replaceWith);
                            };
                        }
                    });
                    window.getSelection ? window.getSelection().removeAllRanges() : document.selection.empty();
                } else if (!e.ctrlKey && !e.metaKey) {
                    $(this).siblings().removeClass(actcls);
                    $(this).siblings().each(function(){
                        if ($(this).hasClass(replaceWith)) {
                            $(this).removeClass(replaceWith);
                            $(this).addClass(replaceClass);
                        }
                    });
                }
                self.last = $(this);
                var actclsItems= $(options.selector + '.' + actcls, scope);
                var replaceWithItems= $(options.selector + '.' + replaceWith, scope);
                $.isFunction(callback) && callback(actclsItems,replaceWithItems);
            });


            $(document).on('click.mSelect', function (e) {
                for (var i in options.except) {
                    var except = options.except[i];
                    if ($(e.target).is(except) || $(e.target).parents(except).size()) {
                        return;
                    }
                }
                scope.find(options.selector).each(function () {
                    if (!self.checkStatics($(this))) {
                        $(this).removeClass(actcls);
                        if ($(this).hasClass(replaceWith)) {
                            $(this).removeClass(replaceWith);
                            $(this).addClass(replaceClass);
                        }
                    }
                });

                var actclsItems= $(options.selector + '.' + actcls, scope);
                var replaceWithItems= $(options.selector + '.' + replaceWith, scope);
                $.isFunction(callback) && callback(actclsItems,replaceWithItems);
            });


            $(document).on('keydown.mSelect', function (e) {
                if ((e.keyCode == 65) && (e.metaKey || e.ctrlKey)) {
                    $(options.selector, scope).each(function () {
                        if (!self.checkStatics($(this))) {
                           // if(!$(this).hasClass(replaceWith))
                                $(this).addClass(actcls);
                            if ($(this).hasClass(replaceClass)) {
                                $(this).removeClass(actcls);
                                $(this).removeClass(replaceClass);
                                $(this).addClass(replaceWith);
                            };
                        }
                    });
                    var actclsItems= $(options.selector + '.' + actcls, scope);
                    var replaceWithItems= $(options.selector + '.' + replaceWith, scope);
                    $.isFunction(callback) && callback(actclsItems,replaceWithItems);
                    e.preventDefault();
                    return false;
                }
            });


            $(document).on('keyup.mSelect', function (e) {
                if (e.keyCode == 16) {
                    self.first = null;
                }
            });
        }
    });
})(jQuery);