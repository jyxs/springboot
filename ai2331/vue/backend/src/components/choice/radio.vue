<template>
  <div class="app-container">
    <el-table
      ref="refTable"
      :data="choiceDatas.tableDatas"
      fix
      stripe
      border
      style="width: 100%"
      :max-height="tableH"
      highlight-current-row
      @current-change="handleChange"
    >
      <el-table-column
        v-for="data in choiceDatas.headers"
        :key="data.id"
        :prop="data.id"
        :label="data.label"
      />
    </el-table>
  </div>
</template>

<script>
export default {
  props: {
    choiceDatas: {
      type: Object,
      default: null
    },
    selected: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      tableH: 600,
      tableHeader: {},
      talbeDatas: {}
    }
  },
  created() {

  },
  mounted() {
    this.initSelected()
    this.tableH = window.innerHeight - 150
  },
  methods: {
    handleChange(val) {
      this.$emit('valueChange', val.id)
    },
    initSelected() {
      if (this.selected) {
        var s = this.selected.split(',')[0]
        for (var index in this.tableDatas) {
          var item = this.tableDatas[index]
          if (item.id === s) {
            this.$refs.refTable.setCurrentRow(item)
          }
        }
      }
    }
  }
}
</script>
