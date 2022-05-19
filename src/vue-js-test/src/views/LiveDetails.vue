<template>
  <div class="card mt-5">
    <div class="card-header bg-dark text-white pulse">
      Real Madris vs PSG (en cours)
      {{info}}
    </div>
    <div class="card-body">
      <p class="card-text">
      <div class="container">
      <div v-for="group, marketName in groupByMarket" :key="group.id">
        <h4>{{marketName}}</h4>
          <div v-for="selection in group" :key="selection.id">
            <div class="row mt-1">
            <div class="col">
            <div @click="addBet(selection)" class="card bg-primary text-white h-100">
              <div class="card-body">
                <p class="card-text">{{selection.name}} : {{selection.currentOdd}}</p>
              </div>
            </div>
          </div>
          </div>
         </div>
      </div>
      </div>
      </p>
      <router-link to="/lives" class="btn btn-light">Retour aux lives</router-link>
    </div>
  </div>

</template>

<script>
// @ is an alias to /src
import axios from "@/services/axios.js";
import { groupBy } from "lodash";

export default {
  name: "livedetails",
  mounted() {
    this.fetchSelections();
    this.selectionInterval = setInterval(this.fetchSelections, 5000)
  },
  beforeDestroy(){
    clearInterval(this.selectionInterval);
  },
  data() {
    return {
      selections: undefined,
    };
  },
  computed: {
    groupByMarket() {
      if (this.selections) {
        const opendedSelections = this.selections.filter(
          (selection) => selection.state === "OPENED"
        );
        return groupBy(opendedSelections, "marketName");
      }
      return [];
    },
  },
  methods: {
    async fetchSelections() {
      const response = await axios.get(
        `/events/${this.$route.params.id}/selections`
      );
      this.selections = response.data;
    },
    async addBet(selection) {
      alert(selection.name);
      if (selection) {
        try {
          await axios.post(`bets/add`, {
            selectionId: selection.id,
            selectionOdd: selection.currentOdd,
            bet: 20,
          });
        } catch (error){
          alert(error.response.data.errormessage || error.response.data);
        }
      }
    },
  },
};
</script>
