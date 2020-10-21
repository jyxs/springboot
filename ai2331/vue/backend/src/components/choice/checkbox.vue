<template>
  <div class="app-container">
    <el-table
      ref="multipleTable"
      :data="tableDatas"
      stripe
      border
      style="width: 100%"
      max-height="600"
      @selection-change="handleSelectionChange"
    >
      >
      <el-table-column
        type="selection"
        width="55"
      />
      <el-table-column
        prop="date"
        label="日期"
        width="180"
      />
      <el-table-column
        prop="name"
        label="姓名"
        width="180"
      />
      <el-table-column
        prop="address"
        label="地址"
      />
    </el-table>
  </div>
</template>

<script>
export default {
  props: {
    tableDatas: {
      type: Array,
      default: () => {
        return []
      }
    },
    selected: {
      type: String,
      default: ''
    }
  },
  data() {
    return {
      multipleSelection: []
    }
  },
  mounted() {
    console.info('1111111111111111')
    this.initSelected()
  },
  methods: {
    toggleSelection(rows) {
      console.info(rows)
      if (rows) {
        rows.forEach(row => {
          this.$refs.multipleTable.toggleRowSelection(row)
        })
      } else {
        this.$refs.multipleTable.clearSelection()
      }
    },
    handleSelectionChange(val) {
      this.multipleSelection = val
    },
    initSelected() {
      console.info(this.selected)
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
