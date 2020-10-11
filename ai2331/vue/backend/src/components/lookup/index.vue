<template>
  <div class="item">
    --{{ scodes }}--{{ multiSelect }}
    <div v-if="lkType === 'radio'">
      <span :label="labelName">
        <el-radio-group v-model="scodes">
          <el-radio
            v-for="opt in options"
            :key="opt.code"
            :label="opt.code"
            :disabled="!readOnly"
          >{{ opt.name }}</el-radio>
        </el-radio-group>
      </span>
    </div>
    <div v-else-if="lkType === 'checkbox'">
      <span :label="labelName">
        <el-checkbox-group v-model="scodes">
          <el-checkbox
            v-for="opt in options"
            :key="opt.code"
            :label="opt.code"
            :disabled="!readOnly"
          >{{ opt.name }}</el-checkbox>
        </el-checkbox-group>
      </span>
    </div>
    <div v-else-if="lkType === 'select'">
      <span :label="labelName">
        <el-select
          v-model="scodes"
          clearable
          :placeholder="labelName"
          :multiple="multiSelect"
        >
          <el-option
            v-for="opt in options"
            :key="opt.code"
            :label="opt.name"
            :disabled="!readOnly"
            :value="opt.code"
          >{{ opt.name }}</el-option>
        </el-select>
      </span>
    </div>
    <div v-else>name</div>
  </div>
</template>

<script>
export default {
  props: {
    lkType: {
      type: String,
      required: true,
      default: ''
    },
    groupCode: {
      type: String,
      required: true
    },
    codes: {
      type: String,
      default: ''
    },
    readOnly: {
      type: Boolean,
      default: true
    },
    labelName: {
      type: String,
      default: '请选择'
    },
    multiSelect: {
      type: Boolean,
      default: false
    }
  },
  data() {
    return {
      options: [],
      scodes: ''
    }
  },
  created() {
    this.$store
      .dispatch('common/lookup', this.groupCode)
      .then((response) => {
        var { datas } = response
        this.options = datas
        console.info(datas)
      })
      .catch(() => {
        this.loading = false
      })
    this.init()
  },
  methods: {
    init() {
      if (this.lkType === 'checkbox' || this.multiSelect) {
        this.scodes = this.codes.split(',')
      } else {
        this.scodes = this.codes
      }
    }
  }
}
</script>

<style scoped>
.item {
  padding: 0.5rem;
  width: 100%;
  height: 100%;
}
</style>
