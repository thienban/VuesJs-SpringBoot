import Vue from 'vue'
import Router from 'vue-router'
import LiveHome from './views/LiveHome.vue'
import LiveDetails from './views/LiveDetails.vue'
import BetsHistory from './views/BetsHistory.vue'

Vue.use(Router)

const router =  new Router({
  routes: [
    {
      path: '/',
      redirect: '/lives'
    },
    {
      path: '/lives',
      name: 'live',
      component: LiveHome
    },
    {
      path: '/livedetails/:id',
      name: 'livedetails',
      component: LiveDetails
    },
    {
      path: '/bets',
      name: 'bets',
      component: BetsHistory
    }
  ]
})
export default router
