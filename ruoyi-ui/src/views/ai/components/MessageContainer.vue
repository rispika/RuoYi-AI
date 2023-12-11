<template>
  <div class="message-container" :style="{height: realHeight, width: realWidth}">

  </div>
</template>

<script>
export default {
  name: "MessageContainer",
  props: {
    height: {
      type: Number | String,
      default: "40px"
    },
    width: {
      type: Number | String,
      default: "800px"
    }
  },
  computed: {
    realHeight() {
      if (typeof this.height === "number") {
        return `${this.height}px`
      } else {
        return this.height
      }
    },
    realWidth() {
      if (typeof this.width === "number") {
        return `${this.width}px`
      } else {
        return this.width
      }
    },
  }

}
</script>

<style scoped lang="scss">
.message-container {
  background: #999999;
  z-index: 100;
  border-radius: 30px;
}
</style>
