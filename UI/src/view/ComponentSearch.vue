<template>
  <div class="ComponentSearch">
    <div id="draggable-block"/>
  </div>
</template>

<script setup lang="ts" name="ComponentSearch">
import DraggableList from "../lib/draggable-list/src";
import {onMounted} from "vue";

const list_data = [
  {A: 'A1', B: 'B1', C: 'C1', D: 'D1', E: 'E1'},
  {A: 'A2', B: 'B2', C: 'C2', D: 'D2', E: 'E2'},
  {A: 'A3', B: 'B3', C: 'C3', D: 'D3', E: 'E3'},
  {A: 'A4', B: 'B4', C: 'C4', D: 'D4', E: 'E4'},
  {A: 'A5', B: 'B5', C: 'C5', D: 'D5', E: 'E5'},
  {A: 'A6', B: 'B6', C: 'C6', D: 'D6', E: 'E6'},
  {A: 'A7', B: 'B7', C: 'C7', D: 'D7', E: 'E7'},
  {A: 'A8', B: 'B8', C: 'C8', D: 'D8', E: 'E8'},
  {A: 'A9', B: 'B9', C: 'C9', D: 'D9', E: 'E9'},
  {A: 'A10', B: 'B10', C: 'C10', D: 'D10', E: 'E10'},
  {A: 'A11', B: 'B11', C: 'C11', D: 'D11', E: 'E11'},
  {A: 'A12', B: 'B12', C: 'C12', D: 'D12', E: 'E12'},
  {A: 'A13', B: 'B13', C: 'C13', D: 'D13', E: 'E13'},
  {A: 'A14', B: 'B14', C: 'C14', D: 'D14', E: 'E14'},
  {A: 'A15', B: 'B15', C: 'C15', D: 'D15', E: 'E15'},
  {A: 'A16', B: 'B16', C: 'C16', D: 'D16', E: 'E16'},
  {A: 'A17', B: 'B17', C: 'C17', D: 'D17', E: 'E17'},
  {A: 'A18', B: 'B18', C: 'C18', D: 'D18', E: 'E18'},
  {A: 'A19', B: 'B19', C: 'C19', D: 'D19', E: 'E19'}
]

onMounted(() => {
  const draggableList = new DraggableList(list_data, 'draggable-block', {})
})

</script>

<style scoped>

.ComponentSearch {
  width: 100vw;
  height: 100vh;
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
}

#draggable-block {
  width: 70%;
  height: 60%;
  background-color: darkgrey;
}

</style>