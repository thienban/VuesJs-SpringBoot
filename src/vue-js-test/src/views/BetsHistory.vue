<template>
  <div class="card mt-5">
    <div class="card-header bg-dark text-white">
      Mes paris ({{bets.length}})
    </div>
    <div class="card-body">
      <p class="card-text">
      <table class="table table-bg table-hover">
        <thead>
        <tr>
          <th scope="col">Réf</th>
          <th scope="col">Date</th>
          <th scope="col">Event / Market / Selection</th>
          <th scope="col">Mise</th>
          <th scope="col">Cote</th>
          <th scope="Gain">Gain</th>
        </tr>
        </thead>
        <tbody>
          <tr v-for="bet in bets" :key="bet.id" class="font-weigh-bold" 
          v-bind:class="{'bg-success': isWon(bet), 'bg-danger': isLoss(bet)}">
            <td>Uni-{{bet.id}}</td>
            <td>{{new Date(bet.date).toLocaleString()}}</td>
            <td>{{bet.event}} / {{bet.market}} / {{bet.selectionName}}</td>
            <td>{{bet.name}}</td>
            <td>{{bet.currentOdd}}</td>
            <td>{{bet.currentOdd * bet.name}}</td>
        </tr>
        </tbody>
      </table>
      </p>
    </div>
  </div>
  </div>
</template>

<script>
import axios from "@/services/axios.js";

export default {
  name: "MyBets",
    data() {
    return {
      bets: undefined,
    };
  },
   mounted() {
    this.fetchBets();
    this.betInterval = setInterval(this.fetchBets, 5000);
  },
  beforeDestroy(){
    clearInterval(this.betInterval);
  },
  methods: {
    async fetchBets() {
      const response = await axios.get(
        "/bets"
      );
      this.bets = response.data;
    },
    isWon(bet) {
      return bet.betState === 'WON';
    },
    isLoss(bet) {
      return bet.betState === 'LOST';
    }
  },
};
</script>

<style scoped>
</style>
