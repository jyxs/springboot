<template>
  <div class="app-container">
    <el-table
      ref="multipleTable"
      :data="choiceDatas.tableDatas"
      stripe
      border
      style="width: 100%"
      :max-height="tableH"
      @selection-change="handleSelectionChange"
    >
      <el-table-column
        type="selection"
        width="55"
      />
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
      multipleSelection: [],
      tableH: 600,
      tableHeader: {},
      talbeDatas: {}
    }
  },
  mounted() {
    this.initSelected()
    this.tableH = window.innerHeight - 150
  },
  methods: {
    toggleSelection(rows) {
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row)
        })
      } else {
        this.$refs.multipleTable.clearSelection()
      }
    },
    handleSelectionChange(val) {
      var ids = []
      val.forEach(item => {
        ids.push(item.id)
      })
      this.multipleSelection = val
      this.$emit('valueChange', ids)
    },
    initSelected() {
      if (this.selected) {
        var s = this.selected.split(',')
        for (var i = 0; i < s.length; i++) {
          for (var index in this.tableDatas) {
            var item = this.tableDatas[index]
            if (item.id === s[i]) {
              this.$refs.multipleTable.toggleRowSelection(item)
              break
            }
          }
        }
      }
    }
  }
}
</script>
