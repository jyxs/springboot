<template>
  <div>
    <div v-if="isSingle">
      <Radio :choice-datas="choiceDatas" :selected="selected" :choice-code="choiceCode" />
    </div>
    <div v-else>
      <CheckBox :choice-datas="choiceDatas" :selected="selected" />
    </div>
    <div class="block">
      <el-pagination
        :current-page.sync="currentPage"
        layout="total, prev, pager, next"
        :total="totalCount"
        :page-size="pageSize"
        @current-change="handleCurrentChange"
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
    totalCount: {
      type: Number,
      default: 100
    },
    pageSize: {
      type: Number,
      default: 10
    },
    qkey: {
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
      currentPage: 1,
      choiceDatas: {}
    }
  },
  created() {
    this.$store
      .dispatch('common/choice', this.choiceCode, this.qkey, this.currentPage, this.pageSize)
      .then((response) => {
        var { datas } = response
        this.choiceDatas = datas
      })
      .catch(() => {
        this.loading = false
      })
  },
  methods: {
    handleCurrentChange(val) {
      console.log(`当前页: ${val}`)
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
</style>
