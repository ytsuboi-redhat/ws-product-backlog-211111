<template>
  <div class="container todo-container">
    <h1>Backlog</h1>
    <div class="row p-3">
      <div class="col-sm">
        <div id="result">{{ message }}</div>
        <div>
          <table id="product-backlog" class="table table-bordered">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">name</th>
                <th scope="col">labels</th>
                <th scope="col">story point</th>
              </tr>
            </thead>
            <draggable tag="tbody" v-model="backlog" @end="end">
              <tr v-for="item in backlog" v-bind:key="item.itemId" v-bind:id="'item-' + item.itemId">
                <td class="itemId">{{ item.itemId }}</td>
                <td class="name">{{ item.name }}</td>
                <td class="labels">
                  <span v-for="label in item.labels" v-bind:key="label"><span class="badge badge-primary">{{ label }}</span>&nbsp;</span>
                </td>
                <td class="story-point">{{ item.storyPoint}}</td>
              </tr>
            </draggable>
          </table>
        </div>
      </div>
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
      // backlog: [{
      //   itemId: 1,
      //   name: 'data_name1',
      //   label: 'data_label',
      //   storyPoint: 5
      // },
      // {
      //   itemId: 2,
      //   name: 'data_name2',
      //   label: 'data_label',
      //   storyPoint: 3
      // },
      // {
      //   itemId: 3,
      //   name: 'data_name3',
      //   label: 'data_label',
      //   storyPoint: 8
      // }]
      backlog: backlogData
    }
  },
  methods: {
    end: () => {
      console.log(backlogData)
    }
  }
}
</script>

<style>
  .todo-container {
    padding: 3rem 1.5rem;
    text-align: center;
  }
</style>
