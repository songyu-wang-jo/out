<template>
  <div class="Index">
    index component {{ appName }} on mode {{ runMode }} on process mode {{ processRunMode }}
    <svg-icon name="peanut"/>
    <el-button type="primary">Primary</el-button>
    <el-table/>
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

let processRunMode = computed(()=>{
  return process.env.NODE_ENV
})

</script>

<style scoped>
.Index {

}
</style>