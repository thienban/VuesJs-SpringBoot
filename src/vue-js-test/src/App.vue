<template>
  <div id="app">
    <div class="home">
    <div class="vue-logo-back">
      <div class="centeralign bg-white" style="height:30px">
        <span class="float-left">
          <a  href="/lives">
                  <img src="./assets/logo-unibet.png" class="header-logo" width="50px" height="50px">
          </a>
        </span>
        <span class="text-dark float-right mr-3">
        <div class="text-secondary mr-3">Login : <span class="font-weight-bold">{{ username }}</span></div>
          <div class="text-secondary mr-3 ">Balance : <span class="font-weight-bold">{{ balance}} € </span></div>
        </span>
        <nav class="navbar navbar-expand-lg navbar-light bg-light">
          <div class="container-fluid">
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
              <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavDropdown">
              <ul class="navbar-nav">
                <li class="nav-item">
                  <router-link tag="a" class="nav-link" to="/lives">En direct</router-link>
                </li>
                <li class="nav-item">
                  <router-link tag="a" class="nav-link" to="/bets">Mes paris</router-link>
                </li>
                </li>
              </ul>
            </div>
          </div>
        </nav>
      </ul>
      </div>

    </div>
    <router-view/>
  </div>
  </div>
</template>
<script>

// @ is an alias to /src
import axios from '@/services/axios.js'

export default {
  name: 'App',
  created() {
    this.fetchBalance();
    this.balanceInterval = setInterval(this.fetchBalance, 5000)
  },
  data() {
    return {
      balanceInterval:undefined,
      username : undefined,
      balance : undefined
    }
  },
  methods:{
    fetchBalance(){
      axios.get('/customers/current').then(res => {
        this.username = res.data.pseudo;
        this.balance = res.data.balance;
      });
    }
  },
  beforeDestroy(){
    clearInterval(this.balanceInterval);
  }
}
</script>
<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}
#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}



.centeralign {
    display: block;
    margin-left: auto;
    margin-right: auto;
}

/* Animation live */
@keyframes shadow-pulse {
  0% {
    box-shadow: 0 0 0 0px rgba(0, 0, 0, 0.2);
  }
  100% {
    box-shadow: 0 0 0 35px rgba(0, 0, 0, 0);
  }
}

.pulse {
  animation: shadow-pulse 1s infinite;
}
</style>
</style>
