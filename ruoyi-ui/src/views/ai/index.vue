<template>
  <div class="ai-main">
    <DbViewer style="position: absolute"></DbViewer>
    <MessageContainer class="message-container"></MessageContainer>
  </div>
</template>

<script>
import DbViewer from "@/components/DbViewer/index.vue";
import MessageContainer from "@/views/ai/components/MessageContainer.vue";
export default {
  components: {
    MessageContainer,
    DbViewer
  },
  data() {
    return {

    }
  }
}
</script>

<style scoped lang="scss">
.ai-main {
  background: red;
  height: 100%;
  width: 100%;
}
.message-container {
  position: absolute;
  bottom: 30px;
  left: 50%;
  transform: translateX(-50%);
  user-select: none;
}
</style>
