<template>
  <div :style="{'width': width}" class="main" v-drag>
    <div class="header">
      <span>student</span>
      <i class="el-icon-arrow-left arrow" :class="{'arrow-unfold': unfoldStatus}" @click="unfold"></i>
    </div>
    <div class="body" v-if="unfoldStatus">
      <div @dblclick="$modal.msgSuccess('你点击了id')">id<span>:bigint(20)</span></div>
      <div @dblclick="$modal.msgSuccess('你点击了name')">name<span>:varcahr(200)</span></div>
    </div>
  </div>
</template>

<script>
export default {
  props: {
    width: {
      type: String,
      default: '250px'
    }
  },
  data() {
    return {
      unfoldStatus: false,
    }
  },
  methods: {
    unfold() {
      this.unfoldStatus = !this.unfoldStatus
    }
  }
}
</script>

<style scoped lang="scss">
.main {
  position: relative;
  background: #3783C0;
  text-align: center;
  color: #fff;
  border-radius: 15px;
  padding: 5px;
  cursor: grab;
  user-select: none;

  .header {
    height: 25px;
    line-height: 25px;

    .arrow-unfold {
      transform: rotate(-90deg);
    }

    .arrow {
      position: absolute;
      right: 10px;
      top: 10px;
      cursor: pointer;
      transition: .5s all;
    }
  }

  .body {
    background: #FFFFFF;
    color: #000000;
    border-radius: 0 0 10px 10px;
    overflow: hidden;
    div {
      height: 25px;
      line-height: 25px;
      &:hover {
        background-color: #C2DCFA;
      }

      span {
        color: #999999;
      }
    }
  }


}
</style>
