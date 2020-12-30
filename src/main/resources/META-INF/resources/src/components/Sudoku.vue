<template>
  <div>
    <b-row v-for="(row, indexRow) in cells" no-gutters class="no-gutter">
      <b-col v-for="(column, indexColumn) in row">
        <input :class="{
    'right': indexColumn === 2 || indexColumn === 5,
        'top': indexRow === 2 || indexRow === 5,
        'cell' : true
        }" :value="column.number === 0 ? '' : column.number"
               @keypress="filterInput"
               :disabled="isDisabled(column)">
      </b-col>
    </b-row>
  </div>
</template>

<script>
export default {
  name: "Calendar",

  props: {
    cells: {
      type: Array,
    },
  },
  methods: {
    isDisabled(cell) {
      return cell.number > 0;
    },
    filterInput(e) {
      const key = e.keyCode;
      if ((key < 49 || key > 57)) {
        return e.preventDefault();
      } else {
        event.target.value = '';
      }
    },
  }
}
</script>

<style>

.cell {
  display: flex;
  width: 75px;
  height: 75px;
  font-size: 20px;
  text-align: center;
  border: grey solid 1px;
}

.col {
  flex-grow: 0 !important;
}

.cell:disabled {
  font-weight: bold;
}

.cell:hover, .cell:not(.disabled) {
  cursor: pointer;
}

.cell:focus {
  background-color: lightblue;
}

.right {
  border-right: black solid 3px;
}

.top {
  border-bottom: black solid 3px;
}

</style>
