<style scoped>
.addmargin {
  margin-top: 10px;
  margin-bottom: 10px;
}

.vue-logo-back {
  background-color: black;
}
</style>

<template>
  <div>
    <div class="card mt-5">
      <div class="card-header bg-dark text-white pulse">
        <h4 v-if="lives">
          Live(s) en cours
          <span class="badge bg-danger">{{ lives.length }}</span>
        </h4>
        <h4 v-else>Pas de lives</h4>
      </div>
      <div class="card-body">
        <p class="card-text"></p>
        <div
          class="card centeralign addmargin"
          style="width: 18rem"
          v-for="live in lives"
          :key="live.id"
        >
          <div class="card-body" v-on:click="goToLive(live.id)">
            <h4 class="card-title font-weight-bold text-justify">
              {{ live.name }}
            </h4>
            <ul class="list-group text-justify text-center">
              <li v-if="live.markets" class="list-group-item">
                {{ live.markets.length }} marché(s) ouvert(s)
              </li>
              <li v-else class="list-group-item">aucun marché</li>
              <li v-if="live.selections" class="list-group-item">
                {{ live.selections.length }} selection(s) ouverte(s)
              </li>
              <li v-else class="list-group-item">aucun selection</li>
              <li v-if="live.markets" class="list-group-item">
                {{ live.bets.length }} pari(s) enregistré(s)
              </li>
              <li v-else class="list-group-item">aucun pari</li>
            </ul>
            <a class="btn btn-primary mt-3" v-on:click="goToLive(live.id)"
              ><span style="color: white">Parier sur {{ live.name }}</span></a
            >
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// @ is an alias to /src
import axios from "@/services/axios.js";

export default {
  name: "live",
  mounted() {
    this.fetchEvents();
  },
  data() {
    return {
      lives: undefined,
      // Examples
      /*lives: [{
              id : 1,
              name : 'Real madrid vs PSG',
              startdate: '2022-02-15 21h00',
              markets:[],
              selections:[],
              nbBets: 27
            },
              {
                id : 1,
                name : 'Inter vs Liverpool',
                startdate: '2022-02-15 21h00',
                markets:[],
                selections:[],
                nbBets: 117
              }],
              */
    };
  },
  components: {},

  methods: {
    async fetchEvents() {
      const response = await axios.get("/events");
      this.lives = response.data;
    },
    goToLive: function (id) {
      this.$router.push(`/livedetails/${id}`);
    },
  },
};
</script>
