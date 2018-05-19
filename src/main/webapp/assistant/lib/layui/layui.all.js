/** layui-v2.2.6 MIT License By https://www.layui.com */
;!function (e) {
    "use strict";
    var t = document, n = {modules: {}, status: {}, timeout: 10, event: {}}, o = function () {
        this.v = "2.2.6"
    }, r = function () {
        var e = t.currentScript ? t.currentScript.src : function () {
            for (var e, n = t.scripts, o = n.length - 1, r = o; r > 0; r--)if ("interactive" === n[r].readyState) {
                e = n[r].src;
                break
            }
            return e || n[o].src
        }();
        return e.substring(0, e.lastIndexOf("/") + 1)
    }(), a = function (t) {
        e.console && console.error && console.error("Layui hint: " + t)
    }, i = "undefined" != typeof opera && "[object Opera]" === opera.toString(), u = {
        layer: "modules/layer",
        laydate: "modules/laydate",
        laypage: "modules/laypage",
        laytpl: "modules/laytpl",
        layim: "modules/layim",
        layedit: "modules/layedit",
        form: "modules/form",
        upload: "modules/upload",
        tree: "modules/tree",
        table: "modules/table",
        element: "modules/element",
        util: "modules/util",
        flow: "modules/flow",
        carousel: "modules/carousel",
        code: "modules/code",
        jquery: "modules/jquery",
        mobile: "modules/mobile",
        "layui.all": "../layui.all"
    };
    o.prototype.cache = n, o.prototype.define = function (e, t) {
        var o = this, r = "function" == typeof e, a = function () {
            var e = function (e, t) {
                layui[e] = t, n.status[e] = !0
            };
            return "function" == typeof t && t(function (o, r) {
                e(o, r), n.callback[o] = function () {
                    t(e)
                }
            }), this
        };
        return r && (t = e, e = []), layui["layui.all"] || !layui["layui.all"] && layui["layui.mobile"] ? a.call(o) : (o.use(e, a), o)
    }, o.prototype.use = function (e, o, l) {
        function s(e, t) {
            var o = "PLaySTATION 3" === navigator.platform ? /^complete$/ : /^(complete|loaded)$/;
            ("load" === e.type || o.test((e.currentTarget || e.srcElement).readyState)) && (n.modules[f] = t, d.removeChild(v), function r() {
                return ++m > 1e3 * n.timeout / 4 ? a(f + " is not a valid module") : void(n.status[f] ? c() : setTimeout(r, 4))
            }())
        }

        function c() {
            l.push(layui[f]), e.length > 1 ? y.use(e.slice(1), o, l) : "function" == typeof o && o.apply(layui, l)
        }

        var y = this, p = n.dir = n.dir ? n.dir : r, d = t.getElementsByTagName("head")[0];
        e = "string" == typeof e ? [e] : e, window.jQuery && jQuery.fn.on && (y.each(e, function (t, n) {
            "jquery" === n && e.splice(t, 1)
        }), layui.jquery = layui.$ = jQuery);
        var f = e[0], m = 0;
        if (l = l || [], n.host = n.host || (p.match(/\/\/([\s\S]+?)\//) || ["//" + location.host + "/"])[0], 0 === e.length || layui["layui.all"] && u[f] || !layui["layui.all"] && layui["layui.mobile"] && u[f])return c(), y;
        if (n.modules[f])!function g() {
            return ++m > 1e3 * n.timeout / 4 ? a(f + " is not a valid module") : void("string" == typeof n.modules[f] && n.status[f] ? c() : setTimeout(g, 4))
        }(); else {
            var v = t.createElement("script"), h = (u[f] ? p + "lay/" : /^\{\/\}/.test(y.modules[f]) ? "" : n.base || "") + (y.modules[f] || f) + ".js";
            h = h.replace(/^\{\/\}/, ""), v.async = !0, v.charset = "utf-8", v.src = h + function () {
                    var e = n.version === !0 ? n.v || (new Date).getTime() : n.version || "";
                    return e ? "?v=" + e : ""
                }(), d.appendChild(v), !v.attachEvent || v.attachEvent.toString && v.attachEvent.toString().indexOf("[native code") < 0 || i ? v.addEventListener("load", function (e) {
                s(e, h)
            }, !1) : v.attachEvent("onreadystatechange", function (e) {
                s(e, h)
            }), n.modules[f] = h
        }
        return y
    }, o.prototype.getStyle = function (t, n) {
        var o = t.currentStyle ? t.currentStyle : e.getComputedStyle(t, null);
        return o[o.getPropertyValue ? "getPropertyValue" : "getAttribute"](n)
    }, o.prototype.link = function (e, o, r) {
        var i = this, u = t.createElement("link"), l = t.getElementsByTagName("head")[0];
        "string" == typeof o && (r = o);
        var s = (r || e).replace(/\.|\//g, ""), c = u.id = "layuicss-" + s, y = 0;
        return u.rel = "stylesheet", u.href = e + (n.debug ? "?v=" + (new Date).getTime() : ""), u.media = "all", t.getElementById(c) || l.appendChild(u), "function" != typeof o ? i : (function p() {
            return ++y > 1e3 * n.timeout / 100 ? a(e + " timeout") : void(1989 === parseInt(i.getStyle(t.getElementById(c), "width")) ? function () {
                o()
            }() : setTimeout(p, 100))
        }(), i)
    }, n.callback = {}, o.prototype.factory = function (e) {
        if (layui[e])return "function" == typeof n.callback[e] ? n.callback[e] : null
    }, o.prototype.addcss = function (e, t, o) {
        return layui.link(n.dir + "css/" + e, t, o)
    }, o.prototype.img = function (e, t, n) {
        var o = new Image;
        return o.src = e, o.complete ? t(o) : (o.onload = function () {
            o.onload = null, t(o)
        }, void(o.onerror = function (e) {
            o.onerror = null, n(e)
        }))
    }, o.prototype.config = function (e) {
        e = e || {};
        for (var t in e)n[t] = e[t];
        return this
    }, o.prototype.modules = function () {
        var e = {};
        for (var t in u)e[t] = u[t];
        return e
    }(), o.prototype.extend = function (e) {
        var t = this;
        e = e || {};
        for (var n in e)t[n] || t.modules[n] ? a("模块名 " + n + " 已被占用") : t.modules[n] = e[n];
        return t
    }, o.prototype.router = function (e) {
        var t = this, e = e || location.hash, n = {path: [], search: {}, hash: (e.match(/[^#](#.*$)/) || [])[1] || ""};
        return /^#\//.test(e) ? (e = e.replace(/^#\//, ""), n.href = "/" + e, e = e.replace(/([^#])(#.*$)/, "$1").split("/") || [], t.each(e, function (e, t) {
            /^\w+=/.test(t) ? function () {
                t = t.split("="), n.search[t[0]] = t[1]
            }() : n.path.push(t)
        }), n) : n
    }, o.prototype.data = function (t, n, o) {
        if (t = t || "layui", o = o || localStorage, e.JSON && e.JSON.parse) {
            if (null === n)return delete o[t];
            n = "object" == typeof n ? n : {key: n};
            try {
                var r = JSON.parse(o[t])
            } catch (a) {
                var r = {}
            }
            return "value" in n && (r[n.key] = n.value), n.remove && delete r[n.key], o[t] = JSON.stringify(r), n.key ? r[n.key] : r
        }
    }, o.prototype.sessionData = function (e, t) {
        return this.data(e, t, sessionStorage)
    }, o.prototype.device = function (t) {
        var n = navigator.userAgent.toLowerCase(), o = function (e) {
            var t = new RegExp(e + "/([^\\s\\_\\-]+)");
            return e = (n.match(t) || [])[1], e || !1
        }, r = {
            os: function () {
                return /windows/.test(n) ? "windows" : /linux/.test(n) ? "linux" : /iphone|ipod|ipad|ios/.test(n) ? "ios" : /mac/.test(n) ? "mac" : void 0
            }(), ie: function () {
                return !!(e.ActiveXObject || "ActiveXObject" in e) && ((n.match(/msie\s(\d+)/) || [])[1] || "11")
            }(), weixin: o("micromessenger")
        };
        return t && !r[t] && (r[t] = o(t)), r.android = /android/.test(n), r.ios = "ios" === r.os, r
    }, o.prototype.hint = function () {
        return {error: a}
    }, o.prototype.each = function (e, t) {
        var n, o = this;
        if ("function" != typeof t)return o;
        if (e = e || [], e.constructor === Object) {
            for (n in e)if (t.call(e[n], n, e[n]))break
        } else for (n = 0; n < e.length && !t.call(e[n], n, e[n]); n++);
        return o
    }, o.prototype.sort = function (e, t, n) {
        var o = JSON.parse(JSON.stringify(e || []));
        return t ? (o.sort(function (e, n) {
            var o = /^-?\d+$/, r = e[t], a = n[t];
            return o.test(r) && (r = parseFloat(r)), o.test(a) && (a = parseFloat(a)), r && !a ? 1 : !r && a ? -1 : r > a ? 1 : r < a ? -1 : 0
        }), n && o.reverse(), o) : o
    }, o.prototype.stope = function (t) {
        t = t || e.event;
        try {
            t.stopPropagation()
        } catch (n) {
            t.cancelBubble = !0
        }
    }, o.prototype.onevent = function (e, t, n) {
        return "string" != typeof e || "function" != typeof n ? this : o.event(e, t, null, n)
    }, o.prototype.event = o.event = function (e, t, o, r) {
        var a = this, i = null, u = t.match(/\((.*)\)$/) || [], l = (e + "." + t).replace(u[0], ""), s = u[1] || "", c = function (e, t) {
            var n = t && t.call(a, o);
            n === !1 && null === i && (i = !1)
        };
        return r ? (n.event[l] = n.event[l] || {}, n.event[l][s] = [r], this) : (layui.each(n.event[l], function (e, t) {
            return "{*}" === s ? void layui.each(t, c) : ("" === e && layui.each(t, c), void(e === s && layui.each(t, c)))
        }), i)
    }, e.layui = new o
}(window);
layui.define(function (a) {
    var i = layui.cache;
    layui.config({dir: i.dir.replace(/lay\/dest\/$/, "")}), a("layui.all", layui.v)
});
layui.define(function (e) {
    "use strict";
    var r = {open: "{{", close: "}}"}, c = {
        exp: function (e) {
            return new RegExp(e, "g")
        }, query: function (e, c, t) {
            var o = ["#([\\s\\S])+?", "([^{#}])*?"][e || 0];
            return n((c || "") + r.open + o + r.close + (t || ""))
        }, escape: function (e) {
            return String(e || "").replace(/&(?!#?[a-zA-Z0-9]+;)/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/'/g, "&#39;").replace(/"/g, "&quot;")
        }, error: function (e, r) {
            var c = "Laytpl Error：";
            return "object" == typeof console && console.error(c + e + "\n" + (r || "")), c + e
        }
    }, n = c.exp, t = function (e) {
        this.tpl = e
    };
    t.pt = t.prototype, window.errors = 0, t.pt.parse = function (e, t) {
        var o = this, p = e, a = n("^" + r.open + "#", ""), l = n(r.close + "$", "");
        e = e.replace(/\s+|\r|\t|\n/g, " ").replace(n(r.open + "#"), r.open + "# ").replace(n(r.close + "}"), "} " + r.close).replace(/\\/g, "\\\\").replace(n(r.open + "!(.+?)!" + r.close), function (e) {
            return e = e.replace(n("^" + r.open + "!"), "").replace(n("!" + r.close), "").replace(n(r.open + "|" + r.close), function (e) {
                return e.replace(/(.)/g, "\\$1")
            })
        }).replace(/(?="|')/g, "\\").replace(c.query(), function (e) {
            return e = e.replace(a, "").replace(l, ""), '";' + e.replace(/\\/g, "") + ';view+="'
        }).replace(c.query(1), function (e) {
            var c = '"+(';
            return e.replace(/\s/g, "") === r.open + r.close ? "" : (e = e.replace(n(r.open + "|" + r.close), ""), /^=/.test(e) && (e = e.replace(/^=/, ""), c = '"+_escape_('), c + e.replace(/\\/g, "") + ')+"')
        }), e = '"use strict";var view = "' + e + '";return view;';
        try {
            return o.cache = e = new Function("d, _escape_", e), e(t, c.escape)
        } catch (u) {
            return delete o.cache, c.error(u, p)
        }
    }, t.pt.render = function (e, r) {
        var n, t = this;
        return e ? (n = t.cache ? t.cache(e, c.escape) : t.parse(t.tpl, e), r ? void r(n) : n) : c.error("no data")
    };
    var o = function (e) {
        return "string" != typeof e ? c.error("Template not found") : new t(e)
    };
    o.config = function (e) {
        e = e || {};
        for (var c in e)r[c] = e[c]
    }, o.v = "1.2.0", e("laytpl", o)
});
layui.define(function (e) {
    "use strict";
    var a = document, t = "getElementById", n = "getElementsByTagName", i = "laypage", r = "layui-disabled", u = function (e) {
        var a = this;
        a.config = e || {}, a.config.index = ++s.index, a.render(!0)
    };
    u.prototype.type = function () {
        var e = this.config;
        if ("object" == typeof e.elem)return void 0 === e.elem.length ? 2 : 3
    }, u.prototype.view = function () {
        var e = this, a = e.config, t = a.groups = "groups" in a ? 0 | a.groups : 5;
        a.layout = "object" == typeof a.layout ? a.layout : ["prev", "page", "next"], a.count = 0 | a.count, a.curr = 0 | a.curr || 1, a.limits = "object" == typeof a.limits ? a.limits : [10, 20, 30, 40, 50], a.limit = 0 | a.limit || 10, a.pages = Math.ceil(a.count / a.limit) || 1, a.curr > a.pages && (a.curr = a.pages), t < 0 ? t = 1 : t > a.pages && (t = a.pages), a.prev = "prev" in a ? a.prev : "&#x4E0A;&#x4E00;&#x9875;", a.next = "next" in a ? a.next : "&#x4E0B;&#x4E00;&#x9875;";
        var n = a.pages > t ? Math.ceil((a.curr + (t > 1 ? 1 : 0)) / (t > 0 ? t : 1)) : 1, i = {
            prev: function () {
                return a.prev ? '<a href="javascript:;" class="layui-laypage-prev' + (1 == a.curr ? " " + r : "") + '" data-page="' + (a.curr - 1) + '">' + a.prev + "</a>" : ""
            }(), page: function () {
                var e = [];
                if (a.count < 1)return "";
                n > 1 && a.first !== !1 && 0 !== t && e.push('<a href="javascript:;" class="layui-laypage-first" data-page="1"  title="&#x9996;&#x9875;">' + (a.first || 1) + "</a>");
                var i = Math.floor((t - 1) / 2), r = n > 1 ? a.curr - i : 1, u = n > 1 ? function () {
                    var e = a.curr + (t - i - 1);
                    return e > a.pages ? a.pages : e
                }() : t;
                for (u - r < t - 1 && (r = u - t + 1), a.first !== !1 && r > 2 && e.push('<span class="layui-laypage-spr">&#x2026;</span>'); r <= u; r++)r === a.curr ? e.push('<span class="layui-laypage-curr"><em class="layui-laypage-em" ' + (/^#/.test(a.theme) ? 'style="background-color:' + a.theme + ';"' : "") + "></em><em>" + r + "</em></span>") : e.push('<a href="javascript:;" data-page="' + r + '">' + r + "</a>");
                return a.pages > t && a.pages > u && a.last !== !1 && (u + 1 < a.pages && e.push('<span class="layui-laypage-spr">&#x2026;</span>'), 0 !== t && e.push('<a href="javascript:;" class="layui-laypage-last" title="&#x5C3E;&#x9875;"  data-page="' + a.pages + '">' + (a.last || a.pages) + "</a>")), e.join("")
            }(), next: function () {
                return a.next ? '<a href="javascript:;" class="layui-laypage-next' + (a.curr == a.pages ? " " + r : "") + '" data-page="' + (a.curr + 1) + '">' + a.next + "</a>" : ""
            }(), count: '<span class="layui-laypage-count">共 ' + a.count + " 条</span>", limit: function () {
                var e = ['<span class="layui-laypage-limits"><select lay-ignore>'];
                return layui.each(a.limits, function (t, n) {
                    e.push('<option value="' + n + '"' + (n === a.limit ? "selected" : "") + ">" + n + " 条/页</option>")
                }), e.join("") + "</select></span>"
            }(), skip: function () {
                return ['<span class="layui-laypage-skip">&#x5230;&#x7B2C;', '<input type="text" min="1" value="' + a.curr + '" class="layui-input">', '&#x9875;<button type="button" class="layui-laypage-btn">&#x786e;&#x5b9a;</button>', "</span>"].join("")
            }()
        };
        return ['<div class="layui-box layui-laypage layui-laypage-' + (a.theme ? /^#/.test(a.theme) ? "molv" : a.theme : "default") + '" id="layui-laypage-' + a.index + '">', function () {
            var e = [];
            return layui.each(a.layout, function (a, t) {
                i[t] && e.push(i[t])
            }), e.join("")
        }(), "</div>"].join("")
    }, u.prototype.jump = function (e, a) {
        if (e) {
            var t = this, i = t.config, r = e.children, u = e[n]("button")[0], l = e[n]("input")[0], p = e[n]("select")[0], c = function () {
                var e = 0 | l.value.replace(/\s|\D/g, "");
                e && (i.curr = e, t.render())
            };
            if (a)return c();
            for (var o = 0, y = r.length; o < y; o++)"a" === r[o].nodeName.toLowerCase() && s.on(r[o], "click", function () {
                var e = 0 | this.getAttribute("data-page");
                e < 1 || e > i.pages || (i.curr = e, t.render())
            });
            p && s.on(p, "change", function () {
                var e = this.value;
                i.curr * e > i.count && (i.curr = Math.ceil(i.count / e)), i.limit = e, t.render()
            }), u && s.on(u, "click", function () {
                c()
            })
        }
    }, u.prototype.skip = function (e) {
        if (e) {
            var a = this, t = e[n]("input")[0];
            t && s.on(t, "keyup", function (t) {
                var n = this.value, i = t.keyCode;
                /^(37|38|39|40)$/.test(i) || (/\D/.test(n) && (this.value = n.replace(/\D/, "")), 13 === i && a.jump(e, !0))
            })
        }
    }, u.prototype.render = function (e) {
        var n = this, i = n.config, r = n.type(), u = n.view();
        2 === r ? i.elem && (i.elem.innerHTML = u) : 3 === r ? i.elem.html(u) : a[t](i.elem) && (a[t](i.elem).innerHTML = u), i.jump && i.jump(i, e);
        var s = a[t]("layui-laypage-" + i.index);
        n.jump(s), i.hash && !e && (location.hash = "!" + i.hash + "=" + i.curr), n.skip(s)
    };
    var s = {
        render: function (e) {
            var a = new u(e);
            return a.index
        }, index: layui.laypage ? layui.laypage.index + 1e4 : 0, on: function (e, a, t) {
            return e.attachEvent ? e.attachEvent("on" + a, function (a) {
                a.target = a.srcElement, t.call(e, a)
            }) : e.addEventListener(a, t, !1), this
        }
    };
    e(i, s)
});
!function () {
    "use strict";
    var e = window.layui && layui.define, t = {
        getPath: function () {
            var e = document.currentScript ? document.currentScript.src : function () {
                for (var e, t = document.scripts, n = t.length - 1, a = n; a > 0; a--)if ("interactive" === t[a].readyState) {
                    e = t[a].src;
                    break
                }
                return e || t[n].src
            }();
            return e.substring(0, e.lastIndexOf("/") + 1)
        }(), getStyle: function (e, t) {
            var n = e.currentStyle ? e.currentStyle : window.getComputedStyle(e, null);
            return n[n.getPropertyValue ? "getPropertyValue" : "getAttribute"](t)
        }, link: function (e, a, i) {
            if (n.path) {
                var r = document.getElementsByTagName("head")[0], o = document.createElement("link");
                "string" == typeof a && (i = a);
                var s = (i || e).replace(/\.|\//g, ""), l = "layuicss-" + s, d = 0;
                o.rel = "stylesheet", o.href = n.path + e, o.id = l, document.getElementById(l) || r.appendChild(o), "function" == typeof a && !function c() {
                    return ++d > 80 ? window.console && console.error("laydate.css: Invalid") : void(1989 === parseInt(t.getStyle(document.getElementById(l), "width")) ? a() : setTimeout(c, 100))
                }()
            }
        }
    }, n = {
        v: "5.0.9",
        config: {},
        index: window.laydate && window.laydate.v ? 1e5 : 0,
        path: t.getPath,
        set: function (e) {
            var t = this;
            return t.config = w.extend({}, t.config, e), t
        },
        ready: function (a) {
            var i = "laydate", r = "", o = (e ? "modules/laydate/" : "theme/") + "default/laydate.css?v=" + n.v + r;
            return e ? layui.addcss(o, a, i) : t.link(o, a, i), this
        }
    }, a = function () {
        var e = this;
        return {
            hint: function (t) {
                e.hint.call(e, t)
            }, config: e.config
        }
    }, i = "laydate", r = ".layui-laydate", o = "layui-this", s = "laydate-disabled", l = "开始日期超出了结束日期<br>建议重新选择", d = [100, 2e5], c = "layui-laydate-static", m = "layui-laydate-list", u = "laydate-selected", h = "layui-laydate-hint", y = "laydate-day-prev", f = "laydate-day-next", p = "layui-laydate-footer", g = ".laydate-btns-confirm", v = "laydate-time-text", D = ".laydate-btns-time", T = function (e) {
        var t = this;
        t.index = ++n.index, t.config = w.extend({}, t.config, n.config, e), n.ready(function () {
            t.init()
        })
    }, w = function (e) {
        return new C(e)
    }, C = function (e) {
        for (var t = 0, n = "object" == typeof e ? [e] : (this.selector = e, document.querySelectorAll(e || null)); t < n.length; t++)this.push(n[t])
    };
    C.prototype = [], C.prototype.constructor = C, w.extend = function () {
        var e = 1, t = arguments, n = function (e, t) {
            e = e || (t.constructor === Array ? [] : {});
            for (var a in t)e[a] = t[a] && t[a].constructor === Object ? n(e[a], t[a]) : t[a];
            return e
        };
        for (t[0] = "object" == typeof t[0] ? t[0] : {}; e < t.length; e++)"object" == typeof t[e] && n(t[0], t[e]);
        return t[0]
    }, w.ie = function () {
        var e = navigator.userAgent.toLowerCase();
        return !!(window.ActiveXObject || "ActiveXObject" in window) && ((e.match(/msie\s(\d+)/) || [])[1] || "11")
    }(), w.stope = function (e) {
        e = e || window.event, e.stopPropagation ? e.stopPropagation() : e.cancelBubble = !0
    }, w.each = function (e, t) {
        var n, a = this;
        if ("function" != typeof t)return a;
        if (e = e || [], e.constructor === Object) {
            for (n in e)if (t.call(e[n], n, e[n]))break
        } else for (n = 0; n < e.length && !t.call(e[n], n, e[n]); n++);
        return a
    }, w.digit = function (e, t, n) {
        var a = "";
        e = String(e), t = t || 2;
        for (var i = e.length; i < t; i++)a += "0";
        return e < Math.pow(10, t) ? a + (0 | e) : e
    }, w.elem = function (e, t) {
        var n = document.createElement(e);
        return w.each(t || {}, function (e, t) {
            n.setAttribute(e, t)
        }), n
    }, C.addStr = function (e, t) {
        return e = e.replace(/\s+/, " "), t = t.replace(/\s+/, " ").split(" "), w.each(t, function (t, n) {
            new RegExp("\\b" + n + "\\b").test(e) || (e = e + " " + n)
        }), e.replace(/^\s|\s$/, "")
    }, C.removeStr = function (e, t) {
        return e = e.replace(/\s+/, " "), t = t.replace(/\s+/, " ").split(" "), w.each(t, function (t, n) {
            var a = new RegExp("\\b" + n + "\\b");
            a.test(e) && (e = e.replace(a, ""))
        }), e.replace(/\s+/, " ").replace(/^\s|\s$/, "")
    }, C.prototype.find = function (e) {
        var t = this, n = 0, a = [], i = "object" == typeof e;
        return this.each(function (r, o) {
            for (var s = i ? [e] : o.querySelectorAll(e || null); n < s.length; n++)a.push(s[n]);
            t.shift()
        }), i || (t.selector = (t.selector ? t.selector + " " : "") + e), w.each(a, function (e, n) {
            t.push(n)
        }), t
    }, C.prototype.each = function (e) {
        return w.each.call(this, this, e)
    }, C.prototype.addClass = function (e, t) {
        return this.each(function (n, a) {
            a.className = C[t ? "removeStr" : "addStr"](a.className, e)
        })
    }, C.prototype.removeClass = function (e) {
        return this.addClass(e, !0)
    }, C.prototype.hasClass = function (e) {
        var t = !1;
        return this.each(function (n, a) {
            new RegExp("\\b" + e + "\\b").test(a.className) && (t = !0)
        }), t
    }, C.prototype.attr = function (e, t) {
        var n = this;
        return void 0 === t ? function () {
            if (n.length > 0)return n[0].getAttribute(e)
        }() : n.each(function (n, a) {
            a.setAttribute(e, t)
        })
    }, C.prototype.removeAttr = function (e) {
        return this.each(function (t, n) {
            n.removeAttribute(e)
        })
    }, C.prototype.html = function (e) {
        return this.each(function (t, n) {
            n.innerHTML = e
        })
    }, C.prototype.val = function (e) {
        return this.each(function (t, n) {
            n.value = e
        })
    }, C.prototype.append = function (e) {
        return this.each(function (t, n) {
            "object" == typeof e ? n.appendChild(e) : n.innerHTML = n.innerHTML + e
        })
    }, C.prototype.remove = function (e) {
        return this.each(function (t, n) {
            e ? n.removeChild(e) : n.parentNode.removeChild(n)
        })
    }, C.prototype.on = function (e, t) {
        return this.each(function (n, a) {
            a.attachEvent ? a.attachEvent("on" + e, function (e) {
                e.target = e.srcElement, t.call(a, e)
            }) : a.addEventListener(e, t, !1)
        })
    }, C.prototype.off = function (e, t) {
        return this.each(function (n, a) {
            a.detachEvent ? a.detachEvent("on" + e, t) : a.removeEventListener(e, t, !1)
        })
    }, T.isLeapYear = function (e) {
        return e % 4 === 0 && e % 100 !== 0 || e % 400 === 0
    }, T.prototype.config = {
        type: "date",
        range: !1,
        format: "yyyy-MM-dd",
        value: null,
        min: "1900-1-1",
        max: "2099-12-31",
        trigger: "focus",
        show: !1,
        showBottom: !0,
        btns: ["clear", "now", "confirm"],
        lang: "cn",
        theme: "default",
        position: null,
        calendar: !1,
        mark: {},
        zIndex: null,
        done: null,
        change: null
    }, T.prototype.lang = function () {
        var e = this, t = e.config, n = {
            cn: {
                weeks: ["日", "一", "二", "三", "四", "五", "六"],
                time: ["时", "分", "秒"],
                timeTips: "选择时间",
                startTime: "开始时间",
                endTime: "结束时间",
                dateTips: "返回日期",
                month: ["一", "二", "三", "四", "五", "六", "七", "八", "九", "十", "十一", "十二"],
                tools: {confirm: "确定", clear: "清空", now: "现在"}
            },
            en: {
                weeks: ["Su", "Mo", "Tu", "We", "Th", "Fr", "Sa"],
                time: ["Hours", "Minutes", "Seconds"],
                timeTips: "Select Time",
                startTime: "Start Time",
                endTime: "End Time",
                dateTips: "Select Date",
                month: ["Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"],
                tools: {confirm: "Confirm", clear: "Clear", now: "Now"}
            }
        };
        return n[t.lang] || n.cn
    }, T.prototype.init = function () {
        var e = this, t = e.config, n = "yyyy|y|MM|M|dd|d|HH|H|mm|m|ss|s", a = "static" === t.position, i = {
            year: "yyyy",
            month: "yyyy-MM",
            date: "yyyy-MM-dd",
            time: "HH:mm:ss",
            datetime: "yyyy-MM-dd HH:mm:ss"
        };
        t.elem = w(t.elem), t.eventElem = w(t.eventElem), t.elem[0] && (t.range === !0 && (t.range = "-"), t.format === i.date && (t.format = i[t.type]), e.format = t.format.match(new RegExp(n + "|.", "g")) || [], e.EXP_IF = "", e.EXP_SPLIT = "", w.each(e.format, function (t, a) {
            var i = new RegExp(n).test(a) ? "\\d{" + function () {
                return new RegExp(n).test(e.format[0 === t ? t + 1 : t - 1] || "") ? /^yyyy|y$/.test(a) ? 4 : a.length : /^yyyy$/.test(a) ? "1,4" : /^y$/.test(a) ? "1,308" : "1,2"
            }() + "}" : "\\" + a;
            e.EXP_IF = e.EXP_IF + i, e.EXP_SPLIT = e.EXP_SPLIT + "(" + i + ")"
        }), e.EXP_IF = new RegExp("^" + (t.range ? e.EXP_IF + "\\s\\" + t.range + "\\s" + e.EXP_IF : e.EXP_IF) + "$"), e.EXP_SPLIT = new RegExp("^" + e.EXP_SPLIT + "$", ""), e.isInput(t.elem[0]) || "focus" === t.trigger && (t.trigger = "click"), t.elem.attr("lay-key") || (t.elem.attr("lay-key", e.index), t.eventElem.attr("lay-key", e.index)), t.mark = w.extend({}, t.calendar && "cn" === t.lang ? {
            "0-1-1": "元旦",
            "0-2-14": "情人",
            "0-3-8": "妇女",
            "0-3-12": "植树",
            "0-4-1": "愚人",
            "0-5-1": "劳动",
            "0-5-4": "青年",
            "0-6-1": "儿童",
            "0-9-10": "教师",
            "0-9-18": "国耻",
            "0-10-1": "国庆",
            "0-12-25": "圣诞"
        } : {}, t.mark), w.each(["min", "max"], function (e, n) {
            var a = [], i = [];
            if ("number" == typeof t[n]) {
                var r = t[n], o = (new Date).getTime(), s = 864e5, l = new Date(r ? r < s ? o + r * s : r : o);
                a = [l.getFullYear(), l.getMonth() + 1, l.getDate()], r < s || (i = [l.getHours(), l.getMinutes(), l.getSeconds()])
            } else a = (t[n].match(/\d+-\d+-\d+/) || [""])[0].split("-"), i = (t[n].match(/\d+:\d+:\d+/) || [""])[0].split(":");
            t[n] = {
                year: 0 | a[0] || (new Date).getFullYear(),
                month: a[1] ? (0 | a[1]) - 1 : (new Date).getMonth(),
                date: 0 | a[2] || (new Date).getDate(),
                hours: 0 | i[0],
                minutes: 0 | i[1],
                seconds: 0 | i[2]
            }
        }), e.elemID = "layui-laydate" + t.elem.attr("lay-key"), (t.show || a) && e.render(), a || e.events(), t.value && (t.value.constructor === Date ? e.setValue(e.parse(0, e.systemDate(t.value))) : e.setValue(t.value)))
    }, T.prototype.render = function () {
        var e = this, t = e.config, n = e.lang(), a = "static" === t.position, i = e.elem = w.elem("div", {
            id: e.elemID,
            "class": ["layui-laydate", t.range ? " layui-laydate-range" : "", a ? " " + c : "", t.theme && "default" !== t.theme && !/^#/.test(t.theme) ? " laydate-theme-" + t.theme : ""].join("")
        }), r = e.elemMain = [], o = e.elemHeader = [], s = e.elemCont = [], l = e.table = [], d = e.footer = w.elem("div", {"class": p});
        if (t.zIndex && (i.style.zIndex = t.zIndex), w.each(new Array(2), function (e) {
                if (!t.range && e > 0)return !0;
                var a = w.elem("div", {"class": "layui-laydate-header"}), i = [function () {
                    var e = w.elem("i", {"class": "layui-icon laydate-icon laydate-prev-y"});
                    return e.innerHTML = "&#xe65a;", e
                }(), function () {
                    var e = w.elem("i", {"class": "layui-icon laydate-icon laydate-prev-m"});
                    return e.innerHTML = "&#xe603;", e
                }(), function () {
                    var e = w.elem("div", {"class": "laydate-set-ym"}), t = w.elem("span"), n = w.elem("span");
                    return e.appendChild(t), e.appendChild(n), e
                }(), function () {
                    var e = w.elem("i", {"class": "layui-icon laydate-icon laydate-next-m"});
                    return e.innerHTML = "&#xe602;", e
                }(), function () {
                    var e = w.elem("i", {"class": "layui-icon laydate-icon laydate-next-y"});
                    return e.innerHTML = "&#xe65b;", e
                }()], d = w.elem("div", {"class": "layui-laydate-content"}), c = w.elem("table"), m = w.elem("thead"), u = w.elem("tr");
                w.each(i, function (e, t) {
                    a.appendChild(t)
                }), m.appendChild(u), w.each(new Array(6), function (e) {
                    var t = c.insertRow(0);
                    w.each(new Array(7), function (a) {
                        if (0 === e) {
                            var i = w.elem("th");
                            i.innerHTML = n.weeks[a], u.appendChild(i)
                        }
                        t.insertCell(a)
                    })
                }), c.insertBefore(m, c.children[0]), d.appendChild(c), r[e] = w.elem("div", {"class": "layui-laydate-main laydate-main-list-" + e}), r[e].appendChild(a), r[e].appendChild(d), o.push(i), s.push(d), l.push(c)
            }), w(d).html(function () {
                var e = [], i = [];
                return "datetime" === t.type && e.push('<span lay-type="datetime" class="laydate-btns-time">' + n.timeTips + "</span>"), w.each(t.btns, function (e, r) {
                    var o = n.tools[r] || "btn";
                    t.range && "now" === r || (a && "clear" === r && (o = "cn" === t.lang ? "重置" : "Reset"), i.push('<span lay-type="' + r + '" class="laydate-btns-' + r + '">' + o + "</span>"))
                }), e.push('<div class="laydate-footer-btns">' + i.join("") + "</div>"), e.join("")
            }()), w.each(r, function (e, t) {
                i.appendChild(t)
            }), t.showBottom && i.appendChild(d), /^#/.test(t.theme)) {
            var m = w.elem("style"), u = ["#{{id}} .layui-laydate-header{background-color:{{theme}};}", "#{{id}} .layui-this{background-color:{{theme}} !important;}"].join("").replace(/{{id}}/g, e.elemID).replace(/{{theme}}/g, t.theme);
            "styleSheet" in m ? (m.setAttribute("type", "text/css"), m.styleSheet.cssText = u) : m.innerHTML = u, w(i).addClass("laydate-theme-molv"), i.appendChild(m)
        }
        e.remove(T.thisElemDate), a ? t.elem.append(i) : (document.body.appendChild(i), e.position()), e.checkDate().calendar(), e.changeEvent(), T.thisElemDate = e.elemID, "function" == typeof t.ready && t.ready(w.extend({}, t.dateTime, {month: t.dateTime.month + 1}))
    }, T.prototype.remove = function (e) {
        var t = this, n = (t.config, w("#" + (e || t.elemID)));
        return n.hasClass(c) || t.checkDate(function () {
            n.remove()
        }), t
    }, T.prototype.position = function () {
        var e = this, t = e.config, n = e.bindElem || t.elem[0], a = n.getBoundingClientRect(), i = e.elem.offsetWidth, r = e.elem.offsetHeight, o = function (e) {
            return e = e ? "scrollLeft" : "scrollTop", document.body[e] | document.documentElement[e]
        }, s = function (e) {
            return document.documentElement[e ? "clientWidth" : "clientHeight"]
        }, l = 5, d = a.left, c = a.bottom;
        d + i + l > s("width") && (d = s("width") - i - l), c + r + l > s() && (c = a.top > r ? a.top - r : s() - r, c -= 2 * l), t.position && (e.elem.style.position = t.position), e.elem.style.left = d + ("fixed" === t.position ? 0 : o(1)) + "px", e.elem.style.top = c + ("fixed" === t.position ? 0 : o()) + "px"
    }, T.prototype.hint = function (e) {
        var t = this, n = (t.config, w.elem("div", {"class": h}));
        n.innerHTML = e || "", w(t.elem).find("." + h).remove(), t.elem.appendChild(n), clearTimeout(t.hinTimer), t.hinTimer = setTimeout(function () {
            w(t.elem).find("." + h).remove()
        }, 3e3)
    }, T.prototype.getAsYM = function (e, t, n) {
        return n ? t-- : t++, t < 0 && (t = 11, e--), t > 11 && (t = 0, e++), [e, t]
    }, T.prototype.systemDate = function (e) {
        var t = e || new Date;
        return {
            year: t.getFullYear(),
            month: t.getMonth(),
            date: t.getDate(),
            hours: e ? e.getHours() : 0,
            minutes: e ? e.getMinutes() : 0,
            seconds: e ? e.getSeconds() : 0
        }
    }, T.prototype.checkDate = function (e) {
        var t, a, i = this, r = (new Date, i.config), o = r.dateTime = r.dateTime || i.systemDate(), s = i.bindElem || r.elem[0], l = (i.isInput(s) ? "val" : "html", i.isInput(s) ? s.value : "static" === r.position ? "" : s.innerHTML), c = function (e) {
            e.year > d[1] && (e.year = d[1], a = !0), e.month > 11 && (e.month = 11, a = !0), e.hours > 23 && (e.hours = 0, a = !0), e.minutes > 59 && (e.minutes = 0, e.hours++, a = !0), e.seconds > 59 && (e.seconds = 0, e.minutes++, a = !0), t = n.getEndDate(e.month + 1, e.year), e.date > t && (e.date = t, a = !0)
        }, m = function (e, t, n) {
            var o = ["startTime", "endTime"];
            t = (t.match(i.EXP_SPLIT) || []).slice(1), n = n || 0, r.range && (i[o[n]] = i[o[n]] || {}), w.each(i.format, function (s, l) {
                var c = parseFloat(t[s]);
                t[s].length < l.length && (a = !0), /yyyy|y/.test(l) ? (c < d[0] && (c = d[0], a = !0), e.year = c) : /MM|M/.test(l) ? (c < 1 && (c = 1, a = !0), e.month = c - 1) : /dd|d/.test(l) ? (c < 1 && (c = 1, a = !0), e.date = c) : /HH|H/.test(l) ? (c < 1 && (c = 0, a = !0), e.hours = c, r.range && (i[o[n]].hours = c)) : /mm|m/.test(l) ? (c < 1 && (c = 0, a = !0), e.minutes = c, r.range && (i[o[n]].minutes = c)) : /ss|s/.test(l) && (c < 1 && (c = 0, a = !0), e.seconds = c, r.range && (i[o[n]].seconds = c))
            }), c(e)
        };
        return "limit" === e ? (c(o), i) : (l = l || r.value, "string" == typeof l && (l = l.replace(/\s+/g, " ").replace(/^\s|\s$/g, "")), i.startState && !i.endState && (delete i.startState, i.endState = !0), "string" == typeof l && l ? i.EXP_IF.test(l) ? r.range ? (l = l.split(" " + r.range + " "), i.startDate = i.startDate || i.systemDate(), i.endDate = i.endDate || i.systemDate(), r.dateTime = w.extend({}, i.startDate), w.each([i.startDate, i.endDate], function (e, t) {
            m(t, l[e], e)
        })) : m(o, l) : (i.hint("日期格式不合法<br>必须遵循下述格式：<br>" + (r.range ? r.format + " " + r.range + " " + r.format : r.format) + "<br>已为你重置"), a = !0) : l && l.constructor === Date ? r.dateTime = i.systemDate(l) : (r.dateTime = i.systemDate(), delete i.startState, delete i.endState, delete i.startDate, delete i.endDate, delete i.startTime, delete i.endTime), c(o), a && l && i.setValue(r.range ? i.endDate ? i.parse() : "" : i.parse()), e && e(), i)
    }, T.prototype.mark = function (e, t) {
        var n, a = this, i = a.config;
        return w.each(i.mark, function (e, a) {
            var i = e.split("-");
            i[0] != t[0] && 0 != i[0] || i[1] != t[1] && 0 != i[1] || i[2] != t[2] || (n = a || t[2])
        }), n && e.html('<span class="laydate-day-mark">' + n + "</span>"), a
    }, T.prototype.limit = function (e, t, n, a) {
        var i, r = this, o = r.config, l = {}, d = o[n > 41 ? "endDate" : "dateTime"], c = w.extend({}, d, t || {});
        return w.each({now: c, min: o.min, max: o.max}, function (e, t) {
            l[e] = r.newDate(w.extend({year: t.year, month: t.month, date: t.date}, function () {
                var e = {};
                return w.each(a, function (n, a) {
                    e[a] = t[a]
                }), e
            }())).getTime()
        }), i = l.now < l.min || l.now > l.max, e && e[i ? "addClass" : "removeClass"](s), i
    }, T.prototype.calendar = function (e) {
        var t, a, i, r = this, s = r.config, l = e || s.dateTime, c = new Date, m = r.lang(), u = "date" !== s.type && "datetime" !== s.type, h = e ? 1 : 0, y = w(r.table[h]).find("td"), f = w(r.elemHeader[h][2]).find("span");
        if (l.year < d[0] && (l.year = d[0], r.hint("最低只能支持到公元" + d[0] + "年")), l.year > d[1] && (l.year = d[1], r.hint("最高只能支持到公元" + d[1] + "年")), r.firstDate || (r.firstDate = w.extend({}, l)), c.setFullYear(l.year, l.month, 1), t = c.getDay(), a = n.getEndDate(l.month || 12, l.year), i = n.getEndDate(l.month + 1, l.year), w.each(y, function (e, n) {
                var d = [l.year, l.month], c = 0;
                n = w(n), n.removeAttr("class"), e < t ? (c = a - t + e, n.addClass("laydate-day-prev"), d = r.getAsYM(l.year, l.month, "sub")) : e >= t && e < i + t ? (c = e - t, s.range || c + 1 === l.date && n.addClass(o)) : (c = e - i - t, n.addClass("laydate-day-next"), d = r.getAsYM(l.year, l.month)), d[1]++, d[2] = c + 1, n.attr("lay-ymd", d.join("-")).html(d[2]), r.mark(n, d).limit(n, {
                    year: d[0],
                    month: d[1] - 1,
                    date: d[2]
                }, e)
            }), w(f[0]).attr("lay-ym", l.year + "-" + (l.month + 1)), w(f[1]).attr("lay-ym", l.year + "-" + (l.month + 1)), "cn" === s.lang ? (w(f[0]).attr("lay-type", "year").html(l.year + "年"), w(f[1]).attr("lay-type", "month").html(l.month + 1 + "月")) : (w(f[0]).attr("lay-type", "month").html(m.month[l.month]), w(f[1]).attr("lay-type", "year").html(l.year)), u && (s.range && (e ? r.endDate = r.endDate || {
                    year: l.year + ("year" === s.type ? 1 : 0),
                    month: l.month + ("month" === s.type ? 0 : -1)
                } : r.startDate = r.startDate || {
                    year: l.year,
                    month: l.month
                }, e && (r.listYM = [[r.startDate.year, r.startDate.month + 1], [r.endDate.year, r.endDate.month + 1]], r.list(s.type, 0).list(s.type, 1), "time" === s.type ? r.setBtnStatus("时间", w.extend({}, r.systemDate(), r.startTime), w.extend({}, r.systemDate(), r.endTime)) : r.setBtnStatus(!0))), s.range || (r.listYM = [[l.year, l.month + 1]], r.list(s.type, 0))), s.range && !e) {
            var p = r.getAsYM(l.year, l.month);
            r.calendar(w.extend({}, l, {year: p[0], month: p[1]}))
        }
        return s.range || r.limit(w(r.footer).find(g), null, 0, ["hours", "minutes", "seconds"]), s.range && e && !u && r.stampRange(), r
    }, T.prototype.list = function (e, t) {
        var n = this, a = n.config, i = a.dateTime, r = n.lang(), l = a.range && "date" !== a.type && "datetime" !== a.type, d = w.elem("ul", {
            "class": m + " " + {
                year: "laydate-year-list",
                month: "laydate-month-list",
                time: "laydate-time-list"
            }[e]
        }), c = n.elemHeader[t], u = w(c[2]).find("span"), h = n.elemCont[t || 0], y = w(h).find("." + m)[0], f = "cn" === a.lang, p = f ? "年" : "", T = n.listYM[t] || {}, C = ["hours", "minutes", "seconds"], x = ["startTime", "endTime"][t];
        if (T[0] < 1 && (T[0] = 1), "year" === e) {
            var M, b = M = T[0] - 7;
            b < 1 && (b = M = 1), w.each(new Array(15), function (e) {
                var i = w.elem("li", {"lay-ym": M}), r = {year: M};
                M == T[0] && w(i).addClass(o), i.innerHTML = M + p, d.appendChild(i), M < n.firstDate.year ? (r.month = a.min.month, r.date = a.min.date) : M >= n.firstDate.year && (r.month = a.max.month, r.date = a.max.date), n.limit(w(i), r, t), M++
            }), w(u[f ? 0 : 1]).attr("lay-ym", M - 8 + "-" + T[1]).html(b + p + " - " + (M - 1 + p))
        } else if ("month" === e)w.each(new Array(12), function (e) {
            var i = w.elem("li", {"lay-ym": e}), s = {year: T[0], month: e};
            e + 1 == T[1] && w(i).addClass(o), i.innerHTML = r.month[e] + (f ? "月" : ""), d.appendChild(i), T[0] < n.firstDate.year ? s.date = a.min.date : T[0] >= n.firstDate.year && (s.date = a.max.date), n.limit(w(i), s, t)
        }), w(u[f ? 0 : 1]).attr("lay-ym", T[0] + "-" + T[1]).html(T[0] + p); else if ("time" === e) {
            var E = function () {
                w(d).find("ol").each(function (e, a) {
                    w(a).find("li").each(function (a, i) {
                        n.limit(w(i), [{hours: a}, {hours: n[x].hours, minutes: a}, {
                            hours: n[x].hours,
                            minutes: n[x].minutes,
                            seconds: a
                        }][e], t, [["hours"], ["hours", "minutes"], ["hours", "minutes", "seconds"]][e])
                    })
                }), a.range || n.limit(w(n.footer).find(g), n[x], 0, ["hours", "minutes", "seconds"])
            };
            a.range ? n[x] || (n[x] = {
                hours: 0,
                minutes: 0,
                seconds: 0
            }) : n[x] = i, w.each([24, 60, 60], function (e, t) {
                var a = w.elem("li"), i = ["<p>" + r.time[e] + "</p><ol>"];
                w.each(new Array(t), function (t) {
                    i.push("<li" + (n[x][C[e]] === t ? ' class="' + o + '"' : "") + ">" + w.digit(t, 2) + "</li>")
                }), a.innerHTML = i.join("") + "</ol>", d.appendChild(a)
            }), E()
        }
        if (y && h.removeChild(y), h.appendChild(d), "year" === e || "month" === e)w(n.elemMain[t]).addClass("laydate-ym-show"), w(d).find("li").on("click", function () {
            var r = 0 | w(this).attr("lay-ym");
            if (!w(this).hasClass(s)) {
                if (0 === t)i[e] = r, l && (n.startDate[e] = r), n.limit(w(n.footer).find(g), null, 0); else if (l)n.endDate[e] = r; else {
                    var c = "year" === e ? n.getAsYM(r, T[1] - 1, "sub") : n.getAsYM(T[0], r, "sub");
                    w.extend(i, {year: c[0], month: c[1]})
                }
                "year" === a.type || "month" === a.type ? (w(d).find("." + o).removeClass(o), w(this).addClass(o), "month" === a.type && "year" === e && (n.listYM[t][0] = r, l && (n[["startDate", "endDate"][t]].year = r), n.list("month", t))) : (n.checkDate("limit").calendar(), n.closeList()), n.setBtnStatus(), a.range || n.done(null, "change"), w(n.footer).find(D).removeClass(s)
            }
        }); else {
            var S = w.elem("span", {"class": v}), k = function () {
                w(d).find("ol").each(function (e) {
                    var t = this, a = w(t).find("li");
                    t.scrollTop = 30 * (n[x][C[e]] - 2), t.scrollTop <= 0 && a.each(function (e, n) {
                        if (!w(this).hasClass(s))return t.scrollTop = 30 * (e - 2), !0
                    })
                })
            }, H = w(c[2]).find("." + v);
            k(), S.innerHTML = a.range ? [r.startTime, r.endTime][t] : r.timeTips, w(n.elemMain[t]).addClass("laydate-time-show"), H[0] && H.remove(), c[2].appendChild(S), w(d).find("ol").each(function (e) {
                var t = this;
                w(t).find("li").on("click", function () {
                    var r = 0 | this.innerHTML;
                    w(this).hasClass(s) || (a.range ? n[x][C[e]] = r : i[C[e]] = r, w(t).find("." + o).removeClass(o), w(this).addClass(o), E(), k(), (n.endDate || "time" === a.type) && n.done(null, "change"), n.setBtnStatus())
                })
            })
        }
        return n
    }, T.prototype.listYM = [], T.prototype.closeList = function () {
        var e = this;
        e.config;
        w.each(e.elemCont, function (t, n) {
            w(this).find("." + m).remove(), w(e.elemMain[t]).removeClass("laydate-ym-show laydate-time-show")
        }), w(e.elem).find("." + v).remove()
    }, T.prototype.setBtnStatus = function (e, t, n) {
        var a, i = this, r = i.config, o = w(i.footer).find(g), d = r.range && "date" !== r.type && "time" !== r.type;
        d && (t = t || i.startDate, n = n || i.endDate, a = i.newDate(t).getTime() > i.newDate(n).getTime(), i.limit(null, t) || i.limit(null, n) ? o.addClass(s) : o[a ? "addClass" : "removeClass"](s), e && a && i.hint("string" == typeof e ? l.replace(/日期/g, e) : l))
    }, T.prototype.parse = function (e, t) {
        var n = this, a = n.config, i = t || (e ? w.extend({}, n.endDate, n.endTime) : a.range ? w.extend({}, n.startDate, n.startTime) : a.dateTime), r = n.format.concat();
        return w.each(r, function (e, t) {
            /yyyy|y/.test(t) ? r[e] = w.digit(i.year, t.length) : /MM|M/.test(t) ? r[e] = w.digit(i.month + 1, t.length) : /dd|d/.test(t) ? r[e] = w.digit(i.date, t.length) : /HH|H/.test(t) ? r[e] = w.digit(i.hours, t.length) : /mm|m/.test(t) ? r[e] = w.digit(i.minutes, t.length) : /ss|s/.test(t) && (r[e] = w.digit(i.seconds, t.length))
        }), a.range && !e ? r.join("") + " " + a.range + " " + n.parse(1) : r.join("")
    }, T.prototype.newDate = function (e) {
        return e = e || {}, new Date(e.year || 1, e.month || 0, e.date || 1, e.hours || 0, e.minutes || 0, e.seconds || 0)
    }, T.prototype.setValue = function (e) {
        var t = this, n = t.config, a = t.bindElem || n.elem[0], i = t.isInput(a) ? "val" : "html";
        return "static" === n.position || w(a)[i](e || ""), this
    }, T.prototype.stampRange = function () {
        var e, t, n = this, a = n.config, i = w(n.elem).find("td");
        if (a.range && !n.endDate && w(n.footer).find(g).addClass(s), n.endDate)return e = n.newDate({
            year: n.startDate.year,
            month: n.startDate.month,
            date: n.startDate.date
        }).getTime(), t = n.newDate({
            year: n.endDate.year,
            month: n.endDate.month,
            date: n.endDate.date
        }).getTime(), e > t ? n.hint(l) : void w.each(i, function (a, i) {
            var r = w(i).attr("lay-ymd").split("-"), s = n.newDate({year: r[0], month: r[1] - 1, date: r[2]}).getTime();
            w(i).removeClass(u + " " + o), s !== e && s !== t || w(i).addClass(w(i).hasClass(y) || w(i).hasClass(f) ? u : o), s > e && s < t && w(i).addClass(u)
        })
    }, T.prototype.done = function (e, t) {
        var n = this, a = n.config, i = w.extend({}, n.startDate ? w.extend(n.startDate, n.startTime) : a.dateTime), r = w.extend({}, w.extend(n.endDate, n.endTime));
        return w.each([i, r], function (e, t) {
            "month" in t && w.extend(t, {month: t.month + 1})
        }), e = e || [n.parse(), i, r], "function" == typeof a[t || "done"] && a[t || "done"].apply(a, e), n
    }, T.prototype.choose = function (e) {
        var t = this, n = t.config, a = n.dateTime, i = w(t.elem).find("td"), r = e.attr("lay-ymd").split("-"), l = function (e) {
            new Date;
            e && w.extend(a, r), n.range && (t.startDate ? w.extend(t.startDate, r) : t.startDate = w.extend({}, r, t.startTime), t.startYMD = r)
        };
        if (r = {year: 0 | r[0], month: (0 | r[1]) - 1, date: 0 | r[2]}, !e.hasClass(s))if (n.range) {
            if (w.each(["startTime", "endTime"], function (e, n) {
                    t[n] = t[n] || {hours: 0, minutes: 0, seconds: 0}
                }), t.endState)l(), delete t.endState, delete t.endDate, t.startState = !0, i.removeClass(o + " " + u), e.addClass(o); else if (t.startState) {
                if (e.addClass(o), t.endDate ? w.extend(t.endDate, r) : t.endDate = w.extend({}, r, t.endTime), t.newDate(r).getTime() < t.newDate(t.startYMD).getTime()) {
                    var d = w.extend({}, t.endDate, {
                        hours: t.startDate.hours,
                        minutes: t.startDate.minutes,
                        seconds: t.startDate.seconds
                    });
                    w.extend(t.endDate, t.startDate, {
                        hours: t.endDate.hours,
                        minutes: t.endDate.minutes,
                        seconds: t.endDate.seconds
                    }), t.startDate = d
                }
                n.showBottom || t.done(), t.stampRange(), t.endState = !0, t.done(null, "change")
            } else e.addClass(o), l(), t.startState = !0;
            w(t.footer).find(g)[t.endDate ? "removeClass" : "addClass"](s)
        } else"static" === n.position ? (l(!0), t.calendar().done().done(null, "change")) : "date" === n.type ? (l(!0), t.setValue(t.parse()).remove().done()) : "datetime" === n.type && (l(!0), t.calendar().done(null, "change"))
    }, T.prototype.tool = function (e, t) {
        var n = this, a = n.config, i = a.dateTime, r = "static" === a.position, o = {
            datetime: function () {
                w(e).hasClass(s) || (n.list("time", 0), a.range && n.list("time", 1), w(e).attr("lay-type", "date").html(n.lang().dateTips))
            }, date: function () {
                n.closeList(), w(e).attr("lay-type", "datetime").html(n.lang().timeTips)
            }, clear: function () {
                n.setValue("").remove(), r && (w.extend(i, n.firstDate), n.calendar()), a.range && (delete n.startState, delete n.endState, delete n.endDate, delete n.startTime, delete n.endTime), n.done(["", {}, {}])
            }, now: function () {
                var e = new Date;
                w.extend(i, n.systemDate(), {
                    hours: e.getHours(),
                    minutes: e.getMinutes(),
                    seconds: e.getSeconds()
                }), n.setValue(n.parse()).remove(), r && n.calendar(), n.done()
            }, confirm: function () {
                if (a.range) {
                    if (!n.endDate)return n.hint("请先选择日期范围");
                    if (w(e).hasClass(s))return n.hint("time" === a.type ? l.replace(/日期/g, "时间") : l)
                } else if (w(e).hasClass(s))return n.hint("不在有效日期或时间范围内");
                n.done(), n.setValue(n.parse()).remove()
            }
        };
        o[t] && o[t]()
    }, T.prototype.change = function (e) {
        var t = this, n = t.config, a = n.dateTime, i = n.range && ("year" === n.type || "month" === n.type), r = t.elemCont[e || 0], o = t.listYM[e], s = function (s) {
            var l = ["startDate", "endDate"][e], d = w(r).find(".laydate-year-list")[0], c = w(r).find(".laydate-month-list")[0];
            return d && (o[0] = s ? o[0] - 15 : o[0] + 15, t.list("year", e)), c && (s ? o[0]-- : o[0]++, t.list("month", e)), (d || c) && (w.extend(a, {year: o[0]}), i && (t[l].year = o[0]), n.range || t.done(null, "change"), t.setBtnStatus(), n.range || t.limit(w(t.footer).find(g), {year: o[0]})), d || c
        };
        return {
            prevYear: function () {
                s("sub") || (a.year--, t.checkDate("limit").calendar(), n.range || t.done(null, "change"))
            }, prevMonth: function () {
                var e = t.getAsYM(a.year, a.month, "sub");
                w.extend(a, {
                    year: e[0],
                    month: e[1]
                }), t.checkDate("limit").calendar(), n.range || t.done(null, "change")
            }, nextMonth: function () {
                var e = t.getAsYM(a.year, a.month);
                w.extend(a, {
                    year: e[0],
                    month: e[1]
                }), t.checkDate("limit").calendar(), n.range || t.done(null, "change")
            }, nextYear: function () {
                s() || (a.year++, t.checkDate("limit").calendar(), n.range || t.done(null, "change"))
            }
        }
    }, T.prototype.changeEvent = function () {
        var e = this;
        e.config;
        w(e.elem).on("click", function (e) {
            w.stope(e)
        }), w.each(e.elemHeader, function (t, n) {
            w(n[0]).on("click", function (n) {
                e.change(t).prevYear()
            }), w(n[1]).on("click", function (n) {
                e.change(t).prevMonth()
            }), w(n[2]).find("span").on("click", function (n) {
                var a = w(this), i = a.attr("lay-ym"), r = a.attr("lay-type");
                i && (i = i.split("-"), e.listYM[t] = [0 | i[0], 0 | i[1]], e.list(r, t), w(e.footer).find(D).addClass(s))
            }), w(n[3]).on("click", function (n) {
                e.change(t).nextMonth()
            }), w(n[4]).on("click", function (n) {
                e.change(t).nextYear()
            })
        }), w.each(e.table, function (t, n) {
            var a = w(n).find("td");
            a.on("click", function () {
                e.choose(w(this))
            })
        }), w(e.footer).find("span").on("click", function () {
            var t = w(this).attr("lay-type");
            e.tool(this, t)
        })
    }, T.prototype.isInput = function (e) {
        return /input|textarea/.test(e.tagName.toLocaleLowerCase())
    }, T.prototype.events = function () {
        var e = this, t = e.config, n = function (n, a) {
            n.on(t.trigger, function () {
                a && (e.bindElem = this), e.render()
            })
        };
        t.elem[0] && !t.elem[0].eventHandler && (n(t.elem, "bind"), n(t.eventElem), w(document).on("click", function (n) {
            n.target !== t.elem[0] && n.target !== t.eventElem[0] && n.target !== w(t.closeStop)[0] && e.remove()
        }).on("keydown", function (t) {
            13 === t.keyCode && w("#" + e.elemID)[0] && e.elemID === T.thisElem && (t.preventDefault(), w(e.footer).find(g)[0].click())
        }), w(window).on("resize", function () {
            return !(!e.elem || !w(r)[0]) && void e.position()
        }), t.elem[0].eventHandler = !0)
    }, n.render = function (e) {
        var t = new T(e);
        return a.call(t)
    }, n.getEndDate = function (e, t) {
        var n = new Date;
        return n.setFullYear(t || n.getFullYear(), e || n.getMonth() + 1, 1), new Date(n.getTime() - 864e5).getDate()
    }, window.lay = window.lay || w, e ? (n.ready(), layui.define(function (e) {
        n.path = layui.cache.dir, e(i, n)
    })) : "function" == typeof define && define.amd ? define(function () {
        return n
    }) : function () {
        n.ready(), window.laydate = n
    }()
}();
!function (e, t) {
    "object" == typeof module && "object" == typeof module.exports ? module.exports = e.document ? t(e, !0) : function (e) {
        if (!e.document)throw new Error("jQuery requires a window with a document");
        return t(e)
    } : t(e)
}("undefined" != typeof window ? window : this, function (e, t) {
    function n(e) {
        var t = !!e && "length" in e && e.length, n = pe.type(e);
        return "function" !== n && !pe.isWindow(e) && ("array" === n || 0 === t || "number" == typeof t && t > 0 && t - 1 in e)
    }

    function r(e, t, n) {
        if (pe.isFunction(t))return pe.grep(e, function (e, r) {
            return !!t.call(e, r, e) !== n
        });
        if (t.nodeType)return pe.grep(e, function (e) {
            return e === t !== n
        });
        if ("string" == typeof t) {
            if (Ce.test(t))return pe.filter(t, e, n);
            t = pe.filter(t, e)
        }
        return pe.grep(e, function (e) {
            return pe.inArray(e, t) > -1 !== n
        })
    }

    function i(e, t) {
        do e = e[t]; while (e && 1 !== e.nodeType);
        return e
    }

    function o(e) {
        var t = {};
        return pe.each(e.match(De) || [], function (e, n) {
            t[n] = !0
        }), t
    }

    function a() {
        re.addEventListener ? (re.removeEventListener("DOMContentLoaded", s), e.removeEventListener("load", s)) : (re.detachEvent("onreadystatechange", s), e.detachEvent("onload", s))
    }

    function s() {
        (re.addEventListener || "load" === e.event.type || "complete" === re.readyState) && (a(), pe.ready())
    }

    function u(e, t, n) {
        if (void 0 === n && 1 === e.nodeType) {
            var r = "data-" + t.replace(_e, "-$1").toLowerCase();
            if (n = e.getAttribute(r), "string" == typeof n) {
                try {
                    n = "true" === n || "false" !== n && ("null" === n ? null : +n + "" === n ? +n : qe.test(n) ? pe.parseJSON(n) : n)
                } catch (i) {
                }
                pe.data(e, t, n)
            } else n = void 0
        }
        return n
    }

    function l(e) {
        var t;
        for (t in e)if (("data" !== t || !pe.isEmptyObject(e[t])) && "toJSON" !== t)return !1;
        return !0
    }

    function c(e, t, n, r) {
        if (He(e)) {
            var i, o, a = pe.expando, s = e.nodeType, u = s ? pe.cache : e, l = s ? e[a] : e[a] && a;
            if (l && u[l] && (r || u[l].data) || void 0 !== n || "string" != typeof t)return l || (l = s ? e[a] = ne.pop() || pe.guid++ : a), u[l] || (u[l] = s ? {} : {toJSON: pe.noop}), "object" != typeof t && "function" != typeof t || (r ? u[l] = pe.extend(u[l], t) : u[l].data = pe.extend(u[l].data, t)), o = u[l], r || (o.data || (o.data = {}), o = o.data), void 0 !== n && (o[pe.camelCase(t)] = n), "string" == typeof t ? (i = o[t], null == i && (i = o[pe.camelCase(t)])) : i = o, i
        }
    }

    function f(e, t, n) {
        if (He(e)) {
            var r, i, o = e.nodeType, a = o ? pe.cache : e, s = o ? e[pe.expando] : pe.expando;
            if (a[s]) {
                if (t && (r = n ? a[s] : a[s].data)) {
                    pe.isArray(t) ? t = t.concat(pe.map(t, pe.camelCase)) : t in r ? t = [t] : (t = pe.camelCase(t), t = t in r ? [t] : t.split(" ")), i = t.length;
                    for (; i--;)delete r[t[i]];
                    if (n ? !l(r) : !pe.isEmptyObject(r))return
                }
                (n || (delete a[s].data, l(a[s]))) && (o ? pe.cleanData([e], !0) : fe.deleteExpando || a != a.window ? delete a[s] : a[s] = void 0)
            }
        }
    }

    function d(e, t, n, r) {
        var i, o = 1, a = 20, s = r ? function () {
            return r.cur()
        } : function () {
            return pe.css(e, t, "")
        }, u = s(), l = n && n[3] || (pe.cssNumber[t] ? "" : "px"), c = (pe.cssNumber[t] || "px" !== l && +u) && Me.exec(pe.css(e, t));
        if (c && c[3] !== l) {
            l = l || c[3], n = n || [], c = +u || 1;
            do o = o || ".5", c /= o, pe.style(e, t, c + l); while (o !== (o = s() / u) && 1 !== o && --a)
        }
        return n && (c = +c || +u || 0, i = n[1] ? c + (n[1] + 1) * n[2] : +n[2], r && (r.unit = l, r.start = c, r.end = i)), i
    }

    function p(e) {
        var t = ze.split("|"), n = e.createDocumentFragment();
        if (n.createElement)for (; t.length;)n.createElement(t.pop());
        return n
    }

    function h(e, t) {
        var n, r, i = 0, o = "undefined" != typeof e.getElementsByTagName ? e.getElementsByTagName(t || "*") : "undefined" != typeof e.querySelectorAll ? e.querySelectorAll(t || "*") : void 0;
        if (!o)for (o = [], n = e.childNodes || e; null != (r = n[i]); i++)!t || pe.nodeName(r, t) ? o.push(r) : pe.merge(o, h(r, t));
        return void 0 === t || t && pe.nodeName(e, t) ? pe.merge([e], o) : o
    }

    function g(e, t) {
        for (var n, r = 0; null != (n = e[r]); r++)pe._data(n, "globalEval", !t || pe._data(t[r], "globalEval"))
    }

    function m(e) {
        Be.test(e.type) && (e.defaultChecked = e.checked)
    }

    function y(e, t, n, r, i) {
        for (var o, a, s, u, l, c, f, d = e.length, y = p(t), v = [], x = 0; x < d; x++)if (a = e[x], a || 0 === a)if ("object" === pe.type(a))pe.merge(v, a.nodeType ? [a] : a); else if (Ue.test(a)) {
            for (u = u || y.appendChild(t.createElement("div")), l = (We.exec(a) || ["", ""])[1].toLowerCase(), f = Xe[l] || Xe._default, u.innerHTML = f[1] + pe.htmlPrefilter(a) + f[2], o = f[0]; o--;)u = u.lastChild;
            if (!fe.leadingWhitespace && $e.test(a) && v.push(t.createTextNode($e.exec(a)[0])), !fe.tbody)for (a = "table" !== l || Ve.test(a) ? "<table>" !== f[1] || Ve.test(a) ? 0 : u : u.firstChild, o = a && a.childNodes.length; o--;)pe.nodeName(c = a.childNodes[o], "tbody") && !c.childNodes.length && a.removeChild(c);
            for (pe.merge(v, u.childNodes), u.textContent = ""; u.firstChild;)u.removeChild(u.firstChild);
            u = y.lastChild
        } else v.push(t.createTextNode(a));
        for (u && y.removeChild(u), fe.appendChecked || pe.grep(h(v, "input"), m), x = 0; a = v[x++];)if (r && pe.inArray(a, r) > -1)i && i.push(a); else if (s = pe.contains(a.ownerDocument, a), u = h(y.appendChild(a), "script"), s && g(u), n)for (o = 0; a = u[o++];)Ie.test(a.type || "") && n.push(a);
        return u = null, y
    }

    function v() {
        return !0
    }

    function x() {
        return !1
    }

    function b() {
        try {
            return re.activeElement
        } catch (e) {
        }
    }

    function w(e, t, n, r, i, o) {
        var a, s;
        if ("object" == typeof t) {
            "string" != typeof n && (r = r || n, n = void 0);
            for (s in t)w(e, s, n, r, t[s], o);
            return e
        }
        if (null == r && null == i ? (i = n, r = n = void 0) : null == i && ("string" == typeof n ? (i = r, r = void 0) : (i = r, r = n, n = void 0)), i === !1)i = x; else if (!i)return e;
        return 1 === o && (a = i, i = function (e) {
            return pe().off(e), a.apply(this, arguments)
        }, i.guid = a.guid || (a.guid = pe.guid++)), e.each(function () {
            pe.event.add(this, t, i, r, n)
        })
    }

    function T(e, t) {
        return pe.nodeName(e, "table") && pe.nodeName(11 !== t.nodeType ? t : t.firstChild, "tr") ? e.getElementsByTagName("tbody")[0] || e.appendChild(e.ownerDocument.createElement("tbody")) : e
    }

    function C(e) {
        return e.type = (null !== pe.find.attr(e, "type")) + "/" + e.type, e
    }

    function E(e) {
        var t = it.exec(e.type);
        return t ? e.type = t[1] : e.removeAttribute("type"), e
    }

    function N(e, t) {
        if (1 === t.nodeType && pe.hasData(e)) {
            var n, r, i, o = pe._data(e), a = pe._data(t, o), s = o.events;
            if (s) {
                delete a.handle, a.events = {};
                for (n in s)for (r = 0, i = s[n].length; r < i; r++)pe.event.add(t, n, s[n][r])
            }
            a.data && (a.data = pe.extend({}, a.data))
        }
    }

    function k(e, t) {
        var n, r, i;
        if (1 === t.nodeType) {
            if (n = t.nodeName.toLowerCase(), !fe.noCloneEvent && t[pe.expando]) {
                i = pe._data(t);
                for (r in i.events)pe.removeEvent(t, r, i.handle);
                t.removeAttribute(pe.expando)
            }
            "script" === n && t.text !== e.text ? (C(t).text = e.text, E(t)) : "object" === n ? (t.parentNode && (t.outerHTML = e.outerHTML), fe.html5Clone && e.innerHTML && !pe.trim(t.innerHTML) && (t.innerHTML = e.innerHTML)) : "input" === n && Be.test(e.type) ? (t.defaultChecked = t.checked = e.checked, t.value !== e.value && (t.value = e.value)) : "option" === n ? t.defaultSelected = t.selected = e.defaultSelected : "input" !== n && "textarea" !== n || (t.defaultValue = e.defaultValue)
        }
    }

    function S(e, t, n, r) {
        t = oe.apply([], t);
        var i, o, a, s, u, l, c = 0, f = e.length, d = f - 1, p = t[0], g = pe.isFunction(p);
        if (g || f > 1 && "string" == typeof p && !fe.checkClone && rt.test(p))return e.each(function (i) {
            var o = e.eq(i);
            g && (t[0] = p.call(this, i, o.html())), S(o, t, n, r)
        });
        if (f && (l = y(t, e[0].ownerDocument, !1, e, r), i = l.firstChild, 1 === l.childNodes.length && (l = i), i || r)) {
            for (s = pe.map(h(l, "script"), C), a = s.length; c < f; c++)o = l, c !== d && (o = pe.clone(o, !0, !0), a && pe.merge(s, h(o, "script"))), n.call(e[c], o, c);
            if (a)for (u = s[s.length - 1].ownerDocument, pe.map(s, E), c = 0; c < a; c++)o = s[c], Ie.test(o.type || "") && !pe._data(o, "globalEval") && pe.contains(u, o) && (o.src ? pe._evalUrl && pe._evalUrl(o.src) : pe.globalEval((o.text || o.textContent || o.innerHTML || "").replace(ot, "")));
            l = i = null
        }
        return e
    }

    function A(e, t, n) {
        for (var r, i = t ? pe.filter(t, e) : e, o = 0; null != (r = i[o]); o++)n || 1 !== r.nodeType || pe.cleanData(h(r)), r.parentNode && (n && pe.contains(r.ownerDocument, r) && g(h(r, "script")), r.parentNode.removeChild(r));
        return e
    }

    function D(e, t) {
        var n = pe(t.createElement(e)).appendTo(t.body), r = pe.css(n[0], "display");
        return n.detach(), r
    }

    function j(e) {
        var t = re, n = lt[e];
        return n || (n = D(e, t), "none" !== n && n || (ut = (ut || pe("<iframe frameborder='0' width='0' height='0'/>")).appendTo(t.documentElement), t = (ut[0].contentWindow || ut[0].contentDocument).document, t.write(), t.close(), n = D(e, t), ut.detach()), lt[e] = n), n
    }

    function L(e, t) {
        return {
            get: function () {
                return e() ? void delete this.get : (this.get = t).apply(this, arguments)
            }
        }
    }

    function H(e) {
        if (e in Et)return e;
        for (var t = e.charAt(0).toUpperCase() + e.slice(1), n = Ct.length; n--;)if (e = Ct[n] + t, e in Et)return e
    }

    function q(e, t) {
        for (var n, r, i, o = [], a = 0, s = e.length; a < s; a++)r = e[a], r.style && (o[a] = pe._data(r, "olddisplay"), n = r.style.display, t ? (o[a] || "none" !== n || (r.style.display = ""), "" === r.style.display && Re(r) && (o[a] = pe._data(r, "olddisplay", j(r.nodeName)))) : (i = Re(r), (n && "none" !== n || !i) && pe._data(r, "olddisplay", i ? n : pe.css(r, "display"))));
        for (a = 0; a < s; a++)r = e[a], r.style && (t && "none" !== r.style.display && "" !== r.style.display || (r.style.display = t ? o[a] || "" : "none"));
        return e
    }

    function _(e, t, n) {
        var r = bt.exec(t);
        return r ? Math.max(0, r[1] - (n || 0)) + (r[2] || "px") : t
    }

    function F(e, t, n, r, i) {
        for (var o = n === (r ? "border" : "content") ? 4 : "width" === t ? 1 : 0, a = 0; o < 4; o += 2)"margin" === n && (a += pe.css(e, n + Oe[o], !0, i)), r ? ("content" === n && (a -= pe.css(e, "padding" + Oe[o], !0, i)), "margin" !== n && (a -= pe.css(e, "border" + Oe[o] + "Width", !0, i))) : (a += pe.css(e, "padding" + Oe[o], !0, i), "padding" !== n && (a += pe.css(e, "border" + Oe[o] + "Width", !0, i)));
        return a
    }

    function M(t, n, r) {
        var i = !0, o = "width" === n ? t.offsetWidth : t.offsetHeight, a = ht(t), s = fe.boxSizing && "border-box" === pe.css(t, "boxSizing", !1, a);
        if (re.msFullscreenElement && e.top !== e && t.getClientRects().length && (o = Math.round(100 * t.getBoundingClientRect()[n])), o <= 0 || null == o) {
            if (o = gt(t, n, a), (o < 0 || null == o) && (o = t.style[n]), ft.test(o))return o;
            i = s && (fe.boxSizingReliable() || o === t.style[n]), o = parseFloat(o) || 0
        }
        return o + F(t, n, r || (s ? "border" : "content"), i, a) + "px"
    }

    function O(e, t, n, r, i) {
        return new O.prototype.init(e, t, n, r, i)
    }

    function R() {
        return e.setTimeout(function () {
            Nt = void 0
        }), Nt = pe.now()
    }

    function P(e, t) {
        var n, r = {height: e}, i = 0;
        for (t = t ? 1 : 0; i < 4; i += 2 - t)n = Oe[i], r["margin" + n] = r["padding" + n] = e;
        return t && (r.opacity = r.width = e), r
    }

    function B(e, t, n) {
        for (var r, i = ($.tweeners[t] || []).concat($.tweeners["*"]), o = 0, a = i.length; o < a; o++)if (r = i[o].call(n, t, e))return r
    }

    function W(e, t, n) {
        var r, i, o, a, s, u, l, c, f = this, d = {}, p = e.style, h = e.nodeType && Re(e), g = pe._data(e, "fxshow");
        n.queue || (s = pe._queueHooks(e, "fx"), null == s.unqueued && (s.unqueued = 0, u = s.empty.fire, s.empty.fire = function () {
            s.unqueued || u()
        }), s.unqueued++, f.always(function () {
            f.always(function () {
                s.unqueued--, pe.queue(e, "fx").length || s.empty.fire()
            })
        })), 1 === e.nodeType && ("height" in t || "width" in t) && (n.overflow = [p.overflow, p.overflowX, p.overflowY], l = pe.css(e, "display"), c = "none" === l ? pe._data(e, "olddisplay") || j(e.nodeName) : l, "inline" === c && "none" === pe.css(e, "float") && (fe.inlineBlockNeedsLayout && "inline" !== j(e.nodeName) ? p.zoom = 1 : p.display = "inline-block")), n.overflow && (p.overflow = "hidden", fe.shrinkWrapBlocks() || f.always(function () {
            p.overflow = n.overflow[0], p.overflowX = n.overflow[1], p.overflowY = n.overflow[2]
        }));
        for (r in t)if (i = t[r], St.exec(i)) {
            if (delete t[r], o = o || "toggle" === i, i === (h ? "hide" : "show")) {
                if ("show" !== i || !g || void 0 === g[r])continue;
                h = !0
            }
            d[r] = g && g[r] || pe.style(e, r)
        } else l = void 0;
        if (pe.isEmptyObject(d))"inline" === ("none" === l ? j(e.nodeName) : l) && (p.display = l); else {
            g ? "hidden" in g && (h = g.hidden) : g = pe._data(e, "fxshow", {}), o && (g.hidden = !h), h ? pe(e).show() : f.done(function () {
                pe(e).hide()
            }), f.done(function () {
                var t;
                pe._removeData(e, "fxshow");
                for (t in d)pe.style(e, t, d[t])
            });
            for (r in d)a = B(h ? g[r] : 0, r, f), r in g || (g[r] = a.start, h && (a.end = a.start, a.start = "width" === r || "height" === r ? 1 : 0))
        }
    }

    function I(e, t) {
        var n, r, i, o, a;
        for (n in e)if (r = pe.camelCase(n), i = t[r], o = e[n], pe.isArray(o) && (i = o[1], o = e[n] = o[0]), n !== r && (e[r] = o, delete e[n]), a = pe.cssHooks[r], a && "expand" in a) {
            o = a.expand(o), delete e[r];
            for (n in o)n in e || (e[n] = o[n], t[n] = i)
        } else t[r] = i
    }

    function $(e, t, n) {
        var r, i, o = 0, a = $.prefilters.length, s = pe.Deferred().always(function () {
            delete u.elem
        }), u = function () {
            if (i)return !1;
            for (var t = Nt || R(), n = Math.max(0, l.startTime + l.duration - t), r = n / l.duration || 0, o = 1 - r, a = 0, u = l.tweens.length; a < u; a++)l.tweens[a].run(o);
            return s.notifyWith(e, [l, o, n]), o < 1 && u ? n : (s.resolveWith(e, [l]), !1)
        }, l = s.promise({
            elem: e,
            props: pe.extend({}, t),
            opts: pe.extend(!0, {specialEasing: {}, easing: pe.easing._default}, n),
            originalProperties: t,
            originalOptions: n,
            startTime: Nt || R(),
            duration: n.duration,
            tweens: [],
            createTween: function (t, n) {
                var r = pe.Tween(e, l.opts, t, n, l.opts.specialEasing[t] || l.opts.easing);
                return l.tweens.push(r), r
            },
            stop: function (t) {
                var n = 0, r = t ? l.tweens.length : 0;
                if (i)return this;
                for (i = !0; n < r; n++)l.tweens[n].run(1);
                return t ? (s.notifyWith(e, [l, 1, 0]), s.resolveWith(e, [l, t])) : s.rejectWith(e, [l, t]), this
            }
        }), c = l.props;
        for (I(c, l.opts.specialEasing); o < a; o++)if (r = $.prefilters[o].call(l, e, c, l.opts))return pe.isFunction(r.stop) && (pe._queueHooks(l.elem, l.opts.queue).stop = pe.proxy(r.stop, r)), r;
        return pe.map(c, B, l), pe.isFunction(l.opts.start) && l.opts.start.call(e, l), pe.fx.timer(pe.extend(u, {
            elem: e,
            anim: l,
            queue: l.opts.queue
        })), l.progress(l.opts.progress).done(l.opts.done, l.opts.complete).fail(l.opts.fail).always(l.opts.always)
    }

    function z(e) {
        return pe.attr(e, "class") || ""
    }

    function X(e) {
        return function (t, n) {
            "string" != typeof t && (n = t, t = "*");
            var r, i = 0, o = t.toLowerCase().match(De) || [];
            if (pe.isFunction(n))for (; r = o[i++];)"+" === r.charAt(0) ? (r = r.slice(1) || "*", (e[r] = e[r] || []).unshift(n)) : (e[r] = e[r] || []).push(n)
        }
    }

    function U(e, t, n, r) {
        function i(s) {
            var u;
            return o[s] = !0, pe.each(e[s] || [], function (e, s) {
                var l = s(t, n, r);
                return "string" != typeof l || a || o[l] ? a ? !(u = l) : void 0 : (t.dataTypes.unshift(l), i(l), !1)
            }), u
        }

        var o = {}, a = e === Qt;
        return i(t.dataTypes[0]) || !o["*"] && i("*")
    }

    function V(e, t) {
        var n, r, i = pe.ajaxSettings.flatOptions || {};
        for (r in t)void 0 !== t[r] && ((i[r] ? e : n || (n = {}))[r] = t[r]);
        return n && pe.extend(!0, e, n), e
    }

    function Y(e, t, n) {
        for (var r, i, o, a, s = e.contents, u = e.dataTypes; "*" === u[0];)u.shift(), void 0 === i && (i = e.mimeType || t.getResponseHeader("Content-Type"));
        if (i)for (a in s)if (s[a] && s[a].test(i)) {
            u.unshift(a);
            break
        }
        if (u[0] in n)o = u[0]; else {
            for (a in n) {
                if (!u[0] || e.converters[a + " " + u[0]]) {
                    o = a;
                    break
                }
                r || (r = a)
            }
            o = o || r
        }
        if (o)return o !== u[0] && u.unshift(o), n[o]
    }

    function J(e, t, n, r) {
        var i, o, a, s, u, l = {}, c = e.dataTypes.slice();
        if (c[1])for (a in e.converters)l[a.toLowerCase()] = e.converters[a];
        for (o = c.shift(); o;)if (e.responseFields[o] && (n[e.responseFields[o]] = t), !u && r && e.dataFilter && (t = e.dataFilter(t, e.dataType)), u = o, o = c.shift())if ("*" === o)o = u; else if ("*" !== u && u !== o) {
            if (a = l[u + " " + o] || l["* " + o], !a)for (i in l)if (s = i.split(" "), s[1] === o && (a = l[u + " " + s[0]] || l["* " + s[0]])) {
                a === !0 ? a = l[i] : l[i] !== !0 && (o = s[0], c.unshift(s[1]));
                break
            }
            if (a !== !0)if (a && e["throws"])t = a(t); else try {
                t = a(t)
            } catch (f) {
                return {state: "parsererror", error: a ? f : "No conversion from " + u + " to " + o}
            }
        }
        return {state: "success", data: t}
    }

    function G(e) {
        return e.style && e.style.display || pe.css(e, "display")
    }

    function K(e) {
        for (; e && 1 === e.nodeType;) {
            if ("none" === G(e) || "hidden" === e.type)return !0;
            e = e.parentNode
        }
        return !1
    }

    function Q(e, t, n, r) {
        var i;
        if (pe.isArray(t))pe.each(t, function (t, i) {
            n || rn.test(e) ? r(e, i) : Q(e + "[" + ("object" == typeof i && null != i ? t : "") + "]", i, n, r)
        }); else if (n || "object" !== pe.type(t))r(e, t); else for (i in t)Q(e + "[" + i + "]", t[i], n, r)
    }

    function Z() {
        try {
            return new e.XMLHttpRequest
        } catch (t) {
        }
    }

    function ee() {
        try {
            return new e.ActiveXObject("Microsoft.XMLHTTP")
        } catch (t) {
        }
    }

    function te(e) {
        return pe.isWindow(e) ? e : 9 === e.nodeType && (e.defaultView || e.parentWindow)
    }

    var ne = [], re = e.document, ie = ne.slice, oe = ne.concat, ae = ne.push, se = ne.indexOf, ue = {}, le = ue.toString, ce = ue.hasOwnProperty, fe = {}, de = "1.12.3", pe = function (e, t) {
        return new pe.fn.init(e, t)
    }, he = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g, ge = /^-ms-/, me = /-([\da-z])/gi, ye = function (e, t) {
        return t.toUpperCase()
    };
    pe.fn = pe.prototype = {
        jquery: de, constructor: pe, selector: "", length: 0, toArray: function () {
            return ie.call(this)
        }, get: function (e) {
            return null != e ? e < 0 ? this[e + this.length] : this[e] : ie.call(this)
        }, pushStack: function (e) {
            var t = pe.merge(this.constructor(), e);
            return t.prevObject = this, t.context = this.context, t
        }, each: function (e) {
            return pe.each(this, e)
        }, map: function (e) {
            return this.pushStack(pe.map(this, function (t, n) {
                return e.call(t, n, t)
            }))
        }, slice: function () {
            return this.pushStack(ie.apply(this, arguments))
        }, first: function () {
            return this.eq(0)
        }, last: function () {
            return this.eq(-1)
        }, eq: function (e) {
            var t = this.length, n = +e + (e < 0 ? t : 0);
            return this.pushStack(n >= 0 && n < t ? [this[n]] : [])
        }, end: function () {
            return this.prevObject || this.constructor()
        }, push: ae, sort: ne.sort, splice: ne.splice
    }, pe.extend = pe.fn.extend = function () {
        var e, t, n, r, i, o, a = arguments[0] || {}, s = 1, u = arguments.length, l = !1;
        for ("boolean" == typeof a && (l = a, a = arguments[s] || {}, s++), "object" == typeof a || pe.isFunction(a) || (a = {}), s === u && (a = this, s--); s < u; s++)if (null != (i = arguments[s]))for (r in i)e = a[r], n = i[r], a !== n && (l && n && (pe.isPlainObject(n) || (t = pe.isArray(n))) ? (t ? (t = !1, o = e && pe.isArray(e) ? e : []) : o = e && pe.isPlainObject(e) ? e : {}, a[r] = pe.extend(l, o, n)) : void 0 !== n && (a[r] = n));
        return a
    }, pe.extend({
        expando: "jQuery" + (de + Math.random()).replace(/\D/g, ""), isReady: !0, error: function (e) {
            throw new Error(e)
        }, noop: function () {
        }, isFunction: function (e) {
            return "function" === pe.type(e)
        }, isArray: Array.isArray || function (e) {
            return "array" === pe.type(e)
        }, isWindow: function (e) {
            return null != e && e == e.window
        }, isNumeric: function (e) {
            var t = e && e.toString();
            return !pe.isArray(e) && t - parseFloat(t) + 1 >= 0
        }, isEmptyObject: function (e) {
            var t;
            for (t in e)return !1;
            return !0
        }, isPlainObject: function (e) {
            var t;
            if (!e || "object" !== pe.type(e) || e.nodeType || pe.isWindow(e))return !1;
            try {
                if (e.constructor && !ce.call(e, "constructor") && !ce.call(e.constructor.prototype, "isPrototypeOf"))return !1
            } catch (n) {
                return !1
            }
            if (!fe.ownFirst)for (t in e)return ce.call(e, t);
            for (t in e);
            return void 0 === t || ce.call(e, t)
        }, type: function (e) {
            return null == e ? e + "" : "object" == typeof e || "function" == typeof e ? ue[le.call(e)] || "object" : typeof e
        }, globalEval: function (t) {
            t && pe.trim(t) && (e.execScript || function (t) {
                e.eval.call(e, t)
            })(t)
        }, camelCase: function (e) {
            return e.replace(ge, "ms-").replace(me, ye)
        }, nodeName: function (e, t) {
            return e.nodeName && e.nodeName.toLowerCase() === t.toLowerCase()
        }, each: function (e, t) {
            var r, i = 0;
            if (n(e))for (r = e.length; i < r && t.call(e[i], i, e[i]) !== !1; i++); else for (i in e)if (t.call(e[i], i, e[i]) === !1)break;
            return e
        }, trim: function (e) {
            return null == e ? "" : (e + "").replace(he, "")
        }, makeArray: function (e, t) {
            var r = t || [];
            return null != e && (n(Object(e)) ? pe.merge(r, "string" == typeof e ? [e] : e) : ae.call(r, e)), r
        }, inArray: function (e, t, n) {
            var r;
            if (t) {
                if (se)return se.call(t, e, n);
                for (r = t.length, n = n ? n < 0 ? Math.max(0, r + n) : n : 0; n < r; n++)if (n in t && t[n] === e)return n
            }
            return -1
        }, merge: function (e, t) {
            for (var n = +t.length, r = 0, i = e.length; r < n;)e[i++] = t[r++];
            if (n !== n)for (; void 0 !== t[r];)e[i++] = t[r++];
            return e.length = i, e
        }, grep: function (e, t, n) {
            for (var r, i = [], o = 0, a = e.length, s = !n; o < a; o++)r = !t(e[o], o), r !== s && i.push(e[o]);
            return i
        }, map: function (e, t, r) {
            var i, o, a = 0, s = [];
            if (n(e))for (i = e.length; a < i; a++)o = t(e[a], a, r), null != o && s.push(o); else for (a in e)o = t(e[a], a, r), null != o && s.push(o);
            return oe.apply([], s)
        }, guid: 1, proxy: function (e, t) {
            var n, r, i;
            if ("string" == typeof t && (i = e[t], t = e, e = i), pe.isFunction(e))return n = ie.call(arguments, 2), r = function () {
                return e.apply(t || this, n.concat(ie.call(arguments)))
            }, r.guid = e.guid = e.guid || pe.guid++, r
        }, now: function () {
            return +new Date
        }, support: fe
    }), "function" == typeof Symbol && (pe.fn[Symbol.iterator] = ne[Symbol.iterator]), pe.each("Boolean Number String Function Array Date RegExp Object Error Symbol".split(" "), function (e, t) {
        ue["[object " + t + "]"] = t.toLowerCase()
    });
    var ve = function (e) {
        function t(e, t, n, r) {
            var i, o, a, s, u, l, f, p, h = t && t.ownerDocument, g = t ? t.nodeType : 9;
            if (n = n || [], "string" != typeof e || !e || 1 !== g && 9 !== g && 11 !== g)return n;
            if (!r && ((t ? t.ownerDocument || t : B) !== H && L(t), t = t || H, _)) {
                if (11 !== g && (l = ye.exec(e)))if (i = l[1]) {
                    if (9 === g) {
                        if (!(a = t.getElementById(i)))return n;
                        if (a.id === i)return n.push(a), n
                    } else if (h && (a = h.getElementById(i)) && R(t, a) && a.id === i)return n.push(a), n
                } else {
                    if (l[2])return Q.apply(n, t.getElementsByTagName(e)), n;
                    if ((i = l[3]) && w.getElementsByClassName && t.getElementsByClassName)return Q.apply(n, t.getElementsByClassName(i)), n
                }
                if (w.qsa && !X[e + " "] && (!F || !F.test(e))) {
                    if (1 !== g)h = t, p = e; else if ("object" !== t.nodeName.toLowerCase()) {
                        for ((s = t.getAttribute("id")) ? s = s.replace(xe, "\\$&") : t.setAttribute("id", s = P), f = N(e), o = f.length, u = de.test(s) ? "#" + s : "[id='" + s + "']"; o--;)f[o] = u + " " + d(f[o]);
                        p = f.join(","), h = ve.test(e) && c(t.parentNode) || t
                    }
                    if (p)try {
                        return Q.apply(n, h.querySelectorAll(p)), n
                    } catch (m) {
                    } finally {
                        s === P && t.removeAttribute("id")
                    }
                }
            }
            return S(e.replace(se, "$1"), t, n, r)
        }

        function n() {
            function e(n, r) {
                return t.push(n + " ") > T.cacheLength && delete e[t.shift()], e[n + " "] = r
            }

            var t = [];
            return e
        }

        function r(e) {
            return e[P] = !0, e
        }

        function i(e) {
            var t = H.createElement("div");
            try {
                return !!e(t)
            } catch (n) {
                return !1
            } finally {
                t.parentNode && t.parentNode.removeChild(t), t = null
            }
        }

        function o(e, t) {
            for (var n = e.split("|"), r = n.length; r--;)T.attrHandle[n[r]] = t
        }

        function a(e, t) {
            var n = t && e, r = n && 1 === e.nodeType && 1 === t.nodeType && (~t.sourceIndex || V) - (~e.sourceIndex || V);
            if (r)return r;
            if (n)for (; n = n.nextSibling;)if (n === t)return -1;
            return e ? 1 : -1
        }

        function s(e) {
            return function (t) {
                var n = t.nodeName.toLowerCase();
                return "input" === n && t.type === e
            }
        }

        function u(e) {
            return function (t) {
                var n = t.nodeName.toLowerCase();
                return ("input" === n || "button" === n) && t.type === e
            }
        }

        function l(e) {
            return r(function (t) {
                return t = +t, r(function (n, r) {
                    for (var i, o = e([], n.length, t), a = o.length; a--;)n[i = o[a]] && (n[i] = !(r[i] = n[i]))
                })
            })
        }

        function c(e) {
            return e && "undefined" != typeof e.getElementsByTagName && e
        }

        function f() {
        }

        function d(e) {
            for (var t = 0, n = e.length, r = ""; t < n; t++)r += e[t].value;
            return r
        }

        function p(e, t, n) {
            var r = t.dir, i = n && "parentNode" === r, o = I++;
            return t.first ? function (t, n, o) {
                for (; t = t[r];)if (1 === t.nodeType || i)return e(t, n, o)
            } : function (t, n, a) {
                var s, u, l, c = [W, o];
                if (a) {
                    for (; t = t[r];)if ((1 === t.nodeType || i) && e(t, n, a))return !0
                } else for (; t = t[r];)if (1 === t.nodeType || i) {
                    if (l = t[P] || (t[P] = {}), u = l[t.uniqueID] || (l[t.uniqueID] = {}), (s = u[r]) && s[0] === W && s[1] === o)return c[2] = s[2];
                    if (u[r] = c, c[2] = e(t, n, a))return !0
                }
            }
        }

        function h(e) {
            return e.length > 1 ? function (t, n, r) {
                for (var i = e.length; i--;)if (!e[i](t, n, r))return !1;
                return !0
            } : e[0]
        }

        function g(e, n, r) {
            for (var i = 0, o = n.length; i < o; i++)t(e, n[i], r);
            return r
        }

        function m(e, t, n, r, i) {
            for (var o, a = [], s = 0, u = e.length, l = null != t; s < u; s++)(o = e[s]) && (n && !n(o, r, i) || (a.push(o), l && t.push(s)));
            return a
        }

        function y(e, t, n, i, o, a) {
            return i && !i[P] && (i = y(i)), o && !o[P] && (o = y(o, a)), r(function (r, a, s, u) {
                var l, c, f, d = [], p = [], h = a.length, y = r || g(t || "*", s.nodeType ? [s] : s, []), v = !e || !r && t ? y : m(y, d, e, s, u), x = n ? o || (r ? e : h || i) ? [] : a : v;
                if (n && n(v, x, s, u), i)for (l = m(x, p), i(l, [], s, u), c = l.length; c--;)(f = l[c]) && (x[p[c]] = !(v[p[c]] = f));
                if (r) {
                    if (o || e) {
                        if (o) {
                            for (l = [], c = x.length; c--;)(f = x[c]) && l.push(v[c] = f);
                            o(null, x = [], l, u)
                        }
                        for (c = x.length; c--;)(f = x[c]) && (l = o ? ee(r, f) : d[c]) > -1 && (r[l] = !(a[l] = f))
                    }
                } else x = m(x === a ? x.splice(h, x.length) : x), o ? o(null, a, x, u) : Q.apply(a, x)
            })
        }

        function v(e) {
            for (var t, n, r, i = e.length, o = T.relative[e[0].type], a = o || T.relative[" "], s = o ? 1 : 0, u = p(function (e) {
                return e === t
            }, a, !0), l = p(function (e) {
                return ee(t, e) > -1
            }, a, !0), c = [function (e, n, r) {
                var i = !o && (r || n !== A) || ((t = n).nodeType ? u(e, n, r) : l(e, n, r));
                return t = null, i
            }]; s < i; s++)if (n = T.relative[e[s].type])c = [p(h(c), n)]; else {
                if (n = T.filter[e[s].type].apply(null, e[s].matches), n[P]) {
                    for (r = ++s; r < i && !T.relative[e[r].type]; r++);
                    return y(s > 1 && h(c), s > 1 && d(e.slice(0, s - 1).concat({value: " " === e[s - 2].type ? "*" : ""})).replace(se, "$1"), n, s < r && v(e.slice(s, r)), r < i && v(e = e.slice(r)), r < i && d(e))
                }
                c.push(n)
            }
            return h(c)
        }

        function x(e, n) {
            var i = n.length > 0, o = e.length > 0, a = function (r, a, s, u, l) {
                var c, f, d, p = 0, h = "0", g = r && [], y = [], v = A, x = r || o && T.find.TAG("*", l), b = W += null == v ? 1 : Math.random() || .1, w = x.length;
                for (l && (A = a === H || a || l); h !== w && null != (c = x[h]); h++) {
                    if (o && c) {
                        for (f = 0, a || c.ownerDocument === H || (L(c), s = !_); d = e[f++];)if (d(c, a || H, s)) {
                            u.push(c);
                            break
                        }
                        l && (W = b)
                    }
                    i && ((c = !d && c) && p--, r && g.push(c))
                }
                if (p += h, i && h !== p) {
                    for (f = 0; d = n[f++];)d(g, y, a, s);
                    if (r) {
                        if (p > 0)for (; h--;)g[h] || y[h] || (y[h] = G.call(u));
                        y = m(y)
                    }
                    Q.apply(u, y), l && !r && y.length > 0 && p + n.length > 1 && t.uniqueSort(u)
                }
                return l && (W = b, A = v), g
            };
            return i ? r(a) : a
        }

        var b, w, T, C, E, N, k, S, A, D, j, L, H, q, _, F, M, O, R, P = "sizzle" + 1 * new Date, B = e.document, W = 0, I = 0, $ = n(), z = n(), X = n(), U = function (e, t) {
            return e === t && (j = !0), 0
        }, V = 1 << 31, Y = {}.hasOwnProperty, J = [], G = J.pop, K = J.push, Q = J.push, Z = J.slice, ee = function (e, t) {
            for (var n = 0, r = e.length; n < r; n++)if (e[n] === t)return n;
            return -1
        }, te = "checked|selected|async|autofocus|autoplay|controls|defer|disabled|hidden|ismap|loop|multiple|open|readonly|required|scoped", ne = "[\\x20\\t\\r\\n\\f]", re = "(?:\\\\.|[\\w-]|[^\\x00-\\xa0])+", ie = "\\[" + ne + "*(" + re + ")(?:" + ne + "*([*^$|!~]?=)" + ne + "*(?:'((?:\\\\.|[^\\\\'])*)'|\"((?:\\\\.|[^\\\\\"])*)\"|(" + re + "))|)" + ne + "*\\]", oe = ":(" + re + ")(?:\\((('((?:\\\\.|[^\\\\'])*)'|\"((?:\\\\.|[^\\\\\"])*)\")|((?:\\\\.|[^\\\\()[\\]]|" + ie + ")*)|.*)\\)|)", ae = new RegExp(ne + "+", "g"), se = new RegExp("^" + ne + "+|((?:^|[^\\\\])(?:\\\\.)*)" + ne + "+$", "g"), ue = new RegExp("^" + ne + "*," + ne + "*"), le = new RegExp("^" + ne + "*([>+~]|" + ne + ")" + ne + "*"), ce = new RegExp("=" + ne + "*([^\\]'\"]*?)" + ne + "*\\]", "g"), fe = new RegExp(oe), de = new RegExp("^" + re + "$"), pe = {
            ID: new RegExp("^#(" + re + ")"),
            CLASS: new RegExp("^\\.(" + re + ")"),
            TAG: new RegExp("^(" + re + "|[*])"),
            ATTR: new RegExp("^" + ie),
            PSEUDO: new RegExp("^" + oe),
            CHILD: new RegExp("^:(only|first|last|nth|nth-last)-(child|of-type)(?:\\(" + ne + "*(even|odd|(([+-]|)(\\d*)n|)" + ne + "*(?:([+-]|)" + ne + "*(\\d+)|))" + ne + "*\\)|)", "i"),
            bool: new RegExp("^(?:" + te + ")$", "i"),
            needsContext: new RegExp("^" + ne + "*[>+~]|:(even|odd|eq|gt|lt|nth|first|last)(?:\\(" + ne + "*((?:-\\d)?\\d*)" + ne + "*\\)|)(?=[^-]|$)", "i")
        }, he = /^(?:input|select|textarea|button)$/i, ge = /^h\d$/i, me = /^[^{]+\{\s*\[native \w/, ye = /^(?:#([\w-]+)|(\w+)|\.([\w-]+))$/, ve = /[+~]/, xe = /'|\\/g, be = new RegExp("\\\\([\\da-f]{1,6}" + ne + "?|(" + ne + ")|.)", "ig"), we = function (e, t, n) {
            var r = "0x" + t - 65536;
            return r !== r || n ? t : r < 0 ? String.fromCharCode(r + 65536) : String.fromCharCode(r >> 10 | 55296, 1023 & r | 56320)
        }, Te = function () {
            L()
        };
        try {
            Q.apply(J = Z.call(B.childNodes), B.childNodes), J[B.childNodes.length].nodeType
        } catch (Ce) {
            Q = {
                apply: J.length ? function (e, t) {
                    K.apply(e, Z.call(t))
                } : function (e, t) {
                    for (var n = e.length, r = 0; e[n++] = t[r++];);
                    e.length = n - 1
                }
            }
        }
        w = t.support = {}, E = t.isXML = function (e) {
            var t = e && (e.ownerDocument || e).documentElement;
            return !!t && "HTML" !== t.nodeName
        }, L = t.setDocument = function (e) {
            var t, n, r = e ? e.ownerDocument || e : B;
            return r !== H && 9 === r.nodeType && r.documentElement ? (H = r, q = H.documentElement, _ = !E(H), (n = H.defaultView) && n.top !== n && (n.addEventListener ? n.addEventListener("unload", Te, !1) : n.attachEvent && n.attachEvent("onunload", Te)), w.attributes = i(function (e) {
                return e.className = "i", !e.getAttribute("className")
            }), w.getElementsByTagName = i(function (e) {
                return e.appendChild(H.createComment("")), !e.getElementsByTagName("*").length
            }), w.getElementsByClassName = me.test(H.getElementsByClassName), w.getById = i(function (e) {
                return q.appendChild(e).id = P, !H.getElementsByName || !H.getElementsByName(P).length
            }), w.getById ? (T.find.ID = function (e, t) {
                if ("undefined" != typeof t.getElementById && _) {
                    var n = t.getElementById(e);
                    return n ? [n] : []
                }
            }, T.filter.ID = function (e) {
                var t = e.replace(be, we);
                return function (e) {
                    return e.getAttribute("id") === t
                }
            }) : (delete T.find.ID, T.filter.ID = function (e) {
                var t = e.replace(be, we);
                return function (e) {
                    var n = "undefined" != typeof e.getAttributeNode && e.getAttributeNode("id");
                    return n && n.value === t
                }
            }), T.find.TAG = w.getElementsByTagName ? function (e, t) {
                return "undefined" != typeof t.getElementsByTagName ? t.getElementsByTagName(e) : w.qsa ? t.querySelectorAll(e) : void 0
            } : function (e, t) {
                var n, r = [], i = 0, o = t.getElementsByTagName(e);
                if ("*" === e) {
                    for (; n = o[i++];)1 === n.nodeType && r.push(n);
                    return r
                }
                return o
            }, T.find.CLASS = w.getElementsByClassName && function (e, t) {
                    if ("undefined" != typeof t.getElementsByClassName && _)return t.getElementsByClassName(e)
                }, M = [], F = [], (w.qsa = me.test(H.querySelectorAll)) && (i(function (e) {
                q.appendChild(e).innerHTML = "<a id='" + P + "'></a><select id='" + P + "-\r\\' msallowcapture=''><option selected=''></option></select>", e.querySelectorAll("[msallowcapture^='']").length && F.push("[*^$]=" + ne + "*(?:''|\"\")"), e.querySelectorAll("[selected]").length || F.push("\\[" + ne + "*(?:value|" + te + ")"), e.querySelectorAll("[id~=" + P + "-]").length || F.push("~="), e.querySelectorAll(":checked").length || F.push(":checked"), e.querySelectorAll("a#" + P + "+*").length || F.push(".#.+[+~]")
            }), i(function (e) {
                var t = H.createElement("input");
                t.setAttribute("type", "hidden"), e.appendChild(t).setAttribute("name", "D"), e.querySelectorAll("[name=d]").length && F.push("name" + ne + "*[*^$|!~]?="), e.querySelectorAll(":enabled").length || F.push(":enabled", ":disabled"), e.querySelectorAll("*,:x"), F.push(",.*:")
            })), (w.matchesSelector = me.test(O = q.matches || q.webkitMatchesSelector || q.mozMatchesSelector || q.oMatchesSelector || q.msMatchesSelector)) && i(function (e) {
                w.disconnectedMatch = O.call(e, "div"), O.call(e, "[s!='']:x"), M.push("!=", oe)
            }), F = F.length && new RegExp(F.join("|")), M = M.length && new RegExp(M.join("|")), t = me.test(q.compareDocumentPosition), R = t || me.test(q.contains) ? function (e, t) {
                var n = 9 === e.nodeType ? e.documentElement : e, r = t && t.parentNode;
                return e === r || !(!r || 1 !== r.nodeType || !(n.contains ? n.contains(r) : e.compareDocumentPosition && 16 & e.compareDocumentPosition(r)))
            } : function (e, t) {
                if (t)for (; t = t.parentNode;)if (t === e)return !0;
                return !1
            }, U = t ? function (e, t) {
                if (e === t)return j = !0, 0;
                var n = !e.compareDocumentPosition - !t.compareDocumentPosition;
                return n ? n : (n = (e.ownerDocument || e) === (t.ownerDocument || t) ? e.compareDocumentPosition(t) : 1, 1 & n || !w.sortDetached && t.compareDocumentPosition(e) === n ? e === H || e.ownerDocument === B && R(B, e) ? -1 : t === H || t.ownerDocument === B && R(B, t) ? 1 : D ? ee(D, e) - ee(D, t) : 0 : 4 & n ? -1 : 1)
            } : function (e, t) {
                if (e === t)return j = !0, 0;
                var n, r = 0, i = e.parentNode, o = t.parentNode, s = [e], u = [t];
                if (!i || !o)return e === H ? -1 : t === H ? 1 : i ? -1 : o ? 1 : D ? ee(D, e) - ee(D, t) : 0;
                if (i === o)return a(e, t);
                for (n = e; n = n.parentNode;)s.unshift(n);
                for (n = t; n = n.parentNode;)u.unshift(n);
                for (; s[r] === u[r];)r++;
                return r ? a(s[r], u[r]) : s[r] === B ? -1 : u[r] === B ? 1 : 0
            }, H) : H
        }, t.matches = function (e, n) {
            return t(e, null, null, n)
        }, t.matchesSelector = function (e, n) {
            if ((e.ownerDocument || e) !== H && L(e), n = n.replace(ce, "='$1']"), w.matchesSelector && _ && !X[n + " "] && (!M || !M.test(n)) && (!F || !F.test(n)))try {
                var r = O.call(e, n);
                if (r || w.disconnectedMatch || e.document && 11 !== e.document.nodeType)return r
            } catch (i) {
            }
            return t(n, H, null, [e]).length > 0
        }, t.contains = function (e, t) {
            return (e.ownerDocument || e) !== H && L(e), R(e, t)
        }, t.attr = function (e, t) {
            (e.ownerDocument || e) !== H && L(e);
            var n = T.attrHandle[t.toLowerCase()], r = n && Y.call(T.attrHandle, t.toLowerCase()) ? n(e, t, !_) : void 0;
            return void 0 !== r ? r : w.attributes || !_ ? e.getAttribute(t) : (r = e.getAttributeNode(t)) && r.specified ? r.value : null
        }, t.error = function (e) {
            throw new Error("Syntax error, unrecognized expression: " + e)
        }, t.uniqueSort = function (e) {
            var t, n = [], r = 0, i = 0;
            if (j = !w.detectDuplicates, D = !w.sortStable && e.slice(0), e.sort(U), j) {
                for (; t = e[i++];)t === e[i] && (r = n.push(i));
                for (; r--;)e.splice(n[r], 1)
            }
            return D = null, e
        }, C = t.getText = function (e) {
            var t, n = "", r = 0, i = e.nodeType;
            if (i) {
                if (1 === i || 9 === i || 11 === i) {
                    if ("string" == typeof e.textContent)return e.textContent;
                    for (e = e.firstChild; e; e = e.nextSibling)n += C(e)
                } else if (3 === i || 4 === i)return e.nodeValue
            } else for (; t = e[r++];)n += C(t);
            return n
        }, T = t.selectors = {
            cacheLength: 50,
            createPseudo: r,
            match: pe,
            attrHandle: {},
            find: {},
            relative: {
                ">": {dir: "parentNode", first: !0},
                " ": {dir: "parentNode"},
                "+": {dir: "previousSibling", first: !0},
                "~": {dir: "previousSibling"}
            },
            preFilter: {
                ATTR: function (e) {
                    return e[1] = e[1].replace(be, we), e[3] = (e[3] || e[4] || e[5] || "").replace(be, we), "~=" === e[2] && (e[3] = " " + e[3] + " "), e.slice(0, 4)
                }, CHILD: function (e) {
                    return e[1] = e[1].toLowerCase(), "nth" === e[1].slice(0, 3) ? (e[3] || t.error(e[0]), e[4] = +(e[4] ? e[5] + (e[6] || 1) : 2 * ("even" === e[3] || "odd" === e[3])), e[5] = +(e[7] + e[8] || "odd" === e[3])) : e[3] && t.error(e[0]), e
                }, PSEUDO: function (e) {
                    var t, n = !e[6] && e[2];
                    return pe.CHILD.test(e[0]) ? null : (e[3] ? e[2] = e[4] || e[5] || "" : n && fe.test(n) && (t = N(n, !0)) && (t = n.indexOf(")", n.length - t) - n.length) && (e[0] = e[0].slice(0, t), e[2] = n.slice(0, t)), e.slice(0, 3))
                }
            },
            filter: {
                TAG: function (e) {
                    var t = e.replace(be, we).toLowerCase();
                    return "*" === e ? function () {
                        return !0
                    } : function (e) {
                        return e.nodeName && e.nodeName.toLowerCase() === t
                    }
                }, CLASS: function (e) {
                    var t = $[e + " "];
                    return t || (t = new RegExp("(^|" + ne + ")" + e + "(" + ne + "|$)")) && $(e, function (e) {
                            return t.test("string" == typeof e.className && e.className || "undefined" != typeof e.getAttribute && e.getAttribute("class") || "")
                        })
                }, ATTR: function (e, n, r) {
                    return function (i) {
                        var o = t.attr(i, e);
                        return null == o ? "!=" === n : !n || (o += "", "=" === n ? o === r : "!=" === n ? o !== r : "^=" === n ? r && 0 === o.indexOf(r) : "*=" === n ? r && o.indexOf(r) > -1 : "$=" === n ? r && o.slice(-r.length) === r : "~=" === n ? (" " + o.replace(ae, " ") + " ").indexOf(r) > -1 : "|=" === n && (o === r || o.slice(0, r.length + 1) === r + "-"))
                    }
                }, CHILD: function (e, t, n, r, i) {
                    var o = "nth" !== e.slice(0, 3), a = "last" !== e.slice(-4), s = "of-type" === t;
                    return 1 === r && 0 === i ? function (e) {
                        return !!e.parentNode
                    } : function (t, n, u) {
                        var l, c, f, d, p, h, g = o !== a ? "nextSibling" : "previousSibling", m = t.parentNode, y = s && t.nodeName.toLowerCase(), v = !u && !s, x = !1;
                        if (m) {
                            if (o) {
                                for (; g;) {
                                    for (d = t; d = d[g];)if (s ? d.nodeName.toLowerCase() === y : 1 === d.nodeType)return !1;
                                    h = g = "only" === e && !h && "nextSibling"
                                }
                                return !0
                            }
                            if (h = [a ? m.firstChild : m.lastChild], a && v) {
                                for (d = m, f = d[P] || (d[P] = {}), c = f[d.uniqueID] || (f[d.uniqueID] = {}),
                                         l = c[e] || [], p = l[0] === W && l[1], x = p && l[2], d = p && m.childNodes[p]; d = ++p && d && d[g] || (x = p = 0) || h.pop();)if (1 === d.nodeType && ++x && d === t) {
                                    c[e] = [W, p, x];
                                    break
                                }
                            } else if (v && (d = t, f = d[P] || (d[P] = {}), c = f[d.uniqueID] || (f[d.uniqueID] = {}), l = c[e] || [], p = l[0] === W && l[1], x = p), x === !1)for (; (d = ++p && d && d[g] || (x = p = 0) || h.pop()) && ((s ? d.nodeName.toLowerCase() !== y : 1 !== d.nodeType) || !++x || (v && (f = d[P] || (d[P] = {}), c = f[d.uniqueID] || (f[d.uniqueID] = {}), c[e] = [W, x]), d !== t)););
                            return x -= i, x === r || x % r === 0 && x / r >= 0
                        }
                    }
                }, PSEUDO: function (e, n) {
                    var i, o = T.pseudos[e] || T.setFilters[e.toLowerCase()] || t.error("unsupported pseudo: " + e);
                    return o[P] ? o(n) : o.length > 1 ? (i = [e, e, "", n], T.setFilters.hasOwnProperty(e.toLowerCase()) ? r(function (e, t) {
                        for (var r, i = o(e, n), a = i.length; a--;)r = ee(e, i[a]), e[r] = !(t[r] = i[a])
                    }) : function (e) {
                        return o(e, 0, i)
                    }) : o
                }
            },
            pseudos: {
                not: r(function (e) {
                    var t = [], n = [], i = k(e.replace(se, "$1"));
                    return i[P] ? r(function (e, t, n, r) {
                        for (var o, a = i(e, null, r, []), s = e.length; s--;)(o = a[s]) && (e[s] = !(t[s] = o))
                    }) : function (e, r, o) {
                        return t[0] = e, i(t, null, o, n), t[0] = null, !n.pop()
                    }
                }), has: r(function (e) {
                    return function (n) {
                        return t(e, n).length > 0
                    }
                }), contains: r(function (e) {
                    return e = e.replace(be, we), function (t) {
                        return (t.textContent || t.innerText || C(t)).indexOf(e) > -1
                    }
                }), lang: r(function (e) {
                    return de.test(e || "") || t.error("unsupported lang: " + e), e = e.replace(be, we).toLowerCase(), function (t) {
                        var n;
                        do if (n = _ ? t.lang : t.getAttribute("xml:lang") || t.getAttribute("lang"))return n = n.toLowerCase(), n === e || 0 === n.indexOf(e + "-"); while ((t = t.parentNode) && 1 === t.nodeType);
                        return !1
                    }
                }), target: function (t) {
                    var n = e.location && e.location.hash;
                    return n && n.slice(1) === t.id
                }, root: function (e) {
                    return e === q
                }, focus: function (e) {
                    return e === H.activeElement && (!H.hasFocus || H.hasFocus()) && !!(e.type || e.href || ~e.tabIndex)
                }, enabled: function (e) {
                    return e.disabled === !1
                }, disabled: function (e) {
                    return e.disabled === !0
                }, checked: function (e) {
                    var t = e.nodeName.toLowerCase();
                    return "input" === t && !!e.checked || "option" === t && !!e.selected
                }, selected: function (e) {
                    return e.parentNode && e.parentNode.selectedIndex, e.selected === !0
                }, empty: function (e) {
                    for (e = e.firstChild; e; e = e.nextSibling)if (e.nodeType < 6)return !1;
                    return !0
                }, parent: function (e) {
                    return !T.pseudos.empty(e)
                }, header: function (e) {
                    return ge.test(e.nodeName)
                }, input: function (e) {
                    return he.test(e.nodeName)
                }, button: function (e) {
                    var t = e.nodeName.toLowerCase();
                    return "input" === t && "button" === e.type || "button" === t
                }, text: function (e) {
                    var t;
                    return "input" === e.nodeName.toLowerCase() && "text" === e.type && (null == (t = e.getAttribute("type")) || "text" === t.toLowerCase())
                }, first: l(function () {
                    return [0]
                }), last: l(function (e, t) {
                    return [t - 1]
                }), eq: l(function (e, t, n) {
                    return [n < 0 ? n + t : n]
                }), even: l(function (e, t) {
                    for (var n = 0; n < t; n += 2)e.push(n);
                    return e
                }), odd: l(function (e, t) {
                    for (var n = 1; n < t; n += 2)e.push(n);
                    return e
                }), lt: l(function (e, t, n) {
                    for (var r = n < 0 ? n + t : n; --r >= 0;)e.push(r);
                    return e
                }), gt: l(function (e, t, n) {
                    for (var r = n < 0 ? n + t : n; ++r < t;)e.push(r);
                    return e
                })
            }
        }, T.pseudos.nth = T.pseudos.eq;
        for (b in{radio: !0, checkbox: !0, file: !0, password: !0, image: !0})T.pseudos[b] = s(b);
        for (b in{submit: !0, reset: !0})T.pseudos[b] = u(b);
        return f.prototype = T.filters = T.pseudos, T.setFilters = new f, N = t.tokenize = function (e, n) {
            var r, i, o, a, s, u, l, c = z[e + " "];
            if (c)return n ? 0 : c.slice(0);
            for (s = e, u = [], l = T.preFilter; s;) {
                r && !(i = ue.exec(s)) || (i && (s = s.slice(i[0].length) || s), u.push(o = [])), r = !1, (i = le.exec(s)) && (r = i.shift(), o.push({
                    value: r,
                    type: i[0].replace(se, " ")
                }), s = s.slice(r.length));
                for (a in T.filter)!(i = pe[a].exec(s)) || l[a] && !(i = l[a](i)) || (r = i.shift(), o.push({
                    value: r,
                    type: a,
                    matches: i
                }), s = s.slice(r.length));
                if (!r)break
            }
            return n ? s.length : s ? t.error(e) : z(e, u).slice(0)
        }, k = t.compile = function (e, t) {
            var n, r = [], i = [], o = X[e + " "];
            if (!o) {
                for (t || (t = N(e)), n = t.length; n--;)o = v(t[n]), o[P] ? r.push(o) : i.push(o);
                o = X(e, x(i, r)), o.selector = e
            }
            return o
        }, S = t.select = function (e, t, n, r) {
            var i, o, a, s, u, l = "function" == typeof e && e, f = !r && N(e = l.selector || e);
            if (n = n || [], 1 === f.length) {
                if (o = f[0] = f[0].slice(0), o.length > 2 && "ID" === (a = o[0]).type && w.getById && 9 === t.nodeType && _ && T.relative[o[1].type]) {
                    if (t = (T.find.ID(a.matches[0].replace(be, we), t) || [])[0], !t)return n;
                    l && (t = t.parentNode), e = e.slice(o.shift().value.length)
                }
                for (i = pe.needsContext.test(e) ? 0 : o.length; i-- && (a = o[i], !T.relative[s = a.type]);)if ((u = T.find[s]) && (r = u(a.matches[0].replace(be, we), ve.test(o[0].type) && c(t.parentNode) || t))) {
                    if (o.splice(i, 1), e = r.length && d(o), !e)return Q.apply(n, r), n;
                    break
                }
            }
            return (l || k(e, f))(r, t, !_, n, !t || ve.test(e) && c(t.parentNode) || t), n
        }, w.sortStable = P.split("").sort(U).join("") === P, w.detectDuplicates = !!j, L(), w.sortDetached = i(function (e) {
            return 1 & e.compareDocumentPosition(H.createElement("div"))
        }), i(function (e) {
            return e.innerHTML = "<a href='#'></a>", "#" === e.firstChild.getAttribute("href")
        }) || o("type|href|height|width", function (e, t, n) {
            if (!n)return e.getAttribute(t, "type" === t.toLowerCase() ? 1 : 2)
        }), w.attributes && i(function (e) {
            return e.innerHTML = "<input/>", e.firstChild.setAttribute("value", ""), "" === e.firstChild.getAttribute("value")
        }) || o("value", function (e, t, n) {
            if (!n && "input" === e.nodeName.toLowerCase())return e.defaultValue
        }), i(function (e) {
            return null == e.getAttribute("disabled")
        }) || o(te, function (e, t, n) {
            var r;
            if (!n)return e[t] === !0 ? t.toLowerCase() : (r = e.getAttributeNode(t)) && r.specified ? r.value : null
        }), t
    }(e);
    pe.find = ve, pe.expr = ve.selectors, pe.expr[":"] = pe.expr.pseudos, pe.uniqueSort = pe.unique = ve.uniqueSort, pe.text = ve.getText, pe.isXMLDoc = ve.isXML, pe.contains = ve.contains;
    var xe = function (e, t, n) {
        for (var r = [], i = void 0 !== n; (e = e[t]) && 9 !== e.nodeType;)if (1 === e.nodeType) {
            if (i && pe(e).is(n))break;
            r.push(e)
        }
        return r
    }, be = function (e, t) {
        for (var n = []; e; e = e.nextSibling)1 === e.nodeType && e !== t && n.push(e);
        return n
    }, we = pe.expr.match.needsContext, Te = /^<([\w-]+)\s*\/?>(?:<\/\1>|)$/, Ce = /^.[^:#\[\.,]*$/;
    pe.filter = function (e, t, n) {
        var r = t[0];
        return n && (e = ":not(" + e + ")"), 1 === t.length && 1 === r.nodeType ? pe.find.matchesSelector(r, e) ? [r] : [] : pe.find.matches(e, pe.grep(t, function (e) {
            return 1 === e.nodeType
        }))
    }, pe.fn.extend({
        find: function (e) {
            var t, n = [], r = this, i = r.length;
            if ("string" != typeof e)return this.pushStack(pe(e).filter(function () {
                for (t = 0; t < i; t++)if (pe.contains(r[t], this))return !0
            }));
            for (t = 0; t < i; t++)pe.find(e, r[t], n);
            return n = this.pushStack(i > 1 ? pe.unique(n) : n), n.selector = this.selector ? this.selector + " " + e : e, n
        }, filter: function (e) {
            return this.pushStack(r(this, e || [], !1))
        }, not: function (e) {
            return this.pushStack(r(this, e || [], !0))
        }, is: function (e) {
            return !!r(this, "string" == typeof e && we.test(e) ? pe(e) : e || [], !1).length
        }
    });
    var Ee, Ne = /^(?:\s*(<[\w\W]+>)[^>]*|#([\w-]*))$/, ke = pe.fn.init = function (e, t, n) {
        var r, i;
        if (!e)return this;
        if (n = n || Ee, "string" == typeof e) {
            if (r = "<" === e.charAt(0) && ">" === e.charAt(e.length - 1) && e.length >= 3 ? [null, e, null] : Ne.exec(e), !r || !r[1] && t)return !t || t.jquery ? (t || n).find(e) : this.constructor(t).find(e);
            if (r[1]) {
                if (t = t instanceof pe ? t[0] : t, pe.merge(this, pe.parseHTML(r[1], t && t.nodeType ? t.ownerDocument || t : re, !0)), Te.test(r[1]) && pe.isPlainObject(t))for (r in t)pe.isFunction(this[r]) ? this[r](t[r]) : this.attr(r, t[r]);
                return this
            }
            if (i = re.getElementById(r[2]), i && i.parentNode) {
                if (i.id !== r[2])return Ee.find(e);
                this.length = 1, this[0] = i
            }
            return this.context = re, this.selector = e, this
        }
        return e.nodeType ? (this.context = this[0] = e, this.length = 1, this) : pe.isFunction(e) ? "undefined" != typeof n.ready ? n.ready(e) : e(pe) : (void 0 !== e.selector && (this.selector = e.selector, this.context = e.context), pe.makeArray(e, this))
    };
    ke.prototype = pe.fn, Ee = pe(re);
    var Se = /^(?:parents|prev(?:Until|All))/, Ae = {children: !0, contents: !0, next: !0, prev: !0};
    pe.fn.extend({
        has: function (e) {
            var t, n = pe(e, this), r = n.length;
            return this.filter(function () {
                for (t = 0; t < r; t++)if (pe.contains(this, n[t]))return !0
            })
        }, closest: function (e, t) {
            for (var n, r = 0, i = this.length, o = [], a = we.test(e) || "string" != typeof e ? pe(e, t || this.context) : 0; r < i; r++)for (n = this[r]; n && n !== t; n = n.parentNode)if (n.nodeType < 11 && (a ? a.index(n) > -1 : 1 === n.nodeType && pe.find.matchesSelector(n, e))) {
                o.push(n);
                break
            }
            return this.pushStack(o.length > 1 ? pe.uniqueSort(o) : o)
        }, index: function (e) {
            return e ? "string" == typeof e ? pe.inArray(this[0], pe(e)) : pe.inArray(e.jquery ? e[0] : e, this) : this[0] && this[0].parentNode ? this.first().prevAll().length : -1
        }, add: function (e, t) {
            return this.pushStack(pe.uniqueSort(pe.merge(this.get(), pe(e, t))))
        }, addBack: function (e) {
            return this.add(null == e ? this.prevObject : this.prevObject.filter(e))
        }
    }), pe.each({
        parent: function (e) {
            var t = e.parentNode;
            return t && 11 !== t.nodeType ? t : null
        }, parents: function (e) {
            return xe(e, "parentNode")
        }, parentsUntil: function (e, t, n) {
            return xe(e, "parentNode", n)
        }, next: function (e) {
            return i(e, "nextSibling")
        }, prev: function (e) {
            return i(e, "previousSibling")
        }, nextAll: function (e) {
            return xe(e, "nextSibling")
        }, prevAll: function (e) {
            return xe(e, "previousSibling")
        }, nextUntil: function (e, t, n) {
            return xe(e, "nextSibling", n)
        }, prevUntil: function (e, t, n) {
            return xe(e, "previousSibling", n)
        }, siblings: function (e) {
            return be((e.parentNode || {}).firstChild, e)
        }, children: function (e) {
            return be(e.firstChild)
        }, contents: function (e) {
            return pe.nodeName(e, "iframe") ? e.contentDocument || e.contentWindow.document : pe.merge([], e.childNodes)
        }
    }, function (e, t) {
        pe.fn[e] = function (n, r) {
            var i = pe.map(this, t, n);
            return "Until" !== e.slice(-5) && (r = n), r && "string" == typeof r && (i = pe.filter(r, i)), this.length > 1 && (Ae[e] || (i = pe.uniqueSort(i)), Se.test(e) && (i = i.reverse())), this.pushStack(i)
        }
    });
    var De = /\S+/g;
    pe.Callbacks = function (e) {
        e = "string" == typeof e ? o(e) : pe.extend({}, e);
        var t, n, r, i, a = [], s = [], u = -1, l = function () {
            for (i = e.once, r = t = !0; s.length; u = -1)for (n = s.shift(); ++u < a.length;)a[u].apply(n[0], n[1]) === !1 && e.stopOnFalse && (u = a.length, n = !1);
            e.memory || (n = !1), t = !1, i && (a = n ? [] : "")
        }, c = {
            add: function () {
                return a && (n && !t && (u = a.length - 1, s.push(n)), function r(t) {
                    pe.each(t, function (t, n) {
                        pe.isFunction(n) ? e.unique && c.has(n) || a.push(n) : n && n.length && "string" !== pe.type(n) && r(n)
                    })
                }(arguments), n && !t && l()), this
            }, remove: function () {
                return pe.each(arguments, function (e, t) {
                    for (var n; (n = pe.inArray(t, a, n)) > -1;)a.splice(n, 1), n <= u && u--
                }), this
            }, has: function (e) {
                return e ? pe.inArray(e, a) > -1 : a.length > 0
            }, empty: function () {
                return a && (a = []), this
            }, disable: function () {
                return i = s = [], a = n = "", this
            }, disabled: function () {
                return !a
            }, lock: function () {
                return i = !0, n || c.disable(), this
            }, locked: function () {
                return !!i
            }, fireWith: function (e, n) {
                return i || (n = n || [], n = [e, n.slice ? n.slice() : n], s.push(n), t || l()), this
            }, fire: function () {
                return c.fireWith(this, arguments), this
            }, fired: function () {
                return !!r
            }
        };
        return c
    }, pe.extend({
        Deferred: function (e) {
            var t = [["resolve", "done", pe.Callbacks("once memory"), "resolved"], ["reject", "fail", pe.Callbacks("once memory"), "rejected"], ["notify", "progress", pe.Callbacks("memory")]], n = "pending", r = {
                state: function () {
                    return n
                }, always: function () {
                    return i.done(arguments).fail(arguments), this
                }, then: function () {
                    var e = arguments;
                    return pe.Deferred(function (n) {
                        pe.each(t, function (t, o) {
                            var a = pe.isFunction(e[t]) && e[t];
                            i[o[1]](function () {
                                var e = a && a.apply(this, arguments);
                                e && pe.isFunction(e.promise) ? e.promise().progress(n.notify).done(n.resolve).fail(n.reject) : n[o[0] + "With"](this === r ? n.promise() : this, a ? [e] : arguments)
                            })
                        }), e = null
                    }).promise()
                }, promise: function (e) {
                    return null != e ? pe.extend(e, r) : r
                }
            }, i = {};
            return r.pipe = r.then, pe.each(t, function (e, o) {
                var a = o[2], s = o[3];
                r[o[1]] = a.add, s && a.add(function () {
                    n = s
                }, t[1 ^ e][2].disable, t[2][2].lock), i[o[0]] = function () {
                    return i[o[0] + "With"](this === i ? r : this, arguments), this
                }, i[o[0] + "With"] = a.fireWith
            }), r.promise(i), e && e.call(i, i), i
        }, when: function (e) {
            var t, n, r, i = 0, o = ie.call(arguments), a = o.length, s = 1 !== a || e && pe.isFunction(e.promise) ? a : 0, u = 1 === s ? e : pe.Deferred(), l = function (e, n, r) {
                return function (i) {
                    n[e] = this, r[e] = arguments.length > 1 ? ie.call(arguments) : i, r === t ? u.notifyWith(n, r) : --s || u.resolveWith(n, r)
                }
            };
            if (a > 1)for (t = new Array(a), n = new Array(a), r = new Array(a); i < a; i++)o[i] && pe.isFunction(o[i].promise) ? o[i].promise().progress(l(i, n, t)).done(l(i, r, o)).fail(u.reject) : --s;
            return s || u.resolveWith(r, o), u.promise()
        }
    });
    var je;
    pe.fn.ready = function (e) {
        return pe.ready.promise().done(e), this
    }, pe.extend({
        isReady: !1, readyWait: 1, holdReady: function (e) {
            e ? pe.readyWait++ : pe.ready(!0)
        }, ready: function (e) {
            (e === !0 ? --pe.readyWait : pe.isReady) || (pe.isReady = !0, e !== !0 && --pe.readyWait > 0 || (je.resolveWith(re, [pe]), pe.fn.triggerHandler && (pe(re).triggerHandler("ready"), pe(re).off("ready"))))
        }
    }), pe.ready.promise = function (t) {
        if (!je)if (je = pe.Deferred(), "complete" === re.readyState || "loading" !== re.readyState && !re.documentElement.doScroll)e.setTimeout(pe.ready); else if (re.addEventListener)re.addEventListener("DOMContentLoaded", s), e.addEventListener("load", s); else {
            re.attachEvent("onreadystatechange", s), e.attachEvent("onload", s);
            var n = !1;
            try {
                n = null == e.frameElement && re.documentElement
            } catch (r) {
            }
            n && n.doScroll && !function i() {
                if (!pe.isReady) {
                    try {
                        n.doScroll("left")
                    } catch (t) {
                        return e.setTimeout(i, 50)
                    }
                    a(), pe.ready()
                }
            }()
        }
        return je.promise(t)
    }, pe.ready.promise();
    var Le;
    for (Le in pe(fe))break;
    fe.ownFirst = "0" === Le, fe.inlineBlockNeedsLayout = !1, pe(function () {
        var e, t, n, r;
        n = re.getElementsByTagName("body")[0], n && n.style && (t = re.createElement("div"), r = re.createElement("div"), r.style.cssText = "position:absolute;border:0;width:0;height:0;top:0;left:-9999px", n.appendChild(r).appendChild(t), "undefined" != typeof t.style.zoom && (t.style.cssText = "display:inline;margin:0;border:0;padding:1px;width:1px;zoom:1", fe.inlineBlockNeedsLayout = e = 3 === t.offsetWidth, e && (n.style.zoom = 1)), n.removeChild(r))
    }), function () {
        var e = re.createElement("div");
        fe.deleteExpando = !0;
        try {
            delete e.test
        } catch (t) {
            fe.deleteExpando = !1
        }
        e = null
    }();
    var He = function (e) {
        var t = pe.noData[(e.nodeName + " ").toLowerCase()], n = +e.nodeType || 1;
        return (1 === n || 9 === n) && (!t || t !== !0 && e.getAttribute("classid") === t)
    }, qe = /^(?:\{[\w\W]*\}|\[[\w\W]*\])$/, _e = /([A-Z])/g;
    pe.extend({
        cache: {},
        noData: {"applet ": !0, "embed ": !0, "object ": "clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"},
        hasData: function (e) {
            return e = e.nodeType ? pe.cache[e[pe.expando]] : e[pe.expando], !!e && !l(e)
        },
        data: function (e, t, n) {
            return c(e, t, n)
        },
        removeData: function (e, t) {
            return f(e, t)
        },
        _data: function (e, t, n) {
            return c(e, t, n, !0)
        },
        _removeData: function (e, t) {
            return f(e, t, !0)
        }
    }), pe.fn.extend({
        data: function (e, t) {
            var n, r, i, o = this[0], a = o && o.attributes;
            if (void 0 === e) {
                if (this.length && (i = pe.data(o), 1 === o.nodeType && !pe._data(o, "parsedAttrs"))) {
                    for (n = a.length; n--;)a[n] && (r = a[n].name, 0 === r.indexOf("data-") && (r = pe.camelCase(r.slice(5)), u(o, r, i[r])));
                    pe._data(o, "parsedAttrs", !0)
                }
                return i
            }
            return "object" == typeof e ? this.each(function () {
                pe.data(this, e)
            }) : arguments.length > 1 ? this.each(function () {
                pe.data(this, e, t)
            }) : o ? u(o, e, pe.data(o, e)) : void 0
        }, removeData: function (e) {
            return this.each(function () {
                pe.removeData(this, e)
            })
        }
    }), pe.extend({
        queue: function (e, t, n) {
            var r;
            if (e)return t = (t || "fx") + "queue", r = pe._data(e, t), n && (!r || pe.isArray(n) ? r = pe._data(e, t, pe.makeArray(n)) : r.push(n)), r || []
        }, dequeue: function (e, t) {
            t = t || "fx";
            var n = pe.queue(e, t), r = n.length, i = n.shift(), o = pe._queueHooks(e, t), a = function () {
                pe.dequeue(e, t)
            };
            "inprogress" === i && (i = n.shift(), r--), i && ("fx" === t && n.unshift("inprogress"), delete o.stop, i.call(e, a, o)), !r && o && o.empty.fire()
        }, _queueHooks: function (e, t) {
            var n = t + "queueHooks";
            return pe._data(e, n) || pe._data(e, n, {
                    empty: pe.Callbacks("once memory").add(function () {
                        pe._removeData(e, t + "queue"), pe._removeData(e, n)
                    })
                })
        }
    }), pe.fn.extend({
        queue: function (e, t) {
            var n = 2;
            return "string" != typeof e && (t = e, e = "fx", n--), arguments.length < n ? pe.queue(this[0], e) : void 0 === t ? this : this.each(function () {
                var n = pe.queue(this, e, t);
                pe._queueHooks(this, e), "fx" === e && "inprogress" !== n[0] && pe.dequeue(this, e)
            })
        }, dequeue: function (e) {
            return this.each(function () {
                pe.dequeue(this, e)
            })
        }, clearQueue: function (e) {
            return this.queue(e || "fx", [])
        }, promise: function (e, t) {
            var n, r = 1, i = pe.Deferred(), o = this, a = this.length, s = function () {
                --r || i.resolveWith(o, [o])
            };
            for ("string" != typeof e && (t = e, e = void 0), e = e || "fx"; a--;)n = pe._data(o[a], e + "queueHooks"), n && n.empty && (r++, n.empty.add(s));
            return s(), i.promise(t)
        }
    }), function () {
        var e;
        fe.shrinkWrapBlocks = function () {
            if (null != e)return e;
            e = !1;
            var t, n, r;
            return n = re.getElementsByTagName("body")[0], n && n.style ? (t = re.createElement("div"), r = re.createElement("div"), r.style.cssText = "position:absolute;border:0;width:0;height:0;top:0;left:-9999px", n.appendChild(r).appendChild(t), "undefined" != typeof t.style.zoom && (t.style.cssText = "-webkit-box-sizing:content-box;-moz-box-sizing:content-box;box-sizing:content-box;display:block;margin:0;border:0;padding:1px;width:1px;zoom:1", t.appendChild(re.createElement("div")).style.width = "5px", e = 3 !== t.offsetWidth), n.removeChild(r), e) : void 0
        }
    }();
    var Fe = /[+-]?(?:\d*\.|)\d+(?:[eE][+-]?\d+|)/.source, Me = new RegExp("^(?:([+-])=|)(" + Fe + ")([a-z%]*)$", "i"), Oe = ["Top", "Right", "Bottom", "Left"], Re = function (e, t) {
        return e = t || e, "none" === pe.css(e, "display") || !pe.contains(e.ownerDocument, e)
    }, Pe = function (e, t, n, r, i, o, a) {
        var s = 0, u = e.length, l = null == n;
        if ("object" === pe.type(n)) {
            i = !0;
            for (s in n)Pe(e, t, s, n[s], !0, o, a)
        } else if (void 0 !== r && (i = !0, pe.isFunction(r) || (a = !0), l && (a ? (t.call(e, r), t = null) : (l = t, t = function (e, t, n) {
                return l.call(pe(e), n)
            })), t))for (; s < u; s++)t(e[s], n, a ? r : r.call(e[s], s, t(e[s], n)));
        return i ? e : l ? t.call(e) : u ? t(e[0], n) : o
    }, Be = /^(?:checkbox|radio)$/i, We = /<([\w:-]+)/, Ie = /^$|\/(?:java|ecma)script/i, $e = /^\s+/, ze = "abbr|article|aside|audio|bdi|canvas|data|datalist|details|dialog|figcaption|figure|footer|header|hgroup|main|mark|meter|nav|output|picture|progress|section|summary|template|time|video";
    !function () {
        var e = re.createElement("div"), t = re.createDocumentFragment(), n = re.createElement("input");
        e.innerHTML = "  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>", fe.leadingWhitespace = 3 === e.firstChild.nodeType, fe.tbody = !e.getElementsByTagName("tbody").length, fe.htmlSerialize = !!e.getElementsByTagName("link").length, fe.html5Clone = "<:nav></:nav>" !== re.createElement("nav").cloneNode(!0).outerHTML, n.type = "checkbox", n.checked = !0, t.appendChild(n), fe.appendChecked = n.checked, e.innerHTML = "<textarea>x</textarea>", fe.noCloneChecked = !!e.cloneNode(!0).lastChild.defaultValue, t.appendChild(e), n = re.createElement("input"), n.setAttribute("type", "radio"), n.setAttribute("checked", "checked"), n.setAttribute("name", "t"), e.appendChild(n), fe.checkClone = e.cloneNode(!0).cloneNode(!0).lastChild.checked, fe.noCloneEvent = !!e.addEventListener, e[pe.expando] = 1, fe.attributes = !e.getAttribute(pe.expando)
    }();
    var Xe = {
        option: [1, "<select multiple='multiple'>", "</select>"],
        legend: [1, "<fieldset>", "</fieldset>"],
        area: [1, "<map>", "</map>"],
        param: [1, "<object>", "</object>"],
        thead: [1, "<table>", "</table>"],
        tr: [2, "<table><tbody>", "</tbody></table>"],
        col: [2, "<table><tbody></tbody><colgroup>", "</colgroup></table>"],
        td: [3, "<table><tbody><tr>", "</tr></tbody></table>"],
        _default: fe.htmlSerialize ? [0, "", ""] : [1, "X<div>", "</div>"]
    };
    Xe.optgroup = Xe.option, Xe.tbody = Xe.tfoot = Xe.colgroup = Xe.caption = Xe.thead, Xe.th = Xe.td;
    var Ue = /<|&#?\w+;/, Ve = /<tbody/i;
    !function () {
        var t, n, r = re.createElement("div");
        for (t in{
            submit: !0,
            change: !0,
            focusin: !0
        })n = "on" + t, (fe[t] = n in e) || (r.setAttribute(n, "t"), fe[t] = r.attributes[n].expando === !1);
        r = null
    }();
    var Ye = /^(?:input|select|textarea)$/i, Je = /^key/, Ge = /^(?:mouse|pointer|contextmenu|drag|drop)|click/, Ke = /^(?:focusinfocus|focusoutblur)$/, Qe = /^([^.]*)(?:\.(.+)|)/;
    pe.event = {
        global: {},
        add: function (e, t, n, r, i) {
            var o, a, s, u, l, c, f, d, p, h, g, m = pe._data(e);
            if (m) {
                for (n.handler && (u = n, n = u.handler, i = u.selector), n.guid || (n.guid = pe.guid++), (a = m.events) || (a = m.events = {}), (c = m.handle) || (c = m.handle = function (e) {
                    return "undefined" == typeof pe || e && pe.event.triggered === e.type ? void 0 : pe.event.dispatch.apply(c.elem, arguments)
                }, c.elem = e), t = (t || "").match(De) || [""], s = t.length; s--;)o = Qe.exec(t[s]) || [], p = g = o[1], h = (o[2] || "").split(".").sort(), p && (l = pe.event.special[p] || {}, p = (i ? l.delegateType : l.bindType) || p, l = pe.event.special[p] || {}, f = pe.extend({
                    type: p,
                    origType: g,
                    data: r,
                    handler: n,
                    guid: n.guid,
                    selector: i,
                    needsContext: i && pe.expr.match.needsContext.test(i),
                    namespace: h.join(".")
                }, u), (d = a[p]) || (d = a[p] = [], d.delegateCount = 0, l.setup && l.setup.call(e, r, h, c) !== !1 || (e.addEventListener ? e.addEventListener(p, c, !1) : e.attachEvent && e.attachEvent("on" + p, c))), l.add && (l.add.call(e, f), f.handler.guid || (f.handler.guid = n.guid)), i ? d.splice(d.delegateCount++, 0, f) : d.push(f), pe.event.global[p] = !0);
                e = null
            }
        },
        remove: function (e, t, n, r, i) {
            var o, a, s, u, l, c, f, d, p, h, g, m = pe.hasData(e) && pe._data(e);
            if (m && (c = m.events)) {
                for (t = (t || "").match(De) || [""], l = t.length; l--;)if (s = Qe.exec(t[l]) || [], p = g = s[1], h = (s[2] || "").split(".").sort(), p) {
                    for (f = pe.event.special[p] || {}, p = (r ? f.delegateType : f.bindType) || p, d = c[p] || [], s = s[2] && new RegExp("(^|\\.)" + h.join("\\.(?:.*\\.|)") + "(\\.|$)"), u = o = d.length; o--;)a = d[o], !i && g !== a.origType || n && n.guid !== a.guid || s && !s.test(a.namespace) || r && r !== a.selector && ("**" !== r || !a.selector) || (d.splice(o, 1), a.selector && d.delegateCount--, f.remove && f.remove.call(e, a));
                    u && !d.length && (f.teardown && f.teardown.call(e, h, m.handle) !== !1 || pe.removeEvent(e, p, m.handle), delete c[p])
                } else for (p in c)pe.event.remove(e, p + t[l], n, r, !0);
                pe.isEmptyObject(c) && (delete m.handle, pe._removeData(e, "events"))
            }
        },
        trigger: function (t, n, r, i) {
            var o, a, s, u, l, c, f, d = [r || re], p = ce.call(t, "type") ? t.type : t, h = ce.call(t, "namespace") ? t.namespace.split(".") : [];
            if (s = c = r = r || re, 3 !== r.nodeType && 8 !== r.nodeType && !Ke.test(p + pe.event.triggered) && (p.indexOf(".") > -1 && (h = p.split("."), p = h.shift(), h.sort()), a = p.indexOf(":") < 0 && "on" + p, t = t[pe.expando] ? t : new pe.Event(p, "object" == typeof t && t), t.isTrigger = i ? 2 : 3, t.namespace = h.join("."), t.rnamespace = t.namespace ? new RegExp("(^|\\.)" + h.join("\\.(?:.*\\.|)") + "(\\.|$)") : null, t.result = void 0, t.target || (t.target = r), n = null == n ? [t] : pe.makeArray(n, [t]), l = pe.event.special[p] || {}, i || !l.trigger || l.trigger.apply(r, n) !== !1)) {
                if (!i && !l.noBubble && !pe.isWindow(r)) {
                    for (u = l.delegateType || p, Ke.test(u + p) || (s = s.parentNode); s; s = s.parentNode)d.push(s), c = s;
                    c === (r.ownerDocument || re) && d.push(c.defaultView || c.parentWindow || e)
                }
                for (f = 0; (s = d[f++]) && !t.isPropagationStopped();)t.type = f > 1 ? u : l.bindType || p, o = (pe._data(s, "events") || {})[t.type] && pe._data(s, "handle"), o && o.apply(s, n), o = a && s[a], o && o.apply && He(s) && (t.result = o.apply(s, n), t.result === !1 && t.preventDefault());
                if (t.type = p, !i && !t.isDefaultPrevented() && (!l._default || l._default.apply(d.pop(), n) === !1) && He(r) && a && r[p] && !pe.isWindow(r)) {
                    c = r[a], c && (r[a] = null), pe.event.triggered = p;
                    try {
                        r[p]()
                    } catch (g) {
                    }
                    pe.event.triggered = void 0, c && (r[a] = c)
                }
                return t.result
            }
        },
        dispatch: function (e) {
            e = pe.event.fix(e);
            var t, n, r, i, o, a = [], s = ie.call(arguments), u = (pe._data(this, "events") || {})[e.type] || [], l = pe.event.special[e.type] || {};
            if (s[0] = e, e.delegateTarget = this, !l.preDispatch || l.preDispatch.call(this, e) !== !1) {
                for (a = pe.event.handlers.call(this, e, u), t = 0; (i = a[t++]) && !e.isPropagationStopped();)for (e.currentTarget = i.elem, n = 0; (o = i.handlers[n++]) && !e.isImmediatePropagationStopped();)e.rnamespace && !e.rnamespace.test(o.namespace) || (e.handleObj = o, e.data = o.data, r = ((pe.event.special[o.origType] || {}).handle || o.handler).apply(i.elem, s), void 0 !== r && (e.result = r) === !1 && (e.preventDefault(), e.stopPropagation()));
                return l.postDispatch && l.postDispatch.call(this, e), e.result
            }
        },
        handlers: function (e, t) {
            var n, r, i, o, a = [], s = t.delegateCount, u = e.target;
            if (s && u.nodeType && ("click" !== e.type || isNaN(e.button) || e.button < 1))for (; u != this; u = u.parentNode || this)if (1 === u.nodeType && (u.disabled !== !0 || "click" !== e.type)) {
                for (r = [], n = 0; n < s; n++)o = t[n], i = o.selector + " ", void 0 === r[i] && (r[i] = o.needsContext ? pe(i, this).index(u) > -1 : pe.find(i, this, null, [u]).length), r[i] && r.push(o);
                r.length && a.push({elem: u, handlers: r})
            }
            return s < t.length && a.push({elem: this, handlers: t.slice(s)}), a
        },
        fix: function (e) {
            if (e[pe.expando])return e;
            var t, n, r, i = e.type, o = e, a = this.fixHooks[i];
            for (a || (this.fixHooks[i] = a = Ge.test(i) ? this.mouseHooks : Je.test(i) ? this.keyHooks : {}), r = a.props ? this.props.concat(a.props) : this.props, e = new pe.Event(o), t = r.length; t--;)n = r[t], e[n] = o[n];
            return e.target || (e.target = o.srcElement || re), 3 === e.target.nodeType && (e.target = e.target.parentNode), e.metaKey = !!e.metaKey, a.filter ? a.filter(e, o) : e
        },
        props: "altKey bubbles cancelable ctrlKey currentTarget detail eventPhase metaKey relatedTarget shiftKey target timeStamp view which".split(" "),
        fixHooks: {},
        keyHooks: {
            props: "char charCode key keyCode".split(" "), filter: function (e, t) {
                return null == e.which && (e.which = null != t.charCode ? t.charCode : t.keyCode), e
            }
        },
        mouseHooks: {
            props: "button buttons clientX clientY fromElement offsetX offsetY pageX pageY screenX screenY toElement".split(" "),
            filter: function (e, t) {
                var n, r, i, o = t.button, a = t.fromElement;
                return null == e.pageX && null != t.clientX && (r = e.target.ownerDocument || re, i = r.documentElement, n = r.body, e.pageX = t.clientX + (i && i.scrollLeft || n && n.scrollLeft || 0) - (i && i.clientLeft || n && n.clientLeft || 0), e.pageY = t.clientY + (i && i.scrollTop || n && n.scrollTop || 0) - (i && i.clientTop || n && n.clientTop || 0)), !e.relatedTarget && a && (e.relatedTarget = a === e.target ? t.toElement : a), e.which || void 0 === o || (e.which = 1 & o ? 1 : 2 & o ? 3 : 4 & o ? 2 : 0), e
            }
        },
        special: {
            load: {noBubble: !0}, focus: {
                trigger: function () {
                    if (this !== b() && this.focus)try {
                        return this.focus(), !1
                    } catch (e) {
                    }
                }, delegateType: "focusin"
            }, blur: {
                trigger: function () {
                    if (this === b() && this.blur)return this.blur(), !1
                }, delegateType: "focusout"
            }, click: {
                trigger: function () {
                    if (pe.nodeName(this, "input") && "checkbox" === this.type && this.click)return this.click(), !1
                }, _default: function (e) {
                    return pe.nodeName(e.target, "a")
                }
            }, beforeunload: {
                postDispatch: function (e) {
                    void 0 !== e.result && e.originalEvent && (e.originalEvent.returnValue = e.result)
                }
            }
        },
        simulate: function (e, t, n) {
            var r = pe.extend(new pe.Event, n, {type: e, isSimulated: !0});
            pe.event.trigger(r, null, t), r.isDefaultPrevented() && n.preventDefault()
        }
    }, pe.removeEvent = re.removeEventListener ? function (e, t, n) {
        e.removeEventListener && e.removeEventListener(t, n)
    } : function (e, t, n) {
        var r = "on" + t;
        e.detachEvent && ("undefined" == typeof e[r] && (e[r] = null), e.detachEvent(r, n))
    }, pe.Event = function (e, t) {
        return this instanceof pe.Event ? (e && e.type ? (this.originalEvent = e, this.type = e.type, this.isDefaultPrevented = e.defaultPrevented || void 0 === e.defaultPrevented && e.returnValue === !1 ? v : x) : this.type = e, t && pe.extend(this, t), this.timeStamp = e && e.timeStamp || pe.now(), void(this[pe.expando] = !0)) : new pe.Event(e, t)
    }, pe.Event.prototype = {
        constructor: pe.Event,
        isDefaultPrevented: x,
        isPropagationStopped: x,
        isImmediatePropagationStopped: x,
        preventDefault: function () {
            var e = this.originalEvent;
            this.isDefaultPrevented = v, e && (e.preventDefault ? e.preventDefault() : e.returnValue = !1)
        },
        stopPropagation: function () {
            var e = this.originalEvent;
            this.isPropagationStopped = v, e && !this.isSimulated && (e.stopPropagation && e.stopPropagation(), e.cancelBubble = !0)
        },
        stopImmediatePropagation: function () {
            var e = this.originalEvent;
            this.isImmediatePropagationStopped = v, e && e.stopImmediatePropagation && e.stopImmediatePropagation(), this.stopPropagation()
        }
    }, pe.each({
        mouseenter: "mouseover",
        mouseleave: "mouseout",
        pointerenter: "pointerover",
        pointerleave: "pointerout"
    }, function (e, t) {
        pe.event.special[e] = {
            delegateType: t, bindType: t, handle: function (e) {
                var n, r = this, i = e.relatedTarget, o = e.handleObj;
                return i && (i === r || pe.contains(r, i)) || (e.type = o.origType, n = o.handler.apply(this, arguments), e.type = t), n
            }
        }
    }), fe.submit || (pe.event.special.submit = {
        setup: function () {
            return !pe.nodeName(this, "form") && void pe.event.add(this, "click._submit keypress._submit", function (e) {
                    var t = e.target, n = pe.nodeName(t, "input") || pe.nodeName(t, "button") ? pe.prop(t, "form") : void 0;
                    n && !pe._data(n, "submit") && (pe.event.add(n, "submit._submit", function (e) {
                        e._submitBubble = !0
                    }), pe._data(n, "submit", !0))
                })
        }, postDispatch: function (e) {
            e._submitBubble && (delete e._submitBubble, this.parentNode && !e.isTrigger && pe.event.simulate("submit", this.parentNode, e))
        }, teardown: function () {
            return !pe.nodeName(this, "form") && void pe.event.remove(this, "._submit")
        }
    }), fe.change || (pe.event.special.change = {
        setup: function () {
            return Ye.test(this.nodeName) ? ("checkbox" !== this.type && "radio" !== this.type || (pe.event.add(this, "propertychange._change", function (e) {
                "checked" === e.originalEvent.propertyName && (this._justChanged = !0)
            }), pe.event.add(this, "click._change", function (e) {
                this._justChanged && !e.isTrigger && (this._justChanged = !1), pe.event.simulate("change", this, e)
            })), !1) : void pe.event.add(this, "beforeactivate._change", function (e) {
                var t = e.target;
                Ye.test(t.nodeName) && !pe._data(t, "change") && (pe.event.add(t, "change._change", function (e) {
                    !this.parentNode || e.isSimulated || e.isTrigger || pe.event.simulate("change", this.parentNode, e)
                }), pe._data(t, "change", !0))
            })
        }, handle: function (e) {
            var t = e.target;
            if (this !== t || e.isSimulated || e.isTrigger || "radio" !== t.type && "checkbox" !== t.type)return e.handleObj.handler.apply(this, arguments)
        }, teardown: function () {
            return pe.event.remove(this, "._change"), !Ye.test(this.nodeName)
        }
    }), fe.focusin || pe.each({focus: "focusin", blur: "focusout"}, function (e, t) {
        var n = function (e) {
            pe.event.simulate(t, e.target, pe.event.fix(e))
        };
        pe.event.special[t] = {
            setup: function () {
                var r = this.ownerDocument || this, i = pe._data(r, t);
                i || r.addEventListener(e, n, !0), pe._data(r, t, (i || 0) + 1)
            }, teardown: function () {
                var r = this.ownerDocument || this, i = pe._data(r, t) - 1;
                i ? pe._data(r, t, i) : (r.removeEventListener(e, n, !0), pe._removeData(r, t))
            }
        }
    }), pe.fn.extend({
        on: function (e, t, n, r) {
            return w(this, e, t, n, r)
        }, one: function (e, t, n, r) {
            return w(this, e, t, n, r, 1)
        }, off: function (e, t, n) {
            var r, i;
            if (e && e.preventDefault && e.handleObj)return r = e.handleObj, pe(e.delegateTarget).off(r.namespace ? r.origType + "." + r.namespace : r.origType, r.selector, r.handler), this;
            if ("object" == typeof e) {
                for (i in e)this.off(i, t, e[i]);
                return this
            }
            return t !== !1 && "function" != typeof t || (n = t, t = void 0), n === !1 && (n = x), this.each(function () {
                pe.event.remove(this, e, n, t)
            })
        }, trigger: function (e, t) {
            return this.each(function () {
                pe.event.trigger(e, t, this)
            })
        }, triggerHandler: function (e, t) {
            var n = this[0];
            if (n)return pe.event.trigger(e, t, n, !0)
        }
    });
    var Ze = / jQuery\d+="(?:null|\d+)"/g, et = new RegExp("<(?:" + ze + ")[\\s/>]", "i"), tt = /<(?!area|br|col|embed|hr|img|input|link|meta|param)(([\w:-]+)[^>]*)\/>/gi, nt = /<script|<style|<link/i, rt = /checked\s*(?:[^=]|=\s*.checked.)/i, it = /^true\/(.*)/, ot = /^\s*<!(?:\[CDATA\[|--)|(?:\]\]|--)>\s*$/g, at = p(re), st = at.appendChild(re.createElement("div"));
    pe.extend({
        htmlPrefilter: function (e) {
            return e.replace(tt, "<$1></$2>")
        }, clone: function (e, t, n) {
            var r, i, o, a, s, u = pe.contains(e.ownerDocument, e);
            if (fe.html5Clone || pe.isXMLDoc(e) || !et.test("<" + e.nodeName + ">") ? o = e.cloneNode(!0) : (st.innerHTML = e.outerHTML, st.removeChild(o = st.firstChild)), !(fe.noCloneEvent && fe.noCloneChecked || 1 !== e.nodeType && 11 !== e.nodeType || pe.isXMLDoc(e)))for (r = h(o), s = h(e), a = 0; null != (i = s[a]); ++a)r[a] && k(i, r[a]);
            if (t)if (n)for (s = s || h(e), r = r || h(o), a = 0; null != (i = s[a]); a++)N(i, r[a]); else N(e, o);
            return r = h(o, "script"), r.length > 0 && g(r, !u && h(e, "script")), r = s = i = null, o
        }, cleanData: function (e, t) {
            for (var n, r, i, o, a = 0, s = pe.expando, u = pe.cache, l = fe.attributes, c = pe.event.special; null != (n = e[a]); a++)if ((t || He(n)) && (i = n[s], o = i && u[i])) {
                if (o.events)for (r in o.events)c[r] ? pe.event.remove(n, r) : pe.removeEvent(n, r, o.handle);
                u[i] && (delete u[i], l || "undefined" == typeof n.removeAttribute ? n[s] = void 0 : n.removeAttribute(s), ne.push(i))
            }
        }
    }), pe.fn.extend({
        domManip: S, detach: function (e) {
            return A(this, e, !0)
        }, remove: function (e) {
            return A(this, e)
        }, text: function (e) {
            return Pe(this, function (e) {
                return void 0 === e ? pe.text(this) : this.empty().append((this[0] && this[0].ownerDocument || re).createTextNode(e))
            }, null, e, arguments.length)
        }, append: function () {
            return S(this, arguments, function (e) {
                if (1 === this.nodeType || 11 === this.nodeType || 9 === this.nodeType) {
                    var t = T(this, e);
                    t.appendChild(e)
                }
            })
        }, prepend: function () {
            return S(this, arguments, function (e) {
                if (1 === this.nodeType || 11 === this.nodeType || 9 === this.nodeType) {
                    var t = T(this, e);
                    t.insertBefore(e, t.firstChild)
                }
            })
        }, before: function () {
            return S(this, arguments, function (e) {
                this.parentNode && this.parentNode.insertBefore(e, this)
            })
        }, after: function () {
            return S(this, arguments, function (e) {
                this.parentNode && this.parentNode.insertBefore(e, this.nextSibling)
            })
        }, empty: function () {
            for (var e, t = 0; null != (e = this[t]); t++) {
                for (1 === e.nodeType && pe.cleanData(h(e, !1)); e.firstChild;)e.removeChild(e.firstChild);
                e.options && pe.nodeName(e, "select") && (e.options.length = 0)
            }
            return this
        }, clone: function (e, t) {
            return e = null != e && e, t = null == t ? e : t, this.map(function () {
                return pe.clone(this, e, t)
            })
        }, html: function (e) {
            return Pe(this, function (e) {
                var t = this[0] || {}, n = 0, r = this.length;
                if (void 0 === e)return 1 === t.nodeType ? t.innerHTML.replace(Ze, "") : void 0;
                if ("string" == typeof e && !nt.test(e) && (fe.htmlSerialize || !et.test(e)) && (fe.leadingWhitespace || !$e.test(e)) && !Xe[(We.exec(e) || ["", ""])[1].toLowerCase()]) {
                    e = pe.htmlPrefilter(e);
                    try {
                        for (; n < r; n++)t = this[n] || {}, 1 === t.nodeType && (pe.cleanData(h(t, !1)), t.innerHTML = e);
                        t = 0
                    } catch (i) {
                    }
                }
                t && this.empty().append(e)
            }, null, e, arguments.length)
        }, replaceWith: function () {
            var e = [];
            return S(this, arguments, function (t) {
                var n = this.parentNode;
                pe.inArray(this, e) < 0 && (pe.cleanData(h(this)),
                n && n.replaceChild(t, this))
            }, e)
        }
    }), pe.each({
        appendTo: "append",
        prependTo: "prepend",
        insertBefore: "before",
        insertAfter: "after",
        replaceAll: "replaceWith"
    }, function (e, t) {
        pe.fn[e] = function (e) {
            for (var n, r = 0, i = [], o = pe(e), a = o.length - 1; r <= a; r++)n = r === a ? this : this.clone(!0), pe(o[r])[t](n), ae.apply(i, n.get());
            return this.pushStack(i)
        }
    });
    var ut, lt = {
        HTML: "block",
        BODY: "block"
    }, ct = /^margin/, ft = new RegExp("^(" + Fe + ")(?!px)[a-z%]+$", "i"), dt = function (e, t, n, r) {
        var i, o, a = {};
        for (o in t)a[o] = e.style[o], e.style[o] = t[o];
        i = n.apply(e, r || []);
        for (o in t)e.style[o] = a[o];
        return i
    }, pt = re.documentElement;
    !function () {
        function t() {
            var t, c, f = re.documentElement;
            f.appendChild(u), l.style.cssText = "-webkit-box-sizing:border-box;box-sizing:border-box;position:relative;display:block;margin:auto;border:1px;padding:1px;top:1%;width:50%", n = i = s = !1, r = a = !0, e.getComputedStyle && (c = e.getComputedStyle(l), n = "1%" !== (c || {}).top, s = "2px" === (c || {}).marginLeft, i = "4px" === (c || {width: "4px"}).width, l.style.marginRight = "50%", r = "4px" === (c || {marginRight: "4px"}).marginRight, t = l.appendChild(re.createElement("div")), t.style.cssText = l.style.cssText = "-webkit-box-sizing:content-box;-moz-box-sizing:content-box;box-sizing:content-box;display:block;margin:0;border:0;padding:0", t.style.marginRight = t.style.width = "0", l.style.width = "1px", a = !parseFloat((e.getComputedStyle(t) || {}).marginRight), l.removeChild(t)), l.style.display = "none", o = 0 === l.getClientRects().length, o && (l.style.display = "", l.innerHTML = "<table><tr><td></td><td>t</td></tr></table>", t = l.getElementsByTagName("td"), t[0].style.cssText = "margin:0;border:0;padding:0;display:none", o = 0 === t[0].offsetHeight, o && (t[0].style.display = "", t[1].style.display = "none", o = 0 === t[0].offsetHeight)), f.removeChild(u)
        }

        var n, r, i, o, a, s, u = re.createElement("div"), l = re.createElement("div");
        l.style && (l.style.cssText = "float:left;opacity:.5", fe.opacity = "0.5" === l.style.opacity, fe.cssFloat = !!l.style.cssFloat, l.style.backgroundClip = "content-box", l.cloneNode(!0).style.backgroundClip = "", fe.clearCloneStyle = "content-box" === l.style.backgroundClip, u = re.createElement("div"), u.style.cssText = "border:0;width:8px;height:0;top:0;left:-9999px;padding:0;margin-top:1px;position:absolute", l.innerHTML = "", u.appendChild(l), fe.boxSizing = "" === l.style.boxSizing || "" === l.style.MozBoxSizing || "" === l.style.WebkitBoxSizing, pe.extend(fe, {
            reliableHiddenOffsets: function () {
                return null == n && t(), o
            }, boxSizingReliable: function () {
                return null == n && t(), i
            }, pixelMarginRight: function () {
                return null == n && t(), r
            }, pixelPosition: function () {
                return null == n && t(), n
            }, reliableMarginRight: function () {
                return null == n && t(), a
            }, reliableMarginLeft: function () {
                return null == n && t(), s
            }
        }))
    }();
    var ht, gt, mt = /^(top|right|bottom|left)$/;
    e.getComputedStyle ? (ht = function (t) {
        var n = t.ownerDocument.defaultView;
        return n && n.opener || (n = e), n.getComputedStyle(t)
    }, gt = function (e, t, n) {
        var r, i, o, a, s = e.style;
        return n = n || ht(e), a = n ? n.getPropertyValue(t) || n[t] : void 0, "" !== a && void 0 !== a || pe.contains(e.ownerDocument, e) || (a = pe.style(e, t)), n && !fe.pixelMarginRight() && ft.test(a) && ct.test(t) && (r = s.width, i = s.minWidth, o = s.maxWidth, s.minWidth = s.maxWidth = s.width = a, a = n.width, s.width = r, s.minWidth = i, s.maxWidth = o), void 0 === a ? a : a + ""
    }) : pt.currentStyle && (ht = function (e) {
        return e.currentStyle
    }, gt = function (e, t, n) {
        var r, i, o, a, s = e.style;
        return n = n || ht(e), a = n ? n[t] : void 0, null == a && s && s[t] && (a = s[t]), ft.test(a) && !mt.test(t) && (r = s.left, i = e.runtimeStyle, o = i && i.left, o && (i.left = e.currentStyle.left), s.left = "fontSize" === t ? "1em" : a, a = s.pixelLeft + "px", s.left = r, o && (i.left = o)), void 0 === a ? a : a + "" || "auto"
    });
    var yt = /alpha\([^)]*\)/i, vt = /opacity\s*=\s*([^)]*)/i, xt = /^(none|table(?!-c[ea]).+)/, bt = new RegExp("^(" + Fe + ")(.*)$", "i"), wt = {
        position: "absolute",
        visibility: "hidden",
        display: "block"
    }, Tt = {
        letterSpacing: "0",
        fontWeight: "400"
    }, Ct = ["Webkit", "O", "Moz", "ms"], Et = re.createElement("div").style;
    pe.extend({
        cssHooks: {
            opacity: {
                get: function (e, t) {
                    if (t) {
                        var n = gt(e, "opacity");
                        return "" === n ? "1" : n
                    }
                }
            }
        },
        cssNumber: {
            animationIterationCount: !0,
            columnCount: !0,
            fillOpacity: !0,
            flexGrow: !0,
            flexShrink: !0,
            fontWeight: !0,
            lineHeight: !0,
            opacity: !0,
            order: !0,
            orphans: !0,
            widows: !0,
            zIndex: !0,
            zoom: !0
        },
        cssProps: {"float": fe.cssFloat ? "cssFloat" : "styleFloat"},
        style: function (e, t, n, r) {
            if (e && 3 !== e.nodeType && 8 !== e.nodeType && e.style) {
                var i, o, a, s = pe.camelCase(t), u = e.style;
                if (t = pe.cssProps[s] || (pe.cssProps[s] = H(s) || s), a = pe.cssHooks[t] || pe.cssHooks[s], void 0 === n)return a && "get" in a && void 0 !== (i = a.get(e, !1, r)) ? i : u[t];
                if (o = typeof n, "string" === o && (i = Me.exec(n)) && i[1] && (n = d(e, t, i), o = "number"), null != n && n === n && ("number" === o && (n += i && i[3] || (pe.cssNumber[s] ? "" : "px")), fe.clearCloneStyle || "" !== n || 0 !== t.indexOf("background") || (u[t] = "inherit"), !(a && "set" in a && void 0 === (n = a.set(e, n, r)))))try {
                    u[t] = n
                } catch (l) {
                }
            }
        },
        css: function (e, t, n, r) {
            var i, o, a, s = pe.camelCase(t);
            return t = pe.cssProps[s] || (pe.cssProps[s] = H(s) || s), a = pe.cssHooks[t] || pe.cssHooks[s], a && "get" in a && (o = a.get(e, !0, n)), void 0 === o && (o = gt(e, t, r)), "normal" === o && t in Tt && (o = Tt[t]), "" === n || n ? (i = parseFloat(o), n === !0 || isFinite(i) ? i || 0 : o) : o
        }
    }), pe.each(["height", "width"], function (e, t) {
        pe.cssHooks[t] = {
            get: function (e, n, r) {
                if (n)return xt.test(pe.css(e, "display")) && 0 === e.offsetWidth ? dt(e, wt, function () {
                    return M(e, t, r)
                }) : M(e, t, r)
            }, set: function (e, n, r) {
                var i = r && ht(e);
                return _(e, n, r ? F(e, t, r, fe.boxSizing && "border-box" === pe.css(e, "boxSizing", !1, i), i) : 0)
            }
        }
    }), fe.opacity || (pe.cssHooks.opacity = {
        get: function (e, t) {
            return vt.test((t && e.currentStyle ? e.currentStyle.filter : e.style.filter) || "") ? .01 * parseFloat(RegExp.$1) + "" : t ? "1" : ""
        }, set: function (e, t) {
            var n = e.style, r = e.currentStyle, i = pe.isNumeric(t) ? "alpha(opacity=" + 100 * t + ")" : "", o = r && r.filter || n.filter || "";
            n.zoom = 1, (t >= 1 || "" === t) && "" === pe.trim(o.replace(yt, "")) && n.removeAttribute && (n.removeAttribute("filter"), "" === t || r && !r.filter) || (n.filter = yt.test(o) ? o.replace(yt, i) : o + " " + i)
        }
    }), pe.cssHooks.marginRight = L(fe.reliableMarginRight, function (e, t) {
        if (t)return dt(e, {display: "inline-block"}, gt, [e, "marginRight"])
    }), pe.cssHooks.marginLeft = L(fe.reliableMarginLeft, function (e, t) {
        if (t)return (parseFloat(gt(e, "marginLeft")) || (pe.contains(e.ownerDocument, e) ? e.getBoundingClientRect().left - dt(e, {marginLeft: 0}, function () {
                return e.getBoundingClientRect().left
            }) : 0)) + "px"
    }), pe.each({margin: "", padding: "", border: "Width"}, function (e, t) {
        pe.cssHooks[e + t] = {
            expand: function (n) {
                for (var r = 0, i = {}, o = "string" == typeof n ? n.split(" ") : [n]; r < 4; r++)i[e + Oe[r] + t] = o[r] || o[r - 2] || o[0];
                return i
            }
        }, ct.test(e) || (pe.cssHooks[e + t].set = _)
    }), pe.fn.extend({
        css: function (e, t) {
            return Pe(this, function (e, t, n) {
                var r, i, o = {}, a = 0;
                if (pe.isArray(t)) {
                    for (r = ht(e), i = t.length; a < i; a++)o[t[a]] = pe.css(e, t[a], !1, r);
                    return o
                }
                return void 0 !== n ? pe.style(e, t, n) : pe.css(e, t)
            }, e, t, arguments.length > 1)
        }, show: function () {
            return q(this, !0)
        }, hide: function () {
            return q(this)
        }, toggle: function (e) {
            return "boolean" == typeof e ? e ? this.show() : this.hide() : this.each(function () {
                Re(this) ? pe(this).show() : pe(this).hide()
            })
        }
    }), pe.Tween = O, O.prototype = {
        constructor: O, init: function (e, t, n, r, i, o) {
            this.elem = e, this.prop = n, this.easing = i || pe.easing._default, this.options = t, this.start = this.now = this.cur(), this.end = r, this.unit = o || (pe.cssNumber[n] ? "" : "px")
        }, cur: function () {
            var e = O.propHooks[this.prop];
            return e && e.get ? e.get(this) : O.propHooks._default.get(this)
        }, run: function (e) {
            var t, n = O.propHooks[this.prop];
            return this.options.duration ? this.pos = t = pe.easing[this.easing](e, this.options.duration * e, 0, 1, this.options.duration) : this.pos = t = e, this.now = (this.end - this.start) * t + this.start, this.options.step && this.options.step.call(this.elem, this.now, this), n && n.set ? n.set(this) : O.propHooks._default.set(this), this
        }
    }, O.prototype.init.prototype = O.prototype, O.propHooks = {
        _default: {
            get: function (e) {
                var t;
                return 1 !== e.elem.nodeType || null != e.elem[e.prop] && null == e.elem.style[e.prop] ? e.elem[e.prop] : (t = pe.css(e.elem, e.prop, ""), t && "auto" !== t ? t : 0)
            }, set: function (e) {
                pe.fx.step[e.prop] ? pe.fx.step[e.prop](e) : 1 !== e.elem.nodeType || null == e.elem.style[pe.cssProps[e.prop]] && !pe.cssHooks[e.prop] ? e.elem[e.prop] = e.now : pe.style(e.elem, e.prop, e.now + e.unit)
            }
        }
    }, O.propHooks.scrollTop = O.propHooks.scrollLeft = {
        set: function (e) {
            e.elem.nodeType && e.elem.parentNode && (e.elem[e.prop] = e.now)
        }
    }, pe.easing = {
        linear: function (e) {
            return e
        }, swing: function (e) {
            return .5 - Math.cos(e * Math.PI) / 2
        }, _default: "swing"
    }, pe.fx = O.prototype.init, pe.fx.step = {};
    var Nt, kt, St = /^(?:toggle|show|hide)$/, At = /queueHooks$/;
    pe.Animation = pe.extend($, {
        tweeners: {
            "*": [function (e, t) {
                var n = this.createTween(e, t);
                return d(n.elem, e, Me.exec(t), n), n
            }]
        }, tweener: function (e, t) {
            pe.isFunction(e) ? (t = e, e = ["*"]) : e = e.match(De);
            for (var n, r = 0, i = e.length; r < i; r++)n = e[r], $.tweeners[n] = $.tweeners[n] || [], $.tweeners[n].unshift(t)
        }, prefilters: [W], prefilter: function (e, t) {
            t ? $.prefilters.unshift(e) : $.prefilters.push(e)
        }
    }), pe.speed = function (e, t, n) {
        var r = e && "object" == typeof e ? pe.extend({}, e) : {
            complete: n || !n && t || pe.isFunction(e) && e,
            duration: e,
            easing: n && t || t && !pe.isFunction(t) && t
        };
        return r.duration = pe.fx.off ? 0 : "number" == typeof r.duration ? r.duration : r.duration in pe.fx.speeds ? pe.fx.speeds[r.duration] : pe.fx.speeds._default, null != r.queue && r.queue !== !0 || (r.queue = "fx"), r.old = r.complete, r.complete = function () {
            pe.isFunction(r.old) && r.old.call(this), r.queue && pe.dequeue(this, r.queue)
        }, r
    }, pe.fn.extend({
        fadeTo: function (e, t, n, r) {
            return this.filter(Re).css("opacity", 0).show().end().animate({opacity: t}, e, n, r)
        }, animate: function (e, t, n, r) {
            var i = pe.isEmptyObject(e), o = pe.speed(t, n, r), a = function () {
                var t = $(this, pe.extend({}, e), o);
                (i || pe._data(this, "finish")) && t.stop(!0)
            };
            return a.finish = a, i || o.queue === !1 ? this.each(a) : this.queue(o.queue, a)
        }, stop: function (e, t, n) {
            var r = function (e) {
                var t = e.stop;
                delete e.stop, t(n)
            };
            return "string" != typeof e && (n = t, t = e, e = void 0), t && e !== !1 && this.queue(e || "fx", []), this.each(function () {
                var t = !0, i = null != e && e + "queueHooks", o = pe.timers, a = pe._data(this);
                if (i)a[i] && a[i].stop && r(a[i]); else for (i in a)a[i] && a[i].stop && At.test(i) && r(a[i]);
                for (i = o.length; i--;)o[i].elem !== this || null != e && o[i].queue !== e || (o[i].anim.stop(n), t = !1, o.splice(i, 1));
                !t && n || pe.dequeue(this, e)
            })
        }, finish: function (e) {
            return e !== !1 && (e = e || "fx"), this.each(function () {
                var t, n = pe._data(this), r = n[e + "queue"], i = n[e + "queueHooks"], o = pe.timers, a = r ? r.length : 0;
                for (n.finish = !0, pe.queue(this, e, []), i && i.stop && i.stop.call(this, !0), t = o.length; t--;)o[t].elem === this && o[t].queue === e && (o[t].anim.stop(!0), o.splice(t, 1));
                for (t = 0; t < a; t++)r[t] && r[t].finish && r[t].finish.call(this);
                delete n.finish
            })
        }
    }), pe.each(["toggle", "show", "hide"], function (e, t) {
        var n = pe.fn[t];
        pe.fn[t] = function (e, r, i) {
            return null == e || "boolean" == typeof e ? n.apply(this, arguments) : this.animate(P(t, !0), e, r, i)
        }
    }), pe.each({
        slideDown: P("show"),
        slideUp: P("hide"),
        slideToggle: P("toggle"),
        fadeIn: {opacity: "show"},
        fadeOut: {opacity: "hide"},
        fadeToggle: {opacity: "toggle"}
    }, function (e, t) {
        pe.fn[e] = function (e, n, r) {
            return this.animate(t, e, n, r)
        }
    }), pe.timers = [], pe.fx.tick = function () {
        var e, t = pe.timers, n = 0;
        for (Nt = pe.now(); n < t.length; n++)e = t[n], e() || t[n] !== e || t.splice(n--, 1);
        t.length || pe.fx.stop(), Nt = void 0
    }, pe.fx.timer = function (e) {
        pe.timers.push(e), e() ? pe.fx.start() : pe.timers.pop()
    }, pe.fx.interval = 13, pe.fx.start = function () {
        kt || (kt = e.setInterval(pe.fx.tick, pe.fx.interval))
    }, pe.fx.stop = function () {
        e.clearInterval(kt), kt = null
    }, pe.fx.speeds = {slow: 600, fast: 200, _default: 400}, pe.fn.delay = function (t, n) {
        return t = pe.fx ? pe.fx.speeds[t] || t : t, n = n || "fx", this.queue(n, function (n, r) {
            var i = e.setTimeout(n, t);
            r.stop = function () {
                e.clearTimeout(i)
            }
        })
    }, function () {
        var e, t = re.createElement("input"), n = re.createElement("div"), r = re.createElement("select"), i = r.appendChild(re.createElement("option"));
        n = re.createElement("div"), n.setAttribute("className", "t"), n.innerHTML = "  <link/><table></table><a href='/a'>a</a><input type='checkbox'/>", e = n.getElementsByTagName("a")[0], t.setAttribute("type", "checkbox"), n.appendChild(t), e = n.getElementsByTagName("a")[0], e.style.cssText = "top:1px", fe.getSetAttribute = "t" !== n.className, fe.style = /top/.test(e.getAttribute("style")), fe.hrefNormalized = "/a" === e.getAttribute("href"), fe.checkOn = !!t.value, fe.optSelected = i.selected, fe.enctype = !!re.createElement("form").enctype, r.disabled = !0, fe.optDisabled = !i.disabled, t = re.createElement("input"), t.setAttribute("value", ""), fe.input = "" === t.getAttribute("value"), t.value = "t", t.setAttribute("type", "radio"), fe.radioValue = "t" === t.value
    }();
    var Dt = /\r/g, jt = /[\x20\t\r\n\f]+/g;
    pe.fn.extend({
        val: function (e) {
            var t, n, r, i = this[0];
            {
                if (arguments.length)return r = pe.isFunction(e), this.each(function (n) {
                    var i;
                    1 === this.nodeType && (i = r ? e.call(this, n, pe(this).val()) : e, null == i ? i = "" : "number" == typeof i ? i += "" : pe.isArray(i) && (i = pe.map(i, function (e) {
                        return null == e ? "" : e + ""
                    })), t = pe.valHooks[this.type] || pe.valHooks[this.nodeName.toLowerCase()], t && "set" in t && void 0 !== t.set(this, i, "value") || (this.value = i))
                });
                if (i)return t = pe.valHooks[i.type] || pe.valHooks[i.nodeName.toLowerCase()], t && "get" in t && void 0 !== (n = t.get(i, "value")) ? n : (n = i.value, "string" == typeof n ? n.replace(Dt, "") : null == n ? "" : n)
            }
        }
    }), pe.extend({
        valHooks: {
            option: {
                get: function (e) {
                    var t = pe.find.attr(e, "value");
                    return null != t ? t : pe.trim(pe.text(e)).replace(jt, " ")
                }
            }, select: {
                get: function (e) {
                    for (var t, n, r = e.options, i = e.selectedIndex, o = "select-one" === e.type || i < 0, a = o ? null : [], s = o ? i + 1 : r.length, u = i < 0 ? s : o ? i : 0; u < s; u++)if (n = r[u], (n.selected || u === i) && (fe.optDisabled ? !n.disabled : null === n.getAttribute("disabled")) && (!n.parentNode.disabled || !pe.nodeName(n.parentNode, "optgroup"))) {
                        if (t = pe(n).val(), o)return t;
                        a.push(t)
                    }
                    return a
                }, set: function (e, t) {
                    for (var n, r, i = e.options, o = pe.makeArray(t), a = i.length; a--;)if (r = i[a], pe.inArray(pe.valHooks.option.get(r), o) > -1)try {
                        r.selected = n = !0
                    } catch (s) {
                        r.scrollHeight
                    } else r.selected = !1;
                    return n || (e.selectedIndex = -1), i
                }
            }
        }
    }), pe.each(["radio", "checkbox"], function () {
        pe.valHooks[this] = {
            set: function (e, t) {
                if (pe.isArray(t))return e.checked = pe.inArray(pe(e).val(), t) > -1
            }
        }, fe.checkOn || (pe.valHooks[this].get = function (e) {
            return null === e.getAttribute("value") ? "on" : e.value
        })
    });
    var Lt, Ht, qt = pe.expr.attrHandle, _t = /^(?:checked|selected)$/i, Ft = fe.getSetAttribute, Mt = fe.input;
    pe.fn.extend({
        attr: function (e, t) {
            return Pe(this, pe.attr, e, t, arguments.length > 1)
        }, removeAttr: function (e) {
            return this.each(function () {
                pe.removeAttr(this, e)
            })
        }
    }), pe.extend({
        attr: function (e, t, n) {
            var r, i, o = e.nodeType;
            if (3 !== o && 8 !== o && 2 !== o)return "undefined" == typeof e.getAttribute ? pe.prop(e, t, n) : (1 === o && pe.isXMLDoc(e) || (t = t.toLowerCase(), i = pe.attrHooks[t] || (pe.expr.match.bool.test(t) ? Ht : Lt)), void 0 !== n ? null === n ? void pe.removeAttr(e, t) : i && "set" in i && void 0 !== (r = i.set(e, n, t)) ? r : (e.setAttribute(t, n + ""), n) : i && "get" in i && null !== (r = i.get(e, t)) ? r : (r = pe.find.attr(e, t), null == r ? void 0 : r))
        }, attrHooks: {
            type: {
                set: function (e, t) {
                    if (!fe.radioValue && "radio" === t && pe.nodeName(e, "input")) {
                        var n = e.value;
                        return e.setAttribute("type", t), n && (e.value = n), t
                    }
                }
            }
        }, removeAttr: function (e, t) {
            var n, r, i = 0, o = t && t.match(De);
            if (o && 1 === e.nodeType)for (; n = o[i++];)r = pe.propFix[n] || n, pe.expr.match.bool.test(n) ? Mt && Ft || !_t.test(n) ? e[r] = !1 : e[pe.camelCase("default-" + n)] = e[r] = !1 : pe.attr(e, n, ""), e.removeAttribute(Ft ? n : r)
        }
    }), Ht = {
        set: function (e, t, n) {
            return t === !1 ? pe.removeAttr(e, n) : Mt && Ft || !_t.test(n) ? e.setAttribute(!Ft && pe.propFix[n] || n, n) : e[pe.camelCase("default-" + n)] = e[n] = !0, n
        }
    }, pe.each(pe.expr.match.bool.source.match(/\w+/g), function (e, t) {
        var n = qt[t] || pe.find.attr;
        Mt && Ft || !_t.test(t) ? qt[t] = function (e, t, r) {
            var i, o;
            return r || (o = qt[t], qt[t] = i, i = null != n(e, t, r) ? t.toLowerCase() : null, qt[t] = o), i
        } : qt[t] = function (e, t, n) {
            if (!n)return e[pe.camelCase("default-" + t)] ? t.toLowerCase() : null
        }
    }), Mt && Ft || (pe.attrHooks.value = {
        set: function (e, t, n) {
            return pe.nodeName(e, "input") ? void(e.defaultValue = t) : Lt && Lt.set(e, t, n)
        }
    }), Ft || (Lt = {
        set: function (e, t, n) {
            var r = e.getAttributeNode(n);
            if (r || e.setAttributeNode(r = e.ownerDocument.createAttribute(n)), r.value = t += "", "value" === n || t === e.getAttribute(n))return t
        }
    }, qt.id = qt.name = qt.coords = function (e, t, n) {
        var r;
        if (!n)return (r = e.getAttributeNode(t)) && "" !== r.value ? r.value : null
    }, pe.valHooks.button = {
        get: function (e, t) {
            var n = e.getAttributeNode(t);
            if (n && n.specified)return n.value
        }, set: Lt.set
    }, pe.attrHooks.contenteditable = {
        set: function (e, t, n) {
            Lt.set(e, "" !== t && t, n)
        }
    }, pe.each(["width", "height"], function (e, t) {
        pe.attrHooks[t] = {
            set: function (e, n) {
                if ("" === n)return e.setAttribute(t, "auto"), n
            }
        }
    })), fe.style || (pe.attrHooks.style = {
        get: function (e) {
            return e.style.cssText || void 0
        }, set: function (e, t) {
            return e.style.cssText = t + ""
        }
    });
    var Ot = /^(?:input|select|textarea|button|object)$/i, Rt = /^(?:a|area)$/i;
    pe.fn.extend({
        prop: function (e, t) {
            return Pe(this, pe.prop, e, t, arguments.length > 1)
        }, removeProp: function (e) {
            return e = pe.propFix[e] || e, this.each(function () {
                try {
                    this[e] = void 0, delete this[e]
                } catch (t) {
                }
            })
        }
    }), pe.extend({
        prop: function (e, t, n) {
            var r, i, o = e.nodeType;
            if (3 !== o && 8 !== o && 2 !== o)return 1 === o && pe.isXMLDoc(e) || (t = pe.propFix[t] || t, i = pe.propHooks[t]), void 0 !== n ? i && "set" in i && void 0 !== (r = i.set(e, n, t)) ? r : e[t] = n : i && "get" in i && null !== (r = i.get(e, t)) ? r : e[t]
        }, propHooks: {
            tabIndex: {
                get: function (e) {
                    var t = pe.find.attr(e, "tabindex");
                    return t ? parseInt(t, 10) : Ot.test(e.nodeName) || Rt.test(e.nodeName) && e.href ? 0 : -1
                }
            }
        }, propFix: {"for": "htmlFor", "class": "className"}
    }), fe.hrefNormalized || pe.each(["href", "src"], function (e, t) {
        pe.propHooks[t] = {
            get: function (e) {
                return e.getAttribute(t, 4)
            }
        }
    }), fe.optSelected || (pe.propHooks.selected = {
        get: function (e) {
            var t = e.parentNode;
            return t && (t.selectedIndex, t.parentNode && t.parentNode.selectedIndex), null
        }, set: function (e) {
            var t = e.parentNode;
            t && (t.selectedIndex, t.parentNode && t.parentNode.selectedIndex)
        }
    }), pe.each(["tabIndex", "readOnly", "maxLength", "cellSpacing", "cellPadding", "rowSpan", "colSpan", "useMap", "frameBorder", "contentEditable"], function () {
        pe.propFix[this.toLowerCase()] = this
    }), fe.enctype || (pe.propFix.enctype = "encoding");
    var Pt = /[\t\r\n\f]/g;
    pe.fn.extend({
        addClass: function (e) {
            var t, n, r, i, o, a, s, u = 0;
            if (pe.isFunction(e))return this.each(function (t) {
                pe(this).addClass(e.call(this, t, z(this)))
            });
            if ("string" == typeof e && e)for (t = e.match(De) || []; n = this[u++];)if (i = z(n), r = 1 === n.nodeType && (" " + i + " ").replace(Pt, " ")) {
                for (a = 0; o = t[a++];)r.indexOf(" " + o + " ") < 0 && (r += o + " ");
                s = pe.trim(r), i !== s && pe.attr(n, "class", s)
            }
            return this
        }, removeClass: function (e) {
            var t, n, r, i, o, a, s, u = 0;
            if (pe.isFunction(e))return this.each(function (t) {
                pe(this).removeClass(e.call(this, t, z(this)))
            });
            if (!arguments.length)return this.attr("class", "");
            if ("string" == typeof e && e)for (t = e.match(De) || []; n = this[u++];)if (i = z(n), r = 1 === n.nodeType && (" " + i + " ").replace(Pt, " ")) {
                for (a = 0; o = t[a++];)for (; r.indexOf(" " + o + " ") > -1;)r = r.replace(" " + o + " ", " ");
                s = pe.trim(r), i !== s && pe.attr(n, "class", s)
            }
            return this
        }, toggleClass: function (e, t) {
            var n = typeof e;
            return "boolean" == typeof t && "string" === n ? t ? this.addClass(e) : this.removeClass(e) : pe.isFunction(e) ? this.each(function (n) {
                pe(this).toggleClass(e.call(this, n, z(this), t), t)
            }) : this.each(function () {
                var t, r, i, o;
                if ("string" === n)for (r = 0, i = pe(this), o = e.match(De) || []; t = o[r++];)i.hasClass(t) ? i.removeClass(t) : i.addClass(t); else void 0 !== e && "boolean" !== n || (t = z(this), t && pe._data(this, "__className__", t), pe.attr(this, "class", t || e === !1 ? "" : pe._data(this, "__className__") || ""))
            })
        }, hasClass: function (e) {
            var t, n, r = 0;
            for (t = " " + e + " "; n = this[r++];)if (1 === n.nodeType && (" " + z(n) + " ").replace(Pt, " ").indexOf(t) > -1)return !0;
            return !1
        }
    }), pe.each("blur focus focusin focusout load resize scroll unload click dblclick mousedown mouseup mousemove mouseover mouseout mouseenter mouseleave change select submit keydown keypress keyup error contextmenu".split(" "), function (e, t) {
        pe.fn[t] = function (e, n) {
            return arguments.length > 0 ? this.on(t, null, e, n) : this.trigger(t)
        }
    }), pe.fn.extend({
        hover: function (e, t) {
            return this.mouseenter(e).mouseleave(t || e)
        }
    });
    var Bt = e.location, Wt = pe.now(), It = /\?/, $t = /(,)|(\[|{)|(}|])|"(?:[^"\\\r\n]|\\["\\\/bfnrt]|\\u[\da-fA-F]{4})*"\s*:?|true|false|null|-?(?!0\d)\d+(?:\.\d+|)(?:[eE][+-]?\d+|)/g;
    pe.parseJSON = function (t) {
        if (e.JSON && e.JSON.parse)return e.JSON.parse(t + "");
        var n, r = null, i = pe.trim(t + "");
        return i && !pe.trim(i.replace($t, function (e, t, i, o) {
            return n && t && (r = 0), 0 === r ? e : (n = i || t, r += !o - !i, "")
        })) ? Function("return " + i)() : pe.error("Invalid JSON: " + t)
    }, pe.parseXML = function (t) {
        var n, r;
        if (!t || "string" != typeof t)return null;
        try {
            e.DOMParser ? (r = new e.DOMParser, n = r.parseFromString(t, "text/xml")) : (n = new e.ActiveXObject("Microsoft.XMLDOM"), n.async = "false", n.loadXML(t))
        } catch (i) {
            n = void 0
        }
        return n && n.documentElement && !n.getElementsByTagName("parsererror").length || pe.error("Invalid XML: " + t), n
    };
    var zt = /#.*$/, Xt = /([?&])_=[^&]*/, Ut = /^(.*?):[ \t]*([^\r\n]*)\r?$/gm, Vt = /^(?:about|app|app-storage|.+-extension|file|res|widget):$/, Yt = /^(?:GET|HEAD)$/, Jt = /^\/\//, Gt = /^([\w.+-]+:)(?:\/\/(?:[^\/?#]*@|)([^\/?#:]*)(?::(\d+)|)|)/, Kt = {}, Qt = {}, Zt = "*/".concat("*"), en = Bt.href, tn = Gt.exec(en.toLowerCase()) || [];
    pe.extend({
        active: 0,
        lastModified: {},
        etag: {},
        ajaxSettings: {
            url: en,
            type: "GET",
            isLocal: Vt.test(tn[1]),
            global: !0,
            processData: !0,
            async: !0,
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",
            accepts: {
                "*": Zt,
                text: "text/plain",
                html: "text/html",
                xml: "application/xml, text/xml",
                json: "application/json, text/javascript"
            },
            contents: {xml: /\bxml\b/, html: /\bhtml/, json: /\bjson\b/},
            responseFields: {xml: "responseXML", text: "responseText", json: "responseJSON"},
            converters: {"* text": String, "text html": !0, "text json": pe.parseJSON, "text xml": pe.parseXML},
            flatOptions: {url: !0, context: !0}
        },
        ajaxSetup: function (e, t) {
            return t ? V(V(e, pe.ajaxSettings), t) : V(pe.ajaxSettings, e)
        },
        ajaxPrefilter: X(Kt),
        ajaxTransport: X(Qt),
        ajax: function (t, n) {
            function r(t, n, r, i) {
                var o, f, v, x, w, C = n;
                2 !== b && (b = 2, u && e.clearTimeout(u), c = void 0, s = i || "", T.readyState = t > 0 ? 4 : 0, o = t >= 200 && t < 300 || 304 === t, r && (x = Y(d, T, r)), x = J(d, x, T, o), o ? (d.ifModified && (w = T.getResponseHeader("Last-Modified"), w && (pe.lastModified[a] = w), w = T.getResponseHeader("etag"), w && (pe.etag[a] = w)), 204 === t || "HEAD" === d.type ? C = "nocontent" : 304 === t ? C = "notmodified" : (C = x.state, f = x.data, v = x.error, o = !v)) : (v = C, !t && C || (C = "error", t < 0 && (t = 0))), T.status = t, T.statusText = (n || C) + "", o ? g.resolveWith(p, [f, C, T]) : g.rejectWith(p, [T, C, v]), T.statusCode(y), y = void 0, l && h.trigger(o ? "ajaxSuccess" : "ajaxError", [T, d, o ? f : v]), m.fireWith(p, [T, C]), l && (h.trigger("ajaxComplete", [T, d]), --pe.active || pe.event.trigger("ajaxStop")))
            }

            "object" == typeof t && (n = t, t = void 0), n = n || {};
            var i, o, a, s, u, l, c, f, d = pe.ajaxSetup({}, n), p = d.context || d, h = d.context && (p.nodeType || p.jquery) ? pe(p) : pe.event, g = pe.Deferred(), m = pe.Callbacks("once memory"), y = d.statusCode || {}, v = {}, x = {}, b = 0, w = "canceled", T = {
                readyState: 0,
                getResponseHeader: function (e) {
                    var t;
                    if (2 === b) {
                        if (!f)for (f = {}; t = Ut.exec(s);)f[t[1].toLowerCase()] = t[2];
                        t = f[e.toLowerCase()]
                    }
                    return null == t ? null : t
                },
                getAllResponseHeaders: function () {
                    return 2 === b ? s : null
                },
                setRequestHeader: function (e, t) {
                    var n = e.toLowerCase();
                    return b || (e = x[n] = x[n] || e, v[e] = t), this
                },
                overrideMimeType: function (e) {
                    return b || (d.mimeType = e), this
                },
                statusCode: function (e) {
                    var t;
                    if (e)if (b < 2)for (t in e)y[t] = [y[t], e[t]]; else T.always(e[T.status]);
                    return this
                },
                abort: function (e) {
                    var t = e || w;
                    return c && c.abort(t), r(0, t), this
                }
            };
            if (g.promise(T).complete = m.add, T.success = T.done, T.error = T.fail, d.url = ((t || d.url || en) + "").replace(zt, "").replace(Jt, tn[1] + "//"), d.type = n.method || n.type || d.method || d.type, d.dataTypes = pe.trim(d.dataType || "*").toLowerCase().match(De) || [""], null == d.crossDomain && (i = Gt.exec(d.url.toLowerCase()), d.crossDomain = !(!i || i[1] === tn[1] && i[2] === tn[2] && (i[3] || ("http:" === i[1] ? "80" : "443")) === (tn[3] || ("http:" === tn[1] ? "80" : "443")))), d.data && d.processData && "string" != typeof d.data && (d.data = pe.param(d.data, d.traditional)), U(Kt, d, n, T), 2 === b)return T;
            l = pe.event && d.global, l && 0 === pe.active++ && pe.event.trigger("ajaxStart"), d.type = d.type.toUpperCase(), d.hasContent = !Yt.test(d.type), a = d.url, d.hasContent || (d.data && (a = d.url += (It.test(a) ? "&" : "?") + d.data, delete d.data), d.cache === !1 && (d.url = Xt.test(a) ? a.replace(Xt, "$1_=" + Wt++) : a + (It.test(a) ? "&" : "?") + "_=" + Wt++)), d.ifModified && (pe.lastModified[a] && T.setRequestHeader("If-Modified-Since", pe.lastModified[a]), pe.etag[a] && T.setRequestHeader("If-None-Match", pe.etag[a])), (d.data && d.hasContent && d.contentType !== !1 || n.contentType) && T.setRequestHeader("Content-Type", d.contentType), T.setRequestHeader("Accept", d.dataTypes[0] && d.accepts[d.dataTypes[0]] ? d.accepts[d.dataTypes[0]] + ("*" !== d.dataTypes[0] ? ", " + Zt + "; q=0.01" : "") : d.accepts["*"]);
            for (o in d.headers)T.setRequestHeader(o, d.headers[o]);
            if (d.beforeSend && (d.beforeSend.call(p, T, d) === !1 || 2 === b))return T.abort();
            w = "abort";
            for (o in{success: 1, error: 1, complete: 1})T[o](d[o]);
            if (c = U(Qt, d, n, T)) {
                if (T.readyState = 1, l && h.trigger("ajaxSend", [T, d]), 2 === b)return T;
                d.async && d.timeout > 0 && (u = e.setTimeout(function () {
                    T.abort("timeout")
                }, d.timeout));
                try {
                    b = 1, c.send(v, r)
                } catch (C) {
                    if (!(b < 2))throw C;
                    r(-1, C)
                }
            } else r(-1, "No Transport");
            return T
        },
        getJSON: function (e, t, n) {
            return pe.get(e, t, n, "json")
        },
        getScript: function (e, t) {
            return pe.get(e, void 0, t, "script")
        }
    }), pe.each(["get", "post"], function (e, t) {
        pe[t] = function (e, n, r, i) {
            return pe.isFunction(n) && (i = i || r, r = n, n = void 0), pe.ajax(pe.extend({
                url: e,
                type: t,
                dataType: i,
                data: n,
                success: r
            }, pe.isPlainObject(e) && e))
        }
    }), pe._evalUrl = function (e) {
        return pe.ajax({url: e, type: "GET", dataType: "script", cache: !0, async: !1, global: !1, "throws": !0})
    }, pe.fn.extend({
        wrapAll: function (e) {
            if (pe.isFunction(e))return this.each(function (t) {
                pe(this).wrapAll(e.call(this, t))
            });
            if (this[0]) {
                var t = pe(e, this[0].ownerDocument).eq(0).clone(!0);
                this[0].parentNode && t.insertBefore(this[0]), t.map(function () {
                    for (var e = this; e.firstChild && 1 === e.firstChild.nodeType;)e = e.firstChild;
                    return e
                }).append(this)
            }
            return this
        }, wrapInner: function (e) {
            return pe.isFunction(e) ? this.each(function (t) {
                pe(this).wrapInner(e.call(this, t))
            }) : this.each(function () {
                var t = pe(this), n = t.contents();
                n.length ? n.wrapAll(e) : t.append(e)
            })
        }, wrap: function (e) {
            var t = pe.isFunction(e);
            return this.each(function (n) {
                pe(this).wrapAll(t ? e.call(this, n) : e)
            })
        }, unwrap: function () {
            return this.parent().each(function () {
                pe.nodeName(this, "body") || pe(this).replaceWith(this.childNodes)
            }).end()
        }
    }), pe.expr.filters.hidden = function (e) {
        return fe.reliableHiddenOffsets() ? e.offsetWidth <= 0 && e.offsetHeight <= 0 && !e.getClientRects().length : K(e)
    }, pe.expr.filters.visible = function (e) {
        return !pe.expr.filters.hidden(e)
    };
    var nn = /%20/g, rn = /\[\]$/, on = /\r?\n/g, an = /^(?:submit|button|image|reset|file)$/i, sn = /^(?:input|select|textarea|keygen)/i;
    pe.param = function (e, t) {
        var n, r = [], i = function (e, t) {
            t = pe.isFunction(t) ? t() : null == t ? "" : t, r[r.length] = encodeURIComponent(e) + "=" + encodeURIComponent(t)
        };
        if (void 0 === t && (t = pe.ajaxSettings && pe.ajaxSettings.traditional), pe.isArray(e) || e.jquery && !pe.isPlainObject(e))pe.each(e, function () {
            i(this.name, this.value)
        }); else for (n in e)Q(n, e[n], t, i);
        return r.join("&").replace(nn, "+")
    }, pe.fn.extend({
        serialize: function () {
            return pe.param(this.serializeArray())
        }, serializeArray: function () {
            return this.map(function () {
                var e = pe.prop(this, "elements");
                return e ? pe.makeArray(e) : this
            }).filter(function () {
                var e = this.type;
                return this.name && !pe(this).is(":disabled") && sn.test(this.nodeName) && !an.test(e) && (this.checked || !Be.test(e))
            }).map(function (e, t) {
                var n = pe(this).val();
                return null == n ? null : pe.isArray(n) ? pe.map(n, function (e) {
                    return {name: t.name, value: e.replace(on, "\r\n")}
                }) : {name: t.name, value: n.replace(on, "\r\n")}
            }).get()
        }
    }), pe.ajaxSettings.xhr = void 0 !== e.ActiveXObject ? function () {
        return this.isLocal ? ee() : re.documentMode > 8 ? Z() : /^(get|post|head|put|delete|options)$/i.test(this.type) && Z() || ee()
    } : Z;
    var un = 0, ln = {}, cn = pe.ajaxSettings.xhr();
    e.attachEvent && e.attachEvent("onunload", function () {
        for (var e in ln)ln[e](void 0, !0)
    }), fe.cors = !!cn && "withCredentials" in cn, cn = fe.ajax = !!cn, cn && pe.ajaxTransport(function (t) {
        if (!t.crossDomain || fe.cors) {
            var n;
            return {
                send: function (r, i) {
                    var o, a = t.xhr(), s = ++un;
                    if (a.open(t.type, t.url, t.async, t.username, t.password), t.xhrFields)for (o in t.xhrFields)a[o] = t.xhrFields[o];
                    t.mimeType && a.overrideMimeType && a.overrideMimeType(t.mimeType), t.crossDomain || r["X-Requested-With"] || (r["X-Requested-With"] = "XMLHttpRequest");
                    for (o in r)void 0 !== r[o] && a.setRequestHeader(o, r[o] + "");
                    a.send(t.hasContent && t.data || null), n = function (e, r) {
                        var o, u, l;
                        if (n && (r || 4 === a.readyState))if (delete ln[s], n = void 0, a.onreadystatechange = pe.noop, r)4 !== a.readyState && a.abort(); else {
                            l = {}, o = a.status, "string" == typeof a.responseText && (l.text = a.responseText);
                            try {
                                u = a.statusText
                            } catch (c) {
                                u = ""
                            }
                            o || !t.isLocal || t.crossDomain ? 1223 === o && (o = 204) : o = l.text ? 200 : 404
                        }
                        l && i(o, u, l, a.getAllResponseHeaders())
                    }, t.async ? 4 === a.readyState ? e.setTimeout(n) : a.onreadystatechange = ln[s] = n : n()
                }, abort: function () {
                    n && n(void 0, !0)
                }
            }
        }
    }), pe.ajaxSetup({
        accepts: {script: "text/javascript, application/javascript, application/ecmascript, application/x-ecmascript"},
        contents: {script: /\b(?:java|ecma)script\b/},
        converters: {
            "text script": function (e) {
                return pe.globalEval(e), e
            }
        }
    }), pe.ajaxPrefilter("script", function (e) {
        void 0 === e.cache && (e.cache = !1), e.crossDomain && (e.type = "GET", e.global = !1)
    }), pe.ajaxTransport("script", function (e) {
        if (e.crossDomain) {
            var t, n = re.head || pe("head")[0] || re.documentElement;
            return {
                send: function (r, i) {
                    t = re.createElement("script"), t.async = !0, e.scriptCharset && (t.charset = e.scriptCharset), t.src = e.url, t.onload = t.onreadystatechange = function (e, n) {
                        (n || !t.readyState || /loaded|complete/.test(t.readyState)) && (t.onload = t.onreadystatechange = null, t.parentNode && t.parentNode.removeChild(t), t = null, n || i(200, "success"))
                    }, n.insertBefore(t, n.firstChild)
                }, abort: function () {
                    t && t.onload(void 0, !0)
                }
            }
        }
    });
    var fn = [], dn = /(=)\?(?=&|$)|\?\?/;
    pe.ajaxSetup({
        jsonp: "callback", jsonpCallback: function () {
            var e = fn.pop() || pe.expando + "_" + Wt++;
            return this[e] = !0, e
        }
    }), pe.ajaxPrefilter("json jsonp", function (t, n, r) {
        var i, o, a, s = t.jsonp !== !1 && (dn.test(t.url) ? "url" : "string" == typeof t.data && 0 === (t.contentType || "").indexOf("application/x-www-form-urlencoded") && dn.test(t.data) && "data");
        if (s || "jsonp" === t.dataTypes[0])return i = t.jsonpCallback = pe.isFunction(t.jsonpCallback) ? t.jsonpCallback() : t.jsonpCallback, s ? t[s] = t[s].replace(dn, "$1" + i) : t.jsonp !== !1 && (t.url += (It.test(t.url) ? "&" : "?") + t.jsonp + "=" + i), t.converters["script json"] = function () {
            return a || pe.error(i + " was not called"), a[0]
        }, t.dataTypes[0] = "json", o = e[i], e[i] = function () {
            a = arguments
        }, r.always(function () {
            void 0 === o ? pe(e).removeProp(i) : e[i] = o, t[i] && (t.jsonpCallback = n.jsonpCallback, fn.push(i)), a && pe.isFunction(o) && o(a[0]), a = o = void 0
        }), "script"
    }), pe.parseHTML = function (e, t, n) {
        if (!e || "string" != typeof e)return null;
        "boolean" == typeof t && (n = t, t = !1), t = t || re;
        var r = Te.exec(e), i = !n && [];
        return r ? [t.createElement(r[1])] : (r = y([e], t, i), i && i.length && pe(i).remove(), pe.merge([], r.childNodes))
    };
    var pn = pe.fn.load;
    return pe.fn.load = function (e, t, n) {
        if ("string" != typeof e && pn)return pn.apply(this, arguments);
        var r, i, o, a = this, s = e.indexOf(" ");
        return s > -1 && (r = pe.trim(e.slice(s, e.length)), e = e.slice(0, s)), pe.isFunction(t) ? (n = t, t = void 0) : t && "object" == typeof t && (i = "POST"), a.length > 0 && pe.ajax({
            url: e,
            type: i || "GET",
            dataType: "html",
            data: t
        }).done(function (e) {
            o = arguments, a.html(r ? pe("<div>").append(pe.parseHTML(e)).find(r) : e)
        }).always(n && function (e, t) {
                a.each(function () {
                    n.apply(this, o || [e.responseText, t, e])
                })
            }), this
    }, pe.each(["ajaxStart", "ajaxStop", "ajaxComplete", "ajaxError", "ajaxSuccess", "ajaxSend"], function (e, t) {
        pe.fn[t] = function (e) {
            return this.on(t, e)
        }
    }), pe.expr.filters.animated = function (e) {
        return pe.grep(pe.timers, function (t) {
            return e === t.elem
        }).length
    }, pe.offset = {
        setOffset: function (e, t, n) {
            var r, i, o, a, s, u, l, c = pe.css(e, "position"), f = pe(e), d = {};
            "static" === c && (e.style.position = "relative"), s = f.offset(), o = pe.css(e, "top"), u = pe.css(e, "left"), l = ("absolute" === c || "fixed" === c) && pe.inArray("auto", [o, u]) > -1, l ? (r = f.position(), a = r.top, i = r.left) : (a = parseFloat(o) || 0, i = parseFloat(u) || 0), pe.isFunction(t) && (t = t.call(e, n, pe.extend({}, s))), null != t.top && (d.top = t.top - s.top + a), null != t.left && (d.left = t.left - s.left + i), "using" in t ? t.using.call(e, d) : f.css(d)
        }
    }, pe.fn.extend({
        offset: function (e) {
            if (arguments.length)return void 0 === e ? this : this.each(function (t) {
                pe.offset.setOffset(this, e, t)
            });
            var t, n, r = {top: 0, left: 0}, i = this[0], o = i && i.ownerDocument;
            if (o)return t = o.documentElement, pe.contains(t, i) ? ("undefined" != typeof i.getBoundingClientRect && (r = i.getBoundingClientRect()), n = te(o), {
                top: r.top + (n.pageYOffset || t.scrollTop) - (t.clientTop || 0),
                left: r.left + (n.pageXOffset || t.scrollLeft) - (t.clientLeft || 0)
            }) : r
        }, position: function () {
            if (this[0]) {
                var e, t, n = {top: 0, left: 0}, r = this[0];
                return "fixed" === pe.css(r, "position") ? t = r.getBoundingClientRect() : (e = this.offsetParent(), t = this.offset(), pe.nodeName(e[0], "html") || (n = e.offset()), n.top += pe.css(e[0], "borderTopWidth", !0), n.left += pe.css(e[0], "borderLeftWidth", !0)), {
                    top: t.top - n.top - pe.css(r, "marginTop", !0),
                    left: t.left - n.left - pe.css(r, "marginLeft", !0)
                }
            }
        }, offsetParent: function () {
            return this.map(function () {
                for (var e = this.offsetParent; e && !pe.nodeName(e, "html") && "static" === pe.css(e, "position");)e = e.offsetParent;
                return e || pt
            })
        }
    }), pe.each({scrollLeft: "pageXOffset", scrollTop: "pageYOffset"}, function (e, t) {
        var n = /Y/.test(t);
        pe.fn[e] = function (r) {
            return Pe(this, function (e, r, i) {
                var o = te(e);
                return void 0 === i ? o ? t in o ? o[t] : o.document.documentElement[r] : e[r] : void(o ? o.scrollTo(n ? pe(o).scrollLeft() : i, n ? i : pe(o).scrollTop()) : e[r] = i)
            }, e, r, arguments.length, null)
        }
    }), pe.each(["top", "left"], function (e, t) {
        pe.cssHooks[t] = L(fe.pixelPosition, function (e, n) {
            if (n)return n = gt(e, t), ft.test(n) ? pe(e).position()[t] + "px" : n
        })
    }), pe.each({Height: "height", Width: "width"}, function (e, t) {
        pe.each({padding: "inner" + e, content: t, "": "outer" + e}, function (n, r) {
            pe.fn[r] = function (r, i) {
                var o = arguments.length && (n || "boolean" != typeof r), a = n || (r === !0 || i === !0 ? "margin" : "border");
                return Pe(this, function (t, n, r) {
                    var i;
                    return pe.isWindow(t) ? t.document.documentElement["client" + e] : 9 === t.nodeType ? (i = t.documentElement, Math.max(t.body["scroll" + e], i["scroll" + e], t.body["offset" + e], i["offset" + e], i["client" + e])) : void 0 === r ? pe.css(t, n, a) : pe.style(t, n, r, a)
                }, t, o ? r : void 0, o, null)
            }
        })
    }), pe.fn.extend({
        bind: function (e, t, n) {
            return this.on(e, null, t, n)
        }, unbind: function (e, t) {
            return this.off(e, null, t)
        }, delegate: function (e, t, n, r) {
            return this.on(t, e, n, r)
        }, undelegate: function (e, t, n) {
            return 1 === arguments.length ? this.off(e, "**") : this.off(t, e || "**", n)
        }
    }), pe.fn.size = function () {
        return this.length
    }, pe.fn.andSelf = pe.fn.addBack, layui.define(function (e) {
        layui.$ = pe, e("jquery", pe)
    }), pe
});
!function (e, t) {
    "use strict";
    var i, n, a = e.layui && layui.define, o = {
        getPath: function () {
            var e = document.currentScript ? document.currentScript.src : function () {
                for (var e, t = document.scripts, i = t.length - 1, n = i; n > 0; n--)if ("interactive" === t[n].readyState) {
                    e = t[n].src;
                    break
                }
                return e || t[i].src
            }();
            return e.substring(0, e.lastIndexOf("/") + 1)
        }(),
        config: {},
        end: {},
        minIndex: 0,
        minLeft: [],
        btn: ["&#x786E;&#x5B9A;", "&#x53D6;&#x6D88;"],
        type: ["dialog", "page", "iframe", "loading", "tips"],
        getStyle: function (t, i) {
            var n = t.currentStyle ? t.currentStyle : e.getComputedStyle(t, null);
            return n[n.getPropertyValue ? "getPropertyValue" : "getAttribute"](i)
        },
        link: function (t, i, n) {
            if (r.path) {
                var a = document.getElementsByTagName("head")[0], s = document.createElement("link");
                "string" == typeof i && (n = i);
                var l = (n || t).replace(/\.|\//g, ""), f = "layuicss-" + l, c = 0;
                s.rel = "stylesheet", s.href = r.path + t, s.id = f, document.getElementById(f) || a.appendChild(s), "function" == typeof i && !function u() {
                    return ++c > 80 ? e.console && console.error("layer.css: Invalid") : void(1989 === parseInt(o.getStyle(document.getElementById(f), "width")) ? i() : setTimeout(u, 100))
                }()
            }
        }
    }, r = {
        v: "3.1.1", ie: function () {
            var t = navigator.userAgent.toLowerCase();
            return !!(e.ActiveXObject || "ActiveXObject" in e) && ((t.match(/msie\s(\d+)/) || [])[1] || "11")
        }(), index: e.layer && e.layer.v ? 1e5 : 0, path: o.getPath, config: function (e, t) {
            return e = e || {}, r.cache = o.config = i.extend({}, o.config, e), r.path = o.config.path || r.path, "string" == typeof e.extend && (e.extend = [e.extend]), o.config.path && r.ready(), e.extend ? (a ? layui.addcss("modules/layer/" + e.extend) : o.link("theme/" + e.extend), this) : this
        }, ready: function (e) {
            var t = "layer", i = "", n = (a ? "modules/layer/" : "theme/") + "default/layer.css?v=" + r.v + i;
            return a ? layui.addcss(n, e, t) : o.link(n, e, t), this
        }, alert: function (e, t, n) {
            var a = "function" == typeof t;
            return a && (n = t), r.open(i.extend({content: e, yes: n}, a ? {} : t))
        }, confirm: function (e, t, n, a) {
            var s = "function" == typeof t;
            return s && (a = n, n = t), r.open(i.extend({content: e, btn: o.btn, yes: n, btn2: a}, s ? {} : t))
        }, msg: function (e, n, a) {
            var s = "function" == typeof n, f = o.config.skin, c = (f ? f + " " + f + "-msg" : "") || "layui-layer-msg", u = l.anim.length - 1;
            return s && (a = n), r.open(i.extend({
                content: e,
                time: 3e3,
                shade: !1,
                skin: c,
                title: !1,
                closeBtn: !1,
                btn: !1,
                resize: !1,
                end: a
            }, s && !o.config.skin ? {skin: c + " layui-layer-hui", anim: u} : function () {
                return n = n || {}, (n.icon === -1 || n.icon === t && !o.config.skin) && (n.skin = c + " " + (n.skin || "layui-layer-hui")), n
            }()))
        }, load: function (e, t) {
            return r.open(i.extend({type: 3, icon: e || 0, resize: !1, shade: .01}, t))
        }, tips: function (e, t, n) {
            return r.open(i.extend({
                type: 4,
                content: [e, t],
                closeBtn: !1,
                time: 3e3,
                shade: !1,
                resize: !1,
                fixed: !1,
                maxWidth: 210
            }, n))
        }
    }, s = function (e) {
        var t = this;
        t.index = ++r.index, t.config = i.extend({}, t.config, o.config, e), document.body ? t.creat() : setTimeout(function () {
            t.creat()
        }, 30)
    };
    s.pt = s.prototype;
    var l = ["layui-layer", ".layui-layer-title", ".layui-layer-main", ".layui-layer-dialog", "layui-layer-iframe", "layui-layer-content", "layui-layer-btn", "layui-layer-close"];
    l.anim = ["layer-anim-00", "layer-anim-01", "layer-anim-02", "layer-anim-03", "layer-anim-04", "layer-anim-05", "layer-anim-06"], s.pt.config = {
        type: 0,
        shade: .3,
        fixed: !0,
        move: l[1],
        title: "&#x4FE1;&#x606F;",
        offset: "auto",
        area: "auto",
        closeBtn: 1,
        time: 0,
        zIndex: 19891014,
        maxWidth: 360,
        anim: 0,
        isOutAnim: !0,
        icon: -1,
        moveType: 1,
        resize: !0,
        scrollbar: !0,
        tips: 2
    }, s.pt.vessel = function (e, t) {
        var n = this, a = n.index, r = n.config, s = r.zIndex + a, f = "object" == typeof r.title, c = r.maxmin && (1 === r.type || 2 === r.type), u = r.title ? '<div class="layui-layer-title" style="' + (f ? r.title[1] : "") + '">' + (f ? r.title[0] : r.title) + "</div>" : "";
        return r.zIndex = s, t([r.shade ? '<div class="layui-layer-shade" id="layui-layer-shade' + a + '" times="' + a + '" style="' + ("z-index:" + (s - 1) + "; ") + '"></div>' : "", '<div class="' + l[0] + (" layui-layer-" + o.type[r.type]) + (0 != r.type && 2 != r.type || r.shade ? "" : " layui-layer-border") + " " + (r.skin || "") + '" id="' + l[0] + a + '" type="' + o.type[r.type] + '" times="' + a + '" showtime="' + r.time + '" conType="' + (e ? "object" : "string") + '" style="z-index: ' + s + "; width:" + r.area[0] + ";height:" + r.area[1] + (r.fixed ? "" : ";position:absolute;") + '">' + (e && 2 != r.type ? "" : u) + '<div id="' + (r.id || "") + '" class="layui-layer-content' + (0 == r.type && r.icon !== -1 ? " layui-layer-padding" : "") + (3 == r.type ? " layui-layer-loading" + r.icon : "") + '">' + (0 == r.type && r.icon !== -1 ? '<i class="layui-layer-ico layui-layer-ico' + r.icon + '"></i>' : "") + (1 == r.type && e ? "" : r.content || "") + '</div><span class="layui-layer-setwin">' + function () {
            var e = c ? '<a class="layui-layer-min" href="javascript:;"><cite></cite></a><a class="layui-layer-ico layui-layer-max" href="javascript:;"></a>' : "";
            return r.closeBtn && (e += '<a class="layui-layer-ico ' + l[7] + " " + l[7] + (r.title ? r.closeBtn : 4 == r.type ? "1" : "2") + '" href="javascript:;"></a>'), e
        }() + "</span>" + (r.btn ? function () {
            var e = "";
            "string" == typeof r.btn && (r.btn = [r.btn]);
            for (var t = 0, i = r.btn.length; t < i; t++)e += '<a class="' + l[6] + t + '">' + r.btn[t] + "</a>";
            return '<div class="' + l[6] + " layui-layer-btn-" + (r.btnAlign || "") + '">' + e + "</div>"
        }() : "") + (r.resize ? '<span class="layui-layer-resize"></span>' : "") + "</div>"], u, i('<div class="layui-layer-move"></div>')), n
    }, s.pt.creat = function () {
        var e = this, t = e.config, a = e.index, s = t.content, f = "object" == typeof s, c = i("body");
        if (!t.id || !i("#" + t.id)[0]) {
            switch ("string" == typeof t.area && (t.area = "auto" === t.area ? ["", ""] : [t.area, ""]), t.shift && (t.anim = t.shift), 6 == r.ie && (t.fixed = !1), t.type) {
                case 0:
                    t.btn = "btn" in t ? t.btn : o.btn[0], r.closeAll("dialog");
                    break;
                case 2:
                    var s = t.content = f ? t.content : [t.content || "http://layer.layui.com", "auto"];
                    t.content = '<iframe scrolling="' + (t.content[1] || "auto") + '" allowtransparency="true" id="' + l[4] + a + '" name="' + l[4] + a + '" onload="this.className=\'\';" class="layui-layer-load" frameborder="0" src="' + t.content[0] + '"></iframe>';
                    break;
                case 3:
                    delete t.title, delete t.closeBtn, t.icon === -1 && 0 === t.icon, r.closeAll("loading");
                    break;
                case 4:
                    f || (t.content = [t.content, "body"]), t.follow = t.content[1], t.content = t.content[0] + '<i class="layui-layer-TipsG"></i>', delete t.title, t.tips = "object" == typeof t.tips ? t.tips : [t.tips, !0], t.tipsMore || r.closeAll("tips")
            }
            if (e.vessel(f, function (n, r, u) {
                    c.append(n[0]), f ? function () {
                        2 == t.type || 4 == t.type ? function () {
                            i("body").append(n[1])
                        }() : function () {
                            s.parents("." + l[0])[0] || (s.data("display", s.css("display")).show().addClass("layui-layer-wrap").wrap(n[1]), i("#" + l[0] + a).find("." + l[5]).before(r))
                        }()
                    }() : c.append(n[1]), i(".layui-layer-move")[0] || c.append(o.moveElem = u), e.layero = i("#" + l[0] + a), t.scrollbar || l.html.css("overflow", "hidden").attr("layer-full", a)
                }).auto(a), i("#layui-layer-shade" + e.index).css({
                    "background-color": t.shade[1] || "#000",
                    opacity: t.shade[0] || t.shade
                }), 2 == t.type && 6 == r.ie && e.layero.find("iframe").attr("src", s[0]), 4 == t.type ? e.tips() : e.offset(), t.fixed && n.on("resize", function () {
                    e.offset(), (/^\d+%$/.test(t.area[0]) || /^\d+%$/.test(t.area[1])) && e.auto(a), 4 == t.type && e.tips()
                }), t.time <= 0 || setTimeout(function () {
                    r.close(e.index)
                }, t.time), e.move().callback(), l.anim[t.anim]) {
                var u = "layer-anim " + l.anim[t.anim];
                e.layero.addClass(u).one("webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend", function () {
                    i(this).removeClass(u)
                })
            }
            t.isOutAnim && e.layero.data("isOutAnim", !0)
        }
    }, s.pt.auto = function (e) {
        var t = this, a = t.config, o = i("#" + l[0] + e);
        "" === a.area[0] && a.maxWidth > 0 && (r.ie && r.ie < 8 && a.btn && o.width(o.innerWidth()), o.outerWidth() > a.maxWidth && o.width(a.maxWidth));
        var s = [o.innerWidth(), o.innerHeight()], f = o.find(l[1]).outerHeight() || 0, c = o.find("." + l[6]).outerHeight() || 0, u = function (e) {
            e = o.find(e), e.height(s[1] - f - c - 2 * (0 | parseFloat(e.css("padding-top"))))
        };
        switch (a.type) {
            case 2:
                u("iframe");
                break;
            default:
                "" === a.area[1] ? a.maxHeight > 0 && o.outerHeight() > a.maxHeight ? (s[1] = a.maxHeight, u("." + l[5])) : a.fixed && s[1] >= n.height() && (s[1] = n.height(), u("." + l[5])) : u("." + l[5])
        }
        return t
    }, s.pt.offset = function () {
        var e = this, t = e.config, i = e.layero, a = [i.outerWidth(), i.outerHeight()], o = "object" == typeof t.offset;
        e.offsetTop = (n.height() - a[1]) / 2, e.offsetLeft = (n.width() - a[0]) / 2, o ? (e.offsetTop = t.offset[0], e.offsetLeft = t.offset[1] || e.offsetLeft) : "auto" !== t.offset && ("t" === t.offset ? e.offsetTop = 0 : "r" === t.offset ? e.offsetLeft = n.width() - a[0] : "b" === t.offset ? e.offsetTop = n.height() - a[1] : "l" === t.offset ? e.offsetLeft = 0 : "lt" === t.offset ? (e.offsetTop = 0, e.offsetLeft = 0) : "lb" === t.offset ? (e.offsetTop = n.height() - a[1], e.offsetLeft = 0) : "rt" === t.offset ? (e.offsetTop = 0, e.offsetLeft = n.width() - a[0]) : "rb" === t.offset ? (e.offsetTop = n.height() - a[1], e.offsetLeft = n.width() - a[0]) : e.offsetTop = t.offset), t.fixed || (e.offsetTop = /%$/.test(e.offsetTop) ? n.height() * parseFloat(e.offsetTop) / 100 : parseFloat(e.offsetTop), e.offsetLeft = /%$/.test(e.offsetLeft) ? n.width() * parseFloat(e.offsetLeft) / 100 : parseFloat(e.offsetLeft), e.offsetTop += n.scrollTop(), e.offsetLeft += n.scrollLeft()), i.attr("minLeft") && (e.offsetTop = n.height() - (i.find(l[1]).outerHeight() || 0), e.offsetLeft = i.css("left")), i.css({
            top: e.offsetTop,
            left: e.offsetLeft
        })
    }, s.pt.tips = function () {
        var e = this, t = e.config, a = e.layero, o = [a.outerWidth(), a.outerHeight()], r = i(t.follow);
        r[0] || (r = i("body"));
        var s = {
            width: r.outerWidth(),
            height: r.outerHeight(),
            top: r.offset().top,
            left: r.offset().left
        }, f = a.find(".layui-layer-TipsG"), c = t.tips[0];
        t.tips[1] || f.remove(), s.autoLeft = function () {
            s.left + o[0] - n.width() > 0 ? (s.tipLeft = s.left + s.width - o[0], f.css({
                right: 12,
                left: "auto"
            })) : s.tipLeft = s.left
        }, s.where = [function () {
            s.autoLeft(), s.tipTop = s.top - o[1] - 10, f.removeClass("layui-layer-TipsB").addClass("layui-layer-TipsT").css("border-right-color", t.tips[1])
        }, function () {
            s.tipLeft = s.left + s.width + 10, s.tipTop = s.top, f.removeClass("layui-layer-TipsL").addClass("layui-layer-TipsR").css("border-bottom-color", t.tips[1])
        }, function () {
            s.autoLeft(), s.tipTop = s.top + s.height + 10, f.removeClass("layui-layer-TipsT").addClass("layui-layer-TipsB").css("border-right-color", t.tips[1])
        }, function () {
            s.tipLeft = s.left - o[0] - 10, s.tipTop = s.top, f.removeClass("layui-layer-TipsR").addClass("layui-layer-TipsL").css("border-bottom-color", t.tips[1])
        }], s.where[c - 1](), 1 === c ? s.top - (n.scrollTop() + o[1] + 16) < 0 && s.where[2]() : 2 === c ? n.width() - (s.left + s.width + o[0] + 16) > 0 || s.where[3]() : 3 === c ? s.top - n.scrollTop() + s.height + o[1] + 16 - n.height() > 0 && s.where[0]() : 4 === c && o[0] + 16 - s.left > 0 && s.where[1](), a.find("." + l[5]).css({
            "background-color": t.tips[1],
            "padding-right": t.closeBtn ? "30px" : ""
        }), a.css({left: s.tipLeft - (t.fixed ? n.scrollLeft() : 0), top: s.tipTop - (t.fixed ? n.scrollTop() : 0)})
    }, s.pt.move = function () {
        var e = this, t = e.config, a = i(document), s = e.layero, l = s.find(t.move), f = s.find(".layui-layer-resize"), c = {};
        return t.move && l.css("cursor", "move"), l.on("mousedown", function (e) {
            e.preventDefault(), t.move && (c.moveStart = !0, c.offset = [e.clientX - parseFloat(s.css("left")), e.clientY - parseFloat(s.css("top"))], o.moveElem.css("cursor", "move").show())
        }), f.on("mousedown", function (e) {
            e.preventDefault(), c.resizeStart = !0, c.offset = [e.clientX, e.clientY], c.area = [s.outerWidth(), s.outerHeight()], o.moveElem.css("cursor", "se-resize").show()
        }), a.on("mousemove", function (i) {
            if (c.moveStart) {
                var a = i.clientX - c.offset[0], o = i.clientY - c.offset[1], l = "fixed" === s.css("position");
                if (i.preventDefault(), c.stX = l ? 0 : n.scrollLeft(), c.stY = l ? 0 : n.scrollTop(), !t.moveOut) {
                    var f = n.width() - s.outerWidth() + c.stX, u = n.height() - s.outerHeight() + c.stY;
                    a < c.stX && (a = c.stX), a > f && (a = f), o < c.stY && (o = c.stY), o > u && (o = u)
                }
                s.css({left: a, top: o})
            }
            if (t.resize && c.resizeStart) {
                var a = i.clientX - c.offset[0], o = i.clientY - c.offset[1];
                i.preventDefault(), r.style(e.index, {
                    width: c.area[0] + a,
                    height: c.area[1] + o
                }), c.isResize = !0, t.resizing && t.resizing(s)
            }
        }).on("mouseup", function (e) {
            c.moveStart && (delete c.moveStart, o.moveElem.hide(), t.moveEnd && t.moveEnd(s)), c.resizeStart && (delete c.resizeStart, o.moveElem.hide())
        }), e
    }, s.pt.callback = function () {
        function e() {
            var e = a.cancel && a.cancel(t.index, n);
            e === !1 || r.close(t.index)
        }

        var t = this, n = t.layero, a = t.config;
        t.openLayer(), a.success && (2 == a.type ? n.find("iframe").on("load", function () {
            a.success(n, t.index)
        }) : a.success(n, t.index)), 6 == r.ie && t.IE6(n), n.find("." + l[6]).children("a").on("click", function () {
            var e = i(this).index();
            if (0 === e)a.yes ? a.yes(t.index, n) : a.btn1 ? a.btn1(t.index, n) : r.close(t.index); else {
                var o = a["btn" + (e + 1)] && a["btn" + (e + 1)](t.index, n);
                o === !1 || r.close(t.index)
            }
        }), n.find("." + l[7]).on("click", e), a.shadeClose && i("#layui-layer-shade" + t.index).on("click", function () {
            r.close(t.index)
        }), n.find(".layui-layer-min").on("click", function () {
            var e = a.min && a.min(n);
            e === !1 || r.min(t.index, a)
        }), n.find(".layui-layer-max").on("click", function () {
            i(this).hasClass("layui-layer-maxmin") ? (r.restore(t.index), a.restore && a.restore(n)) : (r.full(t.index, a), setTimeout(function () {
                a.full && a.full(n)
            }, 100))
        }), a.end && (o.end[t.index] = a.end)
    }, o.reselect = function () {
        i.each(i("select"), function (e, t) {
            var n = i(this);
            n.parents("." + l[0])[0] || 1 == n.attr("layer") && i("." + l[0]).length < 1 && n.removeAttr("layer").show(), n = null
        })
    }, s.pt.IE6 = function (e) {
        i("select").each(function (e, t) {
            var n = i(this);
            n.parents("." + l[0])[0] || "none" === n.css("display") || n.attr({layer: "1"}).hide(), n = null
        })
    }, s.pt.openLayer = function () {
        var e = this;
        r.zIndex = e.config.zIndex, r.setTop = function (e) {
            var t = function () {
                r.zIndex++, e.css("z-index", r.zIndex + 1)
            };
            return r.zIndex = parseInt(e[0].style.zIndex), e.on("mousedown", t), r.zIndex
        }
    }, o.record = function (e) {
        var t = [e.width(), e.height(), e.position().top, e.position().left + parseFloat(e.css("margin-left"))];
        e.find(".layui-layer-max").addClass("layui-layer-maxmin"), e.attr({area: t})
    }, o.rescollbar = function (e) {
        l.html.attr("layer-full") == e && (l.html[0].style.removeProperty ? l.html[0].style.removeProperty("overflow") : l.html[0].style.removeAttribute("overflow"), l.html.removeAttr("layer-full"))
    }, e.layer = r, r.getChildFrame = function (e, t) {
        return t = t || i("." + l[4]).attr("times"), i("#" + l[0] + t).find("iframe").contents().find(e)
    }, r.getFrameIndex = function (e) {
        return i("#" + e).parents("." + l[4]).attr("times")
    }, r.iframeAuto = function (e) {
        if (e) {
            var t = r.getChildFrame("html", e).outerHeight(), n = i("#" + l[0] + e), a = n.find(l[1]).outerHeight() || 0, o = n.find("." + l[6]).outerHeight() || 0;
            n.css({height: t + a + o}), n.find("iframe").css({height: t})
        }
    }, r.iframeSrc = function (e, t) {
        i("#" + l[0] + e).find("iframe").attr("src", t)
    }, r.style = function (e, t, n) {
        var a = i("#" + l[0] + e), r = a.find(".layui-layer-content"), s = a.attr("type"), f = a.find(l[1]).outerHeight() || 0, c = a.find("." + l[6]).outerHeight() || 0;
        a.attr("minLeft");
        s !== o.type[3] && s !== o.type[4] && (n || (parseFloat(t.width) <= 260 && (t.width = 260), parseFloat(t.height) - f - c <= 64 && (t.height = 64 + f + c)), a.css(t), c = a.find("." + l[6]).outerHeight(), s === o.type[2] ? a.find("iframe").css({height: parseFloat(t.height) - f - c}) : r.css({height: parseFloat(t.height) - f - c - parseFloat(r.css("padding-top")) - parseFloat(r.css("padding-bottom"))}))
    }, r.min = function (e, t) {
        var a = i("#" + l[0] + e), s = a.find(l[1]).outerHeight() || 0, f = a.attr("minLeft") || 181 * o.minIndex + "px", c = a.css("position");
        o.record(a), o.minLeft[0] && (f = o.minLeft[0], o.minLeft.shift()), a.attr("position", c), r.style(e, {
            width: 180,
            height: s,
            left: f,
            top: n.height() - s,
            position: "fixed",
            overflow: "hidden"
        }, !0), a.find(".layui-layer-min").hide(), "page" === a.attr("type") && a.find(l[4]).hide(), o.rescollbar(e), a.attr("minLeft") || o.minIndex++, a.attr("minLeft", f)
    }, r.restore = function (e) {
        var t = i("#" + l[0] + e), n = t.attr("area").split(",");
        t.attr("type");
        r.style(e, {
            width: parseFloat(n[0]),
            height: parseFloat(n[1]),
            top: parseFloat(n[2]),
            left: parseFloat(n[3]),
            position: t.attr("position"),
            overflow: "visible"
        }, !0), t.find(".layui-layer-max").removeClass("layui-layer-maxmin"), t.find(".layui-layer-min").show(), "page" === t.attr("type") && t.find(l[4]).show(), o.rescollbar(e)
    }, r.full = function (e) {
        var t, a = i("#" + l[0] + e);
        o.record(a), l.html.attr("layer-full") || l.html.css("overflow", "hidden").attr("layer-full", e), clearTimeout(t), t = setTimeout(function () {
            var t = "fixed" === a.css("position");
            r.style(e, {
                top: t ? 0 : n.scrollTop(),
                left: t ? 0 : n.scrollLeft(),
                width: n.width(),
                height: n.height()
            }, !0), a.find(".layui-layer-min").hide()
        }, 100)
    }, r.title = function (e, t) {
        var n = i("#" + l[0] + (t || r.index)).find(l[1]);
        n.html(e)
    }, r.close = function (e) {
        var t = i("#" + l[0] + e), n = t.attr("type"), a = "layer-anim-close";
        if (t[0]) {
            var s = "layui-layer-wrap", f = function () {
                if (n === o.type[1] && "object" === t.attr("conType")) {
                    t.children(":not(." + l[5] + ")").remove();
                    for (var a = t.find("." + s), r = 0; r < 2; r++)a.unwrap();
                    a.css("display", a.data("display")).removeClass(s)
                } else {
                    if (n === o.type[2])try {
                        var f = i("#" + l[4] + e)[0];
                        f.contentWindow.document.write(""), f.contentWindow.close(), t.find("." + l[5])[0].removeChild(f)
                    } catch (c) {
                    }
                    t[0].innerHTML = "", t.remove()
                }
                "function" == typeof o.end[e] && o.end[e](), delete o.end[e]
            };
            t.data("isOutAnim") && t.addClass("layer-anim " + a), i("#layui-layer-moves, #layui-layer-shade" + e).remove(), 6 == r.ie && o.reselect(), o.rescollbar(e), t.attr("minLeft") && (o.minIndex--, o.minLeft.push(t.attr("minLeft"))), r.ie && r.ie < 10 || !t.data("isOutAnim") ? f() : setTimeout(function () {
                f()
            }, 200)
        }
    }, r.closeAll = function (e) {
        i.each(i("." + l[0]), function () {
            var t = i(this), n = e ? t.attr("type") === e : 1;
            n && r.close(t.attr("times")), n = null
        })
    };
    var f = r.cache || {}, c = function (e) {
        return f.skin ? " " + f.skin + " " + f.skin + "-" + e : ""
    };
    r.prompt = function (e, t) {
        var a = "";
        if (e = e || {}, "function" == typeof e && (t = e), e.area) {
            var o = e.area;
            a = 'style="width: ' + o[0] + "; height: " + o[1] + ';"', delete e.area
        }
        var s, l = 2 == e.formType ? '<textarea class="layui-layer-input"' + a + "></textarea>" : function () {
            return '<input type="' + (1 == e.formType ? "password" : "text") + '" class="layui-layer-input">'
        }(), f = e.success;
        return delete e.success, r.open(i.extend({
            type: 1,
            btn: ["&#x786E;&#x5B9A;", "&#x53D6;&#x6D88;"],
            content: l,
            skin: "layui-layer-prompt" + c("prompt"),
            maxWidth: n.width(),
            success: function (t) {
                s = t.find(".layui-layer-input"), s.val(e.value || "").focus(), "function" == typeof f && f(t)
            },
            resize: !1,
            yes: function (i) {
                var n = s.val();
                "" === n ? s.focus() : n.length > (e.maxlength || 500) ? r.tips("&#x6700;&#x591A;&#x8F93;&#x5165;" + (e.maxlength || 500) + "&#x4E2A;&#x5B57;&#x6570;", s, {tips: 1}) : t && t(n, i, s)
            }
        }, e))
    }, r.tab = function (e) {
        e = e || {};
        var t = e.tab || {}, n = "layui-this", a = e.success;
        return delete e.success, r.open(i.extend({
            type: 1,
            skin: "layui-layer-tab" + c("tab"),
            resize: !1,
            title: function () {
                var e = t.length, i = 1, a = "";
                if (e > 0)for (a = '<span class="' + n + '">' + t[0].title + "</span>"; i < e; i++)a += "<span>" + t[i].title + "</span>";
                return a
            }(),
            content: '<ul class="layui-layer-tabmain">' + function () {
                var e = t.length, i = 1, a = "";
                if (e > 0)for (a = '<li class="layui-layer-tabli ' + n + '">' + (t[0].content || "no content") + "</li>"; i < e; i++)a += '<li class="layui-layer-tabli">' + (t[i].content || "no  content") + "</li>";
                return a
            }() + "</ul>",
            success: function (t) {
                var o = t.find(".layui-layer-title").children(), r = t.find(".layui-layer-tabmain").children();
                o.on("mousedown", function (t) {
                    t.stopPropagation ? t.stopPropagation() : t.cancelBubble = !0;
                    var a = i(this), o = a.index();
                    a.addClass(n).siblings().removeClass(n), r.eq(o).show().siblings().hide(), "function" == typeof e.change && e.change(o)
                }), "function" == typeof a && a(t)
            }
        }, e))
    }, r.photos = function (t, n, a) {
        function o(e, t, i) {
            var n = new Image;
            return n.src = e, n.complete ? t(n) : (n.onload = function () {
                n.onload = null, t(n)
            }, void(n.onerror = function (e) {
                n.onerror = null, i(e)
            }))
        }

        var s = {};
        if (t = t || {}, t.photos) {
            var l = t.photos.constructor === Object, f = l ? t.photos : {}, u = f.data || [], d = f.start || 0;
            s.imgIndex = (0 | d) + 1, t.img = t.img || "img";
            var y = t.success;
            if (delete t.success, l) {
                if (0 === u.length)return r.msg("&#x6CA1;&#x6709;&#x56FE;&#x7247;")
            } else {
                var p = i(t.photos), h = function () {
                    u = [], p.find(t.img).each(function (e) {
                        var t = i(this);
                        t.attr("layer-index", e), u.push({
                            alt: t.attr("alt"),
                            pid: t.attr("layer-pid"),
                            src: t.attr("layer-src") || t.attr("src"),
                            thumb: t.attr("src")
                        })
                    })
                };
                if (h(), 0 === u.length)return;
                if (n || p.on("click", t.img, function () {
                        var e = i(this), n = e.attr("layer-index");
                        r.photos(i.extend(t, {photos: {start: n, data: u, tab: t.tab}, full: t.full}), !0), h()
                    }), !n)return
            }
            s.imgprev = function (e) {
                s.imgIndex--, s.imgIndex < 1 && (s.imgIndex = u.length), s.tabimg(e)
            }, s.imgnext = function (e, t) {
                s.imgIndex++, s.imgIndex > u.length && (s.imgIndex = 1, t) || s.tabimg(e)
            }, s.keyup = function (e) {
                if (!s.end) {
                    var t = e.keyCode;
                    e.preventDefault(), 37 === t ? s.imgprev(!0) : 39 === t ? s.imgnext(!0) : 27 === t && r.close(s.index)
                }
            }, s.tabimg = function (e) {
                if (!(u.length <= 1))return f.start = s.imgIndex - 1, r.close(s.index), r.photos(t, !0, e)
            }, s.event = function () {
                s.bigimg.hover(function () {
                    s.imgsee.show()
                }, function () {
                    s.imgsee.hide()
                }), s.bigimg.find(".layui-layer-imgprev").on("click", function (e) {
                    e.preventDefault(), s.imgprev()
                }), s.bigimg.find(".layui-layer-imgnext").on("click", function (e) {
                    e.preventDefault(), s.imgnext()
                }), i(document).on("keyup", s.keyup)
            }, s.loadi = r.load(1, {shade: !("shade" in t) && .9, scrollbar: !1}), o(u[d].src, function (n) {
                r.close(s.loadi), s.index = r.open(i.extend({
                    type: 1,
                    id: "layui-layer-photos",
                    area: function () {
                        var a = [n.width, n.height], o = [i(e).width() - 100, i(e).height() - 100];
                        if (!t.full && (a[0] > o[0] || a[1] > o[1])) {
                            var r = [a[0] / o[0], a[1] / o[1]];
                            r[0] > r[1] ? (a[0] = a[0] / r[0], a[1] = a[1] / r[0]) : r[0] < r[1] && (a[0] = a[0] / r[1], a[1] = a[1] / r[1])
                        }
                        return [a[0] + "px", a[1] + "px"]
                    }(),
                    title: !1,
                    shade: .9,
                    shadeClose: !0,
                    closeBtn: !1,
                    move: ".layui-layer-phimg img",
                    moveType: 1,
                    scrollbar: !1,
                    moveOut: !0,
                    isOutAnim: !1,
                    skin: "layui-layer-photos" + c("photos"),
                    content: '<div class="layui-layer-phimg"><img src="' + u[d].src + '" alt="' + (u[d].alt || "") + '" layer-pid="' + u[d].pid + '"><div class="layui-layer-imgsee">' + (u.length > 1 ? '<span class="layui-layer-imguide"><a href="javascript:;" class="layui-layer-iconext layui-layer-imgprev"></a><a href="javascript:;" class="layui-layer-iconext layui-layer-imgnext"></a></span>' : "") + '<div class="layui-layer-imgbar" style="display:' + (a ? "block" : "") + '"><span class="layui-layer-imgtit"><a href="javascript:;">' + (u[d].alt || "") + "</a><em>" + s.imgIndex + "/" + u.length + "</em></span></div></div></div>",
                    success: function (e, i) {
                        s.bigimg = e.find(".layui-layer-phimg"), s.imgsee = e.find(".layui-layer-imguide,.layui-layer-imgbar"), s.event(e), t.tab && t.tab(u[d], e), "function" == typeof y && y(e)
                    },
                    end: function () {
                        s.end = !0, i(document).off("keyup", s.keyup)
                    }
                }, t))
            }, function () {
                r.close(s.loadi), r.msg("&#x5F53;&#x524D;&#x56FE;&#x7247;&#x5730;&#x5740;&#x5F02;&#x5E38;<br>&#x662F;&#x5426;&#x7EE7;&#x7EED;&#x67E5;&#x770B;&#x4E0B;&#x4E00;&#x5F20;&#xFF1F;", {
                    time: 3e4,
                    btn: ["&#x4E0B;&#x4E00;&#x5F20;", "&#x4E0D;&#x770B;&#x4E86;"],
                    yes: function () {
                        u.length > 1 && s.imgnext(!0, !0)
                    }
                })
            })
        }
    }, o.run = function (t) {
        i = t, n = i(e), l.html = i("html"), r.open = function (e) {
            var t = new s(e);
            return t.index
        }
    }, e.layui && layui.define ? (r.ready(), layui.define("jquery", function (t) {
        r.path = layui.cache.dir, o.run(layui.$), e.layer = r, t("layer", r)
    })) : "function" == typeof define && define.amd ? define(["jquery"], function () {
        return o.run(e.jQuery), r
    }) : function () {
        o.run(e.jQuery), r.ready()
    }()
}(window);
layui.define("jquery", function (t) {
    "use strict";
    var a = layui.$, i = (layui.hint(), layui.device()), e = "element", l = "layui-this", n = "layui-show", s = function () {
        this.config = {}
    };
    s.prototype.set = function (t) {
        var i = this;
        return a.extend(!0, i.config, t), i
    }, s.prototype.on = function (t, a) {
        return layui.onevent.call(this, e, t, a)
    }, s.prototype.tabAdd = function (t, i) {
        var e = ".layui-tab-title", l = a(".layui-tab[lay-filter=" + t + "]"), n = l.children(e), s = n.children(".layui-tab-bar"), o = l.children(".layui-tab-content"), r = '<li lay-id="' + (i.id || "") + '"' + (i.attr ? ' lay-attr="' + i.attr + '"' : "") + ">" + (i.title || "unnaming") + "</li>";
        return s[0] ? s.before(r) : n.append(r), o.append('<div class="layui-tab-item">' + (i.content || "") + "</div>"), f.hideTabMore(!0), f.tabAuto(), this
    }, s.prototype.tabDelete = function (t, i) {
        var e = ".layui-tab-title", l = a(".layui-tab[lay-filter=" + t + "]"), n = l.children(e), s = n.find('>li[lay-id="' + i + '"]');
        return f.tabDelete(null, s), this
    }, s.prototype.tabChange = function (t, i) {
        var e = ".layui-tab-title", l = a(".layui-tab[lay-filter=" + t + "]"), n = l.children(e), s = n.find('>li[lay-id="' + i + '"]');
        return f.tabClick.call(s[0], null, null, s), this
    }, s.prototype.tab = function (t) {
        t = t || {}, b.on("click", t.headerElem, function (i) {
            var e = a(this).index();
            f.tabClick.call(this, i, e, null, t)
        })
    }, s.prototype.progress = function (t, i) {
        var e = "layui-progress", l = a("." + e + "[lay-filter=" + t + "]"), n = l.find("." + e + "-bar"), s = n.find("." + e + "-text");
        return n.css("width", i), s.text(i), this
    };
    var o = ".layui-nav", r = "layui-nav-item", c = "layui-nav-bar", u = "layui-nav-tree", d = "layui-nav-child", y = "layui-nav-more", h = "layui-anim layui-anim-upbit", f = {
        tabClick: function (t, i, s, o) {
            o = o || {};
            var r = s || a(this), i = i || r.parent().children("li").index(r), c = o.headerElem ? r.parent() : r.parents(".layui-tab").eq(0), u = o.bodyElem ? a(o.bodyElem) : c.children(".layui-tab-content").children(".layui-tab-item"), d = r.find("a"), y = c.attr("lay-filter");
            "javascript:;" !== d.attr("href") && "_blank" === d.attr("target") || (r.addClass(l).siblings().removeClass(l), u.eq(i).addClass(n).siblings().removeClass(n)), layui.event.call(this, e, "tab(" + y + ")", {
                elem: c,
                index: i
            })
        }, tabDelete: function (t, i) {
            var n = i || a(this).parent(), s = n.index(), o = n.parents(".layui-tab").eq(0), r = o.children(".layui-tab-content").children(".layui-tab-item"), c = o.attr("lay-filter");
            n.hasClass(l) && (n.next()[0] ? f.tabClick.call(n.next()[0], null, s + 1) : n.prev()[0] && f.tabClick.call(n.prev()[0], null, s - 1)), n.remove(), r.eq(s).remove(), setTimeout(function () {
                f.tabAuto()
            }, 50), layui.event.call(this, e, "tabDelete(" + c + ")", {elem: o, index: s})
        }, tabAuto: function () {
            var t = "layui-tab-more", e = "layui-tab-bar", l = "layui-tab-close", n = this;
            a(".layui-tab").each(function () {
                var s = a(this), o = s.children(".layui-tab-title"), r = (s.children(".layui-tab-content").children(".layui-tab-item"), 'lay-stope="tabmore"'), c = a('<span class="layui-unselect layui-tab-bar" ' + r + "><i " + r + ' class="layui-icon">&#xe61a;</i></span>');
                if (n === window && 8 != i.ie && f.hideTabMore(!0), s.attr("lay-allowClose") && o.find("li").each(function () {
                        var t = a(this);
                        if (!t.find("." + l)[0]) {
                            var i = a('<i class="layui-icon layui-unselect ' + l + '">&#x1006;</i>');
                            i.on("click", f.tabDelete), t.append(i)
                        }
                    }), "string" != typeof s.attr("lay-unauto"))if (o.prop("scrollWidth") > o.outerWidth() + 1) {
                    if (o.find("." + e)[0])return;
                    o.append(c), s.attr("overflow", ""), c.on("click", function (a) {
                        o[this.title ? "removeClass" : "addClass"](t), this.title = this.title ? "" : "收缩"
                    })
                } else o.find("." + e).remove(), s.removeAttr("overflow")
            })
        }, hideTabMore: function (t) {
            var i = a(".layui-tab-title");
            t !== !0 && "tabmore" === a(t.target).attr("lay-stope") || (i.removeClass("layui-tab-more"), i.find(".layui-tab-bar").attr("title", ""))
        }, clickThis: function () {
            var t = a(this), i = t.parents(o), n = i.attr("lay-filter"), s = t.parent(), c = t.siblings("." + d), y = "string" == typeof s.attr("lay-unselect");
            "javascript:;" !== t.attr("href") && "_blank" === t.attr("target") || y || c[0] || (i.find("." + l).removeClass(l), s.addClass(l)), i.hasClass(u) && (c.removeClass(h), c[0] && (s["none" === c.css("display") ? "addClass" : "removeClass"](r + "ed"), "all" === i.attr("lay-shrink") && s.siblings().removeClass(r + "ed"))), layui.event.call(this, e, "nav(" + n + ")", t)
        }, collapse: function () {
            var t = a(this), i = t.find(".layui-colla-icon"), l = t.siblings(".layui-colla-content"), s = t.parents(".layui-collapse").eq(0), o = s.attr("lay-filter"), r = "none" === l.css("display");
            if ("string" == typeof s.attr("lay-accordion")) {
                var c = s.children(".layui-colla-item").children("." + n);
                c.siblings(".layui-colla-title").children(".layui-colla-icon").html("&#xe602;"), c.removeClass(n)
            }
            l[r ? "addClass" : "removeClass"](n), i.html(r ? "&#xe61a;" : "&#xe602;"), layui.event.call(this, e, "collapse(" + o + ")", {
                title: t,
                content: l,
                show: r
            })
        }
    };
    s.prototype.init = function (t, e) {
        var l = function () {
            return e ? '[lay-filter="' + e + '"]' : ""
        }(), s = {
            tab: function () {
                f.tabAuto.call({})
            }, nav: function () {
                var t = 200, e = {}, s = {}, p = {}, b = function (l, o, r) {
                    var c = a(this), f = c.find("." + d);
                    o.hasClass(u) ? l.css({
                        top: c.position().top,
                        height: c.children("a").outerHeight(),
                        opacity: 1
                    }) : (f.addClass(h), l.css({
                        left: c.position().left + parseFloat(c.css("marginLeft")),
                        top: c.position().top + c.height() - l.height()
                    }), e[r] = setTimeout(function () {
                        l.css({width: c.width(), opacity: 1})
                    }, i.ie && i.ie < 10 ? 0 : t), clearTimeout(p[r]), "block" === f.css("display") && clearTimeout(s[r]), s[r] = setTimeout(function () {
                        f.addClass(n), c.find("." + y).addClass(y + "d")
                    }, 300))
                };
                a(o + l).each(function (i) {
                    var l = a(this), o = a('<span class="' + c + '"></span>'), h = l.find("." + r);
                    l.find("." + c)[0] || (l.append(o), h.on("mouseenter", function () {
                        b.call(this, o, l, i)
                    }).on("mouseleave", function () {
                        l.hasClass(u) || (clearTimeout(s[i]), s[i] = setTimeout(function () {
                            l.find("." + d).removeClass(n), l.find("." + y).removeClass(y + "d")
                        }, 300))
                    }), l.on("mouseleave", function () {
                        clearTimeout(e[i]), p[i] = setTimeout(function () {
                            l.hasClass(u) ? o.css({
                                height: 0,
                                top: o.position().top + o.height() / 2,
                                opacity: 0
                            }) : o.css({width: 0, left: o.position().left + o.width() / 2, opacity: 0})
                        }, t)
                    })), h.find("a").each(function () {
                        var t = a(this), i = (t.parent(), t.siblings("." + d));
                        i[0] && !t.children("." + y)[0] && t.append('<span class="' + y + '"></span>'), t.off("click", f.clickThis).on("click", f.clickThis)
                    })
                })
            }, breadcrumb: function () {
                var t = ".layui-breadcrumb";
                a(t + l).each(function () {
                    var t = a(this), i = "lay-separator", e = t.attr(i) || "/", l = t.find("a");
                    l.next("span[" + i + "]")[0] || (l.each(function (t) {
                        t !== l.length - 1 && a(this).after("<span " + i + ">" + e + "</span>")
                    }), t.css("visibility", "visible"))
                })
            }, progress: function () {
                var t = "layui-progress";
                a("." + t + l).each(function () {
                    var i = a(this), e = i.find(".layui-progress-bar"), l = e.attr("lay-percent");
                    e.css("width", function () {
                        return /^.+\/.+$/.test(l) ? 100 * new Function("return " + l)() + "%" : l
                    }()), i.attr("lay-showPercent") && setTimeout(function () {
                        e.html('<span class="' + t + '-text">' + l + "</span>")
                    }, 350)
                })
            }, collapse: function () {
                var t = "layui-collapse";
                a("." + t + l).each(function () {
                    var t = a(this).find(".layui-colla-item");
                    t.each(function () {
                        var t = a(this), i = t.find(".layui-colla-title"), e = t.find(".layui-colla-content"), l = "none" === e.css("display");
                        i.find(".layui-colla-icon").remove(), i.append('<i class="layui-icon layui-colla-icon">' + (l ? "&#xe602;" : "&#xe61a;") + "</i>"), i.off("click", f.collapse).on("click", f.collapse)
                    })
                })
            }
        };
        return s[t] ? s[t]() : layui.each(s, function (t, a) {
            a()
        })
    }, s.prototype.render = s.prototype.init;
    var p = new s, b = a(document);
    p.render();
    var v = ".layui-tab-title li";
    b.on("click", v, f.tabClick), b.on("click", f.hideTabMore), a(window).on("resize", f.tabAuto), t(e, p)
});
layui.define("layer", function (e) {
    "use strict";
    var t = layui.$, i = layui.layer, n = layui.hint(), a = layui.device(), o = {
        config: {}, set: function (e) {
            var i = this;
            return i.config = t.extend({}, i.config, e), i
        }, on: function (e, t) {
            return layui.onevent.call(this, r, e, t)
        }
    }, l = function () {
        var e = this;
        return {
            upload: function (t) {
                e.upload.call(e, t)
            }, config: e.config
        }
    }, r = "upload", u = "layui-upload-file", c = "layui-upload-form", f = "layui-upload-iframe", s = "layui-upload-choose", p = function (e) {
        var i = this;
        i.config = t.extend({}, i.config, o.config, e), i.render()
    };
    p.prototype.config = {
        accept: "images",
        exts: "",
        auto: !0,
        bindAction: "",
        url: "",
        field: "file",
        method: "post",
        data: {},
        drag: !0,
        size: 0,
        number: 0,
        multiple: !1
    }, p.prototype.render = function (e) {
        var i = this, e = i.config;
        e.elem = t(e.elem), e.bindAction = t(e.bindAction), i.file(), i.events()
    }, p.prototype.file = function () {
        var e = this, i = e.config, n = e.elemFile = t(['<input class="' + u + '" type="file" accept="' + i.acceptMime + '" name="' + i.field + '"', i.multiple ? " multiple" : "", ">"].join("")), o = i.elem.next();
        (o.hasClass(u) || o.hasClass(c)) && o.remove(), a.ie && a.ie < 10 && i.elem.wrap('<div class="layui-upload-wrap"></div>'), e.isFile() ? (e.elemFile = i.elem, i.field = i.elem[0].name) : i.elem.after(n), a.ie && a.ie < 10 && e.initIE()
    }, p.prototype.initIE = function () {
        var e = this, i = e.config, n = t('<iframe id="' + f + '" class="' + f + '" name="' + f + '" frameborder="0"></iframe>'), a = t(['<form target="' + f + '" class="' + c + '" method="' + i.method, '" key="set-mine" enctype="multipart/form-data" action="' + i.url + '">', "</form>"].join(""));
        t("#" + f)[0] || t("body").append(n), i.elem.next().hasClass(f) || (e.elemFile.wrap(a), i.elem.next("." + f).append(function () {
            var e = [];
            return layui.each(i.data, function (t, i) {
                i = "function" == typeof i ? i() : i, e.push('<input type="hidden" name="' + t + '" value="' + i + '">')
            }), e.join("")
        }()))
    }, p.prototype.msg = function (e) {
        return i.msg(e, {icon: 2, shift: 6})
    }, p.prototype.isFile = function () {
        var e = this.config.elem[0];
        if (e)return "input" === e.tagName.toLocaleLowerCase() && "file" === e.type
    }, p.prototype.preview = function (e) {
        var t = this;
        window.FileReader && layui.each(t.chooseFiles, function (t, i) {
            var n = new FileReader;
            n.readAsDataURL(i), n.onload = function () {
                e && e(t, i, this.result)
            }
        })
    }, p.prototype.upload = function (e, i) {
        var n, o = this, l = o.config, r = o.elemFile[0], u = function () {
            var i = 0, n = 0, a = e || o.files || o.chooseFiles || r.files, u = function () {
                l.multiple && i + n === o.fileLength && "function" == typeof l.allDone && l.allDone({
                    total: o.fileLength,
                    successful: i,
                    aborted: n
                })
            };
            layui.each(a, function (e, a) {
                var r = new FormData;
                r.append(l.field, a), layui.each(l.data, function (e, t) {
                    t = "function" == typeof t ? t() : t, r.append(e, t)
                }), t.ajax({
                    url: l.url,
                    type: l.method,
                    data: r,
                    contentType: !1,
                    processData: !1,
                    dataType: "json",
                    headers: l.headers || {},
                    success: function (t) {
                        i++, d(e, t), u()
                    },
                    error: function () {
                        n++, o.msg("请求上传接口出现异常"), m(e), u()
                    }
                })
            })
        }, c = function () {
            var e = t("#" + f);
            o.elemFile.parent().submit(), clearInterval(p.timer), p.timer = setInterval(function () {
                var t, i = e.contents().find("body");
                try {
                    t = i.text()
                } catch (n) {
                    o.msg("获取上传后的响应信息出现异常"), clearInterval(p.timer), m()
                }
                t && (clearInterval(p.timer), i.html(""), d(0, t))
            }, 30)
        }, d = function (e, t) {
            if (o.elemFile.next("." + s).remove(), r.value = "", "object" != typeof t)try {
                t = JSON.parse(t)
            } catch (i) {
                return t = {}, o.msg("请对上传接口返回有效JSON")
            }
            "function" == typeof l.done && l.done(t, e || 0, function (e) {
                o.upload(e)
            })
        }, m = function (e) {
            l.auto && (r.value = ""), "function" == typeof l.error && l.error(e || 0, function (e) {
                o.upload(e)
            })
        }, h = l.exts, v = function () {
            var t = [];
            return layui.each(e || o.chooseFiles, function (e, i) {
                t.push(i.name)
            }), t
        }(), g = {
            preview: function (e) {
                o.preview(e)
            }, upload: function (e, t) {
                var i = {};
                i[e] = t, o.upload(i)
            }, pushFile: function () {
                return o.files = o.files || {}, layui.each(o.chooseFiles, function (e, t) {
                    o.files[e] = t
                }), o.files
            }
        }, y = function () {
            return "choose" === i ? l.choose && l.choose(g) : (l.before && l.before(g), a.ie ? a.ie > 9 ? u() : c() : void u())
        };
        if (v = 0 === v.length ? r.value.match(/[^\/\\]+\..+/g) || [] || "" : v, 0 !== v.length) {
            switch (l.accept) {
                case"file":
                    if (h && !RegExp("\\w\\.(" + h + ")$", "i").test(escape(v)))return o.msg("选择的文件中包含不支持的格式"), r.value = "";
                    break;
                case"video":
                    if (!RegExp("\\w\\.(" + (h || "avi|mp4|wma|rmvb|rm|flash|3gp|flv") + ")$", "i").test(escape(v)))return o.msg("选择的视频中包含不支持的格式"), r.value = "";
                    break;
                case"audio":
                    if (!RegExp("\\w\\.(" + (h || "mp3|wav|mid") + ")$", "i").test(escape(v)))return o.msg("选择的音频中包含不支持的格式"), r.value = "";
                    break;
                default:
                    if (layui.each(v, function (e, t) {
                            RegExp("\\w\\.(" + (h || "jpg|png|gif|bmp|jpeg$") + ")", "i").test(escape(t)) || (n = !0)
                        }), n)return o.msg("选择的图片中包含不支持的格式"), r.value = ""
            }
            if (o.fileLength = function () {
                    var t = 0, i = e || o.files || o.chooseFiles || r.files;
                    return layui.each(i, function () {
                        t++
                    }), t
                }(), l.number && o.fileLength > l.number)return o.msg("同时最多只能上传的数量为：" + l.number);
            if (l.size > 0 && !(a.ie && a.ie < 10)) {
                var F;
                if (layui.each(o.chooseFiles, function (e, t) {
                        if (t.size > 1024 * l.size) {
                            var i = l.size / 1024;
                            i = i >= 1 ? Math.floor(i) + (i % 1 > 0 ? i.toFixed(1) : 0) + "MB" : l.size + "KB", r.value = "", F = i
                        }
                    }), F)return o.msg("文件不能超过" + F)
            }
            y()
        }
    }, p.prototype.events = function () {
        var e = this, i = e.config, o = function (t) {
            e.chooseFiles = {}, layui.each(t, function (t, i) {
                var n = (new Date).getTime();
                e.chooseFiles[n + "-" + t] = i
            })
        }, l = function (t, n) {
            var a = e.elemFile, o = t.length > 1 ? t.length + "个文件" : (t[0] || {}).name || a[0].value.match(/[^\/\\]+\..+/g) || [] || "";
            a.next().hasClass(s) && a.next().remove(), e.upload(null, "choose"), e.isFile() || i.choose || a.after('<span class="layui-inline ' + s + '">' + o + "</span>")
        };
        i.elem.off("upload.start").on("upload.start", function () {
            var a = t(this), o = a.attr("lay-data");
            if (o)try {
                o = new Function("return " + o)(), e.config = t.extend({}, i, o)
            } catch (l) {
                n.error("Upload element property lay-data configuration item has a syntax error: " + o)
            }
            e.config.item = a, e.elemFile[0].click()
        }), a.ie && a.ie < 10 || i.elem.off("upload.over").on("upload.over", function () {
            var e = t(this);
            e.attr("lay-over", "")
        }).off("upload.leave").on("upload.leave", function () {
            var e = t(this);
            e.removeAttr("lay-over")
        }).off("upload.drop").on("upload.drop", function (n, a) {
            var r = t(this), u = a.originalEvent.dataTransfer.files || [];
            r.removeAttr("lay-over"), o(u), i.auto ? e.upload(u) : l(u)
        }), e.elemFile.off("upload.change").on("upload.change", function () {
            var t = this.files || [];
            o(t), i.auto ? e.upload() : l(t)
        }), i.bindAction.off("upload.action").on("upload.action", function () {
            e.upload()
        }), i.elem.data("haveEvents") || (e.elemFile.on("change", function () {
            t(this).trigger("upload.change")
        }), i.elem.on("click", function () {
            e.isFile() || t(this).trigger("upload.start")
        }), i.drag && i.elem.on("dragover", function (e) {
            e.preventDefault(), t(this).trigger("upload.over")
        }).on("dragleave", function (e) {
            t(this).trigger("upload.leave")
        }).on("drop", function (e) {
            e.preventDefault(), t(this).trigger("upload.drop", e)
        }), i.bindAction.on("click", function () {
            t(this).trigger("upload.action")
        }), i.elem.data("haveEvents", !0))
    }, o.render = function (e) {
        var t = new p(e);
        return l.call(t)
    }, e(r, o)
});
layui.define("layer", function (e) {
    "use strict";
    var t = layui.$, i = layui.layer, a = layui.hint(), n = layui.device(), l = "form", r = ".layui-form", s = "layui-this", o = "layui-hide", u = "layui-disabled", c = function () {
        this.config = {
            verify: {
                required: [/[\S]+/, "必填项不能为空"],
                phone: [/^1\d{10}$/, "请输入正确的手机号"],
                email: [/^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/, "邮箱格式不正确"],
                url: [/(^#)|(^http(s*):\/\/[^\s]+\.[^\s]+)/, "链接格式不正确"],
                number: function (e) {
                    if (!e || isNaN(e))return "只能填写数字"
                },
                date: [/^(\d{4})[-\/](\d{1}|0\d{1}|1[0-2])([-\/](\d{1}|0\d{1}|[1-2][0-9]|3[0-1]))*$/, "日期格式不正确"],
                identity: [/(^\d{15}$)|(^\d{17}(x|X|\d)$)/, "请输入正确的身份证号"]
            }
        }
    };
    c.prototype.set = function (e) {
        var i = this;
        return t.extend(!0, i.config, e), i
    }, c.prototype.verify = function (e) {
        var i = this;
        return t.extend(!0, i.config.verify, e), i
    }, c.prototype.on = function (e, t) {
        return layui.onevent.call(this, l, e, t)
    }, c.prototype.render = function (e, i) {
        var n = this, c = t(r + function () {
                return i ? '[lay-filter="' + i + '"]' : ""
            }()), d = {
            select: function () {
                var e, i = "请选择", a = "layui-form-select", n = "layui-select-title", r = "layui-select-none", d = "", f = c.find("select"), y = function (i, l) {
                    t(i.target).parent().hasClass(n) && !l || (t("." + a).removeClass(a + "ed " + a + "up"), e && d && e.val(d)), e = null
                }, h = function (i, c, f) {
                    var h = t(this), p = i.find("." + n), m = p.find("input"), k = i.find("dl"), g = k.children("dd");
                    if (!c) {
                        var x = function () {
                            var e = i.offset().top + i.outerHeight() + 5 - v.scrollTop(), t = k.outerHeight();
                            i.addClass(a + "ed"), g.removeClass(o), e + t > v.height() && e >= t && i.addClass(a + "up")
                        }, b = function (e) {
                            i.removeClass(a + "ed " + a + "up"), m.blur(), e || C(m.val(), function (e) {
                                e && (d = k.find("." + s).html(), m && m.val(d))
                            })
                        };
                        p.on("click", function (e) {
                            i.hasClass(a + "ed") ? b() : (y(e, !0), x()), k.find("." + r).remove()
                        }), p.find(".layui-edge").on("click", function () {
                            m.focus()
                        }), m.on("keyup", function (e) {
                            var t = e.keyCode;
                            9 === t && x()
                        }).on("keydown", function (e) {
                            var t = e.keyCode;
                            9 === t ? b() : 13 === t && e.preventDefault()
                        });
                        var C = function (e, i, a) {
                            var n = 0;
                            layui.each(g, function () {
                                var i = t(this), l = i.text(), r = l.indexOf(e) === -1;
                                ("" === e || "blur" === a ? e !== l : r) && n++, "keyup" === a && i[r ? "addClass" : "removeClass"](o)
                            });
                            var l = n === g.length;
                            return i(l), l
                        }, w = function (e) {
                            var t = this.value, i = e.keyCode;
                            return 9 !== i && 13 !== i && 37 !== i && 38 !== i && 39 !== i && 40 !== i && (C(t, function (e) {
                                    e ? k.find("." + r)[0] || k.append('<p class="' + r + '">无匹配项</p>') : k.find("." + r).remove()
                                }, "keyup"), void("" === t && k.find("." + r).remove()))
                        };
                        f && m.on("keyup", w).on("blur", function (t) {
                            e = m, d = k.find("." + s).html(), setTimeout(function () {
                                C(m.val(), function (e) {
                                    d || m.val("")
                                }, "blur")
                            }, 200)
                        }), g.on("click", function () {
                            var e = t(this), a = e.attr("lay-value"), n = h.attr("lay-filter");
                            return !e.hasClass(u) && (e.hasClass("layui-select-tips") ? m.val("") : (m.val(e.text()), e.addClass(s)), e.siblings().removeClass(s), h.val(a).removeClass("layui-form-danger"), layui.event.call(this, l, "select(" + n + ")", {
                                    elem: h[0],
                                    value: a,
                                    othis: i
                                }), b(!0), !1)
                        }), i.find("dl>dt").on("click", function (e) {
                            return !1
                        }), t(document).off("click", y).on("click", y)
                    }
                };
                f.each(function (e, l) {
                    var r = t(this), o = r.next("." + a), c = this.disabled, d = l.value, f = t(l.options[l.selectedIndex]), y = l.options[0];
                    if ("string" == typeof r.attr("lay-ignore"))return r.show();
                    var v = "string" == typeof r.attr("lay-search"), p = y ? y.value ? i : y.innerHTML || i : i, m = t(['<div class="' + (v ? "" : "layui-unselect ") + a + (c ? " layui-select-disabled" : "") + '">', '<div class="' + n + '"><input type="text" placeholder="' + p + '" value="' + (d ? f.html() : "") + '" ' + (v ? "" : "readonly") + ' class="layui-input' + (v ? "" : " layui-unselect") + (c ? " " + u : "") + '">', '<i class="layui-edge"></i></div>', '<dl class="layui-anim layui-anim-upbit' + (r.find("optgroup")[0] ? " layui-select-group" : "") + '">' + function (e) {
                        var t = [];
                        return layui.each(e, function (e, a) {
                            0 !== e || a.value ? "optgroup" === a.tagName.toLowerCase() ? t.push("<dt>" + a.label + "</dt>") : t.push('<dd lay-value="' + a.value + '" class="' + (d === a.value ? s : "") + (a.disabled ? " " + u : "") + '">' + a.innerHTML + "</dd>") : t.push('<dd lay-value="" class="layui-select-tips">' + (a.innerHTML || i) + "</dd>")
                        }), 0 === t.length && t.push('<dd lay-value="" class="' + u + '">没有选项</dd>'), t.join("")
                    }(r.find("*")) + "</dl>", "</div>"].join(""));
                    o[0] && o.remove(), r.after(m), h.call(this, m, c, v)
                })
            }, checkbox: function () {
                var e = {
                    checkbox: ["layui-form-checkbox", "layui-form-checked", "checkbox"],
                    _switch: ["layui-form-switch", "layui-form-onswitch", "switch"]
                }, i = c.find("input[type=checkbox]"), a = function (e, i) {
                    var a = t(this);
                    e.on("click", function () {
                        var t = a.attr("lay-filter"), n = (a.attr("lay-text") || "").split("|");
                        a[0].disabled || (a[0].checked ? (a[0].checked = !1, e.removeClass(i[1]).find("em").text(n[1])) : (a[0].checked = !0, e.addClass(i[1]).find("em").text(n[0])), layui.event.call(a[0], l, i[2] + "(" + t + ")", {
                            elem: a[0],
                            value: a[0].value,
                            othis: e
                        }))
                    })
                };
                i.each(function (i, n) {
                    var l = t(this), r = l.attr("lay-skin"), s = (l.attr("lay-text") || "").split("|"), o = this.disabled;
                    "switch" === r && (r = "_" + r);
                    var c = e[r] || e.checkbox;
                    if ("string" == typeof l.attr("lay-ignore"))return l.show();
                    var d = l.next("." + c[0]), f = t(['<div class="layui-unselect ' + c[0] + (n.checked ? " " + c[1] : "") + (o ? " layui-checkbox-disbaled " + u : "") + '" lay-skin="' + (r || "") + '">', {_switch: "<em>" + ((n.checked ? s[0] : s[1]) || "") + "</em><i></i>"}[r] || (n.title.replace(/\s/g, "") ? "<span>" + n.title + "</span>" : "") + '<i class="layui-icon">' + (r ? "&#xe605;" : "&#xe618;") + "</i>", "</div>"].join(""));
                    d[0] && d.remove(), l.after(f), a.call(this, f, c)
                })
            }, radio: function () {
                var e = "layui-form-radio", i = ["&#xe643;", "&#xe63f;"], a = c.find("input[type=radio]"), n = function (a) {
                    var n = t(this), s = "layui-anim-scaleSpring";
                    a.on("click", function () {
                        var o = n[0].name, u = n.parents(r), c = n.attr("lay-filter"), d = u.find("input[name=" + o.replace(/(\.|#|\[|\])/g, "\\$1") + "]");
                        n[0].disabled || (layui.each(d, function () {
                            var a = t(this).next("." + e);
                            this.checked = !1, a.removeClass(e + "ed"), a.find(".layui-icon").removeClass(s).html(i[1])
                        }), n[0].checked = !0, a.addClass(e + "ed"), a.find(".layui-icon").addClass(s).html(i[0]), layui.event.call(n[0], l, "radio(" + c + ")", {
                            elem: n[0],
                            value: n[0].value,
                            othis: a
                        }))
                    })
                };
                a.each(function (a, l) {
                    var r = t(this), s = r.next("." + e), o = this.disabled;
                    if ("string" == typeof r.attr("lay-ignore"))return r.show();
                    s[0] && s.remove();
                    var c = t(['<div class="layui-unselect ' + e + (l.checked ? " " + e + "ed" : "") + (o ? " layui-radio-disbaled " + u : "") + '">', '<i class="layui-anim layui-icon">' + i[l.checked ? 0 : 1] + "</i>", "<div>" + function () {
                        var e = l.title || "";
                        return "string" == typeof r.next().attr("lay-radio") && (e = r.next().html(), r.next().remove()), e
                    }() + "</div>", "</div>"].join(""));
                    r.after(c), n.call(this, c)
                })
            }
        };
        return e ? d[e] ? d[e]() : a.error("不支持的" + e + "表单渲染") : layui.each(d, function (e, t) {
            t()
        }), n
    };
    var d = function () {
        var e = t(this), a = f.config.verify, s = null, o = "layui-form-danger", u = {}, c = e.parents(r), d = c.find("*[lay-verify]"), y = e.parents("form")[0], v = c.find("input,select,textarea"), h = e.attr("lay-filter");
        if (layui.each(d, function (e, l) {
                var r = t(this), u = r.attr("lay-verify").split("|"), c = r.attr("lay-verType"), d = r.val();
                if (r.removeClass(o), layui.each(u, function (e, t) {
                        var u, f = "", y = "function" == typeof a[t];
                        if (a[t]) {
                            var u = y ? f = a[t](d, l) : !a[t][0].test(d);
                            if (f = f || a[t][1], u)return "tips" === c ? i.tips(f, function () {
                                return "string" == typeof r.attr("lay-ignore") || "select" !== l.tagName.toLowerCase() && !/^checkbox|radio$/.test(l.type) ? r : r.next()
                            }(), {tips: 1}) : "alert" === c ? i.alert(f, {
                                title: "提示",
                                shadeClose: !0
                            }) : i.msg(f, {icon: 5, shift: 6}), n.android || n.ios || l.focus(), r.addClass(o), s = !0
                        }
                    }), s)return s
            }), s)return !1;
        var p = {};
        return layui.each(v, function (e, t) {
            if (t.name = (t.name || "").replace(/^\s*|\s*&/, ""), t.name) {
                if (/^.*\[\]$/.test(t.name)) {
                    var i = t.name.match(/^(.*)\[\]$/g)[0];
                    p[i] = 0 | p[i], t.name = t.name.replace(/^(.*)\[\]$/, "$1[" + p[i]++ + "]")
                }
            /^
                checkbox | radio$ /
            .
                test(t.type) && !t.checked || (u[t.name] = t.value)
            }
        }), layui.event.call(this, l, "submit(" + h + ")", {elem: this, form: y, field: u})
    }, f = new c, y = t(document), v = t(window);
    f.render(), y.on("reset", r, function () {
        var e = t(this).attr("lay-filter");
        setTimeout(function () {
            f.render(null, e)
        }, 50)
    }), y.on("submit", r, d).on("click", "*[lay-submit]", d), e(l, f)
});
layui.define("jquery", function (e) {
    "use strict";
    var o = layui.$, a = layui.hint(), i = "layui-tree-enter", r = function (e) {
        this.options = e
    }, t = {
        arrow: ["&#xe623;", "&#xe625;"],
        checkbox: ["&#xe626;", "&#xe627;"],
        radio: ["&#xe62b;", "&#xe62a;"],
        branch: ["&#xe622;", "&#xe624;"],
        leaf: "&#xe621;"
    };
    r.prototype.init = function (e) {
        var o = this;
        e.addClass("layui-box layui-tree"), o.options.skin && e.addClass("layui-tree-skin-" + o.options.skin), o.tree(e), o.on(e)
    }, r.prototype.tree = function (e, a) {
        var i = this, r = i.options, n = a || r.nodes;
        layui.each(n, function (a, n) {
            var l = n.children && n.children.length > 0, c = o('<ul class="' + (n.spread ? "layui-show" : "") + '"></ul>'), s = o(["<li " + (n.spread ? 'data-spread="' + n.spread + '"' : "") + ">", function () {
                return l ? '<i class="layui-icon layui-tree-spread">' + (n.spread ? t.arrow[1] : t.arrow[0]) + "</i>" : ""
            }(), function () {
                return r.check ? '<i class="layui-icon layui-tree-check">' + ("checkbox" === r.check ? t.checkbox[0] : "radio" === r.check ? t.radio[0] : "") + "</i>" : ""
            }(), function () {
                return '<a href="' + (n.href || "javascript:;") + '" ' + (r.target && n.href ? 'target="' + r.target + '"' : "") + ">" + ('<i class="layui-icon layui-tree-' + (l ? "branch" : "leaf") + '">' + (l ? n.spread ? t.branch[1] : t.branch[0] : t.leaf) + "</i>") + ("<cite>" + (n.name || "未命名") + "</cite></a>")
            }(), "</li>"].join(""));
            l && (s.append(c), i.tree(c, n.children)), e.append(s), "function" == typeof r.click && i.click(s, n), i.spread(s, n), r.drag && i.drag(s, n)
        })
    }, r.prototype.click = function (e, o) {
        var a = this, i = a.options;
        e.children("a").on("click", function (e) {
            layui.stope(e), i.click(o)
        })
    }, r.prototype.spread = function (e, o) {
        var a = this, i = (a.options, e.children(".layui-tree-spread")), r = e.children("ul"), n = e.children("a"), l = function () {
            e.data("spread") ? (e.data("spread", null), r.removeClass("layui-show"), i.html(t.arrow[0]), n.find(".layui-icon").html(t.branch[0])) : (e.data("spread", !0), r.addClass("layui-show"), i.html(t.arrow[1]), n.find(".layui-icon").html(t.branch[1]))
        };
        r[0] && (i.on("click", l), n.on("dblclick", l))
    }, r.prototype.on = function (e) {
        var a = this, r = a.options, t = "layui-tree-drag";
        e.find("i").on("selectstart", function (e) {
            return !1
        }), r.drag && o(document).on("mousemove", function (e) {
            var i = a.move;
            if (i.from) {
                var r = (i.to, o('<div class="layui-box ' + t + '"></div>'));
                e.preventDefault(), o("." + t)[0] || o("body").append(r);
                var n = o("." + t)[0] ? o("." + t) : r;
                n.addClass("layui-show").html(i.from.elem.children("a").html()), n.css({
                    left: e.pageX + 10,
                    top: e.pageY + 10
                })
            }
        }).on("mouseup", function () {
            var e = a.move;
            e.from && (e.from.elem.children("a").removeClass(i), e.to && e.to.elem.children("a").removeClass(i), a.move = {}, o("." + t).remove())
        })
    }, r.prototype.move = {}, r.prototype.drag = function (e, a) {
        var r = this, t = (r.options, e.children("a")), n = function () {
            var t = o(this), n = r.move;
            n.from && (n.to = {item: a, elem: e}, t.addClass(i))
        };
        t.on("mousedown", function () {
            var o = r.move;
            o.from = {item: a, elem: e}
        }), t.on("mouseenter", n).on("mousemove", n).on("mouseleave", function () {
            var e = o(this), a = r.move;
            a.from && (delete a.to, e.removeClass(i))
        })
    }, e("tree", function (e) {
        var i = new r(e = e || {}), t = o(e.elem);
        return t[0] ? void i.init(t) : a.error("layui.tree 没有找到" + e.elem + "元素")
    })
});
layui.define(["laytpl", "laypage", "layer", "form"], function (e) {
    "use strict";
    var t = layui.$, i = layui.laytpl, a = layui.laypage, l = layui.layer, n = layui.form, o = layui.hint(), r = layui.device(), d = {
        config: {
            checkName: "LAY_CHECKED",
            indexName: "LAY_TABLE_INDEX"
        }, cache: {}, index: layui.table ? layui.table.index + 1e4 : 0, set: function (e) {
            var i = this;
            return i.config = t.extend({}, i.config, e), i
        }, on: function (e, t) {
            return layui.onevent.call(this, s, e, t)
        }
    }, c = function () {
        var e = this, t = e.config, i = t.id;
        return i && (c.config[i] = t), {
            reload: function (t) {
                e.reload.call(e, t)
            }, config: t
        }
    }, s = "table", u = ".layui-table", h = "layui-hide", f = "layui-none", y = "layui-table-view", p = ".layui-table-header", m = ".layui-table-body", v = ".layui-table-main", g = ".layui-table-fixed", x = ".layui-table-fixed-l", b = ".layui-table-fixed-r", k = ".layui-table-tool", C = ".layui-table-page", w = ".layui-table-sort", N = "layui-table-edit", T = "layui-table-hover", F = function (e) {
        var t = '{{#if(item2.colspan){}} colspan="{{item2.colspan}}"{{#} if(item2.rowspan){}} rowspan="{{item2.rowspan}}"{{#}}}';
        return e = e || {}, ['<table cellspacing="0" cellpadding="0" border="0" class="layui-table" ', '{{# if(d.data.skin){ }}lay-skin="{{d.data.skin}}"{{# } }} {{# if(d.data.size){ }}lay-size="{{d.data.size}}"{{# } }} {{# if(d.data.even){ }}lay-even{{# } }}>', "<thead>", "{{# layui.each(d.data.cols, function(i1, item1){ }}", "<tr>", "{{# layui.each(item1, function(i2, item2){ }}", '{{# if(item2.fixed && item2.fixed !== "right"){ left = true; } }}', '{{# if(item2.fixed === "right"){ right = true; } }}', function () {
            return e.fixed && "right" !== e.fixed ? '{{# if(item2.fixed && item2.fixed !== "right"){ }}' : "right" === e.fixed ? '{{# if(item2.fixed === "right"){ }}' : ""
        }(), '<th data-field="{{ item2.field||i2 }}" {{# if(item2.minWidth){ }}data-minwidth="{{item2.minWidth}}"{{# } }} ' + t + ' {{# if(item2.unresize){ }}data-unresize="true"{{# } }}>', '<div class="layui-table-cell laytable-cell-', "{{# if(item2.colspan > 1){ }}", "group", "{{# } else { }}", "{{d.index}}-{{item2.field || i2}}", '{{# if(item2.type !== "normal"){ }}', " laytable-cell-{{ item2.type }}", "{{# } }}", "{{# } }}", '" {{#if(item2.align){}}align="{{item2.align}}"{{#}}}>', '{{# if(item2.type === "checkbox"){ }}', '<input type="checkbox" name="layTableCheckbox" lay-skin="primary" lay-filter="layTableAllChoose" {{# if(item2[d.data.checkName]){ }}checked{{# }; }}>', "{{# } else { }}", '<span>{{item2.title||""}}</span>', "{{# if(!(item2.colspan > 1) && item2.sort){ }}", '<span class="layui-table-sort layui-inline"><i class="layui-edge layui-table-sort-asc"></i><i class="layui-edge layui-table-sort-desc"></i></span>', "{{# } }}", "{{# } }}", "</div>", "</th>", e.fixed ? "{{# }; }}" : "", "{{# }); }}", "</tr>", "{{# }); }}", "</thead>", "</table>"].join("")
    }, W = ['<table cellspacing="0" cellpadding="0" border="0" class="layui-table" ', '{{# if(d.data.skin){ }}lay-skin="{{d.data.skin}}"{{# } }} {{# if(d.data.size){ }}lay-size="{{d.data.size}}"{{# } }} {{# if(d.data.even){ }}lay-even{{# } }}>', "<tbody></tbody>", "</table>"].join(""), z = ['<div class="layui-form layui-border-box {{d.VIEW_CLASS}}" lay-filter="LAY-table-{{d.index}}" style="{{# if(d.data.width){ }}width:{{d.data.width}}px;{{# } }} {{# if(d.data.height){ }}height:{{d.data.height}}px;{{# } }}">', "{{# if(d.data.toolbar){ }}", '<div class="layui-table-tool"></div>', "{{# } }}", '<div class="layui-table-box">', "{{# var left, right; }}", '<div class="layui-table-header">', F(), "</div>", '<div class="layui-table-body layui-table-main">', W, "</div>", "{{# if(left){ }}", '<div class="layui-table-fixed layui-table-fixed-l">', '<div class="layui-table-header">', F({fixed: !0}), "</div>", '<div class="layui-table-body">', W, "</div>", "</div>", "{{# }; }}", "{{# if(right){ }}", '<div class="layui-table-fixed layui-table-fixed-r">', '<div class="layui-table-header">', F({fixed: "right"}), '<div class="layui-table-mend"></div>', "</div>", '<div class="layui-table-body">', W, "</div>", "</div>", "{{# }; }}", "</div>", "{{# if(d.data.page){ }}", '<div class="layui-table-page">', '<div id="layui-table-page{{d.index}}"></div>', "</div>", "{{# } }}", "<style>", "{{# layui.each(d.data.cols, function(i1, item1){", "layui.each(item1, function(i2, item2){ }}", ".laytable-cell-{{d.index}}-{{item2.field||i2}}{ ", "{{# if(item2.width){ }}", "width: {{item2.width}}px;", "{{# } }}", " }", "{{# });", "}); }}", "</style>", "</div>"].join(""), A = t(window), S = t(document), M = function (e) {
        var i = this;
        i.index = ++d.index, i.config = t.extend({}, i.config, d.config, e), i.render()
    };
    M.prototype.config = {
        limit: 10,
        loading: !0,
        cellMinWidth: 60,
        text: {none: "无数据"}
    }, M.prototype.render = function () {
        var e = this, a = e.config;
        if (a.elem = t(a.elem), a.where = a.where || {}, a.id = a.id || a.elem.attr("id"), a.request = t.extend({
                pageName: "page",
                limitName: "limit"
            }, a.request), a.response = t.extend({
                statusName: "code",
                statusCode: 0,
                msgName: "msg",
                dataName: "data",
                countName: "count"
            }, a.response), "object" == typeof a.page && (a.limit = a.page.limit || a.limit, a.limits = a.page.limits || a.limits, e.page = a.page.curr = a.page.curr || 1, delete a.page.elem, delete a.page.jump), !a.elem[0])return e;
        e.setArea();
        var l = a.elem, n = l.next("." + y), o = e.elem = t(i(z).render({VIEW_CLASS: y, data: a, index: e.index}));
        if (a.index = e.index, n[0] && n.remove(), l.after(o), e.layHeader = o.find(p), e.layMain = o.find(v), e.layBody = o.find(m), e.layFixed = o.find(g), e.layFixLeft = o.find(x), e.layFixRight = o.find(b), e.layTool = o.find(k), e.layPage = o.find(C), e.layTool.html(i(t(a.toolbar).html() || "").render(a)), a.height && e.fullSize(), a.cols.length > 1) {
            var r = e.layFixed.find(p).find("th");
            r.height(e.layHeader.height() - 1 - parseFloat(r.css("padding-top")) - parseFloat(r.css("padding-bottom")))
        }
        e.pullData(e.page), e.events()
    }, M.prototype.initOpts = function (e) {
        var t = this, i = (t.config, {checkbox: 48, space: 15, numbers: 40});
        e.checkbox && (e.type = "checkbox"), e.space && (e.type = "space"), e.type || (e.type = "normal"), "normal" !== e.type && (e.unresize = !0, e.width = e.width || i[e.type])
    }, M.prototype.setArea = function () {
        var e = this, t = e.config, i = 0, a = 0, l = 0, n = 0, o = t.width || function () {
                var e = function (i) {
                    var a, l;
                    i = i || t.elem.parent(), a = i.width();
                    try {
                        l = "none" === i.css("display")
                    } catch (n) {
                    }
                    return !i[0] || a && !l ? a : e(i.parent())
                };
                return e()
            }();
        e.eachCols(function () {
            i++
        }), o -= function () {
            return "line" === t.skin || "nob" === t.skin ? 2 : i + 1
        }(), layui.each(t.cols, function (t, i) {
            layui.each(i, function (t, l) {
                var r;
                return l ? (e.initOpts(l), r = l.width || 0, void(l.colspan > 1 || (/\d+%$/.test(r) ? l.width = r = Math.floor(parseFloat(r) / 100 * o) : r || (l.width = r = 0, a++), n += r))) : void i.splice(t, 1)
            })
        }), e.autoColNums = a, o > n && a && (l = (o - n) / a), layui.each(t.cols, function (e, i) {
            layui.each(i, function (e, i) {
                var a = i.minWidth || t.cellMinWidth;
                i.colspan > 1 || 0 === i.width && (i.width = Math.floor(l >= a ? l : a))
            })
        }), t.height && /^full-\d+$/.test(t.height) && (e.fullHeightGap = t.height.split("-")[1], t.height = A.height() - e.fullHeightGap)
    }, M.prototype.reload = function (e) {
        var i = this;
        i.config.data && i.config.data.constructor === Array && delete i.config.data, i.config = t.extend({}, i.config, e), i.render()
    }, M.prototype.page = 1, M.prototype.pullData = function (e, i) {
        var a = this, n = a.config, o = n.request, r = n.response, d = function () {
            "object" == typeof n.initSort && a.sort(n.initSort.field, n.initSort.type)
        };
        if (a.startTime = (new Date).getTime(), n.url) {
            var c = {};
            c[o.pageName] = e, c[o.limitName] = n.limit;
            var s = t.extend(c, n.where);
            n.contentType && 0 == n.contentType.indexOf("application/json") && (s = JSON.stringify(s)), t.ajax({
                type: n.method || "get",
                url: n.url,
                contentType: n.contentType,
                data: s,
                dataType: "json",
                headers: n.headers || {},
                success: function (t) {
                    t[r.statusName] != r.statusCode ? (a.renderForm(), a.layMain.html('<div class="' + f + '">' + (t[r.msgName] || "返回的数据状态异常") + "</div>")) : (a.renderData(t, e, t[r.countName]), d(), n.time = (new Date).getTime() - a.startTime + " ms"), i && l.close(i), "function" == typeof n.done && n.done(t, e, t[r.countName])
                },
                error: function (e, t) {
                    a.layMain.html('<div class="' + f + '">数据接口请求异常</div>'), a.renderForm(), i && l.close(i)
                }
            })
        } else if (n.data && n.data.constructor === Array) {
            var u = {}, h = e * n.limit - n.limit;
            u[r.dataName] = n.data.concat().splice(h, n.limit), u[r.countName] = n.data.length, a.renderData(u, e, n.data.length), d(), "function" == typeof n.done && n.done(u, e, u[r.countName])
        }
    }, M.prototype.eachCols = function (e) {
        var i = t.extend(!0, [], this.config.cols), a = [], l = 0;
        layui.each(i, function (e, t) {
            layui.each(t, function (t, n) {
                if (n.colspan > 1) {
                    var o = 0;
                    l++, n.CHILD_COLS = [], layui.each(i[e + 1], function (e, t) {
                        t.PARENT_COL || o == n.colspan || (t.PARENT_COL = l, n.CHILD_COLS.push(t), o += t.colspan > 1 ? t.colspan : 1)
                    })
                }
                n.PARENT_COL || a.push(n)
            })
        });
        var n = function (t) {
            layui.each(t || a, function (t, i) {
                return i.CHILD_COLS ? n(i.CHILD_COLS) : void e(t, i)
            })
        };
        n()
    }, M.prototype.renderData = function (e, n, o, r) {
        var c = this, s = c.config, u = e[s.response.dataName] || [], y = [], p = [], m = [], v = function () {
            return !r && c.sortKey ? c.sort(c.sortKey.field, c.sortKey.sort, !0) : (layui.each(u, function (e, a) {
                var l = [], o = [], u = [], h = e + s.limit * (n - 1) + 1;
                0 !== a.length && (r || (a[d.config.indexName] = e), c.eachCols(function (e, n) {
                    var r = n.field || e, f = a[r];
                    c.getColElem(c.layHeader, r);
                    if (void 0 !== f && null !== f || (f = ""), !(n.colspan > 1)) {
                        var y = ['<td data-field="' + r + '" ' + function () {
                            var e = [];
                            return n.edit && e.push('data-edit="' + n.edit + '"'), n.align && e.push('align="' + n.align + '"'), n.templet && e.push('data-content="' + f + '"'), n.toolbar && e.push('data-off="true"'), n.event && e.push('lay-event="' + n.event + '"'), n.style && e.push('style="' + n.style + '"'), n.minWidth && e.push('data-minwidth="' + n.minWidth + '"'), e.join(" ")
                        }() + ">", '<div class="layui-table-cell laytable-cell-' + function () {
                            var e = s.index + "-" + r;
                            return "normal" === n.type ? e : e + " laytable-cell-" + n.type
                        }() + '">' + function () {
                            var e = t.extend(!0, {LAY_INDEX: h}, a);
                            return "checkbox" === n.type ? '<input type="checkbox" name="layTableCheckbox" lay-skin="primary" ' + function () {
                                var t = d.config.checkName;
                                return n[t] ? (a[t] = n[t], n[t] ? "checked" : "") : e[t] ? "checked" : ""
                            }() + ">" : "numbers" === n.type ? h : n.toolbar ? i(t(n.toolbar).html() || "").render(e) : n.templet ? function () {
                                return "function" == typeof n.templet ? n.templet(e) : i(t(n.templet).html() || String(f)).render(e)
                            }() : f
                        }(), "</div></td>"].join("");
                        l.push(y), n.fixed && "right" !== n.fixed && o.push(y), "right" === n.fixed && u.push(y)
                    }
                }), y.push('<tr data-index="' + e + '">' + l.join("") + "</tr>"), p.push('<tr data-index="' + e + '">' + o.join("") + "</tr>"), m.push('<tr data-index="' + e + '">' + u.join("") + "</tr>"))
            }), c.layBody.scrollTop(0), c.layMain.find("." + f).remove(), c.layMain.find("tbody").html(y.join("")), c.layFixLeft.find("tbody").html(p.join("")), c.layFixRight.find("tbody").html(m.join("")), c.renderForm(), c.syncCheckAll(), c.haveInit ? c.scrollPatch() : setTimeout(function () {
                c.scrollPatch()
            }, 50), c.haveInit = !0, void l.close(c.tipsIndex))
        };
        return c.key = s.id || s.index, d.cache[c.key] = u, c.layPage[0 === u.length && 1 == n ? "addClass" : "removeClass"](h), r ? v() : 0 === u.length ? (c.renderForm(), c.layFixed.remove(), c.layMain.find("tbody").html(""), c.layMain.find("." + f).remove(), c.layMain.append('<div class="' + f + '">' + s.text.none + "</div>")) : (v(), void(s.page && (s.page = t.extend({
            elem: "layui-table-page" + s.index,
            count: o,
            limit: s.limit,
            limits: s.limits || [10, 20, 30, 40, 50, 60, 70, 80, 90],
            groups: 3,
            layout: ["prev", "page", "next", "skip", "count", "limit"],
            prev: '<i class="layui-icon">&#xe603;</i>',
            next: '<i class="layui-icon">&#xe602;</i>',
            jump: function (e, t) {
                t || (c.page = e.curr, s.limit = e.limit, c.pullData(e.curr, c.loading()))
            }
        }, s.page), s.page.count = o, a.render(s.page))))
    }, M.prototype.getColElem = function (e, t) {
        var i = this, a = i.config;
        return e.eq(0).find(".laytable-cell-" + (a.index + "-" + t) + ":eq(0)")
    }, M.prototype.renderForm = function (e) {
        n.render(e, "LAY-table-" + this.index)
    }, M.prototype.sort = function (e, i, a, l) {
        var n, r, c = this, u = {}, h = c.config, f = h.elem.attr("lay-filter"), y = d.cache[c.key];
        "string" == typeof e && c.layHeader.find("th").each(function (i, a) {
            var l = t(this), o = l.data("field");
            if (o === e)return e = l, n = o, !1
        });
        try {
            var n = n || e.data("field");
            if (c.sortKey && !a && n === c.sortKey.field && i === c.sortKey.sort)return;
            var p = c.layHeader.find("th .laytable-cell-" + h.index + "-" + n).find(w);
            c.layHeader.find("th").find(w).removeAttr("lay-sort"), p.attr("lay-sort", i || null), c.layFixed.find("th")
        } catch (m) {
            return o.error("Table modules: Did not match to field")
        }
        c.sortKey = {
            field: n,
            sort: i
        }, "asc" === i ? r = layui.sort(y, n) : "desc" === i ? r = layui.sort(y, n, !0) : (r = layui.sort(y, d.config.indexName), delete c.sortKey), u[h.response.dataName] = r, c.renderData(u, c.page, c.count, !0), l && layui.event.call(e, s, "sort(" + f + ")", {
            field: n,
            type: i
        })
    }, M.prototype.loading = function () {
        var e = this, t = e.config;
        if (t.loading && t.url)return l.msg("数据请求中", {
            icon: 16,
            offset: [e.elem.offset().top + e.elem.height() / 2 - 35 - A.scrollTop() + "px", e.elem.offset().left + e.elem.width() / 2 - 90 - A.scrollLeft() + "px"],
            time: -1,
            anim: -1,
            fixed: !1
        })
    }, M.prototype.setCheckData = function (e, t) {
        var i = this, a = i.config, l = d.cache[i.key];
        l[e] && l[e].constructor !== Array && (l[e][a.checkName] = t)
    }, M.prototype.syncCheckAll = function () {
        var e = this, t = e.config, i = e.layHeader.find('input[name="layTableCheckbox"]'), a = function (i) {
            return e.eachCols(function (e, a) {
                "checkbox" === a.type && (a[t.checkName] = i)
            }), i
        };
        i[0] && (d.checkStatus(e.key).isAll ? (i[0].checked || (i.prop("checked", !0), e.renderForm("checkbox")), a(!0)) : (i[0].checked && (i.prop("checked", !1), e.renderForm("checkbox")), a(!1)))
    }, M.prototype.getCssRule = function (e, t) {
        var i = this, a = i.elem.find("style")[0], l = a.sheet || a.styleSheet || {}, n = l.cssRules || l.rules;
        layui.each(n, function (a, l) {
            if (l.selectorText === ".laytable-cell-" + i.index + "-" + e)return t(l), !0
        })
    }, M.prototype.fullSize = function () {
        var e, t = this, i = t.config, a = i.height;
        t.fullHeightGap && (a = A.height() - t.fullHeightGap, a < 135 && (a = 135), t.elem.css("height", a)), e = parseFloat(a) - parseFloat(t.layHeader.height()) - 1, i.toolbar && (e -= t.layTool.outerHeight()), i.page && (e = e - t.layPage.outerHeight() - 1), t.layMain.css("height", e)
    }, M.prototype.getScrollWidth = function (e) {
        var t = 0;
        return e ? t = e.offsetWidth - e.clientWidth : (e = document.createElement("div"), e.style.width = "100px", e.style.height = "100px", e.style.overflowY = "scroll", document.body.appendChild(e), t = e.offsetWidth - e.clientWidth, document.body.removeChild(e)), t
    }, M.prototype.scrollPatch = function () {
        var e = this, i = e.layMain.children("table"), a = e.layMain.width() - e.layMain.prop("clientWidth"), l = e.layMain.height() - e.layMain.prop("clientHeight"), n = e.getScrollWidth(e.layMain[0]), o = i.outerWidth() - e.layMain.width();
        if (e.autoColNums && o < 5 && !e.scrollPatchWStatus) {
            var r = e.layHeader.eq(0).find("thead th:last-child"), d = r.data("field");
            e.getCssRule(d, function (t) {
                var i = t.style.width || r.outerWidth();
                t.style.width = parseFloat(i) - n - o + "px", e.layMain.height() - e.layMain.prop("clientHeight") > 0 && (t.style.width = parseFloat(t.style.width) - 1 + "px"), e.scrollPatchWStatus = !0
            })
        }
        if (a && l) {
            if (!e.elem.find(".layui-table-patch")[0]) {
                var c = t('<th class="layui-table-patch"><div class="layui-table-cell"></div></th>');
                c.find("div").css({width: a}), e.layHeader.eq(0).find("thead tr").append(c)
            }
        } else e.layHeader.eq(0).find(".layui-table-patch").remove();
        var s = e.layMain.height(), u = s - l;
        e.layFixed.find(m).css("height", i.height() > u ? u : "auto"), e.layFixRight[o > 0 ? "removeClass" : "addClass"](h), e.layFixRight.css("right", a - 1)
    }, M.prototype.events = function () {
        var e, a = this, n = a.config, o = t("body"), c = {}, u = a.layHeader.find("th"), h = ".layui-table-cell", f = n.elem.attr("lay-filter");
        u.on("mousemove", function (e) {
            var i = t(this), a = i.offset().left, l = e.clientX - a;
            i.attr("colspan") > 1 || i.data("unresize") || c.resizeStart || (c.allowResize = i.width() - l <= 10, o.css("cursor", c.allowResize ? "col-resize" : ""))
        }).on("mouseleave", function () {
            t(this);
            c.resizeStart || o.css("cursor", "")
        }).on("mousedown", function (e) {
            var i = t(this);
            if (c.allowResize) {
                var l = i.data("field");
                e.preventDefault(), c.resizeStart = !0, c.offset = [e.clientX, e.clientY], a.getCssRule(l, function (e) {
                    var t = e.style.width || i.outerWidth();
                    c.rule = e, c.ruleWidth = parseFloat(t), c.minWidth = i.data("minwidth") || n.cellMinWidth
                })
            }
        }), S.on("mousemove", function (t) {
            if (c.resizeStart) {
                if (t.preventDefault(), c.rule) {
                    var i = c.ruleWidth + t.clientX - c.offset[0];
                    i < c.minWidth && (i = c.minWidth), c.rule.style.width = i + "px", l.close(a.tipsIndex)
                }
                e = 1
            }
        }).on("mouseup", function (t) {
            c.resizeStart && (c = {}, o.css("cursor", ""), a.scrollPatch()), 2 === e && (e = null)
        }), u.on("click", function () {
            var i, l = t(this), n = l.find(w), o = n.attr("lay-sort");
            return n[0] && 1 !== e ? (i = "asc" === o ? "desc" : "desc" === o ? null : "asc", void a.sort(l, i, null, !0)) : e = 2
        }).find(w + " .layui-edge ").on("click", function (e) {
            var i = t(this), l = i.index(), n = i.parents("th").eq(0).data("field");
            layui.stope(e), 0 === l ? a.sort(n, "asc", null, !0) : a.sort(n, "desc", null, !0)
        }), a.elem.on("click", 'input[name="layTableCheckbox"]+', function () {
            var e = t(this).prev(), i = a.layBody.find('input[name="layTableCheckbox"]'), l = e.parents("tr").eq(0).data("index"), n = e[0].checked, o = "layTableAllChoose" === e.attr("lay-filter");
            o ? (i.each(function (e, t) {
                t.checked = n, a.setCheckData(e, n)
            }), a.syncCheckAll(), a.renderForm("checkbox")) : (a.setCheckData(l, n), a.syncCheckAll()), layui.event.call(this, s, "checkbox(" + f + ")", {
                checked: n,
                data: d.cache[a.key] ? d.cache[a.key][l] || {} : {},
                type: o ? "all" : "one"
            })
        }), a.layBody.on("mouseenter", "tr", function () {
            var e = t(this), i = e.index();
            a.layBody.find("tr:eq(" + i + ")").addClass(T)
        }).on("mouseleave", "tr", function () {
            var e = t(this), i = e.index();
            a.layBody.find("tr:eq(" + i + ")").removeClass(T)
        }), a.layBody.on("change", "." + N, function () {
            var e = t(this), i = this.value, l = e.parent().data("field"), n = e.parents("tr").eq(0).data("index"), o = d.cache[a.key][n];
            o[l] = i, layui.event.call(this, s, "edit(" + f + ")", {value: i, data: o, field: l})
        }).on("blur", "." + N, function () {
            var e, l = t(this), n = l.parent().data("field"), o = l.parents("tr").eq(0).data("index"), r = d.cache[a.key][o];
            a.eachCols(function (t, i) {
                i.field == n && i.templet && (e = i.templet)
            }), l.siblings(h).html(e ? i(t(e).html() || this.value).render(r) : this.value), l.parent().data("content", this.value), l.remove()
        }), a.layBody.on("click", "td", function () {
            var e = t(this), i = (e.data("field"), e.data("edit")), o = e.children(h);
            if (l.close(a.tipsIndex), !e.data("off"))if (i)if ("select" === i); else {
                var d = t('<input class="layui-input ' + N + '">');
                d[0].value = e.data("content") || o.text(), e.find("." + N)[0] || e.append(d), d.focus()
            } else o.find(".layui-form-switch,.layui-form-checkbox")[0] || Math.round(o.prop("scrollWidth")) > Math.round(o.outerWidth()) && (a.tipsIndex = l.tips(['<div class="layui-table-tips-main" style="margin-top: -' + (o.height() + 16) + "px;" + function () {
                return "sm" === n.size ? "padding: 4px 15px; font-size: 12px;" : "lg" === n.size ? "padding: 14px 15px;" : ""
            }() + '">', o.html(), "</div>", '<i class="layui-icon layui-table-tips-c">&#x1006;</i>'].join(""), o[0], {
                tips: [3, ""],
                time: -1,
                anim: -1,
                maxWidth: r.ios || r.android ? 300 : 600,
                isOutAnim: !1,
                skin: "layui-table-tips",
                success: function (e, t) {
                    e.find(".layui-table-tips-c").on("click", function () {
                        l.close(t)
                    })
                }
            }))
        }), a.layBody.on("click", "*[lay-event]", function () {
            var e = t(this), l = e.parents("tr").eq(0).data("index"), n = a.layBody.find('tr[data-index="' + l + '"]'), o = "layui-table-click", r = d.cache[a.key][l];
            layui.event.call(this, s, "tool(" + f + ")", {
                data: d.clearCacheKey(r),
                event: e.attr("lay-event"),
                tr: n,
                del: function () {
                    d.cache[a.key][l] = [], n.remove(), a.scrollPatch()
                },
                update: function (e) {
                    e = e || {}, layui.each(e, function (e, l) {
                        if (e in r) {
                            var o, d = n.children('td[data-field="' + e + '"]');
                            r[e] = l, a.eachCols(function (t, i) {
                                i.field == e && i.templet && (o = i.templet)
                            }), d.children(h).html(o ? i(t(o).html() || l).render(r) : l), d.data("content", l)
                        }
                    })
                }
            }), n.addClass(o).siblings("tr").removeClass(o)
        }), a.layMain.on("scroll", function () {
            var e = t(this), i = e.scrollLeft(), n = e.scrollTop();
            a.layHeader.scrollLeft(i), a.layFixed.find(m).scrollTop(n), l.close(a.tipsIndex)
        }), A.on("resize", function () {
            a.fullSize(), a.scrollPatch()
        })
    }, d.init = function (e, i) {
        i = i || {};
        var a = this, l = t(e ? 'table[lay-filter="' + e + '"]' : u + "[lay-data]"), n = "Table element property lay-data configuration item has a syntax error: ";
        return l.each(function () {
            var a = t(this), l = a.attr("lay-data");
            try {
                l = new Function("return " + l)()
            } catch (r) {
                o.error(n + l)
            }
            var c = [], s = t.extend({
                elem: this,
                cols: [],
                data: [],
                skin: a.attr("lay-skin"),
                size: a.attr("lay-size"),
                even: "string" == typeof a.attr("lay-even")
            }, d.config, i, l);
            e && a.hide(), a.find("thead>tr").each(function (e) {
                s.cols[e] = [], t(this).children().each(function (i) {
                    var a = t(this), l = a.attr("lay-data");
                    try {
                        l = new Function("return " + l)()
                    } catch (r) {
                        return o.error(n + l)
                    }
                    var d = t.extend({
                        title: a.text(),
                        colspan: a.attr("colspan") || 0,
                        rowspan: a.attr("rowspan") || 0
                    }, l);
                    d.colspan < 2 && c.push(d), s.cols[e].push(d)
                })
            }), a.find("tbody>tr").each(function (e) {
                var i = t(this), a = {};
                i.children("td").each(function (e, i) {
                    var l = t(this), n = l.data("field");
                    if (n)return a[n] = l.html()
                }), layui.each(c, function (e, t) {
                    var l = i.children("td").eq(e);
                    a[t.field] = l.html()
                }), s.data[e] = a
            }), d.render(s)
        }), a
    }, d.checkStatus = function (e) {
        var t = 0, i = 0, a = [], l = d.cache[e] || [];
        return layui.each(l, function (e, l) {
            return l.constructor === Array ? void i++ : void(l[d.config.checkName] && (t++, a.push(d.clearCacheKey(l))))
        }), {data: a, isAll: !!l.length && t === l.length - i}
    }, c.config = {}, d.reload = function (e, i) {
        var a = c.config[e];
        return i = i || {}, a ? (i.data && i.data.constructor === Array && delete a.data, d.render(t.extend(!0, {}, a, i))) : o.error("The ID option was not found in the table instance")
    }, d.render = function (e) {
        var t = new M(e);
        return c.call(t)
    }, d.clearCacheKey = function (e) {
        return e = t.extend({}, e), delete e[d.config.checkName], delete e[d.config.indexName], e
    }, d.init(), e(s, d)
});
layui.define("jquery", function (e) {
    "use strict";
    var i = layui.$, n = (layui.hint(), layui.device(), {
        config: {}, set: function (e) {
            var n = this;
            return n.config = i.extend({}, n.config, e), n
        }, on: function (e, i) {
            return layui.onevent.call(this, t, e, i)
        }
    }), t = "carousel", a = "layui-this", l = ">*[carousel-item]>*", o = "layui-carousel-left", r = "layui-carousel-right", d = "layui-carousel-prev", s = "layui-carousel-next", u = "layui-carousel-arrow", c = "layui-carousel-ind", m = function (e) {
        var t = this;
        t.config = i.extend({}, t.config, n.config, e), t.render()
    };
    m.prototype.config = {
        width: "600px",
        height: "280px",
        full: !1,
        arrow: "hover",
        indicator: "inside",
        autoplay: !0,
        interval: 3e3,
        anim: "",
        trigger: "click",
        index: 0
    }, m.prototype.render = function () {
        var e = this, n = e.config;
        n.elem = i(n.elem), n.elem[0] && (e.elemItem = n.elem.find(l), n.index < 0 && (n.index = 0), n.index >= e.elemItem.length && (n.index = e.elemItem.length - 1), n.interval < 800 && (n.interval = 800), n.full ? n.elem.css({
            position: "fixed",
            width: "100%",
            height: "100%",
            zIndex: 9999
        }) : n.elem.css({
            width: n.width,
            height: n.height
        }), n.elem.attr("lay-anim", n.anim), e.elemItem.eq(n.index).addClass(a), e.elemItem.length <= 1 || (e.indicator(), e.arrow(), e.autoplay(), e.events()))
    }, m.prototype.reload = function (e) {
        var n = this;
        clearInterval(n.timer), n.config = i.extend({}, n.config, e), n.render()
    }, m.prototype.prevIndex = function () {
        var e = this, i = e.config, n = i.index - 1;
        return n < 0 && (n = e.elemItem.length - 1), n
    }, m.prototype.nextIndex = function () {
        var e = this, i = e.config, n = i.index + 1;
        return n >= e.elemItem.length && (n = 0), n
    }, m.prototype.addIndex = function (e) {
        var i = this, n = i.config;
        e = e || 1, n.index = n.index + e, n.index >= i.elemItem.length && (n.index = 0)
    }, m.prototype.subIndex = function (e) {
        var i = this, n = i.config;
        e = e || 1, n.index = n.index - e, n.index < 0 && (n.index = i.elemItem.length - 1)
    }, m.prototype.autoplay = function () {
        var e = this, i = e.config;
        i.autoplay && (e.timer = setInterval(function () {
            e.slide()
        }, i.interval))
    }, m.prototype.arrow = function () {
        var e = this, n = e.config, t = i(['<button class="layui-icon ' + u + '" lay-type="sub">' + ("updown" === n.anim ? "&#xe619;" : "&#xe603;") + "</button>", '<button class="layui-icon ' + u + '" lay-type="add">' + ("updown" === n.anim ? "&#xe61a;" : "&#xe602;") + "</button>"].join(""));
        n.elem.attr("lay-arrow", n.arrow), n.elem.find("." + u)[0] && n.elem.find("." + u).remove(), n.elem.append(t), t.on("click", function () {
            var n = i(this), t = n.attr("lay-type");
            e.slide(t)
        })
    }, m.prototype.indicator = function () {
        var e = this, n = e.config, t = e.elemInd = i(['<div class="' + c + '"><ul>', function () {
            var i = [];
            return layui.each(e.elemItem, function (e) {
                i.push("<li" + (n.index === e ? ' class="layui-this"' : "") + "></li>")
            }), i.join("")
        }(), "</ul></div>"].join(""));
        n.elem.attr("lay-indicator", n.indicator), n.elem.find("." + c)[0] && n.elem.find("." + c).remove(), n.elem.append(t), "updown" === n.anim && t.css("margin-top", -(t.height() / 2)), t.find("li").on("hover" === n.trigger ? "mouseover" : n.trigger, function () {
            var t = i(this), a = t.index();
            a > n.index ? e.slide("add", a - n.index) : a < n.index && e.slide("sub", n.index - a)
        })
    }, m.prototype.slide = function (e, i) {
        var n = this, l = n.elemItem, u = n.config, c = u.index, m = u.elem.attr("lay-filter");
        n.haveSlide || ("sub" === e ? (n.subIndex(i), l.eq(u.index).addClass(d), setTimeout(function () {
            l.eq(c).addClass(r), l.eq(u.index).addClass(r)
        }, 50)) : (n.addIndex(i), l.eq(u.index).addClass(s), setTimeout(function () {
            l.eq(c).addClass(o), l.eq(u.index).addClass(o)
        }, 50)), setTimeout(function () {
            l.removeClass(a + " " + d + " " + s + " " + o + " " + r), l.eq(u.index).addClass(a), n.haveSlide = !1
        }, 300), n.elemInd.find("li").eq(u.index).addClass(a).siblings().removeClass(a), n.haveSlide = !0, layui.event.call(this, t, "change(" + m + ")", {
            index: u.index,
            prevIndex: c,
            item: l.eq(u.index)
        }))
    }, m.prototype.events = function () {
        var e = this, i = e.config;
        i.elem.data("haveEvents") || (i.elem.on("mouseenter", function () {
            clearInterval(e.timer)
        }).on("mouseleave", function () {
            e.autoplay()
        }), i.elem.data("haveEvents", !0))
    }, n.render = function (e) {
        var i = new m(e);
        return i
    }, e(t, n)
});
layui.define("jquery", function (e) {
    "use strict";
    var t = layui.$, i = {
        fixbar: function (e) {
            var i, o, a = "layui-fixbar", r = "layui-fixbar-top", n = t(document), l = t("body");
            e = t.extend({showHeight: 200}, e), e.bar1 = e.bar1 === !0 ? "&#xe606;" : e.bar1, e.bar2 = e.bar2 === !0 ? "&#xe607;" : e.bar2, e.bgcolor = e.bgcolor ? "background-color:" + e.bgcolor : "";
            var c = [e.bar1, e.bar2, "&#xe604;"], g = t(['<ul class="' + a + '">', e.bar1 ? '<li class="layui-icon" lay-type="bar1" style="' + e.bgcolor + '">' + c[0] + "</li>" : "", e.bar2 ? '<li class="layui-icon" lay-type="bar2" style="' + e.bgcolor + '">' + c[1] + "</li>" : "", '<li class="layui-icon ' + r + '" lay-type="top" style="' + e.bgcolor + '">' + c[2] + "</li>", "</ul>"].join("")), s = g.find("." + r), u = function () {
                var t = n.scrollTop();
                t >= e.showHeight ? i || (s.show(), i = 1) : i && (s.hide(), i = 0)
            };
            t("." + a)[0] || ("object" == typeof e.css && g.css(e.css), l.append(g), u(), g.find("li").on("click", function () {
                var i = t(this), o = i.attr("lay-type");
                "top" === o && t("html,body").animate({scrollTop: 0}, 200), e.click && e.click.call(this, o)
            }), n.on("scroll", function () {
                clearTimeout(o), o = setTimeout(function () {
                    u()
                }, 100)
            }))
        }, countdown: function (e, t, i) {
            var o = this, a = "function" == typeof t, r = new Date(e).getTime(), n = new Date(!t || a ? (new Date).getTime() : t).getTime(), l = r - n, c = [Math.floor(l / 864e5), Math.floor(l / 36e5) % 24, Math.floor(l / 6e4) % 60, Math.floor(l / 1e3) % 60];
            a && (i = t);
            var g = setTimeout(function () {
                o.countdown(e, n + 1e3, i)
            }, 1e3);
            return i && i(l > 0 ? c : [0, 0, 0, 0], t, g), l <= 0 && clearTimeout(g), g
        }, timeAgo: function (e, t) {
            var i = this, o = [[], []], a = (new Date).getTime() - new Date(e).getTime();
            return a > 6912e5 ? (a = new Date(e), o[0][0] = i.digit(a.getFullYear(), 4), o[0][1] = i.digit(a.getMonth() + 1), o[0][2] = i.digit(a.getDate()), t || (o[1][0] = i.digit(a.getHours()), o[1][1] = i.digit(a.getMinutes()), o[1][2] = i.digit(a.getSeconds())), o[0].join("-") + " " + o[1].join(":")) : a >= 864e5 ? (a / 1e3 / 60 / 60 / 24 | 0) + "天前" : a >= 36e5 ? (a / 1e3 / 60 / 60 | 0) + "小时前" : a >= 12e4 ? (a / 1e3 / 60 | 0) + "分钟前" : a < 0 ? "未来" : "刚刚"
        }, digit: function (e, t) {
            var i = "";
            e = String(e), t = t || 2;
            for (var o = e.length; o < t; o++)i += "0";
            return e < Math.pow(10, t) ? i + (0 | e) : e
        }, toDateString: function (e, t) {
            var i = this, o = new Date(e || new Date), a = [i.digit(o.getFullYear(), 4), i.digit(o.getMonth() + 1), i.digit(o.getDate())], r = [i.digit(o.getHours()), i.digit(o.getMinutes()), i.digit(o.getSeconds())];
            return t = t || "yyyy-MM-dd HH:mm:ss", t.replace(/yyyy/g, a[0]).replace(/MM/g, a[1]).replace(/dd/g, a[2]).replace(/HH/g, r[0]).replace(/mm/g, r[1]).replace(/ss/g, r[2])
        }
    };
    e("util", i)
});
layui.define("jquery", function (e) {
    "use strict";
    var l = layui.$, o = function (e) {
    }, t = '<i class="layui-anim layui-anim-rotate layui-anim-loop layui-icon ">&#xe63e;</i>';
    o.prototype.load = function (e) {
        var o, i, n, r, a = this, c = 0;
        e = e || {};
        var f = l(e.elem);
        if (f[0]) {
            var m = l(e.scrollElem || document), u = e.mb || 50, s = !("isAuto" in e) || e.isAuto, v = e.end || "没有更多了", y = e.scrollElem && e.scrollElem !== document, d = "<cite>加载更多</cite>", h = l('<div class="layui-flow-more"><a href="javascript:;">' + d + "</a></div>");
            f.find(".layui-flow-more")[0] || f.append(h);
            var p = function (e, t) {
                e = l(e), h.before(e), t = 0 == t || null, t ? h.html(v) : h.find("a").html(d), i = t, o = null, n && n()
            }, g = function () {
                o = !0, h.find("a").html(t), "function" == typeof e.done && e.done(++c, p)
            };
            if (g(), h.find("a").on("click", function () {
                    l(this);
                    i || o || g()
                }), e.isLazyimg)var n = a.lazyimg({elem: e.elem + " img", scrollElem: e.scrollElem});
            return s ? (m.on("scroll", function () {
                var e = l(this), t = e.scrollTop();
                r && clearTimeout(r), i || (r = setTimeout(function () {
                    var i = y ? e.height() : l(window).height(), n = y ? e.prop("scrollHeight") : document.documentElement.scrollHeight;
                    n - t - i <= u && (o || g())
                }, 100))
            }), a) : a
        }
    }, o.prototype.lazyimg = function (e) {
        var o, t = this, i = 0;
        e = e || {};
        var n = l(e.scrollElem || document), r = e.elem || "img", a = e.scrollElem && e.scrollElem !== document, c = function (e, l) {
            var o = n.scrollTop(), r = o + l, c = a ? function () {
                return e.offset().top - n.offset().top + o
            }() : e.offset().top;
            if (c >= o && c <= r && !e.attr("src")) {
                var m = e.attr("lay-src");
                layui.img(m, function () {
                    var l = t.lazyimg.elem.eq(i);
                    e.attr("src", m).removeAttr("lay-src"), l[0] && f(l), i++
                })
            }
        }, f = function (e, o) {
            var f = a ? (o || n).height() : l(window).height(), m = n.scrollTop(), u = m + f;
            if (t.lazyimg.elem = l(r), e)c(e, f); else for (var s = 0; s < t.lazyimg.elem.length; s++) {
                var v = t.lazyimg.elem.eq(s), y = a ? function () {
                    return v.offset().top - n.offset().top + m
                }() : v.offset().top;
                if (c(v, f), i = s, y > u)break
            }
        };
        if (f(), !o) {
            var m;
            n.on("scroll", function () {
                var e = l(this);
                m && clearTimeout(m), m = setTimeout(function () {
                    f(null, e)
                }, 50)
            }), o = !0
        }
        return f
    }, e("flow", new o)
});
layui.define(["layer", "form"], function (t) {
    "use strict";
    var e = layui.$, i = layui.layer, a = layui.form, l = (layui.hint(), layui.device()), n = "layedit", o = "layui-show", r = "layui-disabled", c = function () {
        var t = this;
        t.index = 0, t.config = {
            tool: ["strong", "italic", "underline", "del", "|", "left", "center", "right", "|", "link", "unlink", "face", "image"],
            hideTool: [],
            height: 280
        }
    };
    c.prototype.set = function (t) {
        var i = this;
        return e.extend(!0, i.config, t), i
    }, c.prototype.on = function (t, e) {
        return layui.onevent(n, t, e)
    }, c.prototype.build = function (t, i) {
        i = i || {};
        var a = this, n = a.config, r = "layui-layedit", c = e("string" == typeof t ? "#" + t : t), u = "LAY_layedit_" + ++a.index, d = c.next("." + r), y = e.extend({}, n, i), f = function () {
            var t = [], e = {};
            return layui.each(y.hideTool, function (t, i) {
                e[i] = !0
            }), layui.each(y.tool, function (i, a) {
                C[a] && !e[a] && t.push(C[a])
            }), t.join("")
        }(), m = e(['<div class="' + r + '">', '<div class="layui-unselect layui-layedit-tool">' + f + "</div>", '<div class="layui-layedit-iframe">', '<iframe id="' + u + '" name="' + u + '" textarea="' + t + '" frameborder="0"></iframe>', "</div>", "</div>"].join(""));
        return l.ie && l.ie < 8 ? c.removeClass("layui-hide").addClass(o) : (d[0] && d.remove(), s.call(a, m, c[0], y), c.addClass("layui-hide").after(m), a.index)
    }, c.prototype.getContent = function (t) {
        var e = u(t);
        if (e[0])return d(e[0].document.body.innerHTML)
    }, c.prototype.getText = function (t) {
        var i = u(t);
        if (i[0])return e(i[0].document.body).text()
    }, c.prototype.setContent = function (t, i, a) {
        var l = u(t);
        l[0] && (a ? e(l[0].document.body).append(i) : e(l[0].document.body).html(i), layedit.sync(t))
    }, c.prototype.sync = function (t) {
        var i = u(t);
        if (i[0]) {
            var a = e("#" + i[1].attr("textarea"));
            a.val(d(i[0].document.body.innerHTML))
        }
    }, c.prototype.getSelection = function (t) {
        var e = u(t);
        if (e[0]) {
            var i = m(e[0].document);
            return document.selection ? i.text : i.toString()
        }
    };
    var s = function (t, i, a) {
        var l = this, n = t.find("iframe");
        n.css({height: a.height}).on("load", function () {
            var o = n.contents(), r = n.prop("contentWindow"), c = o.find("head"), s = e(["<style>", "*{margin: 0; padding: 0;}", "body{padding: 10px; line-height: 20px; overflow-x: hidden; word-wrap: break-word; font: 14px Helvetica Neue,Helvetica,PingFang SC,Microsoft YaHei,Tahoma,Arial,sans-serif; -webkit-box-sizing: border-box !important; -moz-box-sizing: border-box !important; box-sizing: border-box !important;}", "a{color:#01AAED; text-decoration:none;}a:hover{color:#c00}", "p{margin-bottom: 10px;}", "img{display: inline-block; border: none; vertical-align: middle;}", "pre{margin: 10px 0; padding: 10px; line-height: 20px; border: 1px solid #ddd; border-left-width: 6px; background-color: #F2F2F2; color: #333; font-family: Courier New; font-size: 12px;}", "</style>"].join("")), u = o.find("body");
            c.append(s), u.attr("contenteditable", "true").css({"min-height": a.height}).html(i.value || ""), y.apply(l, [r, n, i, a]), g.call(l, r, t, a)
        })
    }, u = function (t) {
        var i = e("#LAY_layedit_" + t), a = i.prop("contentWindow");
        return [a, i]
    }, d = function (t) {
        return 8 == l.ie && (t = t.replace(/<.+>/g, function (t) {
            return t.toLowerCase()
        })), t
    }, y = function (t, a, n, o) {
        var r = t.document, c = e(r.body);
        c.on("keydown", function (t) {
            var e = t.keyCode;
            if (13 === e) {
                var a = m(r), l = p(a), n = l.parentNode;
                if ("pre" === n.tagName.toLowerCase()) {
                    if (t.shiftKey)return;
                    return i.msg("请暂时用shift+enter"), !1
                }
                r.execCommand("formatBlock", !1, "<p>")
            }
        }), e(n).parents("form").on("submit", function () {
            var t = c.html();
            8 == l.ie && (t = t.replace(/<.+>/g, function (t) {
                return t.toLowerCase()
            })), n.value = t
        }), c.on("paste", function (e) {
            r.execCommand("formatBlock", !1, "<p>"), setTimeout(function () {
                f.call(t, c), n.value = c.html()
            }, 100)
        })
    }, f = function (t) {
        var i = this;
        i.document;
        t.find("*[style]").each(function () {
            var t = this.style.textAlign;
            this.removeAttribute("style"), e(this).css({"text-align": t || ""})
        }), t.find("table").addClass("layui-table"), t.find("script,link").remove()
    }, m = function (t) {
        return t.selection ? t.selection.createRange() : t.getSelection().getRangeAt(0)
    }, p = function (t) {
        return t.endContainer || t.parentElement().childNodes[0]
    }, v = function (t, i, a) {
        var l = this.document, n = document.createElement(t);
        for (var o in i)n.setAttribute(o, i[o]);
        if (n.removeAttribute("text"), l.selection) {
            var r = a.text || i.text;
            if ("a" === t && !r)return;
            r && (n.innerHTML = r), a.pasteHTML(e(n).prop("outerHTML")), a.select()
        } else {
            var r = a.toString() || i.text;
            if ("a" === t && !r)return;
            r && (n.innerHTML = r), a.deleteContents(), a.insertNode(n)
        }
    }, h = function (t, i) {
        var a = this.document, l = "layedit-tool-active", n = p(m(a)), o = function (e) {
            return t.find(".layedit-tool-" + e)
        };
        i && i[i.hasClass(l) ? "removeClass" : "addClass"](l), t.find(">i").removeClass(l), o("unlink").addClass(r), e(n).parents().each(function () {
            var t = this.tagName.toLowerCase(), e = this.style.textAlign;
            "b" !== t && "strong" !== t || o("b").addClass(l), "i" !== t && "em" !== t || o("i").addClass(l), "u" === t && o("u").addClass(l), "strike" === t && o("d").addClass(l), "p" === t && ("center" === e ? o("center").addClass(l) : "right" === e ? o("right").addClass(l) : o("left").addClass(l)), "a" === t && (o("link").addClass(l), o("unlink").removeClass(r))
        })
    }, g = function (t, a, l) {
        var n = t.document, o = e(n.body), c = {
            link: function (i) {
                var a = p(i), l = e(a).parent();
                b.call(o, {href: l.attr("href"), target: l.attr("target")}, function (e) {
                    var a = l[0];
                    "A" === a.tagName ? a.href = e.url : v.call(t, "a", {target: e.target, href: e.url, text: e.url}, i)
                })
            }, unlink: function (t) {
                n.execCommand("unlink")
            }, face: function (e) {
                x.call(this, function (i) {
                    v.call(t, "img", {src: i.src, alt: i.alt}, e)
                })
            }, image: function (a) {
                var n = this;
                layui.use("upload", function (o) {
                    var r = l.uploadImage || {};
                    o.render({
                        url: r.url, method: r.type, elem: e(n).find("input")[0], done: function (e) {
                            0 == e.code ? (e.data = e.data || {}, v.call(t, "img", {
                                src: e.data.src,
                                alt: e.data.title
                            }, a)) : i.msg(e.msg || "上传失败")
                        }
                    })
                })
            }, code: function (e) {
                k.call(o, function (i) {
                    v.call(t, "pre", {text: i.code, "lay-lang": i.lang}, e)
                })
            }, help: function () {
                i.open({
                    type: 2,
                    title: "帮助",
                    area: ["600px", "380px"],
                    shadeClose: !0,
                    shade: .1,
                    skin: "layui-layer-msg",
                    content: ["http://www.layui.com/about/layedit/help.html", "no"]
                })
            }
        }, s = a.find(".layui-layedit-tool"), u = function () {
            var i = e(this), a = i.attr("layedit-event"), l = i.attr("lay-command");
            if (!i.hasClass(r)) {
                o.focus();
                var u = m(n);
                u.commonAncestorContainer;
                l ? (n.execCommand(l), /justifyLeft|justifyCenter|justifyRight/.test(l) && n.execCommand("formatBlock", !1, "<p>"), setTimeout(function () {
                    o.focus()
                }, 10)) : c[a] && c[a].call(this, u), h.call(t, s, i)
            }
        }, d = /image/;
        s.find(">i").on("mousedown", function () {
            var t = e(this), i = t.attr("layedit-event");
            d.test(i) || u.call(this)
        }).on("click", function () {
            var t = e(this), i = t.attr("layedit-event");
            d.test(i) && u.call(this)
        }), o.on("click", function () {
            h.call(t, s), i.close(x.index)
        })
    }, b = function (t, e) {
        var l = this, n = i.open({
            type: 1,
            id: "LAY_layedit_link",
            area: "350px",
            shade: .05,
            shadeClose: !0,
            moveType: 1,
            title: "超链接",
            skin: "layui-layer-msg",
            content: ['<ul class="layui-form" style="margin: 15px;">', '<li class="layui-form-item">', '<label class="layui-form-label" style="width: 60px;">URL</label>', '<div class="layui-input-block" style="margin-left: 90px">', '<input name="url" lay-verify="url" value="' + (t.href || "") + '" autofocus="true" autocomplete="off" class="layui-input">', "</div>", "</li>", '<li class="layui-form-item">', '<label class="layui-form-label" style="width: 60px;">打开方式</label>', '<div class="layui-input-block" style="margin-left: 90px">', '<input type="radio" name="target" value="_self" class="layui-input" title="当前窗口"' + ("_self" !== t.target && t.target ? "" : "checked") + ">", '<input type="radio" name="target" value="_blank" class="layui-input" title="新窗口" ' + ("_blank" === t.target ? "checked" : "") + ">", "</div>", "</li>", '<li class="layui-form-item" style="text-align: center;">', '<button type="button" lay-submit lay-filter="layedit-link-yes" class="layui-btn"> 确定 </button>', '<button style="margin-left: 20px;" type="button" class="layui-btn layui-btn-primary"> 取消 </button>', "</li>", "</ul>"].join(""),
            success: function (t, n) {
                var o = "submit(layedit-link-yes)";
                a.render("radio"), t.find(".layui-btn-primary").on("click", function () {
                    i.close(n), l.focus()
                }), a.on(o, function (t) {
                    i.close(b.index), e && e(t.field)
                })
            }
        });
        b.index = n
    }, x = function (t) {
        var a = function () {
            var t = ["[微笑]", "[嘻嘻]", "[哈哈]", "[可爱]", "[可怜]", "[挖鼻]", "[吃惊]", "[害羞]", "[挤眼]", "[闭嘴]", "[鄙视]", "[爱你]", "[泪]", "[偷笑]", "[亲亲]", "[生病]", "[太开心]", "[白眼]", "[右哼哼]", "[左哼哼]", "[嘘]", "[衰]", "[委屈]", "[吐]", "[哈欠]", "[抱抱]", "[怒]", "[疑问]", "[馋嘴]", "[拜拜]", "[思考]", "[汗]", "[困]", "[睡]", "[钱]", "[失望]", "[酷]", "[色]", "[哼]", "[鼓掌]", "[晕]", "[悲伤]", "[抓狂]", "[黑线]", "[阴险]", "[怒骂]", "[互粉]", "[心]", "[伤心]", "[猪头]", "[熊猫]", "[兔子]", "[ok]", "[耶]", "[good]", "[NO]", "[赞]", "[来]", "[弱]", "[草泥马]", "[神马]", "[囧]", "[浮云]", "[给力]", "[围观]", "[威武]", "[奥特曼]", "[礼物]", "[钟]", "[话筒]", "[蜡烛]", "[蛋糕]"], e = {};
            return layui.each(t, function (t, i) {
                e[i] = layui.cache.dir + "images/face/" + t + ".gif"
            }), e
        }();
        return x.hide = x.hide || function (t) {
                "face" !== e(t.target).attr("layedit-event") && i.close(x.index)
            }, x.index = i.tips(function () {
            var t = [];
            return layui.each(a, function (e, i) {
                t.push('<li title="' + e + '"><img src="' + i + '" alt="' + e + '"></li>')
            }), '<ul class="layui-clear">' + t.join("") + "</ul>"
        }(), this, {
            tips: 1, time: 0, skin: "layui-box layui-util-face", maxWidth: 500, success: function (l, n) {
                l.css({marginTop: -4, marginLeft: -10}).find(".layui-clear>li").on("click", function () {
                    t && t({src: a[this.title], alt: this.title}), i.close(n)
                }), e(document).off("click", x.hide).on("click", x.hide)
            }
        })
    }, k = function (t) {
        var e = this, l = i.open({
            type: 1,
            id: "LAY_layedit_code",
            area: "550px",
            shade: .05,
            shadeClose: !0,
            moveType: 1,
            title: "插入代码",
            skin: "layui-layer-msg",
            content: ['<ul class="layui-form layui-form-pane" style="margin: 15px;">', '<li class="layui-form-item">', '<label class="layui-form-label">请选择语言</label>', '<div class="layui-input-block">', '<select name="lang">', '<option value="JavaScript">JavaScript</option>', '<option value="HTML">HTML</option>', '<option value="CSS">CSS</option>', '<option value="Java">Java</option>', '<option value="PHP">PHP</option>', '<option value="C#">C#</option>', '<option value="Python">Python</option>', '<option value="Ruby">Ruby</option>', '<option value="Go">Go</option>', "</select>", "</div>", "</li>", '<li class="layui-form-item layui-form-text">', '<label class="layui-form-label">代码</label>', '<div class="layui-input-block">', '<textarea name="code" lay-verify="required" autofocus="true" class="layui-textarea" style="height: 200px;"></textarea>', "</div>", "</li>", '<li class="layui-form-item" style="text-align: center;">', '<button type="button" lay-submit lay-filter="layedit-code-yes" class="layui-btn"> 确定 </button>', '<button style="margin-left: 20px;" type="button" class="layui-btn layui-btn-primary"> 取消 </button>', "</li>", "</ul>"].join(""),
            success: function (l, n) {
                var o = "submit(layedit-code-yes)";
                a.render("select"), l.find(".layui-btn-primary").on("click", function () {
                    i.close(n), e.focus()
                }), a.on(o, function (e) {
                    i.close(k.index), t && t(e.field)
                })
            }
        });
        k.index = l
    }, C = {
        html: '<i class="layui-icon layedit-tool-html" title="HTML源代码" lay-command="html" layedit-event="html"">&#xe64b;</i><span class="layedit-tool-mid"></span>',
        strong: '<i class="layui-icon layedit-tool-b" title="加粗" lay-command="Bold" layedit-event="b"">&#xe62b;</i>',
        italic: '<i class="layui-icon layedit-tool-i" title="斜体" lay-command="italic" layedit-event="i"">&#xe644;</i>',
        underline: '<i class="layui-icon layedit-tool-u" title="下划线" lay-command="underline" layedit-event="u"">&#xe646;</i>',
        del: '<i class="layui-icon layedit-tool-d" title="删除线" lay-command="strikeThrough" layedit-event="d"">&#xe64f;</i>',
        "|": '<span class="layedit-tool-mid"></span>',
        left: '<i class="layui-icon layedit-tool-left" title="左对齐" lay-command="justifyLeft" layedit-event="left"">&#xe649;</i>',
        center: '<i class="layui-icon layedit-tool-center" title="居中对齐" lay-command="justifyCenter" layedit-event="center"">&#xe647;</i>',
        right: '<i class="layui-icon layedit-tool-right" title="右对齐" lay-command="justifyRight" layedit-event="right"">&#xe648;</i>',
        link: '<i class="layui-icon layedit-tool-link" title="插入链接" layedit-event="link"">&#xe64c;</i>',
        unlink: '<i class="layui-icon layedit-tool-unlink layui-disabled" title="清除链接" lay-command="unlink" layedit-event="unlink"">&#xe64d;</i>',
        face: '<i class="layui-icon layedit-tool-face" title="表情" layedit-event="face"">&#xe650;</i>',
        image: '<i class="layui-icon layedit-tool-image" title="图片" layedit-event="image">&#xe64a;<input type="file" name="file"></i>',
        code: '<i class="layui-icon layedit-tool-code" title="插入代码" layedit-event="code">&#xe64e;</i>',
        help: '<i class="layui-icon layedit-tool-help" title="帮助" layedit-event="help">&#xe607;</i>'
    }, w = new c;
    t(n, w)
});
layui.define("jquery", function (e) {
    "use strict";
    var a = layui.$, l = "http://www.layui.com/doc/modules/code.html";
    e("code", function (e) {
        var t = [];
        e = e || {}, e.elem = a(e.elem || ".layui-code"), e.about = !("about" in e) || e.about, e.elem.each(function () {
            t.push(this)
        }), layui.each(t.reverse(), function (t, i) {
            var c = a(i), o = c.html();
            (c.attr("lay-encode") || e.encode) && (o = o.replace(/&(?!#?[a-zA-Z0-9]+;)/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;").replace(/'/g, "&#39;").replace(/"/g, "&quot;")), c.html('<ol class="layui-code-ol"><li>' + o.replace(/[\r\t\n]+/g, "</li><li>") + "</li></ol>"), c.find(">.layui-code-h3")[0] || c.prepend('<h3 class="layui-code-h3">' + (c.attr("lay-title") || e.title || "code") + (e.about ? '<a href="' + l + '" target="_blank">layui.code</a>' : "") + "</h3>");
            var d = c.find(">.layui-code-ol");
            c.addClass("layui-box layui-code-view"), (c.attr("lay-skin") || e.skin) && c.addClass("layui-code-" + (c.attr("lay-skin") || e.skin)), (d.find("li").length / 100 | 0) > 0 && d.css("margin-left", (d.find("li").length / 100 | 0) + "px"), (c.attr("lay-height") || e.height) && d.css("max-height", c.attr("lay-height") || e.height)
        })
    })
}).addcss("modules/code.css", "skincodecss");