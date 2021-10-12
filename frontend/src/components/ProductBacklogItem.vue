<template>
  <div class="container backlog-container">
    <h1>Backlog Item <span v-if="item.itemId">#{{ item.itemId }}</span></h1>
    <div class="row p-3">
      <div class="col-sm">
        <div id="result">{{ message }}</div>
        <div>
          <table id="product-backlog-item" class="table">
            <tr>
              <th scope="row">name</th>
              <td class="name"><input id="name" class="form-control" v-model="item.name"></td>
            </tr>
            <tr>
              <th scope="row">labels</th>
              <td class="labels"><input id="labels" class="form-control" v-model="item.labels"></td>
            </tr>
            <tr>
              <th scope="row">description</th>
              <td class="description"><textarea id="description" class="form-control" v-model="item.description"></textarea></td>
            </tr>
            <tr>
              <th scope="row">story point</th>
              <td class="story-point"><input id="story-point" class="form-control" v-model="item.storyPoint"><span id="story-point-error"></span></td>
            </tr>
            <tr>
              <th scope="row">memo</th>
              <td class="memo"><textarea id="memo" class="form-control" v-model="item.memo"></textarea></td>
            </tr>
            <tr>
              <th scope="row">attachment</th>
              <td class="attachment">
                <img v-bind:src="item.attachment" alt="attachment" v-if="attachmentVisible">
                <input id="attachemnt" type="file">
              </td>
            </tr>
          </table>
        </div>
      </div>
    </div>
    <div class="row p-3">
      <div class="col-sm"></div>
      <div class="col-sm"></div>
      <div class="col-sm">
        <button id="delete-button" class="btn btn-outline-primary">delete</button>
      </div>
      <div class="col-sm">
        <button id="delete-button" class="btn btn-outline-primary">update</button>
      </div>
    </div>
  </div>
</template>

<script>
import backlogData from '@/assets/data/backlog.json'

export default {
  name: 'BacklogItem',
  props: {
    msg: String
  },
  data () {
    return {
      message: '',
      item: null,
      attachmentVisible: false
    }
  },
  beforeMount () {
    let itemId = this.$route.params['id']
    backlogData.forEach(backlogItem => {
      if (backlogItem.itemId === itemId) {
        this.item = backlogItem
      }
    })
    if (this.item === null) {
      this.item = {}
    }
    if (this.item.attachment) {
      this.attachmentVisible = true
    }
  }
}
</script>

<style>
  .backlog-container {
    padding: 3rem 1.5rem;
    text-align: center;
  }
  img {
    padding-bottom: 2rem;
    display: block;
    margin: 0 auto;
  }
</style>
