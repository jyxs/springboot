<template>
  <div class="app-container">
    <el-table
      ref="refTable"
      :data="tableDatas"
      stripe
      border
      style="width: 100%"
      max-height="600"
      highlight-current-row
      @current-change="handleChange"
    >
      <el-table-column
        prop="id"
        label="ID"
        width="80"
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
  created() {

  },
  mounted() {
    this.initSelected()
  },
  methods: {
    handleChange(val) {
      console.info('1')
      console.info(val)
      // this.$emit('valueChange', val)
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
