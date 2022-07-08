<template>
  <router-view v-slot="{ Component }">
    <transition name="fade">
      <component :is="Component" />
    </transition>
  </router-view>
</template>

<script setup lang="ts">
</script>

<style>
*{
  font-size: 14px;
  padding: 0;
  margin: 0;
}
</style>