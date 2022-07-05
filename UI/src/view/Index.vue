<template>
  <div class="Index">
    index component {{ appName }} on mode {{ runMode }}
    <svg-icon name="peanut"/>
  </div>
</template>

<script setup lang="ts" name="Index">
import {computed} from "vue";
import SvgIcon from "../components/SvgIcon.vue";

let appName = computed(() => {
  return import.meta.env.out_app_name
})

let runMode = computed(() => {
  return import.meta.env.out_env
})

</script>

<style scoped>
.Index {

}
</style>