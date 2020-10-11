<template>
  <lookup :lk-type="lkType" :group-code="groupCode" :codes="codes" :multi-select="multiSelect" />
</template>

<script>
import lookup from '@/components/lookup/index'
export default {
  components: {
    lookup
  },
  data() {
    return {
      lkType: 'select',
      groupCode: 'abc',
      codes: '1',
      multiSelect: false
    }
  }
}
</script>

<style>

</style>
