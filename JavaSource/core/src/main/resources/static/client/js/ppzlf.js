//老师
var mySwiper3 = new Swiper('.cgundong', {
  slidesPerView: 3,
  loop: true,
  autoplay: 400000,
});
$('#ileft').on('click', function (e) {
  e.preventDefault()
  mySwiper3.swipePrev()
})
$('#iright').on('click', function (e) {
  e.preventDefault()
  mySwiper3.swipeNext()
})
//证书
var mySwiper = new Swiper('.zgundong', {
  slidesPerView: 2,
  loop: true,
  autoplay: 3000,
});
$('#ileft2').on('click', function (e) {
  e.preventDefault()
  mySwiper.swipePrev()
})
$('#iright2').on('click', function (e) {
  e.preventDefault()
  mySwiper.swipeNext()
})
//平滑
$('a[href*="#"]:not([href="#"])').click(function () {
  if (location.pathname.replace(/^\//, '') == this.pathname.replace(/^\//, '') && location.hostname == this.hostname) {
    var target = $(this.hash);
    target = target.length ? target : $('[name=' + this.hash.slice(1) + ']');
    if (target.length) {
      $('html, body').animate({
        scrollTop: target.offset().top
      }, 1000);
      return false;
    }
  }
});
//百度代码
// var _hmt = _hmt || [];
// (function () {
//   var hm = document.createElement("script");
//   hm.src = "https://hm.baidu.com/hm.js?6d962a15c28b1ea421f7d386c37ca7de";
//   var s = document.getElementsByTagName("script")[0];
//   s.parentNode.insertBefore(hm, s);
// })();
//动画
//if (!(/msie [6|7|8|9]/i.test(navigator.userAgent))){
//	new WOW().init();
//};
//咨询按钮
// var kefuurl = "http://p.qiao.baidu.com/cps/chat?siteId=14327730&userId=5106305&cp=www.viponlyedu.com.cn%2Fppzlf&cr=www.viponlyedu.com.cn%2Fppzlf&cw=%E7%99%BE%E5%BA%A6%E6%99%BA%E7%AB%8B%E6%96%B9%E5%93%81%E4%B8%93";
// $(".btnzx").attr("href", kefuurl).attr("target", "_blank");
// $(".btnzx2").attr("href", kefuurl).attr("target", "_blank");
// $(".fancon .btn").attr("href", kefuurl).attr("target", "_blank");
//底部关闭
$(".close").click(function () {
  $(".fixfoot").hide(100);
})
//电话分配
thisHost = location.hostname;
strwrite = thisHost
var from = location.search;
if (from == "?from=baiduqq") {
  var webtel = "400-086-7931";
} else {
  var webtel = "400-803-6990";
}
$(".webtel").html(webtel);
