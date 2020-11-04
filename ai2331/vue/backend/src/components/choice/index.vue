<template>
  <div>
    <div class="search">
      <el-input v-model="qkey" placeholder="请输入需要查询的内容" class="input-with-select" clearable @keyup.enter.native="loadDatas">
        <el-button slot="append" icon="el-icon-search" @click="loadDatas" />
      </el-input>
    </div>
    <div v-if="isSingle">
      <Radio :choice-datas="choiceDatas" :selected="selected" @valueChange="valueChange" />
    </div>
    <div v-else>
      <CheckBox :choice-datas="choiceDatas" :selected="selected" @valueChange="valueChange" />
    </div>
    <div class="block">
      <el-pagination
        :current-page.sync="pager.currentPage"
        layout="total,sizes, prev, pager, next"
        :total="pager.totalCount"
        :page-size="pager.pageSize"
        :page-sizes="sizes"
        @current-change="handleCurrentChange"
        @size-change="handleSizeChange"
      />
    </div>
  </div>
</template>
<script>
import CheckBox from './checkbox'
import Radio from './radio'

export default {
  components: {
    CheckBox,
    Radio
  },
  props: {
    isSingle: {
      type: Boolean,
      default: true
    },
    selected: {
      type: String,
      default: ''
    },
    choiceCode: {
      type: String,
      required: true
    }
  },
  data() {
    return {
      choiceDatas: {},
      sizes: [10, 20, 30],
      pager: {
        currentPage: 1,
        totalCount: 0,
        pageSize: 10
      },
      qkey: ''
    }
  },
  created() {
    this.loadDatas()
  },
  methods: {
    handleCurrentChange(val) {
      this.pager.currentPage = val
      this.loadDatas()
    },
    handleSizeChange(val) {
      this.pager.pageSize = val
      this.loadDatas()
    },
    loadDatas() {
      this.$store
        .dispatch('common/choice', { choiceCode: this.choiceCode, qkey: this.qkey, currentPage: this.pager.currentPage, pageSize: this.pager.pageSize })
        .then((response) => {
          var { datas } = response
          this.choiceDatas = datas
          var _pager = this.choiceDatas.pager
          this.pager.pageSize = _pager.pageSize
          this.pager.currentPage = _pager.pageNumber
          this.pager.totalCount = _pager.totalNumber
        })
        .catch(() => {
          this.loading = false
        })
    },
    valueChange(values) {
      this.$emit('valueChange', values)
    }
  }
}
</script>
<style scoped>
.block{
  display: flex;
  justify-content: flex-end;
  padding-right: 15px;
}
.search{
  padding-top: 20px;
  padding-left:20px;
  padding-right: 20px;
}
</style>
