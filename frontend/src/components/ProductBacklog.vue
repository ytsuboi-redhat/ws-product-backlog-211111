<template>
  <div class="container backlog-container">
    <h1>Backlog</h1>
    <div class="row p-3">
      <div class="col-sm">
        <div id="result">{{ message }}</div>
        <div>
          <table id="product-backlog" class="table">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">name</th>
                <th scope="col">labels</th>
                <th scope="col">story point</th>
              </tr>
            </thead>
            <draggable tag="tbody" v-model="backlog" @end="end">
              <tr v-for="item in backlog" v-bind:key="item.itemId" v-bind:id="'item-' + item.itemId" @dblclick="routeUpdate(item.itemId)">
                <td class="itemId">{{ item.itemId }}</td>
                <td class="name">{{ item.name }}</td>
                <td class="labels">
                  <span v-for="label in item.labels" v-bind:key="label"><span class="badge badge-primary">{{ label }}</span>&nbsp;</span>
                </td>
                <td class="story-point">{{ item.storyPoint }}</td>
              </tr>
            </draggable>
          </table>
        </div>
      </div>
    </div>
    <div class="row p-3">
      <button id="register-button" class="btn btn-outline-primary" @mouseup="routeRegister">register</button>
      <router-link to="backlog-item">test</router-link>
    </div>
  </div>
</template>

<script>
import backlogData from '@/assets/data/backlog.json'

const draggable = require('vuedraggable')

export default {
  name: 'Backlog',
  props: {
    msg: String
  },
  components: {
    draggable: draggable
  },
  data () {
    return {
      message: '',
      backlog: backlogData
    }
  },
  methods: {
    end () {
      console.log(backlogData)
    },
    routeRegister () {
      this.$router.push('backlog-item')
    },
    routeUpdate (itemId) {
      this.$router.push({
        name: 'backlog-item',
        params: { id: itemId }
      })
    }
  }
}
</script>

<style>
  .backlog-container {
    padding: 3rem 1.5rem;
    text-align: center;
  }
</style>
