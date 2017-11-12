/*ace-extra*/
if (!("ace" in window)) { window.ace = {} } if (!("vars" in window.ace)) { window.ace.vars = { icon: " ace-icon ", ".icon": ".ace-icon" } } ace.config = { cookie_expiry: 604800, storage_method: 2 }; ace.settings = { is: function (b, a) { return (ace.data.get("settings", b + "-" + a) == 1) }, exists: function (b, a) { return (ace.data.get("settings", b + "-" + a) !== null) }, set: function (b, a) { ace.data.set("settings", b + "-" + a, 1) }, unset: function (b, a) { ace.data.set("settings", b + "-" + a, -1) }, remove: function (b, a) { ace.data.remove("settings", b + "-" + a) }, navbar_fixed: function (a, d, b) { var c = document.getElementById("navbar"); if (!c) { return false } a = a || false; if (!a && b !== false) { var e = null; if (ace.settings.is("sidebar", "fixed") || ((e = document.getElementById("sidebar")) && ace.hasClass(e, "sidebar-fixed"))) { ace.settings.sidebar_fixed(false) } } if (a) { if (!ace.hasClass(c, "navbar-fixed-top")) { ace.addClass(c, "navbar-fixed-top") } if (d !== false) { ace.settings.set("navbar", "fixed") } } else { ace.removeClass(c, "navbar-fixed-top"); if (d !== false) { ace.settings.unset("navbar", "fixed") } } document.getElementById("ace-settings-navbar").checked = a; if (window.jQuery) { jQuery(document).trigger("settings.ace", ["navbar_fixed", a]) } }, sidebar_fixed: function (a, d, c) { var f = document.getElementById("sidebar"); if (!f) { return false } a = a || false; if (!a && c !== false) { var b = null; if (ace.settings.is("breadcrumbs", "fixed") || ((b = document.getElementById("breadcrumbs")) && ace.hasClass(b, "breadcrumbs-fixed"))) { ace.settings.breadcrumbs_fixed(false) } } if (a && c !== false && !ace.settings.is("navbar", "fixed")) { ace.settings.navbar_fixed(true) } if (a) { if (!ace.hasClass(f, "sidebar-fixed")) { ace.addClass(f, "sidebar-fixed"); var e = document.getElementById("menu-toggler"); if (e) { ace.addClass(e, "fixed") } } if (d !== false) { ace.settings.set("sidebar", "fixed") } } else { ace.removeClass(f, "sidebar-fixed"); var e = document.getElementById("menu-toggler"); if (e) { ace.removeClass(e, "fixed") } if (d !== false) { ace.settings.unset("sidebar", "fixed") } } document.getElementById("ace-settings-sidebar").checked = a; if (window.jQuery) { jQuery(document).trigger("settings.ace", ["sidebar_fixed", a]) } }, breadcrumbs_fixed: function (a, d, c) { var b = document.getElementById("breadcrumbs"); if (!b) { return false } a = a || false; if (a && c !== false && !ace.settings.is("sidebar", "fixed")) { ace.settings.sidebar_fixed(true) } if (a) { if (!ace.hasClass(b, "breadcrumbs-fixed")) { ace.addClass(b, "breadcrumbs-fixed") } if (d !== false) { ace.settings.set("breadcrumbs", "fixed") } } else { ace.removeClass(b, "breadcrumbs-fixed"); if (d !== false) { ace.settings.unset("breadcrumbs", "fixed") } } document.getElementById("ace-settings-breadcrumbs").checked = a; if (window.jQuery) { jQuery(document).trigger("settings.ace", ["breadcrumbs_fixed", a]) } }, main_container_fixed: function (a, d) { a = a || false; var c = document.getElementById("main-container"); if (!c) { return false } var b = document.getElementById("navbar-container"); if (a) { if (!ace.hasClass(c, "container")) { ace.addClass(c, "container") } if (!ace.hasClass(b, "container")) { ace.addClass(b, "container") } if (d !== false) { ace.settings.set("main-container", "fixed") } } else { ace.removeClass(c, "container"); ace.removeClass(b, "container"); if (d !== false) { ace.settings.unset("main-container", "fixed") } } document.getElementById("ace-settings-add-container").checked = a; if (navigator.userAgent.match(/webkit/i)) { var e = document.getElementById("sidebar"); ace.toggleClass(e, "menu-min"); setTimeout(function () { ace.toggleClass(e, "menu-min") }, 0) } if (window.jQuery) { jQuery(document).trigger("settings.ace", ["main_container_fixed", a]) } }, sidebar_collapsed: function (a, e) { var f = document.getElementById("sidebar"); if (!f) { return false } a = a || false; var g = ace.isHTTMlElement(this) ? this : f.querySelector(".sidebar-collapse"); var d = g ? g.querySelector(ace.vars[".icon"]) : null, c, b; if (d) { c = d.getAttribute("data-icon1"); b = d.getAttribute("data-icon2") } if (a) { ace.addClass(f, "menu-min"); if (d) { ace.removeClass(d, c); ace.addClass(d, b) } if (e !== false) { ace.settings.set("sidebar", "collapsed") } } else { ace.removeClass(f, "menu-min"); if (d) { ace.removeClass(d, b); ace.addClass(d, c) } if (e !== false) { ace.settings.unset("sidebar", "collapsed") } } if (window.jQuery) { jQuery(document).trigger("settings.ace", ["sidebar_collapsed", a]) } } }; ace.settings.check = function (c, e) { if (!ace.settings.exists(c, e)) { return } var a = ace.settings.is(c, e); var b = { "navbar-fixed": "navbar-fixed-top", "sidebar-fixed": "sidebar-fixed", "breadcrumbs-fixed": "breadcrumbs-fixed", "sidebar-collapsed": "menu-min", "main-container-fixed": "container" }; var d = document.getElementById(c); if (a != ace.hasClass(d, b[c + "-" + e])) { ace.settings[c.replace("-", "_") + "_" + e](a) } }; ace.data_storage = function (e, c) { var b = "ace."; var d = null; var a = 0; if ((e == 1 || e === c) && "localStorage" in window && window.localStorage !== null) { d = ace.storage; a = 1 } else { if (d == null && (e == 2 || e === c) && "cookie" in document && document.cookie !== null) { d = ace.cookie; a = 2 } } this.set = function (h, g, i, k) { if (!d) { return } if (i === k) { i = g; g = h; if (i == null) { d.remove(b + g) } else { if (a == 1) { d.set(b + g, i) } else { if (a == 2) { d.set(b + g, i, ace.config.cookie_expiry) } } } } else { if (a == 1) { if (i == null) { d.remove(b + h + "." + g) } else { d.set(b + h + "." + g, i) } } else { if (a == 2) { var j = d.get(b + h); var f = j ? JSON.parse(j) : {}; if (i == null) { delete f[g]; if (ace.sizeof(f) == 0) { d.remove(b + h); return } } else { f[g] = i } d.set(b + h, JSON.stringify(f), ace.config.cookie_expiry) } } } }; this.get = function (h, g, j) { if (!d) { return null } if (g === j) { g = h; return d.get(b + g) } else { if (a == 1) { return d.get(b + h + "." + g) } else { if (a == 2) { var i = d.get(b + h); var f = i ? JSON.parse(i) : {}; return g in f ? f[g] : null } } } }; this.remove = function (g, f, h) { if (!d) { return } if (f === h) { f = g; this.set(f, null) } else { this.set(g, f, null) } } }; ace.cookie = { get: function (c) { var d = document.cookie, g, f = c + "=", a; if (!d) { return } a = d.indexOf("; " + f); if (a == -1) { a = d.indexOf(f); if (a != 0) { return null } } else { a += 2 } g = d.indexOf(";", a); if (g == -1) { g = d.length } return decodeURIComponent(d.substring(a + f.length, g)) }, set: function (b, e, a, g, c, f) { var h = new Date(); if (typeof (a) == "object" && a.toGMTString) { a = a.toGMTString() } else { if (parseInt(a, 10)) { h.setTime(h.getTime() + (parseInt(a, 10) * 1000)); a = h.toGMTString() } else { a = "" } } document.cookie = b + "=" + encodeURIComponent(e) + ((a) ? "; expires=" + a : "") + ((g) ? "; path=" + g : "") + ((c) ? "; domain=" + c : "") + ((f) ? "; secure" : "") }, remove: function (a, b) { this.set(a, "", -1000, b) } }; ace.storage = { get: function (a) { return window.localStorage.getItem(a) }, set: function (a, b) { window.localStorage.setItem(a, b) }, remove: function (a) { window.localStorage.removeItem(a) } }; ace.sizeof = function (c) { var b = 0; for (var a in c) { if (c.hasOwnProperty(a)) { b++ } } return b }; ace.hasClass = function (b, a) { return (" " + b.className + " ").indexOf(" " + a + " ") > -1 }; ace.addClass = function (c, b) { if (!ace.hasClass(c, b)) { var a = c.className; c.className = a + " " + b } }; ace.removeClass = function (b, a) { ace.replaceClass(b, a) }; ace.replaceClass = function (c, b, d) { var a = new RegExp(("(^|\\s)" + b + "(\\s|$)"), "i"); c.className = c.className.replace(a, function (e, g, f) { return d ? (g + d + f) : " " }).replace(/^\s+|\s+$/g, "") }; ace.toggleClass = function (b, a) { if (ace.hasClass(b, a)) { ace.removeClass(b, a) } else { ace.addClass(b, a) } }; ace.isHTTMlElement = function (a) { return window.HTMLElement ? a instanceof HTMLElement : ("nodeType" in a ? a.nodeType == 1 : false) }; ace.data = new ace.data_storage(ace.config.storage_method);
/*ace-elements*/
!function (e, t) { var i = function (t, i) { function n(e) { e.preventDefault(), e.stopPropagation(); var t = y.offset(), i = t[c], n = v ? e.pageY : e.pageX; n > i + z ? (z = n - i - A + I, z > E && (z = E)) : (z = n - i - I, 0 > z && (z = 0)), o.update_scroll() } function a(t) { t.preventDefault(), t.stopPropagation(), j = N = v ? t.pageY : t.pageX, H = !0, e("html").off("mousemove.ace_scroll").on("mousemove.ace_scroll", s), e(P).off("mouseup.ace_scroll").on("mouseup.ace_scroll", r), y.addClass("active"), q && o.$element.trigger("drag.start") } function s(e) { e.preventDefault(), e.stopPropagation(), j = v ? e.pageY : e.pageX, j - N + z > E ? j = N + E - z : 0 > j - N + z && (j = N - z), z += j - N, N = j, 0 > z ? z = 0 : z > E && (z = E), o.update_scroll() } function r(t) { t.preventDefault(), t.stopPropagation(), H = !1, e("html").off(".ace_scroll"), e(P).off(".ace_scroll"), y.removeClass("active"), q && o.$element.trigger("drag.end") } var o = this, l = e.extend({}, e.fn.ace_scroll.defaults, i); this.size = 0, this.$element = e(t), this.element = t; var c, d, h, u, f, p, v = !0, g = !1, m = !1, b = !1, w = null, _ = null, y = null, C = null, k = null, x = null, $ = null, A = 0, z = 0, E = 0, I = 0, D = !0, T = !1, S = 1, M = !1, H = !1, P = "onmouseup" in window ? window : "html", q = l.dragEvent || !1, L = i.scrollEvent || !1; this.create = function (t) { if (!b) { if (t && (l = e.extend({}, e.fn.ace_scroll.defaults, t)), this.size = parseInt(this.$element.attr("data-size")) || l.size || 200, v = !l.horizontal, c = v ? "top" : "left", d = v ? "height" : "width", h = v ? "maxHeight" : "maxWidth", u = v ? "clientHeight" : "clientWidth", f = v ? "scrollTop" : "scrollLeft", p = v ? "scrollHeight" : "scrollWidth", this.$element.addClass("ace-scroll " + ((v ? "" : " scroll-hz") + (l.styleClass ? " " + l.styleClass : ""))), "static" == this.$element.css("position") ? (M = this.element.style.position, this.element.style.position = "relative") : M = !1, this.$element.wrapInner('<div class="scroll-content" />'), this.$element.prepend('<div class="scroll-track"><div class="scroll-bar"></div></div>'), w = this.$element.find(".scroll-content").eq(0), v || w.wrapInner("<div />"), _ = w.get(0), y = this.$element.find(".scroll-track").eq(0), C = y.find(".scroll-bar").eq(0), k = y.get(0), x = C.get(0), $ = x.style, y.hide(), y.on("mousedown", n), C.on("mousedown", a), w.on("scroll", function () { D && (z = parseInt(Math.round(this[f] * S)), $[c] = z + "px"), D = !1, L && this.$element.trigger("scroll", [_]) }), l.mouseWheel) { var i = l.mouseWheelLock, s = !l.lockAnyway; this.$element.on("mousewheel.ace_scroll DOMMouseScroll.ace_scroll", function (t) { if (!g) { if (!m) return s; H && (H = !1, e("html").off(".ace_scroll"), e(P).off(".ace_scroll"), q && o.$element.trigger("drag.end")); var n = t.originalEvent.detail < 0 || t.originalEvent.wheelDelta > 0 ? 1 : -1, a = !1, r = _[u], l = _[f]; i || (a = -1 == n ? _[p] <= l + r : 0 == l), o.move_bar(!0); var c = parseInt(Math.round(Math.min(Math.max(r / 8, 54)), o.size)) + 1; return _[f] = l - n * c, a && s } }) } var r = ace.vars.touch && "ace_drag" in e.event.special && l.touchDrag; if (r) { var A = "", E = r ? "ace_drag" : "swipe"; this.$element.on(E + ".ace_scroll", function (e) { if (A = e.direction, v && ("up" == A || "down" == A) || !v && ("left" == A || "right" == A)) { var t = v ? e.dy : e.dx; 0 != t && (Math.abs(t) > 20 && r && (t = 2 * t), o.move_bar(!0), _[f] = _[f] + t) } }) } l.hoverReset && this.$element.on("mouseenter.ace_scroll touchstart.ace_scroll", function () { o.reset() }), v || w.children(0).css(d, this.size), w.css(h, this.size), g = !1, b = !0 } }, this.is_active = function () { return m }, this.is_enabled = function () { return !g }, this.move_bar = function (e) { D = e }, this.reset = function () { if (!g) { b || this.create(); var e = v ? _[p] : this.size; if (v && 0 == e || !v && 0 == this.element.scrollWidth) return void this.$element.removeClass("scroll-active"); var t = v ? this.size : _.clientWidth; v || w.children(0).css(d, this.size), w.css(h, this.size), e > t ? (m = !0, y.css(d, t).show(), S = parseFloat((t / e).toFixed(5)), A = parseInt(Math.round(t * S)), I = parseInt(Math.round(A / 2)), E = t - A, z = parseInt(Math.round(_[f] * S)), $[d] = A + "px", $[c] = z + "px", this.$element.addClass("scroll-active"), T || (l.reset && (_[f] = 0, $[c] = 0), T = !0)) : (m = !1, y.hide(), this.$element.removeClass("scroll-active"), w.css(h, "")) } }, this.disable = function () { return _[f] = 0, $[c] = 0, g = !0, m = !1, y.hide(), this.$element.removeClass("scroll-active"), w.css(h, ""), this }, this.enable = function () { return g = !1, this.reset(), this }, this.destroy = function () { return m = !1, g = !1, b = !1, this.$element.removeClass("ace-scroll scroll-hz" + (l.extraClass ? " " + l.extraClass : "")), this.$element.off(".ace_scroll"), v || w.find("> div").children().unwrap(), w.children().unwrap(), w.remove(), y.remove(), M !== !1 && (this.element.style.position = M), this }, this.modify = function (t) { return t && (l = e.extend({}, e.fn.ace_scroll.defaults, t)), this.destroy(), this.create(), this.reset(), this }, this.update = function (e) { return this.size = e.size, this }, this.update_scroll = function () { D = !1, $[c] = z + "px", _[f] = parseInt(Math.round(z / S)) }; var N = -1, j = -1; return this.create(), this.reset(), this }; e.fn.ace_scroll = function (n, a) { var s, r = this.each(function () { var t = e(this), r = t.data("ace_scroll"), o = "object" == typeof n && n; r || t.data("ace_scroll", r = new i(this, o)), "string" == typeof n && (s = r[n](a)) }); return s === t ? r : s }, e.fn.ace_scroll.defaults = { size: 200, horizontal: !1, mouseWheel: !0, mouseWheelLock: !1, lockAnyway: !1, styleClass: !1, hoverReset: !0, reset: !1, dragEvent: !1, touchDrag: !0, touchSwipe: !1, scrollEvent: !1 } }(window.jQuery), function (e, t) { var i = function (t, i) { var n = e.extend({}, e.fn.ace_colorpicker.defaults, i), a = e(t), s = "", r = "", o = null, l = []; a.addClass("hide").find("option").each(function () { var e = "colorpick-btn", t = this.value.replace(/[^\w\s,#\(\)\.]/g, ""); this.value != t && (this.value = t), this.selected && (e += " selected", r = t), l.push(t), s += '<li><a class="' + e + '" href="#" style="background-color:' + t + ';" data-color="' + t + '"></a></li>' }).end().on("change.color", function () { a.next().find(".btn-colorpicker").css("background-color", this.value) }).after('<div class="dropdown dropdown-colorpicker">		<a data-toggle="dropdown" class="dropdown-toggle" ' + (n.auto_pos ? 'data-position="auto"' : "") + ' href="#"><span class="btn-colorpicker" style="background-color:' + r + '"></span></a><ul class="dropdown-menu' + (n.caret ? " dropdown-caret" : "") + (n.pull_right ? " dropdown-menu-right" : "") + '">' + s + "</ul></div>"); var c = a.next().find(".dropdown-menu"); c.on(ace.click_event, function (t) { var i = e(t.target); if (!i.is(".colorpick-btn")) return !1; o && o.removeClass("selected"), o = i, o.addClass("selected"); var n = o.data("color"); return a.val(n).trigger("change"), t.preventDefault(), !0 }), o = a.next().find("a.selected"), this.pick = function (i, n) { if ("number" == typeof i) { if (i >= l.length) return; t.selectedIndex = i, c.find("a:eq(" + i + ")").trigger(ace.click_event) } else if ("string" == typeof i) { var s = i.replace(/[^\w\s,#\(\)\.]/g, ""); if (i = l.indexOf(s), -1 == i && n === !0 && (l.push(s), e("<option />").appendTo(a).val(s), e('<li><a class="colorpick-btn" href="#"></a></li>').appendTo(c).find("a").css("background-color", s).data("color", s), i = l.length - 1), -1 == i) return; c.find("a:eq(" + i + ")").trigger(ace.click_event) } }, this.destroy = function () { a.removeClass("hide").off("change.color").next().remove(), l = [] } }; e.fn.ace_colorpicker = function (n, a) { var s, r = this.each(function () { var t = e(this), r = t.data("ace_colorpicker"), o = "object" == typeof n && n; r || t.data("ace_colorpicker", r = new i(this, o)), "string" == typeof n && (s = r[n](a)) }); return s === t ? r : s }, e.fn.ace_colorpicker.defaults = { pull_right: !1, caret: !0, auto_pos: !0 } }(window.jQuery), function (e, t) { var i = "multiple" in document.createElement("INPUT"), n = "FileList" in window, a = "FileReader" in window, s = "File" in window, r = function (t, i) { var n = this; this.settings = e.extend({}, e.fn.ace_file_input.defaults, i), this.$element = e(t), this.element = t, this.disabled = !1, this.can_reset = !0, this.$element.off("change.ace_inner_call").on("change.ace_inner_call", function (e, t) { return t !== !0 ? l.call(n) : void 0 }); var a = this.$element.closest("label").css({ display: "block" }), s = 0 == a.length ? "label" : "span"; this.$element.wrap("<" + s + ' class="ace-file-input" />'), this.apply_settings(), this.reset_input_field() }; r.error = { FILE_LOAD_FAILED: 1, IMAGE_LOAD_FAILED: 2, THUMBNAIL_FAILED: 3 }, r.prototype.apply_settings = function () { var t = this; this.multi = this.$element.attr("multiple") && i, this.well_style = "well" == this.settings.style, this.well_style ? this.$element.parent().addClass("ace-file-multiple") : this.$element.parent().removeClass("ace-file-multiple"), this.$element.parent().find(":not(input[type=file])").remove(), this.$element.after('<span class="ace-file-container" data-title="' + this.settings.btn_choose + '"><span class="ace-file-name" data-title="' + this.settings.no_file + '">' + (this.settings.no_icon ? '<i class="' + ace.vars.icon + this.settings.no_icon + '"></i>' : "") + "</span></span>"), this.$label = this.$element.next(), this.$container = this.$element.closest(".ace-file-input"); var a = !!this.settings.icon_remove; if (a) { var s = e('<a class="remove" href="#"><i class="' + ace.vars.icon + this.settings.icon_remove + '"></i></a>').appendTo(this.$element.parent()); s.on(ace.click_event, function (e) { if (e.preventDefault(), !t.can_reset) return !1; var i = !0; if (t.settings.before_remove && (i = t.settings.before_remove.call(t.element)), !i) return !1; t.reset_input(); return !1 }) } this.settings.droppable && n && o.call(this) }, r.prototype.show_file_list = function (t) { var i = "undefined" == typeof t ? this.$element.data("ace_input_files") : t; if (i && 0 != i.length) { this.well_style && (this.$label.find(".ace-file-name").remove(), this.settings.btn_change || this.$label.addClass("hide-placeholder")), this.$label.attr("data-title", this.settings.btn_change).addClass("selected"); for (var n = 0; n < i.length; n++) { var s = "string" == typeof i[n] ? i[n] : e.trim(i[n].name), r = s.lastIndexOf("\\") + 1; 0 == r && (r = s.lastIndexOf("/") + 1), s = s.substr(r); var o = "fa fa-file", l = "file"; if (/\.(jpe?g|png|gif|svg|bmp|tiff?)$/i.test(s) ? (o = "fa fa-picture-o file-image", l = "image") : /\.(mpe?g|flv|mov|avi|swf|mp4|mkv|webm|wmv|3gp)$/i.test(s) ? (o = "fa fa-film file-video", l = "video") : /\.(mp3|ogg|wav|wma|amr|aac)$/i.test(s) && (o = "fa fa-music file-audio", l = "audio"), this.well_style) { this.$label.append('<span class="ace-file-name" data-title="' + s + '"><i class="' + ace.vars.icon + o + '"></i></span>'); var d = e.trim(i[n].type), h = a && this.settings.thumbnail && (d.length > 0 && d.match("image") || 0 == d.length && "image" == l); if (h) { var u = this; e.when(c.call(this, i[n])).fail(function (e) { u.settings.preview_error && u.settings.preview_error.call(u, s, e.code) }) } } else this.$label.find(".ace-file-name").attr({ "data-title": s }).find(ace.vars[".icon"]).attr("class", ace.vars.icon + o) } return !0 } }, r.prototype.reset_input = function () { this.reset_input_ui(), this.reset_input_field() }, r.prototype.reset_input_ui = function () { this.$label.attr({ "data-title": this.settings.btn_choose, "class": "ace-file-container" }).find(".ace-file-name:first").attr({ "data-title": this.settings.no_file, "class": "ace-file-name" }).find(ace.vars[".icon"]).attr("class", ace.vars.icon + this.settings.no_icon).prev("img").remove(), this.settings.no_icon || this.$label.find(ace.vars[".icon"]).remove(), this.$label.find(".ace-file-name").not(":first").remove(), this.reset_input_data() }, r.prototype.reset_input_field = function () { this.$element.wrap("<form>").parent().get(0).reset(), this.$element.unwrap() }, r.prototype.reset_input_data = function () { this.$element.data("ace_input_files") && (this.$element.removeData("ace_input_files"), this.$element.removeData("ace_input_method")) }, r.prototype.enable_reset = function (e) { this.can_reset = e }, r.prototype.disable = function () { this.disabled = !0, this.$element.attr("disabled", "disabled").addClass("disabled") }, r.prototype.enable = function () { this.disabled = !1, this.$element.removeAttr("disabled").removeClass("disabled") }, r.prototype.files = function () { return e(this).data("ace_input_files") || null }, r.prototype.method = function () { return e(this).data("ace_input_method") || "" }, r.prototype.update_settings = function (t) { this.settings = e.extend({}, this.settings, t), this.apply_settings() }, r.prototype.loading = function (t) { if (t === !1) this.$container.find(".ace-file-overlay").remove(), this.element.removeAttribute("readonly"); else { var i = "string" == typeof t ? t : '<i class="overlay-content fa fa-spin fa-spinner orange2 fa-2x"></i>', n = this.$container.find(".ace-file-overlay"); 0 == n.length && (n = e('<div class="ace-file-overlay"></div>').appendTo(this.$container), n.on("click tap", function (e) { return e.stopImmediatePropagation(), e.preventDefault(), !1 }), this.element.setAttribute("readonly", "true")), n.empty().append(i) } }; var o = function () { var e = this, t = this.$element.parent(); t.off("dragenter").on("dragenter", function (e) { e.preventDefault(), e.stopPropagation() }).off("dragover").on("dragover", function (e) { e.preventDefault(), e.stopPropagation() }).off("drop").on("drop", function (t) { t.preventDefault(), t.stopPropagation(); var i = t.originalEvent.dataTransfer, n = i.files; if (!e.multi && n.length > 1) { var a = []; a.push(n[0]), n = a } return n = h.call(e, n, !0), n === !1 ? !1 : (e.$element.data("ace_input_method", "drop"), e.$element.data("ace_input_files", n), e.show_file_list(n), e.$element.triggerHandler("change", [!0]), !0) }) }, l = function () { var e = this.element.files || [this.element.value]; return e = h.call(this, e, !1), e === !1 ? !1 : (this.$element.data("ace_input_method", "select"), this.$element.data("ace_input_files", e), this.show_file_list(e), !0) }, c = function (t) { var i = this, n = i.$label.find(".ace-file-name:last"), a = new e.Deferred, s = new FileReader; return s.onload = function (s) { n.prepend("<img class='middle' style='display:none;' />"); var o = n.find("img:last").get(0); e(o).one("load", function () { var s = 50; "large" == i.settings.thumbnail ? s = 150 : "fit" == i.settings.thumbnail && (s = n.width()), n.addClass(s > 50 ? "large" : ""); var l = d(o, s, t.type); if (null == l) return e(this).remove(), void a.reject({ code: r.error.THUMBNAIL_FAILED }); var c = l.w, h = l.h; "small" == i.settings.thumbnail && (c = h = s), e(o).css({ "background-image": "url(" + l.src + ")", width: c, height: h }).data("thumb", l.src).attr({ src: "data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg==" }).show(), a.resolve() }).one("error", function () { n.find("img").remove(), a.reject({ code: r.error.IMAGE_LOAD_FAILED }) }), o.src = s.target.result }, s.onerror = function () { a.reject({ code: r.error.FILE_LOAD_FAILED }) }, s.readAsDataURL(t), a.promise() }, d = function (t, i) { var n = t.width, a = t.height; n = n > 0 ? n : e(t).width(), a = a > 0 ? a : e(t).height(), (n > i || a > i) && (n > a ? (a = parseInt(i / n * a), n = i) : (n = parseInt(i / a * n), a = i)); var s; try { var r = document.createElement("canvas"); r.width = n, r.height = a; var o = r.getContext("2d"); o.drawImage(t, 0, 0, t.width, t.height, 0, 0, n, a), s = r.toDataURL() } catch (l) { s = null } return s ? (/^data\:image\/(png|jpe?g|gif);base64,[0-9A-Za-z\+\/\=]+$/.test(s) || (s = null), s ? { src: s, w: n, h: a } : null) : null }, h = function (e, t) { var i = p.call(this, e, t); return -1 === i ? (this.reset_input(), !1) : i && 0 != i.length ? ((i instanceof Array || n && i instanceof FileList) && (e = i), i = !0, this.settings.before_change && (i = this.settings.before_change.call(this.element, e, t)), -1 === i ? (this.reset_input(), !1) : i && 0 != i.length ? ((i instanceof Array || n && i instanceof FileList) && (e = i), e) : (this.$element.data("ace_input_files") || this.reset_input(), !1)) : (this.$element.data("ace_input_files") || this.reset_input(), !1) }, u = function (e) { return e ? ("string" == typeof e && (e = [e]), 0 == e.length ? null : new RegExp(".(?:" + e.join("|") + ")$", "i")) : null }, f = function (e) { return e ? ("string" == typeof e && (e = [e]), 0 == e.length ? null : new RegExp("^(?:" + e.join("|").replace(/\//g, "\\/") + ")$", "i")) : null }, p = function (t, i) { var n = u(this.settings.allowExt), a = u(this.settings.denyExt), r = f(this.settings.allowMime), o = f(this.settings.denyMime), l = this.settings.maxSize || !1; if (!(n || a || r || o || l)) return !0; for (var c = [], d = {}, h = 0; h < t.length; h++) { var p = t[h], v = s ? p.name : p; if (!n || n.test(v)) if (a && a.test(v)) "ext" in d || (d.ext = []), d.ext.push(v); else { var g; if (s) { if ((g = e.trim(p.type)).length > 0) { if (r && !r.test(g)) { "mime" in d || (d.mime = []), d.mime.push(v); continue } if (o && o.test(g)) { "mime" in d || (d.mime = []), d.mime.push(v); continue } } l && p.size > l ? ("size" in d || (d.size = []), d.size.push(v)) : c.push(p) } else c.push(p) } else "ext" in d || (d.ext = []), d.ext.push(v) } if (c.length == t.length) return t; var m = { ext: 0, mime: 0, size: 0 }; "ext" in d && (m.ext = d.ext.length), "mime" in d && (m.mime = d.mime.length), "size" in d && (m.size = d.size.length); var b; return this.$element.trigger(b = new e.Event("file.error.ace"), { file_count: t.length, invalid_count: t.length - c.length, error_list: d, error_count: m, dropped: i }), b.isDefaultPrevented() ? -1 : c }; e.fn.ace_file_input = function (i, n) { var a, s = this.each(function () { var t = e(this), s = t.data("ace_file_input"), o = "object" == typeof i && i; s || t.data("ace_file_input", s = new r(this, o)), "string" == typeof i && (a = s[i](n)) }); return a === t ? s : a }, e.fn.ace_file_input.defaults = { style: !1, no_file: "No File ...", no_icon: "fa fa-upload", btn_choose: "Choose", btn_change: "Change", icon_remove: "fa fa-times", droppable: !1, thumbnail: !1, allowExt: null, denyExt: null, allowMime: null, denyMime: null, maxSize: !1, before_change: null, before_remove: null, preview_error: null } }(window.jQuery), !function (e) { "use strict"; var t = function (t, i) { this.$element = e(t), this.options = e.extend({}, e.fn.typeahead.defaults, i), this.matcher = this.options.matcher || this.matcher, this.sorter = this.options.sorter || this.sorter, this.highlighter = this.options.highlighter || this.highlighter, this.updater = this.options.updater || this.updater, this.source = this.options.source, this.$menu = e(this.options.menu), this.shown = !1, this.listen() }; t.prototype = { constructor: t, select: function () { var e = this.$menu.find(".active").attr("data-value"); return this.$element.val(this.updater(e)).change(), this.hide() }, updater: function (e) { return e }, show: function () { var t = e.extend({}, this.$element.position(), { height: this.$element[0].offsetHeight }); return this.$menu.insertAfter(this.$element).css({ top: t.top + t.height, left: t.left }).show(), this.shown = !0, this }, hide: function () { return this.$menu.hide(), this.shown = !1, this }, lookup: function () { var t; return this.query = this.$element.val(), !this.query || this.query.length < this.options.minLength ? this.shown ? this.hide() : this : (t = e.isFunction(this.source) ? this.source(this.query, e.proxy(this.process, this)) : this.source, t ? this.process(t) : this) }, process: function (t) { var i = this; return t = e.grep(t, function (e) { return i.matcher(e) }), t = this.sorter(t), t.length ? this.render(t.slice(0, this.options.items)).show() : this.shown ? this.hide() : this }, matcher: function (e) { return ~e.toLowerCase().indexOf(this.query.toLowerCase()) }, sorter: function (e) { for (var t, i = [], n = [], a = []; t = e.shift() ;) t.toLowerCase().indexOf(this.query.toLowerCase()) ? ~t.indexOf(this.query) ? n.push(t) : a.push(t) : i.push(t); return i.concat(n, a) }, highlighter: function (e) { var t = this.query.replace(/[\-\[\]{}()*+?.,\\\^$|#\s]/g, "\\$&"); return e.replace(new RegExp("(" + t + ")", "ig"), function (e, t) { return "<strong>" + t + "</strong>" }) }, render: function (t) { var i = this; return t = e(t).map(function (t, n) { return t = e(i.options.item).attr("data-value", n), t.find("a").html(i.highlighter(n)), t[0] }), t.first().addClass("active"), this.$menu.html(t), this }, next: function () { var t = this.$menu.find(".active").removeClass("active"), i = t.next(); i.length || (i = e(this.$menu.find("li")[0])), i.addClass("active") }, prev: function () { var e = this.$menu.find(".active").removeClass("active"), t = e.prev(); t.length || (t = this.$menu.find("li").last()), t.addClass("active") }, listen: function () { this.$element.on("focus", e.proxy(this.focus, this)).on("blur", e.proxy(this.blur, this)).on("keypress", e.proxy(this.keypress, this)).on("keyup", e.proxy(this.keyup, this)), this.eventSupported("keydown") && this.$element.on("keydown", e.proxy(this.keydown, this)), this.$menu.on("click", e.proxy(this.click, this)).on("mouseenter", "li", e.proxy(this.mouseenter, this)).on("mouseleave", "li", e.proxy(this.mouseleave, this)) }, eventSupported: function (e) { var t = e in this.$element; return t || (this.$element.setAttribute(e, "return;"), t = "function" == typeof this.$element[e]), t }, move: function (e) { if (this.shown) { switch (e.keyCode) { case 9: case 13: case 27: e.preventDefault(); break; case 38: e.preventDefault(), this.prev(); break; case 40: e.preventDefault(), this.next() } e.stopPropagation() } }, keydown: function (t) { this.suppressKeyPressRepeat = ~e.inArray(t.keyCode, [40, 38, 9, 13, 27]), this.move(t) }, keypress: function (e) { this.suppressKeyPressRepeat || this.move(e) }, keyup: function (e) { switch (e.keyCode) { case 40: case 38: case 16: case 17: case 18: break; case 9: case 13: if (!this.shown) return; this.select(); break; case 27: if (!this.shown) return; this.hide(); break; default: this.lookup() } e.stopPropagation(), e.preventDefault() }, focus: function () { this.focused = !0 }, blur: function () { this.focused = !1, !this.mousedover && this.shown && this.hide() }, click: function (e) { e.stopPropagation(), e.preventDefault(), this.select(), this.$element.focus() }, mouseenter: function (t) { this.mousedover = !0, this.$menu.find(".active").removeClass("active"), e(t.currentTarget).addClass("active") }, mouseleave: function () { this.mousedover = !1, !this.focused && this.shown && this.hide() } }; var i = e.fn.typeahead; e.fn.typeahead = function (i) { return this.each(function () { var n = e(this), a = n.data("typeahead"), s = "object" == typeof i && i; a || n.data("typeahead", a = new t(this, s)), "string" == typeof i && a[i]() }) }, e.fn.typeahead.defaults = { source: [], items: 8, menu: '<ul class="typeahead dropdown-menu"></ul>', item: '<li><a href="#"></a></li>', minLength: 1 }, e.fn.typeahead.Constructor = t, e.fn.typeahead.noConflict = function () { return e.fn.typeahead = i, this }, e(document).on("focus.typeahead.data-api", '[data-provide="typeahead"]', function () { var t = e(this); t.data("typeahead") || t.typeahead(t.data()) }) }(window.jQuery), function (e) { e.fn.ace_wysiwyg = function (t) { var i = e.extend({ speech_button: !0, wysiwyg: {} }, t), n = ["#ac725e", "#d06b64", "#f83a22", "#fa573c", "#ff7537", "#ffad46", "#42d692", "#16a765", "#7bd148", "#b3dc6c", "#fbe983", "#fad165", "#92e1c0", "#9fe1e7", "#9fc6e7", "#4986e7", "#9a9cff", "#b99aff", "#c2c2c2", "#cabdbf", "#cca6ac", "#f691b2", "#cd74e6", "#a47ae2", "#444444"], a = { font: { values: ["Arial", "Courier", "Comic Sans MS", "Helvetica", "Open Sans", "Tahoma", "Verdana"], icon: "fa fa-font", title: "Font" }, fontSize: { values: { 5: "Huge", 3: "Normal", 1: "Small" }, icon: "fa fa-text-height", title: "Font Size" }, bold: { icon: "fa fa-bold", title: "Bold (Ctrl/Cmd+B)" }, italic: { icon: "fa fa-italic", title: "Italic (Ctrl/Cmd+I)" }, strikethrough: { icon: "fa fa-strikethrough", title: "Strikethrough" }, underline: { icon: "fa fa-underline", title: "Underline" }, insertunorderedlist: { icon: "fa fa-list-ul", title: "Bullet list" }, insertorderedlist: { icon: "fa fa-list-ol", title: "Number list" }, outdent: { icon: "fa fa-outdent", title: "Reduce indent (Shift+Tab)" }, indent: { icon: "fa fa-indent", title: "Indent (Tab)" }, justifyleft: { icon: "fa fa-align-left", title: "Align Left (Ctrl/Cmd+L)" }, justifycenter: { icon: "fa fa-align-center", title: "Center (Ctrl/Cmd+E)" }, justifyright: { icon: "fa fa-align-right", title: "Align Right (Ctrl/Cmd+R)" }, justifyfull: { icon: "fa fa-align-justify", title: "Justify (Ctrl/Cmd+J)" }, createLink: { icon: "fa fa-link", title: "Hyperlink", button_text: "Add", placeholder: "URL", button_class: "btn-primary" }, unlink: { icon: "fa fa-chain-broken", title: "Remove Hyperlink" }, insertImage: { icon: "fa fa-picture-o", title: "Insert picture", button_text: '<i class="' + ace.vars.icon + 'fa fa-file"></i> Choose Image &hellip;', placeholder: "Image URL", button_insert: "Insert", button_class: "btn-success", button_insert_class: "btn-primary", choose_file: !0 }, foreColor: { values: n, title: "Change Color" }, backColor: { values: n, title: "Change Background Color" }, undo: { icon: "fa fa-undo", title: "Undo (Ctrl/Cmd+Z)" }, redo: { icon: "fa fa-repeat", title: "Redo (Ctrl/Cmd+Y)" }, viewSource: { icon: "fa fa-code", title: "View Source" } }, s = i.toolbar || ["font", null, "fontSize", null, "bold", "italic", "strikethrough", "underline", null, "insertunorderedlist", "insertorderedlist", "outdent", "indent", null, "justifyleft", "justifycenter", "justifyright", "justifyfull", null, "createLink", "unlink", null, "insertImage", null, "foreColor", null, "undo", "redo", null, "viewSource"]; return this.each(function () { var t = ' <div class="wysiwyg-toolbar btn-toolbar center"> <div class="btn-group"> '; for (var n in s) if (s.hasOwnProperty(n)) { var r = s[n]; if (null === r) { t += ' </div> <div class="btn-group"> '; continue } if ("string" == typeof r && r in a) r = a[r], r.name = s[n]; else { if (!("object" == typeof r && r.name in a)) continue; r = e.extend(a[r.name], r) } var o = "className" in r ? r.className : "btn-default"; switch (r.name) { case "font": t += ' <a class="btn btn-sm ' + o + ' dropdown-toggle" data-toggle="dropdown" title="' + r.title + '"><i class="' + ace.vars.icon + r.icon + '"></i><i class="' + ace.vars.icon + 'fa fa-angle-down icon-on-right"></i></a> ', t += ' <ul class="dropdown-menu dropdown-light dropdown-caret">'; for (var l in r.values) r.values.hasOwnProperty(l) && (t += ' <li><a data-edit="fontName ' + r.values[l] + '" style="font-family:\'' + r.values[l] + "'\">" + r.values[l] + "</a></li> "); t += " </ul>"; break; case "fontSize": t += ' <a class="btn btn-sm ' + o + ' dropdown-toggle" data-toggle="dropdown" title="' + r.title + '"><i class="' + ace.vars.icon + r.icon + '"></i>&nbsp;<i class="' + ace.vars.icon + 'fa fa-angle-down icon-on-right"></i></a> ', t += ' <ul class="dropdown-menu dropdown-light dropdown-caret"> '; for (var c in r.values) r.values.hasOwnProperty(c) && (t += ' <li><a data-edit="fontSize ' + c + '"><font size="' + c + '">' + r.values[c] + "</font></a></li> "); t += " </ul> "; break; case "createLink": t += ' <div class="btn-group"> <a class="btn btn-sm ' + o + ' dropdown-toggle" data-toggle="dropdown" title="' + r.title + '"><i class="' + ace.vars.icon + r.icon + '"></i></a> ', t += ' <div class="dropdown-menu dropdown-caret dropdown-menu-right">							 <div class="input-group">								<input class="form-control" placeholder="' + r.placeholder + '" type="text" data-edit="' + r.name + '" />								<span class="input-group-btn">									<button class="btn btn-sm ' + r.button_class + '" type="button">' + r.button_text + "</button>								</span>							 </div>						</div> </div>"; break; case "insertImage": t += ' <div class="btn-group"> <a class="btn btn-sm ' + o + ' dropdown-toggle" data-toggle="dropdown" title="' + r.title + '"><i class="' + ace.vars.icon + r.icon + '"></i></a> ', t += ' <div class="dropdown-menu dropdown-caret dropdown-menu-right">							 <div class="input-group">								<input class="form-control" placeholder="' + r.placeholder + '" type="text" data-edit="' + r.name + '" />								<span class="input-group-btn">									<button class="btn btn-sm ' + r.button_insert_class + '" type="button">' + r.button_insert + "</button>								</span>							 </div>", r.choose_file && "FileReader" in window && (t += '<div class="space-2"></div>							 <label class="center block no-margin-bottom">								<button class="btn btn-sm ' + r.button_class + ' wysiwyg-choose-file" type="button">' + r.button_text + '</button>								<input type="file" data-edit="' + r.name + '" />							  </label>'), t += " </div> </div>"; break; case "foreColor": case "backColor": t += ' <select class="hide wysiwyg_colorpicker" title="' + r.title + '"> '; for (var d in r.values) t += ' <option value="' + r.values[d] + '">' + r.values[d] + "</option> "; t += " </select> ", t += ' <input style="display:none;" disabled class="hide" type="text" data-edit="' + r.name + '" /> '; break; case "viewSource": t += ' <a class="btn btn-sm ' + o + '" data-view="source" title="' + r.title + '"><i class="' + ace.vars.icon + r.icon + '"></i></a> '; break; default: t += ' <a class="btn btn-sm ' + o + '" data-edit="' + r.name + '" title="' + r.title + '"><i class="' + ace.vars.icon + r.icon + '"></i></a> ' } } t += " </div> "; var h; i.speech_button && "onwebkitspeechchange" in (h = document.createElement("input")) && (t += ' <input class="wysiwyg-speech-input" type="text" data-edit="inserttext" x-webkit-speech />'), h = null, t += " </div> ", t = i.toolbar_place ? i.toolbar_place.call(this, t) : e(this).before(t).prev(), t.find("a[title]").tooltip({ animation: !1, container: "body" }), t.find(".dropdown-menu input[type=text]").on("click", function () { return !1 }).on("change", function () { e(this).closest(".dropdown-menu").siblings(".dropdown-toggle").dropdown("toggle") }).on("keydown", function (t) { 27 == t.which ? (this.value = "", e(this).change()) : 13 == t.which && (t.preventDefault(), t.stopPropagation(), e(this).change()) }), t.find("input[type=file]").prev().on(ace.click_event, function () { e(this).next().click() }), t.find(".wysiwyg_colorpicker").each(function () { e(this).ace_colorpicker({ pull_right: !0 }).change(function () { e(this).nextAll("input").eq(0).val(this.value).change() }).next().find(".btn-colorpicker").tooltip({ title: this.title, animation: !1, container: "body" }) }); var u = e(this), f = !1; t.find("a[data-view=source]").on("click", function (t) { if (t.preventDefault(), f) { var i = u.next(); u.html(i.val()).show(), i.remove(), e(this).removeClass("active") } else e("<textarea />").css({ width: u.outerWidth(), height: u.outerHeight() }).val(u.html()).insertAfter(u), u.hide(), e(this).addClass("active"); f = !f }); var p = e.extend({}, { activeToolbarClass: "active", toolbarSelector: t }, i.wysiwyg || {}); e(this).wysiwyg(p) }), this } }(window.jQuery), function (e, t) { function i(t, i) { var n = i.max; n = ("" + n).length; var a = parseInt(Math.max(20 * n + 40, 90)), s = e(t); s.addClass("spinner-input form-control").wrap('<div class="ace-spinner">'); var r = s.closest(".ace-spinner").spinner(i).wrapInner("<div class='input-group'></div>"), o = r.data("spinner"); i.on_sides ? (s.before('<div class="spinner-buttons input-group-btn">					<button type="button" class="btn spinner-down btn-xs ' + i.btn_down_class + '">						<i class="' + ace.vars.icon + i.icon_down + '"></i>					</button>				</div>').after('<div class="spinner-buttons input-group-btn">					<button type="button" class="btn spinner-up btn-xs ' + i.btn_up_class + '">						<i class="' + ace.vars.icon + i.icon_up + '"></i>					</button>				</div>'), r.addClass("touch-spinner"), r.css("width", a + "px")) : (s.after('<div class="spinner-buttons input-group-btn">					<button type="button" class="btn spinner-up btn-xs ' + i.btn_up_class + '">						<i class="' + ace.vars.icon + i.icon_up + '"></i>					</button>					<button type="button" class="btn spinner-down btn-xs ' + i.btn_down_class + '">						<i class="' + ace.vars.icon + i.icon_down + '"></i>					</button>				</div>'), ace.vars.touch || i.touch_spinner ? (r.addClass("touch-spinner"), r.css("width", a + "px")) : (s.next().addClass("btn-group-vertical"), r.css("width", a + "px"))), s.on("mousewheel.spinner DOMMouseScroll.spinner", function (e) { var t = e.originalEvent.detail < 0 || e.originalEvent.wheelDelta > 0 ? 1 : -1; return o.step(t > 0), o.triggerChangedEvent(), !1 }), r.on("changed", function () { s.trigger("change") }), this._call = function (e, t) { o[e](t) } } e.fn.ace_spinner = function (n, a) { var s, r = this.each(function () { var t = e(this), r = t.data("ace_spinner"), o = "object" == typeof n && n; r || (o = e.extend({}, e.fn.ace_spinner.defaults, n), t.data("ace_spinner", r = new i(this, o))), "string" == typeof n && (s = r._call(n, a)) }); return s === t ? r : s }, e.fn.ace_spinner.defaults = { icon_up: "fa fa-chevron-up", icon_down: "fa fa-chevron-down", on_sides: !1, btn_up_class: "", btn_down_class: "", max: 999, touch_spinner: !1 } }(window.jQuery), function (e) { var t = { "open-icon": ace.vars.icon + "fa fa-folder-open", "close-icon": ace.vars.icon + "fa fa-folder", selectable: !0, "selected-icon": ace.vars.icon + "fa fa-check", "unselected-icon": ace.vars.icon + "fa fa-times" }; e.fn.ace_tree = function (i) { return t = e.extend({}, t, i), this.each(function () { var i = e(this); i.html('<div class="tree-folder" style="display:none;">				<div class="tree-folder-header">					<i class="' + ace.vars.icon + t["close-icon"] + '"></i>					<div class="tree-folder-name"></div>				</div>				<div class="tree-folder-content"></div>				<div class="tree-loader" style="display:none"></div>			</div>			<div class="tree-item" style="display:none;">				' + (null == t["unselected-icon"] ? "" : '<i class="' + ace.vars.icon + t["unselected-icon"] + '"></i>') + '				<div class="tree-item-name"></div>			</div>'), i.addClass(1 == t.selectable ? "tree-selectable" : "tree-unselectable"), i.tree(t) }), this } }(window.jQuery), function (e) { e.fn.ace_wizard = function (t) { return this.each(function () { var i = e(this); i.wizard(); var n = i.siblings(".wizard-actions").eq(0), a = i.data("wizard"); a.$prevBtn.remove(), a.$nextBtn.remove(), a.$prevBtn = n.find(".btn-prev").eq(0).on(ace.click_event, function () { a.previous() }).attr("disabled", "disabled"), a.$nextBtn = n.find(".btn-next").eq(0).on(ace.click_event, function () { a.next() }).removeAttr("disabled"), a.nextText = a.$nextBtn.text(); var s = t && (t.selectedItem && t.selectedItem.step || t.step); s && (a.currentStep = s, a.setState()) }), this } }(window.jQuery);
/*ace*/
"ace" in window || (window.ace = {}),
"helper" in window.ace || (window.ace.helper = {}),
"vars" in window.ace || (window.ace.vars = {
    icon: " ace-icon ",
    ".icon": ".ace-icon"
}),
ace.vars.touch = "ontouchstart" in document.documentElement,
jQuery(function (e) {
    ace.click_event = ace.vars.touch && e.fn.tap ? "tap" : "click";
    var a = navigator.userAgent;
    ace.vars.webkit = !!a.match(/AppleWebKit/i),
    ace.vars.safari = !!a.match(/Safari/i) && !a.match(/Chrome/i),
    ace.vars.android = ace.vars.safari && !!a.match(/Android/i),
    ace.vars.ios_safari = !!a.match(/OS ([4-8])(_\d)+ like Mac OS X/i) && !a.match(/CriOS/i),
    ace.vars.non_auto_fixed = ace.vars.android || ace.vars.ios_safari,
    ace.vars.non_auto_fixed && e("body").addClass("mob-safari"),
    ace.vars.transition = "transition" in document.body.style || "WebkitTransition" in document.body.style || "MozTransition" in document.body.style || "OTransition" in document.body.style;
    var t = {
        general_vars: null,
        handle_side_menu: null,
        add_touch_drag: null,
        sidebar_scrollable: [!0, !0, !1 || ace.vars.safari || ace.vars.ios_safari, 200, !1],
        sidebar_hoverable: null,
        general_things: null,
        widget_boxes: null,
        widget_reload_handler: null,
        settings_box: null,
        settings_rtl: null,
        settings_skin: null,
        enable_searchbox_autocomplete: null,
        auto_hide_sidebar: null,
        auto_padding: null,
        auto_container: null
    };
    for (var s in t) if (s in ace) {
        var i = t[s];
        i !== !1 && (null == i ? i = [jQuery] : i instanceof String ? i = [jQuery, i] : i instanceof Array && i.unshift(jQuery), ace[s].apply(null, i))
    }
}),
ace.general_vars = function (e) {
    var a = "menu-min",
    t = "responsive-min",
    s = "h-sidebar",
    i = e("#sidebar").eq(0);
    ace.vars.mobile_style = 1,
    i.hasClass("responsive") && !e("#menu-toggler").hasClass("navbar-toggle") ? ace.vars.mobile_style = 2 : i.hasClass(t) ? ace.vars.mobile_style = 3 : i.hasClass("navbar-collapse") && (ace.vars.mobile_style = 4),
    e(window).on("resize.ace.vars",
    function () {
        ace.vars.window = {
            width: parseInt(e(this).width()),
            height: parseInt(e(this).height())
        },
        ace.vars.mobile_view = ace.vars.mobile_style < 4 && ace.helper.mobile_view(),
        ace.vars.collapsible = !ace.vars.mobile_view && ace.helper.collapsible(),
        ace.vars.nav_collapse = (ace.vars.collapsible || ace.vars.mobile_view) && e("#navbar").hasClass("navbar-collapse");
        var i = e(document.getElementById("sidebar"));
        ace.vars.minimized = !ace.vars.collapsible && i.hasClass(a) || 3 == ace.vars.mobile_style && ace.vars.mobile_view && i.hasClass(t),
        ace.vars.horizontal = !(ace.vars.mobile_view || ace.vars.collapsible) && i.hasClass(s)
    }).triggerHandler("resize.ace.vars")
},
ace.general_things = function (e) {
    var a = !!e.fn.ace_scroll;
    a && e(".dropdown-content").ace_scroll({
        reset: !1,
        mouseWheelLock: !0
    }),
    e(window).on("resize.reset_scroll",
    function () {
        a && e(".ace-scroll").ace_scroll("reset")
    }),
    e(document).on("settings.ace.reset_scroll",
    function (t, s) {
        "sidebar_collapsed" == s && a && e(".ace-scroll").ace_scroll("reset")
    }),
    e(document).on("click.dropdown.pos", '.dropdown-toggle[data-position="auto"]',
    function () {
        var a = e(this).offset(),
        t = e(this.parentNode);
        parseInt(a.top + e(this).height()) + 50 > ace.helper.scrollTop() + ace.helper.winHeight() - t.find(".dropdown-menu").eq(0).height() ? t.addClass("dropup") : t.removeClass("dropup")
    }),
    e(document).on("click", ".dropdown-navbar .nav-tabs",
    function (a) {
        a.stopPropagation(); {
            var t;
            a.target
        } (t = e(a.target).closest("[data-toggle=tab]")) && t.length > 0 && (t.tab("show"), a.preventDefault())
    }),
    e('.ace-nav [class*="icon-animated-"]').closest("a").one("click",
    function () {
        var a = e(this).find('[class*="icon-animated-"]').eq(0),
        t = a.attr("class").match(/icon\-animated\-([\d\w]+)/);
        a.removeClass(t[0])
    }),
    e(".sidebar .nav-list .badge[title],.sidebar .nav-list .badge[title]").each(function () {
        var a = e(this).attr("class").match(/tooltip\-(?:\w+)/);
        a = a ? a[0] : "tooltip-error",
        e(this).tooltip({
            placement: function (a, t) {
                var s = e(t).offset();
                return parseInt(s.left) < parseInt(document.body.scrollWidth / 2) ? "right" : "left"
            },
            container: "body",
            template: '<div class="tooltip ' + a + '"><div class="tooltip-arrow"></div><div class="tooltip-inner"></div></div>'
        })
    });
    var t = e(".btn-scroll-up");
    if (t.length > 0) {
        var s = !1;
        e(window).on("scroll.scroll_btn",
        function () {
            ace.helper.scrollTop() > parseInt(ace.helper.winHeight() / 4) ? s || (t.addClass("display"), s = !0) : s && (t.removeClass("display"), s = !1)
        }).triggerHandler("scroll.scroll_btn"),
        t.on(ace.click_event,
        function () {
            var a = Math.min(500, Math.max(100, parseInt(ace.helper.scrollTop() / 3)));
            return e("html,body").animate({
                scrollTop: 0
            },
            a),
            !1
        })
    }
    if (ace.vars.webkit) {
        var i = e(".ace-nav").get(0);
        i && e(window).on("resize.webkit",
        function () {
            ace.helper.redraw(i)
        })
    }
},
ace.helper.collapsible = function () {
    var e;
    return null != document.querySelector("#sidebar.navbar-collapse") && null != (e = document.querySelector('.navbar-toggle[data-target*=".sidebar"]')) && e.scrollHeight > 0
},
ace.helper.mobile_view = function () {
    var e;
    return null != (e = document.getElementById("menu-toggler")) && e.scrollHeight > 0
},
ace.helper.redraw = function (e) {
    var a = e.style.display;
    e.style.display = "none",
    e.offsetHeight,
    e.style.display = a
},
ace.helper.scrollTop = function () {
    return document.scrollTop || document.documentElement.scrollTop || document.body.scrollTop
},
ace.helper.winHeight = function () {
    return window.innerHeight || document.documentElement.clientHeight
},
ace.helper.camelCase = function (e) {
    return e.replace(/-([\da-z])/gi,
    function (e, a) {
        return a ? a.toUpperCase() : ""
    })
},
ace.helper.removeStyle = "removeProperty" in document.body.style ?
function (e, a) {
    e.style.removeProperty(a)
} : function (e, a) {
    e.style[ace.helper.camelCase(a)] = ""
},
ace.helper.hasClass = "classList" in document.documentElement ?
function (e, a) {
    return e.classList.contains(a)
} : function (e, a) {
    return e.className.indexOf(a) > -1
},
ace.add_touch_drag = function (e) {
    if (ace.vars.touch) {
        var a = "touchstart MSPointerDown pointerdown",
        t = "touchend touchcancel MSPointerUp MSPointerCancel pointerup pointercancel",
        s = "touchmove MSPointerMove MSPointerHover pointermove";
        e.event.special.ace_drag = {
            setup: function () {
                var i = 0,
                n = e(this);
                n.on(a,
                function (a) {
                    function r(e) {
                        if (c) {
                            var a = e.originalEvent.touches ? e.originalEvent.touches[0] : e;
                            if (o = {
                                coords: [a.pageX, a.pageY]
                            },
                            c && o && (h = 0, u = 0, d = Math.abs(u = c.coords[1] - o.coords[1]) > i && Math.abs(h = c.coords[0] - o.coords[0]) <= Math.abs(u) ? u > 0 ? "up" : "down" : Math.abs(h = c.coords[0] - o.coords[0]) > i && Math.abs(u) <= Math.abs(h) ? h > 0 ? "left" : "right" : !1, d !== !1)) {
                                var t = {};
                                c.origin.trigger({
                                    type: "ace_drag",
                                    direction: d,
                                    dx: h,
                                    dy: u,
                                    retval: t
                                }),
                                e.preventDefault()
                            }
                            c.coords[0] = o.coords[0],
                            c.coords[1] = o.coords[1]
                        }
                    }
                    var o, l = a.originalEvent.touches ? a.originalEvent.touches[0] : a,
                    c = {
                        coords: [l.pageX, l.pageY],
                        origin: e(a.target)
                    };
                    c.origin.trigger({
                        type: "ace_dragStart",
                        start: c || [-1, -1]
                    });
                    var d = !1,
                    h = 0,
                    u = 0;
                    n.on(s, r).one(t,
                    function () {
                        n.off(s, r),
                        c.origin.trigger({
                            type: "ace_dragEnd",
                            stop: o || [-1, -1]
                        }),
                        c = o = void 0
                    })
                })
            }
        }
    }
},
ace.handle_side_menu = function (e) {
    var a = e(".sidebar").eq(0);
    e(document).on(ace.click_event + ".ace.menu", "#menu-toggler",
    function () {
        return a.toggleClass("display"),
        e(this).toggleClass("display"),
        e(this).hasClass("display") && "sidebar_scroll" in ace.helper && ace.helper.sidebar_scroll.reset(),
        !1
    }).on(ace.click_event + ".ace.menu", ".sidebar-collapse",
    function () {
        ace.vars.collapsible || ace.vars.horizontal || (ace.vars.minimized = !ace.vars.minimized)
    }).on(ace.click_event + ".ace.menu", ".sidebar-expand",
    function () {
        var t = e(this).find(ace.vars[".icon"]),
        s = t.attr("data-icon1"),
        i = t.attr("data-icon2");
        a.hasClass("responsive-min") ? (t.removeClass(s).addClass(i), a.removeClass("responsive-min"), a.addClass("display responsive-max"), ace.vars.minimized = !1) : (t.removeClass(i).addClass(s), a.removeClass("display responsive-max"), a.addClass("responsive-min"), ace.vars.minimized = !0),
        e(document).triggerHandler("settings.ace", ["sidebar_collapsed", ace.vars.minimized])
    });
    var t = ace.vars.ios_safari;
    e(document).on(ace.click_event + ".ace.submenu", ".sidebar .nav-list",
    function (a) {
        var s = this,
        i = e(a.target).closest("a");
        if (i && 0 != i.length) {
            var n = ace.vars.minimized && !ace.vars.collapsible;
            if (i.hasClass("dropdown-toggle")) {
                var r = i.siblings(".submenu").get(0);
                if (!r) return !1;
                var o = 0,
                l = 250,
                c = r.parentNode.parentNode;
                if (n && c == s || e(r.parentNode).hasClass("hover") && !ace.vars.collapsible) return a.preventDefault(),
                !1;
                0 == r.scrollHeight && e(c).find("> .open > .submenu").each(function () {
                    this == r || e(this.parentNode).hasClass("active") || (o -= this.scrollHeight, ace.submenu.hide(this, l))
                });
                var d = 0;
                return 1 == (d = ace.submenu.toggle(r, l)) ? 0 != o && (o += r.scrollHeight) : -1 == d && (o -= r.scrollHeight),
                0 != o && "sidebar_scroll" in ace.helper && ace.helper.sidebar_scroll.prehide(o),
                a.preventDefault(),
                !1
            }
            if ("tap" == ace.click_event && n && i.get(0).parentNode.parentNode == s) {
                var h = i.find(".menu-text").get(0);
                if (a.target != h && !e.contains(h, a.target)) return a.preventDefault(),
                !1
            }
            if (t && "false" !== i.attr("data-link")) return document.location = i.attr("href"),
            a.preventDefault(),
            !1
        }
    })
},
ace.submenu = {
    show: function (e, a) {
        var t, s = $(e);
        if (s.trigger(t = $.Event("show.ace.submenu")), t.isDefaultPrevented()) return !1;
        s.css({
            height: 0,
            overflow: "hidden",
            display: "block"
        }).removeClass("nav-hide").addClass("nav-show").parent().addClass("open"),
        a > 0 && s.css({
            height: e.scrollHeight,
            "transition-property": "height",
            "transition-duration": a / 1e3 + "s"
        });
        var i = function (e) {
            e && e.stopPropagation(),
            s.css({
                "transition-property": "",
                "transition-duration": "",
                overflow: "",
                height: ""
            }),
            ace.vars.transition && s.off(".trans"),
            s.trigger($.Event("shown.ace.submenu"))
        };
        return a > 0 && ace.vars.transition ? s.one("transitionend.trans webkitTransitionEnd.trans mozTransitionEnd.trans oTransitionEnd.trans", i) : i(),
        ace.vars.android && setTimeout(function () {
            s.css({
                overflow: "",
                height: ""
            })
        },
        a + 10),
        !0
    },
    hide: function (e, a) {
        var t, s = $(e);
        if (s.trigger(t = $.Event("hide.ace.submenu")), t.isDefaultPrevented()) return !1;
        s.css({
            height: e.scrollHeight,
            overflow: "hidden"
        }).parent().removeClass("open"),
        e.offsetHeight,
        a > 0 && s.css({
            height: 0,
            "transition-property": "height",
            "transition-duration": a / 1e3 + "s"
        });
        var i = function (e) {
            e && e.stopPropagation(),
            s.css({
                display: "none",
                overflow: "",
                height: "",
                "transition-property": "",
                "transition-duration": ""
            }).removeClass("nav-show").addClass("nav-hide"),
            ace.vars.transition && s.off(".trans"),
            s.trigger($.Event("hidden.ace.submenu"))
        };
        return a > 0 && ace.vars.transition ? s.one("transitionend.trans webkitTransitionEnd.trans mozTransitionEnd.trans oTransitionEnd.trans", i) : i(),
        ace.vars.android && setTimeout(function () {
            s.css({
                display: "none",
                overflow: "",
                height: ""
            })
        },
        a + 10),
        !0
    },
    toggle: function (e, a) {
        if (0 == e.scrollHeight) {
            if (ace.submenu.show(e, a)) return 1
        } else if (ace.submenu.hide(e, a)) return -1;
        return 0
    }
},
ace.sidebar_scrollable = function (e, a, t, s, i, n) {
    if (e.fn.ace_scroll) {
        var r = ace.vars.safari && navigator.userAgent.match(/version\/[1-5]/i),
        o = e(".sidebar"),
        l = (e(".navbar"), o.find(".nav-list")),
        c = o.find(".sidebar-toggle"),
        d = o.find(".sidebar-shortcuts"),
        h = e(window),
        u = o.get(0),
        v = l.get(0);
        if (u && v) {
            var p, f, g = null,
            m = null,
            b = null,
            w = null,
            _ = null,
            y = !1,
            C = !1,
            a = a || !1,
            t = t || !1,
            s = s || !1,
            k = "getComputedStyle" in window ?
            function () {
                return u.offsetHeight,
                "fixed" == window.getComputedStyle(u).position
            } : function () {
                return u.offsetHeight,
                "fixed" == o.css("position")
            },
            x = k(),
            T = o.hasClass("h-sidebar"),
            H = ace.helper.sidebar_scroll = {
                available_height: function () {
                    var e = l.parent().offset();
                    return x && (e.top -= ace.helper.scrollTop()),
                    h.innerHeight() - e.top - (s ? 0 : c.outerHeight())
                },
                content_height: function () {
                    return v.scrollHeight
                },
                initiate: function (h) {
                    if (!C && x) {
                        l.wrap('<div style="position: relative;" />'),
                        l.after("<div><div></div></div>"),
                        l.wrap('<div class="nav-wrap" />'),
                        s || c.css({
                            "z-index": 1
                        }),
                        t || d.css({
                            "z-index": 99
                        }),
                        g = l.parent().next().ace_scroll({
                            size: H.available_height(),
                            reset: !0,
                            mouseWheelLock: !0,
                            hoverReset: !1,
                            dragEvent: !0,
                            touchDrag: !1
                        }).closest(".ace-scroll").addClass("nav-scroll"),
                        _ = g.data("ace_scroll"),
                        m = g.find(".scroll-content").eq(0),
                        b = m.find(" > div").eq(0),
                        w = g.find(".scroll-bar").eq(0),
                        t && (l.parent().prepend(d).wrapInner("<div />"), l = l.parent()),
                        s && (l.append(c), l.closest(".nav-wrap").addClass("nav-wrap-t")),
                        l.css({
                            position: "relative"
                        }),
                        n === !0 && g.addClass("scrollout"),
                        v = l.get(0),
                        v.style.top = 0,
                        m.on("scroll.nav",
                        function () {
                            v.style.top = -1 * this.scrollTop + "px"
                        }),
                        l.on("mousewheel.ace_scroll DOMMouseScroll.ace_scroll",
                        function (e) {
                            return g.trigger(e)
                        });
                        var u = m.get(0);
                        if (l.on("ace_drag.nav",
                        function (e) {
                            if (y && ("up" == e.direction || "down" == e.direction)) {
                                _.move_bar(!0),
                                move_nav = !1;
                                var a = e.dy;
                                Math.abs(a) > 20 && (a = 2 * a),
                                0 != a && (u.scrollTop = u.scrollTop + a, v.style.top = -1 * u.scrollTop + "px")
                        }
                        }), i && l.on("ace_dragStart.nav",
                        function (e) {
                            e.stopPropagation(),
                            l.css("transition-property", "none"),
                            w.css("transition-property", "none")
                        }).on("ace_dragEnd.nav",
                        function (e) {
                            e.stopPropagation(),
                            l.css("transition-property", "top"),
                            w.css("transition-property", "top")
                        }), r && !s) {
                            var p = c.get(0);
                            p && m.on("scroll.safari",
                            function () {
                                ace.helper.redraw(p)
                            })
                        }
                        if (C = !0, 1 == h) {
                            if (H.reset(), a && _.is_active()) {
                                var f, k = o.find(".nav-list");
                                ace.vars.minimized && !ace.vars.collapsible ? f = k.find("> .active") : (f = l.find("> .active.hover"), 0 == f.length && (f = l.find(".active:not(.open)")));
                                var T = f.outerHeight();
                                k = k.get(0);
                                try {
                                    for (var z = f.get(0) ; z != k;) T += z.offsetTop,
                                    z = z.parentNode;
                                    var E = T - g.height();
                                    E > 0 && (v.style.top = -E + "px", m.scrollTop(E))
                                } catch (e) { }
                            }
                            a = !1
                        }
                        if ("number" == typeof i && i > 0 && (l.css({
                            "transition-property": "top",
                            "transition-duration": (i / 1e3).toFixed(2) + "s"
                        }), w.css({
                            "transition-property": "top",
                            "transition-duration": (i / 1500).toFixed(2) + "s"
                        }), g.on("drag.start",
                        function (e) {
                            e.stopPropagation(),
                            l.css("transition-property", "none")
                        }).on("drag.end",
                        function (e) {
                            e.stopPropagation(),
                            l.css("transition-property", "top")
                        })), ace.vars.android) {
                            var S = ace.helper.scrollTop();
                            2 > S && (window.scrollTo(S, 0), setTimeout(function () {
                                H.reset()
                            },
                            20));
                            var M, P = ace.helper.winHeight();
                            e(window).on("scroll.ace_scroll",
                            function () {
                                y && _.is_active() && (M = ace.helper.winHeight(), M != P && (P = M, H.reset()))
                            })
                        }
                    }
                },
                reset: function () {
                    if (!x) return void H.disable();
                    C || H.initiate();
                    var e = !ace.vars.collapsible && (!T || T && ace.vars.mobile_view) && (p = H.available_height()) < (f = v.scrollHeight);
                    y = !0,
                    e && (b.css({
                        height: f,
                        width: 8
                    }), g.prev().css({
                        "max-height": p
                    }), _.update({
                        size: p
                    }).enable().reset()),
                    e && _.is_active() ? o.addClass("sidebar-scroll") : y && H.disable()
                },
                disable: function () {
                    y = !1,
                    g && (g.css({
                        height: "",
                        "max-height": ""
                    }), b.css({
                        height: "",
                        width: ""
                    }), g.prev().css({
                        "max-height": ""
                    }), _.disable()),
                    parseInt(v.style.top) < 0 && i && ace.vars.transition ? l.one("transitionend.trans webkitTransitionEnd.trans mozTransitionEnd.trans oTransitionEnd.trans",
                    function () {
                        o.removeClass("sidebar-scroll"),
                        l.off(".trans")
                    }) : o.removeClass("sidebar-scroll"),
                    v.style.top = 0
                },
                prehide: function (e) {
                    if (y && !ace.vars.minimized) if (H.content_height() + e < H.available_height()) H.disable();
                    else if (0 > e) {
                        var a = m.scrollTop() + e;
                        if (0 > a) return;
                        v.style.top = -1 * a + "px"
                    }
                }
            };
            H.initiate(!0),
            e(document).on("settings.ace.scroll",
            function (e, a) {
                "sidebar_collapsed" == a && x ? H.reset() : ("sidebar_fixed" === a || "navbar_fixed" === a) && (x = k(), x && !y ? H.reset() : x || H.disable())
            }),
            h.on("resize.ace.scroll",
            function () {
                x = k(),
                H.reset()
            }),
            o.on("hidden.ace.submenu shown.ace.submenu", ".submenu",
            function (e) {
                e.stopPropagation(),
                ace.vars.minimized || (ace.vars.webkit ? setTimeout(function () {
                    H.reset()
                },
                0) : H.reset())
            })
        }
    }
},
ace.sidebar_hoverable = function (e) {
    function a(a) {
        var t = e(a);
        a.style.removeProperty("top"),
        a.style.removeProperty("bottom");
        var s = null;
        ace.vars.minimized && (s = a.parentNode.querySelector(".menu-text")) && s.style.removeProperty("margin-top");
        var i = t.offset(),
        n = ace.helper.scrollTop(),
        o = !1,
        d = n;
        l && (d += r.clientHeight + 1);
        var h = a.scrollHeight;
        s && (h += 40, i.top -= 40);
        var u, v = parseInt(i.top + h);
        if ((u = v - (window.innerHeight + n - 50)) > 0) if (c > h - u && i.top - u > d) a.style.top = "auto",
        a.style.bottom = "-10px",
        s && (s.style.marginTop = -(h - 50) + "px", o = !0);
        else {
            i.top - u < d && (u = i.top - d),
            v - u < i.top + c && (u -= c);
            var p = s ? 40 : 20;
            u > p && (a.style.top = -u + "px", s && (s.style.marginTop = -u + "px", o = !0))
        }
        var f = this.className.lastIndexOf("pull_up");
        o ? -1 == f && (this.className = this.className + " pull_up") : f >= 0 && (this.className = this.className.replace(/(^|\s)pull_up($|\s)/, "")),
        ace.vars.safari && ace.helper.redraw(a)
    }
    if ("querySelector" in document && "removeProperty" in document.body.style) {
        ace.helper.sidebar_hover = {
            reset: function () {
                s.find(".submenu").each(function () {
                    var a = this,
                    t = this.parentNode;
                    if (a) {
                        a.style.removeProperty("top"),
                        a.style.removeProperty("bottom");
                        var s = t.querySelector(".menu-text");
                        s && s.style.removeProperty("margin-top")
                    }
                    t.className.lastIndexOf("_up") >= 0 && e(t).removeClass("pull_up")
                })
            }
        };
        var t = "getComputedStyle" in window ?
        function () {
            return r.offsetHeight,
            "fixed" == window.getComputedStyle(r).position
        } : function () {
            return r.offsetHeight,
            "fixed" == n.css("position")
        };
        e(window).on("resize.ace_hover",
        function () {
            l = t(),
            ace.helper.sidebar_hover.reset()
        }),
        e(document).on("settings.ace.hover",
        function (e, a, t) {
            "sidebar_collapsed" == a ? ace.helper.sidebar_hover.reset() : "navbar_fixed" == a && (l = t)
        });
        var s = e(".sidebar").eq(0),
        i = (s.get(0), s.find(".nav-list").get(0)),
        n = e(".navbar").eq(0),
        r = n.get(0),
        o = s.hasClass("h-sidebar"),
        l = "fixed" == n.css("position");
        s.find(".submenu").parent().addClass("hsub"),
        s.on("mouseenter.ace_hover", ".nav-list li.hsub",
        function () {
            if (!(ace.vars.collapsible || o && !ace.vars.mobile_view)) {
                var e = this.querySelector(".submenu");
                e && (ace.helper.hasClass(this, "hover") ? a.call(this, e) : this.parentNode == i && ace.vars.minimized && a.call(this, e))
            }
        });
        var c = 50
    }
},
ace.widget_boxes = function (e) {
    e(document).on("hide.bs.collapse show.bs.collapse",
    function (a) {
        var t = a.target.getAttribute("id");
        e('[href*="#' + t + '"]').find(ace.vars[".icon"]).each(function () {
            var t, s = e(this),
            i = null,
            n = null;
            return (i = s.attr("data-icon-show")) ? n = s.attr("data-icon-hide") : (t = s.attr("class").match(/fa\-(.*)\-(up|down)/)) && (i = "fa-" + t[1] + "-down", n = "fa-" + t[1] + "-up"),
            i ? ("show" == a.type ? s.removeClass(i).addClass(n) : s.removeClass(n).addClass(i), !1) : void 0
        })
    });
    var a = function (a) {
        this.$box = e(a);
        this.reload = function () {
            var e = this.$box,
            a = !1;
            "static" == e.css("position") && (a = !0, e.addClass("position-relative")),
            e.append('<div class="widget-box-overlay"><i class="' + ace.vars.icon + 'loading-icon fa fa-spinner fa-spin fa-2x white"></i></div>'),
            e.one("reloaded.ace.widget",
            function () {
                e.find(".widget-box-overlay").remove(),
                a && e.removeClass("position-relative")
            })
        },
        this.close = function () {
            var e = this.$box,
            a = 300;
            e.fadeOut(a,
            function () {
                e.trigger("closed.ace.widget"),
                e.remove()
            })
        },
        this.toggle = function (e, a) {
            var t = this.$box,
            s = t.find(".widget-body"),
            i = null,
            n = "undefined" != typeof e ? e : t.hasClass("collapsed") ? "show" : "hide",
            r = "show" == n ? "shown" : "hidden";
            if ("undefined" == typeof a && (a = t.find("> .widget-header a[data-action=collapse]").eq(0), 0 == a.length && (a = null)), a) {
                i = a.find(ace.vars[".icon"]).eq(0);
                var o, l = null,
                c = null; (l = i.attr("data-icon-show")) ? c = i.attr("data-icon-hide") : (o = i.attr("class").match(/fa\-(.*)\-(up|down)/)) && (l = "fa-" + o[1] + "-down", c = "fa-" + o[1] + "-up")
            }
            var d = s.find(".widget-body-inner");
            s = 0 == d.length ? s.wrapInner('<div class="widget-body-inner"></div>').find(":first-child").eq(0) : d.eq(0);
            var h = 300,
            u = 200;
            "show" == n ? (i && i.removeClass(l).addClass(c), t.removeClass("collapsed"), s.slideUp(0,
            function () {
                s.slideDown(h,
                function () {
                    t.trigger(r + ".ace.widget")
                })
            })) : (i && i.removeClass(c).addClass(l), s.slideUp(u,
            function () {
                t.addClass("collapsed"),
                t.trigger(r + ".ace.widget")
            }))
        },
        this.hide = function () {
            this.toggle("hide")
        },
        this.show = function () {
            this.toggle("show")
        },
        this.fullscreen = function () {
            var e = this.$box.find("> .widget-header a[data-action=fullscreen]").find(ace.vars[".icon"]).eq(0),
            a = null,
            t = null; (a = e.attr("data-icon1")) ? t = e.attr("data-icon2") : (a = "fa-expand", t = "fa-compress"),
            this.$box.hasClass("fullscreen") ? (e.addClass(a).removeClass(t), this.$box.removeClass("fullscreen")) : (e.removeClass(a).addClass(t), this.$box.addClass("fullscreen")),
            this.$box.trigger("fullscreened.ace.widget")
        }
    };
    e.fn.widget_box = function (t, s) {
        var i, n = this.each(function () {
            var n = e(this),
            r = n.data("widget_box"),
            o = "object" == typeof t && t;
            r || n.data("widget_box", r = new a(this, o)),
            "string" == typeof t && (i = r[t](s))
        });
        return void 0 === i ? n : i
    },
    e(document).on("click.ace.widget", ".widget-header a[data-action]",
    function (t) {
        t.preventDefault();
        var s = e(this),
        i = s.closest(".widget-box");
        if (0 != i.length && !i.hasClass("ui-sortable-helper")) {
            var n = i.data("widget_box");
            n || i.data("widget_box", n = new a(i.get(0)));
            var r = s.data("action");
            if ("collapse" == r) {
                var o, l = i.hasClass("collapsed") ? "show" : "hide";
                if (i.trigger(o = e.Event(l + ".ace.widget")), o.isDefaultPrevented()) return;
                n.toggle(l, s)
            } else if ("close" == r) {
                var o;
                if (i.trigger(o = e.Event("close.ace.widget")), o.isDefaultPrevented()) return;
                n.close()
            } else if ("reload" == r) {
                s.blur();
                var o;
                if (i.trigger(o = e.Event("reload.ace.widget")), o.isDefaultPrevented()) return;
                n.reload()
            } else if ("fullscreen" == r) {
                var o;
                if (i.trigger(o = e.Event("fullscreen.ace.widget")), o.isDefaultPrevented()) return;
                n.fullscreen()
            } else "settings" == r && i.trigger("setting.ace.widget")
        }
    })
},
ace.settings_box = function (e) {
    e("#ace-settings-btn").on(ace.click_event,
    function (a) {
        a.preventDefault(),
        e(this).toggleClass("open"),
        e("#ace-settings-box").toggleClass("open")
    }),
    e("#ace-settings-navbar").on("click",
    function () {
        ace.settings.navbar_fixed(this.checked)
    }).each(function () {
        this.checked = ace.settings.is("navbar", "fixed")
    }),
    e("#ace-settings-sidebar").on("click",
    function () {
        ace.settings.sidebar_fixed(this.checked)
    }).each(function () {
        this.checked = ace.settings.is("sidebar", "fixed")
    }),
    e("#ace-settings-breadcrumbs").on("click",
    function () {
        ace.settings.breadcrumbs_fixed(this.checked)
    }).each(function () {
        this.checked = ace.settings.is("breadcrumbs", "fixed")
    }),
    e("#ace-settings-add-container").on("click",
    function () {
        ace.settings.main_container_fixed(this.checked)
    }).each(function () {
        this.checked = ace.settings.is("main-container", "fixed")
    }),
    e("#ace-settings-compact").removeAttr("checked").on("click",
    function () {
        if (this.checked) {
            e("#sidebar").addClass("compact");
            var a = e("#ace-settings-hover");
            a.length > 0 && !a.get(0).checked && a.removeAttr("checked").trigger("click")
        } else e("#sidebar").removeClass("compact"),
        "sidebar_scroll" in ace.helper && ace.helper.sidebar_scroll.reset()
    }),
    e("#ace-settings-highlight").removeAttr("checked").on("click",
    function () {
        this.checked ? e("#sidebar .nav-list > li").addClass("highlight") : e("#sidebar .nav-list > li").removeClass("highlight")
    }),
    e("#ace-settings-hover").removeAttr("checked").on("click",
    function () {
        if (!e(".sidebar").hasClass("h-sidebar")) {
            if (this.checked) ace.vars["no-scroll"] = !0,
            e("#sidebar li").addClass("hover").filter(".open").removeClass("open").find("> .submenu").css("display", "none");
            else {
                ace.vars["no-scroll"] = !1,
                e("#sidebar li.hover").removeClass("hover");
                var a = e("#ace-settings-compact");
                a.length > 0 && a.get(0).checked && a.trigger("click"),
                "sidebar_hover" in ace.helper && ace.helper.sidebar_hover.reset()
            }
            "sidebar_scroll" in ace.helper && ace.helper.sidebar_scroll.reset()
        }
    })
},
ace.settings_rtl = function (e) {
    e("#ace-settings-rtl").removeAttr("checked").on("click",
    function () {
        ace.switch_direction(jQuery)
    })
},
ace.switch_direction = function (e) {
    function a(e, a) {
        t.find("." + e).removeClass(e).addClass("tmp-rtl-" + e).end().find("." + a).removeClass(a).addClass(e).end().find(".tmp-rtl-" + e).removeClass("tmp-rtl-" + e).addClass(a)
    }
    var t = e(document.body);
    t.toggleClass("rtl").find(".dropdown-menu:not(.datepicker-dropdown,.colorpicker)").toggleClass("dropdown-menu-right").end().find(".pull-right:not(.dropdown-menu,blockquote,.profile-skills .pull-right)").removeClass("pull-right").addClass("tmp-rtl-pull-right").end().find(".pull-left:not(.dropdown-submenu,.profile-skills .pull-left)").removeClass("pull-left").addClass("pull-right").end().find(".tmp-rtl-pull-right").removeClass("tmp-rtl-pull-right").addClass("pull-left").end().find(".chosen-select").toggleClass("chosen-rtl").next().toggleClass("chosen-rtl"),
    a("align-left", "align-right"),
    a("no-padding-left", "no-padding-right"),
    a("arrowed", "arrowed-right"),
    a("arrowed-in", "arrowed-in-right"),
    a("tabs-left", "tabs-right"),
    a("messagebar-item-left", "messagebar-item-right"),
    e(".fa").each(function () {
        if (!(this.className.match(/ui-icon/) || e(this).closest(".fc-button").length > 0)) for (var a = this.attributes.length,
        t = 0; a > t; t++) {
            var s = this.attributes[t].value;
            s.match(/fa\-(?:[\w\-]+)\-left/) ? this.attributes[t].value = s.replace(/fa\-([\w\-]+)\-(left)/i, "fa-$1-right") : s.match(/fa\-(?:[\w\-]+)\-right/) && (this.attributes[t].value = s.replace(/fa\-([\w\-]+)\-(right)/i, "fa-$1-left"))
        }
    });
    var s = t.hasClass("rtl");
    s ? e(".scroll-hz").addClass("make-ltr").find(".scroll-content").wrapInner('<div class="make-rtl" />') : e(".scroll-hz").removeClass("make-ltr").find(".make-rtl").children().unwrap(),
    e.fn.ace_scroll && e(".scroll-hz").ace_scroll("reset");
    try {
        var i = e("#piechart-placeholder");
        if (i.length > 0) {
            var n = e(document.body).hasClass("rtl") ? "nw" : "ne";
            i.data("draw").call(i.get(0), i, i.data("chart"), n)
        }
    } catch (r) { }
},
ace.settings_skin = function (e) {
    try {
        e("#skin-colorpicker").ace_colorpicker()
    } catch (a) { }
    e("#skin-colorpicker").on("change",
    function () {
        var a = e(this).find("option:selected").data("skin"),
        t = e(document.body);
        t.removeClass("no-skin skin-1 skin-2 skin-3"),
        t.addClass(a),
        ace.data.set("skin", a);
        var s = ["red", "blue", "green", ""];
        e(".ace-nav > li.grey").removeClass("dark"),
        e(".ace-nav > li").removeClass("no-border margin-1"),
        e(".ace-nav > li:not(:last-child)").removeClass("light-pink").find("> a > " + ace.vars[".icon"]).removeClass("pink").end().eq(0).find(".badge").removeClass("badge-warning"),
        e(".sidebar-shortcuts .btn").removeClass("btn-pink btn-white").find(ace.vars[".icon"]).removeClass("white"),
        e(".ace-nav > li.grey").removeClass("red").find(".badge").removeClass("badge-yellow"),
        e(".sidebar-shortcuts .btn").removeClass("btn-primary btn-white");
        var i = 0;
        e(".sidebar-shortcuts .btn").each(function () {
            e(this).find(ace.vars[".icon"]).removeClass(s[i++])
        });
        var n = ["btn-success", "btn-info", "btn-warning", "btn-danger"];
        if ("no-skin" == a) {
            var i = 0;
            e(".sidebar-shortcuts .btn").each(function () {
                e(this).attr("class", "btn " + n[i++ % 4])
            })
        } else if ("skin-1" == a) {
            e(".ace-nav > li.grey").addClass("dark");
            var i = 0;
            e(".sidebar-shortcuts").find(".btn").each(function () {
                e(this).attr("class", "btn " + n[i++ % 4])
            })
        } else if ("skin-2" == a) e(".ace-nav > li").addClass("no-border margin-1"),
        e(".ace-nav > li:not(:last-child)").addClass("light-pink").find("> a > " + ace.vars[".icon"]).addClass("pink").end().eq(0).find(".badge").addClass("badge-warning"),
        e(".sidebar-shortcuts .btn").attr("class", "btn btn-white btn-pink").find(ace.vars[".icon"]).addClass("white");
        else if ("skin-3" == a) {
            t.addClass("no-skin"),
            e(".ace-nav > li.grey").addClass("red").find(".badge").addClass("badge-yellow");
            var i = 0;
            e(".sidebar-shortcuts .btn").each(function () {
                e(this).attr("class", "btn btn-primary btn-white"),
                e(this).find(ace.vars[".icon"]).addClass(s[i++])
            })
        }
        "sidebar_scroll" in ace.helper && ace.helper.sidebar_scroll.reset()
    })
},
ace.widget_reload_handler = function (e) {
    e(document).on("reload.ace.widget", ".widget-box",
    function () {
        var a = e(this);
        setTimeout(function () {
            a.trigger("reloaded.ace.widget")
        },
        parseInt(1e3 * Math.random() + 1e3))
    })
},
ace.enable_searchbox_autocomplete = function (e) {
    ace.vars.US_STATES = ["Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Dakota", "North Carolina", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"];
    try {
        e("#nav-search-input").typeahead({
            source: ace.vars.US_STATES,
            updater: function (a) {
                return e("#nav-search-input").focus(),
                a
            }
        })
    } catch (a) { }
};