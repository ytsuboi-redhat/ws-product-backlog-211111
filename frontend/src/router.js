import Vue from 'vue'
import Router from 'vue-router'
import Backlog from '@/components/ProductBacklog'
import BacklogItem from '@/components/ProductBacklogItem'

Vue.use(Router)

export default new Router({
  mode: 'history',
  base: process.env.BASE_URL,
  routes: [
    {
      path: '/backlog',
      name: 'backlog',
      component: Backlog
    },
    {
      path: '/backlog-item',
      name: 'backlog-item',
      component: BacklogItem
    }
  ]
})
