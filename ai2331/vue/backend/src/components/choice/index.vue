<template>
  <div v-if="isSingle">
    <Radio :table-datas="tableDatas" :selected="selected" />
  </div>
  <div v-else>
    <CheckBox :table-datas="tableDatas" :selected="selected" />
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
  }
}
</script>
