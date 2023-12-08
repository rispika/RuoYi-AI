import Vue from "vue";
const drag = Vue.directive("drag", {
  bind: function (el) {},
  inserted: function (el) {
    el.onmousedown = function (e) {
      // 鼠标按下时的的位置
      var startX = e.pageX;
      var startY = e.pageY;

      //父元素的初始左偏移和上偏移
      var originX = el.parentNode.style.left;
      var originY = el.parentNode.style.top;

      //父元素的初始左偏移和上偏移，如果没有设置过这些值则默认为0
      if (originX && originX !== "") {
        originX = originX.replace("px", "") * 1;
        originY = originY.replace("px", "") * 1;
      } else {
        originX = 0;
        originY = 0;
      }
      el.parentNode.style.position = "absolute";
      el.style.cursor = "grabbing";
      var parentBox = el.parentNode.parentNode;
      //当鼠标在元素上移动时触发的事件处理函数。在这里计算元素的新位置，使元素随着鼠标移动而移动
      document.onmousemove = function (e) {
        var left = e.pageX - startX + originX;
        if (left >= parentBox.offsetWidth - el.offsetWidth) {
          left = parentBox.offsetWidth - el.offsetWidth;
        } else if (left <= 0) {
          left = 0;
        } else {
          el.parentNode.style.left = left + "px";
        }
        var top = e.pageY - startY + originY;
        if (top >= parentBox.offsetHeight - el.offsetHeight) {
          top = parentBox.offsetHeight - el.offsetHeight;
        } else if (top <= 0) {
          top = 0;
        } else {
          el.parentNode.style.top = top + "px";
        }

      };
      //当鼠标释放按钮时触发的事件处理函数。在这里清除了之前设置的鼠标移动和鼠标释放事件，以停止拖拽
      document.onmouseup = function () {
        document.onmousemove = document.onmouseup = null;
        el.style.cursor = "grab";
      };
    };
  },
  updated: function (el) {},
});
//将自定义指令导出
export default drag;
