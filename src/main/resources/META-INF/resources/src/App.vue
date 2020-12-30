<template>
  <div class="container">
    <h1>Sudoku</h1>
    <b-button v-on:click="createNewSudoku" variant="success">Neu</b-button>
    <b-button v-on:click="solveSudoku" class="ml-1" variant="success">Lösen</b-button>
    <Sudoku :cells="this.cells"></Sudoku>
  </div>
</template>
<script>
import Sudoku from "./components/Sudoku.vue";
import axios from "axios";

export default {
  name: "app",
  components: {
    Sudoku,
  },
  created() {
    document.title = "Sudoku";
  },
  data() {
    return {
      cells: {
        type: Array,
        default: () => [],
      },
    };
  },

  methods: {
    solveSudoku: function (event) {
      axios.post("http://localhost:8081/sudokus/solve", this.cells).then((response) => {
        this.cells = JSON.parse(JSON.stringify(response.data));
      })
    },
    createNewSudoku: function (event) {
      axios.post("http://localhost:8081/sudokus/create").then((response) => {
        this.cells = JSON.parse(JSON.stringify(response.data));
      });
    }

  },

  async mounted() {
    await axios.post("http://localhost:8081/sudokus/create").then((response) => {
      this.cells = JSON.parse(JSON.stringify(response.data));
    });

  }
};
</script>
<style>
#app {
  font-family: "Avenir", Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  color: #2c3e50;
  margin-top: 60px;
}

body {
  font-family: sans-serif;
  font-weight: 100;
  --grey-100: #e4e9f0;
  --grey-200: #cfd7e3;
  --grey-300: #b5c0cd;
  --grey-800: #3e4e63;
  --grid-gap: 1px;
  --day-label-size: 20px;
}
</style>

