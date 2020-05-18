
// 中考倒计时
{
    var countDownTime = {
        init: function (a, b, c, d, e) {
            this.t = a, this.d = b, this.h = c, this.m = d, this.s = e
        },
        start: function () {
            var a = this;
            setInterval(a.timer, 1e3);
        },
        timer: function (a) {
            var b, c, d, e, f, g, h;
            a = this.countDownTime, b = new Date, c = new Date(a.t), d = c.getTime() - b.getTime(), e = Math.floor(a.formatTime(d, "day")), f = Math.floor(a.formatTime(d, "hours")), g = Math.floor(a.formatTime(d, "minutes")), h = Math.floor(a.formatTime(d, "seconds")), a.d && (a.d.innerText = a.formatNumber(e)), a.h && (a.h.innerText = a.formatNumber(f)), a.m && (a.m.innerText = a.formatNumber(g)), a.s && (a.s.innerText = a.formatNumber(h))
        },
        formatNumber: function (a) {
            if (a <= 0) { a = 0 }
            return a = a.toString(), a[1] ? a : "0" + a
        },
        formatTime: function (a, b) {
            switch (b) {
                case "day":
                    return a / 1e3 / 60 / 60 / 24;
                case "hours":
                    return a / 1e3 / 60 / 60 % 24;
                case "minutes":
                    return a / 1e3 / 60 % 60;
                case "seconds":
                    return a / 1e3 % 60
            }
        }
    };

    var dayZk = document.getElementById('day-zk');
    var hoursZk = document.getElementById('hours-zk');
    var minutesZk = document.getElementById('minutes-zk');
    var secondsZk = document.getElementById('seconds-zk');
    // 声明结束时间
    countDownTime.init('2020/6/16 00:00:00', dayZk, hoursZk, minutesZk, secondsZk);
    countDownTime.start();
}

// 高考倒计时
{
    var countDownTime2 = {
        init: function (a, b, c, d, e) {
            this.t = a, this.d = b, this.h = c, this.m = d, this.s = e
        },
        start: function () {
            var a = this;
            setInterval(a.timer, 1e3);
        },
        timer: function (a) {
            var b, c, d, e, f, g, h;
            a = this.countDownTime2, b = new Date, c = new Date(a.t), d = c.getTime() - b.getTime(), e = Math.floor(a.formatTime(d, "day")), f = Math.floor(a.formatTime(d, "hours")), g = Math.floor(a.formatTime(d, "minutes")), h = Math.floor(a.formatTime(d, "seconds")), a.d && (a.d.innerText = a.formatNumber(e)), a.h && (a.h.innerText = a.formatNumber(f)), a.m && (a.m.innerText = a.formatNumber(g)), a.s && (a.s.innerText = a.formatNumber(h))
        },
        formatNumber: function (a) {
            if (a <= 0) { a = 0 }
            return a = a.toString(), a[1] ? a : "0" + a
        },
        formatTime: function (a, b) {
            switch (b) {
                case "day":
                    return a / 1e3 / 60 / 60 / 24;
                case "hours":
                    return a / 1e3 / 60 / 60 % 24;
                case "minutes":
                    return a / 1e3 / 60 % 60;
                case "seconds":
                    return a / 1e3 % 60
            }
        }
    };
    var dayGk = document.getElementById('day-gk');
    var hoursGk = document.getElementById('hours-gk');
    var minutesGk = document.getElementById('minutes-gk');
    var secondsGk = document.getElementById('seconds-gk');
    // 声明结束时间
    countDownTime2.init('2020/7/7 00:00:00', dayGk, hoursGk, minutesGk, secondsGk);
    countDownTime2.start();
}