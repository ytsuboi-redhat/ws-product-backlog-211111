<template>
  <div class="container todo-container">
    <h1>Backlog</h1>
    <div class="row p-3">
      <div class="col-sm">
        <div id="result">{{ message }}</div>
        <div>
          <table id="todo-list" class="table table-bordered">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">name</th>
                <th scope="col">labels</th>
                <th scope="col">story point</th>
              </tr>
            </thead>
            <tbody>
              <tr v-for="item in backlog" :key="item.itemId" v-bind:id="'item-' + item.itemId">
                <td class="index">{{ item.itemId }}</td>
                <td class="name">{{ item.name }}</td>
                <td class="labels">{{ item.label }}</td>
                <td class="story-point">{{ item.storyPoint}}</td>
              </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
// import TodoService from '@/services/TodoService'

export default {
  name: 'Backlog',
  props: {
    msg: String
  },
  data () {
    return {
      message: '',
      backlog: [{
        itemId: 1,
        name: 'data_name1',
        label: 'data_label',
        storyPoint: 5
      },
      {
        itemId: 2,
        name: 'data_name2',
        label: 'data_label',
        storyPoint: 3
      },
      {
        itemId: 3,
        name: 'data_name3',
        label: 'data_label',
        storyPoint: 8
      }]
    }
  },
  methods: {
  }
}
</script>

<style>
  .todo-container {
    padding: 3rem 1.5rem;
    text-align: center;
  }
</style>
