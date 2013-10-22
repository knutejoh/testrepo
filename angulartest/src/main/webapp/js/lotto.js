/* 
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


drawlist = {
    MONTH_TABLE: new Array("Januar", "Februar", "Mars", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Desember"),
    DAY_TABLE: new Array("S&oslash;", "Ma", "Ti", "On", "To", "Fr", "L&oslash;"),
    YEAR_SEPARATOR: ":",
    MONTH_SEPARATOR: ".",
    DATE_OFFSET: 63,
    DRAW_ID_DELTA: 32,
    firstYear: -1,
    dateTable: null,
    drawIDTable: null,
    gameIDTable: null,
    tmp_firstYear: null,
    tmp_dateTable: null,
    tmp_drawIDTable: null,
    tmp_gameIDTable: null,
    decompress:
            function(a) {
                console.log("Her er vi inne i ting!!");
                for (var b = 0; b < a.length; b++) {
                    var c = a[b];
                    console.log("Behandler" + c);
                    if (b == 0) {
                        drawlist.setDrawList(c.drawData, c.firstYear, c.gameID)
                    } else {
                        drawlist.addDrawList(c.drawData, c.firstYear, c.gameID)
                    }
                }
                return drawlist.createItems()
            }, createItems: function() {
        var g = [];
        if (!drawlist.dateTable) {
            return g
        }
        for (var a = 0; a < drawlist.dateTable.length;
                a++) {
            var e = drawlist.dateTable[a], h = drawlist.firstYear - a, c = {label: String(h), value: []};
            for (var d = 0; d < e.length; d++) {
                var i = e[d], f = e.length - d;
                if (i.length == 0) {
                    continue
                }
                c.value.push({label: drawlist.MONTH_TABLE[e.length - (d + 1)]});
                for (var l = 0; l < i.length; l++) {
                    var b = i[l], k = new Date(h, f - 1, b), j = com.formatDatePattern(k, "EEE dd/MM");
                    c.value.push({label: j, value: {date: k, drawID: drawlist.drawIDTable[a][d][l], gameID: drawlist.gameIDTable[a][d][l]}})
                }
            }
            g.push(c)
        }
        return g
    }, setDrawList: function(b, c, a) {
        if (!drawlist.parseDrawData(b, c, a)) {
            return
        }
        drawlist.firstYear = drawlist.tmp_firstYear;
        drawlist.dateTable = drawlist.tmp_dateTable;
        drawlist.drawIDTable = drawlist.tmp_drawIDTable;
        drawlist.gameIDTable = drawlist.tmp_gameIDTable
    }, addDrawList: function(a, h, c) {
        var f, e, d, b, m, g;
        if (!drawlist.parseDrawData(a, h, c)) {
            return
        }
        m = drawlist.firstYear - drawlist.tmp_firstYear;
        while (m < 0) {
            if (m == -1) {
                g = drawlist.tmp_dateTable[0].length
            } else {
                g = 12
            }
            drawlist.insertYear(drawlist.dateTable, drawlist.drawIDTable, drawlist.gameIDTable, 0, g);
            drawlist.firstYear++;
            m++
        }
        while (m > 0) {
            if (m == 1) {
                g = drawlist.dateTable[0].length
            } else {
                g = 12
            }
            drawlist.insertYear(drawlist.tmp_dateTable, drawlist.tmp_drawIDTable, drawlist.tmp_gameIDTable, 0, g);
            drawlist.tmp_firstYear++;
            m--
        }
        m = drawlist.tmp_dateTable.length - drawlist.dateTable.length;
        while (m > 0) {
            drawlist.insertYear(drawlist.dateTable, drawlist.drawIDTable, drawlist.gameIDTable, drawlist.dateTable.length, 12);
            m--
        }
        for (f = 0; f < drawlist.dateTable.length; f++) {
            if (f >= drawlist.tmp_dateTable.length) {
                break
            }
            for (e = 0; e < drawlist.dateTable[f].length; e++) {
                if (drawlist.tmp_dateTable[f][e].length <= 0) {
                    continue
                }
                if (drawlist.dateTable[f][e].length <= 0) {
                    drawlist.dateTable[f][e] = drawlist.tmp_dateTable[f][e];
                    drawlist.drawIDTable[f][e] = drawlist.tmp_drawIDTable[f][e];
                    drawlist.gameIDTable[f][e] = drawlist.tmp_gameIDTable[f][e];
                    continue
                }
                b = 0;
                for (d = 0; d < drawlist.tmp_dateTable[f][e].length; d++) {
                    while ((b < drawlist.dateTable[f][e].length) && (drawlist.dateTable[f][e][b] > drawlist.tmp_dateTable[f][e][d])) {
                        b++
                    }
                    if ((b < drawlist.dateTable[f][e].length) && (drawlist.dateTable[f][e][b] == drawlist.tmp_dateTable[f][e][d])) {
                        drawlist.drawIDTable[f][e][b] += "," + drawlist.tmp_drawIDTable[f][e][d];
                        drawlist.gameIDTable[f][e][b] += "," + drawlist.tmp_gameIDTable[f][e][d]
                    } else {
                        if (typeof(drawlist.dateTable[f][e].splice) != "function") {
                            drawlist.dateTable[f][e] = drawlist.insert(drawlist.dateTable[f][e], b, [drawlist.tmp_dateTable[f][e][d]]);
                            drawlist.drawIDTable[f][e] = drawlist.insert(drawlist.drawIDTable[f][e], b, [drawlist.tmp_drawIDTable[f][e][d]]);
                            drawlist.gameIDTable[f][e] = drawlist.insert(drawlist.gameIDTable[f][e], b, [drawlist.tmp_gameIDTable[f][e][d]])
                        } else {
                            drawlist.dateTable[f][e].splice(b, 0, drawlist.tmp_dateTable[f][e][d]);
                            drawlist.drawIDTable[f][e].splice(b, 0, drawlist.tmp_drawIDTable[f][e][d]);
                            drawlist.gameIDTable[f][e].splice(b, 0, drawlist.tmp_gameIDTable[f][e][d])
                        }
                    }
                }
            }
        }
    }, parseDrawData: function(a, f, b) {
        var e, d, c, m, l, g, h = 9;
        f = parseInt(f);
        b = parseInt(b);
        if (isNaN(f) || (f < 0) || isNaN(b) || (b < 0) || (a == null) || (a == "null") || (a == "")) {
            return false
        }
        drawlist.tmp_firstYear = f;
        g = -1;
        drawlist.tmp_dateTable = a.split(drawlist.YEAR_SEPARATOR);
        drawlist.tmp_drawIDTable = new Array(drawlist.tmp_dateTable.length);
        drawlist.tmp_gameIDTable = new Array(drawlist.tmp_dateTable.length);
        for (e = 0; e < drawlist.tmp_dateTable.length; e++) {
            drawlist.tmp_dateTable[e] = drawlist.tmp_dateTable[e].split(drawlist.MONTH_SEPARATOR);
            if (typeof(drawlist.tmp_dateTable[e].splice) != "function") {
                while (drawlist.tmp_dateTable[e].length < 12) {
                    drawlist.tmp_dateTable[e] = drawlist.insert(drawlist.tmp_dateTable[e], 0, [new Array()])
                }
            } else {
                while (drawlist.tmp_dateTable[e].length < 12) {
                    drawlist.tmp_dateTable[e].splice(0, 0, new Array())
                }
            }
            drawlist.tmp_drawIDTable[e] = new Array(drawlist.tmp_dateTable[e].length);
            drawlist.tmp_gameIDTable[e] = new Array(drawlist.tmp_dateTable[e].length);
            for (d = 0; d < drawlist.tmp_dateTable[e].length; d++) {
                l = drawlist.tmp_dateTable[e][d];
                drawlist.tmp_dateTable[e][d] = new Array();
                drawlist.tmp_drawIDTable[e][d] = new Array();
                drawlist.tmp_gameIDTable[e][d] = new Array();
                c = 0;
                m = 0;
                while (m < l.length) {
                    drawlist.tmp_dateTable[e][d][c] = l.charCodeAt(m) - drawlist.DATE_OFFSET;
                    m++;
                    if (drawlist.tmp_dateTable[e][d][c] > 31) {
                        drawlist.tmp_dateTable[e][d][c] -= drawlist.DRAW_ID_DELTA;
                        g = parseInt(l.substring(m, m + h), 16);
                        m += h
                    }
                    drawlist.tmp_drawIDTable[e][d][c] = String(g);
                    drawlist.tmp_gameIDTable[e][d][c] = b;
                    if (g < 0) {
                        g++
                    } else {
                        g--
                    }
                    c++
                }
            }
        }
        return true
    }, insert: function(c, b, d) {
        var a;
        if (b == 0) {
            a = d
        } else {
            a = c.slice(0, b);
            a = a.concat(d)
        }
        return a.concat(c.slice(b))
    }, insertYear: function(d, c, f, b, a) {
        var e;
        if (typeof(d.splice) != "function") {
            d = drawlist.insert(d, b, [new Array(a)]);
            c = drawlist.insert(c, b, [new Array(a)]);
            f = drawlist.insert(f, b, [new Array(a)])
        } else {
            d.splice(b, 0, new Array(a));
            c.splice(b, 0, new Array(a));
            f.splice(b, 0, new Array(a))
        }
        for (e = 0; e < a; e++) {
            d[b][e] = new Array();
            c[b][e] = new Array();
            f[b][e] = new Array()
        }
    }};


var com = {};

com.formatDatePattern = function(a, c) {
    if (a == null || isNaN(a)) {
        a = new Date()
    }
    if ((c == null) || (c.length < 1)) {
        c = "EE dd.MM.yyyy kl. HH:mm"
    }
    var d = ["S\u00F8ndag", "Mandag", "Tirsdag", "Onsdag", "Torsdag", "Fredag", "L\u00F8rdag"];
    var b = ["Januar", "Februar", "Mars", "April", "Mai", "Juni", "Juli", "August", "September", "Oktober", "November", "Desember"];
    return c.replace(/dd/g, com.padString(a.getDate(), "0", 2)).replace(/yyyy/g, com.padString(a.getFullYear(), "0", 4)).replace(/yy/g, com.padString(a.getFullYear() % 100, "0", 2)).replace(/HH/g, com.padString(a.getHours(), "0", 2)).replace(/mm/g, com.padString(a.getMinutes(), "0", 2)).replace(/ss/g, com.padString(a.getSeconds(), "0", 2)).replace(/SSS/g, com.padString(a.getSeconds(), "0", 3)).replace(/MMMM/g, b[a.getMonth()]).replace(/MMM/g, b[a.getMonth()].substr(0, 3)).replace(/MM/g, com.padString(a.getMonth() + 1, "0", 2)).replace(/EEEE/g, d[a.getDay()]).replace(/EEE/g, d[a.getDay()].substr(0, 3)).replace(/EE/g, d[a.getDay()].substr(0, 2))
};

com.padString = function(c, d, b) {
    var e = String(c);
    if ((d == null) || (d.length < 1)) {
        return e
    }
    for (var a = e.length; a < b; a++) {
        e = d + e
    }
    return e
};
//