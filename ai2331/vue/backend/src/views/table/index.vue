<template>
  <div>
    <Choice :is-single="false" choice-code="corp_all" @valueChange="valueChange" />
  </div>
</template>
<script>
import Choice from '@/components/choice/index'
export default {
  components: {
    Choice
  },
  methods: {
    valueChange(values) {
      console.info(values)
    }
  }
}
</script>
